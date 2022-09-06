package com.google.android.exoplayer2.mediacodec;

import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.mediacodec.-$$Lambda$MediaCodecUtil$cCWO-3tN34TxRUMGlkaLU13g9pw  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$MediaCodecUtil$cCWO3tN34TxRUMGlkaLU13g9pw implements MediaCodecUtil.ScoreProvider {
    public static final /* synthetic */ $$Lambda$MediaCodecUtil$cCWO3tN34TxRUMGlkaLU13g9pw INSTANCE = new $$Lambda$MediaCodecUtil$cCWO3tN34TxRUMGlkaLU13g9pw();

    private /* synthetic */ $$Lambda$MediaCodecUtil$cCWO3tN34TxRUMGlkaLU13g9pw() {
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecUtil.ScoreProvider
    public final int getScore(Object obj) {
        return MediaCodecUtil.lambda$applyWorkarounds$1((MediaCodecInfo) obj);
    }
}
