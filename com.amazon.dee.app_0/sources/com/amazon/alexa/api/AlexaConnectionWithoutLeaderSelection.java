package com.amazon.alexa.api;

import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import com.amazon.alexa.api.AlexaSettingsListenerProxy;
import com.amazon.alexa.api.Releasable;
import com.amazon.alexa.api.messages.MessageReceiversManager;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.utils.ApiThreadHelper;
import com.amazon.alexa.utils.security.SignatureVerifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public abstract class AlexaConnectionWithoutLeaderSelection<T extends Releasable> extends ManagedServiceConnection<T> {
    private final Map<AlexaDialogController, AlexaDialogControllerProxy> alexaDialogControllers;
    private final Map<AlexaDialogControllerV2, AlexaDialogControllerProxyV2> alexaDialogControllersV2;
    private final Map<AlexaSettingsListener, AlexaSettingsListenerProxy> alexaSettingsListeners;
    private MessageReceiver<as> disconnectMessageReceiver;
    private volatile boolean hasUserLoggedIn;
    private ComponentName serviceComponentName;
    private final Map<AlexaStateListener, AlexaStateListenerProxy> stateListeners;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaConnectionWithoutLeaderSelection(@NonNull Context context) {
        super(context);
        this.stateListeners = new ConcurrentHashMap();
        this.alexaDialogControllers = new ConcurrentHashMap();
        this.alexaDialogControllersV2 = new ConcurrentHashMap();
        this.alexaSettingsListeners = new ConcurrentHashMap();
        this.serviceComponentName = new ComponentName(context.getString(R.string.alexa_services_package_name), getServiceName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaConnectionWithoutLeaderSelection(@NonNull Context context, @NonNull SignatureVerifier signatureVerifier, @NonNull Handler handler, @NonNull Handler handler2) {
        super(context, signatureVerifier, handler, handler2);
        this.stateListeners = new ConcurrentHashMap();
        this.alexaDialogControllers = new ConcurrentHashMap();
        this.alexaDialogControllersV2 = new ConcurrentHashMap();
        this.alexaSettingsListeners = new ConcurrentHashMap();
        this.serviceComponentName = new ComponentName(context.getString(R.string.alexa_services_package_name), getServiceName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doDeregisterForceDisconnectListener() {
        MessageReceiver<as> messageReceiver = this.disconnectMessageReceiver;
        if (messageReceiver != null) {
            deregisterForceDisconnectListener(messageReceiver);
            getMessageReceiversManager().removeMessageReceiver(this.disconnectMessageReceiver);
            this.disconnectMessageReceiver = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaStateListenerProxy addListener(AlexaStateListener alexaStateListener, AlexaStateListenerProxy alexaStateListenerProxy) {
        return this.stateListeners.put(alexaStateListener, alexaStateListenerProxy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaDialogControllerProxy addProxy(AlexaDialogController alexaDialogController, AlexaDialogControllerProxy alexaDialogControllerProxy) {
        return this.alexaDialogControllers.put(alexaDialogController, alexaDialogControllerProxy);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaDialogControllerProxyV2 addProxy(AlexaDialogControllerV2 alexaDialogControllerV2, AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2) {
        return this.alexaDialogControllersV2.put(alexaDialogControllerV2, alexaDialogControllerProxyV2);
    }

    MessageReceiver<as> createForceDisconnectMessageReceiver() {
        return getMessageReceiversManager().createMessageReceiver(new ForceDisconnectMessageProcessor() { // from class: com.amazon.alexa.api.AlexaConnectionWithoutLeaderSelection.3
            @Override // com.amazon.alexa.api.ForceDisconnectMessageProcessor
            public void onForceDisconnect() {
                AlexaConnectionWithoutLeaderSelection.this.disconnect();
            }
        });
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    protected MessageReceiversManager createMessageReceiverManager() {
        return new MessageReceiversManager(getSignatureVerifier());
    }

    protected abstract void deregisterForceDisconnectListener(MessageReceiver<as> messageReceiver);

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaDialogControllerProxy getProxy(AlexaDialogController alexaDialogController) {
        return this.alexaDialogControllers.get(alexaDialogController);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaDialogControllerProxyV2 getProxy(AlexaDialogControllerV2 alexaDialogControllerV2) {
        return this.alexaDialogControllersV2.get(alexaDialogControllerV2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaSettingsListenerProxy getProxy(final AlexaSettingsListener alexaSettingsListener) {
        if (!this.alexaSettingsListeners.containsKey(alexaSettingsListener)) {
            this.alexaSettingsListeners.put(alexaSettingsListener, new AlexaSettingsListenerProxy.Stub() { // from class: com.amazon.alexa.api.AlexaConnectionWithoutLeaderSelection.2
                @Override // com.amazon.alexa.api.AlexaSettingsListenerProxy
                public void onLocaleChanged(final String str) {
                    ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.AlexaConnectionWithoutLeaderSelection.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            alexaSettingsListener.onLocaleChanged(java.util.Locale.forLanguageTag(str));
                        }
                    });
                }
            });
        }
        return this.alexaSettingsListeners.get(alexaSettingsListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public ComponentName getServiceComponentName() {
        return this.serviceComponentName;
    }

    protected abstract String getServiceName();

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public boolean internalIsConnected() {
        return super.internalIsConnected() && this.hasUserLoggedIn;
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    protected boolean isServiceComponentNameResolved() {
        return this.serviceComponentName != null;
    }

    protected abstract boolean isUserLoggedIn();

    protected void onClientConnected() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onClientDisconnected() {
        this.alexaDialogControllers.clear();
        this.alexaDialogControllersV2.clear();
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    protected void onConnectedToService() {
        doDeregisterForceDisconnectListener();
        this.disconnectMessageReceiver = createForceDisconnectMessageReceiver();
        registerForceDisconnectListener(this.disconnectMessageReceiver);
        this.hasUserLoggedIn = isUserLoggedIn();
        updateConnectedState();
        if (this.hasUserLoggedIn) {
            onClientConnected();
            return;
        }
        sendConnectingFailed(AlexaConnectingFailedReason.NO_ALEXA_SERVICES_ACCOUNT_REGISTERED, "The AlexaServices does not have a registered account.");
        disconnect();
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    protected void onDisconnectedFromService() {
        onClientDisconnected();
        doDeregisterForceDisconnectListener();
    }

    protected abstract void registerForceDisconnectListener(MessageReceiver<as> messageReceiver);

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public void release() {
        executeOnInternalConnectionThread(new Runnable() { // from class: com.amazon.alexa.api.AlexaConnectionWithoutLeaderSelection.1
            @Override // java.lang.Runnable
            public void run() {
                AlexaConnectionWithoutLeaderSelection.this.lock.lock();
                try {
                    AlexaConnectionWithoutLeaderSelection.this.doDeregisterForceDisconnectListener();
                    AlexaConnectionWithoutLeaderSelection.super.release();
                } finally {
                    AlexaConnectionWithoutLeaderSelection.this.lock.unlock();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaStateListenerProxy removeListener(AlexaStateListener alexaStateListener) {
        return this.stateListeners.remove(alexaStateListener);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaDialogControllerProxy removeProxy(AlexaDialogController alexaDialogController) {
        return this.alexaDialogControllers.remove(alexaDialogController);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaDialogControllerProxyV2 removeProxy(AlexaDialogControllerV2 alexaDialogControllerV2) {
        return this.alexaDialogControllersV2.remove(alexaDialogControllerV2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaSettingsListenerProxy removeProxy(AlexaSettingsListener alexaSettingsListener) {
        return this.alexaSettingsListeners.remove(alexaSettingsListener);
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    protected void resolveServiceComponentName() {
    }
}
