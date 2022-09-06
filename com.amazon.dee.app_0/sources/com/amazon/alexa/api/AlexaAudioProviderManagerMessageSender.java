package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.api.messages.messagesender.AlexaBidirectionalMessageSender;
import com.amazon.alexa.client.annotations.Nullable;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class AlexaAudioProviderManagerMessageSender extends AlexaBidirectionalMessageSender<AlexaAudioProviderManagerMessageType> {
    private static final String TAG = "AlexaAudioProviderManagerMessageSender";
    private static final long WAIT_TIMEOUT_MS = 1000;
    private at<java.util.Locale> getLocaleResult;
    private at<Bundle> getPreferencesResult;
    private at<Boolean> isUserLoggedInResult;
    private at<Boolean> setUpdatePreferencesResult;

    /* renamed from: com.amazon.alexa.api.AlexaAudioProviderManagerMessageSender$5  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType = new int[AlexaAudioProviderManagerMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.IS_USER_LOGGED_IN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.UPDATE_PREFERENCES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.GET_PREFERENCES.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.GET_LOCALE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes6.dex */
    private static class AlexaStateListenerMessagePayload extends BaseMessagePayload {
        AlexaStateListenerMessagePayload(ExtendedClient extendedClient, AlexaStateListenerProxy alexaStateListenerProxy) {
            super(extendedClient);
            add(AudioProviderManagerArgumentKey.ALEXA_STATE_LISTENER_PROXY, alexaStateListenerProxy.asBinder());
        }
    }

    /* loaded from: classes6.dex */
    private static class DialogControllerMessagePayload extends BaseMessagePayload {
        @Deprecated
        DialogControllerMessagePayload(ExtendedClient extendedClient, AlexaDialogControllerProxy alexaDialogControllerProxy) {
            super(ExtendedClient.overrideVersion(extendedClient, Versions.V1_0_0));
            add(AudioProviderManagerArgumentKey.ALEXA_DIALOG_CONTROLLER_PROXY, alexaDialogControllerProxy.asBinder());
        }

        DialogControllerMessagePayload(ExtendedClient extendedClient, AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2) {
            super(extendedClient);
            add(AudioProviderManagerArgumentKey.ALEXA_DIALOG_CONTROLLER_PROXY, alexaDialogControllerProxyV2.asBinder());
        }
    }

    /* loaded from: classes6.dex */
    private static class ForceDisconnectListenerMessagePayload extends BaseMessagePayload {
        ForceDisconnectListenerMessagePayload(ExtendedClient extendedClient, MessageReceiver<as> messageReceiver) {
            super(extendedClient);
            add(AudioProviderManagerArgumentKey.FORCE_DISCONNECT_LISTENER, messageReceiver.getMessenger().getBinder());
        }
    }

    /* loaded from: classes6.dex */
    private class ResponseProcessor extends MessageProcessor<AlexaAudioProviderManagerMessageType> {
        private ResponseProcessor() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.api.messages.MessageProcessor
        /* renamed from: getMessageType */
        public AlexaAudioProviderManagerMessageType mo845getMessageType(Message message) {
            try {
                return AlexaAudioProviderManagerMessageType.fromOrdinal(message.what);
            } catch (IllegalArgumentException e) {
                Log.e(AlexaAudioProviderManagerMessageSender.TAG, "Unrecognized message type, ", e);
                return AlexaAudioProviderManagerMessageType.UNKNOWN;
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // com.amazon.alexa.api.messages.MessageProcessor
        public void processMessage(AlexaAudioProviderManagerMessageType alexaAudioProviderManagerMessageType, Bundle bundle, @Nullable Messenger messenger) {
            String str = AlexaAudioProviderManagerMessageSender.TAG;
            Log.i(str, "received response " + alexaAudioProviderManagerMessageType);
            switch (alexaAudioProviderManagerMessageType.ordinal()) {
                case 10:
                    AlexaAudioProviderManagerMessageSender.this.onIsUserLoggedInResponse(Bundles.getBoolean(bundle, AudioProviderManagerArgumentKey.USER_LOGGED_IN));
                    return;
                case 12:
                    AlexaAudioProviderManagerMessageSender.this.onSetPreferencesResponse(Bundles.getBoolean(bundle, AudioProviderManagerArgumentKey.SET_PREFERENCES_UPDATE));
                    return;
                case 13:
                    AlexaAudioProviderManagerMessageSender.this.onGetPreferencesResponse(Bundles.getBundle(bundle, AudioProviderManagerArgumentKey.PREFERENCES_BUNDLE));
                    return;
                case 14:
                    String optionalString = Bundles.getOptionalString(bundle, AudioProviderManagerArgumentKey.LOCALE);
                    if (optionalString == null) {
                        AlexaAudioProviderManagerMessageSender.this.onGetLocaleResponse(null);
                        break;
                    } else {
                        AlexaAudioProviderManagerMessageSender.this.onGetLocaleResponse(java.util.Locale.forLanguageTag(optionalString));
                        break;
                    }
            }
            String str2 = AlexaAudioProviderManagerMessageSender.TAG;
            Log.w(str2, "Unsupported message " + alexaAudioProviderManagerMessageType);
        }
    }

    /* loaded from: classes6.dex */
    private static class SettingsListenerMessagePayload extends BaseMessagePayload {
        SettingsListenerMessagePayload(ExtendedClient extendedClient, AlexaSettingsListenerProxy alexaSettingsListenerProxy) {
            super(extendedClient);
            add(AudioProviderManagerArgumentKey.ALEXA_SETTINGS_LISTENER_PROXY, alexaSettingsListenerProxy.asBinder());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class StartTurnDialogControllerMessagePayload extends DialogControllerMessagePayload {
        @Deprecated
        StartTurnDialogControllerMessagePayload(ExtendedClient extendedClient, AlexaDialogControllerProxy alexaDialogControllerProxy, AlexaAudioMetadata alexaAudioMetadata, ParcelFileDescriptor parcelFileDescriptor) {
            this(extendedClient, alexaDialogControllerProxy, alexaAudioMetadata, parcelFileDescriptor, (Bundle) null);
        }

        @Deprecated
        StartTurnDialogControllerMessagePayload(ExtendedClient extendedClient, AlexaDialogControllerProxy alexaDialogControllerProxy, AlexaAudioMetadata alexaAudioMetadata, ParcelFileDescriptor parcelFileDescriptor, @Nullable Bundle bundle) {
            super(extendedClient, alexaDialogControllerProxy);
            add(AudioProviderManagerArgumentKey.ALEXA_AUDIO_METADATA, alexaAudioMetadata);
            add(AudioProviderManagerArgumentKey.AUDIO_FILE_DESCRIPTOR, parcelFileDescriptor);
            if (bundle != null) {
                add((Bundles.Key) AudioProviderManagerArgumentKey.DIALOG_EXTRAS, bundle);
            }
        }

        StartTurnDialogControllerMessagePayload(ExtendedClient extendedClient, AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2, AlexaAudioMetadata alexaAudioMetadata, ParcelFileDescriptor parcelFileDescriptor, @Nullable Bundle bundle) {
            super(extendedClient, alexaDialogControllerProxyV2);
            add(AudioProviderManagerArgumentKey.ALEXA_AUDIO_METADATA, alexaAudioMetadata);
            add(AudioProviderManagerArgumentKey.AUDIO_FILE_DESCRIPTOR, parcelFileDescriptor);
            if (bundle != null) {
                add((Bundles.Key) AudioProviderManagerArgumentKey.DIALOG_EXTRAS, bundle);
            }
        }

        StartTurnDialogControllerMessagePayload(ExtendedClient extendedClient, AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2, AlexaAudioMetadata alexaAudioMetadata, ParcelFileDescriptor parcelFileDescriptor, @Nullable ParcelFileDescriptor parcelFileDescriptor2, @Nullable Bundle bundle) {
            this(extendedClient, alexaDialogControllerProxyV2, alexaAudioMetadata, parcelFileDescriptor, bundle);
            if (parcelFileDescriptor2 != null) {
                add(AudioProviderManagerArgumentKey.METADATA_FILE_DESCRIPTOR, parcelFileDescriptor2);
            }
        }
    }

    /* loaded from: classes6.dex */
    private static class UpdatePreferencesMessagePayload extends BaseMessagePayload {
        UpdatePreferencesMessagePayload(ExtendedClient extendedClient, Bundle bundle) {
            super(extendedClient);
            add((Bundles.Key) AudioProviderManagerArgumentKey.PREFERENCES_BUNDLE, bundle);
        }
    }

    public AlexaAudioProviderManagerMessageSender(IBinder iBinder, MessageReceiversManager messageReceiversManager) {
        super(iBinder, messageReceiversManager);
    }

    @Deprecated
    public void continueDialog(ExtendedClient extendedClient, AlexaDialogControllerProxy alexaDialogControllerProxy, AlexaAudioMetadata alexaAudioMetadata, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
        sendMessage(AlexaAudioProviderManagerMessageType.CONTINUE_DIALOG, new StartTurnDialogControllerMessagePayload(extendedClient, alexaDialogControllerProxy, alexaAudioMetadata, parcelFileDescriptor).getBundle());
    }

    public void continueDialog(ExtendedClient extendedClient, AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2, AlexaAudioMetadata alexaAudioMetadata, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
        sendMessage(AlexaAudioProviderManagerMessageType.CONTINUE_DIALOG, new StartTurnDialogControllerMessagePayload(extendedClient, alexaDialogControllerProxyV2, alexaAudioMetadata, parcelFileDescriptor, (Bundle) null).getBundle());
    }

    @Override // com.amazon.alexa.api.messages.messagesender.AlexaBidirectionalMessageSender
    protected MessageProcessor<AlexaAudioProviderManagerMessageType> createResponseProcessor() {
        return new ResponseProcessor();
    }

    public void deregisterAlexaSettingsListener(ExtendedClient extendedClient, AlexaSettingsListenerProxy alexaSettingsListenerProxy) throws RemoteException {
        sendMessage(AlexaAudioProviderManagerMessageType.DEREGISTER_SETTINGS_LISTENER, new SettingsListenerMessagePayload(extendedClient, alexaSettingsListenerProxy).getBundle());
    }

    public void deregisterAlexaStateListener(ExtendedClient extendedClient, AlexaStateListenerProxy alexaStateListenerProxy) throws RemoteException {
        sendMessage(AlexaAudioProviderManagerMessageType.DEREGISTER_ALEXA_STATE_LISTENER, new AlexaStateListenerMessagePayload(extendedClient, alexaStateListenerProxy).getBundle());
    }

    public void deregisterForceDisconnectListener(ExtendedClient extendedClient, MessageReceiver<as> messageReceiver) throws RemoteException {
        sendMessage(AlexaAudioProviderManagerMessageType.DEREGISTER_FORCE_DISCONNECT_LISTENER, new ForceDisconnectListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    @Override // com.amazon.alexa.api.messages.messagesender.AlexaBidirectionalMessageSender
    protected Set<AlexaAudioProviderManagerMessageType> getExpectedMessageTypes() {
        return Collections.unmodifiableSet(EnumSet.of(AlexaAudioProviderManagerMessageType.IS_USER_LOGGED_IN, AlexaAudioProviderManagerMessageType.UPDATE_PREFERENCES, AlexaAudioProviderManagerMessageType.GET_PREFERENCES, AlexaAudioProviderManagerMessageType.GET_LOCALE));
    }

    @Nullable
    public java.util.Locale getLocale(ExtendedClient extendedClient) throws RemoteException {
        final BaseMessagePayload baseMessagePayload = new BaseMessagePayload(extendedClient);
        if (this.getLocaleResult == null) {
            this.getLocaleResult = new at<java.util.Locale>(10000L, null) { // from class: com.amazon.alexa.api.AlexaAudioProviderManagerMessageSender.4
                @Override // com.amazon.alexa.api.at
                protected void execute() throws RemoteException {
                    AlexaAudioProviderManagerMessageSender.this.sendMessage(AlexaAudioProviderManagerMessageType.GET_LOCALE, baseMessagePayload.getBundle());
                }
            };
        }
        return this.getLocaleResult.call();
    }

    public Bundle getPreferenceValues(ExtendedClient extendedClient) throws RemoteException {
        final BaseMessagePayload baseMessagePayload = new BaseMessagePayload(extendedClient);
        if (this.getPreferencesResult == null) {
            this.getPreferencesResult = new at<Bundle>(10000L, new Bundle()) { // from class: com.amazon.alexa.api.AlexaAudioProviderManagerMessageSender.2
                @Override // com.amazon.alexa.api.at
                protected void execute() throws RemoteException {
                    AlexaAudioProviderManagerMessageSender.this.sendMessage(AlexaAudioProviderManagerMessageType.GET_PREFERENCES, baseMessagePayload.getBundle());
                }
            };
        }
        return this.getPreferencesResult.call();
    }

    public boolean isUserLoggedIn(ExtendedClient extendedClient) throws RemoteException {
        final BaseMessagePayload baseMessagePayload = new BaseMessagePayload(extendedClient);
        if (this.isUserLoggedInResult == null) {
            this.isUserLoggedInResult = new at<Boolean>(10000L, false) { // from class: com.amazon.alexa.api.AlexaAudioProviderManagerMessageSender.3
                @Override // com.amazon.alexa.api.at
                protected void execute() throws RemoteException {
                    AlexaAudioProviderManagerMessageSender.this.sendMessage(AlexaAudioProviderManagerMessageType.IS_USER_LOGGED_IN, baseMessagePayload.getBundle());
                }
            };
        }
        return this.isUserLoggedInResult.call().booleanValue();
    }

    public void onClientDisconnect(ExtendedClient extendedClient) throws RemoteException {
        sendMessage(AlexaAudioProviderManagerMessageType.ON_CLIENT_DISCONNECT, new BaseMessagePayload(extendedClient).getBundle());
    }

    void onGetLocaleResponse(java.util.Locale locale) {
        at<java.util.Locale> atVar = this.getLocaleResult;
        if (atVar != null) {
            atVar.setResult(locale);
            this.getLocaleResult = null;
        }
    }

    void onGetPreferencesResponse(Bundle bundle) {
        at<Bundle> atVar = this.getPreferencesResult;
        if (atVar != null) {
            atVar.setResult(bundle);
            this.getPreferencesResult = null;
        }
    }

    void onIsUserLoggedInResponse(boolean z) {
        at<Boolean> atVar = this.isUserLoggedInResult;
        if (atVar != null) {
            atVar.setResult(Boolean.valueOf(z));
            this.isUserLoggedInResult = null;
        }
    }

    void onSetPreferencesResponse(boolean z) {
        at<Boolean> atVar = this.setUpdatePreferencesResult;
        if (atVar != null) {
            atVar.setResult(Boolean.valueOf(z));
            this.setUpdatePreferencesResult = null;
        }
    }

    public void prepare(ExtendedClient extendedClient) throws RemoteException {
        sendMessage(AlexaAudioProviderManagerMessageType.PREPARE, new BaseMessagePayload(extendedClient).getBundle());
    }

    public void registerAlexaSettingsListener(ExtendedClient extendedClient, AlexaSettingsListenerProxy alexaSettingsListenerProxy) throws RemoteException {
        sendMessage(AlexaAudioProviderManagerMessageType.REGISTER_SETTINGS_LISTENER, new SettingsListenerMessagePayload(extendedClient, alexaSettingsListenerProxy).getBundle());
    }

    public void registerAlexaStateListener(ExtendedClient extendedClient, AlexaStateListenerProxy alexaStateListenerProxy) throws RemoteException {
        sendMessage(AlexaAudioProviderManagerMessageType.REGISTER_ALEXA_STATE_LISTENER, new AlexaStateListenerMessagePayload(extendedClient, alexaStateListenerProxy).getBundle());
    }

    public void registerForceDisconnectListener(ExtendedClient extendedClient, MessageReceiver<as> messageReceiver) throws RemoteException {
        sendMessage(AlexaAudioProviderManagerMessageType.REGISTER_FORCE_DISCONNECT_LISTENER, new ForceDisconnectListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    @Deprecated
    public void startDialog(ExtendedClient extendedClient, AlexaDialogControllerProxy alexaDialogControllerProxy, AlexaAudioMetadata alexaAudioMetadata, ParcelFileDescriptor parcelFileDescriptor, @Nullable Bundle bundle) throws RemoteException {
        sendMessage(AlexaAudioProviderManagerMessageType.START_DIALOG, new StartTurnDialogControllerMessagePayload(extendedClient, alexaDialogControllerProxy, alexaAudioMetadata, parcelFileDescriptor, bundle).getBundle());
    }

    public void startDialog(ExtendedClient extendedClient, AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2, AlexaAudioMetadata alexaAudioMetadata, ParcelFileDescriptor parcelFileDescriptor, @Nullable Bundle bundle) throws RemoteException {
        startDialog(extendedClient, alexaDialogControllerProxyV2, alexaAudioMetadata, parcelFileDescriptor, null, bundle);
    }

    public void startDialog(ExtendedClient extendedClient, AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2, AlexaAudioMetadata alexaAudioMetadata, ParcelFileDescriptor parcelFileDescriptor, @Nullable ParcelFileDescriptor parcelFileDescriptor2, @Nullable Bundle bundle) throws RemoteException {
        sendMessage(parcelFileDescriptor2 == null ? AlexaAudioProviderManagerMessageType.START_DIALOG : AlexaAudioProviderManagerMessageType.START_DIALOG_WITH_METADATA, new StartTurnDialogControllerMessagePayload(extendedClient, alexaDialogControllerProxyV2, alexaAudioMetadata, parcelFileDescriptor, parcelFileDescriptor2, bundle).getBundle());
    }

    @Deprecated
    public void stopDialogTurn(ExtendedClient extendedClient, AlexaDialogControllerProxy alexaDialogControllerProxy) throws RemoteException {
        sendMessage(AlexaAudioProviderManagerMessageType.STOP_DIALOG_TURN, new DialogControllerMessagePayload(extendedClient, alexaDialogControllerProxy).getBundle());
    }

    public void stopDialogTurn(ExtendedClient extendedClient, AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2) throws RemoteException {
        sendMessage(AlexaAudioProviderManagerMessageType.STOP_DIALOG_TURN, new DialogControllerMessagePayload(extendedClient, alexaDialogControllerProxyV2).getBundle());
    }

    public boolean updatePreferences(ExtendedClient extendedClient, Bundle bundle) throws RemoteException {
        final UpdatePreferencesMessagePayload updatePreferencesMessagePayload = new UpdatePreferencesMessagePayload(extendedClient, bundle);
        if (this.setUpdatePreferencesResult == null) {
            this.setUpdatePreferencesResult = new at<Boolean>(10000L, false) { // from class: com.amazon.alexa.api.AlexaAudioProviderManagerMessageSender.1
                @Override // com.amazon.alexa.api.at
                protected void execute() throws RemoteException {
                    AlexaAudioProviderManagerMessageSender.this.sendMessage(AlexaAudioProviderManagerMessageType.UPDATE_PREFERENCES, updatePreferencesMessagePayload.getBundle());
                }
            };
        }
        return this.setUpdatePreferencesResult.call().booleanValue();
    }
}
