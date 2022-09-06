package com.amazon.alexa.accessorykit.nearmiss;

import io.reactivex.rxjava3.functions.Function;
import org.json.JSONObject;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.nearmiss.-$$Lambda$MlisHttpClient$Qd_euoQ4L8qNydYltUHylJwvyaE  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$MlisHttpClient$Qd_euoQ4L8qNydYltUHylJwvyaE implements Function {
    public static final /* synthetic */ $$Lambda$MlisHttpClient$Qd_euoQ4L8qNydYltUHylJwvyaE INSTANCE = new $$Lambda$MlisHttpClient$Qd_euoQ4L8qNydYltUHylJwvyaE();

    private /* synthetic */ $$Lambda$MlisHttpClient$Qd_euoQ4L8qNydYltUHylJwvyaE() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        String string;
        string = ((JSONObject) obj).getString("id");
        return string;
    }
}
