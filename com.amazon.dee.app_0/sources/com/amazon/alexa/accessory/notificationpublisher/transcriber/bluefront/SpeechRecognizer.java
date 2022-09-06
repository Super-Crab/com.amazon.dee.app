package com.amazon.alexa.accessory.notificationpublisher.transcriber.bluefront;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.notificationpublisher.R;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.bluefront.api.common.Arrf;
import com.amazon.bluefront.api.common.TranslateToObfuscatedCustomerIdFrom;
import com.amazon.blueshift.bluefront.android.SpeechClient;
import com.amazon.blueshift.bluefront.android.SpeechRequestListener;
import com.amazon.blueshift.bluefront.android.audio.AudioRecordProvider;
import com.amazon.blueshift.bluefront.android.audio.AudioRecordWrapper;
import com.amazon.blueshift.bluefront.android.audio.AudioRecorder;
import com.amazon.blueshift.bluefront.android.audio.AudioTimeouts;
import com.amazon.blueshift.bluefront.android.common.AndroidDevice;
import com.amazon.blueshift.bluefront.android.metrics.SpeechMetricsConfiguration;
import com.amazon.blueshift.bluefront.android.request.SpeechRequest;
import com.amazon.blueshift.bluefront.android.vad.config.DnnVADConfig;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
/* loaded from: classes.dex */
public final class SpeechRecognizer {
    public static final String TAG = "SpeechRecognizer";
    private static final String ZIMM_BLUEFRONT_PROFILE = "keyboard";
    private AudioTimeouts audioTimeouts;
    private Context context;
    private String customerId;
    private String deviceId;
    private String hostname;
    private SpeechRequestListener<Arrf> listener;
    private Map<String, Object> metadata;
    private DnnVADConfig vadConfig;

    private SpeechRecognizer() {
        SpeechClient.configure(SpeechMetricsConfiguration.CONFIGURE_METRICS_INTERNALLY);
    }

    public static SpeechRecognizer createSpeechRecognizer(Context context) {
        SpeechRecognizer speechRecognizer = new SpeechRecognizer();
        speechRecognizer.context = context;
        speechRecognizer.customerId = speechRecognizer.getCustomerId(context);
        speechRecognizer.deviceId = speechRecognizer.getDeviceId(context);
        speechRecognizer.hostname = context.getResources().getString(R.string.bluefront_endpoint);
        speechRecognizer.vadConfig = new DnnVADConfig(Float.valueOf(1.6f), 1, 220);
        speechRecognizer.audioTimeouts = new AudioTimeouts(SpeechClientConfiguration.NO_SPEECH_TIMEOUT_IN_MILLISECONDS, SpeechClientConfiguration.MAXIMUM_SPEECH_TIMEOUT_IN_MILLISECONDS);
        speechRecognizer.metadata = new HashMap();
        speechRecognizer.metadata.put("isLiveTraffic", false);
        return speechRecognizer;
    }

    private String getCustomerId(Context context) {
        String string = context.getResources().getString(R.string.customerId);
        try {
            String id = DependencyProvider.getIdentityService().getUser(TAG).getId();
            return !Strings.isNullOrEmpty(id) ? id : string;
        } catch (Exception unused) {
            Log.w(TAG, "getCustomerId - Exception getting customer ID");
            return string;
        }
    }

    private String getDeviceId(Context context) {
        String string = context.getResources().getString(R.string.deviceId);
        try {
            String deviceType = DependencyProvider.getDeviceType();
            return !Strings.isNullOrEmpty(deviceType) ? deviceType : string;
        } catch (Exception e) {
            Log.w(TAG, "getDeviceId - Exception getting device type - ", e);
            return string;
        }
    }

    private void makeSpeechRequest(String str) throws Exception {
        Log.i(TAG, "makeSpeechRequest");
        SpeechRequest build = SpeechRequest.speechToTextRequestBuilder().hostname(this.hostname).customer(this.customerId, TranslateToObfuscatedCustomerIdFrom.NO_TRANSLATION).device(new AndroidDevice.Builder(this.context).deviceId(this.deviceId).build()).locale(Locale.US).requestId(str).metadataMap(this.metadata).profile(ZIMM_BLUEFRONT_PROFILE).mapAccessToken(DependencyProvider.getAccessToken(TAG)).build();
        try {
            AudioRecordWrapper audioRecordWrapper = getAudioRecordWrapper();
            String str2 = TAG;
            Log.i(str2, "makeSpeechRequest - audioRecordWrapper state is - " + audioRecordWrapper.getState());
            AudioRecorder build2 = new AudioRecorder.Builder(this.context).dnnVADConfig(this.vadConfig).audioRecord(audioRecordWrapper).audioTimeouts(this.audioTimeouts).build();
            if (build2 != null) {
                SpeechClient speechClient = new SpeechClient(build, build2);
                speechClient.setSpeechRequestListener(this.listener);
                SpeechClientManager.startRequest(speechClient);
                return;
            }
            Log.e(TAG, "makeSpeechRequest - Recorder is null");
            throw new Exception("Recorder is null");
        } catch (Exception e) {
            String str3 = TAG;
            Log.e(str3, "makeSpeechRequest - Failed to create audio recorder - " + e);
            throw e;
        }
    }

    @VisibleForTesting
    AudioRecordWrapper getAudioRecordWrapper() throws Exception {
        AudioRecordWrapper audioRecord = AudioRecordProvider.INSTANCE.getAudioRecord();
        if (audioRecord != null) {
            if (audioRecord.getState() == 1) {
                return audioRecord;
            }
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("getAudioRecordWrapper - audioRecordWrapper state is not initialized - ");
            outline107.append(audioRecord.getState());
            Log.e(str, outline107.toString());
            throw new Exception("Audio record is not initialized");
        }
        Log.e(TAG, "getAudioRecordWrapper - audioRecordWrapper is null");
        throw new Exception("Audio record wrapper is null");
    }

    public void setRecognitionListener(SpeechRequestListener<Arrf> speechRequestListener) {
        this.listener = speechRequestListener;
    }

    public String startListening() throws Exception {
        Log.i(TAG, "startListening");
        String uuid = UUID.randomUUID().toString();
        makeSpeechRequest(uuid);
        return uuid;
    }

    public void stopListening() {
        Log.i(TAG, "stopListening");
        SpeechClientManager.stopRequest();
    }
}
