package com.amazon.alexa;

import android.os.IBinder;
import com.amazon.alexa.api.AlexaContextsProvider;
import com.amazon.alexa.api.AlexaContextsProviderMessageSender;
import com.amazon.alexa.api.ExtendedClient;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: AlexaContextsProviderFactory.java */
@Singleton
/* loaded from: classes.dex */
public class Mlj {
    public final KvZ zZm;

    @Inject
    public Mlj(KvZ kvZ) {
        this.zZm = kvZ;
    }

    public AlexaContextsProvider zZm(IBinder iBinder, ExtendedClient extendedClient) {
        return new AlexaContextsProviderMessageSender(iBinder, extendedClient, this.zZm.zZm(extendedClient));
    }
}
