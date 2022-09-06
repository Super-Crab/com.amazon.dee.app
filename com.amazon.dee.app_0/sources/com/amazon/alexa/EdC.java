package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.TtM;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.Message;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: SynchronizeStateChain.java */
@Singleton
/* loaded from: classes.dex */
public class EdC implements yDI {
    public static final String zZm = "EdC";
    public final yED BIo;
    public IHN Qle = IHN.zZm;
    public final AtomicBoolean jiA = new AtomicBoolean(false);
    public final WnL zQM;
    public final AlexaClientEventBus zyO;

    /* compiled from: SynchronizeStateChain.java */
    /* loaded from: classes.dex */
    private class zZm extends UcG {
        public /* synthetic */ zZm(tAP tap) {
        }

        @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
        public void onFailure(RrI rrI, @Nullable Integer num, @Nullable Exception exc) {
            EdC.this.jiA.set(false);
            EdC.Qle(EdC.this);
            WnL wnL = EdC.this.zQM;
            wnL.uzr = num;
            wnL.HvC = exc;
            EdC.this.zyO.zyO(new MKA(true, num, exc));
        }

        @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
        public void onRequestDropped(RrI rrI, TtM.zZm zzm) {
            EdC.this.jiA.set(false);
            int ordinal = zzm.ordinal();
            if (ordinal == 1) {
                WnL wnL = EdC.this.zQM;
                wnL.BIo = true;
                wnL.yPL = true;
            } else if (ordinal == 2) {
                EdC.this.zQM.zQM = true;
            }
            EdC.this.zZm();
        }

        @Override // com.amazon.alexa.UcG, com.amazon.alexa.TtM
        public void onSuccess(RrI rrI, Collection<Message> collection) {
            EdC.this.jiA.set(false);
            for (Message message : collection) {
                if (AvsApiConstants.System.Directives.SetEndpoint.zZm.equals(message.getHeader().getName())) {
                    EdC.zyO(EdC.this);
                    return;
                }
            }
            EdC.jiA(EdC.this);
        }
    }

    @Inject
    public EdC(yED yed, WnL wnL, AlexaClientEventBus alexaClientEventBus) {
        this.BIo = yed;
        this.zQM = wnL;
        this.zyO = alexaClientEventBus;
    }

    public static /* synthetic */ void Qle(EdC edC) {
        edC.zQM.jiA();
        edC.zZm();
    }

    public static /* synthetic */ void jiA(EdC edC) {
        if (edC.zQM.zyO()) {
            edC.zQM.yPL = false;
            edC.Qle.onSuccess();
        }
        edC.Qle = IHN.zZm;
    }

    public static /* synthetic */ void zyO(EdC edC) {
        edC.zQM.jiA();
    }

    @Override // com.amazon.alexa.yDI
    public void teardown() {
    }

    @Override // com.amazon.alexa.yDI
    public void zZm(IHN ihn) {
        boolean z = false;
        if (this.zQM.zQM()) {
            Log.i(zZm, "Not sending SynchronizeState event because downchannel is not connected");
        } else if (this.zQM.LPk) {
            Log.i(zZm, "Not sending SynchronizeState event because capabilities have not been updated.");
        } else if (!this.jiA.compareAndSet(false, true)) {
            Log.i(zZm, "Waiting for the response to an existing downchannel request");
        } else {
            z = true;
        }
        if (z) {
            this.Qle = ihn;
            this.zQM.jiA();
            this.BIo.zZm(new zZm(null));
            return;
        }
        ihn.zZm();
    }

    public final void zZm() {
        this.Qle.zZm(hlN.SYNCHRONIZE_STATE_FAILED);
        this.Qle = IHN.zZm;
    }
}
