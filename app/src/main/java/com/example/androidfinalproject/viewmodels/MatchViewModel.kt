package com.example.androidfinalproject.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidfinalproject.model.Match
import com.example.androidfinalproject.repository.MatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(private val repository: MatchRepository) : ViewModel() {

    private val _matchList = MutableStateFlow<List<Match>>(emptyList())
    val matchList: StateFlow<List<Match>> = _matchList.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllMatches().collect { matches ->
                _matchList.value = matches
            }
        }
    }

    // Insertar un nuevo partido
    fun addMatch(match: Match) {
        viewModelScope.launch {
            repository.addMatch(match)
        }
    }
}