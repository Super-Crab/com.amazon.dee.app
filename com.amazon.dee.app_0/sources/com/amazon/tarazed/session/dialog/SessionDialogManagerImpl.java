package com.amazon.tarazed.session.dialog;

import android.content.Context;
import com.amazon.tarazed.arcus.ArcusHelper;
import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.session.TarazedSessionStateChangeSource;
import com.amazon.tarazed.core.session.dialog.SessionAskPermissionDialog;
import com.amazon.tarazed.core.session.dialog.SessionDialogManager;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionDialogManagerImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\u0006\u0010;\u001a\u00020<J\b\u0010=\u001a\u00020<H\u0002R\u0014\u0010\u0015\u001a\u00020\u0016X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020 X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020$X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0014\u0010'\u001a\u00020(X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020,X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0014\u0010/\u001a\u000200X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0014\u00103\u001a\u000204X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0014\u00107\u001a\u000208X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:¨\u0006>"}, d2 = {"Lcom/amazon/tarazed/session/dialog/SessionDialogManagerImpl;", "Lcom/amazon/tarazed/core/session/dialog/SessionDialogManager;", "context", "Landroid/content/Context;", "deviceInfo", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "sessionNotifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "bizMetricsHelper", "Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "dispatchers", "Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;", "arcusHelper", "Lcom/amazon/tarazed/arcus/ArcusHelper;", "(Landroid/content/Context;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;Lkotlinx/coroutines/CoroutineScope;Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;Lcom/amazon/tarazed/arcus/ArcusHelper;)V", "confirmEndSessionDialog", "Lcom/amazon/tarazed/session/dialog/ConfirmEndSessionDialog;", "getConfirmEndSessionDialog", "()Lcom/amazon/tarazed/session/dialog/ConfirmEndSessionDialog;", "endSessionSource", "Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeSource;", "getEndSessionSource", "()Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeSource;", "setEndSessionSource", "(Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeSource;)V", "endedAgentDialog", "Lcom/amazon/tarazed/session/dialog/SessionEndedAgentDialog;", "getEndedAgentDialog", "()Lcom/amazon/tarazed/session/dialog/SessionEndedAgentDialog;", "endedCustomerDialog", "Lcom/amazon/tarazed/session/dialog/SessionEndedCustomerDialog;", "getEndedCustomerDialog", "()Lcom/amazon/tarazed/session/dialog/SessionEndedCustomerDialog;", "endedInactivityDialog", "Lcom/amazon/tarazed/session/dialog/SessionEndedInactivityDialog;", "getEndedInactivityDialog", "()Lcom/amazon/tarazed/session/dialog/SessionEndedInactivityDialog;", "endedUnknownDialog", "Lcom/amazon/tarazed/session/dialog/SessionEndedUnknownDialog;", "getEndedUnknownDialog", "()Lcom/amazon/tarazed/session/dialog/SessionEndedUnknownDialog;", "resumeSessionPermissionDialog", "Lcom/amazon/tarazed/session/dialog/AskResumeSessionPermissionDialog;", "getResumeSessionPermissionDialog", "()Lcom/amazon/tarazed/session/dialog/AskResumeSessionPermissionDialog;", "sessionEndedDialog", "Lcom/amazon/tarazed/session/dialog/SessionEndedDialog;", "getSessionEndedDialog", "()Lcom/amazon/tarazed/session/dialog/SessionEndedDialog;", "startSessionPermissionDialog", "Lcom/amazon/tarazed/core/session/dialog/SessionAskPermissionDialog;", "getStartSessionPermissionDialog", "()Lcom/amazon/tarazed/core/session/dialog/SessionAskPermissionDialog;", "dismissAllDialogs", "", "dismissAllEndedDialogs", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class SessionDialogManagerImpl implements SessionDialogManager {
    @NotNull
    private final ConfirmEndSessionDialog confirmEndSessionDialog;
    @Nullable
    private TarazedSessionStateChangeSource endSessionSource;
    @NotNull
    private final SessionEndedAgentDialog endedAgentDialog;
    @NotNull
    private final SessionEndedCustomerDialog endedCustomerDialog;
    @NotNull
    private final SessionEndedInactivityDialog endedInactivityDialog;
    @NotNull
    private final SessionEndedUnknownDialog endedUnknownDialog;
    @NotNull
    private final AskResumeSessionPermissionDialog resumeSessionPermissionDialog;
    @NotNull
    private final SessionEndedDialog sessionEndedDialog;
    @NotNull
    private final SessionAskPermissionDialog startSessionPermissionDialog;

    public SessionDialogManagerImpl(@NotNull Context context, @NotNull DeviceInfoUtility deviceInfo, @NotNull TarazedSessionNotifier sessionNotifier, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull BizMetricsHelper bizMetricsHelper, @NotNull CoroutineScope coroutineScope, @NotNull DispatcherProvider dispatchers, @NotNull ArcusHelper arcusHelper) {
        SessionAskPermissionDialog primerPermissionDialog;
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(deviceInfo, "deviceInfo");
        Intrinsics.checkParameterIsNotNull(sessionNotifier, "sessionNotifier");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(bizMetricsHelper, "bizMetricsHelper");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "coroutineScope");
        Intrinsics.checkParameterIsNotNull(dispatchers, "dispatchers");
        Intrinsics.checkParameterIsNotNull(arcusHelper, "arcusHelper");
        if (deviceInfo.is1PDevice()) {
            primerPermissionDialog = new AskStartSessionPermissionDialog(context, deviceInfo, sessionNotifier, logger, metricsHelper, bizMetricsHelper);
        } else {
            primerPermissionDialog = new PrimerPermissionDialog(context, sessionNotifier, coroutineScope, dispatchers, bizMetricsHelper, metricsHelper, arcusHelper);
        }
        this.startSessionPermissionDialog = primerPermissionDialog;
        this.resumeSessionPermissionDialog = new AskResumeSessionPermissionDialog(context, deviceInfo, sessionNotifier, logger, metricsHelper, bizMetricsHelper);
        this.confirmEndSessionDialog = new ConfirmEndSessionDialog(context, deviceInfo, sessionNotifier, logger, metricsHelper, bizMetricsHelper);
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
        this.sessionEndedDialog = new SessionEndedDialog(applicationContext, deviceInfo, sessionNotifier, logger, metricsHelper, bizMetricsHelper);
        Context applicationContext2 = context.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext2, "context.applicationContext");
        this.endedUnknownDialog = new SessionEndedUnknownDialog(applicationContext2, deviceInfo, sessionNotifier, logger, metricsHelper, bizMetricsHelper);
        Context applicationContext3 = context.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext3, "context.applicationContext");
        this.endedInactivityDialog = new SessionEndedInactivityDialog(applicationContext3, deviceInfo, sessionNotifier, logger, metricsHelper, bizMetricsHelper);
        Context applicationContext4 = context.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext4, "context.applicationContext");
        this.endedAgentDialog = new SessionEndedAgentDialog(applicationContext4, deviceInfo, sessionNotifier, logger, metricsHelper, bizMetricsHelper);
        Context applicationContext5 = context.getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext5, "context.applicationContext");
        this.endedCustomerDialog = new SessionEndedCustomerDialog(applicationContext5, deviceInfo, sessionNotifier, logger, metricsHelper, bizMetricsHelper);
    }

    private final void dismissAllEndedDialogs() {
        mo4644getSessionEndedDialog().finish();
        mo4639getEndedAgentDialog().finish();
        mo4640getEndedCustomerDialog().finish();
        mo4641getEndedInactivityDialog().finish();
        mo4642getEndedUnknownDialog().finish();
    }

    public final void dismissAllDialogs() {
        dismissAllPermissionDialogs();
        dismissAllEndedDialogs();
    }

    @Override // com.amazon.tarazed.core.session.dialog.SessionDialogManager
    public void dismissAllPermissionDialogs() {
        SessionDialogManager.DefaultImpls.dismissAllPermissionDialogs(this);
    }

    @Override // com.amazon.tarazed.core.session.dialog.SessionDialogManager
    @Nullable
    public TarazedSessionStateChangeSource getEndSessionSource() {
        return this.endSessionSource;
    }

    @Override // com.amazon.tarazed.core.session.dialog.SessionDialogManager
    @NotNull
    public SessionAskPermissionDialog getStartSessionPermissionDialog() {
        return this.startSessionPermissionDialog;
    }

    @Override // com.amazon.tarazed.core.session.dialog.SessionDialogManager
    public void setEndSessionSource(@Nullable TarazedSessionStateChangeSource tarazedSessionStateChangeSource) {
        this.endSessionSource = tarazedSessionStateChangeSource;
    }

    @Override // com.amazon.tarazed.core.session.dialog.SessionDialogManager
    public void showEndSessionDialog() {
        SessionDialogManager.DefaultImpls.showEndSessionDialog(this);
    }

    @Override // com.amazon.tarazed.core.session.dialog.SessionDialogManager
    @NotNull
    /* renamed from: getConfirmEndSessionDialog  reason: collision with other method in class */
    public ConfirmEndSessionDialog mo4638getConfirmEndSessionDialog() {
        return this.confirmEndSessionDialog;
    }

    @Override // com.amazon.tarazed.core.session.dialog.SessionDialogManager
    @NotNull
    /* renamed from: getEndedAgentDialog  reason: collision with other method in class */
    public SessionEndedAgentDialog mo4639getEndedAgentDialog() {
        return this.endedAgentDialog;
    }

    @Override // com.amazon.tarazed.core.session.dialog.SessionDialogManager
    @NotNull
    /* renamed from: getEndedCustomerDialog  reason: collision with other method in class */
    public SessionEndedCustomerDialog mo4640getEndedCustomerDialog() {
        return this.endedCustomerDialog;
    }

    @Override // com.amazon.tarazed.core.session.dialog.SessionDialogManager
    @NotNull
    /* renamed from: getEndedInactivityDialog  reason: collision with other method in class */
    public SessionEndedInactivityDialog mo4641getEndedInactivityDialog() {
        return this.endedInactivityDialog;
    }

    @Override // com.amazon.tarazed.core.session.dialog.SessionDialogManager
    @NotNull
    /* renamed from: getEndedUnknownDialog  reason: collision with other method in class */
    public SessionEndedUnknownDialog mo4642getEndedUnknownDialog() {
        return this.endedUnknownDialog;
    }

    @Override // com.amazon.tarazed.core.session.dialog.SessionDialogManager
    @NotNull
    /* renamed from: getResumeSessionPermissionDialog  reason: collision with other method in class */
    public AskResumeSessionPermissionDialog mo4643getResumeSessionPermissionDialog() {
        return this.resumeSessionPermissionDialog;
    }

    @Override // com.amazon.tarazed.core.session.dialog.SessionDialogManager
    @NotNull
    /* renamed from: getSessionEndedDialog  reason: collision with other method in class */
    public SessionEndedDialog mo4644getSessionEndedDialog() {
        return this.sessionEndedDialog;
    }
}
