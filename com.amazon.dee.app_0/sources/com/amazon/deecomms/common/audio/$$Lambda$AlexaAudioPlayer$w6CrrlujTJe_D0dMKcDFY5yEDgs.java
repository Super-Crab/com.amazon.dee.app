package com.amazon.deecomms.common.audio;

import android.media.MediaPlayer;
/* compiled from: lambda */
/* renamed from: com.amazon.deecomms.common.audio.-$$Lambda$AlexaAudioPlayer$w6CrrlujTJe_D0dMKcDFY5yEDgs  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$AlexaAudioPlayer$w6CrrlujTJe_D0dMKcDFY5yEDgs implements MediaPlayer.OnErrorListener {
    public static final /* synthetic */ $$Lambda$AlexaAudioPlayer$w6CrrlujTJe_D0dMKcDFY5yEDgs INSTANCE = new $$Lambda$AlexaAudioPlayer$w6CrrlujTJe_D0dMKcDFY5yEDgs();

    private /* synthetic */ $$Lambda$AlexaAudioPlayer$w6CrrlujTJe_D0dMKcDFY5yEDgs() {
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        AlexaAudioPlayer.lambda$handlePlay$0(mediaPlayer, i, i2);
        return false;
    }
}
