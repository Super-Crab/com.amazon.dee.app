package com.amazon.alexa.redesign.utils;

import com.amazon.alexa.redesign.utils.HomeCardsProducer;
import java.util.List;
import org.json.JSONObject;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.redesign.utils.-$$Lambda$HomeCardsProducer$HPFm_OGsK6Tn5OkDNcdMp01VrGQ  reason: invalid class name */
/* loaded from: classes10.dex */
public final /* synthetic */ class $$Lambda$HomeCardsProducer$HPFm_OGsK6Tn5OkDNcdMp01VrGQ implements HomeCardsProducer.NewCardFactory {
    public static final /* synthetic */ $$Lambda$HomeCardsProducer$HPFm_OGsK6Tn5OkDNcdMp01VrGQ INSTANCE = new $$Lambda$HomeCardsProducer$HPFm_OGsK6Tn5OkDNcdMp01VrGQ();

    private /* synthetic */ $$Lambda$HomeCardsProducer$HPFm_OGsK6Tn5OkDNcdMp01VrGQ() {
    }

    @Override // com.amazon.alexa.redesign.utils.HomeCardsProducer.NewCardFactory
    public final Object create(JSONObject jSONObject, List list) {
        return HomeCardsProducer.lambda$fromRawHomeCards$1(jSONObject, list);
    }
}
