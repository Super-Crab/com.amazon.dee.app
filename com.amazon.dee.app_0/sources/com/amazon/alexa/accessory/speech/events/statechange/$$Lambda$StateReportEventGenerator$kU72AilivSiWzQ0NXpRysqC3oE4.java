package com.amazon.alexa.accessory.speech.events.statechange;

import com.amazon.alexa.accessory.User;
import io.reactivex.rxjava3.functions.Predicate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.speech.events.statechange.-$$Lambda$StateReportEventGenerator$kU72AilivSiWzQ0NXpRysqC3oE4  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$StateReportEventGenerator$kU72AilivSiWzQ0NXpRysqC3oE4 implements Predicate {
    public static final /* synthetic */ $$Lambda$StateReportEventGenerator$kU72AilivSiWzQ0NXpRysqC3oE4 INSTANCE = new $$Lambda$StateReportEventGenerator$kU72AilivSiWzQ0NXpRysqC3oE4();

    private /* synthetic */ $$Lambda$StateReportEventGenerator$kU72AilivSiWzQ0NXpRysqC3oE4() {
    }

    @Override // io.reactivex.rxjava3.functions.Predicate
    public final boolean test(Object obj) {
        return StateReportEventGenerator.lambda$getStateReportInfoForPayloadGeneration$7((User) obj);
    }
}
