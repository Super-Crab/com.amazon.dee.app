package com.amazon.alexa.accessory.speech.events.statechange;

import com.amazon.alexa.accessory.speech.events.statechange.StateReportEventGenerator;
import com.google.common.base.Predicate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.speech.events.statechange.-$$Lambda$StateReportEventGenerator$aoeoHa8zOjOrG0GGmIxx9SFK-xg  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$StateReportEventGenerator$aoeoHa8zOjOrG0GGmIxx9SFKxg implements Predicate {
    public static final /* synthetic */ $$Lambda$StateReportEventGenerator$aoeoHa8zOjOrG0GGmIxx9SFKxg INSTANCE = new $$Lambda$StateReportEventGenerator$aoeoHa8zOjOrG0GGmIxx9SFKxg();

    private /* synthetic */ $$Lambda$StateReportEventGenerator$aoeoHa8zOjOrG0GGmIxx9SFKxg() {
    }

    @Override // com.google.common.base.Predicate
    public final boolean apply(Object obj) {
        return StateReportEventGenerator.lambda$generatePayload$10((StateReportEventGenerator.StateReportInfo) obj);
    }
}
