package com.amazon.alexa.accessoryclient.client;

import android.os.RemoteException;
import com.amazon.alexa.accessoryclient.common.util.MetricsConstants;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AbstractAlexaAccessoryClient.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/os/RemoteException;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AbstractAlexaAccessoryClient$prepareInputAndOutputStreams$mainOutputStreamSink$1 extends Lambda implements Function1<RemoteException, Unit> {
    final /* synthetic */ AbstractAlexaAccessoryClient this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractAlexaAccessoryClient$prepareInputAndOutputStreams$mainOutputStreamSink$1(AbstractAlexaAccessoryClient abstractAlexaAccessoryClient) {
        super(1);
        this.this$0 = abstractAlexaAccessoryClient;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(RemoteException remoteException) {
        invoke2(remoteException);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull RemoteException it2) {
        Intrinsics.checkParameterIsNotNull(it2, "it");
        this.this$0.accessoryMetricsService.recordOccurrence(MetricsConstants.CLIENT_OUTPUT_STREAM, MetricsConstants.CLIENT_OUTPUT_STREAM_WRITE_EXCEPTION, true, null);
        AbstractAlexaAccessoryClient abstractAlexaAccessoryClient = this.this$0;
        abstractAlexaAccessoryClient.reconnect("OutputStream channel 2 failed to write with exception " + it2);
    }
}
