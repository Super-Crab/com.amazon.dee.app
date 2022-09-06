package com.amazon.alexa.accessory.frames.service;

import android.os.Handler;
import android.os.HandlerThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.AlexaService;
import com.amazon.alexa.accessory.frames.metrics.MetricsRecorder;
import com.amazon.alexa.accessory.frames.topContact.TopContactManager;
import com.amazon.alexa.accessory.frames.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsConstants;
import com.amazon.alexa.accessory.notificationpublisher.providers.DependencyProvider;
import com.amazon.alexa.accessory.notificationpublisher.servicerequest.CreateDownloadableSpeechFromTextRequestBody;
import com.amazon.alexa.accessory.notificationpublisher.servicerequest.CreateDownloadableSpeechFromTextRequestSender;
import com.amazon.alexa.accessory.notificationpublisher.servicerequest.CreateDownloadableSpeechFromTextResponseHandler;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class TtsManager implements CreateDownloadableSpeechFromTextResponseHandler {
    private static final String TAG = "TtsManager";
    public static Handler handler;
    private static TtsManager ttsManager;
    private Map<String, JSONObject> incomingTtsMap = new ConcurrentHashMap();

    private TtsManager() {
        initTtsThread();
    }

    public static synchronized TtsManager getInstance() {
        TtsManager ttsManager2;
        synchronized (TtsManager.class) {
            if (ttsManager == null) {
                ttsManager = new TtsManager();
            }
            ttsManager2 = ttsManager;
        }
        return ttsManager2;
    }

    public static void initTtsThread() {
        HandlerThread handlerThread = new HandlerThread("TtsRequestThread", 10);
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
    }

    public boolean addIncomingTtsRequest(@NonNull JSONObject jSONObject) {
        try {
            String str = NotificationConstants.TTS_REQUEST_ID_PREFIX + UUID.randomUUID().toString();
            jSONObject.put("requestId", str);
            this.incomingTtsMap.put(str, jSONObject);
            return true;
        } catch (JSONException e) {
            TopContactManager.getInstance().topContactFlowEnded();
            Log.w(TAG, "addIncomingTtsRequest - Exception adding request to map ", e);
            return false;
        }
    }

    public void createDownloadableSpeechFromText(@NonNull JSONObject jSONObject) throws Exception {
        String optString = jSONObject.optString("requestId");
        String str = TAG;
        Log.i(str, "createDownloadableSpeechFromText requestId:" + optString);
        HashMap outline133 = GeneratedOutlineSupport1.outline133(MetricsConstants.CUSTOM_VALUES_KEY, jSONObject.optString(NotificationConstants.REQUEST_TYPE, "None"));
        try {
            AlexaService.wakeUp(DependencyProvider.getContext());
            CreateDownloadableSpeechFromTextRequestBody build = new CreateDownloadableSpeechFromTextRequestBody.Builder().setPrimaryText(jSONObject.getString(NotificationConstants.PRIMARY_TEXT)).setRequestId(optString).setDeviceType(DependencyProvider.getDeviceType()).setDeviceSerialNumber(DependencyProvider.getSerialNumber()).build();
            String str2 = TAG;
            Log.d(str2, "createDownloadableSpeechFromText - Body payload " + build.toString());
            CreateDownloadableSpeechFromTextRequestSender.createDownloadableSpeechFromTextRequest(build, this, outline133);
        } catch (Exception e) {
            TopContactManager.getInstance().topContactFlowEnded();
            Log.e(TAG, "Failed to createDownloadableSpeechFromText.", e);
            removeIncomingTtsRequest(optString);
            throw e;
        }
    }

    public JSONObject createTtsRequest(String str, String str2, String str3) throws JSONException {
        JSONObject put = new JSONObject().put(NotificationConstants.PRIMARY_TEXT, str).put("locale", NotificationConstants.DEFAULT_LOCALE).put(NotificationConstants.PRIMARY_TEXT_TYPE, str2).put(NotificationConstants.REQUEST_TYPE, str3);
        String str4 = TAG;
        Log.d(str4, "createTtsRequest - requestId: " + put);
        return put;
    }

    @Nullable
    public JSONObject getAndRemoveIncomingTts(@NonNull String str) {
        String str2 = TAG;
        Log.i(str2, "getAndRemoveIncomingTts for RequestId - " + str);
        JSONObject jSONObject = this.incomingTtsMap.get(str);
        removeIncomingTtsRequest(str);
        return jSONObject;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.servicerequest.CreateDownloadableSpeechFromTextResponseHandler
    public void handleDownloadableSpeechFromTextResponse(boolean z, CreateDownloadableSpeechFromTextRequestBody createDownloadableSpeechFromTextRequestBody, Map<String, Object> map) {
        String requestId = createDownloadableSpeechFromTextRequestBody.getRequestId();
        if (!z) {
            String str = TAG;
            Log.d(str, "handleDownloadableSpeechFromTextResponse request failed " + requestId);
            if (TopContactManager.ttsState == TopContactManager.TTSState.REQUESTED) {
                MetricsRecorder.getInstance().recordCounter(com.amazon.alexa.accessory.frames.metrics.MetricsConstants.TOP_CONTACT_TTS_REQUEST_FAILED);
                TopContactManager.getInstance().setTTSState(TopContactManager.TTSState.ERROR);
                TopContactManager.getInstance().requestAudioFocus();
            }
            removeIncomingTtsRequest(requestId);
            return;
        }
        String str2 = TAG;
        Log.i(str2, "handleDownloadableSpeechFromTextResponse request succeed " + requestId);
        MetricsRecorder.getInstance().recordCounter(com.amazon.alexa.accessory.frames.metrics.MetricsConstants.TOP_CONTACT_TTS_REQUEST_SUCCEEDED);
    }

    public boolean isRequestInIncomingTtsMap(@NonNull String str) {
        return !Strings.isNullOrEmpty(str) && this.incomingTtsMap.containsKey(str);
    }

    public void removeIncomingTtsRequest(@NonNull String str) {
        if (isRequestInIncomingTtsMap(str)) {
            String str2 = TAG;
            Log.i(str2, "removeIncomingTtsRequest - Removing requestId - " + str);
            this.incomingTtsMap.remove(str);
        }
    }
}
