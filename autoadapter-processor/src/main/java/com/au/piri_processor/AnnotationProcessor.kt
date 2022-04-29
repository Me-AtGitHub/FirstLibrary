package com.au.piri_processor

import com.au.piri_annotation.AdapterModule
import com.au.piri_annotation.ViewHolderBinding
import models.ModelData
import models.ViewHolderBindingData
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement

@SupportedSourceVersion(SourceVersion.RELEASE_8)
class AnnotationProcessor :AbstractProcessor() {


    override fun init(processingEnv: ProcessingEnvironment?) {
        super.init(processingEnv)

    }

    override fun process(set: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        val kaptKotlinGeneratedDir = processingEnv.options[KAPT_KOTLIN_GENERATED_OPTION_NAME] ?: return false
        roundEnv!!.getElementsAnnotatedWith(AdapterModule::class.java).forEach {
            val modelData = getModelData(it)
        }
        return true
    }

    private fun getModelData(element: Element):ModelData{
        val packageName = processingEnv.elementUtils.getPackageOf(element).toString()
        val modelName = element.simpleName.toString()
        val annotation = element.getAnnotation(AdapterModule::class.java)
        val layoutId = annotation.layout
        val viewHolderBindingData = element.enclosedElements.mapNotNull {
            val viewHolderBinding = it.getAnnotation(ViewHolderBinding::class.java)
            if(viewHolderBinding == null){
                null
            }else{
                val elementName = it.simpleName.toString()
                val fieldName = elementName.substring(0,elementName.indexOf("$"))
                ViewHolderBindingData(fieldName,viewHolderBinding.viewId)
            }
        }

        return ModelData(packageName, modelName, layoutId, viewHolderBindingData)

    }

    override fun getSupportedAnnotationTypes() = mutableSetOf(AdapterModule::class.java.canonicalName)

    override fun getSupportedSourceVersion(): SourceVersion {
        return super.getSupportedSourceVersion()
    }

    companion object{
        const val KAPT_KOTLIN_GENERATED_OPTION_NAME = "kapt.kotlin.generated"
    }

}