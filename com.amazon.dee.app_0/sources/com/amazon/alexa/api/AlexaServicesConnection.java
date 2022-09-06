package com.amazon.alexa.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.AlexaConnection;
import com.amazon.alexa.api.AlexaServices;
import com.amazon.alexa.api.ManagedServiceConnection;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.Provider;
import com.amazon.alexa.utils.security.SignatureVerifier;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Set;
/* loaded from: classes6.dex */
public class AlexaServicesConnection extends AlexaConnection<AlexaServicesMessageSender> {
    private static final String TAG = "AlexaServicesConnection";
    private boolean allowsBackgroundActivityStarts;
    private boolean keepAlive;
    private final AlexaConnectionProxyMapping mapping;

    /* loaded from: classes6.dex */
    public interface ConnectionListener extends ManagedServiceConnection.ConnectionListener {
    }

    public AlexaServicesConnection(@NonNull Context context) {
        this(context, null);
    }

    AlexaServicesConnection(@NonNull Context context, @NonNull SignatureVerifier signatureVerifier, @NonNull Handler handler, @NonNull Handler handler2) {
        super(context, signatureVerifier, handler, handler2);
        this.keepAlive = false;
        this.allowsBackgroundActivityStarts = false;
        this.mapping = new AlexaConnectionProxyMapping(getMessageReceiversManagerProvider());
    }

    public AlexaServicesConnection(@NonNull Context context, @Nullable String str) {
        super(context, str == null ? getCallingClass() : str);
        this.keepAlive = false;
        this.allowsBackgroundActivityStarts = false;
        this.mapping = new AlexaConnectionProxyMapping(getMessageReceiversManagerProvider());
    }

    private static String getCallingClass() {
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            String className = stackTraceElement.getClassName();
            if (className.startsWith("com.amazon") && !className.startsWith("com.amazon.alexa.api")) {
                return className;
            }
        }
        return null;
    }

    private Provider<MessageReceiversManager> getMessageReceiversManagerProvider() {
        return new Provider<MessageReceiversManager>() { // from class: com.amazon.alexa.api.AlexaServicesConnection.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.utils.Provider
            /* renamed from: get */
            public MessageReceiversManager mo2864get() {
                return AlexaServicesConnection.this.getMessageReceiversManager();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaAudioInteractionProxy addAudioInteraction(AlexaAudioInteraction alexaAudioInteraction, AlexaAudioInteractionProxy alexaAudioInteractionProxy) {
        return this.mapping.addAudioInteraction(alexaAudioInteraction, alexaAudioInteractionProxy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaContextProviderProxy addContextProvider(AlexaContextProvider alexaContextProvider, AlexaContextProviderProxy alexaContextProviderProxy) {
        return this.mapping.addContextProvider(alexaContextProvider, alexaContextProviderProxy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public AlexaAudioPlaybackListenerProxy addListener(AlexaAudioPlaybackListener alexaAudioPlaybackListener, AlexaAudioPlaybackListenerProxy alexaAudioPlaybackListenerProxy) {
        return this.mapping.addListener(alexaAudioPlaybackListener, alexaAudioPlaybackListenerProxy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaPlayerInfoCardListenerProxy addListener(AlexaPlayerInfoCardListener alexaPlayerInfoCardListener, AlexaPlayerInfoCardListenerProxy alexaPlayerInfoCardListenerProxy) {
        return this.mapping.addListener(alexaPlayerInfoCardListener, alexaPlayerInfoCardListenerProxy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaSettingsListenerProxy addListener(AlexaSettingsListener alexaSettingsListener, AlexaSettingsListenerProxy alexaSettingsListenerProxy) {
        return this.mapping.addListener(alexaSettingsListener, alexaSettingsListenerProxy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaUserSpeechListenerProxy addListener(AlexaUserSpeechListener alexaUserSpeechListener, AlexaUserSpeechListenerProxy alexaUserSpeechListenerProxy) {
        return this.mapping.addListener(alexaUserSpeechListener, alexaUserSpeechListenerProxy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UserPerceivedLatencyListenerProxy addListener(AlexaUserPerceivedLatencyListener alexaUserPerceivedLatencyListener, UserPerceivedLatencyListenerProxy userPerceivedLatencyListenerProxy) {
        return this.mapping.addListener(alexaUserPerceivedLatencyListener, userPerceivedLatencyListenerProxy);
    }

    @Override // com.amazon.alexa.api.AlexaConnection, com.amazon.alexa.api.ManagedServiceConnection
    public void connect() {
        super.connect();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.api.ManagedServiceConnection
    /* renamed from: createServiceInterface */
    public AlexaServicesMessageSender mo811createServiceInterface(IBinder iBinder) {
        return new AlexaServicesMessageSender(iBinder, getMessageReceiversManager());
    }

    @Override // com.amazon.alexa.api.AlexaConnection
    protected void deregisterClientConnectionController(MessageReceiver<ClientConnectionControllerMessageType> messageReceiver) {
        try {
            AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) this.serviceInterface.get();
            if (alexaServicesMessageSender == null) {
                return;
            }
            alexaServicesMessageSender.deregisterClientConnectionController(getClient(), messageReceiver);
        } catch (RemoteException e) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception while deregistering connection manager: ");
            outline107.append(e.getMessage());
            Log.e(str, outline107.toString());
        }
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public void deregisterListener(@NonNull ManagedServiceConnection.ConnectionListener connectionListener) {
        super.deregisterListener(connectionListener);
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public void disconnect() {
        super.disconnect();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<AlexaUserPerceivedLatencyListener> getAllUplListeners() {
        return this.mapping.getAllUplListeners();
    }

    @Override // com.amazon.alexa.api.AlexaConnection
    public /* bridge */ /* synthetic */ AlexaConnection.BroadcastSender getBroadcastSender() {
        return super.getBroadcastSender();
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    protected Intent getConnectionIntent(ComponentName componentName, boolean z) {
        return AlexaServiceIntentFactory.createIntent(getClient(), componentName, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaContextProviderProxy getContextProvider(AlexaContextProvider alexaContextProvider) {
        return this.mapping.getContextProvider(alexaContextProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaContextsProviderMessageType> getContextsProviderMessageReceiver(AlexaContextsProvider alexaContextsProvider) {
        return this.mapping.getContextsProviderMessageReceiver(alexaContextsProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LegacyUserSpeechProvider getDialogController(AlexaDialogControllerProxy alexaDialogControllerProxy) {
        return this.mapping.getDialogController(alexaDialogControllerProxy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LegacyUserSpeechProvider getDialogController(AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2) {
        return this.mapping.getDialogController(alexaDialogControllerProxyV2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<e> getListener(AlertsListener alertsListener) {
        return this.mapping.getListener(alertsListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ApiType_ArtifactDownloadListenerMessageType> getListener(AlexaArtifactDownloadListener alexaArtifactDownloadListener) {
        return this.mapping.getListener(alexaArtifactDownloadListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ApiType_AttentionSystemListenerMessageType> getListener(AlexaAttentionSystemListener alexaAttentionSystemListener) {
        return this.mapping.getListener(alexaAttentionSystemListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaAttentionSystemSettingsMessageType> getListener(AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener) {
        return this.mapping.getListener(alexaAttentionSystemSettingsListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaAudioPlaybackStatusListenerMessageType> getListener(AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
        return this.mapping.getListener(alexaAudioPlaybackStatusListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ApiType_CaptionListenerMessageType> getListener(AlexaCaptionListener alexaCaptionListener) {
        return this.mapping.getListener(alexaCaptionListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaCardListenerMessageType> getListener(AlexaCardListener alexaCardListener) {
        return this.mapping.getListener(alexaCardListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public MessageReceiver<AlexaCardListenerMessageType> getListener(AlexaCardRendererListener alexaCardRendererListener) {
        return this.mapping.getListener(alexaCardRendererListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ApiType_DriveModeListenerMessageType> getListener(AlexaDriveModeListener alexaDriveModeListener) {
        return this.mapping.getListener(alexaDriveModeListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ExpectTextMessageType> getListener(AlexaExpectTextListener alexaExpectTextListener) {
        return this.mapping.getListener(alexaExpectTextListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ax> getListener(AlexaLocalesListener alexaLocalesListener) {
        return this.mapping.getListener(alexaLocalesListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ApiType_MediaPlaybackListenerMessageType> getListener(AlexaMediaPlaybackListener alexaMediaPlaybackListener) {
        return this.mapping.getListener(alexaMediaPlaybackListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<be> getListener(AlexaMetricsListener alexaMetricsListener) {
        return this.mapping.getListener(alexaMetricsListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaReadinessMessageType> getListener(AlexaReadinessListener alexaReadinessListener) {
        return this.mapping.getListener(alexaReadinessListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<bs> getListener(AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
        return this.mapping.getListener(alexaSupportedLocalesListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<TextResponseListenerMessageType> getListener(AlexaTextResponseListener alexaTextResponseListener) {
        return this.mapping.getListener(alexaTextResponseListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ca> getListener(AlexaVisualTask alexaVisualTask) {
        return this.mapping.getListener(alexaVisualTask);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaWakeWordListenerMessageType> getListener(AlexaWakeWordListener alexaWakeWordListener) {
        return this.mapping.getListener(alexaWakeWordListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaConnectionProxyMapping getMapping() {
        return this.mapping;
    }

    @Override // com.amazon.alexa.api.AlexaConnection
    protected String getServiceName() {
        return getContext().getString(R.string.alexa_service_component_name);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaUserSpeechProviderMessageType> getUserSpeechProviderMessageReceiver(AlexaUserSpeechProvider alexaUserSpeechProvider) {
        return this.mapping.getUserSpeechProviderMessageReceiver(alexaUserSpeechProvider);
    }

    @Override // com.amazon.alexa.api.AlexaConnection
    public /* bridge */ /* synthetic */ void initialize() {
        super.initialize();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isAudioInteractionScheduled(AlexaAudioInteraction alexaAudioInteraction) {
        return this.mapping.isAudioInteractionScheduled(alexaAudioInteraction);
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public boolean isConnected() {
        return super.isConnected();
    }

    @Override // com.amazon.alexa.api.AlexaConnection
    protected boolean isUserLoggedIn() {
        return AlexaServices.Account.isUserLoggedIn(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isVisualTaskScheduled(AlexaVisualTask alexaVisualTask) {
        return this.mapping.isVisualTaskScheduled(alexaVisualTask);
    }

    @Override // com.amazon.alexa.api.AlexaConnection
    protected void onClientDisconnected() {
        super.onClientDisconnected();
        AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) this.serviceInterface.get();
        if (alexaServicesMessageSender != null) {
            try {
                try {
                    alexaServicesMessageSender.onClientDisconnect(getClient());
                } catch (RemoteException e) {
                    Log.e(TAG, "Unable to send client disconnect", e);
                }
            } finally {
                this.mapping.clearInternalState();
            }
        }
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    protected boolean preferBackgroundService() {
        return !this.keepAlive;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void putDialogController(AlexaDialogControllerProxy alexaDialogControllerProxy, LegacyUserSpeechProvider legacyUserSpeechProvider) {
        this.mapping.putDialogController(alexaDialogControllerProxy, legacyUserSpeechProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void putDialogController(AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2, LegacyUserSpeechProvider legacyUserSpeechProvider) {
        this.mapping.putDialogController(alexaDialogControllerProxyV2, legacyUserSpeechProvider);
    }

    @Override // com.amazon.alexa.api.AlexaConnection
    protected void registerClientConnectionController(MessageReceiver<ClientConnectionControllerMessageType> messageReceiver) {
        try {
            ((AlexaServicesMessageSender) this.serviceInterface.get()).onClientConnect(getClient(), this.keepAlive, messageReceiver, this.allowsBackgroundActivityStarts, null, shouldForceCapabilitiesRefresh());
        } catch (RemoteException e) {
            Log.e(TAG, "Unable to register required listeners", e);
            disconnect();
        }
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public void registerListener(@NonNull ManagedServiceConnection.ConnectionListener connectionListener) {
        super.registerListener(connectionListener);
    }

    @Override // com.amazon.alexa.api.AlexaConnection, com.amazon.alexa.api.ManagedServiceConnection
    public /* bridge */ /* synthetic */ void release() {
        super.release();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaAudioInteractionProxy removeAudioInteraction(AlexaAudioInteraction alexaAudioInteraction) {
        return this.mapping.removeAudioInteraction(alexaAudioInteraction);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaContextProviderProxy removeContextProvider(AlexaContextProvider alexaContextProvider) {
        return this.mapping.removeContextProvider(alexaContextProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaContextsProviderMessageType> removeContextProviderMessageReceiver(AlexaContextsProvider alexaContextsProvider) {
        return this.mapping.removeContextProviderMessageReceiver(alexaContextsProvider);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LegacyUserSpeechProvider removeDialogController(AlexaDialogControllerProxy alexaDialogControllerProxy) {
        return this.mapping.removeDialogController(alexaDialogControllerProxy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LegacyUserSpeechProvider removeDialogController(AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2) {
        return this.mapping.removeDialogController(alexaDialogControllerProxyV2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public AlexaAudioPlaybackListenerProxy removeListener(AlexaAudioPlaybackListener alexaAudioPlaybackListener) {
        return this.mapping.removeListener(alexaAudioPlaybackListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaPlayerInfoCardListenerProxy removeListener(AlexaPlayerInfoCardListener alexaPlayerInfoCardListener) {
        return this.mapping.removeListener(alexaPlayerInfoCardListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaSettingsListenerProxy removeListener(AlexaSettingsListener alexaSettingsListener) {
        return this.mapping.removeListener(alexaSettingsListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaUserSpeechListenerProxy removeListener(AlexaUserSpeechListener alexaUserSpeechListener) {
        return this.mapping.removeListener(alexaUserSpeechListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UserPerceivedLatencyListenerProxy removeListener(AlexaUserPerceivedLatencyListener alexaUserPerceivedLatencyListener) {
        return this.mapping.removeListener(alexaUserPerceivedLatencyListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<e> removeListener(AlertsListener alertsListener) {
        return this.mapping.removeListener(alertsListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ApiType_ArtifactDownloadListenerMessageType> removeListener(AlexaArtifactDownloadListener alexaArtifactDownloadListener) {
        return this.mapping.removeListener(alexaArtifactDownloadListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ApiType_AttentionSystemListenerMessageType> removeListener(AlexaAttentionSystemListener alexaAttentionSystemListener) {
        return this.mapping.removeListener(alexaAttentionSystemListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaAttentionSystemSettingsMessageType> removeListener(AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener) {
        return this.mapping.removeListener(alexaAttentionSystemSettingsListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaAudioPlaybackStatusListenerMessageType> removeListener(AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
        return this.mapping.removeListener(alexaAudioPlaybackStatusListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ApiType_CaptionListenerMessageType> removeListener(AlexaCaptionListener alexaCaptionListener) {
        return this.mapping.removeListener(alexaCaptionListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaCardListenerMessageType> removeListener(AlexaCardListener alexaCardListener) {
        return this.mapping.removeListener(alexaCardListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public MessageReceiver<AlexaCardListenerMessageType> removeListener(AlexaCardRendererListener alexaCardRendererListener) {
        return this.mapping.removeListener(alexaCardRendererListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ApiType_DriveModeListenerMessageType> removeListener(AlexaDriveModeListener alexaDriveModeListener) {
        return this.mapping.removeListener(alexaDriveModeListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ExpectTextMessageType> removeListener(AlexaExpectTextListener alexaExpectTextListener) {
        return this.mapping.removeListener(alexaExpectTextListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ax> removeListener(AlexaLocalesListener alexaLocalesListener) {
        return this.mapping.removeListener(alexaLocalesListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ApiType_MediaPlaybackListenerMessageType> removeListener(AlexaMediaPlaybackListener alexaMediaPlaybackListener) {
        return this.mapping.removeListener(alexaMediaPlaybackListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<be> removeListener(AlexaMetricsListener alexaMetricsListener) {
        return this.mapping.removeListener(alexaMetricsListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaReadinessMessageType> removeListener(AlexaReadinessListener alexaReadinessListener) {
        return this.mapping.removeListener(alexaReadinessListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<bs> removeListener(AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
        return this.mapping.removeListener(alexaSupportedLocalesListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<TextResponseListenerMessageType> removeListener(AlexaTextResponseListener alexaTextResponseListener) {
        return this.mapping.removeListener(alexaTextResponseListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<ca> removeListener(AlexaVisualTask alexaVisualTask) {
        return this.mapping.removeListener(alexaVisualTask);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaWakeWordListenerMessageType> removeListener(AlexaWakeWordListener alexaWakeWordListener) {
        return this.mapping.removeListener(alexaWakeWordListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MessageReceiver<AlexaUserSpeechProviderMessageType> removeUserSpeechProviderMessageReceiver(AlexaUserSpeechProvider alexaUserSpeechProvider) {
        return this.mapping.removeUserSpeechProviderMessageReceiver(alexaUserSpeechProvider);
    }

    public void setAllowsBackgroundActivityStarts(boolean z) {
        this.allowsBackgroundActivityStarts = z;
    }

    public void setKeepAlive(boolean z) {
        this.keepAlive = z;
    }

    protected boolean shouldForceCapabilitiesRefresh() {
        return false;
    }
}
