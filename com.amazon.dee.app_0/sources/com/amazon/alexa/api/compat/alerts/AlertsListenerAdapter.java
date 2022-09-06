package com.amazon.alexa.api.compat.alerts;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlertsListenerAdapter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u001c\u0010\r\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/api/compat/alerts/AlertsListenerAdapter;", "Lcom/amazon/alexa/api/AlertsListener;", "compatListener", "Lcom/amazon/alexa/api/compat/alerts/AlertsListener;", "(Lcom/amazon/alexa/api/compat/alerts/AlertsListener;)V", "getCompatListener", "()Lcom/amazon/alexa/api/compat/alerts/AlertsListener;", "onAlertFinished", "", "id", "", "type", "Lcom/amazon/alexa/api/AlertType;", "onAlertStarted", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AlertsListenerAdapter implements com.amazon.alexa.api.AlertsListener {
    @NotNull
    private final AlertsListener compatListener;

    public AlertsListenerAdapter(@NotNull AlertsListener compatListener) {
        Intrinsics.checkParameterIsNotNull(compatListener, "compatListener");
        this.compatListener = compatListener;
    }

    @NotNull
    public final AlertsListener getCompatListener() {
        return this.compatListener;
    }

    @Override // com.amazon.alexa.api.AlertsListener
    public void onAlertFinished(@Nullable String str, @Nullable com.amazon.alexa.api.AlertType alertType) {
        this.compatListener.onAlertFinished(str, alertType != null ? AlertType.Companion.convertToCompat(alertType) : null);
    }

    @Override // com.amazon.alexa.api.AlertsListener
    public void onAlertStarted(@Nullable String str, @Nullable com.amazon.alexa.api.AlertType alertType) {
        this.compatListener.onAlertStarted(str, alertType != null ? AlertType.Companion.convertToCompat(alertType) : null);
    }
}
