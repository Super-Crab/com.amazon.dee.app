package com.amazon.alexa;

import android.os.IBinder;
import com.amazon.alexa.api.AlexaUserSpeechProvider;
import com.amazon.alexa.api.AlexaUserSpeechProviderMessageSender;
import com.amazon.alexa.api.ExtendedClient;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: AlexaUserSpeechProviderFactory.java */
@Singleton
/* loaded from: classes.dex */
public class CGv {
    public final KvZ zZm;

    @Inject
    public CGv(KvZ kvZ) {
        this.zZm = kvZ;
    }

    public AlexaUserSpeechProvider zZm(IBinder iBinder, ExtendedClient extendedClient) {
        return new AlexaUserSpeechProviderMessageSender(iBinder, extendedClient, this.zZm.zZm(extendedClient));
    }
}
