package com.amazon.alexa.handsfree.audio.api;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.handsfree.audio.AlexaConnectionListener;
import com.amazon.alexa.handsfree.audio.AlexaConnectionListenerFactory;
import com.amazon.alexa.handsfree.audio.AlexaServicesConnectionFactory;
import com.amazon.alexa.handsfree.audio.ConnectedListenerProvider;
import com.amazon.alexa.handsfree.audio.ReleaseListenerProvider;
import com.amazon.alexa.handsfree.audio.UserSpeechProvider;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes8.dex */
public class AudioReader {
    private static final String TAG = "AudioReader";
    private final AlexaConnectionListenerFactory mAlexaConnectionListenerFactory;
    private final AlexaServicesConnectionFactory mAlexaServicesConnectionFactory;
    private final ConnectedListenerProvider mConnectedListenerProvider;
    private AlexaServicesConnection mCurrentAlexaServicesConnection;
    private OnReleaseListener mOnReleaseListener;
    private final ReleaseListenerProvider mReleaseListenerProvider;
    private UserSpeechProvider mUserSpeechProvider;

    public AudioReader() {
        this(new AlexaServicesConnectionFactory(), new AlexaConnectionListenerFactory(), new ReleaseListenerProvider(), new ConnectedListenerProvider(), null);
    }

    public AlexaServicesConnection getCurrentAlexaServicesConnection() {
        return this.mCurrentAlexaServicesConnection;
    }

    public OnReleaseListener getOnReleaseListener() {
        return this.mOnReleaseListener;
    }

    public UserSpeechProvider getUserSpeechProvider() {
        return this.mUserSpeechProvider;
    }

    public void onAlexaConnectionConnected() {
        this.mUserSpeechProvider.requestDialog(this.mCurrentAlexaServicesConnection);
    }

    public void onAudioReceived(byte[] bArr) {
        if (this.mUserSpeechProvider == null) {
            Log.e(TAG, "onAudioReceived: audio received when wake word is not detected");
            return;
        }
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onAudioReceived: size ");
        outline107.append(bArr.length);
        Log.d(str, outline107.toString());
        this.mUserSpeechProvider.recordAudio(bArr);
    }

    public void onServiceDestroyed() {
        Log.d(TAG, "onServiceDestroyed");
        OnReleaseListener onReleaseListener = this.mOnReleaseListener;
        if (onReleaseListener != null) {
            onReleaseListener.onRelease();
        }
        this.mCurrentAlexaServicesConnection = null;
    }

    public void onTransferFinished() {
        if (this.mUserSpeechProvider == null) {
            Log.e(TAG, "onTransferFinished: userSpeechProvider is null");
            return;
        }
        Log.i(TAG, "onTransferFinished");
        this.mUserSpeechProvider.resetAudio();
    }

    public void onWakeWordDetected(@NonNull Context context, @NonNull UserSpeechProvider userSpeechProvider) {
        Log.i(TAG, "onWakeWordDetected");
        recordVoiceAppLogs(context);
        this.mUserSpeechProvider = userSpeechProvider;
        this.mCurrentAlexaServicesConnection = this.mAlexaServicesConnectionFactory.getAlexaServicesConnection(context);
        AlexaConnectionListener alexaConnectionListener = this.mAlexaConnectionListenerFactory.getAlexaConnectionListener(this);
        this.mOnReleaseListener = this.mReleaseListenerProvider.getAudioReleaser(alexaConnectionListener, this.mCurrentAlexaServicesConnection);
        userSpeechProvider.setOnReleaseListener(this.mOnReleaseListener);
        alexaConnectionListener.setOnReleaseListener(this.mOnReleaseListener);
        alexaConnectionListener.setOnConnectedListener(this.mConnectedListenerProvider.getConnectedListener(this.mCurrentAlexaServicesConnection));
        alexaConnectionListener.connect(this.mCurrentAlexaServicesConnection);
    }

    public void onWakeWordDetectionAborted(@NonNull String str) {
        this.mOnReleaseListener.onRelease();
        if (this.mUserSpeechProvider == null) {
            Log.e(TAG, "onWakeWordDetectionAborted: userSpeechProvider is null");
            return;
        }
        String str2 = TAG;
        Log.i(str2, "onWakeWordDetectionAborted: wake word detection aborted, message: " + str);
        this.mUserSpeechProvider.resetAudio();
        this.mCurrentAlexaServicesConnection = null;
    }

    @VisibleForTesting
    void recordVoiceAppLogs(@NonNull Context context) {
        AMPDInformationProvider aMPDInformationProvider = AMPDInformationProvider.getInstance(context);
        Log.d(TAG, String.format("Current voice app is %s with version %s", aMPDInformationProvider.getVoiceAppPackageNameString(), aMPDInformationProvider.getVoiceAppVersionString()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AudioReader(@NonNull AlexaServicesConnectionFactory alexaServicesConnectionFactory, @NonNull AlexaConnectionListenerFactory alexaConnectionListenerFactory, @NonNull ReleaseListenerProvider releaseListenerProvider, @NonNull ConnectedListenerProvider connectedListenerProvider, @Nullable UserSpeechProvider userSpeechProvider) {
        this.mAlexaServicesConnectionFactory = alexaServicesConnectionFactory;
        this.mAlexaConnectionListenerFactory = alexaConnectionListenerFactory;
        this.mReleaseListenerProvider = releaseListenerProvider;
        this.mConnectedListenerProvider = connectedListenerProvider;
        this.mUserSpeechProvider = userSpeechProvider;
    }
}
