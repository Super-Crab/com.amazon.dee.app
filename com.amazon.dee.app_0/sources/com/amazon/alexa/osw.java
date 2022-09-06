package com.amazon.alexa;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.hVu;
import com.amazon.alexa.onD;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.LoopingMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.Util;
import com.google.common.collect.ImmutableList;
import dagger.Lazy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import okhttp3.Response;
/* compiled from: AVSMediaSourceFactory.java */
/* loaded from: classes.dex */
public class osw implements KSk {
    public static final List<zZm> BIo = ImmutableList.of(zZm.M3U, zZm.PLS);
    public static final String zZm = "osw";
    public final Lazy<shl> JTe;
    public final cCP LPk;
    public final Lazy<onD> Qle;
    public final DefaultBandwidthMeter jiA;
    public final Context zQM;
    public final Handler zyO;

    /* compiled from: AVSMediaSourceFactory.java */
    /* loaded from: classes.dex */
    private static class BIo implements DataSource.Factory {
        public final Context zZm;

        public BIo(Context context) {
            this.zZm = context;
        }

        @Override // com.google.android.exoplayer2.upstream.DataSource.Factory
        /* renamed from: createDataSource */
        public DataSource mo7468createDataSource() {
            return new RawResourceDataSource(this.zZm);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AVSMediaSourceFactory.java */
    /* loaded from: classes.dex */
    public enum zZm {
        DASH(0),
        SMOOTH_STREAMING(1),
        HTTP_LIVE_STREAMING(2),
        OTHER(3),
        M3U(4),
        PLS(5),
        RAW(6);
        
        public final int exoplayerType;

        zZm(int i) {
            this.exoplayerType = i;
        }

        public static zZm zZm(Uri uri) {
            zZm[] values;
            String lastPathSegment = uri.getLastPathSegment();
            String str = osw.zZm;
            String str2 = "Inferring content type from extension: " + lastPathSegment;
            if (uri.getScheme() != null && uri.getScheme().equals(RawResourceDataSource.RAW_RESOURCE_SCHEME)) {
                return RAW;
            }
            if (lastPathSegment != null && (lastPathSegment.endsWith(".ashx") || lastPathSegment.endsWith(".m3u"))) {
                return M3U;
            }
            if (lastPathSegment != null && lastPathSegment.endsWith(".pls")) {
                return PLS;
            }
            if (lastPathSegment == null) {
                return OTHER;
            }
            int inferContentType = Util.inferContentType(lastPathSegment);
            for (zZm zzm : values()) {
                if (zzm.exoplayerType == inferContentType) {
                    return zzm;
                }
            }
            return OTHER;
        }
    }

    public osw(Handler handler, Context context, DefaultBandwidthMeter defaultBandwidthMeter, @Nullable Lazy<onD> lazy, Lazy<shl> lazy2, Lazy<dAN> lazy3, cCP ccp) {
        this.zyO = handler;
        this.zQM = context;
        this.Qle = lazy;
        this.JTe = lazy2;
        this.jiA = defaultBandwidthMeter;
        this.LPk = ccp;
    }

    public final ConcatenatingMediaSource zZm(onD ond, Uri uri, boolean z, DataSource.Factory factory, Handler handler) throws IOException {
        MediaSource zZm2;
        Response zZm3 = onD.zZm(ond.BIo, uri);
        if (zZm3.isSuccessful() && zZm3.code() == 200) {
            String string = zZm3.body().string();
            ArrayList<Uri> arrayList = new ArrayList();
            if (string != null) {
                try {
                    if (!string.isEmpty()) {
                        Matcher matcher = onD.zZm.matcher(string);
                        while (matcher.find()) {
                            arrayList.add(Uri.parse(string.substring(matcher.start(), matcher.end())));
                        }
                        if (!arrayList.isEmpty()) {
                            ArrayList arrayList2 = new ArrayList();
                            for (Uri uri2 : arrayList) {
                                zZm zZm4 = zZm(uri2, factory);
                                if (BIo.contains(zZm4)) {
                                    zZm2 = zZm(ond, uri2, z, factory, handler);
                                } else {
                                    zZm2 = zZm(zZm4, uri2, z, factory, handler);
                                }
                                arrayList2.add(zZm2);
                            }
                            return this.LPk.zZm(arrayList2);
                        }
                        throw new onD.zZm("Response did not contain URLs");
                    }
                } catch (IOException e) {
                    throw new onD.zZm(e);
                }
            }
            throw new onD.zZm("Response was empty");
        }
        StringBuilder zZm5 = C0179Pya.zZm("Received a non-200 response: ");
        zZm5.append(zZm3.code());
        throw new onD.zZm(zZm5.toString());
    }

    @Override // com.amazon.alexa.KSk
    public MediaSource zZm(kQf kqf) throws IOException {
        char c;
        DataSource.Factory zzm;
        Lazy<onD> lazy;
        Context context = this.zQM;
        String scheme = kqf.zQM.getScheme();
        int hashCode = scheme.hashCode();
        if (hashCode != -1255746506) {
            if (hashCode == 98494 && scheme.equals("cid")) {
                c = 0;
            }
            c = 65535;
        } else {
            if (scheme.equals(RawResourceDataSource.RAW_RESOURCE_SCHEME)) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            zzm = new hVu.zZm(this.JTe.mo358get());
        } else if (c != 1) {
            zzm = new DefaultDataSourceFactory(context, this.jiA, new DefaultHttpDataSourceFactory(Util.getUserAgent(context, AlexaService.zZm), this.jiA, 8000, 8000, true));
        } else {
            zzm = new BIo(context);
        }
        DataSource.Factory factory = zzm;
        Uri uri = kqf.zQM;
        boolean z = kqf.JTe;
        zZm zZm2 = zZm(uri, factory);
        if (BIo.contains(zZm2) && (lazy = this.Qle) != null) {
            return zZm(lazy.mo358get(), uri, z, factory, this.zyO);
        }
        return zZm(zZm2, uri, z, factory, this.zyO);
    }

    public final MediaSource zZm(zZm zzm, Uri uri, boolean z, DataSource.Factory factory, Handler handler) {
        int ordinal = zzm.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return new SsMediaSource.Factory(factory).mo7417createMediaSource(uri);
            }
            if (ordinal == 2) {
                return new HlsMediaSource.Factory(factory).mo7417createMediaSource(uri);
            }
            if (ordinal != 6) {
                return new ExtractorMediaSource(uri, factory, new DefaultExtractorsFactory(), handler, null);
            }
            if (z) {
                return new LoopingMediaSource(new ExtractorMediaSource(uri, factory, new DefaultExtractorsFactory(), handler, null));
            }
            return new ExtractorMediaSource(uri, factory, new DefaultExtractorsFactory(), handler, null);
        }
        return new DashMediaSource.Factory(factory).mo7417createMediaSource(uri);
    }

    public final zZm zZm(Uri uri, DataSource.Factory factory) {
        zZm zZm2 = zZm.zZm(uri);
        if (!zZm2.equals(zZm.OTHER) || TextUtils.isEmpty(uri.getScheme()) || !uri.getScheme().equals("https")) {
            return zZm2;
        }
        try {
            DataSource mo7468createDataSource = factory.mo7468createDataSource();
            mo7468createDataSource.open(new DataSpec(uri));
            Uri uri2 = mo7468createDataSource.getUri();
            if (uri2 != null && !uri2.equals(uri)) {
                String.format("PlayItem %s redirect resolved to %s", uri, uri2);
                uri = uri2;
            }
        } catch (IOException e) {
            Log.w(zZm, "Failed to resolve redirects for stream", e);
        } catch (IllegalArgumentException e2) {
            Log.w(zZm, "Cannot resolve redirects for an invalid url", e2);
        }
        return zZm.zZm(uri);
    }
}
