package com.amazon.tarazed.core.session;

import amazon.speech.simclient.settings.Settings;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.drew.metadata.iptc.IptcDirectory;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedSession.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@"}, d2 = {"handleStateChangeEvent", "", "event", "Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeRequest;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.tarazed.core.session.TarazedSession", f = "TarazedSession.kt", i = {0, 0, 1, 1, 2, 2, 3, 3, 3, 4, 4, 5, 5}, l = {311, 316, DeviceConfigConstants.VIDEO_WIDTH_320, IptcDirectory.TAG_TIME_SENT, 345, IptcDirectory.TAG_ARM_IDENTIFIER}, m = "handleStateChangeEvent", n = {"this", "event", "this", "event", "this", "event", "this", "event", Settings.ListeningSettings.EXTRA_REASON, "this", "event", "this", "event"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes13.dex */
public final class TarazedSession$handleStateChangeEvent$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TarazedSession this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedSession$handleStateChangeEvent$1(TarazedSession tarazedSession, Continuation continuation) {
        super(continuation);
        this.this$0 = tarazedSession;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.handleStateChangeEvent(null, this);
    }
}
