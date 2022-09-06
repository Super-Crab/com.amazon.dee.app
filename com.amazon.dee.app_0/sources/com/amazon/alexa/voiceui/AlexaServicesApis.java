package com.amazon.alexa.voiceui;

import androidx.annotation.RequiresPermission;
import com.amazon.alexa.api.AlexaCardListener;
import com.amazon.alexa.api.AlexaDriveModeListener;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.AlexaStateListener;
import com.amazon.alexa.api.AlexaUserInterfaceOptions;
import com.amazon.alexa.api.AlexaUserSpeechListener;
import java.util.Locale;
/* loaded from: classes11.dex */
public interface AlexaServicesApis {
    void cancel();

    void connect();

    void deregisterAlexaStateListener(AlexaStateListener alexaStateListener);

    void deregisterCardListener(AlexaCardListener alexaCardListener);

    void deregisterConnectionListener(AlexaServicesConnection.ConnectionListener connectionListener);

    void deregisterDriveModeListener(AlexaDriveModeListener alexaDriveModeListener);

    void deregisterUserSpeechListener(AlexaUserSpeechListener alexaUserSpeechListener);

    void disconnect();

    Locale getLocale();

    boolean isConnected();

    void registerAlexaStateListener(AlexaStateListener alexaStateListener);

    void registerCardListener(AlexaCardListener alexaCardListener);

    void registerConnectionListener(AlexaServicesConnection.ConnectionListener connectionListener);

    void registerDriveModeListener(AlexaDriveModeListener alexaDriveModeListener);

    void registerUserSpeechListener(AlexaUserSpeechListener alexaUserSpeechListener);

    void release();

    @RequiresPermission("android.permission.RECORD_AUDIO")
    void start(String str);

    @RequiresPermission("android.permission.RECORD_AUDIO")
    void start(String str, AlexaUserInterfaceOptions alexaUserInterfaceOptions);
}
