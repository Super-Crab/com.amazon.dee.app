package com.amazon.alexa;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.concurrent.ExecutorFactory;
import com.bumptech.glide.Glide;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: DefaultMediaPlaybackAuthority.java */
/* loaded from: classes.dex */
public class ciO implements liS {
    public static final String zZm = "ciO";
    public final Context BIo;
    public final C0245zoo JTe;
    public final OIb LPk;
    public MediaSessionCompat.Callback Mlj;
    public final zZm Qle;
    public volatile boolean dMe;
    public final ScheduledExecutorService jiA;
    public PlaybackStateCompat.Builder lOf;
    public xNT yPL;
    public final AlexaClientEventBus zQM;
    public final ExecutorService zyO;
    public final Map<xNT, MediaMetadataCompat.Builder> zzR;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DefaultMediaPlaybackAuthority.java */
    @VisibleForTesting
    /* loaded from: classes.dex */
    public static class zZm {
        public Bitmap zZm(Context context, Uri uri) {
            int zZm = ciO.zZm(context, 128);
            try {
                return Glide.with(context).mo1635asBitmap().mo6758load(uri).into(zZm, zZm).get();
            } catch (Exception unused) {
                String str = ciO.zZm;
                Log.e(str, "Unable to load bitmap: " + uri);
                return null;
            }
        }
    }

    public ciO(Context context, C0245zoo c0245zoo, AlexaClientEventBus alexaClientEventBus) {
        ExecutorService newSingleThreadCachedThreadPool = ExecutorFactory.newSingleThreadCachedThreadPool("image-downloader");
        ScheduledExecutorService newSingleThreadScheduledExecutor = ExecutorFactory.newSingleThreadScheduledExecutor("clear-audio-item");
        zZm zzm = new zZm();
        OIb oIb = new OIb(context, alexaClientEventBus);
        this.BIo = context;
        this.JTe = c0245zoo;
        this.zQM = alexaClientEventBus;
        this.zyO = newSingleThreadCachedThreadPool;
        this.jiA = newSingleThreadScheduledExecutor;
        this.Qle = zzm;
        this.LPk = oIb;
        this.zzR = new LinkedHashMap();
        this.zQM.zZm(this);
    }

    @Override // com.amazon.alexa.liS
    public synchronized boolean BIo() {
        return false;
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
        return this.dMe;
    }

    @Override // com.amazon.alexa.liS
    public synchronized String getTitle() {
        return Qle(this.yPL);
    }

    @Override // com.amazon.alexa.liS
    public synchronized boolean jiA() {
        return false;
    }

    @Subscribe(priority = 100)
    public synchronized void on(CKO cko) {
        PlaybackStateCompat build;
        MediaSessionCompat.Callback callback;
        StringBuilder zZm2 = C0179Pya.zZm("Audio item state changed to: ");
        zZm2.append(((IyB) cko).BIo);
        zZm2.toString();
        IyB iyB = (IyB) cko;
        zZm(iyB.zQM);
        this.dMe = false;
        switch (ABK.zZm[iyB.BIo.ordinal()]) {
            case 1:
                this.lOf.setState(3, iyB.zyO, 1.0f);
                this.lOf.setActions(562L);
                xNT xnt = iyB.zQM;
                if (xnt != null) {
                    LinkedList<xNT> linkedList = new LinkedList();
                    for (xNT xnt2 : this.zzR.keySet()) {
                        if (!xnt.equals(xnt2)) {
                            linkedList.add(xnt2);
                        }
                    }
                    for (xNT xnt3 : linkedList) {
                        this.zzR.remove(xnt3);
                    }
                }
                this.dMe = true;
                break;
            case 2:
                this.lOf.setState(2, iyB.zyO, 0.0f);
                this.lOf.setActions(564L);
                break;
            case 3:
                this.lOf.setState(6, iyB.zyO, 0.0f);
                this.lOf.setActions(562L);
                this.dMe = true;
                break;
            case 4:
                this.lOf.setState(0, 0L, 0.0f);
                this.lOf.setActions(564L);
                break;
            case 5:
                this.lOf.setState(1, iyB.zyO, 0.0f);
                this.lOf.setActions(564L);
                break;
            case 6:
                this.lOf.setState(7, iyB.zyO, 0.0f);
                this.lOf.setActions(564L);
                break;
        }
        if (!this.LPk.Qle()) {
            this.LPk.zZm();
            if (!this.LPk.JTe() && (callback = this.Mlj) != null) {
                this.LPk.zZm(callback);
                this.LPk.zZm(3);
                this.LPk.zZm(true);
            }
        }
        OIb oIb = this.LPk;
        PlaybackStateCompat.Builder builder = this.lOf;
        if (builder == null) {
            build = new PlaybackStateCompat.Builder().build();
        } else {
            build = builder.build();
            if (this.JTe.BIo()) {
                int state = build.getState();
                if (state == 1 || state == 2) {
                    this.lOf.setState(3, build.getPosition(), build.getPlaybackSpeed());
                }
                build = this.lOf.build();
            }
        }
        oIb.zZm(build);
    }

    @Override // com.amazon.alexa.liS
    public synchronized void teardown() {
        this.Mlj = null;
        this.LPk.yPL();
        this.zyO.shutdownNow();
        this.jiA.shutdownNow();
        this.zzR.clear();
        this.zQM.BIo(this);
    }

    @Override // com.amazon.alexa.liS
    public synchronized boolean yPL() {
        return false;
    }

    @Override // com.amazon.alexa.liS
    public synchronized boolean zZm() {
        return false;
    }

    public final synchronized String BIo(xNT xnt) {
        return jiA(xnt).getString(MediaMetadataCompat.METADATA_KEY_ALBUM);
    }

    public final synchronized String Qle(xNT xnt) {
        return jiA(xnt).getString(MediaMetadataCompat.METADATA_KEY_TITLE);
    }

    public final synchronized MediaMetadataCompat jiA(xNT xnt) {
        if (!this.zzR.containsKey(xnt)) {
            this.zzR.put(xnt, new MediaMetadataCompat.Builder());
        }
        return this.zzR.get(xnt).build();
    }

    @Override // com.amazon.alexa.liS
    public synchronized String zQM() {
        return BIo(this.yPL);
    }

    @Override // com.amazon.alexa.liS
    public synchronized MediaSessionCompat.Token zyO() {
        return this.LPk.zyO();
    }

    public final synchronized Bitmap zQM(xNT xnt) {
        return jiA(xnt).getBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART);
    }

    @Override // com.amazon.alexa.liS
    public synchronized void zZm(MediaSessionCompat.Callback callback) {
        this.Mlj = callback;
        this.LPk.zZm(callback);
    }

    public final synchronized String zyO(xNT xnt) {
        return jiA(xnt).getString(MediaMetadataCompat.METADATA_KEY_ARTIST);
    }

    public final void zZm(@Nullable xNT xnt) {
        xNT xnt2 = this.yPL;
        if (xnt2 == null || !xnt2.equals(xnt)) {
            this.yPL = xnt;
            if (!this.zzR.containsKey(xnt)) {
                this.zzR.put(xnt, new MediaMetadataCompat.Builder());
            }
            this.lOf = new PlaybackStateCompat.Builder();
        }
    }

    public static /* synthetic */ int zZm(Context context, int i) {
        return (int) Math.ceil(i * context.getResources().getDisplayMetrics().density);
    }

    @Subscribe(priority = 100)
    public synchronized void on(pPd ppd) {
        String zyO = zyO(((MuN) ppd).BIo);
        MuN muN = (MuN) ppd;
        String BIo = BIo(muN.BIo);
        String Qle = Qle(muN.BIo);
        if (zyO == null || BIo == null || Qle == null || !zyO.equals(muN.zyO) || !BIo.equals(muN.jiA) || !Qle.equals(muN.zQM)) {
            zZm(muN.BIo);
            MediaMetadataCompat.Builder builder = this.zzR.get(muN.BIo);
            builder.putString(MediaMetadataCompat.METADATA_KEY_ARTIST, muN.zyO).putString(MediaMetadataCompat.METADATA_KEY_ALBUM, muN.jiA).putString(MediaMetadataCompat.METADATA_KEY_TITLE, muN.zQM);
            if (muN.JTe != null && zQM(muN.BIo) == null) {
                this.zyO.submit(new kvY(this, ppd, builder));
            }
            this.LPk.zZm(jiA(muN.BIo));
        }
    }
}
