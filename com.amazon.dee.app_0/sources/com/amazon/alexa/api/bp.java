package com.amazon.alexa.api;

import android.os.IBinder;
import android.os.RemoteException;
import com.amazon.alexa.api.messages.messagesender.AlexaMessageSender;
/* loaded from: classes6.dex */
class bp extends AlexaMessageSender<bm> {
    private final ExtendedClient a;

    /* loaded from: classes6.dex */
    static class a extends BaseMessagePayload {
        a(ExtendedClient extendedClient, String str) {
            super(extendedClient);
            add(bn.FAILURE_MESSAGE, str == null ? " " : str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public bp(IBinder iBinder, ExtendedClient extendedClient) {
        super(iBinder);
        this.a = extendedClient;
    }

    public void a() throws RemoteException {
        sendMessage(bm.ON_SUCCESS, new BaseMessagePayload(this.a).getBundle());
    }

    public void a(String str) throws RemoteException {
        sendMessage(bm.ON_FAILURE, new a(this.a, str).getBundle());
    }
}
