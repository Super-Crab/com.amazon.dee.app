package com.amazon.commscore.commsidentity.repo.repository;

import androidx.core.util.Pair;
import com.amazon.commscore.commsidentity.remote.model.AccountForDirectedId;
import com.amazon.commscore.commsidentity.remote.model.IdentityV2;
import io.reactivex.rxjava3.functions.BiFunction;
/* compiled from: lambda */
/* renamed from: com.amazon.commscore.commsidentity.repo.repository.-$$Lambda$HXLOmC9t7mZ2fqK3vqFuMkyVfRQ  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$HXLOmC9t7mZ2fqK3vqFuMkyVfRQ implements BiFunction {
    public static final /* synthetic */ $$Lambda$HXLOmC9t7mZ2fqK3vqFuMkyVfRQ INSTANCE = new $$Lambda$HXLOmC9t7mZ2fqK3vqFuMkyVfRQ();

    private /* synthetic */ $$Lambda$HXLOmC9t7mZ2fqK3vqFuMkyVfRQ() {
    }

    @Override // io.reactivex.rxjava3.functions.BiFunction
    public final Object apply(Object obj, Object obj2) {
        return Pair.create((AccountForDirectedId) obj, (IdentityV2) obj2);
    }
}
