package com.amazon.alexa.accessoryclient.client;

import android.os.RemoteException;
import com.amazon.alexa.accessory.internal.util.Logger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: AbstractAlexaAccessoryClient.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Landroid/os/RemoteException;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class AbstractAlexaAccessoryClient$prepareInputAndOutputStreams$2 extends Lambda implements Function1<RemoteException, Unit> {
    public static final AbstractAlexaAccessoryClient$prepareInputAndOutputStreams$2 INSTANCE = new AbstractAlexaAccessoryClient$prepareInputAndOutputStreams$2();

    AbstractAlexaAccessoryClient$prepareInputAndOutputStreams$2() {
        super(1);
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
        Logger.d("OutputStream channel 1 failed to write, ignoring with exception " + it2);
    }
}
