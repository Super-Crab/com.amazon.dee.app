package com.amazon.alexa;

import com.amazon.alexa.bluetooth.sco.BluetoothScoController;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: SpecialCaseOverrideAuthority.java */
@Singleton
/* loaded from: classes.dex */
public class vVi {
    public static final String zZm = "vVi";
    public final BluetoothScoController BIo;
    public final Wyh zQM;

    @Inject
    public vVi(BluetoothScoController bluetoothScoController, Wyh wyh) {
        this.BIo = bluetoothScoController;
        this.zQM = wyh;
    }

    public void zZm(Set<tYL> set) {
        for (tYL tyl : set) {
            if (zZm(tyl.JTe)) {
                this.BIo.startSco();
                return;
            }
        }
        this.BIo.stopSco();
    }

    public boolean zZm(DialogRequestIdentifier dialogRequestIdentifier) {
        NEe zZm2 = this.zQM.zZm(dialogRequestIdentifier);
        return zZm2 != null && zZm2.jiA().getAudioOutputContext().shouldPlayOverSco();
    }
}
