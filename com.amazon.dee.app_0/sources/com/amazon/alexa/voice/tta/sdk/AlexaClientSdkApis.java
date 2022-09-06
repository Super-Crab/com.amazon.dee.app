package com.amazon.alexa.voice.tta.sdk;

import android.util.Log;
import com.amazon.alexa.api.AlexaApiCallbacks;
import com.amazon.alexa.api.AlexaCardListener;
import com.amazon.alexa.api.AlexaLocalesListener;
import com.amazon.alexa.api.AlexaPlayerInfoCardListener;
import com.amazon.alexa.api.AlexaVisualTask;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.api.compat.AlexaExpectTextListener;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import com.amazon.alexa.api.compat.AlexaStateListener;
import com.amazon.alexa.api.compat.AlexaTextResponseListener;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
/* loaded from: classes11.dex */
public class AlexaClientSdkApis {
    private static final String TAG = "AlexaClientSdkApis";
    private final AlexaMobileFrameworkApis alexaMobileFrameworkApis;
    private final Gson gson;
    private final AlexaApiCallbacks sendTextAPICallback = new AlexaApiCallbacks() { // from class: com.amazon.alexa.voice.tta.sdk.AlexaClientSdkApis.1
        @Override // com.amazon.alexa.api.AlexaApiCallbacks
        public void onFailure(ApiCallFailure apiCallFailure) {
            super.onFailure(apiCallFailure);
            String str = AlexaClientSdkApis.TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("sendText failed! reason: ");
            outline107.append(apiCallFailure.getMessage());
            outline107.append(", type: ");
            outline107.append(apiCallFailure.getFailureType());
            Log.e(str, outline107.toString(), apiCallFailure.getException());
        }

        @Override // com.amazon.alexa.api.AlexaApiCallbacks
        public void onSuccess() {
            super.onSuccess();
            String unused = AlexaClientSdkApis.TAG;
        }
    };

    public AlexaClientSdkApis(AlexaMobileFrameworkApis alexaMobileFrameworkApis, Gson gson) {
        this.alexaMobileFrameworkApis = alexaMobileFrameworkApis;
        this.gson = gson;
    }

    public void deregisterCardListener(AlexaCardListener alexaCardListener) {
        this.alexaMobileFrameworkApis.getCards().deregisterCardRendererListener(alexaCardListener);
    }

    public void deregisterExpectTextListener(AlexaExpectTextListener alexaExpectTextListener) {
        this.alexaMobileFrameworkApis.getText().deregisterExpectTextListener(alexaExpectTextListener);
    }

    public void deregisterLocalesListener(AlexaLocalesListener alexaLocalesListener) {
        this.alexaMobileFrameworkApis.getLocale().deregisterLocalesListener(alexaLocalesListener);
    }

    public void deregisterPlayerInfoCardListener(AlexaPlayerInfoCardListener alexaPlayerInfoCardListener) {
        this.alexaMobileFrameworkApis.getCards().deregisterPlayerInfoCardListener(alexaPlayerInfoCardListener);
    }

    public void deregisterStateListener(AlexaStateListener alexaStateListener) {
        this.alexaMobileFrameworkApis.getAttentionSystem().deregisterStateListener(alexaStateListener);
    }

    public void deregisterTextResponseListener(AlexaTextResponseListener alexaTextResponseListener) {
        this.alexaMobileFrameworkApis.getText().deregisterTextResponseListener(alexaTextResponseListener);
    }

    public void destroy() {
        this.alexaMobileFrameworkApis.destroy();
    }

    public void registerCardListener(AlexaCardListener alexaCardListener) {
        this.alexaMobileFrameworkApis.getCards().registerCardRendererListener(alexaCardListener);
    }

    public void registerExpectTextListener(AlexaExpectTextListener alexaExpectTextListener) {
        this.alexaMobileFrameworkApis.getText().registerExpectTextListener(alexaExpectTextListener);
    }

    public void registerLocalesListener(AlexaLocalesListener alexaLocalesListener) {
        this.alexaMobileFrameworkApis.getLocale().registerLocalesListener(alexaLocalesListener);
    }

    public void registerPlayerInfoCardListener(AlexaPlayerInfoCardListener alexaPlayerInfoCardListener) {
        this.alexaMobileFrameworkApis.getCards().registerPlayerInfoCardListener(alexaPlayerInfoCardListener);
    }

    public void registerStateListener(AlexaStateListener alexaStateListener) {
        this.alexaMobileFrameworkApis.getAttentionSystem().registerStateListener(alexaStateListener);
    }

    public void registerTextResponseListener(AlexaTextResponseListener alexaTextResponseListener) {
        this.alexaMobileFrameworkApis.getText().registerTextResponseListener(alexaTextResponseListener);
    }

    public void scheduleVisualTask(AlexaVisualTask alexaVisualTask) {
        this.alexaMobileFrameworkApis.getVisualTask().schedule(alexaVisualTask);
    }

    public void sendText(String str) {
        this.alexaMobileFrameworkApis.getText().sendMessage(str);
    }

    public void start() {
        this.alexaMobileFrameworkApis.start();
    }

    public void stop() {
        this.alexaMobileFrameworkApis.stop();
    }

    public void unscheduleVisualTask(AlexaVisualTask alexaVisualTask) {
        this.alexaMobileFrameworkApis.getVisualTask().unschedule(alexaVisualTask);
    }
}
