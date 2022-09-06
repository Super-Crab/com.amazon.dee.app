package com.amazon.tarazed.core.session;

import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.sessionclient.model.createcredentials.LoggingCredentials;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedSession.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedSession$endSession$logUpload$1 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ TarazedSession this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedSession$endSession$logUpload$1(TarazedSession tarazedSession) {
        super(1);
        this.this$0 = tarazedSession;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@Nullable Throwable th) {
        LoggingCredentials loggingCredentials;
        TarazedSessionLogger tarazedSessionLogger;
        LoggingCredentials loggingCredentials2;
        TarazedMetricsHelper tarazedMetricsHelper;
        loggingCredentials = this.this$0.loggingCredentials;
        if (loggingCredentials != null) {
            tarazedSessionLogger = this.this$0.logger;
            loggingCredentials2 = this.this$0.loggingCredentials;
            if (loggingCredentials2 == null) {
                Intrinsics.throwNpe();
            }
            tarazedMetricsHelper = this.this$0.metricsHelper;
            tarazedSessionLogger.uploadLogs$TarazedMobileCore_release(loggingCredentials2, tarazedMetricsHelper);
        }
    }
}
