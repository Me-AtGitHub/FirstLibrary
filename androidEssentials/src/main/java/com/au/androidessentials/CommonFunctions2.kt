/*
package com.au.androidessentials

class CommonFunctions2 {

        companion object {
            private const val JPEG_FILE_PREFIX = "IMG_"
            private const val JPEG_FILE_SUFFIX = ".jpg"
            private var dialog: Dialog? = null

            fun underLineTxt(content: String, textView: TextView) {
                val content1 = SpannableString(content)
                content1.setSpan(UnderlineSpan(), 0, content.length, 0)
                textView.text = content1
            }


            fun saveImageToExternalStorage(finalBitmap: Bitmap): File {
                val file: File
                val root =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                        .toString()
                val myDir = File("$root/Traxi")
                myDir.mkdirs()
                val fname = ("Image_" + System.currentTimeMillis() + ".png")
                file = File(myDir, fname)
                if (file.exists())
                    file.delete()
                try {
                    val out = FileOutputStream(file)
                    finalBitmap.compress(Bitmap.CompressFormat.PNG, 70, out)
                    out.flush()
                    out.close()
                    return file
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                return file
            }

            fun getFile(imgPath: String?, mContext: Context): Bitmap? {
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
                    } else
                        "There might be some problem in fetching photo.. please try again.".showToast(
                            mContext
                        )


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

            fun calculateInSampleSize(
                options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int
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

            //method to hide the KeyBoard in activity
            fun hideKeyboard(activity: Activity) {
                val view = activity.currentFocus
                if (view != null) {
                    val inputMethodManager =
                        activity.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                    inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
                }
            }

            //this method return the server error messages
            fun getErrorMessage(responseBody: ResponseBody, context: Context, errorCode: Int) {
                try {
                    val errorConverter = RestClient.getRetrofitInstance()
                        .responseBodyConverter<ErrorBody>(
                            ErrorBody::class.java,
                            arrayOfNulls<Annotation>(0)
                        )
                    val error = errorConverter.convert(responseBody)
                    error!!.error_description.showToast(context)
                    if (errorCode == 401) {
                        ApplicationGlobal.accessToken = ""
                        Prefs.with(context).removeAll(context)
                        context.startActivity(Intent(context, SplashActivity::class.java))
                        (context as Activity).finishAffinity()
                    }
                } catch (e: java.lang.Exception) {

                }
            }

            //logOut method for Gmail Login
            fun googleLogout(context: Context) {
                val googleSignInOptions =
                    GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
                val googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions)
                googleSignInClient.signOut()
            }

            //this method return the phone is Connected with internet or Not

            fun displayLocationSettingsRequest(activity: Activity) {
                val googleApiClient = GoogleApiClient.Builder(activity)
                    .addApi(LocationServices.API).build()
                googleApiClient.connect()

                val locationRequest = LocationRequest.create();
                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                locationRequest.setInterval(10000)
                locationRequest.setFastestInterval(10000 / 2)

                val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
                builder.setAlwaysShow(true)

                val result = LocationServices.getSettingsClient(activity)
                    .checkLocationSettings(builder.build())
                result.addOnCompleteListener { task ->
                    try {
                        val response = task.getResult(ApiException::class.java)
                    } catch (e: ApiException) {
                        when (e.statusCode) {
                            LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                                Log.i("GPSPERMISSION", "In required")
                                val resolvableApiException = e as ResolvableApiException
                                resolvableApiException.startResolutionForResult(
                                    activity, Constants.ENABLE_LOCATION
                                )
                            }
                        }
                    }
                }
            }

            fun getCurrentDateTime(): String {
                val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                return df.format(Calendar.getInstance().getTime())
            }

        }

    }

*/
