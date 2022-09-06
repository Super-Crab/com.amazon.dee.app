package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.AlexaAudioProviderService;
import com.amazon.alexa.client.annotations.Nullable;
import java.util.concurrent.Callable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class q extends p {
    private static final String c = p.class.getSimpleName();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.api.q$5  reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] a = new int[AlexaAudioProviderServiceMessageType.values().length];

        static {
            try {
                a[AlexaAudioProviderServiceMessageType.SET_WAKE_WORD_RECOGNITION_ENABLED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[AlexaAudioProviderServiceMessageType.SET_LOCALE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[AlexaAudioProviderServiceMessageType.SET_WAKE_WORD_CONFIDENCE_THRESHOLD.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[AlexaAudioProviderServiceMessageType.GET_WAKE_WORD_CONFIDENCE_THRESHOLD.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class a extends BaseMessagePayload {
        public a(ExtendedClient extendedClient, int i) {
            super(extendedClient);
            add(o.THRESHOLD_VALUE, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public q(AlexaAudioProviderService alexaAudioProviderService) {
        super(alexaAudioProviderService);
    }

    private bp c(Bundle bundle) {
        return new bp(Bundles.getBinder(bundle, o.RESULT_CALLBACK), Bundles.getClient(bundle));
    }

    private void d(Bundle bundle) throws RemoteException {
        final bp c2 = c(bundle);
        final int integer = Bundles.getInteger(bundle, o.THRESHOLD_VALUE);
        a(new Runnable() { // from class: com.amazon.alexa.api.q.3
            @Override // java.lang.Runnable
            public void run() {
                q.this.b.setWakeWordConfidenceThreshold(Integer.valueOf(integer), new AlexaAudioProviderService.ResultCallbacks(c2));
            }
        });
    }

    private Integer e(Bundle bundle) throws RemoteException {
        return (Integer) a(new Callable<Integer>() { // from class: com.amazon.alexa.api.q.4
            @Override // java.util.concurrent.Callable
            /* renamed from: a */
            public Integer call() throws Exception {
                return q.this.b.getWakeWordConfidenceThreshold();
            }
        });
    }

    @Override // com.amazon.alexa.api.p
    protected void a(Bundle bundle) throws RemoteException {
        if (!Versions.isPayloadSupportedByVersion(bundle, Versions.V1_1_0)) {
            super.a(bundle);
            return;
        }
        final bp c2 = c(bundle);
        final boolean z = Bundles.getBoolean(bundle, o.WAKE_WORD_ENABLED);
        a(new Runnable() { // from class: com.amazon.alexa.api.q.1
            @Override // java.lang.Runnable
            public void run() {
                q.this.b.setWakeWordRecognitionEnabled(z, new AlexaAudioProviderService.ResultCallbacks(c2));
            }
        });
    }

    @Override // com.amazon.alexa.api.p, com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: a */
    public synchronized void processMessage(AlexaAudioProviderServiceMessageType alexaAudioProviderServiceMessageType, Bundle bundle, @Nullable Messenger messenger) {
        try {
            ExtendedClient client = Bundles.getClient(bundle);
            int i = AnonymousClass5.a[alexaAudioProviderServiceMessageType.ordinal()];
            if (i == 1) {
                a(bundle);
            } else if (i == 2) {
                b(bundle);
            } else if (i == 3) {
                d(bundle);
            } else if (i != 4) {
                super.processMessage(alexaAudioProviderServiceMessageType, bundle, messenger);
            } else {
                Integer e = e(bundle);
                if (e == null) {
                    e = 0;
                }
                reply(messenger, alexaAudioProviderServiceMessageType, new a(client, e.intValue()).getBundle());
            }
        } catch (RemoteException e2) {
            Log.e(c, "Failed to handle incoming message!", e2);
        }
    }

    @Override // com.amazon.alexa.api.p
    protected void b(Bundle bundle) throws RemoteException {
        if (!Versions.isPayloadSupportedByVersion(bundle, Versions.V1_1_0)) {
            super.b(bundle);
            return;
        }
        final bp c2 = c(bundle);
        final String string = Bundles.getString(bundle, o.CURRENT_LOCALE);
        a(new Runnable() { // from class: com.amazon.alexa.api.q.2
            @Override // java.lang.Runnable
            public void run() {
                q.this.b.setLocale(java.util.Locale.forLanguageTag(string), new AlexaAudioProviderService.ResultCallbacks(c2));
            }
        });
    }
}
