package sa.factory.app1289801.model

import kotlinx.serialization.Serializable

@Serializable
data class PrintJobRevisionBubble(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class PrintDebugSession(
    val requestId: String,
    val items: List<PrintJobRevisionBubble>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: PrintDebugSessionStatus = PrintDebugSessionStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class PrintDebugSessionStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
