package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: VisualFocusManager.java */
@Singleton
/* loaded from: classes.dex */
public class kDa {
    public static final String zZm = "kDa";
    public final AlexaClientEventBus BIo;
    public zZm zQM;

    /* compiled from: VisualFocusManager.java */
    /* loaded from: classes.dex */
    private class zZm extends AbstractC0239xsr {
        public dnp BIo;

        public /* synthetic */ zZm(kDa kda, String str, fKc fkc) {
            this.BIo = dnp.zZm(str);
        }

        @Override // com.amazon.alexa.ndD
        public dnp BIo() {
            return this.BIo;
        }

        @Override // com.amazon.alexa.ndD
        public void zQM() {
        }
    }

    @Inject
    public kDa(AlexaClientEventBus alexaClientEventBus) {
        this.BIo = alexaClientEventBus;
        this.BIo.zZm(this);
    }

    @Subscribe
    public void schedule(Wyk wyk) {
        String taskComponentName = ((FsB) wyk).jiA.getTaskComponentName();
        if (taskComponentName != null && !taskComponentName.isEmpty()) {
            this.zQM = new zZm(this, taskComponentName, null);
            this.BIo.zyO(new AIx(JiQ.DIALOG_UI, this.zQM));
            return;
        }
        Log.w(zZm, "Unable to schedule visual task with empty task component name");
    }

    @Subscribe
    public void unschedule(ArZ arZ) {
        String str = zZm;
        if (this.zQM != null) {
            StringBuilder zZm2 = C0179Pya.zZm("Send finished interaction event with interface name : ");
            zZm2.append(this.zQM.BIo);
            zZm2.toString();
            this.BIo.zyO(new kwP(this.zQM.zZm));
            this.zQM = null;
            return;
        }
        StringBuilder zZm3 = C0179Pya.zZm("Unable to unschedule visual task as either its already unschedule or was never schedule with task component name ");
        zZm3.append(((Ixs) arZ).jiA.getTaskComponentName());
        Log.w(str, zZm3.toString());
    }
}
