package com.amazon.alexa.audiopersonalization.listeners;

import android.util.Log;
import com.amazon.alexa.audiopersonalization.api.AmaApi;
import com.amazon.alexa.audiopersonalization.api.ErrorDelegate;
import com.amazon.alexa.audiopersonalization.api.SuccessDelegate;
import com.amazon.alexa.audiopersonalization.constants.EventBusConstants;
import com.amazon.alexa.audiopersonalization.factory.JSONObjectFactory;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.annotation.OverridingMethodsMustInvokeSuper;
/* loaded from: classes6.dex */
public class AccessoriesListener extends AbstractEventListener {
    private static final String TAG = "AccessoriesListener";
    private final AmaApi mAmaApi;

    public AccessoriesListener(EventBus eventBus, AmaApi amaApi, JSONObjectFactory jSONObjectFactory) {
        super(eventBus, jSONObjectFactory);
        this.mAmaApi = amaApi;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendBudsInEarEvent(Message message) {
        String payloadAsString = message.getPayloadAsString();
        GeneratedOutlineSupport1.outline163("Received Request for buds in ear with payload ", payloadAsString, TAG);
        String str = (String) parsePayload(payloadAsString, "deviceAddress", String.class);
        if (str == null) {
            Log.e(TAG, "sendBudsInEarEvent null address");
            sendErrorMsg(EventBusConstants.EVENT_TYPE_ACCESSORY_ERROR);
            return;
        }
        this.mAmaApi.getBudsInEar(str, new SuccessDelegate() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$AccessoriesListener$iSxqhaxITH-ROAPF-sVCkZ6NygE
            @Override // com.amazon.alexa.audiopersonalization.api.SuccessDelegate
            public final void onSuccess(Object obj) {
                AccessoriesListener.this.lambda$sendBudsInEarEvent$0$AccessoriesListener((Boolean) obj);
            }
        }, new ErrorDelegate() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$AccessoriesListener$_jbRsrTWle_a_Nh7lreDvlPSlHs
            @Override // com.amazon.alexa.audiopersonalization.api.ErrorDelegate
            public final void onError() {
                AccessoriesListener.this.lambda$sendBudsInEarEvent$1$AccessoriesListener();
            }
        });
    }

    public /* synthetic */ void lambda$sendBudsInEarEvent$0$AccessoriesListener(Boolean bool) {
        String str = TAG;
        Log.i(str, "Successfully retrieved if buds are in ear. In ear: " + bool);
        String createPayload = createPayload(EventBusConstants.JSON_KEY_IN_EAR_STATUS, bool);
        if (createPayload == null) {
            sendErrorMsg(EventBusConstants.EVENT_TYPE_ACCESSORY_ERROR);
            Log.e(TAG, "sendBudsInEarEvent: payload could not be created");
            return;
        }
        GeneratedOutlineSupport1.outline163("Sending buds in ear response with payload ", createPayload, TAG);
        sendEventBusMessage(EventBusConstants.EVENT_TYPE_ACCESSORY_BUDS_IN_EAR_RESPONSE, createPayload);
    }

    public /* synthetic */ void lambda$sendBudsInEarEvent$1$AccessoriesListener() {
        Log.e(TAG, "sendBudsInEarEvent sending an error ");
        sendErrorMsg(EventBusConstants.EVENT_TYPE_ACCESSORY_ERROR);
    }

    public void start() {
        stop();
        subscribeToEvent(EventBusConstants.EVENT_TYPE_ACCESSORY_BUDS_IN_EAR_REQUEST, new MessageHandler() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$AccessoriesListener$z4UBIK6l2GJU901iKdkprf5zdFw
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AccessoriesListener.this.sendBudsInEarEvent(message);
            }
        });
    }

    @Override // com.amazon.alexa.audiopersonalization.listeners.AbstractEventListener
    @OverridingMethodsMustInvokeSuper
    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }
}
