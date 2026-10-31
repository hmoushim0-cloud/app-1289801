package sa.factory.app1289801.data

import sa.factory.app1289801.model.PrintDebugSession
import sa.factory.app1289801.model.PrintJobRevisionBubble
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface PrintDebugSessionRepository {
    fun getAvailableCatalog(): Flow<List<PrintJobRevisionBubble>>
    fun getActiveRequest(): Flow<PrintDebugSession?>
    suspend fun submitRequest(request: PrintDebugSession): Result<String>
}

class InMemoryPrintDebugSessionRepository : PrintDebugSessionRepository {
    private val _catalog = MutableStateFlow<List<PrintJobRevisionBubble>>(listOf(
        PrintJobRevisionBubble("1", "تشغيل جلسة تصحيح الفقاعات", "تفعيل آلية إصدارات للفقاعات الخاصة بالتجارب والتصحيح مع سجل تدقيق يوضح ما تم تغييره ومتى، مما يقلل إعادة العمل ويحسن جودة المخرجات.", 5000L, "assignment"),
        PrintJobRevisionBubble("2", "تهيئة مزامنة تخزين محلي مع الاستضافة", "ضبط قاعدة بيانات محلية + طبقة مزامنة خلفية مع استراتيجية تعارضات واضحة (آخر تعديل/دمج حسب الحقول) لضمان استمرار الخدمة أثناء ضعف الاتصال.", 9500L, "inventory")
    ))
    private val _active = MutableStateFlow<PrintDebugSession?>(null)

    override fun getAvailableCatalog(): Flow<List<PrintJobRevisionBubble>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<PrintDebugSession?> = _active.asStateFlow()

    override suspend fun submitRequest(request: PrintDebugSession): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
