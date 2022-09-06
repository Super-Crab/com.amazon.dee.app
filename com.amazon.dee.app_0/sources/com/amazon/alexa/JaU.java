package com.amazon.alexa;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.amazon.alexa.fcj;
import com.amazon.alexa.hVu;
import com.amazon.alexa.onD;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
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
import dagger.Lazy;
import java.io.IOException;
import java.util.regex.Matcher;
import okhttp3.Response;
/* compiled from: OldMediaSourceFactory.java */
@Deprecated
/* loaded from: classes.dex */
public class JaU implements KSk {
    public static final String zZm = "JaU";
    public final Context BIo;
    public final Lazy<shl> Qle;
    public final Lazy<onD> jiA;
    public final Handler zQM;
    public final DefaultBandwidthMeter zyO;

    /* compiled from: OldMediaSourceFactory.java */
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
    /* compiled from: OldMediaSourceFactory.java */
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
            String str = "Inferring content type from extension: " + lastPathSegment;
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

    public JaU(Handler handler, Context context, DefaultBandwidthMeter defaultBandwidthMeter, @Nullable Lazy<onD> lazy, Lazy<shl> lazy2, Lazy<dAN> lazy3) {
        this.zQM = handler;
        this.BIo = context;
        this.jiA = lazy;
        this.Qle = lazy2;
        this.zyO = defaultBandwidthMeter;
    }

    public MediaSource zZm(kQf kqf, DataSource.Factory factory, Handler handler) throws IOException {
        Uri uri;
        Uri uri2 = kqf.zQM;
        zZm zZm2 = zZm.zZm(uri2);
        if (zZm2.equals(zZm.OTHER) && !TextUtils.isEmpty(uri2.getScheme()) && uri2.getScheme().equals("https")) {
            try {
                DataSource mo7468createDataSource = factory.mo7468createDataSource();
                mo7468createDataSource.open(new DataSpec(uri2));
                uri = mo7468createDataSource.getUri();
            } catch (IOException e) {
                Log.w(zZm, "Failed to resolve redirects for stream", e);
            } catch (IllegalArgumentException e2) {
                Log.w(zZm, "Cannot resolve redirects for an invalid url", e2);
            }
            if (uri != null && !uri.equals(uri2)) {
                String.format("PlayItem %s redirect resolved to %s", uri2, uri);
                zZm2 = zZm.zZm(uri);
            }
            uri = uri2;
            zZm2 = zZm.zZm(uri);
        }
        int ordinal = zZm2.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return new SsMediaSource.Factory(factory).mo7417createMediaSource(uri2);
            }
            if (ordinal != 2) {
                if (ordinal == 4 || ordinal == 5) {
                    if (this.jiA != null) {
                        Puy mo947BIo = kqf.mo947BIo();
                        Response zZm3 = onD.zZm(this.jiA.mo358get().BIo, kqf.zQM);
                        if (zZm3.isSuccessful() && zZm3.code() == 200) {
                            String string = zZm3.body().string();
                            if (string != null) {
                                try {
                                    if (!string.isEmpty()) {
                                        Matcher matcher = onD.zZm.matcher(string);
                                        if (matcher.find()) {
                                            return zZm(new kQf(mo947BIo, Uri.parse(string.substring(matcher.start(), matcher.end())), kqf.zyO, null, null, fcj.zZm.PAUSE, false), factory, handler);
                                        }
                                        throw new onD.zZm("Response did not contain URLs");
                                    }
                                } catch (IOException e3) {
                                    throw new onD.zZm(e3);
                                }
                            }
                            throw new onD.zZm("Response was empty");
                        }
                        StringBuilder zZm4 = C0179Pya.zZm("Received a non-200 response: ");
                        zZm4.append(zZm3.code());
                        throw new onD.zZm(zZm4.toString());
                    }
                } else if (ordinal == 6) {
                    if (kqf.JTe) {
                        return new LoopingMediaSource(new ExtractorMediaSource(uri2, factory, new DefaultExtractorsFactory(), handler, null));
                    }
                    return new ExtractorMediaSource(uri2, factory, new DefaultExtractorsFactory(), handler, null);
                }
                return new ExtractorMediaSource(uri2, factory, new DefaultExtractorsFactory(), handler, null);
            }
            return new HlsMediaSource.Factory(factory).mo7417createMediaSource(uri2);
        }
        return new DashMediaSource.Factory(factory).mo7417createMediaSource(uri2);
    }

    @Override // com.amazon.alexa.KSk
    public MediaSource zZm(kQf kqf) throws IOException {
        char c;
        DataSource.Factory zzm;
        Context context = this.BIo;
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
            zzm = new hVu.zZm(this.Qle.mo358get());
        } else if (c != 1) {
            zzm = new DefaultDataSourceFactory(context, this.zyO, new DefaultHttpDataSourceFactory(Util.getUserAgent(context, AlexaService.zZm), this.zyO, 8000, 8000, true));
        } else {
            zzm = new BIo(context);
        }
        return zZm(kqf, zzm, this.zQM);
    }
}
