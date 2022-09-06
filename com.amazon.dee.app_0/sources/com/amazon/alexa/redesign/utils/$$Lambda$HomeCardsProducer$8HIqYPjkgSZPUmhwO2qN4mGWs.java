package com.amazon.alexa.redesign.utils;

import com.amazon.alexa.redesign.utils.HomeCardsProducer;
import java.util.List;
import org.json.JSONObject;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.redesign.utils.-$$Lambda$HomeCardsProducer$8H-Iq-YPjkgSZPUmhwO2qN4mGWs  reason: invalid class name */
/* loaded from: classes10.dex */
public final /* synthetic */ class $$Lambda$HomeCardsProducer$8HIqYPjkgSZPUmhwO2qN4mGWs implements HomeCardsProducer.NewCardFactory {
    public static final /* synthetic */ $$Lambda$HomeCardsProducer$8HIqYPjkgSZPUmhwO2qN4mGWs INSTANCE = new $$Lambda$HomeCardsProducer$8HIqYPjkgSZPUmhwO2qN4mGWs();

    private /* synthetic */ $$Lambda$HomeCardsProducer$8HIqYPjkgSZPUmhwO2qN4mGWs() {
    }

    @Override // com.amazon.alexa.redesign.utils.HomeCardsProducer.NewCardFactory
    public final Object create(JSONObject jSONObject, List list) {
        return HomeCardsProducer.lambda$fromRawHomeCards$2(jSONObject, list);
    }
}
