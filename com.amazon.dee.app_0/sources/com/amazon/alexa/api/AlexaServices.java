package com.amazon.alexa.api;

import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.AlexaAudioPlaybackListenerProxy;
import com.amazon.alexa.api.AlexaContextProviderProxy;
import com.amazon.alexa.api.AlexaMetricsName;
import com.amazon.alexa.api.AlexaPlayerInfoCardListenerProxy;
import com.amazon.alexa.api.AlexaServicesApis;
import com.amazon.alexa.api.AlexaSettingsListenerProxy;
import com.amazon.alexa.api.AlexaUserSpeechListenerProxy;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.api.LeaderSelectionAuthority;
import com.amazon.alexa.api.UserPerceivedLatencyListenerProxy;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.ApiThreadHelper;
import com.amazon.alexa.utils.security.ComponentEnabler;
import com.amazon.alexa.utils.validation.Preconditions;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceBridgePayloadUtil;
import com.amazon.leaderselection.LeaderSelector;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes6.dex */
public class AlexaServices {
    static final String ALEXA_SERVICE_NAME = "com.amazon.alexa.AlexaService";
    static final String AUDIO_PROVIDER_SERVICE_NAME = "com.amazon.alexa.AlexaAudioProviderManagerService";
    private static final String DEFAULT_INVOCATION_TYPE = "UNKNOWN";
    private static final String NOT_BOUND_MESSAGE = "Connection object is not bound. Cannot %s";
    static final String NOT_CONNECTED_MESSAGE = "Connection object is not connected. Cannot %s";
    private static final String TAG = "AlexaServices";

    /* loaded from: classes6.dex */
    public static class Account {
        private Account() {
            throw new UnsupportedOperationException();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static boolean isUserLoggedIn(@NonNull AlexaServicesConnection alexaServicesConnection) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            if (!alexaServicesConnection.isBoundToService()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_BOUND_MESSAGE, "check isUserLoggedIn"));
                return false;
            }
            try {
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender != null) {
                    try {
                        return alexaServicesMessageSender.isUserLoggedIn(client);
                    } catch (RemoteException e) {
                        Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
                    }
                }
                return false;
            } finally {
                Log.e(AlexaServices.TAG, "Unable to check login status");
            }
        }

        public static void logOut(@NonNull AlexaServicesConnection alexaServicesConnection) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            if (!alexaServicesConnection.isConnected()) {
                Log.e(AlexaServices.TAG, "AlexaServicesConnection is not connected");
                return;
            }
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
            if (alexaServicesMessageSender == null) {
                return;
            }
            try {
                alexaServicesMessageSender.logOut(client);
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }
    }

    /* loaded from: classes6.dex */
    public static class Alerts {
        public static void deregisterListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlertsListener alertsListener) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alertsListener, "The provided AlertsListener was null.");
            MessageReceiver<e> removeListener = alexaServicesConnection.removeListener(alertsListener);
            try {
                if (alexaServicesConnection.isBoundToService()) {
                    ExtendedClient client = alexaServicesConnection.getClient();
                    AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                    if (alexaServicesMessageSender == null) {
                        Log.w(AlexaServices.TAG, "message sender is not found");
                    } else if (removeListener != null) {
                        alexaServicesMessageSender.deregisterAlertsListener(client, removeListener);
                    }
                } else {
                    Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_BOUND_MESSAGE, "deregister AlertsListener"));
                }
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }

        public static void registerListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlertsListener alertsListener) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alertsListener, "The provided AlertsListener was null.");
            if (!alexaServicesConnection.isConnected()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "register AlertsListener"));
                return;
            }
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
            if (alexaServicesMessageSender == null) {
                Log.w(AlexaServices.TAG, "message sender is not found");
                return;
            }
            try {
                alexaServicesMessageSender.registerAlertsListener(client, alexaServicesConnection.getListener(alertsListener));
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }
    }

    /* loaded from: classes6.dex */
    public static class AudioPlaybackControl {
        private AudioPlaybackControl() {
            throw new UnsupportedOperationException();
        }

        @Deprecated
        public static void deregister(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaAudioPlaybackStatusListener, "The provided alexaAudioPlaybackStatusListener was null.");
            MessageReceiver<AlexaAudioPlaybackStatusListenerMessageType> removeListener = alexaServicesConnection.removeListener(alexaAudioPlaybackStatusListener);
            try {
                AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender messageSender = AlexaServicesTools.getMessageSender(alexaServicesConnection);
                if (removeListener == null) {
                    Log.e(AlexaServices.TAG, "can't deregister AlexaAudioPlaybackStatusListener");
                } else {
                    messageSender.deregisterAlexaAudioPlaybackStatusListener(client, removeListener);
                }
            } catch (Exception e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }

        public static void deregisterListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaAudioPlaybackListener alexaAudioPlaybackListener) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaAudioPlaybackListener, "The provided AlexaAudioPlaybackListener was null.");
            AlexaAudioPlaybackListenerProxy removeListener = alexaServicesConnection.removeListener(alexaAudioPlaybackListener);
            if (!alexaServicesConnection.isBoundToService()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_BOUND_MESSAGE, "deregister AlexaAudioPlaybackListener"));
            } else if (removeListener == null) {
                Log.w(AlexaServices.TAG, "Proxy object is null. Cannot deregister listener");
            } else {
                try {
                    ExtendedClient client = alexaServicesConnection.getClient();
                    AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                    if (alexaServicesMessageSender == null) {
                        return;
                    }
                    alexaServicesMessageSender.deregisterAlexaAudioPlaybackListener(client, removeListener);
                } catch (RemoteException e) {
                    Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
                }
            }
        }

        public static void next(@NonNull AlexaServicesConnection alexaServicesConnection) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            if (!alexaServicesConnection.isConnected()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "skip next"));
                return;
            }
            try {
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender == null) {
                    return;
                }
                alexaServicesMessageSender.next(client);
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }

        public static void pause(@NonNull AlexaServicesConnection alexaServicesConnection) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            if (!alexaServicesConnection.isConnected()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "pause"));
                return;
            }
            try {
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender == null) {
                    return;
                }
                alexaServicesMessageSender.pause(client);
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }

        public static void play(@NonNull AlexaServicesConnection alexaServicesConnection) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            if (!alexaServicesConnection.isConnected()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, VoiceBridgePayloadUtil.PayloadCommand.PLAY));
                return;
            }
            try {
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender == null) {
                    return;
                }
                alexaServicesMessageSender.play(client);
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }

        public static void previous(@NonNull AlexaServicesConnection alexaServicesConnection) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            if (!alexaServicesConnection.isConnected()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "skip previous"));
                return;
            }
            try {
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender == null) {
                    return;
                }
                alexaServicesMessageSender.previous(client);
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }

        @Deprecated
        public static void register(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaAudioPlaybackStatusListener, "The provided alexaAudioPlaybackStatusListener was null.");
            try {
                AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
                AlexaServicesTools.getMessageSender(alexaServicesConnection).registerAlexaAudioPlaybackStatusListener(alexaServicesConnection.getClient(), alexaServicesConnection.getListener(alexaAudioPlaybackStatusListener));
            } catch (Exception e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }

        public static void registerListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull final AlexaAudioPlaybackListener alexaAudioPlaybackListener) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaAudioPlaybackListener, "The provided AlexaAudioPlaybackListener was null.");
            if (!alexaServicesConnection.isConnected()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "register AlexaAudioPlaybackListener"));
                return;
            }
            AlexaAudioPlaybackListenerProxy.Stub stub = new AlexaAudioPlaybackListenerProxy.Stub() { // from class: com.amazon.alexa.api.AlexaServices.AudioPlaybackControl.1
                @Override // com.amazon.alexa.api.AlexaAudioPlaybackListenerProxy
                public void onAudioPlaybackChanged(final AlexaPlaybackState alexaPlaybackState) throws RemoteException {
                    ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.AlexaServices.AudioPlaybackControl.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AlexaAudioPlaybackListener.this.onAudioPlaybackChanged(alexaPlaybackState);
                        }
                    });
                }
            };
            try {
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender == null) {
                    return;
                }
                alexaServicesMessageSender.registerAlexaAudioPlaybackListener(client, stub);
                alexaServicesConnection.addListener(alexaAudioPlaybackListener, stub);
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }

        public static void stop(@NonNull AlexaServicesConnection alexaServicesConnection) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            if (!alexaServicesConnection.isConnected()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "stop playback"));
                return;
            }
            try {
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender == null) {
                    return;
                }
                alexaServicesMessageSender.stop(client);
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }
    }

    /* loaded from: classes6.dex */
    public static class Cards {
        private Cards() {
            throw new UnsupportedOperationException();
        }

        public static void deregisterAlexaCardRendererListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaCardListener alexaCardListener) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaCardListener, "The provided AlexaCardListener was null.");
            MessageReceiver<AlexaCardListenerMessageType> removeListener = alexaServicesConnection.removeListener(alexaCardListener);
            try {
                if (!alexaServicesConnection.isBoundToService()) {
                    Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_BOUND_MESSAGE, "deregister AlexaCardListener"));
                } else if (removeListener != null) {
                    ExtendedClient client = alexaServicesConnection.getClient();
                    AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                    if (alexaServicesMessageSender != null) {
                        alexaServicesMessageSender.deregisterAlexaCardRendererListener(client, removeListener);
                    }
                } else {
                    Log.w(AlexaServices.TAG, "Proxy object is null. Cannot deregister listener");
                }
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }

        public static void deregisterCardRendererListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaCardRendererListener alexaCardRendererListener) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaCardRendererListener, "The provided AlexaCardListener was null.");
            MessageReceiver<AlexaCardListenerMessageType> removeListener = alexaServicesConnection.removeListener(alexaCardRendererListener);
            try {
                if (!alexaServicesConnection.isBoundToService()) {
                    Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_BOUND_MESSAGE, "deregister AlexaCardRendererListener"));
                } else if (removeListener != null) {
                    ExtendedClient client = alexaServicesConnection.getClient();
                    AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                    if (alexaServicesMessageSender != null) {
                        alexaServicesMessageSender.deregisterAlexaCardRendererListener(client, removeListener);
                    }
                } else {
                    Log.w(AlexaServices.TAG, "Proxy object is null. Cannot deregister listener");
                }
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }

        public static void deregisterPlayerInfoCardListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaPlayerInfoCardListener alexaPlayerInfoCardListener) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaPlayerInfoCardListener, "The provided AlexaPlayerInfoCardListener was null.");
            AlexaPlayerInfoCardListenerProxy removeListener = alexaServicesConnection.removeListener(alexaPlayerInfoCardListener);
            if (!alexaServicesConnection.isBoundToService()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_BOUND_MESSAGE, "deregister AlexaPlayerInfoCardListener"));
            } else if (removeListener == null) {
                Log.w(AlexaServices.TAG, "Proxy object is null. Cannot deregister listener");
            } else {
                try {
                    ExtendedClient client = alexaServicesConnection.getClient();
                    AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                    if (alexaServicesMessageSender == null) {
                        return;
                    }
                    alexaServicesMessageSender.deregisterAlexaPlayerInfoCardListener(client, removeListener);
                } catch (RemoteException e) {
                    Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
                }
            }
        }

        public static void registerAlexaCardRendererListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaCardListener alexaCardListener) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaCardListener, "The provided AlexaCardListener was null.");
            if (!alexaServicesConnection.isConnected()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "register AlexaCardListener"));
                return;
            }
            try {
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender == null) {
                    return;
                }
                alexaServicesMessageSender.registerAlexaCardRendererListener(client, alexaServicesConnection.getListener(alexaCardListener));
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }

        public static void registerAlexaCardRendererListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaCardRendererListener alexaCardRendererListener) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaCardRendererListener, "The provided AlexaCardListener was null.");
            if (!alexaServicesConnection.isConnected()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "register AlexaCardRendererListener"));
                return;
            }
            try {
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender == null) {
                    return;
                }
                alexaServicesMessageSender.registerAlexaCardRendererListener(client, alexaServicesConnection.getListener(alexaCardRendererListener));
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }

        public static void registerPlayerInfoCardListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull final AlexaPlayerInfoCardListener alexaPlayerInfoCardListener) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaPlayerInfoCardListener, "The provided AlexaPlayerInfoCardListener was null.");
            if (!alexaServicesConnection.isConnected()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "register AlexaPlayerInfoCardListener"));
                return;
            }
            AlexaPlayerInfoCardListenerProxy.Stub stub = new AlexaPlayerInfoCardListenerProxy.Stub() { // from class: com.amazon.alexa.api.AlexaServices.Cards.1
                @Override // com.amazon.alexa.api.AlexaPlayerInfoCardListenerProxy
                public void onAudioItemStateChanged(AlexaPlayerInfoState alexaPlayerInfoState, String str, long j) throws RemoteException {
                    AlexaPlayerInfoCardListener.this.onAudioItemStateChanged(alexaPlayerInfoState, str, j);
                }

                @Override // com.amazon.alexa.api.AlexaPlayerInfoCardListenerProxy
                public void onReceivedPlayerInfoCard(String str, boolean z) throws RemoteException {
                    AlexaPlayerInfoCardListener.this.onReceivedPlayerInfoCard(str, z);
                }
            };
            try {
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender == null) {
                    return;
                }
                alexaServicesMessageSender.registerAlexaPlayerInfoCardListener(client, stub);
                alexaServicesConnection.addListener(alexaPlayerInfoCardListener, stub);
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }
    }

    /* loaded from: classes6.dex */
    public static class ContextProvider {
        public static void deregister(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaContextProvider alexaContextProvider) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaContextProvider, "The provided AlexaContextProvider was null.");
            if (alexaContextProvider.getAlexaContext() != null) {
                AlexaContextProviderProxy removeContextProvider = alexaServicesConnection.removeContextProvider(alexaContextProvider);
                if (!alexaServicesConnection.isBoundToService()) {
                    Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_BOUND_MESSAGE, "deregister AlexaContextProvider"));
                    return;
                } else if (removeContextProvider == null) {
                    Log.w(AlexaServices.TAG, "Proxy object is null. Cannot deregister context provider");
                    return;
                } else {
                    try {
                        ExtendedClient client = alexaServicesConnection.getClient();
                        AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                        if (alexaServicesMessageSender == null) {
                            return;
                        }
                        alexaServicesMessageSender.deregisterContextProvider(client, removeContextProvider);
                        return;
                    } catch (RemoteException e) {
                        Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
                        return;
                    }
                }
            }
            throw new IllegalStateException("Provided Context is null");
        }

        public static void register(@NonNull final AlexaServicesConnection alexaServicesConnection, @NonNull final AlexaContextProvider alexaContextProvider) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaContextProvider, "The provided AlexaContextProvider was null.");
            if (alexaContextProvider.getAlexaContext() != null) {
                if (!alexaServicesConnection.isConnected()) {
                    Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "register AlexaContextProvider"));
                    return;
                }
                AlexaContextProviderProxy contextProvider = alexaServicesConnection.getContextProvider(alexaContextProvider);
                if (contextProvider == null) {
                    contextProvider = new AlexaContextProviderProxy.Stub() { // from class: com.amazon.alexa.api.AlexaServices.ContextProvider.1
                        private final String identifier;

                        {
                            this.identifier = AlexaServicesConnection.this.getContext().getPackageName() + ProcessIdUtil.DEFAULT_PROCESSID + UUID.randomUUID().toString();
                        }

                        @Override // com.amazon.alexa.api.AlexaContextProviderProxy
                        public AlexaContext getAlexaContext() throws RemoteException {
                            return alexaContextProvider.getAlexaContext();
                        }

                        @Override // com.amazon.alexa.api.AlexaContextProviderProxy
                        public String getIdentifier() throws RemoteException {
                            return this.identifier;
                        }
                    };
                }
                try {
                    ExtendedClient client = alexaServicesConnection.getClient();
                    AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                    if (alexaServicesMessageSender == null) {
                        return;
                    }
                    alexaServicesMessageSender.registerContextProvider(client, contextProvider);
                    alexaServicesConnection.addContextProvider(alexaContextProvider, contextProvider);
                    return;
                } catch (RemoteException e) {
                    Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
                    return;
                }
            }
            throw new IllegalStateException("Provided Context is null");
        }
    }

    /* loaded from: classes6.dex */
    public static class EventSender {
        public static void send(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaEvent alexaEvent) {
            send(alexaServicesConnection, alexaEvent, false);
        }

        @Deprecated
        public static void send(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaEvent alexaEvent, @NonNull List<AlexaContext> list) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaEvent, "The provided AlexaEvent was null.");
            Preconditions.notNull(list, "The provided AlexaContexts was null.");
            if (!alexaServicesConnection.isConnected()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "send AlexaEvent"));
                return;
            }
            try {
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender == null) {
                    return;
                }
                alexaServicesMessageSender.sendAlexaEvent(client, alexaEvent, list);
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }

        public static void send(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaEvent alexaEvent, boolean z) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaEvent, "The provided AlexaEvent was null.");
            send(alexaServicesConnection, alexaEvent, z, null);
        }

        public static void send(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaEvent alexaEvent, boolean z, @Nullable AlexaApiCallbacks alexaApiCallbacks) {
            AlexaApiCallbacksWrapper alexaApiCallbacksWrapper;
            Bundle bundle;
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaEvent, "The provided AlexaEvent was null.");
            AlexaApiCallbacks bgVar = alexaApiCallbacks == null ? new bg() : alexaApiCallbacks;
            MetricBroadcastSender metricBroadcastSender = alexaServicesConnection.getMetricBroadcastSender();
            String name = AlexaServicesMessageType.SEND_ALEXA_EVENT.name();
            try {
                AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender messageSender = AlexaServicesTools.getMessageSender(alexaServicesConnection);
                if (alexaApiCallbacks != null) {
                    AlexaConnectionProxyMapping mapping = alexaServicesConnection.getMapping();
                    alexaApiCallbacksWrapper = new AlexaApiCallbacksWrapper(bgVar, name, metricBroadcastSender, mapping);
                    try {
                        bundle = mapping.getAlexaApiCallbacks(alexaApiCallbacksWrapper);
                    } catch (Exception e) {
                        e = e;
                        Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
                        bgVar.doOnFailure(ApiCallFailure.MessagingFailure.create("Could not send AlexaEvent", e));
                        if (alexaApiCallbacksWrapper != null || metricBroadcastSender == null) {
                            return;
                        }
                        metricBroadcastSender.sendEvent(AlexaMetricsName.SdkUsage.ApiCalls.Client.ATTEMPT.injectWith(name), "", null, bgVar.getId());
                        metricBroadcastSender.sendEvent(AlexaMetricsName.SdkUsage.ApiCalls.Client.FAILURE.injectWith(name).appendToAlexaMetricsName(ApiCallFailure.FailureType.MESSAGING.name()), "", null, bgVar.getId());
                        return;
                    }
                } else {
                    bundle = null;
                    alexaApiCallbacksWrapper = null;
                }
                messageSender.sendAlexaEvent(client, alexaEvent, z, bundle);
            } catch (Exception e2) {
                e = e2;
                alexaApiCallbacksWrapper = null;
            }
        }
    }

    /* loaded from: classes6.dex */
    public static class InteractionScheduler {
        public static void schedule(AlexaServicesConnection alexaServicesConnection, AlexaAudioInteraction alexaAudioInteraction) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaAudioInteraction, "The provided AlexaAudioInteraction was null.");
            if (alexaServicesConnection.isAudioInteractionScheduled(alexaAudioInteraction)) {
                String str = AlexaServices.TAG;
                Log.w(str, "Interaction: " + alexaAudioInteraction + " is already scheduled.");
            } else if (!alexaServicesConnection.isConnected()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "schedule AlexaAudioInteraction"));
                alexaServicesConnection.getBroadcastSender().sendEvent(AlexaMetricsName.InteractionSchedulerFailure.SCHEDULE_FAILED_NOT_CONNECTED);
            } else {
                n nVar = new n(alexaAudioInteraction);
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender == null) {
                    return;
                }
                try {
                    alexaServicesMessageSender.scheduleInteraction(client, nVar);
                    alexaServicesConnection.addAudioInteraction(alexaAudioInteraction, nVar);
                } catch (RemoteException e) {
                    Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
                }
            }
        }

        public static void unschedule(AlexaServicesConnection alexaServicesConnection, AlexaAudioInteraction alexaAudioInteraction) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaAudioInteraction, "The provided AlexaAudioInteraction was null.");
            Preconditions.notNull(alexaAudioInteraction.getAlexaAudioChannel(), "The provided AudioChannel is null");
            AlexaAudioInteractionProxy removeAudioInteraction = alexaServicesConnection.removeAudioInteraction(alexaAudioInteraction);
            if (!alexaServicesConnection.isBoundToService()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_BOUND_MESSAGE, "unschedule AlexaAudioInteraction"));
            } else if (removeAudioInteraction == null) {
                Log.w(AlexaServices.TAG, "Proxy object is null. Cannot unschedule interaction");
            } else {
                try {
                    ExtendedClient client = alexaServicesConnection.getClient();
                    AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                    if (alexaServicesMessageSender == null) {
                        return;
                    }
                    alexaServicesMessageSender.unscheduleInteraction(client, removeAudioInteraction);
                } catch (RemoteException e) {
                    Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
                }
            }
        }
    }

    /* loaded from: classes6.dex */
    public static class Metrics {
        public static void deregisterListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaUserPerceivedLatencyListener alexaUserPerceivedLatencyListener) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaUserPerceivedLatencyListener, "The provided UserPerceivedLatencyListener was null.");
            UserPerceivedLatencyListenerProxy removeListener = alexaServicesConnection.removeListener(alexaUserPerceivedLatencyListener);
            if (removeListener == null) {
                Log.w(AlexaServices.TAG, "Proxy object is null. Cannot deregister listener");
            } else if (!alexaServicesConnection.isBoundToService()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_BOUND_MESSAGE, "deregister UserPerceivedLatencyListener"));
            } else {
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender == null) {
                    return;
                }
                try {
                    alexaServicesMessageSender.deregisterUserPerceivedLatencyListener(client, removeListener);
                } catch (RemoteException e) {
                    Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
                }
            }
        }

        public static void deregisterMetricsListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaMetricsListener alexaMetricsListener) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaMetricsListener, "The provided AlexaMetricsListener was null.");
            MessageReceiver<be> removeListener = alexaServicesConnection.removeListener(alexaMetricsListener);
            try {
                if (alexaServicesConnection.isBoundToService()) {
                    ExtendedClient client = alexaServicesConnection.getClient();
                    AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                    if (alexaServicesMessageSender == null) {
                        Log.w(AlexaServices.TAG, "message sender is not found");
                    } else if (removeListener != null) {
                        alexaServicesMessageSender.deregisterMetricsListener(client, removeListener);
                    }
                } else {
                    Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_BOUND_MESSAGE, "deregister AlexaMetricsListener"));
                }
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }

        public static void registerListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull final AlexaUserPerceivedLatencyListener alexaUserPerceivedLatencyListener) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaUserPerceivedLatencyListener, "The provided UserPerceivedLatencyListener was null.");
            if (!alexaServicesConnection.isConnected()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "register UserPerceivedLatencyListener"));
                return;
            }
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
            if (alexaServicesMessageSender == null) {
                return;
            }
            try {
                UserPerceivedLatencyListenerProxy.Stub stub = new UserPerceivedLatencyListenerProxy.Stub() { // from class: com.amazon.alexa.api.AlexaServices.Metrics.1
                    @Override // com.amazon.alexa.api.UserPerceivedLatencyListenerProxy
                    public void onLatencyData(String str, Bundle bundle) throws RemoteException {
                        AlexaUserPerceivedLatencyListener.this.onLatencyData(str, UserPerceivedLatencyData.fromBundle(bundle));
                    }
                };
                alexaServicesMessageSender.registerUserPerceivedLatencyListener(client, stub);
                alexaServicesConnection.addListener(alexaUserPerceivedLatencyListener, stub);
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }

        public static void registerMetricsListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaMetricsListener alexaMetricsListener) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaMetricsListener, "The provided AlexaMetricsListener was null.");
            if (!alexaServicesConnection.isConnected()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "register AlexaMetricsListener"));
                return;
            }
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
            if (alexaServicesMessageSender == null) {
                return;
            }
            try {
                alexaServicesMessageSender.registerMetricsListener(client, alexaServicesConnection.getListener(alexaMetricsListener));
            } catch (Exception e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }
    }

    /* loaded from: classes6.dex */
    public static class Readiness {
        public static void deregister(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaReadinessListener alexaReadinessListener) {
            bj.b(alexaServicesConnection, alexaReadinessListener);
        }

        public static AlexaReadyState getReadyState(@NonNull AlexaServicesConnection alexaServicesConnection) {
            return bj.a(alexaServicesConnection);
        }

        public static void register(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaReadinessListener alexaReadinessListener) {
            bj.a(alexaServicesConnection, alexaReadinessListener);
        }
    }

    /* loaded from: classes6.dex */
    public static class Recognizer {
        private Recognizer() {
            throw new UnsupportedOperationException();
        }

        public static void cancelUserInteraction(@NonNull AlexaServicesConnection alexaServicesConnection) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            if (!alexaServicesConnection.isConnected()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "cancelUserInteraction"));
                return;
            }
            try {
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender == null) {
                    return;
                }
                alexaServicesMessageSender.cancelUserInteraction(client);
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }

        private static void closeFileDescriptor(@Nullable ParcelFileDescriptor parcelFileDescriptor) {
            if (parcelFileDescriptor != null) {
                try {
                    parcelFileDescriptor.close();
                } catch (IOException unused) {
                    Log.e(AlexaServices.TAG, "Unable to close file descriptor");
                }
            }
        }

        @Deprecated
        public static void continueDialog(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaDialogController alexaDialogController, @NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaDialogController, "The provided AlexaDialogController was null.");
            Preconditions.notNull(alexaAudioMetadata, "The provided AlexaAudioMetadata was null.");
            Preconditions.notNull(alexaAudioSink, "The provided AlexaAudioSink was null.");
            try {
                AlexaDialogControllerProxy proxy = alexaServicesConnection.getProxy(alexaDialogController);
                boolean z = false;
                if (!alexaServicesConnection.isConnected()) {
                    Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "continueDialog"));
                    sendDisconnectedMetric(alexaServicesConnection, null);
                } else if (proxy == null) {
                    sendOutOfTurnMetric(alexaServicesConnection, null);
                    throw new IllegalStateException("Tried to continue a dialog with a AlexaDialogController which has not started a dialog");
                } else if (h.a(alexaAudioMetadata)) {
                    continueDialog(alexaServicesConnection, proxy, alexaAudioMetadata, alexaAudioSink.getReadDescriptor());
                    z = true;
                } else {
                    Log.e(AlexaServices.TAG, "Invalid Alexa Audio Metadata");
                    sendInvalidAudioMetadataMetric(alexaServicesConnection, null);
                }
                if (z) {
                }
            } finally {
                alexaAudioSink.abandon();
                dropDialog(alexaDialogController, true);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Deprecated
        public static void continueDialog(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaDialogControllerProxy alexaDialogControllerProxy, @NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull ParcelFileDescriptor parcelFileDescriptor) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaDialogControllerProxy, "The provided AlexaDialogControllerProxy was null.");
            Preconditions.notNull(alexaAudioMetadata, "The provided AlexaAudioMetadata was null.");
            Preconditions.notNull(parcelFileDescriptor, "The provided ParcelFileDescriptor was null.");
            LegacyUserSpeechProvider dialogController = alexaServicesConnection.getDialogController(alexaDialogControllerProxy);
            if (dialogController != null) {
                dialogController.continueDialog(new AlexaAudioSink(parcelFileDescriptor, null));
                return;
            }
            closeFileDescriptor(parcelFileDescriptor);
            dropDialog(alexaDialogControllerProxy, true);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static void continueDialog(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2, @NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull ParcelFileDescriptor parcelFileDescriptor) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaDialogControllerProxyV2, "The provided AlexaDialogControllerProxy was null.");
            Preconditions.notNull(alexaAudioMetadata, "The provided AlexaAudioMetadata was null.");
            Preconditions.notNull(parcelFileDescriptor, "The provided ParcelFileDescriptor was null.");
            LegacyUserSpeechProvider dialogController = alexaServicesConnection.getDialogController(alexaDialogControllerProxyV2);
            if (dialogController != null) {
                dialogController.continueDialog(new AlexaAudioSink(parcelFileDescriptor, null));
                return;
            }
            closeFileDescriptor(parcelFileDescriptor);
            dropDialog(alexaDialogControllerProxyV2, true);
        }

        @Deprecated
        public static void continueDialog(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaDialogControllerV2 alexaDialogControllerV2, @NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaDialogControllerV2, "The provided AlexaDialogController was null.");
            Preconditions.notNull(alexaAudioMetadata, "The provided AlexaAudioMetadata was null.");
            Preconditions.notNull(alexaAudioSink, "The provided AlexaAudioSink was null.");
            try {
                AlexaDialogControllerProxyV2 proxy = alexaServicesConnection.getProxy(alexaDialogControllerV2);
                boolean z = false;
                if (!alexaServicesConnection.isConnected()) {
                    Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "continueDialog"));
                    sendDisconnectedMetric(alexaServicesConnection, null);
                } else if (proxy == null) {
                    sendOutOfTurnMetric(alexaServicesConnection, null);
                    throw new IllegalStateException("Tried to continue a dialog with a AlexaDialogController which has not started a dialog");
                } else if (h.a(alexaAudioMetadata)) {
                    continueDialog(alexaServicesConnection, proxy, alexaAudioMetadata, alexaAudioSink.getReadDescriptor());
                    z = true;
                } else {
                    Log.e(AlexaServices.TAG, "Invalid Alexa Audio Metadata");
                    sendInvalidAudioMetadataMetric(alexaServicesConnection, null);
                }
                if (z) {
                }
            } finally {
                alexaAudioSink.abandon();
                dropDialog(alexaDialogControllerV2, true);
            }
        }

        public static void deregisterListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaStateListener alexaStateListener) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaStateListener, "The provided AlexaStateListener was null.");
            AlexaStateListenerProxy removeListener = alexaServicesConnection.removeListener(alexaStateListener);
            if (removeListener != null) {
                deregisterListener(alexaServicesConnection, removeListener);
            } else {
                Log.w(AlexaServices.TAG, "Proxy object is null. Cannot deregister listener");
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static void deregisterListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaStateListenerProxy alexaStateListenerProxy) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaStateListenerProxy, "The provided AlexaStateListenerProxy was null.");
            if (!alexaServicesConnection.isBoundToService()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_BOUND_MESSAGE, "deregister AlexaStateListener"));
                return;
            }
            try {
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender == null) {
                    return;
                }
                alexaServicesMessageSender.deregisterAlexaStateListener(client, alexaStateListenerProxy);
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }

        public static void deregisterUserSpeechListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaUserSpeechListener alexaUserSpeechListener) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaUserSpeechListener, "The provided AlexaUserSpeechListener was null.");
            AlexaUserSpeechListenerProxy removeListener = alexaServicesConnection.removeListener(alexaUserSpeechListener);
            if (!alexaServicesConnection.isBoundToService()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_BOUND_MESSAGE, "deregister AlexaUserSpeechListener"));
            } else if (removeListener == null) {
                Log.w(AlexaServices.TAG, "Proxy object is null. Cannot deregister listener");
            } else {
                try {
                    ExtendedClient client = alexaServicesConnection.getClient();
                    AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                    if (alexaServicesMessageSender == null) {
                        return;
                    }
                    alexaServicesMessageSender.deregisterAlexaUserSpeechListener(client, removeListener);
                } catch (RemoteException e) {
                    Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
                }
            }
        }

        private static void dropDialog(@Nullable AlexaDialogController alexaDialogController, boolean z) {
            Log.w(AlexaServices.TAG, "Dropping dialog");
            if (alexaDialogController != null) {
                if (z) {
                    alexaDialogController.onDialogStarted();
                }
                alexaDialogController.stopRecording();
                alexaDialogController.onDialogFinished();
            }
        }

        private static void dropDialog(@Nullable AlexaDialogControllerProxy alexaDialogControllerProxy, boolean z) {
            if (alexaDialogControllerProxy != null) {
                try {
                    String str = AlexaServices.TAG;
                    Log.w(str, "Dropping dialog: " + alexaDialogControllerProxy.getDialogIdentifier());
                    if (z) {
                        alexaDialogControllerProxy.onDialogStarted();
                    }
                    alexaDialogControllerProxy.stopRecording();
                    alexaDialogControllerProxy.onDialogFinished();
                } catch (RemoteException e) {
                    Log.w(AlexaServices.TAG, "Unable to drop the dialog", e);
                }
            }
        }

        private static void dropDialog(@Nullable AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2, boolean z) {
            if (alexaDialogControllerProxyV2 != null) {
                try {
                    String str = AlexaServices.TAG;
                    Log.w(str, "Dropping dialog: " + alexaDialogControllerProxyV2.getDialogIdentifier());
                    if (z) {
                        alexaDialogControllerProxyV2.onDialogStarted();
                    }
                    alexaDialogControllerProxyV2.stopRecording();
                    alexaDialogControllerProxyV2.onDialogFinished();
                } catch (RemoteException e) {
                    Log.w(AlexaServices.TAG, "Unable to drop the dialog", e);
                }
            }
        }

        private static void dropDialog(@Nullable AlexaDialogControllerV2 alexaDialogControllerV2, boolean z) {
            Log.w(AlexaServices.TAG, "Dropping dialog");
            if (alexaDialogControllerV2 != null) {
                if (z) {
                    alexaDialogControllerV2.onDialogStarted();
                }
                alexaDialogControllerV2.stopRecording();
                alexaDialogControllerV2.onDialogFinished();
            }
        }

        public static void muteMicrophone(@NonNull AlexaServicesConnection alexaServicesConnection, boolean z) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            if (!alexaServicesConnection.isConnected()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "muteMicrophone"));
                return;
            }
            try {
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender == null) {
                    return;
                }
                alexaServicesMessageSender.muteMicrophone(client, z);
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }

        public static void prepare(@NonNull AlexaServicesConnection alexaServicesConnection) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            if (!alexaServicesConnection.isConnected()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "prepare"));
                return;
            }
            try {
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender == null) {
                    return;
                }
                alexaServicesMessageSender.temporarilySuppressAllAudio(client);
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }

        public static void registerListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaStateListener alexaStateListener) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaStateListener, "The provided AlexaStateListener was null.");
            if (!alexaServicesConnection.isConnected()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "register AlexaStateListener"));
                return;
            }
            bq bqVar = new bq(alexaStateListener);
            registerListener(alexaServicesConnection, bqVar);
            alexaServicesConnection.addListener(alexaStateListener, bqVar);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static void registerListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaStateListenerProxy alexaStateListenerProxy) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaStateListenerProxy, "The provided AlexaStateListenerProxy was null.");
            if (!alexaServicesConnection.isConnected()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "register AlexaStateListener"));
                return;
            }
            try {
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender == null) {
                    return;
                }
                alexaServicesMessageSender.registerAlexaStateListener(client, alexaStateListenerProxy);
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }

        public static void registerUserSpeechListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull final AlexaUserSpeechListener alexaUserSpeechListener) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaUserSpeechListener, "The provided AlexaUserSpeechListener was null.");
            if (!alexaServicesConnection.isConnected()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "register AlexaUserSpeechListener"));
                return;
            }
            AlexaUserSpeechListenerProxy.Stub stub = new AlexaUserSpeechListenerProxy.Stub() { // from class: com.amazon.alexa.api.AlexaServices.Recognizer.1
                @Override // com.amazon.alexa.api.AlexaUserSpeechListenerProxy
                public void onAlexaUserSpeechVolumeChanged(final float f) {
                    ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.AlexaServices.Recognizer.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AlexaUserSpeechListener.this.onAlexaUserSpeechVolumeChanged(f);
                        }
                    });
                }
            };
            try {
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender == null) {
                    return;
                }
                alexaServicesMessageSender.registerAlexaUserSpeechListener(client, stub);
                alexaServicesConnection.addListener(alexaUserSpeechListener, stub);
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }

        private static void reportMetric(AlexaServicesConnection alexaServicesConnection, AlexaMetricsName alexaMetricsName, @Nullable String str) {
            new MetricBroadcastSender(alexaServicesConnection).sendVoiceInteractionEvent(alexaMetricsName, str);
        }

        private static void sendBindingFailureMetric(AlexaServicesConnection alexaServicesConnection, @Nullable String str) {
            reportMetric(alexaServicesConnection, AlexaMetricsName.VoiceInteraction.Failure.BINDING_ERROR, str);
        }

        private static void sendDisconnectedMetric(AlexaServicesConnection alexaServicesConnection, @Nullable String str) {
            LeaderSelectionAuthority.LeaderSelectionFailureReason leaderSelectionFailureReason = alexaServicesConnection.getLeaderSelectionFailureReason();
            reportMetric(alexaServicesConnection, leaderSelectionFailureReason != null ? leaderSelectionFailureReason.equals(LeaderSelectionAuthority.LeaderSelectionFailureReason.DISABLED) ? AlexaMetricsName.VoiceInteraction.Failure.LEADER_DISABLED_ERROR : AlexaMetricsName.VoiceInteraction.Failure.LEADER_SELECTION_ERROR : AlexaMetricsName.VoiceInteraction.Failure.LOCAL_SERVICE_DISCONNECTED, str);
        }

        private static void sendInvalidAudioMetadataMetric(AlexaServicesConnection alexaServicesConnection, @Nullable String str) {
            reportMetric(alexaServicesConnection, AlexaMetricsName.VoiceInteraction.Abandoned.INVALID_AUDIO_METADATA, str);
        }

        private static void sendOutOfTurnMetric(AlexaServicesConnection alexaServicesConnection, @Nullable String str) {
            reportMetric(alexaServicesConnection, AlexaMetricsName.VoiceInteraction.Abandoned.OUT_OF_TURN, str);
        }

        @Deprecated
        public static void start(@NonNull AlexaServicesConnection alexaServicesConnection) {
            aj.a(alexaServicesConnection, "UNKNOWN", LaunchType.UNKNOWN);
        }

        public static void start(@NonNull AlexaServicesConnection alexaServicesConnection, String str) {
            aj.a(alexaServicesConnection, str, LaunchType.UNKNOWN);
        }

        public static void start(@NonNull AlexaServicesConnection alexaServicesConnection, String str, AlexaUserInterfaceOptions alexaUserInterfaceOptions) {
            aj.a(alexaServicesConnection, str, LaunchType.UNKNOWN, alexaUserInterfaceOptions);
        }

        public static void start(@NonNull AlexaServicesConnection alexaServicesConnection, String str, LaunchType launchType) {
            aj.a(alexaServicesConnection, str, launchType);
        }

        public static void start(@NonNull AlexaServicesConnection alexaServicesConnection, String str, LaunchType launchType, AlexaUserInterfaceOptions alexaUserInterfaceOptions) {
            aj.a(alexaServicesConnection, str, launchType, alexaUserInterfaceOptions);
        }

        @Deprecated
        public static void startDialog(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaDialogController alexaDialogController, @NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink) {
            startDialog(alexaServicesConnection, alexaDialogController, alexaAudioMetadata, alexaAudioSink, (AlexaDialogExtras) null);
        }

        /* JADX WARN: Removed duplicated region for block: B:24:0x0063  */
        /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
        @java.lang.Deprecated
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static void startDialog(@com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaServicesConnection r5, @com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaDialogController r6, @com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaAudioMetadata r7, @com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaAudioSink r8, @com.amazon.alexa.client.annotations.Nullable com.amazon.alexa.api.AlexaDialogExtras r9) {
            /*
                java.lang.String r0 = "The provided AlexaServicesConnection was null."
                com.amazon.alexa.utils.validation.Preconditions.notNull(r5, r0)
                java.lang.String r0 = "The provided AlexaDialogController was null."
                com.amazon.alexa.utils.validation.Preconditions.notNull(r6, r0)
                java.lang.String r0 = "The provided AlexaAudioMetadata was null."
                com.amazon.alexa.utils.validation.Preconditions.notNull(r7, r0)
                java.lang.String r0 = "The provided AlexaAudioSink was null."
                com.amazon.alexa.utils.validation.Preconditions.notNull(r8, r0)
                r0 = 0
                if (r9 == 0) goto L1c
                java.lang.String r1 = r9.getInvocationType()
                goto L1d
            L1c:
                r1 = r0
            L1d:
                r2 = 1
                r3 = 0
                boolean r4 = r5.isConnected()     // Catch: java.lang.Throwable -> L6a
                if (r4 == 0) goto L48
                boolean r4 = com.amazon.alexa.api.h.a(r7)     // Catch: java.lang.Throwable -> L6a
                if (r4 == 0) goto L44
                com.amazon.alexa.api.ak r1 = new com.amazon.alexa.api.ak     // Catch: java.lang.Throwable -> L6a
                r1.<init>(r6, r5)     // Catch: java.lang.Throwable -> L6a
                android.os.ParcelFileDescriptor r4 = r8.getReadDescriptor()     // Catch: java.lang.Throwable -> L6a
                if (r9 != 0) goto L37
                goto L3b
            L37:
                android.os.Bundle r0 = r9.getBundle()     // Catch: java.lang.Throwable -> L6a
            L3b:
                startDialog(r5, r1, r7, r4, r0)     // Catch: java.lang.Throwable -> L6a
                r5.addProxy(r6, r1)     // Catch: java.lang.Throwable -> L42
                goto L61
            L42:
                r5 = move-exception
                goto L6c
            L44:
                sendInvalidAudioMetadataMetric(r5, r1)     // Catch: java.lang.Throwable -> L6a
                goto L60
            L48:
                java.lang.String r7 = com.amazon.alexa.api.AlexaServices.access$000()     // Catch: java.lang.Throwable -> L6a
                java.util.Locale r9 = java.util.Locale.US     // Catch: java.lang.Throwable -> L6a
                java.lang.String r0 = "Connection object is not connected. Cannot %s"
                java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L6a
                java.lang.String r4 = "startDialog"
                r2[r3] = r4     // Catch: java.lang.Throwable -> L6a
                java.lang.String r9 = java.lang.String.format(r9, r0, r2)     // Catch: java.lang.Throwable -> L6a
                android.util.Log.e(r7, r9)     // Catch: java.lang.Throwable -> L6a
                sendDisconnectedMetric(r5, r1)     // Catch: java.lang.Throwable -> L6a
            L60:
                r2 = r3
            L61:
                if (r2 != 0) goto L69
                r8.abandon()
                dropDialog(r6, r3)
            L69:
                return
            L6a:
                r5 = move-exception
                r2 = r3
            L6c:
                if (r2 != 0) goto L74
                r8.abandon()
                dropDialog(r6, r3)
            L74:
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.api.AlexaServices.Recognizer.startDialog(com.amazon.alexa.api.AlexaServicesConnection, com.amazon.alexa.api.AlexaDialogController, com.amazon.alexa.api.AlexaAudioMetadata, com.amazon.alexa.api.AlexaAudioSink, com.amazon.alexa.api.AlexaDialogExtras):void");
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Deprecated
        public static void startDialog(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaDialogControllerProxy alexaDialogControllerProxy, @NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull ParcelFileDescriptor parcelFileDescriptor, @Nullable Bundle bundle) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaDialogControllerProxy, "The provided AlexaDialogControllerProxy was null.");
            Preconditions.notNull(alexaAudioMetadata, "The provided AlexaAudioMetadata was null.");
            Preconditions.notNull(parcelFileDescriptor, "The provided ParcelFileDescriptor was null.");
            AlexaAudioSink alexaAudioSink = new AlexaAudioSink(parcelFileDescriptor, null);
            AlexaDialogExtras alexaDialogExtras = new AlexaDialogExtras(bundle);
            AlexaDialogRequest build = AlexaDialogRequest.builder().setInvocationType(alexaDialogExtras.getInvocationType()).setLaunchType(alexaDialogExtras.getLaunchType()).build();
            LegacyUserSpeechProvider legacyUserSpeechProvider = new LegacyUserSpeechProvider(alexaDialogControllerProxy, alexaServicesConnection.getAllUplListeners(), alexaAudioMetadata, alexaAudioSink, (AlexaDataSink) null, alexaDialogExtras);
            alexaServicesConnection.putDialogController(alexaDialogControllerProxy, legacyUserSpeechProvider);
            AlexaServicesApis.UserSpeechProviders.register(alexaServicesConnection, legacyUserSpeechProvider, legacyUserSpeechProvider.getUserSpeechProviderMetadata());
            AlexaServicesApis.UserSpeechRecognizer.requestDialog(alexaServicesConnection, legacyUserSpeechProvider, build);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static void startDialog(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2, @NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull ParcelFileDescriptor parcelFileDescriptor, @Nullable ParcelFileDescriptor parcelFileDescriptor2, @Nullable Bundle bundle) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaDialogControllerProxyV2, "The provided AlexaDialogControllerProxy was null.");
            Preconditions.notNull(alexaAudioMetadata, "The provided AlexaAudioMetadata was null.");
            Preconditions.notNull(parcelFileDescriptor, "The provided ParcelFileDescriptor was null.");
            AlexaAudioSink alexaAudioSink = new AlexaAudioSink(parcelFileDescriptor, null);
            AlexaDialogExtras alexaDialogExtras = new AlexaDialogExtras(bundle);
            AlexaDialogRequest build = AlexaDialogRequest.builder().setInvocationType(alexaDialogExtras.getInvocationType()).setLaunchType(alexaDialogExtras.getLaunchType()).build();
            LegacyUserSpeechProvider legacyUserSpeechProvider = new LegacyUserSpeechProvider(alexaDialogControllerProxyV2, alexaServicesConnection.getAllUplListeners(), alexaAudioMetadata, alexaAudioSink, parcelFileDescriptor2 != null ? new AlexaDataSink(parcelFileDescriptor2, null) : null, alexaDialogExtras);
            alexaServicesConnection.putDialogController(alexaDialogControllerProxyV2, legacyUserSpeechProvider);
            AlexaServicesApis.UserSpeechProviders.register(alexaServicesConnection, legacyUserSpeechProvider, legacyUserSpeechProvider.getUserSpeechProviderMetadata());
            AlexaServicesApis.UserSpeechRecognizer.requestDialog(alexaServicesConnection, legacyUserSpeechProvider, build);
        }

        @Deprecated
        public static void startDialog(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaDialogControllerV2 alexaDialogControllerV2, @NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink) {
            startDialog(alexaServicesConnection, alexaDialogControllerV2, alexaAudioMetadata, alexaAudioSink, (AlexaDataSink) null, (AlexaDialogExtras) null);
        }

        @Deprecated
        public static void startDialog(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaDialogControllerV2 alexaDialogControllerV2, @NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink, @NonNull AlexaDataSink alexaDataSink) {
            Preconditions.notNull(alexaDataSink, "The provided AlexaDataSink was null.");
            startDialog(alexaServicesConnection, alexaDialogControllerV2, alexaAudioMetadata, alexaAudioSink, alexaDataSink, (AlexaDialogExtras) null);
        }

        /* JADX WARN: Removed duplicated region for block: B:27:0x0074  */
        /* JADX WARN: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
        @java.lang.Deprecated
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public static void startDialog(@com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaServicesConnection r12, @com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaDialogControllerV2 r13, @com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaAudioMetadata r14, @com.amazon.alexa.client.annotations.NonNull com.amazon.alexa.api.AlexaAudioSink r15, @com.amazon.alexa.client.annotations.Nullable com.amazon.alexa.api.AlexaDataSink r16, @com.amazon.alexa.client.annotations.Nullable com.amazon.alexa.api.AlexaDialogExtras r17) {
            /*
                r0 = r12
                r7 = r13
                java.lang.String r1 = "The provided AlexaServicesConnection was null."
                com.amazon.alexa.utils.validation.Preconditions.notNull(r12, r1)
                java.lang.String r1 = "The provided AlexaDialogController was null."
                com.amazon.alexa.utils.validation.Preconditions.notNull(r13, r1)
                java.lang.String r1 = "The provided AlexaAudioMetadata was null."
                r3 = r14
                com.amazon.alexa.utils.validation.Preconditions.notNull(r14, r1)
                java.lang.String r1 = "The provided AlexaAudioSink was null."
                r8 = r15
                com.amazon.alexa.utils.validation.Preconditions.notNull(r15, r1)
                r1 = 0
                if (r17 == 0) goto L20
                java.lang.String r2 = r17.getInvocationType()
                goto L21
            L20:
                r2 = r1
            L21:
                r9 = 1
                r10 = 0
                boolean r4 = r12.isConnected()     // Catch: java.lang.Throwable -> L80
                if (r4 == 0) goto L59
                boolean r4 = com.amazon.alexa.api.h.a(r14)     // Catch: java.lang.Throwable -> L80
                if (r4 == 0) goto L55
                com.amazon.alexa.api.al r11 = new com.amazon.alexa.api.al     // Catch: java.lang.Throwable -> L80
                r11.<init>(r13, r12)     // Catch: java.lang.Throwable -> L80
                android.os.ParcelFileDescriptor r4 = r15.getReadDescriptor()     // Catch: java.lang.Throwable -> L80
                if (r16 == 0) goto L40
                android.os.ParcelFileDescriptor r2 = r16.getReadDescriptor()     // Catch: java.lang.Throwable -> L80
                r5 = r2
                goto L41
            L40:
                r5 = r1
            L41:
                if (r17 != 0) goto L44
                goto L48
            L44:
                android.os.Bundle r1 = r17.getBundle()     // Catch: java.lang.Throwable -> L80
            L48:
                r6 = r1
                r1 = r12
                r2 = r11
                r3 = r14
                startDialog(r1, r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L80
                r12.addProxy(r13, r11)     // Catch: java.lang.Throwable -> L53
                goto L72
            L53:
                r0 = move-exception
                goto L82
            L55:
                sendInvalidAudioMetadataMetric(r12, r2)     // Catch: java.lang.Throwable -> L80
                goto L71
            L59:
                java.lang.String r1 = com.amazon.alexa.api.AlexaServices.access$000()     // Catch: java.lang.Throwable -> L80
                java.util.Locale r3 = java.util.Locale.US     // Catch: java.lang.Throwable -> L80
                java.lang.String r4 = "Connection object is not connected. Cannot %s"
                java.lang.Object[] r5 = new java.lang.Object[r9]     // Catch: java.lang.Throwable -> L80
                java.lang.String r6 = "startDialog"
                r5[r10] = r6     // Catch: java.lang.Throwable -> L80
                java.lang.String r3 = java.lang.String.format(r3, r4, r5)     // Catch: java.lang.Throwable -> L80
                android.util.Log.e(r1, r3)     // Catch: java.lang.Throwable -> L80
                sendDisconnectedMetric(r12, r2)     // Catch: java.lang.Throwable -> L80
            L71:
                r9 = r10
            L72:
                if (r9 != 0) goto L7f
                r15.abandon()
                if (r16 == 0) goto L7c
                r16.abandon()
            L7c:
                dropDialog(r13, r10)
            L7f:
                return
            L80:
                r0 = move-exception
                r9 = r10
            L82:
                if (r9 != 0) goto L8f
                r15.abandon()
                if (r16 == 0) goto L8c
                r16.abandon()
            L8c:
                dropDialog(r13, r10)
            L8f:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.api.AlexaServices.Recognizer.startDialog(com.amazon.alexa.api.AlexaServicesConnection, com.amazon.alexa.api.AlexaDialogControllerV2, com.amazon.alexa.api.AlexaAudioMetadata, com.amazon.alexa.api.AlexaAudioSink, com.amazon.alexa.api.AlexaDataSink, com.amazon.alexa.api.AlexaDialogExtras):void");
        }

        @Deprecated
        public static void startDialog(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaDialogControllerV2 alexaDialogControllerV2, @NonNull AlexaAudioMetadata alexaAudioMetadata, @NonNull AlexaAudioSink alexaAudioSink, @Nullable AlexaDialogExtras alexaDialogExtras) {
            startDialog(alexaServicesConnection, alexaDialogControllerV2, alexaAudioMetadata, alexaAudioSink, (AlexaDataSink) null, alexaDialogExtras);
        }

        public static void stop(@NonNull AlexaServicesConnection alexaServicesConnection) {
            aj.a(alexaServicesConnection);
        }

        @Deprecated
        public static void stopDialogTurn(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaDialogController alexaDialogController) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaDialogController, "The provided AlexaDialogController was null.");
            if (alexaServicesConnection.isConnected()) {
                AlexaDialogControllerProxy proxy = alexaServicesConnection.getProxy(alexaDialogController);
                if (proxy != null) {
                    stopDialogTurn(alexaServicesConnection, proxy);
                    return;
                }
                Log.w(AlexaServices.TAG, "Tried to stop a dialog turn with a AlexaDialogController which has not started a dialog");
            } else {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "stopDialogTurn"));
            }
            dropDialog(alexaDialogController, true);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static void stopDialogTurn(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaDialogControllerProxy alexaDialogControllerProxy) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaDialogControllerProxy, "The provided AlexaDialogControllerProxy was null.");
            LegacyUserSpeechProvider removeDialogController = alexaServicesConnection.removeDialogController(alexaDialogControllerProxy);
            if (removeDialogController == null) {
                dropDialog(alexaDialogControllerProxy, true);
                return;
            }
            try {
                removeDialogController.stopDialog();
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, "unable to stop the dialog", e);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static void stopDialogTurn(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaDialogControllerProxyV2, "The provided AlexaDialogControllerProxy was null.");
            LegacyUserSpeechProvider removeDialogController = alexaServicesConnection.removeDialogController(alexaDialogControllerProxyV2);
            if (removeDialogController == null) {
                dropDialog(alexaDialogControllerProxyV2, true);
                return;
            }
            try {
                removeDialogController.stopDialog();
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, "Unable to stop the dialog", e);
            }
        }

        public static void stopDialogTurn(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaDialogControllerV2 alexaDialogControllerV2) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaDialogControllerV2, "The provided AlexaDialogController was null.");
            if (alexaServicesConnection.isConnected()) {
                AlexaDialogControllerProxyV2 proxy = alexaServicesConnection.getProxy(alexaDialogControllerV2);
                if (proxy != null) {
                    stopDialogTurn(alexaServicesConnection, proxy);
                    return;
                }
                Log.w(AlexaServices.TAG, "Tried to stop a dialog turn with a AlexaDialogController which has not started a dialog");
            } else {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "stopDialogTurn"));
            }
            dropDialog(alexaDialogControllerV2, true);
        }
    }

    /* loaded from: classes6.dex */
    public static class Settings {
        private Settings() {
            throw new UnsupportedOperationException();
        }

        public static void deregisterListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaSettingsListener alexaSettingsListener) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaSettingsListener, "The provided AlexaSettingsListener was null.");
            AlexaSettingsListenerProxy removeListener = alexaServicesConnection.removeListener(alexaSettingsListener);
            if (removeListener != null) {
                deregisterListener(alexaServicesConnection, removeListener);
            }
        }

        public static void deregisterListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaSettingsListenerProxy alexaSettingsListenerProxy) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaSettingsListenerProxy, "The provided AlexaSettingsListenerProxy was null.");
            if (!alexaServicesConnection.isBoundToService()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_BOUND_MESSAGE, "deregister AlexaSettingsListener"));
                return;
            }
            try {
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender == null) {
                    return;
                }
                alexaServicesMessageSender.deregisterAlexaSettingsListener(client, alexaSettingsListenerProxy);
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }

        @Nullable
        public static java.util.Locale getLocale(@NonNull AlexaServicesConnection alexaServicesConnection) {
            List<String> locales;
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            if (alexaServicesConnection.isBoundToService()) {
                try {
                    ExtendedClient client = alexaServicesConnection.getClient();
                    AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                    if (alexaServicesMessageSender != null && (locales = alexaServicesMessageSender.getLocales(client)) != null && !locales.isEmpty()) {
                        return java.util.Locale.forLanguageTag(locales.get(0));
                    }
                    return null;
                } catch (RemoteException e) {
                    Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
                }
            } else {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_BOUND_MESSAGE, "getLocale"));
            }
            return null;
        }

        @NonNull
        public static Set<java.util.Locale> getSupportedLocales(@NonNull AlexaServicesConnection alexaServicesConnection) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            HashSet hashSet = new HashSet();
            if (alexaServicesConnection.isBoundToService()) {
                try {
                    ExtendedClient client = alexaServicesConnection.getClient();
                    AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                    if (alexaServicesMessageSender != null) {
                        for (String str : alexaServicesMessageSender.getSupportedLocales(client)) {
                            hashSet.add(java.util.Locale.forLanguageTag(str));
                        }
                    }
                } catch (RemoteException e) {
                    Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
                }
            } else {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_BOUND_MESSAGE, "getSupportedLocales"));
            }
            return hashSet;
        }

        public static void registerListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull final AlexaSettingsListener alexaSettingsListener) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaSettingsListener, "The provided AlexaSettingsListener was null.");
            AlexaSettingsListenerProxy.Stub stub = new AlexaSettingsListenerProxy.Stub() { // from class: com.amazon.alexa.api.AlexaServices.Settings.1
                @Override // com.amazon.alexa.api.AlexaSettingsListenerProxy
                public void onLocaleChanged(final String str) throws RemoteException {
                    ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.AlexaServices.Settings.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AlexaSettingsListener.this.onLocaleChanged(java.util.Locale.forLanguageTag(str));
                        }
                    });
                }
            };
            alexaServicesConnection.addListener(alexaSettingsListener, stub);
            registerListener(alexaServicesConnection, stub);
        }

        public static void registerListener(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaSettingsListenerProxy alexaSettingsListenerProxy) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(alexaSettingsListenerProxy, "The provided AlexaSettingsListenerProxy was null.");
            if (!alexaServicesConnection.isConnected()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "register AlexaSettingsListener"));
                return;
            }
            try {
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender == null) {
                    return;
                }
                alexaServicesMessageSender.registerAlexaSettingsListener(client, alexaSettingsListenerProxy);
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }

        public static void setLocale(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull java.util.Locale locale) {
            Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
            Preconditions.notNull(locale, "The provided Locale was null.");
            if (!alexaServicesConnection.isConnected()) {
                Log.e(AlexaServices.TAG, String.format(java.util.Locale.US, AlexaServices.NOT_CONNECTED_MESSAGE, "setLocale"));
                return;
            }
            try {
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender == null) {
                    return;
                }
                alexaServicesMessageSender.setLocales(client, Collections.singletonList(locale.toLanguageTag()), false);
            } catch (RemoteException e) {
                Log.e(AlexaServices.TAG, AlexaServicesTools.MESSAGING_ERROR, e);
            }
        }
    }

    private AlexaServices() {
        throw new UnsupportedOperationException();
    }

    public static void disable(Context context) {
        Log.i(TAG, "Disabling Alexa");
        LeaderSelector.enable(context, false);
        setServiceEnabled(context, ALEXA_SERVICE_NAME, false);
        setServiceEnabled(context, AUDIO_PROVIDER_SERVICE_NAME, false);
    }

    private static boolean doesServiceExist(Context context, String str) {
        return ComponentEnabler.checkIfServiceExists(context.getPackageManager(), new ComponentName(context.getPackageName(), str));
    }

    public static void enable(Context context) {
        Log.i(TAG, "Enabling Alexa");
        LeaderSelector.enable(context, true);
        setServiceEnabled(context, ALEXA_SERVICE_NAME, true);
        setServiceEnabled(context, AUDIO_PROVIDER_SERVICE_NAME, true);
    }

    public static boolean isAlexaEnabled(Context context) {
        boolean isEnabled = LeaderSelector.isEnabled(context);
        if (isEnabled && doesServiceExist(context, ALEXA_SERVICE_NAME)) {
            isEnabled = isServiceEnabled(context, ALEXA_SERVICE_NAME);
        }
        return (!isEnabled || !doesServiceExist(context, AUDIO_PROVIDER_SERVICE_NAME)) ? isEnabled : isServiceEnabled(context, AUDIO_PROVIDER_SERVICE_NAME);
    }

    private static boolean isServiceEnabled(Context context, String str) {
        return ComponentEnabler.checkIfServiceIsEnabled(context.getPackageManager(), new ComponentName(context.getPackageName(), str));
    }

    private static void setServiceEnabled(Context context, String str, boolean z) {
        ComponentEnabler.setComponentEnabled(context.getPackageManager(), new ComponentName(context.getPackageName(), str), z);
    }
}
