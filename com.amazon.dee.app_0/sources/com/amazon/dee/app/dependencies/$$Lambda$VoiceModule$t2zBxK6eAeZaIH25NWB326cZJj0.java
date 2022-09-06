package com.amazon.dee.app.dependencies;

import com.amazon.alexa.voice.app.LatencyReportingDelegate;
import com.amazon.dee.app.services.metrics.LatencyService;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.dependencies.-$$Lambda$VoiceModule$t2zBxK6eAeZaIH25NWB326cZJj0  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$VoiceModule$t2zBxK6eAeZaIH25NWB326cZJj0 implements LatencyReportingDelegate {
    public static final /* synthetic */ $$Lambda$VoiceModule$t2zBxK6eAeZaIH25NWB326cZJj0 INSTANCE = new $$Lambda$VoiceModule$t2zBxK6eAeZaIH25NWB326cZJj0();

    private /* synthetic */ $$Lambda$VoiceModule$t2zBxK6eAeZaIH25NWB326cZJj0() {
    }

    @Override // com.amazon.alexa.voice.app.LatencyReportingDelegate
    public final void reportLaunchCompletion(String str) {
        LatencyService.complete("voice", str);
    }
}
