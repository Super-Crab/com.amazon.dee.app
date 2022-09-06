package com.amazon.alexa.api;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Message;
import android.os.Messenger;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.BOa;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.Msx;
import com.amazon.alexa.Shr;
import com.amazon.alexa.api.AlexaDialogControllerProxy;
import com.amazon.alexa.api.AlexaMetricsName;
import com.amazon.alexa.api.AlexaServices;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.AlexaSettingsListenerProxy;
import com.amazon.alexa.api.AlexaStateListenerProxy;
import com.amazon.alexa.api.DialogControllerProxyWrapper;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.auth.AccountManager;
import com.amazon.alexa.esV;
import com.amazon.alexa.peZ;
import com.amazon.alexa.qSf;
import com.amazon.alexa.utils.validation.Preconditions;
import com.amazon.alexa.vYS;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes6.dex */
public class AlexaAudioProviderManagerV1 extends MessageProcessor<AlexaAudioProviderManagerMessageType> implements AlexaAudioProviderManagerVx {
    public static final long ENSURE_ALEXA_SERVICES_CONNECTION_TIMEOUT_MS = 1000;
    public static final String TAG = "AlexaAudioProviderManagerV1";
    public final AccountManager accountManager;
    public final AlexaServicesConnection alexaServicesConnection;
    public final ConditionVariable alexaServicesConnectionVariable;
    public final Context context;
    public final peZ devicePreferences;
    public final Map<qSf, DialogControllerProxyWrapper> dialogControllers;
    public final Shr<ForceDisconnectMessageSender> forceDisconnectListeners;
    public final KeyguardManager keyguardManager;
    public final MetricBroadcastSender metricBroadcastSender;

    /* renamed from: com.amazon.alexa.api.AlexaAudioProviderManagerV1$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType = new int[AlexaAudioProviderManagerMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.PREPARE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.START_DIALOG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.CONTINUE_DIALOG.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.STOP_DIALOG_TURN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.REGISTER_ALEXA_STATE_LISTENER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.DEREGISTER_ALEXA_STATE_LISTENER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.ON_CLIENT_DISCONNECT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.REGISTER_FORCE_DISCONNECT_LISTENER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.DEREGISTER_FORCE_DISCONNECT_LISTENER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.IS_USER_LOGGED_IN.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.UPDATE_PREFERENCES.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.GET_PREFERENCES.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.GET_LOCALE.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.GET_LOCALES.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.REGISTER_SETTINGS_LISTENER.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaAudioProviderManagerMessageType[AlexaAudioProviderManagerMessageType.DEREGISTER_SETTINGS_LISTENER.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    /* loaded from: classes6.dex */
    private class AlexaServiceConnectionListener implements AlexaServicesConnection.ConnectionListener {
        public final ConditionVariable alexaServicesConnectionVariable;

        public AlexaServiceConnectionListener(ConditionVariable conditionVariable) {
            this.alexaServicesConnectionVariable = conditionVariable;
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnected() {
            this.alexaServicesConnectionVariable.open();
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
            this.alexaServicesConnectionVariable.open();
            String str2 = "Failed to connect to AlexaService: " + alexaConnectingFailedReason + " " + str;
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onDisconnected() {
            this.alexaServicesConnectionVariable.close();
            AlexaAudioProviderManagerV1.this.forceDisconnectAllClients();
        }
    }

    /* loaded from: classes6.dex */
    private static class AudioProviderBaseMessagePayload extends BaseMessagePayload {
        public AudioProviderBaseMessagePayload(ExtendedClient extendedClient) {
            super(extendedClient);
            addParcelable(AudioProviderManagerArgumentKey.CLIENT, extendedClient.asClient());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes6.dex */
    public class DialogControllerCleanupCallback implements DialogControllerProxyWrapper.WrapperLifecycle {
        public final qSf dialogIdentifier;

        public DialogControllerCleanupCallback(qSf qsf) {
            this.dialogIdentifier = qsf;
        }

        @Override // com.amazon.alexa.api.DialogControllerProxyWrapper.WrapperLifecycle
        public void onFinished() {
            AlexaAudioProviderManagerV1.this.dialogControllers.remove(this.dialogIdentifier);
        }

        @Override // com.amazon.alexa.api.DialogControllerProxyWrapper.WrapperLifecycle
        public void onStarted() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class GetLocaleResultPayload extends AudioProviderBaseMessagePayload {
        public GetLocaleResultPayload(ExtendedClient extendedClient, @Nullable java.util.Locale locale) {
            super(extendedClient);
            if (locale != null) {
                add(AudioProviderManagerArgumentKey.LOCALE, locale.toLanguageTag());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class GetLocalesResultPayload extends AudioProviderBaseMessagePayload {
        public GetLocalesResultPayload(ExtendedClient extendedClient, @Nullable List<java.util.Locale> list) {
            super(extendedClient);
            if (list != null) {
                ArrayList arrayList = new ArrayList();
                for (java.util.Locale locale : list) {
                    arrayList.add(locale.toLanguageTag());
                }
                add(AudioProviderManagerArgumentKey.LOCALES, arrayList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class GetPreferencesResultPayload extends AudioProviderBaseMessagePayload {
        public GetPreferencesResultPayload(ExtendedClient extendedClient, Bundle bundle) {
            super(extendedClient);
            addParcelable(AudioProviderManagerArgumentKey.PREFERENCES_BUNDLE, bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class IsUserLoggedInListener implements AccountManager.ResultCallback<Boolean> {
        public volatile boolean isUserLoggedIn;
        public final ConditionVariable isUserLoggedInVariable;

        public IsUserLoggedInListener(ConditionVariable conditionVariable) {
            this.isUserLoggedInVariable = conditionVariable;
        }

        public boolean isUserLoggedIn() {
            return this.isUserLoggedIn;
        }

        @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
        public void onError(Exception exc) {
            this.isUserLoggedIn = false;
            this.isUserLoggedInVariable.open();
        }

        @Override // com.amazon.alexa.auth.AccountManager.ResultCallback
        public void onResult(Boolean bool) {
            this.isUserLoggedIn = bool.booleanValue();
            this.isUserLoggedInVariable.open();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class SetPreferencesResultPayload extends AudioProviderBaseMessagePayload {
        public SetPreferencesResultPayload(ExtendedClient extendedClient, boolean z) {
            super(extendedClient);
            add(AudioProviderManagerArgumentKey.SET_PREFERENCES_UPDATE, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class UserLoggedInResultPayload extends AudioProviderBaseMessagePayload {
        public UserLoggedInResultPayload(ExtendedClient extendedClient, boolean z) {
            super(extendedClient);
            add(AudioProviderManagerArgumentKey.USER_LOGGED_IN, z);
        }
    }

    public AlexaAudioProviderManagerV1(Context context, AccountManager accountManager, peZ pez) {
        this(context, accountManager, pez, new AlexaServicesConnection(context));
    }

    private void addSubClient(ExtendedClient extendedClient) {
        this.alexaServicesConnection.getClient().addSubClient(extendedClient);
    }

    private void clearSubClients() {
        this.alexaServicesConnection.getClient().clearSubClients();
    }

    private void deregisterAlexaStateListener(Bundle bundle) {
        ExtendedClient client = Bundles.getClient(bundle);
        AlexaStateListenerProxy asInterface = AlexaStateListenerProxy.Stub.asInterface(Bundles.getBinder(bundle, AudioProviderManagerArgumentKey.ALEXA_STATE_LISTENER_PROXY));
        String str = "deregisterAlexaStateListener: " + client;
        setActiveSubClient(client);
        AlexaServices.Recognizer.deregisterListener(this.alexaServicesConnection, asInterface);
    }

    private void deregisterForceDisconnectListener(Bundle bundle) {
        ExtendedClient client = Bundles.getClient(bundle);
        ForceDisconnectMessageSender forceDisconnectMessageSender = new ForceDisconnectMessageSender(Bundles.getBinder(bundle, AudioProviderManagerArgumentKey.FORCE_DISCONNECT_LISTENER), client);
        C0179Pya.zZm("deregisterForceDisconnectListener: ", (Object) client);
        this.forceDisconnectListeners.remove(forceDisconnectMessageSender);
        removeSubClient(client);
    }

    private void deregisterSettingsListener(Bundle bundle) {
        ExtendedClient client = Bundles.getClient(bundle);
        AlexaSettingsListenerProxy asInterface = AlexaSettingsListenerProxy.Stub.asInterface(Bundles.getBinder(bundle, AudioProviderManagerArgumentKey.ALEXA_SETTINGS_LISTENER_PROXY));
        C0179Pya.zZm("deregisterSettingsListener: ", (Object) client);
        AlexaServices.Settings.deregisterListener(this.alexaServicesConnection, asInterface);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void forceDisconnectAllClients() {
        for (ForceDisconnectMessageSender forceDisconnectMessageSender : this.forceDisconnectListeners.zZm()) {
            try {
                forceDisconnectMessageSender.onForceDisconnect();
            } catch (RemoteException e) {
                ExtendedClient BIo = this.forceDisconnectListeners.BIo((Shr<ForceDisconnectMessageSender>) forceDisconnectMessageSender);
                String str = TAG;
                Log.w(str, "Failure when force disconnecting client. Likely disconnected already. Client: " + BIo, e);
            }
        }
        clearSubClients();
    }

    private GetLocaleResultPayload getLocale(Bundle bundle) {
        ExtendedClient client = Bundles.getClient(bundle);
        C0179Pya.zZm("getLocale: ", (Object) client);
        return new GetLocaleResultPayload(client, AlexaServices.Settings.getLocale(this.alexaServicesConnection));
    }

    private GetLocalesResultPayload getLocales(Bundle bundle) {
        ExtendedClient client = Bundles.getClient(bundle);
        C0179Pya.zZm("getLocales: ", (Object) client);
        return new GetLocalesResultPayload(client, Collections.singletonList(AlexaServices.Settings.getLocale(this.alexaServicesConnection)));
    }

    private GetPreferencesResultPayload getPreferences(Bundle bundle) {
        ExtendedClient client = Bundles.getClient(bundle);
        C0179Pya.zZm("getPreferences: ", (Object) client);
        return new GetPreferencesResultPayload(client, this.devicePreferences.zZm().getBundle());
    }

    private UserLoggedInResultPayload isUserLoggedIn(Bundle bundle) {
        boolean z;
        ExtendedClient client = Bundles.getClient(bundle);
        ConditionVariable conditionVariable = new ConditionVariable();
        IsUserLoggedInListener isUserLoggedInListener = new IsUserLoggedInListener(conditionVariable);
        C0179Pya.zZm("isUserLoggedIn: ", (Object) client);
        this.accountManager.isLoggedIn(isUserLoggedInListener);
        if (conditionVariable.block(1000L)) {
            z = isUserLoggedInListener.isUserLoggedIn();
        } else {
            Log.e(TAG, "Timed out waiting for AlexaService");
            z = false;
        }
        return new UserLoggedInResultPayload(client, z);
    }

    private void onClientDisconnect(Bundle bundle) {
        ExtendedClient client = Bundles.getClient(bundle);
        C0179Pya.zZm("onClientDisconnect: ", (Object) client);
        this.forceDisconnectListeners.BIo(client);
        removeSubClient(client);
    }

    private void prepare(Bundle bundle) {
        ExtendedClient client = Bundles.getClient(bundle);
        String str = "prepare: " + client;
        ensureConnection();
        setActiveSubClient(client);
        AlexaServices.Recognizer.prepare(this.alexaServicesConnection);
    }

    private void registerAlexaStateListener(Bundle bundle) {
        ExtendedClient client = Bundles.getClient(bundle);
        AlexaStateListenerProxy asInterface = AlexaStateListenerProxy.Stub.asInterface(Bundles.getBinder(bundle, AudioProviderManagerArgumentKey.ALEXA_STATE_LISTENER_PROXY));
        String str = "registerAlexaStateListener: " + client;
        ensureConnection();
        setActiveSubClient(client);
        AlexaServices.Recognizer.registerListener(this.alexaServicesConnection, asInterface);
    }

    private void registerForceDisconnectListener(Bundle bundle) {
        ExtendedClient client = Bundles.getClient(bundle);
        ForceDisconnectMessageSender forceDisconnectMessageSender = new ForceDisconnectMessageSender(Bundles.getBinder(bundle, AudioProviderManagerArgumentKey.FORCE_DISCONNECT_LISTENER), client);
        C0179Pya.zZm("registerForceDisconnectListener: ", (Object) client);
        this.forceDisconnectListeners.zZm(client, forceDisconnectMessageSender);
        addSubClient(client);
    }

    private void registerSettingsListener(Bundle bundle) {
        ExtendedClient client = Bundles.getClient(bundle);
        AlexaSettingsListenerProxy asInterface = AlexaSettingsListenerProxy.Stub.asInterface(Bundles.getBinder(bundle, AudioProviderManagerArgumentKey.ALEXA_SETTINGS_LISTENER_PROXY));
        String str = "registerSettingsListener: " + client;
        ensureConnection();
        setActiveSubClient(client);
        AlexaServices.Settings.registerListener(this.alexaServicesConnection, asInterface);
    }

    private void removeSubClient(ExtendedClient extendedClient) {
        this.alexaServicesConnection.getClient().removeSubClient(extendedClient);
    }

    private SetPreferencesResultPayload updatePreferences(Bundle bundle) {
        ExtendedClient client = Bundles.getClient(bundle);
        String str = "updatePreferences: " + client;
        this.devicePreferences.zZm(new AlexaPreferences(Bundles.getBundle(bundle, AudioProviderManagerArgumentKey.PREFERENCES_BUNDLE)));
        return new SetPreferencesResultPayload(client, true);
    }

    public void continueDialog(Bundle bundle) throws RemoteException {
        ExtendedClient client = Bundles.getClient(bundle);
        AlexaDialogControllerProxy asInterface = AlexaDialogControllerProxy.Stub.asInterface(Bundles.getBinder(bundle, AudioProviderManagerArgumentKey.ALEXA_DIALOG_CONTROLLER_PROXY));
        Preconditions.notNull(asInterface, "dialog controller is null");
        qSf zZm = qSf.zZm(asInterface.getDialogIdentifier());
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) Bundles.getParcelable(bundle, AudioProviderManagerArgumentKey.AUDIO_FILE_DESCRIPTOR, ParcelFileDescriptor.class);
        if (!this.dialogControllers.containsKey(zZm)) {
            Log.e(TAG, "Attempting to continue dialog from an unknown controller. Start the dialogfirst.");
            dropAudio(parcelFileDescriptor);
            reportError(AlexaMetricsName.VoiceInteraction.Abandoned.OUT_OF_TURN_CONTINUE_INVALID_DIALOG, null);
            return;
        }
        DialogControllerProxyWrapper dialogControllerProxyWrapper = this.dialogControllers.get(zZm);
        AlexaAudioMetadata alexaAudioMetadata = (AlexaAudioMetadata) Bundles.getParcelable(bundle, AudioProviderManagerArgumentKey.ALEXA_AUDIO_METADATA, AlexaAudioMetadata.class);
        String str = "continueDialog: " + client + " " + zZm + " " + dialogControllerProxyWrapper.getDialogTurnIdentifier();
        AlexaDialogExtras alexaDialogExtras = dialogControllerProxyWrapper.zZm;
        if (!shouldAcceptAudio(alexaDialogExtras, false)) {
            Log.i(TAG, "On the lock screen. Dropping audio");
            dropAudio(dialogControllerProxyWrapper, parcelFileDescriptor, true);
            showUI(alexaAudioMetadata, alexaDialogExtras);
            reportError(AlexaMetricsName.VoiceInteraction.Abandoned.SCREEN_LOCKED, alexaDialogExtras.getInvocationType(), dialogControllerProxyWrapper.getDialogTurnIdentifier());
            return;
        }
        ensureConnection();
        if (this.alexaServicesConnection.isConnected()) {
            setActiveSubClient(client);
            AlexaServices.Recognizer.continueDialog(this.alexaServicesConnection, (AlexaDialogControllerProxy) dialogControllerProxyWrapper, alexaAudioMetadata, parcelFileDescriptor);
            return;
        }
        Log.i(TAG, "Not connected to Alexa. Dropping audio");
        dropAudio(dialogControllerProxyWrapper, parcelFileDescriptor, true);
        reportError(AlexaMetricsName.VoiceInteraction.Failure.LOCAL_SERVICE_DISCONNECTED, alexaDialogExtras.getInvocationType(), dialogControllerProxyWrapper.getDialogTurnIdentifier());
    }

    public void dropAudio(DialogControllerProxyWrapper dialogControllerProxyWrapper, ParcelFileDescriptor parcelFileDescriptor, boolean z) throws RemoteException {
        if (!z) {
            AlexaDialogControllerProxy alexaDialogControllerProxy = dialogControllerProxyWrapper.BIo;
            if (alexaDialogControllerProxy != null) {
                alexaDialogControllerProxy.onDialogStarted();
            } else {
                AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2 = dialogControllerProxyWrapper.zQM;
                if (alexaDialogControllerProxyV2 != null) {
                    alexaDialogControllerProxyV2.onDialogStarted();
                }
            }
            dialogControllerProxyWrapper.zyO.onStarted();
        }
        AlexaDialogControllerProxy alexaDialogControllerProxy2 = dialogControllerProxyWrapper.BIo;
        if (alexaDialogControllerProxy2 != null) {
            alexaDialogControllerProxy2.stopRecording();
        } else {
            AlexaDialogControllerProxyV2 alexaDialogControllerProxyV22 = dialogControllerProxyWrapper.zQM;
            if (alexaDialogControllerProxyV22 != null) {
                alexaDialogControllerProxyV22.stopRecording();
            }
        }
        AlexaDialogControllerProxy alexaDialogControllerProxy3 = dialogControllerProxyWrapper.BIo;
        if (alexaDialogControllerProxy3 != null) {
            alexaDialogControllerProxy3.onDialogFinished();
        } else {
            AlexaDialogControllerProxyV2 alexaDialogControllerProxyV23 = dialogControllerProxyWrapper.zQM;
            if (alexaDialogControllerProxyV23 != null) {
                alexaDialogControllerProxyV23.onDialogFinished();
            }
        }
        dialogControllerProxyWrapper.zyO.onFinished();
        dropAudio(parcelFileDescriptor);
    }

    public void ensureConnection() {
        if (!this.alexaServicesConnection.isConnected()) {
            this.alexaServicesConnection.connect();
            if (this.alexaServicesConnectionVariable.block(1000L)) {
                return;
            }
            Log.e(TAG, "Timed out connecting to AlexaService");
            forceDisconnectAllClients();
        }
    }

    @Override // com.amazon.alexa.api.AlexaAudioProviderManagerVx
    public synchronized void onAllClientsDisconnected() {
        this.forceDisconnectListeners.clear();
        this.alexaServicesConnection.release();
    }

    public void reportError(AlexaMetricsName alexaMetricsName, @Nullable String str) {
        reportError(alexaMetricsName, str, null);
    }

    public void setActiveSubClient(ExtendedClient extendedClient) {
        this.alexaServicesConnection.getClient().setActiveSubClient(extendedClient);
    }

    @VisibleForTesting
    public boolean shouldAcceptAudio(@Nullable AlexaDialogExtras alexaDialogExtras, boolean z) {
        boolean z2 = alexaDialogExtras != null && alexaDialogExtras.isUserVoiceVerified();
        if (!Msx.zZm(this.keyguardManager) || z) {
            return true;
        }
        return z2 && this.devicePreferences.zZm().preferDisplayOverLockscreenWithVerifiedVoice();
    }

    public void showUI(AlexaAudioMetadata alexaAudioMetadata, AlexaDialogExtras alexaDialogExtras) {
        esV esv = esV.UNKNOWN;
        if (alexaAudioMetadata.getAlexaWakeword() != null) {
            esv = esV.WAKE_WORD;
        }
        vYS.zZm(this.context, esv, alexaDialogExtras.getAlexaUserInterfaceOptions(), false, vYS.zZm(alexaDialogExtras.getInvocationType()), false);
    }

    public void startDialog(Bundle bundle) throws RemoteException {
        ExtendedClient client = Bundles.getClient(bundle);
        AlexaDialogControllerProxy asInterface = AlexaDialogControllerProxy.Stub.asInterface(Bundles.getBinder(bundle, AudioProviderManagerArgumentKey.ALEXA_DIALOG_CONTROLLER_PROXY));
        Preconditions.notNull(asInterface, "dialog controller is null");
        qSf zZm = qSf.zZm(asInterface.getDialogIdentifier());
        Bundle optionalBundle = Bundles.getOptionalBundle(bundle, AudioProviderManagerArgumentKey.DIALOG_EXTRAS);
        DialogExtras dialogExtras = new DialogExtras(optionalBundle);
        DialogControllerProxyWrapper dialogControllerProxyWrapper = new DialogControllerProxyWrapper(dialogExtras, asInterface, new DialogControllerCleanupCallback(zZm));
        AlexaAudioMetadata alexaAudioMetadata = (AlexaAudioMetadata) Bundles.getParcelable(bundle, AudioProviderManagerArgumentKey.ALEXA_AUDIO_METADATA, AlexaAudioMetadata.class);
        ParcelFileDescriptor parcelFileDescriptor = (ParcelFileDescriptor) Bundles.getParcelable(bundle, AudioProviderManagerArgumentKey.AUDIO_FILE_DESCRIPTOR, ParcelFileDescriptor.class);
        String str = TAG;
        Log.i(str, "startDialog: " + client + " " + zZm);
        this.dialogControllers.put(zZm, dialogControllerProxyWrapper);
        if (!shouldAcceptAudio(dialogExtras, BOa.zZm(alexaAudioMetadata))) {
            Log.i(TAG, "On the lock screen. Dropping audio");
            dropAudio(dialogControllerProxyWrapper, parcelFileDescriptor, false);
            showUI(alexaAudioMetadata, dialogExtras);
            reportError(AlexaMetricsName.VoiceInteraction.Abandoned.SCREEN_LOCKED, dialogExtras.getInvocationType());
            return;
        }
        ensureConnection();
        if (this.alexaServicesConnection.isConnected()) {
            setActiveSubClient(client);
            AlexaServices.Recognizer.startDialog(this.alexaServicesConnection, dialogControllerProxyWrapper, alexaAudioMetadata, parcelFileDescriptor, optionalBundle);
            return;
        }
        Log.i(TAG, "Not connected to Alexa. Dropping audio");
        dropAudio(dialogControllerProxyWrapper, parcelFileDescriptor, false);
        reportError(AlexaMetricsName.VoiceInteraction.Failure.LOCAL_SERVICE_DISCONNECTED, dialogExtras.getInvocationType());
    }

    public void stopDialogTurn(Bundle bundle) throws RemoteException {
        ExtendedClient client = Bundles.getClient(bundle);
        AlexaDialogControllerProxy asInterface = AlexaDialogControllerProxy.Stub.asInterface(Bundles.getBinder(bundle, AudioProviderManagerArgumentKey.ALEXA_DIALOG_CONTROLLER_PROXY));
        String str = "stopDialog: " + client;
        ensureConnection();
        setActiveSubClient(client);
        AlexaServices.Recognizer.stopDialogTurn(this.alexaServicesConnection, asInterface);
    }

    @VisibleForTesting
    public AlexaAudioProviderManagerV1(Context context, AccountManager accountManager, peZ pez, AlexaServicesConnection alexaServicesConnection) {
        this.context = context;
        this.accountManager = accountManager;
        this.devicePreferences = pez;
        this.forceDisconnectListeners = new Shr<>();
        this.dialogControllers = new HashMap();
        this.alexaServicesConnectionVariable = new ConditionVariable();
        this.alexaServicesConnection = alexaServicesConnection;
        this.alexaServicesConnection.setKeepAlive(true);
        this.metricBroadcastSender = new MetricBroadcastSender(alexaServicesConnection);
        this.alexaServicesConnection.registerListener(new AlexaServiceConnectionListener(this.alexaServicesConnectionVariable));
        this.keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: getMessageType */
    public AlexaAudioProviderManagerMessageType mo845getMessageType(Message message) {
        try {
            return AlexaAudioProviderManagerMessageType.fromOrdinal(message.what);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Unrecognized message type", e);
            return AlexaAudioProviderManagerMessageType.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    public synchronized void processMessage(AlexaAudioProviderManagerMessageType alexaAudioProviderManagerMessageType, Bundle bundle, @Nullable Messenger messenger) {
        try {
            switch (alexaAudioProviderManagerMessageType.ordinal()) {
                case 1:
                    prepare(bundle);
                    break;
                case 2:
                    startDialog(bundle);
                    break;
                case 3:
                    continueDialog(bundle);
                    break;
                case 4:
                    stopDialogTurn(bundle);
                    break;
                case 5:
                    registerAlexaStateListener(bundle);
                    break;
                case 6:
                    deregisterAlexaStateListener(bundle);
                    break;
                case 7:
                    onClientDisconnect(bundle);
                    break;
                case 8:
                    registerForceDisconnectListener(bundle);
                    break;
                case 9:
                    deregisterForceDisconnectListener(bundle);
                    break;
                case 10:
                    reply(messenger, alexaAudioProviderManagerMessageType, isUserLoggedIn(bundle).getBundle());
                    break;
                case 11:
                default:
                    String str = TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unsupported message ");
                    sb.append(alexaAudioProviderManagerMessageType);
                    Log.w(str, sb.toString());
                    break;
                case 12:
                    reply(messenger, alexaAudioProviderManagerMessageType, updatePreferences(bundle).getBundle());
                    break;
                case 13:
                    reply(messenger, alexaAudioProviderManagerMessageType, getPreferences(bundle).getBundle());
                    break;
                case 14:
                    reply(messenger, alexaAudioProviderManagerMessageType, getLocale(bundle).getBundle());
                    break;
                case 15:
                    registerSettingsListener(bundle);
                    break;
                case 16:
                    deregisterSettingsListener(bundle);
                    break;
                case 17:
                    reply(messenger, alexaAudioProviderManagerMessageType, getLocales(bundle).getBundle());
                    break;
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to handle incoming message! ", e);
        }
    }

    public void reportError(AlexaMetricsName alexaMetricsName, @Nullable String str, @Nullable String str2) {
        this.metricBroadcastSender.sendVoiceInteractionEvent(alexaMetricsName, str, str2);
    }

    public void dropAudio(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            parcelFileDescriptor.close();
        } catch (IOException e) {
            Log.e(TAG, "Unable to close file descriptor", e);
        }
    }
}
