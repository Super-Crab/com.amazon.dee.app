package com.amazon.alexa.accessory.speechapi.listeners;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: AlertsListener.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0001\tJ\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/listeners/AlertsListener;", "", "onAlertFinished", "", "id", "", "type", "Lcom/amazon/alexa/accessory/speechapi/listeners/AlertsListener$AlertType;", "onAlertStarted", "AlertType", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface AlertsListener {

    /* compiled from: AlertsListener.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/listeners/AlertsListener$AlertType;", "", "(Ljava/lang/String;I)V", "ALARM", "TIMER", "REMINDER", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public enum AlertType {
        ALARM,
        TIMER,
        REMINDER
    }

    void onAlertFinished(@NotNull String str, @NotNull AlertType alertType);

    void onAlertStarted(@NotNull String str, @NotNull AlertType alertType);
}
