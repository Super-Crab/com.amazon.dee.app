package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.wakeword.precondition.InternalWakeWordPrecondition;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: AlexaStateWakeWordPrecondition.java */
@Singleton
/* loaded from: classes.dex */
public class Snr extends InternalWakeWordPrecondition {
    public static final Set<wSq> zZm = Collections.unmodifiableSet(EnumSet.of(wSq.PREPARING_TO_LISTEN, wSq.LISTENING, wSq.THINKING, wSq.REQUEST_PROCESSING, wSq.PREPARING_TO_SPEAK));
    public final AlexaClientEventBus BIo;
    public final Object zQM = new Object();
    public wSq zyO = wSq.UNKNOWN;

    @Inject
    public Snr(AlexaClientEventBus alexaClientEventBus) {
        this.BIo = alexaClientEventBus;
        alexaClientEventBus.zZm(this);
    }

    @Override // com.amazon.alexa.wakeword.precondition.WakeWordPrecondition
    public boolean isWakeWordAllowed() {
        boolean z;
        synchronized (this.zQM) {
            z = !zZm.contains(this.zyO);
        }
        return z;
    }

    @Subscribe
    public void on(HDT hdt) {
        boolean z;
        boolean z2;
        synchronized (this.zQM) {
            z = false;
            if (((RUl) hdt).BIo != this.zyO) {
                this.zyO = ((RUl) hdt).BIo;
                z = true;
                z2 = isWakeWordAllowed();
            } else {
                z2 = false;
            }
        }
        if (z) {
            notifyStateChanged(z2);
        }
    }

    @Override // com.amazon.alexa.wakeword.precondition.InternalWakeWordPrecondition
    public void teardown() {
        this.BIo.BIo(this);
    }
}
