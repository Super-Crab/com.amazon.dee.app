package com.google.android.exoplayer2.source.hls;

import android.net.Uri;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import java.util.List;
import java.util.Map;
/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.source.hls.-$$Lambda$MediaParserHlsMediaChunkExtractor$f9OfLSXGEir8oI1ARv5-TcP1SPE  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$MediaParserHlsMediaChunkExtractor$f9OfLSXGEir8oI1ARv5TcP1SPE implements HlsExtractorFactory {
    public static final /* synthetic */ $$Lambda$MediaParserHlsMediaChunkExtractor$f9OfLSXGEir8oI1ARv5TcP1SPE INSTANCE = new $$Lambda$MediaParserHlsMediaChunkExtractor$f9OfLSXGEir8oI1ARv5TcP1SPE();

    private /* synthetic */ $$Lambda$MediaParserHlsMediaChunkExtractor$f9OfLSXGEir8oI1ARv5TcP1SPE() {
    }

    @Override // com.google.android.exoplayer2.source.hls.HlsExtractorFactory
    /* renamed from: createExtractor */
    public final HlsMediaChunkExtractor mo7400createExtractor(Uri uri, Format format, byte[] bArr, byte[] bArr2, List list, TimestampAdjuster timestampAdjuster, Map map, ExtractorInput extractorInput) {
        return MediaParserHlsMediaChunkExtractor.lambda$static$0(uri, format, bArr, bArr2, list, timestampAdjuster, map, extractorInput);
    }
}
