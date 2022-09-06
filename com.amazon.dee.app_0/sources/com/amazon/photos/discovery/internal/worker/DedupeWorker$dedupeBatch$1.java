package com.amazon.photos.discovery.internal.worker;

import com.amazon.device.messaging.ADMRegistrationConstants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DedupeWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"removeOrphansAndThrow", "", ADMRegistrationConstants.CALL_EXCEPTION, "Ljava/lang/Exception;", "Lkotlin/Exception;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DedupeWorker$dedupeBatch$1 extends Lambda implements Function1<Exception, Unit> {
    final /* synthetic */ DedupeWorker this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DedupeWorker$dedupeBatch$1(DedupeWorker dedupeWorker) {
        super(1);
        this.this$0 = dedupeWorker;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Exception exc) {
        invoke2(exc);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull Exception exception) {
        Intrinsics.checkParameterIsNotNull(exception, "exception");
        this.this$0.getLogger().w("DedupeWorker", "Deduplication encountered a retryable exception.", exception);
        this.this$0.getOrphanRemover().removeOrphanedEntries();
        throw exception;
    }
}
