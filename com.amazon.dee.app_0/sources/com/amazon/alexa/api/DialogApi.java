package com.amazon.alexa.api;
/* loaded from: classes6.dex */
public interface DialogApi {
    void cancel();

    void start(String str, LaunchType launchType);

    void start(String str, LaunchType launchType, AlexaUserInterfaceOptions alexaUserInterfaceOptions);

    void stop();
}
