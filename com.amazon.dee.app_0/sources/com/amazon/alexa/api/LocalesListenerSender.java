package com.amazon.alexa.api;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.aw;
import com.amazon.alexa.api.messages.messagesender.AlexaMessageSender;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes6.dex */
public class LocalesListenerSender extends AlexaMessageSender<ax> implements AlexaLocalesListener {
    private static final String TAG = "LocalesListenerSender";
    private final ExtendedClient extendedClient;

    /* loaded from: classes6.dex */
    private static class a extends BaseMessagePayload {
        a(ExtendedClient extendedClient, List<java.util.Locale> list) {
            super(extendedClient);
            ArrayList arrayList = new ArrayList(list.size());
            for (java.util.Locale locale : list) {
                arrayList.add(locale.toLanguageTag());
            }
            add(aw.a.LOCALES, arrayList);
        }
    }

    public LocalesListenerSender(ExtendedClient extendedClient, IBinder iBinder) {
        super(iBinder);
        this.extendedClient = extendedClient;
    }

    @Override // com.amazon.alexa.api.AlexaLocalesListener
    public void onLocales(List<java.util.Locale> list) {
        try {
            sendMessage(ax.ON_LOCALES, new a(this.extendedClient, list).getBundle());
        } catch (RemoteException e) {
            Log.e(TAG, "Failed to send message for: onLocales", e);
        }
    }
}
