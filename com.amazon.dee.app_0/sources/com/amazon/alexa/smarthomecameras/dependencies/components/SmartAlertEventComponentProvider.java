package com.amazon.alexa.smarthomecameras.dependencies.components;

import com.google.common.base.Preconditions;
import javax.annotation.Nullable;
/* loaded from: classes10.dex */
public class SmartAlertEventComponentProvider {
    private static SmartAlertEventComponent eventComponent;

    @Nullable
    public static synchronized SmartAlertEventComponent getSmartAlertEventComponent() {
        SmartAlertEventComponent smartAlertEventComponent;
        synchronized (SmartAlertEventComponentProvider.class) {
            smartAlertEventComponent = eventComponent;
        }
        return smartAlertEventComponent;
    }

    public static synchronized void reset() {
        synchronized (SmartAlertEventComponentProvider.class) {
            eventComponent = null;
        }
    }

    public static synchronized void setSmartAlertEventComponent(SmartAlertEventComponent smartAlertEventComponent) {
        synchronized (SmartAlertEventComponentProvider.class) {
            Preconditions.checkNotNull(smartAlertEventComponent, "SmartAlertEventComponent cannot be null");
            eventComponent = smartAlertEventComponent;
        }
    }
}
