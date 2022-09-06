package com.amazon.alexa;

import androidx.annotation.Nullable;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: DialogAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class jSO {
    public DialogRequestIdentifier BIo;
    public final AlexaClientEventBus zZm;

    @Inject
    public jSO(AlexaClientEventBus alexaClientEventBus) {
        this.zZm = alexaClientEventBus;
        this.zZm.zZm(this);
    }

    public synchronized boolean BIo(@Nullable DialogRequestIdentifier dialogRequestIdentifier) {
        boolean z;
        DialogRequestIdentifier dialogRequestIdentifier2 = this.BIo;
        if (dialogRequestIdentifier2 != null && dialogRequestIdentifier2 != DialogRequestIdentifier.NONE && dialogRequestIdentifier != null) {
            if (dialogRequestIdentifier2.equals(dialogRequestIdentifier)) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    @Subscribe
    public synchronized void on(kOA koa) {
        zZm();
    }

    public synchronized DialogRequestIdentifier zZm(DialogRequestIdentifier dialogRequestIdentifier) {
        this.BIo = dialogRequestIdentifier;
        this.zZm.zyO(new rtX(this.BIo));
        return this.BIo;
    }

    public synchronized void zZm() {
        this.BIo = null;
    }
}
