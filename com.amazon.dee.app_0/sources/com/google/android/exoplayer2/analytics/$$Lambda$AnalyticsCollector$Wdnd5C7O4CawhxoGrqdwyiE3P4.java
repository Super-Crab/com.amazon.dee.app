package com.google.android.exoplayer2.analytics;

import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.util.ListenerSet;
import com.google.android.exoplayer2.util.MutableFlags;
/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.analytics.-$$Lambda$AnalyticsCollector$Wdnd5C7O4CawhxoGrqdwyi-E3P4  reason: invalid class name */
/* loaded from: classes2.dex */
public final /* synthetic */ class $$Lambda$AnalyticsCollector$Wdnd5C7O4CawhxoGrqdwyiE3P4 implements ListenerSet.IterationFinishedEvent {
    public static final /* synthetic */ $$Lambda$AnalyticsCollector$Wdnd5C7O4CawhxoGrqdwyiE3P4 INSTANCE = new $$Lambda$AnalyticsCollector$Wdnd5C7O4CawhxoGrqdwyiE3P4();

    private /* synthetic */ $$Lambda$AnalyticsCollector$Wdnd5C7O4CawhxoGrqdwyiE3P4() {
    }

    @Override // com.google.android.exoplayer2.util.ListenerSet.IterationFinishedEvent
    public final void invoke(Object obj, MutableFlags mutableFlags) {
        AnalyticsListener analyticsListener = (AnalyticsListener) obj;
        AnalyticsListener.Events events = (AnalyticsListener.Events) mutableFlags;
    }
}
