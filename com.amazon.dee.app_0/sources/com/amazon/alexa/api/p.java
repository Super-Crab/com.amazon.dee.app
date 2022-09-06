package com.amazon.alexa.api;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.AlexaAudioProviderServiceMessageSender;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class p extends MessageProcessor<AlexaAudioProviderServiceMessageType> {
    private static final String c = "p";
    protected final ExecutorService a;
    protected final AlexaAudioProviderService b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.api.p$9  reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] a = new int[AlexaAudioProviderServiceMessageType.values().length];

        static {
            try {
                a[AlexaAudioProviderServiceMessageType.SET_WAKE_WORD_RECOGNITION_ENABLED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[AlexaAudioProviderServiceMessageType.IS_WAKE_WORD_RECOGNITION_ENABLED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[AlexaAudioProviderServiceMessageType.SET_LOCALE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[AlexaAudioProviderServiceMessageType.GET_LOCALE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[AlexaAudioProviderServiceMessageType.GET_SUPPORTED_LOCALES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[AlexaAudioProviderServiceMessageType.CONNECT_FOR_ATTENTION_SYSTEM_UPDATES.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[AlexaAudioProviderServiceMessageType.START_DIALOG.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[AlexaAudioProviderServiceMessageType.GET_CUSTOM_SETTINGS_TITLE.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[AlexaAudioProviderServiceMessageType.GET_CUSTOM_SETTINGS_INTENT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class a extends BaseMessagePayload {
        a(ExtendedClient extendedClient, PendingIntent pendingIntent) {
            super(extendedClient);
            add(o.CUSTOM_SETTINGS_PENDING_INTENT, pendingIntent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class b extends BaseMessagePayload {
        b(ExtendedClient extendedClient, boolean z) {
            super(extendedClient);
            add(o.RESULT, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class c extends BaseMessagePayload {
        c(ExtendedClient extendedClient, String str) {
            super(extendedClient);
            add(o.CUSTOM_SETTINGS_TITLE, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class d extends BaseMessagePayload {
        d(ExtendedClient extendedClient, List<String> list) {
            super(extendedClient);
            add(o.SUPPORTED_LOCALES, list);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public p(AlexaAudioProviderService alexaAudioProviderService) {
        this(alexaAudioProviderService, ExecutorFactory.newSingleThreadExecutor("audio-provider-service-v1"));
    }

    p(AlexaAudioProviderService alexaAudioProviderService, ExecutorService executorService) {
        this.b = alexaAudioProviderService;
        this.a = executorService;
    }

    private boolean a() throws RemoteException {
        return ((Boolean) a(new Callable<Boolean>() { // from class: com.amazon.alexa.api.p.2
            @Override // java.util.concurrent.Callable
            /* renamed from: a */
            public Boolean call() throws Exception {
                return Boolean.valueOf(p.this.b.isWakeWordRecognitionEnabled());
            }
        })).booleanValue();
    }

    private java.util.Locale b() throws RemoteException {
        return (java.util.Locale) a(new Callable<java.util.Locale>() { // from class: com.amazon.alexa.api.p.4
            @Override // java.util.concurrent.Callable
            /* renamed from: a */
            public java.util.Locale call() throws Exception {
                return p.this.b.getLocale();
            }
        });
    }

    private boolean c() throws RemoteException {
        return ((Boolean) a(new Callable<Boolean>() { // from class: com.amazon.alexa.api.p.5
            @Override // java.util.concurrent.Callable
            /* renamed from: a */
            public Boolean call() throws Exception {
                return Boolean.valueOf(p.this.b.connectForAttentionSystemUpdates());
            }
        })).booleanValue();
    }

    private boolean d() throws RemoteException {
        return this.b.startDialog();
    }

    private List<String> e() throws RemoteException {
        Set<java.util.Locale> set = (Set) a(new Callable<Set<java.util.Locale>>() { // from class: com.amazon.alexa.api.p.6
            @Override // java.util.concurrent.Callable
            /* renamed from: a */
            public Set<java.util.Locale> call() throws Exception {
                return p.this.b.getSupportedLocales();
            }
        });
        ArrayList arrayList = new ArrayList();
        if (set != null) {
            for (java.util.Locale locale : set) {
                arrayList.add(locale.toLanguageTag());
            }
        }
        return arrayList;
    }

    private String f() throws RemoteException {
        return (String) a(new Callable<String>() { // from class: com.amazon.alexa.api.p.7
            @Override // java.util.concurrent.Callable
            /* renamed from: a */
            public String call() throws Exception {
                return p.this.b.getCustomSettingsTitle();
            }
        });
    }

    private PendingIntent g() throws RemoteException {
        return (PendingIntent) a(new Callable<PendingIntent>() { // from class: com.amazon.alexa.api.p.8
            @Override // java.util.concurrent.Callable
            /* renamed from: a */
            public PendingIntent call() throws Exception {
                return p.this.b.getCustomSettingsIntent();
            }
        });
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: a */
    public AlexaAudioProviderServiceMessageType mo845getMessageType(Message message) {
        try {
            return AlexaAudioProviderServiceMessageType.fromOrdinal(message.what);
        } catch (IllegalArgumentException e) {
            Log.e(c, "Unrecognized message type", e);
            return AlexaAudioProviderServiceMessageType.UNKNOWN;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <V> V a(Callable<V> callable) throws RemoteException {
        Future submit = this.a.submit(callable);
        try {
            return (V) submit.get(1500L, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            Log.e(c, "Failed to complete task", e);
            submit.cancel(true);
            throw new RemoteException(e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Bundle bundle) throws RemoteException {
        final boolean z = Bundles.getBoolean(bundle, o.WAKE_WORD_ENABLED);
        a(new Runnable() { // from class: com.amazon.alexa.api.p.1
            @Override // java.lang.Runnable
            public void run() {
                p.this.b.setWakeWordRecognitionEnabled(z);
            }
        });
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: a */
    public synchronized void processMessage(AlexaAudioProviderServiceMessageType alexaAudioProviderServiceMessageType, Bundle bundle, @Nullable Messenger messenger) {
        Bundle bundle2;
        try {
            ExtendedClient client = Bundles.getClient(bundle);
            switch (AnonymousClass9.a[alexaAudioProviderServiceMessageType.ordinal()]) {
                case 1:
                    a(bundle);
                    break;
                case 2:
                    bundle2 = new AlexaAudioProviderServiceMessageSender.WakeWordEnabledMessagePayload(client, a()).getBundle();
                    reply(messenger, alexaAudioProviderServiceMessageType, bundle2);
                    break;
                case 3:
                    b(bundle);
                    break;
                case 4:
                    bundle2 = new AlexaAudioProviderServiceMessageSender.AlexaLocaleMessagePayload(client, b().toLanguageTag()).getBundle();
                    reply(messenger, alexaAudioProviderServiceMessageType, bundle2);
                    break;
                case 5:
                    bundle2 = new d(client, e()).getBundle();
                    reply(messenger, alexaAudioProviderServiceMessageType, bundle2);
                    break;
                case 6:
                    bundle2 = new b(client, c()).getBundle();
                    reply(messenger, alexaAudioProviderServiceMessageType, bundle2);
                    break;
                case 7:
                    bundle2 = new b(client, d()).getBundle();
                    reply(messenger, alexaAudioProviderServiceMessageType, bundle2);
                    break;
                case 8:
                    String f = f();
                    if (f == null) {
                        f = "";
                    }
                    bundle2 = new c(client, f).getBundle();
                    reply(messenger, alexaAudioProviderServiceMessageType, bundle2);
                    break;
                case 9:
                    PendingIntent g = g();
                    if (g != null) {
                        bundle2 = new a(client, g).getBundle();
                        reply(messenger, alexaAudioProviderServiceMessageType, bundle2);
                        break;
                    }
                    break;
                default:
                    String str = c;
                    Log.w(str, "Unsupported message " + alexaAudioProviderServiceMessageType);
                    break;
            }
        } catch (RemoteException e) {
            Log.e(c, "Failed to handle incoming message!", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(Runnable runnable) throws RemoteException {
        Future<?> submit = this.a.submit(runnable);
        try {
            submit.get(1500L, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            Log.e(c, "Failed to complete task", e);
            submit.cancel(true);
            throw new RemoteException(e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(Bundle bundle) throws RemoteException {
        final String string = Bundles.getString(bundle, o.CURRENT_LOCALE);
        a(new Runnable() { // from class: com.amazon.alexa.api.p.3
            @Override // java.lang.Runnable
            public void run() {
                p.this.b.setLocale(java.util.Locale.forLanguageTag(string));
            }
        });
    }
}
