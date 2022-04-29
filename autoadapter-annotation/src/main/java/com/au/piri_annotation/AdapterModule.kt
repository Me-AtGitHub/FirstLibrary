package com.au.piri_annotation

import javax.swing.text.View

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class AdapterModule(val layout:Int) {

}