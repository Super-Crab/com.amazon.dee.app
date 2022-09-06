package com.amazon.alexa.tarazed.dmps;

import kotlin.Metadata;
/* compiled from: DMPSHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\u0003H&¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/tarazed/dmps/DMPSHandler;", "", "disableTarazedPermission", "", "enableTarazedPermission", "isTarazedNotificationEnabled", "", "subscribe", "AlexaMobileAndroidTarazedIntegration_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes10.dex */
public interface DMPSHandler {
    void disableTarazedPermission();

    void enableTarazedPermission();

    boolean isTarazedNotificationEnabled();

    void subscribe();
}
