package com.ray.template.android.presentation.ui.main.common.gallery

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.os.bundleOf
import com.ray.template.android.presentation.model.gallery.GalleryFolder
import com.ray.template.android.presentation.model.gallery.GalleryImage
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject

class GalleryCursor @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun getPhotoList(
        page: Int,
        loadSize: Int,
        currentLocation: String?
    ): List<GalleryImage> {
        val galleryImageList = mutableListOf<GalleryImage>()

        val collection: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL
            )
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

        val projection = arrayOf(
            MediaStore.Images.ImageColumns._ID,
            MediaStore.Images.ImageColumns.DATA,
            MediaStore.Images.ImageColumns.DISPLAY_NAME,
            MediaStore.Images.ImageColumns.DATE_TAKEN
        )

        val sortedOrder = MediaStore.Images.ImageColumns.DATE_TAKEN

        val selection: String? = currentLocation?.let {
            "${MediaStore.Images.Media.DATA} LIKE ?"
        }

        val selectionArgument: Array<String>? = currentLocation?.let {
            arrayOf("%$currentLocation%")
        }

        val offset = (page - 1) * loadSize

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
            val bundle = bundleOf(
                ContentResolver.QUERY_ARG_OFFSET to offset,
                ContentResolver.QUERY_ARG_LIMIT to loadSize,
                ContentResolver.QUERY_ARG_SORT_COLUMNS to arrayOf(MediaStore.Files.FileColumns.DATE_MODIFIED),
                ContentResolver.QUERY_ARG_SORT_DIRECTION to ContentResolver.QUERY_SORT_DIRECTION_DESCENDING,
                ContentResolver.QUERY_ARG_SQL_SELECTION to selection,
                ContentResolver.QUERY_ARG_SQL_SELECTION_ARGS to selectionArgument,
            )
            context.contentResolver.query(
                collection,
                projection,
                bundle,
                null
            )
        } else {
            context.contentResolver.query(
                collection,
                projection,
                selection,
                selectionArgument,
                "$sortedOrder DESC LIMIT $loadSize OFFSET $offset",
            )
        }?.use { cursor ->
            while (cursor.moveToNext()) {
                val id = cursor.getColumnIndex(MediaStore.Images.ImageColumns._ID)
                    .takeIf { it != -1 }
                    ?.let { cursor.getLong(it) }
                    ?: continue

                val name = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DISPLAY_NAME)
                    .takeIf { it != -1 }
                    ?.let { cursor.getString(it) }
                    ?: continue

                val filePath = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                    .takeIf { it != -1 }
                    ?.let { cursor.getString(it) }
                    ?: continue

                val date = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATE_TAKEN)
                    .takeIf { it != -1 }
                    ?.let { cursor.getString(it) }
                    ?: continue

                val image = GalleryImage(
                    id = id,
                    filePath = filePath,
                    name = name,
                    date = date
                )
                galleryImageList.add(image)
            }
        }
        return galleryImageList
    }

    fun getFolderList(): List<GalleryFolder> {
        val folderList: ArrayList<GalleryFolder> = arrayListOf(GalleryFolder("최근 항목", ""))

        val collection: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(
                MediaStore.VOLUME_EXTERNAL
            )
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }

        val projection = arrayOf(
            MediaStore.Images.Media.DATA
        )

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.Q) {
            context.contentResolver.query(
                collection,
                projection,
                bundleOf(),
                null
            )
        } else {
            context.contentResolver.query(
                collection,
                projection,
                null,
                null,
                null
            )
        }?.use { cursor ->
            while (cursor.moveToNext()) {
                val filePath = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                    .takeIf { it != -1 }
                    ?.let { cursor.getString(it) }
                    ?: continue

                File(filePath).parentFile?.let { parentFile ->
                    val folder = GalleryFolder(
                        name = parentFile.name,
                        location = parentFile.path
                    )

                    folderList.find {
                        it.location == folder.location
                    } ?: folderList.add(folder)
                }
            }
        }
        return folderList
    }
}
