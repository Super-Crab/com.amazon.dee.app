package com.amazon.alexa.audiopersonalization.listeners;

import android.util.Log;
import com.amazon.alexa.accessory.protocol.Hearing;
import com.amazon.alexa.audiopersonalization.api.AmaApi;
import com.amazon.alexa.audiopersonalization.api.CompletableDelegate;
import com.amazon.alexa.audiopersonalization.api.ErrorDelegate;
import com.amazon.alexa.audiopersonalization.api.ErrorMessageDelegate;
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
public class SettingsEventListener extends AbstractEventListener {
    private static final String TAG = "SettingsEventListener";
    private final AmaApi mAmaApi;

    public SettingsEventListener(EventBus eventBus, AmaApi amaApi, JSONObjectFactory jSONObjectFactory) {
        super(eventBus, jSONObjectFactory);
        this.mAmaApi = amaApi;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSetFeatureStatusReceived(Message message) {
        String payloadAsString = message.getPayloadAsString();
        GeneratedOutlineSupport1.outline163("Received enhancedMedia::settings::setFeatureStatus with payload ", payloadAsString, TAG);
        Boolean bool = (Boolean) parsePayload(payloadAsString, "status", Boolean.class);
        String str = (String) parsePayload(payloadAsString, "deviceAddress", String.class);
        if (bool != null && str != null) {
            this.mAmaApi.setFeatureStatus(bool.booleanValue(), str, new CompletableDelegate() { // from class: com.amazon.alexa.audiopersonalization.listeners.SettingsEventListener.1
                @Override // com.amazon.alexa.audiopersonalization.api.CompletableDelegate
                public void onComplete() {
                    Log.i(SettingsEventListener.TAG, "Successfully set feature status");
                }

                @Override // com.amazon.alexa.audiopersonalization.api.CompletableDelegate
                public void onError() {
                    SettingsEventListener.this.sendErrorMsg(EventBusConstants.EVENT_TYPE_SETTINGS_ERROR);
                    Log.e(SettingsEventListener.TAG, "Received error when setting feature status");
                }
            });
        } else {
            sendErrorMsg(EventBusConstants.EVENT_TYPE_SETTINGS_ERROR);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onSetPersonalizationReceived(Message message) {
        String payloadAsString = message.getPayloadAsString();
        GeneratedOutlineSupport1.outline163("Received enhancedMedia::settings::setPersonalizationLevel with message ", payloadAsString, TAG);
        Integer num = (Integer) parsePayload(payloadAsString, EventBusConstants.JSON_KEY_PERSONALIZATION_LEVEL, Integer.class);
        String str = (String) parsePayload(payloadAsString, "deviceAddress", String.class);
        if (num != null && str != null) {
            this.mAmaApi.setPersonalizationLevel(num.intValue(), str, new CompletableDelegate() { // from class: com.amazon.alexa.audiopersonalization.listeners.SettingsEventListener.2
                @Override // com.amazon.alexa.audiopersonalization.api.CompletableDelegate
                public void onComplete() {
                    Log.i(SettingsEventListener.TAG, "Successfully set personalization level.");
                }

                @Override // com.amazon.alexa.audiopersonalization.api.CompletableDelegate
                public void onError() {
                    SettingsEventListener.this.sendErrorMsg(EventBusConstants.EVENT_TYPE_SETTINGS_ERROR);
                    Log.e(SettingsEventListener.TAG, "Received error when setting personalization level");
                }
            });
        } else {
            sendErrorMsg(EventBusConstants.EVENT_TYPE_SETTINGS_ERROR);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAudioProfileStatus(Message message) {
        String payloadAsString = message.getPayloadAsString();
        GeneratedOutlineSupport1.outline163("Starting request for current audio profile with payload ", payloadAsString, TAG);
        String str = (String) parsePayload(payloadAsString, "deviceAddress", String.class);
        if (str == null) {
            GeneratedOutlineSupport1.outline163("send profile status error no address passed", payloadAsString, TAG);
            sendErrorMsg(EventBusConstants.EVENT_TYPE_SETTINGS_ERROR);
            return;
        }
        this.mAmaApi.getAudioProfile(str, new SuccessDelegate() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$SettingsEventListener$YG9zU82gfOtqCHa5pmMlvh7lVnk
            @Override // com.amazon.alexa.audiopersonalization.api.SuccessDelegate
            public final void onSuccess(Object obj) {
                SettingsEventListener.this.lambda$sendAudioProfileStatus$4$SettingsEventListener((Hearing.Audiogram) obj);
            }
        }, new ErrorMessageDelegate() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$SettingsEventListener$OzT5Md1YkuxhKGKIYyBJfY-pDvQ
            @Override // com.amazon.alexa.audiopersonalization.api.ErrorMessageDelegate
            public final void onError(Throwable th) {
                SettingsEventListener.this.lambda$sendAudioProfileStatus$5$SettingsEventListener(th);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendFeatureStatus(Message message) {
        String payloadAsString = message.getPayloadAsString();
        GeneratedOutlineSupport1.outline163("Starting feature status request. with payload ", payloadAsString, TAG);
        String str = (String) parsePayload(payloadAsString, "deviceAddress", String.class);
        if (str == null) {
            sendErrorMsg(EventBusConstants.EVENT_TYPE_SETTINGS_ERROR);
        } else {
            this.mAmaApi.getFeatureStatus(str, new SuccessDelegate() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$SettingsEventListener$qieGUSrRLkayBqhK74A_MHwWHdI
                @Override // com.amazon.alexa.audiopersonalization.api.SuccessDelegate
                public final void onSuccess(Object obj) {
                    SettingsEventListener.this.lambda$sendFeatureStatus$0$SettingsEventListener((Boolean) obj);
                }
            }, new ErrorMessageDelegate() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$SettingsEventListener$jAGXEEglEYrEJ9Kx94EcW_rhQPQ
                @Override // com.amazon.alexa.audiopersonalization.api.ErrorMessageDelegate
                public final void onError(Throwable th) {
                    SettingsEventListener.this.lambda$sendFeatureStatus$1$SettingsEventListener(th);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendPersonalizationLevel(Message message) {
        String payloadAsString = message.getPayloadAsString();
        Log.i(TAG, "Starting personalization level request.");
        String str = (String) parsePayload(payloadAsString, "deviceAddress", String.class);
        if (str == null) {
            sendErrorMsg(EventBusConstants.EVENT_TYPE_SETTINGS_ERROR);
        } else {
            this.mAmaApi.getPersonalizationLevel(str, new SuccessDelegate() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$SettingsEventListener$bKVKpoFZ3l5IO7IvQ83tCH6gHRI
                @Override // com.amazon.alexa.audiopersonalization.api.SuccessDelegate
                public final void onSuccess(Object obj) {
                    SettingsEventListener.this.lambda$sendPersonalizationLevel$2$SettingsEventListener((Integer) obj);
                }
            }, new ErrorDelegate() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$SettingsEventListener$IvZtw79YPdKimwTO634cK9smAg8
                @Override // com.amazon.alexa.audiopersonalization.api.ErrorDelegate
                public final void onError() {
                    SettingsEventListener.this.lambda$sendPersonalizationLevel$3$SettingsEventListener();
                }
            });
        }
    }

    public /* synthetic */ void lambda$sendAudioProfileStatus$4$SettingsEventListener(Hearing.Audiogram audiogram) {
        Log.i(TAG, "Successfully retrieved assessment profile status");
        String createPayload = createPayload(EventBusConstants.JSON_KEY_AUDIO_PROFILE_STATUS, true);
        if (createPayload != null && audiogram != null) {
            Log.i(TAG, "Sending audio profile exists");
            sendEventBusMessage(EventBusConstants.EVENT_TYPE_AUDIO_PROFILE_RESULT, createPayload);
            return;
        }
        Log.i(TAG, "eventPayload is null or null profile status");
        sendErrorMsg(EventBusConstants.EVENT_TYPE_SETTINGS_ERROR);
    }

    public /* synthetic */ void lambda$sendAudioProfileStatus$5$SettingsEventListener(Throwable th) {
        if (th.toString().contains(ErrorCodeConstants.ERROR_CODE_NO_AUDIO_PROFILE)) {
            String createPayload = createPayload(EventBusConstants.JSON_KEY_AUDIO_PROFILE_STATUS, false);
            if (createPayload == null) {
                sendErrorMsg(EventBusConstants.EVENT_TYPE_SETTINGS_ERROR);
                return;
            }
            Log.i(TAG, "Sending audio profile does not exists");
            sendEventBusMessage(EventBusConstants.EVENT_TYPE_AUDIO_PROFILE_RESULT, createPayload);
            return;
        }
        Log.e(TAG, "Error when getting audio profile status");
        sendErrorMsg(EventBusConstants.EVENT_TYPE_SETTINGS_ERROR);
    }

    public /* synthetic */ void lambda$sendFeatureStatus$0$SettingsEventListener(Boolean bool) {
        String str = TAG;
        Log.i(str, "Successfully retrieved feature status. Value: " + bool);
        String createPayload = createPayload("status", bool);
        if (createPayload == null) {
            sendErrorMsg(EventBusConstants.EVENT_TYPE_SETTINGS_ERROR);
            Log.e(TAG, "getFeatureStatus: payload could not be created");
            return;
        }
        sendEventBusMessage(EventBusConstants.EVENT_TYPE_FEATURE_STATUS_RESULT, createPayload);
    }

    public /* synthetic */ void lambda$sendFeatureStatus$1$SettingsEventListener(Throwable th) {
        sendErrorMsg(EventBusConstants.EVENT_TYPE_SETTINGS_ERROR);
        Log.e(TAG, "Received error when querying feature status");
    }

    public /* synthetic */ void lambda$sendPersonalizationLevel$2$SettingsEventListener(Integer num) {
        String str = TAG;
        Log.i(str, "Successfully retrieved personalization level. Value: " + num);
        String createPayload = createPayload(EventBusConstants.JSON_KEY_PERSONALIZATION_LEVEL, num);
        if (createPayload == null) {
            sendErrorMsg(EventBusConstants.EVENT_TYPE_SETTINGS_ERROR);
            Log.e(TAG, "getPersonalizationLevel: payload could not be created");
            return;
        }
        sendEventBusMessage(EventBusConstants.EVENT_TYPE_PERSONALIZATION_LEVEL_RESULT, createPayload);
    }

    public /* synthetic */ void lambda$sendPersonalizationLevel$3$SettingsEventListener() {
        sendErrorMsg(EventBusConstants.EVENT_TYPE_SETTINGS_ERROR);
        Log.e(TAG, "Received error when querying personalization level");
    }

    public void start() {
        stop();
        subscribeToEvent(EventBusConstants.EVENT_TYPE_SET_FEATURE_STATUS, new MessageHandler() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$SettingsEventListener$1qGQAD1u7cYGu8OeadtqDYzRBx8
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                SettingsEventListener.this.onSetFeatureStatusReceived(message);
            }
        });
        subscribeToEvent(EventBusConstants.EVENT_TYPE_GET_FEATURE_STATUS, new MessageHandler() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$SettingsEventListener$xepXCmC0-eBNsQmdqDkRdx4_fw8
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                SettingsEventListener.this.sendFeatureStatus(message);
            }
        });
        subscribeToEvent(EventBusConstants.EVENT_TYPE_SET_PERSONALIZATION_LEVEL, new MessageHandler() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$SettingsEventListener$8v7sWLjGIo_ZT-7Lba3luZLejlg
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                SettingsEventListener.this.onSetPersonalizationReceived(message);
            }
        });
        subscribeToEvent(EventBusConstants.EVENT_TYPE_GET_PERSONALIZATION_LEVEL, new MessageHandler() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$SettingsEventListener$HNm9aixqrAFojdDyAKBVP-O8k6k
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                SettingsEventListener.this.sendPersonalizationLevel(message);
            }
        });
        subscribeToEvent(EventBusConstants.EVENT_TYPE_GET_AUDIO_PROFILE, new MessageHandler() { // from class: com.amazon.alexa.audiopersonalization.listeners.-$$Lambda$SettingsEventListener$f4__bHsC9JSWpPgORlGKvJemFOU
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                SettingsEventListener.this.sendAudioProfileStatus(message);
            }
        });
    }

    @Override // com.amazon.alexa.audiopersonalization.listeners.AbstractEventListener
    @OverridingMethodsMustInvokeSuper
    public /* bridge */ /* synthetic */ void stop() {
        super.stop();
    }
}
