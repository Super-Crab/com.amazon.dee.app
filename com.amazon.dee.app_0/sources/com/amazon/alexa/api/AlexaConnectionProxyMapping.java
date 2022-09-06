package com.amazon.alexa.api;

import android.os.Bundle;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.utils.Provider;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class AlexaConnectionProxyMapping {
    private final AlexaConnectionProxyMapper<AlertsListener, MessageReceiver<e>> alertsListeners;
    private final v<AlexaApiCallbacks> alexaApiCallbacks;
    private final AlexaConnectionProxyMapper<AlexaArtifactDownloadListener, MessageReceiver<ApiType_ArtifactDownloadListenerMessageType>> artifactDownloadListener;
    private final AlexaConnectionProxyMapper<AlexaAttentionSystemListener, MessageReceiver<ApiType_AttentionSystemListenerMessageType>> attentionSystemListeners;
    private final AlexaConnectionProxyMapper<AlexaAttentionSystemSettingsListener, MessageReceiver<AlexaAttentionSystemSettingsMessageType>> attentionSystemSettingsListeners;
    private final AlexaConnectionProxyMapper<AlexaAudioPlaybackStatusListener, MessageReceiver<AlexaAudioPlaybackStatusListenerMessageType>> audioPlaybackStatusListeners;
    private final AlexaConnectionProxyMapper<AlexaCaptionListener, MessageReceiver<ApiType_CaptionListenerMessageType>> captionListeners;
    private final AlexaConnectionProxyMapper<AlexaCardListener, MessageReceiver<AlexaCardListenerMessageType>> cardListeners;
    private final AlexaConnectionProxyMapper<AlexaCardRendererListener, MessageReceiver<AlexaCardListenerMessageType>> cardRendererListeners;
    private final AlexaConnectionProxyMapper<AlexaContextsProvider, MessageReceiver<AlexaContextsProviderMessageType>> contextProviders;
    private final AlexaConnectionProxyMapper<AlexaDriveModeListener, MessageReceiver<ApiType_DriveModeListenerMessageType>> driveModeListeners;
    private final AlexaConnectionProxyMapper<AlexaExpectTextListener, MessageReceiver<ExpectTextMessageType>> expectTextListeners;
    private final AlexaConnectionProxyMapper<AlexaLocalesListener, MessageReceiver<ax>> localesListeners;
    private final AlexaConnectionProxyMapper<AlexaMediaPlaybackListener, MessageReceiver<ApiType_MediaPlaybackListenerMessageType>> mediaPlaybackListeners;
    private final AlexaConnectionProxyMapper<AlexaMetricsListener, MessageReceiver<be>> metricsListeners;
    private final AlexaConnectionProxyMapper<AlexaReadinessListener, MessageReceiver<AlexaReadinessMessageType>> readinessListeners;
    private final AlexaConnectionProxyMapper<AlexaSupportedLocalesListener, MessageReceiver<bs>> supportedLocalesListeners;
    private final AlexaConnectionProxyMapper<AlexaTextResponseListener, MessageReceiver<TextResponseListenerMessageType>> textResponseListeners;
    private final AlexaConnectionProxyMapper<AlexaUserSpeechProvider, MessageReceiver<AlexaUserSpeechProviderMessageType>> userSpeechProviders;
    private final AlexaConnectionProxyMapper<AlexaVisualTask, MessageReceiver<ca>> visualTasks;
    private final AlexaConnectionProxyMapper<AlexaWakeWordListener, MessageReceiver<AlexaWakeWordListenerMessageType>> wakeWordListeners;
    private final AlexaConnectionProxyMapper<AlexaUserSpeechListener, AlexaUserSpeechListenerProxy> userSpeechListeners = new AlexaConnectionProxyMapper<>();
    private final AlexaConnectionProxyMapper<AlexaAudioPlaybackListener, AlexaAudioPlaybackListenerProxy> audioPlaybackListeners = new AlexaConnectionProxyMapper<>();
    private final AlexaConnectionProxyMapper<AlexaSettingsListener, AlexaSettingsListenerProxy> settingsListeners = new AlexaConnectionProxyMapper<>();
    private final AlexaConnectionProxyMapper<AlexaPlayerInfoCardListener, AlexaPlayerInfoCardListenerProxy> playerInfoCardListeners = new AlexaConnectionProxyMapper<>();
    private final AlexaConnectionProxyMapper<AlexaContextProvider, AlexaContextProviderProxy> alexaContextProviders = new AlexaConnectionProxyMapper<>();
    private final AlexaConnectionProxyMapper<AlexaAudioInteraction, AlexaAudioInteractionProxy> alexaAudioInteractions = new AlexaConnectionProxyMapper<>();
    private final AlexaConnectionProxyMapper<AlexaUserPerceivedLatencyListener, UserPerceivedLatencyListenerProxy> uplListeners = new AlexaConnectionProxyMapper<>();
    private final AlexaConnectionProxyMapper<AlexaDialogControllerProxy, LegacyUserSpeechProvider> dialogControllers = new AlexaConnectionProxyMapper<>();
    private final AlexaConnectionProxyMapper<AlexaDialogControllerProxyV2, LegacyUserSpeechProvider> dialogControllersV2 = new AlexaConnectionProxyMapper<>();

    /* loaded from: classes6.dex */
    private static class CallbackRemovalListener implements ar<AlexaApiCallbacks> {
        private CallbackRemovalListener() {
        }

        @Override // com.amazon.alexa.api.ar
        public void onRemove(AlexaApiCallbacks alexaApiCallbacks) {
            alexaApiCallbacks.doOnFailure(ApiCallFailure.MessagingFailure.create("Disconnected from AlexaService"));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaConnectionProxyMapping(final Provider<MessageReceiversManager> provider) {
        this.contextProviders = new bb<AlexaContextsProvider, AlexaContextsProviderMessageType>(provider) { // from class: com.amazon.alexa.api.AlexaConnectionProxyMapping.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.amazon.alexa.api.bb
            public MessageProcessor<AlexaContextsProviderMessageType> createMessageProcessor(AlexaContextsProvider alexaContextsProvider) {
                return AlexaContextsProviderMessageProcessor.create(alexaContextsProvider);
            }
        };
        this.audioPlaybackStatusListeners = new bb<AlexaAudioPlaybackStatusListener, AlexaAudioPlaybackStatusListenerMessageType>(provider) { // from class: com.amazon.alexa.api.AlexaConnectionProxyMapping.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.amazon.alexa.api.bb
            public MessageProcessor<AlexaAudioPlaybackStatusListenerMessageType> createMessageProcessor(AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
                return AlexaAudioPlaybackStatusListenerProcessor.create(alexaAudioPlaybackStatusListener);
            }
        };
        this.alertsListeners = new bb<AlertsListener, e>(provider) { // from class: com.amazon.alexa.api.AlexaConnectionProxyMapping.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.amazon.alexa.api.bb
            public MessageProcessor<e> createMessageProcessor(AlertsListener alertsListener) {
                return d.a(alertsListener);
            }
        };
        this.metricsListeners = new bb<AlexaMetricsListener, be>(provider) { // from class: com.amazon.alexa.api.AlexaConnectionProxyMapping.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.amazon.alexa.api.bb
            public MessageProcessor<be> createMessageProcessor(AlexaMetricsListener alexaMetricsListener) {
                return bd.a(alexaMetricsListener);
            }
        };
        this.userSpeechProviders = new bb<AlexaUserSpeechProvider, AlexaUserSpeechProviderMessageType>(provider) { // from class: com.amazon.alexa.api.AlexaConnectionProxyMapping.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.amazon.alexa.api.bb
            public MessageProcessor<AlexaUserSpeechProviderMessageType> createMessageProcessor(AlexaUserSpeechProvider alexaUserSpeechProvider) {
                return new AlexaUserSpeechProviderMessageProcessor(alexaUserSpeechProvider, provider);
            }
        };
        this.cardRendererListeners = new bb<AlexaCardRendererListener, AlexaCardListenerMessageType>(provider) { // from class: com.amazon.alexa.api.AlexaConnectionProxyMapping.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.amazon.alexa.api.bb
            public MessageProcessor<AlexaCardListenerMessageType> createMessageProcessor(final AlexaCardRendererListener alexaCardRendererListener) {
                return AlexaCardListenerMessageProcessor.create(new AlexaCardListener() { // from class: com.amazon.alexa.api.AlexaConnectionProxyMapping.6.1
                    @Override // com.amazon.alexa.api.AlexaCardListener
                    public void onReceivedRenderCard(@NonNull String str, @NonNull AlexaCardExtras alexaCardExtras) {
                        alexaCardRendererListener.onReceivedRenderCard(str);
                    }
                });
            }
        };
        this.cardListeners = new bb<AlexaCardListener, AlexaCardListenerMessageType>(provider) { // from class: com.amazon.alexa.api.AlexaConnectionProxyMapping.7
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.amazon.alexa.api.bb
            public MessageProcessor<AlexaCardListenerMessageType> createMessageProcessor(AlexaCardListener alexaCardListener) {
                return AlexaCardListenerMessageProcessor.create(alexaCardListener);
            }
        };
        this.captionListeners = new bb<AlexaCaptionListener, ApiType_CaptionListenerMessageType>(provider) { // from class: com.amazon.alexa.api.AlexaConnectionProxyMapping.8
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.amazon.alexa.api.bb
            public MessageProcessor<ApiType_CaptionListenerMessageType> createMessageProcessor(AlexaCaptionListener alexaCaptionListener) {
                return new ApiType_CaptionListenerProcessor(alexaCaptionListener);
            }
        };
        this.readinessListeners = new bb<AlexaReadinessListener, AlexaReadinessMessageType>(provider) { // from class: com.amazon.alexa.api.AlexaConnectionProxyMapping.9
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.amazon.alexa.api.bb
            public MessageProcessor<AlexaReadinessMessageType> createMessageProcessor(AlexaReadinessListener alexaReadinessListener) {
                return AlexaReadinessMessageProcessor.create(alexaReadinessListener);
            }
        };
        this.visualTasks = new bb<AlexaVisualTask, ca>(provider) { // from class: com.amazon.alexa.api.AlexaConnectionProxyMapping.10
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.amazon.alexa.api.bb
            public MessageProcessor<ca> createMessageProcessor(AlexaVisualTask alexaVisualTask) {
                return cb.a(alexaVisualTask);
            }
        };
        this.localesListeners = new bb<AlexaLocalesListener, ax>(provider) { // from class: com.amazon.alexa.api.AlexaConnectionProxyMapping.11
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.amazon.alexa.api.bb
            public MessageProcessor<ax> createMessageProcessor(AlexaLocalesListener alexaLocalesListener) {
                return ay.a(alexaLocalesListener);
            }
        };
        this.supportedLocalesListeners = new bb<AlexaSupportedLocalesListener, bs>(provider) { // from class: com.amazon.alexa.api.AlexaConnectionProxyMapping.12
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.amazon.alexa.api.bb
            public MessageProcessor<bs> createMessageProcessor(AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
                return bt.a(alexaSupportedLocalesListener);
            }
        };
        this.artifactDownloadListener = new bb<AlexaArtifactDownloadListener, ApiType_ArtifactDownloadListenerMessageType>(provider) { // from class: com.amazon.alexa.api.AlexaConnectionProxyMapping.13
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.amazon.alexa.api.bb
            public MessageProcessor<ApiType_ArtifactDownloadListenerMessageType> createMessageProcessor(AlexaArtifactDownloadListener alexaArtifactDownloadListener) {
                return new ApiType_ArtifactDownloadListenerProcessor(alexaArtifactDownloadListener);
            }
        };
        this.driveModeListeners = new bb<AlexaDriveModeListener, ApiType_DriveModeListenerMessageType>(provider) { // from class: com.amazon.alexa.api.AlexaConnectionProxyMapping.14
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.amazon.alexa.api.bb
            public MessageProcessor<ApiType_DriveModeListenerMessageType> createMessageProcessor(AlexaDriveModeListener alexaDriveModeListener) {
                return new ApiType_DriveModeListenerProcessor(alexaDriveModeListener);
            }
        };
        this.attentionSystemSettingsListeners = new bb<AlexaAttentionSystemSettingsListener, AlexaAttentionSystemSettingsMessageType>(provider) { // from class: com.amazon.alexa.api.AlexaConnectionProxyMapping.15
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.amazon.alexa.api.bb
            public MessageProcessor<AlexaAttentionSystemSettingsMessageType> createMessageProcessor(AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener) {
                return l.a(alexaAttentionSystemSettingsListener);
            }
        };
        this.alexaApiCallbacks = new v<>(new x<AlexaApiCallbacks>(provider) { // from class: com.amazon.alexa.api.AlexaConnectionProxyMapping.16
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.api.x
            public w createBundlerFor(AlexaApiCallbacks alexaApiCallbacks) {
                return AlexaApiCallbacksBundler.create(provider, alexaApiCallbacks);
            }
        }, new CallbackRemovalListener());
        this.textResponseListeners = new bb<AlexaTextResponseListener, TextResponseListenerMessageType>(provider) { // from class: com.amazon.alexa.api.AlexaConnectionProxyMapping.17
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.amazon.alexa.api.bb
            public MessageProcessor<TextResponseListenerMessageType> createMessageProcessor(AlexaTextResponseListener alexaTextResponseListener) {
                return new TextResponseListenerProcessor(alexaTextResponseListener);
            }
        };
        this.mediaPlaybackListeners = new bb<AlexaMediaPlaybackListener, ApiType_MediaPlaybackListenerMessageType>(provider) { // from class: com.amazon.alexa.api.AlexaConnectionProxyMapping.18
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.amazon.alexa.api.bb
            public MessageProcessor<ApiType_MediaPlaybackListenerMessageType> createMessageProcessor(AlexaMediaPlaybackListener alexaMediaPlaybackListener) {
                return new ApiType_MediaPlaybackListenerProcessor(alexaMediaPlaybackListener);
            }
        };
        this.wakeWordListeners = new bb<AlexaWakeWordListener, AlexaWakeWordListenerMessageType>(provider) { // from class: com.amazon.alexa.api.AlexaConnectionProxyMapping.19
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.amazon.alexa.api.bb
            public MessageProcessor<AlexaWakeWordListenerMessageType> createMessageProcessor(AlexaWakeWordListener alexaWakeWordListener) {
                return new AlexaWakeWordListenerMessageProcessor(alexaWakeWordListener);
            }
        };
        this.expectTextListeners = new bb<AlexaExpectTextListener, ExpectTextMessageType>(provider) { // from class: com.amazon.alexa.api.AlexaConnectionProxyMapping.20
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.amazon.alexa.api.bb
            public MessageProcessor<ExpectTextMessageType> createMessageProcessor(AlexaExpectTextListener alexaExpectTextListener) {
                return new AlexaExpectTextListenerProcessor(alexaExpectTextListener);
            }
        };
        this.attentionSystemListeners = new bb<AlexaAttentionSystemListener, ApiType_AttentionSystemListenerMessageType>(provider) { // from class: com.amazon.alexa.api.AlexaConnectionProxyMapping.21
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.amazon.alexa.api.bb
            public MessageProcessor<ApiType_AttentionSystemListenerMessageType> createMessageProcessor(AlexaAttentionSystemListener alexaAttentionSystemListener) {
                return new ApiType_AttentionSystemListenerProcessor(alexaAttentionSystemListener);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaAudioInteractionProxy addAudioInteraction(AlexaAudioInteraction alexaAudioInteraction, AlexaAudioInteractionProxy alexaAudioInteractionProxy) {
        return this.alexaAudioInteractions.put(alexaAudioInteraction, alexaAudioInteractionProxy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaContextProviderProxy addContextProvider(AlexaContextProvider alexaContextProvider, AlexaContextProviderProxy alexaContextProviderProxy) {
        return this.alexaContextProviders.put(alexaContextProvider, alexaContextProviderProxy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaAudioPlaybackListenerProxy addListener(AlexaAudioPlaybackListener alexaAudioPlaybackListener, AlexaAudioPlaybackListenerProxy alexaAudioPlaybackListenerProxy) {
        return this.audioPlaybackListeners.put(alexaAudioPlaybackListener, alexaAudioPlaybackListenerProxy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaPlayerInfoCardListenerProxy addListener(AlexaPlayerInfoCardListener alexaPlayerInfoCardListener, AlexaPlayerInfoCardListenerProxy alexaPlayerInfoCardListenerProxy) {
        return this.playerInfoCardListeners.put(alexaPlayerInfoCardListener, alexaPlayerInfoCardListenerProxy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaSettingsListenerProxy addListener(AlexaSettingsListener alexaSettingsListener, AlexaSettingsListenerProxy alexaSettingsListenerProxy) {
        return this.settingsListeners.put(alexaSettingsListener, alexaSettingsListenerProxy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaUserSpeechListenerProxy addListener(AlexaUserSpeechListener alexaUserSpeechListener, AlexaUserSpeechListenerProxy alexaUserSpeechListenerProxy) {
        return this.userSpeechListeners.put(alexaUserSpeechListener, alexaUserSpeechListenerProxy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UserPerceivedLatencyListenerProxy addListener(AlexaUserPerceivedLatencyListener alexaUserPerceivedLatencyListener, UserPerceivedLatencyListenerProxy userPerceivedLatencyListenerProxy) {
        return this.uplListeners.put(alexaUserPerceivedLatencyListener, userPerceivedLatencyListenerProxy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearInternalState() {
        this.userSpeechListeners.clear();
        this.audioPlaybackListeners.clear();
        this.settingsListeners.clear();
        this.playerInfoCardListeners.clear();
        this.alexaContextProviders.clear();
        this.alexaAudioInteractions.clear();
        this.uplListeners.clear();
        this.contextProviders.clear();
        this.audioPlaybackStatusListeners.clear();
        this.alertsListeners.clear();
        this.metricsListeners.clear();
        this.userSpeechProviders.clear();
        this.dialogControllers.clear();
        this.dialogControllersV2.clear();
        this.cardRendererListeners.clear();
        this.cardListeners.clear();
        this.readinessListeners.clear();
        this.visualTasks.clear();
        this.attentionSystemSettingsListeners.clear();
        this.localesListeners.clear();
        this.supportedLocalesListeners.clear();
        this.artifactDownloadListener.clear();
        this.driveModeListeners.clear();
        this.alexaApiCallbacks.clear();
        this.textResponseListeners.clear();
        this.mediaPlaybackListeners.clear();
        this.wakeWordListeners.clear();
        this.expectTextListeners.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bundle getAlexaApiCallbacks(AlexaApiCallbacks alexaApiCallbacks) {
        return this.alexaApiCallbacks.get(alexaApiCallbacks);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<AlexaUserPerceivedLatencyListener> getAllUplListeners() {
        return this.uplListeners.keySet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaContextProviderProxy getContextProvider(AlexaContextProvider alexaContextProvider) {
        return this.alexaContextProviders.get(alexaContextProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaContextsProviderMessageType> getContextsProviderMessageReceiver(AlexaContextsProvider alexaContextsProvider) {
        return this.contextProviders.get(alexaContextsProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LegacyUserSpeechProvider getDialogController(AlexaDialogControllerProxy alexaDialogControllerProxy) {
        return this.dialogControllers.get(alexaDialogControllerProxy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LegacyUserSpeechProvider getDialogController(AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2) {
        return this.dialogControllersV2.get(alexaDialogControllerProxyV2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<e> getListener(AlertsListener alertsListener) {
        return this.alertsListeners.get(alertsListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ApiType_ArtifactDownloadListenerMessageType> getListener(AlexaArtifactDownloadListener alexaArtifactDownloadListener) {
        return this.artifactDownloadListener.get(alexaArtifactDownloadListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ApiType_AttentionSystemListenerMessageType> getListener(AlexaAttentionSystemListener alexaAttentionSystemListener) {
        return this.attentionSystemListeners.get(alexaAttentionSystemListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaAttentionSystemSettingsMessageType> getListener(AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener) {
        return this.attentionSystemSettingsListeners.get(alexaAttentionSystemSettingsListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaAudioPlaybackStatusListenerMessageType> getListener(AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
        return this.audioPlaybackStatusListeners.get(alexaAudioPlaybackStatusListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ApiType_CaptionListenerMessageType> getListener(AlexaCaptionListener alexaCaptionListener) {
        return this.captionListeners.get(alexaCaptionListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaCardListenerMessageType> getListener(AlexaCardListener alexaCardListener) {
        return this.cardListeners.get(alexaCardListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public MessageReceiver<AlexaCardListenerMessageType> getListener(AlexaCardRendererListener alexaCardRendererListener) {
        return this.cardRendererListeners.get(alexaCardRendererListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ApiType_DriveModeListenerMessageType> getListener(AlexaDriveModeListener alexaDriveModeListener) {
        return this.driveModeListeners.get(alexaDriveModeListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ExpectTextMessageType> getListener(AlexaExpectTextListener alexaExpectTextListener) {
        return this.expectTextListeners.get(alexaExpectTextListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ax> getListener(AlexaLocalesListener alexaLocalesListener) {
        return this.localesListeners.get(alexaLocalesListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ApiType_MediaPlaybackListenerMessageType> getListener(AlexaMediaPlaybackListener alexaMediaPlaybackListener) {
        return this.mediaPlaybackListeners.get(alexaMediaPlaybackListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<be> getListener(AlexaMetricsListener alexaMetricsListener) {
        return this.metricsListeners.get(alexaMetricsListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaReadinessMessageType> getListener(AlexaReadinessListener alexaReadinessListener) {
        return this.readinessListeners.get(alexaReadinessListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<bs> getListener(AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
        return this.supportedLocalesListeners.get(alexaSupportedLocalesListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<TextResponseListenerMessageType> getListener(AlexaTextResponseListener alexaTextResponseListener) {
        return this.textResponseListeners.get(alexaTextResponseListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ca> getListener(AlexaVisualTask alexaVisualTask) {
        return this.visualTasks.get(alexaVisualTask);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaWakeWordListenerMessageType> getListener(AlexaWakeWordListener alexaWakeWordListener) {
        return this.wakeWordListeners.get(alexaWakeWordListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaUserSpeechProviderMessageType> getUserSpeechProviderMessageReceiver(AlexaUserSpeechProvider alexaUserSpeechProvider) {
        return this.userSpeechProviders.get(alexaUserSpeechProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isAudioInteractionScheduled(AlexaAudioInteraction alexaAudioInteraction) {
        return this.alexaAudioInteractions.containsKey(alexaAudioInteraction);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isVisualTaskScheduled(AlexaVisualTask alexaVisualTask) {
        return this.visualTasks.containsKey(alexaVisualTask);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void putDialogController(AlexaDialogControllerProxy alexaDialogControllerProxy, LegacyUserSpeechProvider legacyUserSpeechProvider) {
        this.dialogControllers.put(alexaDialogControllerProxy, legacyUserSpeechProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void putDialogController(AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2, LegacyUserSpeechProvider legacyUserSpeechProvider) {
        this.dialogControllersV2.put(alexaDialogControllerProxyV2, legacyUserSpeechProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bundle removeAlexaApiCallbacks(AlexaApiCallbacks alexaApiCallbacks) {
        return this.alexaApiCallbacks.remove(alexaApiCallbacks);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaAudioInteractionProxy removeAudioInteraction(AlexaAudioInteraction alexaAudioInteraction) {
        return this.alexaAudioInteractions.remove(alexaAudioInteraction);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaContextProviderProxy removeContextProvider(AlexaContextProvider alexaContextProvider) {
        return this.alexaContextProviders.remove(alexaContextProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaContextsProviderMessageType> removeContextProviderMessageReceiver(AlexaContextsProvider alexaContextsProvider) {
        return this.contextProviders.remove(alexaContextsProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LegacyUserSpeechProvider removeDialogController(AlexaDialogControllerProxy alexaDialogControllerProxy) {
        return this.dialogControllers.remove(alexaDialogControllerProxy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LegacyUserSpeechProvider removeDialogController(AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2) {
        return this.dialogControllersV2.remove(alexaDialogControllerProxyV2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaAudioPlaybackListenerProxy removeListener(AlexaAudioPlaybackListener alexaAudioPlaybackListener) {
        return this.audioPlaybackListeners.remove(alexaAudioPlaybackListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaPlayerInfoCardListenerProxy removeListener(AlexaPlayerInfoCardListener alexaPlayerInfoCardListener) {
        return this.playerInfoCardListeners.remove(alexaPlayerInfoCardListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaSettingsListenerProxy removeListener(AlexaSettingsListener alexaSettingsListener) {
        return this.settingsListeners.remove(alexaSettingsListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaUserSpeechListenerProxy removeListener(AlexaUserSpeechListener alexaUserSpeechListener) {
        return this.userSpeechListeners.remove(alexaUserSpeechListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UserPerceivedLatencyListenerProxy removeListener(AlexaUserPerceivedLatencyListener alexaUserPerceivedLatencyListener) {
        return this.uplListeners.remove(alexaUserPerceivedLatencyListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<e> removeListener(AlertsListener alertsListener) {
        return this.alertsListeners.remove(alertsListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ApiType_ArtifactDownloadListenerMessageType> removeListener(AlexaArtifactDownloadListener alexaArtifactDownloadListener) {
        return this.artifactDownloadListener.remove(alexaArtifactDownloadListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ApiType_AttentionSystemListenerMessageType> removeListener(AlexaAttentionSystemListener alexaAttentionSystemListener) {
        return this.attentionSystemListeners.remove(alexaAttentionSystemListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaAttentionSystemSettingsMessageType> removeListener(AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener) {
        return this.attentionSystemSettingsListeners.remove(alexaAttentionSystemSettingsListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaAudioPlaybackStatusListenerMessageType> removeListener(AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
        return this.audioPlaybackStatusListeners.remove(alexaAudioPlaybackStatusListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ApiType_CaptionListenerMessageType> removeListener(AlexaCaptionListener alexaCaptionListener) {
        return this.captionListeners.remove(alexaCaptionListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaCardListenerMessageType> removeListener(AlexaCardListener alexaCardListener) {
        return this.cardListeners.remove(alexaCardListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public MessageReceiver<AlexaCardListenerMessageType> removeListener(AlexaCardRendererListener alexaCardRendererListener) {
        return this.cardRendererListeners.remove(alexaCardRendererListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ApiType_DriveModeListenerMessageType> removeListener(AlexaDriveModeListener alexaDriveModeListener) {
        return this.driveModeListeners.remove(alexaDriveModeListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ExpectTextMessageType> removeListener(AlexaExpectTextListener alexaExpectTextListener) {
        return this.expectTextListeners.remove(alexaExpectTextListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ax> removeListener(AlexaLocalesListener alexaLocalesListener) {
        return this.localesListeners.remove(alexaLocalesListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ApiType_MediaPlaybackListenerMessageType> removeListener(AlexaMediaPlaybackListener alexaMediaPlaybackListener) {
        return this.mediaPlaybackListeners.remove(alexaMediaPlaybackListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<be> removeListener(AlexaMetricsListener alexaMetricsListener) {
        return this.metricsListeners.remove(alexaMetricsListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaReadinessMessageType> removeListener(AlexaReadinessListener alexaReadinessListener) {
        return this.readinessListeners.remove(alexaReadinessListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<bs> removeListener(AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
        return this.supportedLocalesListeners.remove(alexaSupportedLocalesListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<TextResponseListenerMessageType> removeListener(AlexaTextResponseListener alexaTextResponseListener) {
        return this.textResponseListeners.remove(alexaTextResponseListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ca> removeListener(AlexaVisualTask alexaVisualTask) {
        return this.visualTasks.remove(alexaVisualTask);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaWakeWordListenerMessageType> removeListener(AlexaWakeWordListener alexaWakeWordListener) {
        return this.wakeWordListeners.remove(alexaWakeWordListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaUserSpeechProviderMessageType> removeUserSpeechProviderMessageReceiver(AlexaUserSpeechProvider alexaUserSpeechProvider) {
        return this.userSpeechProviders.remove(alexaUserSpeechProvider);
    }
}
