package com.amazon.alexa.api;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.api.messages.messagesender.AlexaBidirectionalMessageSender;
import com.amazon.alexa.client.annotations.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes6.dex */
public class AlexaAudioProviderServiceMessageSender extends AlexaBidirectionalMessageSender<AlexaAudioProviderServiceMessageType> {
    private static final String TAG = "AlexaAudioProviderServiceMessageSender";
    private at<String> alexaLocaleTask;
    private at<Boolean> connectionTask;
    private at<PendingIntent> customSettingIntentTask;
    private at<String> customSettingsTitleTask;
    private at<Boolean> startDialogTask;
    private at<List<String>> supportedLocalesTask;
    private at<Integer> wakeWordConfidenceThresholdTask;
    private at<Boolean> wakeWordEnabledTask;

    /* renamed from: com.amazon.alexa.api.AlexaAudioProviderServiceMessageSender$9  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderServiceMessageType = new int[AlexaAudioProviderServiceMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderServiceMessageType[AlexaAudioProviderServiceMessageType.IS_WAKE_WORD_RECOGNITION_ENABLED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderServiceMessageType[AlexaAudioProviderServiceMessageType.GET_LOCALE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderServiceMessageType[AlexaAudioProviderServiceMessageType.GET_SUPPORTED_LOCALES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderServiceMessageType[AlexaAudioProviderServiceMessageType.CONNECT_FOR_ATTENTION_SYSTEM_UPDATES.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderServiceMessageType[AlexaAudioProviderServiceMessageType.START_DIALOG.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderServiceMessageType[AlexaAudioProviderServiceMessageType.GET_CUSTOM_SETTINGS_TITLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderServiceMessageType[AlexaAudioProviderServiceMessageType.GET_CUSTOM_SETTINGS_INTENT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderServiceMessageType[AlexaAudioProviderServiceMessageType.GET_WAKE_WORD_CONFIDENCE_THRESHOLD.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* loaded from: classes6.dex */
    public static class AlexaLocaleMessagePayload extends BaseMessagePayload {
        @Deprecated
        public AlexaLocaleMessagePayload(ExtendedClient extendedClient, String str) {
            super(ExtendedClient.overrideVersion(extendedClient, Versions.V1_0_0));
            add(o.CURRENT_LOCALE, str);
        }

        public AlexaLocaleMessagePayload(ExtendedClient extendedClient, String str, MessageReceiver<bm> messageReceiver) {
            super(extendedClient);
            add(o.CURRENT_LOCALE, str);
            add(o.RESULT_CALLBACK, messageReceiver.getMessenger().getBinder());
        }

        @Override // com.amazon.alexa.api.BaseMessagePayload
        public /* bridge */ /* synthetic */ Bundle getBundle() {
            return super.getBundle();
        }
    }

    /* loaded from: classes6.dex */
    private class ResponseProcessor extends MessageProcessor<AlexaAudioProviderServiceMessageType> {
        private ResponseProcessor() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.api.messages.MessageProcessor
        /* renamed from: getMessageType */
        public AlexaAudioProviderServiceMessageType mo845getMessageType(Message message) {
            try {
                return AlexaAudioProviderServiceMessageType.fromOrdinal(message.what);
            } catch (IllegalStateException e) {
                Log.e(AlexaAudioProviderServiceMessageSender.TAG, "Unrecognized message type, ", e);
                return AlexaAudioProviderServiceMessageType.UNKNOWN;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.amazon.alexa.api.messages.MessageProcessor
        public void processMessage(AlexaAudioProviderServiceMessageType alexaAudioProviderServiceMessageType, Bundle bundle, @Nullable Messenger messenger) {
            String str = AlexaAudioProviderServiceMessageSender.TAG;
            Log.i(str, "received response " + alexaAudioProviderServiceMessageType);
            switch (alexaAudioProviderServiceMessageType.ordinal()) {
                case 2:
                    AlexaAudioProviderServiceMessageSender.this.onWakeWordEnabled(Bundles.getBoolean(bundle, o.WAKE_WORD_ENABLED));
                    return;
                case 4:
                    AlexaAudioProviderServiceMessageSender.this.onAlexaLocale(Bundles.getString(bundle, o.CURRENT_LOCALE));
                    return;
                case 5:
                    AlexaAudioProviderServiceMessageSender.this.onSupportedLocales(Bundles.getStringList(bundle, o.SUPPORTED_LOCALES));
                    return;
                case 6:
                    AlexaAudioProviderServiceMessageSender.this.onConnection(Bundles.getBoolean(bundle, o.RESULT));
                    return;
                case 7:
                    AlexaAudioProviderServiceMessageSender.this.onStartDialog(Bundles.getBoolean(bundle, o.RESULT));
                    return;
                case 8:
                    AlexaAudioProviderServiceMessageSender.this.onCustomSettingsTitle(Bundles.getString(bundle, o.CUSTOM_SETTINGS_TITLE));
                    return;
                case 9:
                    AlexaAudioProviderServiceMessageSender.this.onCustomSettingsIntent((PendingIntent) Bundles.getParcelable(bundle, o.CUSTOM_SETTINGS_PENDING_INTENT, PendingIntent.class));
                    return;
                case 10:
                    AlexaAudioProviderServiceMessageSender.this.onWakeWordConfidenceThreshold(Bundles.getInteger(bundle, o.THRESHOLD_VALUE));
                    break;
            }
            String str2 = AlexaAudioProviderServiceMessageSender.TAG;
            Log.w(str2, "Unsupported message " + alexaAudioProviderServiceMessageType);
        }
    }

    /* loaded from: classes6.dex */
    public static class WakeWordConfidenceThresholdMessagePayload extends BaseMessagePayload {
        public WakeWordConfidenceThresholdMessagePayload(ExtendedClient extendedClient, int i, MessageReceiver<bm> messageReceiver) {
            super(extendedClient);
            add(o.THRESHOLD_VALUE, i);
            add(o.RESULT_CALLBACK, messageReceiver.getMessenger().getBinder());
        }

        @Override // com.amazon.alexa.api.BaseMessagePayload
        public /* bridge */ /* synthetic */ Bundle getBundle() {
            return super.getBundle();
        }
    }

    /* loaded from: classes6.dex */
    public static class WakeWordEnabledMessagePayload extends BaseMessagePayload {
        @Deprecated
        public WakeWordEnabledMessagePayload(ExtendedClient extendedClient, boolean z) {
            super(ExtendedClient.overrideVersion(extendedClient, Versions.V1_0_0));
            add(o.WAKE_WORD_ENABLED, z);
        }

        public WakeWordEnabledMessagePayload(ExtendedClient extendedClient, boolean z, MessageReceiver<bm> messageReceiver) {
            super(extendedClient);
            add(o.WAKE_WORD_ENABLED, z);
            add(o.RESULT_CALLBACK, messageReceiver.getMessenger().getBinder());
        }

        @Override // com.amazon.alexa.api.BaseMessagePayload
        public /* bridge */ /* synthetic */ Bundle getBundle() {
            return super.getBundle();
        }
    }

    public AlexaAudioProviderServiceMessageSender(IBinder iBinder, MessageReceiversManager messageReceiversManager) {
        super(iBinder, messageReceiversManager);
    }

    private bo createCallbacksProcessor(ResultCallbacks resultCallbacks) {
        return bo.a(resultCallbacks);
    }

    public boolean connectForAttentionSystemUpdates(ExtendedClient extendedClient) throws RemoteException {
        final BaseMessagePayload baseMessagePayload = new BaseMessagePayload(extendedClient);
        if (this.connectionTask == null) {
            this.connectionTask = new at<Boolean>(1000L, false) { // from class: com.amazon.alexa.api.AlexaAudioProviderServiceMessageSender.3
                @Override // com.amazon.alexa.api.at
                protected void execute() throws RemoteException {
                    AlexaAudioProviderServiceMessageSender.this.sendMessage(AlexaAudioProviderServiceMessageType.CONNECT_FOR_ATTENTION_SYSTEM_UPDATES, baseMessagePayload.getBundle());
                }
            };
        }
        Boolean call = this.connectionTask.call();
        if (call == null) {
            return false;
        }
        return call.booleanValue();
    }

    @Override // com.amazon.alexa.api.messages.messagesender.AlexaBidirectionalMessageSender
    protected MessageProcessor<AlexaAudioProviderServiceMessageType> createResponseProcessor() {
        return new ResponseProcessor();
    }

    protected MessageReceiver<bm> createResultCallbackMessageReceiver(ResultCallbacks resultCallbacks) {
        return getMessageReceiversManager().createMessageReceiver(createCallbacksProcessor(resultCallbacks));
    }

    public PendingIntent getCustomSettingsIntent(ExtendedClient extendedClient) throws RemoteException {
        final BaseMessagePayload baseMessagePayload = new BaseMessagePayload(extendedClient);
        if (this.customSettingIntentTask == null) {
            this.customSettingIntentTask = new at<PendingIntent>(1000L, null) { // from class: com.amazon.alexa.api.AlexaAudioProviderServiceMessageSender.7
                @Override // com.amazon.alexa.api.at
                protected void execute() throws RemoteException {
                    AlexaAudioProviderServiceMessageSender.this.sendMessage(AlexaAudioProviderServiceMessageType.GET_CUSTOM_SETTINGS_INTENT, baseMessagePayload.getBundle());
                }
            };
        }
        return this.customSettingIntentTask.call();
    }

    public String getCustomSettingsTitle(ExtendedClient extendedClient) throws RemoteException {
        final BaseMessagePayload baseMessagePayload = new BaseMessagePayload(extendedClient);
        if (this.customSettingsTitleTask == null) {
            this.customSettingsTitleTask = new at<String>(1000L, "") { // from class: com.amazon.alexa.api.AlexaAudioProviderServiceMessageSender.6
                @Override // com.amazon.alexa.api.at
                protected void execute() throws RemoteException {
                    AlexaAudioProviderServiceMessageSender.this.sendMessage(AlexaAudioProviderServiceMessageType.GET_CUSTOM_SETTINGS_TITLE, baseMessagePayload.getBundle());
                }
            };
        }
        return this.customSettingsTitleTask.call();
    }

    @Override // com.amazon.alexa.api.messages.messagesender.AlexaBidirectionalMessageSender
    protected Set<AlexaAudioProviderServiceMessageType> getExpectedMessageTypes() {
        return Collections.unmodifiableSet(EnumSet.of(AlexaAudioProviderServiceMessageType.IS_WAKE_WORD_RECOGNITION_ENABLED, AlexaAudioProviderServiceMessageType.GET_LOCALE, AlexaAudioProviderServiceMessageType.GET_SUPPORTED_LOCALES, AlexaAudioProviderServiceMessageType.CONNECT_FOR_ATTENTION_SYSTEM_UPDATES, AlexaAudioProviderServiceMessageType.START_DIALOG, AlexaAudioProviderServiceMessageType.GET_CUSTOM_SETTINGS_TITLE, AlexaAudioProviderServiceMessageType.GET_CUSTOM_SETTINGS_INTENT, AlexaAudioProviderServiceMessageType.GET_WAKE_WORD_CONFIDENCE_THRESHOLD));
    }

    public String getLocale(ExtendedClient extendedClient) throws RemoteException {
        final BaseMessagePayload baseMessagePayload = new BaseMessagePayload(extendedClient);
        if (this.alexaLocaleTask == null) {
            this.alexaLocaleTask = new at<String>(1000L, "") { // from class: com.amazon.alexa.api.AlexaAudioProviderServiceMessageSender.2
                @Override // com.amazon.alexa.api.at
                protected void execute() throws RemoteException {
                    AlexaAudioProviderServiceMessageSender.this.sendMessage(AlexaAudioProviderServiceMessageType.GET_LOCALE, baseMessagePayload.getBundle());
                }
            };
        }
        return this.alexaLocaleTask.call();
    }

    public List<String> getSupportedLocales(ExtendedClient extendedClient) throws RemoteException {
        final BaseMessagePayload baseMessagePayload = new BaseMessagePayload(extendedClient);
        if (this.supportedLocalesTask == null) {
            this.supportedLocalesTask = new at<List<String>>(1000L, Collections.emptyList()) { // from class: com.amazon.alexa.api.AlexaAudioProviderServiceMessageSender.5
                @Override // com.amazon.alexa.api.at
                protected void execute() throws RemoteException {
                    AlexaAudioProviderServiceMessageSender.this.sendMessage(AlexaAudioProviderServiceMessageType.GET_SUPPORTED_LOCALES, baseMessagePayload.getBundle());
                }
            };
        }
        return this.supportedLocalesTask.call();
    }

    public int getWakeWordConfidenceThreshold(ExtendedClient extendedClient) throws RemoteException {
        final BaseMessagePayload baseMessagePayload = new BaseMessagePayload(extendedClient);
        if (this.wakeWordConfidenceThresholdTask == null) {
            this.wakeWordConfidenceThresholdTask = new at<Integer>(1000L, 0) { // from class: com.amazon.alexa.api.AlexaAudioProviderServiceMessageSender.8
                @Override // com.amazon.alexa.api.at
                protected void execute() throws RemoteException {
                    AlexaAudioProviderServiceMessageSender.this.sendMessage(AlexaAudioProviderServiceMessageType.GET_WAKE_WORD_CONFIDENCE_THRESHOLD, baseMessagePayload.getBundle());
                }
            };
        }
        return this.wakeWordConfidenceThresholdTask.call().intValue();
    }

    public boolean isWakeWordRecognitionEnabled(ExtendedClient extendedClient) throws RemoteException {
        final BaseMessagePayload baseMessagePayload = new BaseMessagePayload(extendedClient);
        if (this.wakeWordEnabledTask == null) {
            this.wakeWordEnabledTask = new at<Boolean>(1000L, false) { // from class: com.amazon.alexa.api.AlexaAudioProviderServiceMessageSender.1
                @Override // com.amazon.alexa.api.at
                protected void execute() throws RemoteException {
                    AlexaAudioProviderServiceMessageSender.this.sendMessage(AlexaAudioProviderServiceMessageType.IS_WAKE_WORD_RECOGNITION_ENABLED, baseMessagePayload.getBundle());
                }
            };
        }
        Boolean call = this.wakeWordEnabledTask.call();
        if (call == null) {
            return false;
        }
        return call.booleanValue();
    }

    void onAlexaLocale(String str) {
        GeneratedOutlineSupport1.outline163("alexaLocale: ", str, TAG);
        at<String> atVar = this.alexaLocaleTask;
        if (atVar != null) {
            atVar.setResult(str);
        }
        this.alexaLocaleTask = null;
    }

    void onConnection(boolean z) {
        GeneratedOutlineSupport1.outline173("Connected to Attention syatem: ", z, TAG);
        at<Boolean> atVar = this.connectionTask;
        if (atVar != null) {
            atVar.setResult(Boolean.valueOf(z));
        }
        this.connectionTask = null;
    }

    void onCustomSettingsIntent(PendingIntent pendingIntent) {
        Log.i(TAG, "onCustomSettingsIntent");
        at<PendingIntent> atVar = this.customSettingIntentTask;
        if (atVar != null) {
            atVar.setResult(pendingIntent);
        }
        this.customSettingIntentTask = null;
    }

    void onCustomSettingsTitle(String str) {
        GeneratedOutlineSupport1.outline163("onCustomSettingsTitle: ", str, TAG);
        at<String> atVar = this.customSettingsTitleTask;
        if (atVar != null) {
            atVar.setResult(str);
        }
        this.customSettingsTitleTask = null;
    }

    void onStartDialog(boolean z) {
        GeneratedOutlineSupport1.outline173("onStartDialog: ", z, TAG);
        at<Boolean> atVar = this.startDialogTask;
        if (atVar != null) {
            atVar.setResult(Boolean.valueOf(z));
        }
        this.startDialogTask = null;
    }

    void onSupportedLocales(List<String> list) {
        Log.i(TAG, "onSupportedLocales");
        at<List<String>> atVar = this.supportedLocalesTask;
        if (atVar != null) {
            atVar.setResult(list);
        }
        this.supportedLocalesTask = null;
    }

    void onWakeWordConfidenceThreshold(int i) {
        String str = TAG;
        Log.i(str, "onWakeWordConfidenceThreshold: " + i);
        at<Integer> atVar = this.wakeWordConfidenceThresholdTask;
        if (atVar != null) {
            atVar.setResult(Integer.valueOf(i));
        }
        this.wakeWordConfidenceThresholdTask = null;
    }

    void onWakeWordEnabled(boolean z) {
        GeneratedOutlineSupport1.outline173("isWakeWordEnabled: ", z, TAG);
        at<Boolean> atVar = this.wakeWordEnabledTask;
        if (atVar != null) {
            atVar.setResult(Boolean.valueOf(z));
        }
        this.wakeWordEnabledTask = null;
    }

    @Deprecated
    public void setLocale(ExtendedClient extendedClient, String str) throws RemoteException {
        sendMessage(AlexaAudioProviderServiceMessageType.SET_LOCALE, new AlexaLocaleMessagePayload(extendedClient, str).getBundle());
    }

    public void setLocale(ExtendedClient extendedClient, String str, ResultCallbacks resultCallbacks) throws RemoteException {
        sendMessage(AlexaAudioProviderServiceMessageType.SET_LOCALE, new AlexaLocaleMessagePayload(extendedClient, str, createResultCallbackMessageReceiver(resultCallbacks)).getBundle());
    }

    public void setWakeWordConfidenceThreshold(ExtendedClient extendedClient, int i, ResultCallbacks resultCallbacks) throws RemoteException {
        sendMessage(AlexaAudioProviderServiceMessageType.SET_WAKE_WORD_CONFIDENCE_THRESHOLD, new WakeWordConfidenceThresholdMessagePayload(extendedClient, i, createResultCallbackMessageReceiver(resultCallbacks)).getBundle());
    }

    @Deprecated
    public void setWakeWordRecognitionEnabled(ExtendedClient extendedClient, boolean z) throws RemoteException {
        sendMessage(AlexaAudioProviderServiceMessageType.SET_WAKE_WORD_RECOGNITION_ENABLED, new WakeWordEnabledMessagePayload(extendedClient, z).getBundle());
    }

    public void setWakeWordRecognitionEnabled(ExtendedClient extendedClient, boolean z, ResultCallbacks resultCallbacks) throws RemoteException {
        sendMessage(AlexaAudioProviderServiceMessageType.SET_WAKE_WORD_RECOGNITION_ENABLED, new WakeWordEnabledMessagePayload(extendedClient, z, createResultCallbackMessageReceiver(resultCallbacks)).getBundle());
    }

    public boolean startDialog(ExtendedClient extendedClient) throws RemoteException {
        final BaseMessagePayload baseMessagePayload = new BaseMessagePayload(extendedClient);
        if (this.startDialogTask == null) {
            this.startDialogTask = new at<Boolean>(1000L, false) { // from class: com.amazon.alexa.api.AlexaAudioProviderServiceMessageSender.4
                @Override // com.amazon.alexa.api.at
                protected void execute() throws RemoteException {
                    AlexaAudioProviderServiceMessageSender.this.sendMessage(AlexaAudioProviderServiceMessageType.START_DIALOG, baseMessagePayload.getBundle());
                }
            };
        }
        Boolean call = this.startDialogTask.call();
        if (call == null) {
            return false;
        }
        return call.booleanValue();
    }
}
