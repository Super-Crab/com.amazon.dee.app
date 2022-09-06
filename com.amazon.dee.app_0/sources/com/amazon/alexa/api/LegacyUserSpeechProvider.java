package com.amazon.alexa.api;

import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.client.annotations.Nullable;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes6.dex */
public class LegacyUserSpeechProvider implements AlexaUserSpeechProvider {
    private static final String TAG = "LegacyUserSpeechProvider";
    private final AlexaDialogExtras alexaDialogExtras;
    private final AlexaAudioMetadata audioMetadata;
    private final AlexaAudioSink audioSink;
    private final AtomicReference<String> currentTurnId;
    private final AlexaDataSink dataSink;
    private final a dialogController;
    private final AtomicReference<AlexaNextDialogTurn> nextTurn;
    private final c stopCallback;
    private final Set<AlexaUserPerceivedLatencyListener> uplListeners;
    private final AlexaUserSpeechProviderMetadata userSpeechProviderMetadata;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class a {
        private AlexaDialogControllerProxy a;
        private AlexaDialogControllerProxyV2 b;

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(AlexaDialogControllerProxy alexaDialogControllerProxy) {
            this.a = alexaDialogControllerProxy;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public a(AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2) {
            this.b = alexaDialogControllerProxyV2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void a() throws RemoteException {
            AlexaDialogControllerProxy alexaDialogControllerProxy = this.a;
            if (alexaDialogControllerProxy != null) {
                alexaDialogControllerProxy.stopRecording();
                return;
            }
            AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2 = this.b;
            if (alexaDialogControllerProxyV2 == null) {
                return;
            }
            alexaDialogControllerProxyV2.stopRecording();
        }

        void a(String str) throws RemoteException {
            AlexaDialogControllerProxy alexaDialogControllerProxy = this.a;
            if (alexaDialogControllerProxy != null) {
                alexaDialogControllerProxy.startRecordingNextDialogTurn(str);
                return;
            }
            AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2 = this.b;
            if (alexaDialogControllerProxyV2 == null) {
                return;
            }
            alexaDialogControllerProxyV2.startRecordingNextDialogTurn(str);
        }

        void b() throws RemoteException {
            AlexaDialogControllerProxy alexaDialogControllerProxy = this.a;
            if (alexaDialogControllerProxy != null) {
                alexaDialogControllerProxy.onDialogTurnFinished();
                return;
            }
            AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2 = this.b;
            if (alexaDialogControllerProxyV2 == null) {
                return;
            }
            alexaDialogControllerProxyV2.onDialogTurnFinished();
        }

        void b(String str) throws RemoteException {
            AlexaDialogControllerProxy alexaDialogControllerProxy = this.a;
            if (alexaDialogControllerProxy != null) {
                alexaDialogControllerProxy.onDialogTurnStarted();
                return;
            }
            AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2 = this.b;
            if (alexaDialogControllerProxyV2 == null) {
                return;
            }
            alexaDialogControllerProxyV2.onDialogTurnStarted(str);
        }

        void c() throws RemoteException {
            AlexaDialogControllerProxy alexaDialogControllerProxy = this.a;
            if (alexaDialogControllerProxy != null) {
                alexaDialogControllerProxy.onDialogStarted();
                return;
            }
            AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2 = this.b;
            if (alexaDialogControllerProxyV2 == null) {
                return;
            }
            alexaDialogControllerProxyV2.onDialogStarted();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void d() throws RemoteException {
            AlexaDialogControllerProxy alexaDialogControllerProxy = this.a;
            if (alexaDialogControllerProxy != null) {
                alexaDialogControllerProxy.onDialogFinished();
                return;
            }
            AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2 = this.b;
            if (alexaDialogControllerProxyV2 == null) {
                return;
            }
            alexaDialogControllerProxyV2.onDialogFinished();
        }

        String e() throws RemoteException {
            AlexaDialogControllerProxy alexaDialogControllerProxy = this.a;
            return alexaDialogControllerProxy != null ? alexaDialogControllerProxy.getDialogIdentifier() : this.b.getDialogIdentifier();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || a.class != obj.getClass()) {
                return false;
            }
            a aVar = (a) obj;
            return Objects.equals(this.a, aVar.a) && Objects.equals(this.b, aVar.b);
        }

        public int hashCode() {
            return Objects.hash(this.a, this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class b implements AlexaDialogTurnMetricsCallback {
        private final String b;

        private b(String str) {
            this.b = str;
        }

        @Override // com.amazon.alexa.api.AlexaDialogTurnMetricsCallback
        public void onUserPerceivedLatencyData(UserPerceivedLatencyData userPerceivedLatencyData) {
            for (AlexaUserPerceivedLatencyListener alexaUserPerceivedLatencyListener : LegacyUserSpeechProvider.this.uplListeners) {
                alexaUserPerceivedLatencyListener.onLatencyData(this.b, userPerceivedLatencyData);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public class c implements AlexaDialogTurnStopCallback {
        private c() {
        }

        @Override // com.amazon.alexa.api.AlexaDialogTurnStopCallback
        public void stopRecording() {
            try {
                LegacyUserSpeechProvider.this.dialogController.a();
            } catch (RemoteException e) {
                Log.e(LegacyUserSpeechProvider.TAG, "failed to call stopRecording callback", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LegacyUserSpeechProvider(AlexaDialogControllerProxy alexaDialogControllerProxy, Set<AlexaUserPerceivedLatencyListener> set, AlexaAudioMetadata alexaAudioMetadata, AlexaAudioSink alexaAudioSink, @Nullable AlexaDataSink alexaDataSink, AlexaDialogExtras alexaDialogExtras) {
        this(new a(alexaDialogControllerProxy), set, alexaAudioMetadata, alexaAudioSink, alexaDataSink, alexaDialogExtras);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LegacyUserSpeechProvider(AlexaDialogControllerProxyV2 alexaDialogControllerProxyV2, Set<AlexaUserPerceivedLatencyListener> set, AlexaAudioMetadata alexaAudioMetadata, AlexaAudioSink alexaAudioSink, @Nullable AlexaDataSink alexaDataSink, AlexaDialogExtras alexaDialogExtras) {
        this(new a(alexaDialogControllerProxyV2), set, alexaAudioMetadata, alexaAudioSink, alexaDataSink, alexaDialogExtras);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LegacyUserSpeechProvider(a aVar, Set<AlexaUserPerceivedLatencyListener> set, AlexaAudioMetadata alexaAudioMetadata, AlexaAudioSink alexaAudioSink, @Nullable AlexaDataSink alexaDataSink, AlexaDialogExtras alexaDialogExtras) {
        this.stopCallback = new c();
        this.currentTurnId = new AtomicReference<>();
        this.nextTurn = new AtomicReference<>();
        this.dialogController = aVar;
        this.uplListeners = set;
        this.audioMetadata = alexaAudioMetadata;
        this.audioSink = alexaAudioSink;
        this.dataSink = alexaDataSink;
        this.alexaDialogExtras = alexaDialogExtras;
        this.userSpeechProviderMetadata = createMetadata();
    }

    private void closeSink(AlexaAudioSink alexaAudioSink) {
        alexaAudioSink.abandon();
        AlexaDataSink alexaDataSink = this.dataSink;
        if (alexaDataSink != null) {
            alexaDataSink.abandon();
        }
    }

    private AlexaUserSpeechProviderMetadata createMetadata() {
        return AlexaUserSpeechProviderMetadata.createLegacy();
    }

    private void dropDialog(AlexaAudioSink alexaAudioSink) {
        closeSink(alexaAudioSink);
        dropDialog(false);
    }

    private void dropDialog(boolean z) {
        try {
            String str = TAG;
            Log.w(str, "Dropping dialog: " + this.dialogController.e());
            if (z) {
                this.dialogController.c();
            }
            this.dialogController.a();
            this.dialogController.d();
        } catch (RemoteException e) {
            Log.w(TAG, "Unable to drop the dialog", e);
        }
    }

    public void continueDialog(AlexaAudioSink alexaAudioSink) {
        AlexaNextDialogTurn andSet = this.nextTurn.getAndSet(null);
        if (andSet == null) {
            dropDialog(alexaAudioSink);
            return;
        }
        andSet.startTurn(alexaAudioSink, this.stopCallback, new b(andSet.getDialogTurnId()));
    }

    public AlexaUserSpeechProviderMetadata getUserSpeechProviderMetadata() {
        return this.userSpeechProviderMetadata;
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogFinished(DialogData dialogData) {
        try {
            try {
                this.dialogController.d();
            } catch (RemoteException e) {
                Log.e(TAG, "failed to call onDialogFinished callback", e);
            }
        } finally {
            closeSink(this.audioSink);
        }
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogRequestDenied(DialogRequestDeniedReason dialogRequestDeniedReason) {
        dropDialog(this.audioSink);
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogRequested(AlexaDialogTurn alexaDialogTurn) {
        this.currentTurnId.set(alexaDialogTurn.getDialogTurnId());
        b bVar = new b(alexaDialogTurn.getDialogTurnId());
        AlexaDataSink alexaDataSink = this.dataSink;
        AlexaAudioMetadata alexaAudioMetadata = this.audioMetadata;
        AlexaAudioSink alexaAudioSink = this.audioSink;
        if (alexaDataSink == null) {
            alexaDialogTurn.startTurn(alexaAudioMetadata, alexaAudioSink, this.stopCallback, bVar, this.alexaDialogExtras);
        } else {
            alexaDialogTurn.startTurn(alexaAudioMetadata, alexaAudioSink, alexaDataSink, this.stopCallback, bVar, this.alexaDialogExtras);
        }
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogStarted(DialogData dialogData) {
        try {
            this.dialogController.c();
        } catch (RemoteException e) {
            Log.e(TAG, "failed to call onDialogStarted callback", e);
        }
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogTurnFinished(DialogTurnData dialogTurnData) {
        this.currentTurnId.set(null);
        try {
            this.dialogController.b();
        } catch (RemoteException e) {
            Log.e(TAG, "failed to call onDialogTurnFinished callback", e);
        }
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogTurnRequested(AlexaNextDialogTurn alexaNextDialogTurn) {
        this.currentTurnId.set(alexaNextDialogTurn.getDialogTurnId());
        this.nextTurn.set(alexaNextDialogTurn);
        try {
            this.dialogController.a(alexaNextDialogTurn.getDialogTurnId());
        } catch (RemoteException e) {
            Log.e(TAG, "failed to call onDialogTurnRequested callback", e);
        }
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void onDialogTurnStarted(DialogTurnData dialogTurnData) {
        try {
            this.dialogController.b(dialogTurnData.getDialogTurnId());
        } catch (RemoteException e) {
            Log.e(TAG, "failed to call onDialogTurnStarted callback", e);
        }
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void pauseWakeWordDetection(String str) {
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void resumeWakeWordDetection(String str) {
    }

    @Override // com.amazon.alexa.api.AlexaUserSpeechProvider
    public void setWakeWordDetectionEnabled(boolean z) {
    }

    public void stopDialog() throws RemoteException {
        this.stopCallback.stopRecording();
        if (this.currentTurnId.getAndSet(null) != null) {
            this.dialogController.b();
        }
        this.dialogController.d();
    }
}
