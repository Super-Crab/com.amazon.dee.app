package com.amazon.alexa.api;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.messages.messagesender.AlexaMessageSender;
/* loaded from: classes6.dex */
public class AttentionSystemSettingsMessageSender extends AlexaMessageSender<AlexaAttentionSystemSettingsMessageType> implements AlexaAttentionSystemSettingsListener {
    private static final String TAG = "AttentionSystemSettingsMessageSender";
    private final ExtendedClient client;

    /* loaded from: classes6.dex */
    static class a extends BaseMessagePayload {
        a(ExtendedClient extendedClient, boolean z) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.IS_ENDPOINT_SOUND_ENABLED, z);
        }
    }

    /* loaded from: classes6.dex */
    static class b extends BaseMessagePayload {
        b(ExtendedClient extendedClient, boolean z) {
            super(extendedClient);
            add(AlexaServicesArgumentKey.IS_WAKE_SOUND_ENABLED, z);
        }
    }

    public AttentionSystemSettingsMessageSender(IBinder iBinder, ExtendedClient extendedClient) {
        super(iBinder);
        this.client = extendedClient;
    }

    @Override // com.amazon.alexa.api.AlexaAttentionSystemSettingsListener
    public void onEndpointSoundEnabledChanged(boolean z) {
        try {
            sendMessage(AlexaAttentionSystemSettingsMessageType.ON_ENDPOINT_SOUND_ENABLED_CHANGED, new a(this.client, z).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send on endpoint sound enabled changed ", e);
        }
    }

    @Override // com.amazon.alexa.api.AlexaAttentionSystemSettingsListener
    public void onWakeSoundEnabledChanged(boolean z) {
        try {
            sendMessage(AlexaAttentionSystemSettingsMessageType.ON_WAKE_SOUND_ENABLED_CHANGED, new b(this.client, z).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send on wake sound enabled changed ", e);
        }
    }
}
