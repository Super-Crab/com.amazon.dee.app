package com.amazon.alexa.api;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.ManagedServiceConnection;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.utils.security.ComponentEnabler;
import com.amazon.alexa.utils.security.SignatureVerifier;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public class AlexaAudioProviderConnection extends AlexaConnectionWithoutLeaderSelection<AlexaAudioProviderManagerMessageSender> {
    private static final String EXTRA_EXTENDED_CLIENT = "EXTRA_EXTENDED_CLIENT";
    private static final String EXTRA_REQUIRES_FOREGROUND = "EXTRA_REQUIRES_FOREGROUND";
    private static final String TAG = "AlexaAudioProviderConnection";
    private Context context;
    private volatile boolean disableConnectionFailureBroadcastIntent;

    /* renamed from: com.amazon.alexa.api.AlexaAudioProviderConnection$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaConnectingFailedReason = new int[AlexaConnectingFailedReason.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaConnectingFailedReason[AlexaConnectingFailedReason.NO_ALEXA_SERVICES_ACCOUNT_REGISTERED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaConnectingFailedReason[AlexaConnectingFailedReason.NO_ALEXA_SERVICES_TO_CONNECT_TO.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaConnectingFailedReason[AlexaConnectingFailedReason.UNKNOWN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes6.dex */
    public interface ConnectionListener extends ManagedServiceConnection.ConnectionListener {
    }

    public AlexaAudioProviderConnection(@NonNull Context context) {
        super(context);
        this.disableConnectionFailureBroadcastIntent = false;
        this.context = context;
    }

    public AlexaAudioProviderConnection(@NonNull Context context, @NonNull SignatureVerifier signatureVerifier, @NonNull Handler handler, @NonNull Handler handler2) {
        super(context, signatureVerifier, handler, handler2);
        this.disableConnectionFailureBroadcastIntent = false;
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.api.ManagedServiceConnection
    /* renamed from: createServiceInterface */
    public AlexaAudioProviderManagerMessageSender mo811createServiceInterface(IBinder iBinder) {
        return new AlexaAudioProviderManagerMessageSender(iBinder, getMessageReceiversManager());
    }

    @Override // com.amazon.alexa.api.AlexaConnectionWithoutLeaderSelection
    protected void deregisterForceDisconnectListener(MessageReceiver<as> messageReceiver) {
        try {
            AlexaAudioProviderManagerMessageSender alexaAudioProviderManagerMessageSender = (AlexaAudioProviderManagerMessageSender) this.serviceInterface.get();
            if (alexaAudioProviderManagerMessageSender == null) {
                return;
            }
            alexaAudioProviderManagerMessageSender.deregisterForceDisconnectListener(getClient(), messageReceiver);
        } catch (RemoteException e) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Exception while deregistering previous listeners: ");
            outline107.append(e.getMessage());
            Log.e(str, outline107.toString());
        }
    }

    public void disableConnectionFailureBroadcastIntent(boolean z) {
        this.disableConnectionFailureBroadcastIntent = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public void doConnect() {
        if (isLeaderInstalled()) {
            super.doConnect();
            return;
        }
        AlexaConnectingFailedReason alexaConnectingFailedReason = AlexaConnectingFailedReason.NO_ALEXA_SERVICES_TO_CONNECT_TO;
        sendConnectingFailed(alexaConnectingFailedReason, getServiceComponentName() + " is not installed.");
    }

    @Override // com.amazon.alexa.api.ManagedServiceConnection
    protected Intent getConnectionIntent(ComponentName componentName, boolean z) {
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.putExtra(EXTRA_EXTENDED_CLIENT, getClient().getBundle());
        intent.putExtra(EXTRA_REQUIRES_FOREGROUND, z);
        return intent;
    }

    @Override // com.amazon.alexa.api.AlexaConnectionWithoutLeaderSelection
    protected String getServiceName() {
        return getContext().getString(R.string.alexa_audio_provider_manager_service_name);
    }

    @Override // com.amazon.alexa.api.AlexaConnectionWithoutLeaderSelection, com.amazon.alexa.api.ManagedServiceConnection
    public /* bridge */ /* synthetic */ boolean internalIsConnected() {
        return super.internalIsConnected();
    }

    boolean isLeaderInstalled() {
        return ComponentEnabler.checkIfComponentIsEnabled(this.context.getPackageManager(), getServiceComponentName());
    }

    @Override // com.amazon.alexa.api.AlexaConnectionWithoutLeaderSelection
    protected boolean isUserLoggedIn() {
        return a.a(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.api.AlexaConnectionWithoutLeaderSelection
    public void onClientDisconnected() {
        super.onClientDisconnected();
        AlexaAudioProviderManagerMessageSender alexaAudioProviderManagerMessageSender = (AlexaAudioProviderManagerMessageSender) this.serviceInterface.get();
        if (alexaAudioProviderManagerMessageSender != null) {
            try {
                alexaAudioProviderManagerMessageSender.onClientDisconnect(getClient());
            } catch (RemoteException e) {
                Log.e(TAG, "Unable to send client disconnect", e);
            }
        }
    }

    @Override // com.amazon.alexa.api.AlexaConnectionWithoutLeaderSelection
    protected void registerForceDisconnectListener(MessageReceiver<as> messageReceiver) {
        try {
            ((AlexaAudioProviderManagerMessageSender) this.serviceInterface.get()).registerForceDisconnectListener(getClient(), messageReceiver);
        } catch (RemoteException e) {
            Log.e(TAG, "Unable to register required listeners", e);
            disconnect();
        }
    }

    @Override // com.amazon.alexa.api.AlexaConnectionWithoutLeaderSelection, com.amazon.alexa.api.ManagedServiceConnection
    public /* bridge */ /* synthetic */ void release() {
        super.release();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.alexa.api.ManagedServiceConnection
    public void sendConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
        super.sendConnectingFailed(alexaConnectingFailedReason, str);
        if (this.disableConnectionFailureBroadcastIntent) {
            return;
        }
        int ordinal = alexaConnectingFailedReason.ordinal();
        this.context.sendBroadcast(ordinal != 2 ? ordinal != 3 ? bh.c(this.context) : bh.b(this.context) : bh.a(this.context));
    }
}
