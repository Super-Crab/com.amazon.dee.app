package com.amazon.alexa.api;

import android.app.PendingIntent;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.utils.validation.Preconditions;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceBridgePayloadUtil;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class MediaPlaybackController {
    private static final String TAG = "MediaPlaybackController";

    private MediaPlaybackController() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void deregister(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaAudioPlaybackStatusListener, "The provided alexaAudioPlaybackStatusListener was null.");
        MessageReceiver<AlexaAudioPlaybackStatusListenerMessageType> removeListener = alexaServicesConnection.removeListener(alexaAudioPlaybackStatusListener);
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender messageSender = AlexaServicesTools.getMessageSender(alexaServicesConnection);
            if (removeListener == null) {
                Log.e(TAG, "can't deregister AlexaAudioPlaybackStatusListener");
            } else {
                messageSender.deregisterAlexaAudioPlaybackStatusListener(client, removeListener);
            }
        } catch (Exception e) {
            Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void deregisterListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaMediaPlaybackListener alexaMediaPlaybackListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaMediaPlaybackListener, "The provided AlexaMediaPlaybackListener was null.");
        MessageReceiver<ApiType_MediaPlaybackListenerMessageType> removeListener = alexaServicesConnection.removeListener(alexaMediaPlaybackListener);
        try {
            AlexaServicesTools.checkAlexaBound(alexaServicesConnection);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender messageSender = AlexaServicesTools.getMessageSender(alexaServicesConnection);
            if (removeListener == null) {
                Log.e(TAG, "deregister listener failed due to the message receiver being null");
            } else {
                messageSender.deregisterMediaPlaybackListener(client, removeListener);
            }
        } catch (Exception e) {
            Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void next(@NonNull AlexaServicesConnection alexaServicesConnection) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        if (!alexaServicesConnection.isConnected()) {
            Log.e(TAG, String.format(java.util.Locale.US, "Connection object is not connected. Cannot %s", "skip next"));
            return;
        }
        try {
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
            if (alexaServicesMessageSender != null) {
                alexaServicesMessageSender.next(client);
            } else {
                Log.e(TAG, "next(...) failed due to the message sender being null");
            }
        } catch (RemoteException e) {
            Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void pause(@NonNull AlexaServicesConnection alexaServicesConnection) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        if (!alexaServicesConnection.isConnected()) {
            Log.e(TAG, String.format(java.util.Locale.US, "Connection object is not connected. Cannot %s", "pause"));
            return;
        }
        try {
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
            if (alexaServicesMessageSender != null) {
                alexaServicesMessageSender.pause(client);
            } else {
                Log.e(TAG, "pause(...) failed due to the message sender being null");
            }
        } catch (RemoteException e) {
            Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void play(@NonNull AlexaServicesConnection alexaServicesConnection) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        if (!alexaServicesConnection.isConnected()) {
            Log.e(TAG, String.format(java.util.Locale.US, "Connection object is not connected. Cannot %s", VoiceBridgePayloadUtil.PayloadCommand.PLAY));
            return;
        }
        try {
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
            if (alexaServicesMessageSender != null) {
                alexaServicesMessageSender.play(client);
            } else {
                Log.e(TAG, "play(...) failed due to the message sender being null");
            }
        } catch (RemoteException e) {
            Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void previous(@NonNull AlexaServicesConnection alexaServicesConnection) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        if (!alexaServicesConnection.isConnected()) {
            Log.e(TAG, String.format(java.util.Locale.US, "Connection object is not connected. Cannot %s", "skip previous"));
            return;
        }
        try {
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
            if (alexaServicesMessageSender != null) {
                alexaServicesMessageSender.previous(client);
            } else {
                Log.e(TAG, "previous(...) failed due to the message sender being null");
            }
        } catch (RemoteException e) {
            Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void register(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaAudioPlaybackStatusListener, "The provided alexaAudioPlaybackStatusListener was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).registerAlexaAudioPlaybackStatusListener(alexaServicesConnection.getClient(), alexaServicesConnection.getListener(alexaAudioPlaybackStatusListener));
        } catch (Exception e) {
            Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void registerListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaMediaPlaybackListener alexaMediaPlaybackListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaMediaPlaybackListener, "The provided AlexaMediaPlaybackListener was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).registerMediaPlaybackListener(alexaServicesConnection.getClient(), alexaServicesConnection.getListener(alexaMediaPlaybackListener));
        } catch (Exception e) {
            Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setMediaNotificationContentIntent(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull PendingIntent pendingIntent) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(pendingIntent, "The provided pendingIntent was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender messageSender = AlexaServicesTools.getMessageSender(alexaServicesConnection);
            if (messageSender != null) {
                messageSender.setMediaNotificationContentIntent(client, pendingIntent);
            } else {
                Log.e(TAG, "failed sending set content intent (null messenger)");
            }
        } catch (Exception e) {
            Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void stop(@NonNull AlexaServicesConnection alexaServicesConnection) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        if (!alexaServicesConnection.isConnected()) {
            Log.e(TAG, String.format(java.util.Locale.US, "Connection object is not connected. Cannot %s", "stop playback"));
            return;
        }
        try {
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
            if (alexaServicesMessageSender != null) {
                alexaServicesMessageSender.stop(client);
            } else {
                Log.e(TAG, "stop(...) failed due to the message sender being null");
            }
        } catch (RemoteException e) {
            Log.e(TAG, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }
}
