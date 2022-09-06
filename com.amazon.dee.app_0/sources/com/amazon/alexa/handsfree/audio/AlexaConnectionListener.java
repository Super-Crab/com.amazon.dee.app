package com.amazon.alexa.handsfree.audio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.handsfree.audio.api.AudioReader;
import com.amazon.alexa.handsfree.audio.api.OnConnectedListener;
import com.amazon.alexa.handsfree.audio.api.OnReleaseListener;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public class AlexaConnectionListener implements AlexaServicesConnection.ConnectionListener {
    private static final String TAG = "AlexaConnectionListener";
    private final AudioReader mAudioReader;
    private OnConnectedListener mOnConnectedListener;
    private OnReleaseListener mOnReleaseListener;

    public AlexaConnectionListener(@NonNull AudioReader audioReader) {
        this.mAudioReader = audioReader;
    }

    public void connect(@NonNull AlexaServicesConnection alexaServicesConnection) {
        Log.i(TAG, "Attempting to connect");
        alexaServicesConnection.registerListener(this);
        alexaServicesConnection.connect();
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnected() {
        Log.i(TAG, "onConnected.");
        this.mAudioReader.onAlexaConnectionConnected();
        OnConnectedListener onConnectedListener = this.mOnConnectedListener;
        if (onConnectedListener != null) {
            onConnectedListener.onConnected();
        } else {
            Log.e(TAG, "mOnConnectedListener was not set");
        }
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onConnectingFailed(@NonNull AlexaConnectingFailedReason alexaConnectingFailedReason, @Nullable String str) {
        String str2 = TAG;
        Log.i(str2, "onConnectingFailed, reason: " + alexaConnectingFailedReason);
        OnReleaseListener onReleaseListener = this.mOnReleaseListener;
        if (onReleaseListener != null) {
            onReleaseListener.onRelease();
        } else {
            Log.e(TAG, "onReleaseListener was not set");
        }
    }

    @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
    public void onDisconnected() {
        Log.i(TAG, "onDisconnected");
        OnReleaseListener onReleaseListener = this.mOnReleaseListener;
        if (onReleaseListener != null) {
            onReleaseListener.onRelease();
        } else {
            Log.e(TAG, "onReleaseListener was not set");
        }
    }

    public void setOnConnectedListener(@NonNull OnConnectedListener onConnectedListener) {
        this.mOnConnectedListener = onConnectedListener;
    }

    public void setOnReleaseListener(@NonNull OnReleaseListener onReleaseListener) {
        this.mOnReleaseListener = onReleaseListener;
    }
}
