package com.google.android.exoplayer2;

import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.util.ListenerSet;
/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.-$$Lambda$ExoPlayerImpl$KOvTAObvT_F_h8dMHR1N2O8QgrE  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$ExoPlayerImpl$KOvTAObvT_F_h8dMHR1N2O8QgrE implements ListenerSet.Event {
    public static final /* synthetic */ $$Lambda$ExoPlayerImpl$KOvTAObvT_F_h8dMHR1N2O8QgrE INSTANCE = new $$Lambda$ExoPlayerImpl$KOvTAObvT_F_h8dMHR1N2O8QgrE();

    private /* synthetic */ $$Lambda$ExoPlayerImpl$KOvTAObvT_F_h8dMHR1N2O8QgrE() {
    }

    @Override // com.google.android.exoplayer2.util.ListenerSet.Event
    public final void invoke(Object obj) {
        ((Player.EventListener) obj).onPlayerError(ExoPlaybackException.createForRenderer(new ExoTimeoutException(1)));
    }
}
