package com.amazon.alexa.handsfree.audio.metro;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.magiear.handsfree.util.IAudioReader;
import com.magiear.handsfree.util.IAudioReaderCallback;
import com.magiear.handsfree.util.ParamDefinition;
/* loaded from: classes8.dex */
public class MetroAudioReaderServiceConnection implements ServiceConnection {
    @VisibleForTesting
    static final int AUDIO_CHUNK_TIME_IN_MS = 20;
    private static final String TAG = "MetroAudioServiceConn";
    private final IAudioReaderCallback mAudioReaderCallback;
    private final IAudioReaderBinderConverter mIAudioReaderBinderConverter;
    private boolean mIsRecordPending;
    private IAudioReader mMetroAudioReader;

    public MetroAudioReaderServiceConnection(@NonNull IAudioReaderCallback iAudioReaderCallback) {
        this(iAudioReaderCallback, new IAudioReaderBinderConverter(), null, false);
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(@NonNull ComponentName componentName, @Nullable IBinder iBinder) {
        if (iBinder == null) {
            Log.e(TAG, "onServiceConnected: No IBinder with the service");
            return;
        }
        this.mMetroAudioReader = this.mIAudioReaderBinderConverter.convert(iBinder);
        if (this.mMetroAudioReader == null) {
            Log.e(TAG, "onServiceConnected: service not connected");
            return;
        }
        try {
            Log.d(TAG, "onServiceConnected: register audio reader service with callback.");
            this.mMetroAudioReader.registerWakeupCallback(this.mAudioReaderCallback);
            if (!this.mIsRecordPending) {
                return;
            }
            startRecording();
            this.mIsRecordPending = false;
        } catch (RemoteException e) {
            Log.e(TAG, "onServiceConnected: connection failed.", e, new Object[0]);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(@NonNull ComponentName componentName) {
        this.mMetroAudioReader = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void startRecording() {
        if (this.mMetroAudioReader == null) {
            Log.d(TAG, "startRecording: Metro audio service is not yet connected, yield until connected");
            this.mIsRecordPending = true;
            return;
        }
        try {
            Bundle bundle = new Bundle();
            bundle.putInt(ParamDefinition.KEY_AUDIO_START_CAP_MS, 20);
            this.mMetroAudioReader.startRecording(bundle);
        } catch (RemoteException e) {
            Log.e(TAG, "onServiceConnected: connection failed.", e, new Object[0]);
        }
    }

    public void stopAudio() {
        Log.d(TAG, "stopAudio called");
        IAudioReader iAudioReader = this.mMetroAudioReader;
        if (iAudioReader == null) {
            Log.e(TAG, "stopAudio: service not connected");
            return;
        }
        try {
            iAudioReader.stopRecording(null);
            Log.d(TAG, "stopRecording successful");
        } catch (RemoteException e) {
            Log.e(TAG, "stopAudio: connection failed", e, new Object[0]);
        }
    }

    @VisibleForTesting
    MetroAudioReaderServiceConnection(@NonNull IAudioReaderCallback iAudioReaderCallback, @NonNull IAudioReaderBinderConverter iAudioReaderBinderConverter, @Nullable IAudioReader iAudioReader, boolean z) {
        this.mAudioReaderCallback = iAudioReaderCallback;
        this.mIAudioReaderBinderConverter = iAudioReaderBinderConverter;
        this.mMetroAudioReader = iAudioReader;
        this.mIsRecordPending = z;
    }
}
