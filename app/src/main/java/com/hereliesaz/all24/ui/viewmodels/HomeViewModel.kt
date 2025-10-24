package com.hereliesaz.all24.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hereliesaz.all24.model.Top24List
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.InputStreamReader

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _top24Lists = MutableStateFlow<List<Top24List>>(emptyList())
    val top24Lists: StateFlow<List<Top24List>> = _top24Lists

    init {
        loadTop24Lists()
    }

    private fun loadTop24Lists() {
        viewModelScope.launch {
            try {
                val inputStream = getApplication<Application>().assets.open("top_24_lists.json")
                val reader = InputStreamReader(inputStream)
                val listType = object : TypeToken<List<Top24List>>() {}.type
                _top24Lists.value = Gson().fromJson(reader, listType)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
