package com.greezun.exoplayertest.ui.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

interface ViewEvent
interface ViewState
interface ViewEffect

abstract class BaseViewModel<UEvent:ViewEvent, UState:ViewState, UEffect:ViewEffect>: ViewModel() {
    abstract fun initState(): UState
    abstract fun handleEvent(event: UEvent)

    private val initialState by lazy { initState() }

    private val _state: MutableState<UState> = mutableStateOf(initialState)
    val state: State<UState> = _state

    private val _event: MutableSharedFlow<UEvent> = MutableSharedFlow()

    private val _effect = MutableSharedFlow<UEffect>(replay = 0)
    val effect: SharedFlow<UEffect> = _effect

    init {
        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        viewModelScope.launch {
            _event.collect {
                handleEvent(it)
            }
        }
    }

    fun setEvent(event: UEvent) {
        viewModelScope.launch { _event.emit(event) }
    }

    protected fun setState(reducer: UState.() -> UState) {
        val newState = state.value.reducer()
        _state.value = newState
    }

    protected fun setEffect(builder: () -> UEffect) {
        val newEffect = builder()
        viewModelScope.launch { _effect.emit(newEffect) }
    }
}