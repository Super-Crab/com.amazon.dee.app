package com.amazon.alexa.accessoryclient.client.accessories;

import com.amazon.alexa.accessory.Accessory;
import io.reactivex.rxjava3.functions.Function;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ClientSessionSupplier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "", "Lcom/amazon/alexa/accessory/Accessory;", "kotlin.jvm.PlatformType", "apply"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class ClientSessionSupplier$hasActiveSessions$1<T, R> implements Function<T, R> {
    public static final ClientSessionSupplier$hasActiveSessions$1 INSTANCE = new ClientSessionSupplier$hasActiveSessions$1();

    ClientSessionSupplier$hasActiveSessions$1() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public /* bridge */ /* synthetic */ Object mo10358apply(Object obj) {
        return Boolean.valueOf(apply((List) obj));
    }

    public final boolean apply(List<Accessory> it2) {
        Intrinsics.checkExpressionValueIsNotNull(it2, "it");
        return !it2.isEmpty();
    }
}
