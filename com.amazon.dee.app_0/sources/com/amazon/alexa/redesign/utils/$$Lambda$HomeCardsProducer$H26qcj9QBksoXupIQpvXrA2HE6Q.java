package com.amazon.alexa.redesign.utils;

import com.amazon.alexa.redesign.utils.HomeCardsProducer;
import java.util.List;
import org.json.JSONObject;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.redesign.utils.-$$Lambda$HomeCardsProducer$H26qcj9QBksoXupIQpvXrA2HE6Q  reason: invalid class name */
/* loaded from: classes10.dex */
public final /* synthetic */ class $$Lambda$HomeCardsProducer$H26qcj9QBksoXupIQpvXrA2HE6Q implements HomeCardsProducer.NewCardFactory {
    public static final /* synthetic */ $$Lambda$HomeCardsProducer$H26qcj9QBksoXupIQpvXrA2HE6Q INSTANCE = new $$Lambda$HomeCardsProducer$H26qcj9QBksoXupIQpvXrA2HE6Q();

    private /* synthetic */ $$Lambda$HomeCardsProducer$H26qcj9QBksoXupIQpvXrA2HE6Q() {
    }

    @Override // com.amazon.alexa.redesign.utils.HomeCardsProducer.NewCardFactory
    public final Object create(JSONObject jSONObject, List list) {
        return HomeCardsProducer.lambda$fromRawHomeCards$0(jSONObject, list);
    }
}
