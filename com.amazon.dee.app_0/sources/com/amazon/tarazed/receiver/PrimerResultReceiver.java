package com.amazon.tarazed.receiver;

import android.os.Bundle;
import android.os.ResultReceiver;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: PrimerResultReceiver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0006J\u001a\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/tarazed/receiver/PrimerResultReceiver;", "Landroid/os/ResultReceiver;", "onAcceptCallback", "Lkotlin/Function0;", "", "onDeclineCallback", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "onReceiveResult", "resultCode", "", "resultData", "Landroid/os/Bundle;", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class PrimerResultReceiver extends ResultReceiver {
    public static final Companion Companion = new Companion(null);
    public static final int RESULT_ACCEPT = 1;
    public static final int RESULT_DECLINE = 0;
    private final Function0<Unit> onAcceptCallback;
    private final Function0<Unit> onDeclineCallback;

    /* compiled from: PrimerResultReceiver.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/receiver/PrimerResultReceiver$Companion;", "", "()V", "RESULT_ACCEPT", "", "RESULT_DECLINE", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PrimerResultReceiver(@NotNull Function0<Unit> onAcceptCallback, @NotNull Function0<Unit> onDeclineCallback) {
        super(null);
        Intrinsics.checkParameterIsNotNull(onAcceptCallback, "onAcceptCallback");
        Intrinsics.checkParameterIsNotNull(onDeclineCallback, "onDeclineCallback");
        this.onAcceptCallback = onAcceptCallback;
        this.onDeclineCallback = onDeclineCallback;
    }

    @Override // android.os.ResultReceiver
    protected void onReceiveResult(int i, @Nullable Bundle bundle) {
        super.onReceiveResult(i, bundle);
        if (i == 1) {
            this.onAcceptCallback.mo12560invoke();
        } else if (i != 0) {
        } else {
            this.onDeclineCallback.mo12560invoke();
        }
    }
}
