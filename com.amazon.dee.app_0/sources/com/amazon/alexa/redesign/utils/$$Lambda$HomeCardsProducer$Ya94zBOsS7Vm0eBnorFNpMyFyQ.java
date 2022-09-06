package com.amazon.alexa.redesign.utils;

import com.amazon.alexa.redesign.utils.HomeCardsProducer;
import java.util.List;
import org.json.JSONObject;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.redesign.utils.-$$Lambda$HomeCardsProducer$Ya94zBOsS7Vm0eBnorFNpM-yFyQ  reason: invalid class name */
/* loaded from: classes10.dex */
public final /* synthetic */ class $$Lambda$HomeCardsProducer$Ya94zBOsS7Vm0eBnorFNpMyFyQ implements HomeCardsProducer.NewCardFactory {
    public static final /* synthetic */ $$Lambda$HomeCardsProducer$Ya94zBOsS7Vm0eBnorFNpMyFyQ INSTANCE = new $$Lambda$HomeCardsProducer$Ya94zBOsS7Vm0eBnorFNpMyFyQ();

    private /* synthetic */ $$Lambda$HomeCardsProducer$Ya94zBOsS7Vm0eBnorFNpMyFyQ() {
    }

    @Override // com.amazon.alexa.redesign.utils.HomeCardsProducer.NewCardFactory
    public final Object create(JSONObject jSONObject, List list) {
        return HomeCardsProducer.lambda$fromRawHomeCards$4(jSONObject, list);
    }
}
