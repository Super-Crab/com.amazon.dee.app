package com.amazon.alexa.api;

import android.util.Log;
import com.amazon.alexa.api.AlexaUserSpeechListenerProxy;
import com.amazon.alexa.api.messages.messagereceiver.MessageReceiver;
import com.amazon.alexa.client.annotations.NonNull;
import com.amazon.alexa.utils.ApiThreadHelper;
import com.amazon.alexa.utils.validation.Preconditions;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class j {
    private static final String a = "j";

    private j() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaAttentionSystemListener alexaAttentionSystemListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaAttentionSystemListener, "The provided AttentionSystemListener was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).registerAttentionSystemListener(alexaServicesConnection.getClient(), alexaServicesConnection.getListener(alexaAttentionSystemListener));
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaAttentionSystemSettingsListener, "The provided AlexaAttentionSystemSettingListener was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).registerAlexaAttentionSystemSettingsListener(alexaServicesConnection.getClient(), alexaServicesConnection.getListener(alexaAttentionSystemSettingsListener));
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaStateListener alexaStateListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaStateListener, "The provided AlexaStateListener was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            bq bqVar = new bq(alexaStateListener);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
            if (alexaServicesMessageSender == null) {
                return;
            }
            alexaServicesMessageSender.registerAlexaStateListener(client, bqVar);
            alexaServicesConnection.addListener(alexaStateListener, bqVar);
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull final AlexaUserSpeechListener alexaUserSpeechListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaUserSpeechListener, "The provided AlexaUserSpeechListener was null.");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            AlexaUserSpeechListenerProxy.Stub stub = new AlexaUserSpeechListenerProxy.Stub() { // from class: com.amazon.alexa.api.j.1
                @Override // com.amazon.alexa.api.AlexaUserSpeechListenerProxy
                public void onAlexaUserSpeechVolumeChanged(final float f) {
                    ApiThreadHelper.runOnUiThread(new Runnable() { // from class: com.amazon.alexa.api.j.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            AlexaUserSpeechListener.this.onAlexaUserSpeechVolumeChanged(f);
                        }
                    });
                }
            };
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
            if (alexaServicesMessageSender == null) {
                return;
            }
            alexaServicesMessageSender.registerAlexaUserSpeechListener(client, stub);
            alexaServicesConnection.addListener(alexaUserSpeechListener, stub);
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a(AlexaServicesConnection alexaServicesConnection, boolean z) {
        Preconditions.notNull(alexaServicesConnection, "alexaAudioProviderConnection was null");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
            if (alexaServicesMessageSender == null) {
                return;
            }
            alexaServicesMessageSender.setWakeSoundEnabled(client, z);
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(AlexaServicesConnection alexaServicesConnection) {
        Preconditions.notNull(alexaServicesConnection, "alexaAudioProviderConnection was null");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
            if (alexaServicesMessageSender == null) {
                return false;
            }
            return alexaServicesMessageSender.isWakeSoundEnabled(client);
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaAttentionSystemListener alexaAttentionSystemListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaAttentionSystemListener, "The provided AttentionSystemListener was null.");
        MessageReceiver<ApiType_AttentionSystemListenerMessageType> removeListener = alexaServicesConnection.removeListener(alexaAttentionSystemListener);
        try {
            AlexaServicesTools.checkAlexaBound(alexaServicesConnection);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender messageSender = AlexaServicesTools.getMessageSender(alexaServicesConnection);
            if (removeListener == null) {
                Log.e(a, "can't deregister AlexaAttentionSystemListener");
            } else {
                messageSender.deregisterAttentionSystemListener(client, removeListener);
            }
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaAttentionSystemSettingsListener alexaAttentionSystemSettingsListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaAttentionSystemSettingsListener, "The provided AlexaAttentionSystemSettingListener was null.");
        MessageReceiver<AlexaAttentionSystemSettingsMessageType> removeListener = alexaServicesConnection.removeListener(alexaAttentionSystemSettingsListener);
        if (removeListener == null) {
            Log.e(a, "can't deregister AttentionSystemSettingsListener");
            return;
        }
        try {
            AlexaServicesTools.checkAlexaBound(alexaServicesConnection);
            AlexaServicesTools.getMessageSender(alexaServicesConnection).deregisterAlexaAttentionSystemSettingsListener(alexaServicesConnection.getClient(), removeListener);
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaStateListener alexaStateListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaStateListener, "The provided AlexaStateListener was null.");
        AlexaStateListenerProxy removeListener = alexaServicesConnection.removeListener(alexaStateListener);
        try {
            AlexaServicesTools.checkAlexaBound(alexaServicesConnection);
            if (removeListener != null) {
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender != null) {
                    alexaServicesMessageSender.deregisterAlexaStateListener(client, removeListener);
                }
            } else {
                Log.w(a, "Proxy object is null. Cannot deregister listener");
            }
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(@NonNull AlexaServicesConnection alexaServicesConnection, @NonNull AlexaUserSpeechListener alexaUserSpeechListener) {
        Preconditions.notNull(alexaServicesConnection, "The provided AlexaServicesConnection was null.");
        Preconditions.notNull(alexaUserSpeechListener, "The provided AlexaUserSpeechListener was null.");
        AlexaUserSpeechListenerProxy removeListener = alexaServicesConnection.removeListener(alexaUserSpeechListener);
        try {
            AlexaServicesTools.checkAlexaBound(alexaServicesConnection);
            if (removeListener != null) {
                ExtendedClient client = alexaServicesConnection.getClient();
                AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
                if (alexaServicesMessageSender != null) {
                    alexaServicesMessageSender.deregisterAlexaUserSpeechListener(client, removeListener);
                }
            } else {
                Log.w(a, "Proxy object is null. Cannot deregister listener");
            }
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void b(AlexaServicesConnection alexaServicesConnection, boolean z) {
        Preconditions.notNull(alexaServicesConnection, "alexaAudioProviderConnection was null");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
            if (alexaServicesMessageSender == null) {
                return;
            }
            alexaServicesMessageSender.setEndpointSoundEnabled(client, z);
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean b(AlexaServicesConnection alexaServicesConnection) {
        Preconditions.notNull(alexaServicesConnection, "alexaServicesConnection was null");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
            if (alexaServicesMessageSender == null) {
                return false;
            }
            return alexaServicesMessageSender.isEndpointSoundEnabled(client);
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void c(AlexaServicesConnection alexaServicesConnection) {
        Preconditions.notNull(alexaServicesConnection, "alexaServicesConnection was null");
        try {
            AlexaServicesTools.checkAlexaConnection(alexaServicesConnection);
            ExtendedClient client = alexaServicesConnection.getClient();
            AlexaServicesMessageSender alexaServicesMessageSender = (AlexaServicesMessageSender) alexaServicesConnection.mo838get();
            if (alexaServicesMessageSender == null) {
                return;
            }
            alexaServicesMessageSender.stopForegroundAudioTask(client);
        } catch (Exception e) {
            Log.e(a, AlexaServicesTools.MESSAGING_ERROR, e);
        }
    }
}
