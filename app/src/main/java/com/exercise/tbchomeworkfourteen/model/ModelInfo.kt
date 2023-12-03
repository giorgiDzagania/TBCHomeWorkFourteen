package com.exercise.tbchomeworkfourteen.model

data class ModelInfo(
    val id: Int,
    val image: Int,
    val name: String,
    val height: Int,
    val type: ModelGender
) {
    enum class ModelGender {
        FEMALE,
        MALE
    }
}