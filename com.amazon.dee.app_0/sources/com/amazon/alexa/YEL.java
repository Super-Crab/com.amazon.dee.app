package com.amazon.alexa;

import android.media.session.PlaybackState;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.HkJ;
import com.amazon.alexa.PJz;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.core.configuration.Feature;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import java.util.HashSet;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
/* compiled from: MediaChangeListener.java */
/* loaded from: classes.dex */
public class YEL extends MediaControllerCompat.Callback {
    public static final String zZm = "YEL";
    public final AlexaClientEventBus BIo;
    public final gSO JTe;
    public WHc LPk;
    public MediaMetadataCompat Mlj;
    public final ScheduledExecutorService Qle;
    public volatile ScheduledFuture<?> dMe;
    public final qKe jiA;
    public MediaControllerCompat lOf;
    public PlaybackStateCompat yPL = PlaybackStateCompat.fromPlaybackState(new PlaybackState.Builder().setState(0, -1, 0.0f).build());
    public final vQe zQM;
    public final OWw zyO;
    public RKf zzR;

    public YEL(AlexaClientEventBus alexaClientEventBus, vQe vqe, OWw oWw, ScheduledExecutorService scheduledExecutorService, qKe qke, MediaControllerCompat mediaControllerCompat, gSO gso, vkx vkxVar) {
        this.lOf = mediaControllerCompat;
        this.BIo = alexaClientEventBus;
        this.zQM = vqe;
        this.zyO = oWw;
        this.jiA = qke;
        this.Qle = scheduledExecutorService;
        this.JTe = gso;
        this.LPk = new WHc(vkxVar);
        StringBuilder zZm2 = C0179Pya.zZm("MediaChangeListener initialized for player: ");
        zZm2.append(this.zQM.getValue());
        zZm2.toString();
    }

    public final void BIo() {
        this.zzR = new RKf(this.zQM);
        this.BIo.zyO(mZe.zZm(aVo.CONTENT, this.zzR, PJz.zZm(PJz.zQM.EXTERNAL_STREAM, PJz.BIo.NO_AUDIOFOCUS), DialogRequestIdentifier.NONE));
    }

    @Override // android.support.v4.media.session.MediaControllerCompat.Callback, android.os.IBinder.DeathRecipient
    public void binderDied() {
        StringBuilder zZm2 = C0179Pya.zZm("Binder dead for Player: ");
        zZm2.append(this.zQM.getValue());
        zZm2.toString();
        this.BIo.zyO(new DNr(this.zQM));
        zQM();
    }

    @Override // android.support.v4.media.session.MediaControllerCompat.Callback
    public void onExtrasChanged(Bundle bundle) {
        super.onExtrasChanged(bundle);
        vQe vqe = this.zQM;
        C0179Pya.zZm("Update spi version and player cookie ", (Object) vqe);
        if (bundle == null) {
            return;
        }
        HkJ zyO = this.zyO.zyO(vqe);
        if (zyO == null) {
            String str = zZm;
            Log.e(str, "oldPlayer for playerId " + vqe + " shouldn't be null at this point.");
            return;
        }
        HkJ.zZm zZm2 = HkJ.zZm(zyO);
        String string = bundle.getString("com.amazon.alexa.externalmediaplayer.spiVersion", "");
        String string2 = bundle.getString("com.amazon.alexa.externalmediaplayer.playerCookie", "");
        String string3 = bundle.getString("com.amazon.alexa.externalmediaplayer.playerVersion", "");
        if (!string.isEmpty()) {
            zZm2.zZm(AbstractC0188bKf.zZm(string));
        }
        if (!string2.isEmpty()) {
            zZm2.zZm(zYH.zZm(string2));
        }
        if (!string3.isEmpty()) {
            zZm2.zZm(ZYY.zZm(string3));
        }
        HkJ zZm3 = zZm2.zZm();
        if (zZm3.equals(zyO)) {
            return;
        }
        this.zyO.BIo(vqe, zZm3);
    }

    @Override // android.support.v4.media.session.MediaControllerCompat.Callback
    public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
        StringBuilder zZm2 = C0179Pya.zZm("onMetadataChanged for Player ");
        zZm2.append(this.zQM);
        zZm2.toString();
        super.onMetadataChanged(mediaMetadataCompat);
        if (mediaMetadataCompat.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID) != null) {
            this.BIo.zyO(ddC.zZm(this.zQM, vat.TRACK_CHANGED));
            this.BIo.zyO(Qrm.zZm(this.zQM, null, mediaMetadataCompat));
        }
    }

    @Override // android.support.v4.media.session.MediaControllerCompat.Callback
    public void onPlaybackStateChanged(PlaybackStateCompat playbackStateCompat) {
        GWl zZm2;
        GWl gWl;
        pfe pfeVar;
        Object[] objArr = new Object[2];
        objArr[0] = this.zQM.getValue();
        objArr[1] = playbackStateCompat != null ? Integer.valueOf(playbackStateCompat.getState()) : "[null]";
        String.format("MediaChangeListener for player '%s' received a state change to state %s", objArr);
        if (playbackStateCompat != null) {
            if (playbackStateCompat.getState() == 3) {
                zZm();
                RKf rKf = this.zzR;
                if (rKf == null) {
                    BIo();
                } else if (rKf.zyO) {
                    zyO();
                    BIo();
                }
                this.BIo.zyO(new cqx(this.zQM));
            }
            if (playbackStateCompat.getState() == 2) {
                this.BIo.zyO(new yag(this.zQM));
                zQM();
            }
            PlaybackStateCompat playbackStateCompat2 = this.yPL;
            HkJ zyO = this.zyO.zyO(this.zQM);
            if (zyO == null) {
                String str = zZm;
                StringBuilder zZm3 = C0179Pya.zZm("currentPlayerState for playerId ");
                zZm3.append(this.zQM);
                zZm3.append(" shouldn't be null at this point.");
                Log.e(str, zZm3.toString());
            } else {
                Roh roh = (Roh) zyO;
                GWl gWl2 = roh.lOf;
                Bundle extras = playbackStateCompat.getExtras();
                if (extras == null) {
                    zZm2 = GWl.zZm;
                } else {
                    String string = extras.getString(fWU.PLAYBACK_SESSION_ID.zZm());
                    if (TextUtils.isEmpty(string)) {
                        zZm2 = GWl.zZm;
                    } else {
                        zZm2 = GWl.zZm(string);
                    }
                }
                String.format("Determining the playbackSessionId for a state change from '%s'. Existing: %s, Received as extra: %s", this.zQM.getValue(), gWl2, zZm2.getValue());
                dEA zyO2 = this.jiA.zyO(roh.zZm);
                if (zyO2 == null) {
                    gWl = GWl.zZm;
                } else {
                    gWl = ((UGX) zyO2).BIo;
                }
                String.format("Inactive playbackSessionId for player '%s': %s", this.zQM.getValue(), gWl.getValue());
                if (!GWl.zZm.equals(gWl) && (GWl.zZm.equals(zZm2) || zZm2.equals(gWl))) {
                    String.format("The playbackSessionId received from '%s' matches up with the inactive playbackSessionId", this.zQM.getValue());
                    zZm2 = gWl;
                } else if (!GWl.zZm.equals(gWl2) && (GWl.zZm.equals(zZm2) || zZm2.equals(gWl2))) {
                    String.format("The playbackSessionId received from '%s' matches up with the existing playbackSessionId", this.zQM.getValue());
                    zZm2 = gWl2;
                } else if (GWl.zZm.equals(zZm2)) {
                    String uuid = UUID.randomUUID().toString();
                    zZm2 = GWl.zZm(uuid);
                    String.format("Generated a new playbackSessionId for player '%s': %s", this.zQM.getValue(), uuid);
                } else {
                    String.format("The the playbackSessionId received from player '%s' is unique to existing IDs", this.zQM.getValue());
                }
                GWl gWl3 = roh.lOf;
                if (!GWl.zZm.equals(gWl3)) {
                    if ((!(playbackStateCompat2 == null || playbackStateCompat2.getState() == 0) && (playbackStateCompat.getState() == 0)) || zZm2 != gWl3) {
                        String.format("Ending session for player '%s' with playbackSessionId %s", this.zQM.getValue(), gWl3.getValue());
                        this.BIo.zyO(ddC.zZm(this.zQM, vat.PLAYBACK_SESSION_ENDED, gWl3));
                        this.zyO.BIo(this.zQM, HkJ.zZm(zyO).zZm(GWl.zZm).zZm());
                    }
                }
                GWl gWl4 = roh.lOf;
                if (((playbackStateCompat2 == null || playbackStateCompat2.getState() == 0) && !(playbackStateCompat.getState() == 0)) || zZm2 != gWl4) {
                    String.format("Starting session for player '%s' with playbackSessionId %s", this.zQM.getValue(), zZm2.getValue());
                    this.BIo.zyO(ddC.zZm(this.zQM, vat.PLAYBACK_SESSION_STARTED, zZm2));
                    this.zyO.BIo(this.zQM, HkJ.zZm(zyO).zZm(zZm2).zZm());
                    dEA zyO3 = this.jiA.zyO(roh.zZm);
                    if (zyO3 != null && zZm2.equals(((UGX) zyO3).BIo)) {
                        this.jiA.BIo((qKe) this.zQM);
                    }
                }
            }
            if (playbackStateCompat.getState() == 7) {
                int errorCode = playbackStateCompat.getErrorCode();
                pfeVar = DGE.dMe.containsKey(Integer.valueOf(errorCode)) ? DGE.dMe.mo7740get(Integer.valueOf(errorCode)) : DGE.lOf;
            } else {
                pfeVar = null;
            }
            if (pfeVar != null) {
                this.BIo.zyO(FXk.zZm(this.zQM, pfeVar));
            }
            if (this.JTe.zZm(Feature.ALEXA_VOX_ANDROID_MEDIA_CHANGE_LISTENER_PLAYBACK_EVENT)) {
                for (vat vatVar : this.LPk.zZm(this.yPL, playbackStateCompat)) {
                    String.format("sending %s event for %s", vatVar.zZm(), this.zQM.getValue());
                    this.BIo.zyO(ddC.zZm(this.zQM, vatVar));
                }
                Log.i(zZm, "Feature ALEXA_VOX_ANDROID_MEDIA_CHANGE_LISTENER_PLAYBACK_EVENT is enabled. ");
            } else {
                PlaybackStateCompat playbackStateCompat3 = this.yPL;
                HashSet<vat> hashSet = new HashSet();
                if (playbackStateCompat.getState() != 7 && (playbackStateCompat3 == null || playbackStateCompat3.getState() != playbackStateCompat.getState())) {
                    if (playbackStateCompat3 != null && playbackStateCompat3.getState() == 3 && playbackStateCompat.getState() != 3 && playbackStateCompat.getState() != 10 && playbackStateCompat.getState() != 9) {
                        hashSet.add(vat.PLAYBACK_STOPPED);
                    }
                    int state = playbackStateCompat.getState();
                    if (state != 3) {
                        if (state == 4 || state == 5) {
                            hashSet.add(vat.PLAY_MODE_CHANGED);
                        } else if (state == 9) {
                            hashSet.add(vat.PLAYBACK_PREVIOUS);
                        } else if (state == 10) {
                            hashSet.add(vat.PLAYBACK_NEXT);
                        }
                    } else if (playbackStateCompat3 == null || (playbackStateCompat3.getState() != 10 && playbackStateCompat3.getState() != 9)) {
                        hashSet.add(vat.PLAYBACK_STARTED);
                    }
                }
                for (vat vatVar2 : hashSet) {
                    String.format("Sending %s event", vatVar2.zZm());
                    this.BIo.zyO(ddC.zZm(this.zQM, vatVar2));
                }
                Log.i(zZm, "Feature ALEXA_VOX_ANDROID_MEDIA_CHANGE_LISTENER_PLAYBACK_EVENT is disabled. ");
            }
            this.yPL = playbackStateCompat;
        }
        MediaMetadataCompat metadata = this.lOf.getMetadata();
        if (metadata != null && !metadata.equals(this.Mlj)) {
            this.BIo.zyO(Qrm.zZm(this.zQM, playbackStateCompat, metadata));
            this.Mlj = metadata;
        } else if (playbackStateCompat != null) {
            StringBuilder zZm4 = C0179Pya.zZm("sendExternalPlayerUpdateEvent: ");
            zZm4.append(this.zQM.getValue());
            zZm4.toString();
            this.BIo.zyO(Qrm.zZm(this.zQM, playbackStateCompat, null));
        }
    }

    @Override // android.support.v4.media.session.MediaControllerCompat.Callback
    public void onRepeatModeChanged(int i) {
        StringBuilder zZm2 = C0179Pya.zZm("onRepeatModeChanged for Player ");
        zZm2.append(this.zQM);
        zZm2.toString();
        super.onRepeatModeChanged(i);
        this.BIo.zyO(ddC.zZm(this.zQM, vat.PLAY_MODE_CHANGED));
    }

    @Override // android.support.v4.media.session.MediaControllerCompat.Callback
    public void onSessionDestroyed() {
        StringBuilder zZm2 = C0179Pya.zZm("Session destroyed for Player: ");
        zZm2.append(this.zQM.getValue());
        zZm2.toString();
        this.BIo.zyO(new DNr(this.zQM));
        zQM();
    }

    @Override // android.support.v4.media.session.MediaControllerCompat.Callback
    public void onSessionEvent(String str, Bundle bundle) {
        super.onSessionEvent(str, bundle);
    }

    @Override // android.support.v4.media.session.MediaControllerCompat.Callback
    public void onShuffleModeChanged(int i) {
        StringBuilder zZm2 = C0179Pya.zZm("onShuffleModeChanged for Player ");
        zZm2.append(this.zQM);
        zZm2.toString();
        super.onShuffleModeChanged(i);
        this.BIo.zyO(ddC.zZm(this.zQM, vat.PLAY_MODE_CHANGED));
    }

    public void zQM() {
        ScheduledFuture<?> scheduledFuture = this.dMe;
        this.dMe = null;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.dMe = this.Qle.schedule(new Ixk(this), 250L, TimeUnit.MILLISECONDS);
    }

    public final void zZm() {
        ScheduledFuture<?> scheduledFuture = this.dMe;
        this.dMe = null;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    public void zyO() {
        RKf rKf = this.zzR;
        this.zzR = null;
        if (rKf != null) {
            this.BIo.zyO(LBB.zZm(rKf.zZm));
        }
    }
}
