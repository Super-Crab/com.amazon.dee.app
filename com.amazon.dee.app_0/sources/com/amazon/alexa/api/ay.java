package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.api.aw;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes6.dex */
class ay extends MessageProcessor<ax> {
    private final AlexaLocalesListener a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.api.ay$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[ax.values().length];

        static {
            try {
                a[ax.ON_LOCALES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    ay(AlexaLocalesListener alexaLocalesListener) {
        this.a = alexaLocalesListener;
    }

    public static ay a(AlexaLocalesListener alexaLocalesListener) {
        return new ay(alexaLocalesListener);
    }

    private void a(Bundle bundle) {
        List<String> stringList = Bundles.getStringList(bundle, aw.a.LOCALES);
        ArrayList arrayList = new ArrayList(stringList.size());
        for (String str : stringList) {
            if (java.util.Locale.forLanguageTag(str) != null) {
                arrayList.add(java.util.Locale.forLanguageTag(str));
            }
        }
        this.a.onLocales(arrayList);
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: a */
    public ax mo845getMessageType(Message message) {
        try {
            return ax.a(message.what);
        } catch (IllegalArgumentException unused) {
            return ax.UNKNOWN;
        }
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: a */
    public void processMessage(ax axVar, Bundle bundle, @Nullable Messenger messenger) {
        if (AnonymousClass1.a[axVar.ordinal()] == 1) {
            a(bundle);
            return;
        }
        String simpleName = ay.class.getSimpleName();
        Log.w(simpleName, "Unsupported message: " + axVar);
    }
}
