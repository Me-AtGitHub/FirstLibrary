/*
package com.au.androidessentials

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.drawable.ColorDrawable
import android.media.ExifInterface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.webkit.MimeTypeMap
import android.widget.AbsListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.sharedapp.R
import com.sharedapp.models.ErrorResponse
import com.sharedapp.network.RestClient
import okhttp3.ResponseBody
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern


object CommonMethod {

    var dialog: Dialog? = null


    */
/*
       * check if email is valid
        *//*

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    fun getFirebaseToken(
        handle: (token: String) -> Unit
    ) {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.e("TAG", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }


            Log.e("TAG", "Fetching FCM registration token :${task.result}")

            handle(task.result)
        })
    }


    fun validatePassword(pass: String): Boolean {
//        val passWordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*)(?=.*[@$!%*?&])[A-Za-z@$!%*?&]{8,}$"
        val passWordPattern = "^(?=.{8,})(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=]).*\$"
        val pattern = Pattern.compile(passWordPattern)
        val matcher = pattern.matcher(pass)

        return matcher.matches()
    }



    */
/* Create Full Screen View*//*

    fun fullScreenView(context: Activity) {

        context.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

    }


    */
/* add Fragment Splash Activity*//*

    fun putFragmentInSplashActivityBackStack(
        fragmentManager: FragmentManager, fragment: Fragment, anim: AnimationEnum?,
        isAdd: Boolean = false, isBackStackEnable: Boolean = false
    ) {
        */
/* Defining fragment transaction *//*

        try {
            with(fragmentManager.beginTransaction()) {


                when (anim) {

                    AnimationEnum.FIO -> {

                        setCustomAnimations(
                            android.R.anim.fade_in,
                            android.R.anim.fade_out
                        )

                    }

                    AnimationEnum.RTL -> {

                        setCustomAnimations(
                            R.anim.enter_from_right,
                            R.anim.exit_from_left,
                            R.anim.enter_from_left,
                            R.anim.exit_from_right
                        )

                    }

                    AnimationEnum.LTR -> {

                        setCustomAnimations(
                            R.anim.enter_from_left,
                            R.anim.exit_from_right,
                            R.anim.enter_from_right,
                            R.anim.exit_from_left
                        )

                    }


                }

                if (isAdd) {


                    add(R.id.splashActivityLayout, fragment)
                } else {

                    replace(R.id.splashActivityLayout, fragment)
                }


                if (isBackStackEnable) {
                    addToBackStack("")
                }




                commit()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    */
/* add Fragment Common Activity *//*

    fun putFragmentInCommonActivityBackStack(
        fragmentManager: FragmentManager, fragment: Fragment, anim: AnimationEnum?,
        isAdd: Boolean = false, isBackStackEnable: Boolean = false
    ) {

        try {
            with(fragmentManager.beginTransaction()) {

                when (anim) {

                    AnimationEnum.FIO -> {

                        setCustomAnimations(
                            android.R.anim.fade_in,
                            android.R.anim.fade_out
                        )

                    }

                    AnimationEnum.RTL -> {

                        setCustomAnimations(
                            R.anim.enter_from_right,
                            R.anim.exit_from_left,
                            R.anim.enter_from_left,
                            R.anim.exit_from_right
                        )

                    }

                    AnimationEnum.LTR -> {

                        setCustomAnimations(
                            R.anim.enter_from_left,
                            R.anim.exit_from_right,
                            R.anim.enter_from_right,
                            R.anim.exit_from_left
                        )

                    }


                }

                if (isAdd) {


                    add(R.id.fragmentContainer, fragment)
                } else {

                    replace(R.id.fragmentContainer, fragment)
                }


                if (isBackStackEnable) {
                    addToBackStack("")
                }


                commit()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    */
/* Show Toast *//*

    fun showToast(msg: String) { Toast.makeText(Global.instance, msg, Toast.LENGTH_SHORT).show() }

    */
/* Show Error Message *//*

    fun showErrorMessage(responseBody: ResponseBody) {

        val errorConverter = RestClient.getRetrofitInstance()
            .responseBodyConverter<ErrorResponse>(
                ErrorResponse::class.java,
                arrayOfNulls<Annotation>(0)
            )


        val errorRes: ErrorResponse? = errorConverter.convert(responseBody)

        showToast(errorRes!!.error_description)

    }


    */
/* Show Progressbar *//*

    fun showProgressDialog(context: Context) {
        dialog = Dialog(context)
        dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog!!.setCancelable(false)
        dialog!!.setContentView(R.layout.dialog_progress_bar)
        dialog!!.show()
    }

    */
/* Dismiss Progress Dialog *//*

    fun dismissProgressDialog() {
        if (dialog != null)
            dialog!!.dismiss()
    }


    */
/* Common Dialog *//*

    fun showDialog(context: Context, type: DialogType, callBack: (Boolean) -> Unit) {

        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custome_dialog)
        val window = dialog.window
        window!!.setLayout(
            AbsListView.LayoutParams.MATCH_PARENT,
            AbsListView.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val title = dialog.findViewById(R.id.titleTV) as TextView
        val text = dialog.findViewById<View>(R.id.message_contTV) as TextView
        var msgContainer: String? = null
        var dialogTitle: String? = null

        if (type == DialogType.LOGOUT) {
            msgContainer = context.getString(R.string.log_out_message)
            dialogTitle = context.getString(R.string.log_out)
        }

        title.text = dialogTitle
        text.text = msgContainer


        val negativeBtn: TextView = dialog.findViewById<View>(R.id.negative) as TextView
        val positiveBtn: TextView = dialog.findViewById<View>(R.id.positive) as TextView

        positiveBtn.setOnClickListener {
            dialog.dismiss()
            callBack(true)

        }

        negativeBtn.setOnClickListener {
            dialog.dismiss()
            callBack(false)
        }



        dialog.show()


    }

    */
/* Log out *//*

    fun logout() {

        prefs.clear()
    }


    fun setUpPhotoFile(mContext: Context): File? {
        var imageF: File? = null
        val directoryPath =
            mContext.getExternalFilesDir(null)!!.absolutePath + File.separator + "POCKET"
        try {
            if (Environment.MEDIA_MOUNTED == Environment
                    .getExternalStorageState()
            ) {
                val storageDir = File(directoryPath).parentFile

                if (storageDir != null) {
                    if (!storageDir.mkdirs()) {
                        if (!storageDir.exists()) {
                            Log.d("CameraSample", "failed to create directory")
                            return null
                        }
                    }
                }
                imageF = File.createTempFile(
                    Constants.JPEG_FILE_PREFIX
                            + System.currentTimeMillis() + "_",
                    Constants.JPEG_FILE_SUFFIX, storageDir
                )
            } else {
                Log.v(
                    mContext.getString(R.string.app_name),
                    "External storage is not mounted READ/WRITE."
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return imageF
    }   // end setUpPhotoFile


    fun getFile(imgPath: String?): Bitmap? {
        val mOrientation: Int
        var bMapRotate: Bitmap? = null
        try {
            if (imgPath != null) {
                val exif = ExifInterface(imgPath)
                mOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1)

                val options = BitmapFactory.Options()
                options.inJustDecodeBounds = true
                BitmapFactory.decodeFile(imgPath, options)
                options.inSampleSize = calculateInSampleSize(options, 400, 400)
                options.inJustDecodeBounds = false
                bMapRotate = BitmapFactory.decodeFile(imgPath, options)
                if (mOrientation == 6) {
                    val matrix = Matrix()
                    matrix.postRotate(90f)
                    bMapRotate = Bitmap.createBitmap(
                        bMapRotate!!, 0, 0,
                        bMapRotate.width, bMapRotate.height,
                        matrix, true
                    )
                } else if (mOrientation == 8) {
                    val matrix = Matrix()
                    matrix.postRotate(270f)
                    bMapRotate = Bitmap.createBitmap(
                        bMapRotate!!, 0, 0,
                        bMapRotate.width, bMapRotate.height,
                        matrix, true
                    )
                } else if (mOrientation == 3) {
                    val matrix = Matrix()
                    matrix.postRotate(180f)
                    bMapRotate = Bitmap.createBitmap(
                        bMapRotate!!, 0, 0,
                        bMapRotate.width, bMapRotate.height,
                        matrix, true
                    )
                }
            }
        } catch (e: OutOfMemoryError) {
            bMapRotate = null
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            bMapRotate = null
            e.printStackTrace()
        }
        return bMapRotate
    }

    private fun calculateInSampleSize(
        options: BitmapFactory.Options,
        reqWidth: Int,
        reqHeight: Int
    ): Int {
        try {
            val height = options.outHeight
            val width = options.outWidth
            var inSampleSize = 1

            if (height > reqHeight || width > reqWidth) {

                val halfHeight = height / 2
                val halfWidth = width / 2

                while (halfHeight / inSampleSize > reqHeight && halfWidth / inSampleSize > reqWidth) {
                    inSampleSize *= 2
                }
            }

            return inSampleSize
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return 0
    }

    fun saveImageToExternalStorage(finalBitmap: Bitmap, context: Context): File {
        val file: File

        val root1 = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString()
        val myDir = File("$root1/PICS")
        myDir.mkdirs()
        val name = (Constants.JPEG_FILE_PREFIX + System.currentTimeMillis()
                + Constants.JPEG_FILE_SUFFIX)
        file = File(myDir, name)
        if (file.exists())
            file.delete()
        try {
            val out = FileOutputStream(file)
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.flush()
            out.close()
            return file
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return file
    }


    fun circularPlaceHolder(context: Context): CircularProgressDrawable {
        val circularProgressDrawable =
            CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.colorSchemeColors
        circularProgressDrawable.start()
        return circularProgressDrawable
    }

    @SuppressLint("SimpleDateFormat")
    fun setDateWithPattern(milliSeconds: Long, pattern: String): String {
        val formatter = SimpleDateFormat(pattern)
        formatter.timeZone = TimeZone.getDefault()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }


    */
/* get Random String *//*

    fun getRandomString(length: Int): String {
        val allowedChars = ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }


    fun getFileNameByUri(context: Context, uri: Uri): String? {
        var fileName = "unknown" //default fileName
        var filePathUri = uri
        if (uri.scheme.toString().compareTo("content") == 0) {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            if (cursor!!.moveToFirst()) {
                val column_index =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA) //Instead of "MediaStore.Images.Media.DATA" can be used "_data"
                filePathUri = Uri.parse(cursor.getString(column_index))
                fileName = filePathUri.lastPathSegment.toString()
            }
        } else if (uri.scheme!!.compareTo("file") == 0) {
            fileName = filePathUri.lastPathSegment.toString()
        } else {
            fileName = fileName + "_" + filePathUri.lastPathSegment
        }
        return fileName
    }


    fun backgroundImageColor(firstChar: String): Int {
        val colorName: Int = when (getNameString(firstChar)) {
            "pinkColor" -> R.color.pinkColor
            "orangeColor" -> R.color.orangeColor
            "yellowColorImg" -> R.color.yellowColorImg
            "greenColorImg" -> R.color.greenColorImg
            "blueColor" -> R.color.blueColor
            "greyColor" -> R.color.greyColor
            else -> R.color.purpleColor
        }

        return colorName
    }

    private fun getNameString(name: String): String {
        return when (name.lowercase()) {
            "a", "g", "m", "s", "y" -> "pinkColor"
            "b", "h", "n", "t", "z" -> "orangeColor"
            "c", "i", "o", "u" -> "yellowColorImg"
            "d", "j", "p", "v" -> "greenColorImg"
            "e", "k", "q", "w" -> "blueColor"
            ",", ".", "(", ")", "&", ";", "*", "!", "-", ":", "/" -> "greyColor"
            else -> "purpleColor"
        }
    }


    */
/* Write Media File in temp file *//*

    fun saveFileInTemp(context: Context, fileUri: Uri): File {

        val id = DocumentsContract.getDocumentId(fileUri)
        val inputStream: InputStream? = context.contentResolver.openInputStream(fileUri)

        val file = File(context.cacheDir.absolutePath.toString() + "/" + id)
        writeFile(inputStream!!, file)

        return file
    }

    fun writeFile(imputStream: InputStream, file: File?) {
        var out: OutputStream? = null
        try {
            out = FileOutputStream(file)
            val buf = ByteArray(1024)
            var len: Int
            while (imputStream.read(buf).also { len = it } > 0) {
                out.write(buf, 0, len)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            try {
                out?.close()
                imputStream.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    */
/* get Mime Type *//*

    fun getMimeType(file: File): String? {
        var type: String? = null
        val extension = MimeTypeMap.getFileExtensionFromUrl(file.path)
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
        return type
    }

    */
/* Is Network Connected *//*

    fun isNetworkConnected(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return result
    }


    fun getDocFile(context: Context, uri: Uri, fileType: String): File? {
        var resultCode: Int = 0
        val contentResolver = context.contentResolver
        val parcelFileDescriptor = try {
            */
/*
             * Get the content resolver instance for this context, and use it
             * to get a ParcelFileDescriptor for the file.
             *//*

            contentResolver.openFileDescriptor(uri, "r")
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            resultCode = 1
            return null
        }
        // Get a regular file descriptor for the file
        val fileDescriptor = parcelFileDescriptor!!.fileDescriptor
        val root =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
                .toString()
        val myDir = File("$root/Shared")

        if (!myDir.exists()) {
            myDir.mkdirs()
        }

        //extension of file
        var ext = "pdf"
        if (fileType == Constants.FILE_TYPE_DOC)
            ext = "doc"


        val fname = "${System.currentTimeMillis()}.$ext"
        val file = File(myDir, fname)
        if (file.exists())
            file.delete()
        var fis: FileInputStream? = null
        var fos: FileOutputStream? = null
        try {
            fis = FileInputStream(fileDescriptor)
            fos = FileOutputStream(file.absolutePath)
            val input = BufferedInputStream(fis)
            val dataBuffer = ByteArray(1024)
            var bytesRead: Int = 0

            bytesRead = input.read(dataBuffer)
            while (bytesRead != -1) {
                fos.write(dataBuffer, 0, bytesRead)
                bytesRead = input.read(dataBuffer)
            }
            fos.flush()
        } catch (e: Exception) {
            Log.i("whats", e.message!!)
            resultCode = 1
        } finally {
            if (fis != null) {
                try {
                    fis.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            if (fos != null) {
                try {
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }

        return if (resultCode == 0)
            file
        else
            null
    }

    fun showDialogFragmentWithoutAnimation(
        activity: AppCompatActivity, dialogFragment: DialogFragment
    ) {
        dialogFragment.setStyle(
            DialogFragment.STYLE_NO_TITLE,
            R.style.TransparentDilaog
        )
        dialogFragment.show(activity.supportFragmentManager, null)
    }


    @SuppressLint("SimpleDateFormat")
    fun getDateInUTC(): String {
//        val myDate = Date()
//
//        val calendar = Calendar.getInstance()
//        calendar.timeZone = TimeZone.getTimeZone("UTC")
//        calendar.time = myDate
//        val time = calendar.time
//        val outputFmt = SimpleDateFormat("yyyy-MM-dd HH:mm:ss +0000")
//        return outputFmt.format(time)


        val date = Date()
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss +0000")
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        Log.i("Callled", "getDateInUTC: ${sdf.format(date)}")
        return sdf.format(date)

    }

    @SuppressLint("SimpleDateFormat")
    fun getTimeInLocal(selectedDate: String): String {
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss +0000", Locale.ENGLISH)

        val date = df.parse(selectedDate)

        val timeZone = Calendar.getInstance().timeZone.id
        val local = Date(date.time + TimeZone.getTimeZone(timeZone).getOffset(date.time))


        val df2 = SimpleDateFormat("h:mm a")
        df2.timeZone = TimeZone.getDefault()
        return df2.format(local)

    }

    @SuppressLint("SimpleDateFormat")
    fun getDateValue(milliSeconds: Long): Date {
        val formatter = SimpleDateFormat("dd/M/yyyy")
        formatter.timeZone = TimeZone.getDefault()
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.parse(formatter.format(calendar.time).toString())

    }

    @SuppressLint("SimpleDateFormat")
    fun getDate(date:String): Date {

        val sdf = SimpleDateFormat("dd/M/yyyy")
        return sdf.parse(date)!!
    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("dd/M/yyyy")
        return sdf.format(Date())

    }

    fun checkDifference(startDate: Date, endDate: Date): Int {
        //milliseconds
        val different = endDate.time - startDate.time
        println("startDate : $startDate")
        println("endDate : $endDate")
        println("different : $different")
        val secondsInMilli: Long = 1000
        val minutesInMilli = secondsInMilli * 60
        val hoursInMilli = minutesInMilli * 60
        val daysInMilli = hoursInMilli * 24
        val elapsedDays = different / daysInMilli

        return elapsedDays.toInt()
    }



}*/
