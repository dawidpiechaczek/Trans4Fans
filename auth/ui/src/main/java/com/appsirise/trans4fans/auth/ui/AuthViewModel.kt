package com.appsirise.trans4fans.auth.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appsirise.core.ui.utils.ErrorMessageHelper
import com.appsirise.core.ui.utils.ViewState
import com.appsirise.trans4fans.auth.data.DogBreed
import com.appsirise.trans4fans.auth.ui.repository.ExampleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class AuthViewModel @Inject constructor(
    private val exampleRepository: ExampleRepository,
) : ViewModel() {

    private val _getBreedsLiveData = MutableLiveData<ViewState<List<DogBreed>>>()
    val getBreedsLiveData: LiveData<ViewState<List<DogBreed>>> = _getBreedsLiveData

    fun getBreeds() {
        viewModelScope.launch {
            try {
                _getBreedsLiveData.value =
                    ViewState.Success(exampleRepository.getDogBreeds())
            } catch (error: Exception) {
                Timber.e(error)
                _getBreedsLiveData.value =
                    ViewState.Error(ErrorMessageHelper(error).getMessageStringId())
            }
        }
    }
}