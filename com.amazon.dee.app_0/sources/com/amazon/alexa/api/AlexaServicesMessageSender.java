package com.amazon.alexa.api;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.api.AlexaCapabilityAgentRegistration;
import com.amazon.alexa.api.AlexaDialogExtras;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.api.messages.messagesender.AlexaBidirectionalMessageSender;
import com.amazon.alexa.client.annotations.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes6.dex */
public class AlexaServicesMessageSender extends AlexaBidirectionalMessageSender<AlexaServicesMessageType> {
    private static final String TAG = "AlexaServicesMessageSender";
    private static final long WAIT_TIMEOUT_MILLISECONDS_LOGIN = 5000;
    private at<List<String>> alexaLocalesTask;
    private at<AlexaReadyState> getReadyStateTask;
    private at<Boolean> isDetectingWakeWordTask;
    private at<Boolean> isEndpointSoundEnabledResult;
    private at<Boolean> isUserLoggedInTask;
    private at<Boolean> isWakeSoundEnabledResult;
    private at<Set<List<String>>> supportedLocaleCombinationsTask;
    private at<List<String>> supportedLocalesTask;

    /* renamed from: com.amazon.alexa.api.AlexaServicesMessageSender$9  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType = new int[AlexaServicesMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.GET_LOCALES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.GET_SUPPORTED_LOCALES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.GET_SUPPORTED_LOCALE_COMBINATIONS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.IS_USER_LOGGED_IN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.IS_DETECTING_WAKE_WORD.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.GET_READY_STATE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.IS_WAKE_SOUND_ENABLED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.IS_ENDPOINT_SOUND_ENABLED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class AlertsListenerMessagePayload extends BaseMessagePayload {
        AlertsListenerMessagePayload(ExtendedClient extendedClient, MessageReceiver<e> messageReceiver) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.ALERTS_LISTENER, messageReceiver.getMessenger().getBinder());
        }
    }

    /* loaded from: classes6.dex */
    private static class AlexaAttentionSystemListenerMessagePayload extends BaseMessagePayload {
        AlexaAttentionSystemListenerMessagePayload(ExtendedClient extendedClient, MessageReceiver<ApiType_AttentionSystemListenerMessageType> messageReceiver) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.ATTENTION_SYSTEM_LISTENER, messageReceiver.getMessenger().getBinder());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class AlexaAudioPlaybackStatusListenerMessagePayload extends BaseMessagePayload {
        AlexaAudioPlaybackStatusListenerMessagePayload(ExtendedClient extendedClient, MessageReceiver<AlexaAudioPlaybackStatusListenerMessageType> messageReceiver) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.AUDIO_PLAYBACK_STATUS_LISTENER, messageReceiver.getMessenger().getBinder());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class AlexaEventMessagePayload extends BaseMessagePayload {
        AlexaEventMessagePayload(ExtendedClient extendedClient, AlexaEvent alexaEvent, List<AlexaContext> list) {
            this(extendedClient, alexaEvent, true);
            addParcelableArray(AlexaServicesArgumentKey.ALEXA_CONTEXTS, list);
        }

        AlexaEventMessagePayload(ExtendedClient extendedClient, AlexaEvent alexaEvent, boolean z) {
            this(extendedClient, alexaEvent, z, null);
        }

        AlexaEventMessagePayload(ExtendedClient extendedClient, AlexaEvent alexaEvent, boolean z, @Nullable Bundle bundle) {
            super(extendedClient, bundle);
            add(AlexaServicesArgumentKey.ALEXA_EVENT, alexaEvent);
            add(AlexaServicesArgumentKey.REQUIRES_CONTEXTS, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class AlexaLocaleMessagePayload extends BaseMessagePayload {
        AlexaLocaleMessagePayload(ExtendedClient extendedClient, List<String> list, boolean z) {
            this(extendedClient, list, z, null);
        }

        AlexaLocaleMessagePayload(ExtendedClient extendedClient, List<String> list, boolean z, @Nullable Bundle bundle) {
            super(extendedClient, bundle);
            add(AlexaServicesArgumentKey.CURRENT_LOCALES, list);
            add(AlexaServicesArgumentKey.FORCE_LOCALE_UPDATE, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class AlexaStateListenerMessagePayload extends BaseMessagePayload {
        AlexaStateListenerMessagePayload(ExtendedClient extendedClient, AlexaStateListenerProxy alexaStateListenerProxy) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.STATE_LISTENER_PROXY, alexaStateListenerProxy.asBinder());
        }
    }

    /* loaded from: classes6.dex */
    private static class ArtifactDownloadListenerPayload extends BaseMessagePayload {
        ArtifactDownloadListenerPayload(ExtendedClient extendedClient, MessageReceiver<ApiType_ArtifactDownloadListenerMessageType> messageReceiver) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.ARTIFACT_DOWNLOAD_LISTENER, messageReceiver.getMessenger().getBinder());
        }
    }

    /* loaded from: classes6.dex */
    private static class AttentionSystemSettingsListenerPayload extends BaseMessagePayload {
        AttentionSystemSettingsListenerPayload(ExtendedClient extendedClient, MessageReceiver<AlexaAttentionSystemSettingsMessageType> messageReceiver) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.ATTENTION_SYSTEM_SETTING_LISTENER, messageReceiver.getMessenger().getBinder());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class AudioInteractionMessagePayload extends BaseMessagePayload {
        AudioInteractionMessagePayload(ExtendedClient extendedClient, AlexaAudioInteractionProxy alexaAudioInteractionProxy) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.AUDIO_INTERACTION_PROXY, alexaAudioInteractionProxy.asBinder());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class AudioPlaybackListenerMessagePayload extends BaseMessagePayload {
        AudioPlaybackListenerMessagePayload(ExtendedClient extendedClient, AlexaAudioPlaybackListenerProxy alexaAudioPlaybackListenerProxy) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.AUDIO_PLAYBACK_LISTENER_PROXY, alexaAudioPlaybackListenerProxy.asBinder());
        }
    }

    /* loaded from: classes6.dex */
    private static class CacheContextsPayload extends BaseMessagePayload {
        CacheContextsPayload(ExtendedClient extendedClient, Bundle bundle) {
            super(extendedClient);
            add((Bundles.Key) AlexaServicesArgumentKey.ALEXA_CONTEXTS, bundle);
        }
    }

    /* loaded from: classes6.dex */
    static class CaptionListenerMessagePayload extends BaseMessagePayload {
        CaptionListenerMessagePayload(ExtendedClient extendedClient, MessageReceiver<ApiType_CaptionListenerMessageType> messageReceiver) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.CAPTION_LISTENER, messageReceiver.getMessenger().getBinder());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class CardListenerMessagePayload extends BaseMessagePayload {
        CardListenerMessagePayload(ExtendedClient extendedClient, MessageReceiver<AlexaCardListenerMessageType> messageReceiver) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.CARD_LISTENER, messageReceiver.getMessenger().getBinder());
        }
    }

    /* loaded from: classes6.dex */
    private static class ClearContextCachePayload extends BaseMessagePayload {
        ClearContextCachePayload(ExtendedClient extendedClient, @Nullable Bundle bundle) {
            super(extendedClient);
            if (bundle != null) {
                add((Bundles.Key) AlexaServicesArgumentKey.NAMESPACES, bundle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class ClientClientConnectionMessagePayload extends ClientConnectionControllerMessagePayload {
        ClientClientConnectionMessagePayload(ExtendedClient extendedClient, MessageReceiver<ClientConnectionControllerMessageType> messageReceiver, boolean z, boolean z2, @Nullable PendingIntent pendingIntent, boolean z3) {
            super(extendedClient, messageReceiver);
            add(AlexaServicesArgumentKey.CLIENT_REQUIRES_FOREGROUND, z);
            add(AlexaServicesArgumentKey.ALLOWS_BACKGROUND_ACTIVITY_STARTS, z2);
            add(AlexaServicesArgumentKey.FORCE_CAPABILITIES_REFRESH, z3);
            if (pendingIntent != null) {
                add(AlexaServicesArgumentKey.NOTIFICATION_PENDING_INTENT, pendingIntent);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class ClientConnectionControllerMessagePayload extends BaseMessagePayload {
        ClientConnectionControllerMessagePayload(ExtendedClient extendedClient, MessageReceiver<ClientConnectionControllerMessageType> messageReceiver) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.CLIENT_CONNECTION_CONTROLLER, messageReceiver.getMessenger().getBinder());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class ContextProviderMessagePayload extends BaseMessagePayload {
        ContextProviderMessagePayload(ExtendedClient extendedClient, AlexaContextProviderProxy alexaContextProviderProxy) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.CONTEXT_PROVIDER_PROXY, alexaContextProviderProxy.asBinder());
        }
    }

    /* loaded from: classes6.dex */
    private static class ContextsProviderMessagePayload extends BaseMessagePayload {
        ContextsProviderMessagePayload(ExtendedClient extendedClient, MessageReceiver<AlexaContextsProviderMessageType> messageReceiver) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.CONTEXTS_PROVIDER, messageReceiver.getMessenger().getBinder());
        }
    }

    /* loaded from: classes6.dex */
    private static class DriveModeEnabledMessagePayload extends BaseMessagePayload {
        DriveModeEnabledMessagePayload(ExtendedClient extendedClient, boolean z) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.IS_DRIVE_MODE_ENABLED, z);
        }
    }

    /* loaded from: classes6.dex */
    private static class DriveModeListenerMessagePayload extends BaseMessagePayload {
        DriveModeListenerMessagePayload(ExtendedClient extendedClient, MessageReceiver<ApiType_DriveModeListenerMessageType> messageReceiver) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.DRIVE_MODE_LISTENER, messageReceiver.getMessenger().getBinder());
        }
    }

    /* loaded from: classes6.dex */
    private static class DriveModeStateMessagePayload extends BaseMessagePayload {
        DriveModeStateMessagePayload(ExtendedClient extendedClient, DriveModeState driveModeState) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.DRIVE_MODE_STATE, (driveModeState == null ? DriveModeState.NONE : driveModeState).name());
        }
    }

    /* loaded from: classes6.dex */
    private static class DriveModeThemeMessagePayload extends BaseMessagePayload {
        DriveModeThemeMessagePayload(ExtendedClient extendedClient, boolean z) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.IS_DRIVE_MODE_DARK_THEME, z);
        }
    }

    /* loaded from: classes6.dex */
    private static class ExpectTextListenerMessagePayload extends BaseMessagePayload {
        public ExpectTextListenerMessagePayload(ExtendedClient extendedClient, MessageReceiver<ExpectTextMessageType> messageReceiver) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.EXPECT_TEXT_LISTENER, messageReceiver.getMessenger().getBinder());
        }
    }

    /* loaded from: classes6.dex */
    private static class ExternalCapabilityAgentStatusPayload extends BaseMessagePayload {
        ExternalCapabilityAgentStatusPayload(ExtendedClient extendedClient, AlexaCapabilityAgentRegistration alexaCapabilityAgentRegistration) {
            super(extendedClient);
            add((Bundles.Key) AlexaServicesArgumentKey.EXTERNAL_CAPABILITY_AGENT_STATUS, new AlexaCapabilityAgentRegistration.Adapter().toBundle(alexaCapabilityAgentRegistration));
        }
    }

    /* loaded from: classes6.dex */
    private static class LocaleListenerPayload extends BaseMessagePayload {
        LocaleListenerPayload(ExtendedClient extendedClient, MessageReceiver<ax> messageReceiver) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.LOCALES_LISTENER, messageReceiver.getMessenger().getBinder());
        }
    }

    /* loaded from: classes6.dex */
    private static class MediaPlaybackContentIntentMessagePayload extends BaseMessagePayload {
        public MediaPlaybackContentIntentMessagePayload(ExtendedClient extendedClient, PendingIntent pendingIntent) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.MEDIA_NOTIFICATION_CONTENT_INTENT, pendingIntent);
        }
    }

    /* loaded from: classes6.dex */
    private static class MediaPlaybackListenerMessagePayload extends BaseMessagePayload {
        public MediaPlaybackListenerMessagePayload(ExtendedClient extendedClient, MessageReceiver<ApiType_MediaPlaybackListenerMessageType> messageReceiver) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.MEDIA_PLAYBACK_CONTROLLER_LISTENER, messageReceiver.getMessenger().getBinder());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class MetricsListenerPayload extends BaseMessagePayload {
        MetricsListenerPayload(ExtendedClient extendedClient, MessageReceiver<be> messageReceiver) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.METRICS_LISTENER, messageReceiver.getMessenger().getBinder());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class MuteMicMessagePayload extends BaseMessagePayload {
        MuteMicMessagePayload(ExtendedClient extendedClient, boolean z) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.IS_MUTED, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class PlayerInfoCardListenerMessagePayload extends BaseMessagePayload {
        PlayerInfoCardListenerMessagePayload(ExtendedClient extendedClient, AlexaPlayerInfoCardListenerProxy alexaPlayerInfoCardListenerProxy) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.PLAYER_INFO_CARD_LISTENER_PROXY, alexaPlayerInfoCardListenerProxy.asBinder());
        }
    }

    /* loaded from: classes6.dex */
    private static class ReadinessListenerMessagePayload extends BaseMessagePayload {
        ReadinessListenerMessagePayload(ExtendedClient extendedClient, MessageReceiver<AlexaReadinessMessageType> messageReceiver) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.READINESS_LISTENER, messageReceiver.getMessenger().getBinder());
        }
    }

    /* loaded from: classes6.dex */
    private static class RequestDialogMessagePayload extends BaseMessagePayload {
        RequestDialogMessagePayload(ExtendedClient extendedClient, MessageReceiver<AlexaUserSpeechProviderMessageType> messageReceiver, Bundle bundle) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.USER_SPEECH_PROVIDER, messageReceiver.getMessenger().getBinder());
            add((Bundles.Key) AlexaServicesArgumentKey.DIALOG_REQUEST, bundle);
        }
    }

    /* loaded from: classes6.dex */
    protected class ResponseProcessor extends MessageProcessor<AlexaServicesMessageType> {
        protected ResponseProcessor() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.api.messages.MessageProcessor
        /* renamed from: getMessageType */
        public AlexaServicesMessageType mo845getMessageType(Message message) {
            try {
                return AlexaServicesMessageType.fromOrdinal(message.what);
            } catch (IllegalStateException e) {
                Log.e(AlexaServicesMessageSender.TAG, "Unrecognized message type, ", e);
                return AlexaServicesMessageType.UNKNOWN;
            }
        }

        @Override // com.amazon.alexa.api.messages.MessageProcessor
        public void processMessage(AlexaServicesMessageType alexaServicesMessageType, Bundle bundle, @Nullable Messenger messenger) {
            String str = AlexaServicesMessageSender.TAG;
            Log.i(str, "received response " + alexaServicesMessageType);
            int ordinal = alexaServicesMessageType.ordinal();
            if (ordinal == 22) {
                AlexaServicesMessageSender.this.onSupportedLocales(Bundles.getStringList(bundle, AlexaServicesArgumentKey.SUPPORTED_LOCALES));
            } else if (ordinal == 43) {
                AlexaServicesMessageSender.this.onIsUserLoggedIn(Bundles.getBoolean(bundle, AlexaServicesArgumentKey.IS_USER_LOGGED_IN));
            } else if (ordinal == 64) {
                AlexaServicesMessageSender.this.onIsDetectingWakeWord(Bundles.getBoolean(bundle, AlexaServicesArgumentKey.IS_DETECTING_WAKE_WORD));
            } else if (ordinal == 67) {
                AlexaServicesMessageSender.this.onGetReadyState((AlexaReadyState) BundleTransformer.getDefaultInstance().getFromBundle(Bundles.getBundle(bundle, AlexaServicesArgumentKey.GET_READY_STATE), AlexaReadyState.class));
            } else if (ordinal == 71) {
                AlexaServicesMessageSender.this.onIsWakeSoundEnabledResponse(Bundles.getBoolean(bundle, AlexaServicesArgumentKey.IS_WAKE_SOUND_ENABLED));
            } else if (ordinal == 73) {
                AlexaServicesMessageSender.this.onIsEndpointSoundEnabledResponse(Bundles.getBoolean(bundle, AlexaServicesArgumentKey.IS_ENDPOINT_SOUND_ENABLED));
            } else if (ordinal == 108) {
                AlexaServicesMessageSender.this.onAlexaLocale(Bundles.getStringList(bundle, AlexaServicesArgumentKey.CURRENT_LOCALES));
            } else if (ordinal != 109) {
                String str2 = AlexaServicesMessageSender.TAG;
                Log.w(str2, "Unsupported message " + alexaServicesMessageType);
            } else {
                List<au> parcelableList = Bundles.getParcelableList(bundle, AlexaServicesArgumentKey.SUPPORTED_LOCALE_COMBINATIONS, au.class);
                HashSet hashSet = new HashSet(parcelableList.size());
                for (au auVar : parcelableList) {
                    hashSet.add(auVar.a());
                }
                AlexaServicesMessageSender.this.onSupportedLocaleCombinations(hashSet);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class SendTextMessagePayload extends BaseMessagePayload {
        public static final String DEFAULT_INVOCATION_TYPE = "AlexaApp.TypeWithAlexa";

        SendTextMessagePayload(ExtendedClient extendedClient, String str, TextAlexaDialogExtras textAlexaDialogExtras) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.SEND_TEXT_MESSAGE, str);
            AlexaDialogExtras.Builder invocationType = AlexaDialogExtras.builder().setLaunchType(LaunchType.TEXT).suppressSpeechResponse(true).setInvocationType(DEFAULT_INVOCATION_TYPE);
            if (textAlexaDialogExtras != null) {
                invocationType.suppressSpeechResponse(textAlexaDialogExtras.suppressSpeechResponse());
                String invocationType2 = textAlexaDialogExtras.getInvocationType();
                if (!TextUtils.isEmpty(invocationType2)) {
                    invocationType.setInvocationType(invocationType2);
                }
            }
            add((Bundles.Key) AlexaServicesArgumentKey.DIALOG_EXTRAS, invocationType.build().getBundle());
        }
    }

    /* loaded from: classes6.dex */
    private static class SetBaseURLsPayload extends BaseMessagePayload {
        SetBaseURLsPayload(ExtendedClient extendedClient, AlexaBaseURLs alexaBaseURLs) {
            super(extendedClient);
            add((Bundles.Key) AlexaServicesArgumentKey.BASE_URLS, alexaBaseURLs.getBundle());
        }
    }

    /* loaded from: classes6.dex */
    private static class SetContextCachingEnabledPayload extends BaseMessagePayload {
        SetContextCachingEnabledPayload(ExtendedClient extendedClient, boolean z, @Nullable Bundle bundle) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.ENABLE_CONTEXT_CACHING, z);
            if (bundle != null) {
                add((Bundles.Key) AlexaServicesArgumentKey.NAMESPACES, bundle);
            }
        }
    }

    /* loaded from: classes6.dex */
    private static class SetEndpointSoundEnabledMessagePayload extends BaseMessagePayload {
        SetEndpointSoundEnabledMessagePayload(ExtendedClient extendedClient, boolean z) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.IS_ENDPOINT_SOUND_ENABLED, z);
        }
    }

    /* loaded from: classes6.dex */
    private static class SetWakeSoundEnabledMessagePayload extends BaseMessagePayload {
        SetWakeSoundEnabledMessagePayload(ExtendedClient extendedClient, boolean z) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.IS_WAKE_SOUND_ENABLED, z);
        }
    }

    /* loaded from: classes6.dex */
    private static class SetWakeWordsMessagePayload extends BaseMessagePayload {
        public SetWakeWordsMessagePayload(ExtendedClient extendedClient, List<String> list) {
            this(extendedClient, list, null);
        }

        public SetWakeWordsMessagePayload(ExtendedClient extendedClient, List<String> list, @Nullable Bundle bundle) {
            super(extendedClient, bundle);
            add(AlexaServicesArgumentKey.WAKE_WORDS, list);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class SettingsListenerMessagePayload extends BaseMessagePayload {
        SettingsListenerMessagePayload(ExtendedClient extendedClient, AlexaSettingsListenerProxy alexaSettingsListenerProxy) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.SETTINGS_LISTENER_PROXY, alexaSettingsListenerProxy.asBinder());
        }
    }

    /* loaded from: classes6.dex */
    private static class StartListeningMessagePayload extends BaseMessagePayload {
        public StartListeningMessagePayload(ExtendedClient extendedClient, @Nullable AlexaDialogExtras alexaDialogExtras) {
            super(extendedClient);
            if (alexaDialogExtras != null) {
                add((Bundles.Key) AlexaServicesArgumentKey.WAKEWORD_DIALOG_EXTRAS, alexaDialogExtras.getBundle());
            }
        }
    }

    /* loaded from: classes6.dex */
    static class StartRecognizingMessagePayload extends BaseMessagePayload {
        StartRecognizingMessagePayload(ExtendedClient extendedClient) {
            super(extendedClient);
        }

        StartRecognizingMessagePayload(ExtendedClient extendedClient, @Nullable Bundle bundle) {
            super(extendedClient);
            if (bundle != null) {
                add((Bundles.Key) AlexaServicesArgumentKey.DIALOG_EXTRAS, bundle);
            }
        }
    }

    /* loaded from: classes6.dex */
    private static class SupportedLocalesListenersPayload extends BaseMessagePayload {
        SupportedLocalesListenersPayload(ExtendedClient extendedClient, MessageReceiver<bs> messageReceiver) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.SUPPORTED_LOCALES_LISTENER, messageReceiver.getMessenger().getBinder());
        }
    }

    /* loaded from: classes6.dex */
    private static class TextResponseListenerMessagePayload extends BaseMessagePayload {
        public TextResponseListenerMessagePayload(ExtendedClient extendedClient, MessageReceiver<TextResponseListenerMessageType> messageReceiver) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.TEXT_RESPONSE_LISTENER, messageReceiver.getMessenger().getBinder());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class UplListenerPayload extends BaseMessagePayload {
        UplListenerPayload(ExtendedClient extendedClient, UserPerceivedLatencyListenerProxy userPerceivedLatencyListenerProxy) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.USER_PERCEIVED_LATENCY_LISTENER_PROXY, userPerceivedLatencyListenerProxy.asBinder());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class UserSpeechMessagePayload extends BaseMessagePayload {
        UserSpeechMessagePayload(ExtendedClient extendedClient, AlexaUserSpeechListenerProxy alexaUserSpeechListenerProxy) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.USER_SPEECH_LISTENER_PROXY, alexaUserSpeechListenerProxy.asBinder());
        }
    }

    /* loaded from: classes6.dex */
    private static class UserSpeechProviderMessagePayload extends BaseMessagePayload {
        UserSpeechProviderMessagePayload(ExtendedClient extendedClient, MessageReceiver<AlexaUserSpeechProviderMessageType> messageReceiver) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.USER_SPEECH_PROVIDER, messageReceiver.getMessenger().getBinder());
        }

        UserSpeechProviderMessagePayload(ExtendedClient extendedClient, MessageReceiver<AlexaUserSpeechProviderMessageType> messageReceiver, Bundle bundle) {
            this(extendedClient, messageReceiver);
            add((Bundles.Key) AlexaServicesArgumentKey.USER_SPEECH_PROVIDER_METADATA, bundle);
        }
    }

    /* loaded from: classes6.dex */
    private static class VisualTaskMessagePayload extends BaseMessagePayload {
        VisualTaskMessagePayload(ExtendedClient extendedClient, MessageReceiver<ca> messageReceiver) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.VISUAL_TASK, messageReceiver.getMessenger().getBinder());
        }
    }

    /* loaded from: classes6.dex */
    private static class WakeWordAllowedMessagePayload extends BaseMessagePayload {
        WakeWordAllowedMessagePayload(ExtendedClient extendedClient, boolean z) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.IS_WAKE_WORD_ALLOWED, z);
        }
    }

    /* loaded from: classes6.dex */
    private static class WakeWordListenerMessagePayload extends BaseMessagePayload {
        public WakeWordListenerMessagePayload(ExtendedClient extendedClient, MessageReceiver<AlexaWakeWordListenerMessageType> messageReceiver) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.WAKEWORD_LISTENER, messageReceiver.getMessenger().getBinder());
        }
    }

    public AlexaServicesMessageSender(IBinder iBinder, MessageReceiversManager messageReceiversManager) {
        super(iBinder, messageReceiversManager);
    }

    public void cacheContexts(ExtendedClient extendedClient, Bundle bundle) throws RemoteException {
        sendMessage(AlexaServicesMessageType.CACHE_CONTEXTS, new CacheContextsPayload(extendedClient, bundle).getBundle());
    }

    public void cancelUserInteraction(ExtendedClient extendedClient) throws RemoteException {
        sendMessage(AlexaServicesMessageType.CANCEL_USER_INTERACTION, new BaseMessagePayload(extendedClient).getBundle());
    }

    public void clearContextCache(ExtendedClient extendedClient, @Nullable Bundle bundle) throws RemoteException {
        sendMessage(AlexaServicesMessageType.CLEAR_CONTEXT_CACHE, new ClearContextCachePayload(extendedClient, bundle).getBundle());
    }

    @Override // com.amazon.alexa.api.messages.messagesender.AlexaBidirectionalMessageSender
    protected MessageProcessor<AlexaServicesMessageType> createResponseProcessor() {
        return new ResponseProcessor();
    }

    public void deregisterAlertsListener(ExtendedClient extendedClient, MessageReceiver<e> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_ALERTS_LISTENER, new AlertsListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void deregisterAlexaAttentionSystemSettingsListener(ExtendedClient extendedClient, MessageReceiver<AlexaAttentionSystemSettingsMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_ATTENTION_SYSTEM_SETTINGS_LISTENER, new AttentionSystemSettingsListenerPayload(extendedClient, messageReceiver).getBundle());
    }

    public void deregisterAlexaAudioPlaybackListener(ExtendedClient extendedClient, AlexaAudioPlaybackListenerProxy alexaAudioPlaybackListenerProxy) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_AUDIO_PLAYBACK_LISTENER, new AudioPlaybackListenerMessagePayload(extendedClient, alexaAudioPlaybackListenerProxy).getBundle());
    }

    public void deregisterAlexaAudioPlaybackStatusListener(ExtendedClient extendedClient, MessageReceiver<AlexaAudioPlaybackStatusListenerMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_ALEXA_AUDIO_PLAYBACK_STATUS_LISTENER, new AlexaAudioPlaybackStatusListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void deregisterAlexaCardRendererListener(ExtendedClient extendedClient, MessageReceiver<AlexaCardListenerMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_CARD_RENDERER_LISTENER, new CardListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void deregisterAlexaPlayerInfoCardListener(ExtendedClient extendedClient, AlexaPlayerInfoCardListenerProxy alexaPlayerInfoCardListenerProxy) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_PLAYER_INFO_CARD_LISTENER, new PlayerInfoCardListenerMessagePayload(extendedClient, alexaPlayerInfoCardListenerProxy).getBundle());
    }

    public void deregisterAlexaSettingsListener(ExtendedClient extendedClient, AlexaSettingsListenerProxy alexaSettingsListenerProxy) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_ALEXA_SETTINGS_LISTENER, new SettingsListenerMessagePayload(extendedClient, alexaSettingsListenerProxy).getBundle());
    }

    public void deregisterAlexaStateListener(ExtendedClient extendedClient, AlexaStateListenerProxy alexaStateListenerProxy) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_ALEXA_STATE_LISTENER, new AlexaStateListenerMessagePayload(extendedClient, alexaStateListenerProxy).getBundle());
    }

    public void deregisterAlexaUserSpeechListener(ExtendedClient extendedClient, AlexaUserSpeechListenerProxy alexaUserSpeechListenerProxy) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_USER_SPEECH_LISTENER, new UserSpeechMessagePayload(extendedClient, alexaUserSpeechListenerProxy).getBundle());
    }

    public void deregisterArtifactDownloadListener(ExtendedClient extendedClient, MessageReceiver<ApiType_ArtifactDownloadListenerMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_ARTIFACT_DOWNLOAD_LISTENER, new ArtifactDownloadListenerPayload(extendedClient, messageReceiver).getBundle());
    }

    public void deregisterAttentionSystemListener(ExtendedClient extendedClient, MessageReceiver<ApiType_AttentionSystemListenerMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_ATTENTION_SYSTEM_LISTENER, new AlexaAttentionSystemListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void deregisterCaptionListener(ExtendedClient extendedClient, MessageReceiver<ApiType_CaptionListenerMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_CAPTION_LISTENER, new CaptionListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void deregisterClientConnectionController(ExtendedClient extendedClient, MessageReceiver<ClientConnectionControllerMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_CLIENT_CONNECTION_CONTROLLER, new ClientConnectionControllerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void deregisterContextProvider(ExtendedClient extendedClient, AlexaContextProviderProxy alexaContextProviderProxy) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_CONTEXT_PROVIDER, new ContextProviderMessagePayload(extendedClient, alexaContextProviderProxy).getBundle());
    }

    public void deregisterContextsProvider(ExtendedClient extendedClient, MessageReceiver<AlexaContextsProviderMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_CONTEXTS_PROVIDER, new ContextsProviderMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void deregisterDriveModeListener(ExtendedClient extendedClient, MessageReceiver<ApiType_DriveModeListenerMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_DRIVE_MODE_LISTENER, new DriveModeListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void deregisterExpectTextListener(ExtendedClient extendedClient, MessageReceiver<ExpectTextMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_EXPECT_TEXT_LISTENER, new ExpectTextListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void deregisterLocaleListener(ExtendedClient extendedClient, MessageReceiver<ax> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_LOCALES_LISTENER, new LocaleListenerPayload(extendedClient, messageReceiver).getBundle());
    }

    public void deregisterMediaPlaybackListener(ExtendedClient extendedClient, MessageReceiver<ApiType_MediaPlaybackListenerMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_MEDIA_PLAYBACK_LISTENER, new MediaPlaybackListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void deregisterMetricsListener(ExtendedClient extendedClient, MessageReceiver<be> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_METRICS_LISTENER, new MetricsListenerPayload(extendedClient, messageReceiver).getBundle());
    }

    public void deregisterReadinessListener(ExtendedClient extendedClient, MessageReceiver<AlexaReadinessMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_READINESS_LISTENER, new ReadinessListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void deregisterSupportedLocalesListener(ExtendedClient extendedClient, MessageReceiver<bs> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_SUPPORTED_LOCALES_LISTENER, new SupportedLocalesListenersPayload(extendedClient, messageReceiver).getBundle());
    }

    public void deregisterTextResponseListener(ExtendedClient extendedClient, MessageReceiver<TextResponseListenerMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_TEXT_RESPONSE_LISTENER, new TextResponseListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void deregisterUserPerceivedLatencyListener(ExtendedClient extendedClient, UserPerceivedLatencyListenerProxy userPerceivedLatencyListenerProxy) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_UPL_LISTENER, new UplListenerPayload(extendedClient, userPerceivedLatencyListenerProxy).getBundle());
    }

    public void deregisterUserSpeechProvider(ExtendedClient extendedClient, MessageReceiver<AlexaUserSpeechProviderMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_USER_SPEECH_PROVIDER, new UserSpeechProviderMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void deregisterWakeWordListener(ExtendedClient extendedClient, MessageReceiver<AlexaWakeWordListenerMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DEREGISTER_WAKE_WORD_LISTENER, new WakeWordListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void disableExternalCapabilityAgent(ExtendedClient extendedClient, AlexaCapabilityAgentRegistration alexaCapabilityAgentRegistration) throws RemoteException {
        sendMessage(AlexaServicesMessageType.DISABLE_EXTERNAL_CAPABILITY_AGENT, new ExternalCapabilityAgentStatusPayload(extendedClient, alexaCapabilityAgentRegistration).getBundle());
    }

    public void enableExternalCapabilityAgent(ExtendedClient extendedClient, AlexaCapabilityAgentRegistration alexaCapabilityAgentRegistration) throws RemoteException {
        sendMessage(AlexaServicesMessageType.ENABLE_EXTERNAL_CAPABILITY_AGENT, new ExternalCapabilityAgentStatusPayload(extendedClient, alexaCapabilityAgentRegistration).getBundle());
    }

    @Override // com.amazon.alexa.api.messages.messagesender.AlexaBidirectionalMessageSender
    protected Set<AlexaServicesMessageType> getExpectedMessageTypes() {
        return Collections.unmodifiableSet(EnumSet.of(AlexaServicesMessageType.GET_LOCALES, AlexaServicesMessageType.GET_SUPPORTED_LOCALES, AlexaServicesMessageType.GET_SUPPORTED_LOCALE_COMBINATIONS, AlexaServicesMessageType.IS_USER_LOGGED_IN, AlexaServicesMessageType.IS_DETECTING_WAKE_WORD, AlexaServicesMessageType.GET_READY_STATE, AlexaServicesMessageType.IS_WAKE_SOUND_ENABLED, AlexaServicesMessageType.IS_ENDPOINT_SOUND_ENABLED));
    }

    public List<String> getLocales(ExtendedClient extendedClient) throws RemoteException {
        final BaseMessagePayload baseMessagePayload = new BaseMessagePayload(extendedClient);
        if (this.alexaLocalesTask == null) {
            this.alexaLocalesTask = new at<List<String>>(1000L, null) { // from class: com.amazon.alexa.api.AlexaServicesMessageSender.1
                @Override // com.amazon.alexa.api.at
                protected void execute() throws RemoteException {
                    AlexaServicesMessageSender.this.sendMessage(AlexaServicesMessageType.GET_LOCALES, baseMessagePayload.getBundle());
                }
            };
        }
        return this.alexaLocalesTask.call();
    }

    public AlexaReadyState getReadyState(ExtendedClient extendedClient) throws RemoteException {
        final BaseMessagePayload baseMessagePayload = new BaseMessagePayload(extendedClient);
        if (this.getReadyStateTask == null) {
            this.getReadyStateTask = new at<AlexaReadyState>(1000L, AlexaReadyState.DEFAULT_STATE) { // from class: com.amazon.alexa.api.AlexaServicesMessageSender.6
                @Override // com.amazon.alexa.api.at
                protected void execute() throws RemoteException {
                    AlexaServicesMessageSender.this.sendMessage(AlexaServicesMessageType.GET_READY_STATE, baseMessagePayload.getBundle());
                }
            };
        }
        return this.getReadyStateTask.call();
    }

    public Set<List<String>> getSupportedLocaleCombinations(ExtendedClient extendedClient) throws RemoteException {
        final BaseMessagePayload baseMessagePayload = new BaseMessagePayload(extendedClient);
        if (this.supportedLocaleCombinationsTask == null) {
            this.supportedLocaleCombinationsTask = new at<Set<List<String>>>(1000L, Collections.emptySet()) { // from class: com.amazon.alexa.api.AlexaServicesMessageSender.3
                @Override // com.amazon.alexa.api.at
                protected void execute() throws RemoteException {
                    AlexaServicesMessageSender.this.sendMessage(AlexaServicesMessageType.GET_SUPPORTED_LOCALE_COMBINATIONS, baseMessagePayload.getBundle());
                }
            };
        }
        return this.supportedLocaleCombinationsTask.call();
    }

    public List<String> getSupportedLocales(ExtendedClient extendedClient) throws RemoteException {
        final BaseMessagePayload baseMessagePayload = new BaseMessagePayload(extendedClient);
        if (this.supportedLocalesTask == null) {
            this.supportedLocalesTask = new at<List<String>>(1000L, Collections.emptyList()) { // from class: com.amazon.alexa.api.AlexaServicesMessageSender.2
                @Override // com.amazon.alexa.api.at
                protected void execute() throws RemoteException {
                    AlexaServicesMessageSender.this.sendMessage(AlexaServicesMessageType.GET_SUPPORTED_LOCALES, baseMessagePayload.getBundle());
                }
            };
        }
        return this.supportedLocalesTask.call();
    }

    public boolean isDetectingWakeWord(ExtendedClient extendedClient) throws RemoteException {
        final BaseMessagePayload baseMessagePayload = new BaseMessagePayload(extendedClient);
        this.isDetectingWakeWordTask = new at<Boolean>(1000L, false) { // from class: com.amazon.alexa.api.AlexaServicesMessageSender.5
            @Override // com.amazon.alexa.api.at
            protected void execute() throws RemoteException {
                AlexaServicesMessageSender.this.sendMessage(AlexaServicesMessageType.IS_DETECTING_WAKE_WORD, baseMessagePayload.getBundle());
            }
        };
        return this.isDetectingWakeWordTask.call().booleanValue();
    }

    public boolean isEndpointSoundEnabled(ExtendedClient extendedClient) throws RemoteException {
        final BaseMessagePayload baseMessagePayload = new BaseMessagePayload(extendedClient);
        if (this.isEndpointSoundEnabledResult == null) {
            this.isEndpointSoundEnabledResult = new at<Boolean>(1000L, false) { // from class: com.amazon.alexa.api.AlexaServicesMessageSender.8
                @Override // com.amazon.alexa.api.at
                protected void execute() throws RemoteException {
                    AlexaServicesMessageSender.this.sendMessage(AlexaServicesMessageType.IS_ENDPOINT_SOUND_ENABLED, baseMessagePayload.getBundle());
                }
            };
        }
        return this.isEndpointSoundEnabledResult.call().booleanValue();
    }

    public boolean isUserLoggedIn(ExtendedClient extendedClient) throws RemoteException {
        final BaseMessagePayload baseMessagePayload = new BaseMessagePayload(extendedClient);
        if (this.isUserLoggedInTask == null) {
            this.isUserLoggedInTask = new at<Boolean>(5000L, false) { // from class: com.amazon.alexa.api.AlexaServicesMessageSender.4
                @Override // com.amazon.alexa.api.at
                protected void execute() throws RemoteException {
                    AlexaServicesMessageSender.this.sendMessage(AlexaServicesMessageType.IS_USER_LOGGED_IN, baseMessagePayload.getBundle());
                }
            };
        }
        return this.isUserLoggedInTask.call().booleanValue();
    }

    public boolean isWakeSoundEnabled(ExtendedClient extendedClient) throws RemoteException {
        final BaseMessagePayload baseMessagePayload = new BaseMessagePayload(extendedClient);
        if (this.isWakeSoundEnabledResult == null) {
            this.isWakeSoundEnabledResult = new at<Boolean>(1000L, false) { // from class: com.amazon.alexa.api.AlexaServicesMessageSender.7
                @Override // com.amazon.alexa.api.at
                protected void execute() throws RemoteException {
                    AlexaServicesMessageSender.this.sendMessage(AlexaServicesMessageType.IS_WAKE_SOUND_ENABLED, baseMessagePayload.getBundle());
                }
            };
        }
        return this.isWakeSoundEnabledResult.call().booleanValue();
    }

    public void logOut(ExtendedClient extendedClient) throws RemoteException {
        sendMessage(AlexaServicesMessageType.LOG_OUT, new BaseMessagePayload(extendedClient).getBundle());
    }

    public void muteMicrophone(ExtendedClient extendedClient, boolean z) throws RemoteException {
        sendMessage(AlexaServicesMessageType.MUTE_MICROPHONE, new MuteMicMessagePayload(extendedClient, z).getBundle());
    }

    public void next(ExtendedClient extendedClient) throws RemoteException {
        sendMessage(AlexaServicesMessageType.NEXT, new BaseMessagePayload(extendedClient).getBundle());
    }

    void onAlexaLocale(List<String> list) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("alexaLocale: ");
        outline107.append(list.toString());
        Log.i(str, outline107.toString());
        at<List<String>> atVar = this.alexaLocalesTask;
        if (atVar != null) {
            atVar.setResult(list);
            this.alexaLocalesTask = null;
        }
    }

    public void onClientConnect(ExtendedClient extendedClient, boolean z, MessageReceiver<ClientConnectionControllerMessageType> messageReceiver, boolean z2, @Nullable PendingIntent pendingIntent, boolean z3) throws RemoteException {
        sendMessage(AlexaServicesMessageType.ON_CLIENT_CONNECT, new ClientClientConnectionMessagePayload(extendedClient, messageReceiver, z, z2, pendingIntent, z3).getBundle());
    }

    public void onClientDisconnect(ExtendedClient extendedClient) throws RemoteException {
        sendMessage(AlexaServicesMessageType.ON_CLIENT_DISCONECT, new BaseMessagePayload(extendedClient).getBundle());
    }

    void onGetReadyState(AlexaReadyState alexaReadyState) {
        String str = TAG;
        Log.i(str, "onGetReadyState: " + alexaReadyState);
        at<AlexaReadyState> atVar = this.getReadyStateTask;
        if (atVar != null) {
            atVar.setResult(alexaReadyState);
            this.getReadyStateTask = null;
        }
    }

    void onIsDetectingWakeWord(boolean z) {
        GeneratedOutlineSupport1.outline173("isDetectingWakeWord: ", z, TAG);
        at<Boolean> atVar = this.isDetectingWakeWordTask;
        if (atVar != null) {
            atVar.setResult(Boolean.valueOf(z));
            this.isDetectingWakeWordTask = null;
        }
    }

    void onIsEndpointSoundEnabledResponse(boolean z) {
        at<Boolean> atVar = this.isEndpointSoundEnabledResult;
        if (atVar != null) {
            atVar.setResult(Boolean.valueOf(z));
            this.isEndpointSoundEnabledResult = null;
        }
    }

    void onIsUserLoggedIn(boolean z) {
        GeneratedOutlineSupport1.outline173("isUserLoggedIn: ", z, TAG);
        at<Boolean> atVar = this.isUserLoggedInTask;
        if (atVar != null) {
            atVar.setResult(Boolean.valueOf(z));
            this.isUserLoggedInTask = null;
        }
    }

    void onIsWakeSoundEnabledResponse(boolean z) {
        at<Boolean> atVar = this.isWakeSoundEnabledResult;
        if (atVar != null) {
            atVar.setResult(Boolean.valueOf(z));
            this.isWakeSoundEnabledResult = null;
        }
    }

    void onSupportedLocaleCombinations(Set<List<String>> set) {
        Log.i(TAG, "onSupportedLocaleCombinations");
        at<Set<List<String>>> atVar = this.supportedLocaleCombinationsTask;
        if (atVar != null) {
            atVar.setResult(set);
            this.supportedLocaleCombinationsTask = null;
        }
    }

    void onSupportedLocales(List<String> list) {
        Log.i(TAG, "onSupportedLocales");
        at<List<String>> atVar = this.supportedLocalesTask;
        if (atVar != null) {
            atVar.setResult(list);
            this.supportedLocalesTask = null;
        }
    }

    public void pause(ExtendedClient extendedClient) throws RemoteException {
        sendMessage(AlexaServicesMessageType.PAUSE, new BaseMessagePayload(extendedClient).getBundle());
    }

    public void play(ExtendedClient extendedClient) throws RemoteException {
        sendMessage(AlexaServicesMessageType.PLAY, new BaseMessagePayload(extendedClient).getBundle());
    }

    public void previous(ExtendedClient extendedClient) throws RemoteException {
        sendMessage(AlexaServicesMessageType.PREVIOUS, new BaseMessagePayload(extendedClient).getBundle());
    }

    public void registerAlertsListener(ExtendedClient extendedClient, MessageReceiver<e> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_ALERTS_LISTENER, new AlertsListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void registerAlexaAttentionSystemSettingsListener(ExtendedClient extendedClient, MessageReceiver<AlexaAttentionSystemSettingsMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_ATTENTION_SYSTEM_SETTINGS_LISTENER, new AttentionSystemSettingsListenerPayload(extendedClient, messageReceiver).getBundle());
    }

    public void registerAlexaAudioPlaybackListener(ExtendedClient extendedClient, AlexaAudioPlaybackListenerProxy alexaAudioPlaybackListenerProxy) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_AUDIO_PLAYBACK_LISTENER, new AudioPlaybackListenerMessagePayload(extendedClient, alexaAudioPlaybackListenerProxy).getBundle());
    }

    public void registerAlexaAudioPlaybackStatusListener(ExtendedClient extendedClient, MessageReceiver<AlexaAudioPlaybackStatusListenerMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_ALEXA_AUDIO_PLAYBACK_STATUS_LISTENER, new AlexaAudioPlaybackStatusListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void registerAlexaCardRendererListener(ExtendedClient extendedClient, MessageReceiver<AlexaCardListenerMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_CARD_RENDERER_LISTENER, new CardListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void registerAlexaPlayerInfoCardListener(ExtendedClient extendedClient, AlexaPlayerInfoCardListenerProxy alexaPlayerInfoCardListenerProxy) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_PLAYER_INFO_CARD_LISTENER, new PlayerInfoCardListenerMessagePayload(extendedClient, alexaPlayerInfoCardListenerProxy).getBundle());
    }

    public void registerAlexaSettingsListener(ExtendedClient extendedClient, AlexaSettingsListenerProxy alexaSettingsListenerProxy) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_ALEXA_SETTINGS_LISTENER, new SettingsListenerMessagePayload(extendedClient, alexaSettingsListenerProxy).getBundle());
    }

    public void registerAlexaStateListener(ExtendedClient extendedClient, AlexaStateListenerProxy alexaStateListenerProxy) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_ALEXA_STATE_LISTENER, new AlexaStateListenerMessagePayload(extendedClient, alexaStateListenerProxy).getBundle());
    }

    public void registerAlexaUserSpeechListener(ExtendedClient extendedClient, AlexaUserSpeechListenerProxy alexaUserSpeechListenerProxy) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_USER_SPEECH_LISTENER, new UserSpeechMessagePayload(extendedClient, alexaUserSpeechListenerProxy).getBundle());
    }

    public void registerArtifactDownloadListener(ExtendedClient extendedClient, MessageReceiver<ApiType_ArtifactDownloadListenerMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_ARTIFACT_DOWNLOAD_LISTENER, new ArtifactDownloadListenerPayload(extendedClient, messageReceiver).getBundle());
    }

    public void registerAttentionSystemListener(ExtendedClient extendedClient, MessageReceiver<ApiType_AttentionSystemListenerMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_ATTENTION_SYSTEM_LISTENER, new AlexaAttentionSystemListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void registerCaptionListener(ExtendedClient extendedClient, MessageReceiver<ApiType_CaptionListenerMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_CAPTION_LISTENER, new CaptionListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void registerContextProvider(ExtendedClient extendedClient, AlexaContextProviderProxy alexaContextProviderProxy) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_CONTEXT_PROVIDER, new ContextProviderMessagePayload(extendedClient, alexaContextProviderProxy).getBundle());
    }

    public void registerContextsProvider(ExtendedClient extendedClient, MessageReceiver<AlexaContextsProviderMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_CONTEXTS_PROVIDER, new ContextsProviderMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void registerDriveModeListener(ExtendedClient extendedClient, MessageReceiver<ApiType_DriveModeListenerMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_DRIVE_MODE_LISTENER, new DriveModeListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void registerExpectTextListener(ExtendedClient extendedClient, MessageReceiver<ExpectTextMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_EXPECT_TEXT_LISTENER, new ExpectTextListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void registerLocaleListener(ExtendedClient extendedClient, MessageReceiver<ax> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_LOCALES_LISTENER, new LocaleListenerPayload(extendedClient, messageReceiver).getBundle());
    }

    public void registerMediaPlaybackListener(ExtendedClient extendedClient, MessageReceiver<ApiType_MediaPlaybackListenerMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_MEDIA_PLAYBACK_LISTENER, new MediaPlaybackListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void registerMetricsListener(ExtendedClient extendedClient, MessageReceiver<be> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_METRICS_LISTENER, new MetricsListenerPayload(extendedClient, messageReceiver).getBundle());
    }

    public void registerReadinessListener(ExtendedClient extendedClient, MessageReceiver<AlexaReadinessMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_READINESS_LISTENER, new ReadinessListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void registerSupportedLocalesListener(ExtendedClient extendedClient, MessageReceiver<bs> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_SUPPORTED_LOCALES_LISTENER, new SupportedLocalesListenersPayload(extendedClient, messageReceiver).getBundle());
    }

    public void registerTextResponseListener(ExtendedClient extendedClient, MessageReceiver<TextResponseListenerMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_TEXT_RESPONSE_LISTENER, new TextResponseListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void registerUserPerceivedLatencyListener(ExtendedClient extendedClient, UserPerceivedLatencyListenerProxy userPerceivedLatencyListenerProxy) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_UPL_LISTENER, new UplListenerPayload(extendedClient, userPerceivedLatencyListenerProxy).getBundle());
    }

    public void registerUserSpeechProvider(ExtendedClient extendedClient, MessageReceiver<AlexaUserSpeechProviderMessageType> messageReceiver, Bundle bundle) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_USER_SPEECH_PROVIDER, new UserSpeechProviderMessagePayload(extendedClient, messageReceiver, bundle).getBundle());
    }

    public void registerWakeWordListener(ExtendedClient extendedClient, MessageReceiver<AlexaWakeWordListenerMessageType> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REGISTER_WAKE_WORD_LISTENER, new WakeWordListenerMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void requestDialog(ExtendedClient extendedClient, MessageReceiver<AlexaUserSpeechProviderMessageType> messageReceiver, Bundle bundle) throws RemoteException {
        sendMessage(AlexaServicesMessageType.REQUEST_DIALOG, new RequestDialogMessagePayload(extendedClient, messageReceiver, bundle).getBundle());
    }

    public void schedule(ExtendedClient extendedClient, MessageReceiver<ca> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.SCHEDULE_VISUAL_TASK, new VisualTaskMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void scheduleInteraction(ExtendedClient extendedClient, AlexaAudioInteractionProxy alexaAudioInteractionProxy) throws RemoteException {
        sendMessage(AlexaServicesMessageType.SCHEDULE_INTERACTION, new AudioInteractionMessagePayload(extendedClient, alexaAudioInteractionProxy).getBundle());
    }

    public void sendAlexaEvent(ExtendedClient extendedClient, AlexaEvent alexaEvent, List<AlexaContext> list) throws RemoteException {
        sendMessage(AlexaServicesMessageType.SEND_ALEXA_EVENT, new AlexaEventMessagePayload(extendedClient, alexaEvent, list).getBundle());
    }

    public void sendAlexaEvent(ExtendedClient extendedClient, AlexaEvent alexaEvent, boolean z) throws RemoteException {
        sendAlexaEvent(extendedClient, alexaEvent, z, null);
    }

    public void sendAlexaEvent(ExtendedClient extendedClient, AlexaEvent alexaEvent, boolean z, @Nullable Bundle bundle) throws RemoteException {
        sendMessage(AlexaServicesMessageType.SEND_ALEXA_EVENT, new AlexaEventMessagePayload(extendedClient, alexaEvent, z, bundle).getBundle());
    }

    public void sendTextMessage(ExtendedClient extendedClient, String str) throws RemoteException {
        sendTextMessage(extendedClient, str, null);
    }

    public void sendTextMessage(ExtendedClient extendedClient, String str, TextAlexaDialogExtras textAlexaDialogExtras) throws RemoteException {
        sendMessage(AlexaServicesMessageType.SEND_TEXT_MESSAGE, new SendTextMessagePayload(extendedClient, str, textAlexaDialogExtras).getBundle());
    }

    public void setBaseURLs(ExtendedClient extendedClient, AlexaBaseURLs alexaBaseURLs) throws RemoteException {
        sendMessage(AlexaServicesMessageType.SET_BASE_URLS, new SetBaseURLsPayload(extendedClient, alexaBaseURLs).getBundle());
    }

    public void setContextCachingEnabled(ExtendedClient extendedClient, boolean z, @Nullable Bundle bundle) throws RemoteException {
        sendMessage(AlexaServicesMessageType.SET_CONTEXT_CACHING_ENABLED, new SetContextCachingEnabledPayload(extendedClient, z, bundle).getBundle());
    }

    public void setDriveModeEnabled(ExtendedClient extendedClient, boolean z) throws RemoteException {
        sendMessage(AlexaServicesMessageType.SET_DRIVE_MODE_ENABLED, new DriveModeEnabledMessagePayload(extendedClient, z).getBundle());
    }

    public void setDriveModeState(ExtendedClient extendedClient, DriveModeState driveModeState) throws RemoteException {
        sendMessage(AlexaServicesMessageType.SET_DRIVE_MODE_STATE, new DriveModeStateMessagePayload(extendedClient, driveModeState).getBundle());
    }

    public void setDriveModeTheme(ExtendedClient extendedClient, boolean z) throws RemoteException {
        sendMessage(AlexaServicesMessageType.SET_DRIVE_MODE_THEME, new DriveModeThemeMessagePayload(extendedClient, z).getBundle());
    }

    public void setEndpointSoundEnabled(ExtendedClient extendedClient, boolean z) throws RemoteException {
        sendMessage(AlexaServicesMessageType.SET_ENDPOINT_SOUND_ENABLED, new SetEndpointSoundEnabledMessagePayload(extendedClient, z).getBundle());
    }

    public void setLocales(ExtendedClient extendedClient, List<String> list, @Nullable Bundle bundle) throws RemoteException {
        sendMessage(AlexaServicesMessageType.SET_LOCALES, new AlexaLocaleMessagePayload(extendedClient, list, false, bundle).getBundle());
    }

    public void setLocales(ExtendedClient extendedClient, List<String> list, boolean z) throws RemoteException {
        sendMessage(AlexaServicesMessageType.SET_LOCALES, new AlexaLocaleMessagePayload(extendedClient, list, z).getBundle());
    }

    public void setMediaNotificationContentIntent(ExtendedClient extendedClient, PendingIntent pendingIntent) throws RemoteException {
        sendMessage(AlexaServicesMessageType.SET_MEDIA_NOTIFICATION_CONTENT_INTENT, new MediaPlaybackContentIntentMessagePayload(extendedClient, pendingIntent).getBundle());
    }

    public void setWakeSoundEnabled(ExtendedClient extendedClient, boolean z) throws RemoteException {
        sendMessage(AlexaServicesMessageType.SET_WAKE_SOUND_ENABLED, new SetWakeSoundEnabledMessagePayload(extendedClient, z).getBundle());
    }

    public void setWakeWordAllowed(ExtendedClient extendedClient, boolean z) throws RemoteException {
        sendMessage(AlexaServicesMessageType.SET_WAKE_WORD_ALLOWED, new WakeWordAllowedMessagePayload(extendedClient, z).getBundle());
    }

    public void setWakeWords(ExtendedClient extendedClient, List<String> list, @Nullable Bundle bundle) throws RemoteException {
        sendMessage(AlexaServicesMessageType.SET_WAKE_WORDS, new SetWakeWordsMessagePayload(extendedClient, list, bundle).getBundle());
    }

    public void startListening(ExtendedClient extendedClient, @Nullable AlexaDialogExtras alexaDialogExtras) throws RemoteException {
        sendMessage(AlexaServicesMessageType.START_LISTENING_WAKE_WORD, new StartListeningMessagePayload(extendedClient, alexaDialogExtras).getBundle());
    }

    public void startRecognizing(ExtendedClient extendedClient) throws RemoteException {
        sendMessage(AlexaServicesMessageType.START_RECOGNIZING, new StartRecognizingMessagePayload(extendedClient).getBundle());
    }

    public void startRecognizing(ExtendedClient extendedClient, @Nullable Bundle bundle) throws RemoteException {
        sendMessage(AlexaServicesMessageType.START_RECOGNIZING, new StartRecognizingMessagePayload(extendedClient, bundle).getBundle());
    }

    public void stop(ExtendedClient extendedClient) throws RemoteException {
        sendMessage(AlexaServicesMessageType.STOP, new BaseMessagePayload(extendedClient).getBundle());
    }

    public void stopForegroundAudioTask(ExtendedClient extendedClient) throws RemoteException {
        sendMessage(AlexaServicesMessageType.STOP_FOREGROUND_AUDIO_TASK, new BaseMessagePayload(extendedClient).getBundle());
    }

    public void stopListening(ExtendedClient extendedClient) throws RemoteException {
        sendMessage(AlexaServicesMessageType.STOP_LISTENING_WAKE_WORD, new BaseMessagePayload(extendedClient).getBundle());
    }

    public void stopRecognizing(ExtendedClient extendedClient) throws RemoteException {
        sendMessage(AlexaServicesMessageType.STOP_RECOGNIZING, new BaseMessagePayload(extendedClient).getBundle());
    }

    public void temporarilySuppressAllAudio(ExtendedClient extendedClient) throws RemoteException {
        sendMessage(AlexaServicesMessageType.TEMPORARILY_SUPPRESS_ALL_AUDIO, new BaseMessagePayload(extendedClient).getBundle());
    }

    public void unschedule(ExtendedClient extendedClient, MessageReceiver<ca> messageReceiver) throws RemoteException {
        sendMessage(AlexaServicesMessageType.UNSCHEDULE_VISUAL_TASK, new VisualTaskMessagePayload(extendedClient, messageReceiver).getBundle());
    }

    public void unscheduleInteraction(ExtendedClient extendedClient, AlexaAudioInteractionProxy alexaAudioInteractionProxy) throws RemoteException {
        sendMessage(AlexaServicesMessageType.UNSCHEDULE_INTERACTION, new AudioInteractionMessagePayload(extendedClient, alexaAudioInteractionProxy).getBundle());
    }
}
