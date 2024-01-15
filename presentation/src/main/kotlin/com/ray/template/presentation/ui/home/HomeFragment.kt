package com.ray.template.presentation.ui.home

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.MotionEvent
import android.view.View
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.mlkit.vision.MlKitAnalyzer
import androidx.camera.view.CameraController.COORDINATE_SYSTEM_VIEW_REFERENCED
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.korean.KoreanTextRecognizerOptions
import com.ray.template.presentation.common.base.BaseFragment
import com.ray.template.presentation.common.util.coroutine.event.eventObserve
import com.ray.template.presentation.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override val viewModel: HomeViewModel by viewModels()
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var textRecognizer: TextRecognizer
    private lateinit var cameraController: LifecycleCameraController

    override fun initView() {
        bind {
            vm = viewModel
            lifecycleOwner = this@HomeFragment

            cameraExecutor = Executors.newSingleThreadExecutor()
            startCamera()
        }
    }

    private fun startCamera() {
        cameraController = LifecycleCameraController(requireContext())
        val previewView: PreviewView = binding.body

        val options = KoreanTextRecognizerOptions.Builder()
            .setExecutor(cameraExecutor)
            .build()
        textRecognizer = TextRecognition.getClient(options)

        cameraController.setImageAnalysisAnalyzer(
            ContextCompat.getMainExecutor(requireContext()),
            MlKitAnalyzer(
                listOf(textRecognizer),
                COORDINATE_SYSTEM_VIEW_REFERENCED,
                ContextCompat.getMainExecutor(requireContext())
            ) { result: MlKitAnalyzer.Result? ->
                val textResult = result?.getValue(textRecognizer)
                if ((textResult == null) ||
                    (textResult.textBlocks.isEmpty())
                ) {
                    previewView.overlay.clear()
                    previewView.setOnTouchListener { _, _ -> false } //no-op
                    return@MlKitAnalyzer
                }

//                println(textResult.textBlocks.map { it.text }.joinToString(separator = " | "))

                println(
                    textResult.textBlocks.map { "text : ${it.text}, position : ${it.boundingBox}" }.joinToString(
                        prefix = "{\n",
                        postfix = "}",
                        separator = ",\n"
                    )
                )
                previewView.setOnClickListener {

                }
//                val qrCodeViewModel = QrCodeViewModel(textResult.textBlocks)
//                val qrCodeDrawable = QrCodeDrawable(qrCodeViewModel)

//                previewView.setOnTouchListener(qrCodeViewModel.qrCodeTouchCallback)
//                previewView.overlay.clear()
//                previewView.overlay.add(qrCodeDrawable)
            }
        )

        cameraController.bindToLifecycle(this)
        previewView.controller = cameraController
    }

    override fun initObserver() {

        repeatOnStarted {
            viewModel.event.eventObserve { event ->
                when (event) {
                    HomeViewEvent.Confirm -> {
                        println("asdfasdf 클릭")
                        cameraController.takePicture(
                            cameraExecutor,
                            object : ImageCapture.OnImageCapturedCallback() {
                                @OptIn(ExperimentalGetImage::class)
                                override fun onCaptureSuccess(image: ImageProxy) {
                                    println("asdfasdf 캡쳐")

                                    val recognizer = TextRecognition.getClient(KoreanTextRecognizerOptions.Builder().build())
                                    val _image = InputImage.fromMediaImage(image.image!!, image.imageInfo.rotationDegrees)

                                    recognizer.process(_image)
                                        .addOnSuccessListener { visionText ->
                                            // Task completed successfully
                                            // ...
                                            println(
                                                visionText.textBlocks.map { "text : ${it.text}, position : ${it.boundingBox}" }.joinToString(
                                                    prefix = "{\n",
                                                    postfix = "}",
                                                    separator = ",\n"
                                                )
                                            )
                                            showMessageSnackBar(
                                                message = "Success"
                                            )
                                            println("asdfasdf 분석")
                                        }
                                        .addOnFailureListener { e ->
                                            // Task failed with an exception
                                            // ...
                                            println(e.message)
                                        }

                                    super.onCaptureSuccess(image)
                                }

                                override fun onError(exception: ImageCaptureException) {
                                    super.onError(exception)
                                    println("onError")
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
        textRecognizer.close()
    }
}

class QrCodeViewModel(barcode: Barcode) {
    var boundingRect: Rect = barcode.boundingBox!!
    var qrContent: String = ""
    var qrCodeTouchCallback = { v: View, e: MotionEvent -> false } //no-op

    init {
        when (barcode.valueType) {
            Barcode.TYPE_URL -> {
                qrContent = barcode.url!!.url!!
                qrCodeTouchCallback = { v: View, e: MotionEvent ->
                    if (e.action == MotionEvent.ACTION_DOWN && boundingRect.contains(e.getX().toInt(), e.getY().toInt())) {
                        val openBrowserIntent = Intent(Intent.ACTION_VIEW)
                        openBrowserIntent.data = Uri.parse(qrContent)
                        v.context.startActivity(openBrowserIntent)
                    }
                    true // return true from the callback to signify the event was handled
                }
            }
            // Add other QR Code types here to handle other types of data,
            // like Wifi credentials.
            else -> {
                qrContent = "Unsupported data type: ${barcode.rawValue.toString()}"
            }
        }
    }
}

class QrCodeDrawable(qrCodeViewModel: QrCodeViewModel) : Drawable() {
    private val boundingRectPaint = Paint().apply {
        style = Paint.Style.STROKE
        color = Color.YELLOW
        strokeWidth = 5F
        alpha = 200
    }

    private val contentRectPaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.YELLOW
        alpha = 255
    }

    private val contentTextPaint = Paint().apply {
        color = Color.DKGRAY
        alpha = 255
        textSize = 36F
    }

    private val qrCodeViewModel = qrCodeViewModel
    private val contentPadding = 25
    private var textWidth = contentTextPaint.measureText(qrCodeViewModel.qrContent).toInt()

    override fun draw(canvas: Canvas) {
        canvas.drawRect(qrCodeViewModel.boundingRect, boundingRectPaint)
        canvas.drawRect(
            Rect(
                qrCodeViewModel.boundingRect.left,
                qrCodeViewModel.boundingRect.bottom + contentPadding / 2,
                qrCodeViewModel.boundingRect.left + textWidth + contentPadding * 2,
                qrCodeViewModel.boundingRect.bottom + contentTextPaint.textSize.toInt() + contentPadding
            ),
            contentRectPaint
        )
        canvas.drawText(
            qrCodeViewModel.qrContent,
            (qrCodeViewModel.boundingRect.left + contentPadding).toFloat(),
            (qrCodeViewModel.boundingRect.bottom + contentPadding * 2).toFloat(),
            contentTextPaint
        )
    }

    override fun setAlpha(alpha: Int) {
        boundingRectPaint.alpha = alpha
        contentRectPaint.alpha = alpha
        contentTextPaint.alpha = alpha
    }

    override fun setColorFilter(colorFiter: ColorFilter?) {
        boundingRectPaint.colorFilter = colorFilter
        contentRectPaint.colorFilter = colorFilter
        contentTextPaint.colorFilter = colorFilter
    }

    @Deprecated("Deprecated in Java")
    override fun getOpacity(): Int = PixelFormat.TRANSLUCENT
}
