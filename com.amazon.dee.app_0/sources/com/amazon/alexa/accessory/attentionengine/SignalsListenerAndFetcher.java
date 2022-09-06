package com.amazon.alexa.accessory.attentionengine;

import android.content.Context;
import android.media.AudioManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.api.AlexaAudioChannel;
import com.amazon.alexa.api.compat.AlexaAudioPlaybackStatusListener;
import com.amazon.alexa.api.compat.AlexaMobileFrameworkApis;
import com.amazon.alexa.api.compat.AlexaState;
import com.amazon.alexa.api.compat.AlexaStateListener;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
/* loaded from: classes.dex */
public final class SignalsListenerAndFetcher {
    private static final String TAG = "SignalsListenerAndFetcher";
    private AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener;
    private AlexaState alexaState;
    private AlexaStateListener alexaStateListener;
    private AlexaMobileFrameworkApis amfApis;
    private AudioManager audioManager;
    private Map<AlexaAudioChannel, Boolean> audioPlaybackStatus;
    private boolean isInitialized;

    /* loaded from: classes.dex */
    private static class LazySignalsListenerAndFetcherHolder {
        static final SignalsListenerAndFetcher INSTANCE = new SignalsListenerAndFetcher();

        private LazySignalsListenerAndFetcherHolder() {
        }
    }

    private boolean getAudioPlaybackStatus(AlexaAudioChannel alexaAudioChannel) throws NullPointerException {
        Boolean bool = this.audioPlaybackStatus.get(alexaAudioChannel);
        if (bool != null) {
            return bool.booleanValue();
        }
        String str = TAG;
        Log.w(str, "getAudioPlaybackStatus - No updated value for channel: " + alexaAudioChannel);
        throw new NullPointerException("No updated value for alexaAudioChannel");
    }

    public static synchronized SignalsListenerAndFetcher getInstance() {
        SignalsListenerAndFetcher signalsListenerAndFetcher;
        synchronized (SignalsListenerAndFetcher.class) {
            signalsListenerAndFetcher = LazySignalsListenerAndFetcherHolder.INSTANCE;
        }
        return signalsListenerAndFetcher;
    }

    public synchronized void init(Context context) {
        Log.d(TAG, "init - start AMF Apis and register listeners");
        if (!this.isInitialized) {
            try {
                this.amfApis = new AlexaMobileFrameworkApis(context, TAG);
                this.audioManager = (AudioManager) context.getSystemService("audio");
                this.amfApis.start();
                this.amfApis.getAttentionSystem().registerStateListener(this.alexaStateListener);
                this.amfApis.getAudioPlaybackControl().registerAlexaAudioPlaybackStatusListener(this.alexaAudioPlaybackStatusListener);
                this.isInitialized = true;
            } catch (Exception e) {
                Log.e(TAG, "init - Exception: ", e);
            }
        }
    }

    public synchronized boolean isUserInDialogWithAlexa() throws RuntimeException {
        if (this.isInitialized) {
            boolean z = false;
            if (this.audioPlaybackStatus != null) {
                try {
                    return getAudioPlaybackStatus(AlexaAudioChannel.DIALOG);
                } catch (NullPointerException e) {
                    String str = TAG;
                    Log.w(str, "isUserInDialogWithAlexa - Exception: " + e);
                    return false;
                }
            }
            if (this.alexaState != AlexaState.IDLE) {
                z = true;
            }
            return z;
        }
        Log.w(TAG, "isUserInDialogWithAlexa - Signals Listener and Fetcher is not initialized.");
        throw new RuntimeException("Signals Listener and Fetcher is not initialized.");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0018, code lost:
        if (r5.audioManager.isMusicActive() != false) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized boolean isUserListeningToAudioEntertainment() throws java.lang.RuntimeException {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.isInitialized     // Catch: java.lang.Throwable -> L4d
            if (r0 == 0) goto L3e
            java.util.Map<com.amazon.alexa.api.AlexaAudioChannel, java.lang.Boolean> r0 = r5.audioPlaybackStatus     // Catch: java.lang.Throwable -> L4d
            if (r0 == 0) goto L36
            r0 = 0
            com.amazon.alexa.api.AlexaAudioChannel r1 = com.amazon.alexa.api.AlexaAudioChannel.CONTENT     // Catch: java.lang.NullPointerException -> L1d java.lang.Throwable -> L4d
            boolean r1 = r5.getAudioPlaybackStatus(r1)     // Catch: java.lang.NullPointerException -> L1d java.lang.Throwable -> L4d
            if (r1 != 0) goto L1a
            android.media.AudioManager r1 = r5.audioManager     // Catch: java.lang.NullPointerException -> L1d java.lang.Throwable -> L4d
            boolean r1 = r1.isMusicActive()     // Catch: java.lang.NullPointerException -> L1d java.lang.Throwable -> L4d
            if (r1 == 0) goto L1b
        L1a:
            r0 = 1
        L1b:
            monitor-exit(r5)
            return r0
        L1d:
            r1 = move-exception
            java.lang.String r2 = com.amazon.alexa.accessory.attentionengine.SignalsListenerAndFetcher.TAG     // Catch: java.lang.Throwable -> L4d
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4d
            r3.<init>()     // Catch: java.lang.Throwable -> L4d
            java.lang.String r4 = "isUserListeningToAudioEntertainment - Exception: "
            r3.append(r4)     // Catch: java.lang.Throwable -> L4d
            r3.append(r1)     // Catch: java.lang.Throwable -> L4d
            java.lang.String r1 = r3.toString()     // Catch: java.lang.Throwable -> L4d
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.w(r2, r1)     // Catch: java.lang.Throwable -> L4d
            monitor-exit(r5)
            return r0
        L36:
            android.media.AudioManager r0 = r5.audioManager     // Catch: java.lang.Throwable -> L4d
            boolean r0 = r0.isMusicActive()     // Catch: java.lang.Throwable -> L4d
            monitor-exit(r5)
            return r0
        L3e:
            java.lang.String r0 = com.amazon.alexa.accessory.attentionengine.SignalsListenerAndFetcher.TAG     // Catch: java.lang.Throwable -> L4d
            java.lang.String r1 = "isUserListeningToAudioEntertainment - Signals Listener and Fetcher is not initialized."
            com.amazon.alexa.accessory.notificationpublisher.utils.Log.w(r0, r1)     // Catch: java.lang.Throwable -> L4d
            java.lang.RuntimeException r0 = new java.lang.RuntimeException     // Catch: java.lang.Throwable -> L4d
            java.lang.String r1 = "Signals Listener and Fetcher is not initialized."
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L4d
            throw r0     // Catch: java.lang.Throwable -> L4d
        L4d:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessory.attentionengine.SignalsListenerAndFetcher.isUserListeningToAudioEntertainment():boolean");
    }

    public synchronized void tearDown() {
        Log.d(TAG, "tearDown");
        if (this.isInitialized) {
            try {
                this.amfApis.getAttentionSystem().deregisterStateListener(this.alexaStateListener);
                this.amfApis.getAudioPlaybackControl().deregisterAlexaAudioPlaybackStatusListener(this.alexaAudioPlaybackStatusListener);
                this.amfApis.stop();
                this.amfApis.destroy();
                this.audioManager = null;
                this.alexaState = null;
                this.audioPlaybackStatus = null;
                this.isInitialized = false;
            } catch (Exception e) {
                String str = TAG;
                Log.w(str, "tearDown - Exception: " + e);
            }
        }
    }

    private SignalsListenerAndFetcher() {
        this.audioManager = null;
        this.alexaState = AlexaState.IDLE;
        this.isInitialized = false;
        this.alexaStateListener = new AlexaStateListener() { // from class: com.amazon.alexa.accessory.attentionengine.SignalsListenerAndFetcher.1
            @Override // com.amazon.alexa.api.compat.AlexaStateListener
            public void onAlexaStateChanged(@Nullable AlexaState alexaState) {
                String str = SignalsListenerAndFetcher.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onAlexaStateChanged - New state = ");
                outline107.append(alexaState.name());
                Log.d(str, outline107.toString());
                synchronized (SignalsListenerAndFetcher.this) {
                    SignalsListenerAndFetcher.this.alexaState = alexaState;
                }
            }
        };
        this.alexaAudioPlaybackStatusListener = new AlexaAudioPlaybackStatusListener() { // from class: com.amazon.alexa.accessory.attentionengine.SignalsListenerAndFetcher.2
            @Override // com.amazon.alexa.api.compat.AlexaAudioPlaybackStatusListener
            public void onAudioPlaybackStatusChanged(@NonNull Map<AlexaAudioChannel, Boolean> map) {
                String str = SignalsListenerAndFetcher.TAG;
                Log.d(str, "onAudioPlaybackStatusChanged - New states = " + map);
                synchronized (SignalsListenerAndFetcher.this) {
                    SignalsListenerAndFetcher.this.audioPlaybackStatus = map;
                }
            }
        };
    }
}
