package com.amazon.alexa.handsfree.quebec.audio;

import android.app.KeyguardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.audio.AudioReaderMetadata;
import com.amazon.alexa.handsfree.audio.UserSpeechProvider;
import com.amazon.alexa.handsfree.audio.api.AudioEvent;
import com.amazon.alexa.handsfree.audio.api.AudioReader;
import com.amazon.alexa.handsfree.audio.api.AudioReaderCallback;
import com.amazon.alexa.handsfree.latencyreporter.Latency;
import com.amazon.alexa.handsfree.latencyreporter.LatencyReporterBuilder;
import com.amazon.alexa.handsfree.latencyreporter.TimestampType;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.quebec.QuebecAPIConstants;
import com.quicinc.voice.activation.IResultCallback;
import com.quicinc.voice.activation.IUtteranceProviderService;
import com.quicinc.voice.activation.IUtteranceReceiverCallback;
import java.util.UUID;
/* loaded from: classes8.dex */
public class QuebecUtteranceProviderServiceConnection implements ServiceConnection {
    private static final String TAG = QuebecUtteranceProviderServiceConnection.class.getSimpleName();
    private AudioReader mAudioReader;
    private Context mContext;
    private LatencyReporterBuilder mLatencyReporterBuilder;
    private IUtteranceProviderService mQuebecUtteranceService;

    public QuebecUtteranceProviderServiceConnection(@NonNull Context context, @NonNull AudioReader audioReader) {
        this(context, audioReader, new LatencyReporterBuilder(context), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LatencyReporterBuilder getPartnerWakeWordDetectionLatencyBuilder(@NonNull Bundle bundle) {
        String uuid = UUID.randomUUID().toString();
        long j = bundle.getLong(QuebecAPIConstants.UTTERANCE_AUDIO_METADATA_START_RECORDING_TIMESTAMP);
        long j2 = bundle.getLong(QuebecAPIConstants.UTTERANCE_SECOND_STAGE_START_TIMESTAMP);
        long j3 = bundle.getLong(QuebecAPIConstants.UTTERANCE_SECOND_STAGE_FINISH_TIMESTAMP);
        Log.i(TAG, String.format("PartnerWakeWordDetectionLatency: start recording time: %d\tstart second stage: %d\tfinish second stage: %d\taudio sample rate: %d\taudio sample bits: %d\taudio channels: %d", Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3), Integer.valueOf(bundle.getInt(QuebecAPIConstants.UTTERANCE_AUDIO_METADATA_AUDIO_SAMPLE_RATE)), Integer.valueOf(bundle.getInt(QuebecAPIConstants.UTTERANCE_AUDIO_METADATA_AUDIO_SAMPLE_BITS)), Integer.valueOf(bundle.getInt(QuebecAPIConstants.UTTERANCE_AUDIO_METADATA_AUDIO_CHANNELS))));
        return this.mLatencyReporterBuilder.withTimestamp(Latency.PARTNER_WAKE_WORD_DETECTION_LATENCY_ANY, TimestampType.START, uuid, j).withTimestamp(Latency.PARTNER_WAKE_WORD_DETECTION_LATENCY_ANY, TimestampType.END, uuid, j3).withTimestamp(Latency.PARTNER_WAKE_WORD_FIRST_STAGE_LATENCY, TimestampType.START, uuid, j).withTimestamp(Latency.PARTNER_WAKE_WORD_FIRST_STAGE_LATENCY, TimestampType.END, uuid, j2).withTimestamp(Latency.PARTNER_WAKE_WORD_SECOND_STAGE_LATENCY, TimestampType.START, uuid, j2).withTimestamp(Latency.PARTNER_WAKE_WORD_SECOND_STAGE_LATENCY, TimestampType.END, uuid, j3).withTimestamp(Latency.OVERALL_VOICE_CHROME_LATENCY, TimestampType.START, j);
    }

    @VisibleForTesting
    IUtteranceProviderService createUtteranceProviderService(@Nullable IBinder iBinder) {
        return IUtteranceProviderService.Stub.asInterface(iBinder);
    }

    @VisibleForTesting
    AudioReaderCallback getAudioReaderCallback() {
        return new AudioReaderCallback() { // from class: com.amazon.alexa.handsfree.quebec.audio.QuebecUtteranceProviderServiceConnection.2
            @Override // com.amazon.alexa.handsfree.audio.api.AudioReaderCallback
            public void onEvent(@NonNull AudioEvent audioEvent) {
                if (audioEvent == AudioEvent.AUDIO_RECORD_STOPPED) {
                    Log.d(QuebecUtteranceProviderServiceConnection.TAG, "AUDIO_RECORD_STOPPED event occurred");
                    if (QuebecUtteranceProviderServiceConnection.this.mQuebecUtteranceService == null) {
                        Log.e(QuebecUtteranceProviderServiceConnection.TAG, "stopAudio: service not connected");
                        return;
                    }
                    try {
                        QuebecUtteranceProviderServiceConnection.this.mQuebecUtteranceService.stopRecording(QuebecUtteranceProviderServiceConnection.this.getResultCallback(), new Bundle());
                    } catch (RemoteException e) {
                        Log.e(QuebecUtteranceProviderServiceConnection.TAG, "stopAudio: connection failed", e, new Object[0]);
                    }
                }
            }
        };
    }

    @VisibleForTesting
    IResultCallback getResultCallback() {
        return new IResultCallback.Stub() { // from class: com.amazon.alexa.handsfree.quebec.audio.QuebecUtteranceProviderServiceConnection.3
            @Override // com.quicinc.voice.activation.IResultCallback
            public void onFailure(@NonNull Bundle bundle) throws RemoteException {
                Log.d(QuebecUtteranceProviderServiceConnection.TAG, "getResultCallback: onFailure.");
            }

            @Override // com.quicinc.voice.activation.IResultCallback
            public void onSuccess(@NonNull Bundle bundle) throws RemoteException {
                Log.d(QuebecUtteranceProviderServiceConnection.TAG, "getResultCallback: onSuccess.");
            }
        };
    }

    @VisibleForTesting
    UserSpeechProvider getUserSpeechProvider(@NonNull Context context, @NonNull AudioReaderCallback audioReaderCallback, @NonNull AudioReaderMetadata audioReaderMetadata, @Nullable ParcelFileDescriptor parcelFileDescriptor) {
        return new UserSpeechProvider(context, audioReaderCallback, audioReaderMetadata, parcelFileDescriptor);
    }

    @VisibleForTesting
    IUtteranceReceiverCallback getUtteranceCallback() {
        return new IUtteranceReceiverCallback.Stub() { // from class: com.amazon.alexa.handsfree.quebec.audio.QuebecUtteranceProviderServiceConnection.1
            @Override // com.quicinc.voice.activation.IUtteranceReceiverCallback
            public void onUtteranceReceived(@Nullable ParcelFileDescriptor parcelFileDescriptor, @NonNull Bundle bundle) {
                long clearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    String str = QuebecUtteranceProviderServiceConnection.TAG;
                    Log.i(str, "onUtteranceReceived: Audio received with file descriptor: " + parcelFileDescriptor);
                    if (!((KeyguardManager) QuebecUtteranceProviderServiceConnection.this.mContext.getSystemService("keyguard")).isDeviceLocked()) {
                        QuebecUtteranceProviderServiceConnection.this.getPartnerWakeWordDetectionLatencyBuilder(bundle).withTimestamp(Latency.ALEXA_SERVICE_WAKE_WORD_DETECTION_LATENCY, TimestampType.START, System.currentTimeMillis()).build().report();
                    }
                    QuebecUtteranceProviderServiceConnection.this.mAudioReader.onWakeWordDetected(QuebecUtteranceProviderServiceConnection.this.mContext, QuebecUtteranceProviderServiceConnection.this.getUserSpeechProvider(QuebecUtteranceProviderServiceConnection.this.mContext, QuebecUtteranceProviderServiceConnection.this.getAudioReaderCallback(), new AudioReaderMetadata(true), parcelFileDescriptor));
                } finally {
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                }
            }

            @Override // com.quicinc.voice.activation.IUtteranceReceiverCallback
            public void onUtteranceVerificationFailure(@Nullable ParcelFileDescriptor parcelFileDescriptor, @NonNull Bundle bundle) throws RemoteException {
                Log.i(QuebecUtteranceProviderServiceConnection.TAG, "VoiceAppWakeWordDetection: Utterance Rejected.");
                if (!((KeyguardManager) QuebecUtteranceProviderServiceConnection.this.mContext.getSystemService("keyguard")).isDeviceLocked()) {
                    QuebecUtteranceProviderServiceConnection.this.getPartnerWakeWordDetectionLatencyBuilder(bundle).build().report();
                }
                QuebecUtteranceProviderServiceConnection.this.mQuebecUtteranceService.stopRecording(QuebecUtteranceProviderServiceConnection.this.getResultCallback(), bundle);
            }
        };
    }

    @Nullable
    IUtteranceProviderService getUtteranceProviderService() {
        return this.mQuebecUtteranceService;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(@NonNull ComponentName componentName, @Nullable IBinder iBinder) {
        if (iBinder == null) {
            Log.e(TAG, "onServiceConnected: Null IBinder.");
            return;
        }
        this.mQuebecUtteranceService = createUtteranceProviderService(iBinder);
        if (this.mQuebecUtteranceService == null) {
            Log.e(TAG, "onServiceConnected: service not connected.");
            return;
        }
        try {
            Bundle bundle = new Bundle();
            this.mQuebecUtteranceService.registerAudioReceiverCallback(getUtteranceCallback(), bundle);
            Log.d(TAG, "onServiceConnected: Service connected.");
        } catch (RemoteException e) {
            Log.e(TAG, "onServiceConnected: registration failed.", e, new Object[0]);
            this.mQuebecUtteranceService = null;
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(@NonNull ComponentName componentName) {
        this.mQuebecUtteranceService = null;
        Log.d(TAG, "onServiceDisconnected: Service disconnected.");
    }

    @VisibleForTesting
    QuebecUtteranceProviderServiceConnection(@NonNull Context context, @NonNull AudioReader audioReader, @NonNull LatencyReporterBuilder latencyReporterBuilder, @Nullable IUtteranceProviderService iUtteranceProviderService) {
        this.mContext = context.getApplicationContext();
        this.mAudioReader = audioReader;
        this.mLatencyReporterBuilder = latencyReporterBuilder;
        this.mQuebecUtteranceService = iUtteranceProviderService;
    }
}
