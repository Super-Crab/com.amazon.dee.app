package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.CGv;
import com.amazon.alexa.Mlj;
import com.amazon.alexa.UYN;
import com.amazon.alexa.api.Bundles;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import java.util.Collections;
import java.util.List;
/* loaded from: classes6.dex */
public class AlexaServicesApisV2_3 extends AlexaServiceApisV2_2_1 {
    public static final String TAG = AlexaServiceApisV2_2_1.class.getSimpleName();
    public final AlexaVisualTaskFactory alexaVisualTaskFactory;
    public final MessageReceiversManager messageReceiversManager;

    /* renamed from: com.amazon.alexa.api.AlexaServicesApisV2_3$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType = new int[AlexaServicesMessageType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_READINESS_LISTENER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_READINESS_LISTENER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.GET_READY_STATE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.SCHEDULE_VISUAL_TASK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.UNSCHEDULE_VISUAL_TASK.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.SET_WAKE_SOUND_ENABLED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.SET_ENDPOINT_SOUND_ENABLED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.IS_WAKE_SOUND_ENABLED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.IS_ENDPOINT_SOUND_ENABLED.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_ATTENTION_SYSTEM_SETTINGS_LISTENER.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_ATTENTION_SYSTEM_SETTINGS_LISTENER.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_LOCALES_LISTENER.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_LOCALES_LISTENER.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_SUPPORTED_LOCALES_LISTENER.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_SUPPORTED_LOCALES_LISTENER.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_ARTIFACT_DOWNLOAD_LISTENER.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_ARTIFACT_DOWNLOAD_LISTENER.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.SET_LOCALE.ordinal()] = 18;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_DRIVE_MODE_LISTENER.ordinal()] = 19;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_DRIVE_MODE_LISTENER.ordinal()] = 20;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.SET_DRIVE_MODE_ENABLED.ordinal()] = 21;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.SET_DRIVE_MODE_STATE.ordinal()] = 22;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.SET_DRIVE_MODE_THEME.ordinal()] = 23;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.SEND_ALEXA_EVENT.ordinal()] = 24;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_TEXT_RESPONSE_LISTENER.ordinal()] = 25;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_TEXT_RESPONSE_LISTENER.ordinal()] = 26;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_EXPECT_TEXT_LISTENER.ordinal()] = 27;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_EXPECT_TEXT_LISTENER.ordinal()] = 28;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_CAPTION_LISTENER.ordinal()] = 29;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_CAPTION_LISTENER.ordinal()] = 30;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.REGISTER_MEDIA_PLAYBACK_LISTENER.ordinal()] = 31;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.DEREGISTER_MEDIA_PLAYBACK_LISTENER.ordinal()] = 32;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.SEND_TEXT_MESSAGE.ordinal()] = 33;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaServicesMessageType[AlexaServicesMessageType.STOP_FOREGROUND_AUDIO_TASK.ordinal()] = 34;
            } catch (NoSuchFieldError unused34) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class EndPointSoundEnabledPayload extends BaseMessagePayload {
        public EndPointSoundEnabledPayload(ExtendedClient extendedClient, boolean z) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.IS_ENDPOINT_SOUND_ENABLED, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class ReadinessMessagePayload extends BaseMessagePayload {
        public ReadinessMessagePayload(ExtendedClient extendedClient, AlexaReadyState alexaReadyState) {
            super(extendedClient);
            add((Bundles.Key) AlexaServicesArgumentKey.GET_READY_STATE, BundleTransformer.getDefaultInstance().toBundle(alexaReadyState));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class WakeSoundEnabledPayload extends BaseMessagePayload {
        public WakeSoundEnabledPayload(ExtendedClient extendedClient, boolean z) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.IS_WAKE_SOUND_ENABLED, z);
        }
    }

    public AlexaServicesApisV2_3(AlexaClient alexaClient, UYN uyn, CGv cGv, Mlj mlj, AlexaVisualTaskFactory alexaVisualTaskFactory, MessageReceiversManager messageReceiversManager) {
        super(alexaClient, uyn, cGv, mlj);
        this.alexaVisualTaskFactory = alexaVisualTaskFactory;
        this.messageReceiversManager = messageReceiversManager;
    }

    private void deregisterAlexaArtifactDownloadListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.deregisterAlexaArtifactDownloadListener(client, new ApiType_ArtifactDownloadListenerSender(client, Bundles.getBinder(bundle, AlexaServicesArgumentKey.ARTIFACT_DOWNLOAD_LISTENER), this.messageReceiversManager));
    }

    private void deregisterAlexaCaptionListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.deregisterAlexaCaptionListener(client, new ApiType_CaptionListenerSender(client, Bundles.getBinder(bundle, AlexaServicesArgumentKey.CAPTION_LISTENER), this.messageReceiversManager));
    }

    private void deregisterAttentionSystemSettingsListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.deregisterAttentionSystemSettingsListener(client, new AttentionSystemSettingsMessageSender(Bundles.getBinder(bundle, AlexaServicesArgumentKey.ATTENTION_SYSTEM_SETTING_LISTENER), client));
    }

    private void deregisterDriveModeListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.deregisterDriveModeListener(client, getApiCallbacksFrom(bundle), new ApiType_DriveModeListenerSender(client, Bundles.getBinder(bundle, AlexaServicesArgumentKey.DRIVE_MODE_LISTENER), this.messageReceiversManager));
    }

    private void deregisterExpectTextListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.deregisterExpectTextListener(client, getApiCallbacksFrom(bundle), new AlexaExpectTextListenerSender(client, Bundles.getBinder(bundle, AlexaServicesArgumentKey.EXPECT_TEXT_LISTENER)));
    }

    private void deregisterLocalesListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.deregisterLocalesListener(client, new LocalesListenerSender(client, Bundles.getBinder(bundle, AlexaServicesArgumentKey.LOCALES_LISTENER)));
    }

    private void deregisterMediaPlaybackResponseListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.deregisterAlexaMediaPlaybackListener(client, getApiCallbacksFrom(bundle), new ApiType_MediaPlaybackListenerSender(client, Bundles.getBinder(bundle, AlexaServicesArgumentKey.MEDIA_PLAYBACK_CONTROLLER_LISTENER), this.messageReceiversManager));
    }

    private void deregisterReadinessListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.deregisterReadinessListener(client, new AlexaReadinessMessageSender(Bundles.getBinder(bundle, AlexaServicesArgumentKey.READINESS_LISTENER), client));
    }

    private void deregisterSupporterLocalesListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.deregisterSupportedLocalesListener(client, new SupportedLocalesListenerSender(client, Bundles.getBinder(bundle, AlexaServicesArgumentKey.SUPPORTED_LOCALES_LISTENER)));
    }

    private void deregisterTextResponseListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.deregisterTextResponseListener(client, getApiCallbacksFrom(bundle), new TextResponseListenerSender(client, Bundles.getBinder(bundle, AlexaServicesArgumentKey.TEXT_RESPONSE_LISTENER)));
    }

    private boolean isClientVersionSupported(Bundle bundle) {
        return Versions.isPayloadSupportedByVersion(bundle, Versions.V2_3_0);
    }

    private void registerAlexaArtifactDownloadListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.registerAlexaArtifactDownloadListener(client, new ApiType_ArtifactDownloadListenerSender(client, Bundles.getBinder(bundle, AlexaServicesArgumentKey.ARTIFACT_DOWNLOAD_LISTENER), this.messageReceiversManager));
    }

    private void registerAlexaCaptionListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.registerAlexaCaptionListener(client, new ApiType_CaptionListenerSender(client, Bundles.getBinder(bundle, AlexaServicesArgumentKey.CAPTION_LISTENER), this.messageReceiversManager));
    }

    private void registerAttentionSystemSettingsListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.registerAttentionSystemSettingsListener(client, new AttentionSystemSettingsMessageSender(Bundles.getBinder(bundle, AlexaServicesArgumentKey.ATTENTION_SYSTEM_SETTING_LISTENER), client));
    }

    private void registerDriveModeListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.registerDriveModeListener(client, getApiCallbacksFrom(bundle), new ApiType_DriveModeListenerSender(client, Bundles.getBinder(bundle, AlexaServicesArgumentKey.DRIVE_MODE_LISTENER), this.messageReceiversManager));
    }

    private void registerExpectTextListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.registerExpectTextListener(client, getApiCallbacksFrom(bundle), new AlexaExpectTextListenerSender(client, Bundles.getBinder(bundle, AlexaServicesArgumentKey.EXPECT_TEXT_LISTENER)));
    }

    private void registerLocalesListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.registerLocalesListener(client, new LocalesListenerSender(client, Bundles.getBinder(bundle, AlexaServicesArgumentKey.LOCALES_LISTENER)));
    }

    private void registerMediaPlaybackResponseListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.registerAlexaMediaPlaybackListener(client, getApiCallbacksFrom(bundle), new ApiType_MediaPlaybackListenerSender(client, Bundles.getBinder(bundle, AlexaServicesArgumentKey.MEDIA_PLAYBACK_CONTROLLER_LISTENER), this.messageReceiversManager));
    }

    private void registerReadinessListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.registerReadinessListener(client, new AlexaReadinessMessageSender(Bundles.getBinder(bundle, AlexaServicesArgumentKey.READINESS_LISTENER), client));
    }

    private void registerSupportedLocalesListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.registerSupportedLocalesListener(client, new SupportedLocalesListenerSender(client, Bundles.getBinder(bundle, AlexaServicesArgumentKey.SUPPORTED_LOCALES_LISTENER)));
    }

    private void registerTextResponseListener(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.registerTextResponseListener(client, getApiCallbacksFrom(bundle), new TextResponseListenerSender(client, Bundles.getBinder(bundle, AlexaServicesArgumentKey.TEXT_RESPONSE_LISTENER)));
    }

    private void scheduleVisualTask(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.schedule(client, getApiCallbacksFrom(bundle), this.alexaVisualTaskFactory.createAlexaVisualTask(Bundles.getBinder(bundle, AlexaServicesArgumentKey.VISUAL_TASK), client));
    }

    private void sendAlexaEvent(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        AlexaEvent alexaEvent = (AlexaEvent) Bundles.getParcelable(bundle, AlexaServicesArgumentKey.ALEXA_EVENT, AlexaEvent.class);
        boolean z = Bundles.getBoolean(bundle, AlexaServicesArgumentKey.REQUIRES_CONTEXTS);
        List<AlexaContext> optionalParcelableList = Bundles.getOptionalParcelableList(bundle, AlexaServicesArgumentKey.ALEXA_CONTEXTS, AlexaContext.class);
        if (optionalParcelableList != null) {
            this.alexaClient.sendAlexaEvent(client, alexaEvent, optionalParcelableList);
        } else if (Bundles.getOptionalBundle(bundle, AlexaServicesArgumentKey.API_CALLBACK) != null) {
            this.alexaClient.sendAlexaEvent(client, alexaEvent, z, getApiCallbacksFrom(bundle));
        } else {
            this.alexaClient.sendAlexaEvent(client, alexaEvent, z);
        }
    }

    private void sendTextMessage(Bundle bundle) {
        this.alexaClient.sendTextMessage(getClient(bundle), getApiCallbacksFrom(bundle), Bundles.getString(bundle, AlexaServicesArgumentKey.SEND_TEXT_MESSAGE), new DialogExtras(Bundles.getOptionalBundle(bundle, AlexaServicesArgumentKey.DIALOG_EXTRAS)));
    }

    private void setDriveModeEnabled(Bundle bundle) {
        this.alexaClient.setDriveModeEnabled(getClient(bundle), getApiCallbacksFrom(bundle), Bundles.getBoolean(bundle, AlexaServicesArgumentKey.IS_DRIVE_MODE_ENABLED));
    }

    private void setDriveModeState(Bundle bundle) {
        this.alexaClient.setDriveModeState(getClient(bundle), getApiCallbacksFrom(bundle), DriveModeState.valueOf(Bundles.getString(bundle, AlexaServicesArgumentKey.DRIVE_MODE_STATE)));
    }

    private void setDriveModeTheme(Bundle bundle) {
        this.alexaClient.setDriveModeTheme(getClient(bundle), getApiCallbacksFrom(bundle), Bundles.getBoolean(bundle, AlexaServicesArgumentKey.IS_DRIVE_MODE_DARK_THEME));
    }

    private void setEndpointSoundEnabled(Bundle bundle) {
        this.alexaClient.setEndpointSoundEnabled(getClient(bundle), Bundles.getBoolean(bundle, AlexaServicesArgumentKey.IS_ENDPOINT_SOUND_ENABLED));
    }

    private void setLocale(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        List<String> singletonList = Collections.singletonList(Bundles.getString(bundle, AlexaServicesArgumentKey.CURRENT_LOCALE));
        boolean z = Bundles.getBoolean(bundle, AlexaServicesArgumentKey.FORCE_LOCALE_UPDATE);
        Bundle optionalBundle = Bundles.getOptionalBundle(bundle, AlexaServicesArgumentKey.API_CALLBACK);
        if (optionalBundle != null) {
            this.alexaClient.setLocales(client, singletonList, new ApiCallback(optionalBundle), z);
            return;
        }
        this.alexaClient.setLocales(client, singletonList, z);
    }

    private void setWakeSoundEnabled(Bundle bundle) {
        this.alexaClient.setWakeSoundEnabled(getClient(bundle), Bundles.getBoolean(bundle, AlexaServicesArgumentKey.IS_WAKE_SOUND_ENABLED));
    }

    private void stopForegroundAudioTask(Bundle bundle) {
        this.alexaClient.stopForegroundAudioTask(getClient(bundle));
    }

    private void unscheduleVisualTask(Bundle bundle) {
        ExtendedClient client = getClient(bundle);
        this.alexaClient.unschedule(client, getApiCallbacksFrom(bundle), this.alexaVisualTaskFactory.createAlexaVisualTask(Bundles.getBinder(bundle, AlexaServicesArgumentKey.VISUAL_TASK), client));
    }

    public ApiCallback getApiCallbacksFrom(Bundle bundle) {
        Bundle optionalBundle = Bundles.getOptionalBundle(bundle, AlexaServicesArgumentKey.API_CALLBACK);
        if (optionalBundle != null) {
            return new ApiCallback(optionalBundle);
        }
        return new ApiCallback(null);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.api.AlexaServiceApisV2_2_1, com.amazon.alexa.api.AlexaServiceApisV2_2, com.amazon.alexa.api.AlexaServicesApisV2_1, com.amazon.alexa.api.AlexaServicesApisV1, com.amazon.alexa.api.AlexaServicesV2, com.amazon.alexa.api.AlexaServicesV1, com.amazon.alexa.api.messages.MessageProcessor
    public void processMessage(AlexaServicesMessageType alexaServicesMessageType, Bundle bundle, @Nullable Messenger messenger) {
        if (!isClientVersionSupported(bundle)) {
            super.processMessage(alexaServicesMessageType, bundle, messenger);
            return;
        }
        try {
            ExtendedClient client = getClient(bundle);
            int ordinal = alexaServicesMessageType.ordinal();
            if (ordinal == 20) {
                setLocale(bundle);
            } else if (ordinal != 40) {
                switch (ordinal) {
                    case 65:
                        registerReadinessListener(bundle);
                        break;
                    case 66:
                        deregisterReadinessListener(bundle);
                        break;
                    case 67:
                        reply(messenger, alexaServicesMessageType, new ReadinessMessagePayload(client, this.alexaClient.getReadyState(client)).getBundle());
                        break;
                    case 68:
                        scheduleVisualTask(bundle);
                        break;
                    case 69:
                        unscheduleVisualTask(bundle);
                        break;
                    case 70:
                        setWakeSoundEnabled(bundle);
                        break;
                    case 71:
                        reply(messenger, alexaServicesMessageType, new WakeSoundEnabledPayload(client, this.alexaClient.isWakeSoundEnabled(client)).getBundle());
                        break;
                    case 72:
                        setEndpointSoundEnabled(bundle);
                        break;
                    case 73:
                        reply(messenger, alexaServicesMessageType, new EndPointSoundEnabledPayload(client, this.alexaClient.isEndpointSoundEnabled(client)).getBundle());
                        break;
                    case 74:
                        registerAttentionSystemSettingsListener(bundle);
                        break;
                    case 75:
                        deregisterAttentionSystemSettingsListener(bundle);
                        break;
                    case 76:
                        registerLocalesListener(bundle);
                        break;
                    case 77:
                        deregisterLocalesListener(bundle);
                        break;
                    case 78:
                        registerSupportedLocalesListener(bundle);
                        break;
                    case 79:
                        deregisterSupporterLocalesListener(bundle);
                        break;
                    case 80:
                        registerDriveModeListener(bundle);
                        break;
                    case 81:
                        deregisterDriveModeListener(bundle);
                        break;
                    case 82:
                        setDriveModeEnabled(bundle);
                        break;
                    case 83:
                        setDriveModeTheme(bundle);
                        break;
                    case 84:
                        registerTextResponseListener(bundle);
                        break;
                    case 85:
                        deregisterTextResponseListener(bundle);
                        break;
                    case 86:
                        sendTextMessage(bundle);
                        break;
                    case 87:
                        registerAlexaArtifactDownloadListener(bundle);
                        break;
                    case 88:
                        deregisterAlexaArtifactDownloadListener(bundle);
                        break;
                    case 89:
                        registerAlexaCaptionListener(bundle);
                        break;
                    case 90:
                        deregisterAlexaCaptionListener(bundle);
                        break;
                    case 91:
                        this.alexaClient.stopForegroundAudioTask(getClient(bundle));
                        break;
                    case 92:
                        registerMediaPlaybackResponseListener(bundle);
                        break;
                    case 93:
                        deregisterMediaPlaybackResponseListener(bundle);
                        break;
                    default:
                        switch (ordinal) {
                            case 102:
                                registerExpectTextListener(bundle);
                                break;
                            case 103:
                                deregisterExpectTextListener(bundle);
                                break;
                            case 104:
                                setDriveModeState(bundle);
                                break;
                            default:
                                super.processMessage(alexaServicesMessageType, bundle, messenger);
                                break;
                        }
                }
            } else {
                sendAlexaEvent(bundle);
            }
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to handle incoming message! ", e);
            this.errorReporter.zZm.notifyHandledException(new UYN.zZm(e));
        }
    }
}
