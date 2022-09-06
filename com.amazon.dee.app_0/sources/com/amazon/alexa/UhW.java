package com.amazon.alexa;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.session.MediaControllerCompat;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.amazon.alexa.UGX;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.client.alexaservice.externalmediaplayer.payload.AutoValue_PlayerRuntimeState;
import com.amazon.alexa.uWW;
import java.util.UUID;
/* compiled from: MediaBrowserPlayer.java */
/* loaded from: classes.dex */
public class UhW implements Runnable {
    public final /* synthetic */ MediaControllerCompat.TransportControls BIo;
    public final /* synthetic */ JYe zQM;
    public final /* synthetic */ uWW zZm;

    public UhW(JYe jYe, uWW uww, MediaControllerCompat.TransportControls transportControls) {
        this.zQM = jYe;
        this.zZm = uww;
        this.BIo = transportControls;
    }

    @Override // java.lang.Runnable
    public void run() {
        Uri build;
        JYe jYe = this.zQM;
        HkJ zyO = jYe.jiA.zyO(jYe.zQM);
        GWl gWl = ((UBM) this.zZm).Qle;
        if (gWl != null && !gWl.getValue().isEmpty()) {
            JYe jYe2 = this.zQM;
            String str = jYe2.zZm;
            String.format("Received a new playbackSession request from AVS for player '%s' with playbackSessionId %s", jYe2.zQM.getValue(), gWl.getValue());
        } else {
            String uuid = UUID.randomUUID().toString();
            GWl zZm = GWl.zZm(uuid);
            JYe jYe3 = this.zQM;
            Log.w(jYe3.zZm, String.format("Player '%s' received a play directive from AVS without a playbackSessionId. A new id has been generated: %s'", jYe3.zQM.getValue(), uuid));
            gWl = zZm;
        }
        HkJ zZm2 = HkJ.zZm(zyO).zZm(((UBM) this.zZm).jiA).zZm();
        JYe jYe4 = this.zQM;
        jYe4.jiA.BIo(jYe4.zQM, zZm2);
        JYe jYe5 = this.zQM;
        qKe qke = jYe5.Qle;
        vQe vqe = jYe5.zQM;
        UGX.zZm zzm = new UGX.zZm();
        if (vqe != null) {
            zzm.zZm = vqe;
            UGX.zZm zzm2 = (UGX.zZm) zzm.zZm(GWl.zZm);
            zzm2.BIo = gWl;
            String str2 = "";
            if (zzm2.zZm == null) {
                str2 = C0179Pya.zZm(str2, " playerId");
            }
            if (zzm2.BIo == null) {
                str2 = C0179Pya.zZm(str2, " inactiveAvsPlaybackSessionId");
            }
            if (str2.isEmpty()) {
                qke.BIo(vqe, new AutoValue_PlayerRuntimeState(zzm2.zZm, zzm2.BIo));
                pKE pke = new pKE();
                UBM ubm = (UBM) this.zZm;
                pHD phd = ubm.zZm;
                long j = ubm.BIo;
                long j2 = ubm.zQM;
                uWW.zZm zzm3 = ubm.JTe;
                StringBuilder sb = new StringBuilder();
                if (phd == null) {
                    build = null;
                } else {
                    if (zzm3 == null) {
                        zzm3 = uWW.zZm.DEFAULT;
                    }
                    if (phd.getValue().startsWith("spotify")) {
                        sb.append("alexa");
                        sb.append(":");
                        sb.append(phd.getValue());
                        sb.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
                        pke.zZm("index", String.valueOf(j), sb).append(WebConstants.UriConstants.AMPERSAND_KEY);
                        pke.zZm("offsetInMilliseconds", String.valueOf(j2), sb).append(WebConstants.UriConstants.AMPERSAND_KEY);
                        pke.zZm(NotificationCompat.CATEGORY_NAVIGATION, zzm3.name(), sb);
                        build = Uri.parse(sb.toString());
                    } else {
                        Uri.Builder builder = new Uri.Builder();
                        builder.scheme("alexa").path(phd.getValue()).appendQueryParameter("index", String.valueOf(j)).appendQueryParameter("offsetInMilliseconds", String.valueOf(j2)).appendQueryParameter(NotificationCompat.CATEGORY_NAVIGATION, zzm3.name());
                        build = builder.build();
                    }
                }
                if (build != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString(fWU.PLAYBACK_SESSION_ID.zZm(), gWl.getValue());
                    if (((UBM) this.zZm).LPk) {
                        this.BIo.prepareFromUri(build, bundle);
                        return;
                    } else {
                        this.BIo.playFromUri(build, bundle);
                        return;
                    }
                }
                String str3 = this.zQM.zZm;
                return;
            }
            throw new IllegalStateException(C0179Pya.zZm("Missing required properties:", str2));
        }
        throw new NullPointerException("Null playerId");
    }
}
