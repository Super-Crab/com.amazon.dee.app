package com.amazon.alexa.drive.userstudy;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaAudioPlaybackListener;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaPlaybackState;
import com.amazon.alexa.api.AlexaPlayerInfoCardListener;
import com.amazon.alexa.api.AlexaPlayerInfoState;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.compat.AlexaServices;
import com.amazon.alexa.api.compat.AlexaState;
import com.amazon.alexa.api.compat.AlexaStateListener;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.text.SimpleDateFormat;
import java.util.Locale;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes7.dex */
public class DriverDistractionLog {
    public static final String ALEXA_ACTION = "Action";
    public static final String ALEXA_EVENT = "Event";
    public static final String ALEXA_STATE_ERROR = "Error";
    public static final String ALEXA_TOUCH = "Touch";
    public static final String EMPTY_STRING = "";
    public static final String TAG = "DriverDistractionLog";
    private static final String TIME_STAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    @VisibleForTesting
    static boolean sFeatureEnabled = false;
    @VisibleForTesting
    AlexaServicesConnection servicesConnection;
    @VisibleForTesting
    String alexaState = "";
    @VisibleForTesting
    String pageName = "";
    @VisibleForTesting
    final AlexaServicesConnection.ConnectionListener connectionListener = new AlexaServicesConnection.ConnectionListener() { // from class: com.amazon.alexa.drive.userstudy.DriverDistractionLog.1
        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnected() {
            DriverDistractionLog driverDistractionLog = DriverDistractionLog.this;
            AlexaServices.Recognizer.registerListener(driverDistractionLog.servicesConnection, driverDistractionLog.alexaStateListener);
            DriverDistractionLog driverDistractionLog2 = DriverDistractionLog.this;
            AlexaServices.AudioPlaybackControl.registerListener(driverDistractionLog2.servicesConnection, driverDistractionLog2.audioPlaybackListener);
            DriverDistractionLog driverDistractionLog3 = DriverDistractionLog.this;
            AlexaServices.Cards.registerPlayerInfoCardListener(driverDistractionLog3.servicesConnection, driverDistractionLog3.playerInfoCardListener);
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
            DriverDistractionLog.log("Error", DriverDistractionLog.this.pageName, str);
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onDisconnected() {
            DriverDistractionLog.this.servicesConnection.deregisterListener(this);
        }
    };
    @VisibleForTesting
    final AlexaStateListener alexaStateListener = new AlexaStateListener() { // from class: com.amazon.alexa.drive.userstudy.-$$Lambda$DriverDistractionLog$dtscKLOZClvwtpYd_W73IL_PGVU
        @Override // com.amazon.alexa.api.compat.AlexaStateListener
        public final void onAlexaStateChanged(AlexaState alexaState) {
            DriverDistractionLog.this.lambda$new$0$DriverDistractionLog(alexaState);
        }
    };
    @VisibleForTesting
    final AlexaAudioPlaybackListener audioPlaybackListener = new AlexaAudioPlaybackListener() { // from class: com.amazon.alexa.drive.userstudy.-$$Lambda$DriverDistractionLog$1frDtLiQ7yKJNcLJJBg_Q4cfoh8
        @Override // com.amazon.alexa.api.AlexaAudioPlaybackListener
        public final void onAudioPlaybackChanged(AlexaPlaybackState alexaPlaybackState) {
            DriverDistractionLog.this.lambda$new$1$DriverDistractionLog(alexaPlaybackState);
        }
    };
    @VisibleForTesting
    final AlexaPlayerInfoCardListener playerInfoCardListener = new AlexaPlayerInfoCardListener() { // from class: com.amazon.alexa.drive.userstudy.DriverDistractionLog.2
        @Override // com.amazon.alexa.api.AlexaPlayerInfoCardListener
        public void onAudioItemStateChanged(AlexaPlayerInfoState alexaPlayerInfoState, String str, long j) {
            DriverDistractionLog driverDistractionLog = DriverDistractionLog.this;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Player:");
            outline107.append(DriverDistractionLog.this.capitalize(alexaPlayerInfoState.name()));
            driverDistractionLog.logAction(outline107.toString());
        }

        @Override // com.amazon.alexa.api.AlexaPlayerInfoCardListener
        public void onReceivedPlayerInfoCard(String str, boolean z) {
            DriverDistractionLog driverDistractionLog = DriverDistractionLog.this;
            driverDistractionLog.logAlexaState("ReceivedPlayerInfoCard|fromVoiceRequest:" + z);
        }
    };

    public DriverDistractionLog(Context context) {
        this.servicesConnection = new AlexaServicesConnection(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String lowerCase = str.toLowerCase();
        return lowerCase.substring(0, 1).toUpperCase() + lowerCase.substring(1);
    }

    private static String getDate(long j) {
        return new SimpleDateFormat(TIME_STAMP_FORMAT, Locale.US).format(Long.valueOf(j));
    }

    public static void log(String str, String str2, String str3) {
        if (!sFeatureEnabled) {
            return;
        }
        String date = getDate(System.currentTimeMillis());
        String str4 = TAG;
        Log.i(str4, date + JsonReaderKt.COMMA + str + JsonReaderKt.COMMA + str2 + JsonReaderKt.COMMA + str3);
        logDriveModeStatus(date, str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logAction(String str) {
        log("Action", this.pageName, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logAlexaState(String str) {
        log(this.alexaState, this.pageName, str);
    }

    private static void logDriveModeStatus(String str, String str2, String str3, String str4) {
        try {
            ModeService modeService = (ModeService) ComponentRegistry.getInstance().get(ModeService.class).orNull();
            if (modeService == null) {
                return;
            }
            modeService.logModeStatus(str, str2, str3, str4);
        } catch (Exception unused) {
            Log.e(TAG, "Mode service not available!");
        }
    }

    public static void logError(String str, String str2) {
        if (!sFeatureEnabled) {
            return;
        }
        String date = getDate(System.currentTimeMillis());
        String str3 = TAG;
        Log.i(str3, date + JsonReaderKt.COMMA + "Error" + JsonReaderKt.COMMA + str + JsonReaderKt.COMMA + str2);
        logDriveModeStatus(date, "Error", str, str2);
    }

    public static void logEvent(String str, String str2) {
        if (!sFeatureEnabled) {
            return;
        }
        String date = getDate(System.currentTimeMillis());
        String str3 = TAG;
        Log.i(str3, date + JsonReaderKt.COMMA + "Event" + JsonReaderKt.COMMA + str + JsonReaderKt.COMMA + str2);
        logDriveModeStatus(date, "Event", str, str2);
    }

    public static void logTouch(String str, String str2) {
        if (!sFeatureEnabled) {
            return;
        }
        String date = getDate(System.currentTimeMillis());
        String str3 = TAG;
        Log.i(str3, date + JsonReaderKt.COMMA + "Touch" + JsonReaderKt.COMMA + str + JsonReaderKt.COMMA + str2);
        logDriveModeStatus(date, "Touch", str, str2);
    }

    public /* synthetic */ void lambda$new$0$DriverDistractionLog(AlexaState alexaState) {
        this.alexaState = capitalize(alexaState.name());
        logAlexaState("");
    }

    public /* synthetic */ void lambda$new$1$DriverDistractionLog(AlexaPlaybackState alexaPlaybackState) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Audio:");
        outline107.append(alexaPlaybackState.toString());
        logAlexaState(outline107.toString());
    }

    public void start() {
        if (!sFeatureEnabled) {
            return;
        }
        this.servicesConnection.registerListener(this.connectionListener);
        this.servicesConnection.connect();
    }

    public void stop() {
        if (!sFeatureEnabled) {
            return;
        }
        AlexaServices.Recognizer.deregisterListener(this.servicesConnection, this.alexaStateListener);
        AlexaServices.AudioPlaybackControl.deregisterListener(this.servicesConnection, this.audioPlaybackListener);
        AlexaServices.Cards.deregisterPlayerInfoCardListener(this.servicesConnection, this.playerInfoCardListener);
        this.servicesConnection.disconnect();
    }

    public void updateUiPage(String str) {
        if (!sFeatureEnabled) {
            return;
        }
        this.pageName = str;
    }
}
