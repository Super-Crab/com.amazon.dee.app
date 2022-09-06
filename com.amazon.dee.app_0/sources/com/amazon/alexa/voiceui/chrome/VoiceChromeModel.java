package com.amazon.alexa.voiceui.chrome;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.AlexaStateListener;
import com.amazon.alexa.api.AlexaUserSpeechListener;
import com.amazon.alexa.voice.ui.speech.AttentionSystemContract;
import com.amazon.alexa.voice.ui.util.AlexaState;
import com.amazon.alexa.voice.ui.util.AlexaStateProperty;
import com.amazon.alexa.voice.ui.util.FloatProperty;
import com.amazon.alexa.voiceui.AlexaServicesApis;
import com.amazon.alexa.voiceui.cards.DefaultLocaleAuthority;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Singleton;
/* JADX INFO: Access modifiers changed from: package-private */
@Singleton
/* loaded from: classes11.dex */
public class VoiceChromeModel implements AttentionSystemContract {
    private final AlexaServicesApis alexaServicesApis;
    private final DefaultLocaleAuthority localeAuthority;
    private final AlexaStateProperty alexaStateProperty = new AlexaStateProperty(AlexaState.IDLE);
    private final FloatProperty soundLevelProperty = new FloatProperty();
    @VisibleForTesting
    final ConnectionListener connectionListener = new ConnectionListener(this, null);
    @VisibleForTesting
    final StateListener stateListener = new StateListener(this, null);

    /* renamed from: com.amazon.alexa.voiceui.chrome.VoiceChromeModel$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$api$AlexaState = new int[com.amazon.alexa.api.AlexaState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaState[com.amazon.alexa.api.AlexaState.PREPARING_TO_LISTEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaState[com.amazon.alexa.api.AlexaState.LISTENING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaState[com.amazon.alexa.api.AlexaState.FINISHING_LISTENING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaState[com.amazon.alexa.api.AlexaState.THINKING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaState[com.amazon.alexa.api.AlexaState.SPEAKING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaState[com.amazon.alexa.api.AlexaState.ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$alexa$api$AlexaState[com.amazon.alexa.api.AlexaState.IDLE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    @VisibleForTesting
    /* loaded from: classes11.dex */
    class ConnectionListener implements AlexaServicesConnection.ConnectionListener {
        /* synthetic */ ConnectionListener(VoiceChromeModel voiceChromeModel, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnected() {
            Locale locale = VoiceChromeModel.this.alexaServicesApis.getLocale();
            if (locale != null) {
                VoiceChromeModel.this.localeAuthority.setLocale(locale);
            }
            VoiceChromeModel.this.alexaServicesApis.registerAlexaStateListener(VoiceChromeModel.this.stateListener);
            VoiceChromeModel.this.alexaServicesApis.registerUserSpeechListener(VoiceChromeModel.this.stateListener);
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnectingFailed(AlexaConnectingFailedReason alexaConnectingFailedReason, String str) {
        }

        @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onDisconnected() {
            VoiceChromeModel.this.deregisterListeners();
        }

        private ConnectionListener() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes11.dex */
    public class StateListener implements AlexaStateListener, AlexaUserSpeechListener {
        /* synthetic */ StateListener(VoiceChromeModel voiceChromeModel, AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // com.amazon.alexa.api.AlexaStateListener
        public void onAlexaStateChanged(@NonNull com.amazon.alexa.api.AlexaState alexaState) {
            switch (alexaState.ordinal()) {
                case 1:
                case 2:
                case 3:
                    VoiceChromeModel.this.alexaStateProperty.set(AlexaState.LISTENING);
                    return;
                case 4:
                    VoiceChromeModel.this.alexaStateProperty.set(AlexaState.THINKING);
                    return;
                case 5:
                    VoiceChromeModel.this.alexaStateProperty.set(AlexaState.SPEAKING);
                    return;
                case 6:
                    VoiceChromeModel.this.alexaStateProperty.set(AlexaState.ERROR);
                    return;
                default:
                    VoiceChromeModel.this.alexaStateProperty.set(AlexaState.IDLE);
                    return;
            }
        }

        @Override // com.amazon.alexa.api.AlexaUserSpeechListener
        public void onAlexaUserSpeechVolumeChanged(float f) {
            VoiceChromeModel.this.soundLevelProperty.set(f);
        }

        private StateListener() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public VoiceChromeModel(AlexaServicesApis alexaServicesApis, DefaultLocaleAuthority defaultLocaleAuthority) {
        this.alexaServicesApis = alexaServicesApis;
        this.localeAuthority = defaultLocaleAuthority;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deregisterListeners() {
        this.alexaServicesApis.deregisterAlexaStateListener(this.stateListener);
        this.alexaServicesApis.deregisterUserSpeechListener(this.stateListener);
        this.alexaStateProperty.set(AlexaState.IDLE);
    }

    @Override // com.amazon.alexa.voice.ui.speech.AttentionSystemContract
    public AlexaStateProperty alexaState() {
        return this.alexaStateProperty;
    }

    public void initialize() {
        this.alexaServicesApis.registerConnectionListener(this.connectionListener);
    }

    public void release() {
        deregisterListeners();
        this.alexaServicesApis.deregisterConnectionListener(this.connectionListener);
    }

    @Override // com.amazon.alexa.voice.ui.speech.AttentionSystemContract
    public FloatProperty soundLevel() {
        return this.soundLevelProperty;
    }
}
