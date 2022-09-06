package com.amazon.alexa;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: AVRCPMediaPlaybackAuthority.java */
/* renamed from: com.amazon.alexa.mUo  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0220mUo implements liS {
    public static final String zZm = "mUo";
    public final Context BIo;
    public final QJr JTe;
    public final OIb LPk;
    public final Map<xNT, MediaMetadataCompat.Builder> Mlj;
    public final C0245zoo Qle;
    public final ScheduledExecutorService jiA;
    public volatile boolean lOf;
    public xNT yPL;
    public final AlexaClientEventBus zQM;
    public final ExecutorService zyO;
    public PlaybackStateCompat.Builder zzR;

    public static /* synthetic */ void BIo(C0220mUo c0220mUo) {
    }

    @Override // com.amazon.alexa.liS
    public synchronized boolean BIo() {
        throw null;
    }

    @Override // com.amazon.alexa.liS
    public synchronized String JTe() {
        return zyO(this.yPL);
    }

    @Override // com.amazon.alexa.liS
    public synchronized Bitmap LPk() {
        return zQM(this.yPL);
    }

    @Override // com.amazon.alexa.liS
    public synchronized boolean Qle() {
        return this.lOf;
    }

    @Override // com.amazon.alexa.liS
    public synchronized String getTitle() {
        return Qle(this.yPL);
    }

    @Override // com.amazon.alexa.liS
    public synchronized boolean jiA() {
        boolean z;
        String str = zZm;
        StringBuilder zZm2 = C0179Pya.zZm("isAnotherAppPlayingContent other playing: ");
        zZm2.append(BIo());
        zZm2.append(" is it us: ");
        zZm2.append(yPL());
        Log.i(str, zZm2.toString());
        if (BIo()) {
            if (!yPL()) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    @Subscribe(priority = 100)
    public synchronized void on(CKO cko) {
        PlaybackStateCompat build;
        zZm(((IyB) cko).zQM);
        this.lOf = false;
        IyB iyB = (IyB) cko;
        switch (LCy.zZm[iyB.BIo.ordinal()]) {
            case 1:
                this.zzR.setState(3, iyB.zyO, 1.0f);
                this.zzR.setActions(562L);
                xNT xnt = iyB.zQM;
                if (xnt != null) {
                    LinkedList<xNT> linkedList = new LinkedList();
                    for (xNT xnt2 : this.Mlj.keySet()) {
                        if (!xnt.equals(xnt2)) {
                            linkedList.add(xnt2);
                        }
                    }
                    for (xNT xnt3 : linkedList) {
                        this.Mlj.remove(xnt3);
                    }
                }
                this.lOf = true;
                break;
            case 2:
                this.zzR.setState(2, iyB.zyO, 0.0f);
                this.zzR.setActions(564L);
                break;
            case 3:
                this.zzR.setState(6, iyB.zyO, 0.0f);
                this.zzR.setActions(562L);
                this.lOf = true;
                break;
            case 4:
                this.zzR.setState(0, 0L, 0.0f);
                this.zzR.setActions(564L);
                break;
            case 5:
                this.zzR.setState(1, iyB.zyO, 0.0f);
                this.zzR.setActions(564L);
                break;
            case 6:
                this.zzR.setState(7, iyB.zyO, 0.0f);
                this.zzR.setActions(564L);
                break;
        }
        if (!this.LPk.Qle() && !jiA()) {
            Log.i(zZm, "Creating mediasession for Playing Music");
            this.LPk.zZm();
            if (!this.LPk.JTe() && this.LPk.jiA()) {
                this.LPk.zQM();
            }
        }
        OIb oIb = this.LPk;
        PlaybackStateCompat.Builder builder = this.zzR;
        if (builder == null) {
            build = new PlaybackStateCompat.Builder().build();
        } else {
            build = builder.build();
            if (this.Qle.BIo()) {
                int state = build.getState();
                if (state == 1 || state == 2) {
                    this.zzR.setState(3, build.getPosition(), build.getPlaybackSpeed());
                }
                build = this.zzR.build();
            }
        }
        oIb.zZm(build);
    }

    @Override // com.amazon.alexa.liS
    public synchronized void teardown() {
        this.LPk.yPL();
        this.zyO.shutdownNow();
        this.jiA.shutdownNow();
        this.Mlj.clear();
        this.zQM.BIo(this);
    }

    @Override // com.amazon.alexa.liS
    public synchronized boolean yPL() {
        return this.JTe.zZm(aVo.CONTENT);
    }

    @Override // com.amazon.alexa.liS
    public synchronized String zQM() {
        return BIo(this.yPL);
    }

    @Override // com.amazon.alexa.liS
    public synchronized MediaSessionCompat.Token zyO() {
        return this.LPk.zyO();
    }

    public final synchronized String BIo(xNT xnt) {
        return jiA(xnt).getString(MediaMetadataCompat.METADATA_KEY_ALBUM);
    }

    public final synchronized String Qle(xNT xnt) {
        return jiA(xnt).getString(MediaMetadataCompat.METADATA_KEY_TITLE);
    }

    public final synchronized Bitmap zQM(xNT xnt) {
        return jiA(xnt).getBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART);
    }

    @Override // com.amazon.alexa.liS
    public synchronized void zZm(MediaSessionCompat.Callback callback) {
        this.LPk.zZm(callback);
    }

    public final synchronized String zyO(xNT xnt) {
        return jiA(xnt).getString(MediaMetadataCompat.METADATA_KEY_ARTIST);
    }

    @Override // com.amazon.alexa.liS
    public synchronized boolean zZm() {
        return this.JTe.LPk();
    }

    public final void zZm(@Nullable xNT xnt) {
        xNT xnt2 = this.yPL;
        if (xnt2 == null || !xnt2.equals(xnt)) {
            this.yPL = xnt;
            if (!this.Mlj.containsKey(xnt)) {
                this.Mlj.put(xnt, new MediaMetadataCompat.Builder());
            }
            this.zzR = new PlaybackStateCompat.Builder();
        }
    }

    public final synchronized MediaMetadataCompat jiA(xNT xnt) {
        if (!this.Mlj.containsKey(xnt)) {
            this.Mlj.put(xnt, new MediaMetadataCompat.Builder());
        }
        return this.Mlj.get(xnt).build();
    }

    @Subscribe(priority = 100)
    public synchronized void on(HDT hdt) {
        if (((RUl) hdt).BIo.ordinal() == 1 && !this.LPk.Qle() && !jiA()) {
            Log.i(zZm, "Creating media session for LISTENING");
            this.LPk.zZm();
            this.LPk.zZm(true);
        }
    }

    @Subscribe(priority = 100)
    public synchronized void on(pPd ppd) {
        String zyO = zyO(((MuN) ppd).BIo);
        MuN muN = (MuN) ppd;
        String BIo = BIo(muN.BIo);
        String Qle = Qle(muN.BIo);
        if (zyO == null || BIo == null || Qle == null || !zyO.equals(muN.zyO) || !BIo.equals(muN.jiA) || !Qle.equals(muN.zQM)) {
            zZm(muN.BIo);
            MediaMetadataCompat.Builder builder = this.Mlj.get(muN.BIo);
            builder.putString(MediaMetadataCompat.METADATA_KEY_ARTIST, muN.zyO).putString(MediaMetadataCompat.METADATA_KEY_ALBUM, muN.jiA).putString(MediaMetadataCompat.METADATA_KEY_TITLE, muN.zQM);
            if (muN.JTe != null && zQM(muN.BIo) == null) {
                this.zyO.submit(new Ogg(this, ppd, builder));
            }
            this.LPk.zZm(jiA(muN.BIo));
        }
    }
}
