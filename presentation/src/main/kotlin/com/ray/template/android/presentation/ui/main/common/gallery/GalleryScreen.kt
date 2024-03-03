package com.ray.template.android.presentation.ui.main.common.gallery

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.holix.android.bottomsheetdialog.compose.BottomSheetBehaviorProperties
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialogProperties
import com.ray.template.android.common.util.coroutine.event.MutableEventFlow
import com.ray.template.android.common.util.coroutine.event.eventObserve
import com.ray.template.android.presentation.R
import com.ray.template.android.presentation.common.theme.Body1
import com.ray.template.android.presentation.common.theme.Gray900
import com.ray.template.android.presentation.common.theme.Headline1
import com.ray.template.android.presentation.common.theme.Headline3
import com.ray.template.android.presentation.common.theme.Space20
import com.ray.template.android.presentation.common.theme.Space24
import com.ray.template.android.presentation.common.theme.Space56
import com.ray.template.android.presentation.common.theme.Space8
import com.ray.template.android.presentation.common.theme.Space80
import com.ray.template.android.presentation.common.theme.White
import com.ray.template.android.presentation.common.util.compose.LaunchedEffectWithLifecycle
import com.ray.template.android.presentation.common.view.BottomSheetScreen
import com.ray.template.android.presentation.common.view.RippleBox
import com.ray.template.android.presentation.model.gallery.GalleryFolder
import com.ray.template.android.presentation.model.gallery.GalleryImage
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.plus

@Composable
fun GalleryScreen(
    navController: NavController,
    onDismissRequest: () -> Unit,
    onResult: (GalleryImage) -> Unit,
    viewModel: GalleryViewModel = hiltViewModel()
) {
    val argument: GalleryArgument = Unit.let {
        val state by viewModel.state.collectAsStateWithLifecycle()

        GalleryArgument(
            state = state,
            event = viewModel.event,
            intent = viewModel::onIntent,
            logEvent = viewModel::logEvent,
            handler = viewModel.handler
        )
    }

    val data: GalleryData = Unit.let {
        val folderList by viewModel.folderList.collectAsStateWithLifecycle()
        val galleryImageList = viewModel.galleryImageList.collectAsLazyPagingItems()

        GalleryData(
            folderList = folderList,
            galleryImageList = galleryImageList
        )
    }

    GalleryScreen(
        navController = navController,
        argument = argument,
        data = data,
        onDismissRequest = onDismissRequest,
        onResult = onResult
    )
}

@Composable
private fun GalleryScreen(
    navController: NavController,
    argument: GalleryArgument,
    data: GalleryData,
    onDismissRequest: () -> Unit,
    onResult: (GalleryImage) -> Unit,
) {
    val (state, event, intent, logEvent, handler) = argument
    val scope = rememberCoroutineScope() + handler
    val localConfiguration = LocalConfiguration.current

    val perMissionAlbumLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            intent(GalleryIntent.OnGrantPermission)
        } else {
            onDismissRequest()
        }
    }
    var currentSelectedId: Long by remember { mutableLongStateOf(-1) }
    var isDropDownMenuExpanded: Boolean by remember { mutableStateOf(false) }
    var currentFolder: GalleryFolder by remember { mutableStateOf(GalleryFolder.recent) }

    BottomSheetScreen(
        onDismissRequest = onDismissRequest,
        properties = BottomSheetDialogProperties(
            behaviorProperties = BottomSheetBehaviorProperties(
                state = BottomSheetBehaviorProperties.State.Expanded,
                skipCollapsed = true
            )
        )
    ) {
        Column(
            modifier = Modifier
                .background(White)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Space56),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(Space20))
                RippleBox(
                    onClick = {
                        onDismissRequest()
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(Space24),
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = null,
                        tint = Gray900
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                RippleBox(
                    onClick = {
                        isDropDownMenuExpanded = !isDropDownMenuExpanded
                    }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = currentFolder.name,
                            style = Headline1
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_chevron_down),
                            contentDescription = null,
                            modifier = Modifier.size(Space24),
                            tint = Gray900
                        )
                        DropdownMenu(
                            modifier = Modifier
                                .requiredSizeIn(maxHeight = localConfiguration.screenHeightDp.dp - Space80)
                                .width(localConfiguration.screenWidthDp.dp * 2 / 5)
                                .background(White),
                            expanded = isDropDownMenuExpanded,
                            onDismissRequest = { isDropDownMenuExpanded = false },
                        ) {
                            data.folderList.mapIndexed { index, folder ->
                                DropdownMenuItem(
                                    text = {
                                        Column(
                                            modifier = Modifier.fillMaxSize()
                                        ) {
                                            Row(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .padding(horizontal = Space8),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Text(
                                                    text = folder.name,
                                                    style = Body1.merge(Gray900)
                                                )
                                                Spacer(modifier = Modifier.weight(1f))
                                                if (folder.name == currentFolder.name) {
                                                    Icon(
                                                        painter = painterResource(R.drawable.ic_check_box_checked),
                                                        contentDescription = null,
                                                        tint = Gray900
                                                    )
                                                }
                                            }
                                        }
                                    },

                                    onClick = {
                                        currentFolder = folder
                                        intent(GalleryIntent.OnChangeFolder(folder))
                                        isDropDownMenuExpanded = false
                                    },
                                    contentPadding = PaddingValues(0.dp)
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                if (currentSelectedId > 0) {
                    RippleBox(
                        onClick = {
                            if (currentSelectedId > 0) {
                                data.galleryImageList.itemSnapshotList
                                    .find { image ->
                                        image?.id == currentSelectedId
                                    }
                                    ?.let { currentImage ->
                                        onDismissRequest()
                                        onResult(currentImage)
                                    }
                            }
                        }
                    ) {
                        Text(
                            text = "확인",
                            style = Headline3.merge(Gray900)
                        )
                    }
                } else {
                    Text(
                        text = "확인",
                        style = Headline3.merge(Gray900)
                    )
                }

                Spacer(modifier = Modifier.width(Space20))
            }

            Box(Modifier.fillMaxSize()) {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(1.dp),
                    horizontalArrangement = Arrangement.spacedBy(1.dp)
                ) {
                    items(data.galleryImageList.itemCount) { index ->
                        data.galleryImageList[index]?.let { gallery ->
                            GalleryItemContent(
                                galleryImage = gallery,
                                currentSelectedImageId = currentSelectedId,
                                onSelectImage = {
                                    currentSelectedId = gallery.id
                                },
                                onDeleteImage = {
                                    currentSelectedId = -1
                                }
                            )
                        }
                    }
                }
            }
        }

        LaunchedEffect(Unit) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                perMissionAlbumLauncher.launch(
                    Manifest.permission.READ_MEDIA_IMAGES
                )
            } else {
                perMissionAlbumLauncher.launch(
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            }
        }

        LaunchedEffectWithLifecycle(event, handler) {
            event.eventObserve { event ->

            }
        }
    }
}

@Preview(apiLevel = 34)
@Composable
private fun GalleryScreenPreview() {
    GalleryScreen(
        navController = rememberNavController(),
        argument = GalleryArgument(
            state = GalleryState.Init,
            event = MutableEventFlow(),
            intent = {},
            logEvent = { _, _ -> },
            handler = CoroutineExceptionHandler { _, _ -> }
        ),
        data = GalleryData(
            folderList = listOf(),
            galleryImageList = MutableStateFlow<PagingData<GalleryImage>>(PagingData.empty()).collectAsLazyPagingItems()
        ),
        onDismissRequest = {},
        onResult = {}
    )
}
