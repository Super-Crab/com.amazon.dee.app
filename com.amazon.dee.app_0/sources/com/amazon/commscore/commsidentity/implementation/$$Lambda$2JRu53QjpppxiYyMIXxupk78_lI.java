package com.amazon.commscore.commsidentity.implementation;

import androidx.core.util.Pair;
import com.amazon.commscore.commsidentity.repo.model.AccountForDirectedId;
import com.amazon.commscore.commsidentity.repo.model.IdentityV2;
import io.reactivex.rxjava3.functions.BiFunction;
/* compiled from: lambda */
/* renamed from: com.amazon.commscore.commsidentity.implementation.-$$Lambda$2JRu53QjpppxiYyMIXxupk78_lI  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$2JRu53QjpppxiYyMIXxupk78_lI implements BiFunction {
    public static final /* synthetic */ $$Lambda$2JRu53QjpppxiYyMIXxupk78_lI INSTANCE = new $$Lambda$2JRu53QjpppxiYyMIXxupk78_lI();

    private /* synthetic */ $$Lambda$2JRu53QjpppxiYyMIXxupk78_lI() {
    }

    @Override // io.reactivex.rxjava3.functions.BiFunction
    public final Object apply(Object obj, Object obj2) {
        return Pair.create((IdentityV2) obj, (AccountForDirectedId) obj2);
    }
}
