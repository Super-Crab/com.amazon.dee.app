package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.GvA;
import com.amazon.alexa.UYN;
import com.amazon.alexa.api.AlexaAudioInteractionProxy;
import com.amazon.alexa.api.AlexaAudioPlaybackListenerProxy;
import com.amazon.alexa.api.AlexaCardRendererListenerProxy;
import com.amazon.alexa.api.AlexaContextProviderProxy;
import com.amazon.alexa.api.AlexaDialogControllerProxy;
import com.amazon.alexa.api.AlexaPlayerInfoCardListenerProxy;
import com.amazon.alexa.api.AlexaSettingsListenerProxy;
import com.amazon.alexa.api.AlexaStateListenerProxy;
import com.amazon.alexa.api.AlexaTemplateCardListenerProxy;
import com.amazon.alexa.api.AlexaUserSpeechListenerProxy;
import com.amazon.alexa.api.UserPerceivedLatencyListenerProxy;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.bNQ;
import java.util.Collections;
import java.util.List;
/* loaded from: classes6.dex */
public class AlexaServicesV1 extends MessageProcessor<AlexaServicesMessageType> {
    public static final String TAG = "AlexaServicesV1";
    public final AlexaClient alexaClient;
    public final UYN errorReporter;
    public final LegacyUserSpeechProviders legacyUserSpeechProviders;

    /* renamed from: com.amazon.alexa.api.AlexaServicesV1$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType = new int[AlexaServicesMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.MUTE_MICROPHONE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.CANCEL_USER_INTERACTION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.START_RECOGNIZING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.STOP_RECOGNIZING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.START_DIALOG.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.CONTINUE_DIALOG.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.STOP_DIALOG_TURN.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_ALEXA_STATE_LISTENER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_ALEXA_STATE_LISTENER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_USER_SPEECH_LISTENER.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_USER_SPEECH_LISTENER.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.PREVIOUS.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.PLAY.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.PAUSE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.STOP.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.NEXT.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.TEMPORARILY_SUPPRESS_ALL_AUDIO.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_AUDIO_PLAYBACK_LISTENER.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_AUDIO_PLAYBACK_LISTENER.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.SET_LOCALE.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.GET_LOCALE.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.GET_SUPPORTED_LOCALES.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_ALEXA_SETTINGS_LISTENER.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_ALEXA_SETTINGS_LISTENER.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_TEMPLATE_CARD_LISTENER.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_TEMPLATE_CARD_LISTENER.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_PLAYER_INFO_CARD_LISTENER.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_PLAYER_INFO_CARD_LISTENER.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_CARD_RENDERER_LISTENER.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_CARD_RENDERER_LISTENER.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.ON_CLIENT_DISCONECT.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_FORCE_DISCONNECT_LISTENER.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_FORCE_DISCONNECT_LISTENER.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.IS_USER_LOGGED_IN.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_CONTEXT_PROVIDER.ordinal()] = 35;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_CONTEXT_PROVIDER.ordinal()] = 36;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.SEND_ALEXA_EVENT.ordinal()] = 37;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.SCHEDULE_INTERACTION.ordinal()] = 38;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.UNSCHEDULE_INTERACTION.ordinal()] = 39;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_UPL_LISTENER.ordinal()] = 40;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_UPL_LISTENER.ordinal()] = 41;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_ALERTS_LISTENER.ordinal()] = 42;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_ALERTS_LISTENER.ordinal()] = 43;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_METRICS_LISTENER.ordinal()] = 44;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_METRICS_LISTENER.ordinal()] = 45;
            } catch (NoSuchFieldError unused45) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.START_LISTENING_WAKE_WORD.ordinal()] = 46;
            } catch (NoSuchFieldError unused46) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.STOP_LISTENING_WAKE_WORD.ordinal()] = 47;
            } catch (NoSuchFieldError unused47) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.SET_WAKE_WORD_ALLOWED.ordinal()] = 48;
            } catch (NoSuchFieldError unused48) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.IS_DETECTING_WAKE_WORD.ordinal()] = 49;
            } catch (NoSuchFieldError unused49) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class CurrentLocaleMessagePayload extends BaseMessagePayload {
        public CurrentLocaleMessagePayload(ExtendedClient extendedClient, String str) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.CURRENT_LOCALE, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class IsDetectingWakeWordMessagePayload extends BaseMessagePayload {
        public IsDetectingWakeWordMessagePayload(ExtendedClient extendedClient, boolean z) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.IS_DETECTING_WAKE_WORD, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class IsUserLoggedInMessagePayload extends BaseMessagePayload {
        public IsUserLoggedInMessagePayload(ExtendedClient extendedClient, boolean z) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.IS_USER_LOGGED_IN, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class SupportedLocalesMessagePayload extends BaseMessagePayload {
        public SupportedLocalesMessagePayload(ExtendedClient extendedClient, List<String> list) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.SUPPORTED_LOCALES, list);
        }
    }

    public AlexaServicesV1(AlexaClient alexaClient, UYN uyn) {
        this(alexaClient, uyn, new LegacyUserSpeechProviders());
    }

    private void cancelUserInteraction(Bundle bundle) {
        this.alexaClient.cancelUserInteraction(getClient(bundle));
    }

    private LegacyUserSpeechProvider createLegacyUserSpeechProvider(AlexaDialogControllerProxy alexaDialogControllerProxy, AlexaAudioMetadata alexaAudioMetadata, ParcelFileDescriptor parcelFileDescriptor, AlexaDialogExtras alexaDialogExtras) {
        return this.legacyUserSpeechProviders.createLegacyUserSpeechProvider(alexaDialogControllerProxy, Collections.emptySet(), alexaAudioMetadata, parcelFileDescriptor, (ParcelFileDescriptor) null, alexaDialogExtras);
    }

    private void deregisterAlertsListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.deregisterAlertsListener(client, new AlertsMessageSender(Bundles.getBinder(bundle, AlexaServicesArgumentKey.ALERTS_LISTENER), client));
    }

    private void deregisterAlexaAudioPlaybackListener(Bundle bundle) {
        this.alexaClient.deregisterAlexaAudioPlaybackListener(getClient(bundle), AlexaAudioPlaybackListenerProxy.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.AUDIO_PLAYBACK_LISTENER_PROXY)));
    }

    private void deregisterAlexaPlayerInfoCardListener(Bundle bundle) {
        this.alexaClient.deregisterAlexaPlayerInfoCardListener(getClient(bundle), AlexaPlayerInfoCardListenerProxy.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.PLAYER_INFO_CARD_LISTENER_PROXY)));
    }

    private void deregisterAlexaSettingsListener(Bundle bundle) {
        this.alexaClient.deregisterAlexaSettingsListener(getClient(bundle), AlexaSettingsListenerProxy.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.SETTINGS_LISTENER_PROXY)));
    }

    private void deregisterAlexaStateListener(Bundle bundle) {
        this.alexaClient.deregisterAlexaStateListener(getClient(bundle), AlexaStateListenerProxy.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.STATE_LISTENER_PROXY)));
    }

    private void deregisterAlexaTemplateCardListener(Bundle bundle) {
        this.alexaClient.deregisterAlexaTemplateCardListener(getClient(bundle), AlexaTemplateCardListenerProxy.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.TEMPLATE_CARD_LISTENER_PROXY)));
    }

    private void deregisterAlexaUserSpeechListener(Bundle bundle) {
        this.alexaClient.deregisterAlexaUserSpeechListener(getClient(bundle), AlexaUserSpeechListenerProxy.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.USER_SPEECH_LISTENER_PROXY)));
    }

    private void deregisterContextProvider(Bundle bundle) throws RemoteException {
        this.alexaClient.deregisterContextProvider(getClient(bundle), AlexaContextProviderProxy.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.CONTEXT_PROVIDER_PROXY)));
    }

    private void deregisterForceDisconnectListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.deregisterForceDisconnectListener(client, new ForceDisconnectMessageSender(Bundles.getBinder(bundle, AlexaServicesArgumentKey.FORCE_DISCONNECT_LISTENER), client));
    }

    private void deregisterMetricsListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.deregisterMetricsListener(client, new MetricsMessageSender(Bundles.getBinder(bundle, AlexaServicesArgumentKey.METRICS_LISTENER), client));
    }

    private void deregisterUserPerceivedLatencyListener(Bundle bundle) {
        this.alexaClient.deregisterUserPerceivedLatencyListener(getClient(bundle), new bNQ(UserPerceivedLatencyListenerProxy.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.USER_PERCEIVED_LATENCY_LISTENER_PROXY))));
    }

    private String getLocale(Bundle bundle) {
        return this.alexaClient.getLocale(getClient(bundle));
    }

    private List<String> getSupportedLocales(Bundle bundle) {
        return this.alexaClient.getSupportedLocales(getClient(bundle));
    }

    private boolean isDetectingWakeword(Bundle bundle) {
        return this.alexaClient.isDetectingWakeWord(getClient(bundle));
    }

    private boolean isUserLoggedIn(Bundle bundle) {
        return this.alexaClient.isUserLoggedIn(getClient(bundle));
    }

    private void muteMicrophone(Bundle bundle) {
        this.alexaClient.muteMicrophone(getClient(bundle), Bundles.getBoolean(bundle, AlexaServicesArgumentKey.IS_MUTED));
    }

    private void next(Bundle bundle) {
        this.alexaClient.next(getClient(bundle));
    }

    private void onClientDisconnect(Bundle bundle) {
        this.alexaClient.onClientDisconnect(getClient(bundle));
    }

    private void pause(Bundle bundle) {
        this.alexaClient.pause(getClient(bundle));
    }

    private void play(Bundle bundle) {
        this.alexaClient.play(getClient(bundle));
    }

    private void previous(Bundle bundle) {
        this.alexaClient.previous(getClient(bundle));
    }

    private void registerAlertsListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.registerAlertsListener(client, new AlertsMessageSender(Bundles.getBinder(bundle, AlexaServicesArgumentKey.ALERTS_LISTENER), client));
    }

    private void registerAlexaAudioPlaybackListener(Bundle bundle) {
        this.alexaClient.registerAlexaAudioPlaybackListener(getClient(bundle), AlexaAudioPlaybackListenerProxy.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.AUDIO_PLAYBACK_LISTENER_PROXY)));
    }

    private void registerAlexaPlayerInfoCardListener(Bundle bundle) {
        this.alexaClient.registerAlexaPlayerInfoCardListener(getClient(bundle), AlexaPlayerInfoCardListenerProxy.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.PLAYER_INFO_CARD_LISTENER_PROXY)));
    }

    private void registerAlexaSettingsListener(Bundle bundle) {
        this.alexaClient.registerAlexaSettingsListener(getClient(bundle), AlexaSettingsListenerProxy.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.SETTINGS_LISTENER_PROXY)));
    }

    private void registerAlexaStateListener(Bundle bundle) {
        this.alexaClient.registerAlexaStateListener(getClient(bundle), AlexaStateListenerProxy.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.STATE_LISTENER_PROXY)));
    }

    private void registerAlexaTemplateCardListener(Bundle bundle) {
        this.alexaClient.registerAlexaTemplateCardListener(getClient(bundle), AlexaTemplateCardListenerProxy.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.TEMPLATE_CARD_LISTENER_PROXY)));
    }

    private void registerAlexaUserSpeechListener(Bundle bundle) {
        this.alexaClient.registerAlexaUserSpeechListener(getClient(bundle), AlexaUserSpeechListenerProxy.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.USER_SPEECH_LISTENER_PROXY)));
    }

    private void registerContextProvider(Bundle bundle) throws RemoteException {
        this.alexaClient.registerContextProvider(getClient(bundle), AlexaContextProviderProxy.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.CONTEXT_PROVIDER_PROXY)));
    }

    private void registerForceDisconnectListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.registerForceDisconnectListener(client, new ForceDisconnectMessageSender(Bundles.getBinder(bundle, AlexaServicesArgumentKey.FORCE_DISCONNECT_LISTENER), client));
    }

    private void registerMetricsListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.registerMetricsListener(client, new MetricsMessageSender(Bundles.getBinder(bundle, AlexaServicesArgumentKey.METRICS_LISTENER), client));
    }

    private void registerUserPerceivedLatencyListener(Bundle bundle) {
        this.alexaClient.registerUserPerceivedLatencyListener(getClient(bundle), new bNQ(UserPerceivedLatencyListenerProxy.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.USER_PERCEIVED_LATENCY_LISTENER_PROXY))));
    }

    private void scheduleInteraction(Bundle bundle) {
        this.alexaClient.scheduleInteraction(getClient(bundle), AlexaAudioInteractionProxy.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.AUDIO_INTERACTION_PROXY)));
    }

    private void sendAlexaEvent(Bundle bundle) {
        boolean z = Bundles.getBoolean(bundle, AlexaServicesArgumentKey.REQUIRES_CONTEXTS);
        this.alexaClient.sendAlexaEvent(getClient(bundle), (AlexaEvent) Bundles.getParcelable(bundle, AlexaServicesArgumentKey.ALEXA_EVENT, AlexaEvent.class), z);
    }

    private void setLocale(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        String string = Bundles.getString(bundle, AlexaServicesArgumentKey.CURRENT_LOCALE);
        this.alexaClient.setLocales(client, Collections.singletonList(string), Bundles.getBoolean(bundle, AlexaServicesArgumentKey.FORCE_LOCALE_UPDATE));
    }

    private void startListening(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        Bundle optionalBundle = Bundles.getOptionalBundle(bundle, AlexaServicesArgumentKey.WAKEWORD_DIALOG_EXTRAS);
        this.alexaClient.startListening(client, optionalBundle != null ? new DialogExtras(optionalBundle) : null);
    }

    private void stop(Bundle bundle) {
        this.alexaClient.stop(getClient(bundle));
    }

    private void stopListening(Bundle bundle) {
        this.alexaClient.stopListening(getClient(bundle));
    }

    private void stopRecognizing(Bundle bundle) {
        this.alexaClient.stopRecognizing(getClient(bundle));
    }

    private void temporarilySuppressAllAudio(Bundle bundle) {
        this.alexaClient.temporarilySuppressAllAudio(getClient(bundle));
    }

    private void unscheduleInteraction(Bundle bundle) {
        this.alexaClient.unscheduleInteraction(getClient(bundle), AlexaAudioInteractionProxy.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.AUDIO_INTERACTION_PROXY)));
    }

    private void updateWakeWordState(Bundle bundle) {
        this.alexaClient.updateWakeWordDetectionState(getClient(bundle), Bundles.getBoolean(bundle, AlexaServicesArgumentKey.IS_WAKE_WORD_ALLOWED));
    }

    public void continueDialog(Bundle bundle) throws RemoteException {
        this.legacyUserSpeechProviders.continueDialog(AlexaDialogControllerProxy.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.DIALOG_CONTROLLER_PROXY)), (ParcelFileDescriptor) Bundles.getParcelable(bundle, AlexaServicesArgumentKey.AUDIO_FILE_DESCRIPTOR, ParcelFileDescriptor.class));
    }

    public void deregisterAlexaCardRendererListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        AlexaCardRendererListenerProxy asInterface = AlexaCardRendererListenerProxy.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.CARD_RENDERER_LISTENER_PROXY));
        if (asInterface == null) {
            Log.e(TAG, "Card Renderer listener is null");
            return;
        }
        this.alexaClient.deregisterAlexaCardRendererListener(client, new GvA(asInterface));
    }

    public ExtendedClient getClient(Bundle bundle) {
        ExtendedClient client = Bundles.getClient(bundle);
        return new ImmutableClient(client.clientRole, client.id, client.packageName, client.version, client.subclients, client.activeSubClientId, client.debugTag);
    }

    public void registerAlexaCardRendererListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        AlexaCardRendererListenerProxy asInterface = AlexaCardRendererListenerProxy.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.CARD_RENDERER_LISTENER_PROXY));
        if (asInterface == null) {
            Log.e(TAG, "Card Renderer listener is null");
            return;
        }
        this.alexaClient.registerAlexaCardRendererListener(client, new GvA(asInterface));
    }

    public void startDialog(Bundle bundle) throws RemoteException {
        ExtendedClient client = getClient(bundle);
        DialogExtras dialogExtras = new DialogExtras(Bundles.getOptionalBundle(bundle, AlexaServicesArgumentKey.DIALOG_EXTRAS));
        LegacyUserSpeechProvider createLegacyUserSpeechProvider = createLegacyUserSpeechProvider(AlexaDialogControllerProxy.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.DIALOG_CONTROLLER_PROXY)), (AlexaAudioMetadata) Bundles.getParcelable(bundle, AlexaServicesArgumentKey.AUDIO_META_DATA, AlexaAudioMetadata.class), (ParcelFileDescriptor) Bundles.getParcelable(bundle, AlexaServicesArgumentKey.AUDIO_FILE_DESCRIPTOR, ParcelFileDescriptor.class), dialogExtras);
        this.alexaClient.registerUserSpeechProvider(client, createLegacyUserSpeechProvider, createLegacyUserSpeechProvider.getUserSpeechProviderMetadata());
        this.alexaClient.requestDialog(client, createLegacyUserSpeechProvider, AlexaDialogRequest.builder().setInvocationType(dialogExtras.getInvocationType()).setLaunchType(dialogExtras.getLaunchType()).build());
    }

    public void startRecognizing(Bundle bundle) throws RemoteException {
        this.alexaClient.startRecognizing(getClient(bundle), DialogExtras.zZm);
    }

    public void stopDialogTurn(Bundle bundle) throws RemoteException {
        this.legacyUserSpeechProviders.stopDialog(AlexaDialogControllerProxy.Stub.asInterface(Bundles.getBinder(bundle, AlexaServicesArgumentKey.DIALOG_CONTROLLER_PROXY)));
    }

    @VisibleForTesting
    public AlexaServicesV1(AlexaClient alexaClient, UYN uyn, LegacyUserSpeechProviders legacyUserSpeechProviders) {
        this.alexaClient = alexaClient;
        this.errorReporter = uyn;
        this.legacyUserSpeechProviders = legacyUserSpeechProviders;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: getMessageType */
    public AlexaServicesMessageType mo845getMessageType(Message message) {
        try {
            return AlexaServicesMessageType.fromOrdinal(message.what);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Unrecognized message type", e);
            return AlexaServicesMessageType.UNKNOWN;
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(AlexaServicesMessageType alexaServicesMessageType, Bundle bundle, @Nullable Messenger messenger) {
        try {
            ExtendedClient client = getClient(bundle);
            int ordinal = alexaServicesMessageType.ordinal();
            if (ordinal == 47) {
                registerAlertsListener(bundle);
            } else if (ordinal == 48) {
                deregisterAlertsListener(bundle);
            } else if (ordinal == 51) {
                registerMetricsListener(bundle);
            } else if (ordinal != 52) {
                switch (ordinal) {
                    case 1:
                        muteMicrophone(bundle);
                        break;
                    case 2:
                        this.alexaClient.cancelUserInteraction(getClient(bundle));
                        break;
                    case 3:
                        startRecognizing(bundle);
                        break;
                    case 4:
                        this.alexaClient.stopRecognizing(getClient(bundle));
                        break;
                    case 5:
                        startDialog(bundle);
                        break;
                    case 6:
                        continueDialog(bundle);
                        break;
                    case 7:
                        stopDialogTurn(bundle);
                        break;
                    case 8:
                        registerAlexaStateListener(bundle);
                        break;
                    case 9:
                        deregisterAlexaStateListener(bundle);
                        break;
                    case 10:
                        registerAlexaUserSpeechListener(bundle);
                        break;
                    case 11:
                        deregisterAlexaUserSpeechListener(bundle);
                        break;
                    case 12:
                        this.alexaClient.previous(getClient(bundle));
                        break;
                    case 13:
                        this.alexaClient.play(getClient(bundle));
                        break;
                    case 14:
                        this.alexaClient.pause(getClient(bundle));
                        break;
                    case 15:
                        this.alexaClient.stop(getClient(bundle));
                        break;
                    case 16:
                        this.alexaClient.next(getClient(bundle));
                        break;
                    case 17:
                        this.alexaClient.temporarilySuppressAllAudio(getClient(bundle));
                        break;
                    case 18:
                        registerAlexaAudioPlaybackListener(bundle);
                        break;
                    case 19:
                        deregisterAlexaAudioPlaybackListener(bundle);
                        break;
                    case 20:
                        setLocale(bundle);
                        break;
                    case 21:
                        String locale = this.alexaClient.getLocale(getClient(bundle));
                        if (locale == null) {
                            locale = "";
                        }
                        reply(messenger, alexaServicesMessageType, new CurrentLocaleMessagePayload(client, locale).getBundle());
                        break;
                    case 22:
                        reply(messenger, alexaServicesMessageType, new SupportedLocalesMessagePayload(client, this.alexaClient.getSupportedLocales(getClient(bundle))).getBundle());
                        break;
                    case 23:
                        registerAlexaSettingsListener(bundle);
                        break;
                    case 24:
                        deregisterAlexaSettingsListener(bundle);
                        break;
                    case 25:
                        registerAlexaTemplateCardListener(bundle);
                        break;
                    case 26:
                        deregisterAlexaTemplateCardListener(bundle);
                        break;
                    case 27:
                        registerAlexaPlayerInfoCardListener(bundle);
                        break;
                    case 28:
                        deregisterAlexaPlayerInfoCardListener(bundle);
                        break;
                    case 29:
                        registerAlexaCardRendererListener(bundle);
                        break;
                    case 30:
                        deregisterAlexaCardRendererListener(bundle);
                        break;
                    case 31:
                        this.alexaClient.onClientDisconnect(getClient(bundle));
                        break;
                    case 32:
                        registerForceDisconnectListener(bundle);
                        break;
                    case 33:
                        deregisterForceDisconnectListener(bundle);
                        break;
                    default:
                        switch (ordinal) {
                            case 38:
                                registerContextProvider(bundle);
                                break;
                            case 39:
                                deregisterContextProvider(bundle);
                                break;
                            case 40:
                                sendAlexaEvent(bundle);
                                break;
                            case 41:
                                scheduleInteraction(bundle);
                                break;
                            case 42:
                                unscheduleInteraction(bundle);
                                break;
                            case 43:
                                reply(messenger, alexaServicesMessageType, new IsUserLoggedInMessagePayload(client, this.alexaClient.isUserLoggedIn(getClient(bundle))).getBundle());
                                break;
                            case 44:
                                registerUserPerceivedLatencyListener(bundle);
                                break;
                            case 45:
                                deregisterUserPerceivedLatencyListener(bundle);
                                break;
                            default:
                                switch (ordinal) {
                                    case 61:
                                        startListening(bundle);
                                        break;
                                    case 62:
                                        this.alexaClient.stopListening(getClient(bundle));
                                        break;
                                    case 63:
                                        updateWakeWordState(bundle);
                                        break;
                                    case 64:
                                        reply(messenger, alexaServicesMessageType, new IsDetectingWakeWordMessagePayload(client, this.alexaClient.isDetectingWakeWord(getClient(bundle))).getBundle());
                                        break;
                                    default:
                                        String str = TAG;
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("Unsupported message ");
                                        sb.append(alexaServicesMessageType);
                                        Log.w(str, sb.toString());
                                        break;
                                }
                        }
                }
            } else {
                deregisterMetricsListener(bundle);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to handle incoming message! ", e);
            this.errorReporter.zZm.notifyHandledException(new UYN.zZm(e));
        }
    }
}
