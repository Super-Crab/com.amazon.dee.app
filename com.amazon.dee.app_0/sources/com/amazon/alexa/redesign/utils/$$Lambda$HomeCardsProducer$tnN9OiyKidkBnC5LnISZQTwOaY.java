package com.amazon.alexa.redesign.utils;

import com.amazon.alexa.redesign.utils.HomeCardsProducer;
import java.util.List;
import org.json.JSONObject;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.redesign.utils.-$$Lambda$HomeCardsProducer$tnN9OiyKidkBnC5LnISZQTw-OaY  reason: invalid class name */
/* loaded from: classes10.dex */
public final /* synthetic */ class $$Lambda$HomeCardsProducer$tnN9OiyKidkBnC5LnISZQTwOaY implements HomeCardsProducer.NewCardFactory {
    public static final /* synthetic */ $$Lambda$HomeCardsProducer$tnN9OiyKidkBnC5LnISZQTwOaY INSTANCE = new $$Lambda$HomeCardsProducer$tnN9OiyKidkBnC5LnISZQTwOaY();

    private /* synthetic */ $$Lambda$HomeCardsProducer$tnN9OiyKidkBnC5LnISZQTwOaY() {
    }

    @Override // com.amazon.alexa.redesign.utils.HomeCardsProducer.NewCardFactory
    public final Object create(JSONObject jSONObject, List list) {
        return HomeCardsProducer.lambda$fromRawHomeCards$5(jSONObject, list);
    }
}
