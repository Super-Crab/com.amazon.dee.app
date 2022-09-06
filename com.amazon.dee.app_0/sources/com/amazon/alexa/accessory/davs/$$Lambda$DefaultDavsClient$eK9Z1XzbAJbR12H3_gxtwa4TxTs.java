package com.amazon.alexa.accessory.davs;

import com.amazon.alexa.accessory.User;
import io.reactivex.rxjava3.functions.Predicate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.davs.-$$Lambda$DefaultDavsClient$eK9Z1XzbAJbR12H3_gxtwa4TxTs  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DefaultDavsClient$eK9Z1XzbAJbR12H3_gxtwa4TxTs implements Predicate {
    public static final /* synthetic */ $$Lambda$DefaultDavsClient$eK9Z1XzbAJbR12H3_gxtwa4TxTs INSTANCE = new $$Lambda$DefaultDavsClient$eK9Z1XzbAJbR12H3_gxtwa4TxTs();

    private /* synthetic */ $$Lambda$DefaultDavsClient$eK9Z1XzbAJbR12H3_gxtwa4TxTs() {
    }

    @Override // io.reactivex.rxjava3.functions.Predicate
    public final boolean test(Object obj) {
        return DefaultDavsClient.lambda$fetchDavsManifest$0((User) obj);
    }
}
