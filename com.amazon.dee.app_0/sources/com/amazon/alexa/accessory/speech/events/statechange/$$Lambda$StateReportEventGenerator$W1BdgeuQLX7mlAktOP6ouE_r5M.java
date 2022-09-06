package com.amazon.alexa.accessory.speech.events.statechange;

import com.amazon.alexa.accessory.speech.events.statechange.StateReportEventGenerator;
import com.google.common.base.Predicate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.speech.events.statechange.-$$Lambda$StateReportEventGenerator$W1BdgeuQLX7mlAktOP6ouE_r-5M  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$StateReportEventGenerator$W1BdgeuQLX7mlAktOP6ouE_r5M implements Predicate {
    public static final /* synthetic */ $$Lambda$StateReportEventGenerator$W1BdgeuQLX7mlAktOP6ouE_r5M INSTANCE = new $$Lambda$StateReportEventGenerator$W1BdgeuQLX7mlAktOP6ouE_r5M();

    private /* synthetic */ $$Lambda$StateReportEventGenerator$W1BdgeuQLX7mlAktOP6ouE_r5M() {
    }

    @Override // com.google.common.base.Predicate
    public final boolean apply(Object obj) {
        return StateReportEventGenerator.lambda$generatePayload$9((StateReportEventGenerator.StateReportInfo) obj);
    }
}
