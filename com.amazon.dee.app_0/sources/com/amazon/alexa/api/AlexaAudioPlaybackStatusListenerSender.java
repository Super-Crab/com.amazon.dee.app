package com.amazon.alexa.api;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.messages.messagesender.AlexaMessageSender;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes6.dex */
public class AlexaAudioPlaybackStatusListenerSender extends AlexaMessageSender<AlexaAudioPlaybackStatusListenerMessageType> implements AlexaAudioPlaybackStatusListener {
    private static final String TAG = "AlexaAudioPlaybackStatusListenerSender";
    private final ExtendedClient extendedClient;

    @VisibleForTesting
    /* loaded from: classes6.dex */
    static class OnAudioPlaybackStatusChangedPayload extends BaseMessagePayload {
        OnAudioPlaybackStatusChangedPayload(ExtendedClient extendedClient, HashMap<AlexaAudioChannel, Boolean> hashMap) {
            super(extendedClient);
            addSerializable(AlexaAudioPlaybackStatusListenerArgumentKey.AUDIO_PLAYBACK_STATUS_KEY, hashMap);
        }
    }

    public AlexaAudioPlaybackStatusListenerSender(IBinder iBinder, ExtendedClient extendedClient) {
        super(iBinder);
        this.extendedClient = extendedClient;
    }

    @Override // com.amazon.alexa.api.AlexaAudioPlaybackStatusListener
    public void onAudioPlaybackStatusChanged(Map<AlexaAudioChannel, Boolean> map) {
        try {
            sendMessage(AlexaAudioPlaybackStatusListenerMessageType.AUDIO_PLAYBACK_STATUS, new OnAudioPlaybackStatusChangedPayload(this.extendedClient, new HashMap(map)).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send message for: onAudioPlaybackStatusChanged", e);
        }
    }
}
