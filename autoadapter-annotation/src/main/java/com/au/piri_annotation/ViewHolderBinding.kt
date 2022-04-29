package com.au.piri_annotation

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class ViewHolderBinding(val viewId:Int)
