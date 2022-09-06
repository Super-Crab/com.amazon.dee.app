package com.amazon.alexa.handsfree.audio.metro;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.audio.AudioReaderFactory;
import com.amazon.alexa.handsfree.audio.AudioReaderMetadata;
import com.amazon.alexa.handsfree.audio.UserSpeechProvider;
import com.amazon.alexa.handsfree.audio.api.AudioEvent;
import com.amazon.alexa.handsfree.audio.api.AudioReader;
import com.amazon.alexa.handsfree.audio.api.AudioReaderCallback;
import com.amazon.alexa.handsfree.audio.features.AudioEnrollmentTypeResolver;
import com.amazon.alexa.handsfree.latencyreporter.Latency;
import com.amazon.alexa.handsfree.latencyreporter.LatencyReporterBuilder;
import com.amazon.alexa.handsfree.latencyreporter.TimestampType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.utils.security.SignatureVerifier;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.magiear.handsfree.util.Common;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes8.dex */
public class MetroAudioReaderService extends Service {
    private static final String TAG = MetroAudioReaderService.class.getSimpleName();
    private AudioEnrollmentTypeResolver mAudioEnrollmentTypeResolver;
    private AudioReader mAudioReader;
    private AudioReaderFactory mAudioReaderFactory;
    private boolean mConnected;
    private final Set<MetroAudioReaderServiceConnection> mConnectionSet;
    private LatencyReporterBuilder mLatencyReporterBuilder;
    private MetroAudioReaderServiceConnection mMetroAudioReaderServiceConnection;
    private SignatureVerifier mSignatureVerifier;

    public MetroAudioReaderService() {
        this(null, null, null, null, null);
    }

    private void setupAndBeginAudioRecording(boolean z) {
        if (this.mAudioReader != null && this.mMetroAudioReaderServiceConnection != null) {
            if (!((KeyguardManager) getSystemService("keyguard")).isDeviceLocked()) {
                Log.d(TAG, "Keyguard manager is unlocked. Reporting latency metrics.");
                this.mLatencyReporterBuilder.withTimestamp(Latency.PARTNER_WAKE_WORD_DETECTION_LATENCY, TimestampType.END, System.currentTimeMillis()).withTimestamp(Latency.ALEXA_SERVICE_WAKE_WORD_DETECTION_LATENCY, TimestampType.START, System.currentTimeMillis()).build().report();
            }
            AudioReaderCallback audioReaderCallback = new AudioReaderCallback() { // from class: com.amazon.alexa.handsfree.audio.metro.MetroAudioReaderService.1
                @Override // com.amazon.alexa.handsfree.audio.api.AudioReaderCallback
                public void onEvent(@NonNull AudioEvent audioEvent) {
                    String str = MetroAudioReaderService.TAG;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received event ");
                    outline107.append(audioEvent.name());
                    Log.d(str, outline107.toString());
                    if (audioEvent == AudioEvent.AUDIO_RECORD_STOPPED) {
                        MetroAudioReaderService.this.mMetroAudioReaderServiceConnection.stopAudio();
                        if (MetroAudioReaderService.this.mConnected) {
                            Log.d(MetroAudioReaderService.TAG, "Connected. Unbinding from service.");
                            MetroAudioReaderService metroAudioReaderService = MetroAudioReaderService.this;
                            metroAudioReaderService.unbindService(metroAudioReaderService.mMetroAudioReaderServiceConnection);
                            MetroAudioReaderService.this.mConnectionSet.remove(MetroAudioReaderService.this.mMetroAudioReaderServiceConnection);
                            MetroAudioReaderService.this.mConnected = false;
                            return;
                        }
                        Log.d(MetroAudioReaderService.TAG, "Not connected to service. Nothing to do.");
                    }
                }
            };
            AudioReaderMetadata audioReaderMetadata = new AudioReaderMetadata(z);
            Log.d(TAG, "Calling AudioReader onWakeWordDetected.");
            this.mAudioReader.onWakeWordDetected(this, getUserSpeechProvider(this, audioReaderCallback, audioReaderMetadata));
            this.mMetroAudioReaderServiceConnection.startRecording();
            return;
        }
        Log.e(TAG, "onStartCommand: audio reader is null!");
    }

    @NonNull
    @VisibleForTesting
    MetroAudioReaderServiceConnection getMetroAudioReaderServiceConnection() {
        return new MetroAudioReaderServiceConnection(new IAudioReaderCallbackProvider(this.mAudioReader).getIAudioReaderCallback());
    }

    @VisibleForTesting
    UserSpeechProvider getUserSpeechProvider(@NonNull Context context, @NonNull AudioReaderCallback audioReaderCallback, @NonNull AudioReaderMetadata audioReaderMetadata) {
        return new UserSpeechProvider(context, audioReaderCallback, audioReaderMetadata);
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(@NonNull Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        this.mAudioReaderFactory = new AudioReaderFactory(this);
        this.mLatencyReporterBuilder = new LatencyReporterBuilder(this);
        this.mAudioEnrollmentTypeResolver = new AudioEnrollmentTypeResolver(this);
        this.mSignatureVerifier = new SignatureVerifier(this);
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        if (this.mAudioReader == null) {
            Log.e(TAG, "onDestroy: audio reader is null!");
            return;
        }
        for (MetroAudioReaderServiceConnection metroAudioReaderServiceConnection : this.mConnectionSet) {
            unbindService(metroAudioReaderServiceConnection);
        }
        this.mAudioReader.onServiceDestroyed();
    }

    @Override // android.app.Service
    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        if (intent != null && intent.getAction() != null) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onStartCommand: intent action: ");
            outline107.append(intent.getAction());
            Log.d(str, outline107.toString());
            if (!this.mSignatureVerifier.verify(Common.HANDSFREE_ASSISTANT_PACKAGE_NAME)) {
                Log.d(TAG, "No valid Voice APK has been found in the system. Skipping request");
                return 2;
            }
            if (Common.INTENT_ACTION_WAKEWORD_ARRIVED.equals(intent.getAction())) {
                Log.i(TAG, "onStartCommand: Alexa detected by first stage wake word engine.");
                Intent intent2 = new Intent(Common.INTENT_ACTION_AUDIO_READER);
                intent2.setPackage(Common.HANDSFREE_ASSISTANT_PACKAGE_NAME);
                this.mAudioReader = this.mAudioReaderFactory.createAudioReader();
                this.mMetroAudioReaderServiceConnection = getMetroAudioReaderServiceConnection();
                this.mConnected = bindService(intent2, this.mMetroAudioReaderServiceConnection, 1);
                this.mConnectionSet.add(this.mMetroAudioReaderServiceConnection);
                String str2 = TAG;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("is_uvr_enabled: ");
                outline1072.append(intent.getBooleanExtra(Common.EXTRA_IS_UVR_ENABLED, true));
                Log.i(str2, outline1072.toString());
                if (this.mAudioEnrollmentTypeResolver.getSpeakerVerificationEnrollmentType() == EnrollmentType._1PSV) {
                    setupAndBeginAudioRecording(true);
                }
            } else if (Common.INTENT_ACTION_WAKEWORD_DETECTED.equals(intent.getAction())) {
                Log.i(TAG, "onStartCommand: Alexa confirmed by second stage wake word engine.");
                boolean booleanExtra = intent.getBooleanExtra(Common.EXTRA_UVR_STATUS, false);
                String str3 = TAG;
                Log.d(str3, "UVR Status: " + booleanExtra);
                setupAndBeginAudioRecording(booleanExtra);
            }
            return 2;
        }
        Log.w(TAG, "onStartCommand: intent is not ready yet, redeliver the intent.");
        stopSelf();
        return 2;
    }

    @VisibleForTesting
    MetroAudioReaderService(@Nullable AudioReaderFactory audioReaderFactory, @Nullable MetroAudioReaderServiceConnection metroAudioReaderServiceConnection, @Nullable SignatureVerifier signatureVerifier, @Nullable LatencyReporterBuilder latencyReporterBuilder, @Nullable AudioEnrollmentTypeResolver audioEnrollmentTypeResolver) {
        this.mConnectionSet = Collections.synchronizedSet(new HashSet());
        this.mAudioReaderFactory = audioReaderFactory;
        this.mMetroAudioReaderServiceConnection = metroAudioReaderServiceConnection;
        this.mSignatureVerifier = signatureVerifier;
        this.mLatencyReporterBuilder = latencyReporterBuilder;
        this.mAudioEnrollmentTypeResolver = audioEnrollmentTypeResolver;
    }
}
