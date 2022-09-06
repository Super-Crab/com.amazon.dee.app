package com.amazon.alexa.audiopersonalization.listeners;

import android.util.Log;
import com.amazon.alexa.audiopersonalization.api.AmaApi;
import com.amazon.alexa.audiopersonalization.api.ErrorMessageDelegate;
import com.amazon.alexa.audiopersonalization.api.OnStart;
import com.amazon.alexa.audiopersonalization.api.SuccessDelegate;
import com.amazon.alexa.audiopersonalization.constants.ErrorCodeConstants;
import com.amazon.alexa.audiopersonalization.constants.EventBusConstants;
import com.amazon.alexa.audiopersonalization.factory.JSONObjectFactory;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.annotation.OverridingMethodsMustInvokeSuper;
/* loaded from: classes6.dex */
public class AppStateListener extends AbstractEventListener {
    private static final String TAG = "AppStateListener";
    private final AmaApi mAmaApi;

    public AppStateListener(EventBus eventBus, AmaApi amaApi, JSONObjectFactory jSONObjectFactory) {
        super(eventBus, jSONObjectFactory);
        this.mAmaApi = amaApi;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendMpeSupport(Message message) {
        String payloadAsString = message.getPayloadAsString();
        GeneratedOutlineSupport1.outline163("Starting MPE support request. with payload ", payloadAsString, TAG);
        String str = (String) parsePayload(payloadAsString, "deviceAddress", String.class);
        if (str == null) {
            sendErrorMsg(EventBusConstants.EVENT_TYPE_APP_STATE_ERROR);
        } else {
            this.mAmaApi.getFeatureStatus(str, new SuccessDelegate() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$AppStateListener$LXgGU8TBBiIA13bgsQVfun4nD1Y
                @Override // com.amazon.alexa.audiopersonalization.api.SuccessDelegate
                public final void onSuccess(Object obj) {
                    AppStateListener.this.lambda$sendMpeSupport$1$AppStateListener((Boolean) obj);
                }
            }, new ErrorMessageDelegate() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$AppStateListener$mHHDlh0z7NQchcGLr_q953tx-Ak
                @Override // com.amazon.alexa.audiopersonalization.api.ErrorMessageDelegate
                public final void onError(Throwable th) {
                    AppStateListener.this.lambda$sendMpeSupport$2$AppStateListener(th);
                }
            });
        }
    }

    public /* synthetic */ void lambda$sendMpeSupport$1$AppStateListener(Boolean bool) {
        Log.i(TAG, "Successfully retrieved feature status. MPE is supported");
        String createPayload = createPayload(EventBusConstants.JSON_KEY_FEATURE_SUPPORTS_MPE, true);
        if (createPayload == null) {
            sendErrorMsg(EventBusConstants.EVENT_TYPE_APP_STATE_ERROR);
            Log.e(TAG, "getFeatureStatus: payload could not be created");
            return;
        }
        sendEventBusMessage(EventBusConstants.EVENT_TYPE_MPE_SUPPORT_RESULT, createPayload);
    }

    public /* synthetic */ void lambda$sendMpeSupport$2$AppStateListener(Throwable th) {
        if (th.toString().contains(ErrorCodeConstants.ERROR_CODE_UNSUPPORTED)) {
            Log.i(TAG, "Received UNSUPPORTED when retrieving MPE status. MPE is not supported.");
            String createPayload = createPayload(EventBusConstants.JSON_KEY_FEATURE_SUPPORTS_MPE, false);
            if (createPayload == null) {
                sendErrorMsg(EventBusConstants.EVENT_TYPE_APP_STATE_ERROR);
                Log.e(TAG, "getFeatureStatus: payload could not be created");
                return;
            }
            sendEventBusMessage(EventBusConstants.EVENT_TYPE_MPE_SUPPORT_RESULT, createPayload);
            return;
        }
        sendErrorMsg(EventBusConstants.EVENT_TYPE_APP_STATE_ERROR);
        String str = TAG;
        Log.e(str, "Received unknown error when querying feature status for MPE support: " + th);
    }

    public void start(final OnStart onStart) {
        stop();
        subscribeToEvent(EventBusConstants.EVENT_TYPE_APP_START, new MessageHandler() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$AppStateListener$1tUIeaml3pYVMo80Jjrtdu0G0Qs
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                OnStart.this.onStart();
            }
        });
        subscribeToEvent(EventBusConstants.EVENT_TYPE_GET_MPE_SUPPORT, new MessageHandler() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$AppStateListener$uW8yvMDrMIamEiUFgLI7SMHYCxw
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AppStateListener.this.sendMpeSupport(message);
            }
        });
    }

    @Override // com.amazon.alexa.audiopersonalization.listeners.AbstractEventListener
    @OverridingMethodsMustInvokeSuper
    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }
}
