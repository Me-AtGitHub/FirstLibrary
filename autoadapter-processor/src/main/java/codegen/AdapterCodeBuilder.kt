package codegen

import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import models.ModelData

class AdapterCodeBuilder(private val adapterName: String, private val data: ModelData) {
    private val viewHolderName = "ViewHolder"
    private val viewHolderClassName = ClassName(data.packageName, viewHolderName)
    private val viewHolderQualifiedClassName =
        ClassName(data.packageName, adapterName + "$viewHolderName")
    private val modelClassName = ClassName(data.packageName, data.modelName)
    private val itemsListClassName =
        ClassName("kotlin-collections", "List").parameterizedBy(modelClassName)
    private val textViewClassName = ClassName("android.widget", "TextView")

    fun build(): TypeSpec = TypeSpec.classBuilder(adapterName)
        .primaryConstructor(
            FunSpec.constructorBuilder().addParameter("items", itemsListClassName).build()
        ).superclass(
            ClassName("androidx.recyclerview.widget.RecyclerView", "Adapter")
                .parameterizedBy(viewHolderQualifiedClassName)
        ).addProperty(
            PropertySpec.builder("items", itemsListClassName)
                .addModifiers(KModifier.PRIVATE)
                .initializer("items")
                .build()
        ).build()
}