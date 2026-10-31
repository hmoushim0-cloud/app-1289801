package sa.factory.app1289801.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.factory.app1289801.data.PrintDebugSessionRepository
import sa.factory.app1289801.model.PrintDebugSession
import sa.factory.app1289801.model.PrintJobRevisionBubble
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PrintDebugSessionViewModel(
    private val repository: PrintDebugSessionRepository
) : ViewModel() {
    val catalog: StateFlow<List<PrintJobRevisionBubble>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<PrintDebugSession?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: PrintDebugSession) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
