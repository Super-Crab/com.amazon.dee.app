package com.amazon.alexa;

import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.Lzl;
import com.amazon.alexa.client.alexaservice.audioprovider.AlexaAudioSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/* compiled from: VoiceDialogTurnData.java */
/* renamed from: com.amazon.alexa.nzJ  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0223nzJ extends Lzl {
    public static final String BIo = "nzJ";
    public cIy JTe;
    public cIy Qle;
    public final ZZq jiA;
    public final shl zQM;
    public final AlexaAudioSource zyO;

    public C0223nzJ(shl shlVar, AlexaAudioSource alexaAudioSource, @Nullable ZZq zZq) {
        this(shlVar, Lzl.zZm.UNVERIFIED, null, null, alexaAudioSource, zZq);
    }

    @Override // com.amazon.alexa.Lzl
    public synchronized boolean JTe() {
        return false;
    }

    @Override // com.amazon.alexa.Lzl
    public synchronized void LPk() {
        Lzl.zZm zzm = this.zZm;
        if (zzm != Lzl.zZm.VERIFIED) {
            if (zzm == Lzl.zZm.UNVERIFIED) {
                this.Qle = this.zQM.zZm(this.zyO).getAttachmentIdentifier();
            }
            if (this.zZm == Lzl.zZm.VERIFYING) {
                this.Qle = this.zQM.BIo(this.zQM.zQM(this.Qle)).getAttachmentIdentifier();
            }
            ZZq zZq = this.jiA;
            if (zZq != null) {
                this.JTe = this.zQM.zZm(zZq).getAttachmentIdentifier();
            }
            this.zZm = Lzl.zZm.VERIFIED;
        }
    }

    public synchronized boolean Mlj() {
        return this.Qle != null;
    }

    @Override // com.amazon.alexa.Lzl
    public String Qle() {
        return null;
    }

    @Override // com.amazon.alexa.Lzl
    public synchronized cIy jiA() {
        return this.JTe;
    }

    public synchronized boolean lOf() {
        return this.JTe != null;
    }

    @Override // com.amazon.alexa.Lzl
    public void yPL() {
        Log.i(BIo, "teardown");
        AlexaAudioSource alexaAudioSource = this.zyO;
        if (alexaAudioSource != null) {
            alexaAudioSource.close();
        }
        if (Mlj()) {
            this.zQM.BIo(this.Qle);
            this.Qle = null;
        }
        if (zzR()) {
            this.jiA.close();
        }
        if (lOf()) {
            this.zQM.BIo(this.JTe);
            this.JTe = null;
        }
    }

    @Override // com.amazon.alexa.Lzl
    public synchronized void zQM() {
        Aml zyO;
        Aml zyO2;
        if (this.zZm != Lzl.zZm.FINISHED) {
            if (Mlj() && (zyO2 = this.zQM.zyO(this.Qle)) != null) {
                zyO2.finish();
            }
            if (lOf() && (zyO = this.zQM.zyO(this.JTe)) != null) {
                zyO.finish();
            }
            this.zZm = Lzl.zZm.FINISHED;
        }
    }

    @Override // com.amazon.alexa.Lzl
    public synchronized Gcr zZm() {
        if (this.zZm == Lzl.zZm.UNVERIFIED) {
            this.zZm = Lzl.zZm.VERIFYING;
            OutputStream outputStream = null;
            InputStream openForReading = this.zyO.isReadable() ? this.zyO.openForReading() : null;
            if (!Mlj()) {
                Aml zZm = this.zQM.zZm();
                this.Qle = zZm.getAttachmentIdentifier();
                outputStream = zZm.getOutputStream();
            }
            if (openForReading != null && outputStream != null) {
                return new Gcr(openForReading, outputStream, true);
            }
            return Gcr.zZm;
        }
        return Gcr.zZm;
    }

    @Override // com.amazon.alexa.Lzl
    public synchronized cIy zyO() {
        return this.Qle;
    }

    public synchronized boolean zzR() {
        return this.jiA != null;
    }

    public C0223nzJ(shl shlVar, cIy ciy, @Nullable cIy ciy2) {
        this(shlVar, Lzl.zZm.VERIFIED, ciy, ciy2, null, null);
    }

    public C0223nzJ(shl shlVar, Lzl.zZm zzm, @Nullable cIy ciy, @Nullable cIy ciy2, @Nullable AlexaAudioSource alexaAudioSource, @Nullable ZZq zZq) {
        this.zQM = shlVar;
        this.zZm = zzm;
        this.Qle = ciy;
        this.JTe = ciy2;
        this.zyO = alexaAudioSource;
        this.jiA = zZq;
    }

    @Override // com.amazon.alexa.Lzl
    public synchronized void zZm(int i) throws IOException {
        if (this.zZm == Lzl.zZm.VERIFYING && i > 0) {
            int i2 = i * 2;
            this.zQM.zQM(this.Qle).getInputStream().read(new byte[i2], 0, i2);
        }
        LPk();
    }

    @Override // com.amazon.alexa.Lzl
    public synchronized void zZm(cIy ciy) {
        this.JTe = ciy;
    }
}
