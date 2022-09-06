package com.amazon.alexa;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.fcj;
/* compiled from: SpeechItem.java */
/* loaded from: classes.dex */
public class bqj extends kQf {
    public final DialogRequestIdentifier LPk;
    public final String yPL;

    public bqj(C0176Pce c0176Pce, Uri uri, @Nullable DialogRequestIdentifier dialogRequestIdentifier, @Nullable String str) {
        super(c0176Pce, uri, 0L, null, null, fcj.zZm.PAUSE, false);
        this.LPk = dialogRequestIdentifier;
        this.yPL = str;
    }

    public static bqj zZm(C0176Pce c0176Pce, Uri uri, @Nullable DialogRequestIdentifier dialogRequestIdentifier, @Nullable String str) {
        return new bqj(c0176Pce, uri, dialogRequestIdentifier, str);
    }

    @Override // com.amazon.alexa.kQf
    /* renamed from: BIo */
    public C0176Pce mo947BIo() {
        return (C0176Pce) this.zZm;
    }
}
