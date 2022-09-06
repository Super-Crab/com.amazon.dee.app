package com.amazon.alexa.voiceui;

import androidx.annotation.RequiresPermission;
import com.amazon.alexa.api.AlexaCardListener;
import com.amazon.alexa.api.AlexaDriveModeListener;
import com.amazon.alexa.api.AlexaServices;
import com.amazon.alexa.api.AlexaServicesApis;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.AlexaStateListener;
import com.amazon.alexa.api.AlexaUserInterfaceOptions;
import com.amazon.alexa.api.AlexaUserSpeechListener;
import java.util.Locale;
/* loaded from: classes11.dex */
public class AlexaServicesWrapper implements AlexaServicesApis {
    private final AlexaServicesConnection alexaServicesConnection;

    public AlexaServicesWrapper(AlexaServicesConnection alexaServicesConnection) {
        this.alexaServicesConnection = alexaServicesConnection;
    }

    @Override // com.amazon.alexa.voiceui.AlexaServicesApis
    public void cancel() {
        AlexaServices.Recognizer.cancelUserInteraction(this.alexaServicesConnection);
    }

    @Override // com.amazon.alexa.voiceui.AlexaServicesApis
    public void connect() {
        this.alexaServicesConnection.connect();
    }

    @Override // com.amazon.alexa.voiceui.AlexaServicesApis
    public void deregisterAlexaStateListener(AlexaStateListener alexaStateListener) {
        AlexaServices.Recognizer.deregisterListener(this.alexaServicesConnection, alexaStateListener);
    }

    @Override // com.amazon.alexa.voiceui.AlexaServicesApis
    public void deregisterCardListener(AlexaCardListener alexaCardListener) {
        AlexaServices.Cards.deregisterAlexaCardRendererListener(this.alexaServicesConnection, alexaCardListener);
    }

    @Override // com.amazon.alexa.voiceui.AlexaServicesApis
    public void deregisterConnectionListener(AlexaServicesConnection.ConnectionListener connectionListener) {
        this.alexaServicesConnection.deregisterListener(connectionListener);
    }

    @Override // com.amazon.alexa.voiceui.AlexaServicesApis
    public void deregisterDriveModeListener(AlexaDriveModeListener alexaDriveModeListener) {
        AlexaServicesApis.DriveMode.deregisterDriveModeListener(this.alexaServicesConnection, alexaDriveModeListener);
    }

    @Override // com.amazon.alexa.voiceui.AlexaServicesApis
    public void deregisterUserSpeechListener(AlexaUserSpeechListener alexaUserSpeechListener) {
        AlexaServices.Recognizer.deregisterUserSpeechListener(this.alexaServicesConnection, alexaUserSpeechListener);
    }

    @Override // com.amazon.alexa.voiceui.AlexaServicesApis
    public void disconnect() {
        this.alexaServicesConnection.disconnect();
    }

    @Override // com.amazon.alexa.voiceui.AlexaServicesApis
    public Locale getLocale() {
        return AlexaServices.Settings.getLocale(this.alexaServicesConnection);
    }

    @Override // com.amazon.alexa.voiceui.AlexaServicesApis
    public boolean isConnected() {
        return this.alexaServicesConnection.isConnected();
    }

    @Override // com.amazon.alexa.voiceui.AlexaServicesApis
    public void registerAlexaStateListener(AlexaStateListener alexaStateListener) {
        AlexaServices.Recognizer.registerListener(this.alexaServicesConnection, alexaStateListener);
    }

    @Override // com.amazon.alexa.voiceui.AlexaServicesApis
    public void registerCardListener(AlexaCardListener alexaCardListener) {
        AlexaServices.Cards.registerAlexaCardRendererListener(this.alexaServicesConnection, alexaCardListener);
    }

    @Override // com.amazon.alexa.voiceui.AlexaServicesApis
    public void registerConnectionListener(AlexaServicesConnection.ConnectionListener connectionListener) {
        this.alexaServicesConnection.registerListener(connectionListener);
    }

    @Override // com.amazon.alexa.voiceui.AlexaServicesApis
    public void registerDriveModeListener(AlexaDriveModeListener alexaDriveModeListener) {
        AlexaServicesApis.DriveMode.registerDriveModeListener(this.alexaServicesConnection, alexaDriveModeListener);
    }

    @Override // com.amazon.alexa.voiceui.AlexaServicesApis
    public void registerUserSpeechListener(AlexaUserSpeechListener alexaUserSpeechListener) {
        AlexaServices.Recognizer.registerUserSpeechListener(this.alexaServicesConnection, alexaUserSpeechListener);
    }

    @Override // com.amazon.alexa.voiceui.AlexaServicesApis
    public void release() {
        this.alexaServicesConnection.release();
    }

    @Override // com.amazon.alexa.voiceui.AlexaServicesApis
    @RequiresPermission("android.permission.RECORD_AUDIO")
    public void start(String str) {
        AlexaServices.Recognizer.start(this.alexaServicesConnection, str);
    }

    @Override // com.amazon.alexa.voiceui.AlexaServicesApis
    @RequiresPermission("android.permission.RECORD_AUDIO")
    public void start(String str, AlexaUserInterfaceOptions alexaUserInterfaceOptions) {
        AlexaServices.Recognizer.start(this.alexaServicesConnection, str, alexaUserInterfaceOptions);
    }
}
