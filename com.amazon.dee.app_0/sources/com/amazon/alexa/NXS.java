package com.amazon.alexa;

import android.os.RemoteException;
import android.util.Log;
import com.amazon.alexa.api.AlexaUserSpeechListenerProxy;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.ApiThreadHelper;
import java.util.Iterator;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: AlexaUserSpeechAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class NXS {
    public static final String zZm = "NXS";
    public final AlexaClientEventBus zQM;
    public final CDz<AlexaUserSpeechListenerProxy> BIo = new CDz<>();
    public float zyO = 0.0f;

    @Inject
    public NXS(AlexaClientEventBus alexaClientEventBus) {
        this.zQM = alexaClientEventBus;
        alexaClientEventBus.zZm(this);
    }

    @Subscribe
    public void on(uqh uqhVar) {
        this.zyO = ((YEO) uqhVar).BIo;
        Iterator<T> it2 = this.BIo.iterator();
        while (it2.hasNext()) {
            ApiThreadHelper.runOnUiThread(new XWf(this, (AlexaUserSpeechListenerProxy) it2.next()));
        }
    }

    public void zZm(ExtendedClient extendedClient, AlexaUserSpeechListenerProxy alexaUserSpeechListenerProxy) {
        this.BIo.zZm(extendedClient, (ExtendedClient) alexaUserSpeechListenerProxy);
        try {
            alexaUserSpeechListenerProxy.onAlexaUserSpeechVolumeChanged(this.zyO);
        } catch (RemoteException e) {
            Log.e(zZm, e.getMessage(), e);
            this.zQM.zyO(xZV.zZm(extendedClient));
        }
    }

    @Subscribe
    public void on(xZV xzv) {
        this.BIo.BIo(((uyC) xzv).BIo);
    }

    public void zZm(AlexaUserSpeechListenerProxy alexaUserSpeechListenerProxy) {
        ExtendedClient remove = this.BIo.remove(alexaUserSpeechListenerProxy);
        if (remove != null) {
            StringBuilder zZm2 = C0179Pya.zZm("Deregistering user speech listener for client: ");
            zZm2.append(remove.getId());
            zZm2.toString();
        }
    }
}
