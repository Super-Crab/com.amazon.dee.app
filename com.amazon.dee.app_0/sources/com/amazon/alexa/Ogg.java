package com.amazon.alexa;

import android.content.Context;
import android.net.Uri;
import android.support.v4.media.MediaMetadataCompat;
import android.util.Log;
/* compiled from: AVRCPMediaPlaybackAuthority.java */
/* loaded from: classes.dex */
public class Ogg implements Runnable {
    public final /* synthetic */ MediaMetadataCompat.Builder BIo;
    public final /* synthetic */ C0220mUo zQM;
    public final /* synthetic */ pPd zZm;

    public Ogg(C0220mUo c0220mUo, pPd ppd, MediaMetadataCompat.Builder builder) {
        this.zQM = c0220mUo;
        this.zZm = ppd;
        this.BIo = builder;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context unused;
        try {
            C0220mUo.BIo(this.zQM);
            unused = this.zQM.BIo;
            Uri uri = ((MuN) this.zZm).JTe;
            throw null;
        } catch (Exception unused2) {
            Log.e(C0220mUo.zZm, "Unable to load bitmap");
        }
    }
}
