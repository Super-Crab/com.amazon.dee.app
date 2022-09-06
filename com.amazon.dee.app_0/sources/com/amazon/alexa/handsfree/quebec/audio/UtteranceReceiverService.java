package com.amazon.alexa.handsfree.quebec.audio;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.audio.AudioReaderFactory;
import com.amazon.alexa.handsfree.audio.api.AudioReader;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.utils.security.SignatureVerifier;
import com.quicinc.voice.activation.IUtteranceProviderService;
/* loaded from: classes8.dex */
public class UtteranceReceiverService extends Service {
    private static final String TAG = UtteranceReceiverService.class.getSimpleName();
    private AudioReader mAudioReader;
    private IBinder mBinder = new LocalBinder();
    private QuebecUtteranceProviderServiceConnection mQuebecUtteranceProviderServiceConnection;
    private SignatureVerifier mSignatureVerifier;

    /* loaded from: classes8.dex */
    class LocalBinder extends Binder {
        LocalBinder() {
        }

        UtteranceReceiverService getService() {
            return UtteranceReceiverService.this;
        }
    }

    public UtteranceReceiverService() {
    }

    private void initialize() {
        Log.d(TAG, "initialize: Initialize via app change broadcast receiver.");
        this.mSignatureVerifier = new SignatureVerifier(getApplicationContext());
        this.mAudioReader = new AudioReaderFactory(getApplicationContext()).createAudioReader();
        this.mQuebecUtteranceProviderServiceConnection = new QuebecUtteranceProviderServiceConnection(getApplicationContext(), this.mAudioReader);
    }

    @VisibleForTesting
    boolean bindServiceConnection() {
        Log.d(TAG, "Trying to bind service.");
        if (!this.mSignatureVerifier.verify("com.quicinc.voice.activation")) {
            Log.w(TAG, "No valid Voice APK has been found in the system. Skipping request.");
            return false;
        }
        Intent intent = new Intent(IUtteranceProviderService.class.getName());
        intent.setPackage(IUtteranceProviderService.class.getPackage().getName());
        return bindService(intent, this.mQuebecUtteranceProviderServiceConnection, 4097);
    }

    @Override // android.app.Service
    public IBinder onBind(@NonNull Intent intent) {
        return this.mBinder;
    }

    @Override // android.app.Service
    public void onCreate() {
        initialize();
        if (bindServiceConnection()) {
            Log.d(TAG, "IUtteranceProviderService bound.");
        } else {
            Log.d(TAG, "IUtteranceProviderService not bound.");
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        this.mBinder = null;
        this.mAudioReader = null;
        this.mQuebecUtteranceProviderServiceConnection = null;
        super.onDestroy();
    }

    @Override // android.app.Service
    public boolean onUnbind(@Nullable Intent intent) {
        Log.d(TAG, "onUnbind");
        unbindService(this.mQuebecUtteranceProviderServiceConnection);
        this.mAudioReader.onTransferFinished();
        return false;
    }

    @VisibleForTesting
    UtteranceReceiverService(@NonNull SignatureVerifier signatureVerifier, @NonNull QuebecUtteranceProviderServiceConnection quebecUtteranceProviderServiceConnection, @NonNull AudioReader audioReader) {
        this.mSignatureVerifier = signatureVerifier;
        this.mQuebecUtteranceProviderServiceConnection = quebecUtteranceProviderServiceConnection;
        this.mAudioReader = audioReader;
    }
}
