package com.amazon.alexa;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.Lzl;
import java.io.IOException;
/* compiled from: TextDialogTurnData.java */
/* loaded from: classes.dex */
public class XND extends Lzl {
    public static final String BIo = "XND";
    public final String zQM;

    public XND(@NonNull String str) {
        this.zQM = str;
    }

    @Override // com.amazon.alexa.Lzl
    public boolean JTe() {
        return !TextUtils.isEmpty(this.zQM);
    }

    @Override // com.amazon.alexa.Lzl
    public void LPk() {
        if (this.zZm == Lzl.zZm.VERIFIED || !(!TextUtils.isEmpty(this.zQM))) {
            return;
        }
        this.zZm = Lzl.zZm.VERIFIED;
    }

    @Override // com.amazon.alexa.Lzl
    public String Qle() {
        return this.zQM;
    }

    @Override // com.amazon.alexa.Lzl
    public cIy jiA() {
        return null;
    }

    @Override // com.amazon.alexa.Lzl
    public void yPL() {
        Log.i(BIo, "teardown");
    }

    @Override // com.amazon.alexa.Lzl
    public void zQM() {
    }

    @Override // com.amazon.alexa.Lzl
    public Gcr zZm() {
        return null;
    }

    @Override // com.amazon.alexa.Lzl
    public void zZm(int i) throws IOException {
    }

    @Override // com.amazon.alexa.Lzl
    public void zZm(cIy ciy) {
    }

    @Override // com.amazon.alexa.Lzl
    public cIy zyO() {
        return null;
    }
}
