package com.amazon.tarazed.sessionmanager;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedResourceManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0080@"}, d2 = {"tearDownSession", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.sessionmanager.TarazedResourceManager", f = "TarazedResourceManager.kt", i = {0, 0}, l = {187}, m = "tearDownSession$TarazedAndroidLibrary_release", n = {"this", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY}, s = {"L$0", "L$1"})
/* loaded from: classes13.dex */
public final class TarazedResourceManager$tearDownSession$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TarazedResourceManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedResourceManager$tearDownSession$1(TarazedResourceManager tarazedResourceManager, Continuation continuation) {
        super(continuation);
        this.this$0 = tarazedResourceManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.tearDownSession$TarazedAndroidLibrary_release(this);
    }
}
