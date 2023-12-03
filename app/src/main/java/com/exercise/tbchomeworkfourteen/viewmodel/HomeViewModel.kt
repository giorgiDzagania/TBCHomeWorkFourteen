package com.exercise.tbchomeworkfourteen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exercise.tbchomeworkfourteen.R
import com.exercise.tbchomeworkfourteen.model.ModelInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeViewModel : ViewModel() {
    private val _models: MutableStateFlow<List<ModelInfo>> = MutableStateFlow(emptyList())
    val models get() = _models

    init {
        _models.value = getModelsList()
    }

    private fun getModelsList(): List<ModelInfo> {
        val allModels = mutableListOf<ModelInfo>()
        val modelOne =
            ModelInfo(1, R.drawable.model_woman, "Emma Lawson", 175, ModelInfo.ModelGender.FEMALE)
        val modelTwo =
            ModelInfo(2, R.drawable.model_man, "Oliver Bennett", 187, ModelInfo.ModelGender.MALE)
        allModels.add(modelOne)
        allModels.add(modelTwo)
        return allModels
    }

    private val firstToModels = listOf(
        ModelInfo(1, R.drawable.model_woman, "Emma Lawson", 175, ModelInfo.ModelGender.FEMALE),
        ModelInfo(2, R.drawable.model_man, "Oliver Bennett", 187, ModelInfo.ModelGender.MALE)
    )

    fun firstToModels() {
        _models.value = firstToModels
    }

    fun addNewModelWoman() {
        viewModelScope.launch {
            val newModel = generatedRandomModelFemale()
            _models.value = _models.value + listOf(newModel)
        }
    }

    fun addNewModelMan() {
        viewModelScope.launch {
            val newModel = generateRandomModelMale()
            _models.value = _models.value + listOf(newModel)
        }
    }

    fun deleteModel(model: ModelInfo) {
        val currentList = _models.value.toMutableList()
        currentList.remove(model)
        _models.value = currentList.toList()
    }

    fun generatedRandomModelFemale(): ModelInfo {
        return ModelInfo(
            id = Random.nextInt(),
            image = R.drawable.model_woman,
            name = "new girl model",
            height = 177,
            type = ModelInfo.ModelGender.FEMALE
        )
    }

    fun generateRandomModelMale(): ModelInfo {
        return ModelInfo(
            id = Random.nextInt(),
            image = R.drawable.model_man,
            name = "new man model",
            height = 189,
            type = ModelInfo.ModelGender.FEMALE
        )
    }

}