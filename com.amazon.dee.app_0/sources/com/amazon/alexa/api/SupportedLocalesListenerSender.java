package com.amazon.alexa.api;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.br;
import com.amazon.alexa.api.messages.messagesender.AlexaMessageSender;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/* loaded from: classes6.dex */
public class SupportedLocalesListenerSender extends AlexaMessageSender<bs> implements AlexaSupportedLocalesListener {
    private static final String TAG = "SupportedLocalesListenerSender";
    private final ExtendedClient extendedClient;

    /* loaded from: classes6.dex */
    private static class a extends BaseMessagePayload {
        a(ExtendedClient extendedClient, Set<java.util.Locale> set, Set<List<java.util.Locale>> set2) {
            super(extendedClient);
            ArrayList arrayList = new ArrayList(set.size());
            for (java.util.Locale locale : set) {
                arrayList.add(locale.toLanguageTag());
            }
            ArrayList arrayList2 = new ArrayList();
            for (List<java.util.Locale> list : set2) {
                arrayList2.add(new au(a(list)));
            }
            add(br.a.LOCALES, arrayList);
            addParcelableArray(br.a.LOCALE_COMBINATIONS, new ArrayList<>(arrayList2));
        }

        private static List<String> a(List<java.util.Locale> list) {
            ArrayList arrayList = new ArrayList();
            for (java.util.Locale locale : list) {
                arrayList.add(locale.toLanguageTag());
            }
            return arrayList;
        }
    }

    public SupportedLocalesListenerSender(ExtendedClient extendedClient, IBinder iBinder) {
        super(iBinder);
        this.extendedClient = extendedClient;
    }

    @Override // com.amazon.alexa.api.AlexaSupportedLocalesListener
    public void onSupportedLocales(Set<java.util.Locale> set, Set<List<java.util.Locale>> set2) {
        try {
            sendMessage(bs.ON_SUPPORTED_LOCALES, new a(this.extendedClient, set, set2).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send message for: onSupportedLocales", e);
        }
    }
}
