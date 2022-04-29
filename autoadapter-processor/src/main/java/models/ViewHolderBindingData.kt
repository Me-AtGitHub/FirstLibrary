package models

data class ViewHolderBindingData(val fieldName: String, val viewId: Int)
data class ModelData(
    val packageName: String,
    val modelName: String,
    val layoutId: Int,
    val viewHolderBindingData: List<ViewHolderBindingData>
)