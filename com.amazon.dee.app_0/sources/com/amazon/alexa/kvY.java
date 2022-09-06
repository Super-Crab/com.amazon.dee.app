package com.amazon.alexa;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.media.MediaMetadataCompat;
import android.util.Log;
import com.amazon.alexa.ciO;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
/* compiled from: DefaultMediaPlaybackAuthority.java */
/* loaded from: classes.dex */
public class kvY implements Runnable {
    public final /* synthetic */ MediaMetadataCompat.Builder BIo;
    public final /* synthetic */ ciO zQM;
    public final /* synthetic */ pPd zZm;

    public kvY(ciO cio, pPd ppd, MediaMetadataCompat.Builder builder) {
        this.zQM = cio;
        this.zZm = ppd;
        this.BIo = builder;
    }

    @Override // java.lang.Runnable
    public void run() {
        ciO.zZm zzm;
        Context context;
        xNT xnt;
        xNT xnt2;
        xNT xnt3;
        OIb oIb;
        MediaMetadataCompat jiA;
        AlexaClientEventBus alexaClientEventBus;
        try {
            zzm = this.zQM.Qle;
            context = this.zQM.BIo;
            Bitmap zZm = zzm.zZm(context, ((MuN) this.zZm).JTe);
            StringBuilder sb = new StringBuilder();
            sb.append("Retrieved bitmap for ");
            sb.append(((MuN) this.zZm).BIo);
            sb.toString();
            synchronized (this.zQM) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Current audio item: ");
                xnt = this.zQM.yPL;
                sb2.append(xnt);
                sb2.toString();
                this.BIo.putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, zZm);
                xnt2 = this.zQM.yPL;
                if (xnt2 != null) {
                    xnt3 = this.zQM.yPL;
                    if (xnt3.equals(((MuN) this.zZm).BIo)) {
                        oIb = this.zQM.LPk;
                        jiA = this.zQM.jiA(((MuN) this.zZm).BIo);
                        oIb.zZm(jiA);
                        alexaClientEventBus = this.zQM.zQM;
                        alexaClientEventBus.zyO(new Rby(((MuN) this.zZm).BIo));
                    }
                }
            }
        } catch (Exception unused) {
            Log.e(ciO.zZm, "Unable to load bitmap");
        }
    }
}
