package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
/* compiled from: ExternalMediaPlayerInteraction.java */
/* loaded from: classes.dex */
public class RKf extends jDH {
    public static final String BIo = "RKf";
    public final vQe zQM;
    public boolean zyO;

    public RKf(vQe vqe) {
        this.zQM = vqe;
    }

    @Override // com.amazon.alexa.ndD
    public dnp BIo() {
        return AvsApiConstants.ExternalMediaPlayer.BIo;
    }

    @Override // com.amazon.alexa.jDH
    public void Qle() {
        String str = BIo;
        StringBuilder zZm = C0179Pya.zZm("onPause for ");
        zZm.append(this.zQM.getValue());
        Log.i(str, zZm.toString());
    }

    @Override // com.amazon.alexa.jDH
    public void jiA() {
        String str = BIo;
        StringBuilder zZm = C0179Pya.zZm("onForeground for ");
        zZm.append(this.zQM.getValue());
        Log.i(str, zZm.toString());
    }

    @Override // com.amazon.alexa.ndD
    public void zQM() {
        String str = BIo;
        StringBuilder zZm = C0179Pya.zZm("onStop for");
        zZm.append(this.zQM.getValue());
        Log.i(str, zZm.toString());
        this.zyO = true;
    }

    @Override // com.amazon.alexa.jDH
    public void zyO() {
        String str = BIo;
        StringBuilder zZm = C0179Pya.zZm("onBackground for ");
        zZm.append(this.zQM.getValue());
        Log.i(str, zZm.toString());
    }
}
