package com.google.android.exoplayer2.mediacodec;

import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.mediacodec.-$$Lambda$MediaCodecUtil$5Z-WFpP5Ck4Hyp9KyuAYDjY5c2U  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$MediaCodecUtil$5ZWFpP5Ck4Hyp9KyuAYDjY5c2U implements MediaCodecUtil.ScoreProvider {
    public static final /* synthetic */ $$Lambda$MediaCodecUtil$5ZWFpP5Ck4Hyp9KyuAYDjY5c2U INSTANCE = new $$Lambda$MediaCodecUtil$5ZWFpP5Ck4Hyp9KyuAYDjY5c2U();

    private /* synthetic */ $$Lambda$MediaCodecUtil$5ZWFpP5Ck4Hyp9KyuAYDjY5c2U() {
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecUtil.ScoreProvider
    public final int getScore(Object obj) {
        return MediaCodecUtil.lambda$applyWorkarounds$2((MediaCodecInfo) obj);
    }
}
