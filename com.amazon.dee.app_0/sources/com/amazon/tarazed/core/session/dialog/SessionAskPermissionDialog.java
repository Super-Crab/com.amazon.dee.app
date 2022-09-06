package com.amazon.tarazed.core.session.dialog;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
/* compiled from: SessionAskPermissionDialog.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\u0010H&J\b\u0010\u0012\u001a\u00020\u0004H&R\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u000e¢\u0006\f\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/amazon/tarazed/core/session/dialog/SessionAskPermissionDialog;", "", "onAcceptSessionCallback", "Lkotlin/Function0;", "", "getOnAcceptSessionCallback", "()Lkotlin/jvm/functions/Function0;", "setOnAcceptSessionCallback", "(Lkotlin/jvm/functions/Function0;)V", "onDeclineSessionCallback", "getOnDeclineSessionCallback", "setOnDeclineSessionCallback", "onTimeoutCallback", "getOnTimeoutCallback", "setOnTimeoutCallback", "finish", "Lkotlinx/coroutines/Job;", "show", "start", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface SessionAskPermissionDialog {
    @NotNull
    Job finish();

    @NotNull
    Function0<Unit> getOnAcceptSessionCallback();

    @NotNull
    Function0<Unit> getOnDeclineSessionCallback();

    @NotNull
    Function0<Unit> getOnTimeoutCallback();

    void setOnAcceptSessionCallback(@NotNull Function0<Unit> function0);

    void setOnDeclineSessionCallback(@NotNull Function0<Unit> function0);

    void setOnTimeoutCallback(@NotNull Function0<Unit> function0);

    @NotNull
    Job show();

    void start();
}
