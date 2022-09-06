package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.validation.Preconditions;
import com.amazon.alexa.wakeword.precondition.InternalWakeWordPrecondition;
import java.util.EnumSet;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: InteractionChannelWakeWordPrecondition.java */
@Singleton
/* loaded from: classes.dex */
public class jxu extends InternalWakeWordPrecondition {
    public static final String zZm = "jxu";
    public final AlexaClientEventBus BIo;
    public boolean jiA;
    public final Object zQM = new Object();
    public final Set<aVo> zyO;

    @Inject
    public jxu(AlexaClientEventBus alexaClientEventBus) {
        Preconditions.notNull(alexaClientEventBus, "eventBus is null");
        this.BIo = alexaClientEventBus;
        this.zyO = EnumSet.of(aVo.IMPORTANT, aVo.COMMUNICATIONS);
        alexaClientEventBus.zZm(this);
    }

    @Override // com.amazon.alexa.wakeword.precondition.WakeWordPrecondition
    public boolean isWakeWordAllowed() {
        boolean z;
        synchronized (this.zQM) {
            z = !this.jiA;
        }
        return z;
    }

    @Subscribe
    public void on(TSb tSb) {
        boolean z;
        boolean z2;
        synchronized (this.zQM) {
            z = false;
            if (!this.zyO.contains(((C0169GLa) tSb).BIo) || this.jiA == ((C0169GLa) tSb).zQM) {
                z2 = false;
            } else {
                this.jiA = ((C0169GLa) tSb).zQM;
                String str = zZm;
                StringBuilder sb = new StringBuilder();
                sb.append(((C0169GLa) tSb).BIo);
                sb.append(" state changed to ");
                sb.append(this.jiA ? "active" : "inactive");
                Log.i(str, sb.toString());
                z = isWakeWordAllowed();
                z2 = true;
            }
        }
        if (z2) {
            notifyStateChanged(z);
        }
    }

    @Override // com.amazon.alexa.wakeword.precondition.InternalWakeWordPrecondition
    public void teardown() {
        this.BIo.BIo(this);
    }
}
