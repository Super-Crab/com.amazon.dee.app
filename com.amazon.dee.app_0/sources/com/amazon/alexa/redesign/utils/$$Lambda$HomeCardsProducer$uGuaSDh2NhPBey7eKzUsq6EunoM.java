package com.amazon.alexa.redesign.utils;

import com.amazon.alexa.redesign.utils.HomeCardsProducer;
import java.util.List;
import org.json.JSONObject;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.redesign.utils.-$$Lambda$HomeCardsProducer$uGuaSDh2NhPBey7eKzUsq6EunoM  reason: invalid class name */
/* loaded from: classes10.dex */
public final /* synthetic */ class $$Lambda$HomeCardsProducer$uGuaSDh2NhPBey7eKzUsq6EunoM implements HomeCardsProducer.NewCardFactory {
    public static final /* synthetic */ $$Lambda$HomeCardsProducer$uGuaSDh2NhPBey7eKzUsq6EunoM INSTANCE = new $$Lambda$HomeCardsProducer$uGuaSDh2NhPBey7eKzUsq6EunoM();

    private /* synthetic */ $$Lambda$HomeCardsProducer$uGuaSDh2NhPBey7eKzUsq6EunoM() {
    }

    @Override // com.amazon.alexa.redesign.utils.HomeCardsProducer.NewCardFactory
    public final Object create(JSONObject jSONObject, List list) {
        return HomeCardsProducer.lambda$fromRawHomeCards$3(jSONObject, list);
    }
}
