package com.amazon.alexa.api;
/* loaded from: classes6.dex */
public interface AlertsListener {
    void onAlertFinished(String str, AlertType alertType);

    void onAlertStarted(String str, AlertType alertType);
}
