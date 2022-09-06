package com.google.android.exoplayer2.extractor.mp4;

import com.google.common.base.Function;
/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.extractor.mp4.-$$Lambda$Mp4Extractor$jNmPwqo4-EkxjMq3riO8isp5v1k  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$Mp4Extractor$jNmPwqo4EkxjMq3riO8isp5v1k implements Function {
    public static final /* synthetic */ $$Lambda$Mp4Extractor$jNmPwqo4EkxjMq3riO8isp5v1k INSTANCE = new $$Lambda$Mp4Extractor$jNmPwqo4EkxjMq3riO8isp5v1k();

    private /* synthetic */ $$Lambda$Mp4Extractor$jNmPwqo4EkxjMq3riO8isp5v1k() {
    }

    @Override // com.google.common.base.Function
    /* renamed from: apply */
    public final Object mo8172apply(Object obj) {
        Track track = (Track) obj;
        Mp4Extractor.lambda$processMoovAtom$1(track);
        return track;
    }
}
