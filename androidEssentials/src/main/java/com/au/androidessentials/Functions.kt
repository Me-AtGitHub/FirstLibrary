package com.au.androidessentials

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.location.LocationManager
import android.net.ConnectivityManager
import android.os.Build
import android.os.Environment
import android.util.DisplayMetrics
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.io.File
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.roundToInt

class Functions {

    companion object {

        private const val JPEG_FILE_PREFIX = "IMG_"
        private const val JPEG_FILE_SUFFIX = ".jpg"

        fun ImageView.load(data: Any, placeholder: Int? = null) {
            if (placeholder == null)
                Glide.with(this).load(data).into(this)
            else Glide.with(this).load(data).placeholder(placeholder).into(this)
        }

        fun ImageView.load(data: Any, placeholder: Drawable? = null) {
            if (placeholder == null)
                Glide.with(this).load(data).into(this)
            else Glide.with(this).load(data).placeholder(placeholder).into(this)
        }


        fun ImageView.loadCircleCrop(data: Any, placeholder: Int? = null) {
            if (placeholder == null)
                Glide.with(this).load(data).apply(RequestOptions.circleCropTransform()).into(this)
            else Glide.with(this).load(data).apply(RequestOptions.circleCropTransform())
                .placeholder(placeholder).into(this)
        }

        fun ImageView.loadCircleCrop(data: Any, placeholder: Drawable? = null) {
            if (placeholder == null)
                Glide.with(this).load(data).apply(RequestOptions.circleCropTransform()).into(this)
            else Glide.with(this).load(data).apply(RequestOptions.circleCropTransform())
                .placeholder(placeholder).into(this)
        }


        fun ImageView.loadCenterCrop(data: Any, placeholder: Drawable? = null) {
            if (placeholder == null)
                Glide.with(this).load(data).apply(RequestOptions.centerCropTransform()).into(this)
            else Glide.with(this).load(data).apply(RequestOptions.centerCropTransform())
                .placeholder(placeholder).into(this)
        }

        fun ImageView.loadCenterCrop(data: Any, placeholder: Int? = null) {
            if (placeholder == null)
                Glide.with(this).load(data).apply(RequestOptions.centerCropTransform()).into(this)
            else Glide.with(this).load(data).apply(RequestOptions.centerCropTransform())
                .placeholder(placeholder).into(this)
        }


        fun ImageView.loadCenterInside(data: Any, placeholder: Int? = null) {
            if (placeholder == null) Glide.with(this).load(data)
                .apply(RequestOptions.centerInsideTransform()).into(this)
            else Glide.with(this).load(data).apply(RequestOptions.centerInsideTransform())
                .placeholder(placeholder).into(this)
        }

        fun ImageView.loadCenterInside(data: Any, placeholder: Drawable? = null) {
            if (placeholder == null) Glide.with(this).load(data)
                .apply(RequestOptions.centerInsideTransform()).into(this)
            else Glide.with(this).load(data).apply(RequestOptions.centerInsideTransform())
                .placeholder(placeholder).into(this)
        }


        fun ImageView.loadFitCenter(data: Any, placeholder: Int? = null) {
            if (placeholder == null) Glide.with(this).load(data)
                .apply(RequestOptions.fitCenterTransform()).into(this)
            else Glide.with(this).load(data).apply(RequestOptions.fitCenterTransform())
                .placeholder(placeholder).into(this)
        }

        fun ImageView.loadFitCenter(data: Any, placeholder: Drawable? = null) {
            if (placeholder == null) Glide.with(this).load(data)
                .apply(RequestOptions.fitCenterTransform()).into(this)
            else Glide.with(this).load(data).apply(RequestOptions.fitCenterTransform())
                .placeholder(placeholder).into(this)
        }


        fun Fragment.replaceFragment(
            layoutHostId: Int,
            fragment: Fragment,
            isBackstack: Boolean = true,
            animations: PairAnim? = null
        ) {
            try {
                this.parentFragmentManager.beginTransaction().let {
                    if (animations != null)
                        it.setCustomAnimations(
                            animations.enter,
                            animations.exit,
                            animations.popEnter,
                            animations.popExit
                        )
                    if (isBackstack)
                        it.replace(layoutHostId, fragment)
                            .addToBackStack(fragment::class.java.canonicalName).commit()
                    else
                        it.replace(layoutHostId, fragment).commit()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun Fragment.addFragment(
            layoutHostId: Int,
            fragment: Fragment,
            animations: PairAnim? = null
        ) {
            try {
                this.parentFragmentManager.beginTransaction().let {
                    if (animations != null)
                        it.setCustomAnimations(
                            animations.enter,
                            animations.exit,
                            animations.popEnter,
                            animations.popExit
                        )
                    it.replace(layoutHostId, fragment).commit()
                }
            } catch (e: Exception) {
            }
        }


        fun String.shortToast(context:Context){
            Toast.makeText(context,this,Toast.LENGTH_SHORT)
        }

        fun String.longToast(context:Context){
            Toast.makeText(context,this,Toast.LENGTH_LONG)
        }

        @SuppressLint("SimpleDateFormat")
        fun String.changeDateFormat(fromFormat:String, toFormat:String):String{
            return SimpleDateFormat(toFormat).format(SimpleDateFormat(fromFormat).parse(this)!!)
        }

        fun Int.dpToPx(context: Context):Int{
            val displayMetrics = context.resources.displayMetrics
            return (this * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
        }


        fun getImageFile(imageDirectoryPath:String): File?{
            var imageFile:File? = null
            if(Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()){
                val storageDir = File(imageDirectoryPath)
                if(!storageDir.mkdirs())
                    if(!storageDir.exists())
                        return null
                imageFile = File.createTempFile(JPEG_FILE_PREFIX + System.currentTimeMillis() + "_", JPEG_FILE_SUFFIX, storageDir)
            }
            return imageFile
        }


        // converts "yyyy-MM-ddTHH:mm:dd.SSZ" date format to "yyyy-MM-dd HH:mm"

        @RequiresApi(Build.VERSION_CODES.O)
        fun String.toLocalDateTime(): String {
            return OffsetDateTime.parse(this).toZonedDateTime()
                .withZoneSameInstant(TimeZone.getDefault().toZoneId())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        }

        @SuppressLint("MissingPermission")
        fun phoneIsOnline(context: Context): Boolean {
            val cm =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            return netInfo != null && netInfo.isConnectedOrConnecting
        }

        fun isLocationEnabled(context: Context): Boolean {
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        }

    }
}