package com.amazon.alexa;

import android.content.Context;
import android.net.Uri;
import android.os.RemoteException;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.AbstractC0197ddD;
import com.amazon.alexa.Kyp;
import com.amazon.alexa.PYA;
import com.amazon.alexa.WlR;
import com.amazon.alexa.cJg;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.Feature;
import com.amazon.alexa.mRm;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashSet;
/* compiled from: MediaBrowserClient.java */
/* loaded from: classes.dex */
public class bYx extends MediaBrowserCompat.ConnectionCallback {
    public static final String zZm = "bYx";
    public final Context BIo;
    public final gSO JTe;
    public final vkx LPk;
    public volatile boolean Mlj = false;
    public final aim Qle;
    public YEL dMe;
    public final yjR jiA;
    public MediaControllerCompat lOf;
    public Xpk uzr;
    public final AlexaClientEventBus yPL;
    public final vQe zQM;
    public final yfS zyO;
    public MediaBrowserCompat zzR;

    public bYx(Context context, yfS yfs, yjR yjr, aim aimVar, gSO gso, vkx vkxVar, AlexaClientEventBus alexaClientEventBus) {
        this.BIo = context;
        this.zQM = ((bve) yfs).BIo;
        this.zyO = yfs;
        this.jiA = yjr;
        this.Qle = aimVar;
        this.JTe = gso;
        this.LPk = vkxVar;
        this.yPL = alexaClientEventBus;
        vQe vqe = this.zQM;
        aimVar.zZm(vqe, Kyp.zZm(vqe).zZm());
    }

    public synchronized void BIo() {
        MediaBrowserCompat mediaBrowserCompat = this.zzR;
        if (mediaBrowserCompat != null) {
            mediaBrowserCompat.disconnect();
            this.zzR = null;
        }
        zQM();
    }

    public synchronized boolean JTe() {
        boolean z;
        if (this.Mlj && LPk()) {
            if (this.lOf.getPlaybackState().getState() != 0) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    public final boolean LPk() {
        return this.lOf.getPlaybackState() != null;
    }

    public synchronized int Qle() {
        if (!this.Mlj || !LPk()) {
            return 7;
        }
        return this.lOf.getPlaybackState().getState();
    }

    public synchronized Kyp jiA() {
        Kyp zyO;
        AKJ akj;
        XSR xsr;
        if (this.Mlj && LPk()) {
            Kyp.zZm zZm2 = Kyp.zZm(this.zQM);
            PlaybackStateCompat playbackState = zyO().getPlaybackState();
            long actions = playbackState.getActions();
            HashSet hashSet = new HashSet();
            if ((4 & actions) != 0) {
                hashSet.add(rjL.Play);
            }
            if ((2 & actions) != 0) {
                hashSet.add(rjL.Pause);
            }
            if ((1 & actions) != 0) {
                hashSet.add(rjL.Stop);
            }
            if ((16 & actions) != 0) {
                hashSet.add(rjL.Previous);
            }
            if ((32 & actions) != 0) {
                hashSet.add(rjL.Next);
            }
            if ((8 & actions) != 0) {
                hashSet.add(rjL.Rewind);
            }
            if ((64 & actions) != 0) {
                hashSet.add(rjL.FastForward);
            }
            if ((256 & actions) != 0) {
                hashSet.add(rjL.AdjustSeekPosition);
                hashSet.add(rjL.SetSeekPosition);
                hashSet.add(rjL.StartOver);
            }
            if ((128 & actions) != 0) {
                hashSet.add(rjL.Favorite);
                hashSet.add(rjL.Unfavorite);
            }
            if ((PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED & actions) != 0 || (PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE & actions) != 0) {
                hashSet.add(rjL.EnableShuffle);
                hashSet.add(rjL.DisableShuffle);
            }
            if ((actions & PlaybackStateCompat.ACTION_SET_REPEAT_MODE) != 0) {
                hashSet.add(rjL.EnableRepeat);
                hashSet.add(rjL.DisableRepeat);
                hashSet.add(rjL.EnableRepeatOne);
            }
            MediaMetadataCompat metadata = zyO().getMetadata();
            AbstractC0197ddD.zZm zZm3 = AbstractC0197ddD.zZm();
            if (metadata != null) {
                String value = ((bve) this.zyO).jiA.getValue();
                WlR.zZm zZm4 = WlR.zZm();
                String string = metadata.getString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI);
                Uri uri = Uri.EMPTY;
                if (string != null) {
                    uri = Uri.parse(string);
                }
                cJg.zZm zzm = new cJg.zZm();
                if (uri != null) {
                    zzm.jiA = uri;
                    zpo zZm5 = zzm.zZm(uri).BIo(uri).zQM(uri).zyO(uri).zZm();
                    Wea wea = Wea.TRACK;
                    if (metadata.getString(MediaMetadataCompat.METADATA_KEY_ADVERTISEMENT) != null && metadata.getLong(MediaMetadataCompat.METADATA_KEY_ADVERTISEMENT) != 0) {
                        wea = Wea.AD;
                    }
                    zZm4.JTe(WlR.zZm(MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE, metadata));
                    zZm4.LPk(WlR.zZm(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI, metadata));
                    zZm4.Mlj(WlR.zZm(MediaMetadataCompat.METADATA_KEY_TITLE, metadata));
                    zZm4.zzR(WlR.zZm(MediaMetadataCompat.METADATA_KEY_TRACK_NUMBER, metadata));
                    zZm4.zQM(WlR.zZm(MediaMetadataCompat.METADATA_KEY_ARTIST, metadata));
                    zZm4.zZm(WlR.zZm(MediaMetadataCompat.METADATA_KEY_ALBUM, metadata));
                    zZm4.Qle(WlR.zZm(MediaMetadataCompat.METADATA_KEY_AUTHOR, metadata));
                    zZm4.yPL(WlR.zZm(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, metadata));
                    zZm4.zZm(wea);
                    zZm4.zZm(metadata.getLong(MediaMetadataCompat.METADATA_KEY_DURATION));
                    zZm4.zZm(zZm5);
                    if (value.equals(PCCConstants.CAPABILITY_INTERFACE_VERSION)) {
                        String zZm6 = WlR.zZm("com.amazon.alexa.externalmediaplayer.metadata.TRACK_ID", metadata);
                        if (zZm6 != null && !zZm6.trim().isEmpty()) {
                            zZm4.yPL(zZm6);
                        }
                        zZm4.LPk(WlR.zZm("com.amazon.alexa.externalmediaplayer.metadata.PLAYBACK_SOURCE_ID", metadata));
                        zZm4.JTe(WlR.zZm("com.amazon.alexa.externalmediaplayer.metadata.PLAYBACK_SOURCE", metadata));
                    }
                    ((mRm.zZm) zZm3).BIo = zZm4.zZm();
                } else {
                    throw new NullPointerException("Null full");
                }
            }
            AbstractC0197ddD zZm7 = zZm3.zZm();
            PYA.zZm zzm2 = (PYA.zZm) zZm2;
            zzm2.BIo = NdN.zZm(playbackState);
            zzm2.zQM = hashSet;
            zzm2.zyO = zZm7;
            Kyp.zZm zZm8 = zzm2.zZm(playbackState.getPosition());
            int shuffleMode = zyO().getShuffleMode();
            if (shuffleMode != 1 && shuffleMode != 2) {
                akj = AKJ.NOT_SHUFFLED;
            } else {
                akj = AKJ.SHUFFLED;
            }
            PYA.zZm zzm3 = (PYA.zZm) zZm8;
            zzm3.Qle = akj;
            int repeatMode = zyO().getRepeatMode();
            if (repeatMode == 1) {
                xsr = XSR.ONE_REPEATED;
            } else if (repeatMode != 2 && repeatMode != 3) {
                xsr = XSR.NOT_REPEATED;
            } else {
                xsr = XSR.REPEATED;
            }
            zzm3.JTe = xsr;
            MediaMetadataCompat metadata2 = zyO().getMetadata();
            if (metadata2 != null) {
                zzm2.LPk = MAh.zZm(metadata2.getRating(MediaMetadataCompat.METADATA_KEY_RATING));
            }
            zyO = zZm2.zZm();
            this.Qle.zQM(this.zQM, zyO);
        } else {
            zyO = this.Qle.zyO(this.zQM);
            if (zyO != null) {
                PYA pya = (PYA) zyO;
                PYA.zZm zzm4 = (PYA.zZm) Kyp.zZm(this.zQM).zZm(pya.jiA);
                zzm4.JTe = pya.JTe;
                zzm4.zQM = pya.zQM;
                zzm4.LPk = pya.LPk;
                zzm4.zyO = pya.zyO;
                zzm4.Qle = pya.Qle;
                zzm4.BIo = NdN.PAUSED;
                zyO = zzm4.zZm();
            }
        }
        return zyO;
    }

    @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
    public synchronized void onConnected() {
        StringBuilder zZm2 = C0179Pya.zZm("Connected to the MediaBrowserService for registration: [");
        zZm2.append(this.zyO);
        zZm2.append("]");
        zZm2.toString();
        MediaBrowserCompat mediaBrowserCompat = this.zzR;
        if (mediaBrowserCompat != null) {
            try {
                this.lOf = zZm(mediaBrowserCompat.getSessionToken());
                this.Mlj = true;
                this.dMe = this.jiA.zZm(((bve) this.zyO).BIo, this.lOf, this.JTe, this.LPk);
                this.lOf.registerCallback(this.dMe);
                this.dMe.onExtrasChanged(this.lOf.getExtras());
                if (this.uzr != null) {
                    this.uzr.onConnected();
                }
            } catch (RemoteException e) {
                Log.e(zZm, "Error fetching token from MediaBrowserService", e);
                BIo();
                Xpk xpk = this.uzr;
                if (xpk != null) {
                    xpk.zZm("Error fetching token from connected media browser service.");
                }
            }
        } else {
            Log.e(zZm, "Error due to MediaBrowser being null");
            BIo();
            Xpk xpk2 = this.uzr;
            if (xpk2 != null) {
                xpk2.zZm("Connected media browser service is null.");
            }
        }
    }

    @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
    public synchronized void onConnectionFailed() {
        Log.w(zZm, "Connection failed to MediaBrowserService");
        zQM();
        Xpk xpk = this.uzr;
        if (xpk != null) {
            xpk.zZm("Connection to the media browser service failed.");
        }
    }

    @Override // android.support.v4.media.MediaBrowserCompat.ConnectionCallback
    public synchronized void onConnectionSuspended() {
        YEL yel = this.dMe;
        if (yel != null) {
            yel.zQM();
        }
        this.Mlj = false;
        Xpk xpk = this.uzr;
        if (xpk != null) {
            xpk.onConnectionSuspended();
        }
    }

    public synchronized void yPL() {
        aim aimVar = this.Qle;
        vQe vqe = this.zQM;
        aimVar.BIo(vqe, Kyp.zZm(vqe).zZm());
    }

    public final void zQM() {
        YEL yel;
        if (this.JTe.zZm(Feature.ALEXA_VOX_ANDROID_EMP_DISCONNECTED_EVENT)) {
            this.yPL.zyO(ddC.zZm(this.zQM, vat.DISCONNECTED));
            Log.i(zZm, "Feature ALEXA_VOX_ANDROID_EMP_DISCONNECTED_EVENT is enabled. ");
        }
        MediaControllerCompat mediaControllerCompat = this.lOf;
        if (mediaControllerCompat != null && (yel = this.dMe) != null) {
            mediaControllerCompat.unregisterCallback(yel);
        }
        this.zzR = null;
        this.lOf = null;
        this.dMe = null;
        this.Mlj = false;
    }

    public synchronized void zZm() {
        if (!this.Mlj) {
            if (this.zzR == null) {
                this.zzR = new MediaBrowserCompat(this.BIo, ((bve) this.zyO).zyO, this, null);
            }
            this.zzR.connect();
        }
    }

    public synchronized MediaControllerCompat zyO() {
        MediaControllerCompat mediaControllerCompat;
        mediaControllerCompat = this.lOf;
        if (mediaControllerCompat == null) {
            throw new IllegalStateException("MediaController is null.");
        }
        return mediaControllerCompat;
    }

    @VisibleForTesting
    public MediaControllerCompat zZm(MediaSessionCompat.Token token) throws RemoteException {
        StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Creating MediaController with session token [", token.getToken() != null ? token.getToken().toString() : null, "] and context [");
        outline115.append(this.BIo);
        outline115.append("]");
        outline115.toString();
        return new MediaControllerCompat(this.BIo, token);
    }
}
