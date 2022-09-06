package com.amazon.alexa.api;

import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import com.amazon.alexa.api.br;
import com.amazon.alexa.api.messages.MessageProcessor;
import com.amazon.alexa.client.annotations.Nullable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
/* loaded from: classes6.dex */
public class bt extends MessageProcessor<bs> {
    private final AlexaSupportedLocalesListener a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.alexa.api.bt$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[bs.values().length];

        static {
            try {
                a[bs.ON_SUPPORTED_LOCALES.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public bt(AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
        this.a = alexaSupportedLocalesListener;
    }

    public static bt a(AlexaSupportedLocalesListener alexaSupportedLocalesListener) {
        return new bt(alexaSupportedLocalesListener);
    }

    private void a(Bundle bundle) {
        HashSet hashSet = new HashSet(a(Bundles.getStringList(bundle, br.a.LOCALES)));
        List<au> parcelableList = Bundles.getParcelableList(bundle, br.a.LOCALE_COMBINATIONS, au.class);
        HashSet hashSet2 = new HashSet(parcelableList.size());
        for (au auVar : parcelableList) {
            hashSet2.add(a(auVar.a()));
        }
        this.a.onSupportedLocales(hashSet, hashSet2);
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: a */
    public bs mo845getMessageType(Message message) {
        try {
            return bs.a(message.what);
        } catch (IllegalArgumentException unused) {
            return bs.UNKNOWN;
        }
    }

    List<java.util.Locale> a(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            arrayList.add(java.util.Locale.forLanguageTag(str));
        }
        return arrayList;
    }

    @Override // com.amazon.alexa.api.messages.MessageProcessor
    /* renamed from: a */
    public void processMessage(bs bsVar, Bundle bundle, @Nullable Messenger messenger) {
        if (AnonymousClass1.a[bsVar.ordinal()] == 1) {
            a(bundle);
            return;
        }
        String simpleName = bt.class.getSimpleName();
        Log.w(simpleName, "Unsupported message: " + bsVar);
    }
}
