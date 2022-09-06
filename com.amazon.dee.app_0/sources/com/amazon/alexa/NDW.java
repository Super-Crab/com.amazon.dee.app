package com.amazon.alexa;

import android.net.Uri;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import com.amazon.alexa.NEv;
import com.amazon.alexa.api.AlexaMediaPlaybackListener;
import com.amazon.alexa.api.AlexaMediaPlaybackMetadata;
import com.amazon.alexa.api.AlexaMediaPlaybackState;
import com.amazon.alexa.api.AlexaPlaybackState;
import com.amazon.alexa.api.AlexaPlayerInfoState;
import com.amazon.alexa.api.ExtendedClient;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.client.annotations.Nullable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import org.greenrobot.eventbus.Subscribe;
/* compiled from: AlexaMediaPlaybackAuthority.java */
/* loaded from: classes.dex */
public class NDW {
    public final AlexaClientEventBus zZm;
    public final Shr<AlexaMediaPlaybackListener> BIo = new Shr<>();
    @Nullable
    public volatile AlexaMediaPlaybackState zQM = null;
    @Nullable
    public final HashMap<String, AlexaMediaPlaybackMetadata> zyO = new LinkedHashMap();

    @Inject
    public NDW(AlexaClientEventBus alexaClientEventBus) {
        this.zZm = alexaClientEventBus;
        this.zZm.zZm(this);
    }

    @Subscribe
    public void on(vhe vheVar) {
        AlexaMediaPlaybackMetadata alexaMediaPlaybackMetadata;
        Log.i("MediaPlayback", "on: MediaPlayerListenerRegister");
        UaM uaM = (UaM) vheVar;
        ExtendedClient extendedClient = uaM.zQM;
        AlexaMediaPlaybackListener alexaMediaPlaybackListener = uaM.jiA;
        if (!this.BIo.zZm((Shr<AlexaMediaPlaybackListener>) alexaMediaPlaybackListener)) {
            this.BIo.zZm(extendedClient, alexaMediaPlaybackListener);
            if (this.zQM != null) {
                alexaMediaPlaybackListener.onMediaPlaybackStateUpdate(this.zQM);
                String mediaItemIdentifier = this.zQM.getMediaItemIdentifier();
                if (!TextUtils.isEmpty(mediaItemIdentifier) && (alexaMediaPlaybackMetadata = this.zyO.get(mediaItemIdentifier)) != null) {
                    alexaMediaPlaybackListener.onMediaMetadata(alexaMediaPlaybackMetadata);
                }
            }
        }
        this.zZm.zyO(NEv.zQM.zZm(uaM.BIo));
    }

    public final void zZm(AlexaPlayerInfoState alexaPlayerInfoState, String str, String str2) {
        if (alexaPlayerInfoState.equals(AlexaPlayerInfoState.DONE) || alexaPlayerInfoState.equals(AlexaPlayerInfoState.CANCELLED)) {
            Iterator<AlexaMediaPlaybackMetadata> it2 = this.zyO.values().iterator();
            while (it2.hasNext()) {
                AlexaMediaPlaybackMetadata next = it2.next();
                if (str != null && str.equals(next.getMediaServiceProvider())) {
                    it2.remove();
                    if (next.getMediaItemIdentifier().equals(str2)) {
                        return;
                    }
                }
            }
        }
    }

    @Subscribe
    public void on(eXo exo) {
        Log.i("MediaPlayback", "on: MediaPlayerListenerDeregister");
        C0199dlc c0199dlc = (C0199dlc) exo;
        this.BIo.remove(c0199dlc.jiA);
        this.zZm.zyO(NEv.zQM.zZm(c0199dlc.BIo));
    }

    @Subscribe
    public void on(pPd ppd) {
        MuN muN = (MuN) ppd;
        String value = muN.BIo.getValue();
        Long l = muN.LPk;
        AlexaMediaPlaybackMetadata build = AlexaMediaPlaybackMetadata.builder(value).setMediaServiceProvider("Internal AudioPlayer").setArtistName(muN.zyO).setAlbumArtUri(muN.JTe).setAlbumName(muN.jiA).setSongName(muN.zQM).setSecondaryArtUri(muN.Qle).setDuration(TimeUnit.SECONDS.toMillis(l != null ? l.longValue() : 0L)).build();
        if (!this.zyO.containsKey(value)) {
            this.zyO.put(value, build);
            for (AlexaMediaPlaybackListener alexaMediaPlaybackListener : this.BIo.zZm()) {
                alexaMediaPlaybackListener.onMediaMetadata(build);
            }
        }
    }

    @Subscribe
    public synchronized void on(CKO cko) {
        IyB iyB = (IyB) cko;
        AlexaMediaPlaybackState build = AlexaMediaPlaybackState.builder().setMediaItemIdentifier(((IyB) cko).zQM.getValue()).setBufferedPosition(-1L).setLastPositionUpdateTime(-1L).setCurrentPosition(iyB.zyO).setPlayerState(iyB.BIo).build();
        this.zQM = build;
        zZm(build.getPlayerState(), "Internal AudioPlayer", build.getMediaItemIdentifier());
        for (AlexaMediaPlaybackListener alexaMediaPlaybackListener : this.BIo.zZm()) {
            alexaMediaPlaybackListener.onMediaPlaybackStateUpdate(build);
        }
    }

    @Subscribe
    public synchronized void on(Qrm qrm) {
        String str;
        AlexaPlayerInfoState alexaPlayerInfoState;
        AlexaPlaybackState alexaPlaybackState;
        Log.i("MediaPlayback", "on: ExternalMediaPlayerUpdateEvent");
        MediaMetadataCompat mediaMetadataCompat = ((NMu) qrm).BIo;
        NMu nMu = (NMu) qrm;
        PlaybackStateCompat playbackStateCompat = nMu.zQM;
        AlexaMediaPlaybackState alexaMediaPlaybackState = null;
        if (mediaMetadataCompat != null) {
            str = mediaMetadataCompat.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID);
            if (str != null && !this.zyO.containsKey(str)) {
                String value = nMu.zyO.getValue();
                AlexaMediaPlaybackMetadata.Builder builder = AlexaMediaPlaybackMetadata.builder(str);
                builder.setMediaServiceProvider(value);
                builder.setSongName(mediaMetadataCompat.getString(MediaMetadataCompat.METADATA_KEY_TITLE));
                builder.setAlbumName(mediaMetadataCompat.getString(MediaMetadataCompat.METADATA_KEY_ALBUM));
                builder.setArtistName(mediaMetadataCompat.getString(MediaMetadataCompat.METADATA_KEY_ARTIST));
                builder.setDuration(mediaMetadataCompat.getLong(MediaMetadataCompat.METADATA_KEY_DURATION));
                String string = mediaMetadataCompat.getString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI);
                if (!TextUtils.isEmpty(string)) {
                    builder.setAlbumArtUri(Uri.parse(string));
                }
                String string2 = mediaMetadataCompat.getString(MediaMetadataCompat.METADATA_KEY_ART_URI);
                if (!TextUtils.isEmpty(string2)) {
                    builder.setSecondaryArtUri(Uri.parse(string2));
                }
                AlexaMediaPlaybackMetadata build = builder.build();
                this.zyO.put(str, build);
                for (AlexaMediaPlaybackListener alexaMediaPlaybackListener : this.BIo.zZm()) {
                    alexaMediaPlaybackListener.onMediaMetadata(build);
                }
            }
        } else {
            str = null;
        }
        if (playbackStateCompat != null) {
            int state = playbackStateCompat.getState();
            if (state == 0) {
                alexaPlayerInfoState = AlexaPlayerInfoState.IDLE;
            } else if (state == 1) {
                alexaPlayerInfoState = AlexaPlayerInfoState.DONE;
            } else if (state == 2) {
                alexaPlayerInfoState = AlexaPlayerInfoState.PAUSED;
            } else if (state == 3) {
                alexaPlayerInfoState = AlexaPlayerInfoState.PLAYING;
            } else if (state != 6) {
                alexaPlayerInfoState = state != 7 ? null : AlexaPlayerInfoState.ERROR;
            } else {
                alexaPlayerInfoState = AlexaPlayerInfoState.BUFFERING;
            }
            if (alexaPlayerInfoState != null) {
                AlexaMediaPlaybackState.Builder builder2 = AlexaMediaPlaybackState.builder();
                if (!TextUtils.isEmpty(str)) {
                    builder2.setMediaItemIdentifier(str);
                }
                if ((playbackStateCompat.getActions() & 72) == 72) {
                    alexaPlaybackState = AlexaPlaybackState.STOPPABLE_AND_NAVIGABLE;
                } else {
                    alexaPlaybackState = AlexaPlaybackState.STOPPABLE;
                }
                builder2.setPlaybackActions(alexaPlaybackState);
                builder2.setPlayerState(alexaPlayerInfoState);
                builder2.setCurrentPosition(playbackStateCompat.getPosition());
                builder2.setLastPositionUpdateTime(playbackStateCompat.getLastPositionUpdateTime());
                builder2.setBufferedPosition(playbackStateCompat.getBufferedPosition());
                alexaMediaPlaybackState = builder2.build();
            }
            if (alexaMediaPlaybackState == null) {
                return;
            }
            this.zQM = alexaMediaPlaybackState;
            zZm(this.zQM.getPlayerState(), nMu.zyO.getValue(), this.zQM.getMediaItemIdentifier());
            for (AlexaMediaPlaybackListener alexaMediaPlaybackListener2 : this.BIo.zZm()) {
                alexaMediaPlaybackListener2.onMediaPlaybackStateUpdate(this.zQM);
            }
        }
    }

    @Subscribe
    public void on(xZV xzv) {
        this.BIo.BIo(((uyC) xzv).BIo);
    }
}
