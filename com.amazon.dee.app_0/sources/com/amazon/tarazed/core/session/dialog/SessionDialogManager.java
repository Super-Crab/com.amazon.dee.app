package com.amazon.tarazed.core.session.dialog;

import com.amazon.tarazed.core.session.TarazedSessionStateChangeSource;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionDialogManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001dH\u0016R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u001a\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u000e¢\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0010\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0012\u0010\u0012\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000fR\u0012\u0010\u0014\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000fR\u0012\u0010\u0016\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0005R\u0012\u0010\u0018\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u000fR\u0012\u0010\u001a\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0005¨\u0006\u001f"}, d2 = {"Lcom/amazon/tarazed/core/session/dialog/SessionDialogManager;", "", "confirmEndSessionDialog", "Lcom/amazon/tarazed/core/session/dialog/SessionAskPermissionDialog;", "getConfirmEndSessionDialog", "()Lcom/amazon/tarazed/core/session/dialog/SessionAskPermissionDialog;", "endSessionSource", "Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeSource;", "getEndSessionSource", "()Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeSource;", "setEndSessionSource", "(Lcom/amazon/tarazed/core/session/TarazedSessionStateChangeSource;)V", "endedAgentDialog", "Lcom/amazon/tarazed/core/session/dialog/SessionDialog;", "getEndedAgentDialog", "()Lcom/amazon/tarazed/core/session/dialog/SessionDialog;", "endedCustomerDialog", "getEndedCustomerDialog", "endedInactivityDialog", "getEndedInactivityDialog", "endedUnknownDialog", "getEndedUnknownDialog", "resumeSessionPermissionDialog", "getResumeSessionPermissionDialog", "sessionEndedDialog", "getSessionEndedDialog", "startSessionPermissionDialog", "getStartSessionPermissionDialog", "dismissAllPermissionDialogs", "", "showEndSessionDialog", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface SessionDialogManager {

    /* compiled from: SessionDialogManager.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class DefaultImpls {
        public static void dismissAllPermissionDialogs(SessionDialogManager sessionDialogManager) {
            sessionDialogManager.getStartSessionPermissionDialog().finish();
            sessionDialogManager.mo4643getResumeSessionPermissionDialog().finish();
            sessionDialogManager.mo4638getConfirmEndSessionDialog().finish();
        }

        public static void showEndSessionDialog(SessionDialogManager sessionDialogManager) {
            TarazedSessionStateChangeSource endSessionSource = sessionDialogManager.getEndSessionSource();
            if (endSessionSource != null) {
                int i = WhenMappings.$EnumSwitchMapping$0[endSessionSource.ordinal()];
                if (i == 1) {
                    sessionDialogManager.mo4641getEndedInactivityDialog().show();
                    return;
                } else if (i == 2) {
                    sessionDialogManager.mo4639getEndedAgentDialog().show();
                    return;
                } else if (i == 3) {
                    sessionDialogManager.mo4640getEndedCustomerDialog().show();
                    return;
                } else if (i == 4) {
                    sessionDialogManager.mo4642getEndedUnknownDialog().show();
                    return;
                }
            }
            sessionDialogManager.mo4644getSessionEndedDialog().show();
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TarazedSessionStateChangeSource.values().length];

        static {
            $EnumSwitchMapping$0[TarazedSessionStateChangeSource.SOURCE_INACTIVE.ordinal()] = 1;
            $EnumSwitchMapping$0[TarazedSessionStateChangeSource.SOURCE_AGENT.ordinal()] = 2;
            $EnumSwitchMapping$0[TarazedSessionStateChangeSource.SOURCE_CUSTOMER.ordinal()] = 3;
            $EnumSwitchMapping$0[TarazedSessionStateChangeSource.SOURCE_OTHER.ordinal()] = 4;
        }
    }

    void dismissAllPermissionDialogs();

    @NotNull
    /* renamed from: getConfirmEndSessionDialog */
    SessionAskPermissionDialog mo4638getConfirmEndSessionDialog();

    @Nullable
    TarazedSessionStateChangeSource getEndSessionSource();

    @NotNull
    /* renamed from: getEndedAgentDialog */
    SessionDialog mo4639getEndedAgentDialog();

    @NotNull
    /* renamed from: getEndedCustomerDialog */
    SessionDialog mo4640getEndedCustomerDialog();

    @NotNull
    /* renamed from: getEndedInactivityDialog */
    SessionDialog mo4641getEndedInactivityDialog();

    @NotNull
    /* renamed from: getEndedUnknownDialog */
    SessionDialog mo4642getEndedUnknownDialog();

    @NotNull
    /* renamed from: getResumeSessionPermissionDialog */
    SessionAskPermissionDialog mo4643getResumeSessionPermissionDialog();

    @NotNull
    /* renamed from: getSessionEndedDialog */
    SessionDialog mo4644getSessionEndedDialog();

    @NotNull
    SessionAskPermissionDialog getStartSessionPermissionDialog();

    void setEndSessionSource(@Nullable TarazedSessionStateChangeSource tarazedSessionStateChangeSource);

    void showEndSessionDialog();
}
