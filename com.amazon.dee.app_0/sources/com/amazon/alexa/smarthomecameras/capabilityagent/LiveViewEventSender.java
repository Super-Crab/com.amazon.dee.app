package com.amazon.alexa.smarthomecameras.capabilityagent;
/* loaded from: classes10.dex */
public interface LiveViewEventSender {
    void initialize();

    void sendLiveViewStartedEvent(String str, String str2);

    void sendLiveViewStoppedEvent(String str, String str2);

    void sendRequestStartLiveViewEvent(String str);

    void sendRequestStopLiveViewEvent(String str, String str2);

    void teardown();
}
