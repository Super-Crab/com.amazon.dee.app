package com.amazon.alexa.api;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Messenger;
import androidx.annotation.Nullable;
import com.amazon.alexa.CGv;
import com.amazon.alexa.Mlj;
import com.amazon.alexa.UYN;
import com.amazon.alexa.api.AlexaCapabilityAgentRegistration;
import com.amazon.alexa.api.messages.MessageReceiversManager;
/* loaded from: classes6.dex */
public class AlexaServicesApisV2_3_1 extends AlexaServicesApisV2_3 {
    public static final String TAG = "AlexaServicesApisV2_3_1";

    /* renamed from: com.amazon.alexa.api.AlexaServicesApisV2_3_1$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType = new int[AlexaServicesMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.SET_MEDIA_NOTIFICATION_CONTENT_INTENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.ENABLE_EXTERNAL_CAPABILITY_AGENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DISABLE_EXTERNAL_CAPABILITY_AGENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_WAKE_WORD_LISTENER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_WAKE_WORD_LISTENER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.SET_WAKE_WORDS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_ALEXA_AUDIO_PLAYBACK_STATUS_LISTENER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_ALEXA_AUDIO_PLAYBACK_STATUS_LISTENER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public AlexaServicesApisV2_3_1(AlexaClient alexaClient, UYN uyn, CGv cGv, Mlj mlj, AlexaVisualTaskFactory alexaVisualTaskFactory, MessageReceiversManager messageReceiversManager) {
        super(alexaClient, uyn, cGv, mlj, alexaVisualTaskFactory, messageReceiversManager);
    }

    private void deregisterAlexaAudioPlaybackStatusListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.deregisterAlexaAudioPlaybackStatusListener(client, new AlexaAudioPlaybackStatusListenerSender(Bundles.getBinder(bundle, AlexaServicesArgumentKey.AUDIO_PLAYBACK_STATUS_LISTENER), client));
    }

    private void deregisterWakeWordListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.deregisterAlexaWakeWordListener(client, getApiCallbacksFrom(bundle), new AlexaWakeWordListenerMessageSender(client, Bundles.getBinder(bundle, AlexaServicesArgumentKey.WAKEWORD_LISTENER)));
    }

    private void disableExternalCapabilityAgent(Bundle bundle) {
        this.alexaClient.disableExternalCapabilityAgent(getClient(bundle), getApiCallbacksFrom(bundle), new AlexaCapabilityAgentRegistration.Adapter().mo844createFromBundle(Bundles.getBundle(bundle, AlexaServicesArgumentKey.EXTERNAL_CAPABILITY_AGENT_STATUS)));
    }

    private void enableExternalCapabilityAgent(Bundle bundle) {
        this.alexaClient.enableExternalCapabilityAgent(getClient(bundle), getApiCallbacksFrom(bundle), new AlexaCapabilityAgentRegistration.Adapter().mo844createFromBundle(Bundles.getBundle(bundle, AlexaServicesArgumentKey.EXTERNAL_CAPABILITY_AGENT_STATUS)));
    }

    private boolean isClientVersionSupported(Bundle bundle) {
        return Versions.isPayloadSupportedByVersion(bundle, Versions.V2_3_1);
    }

    private void registerAlexaAudioPlaybackStatusListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.registerAlexaAudioPlaybackStatusListener(client, new AlexaAudioPlaybackStatusListenerSender(Bundles.getBinder(bundle, AlexaServicesArgumentKey.AUDIO_PLAYBACK_STATUS_LISTENER), client));
    }

    private void registerWakeWordListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.registerAlexaWakeWordListener(client, getApiCallbacksFrom(bundle), new AlexaWakeWordListenerMessageSender(client, Bundles.getBinder(bundle, AlexaServicesArgumentKey.WAKEWORD_LISTENER)));
    }

    private void setMediaNotificationContentIntent(Bundle bundle) {
        this.alexaClient.setMediaNotificationContentIntent(getClient(bundle), getApiCallbacksFrom(bundle), (PendingIntent) Bundles.getOptionalParcelable(bundle, AlexaServicesArgumentKey.MEDIA_NOTIFICATION_CONTENT_INTENT, PendingIntent.class));
    }

    private void setWakeWords(Bundle bundle) {
        this.alexaClient.setWakeWords(getClient(bundle), getApiCallbacksFrom(bundle), Bundles.getStringList(bundle, AlexaServicesArgumentKey.WAKE_WORDS));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.AlexaServicesApisV2_3, com.amazon.alexa.api.AlexaServiceApisV2_2_1, com.amazon.alexa.api.AlexaServiceApisV2_2, com.amazon.alexa.api.AlexaServicesApisV2_1, com.amazon.alexa.api.AlexaServicesApisV1, com.amazon.alexa.api.AlexaServicesV2, com.amazon.alexa.api.AlexaServicesV1, com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(AlexaServicesMessageType alexaServicesMessageType, Bundle bundle, @Nullable Messenger messenger) {
        if (!isClientVersionSupported(bundle)) {
            super.processMessage(alexaServicesMessageType, bundle, messenger);
            return;
        }
        switch (alexaServicesMessageType.ordinal()) {
            case 94:
                setMediaNotificationContentIntent(bundle);
                return;
            case 95:
                enableExternalCapabilityAgent(bundle);
                return;
            case 96:
                disableExternalCapabilityAgent(bundle);
                return;
            case 97:
                registerWakeWordListener(bundle);
                return;
            case 98:
                deregisterWakeWordListener(bundle);
                return;
            case 99:
                setWakeWords(bundle);
                return;
            case 100:
                registerAlexaAudioPlaybackStatusListener(bundle);
                return;
            case 101:
                deregisterAlexaAudioPlaybackStatusListener(bundle);
                return;
            default:
                super.processMessage(alexaServicesMessageType, bundle, messenger);
                return;
        }
    }
}
