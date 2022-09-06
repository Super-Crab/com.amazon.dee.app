package io.ktor.util;

import com.amazon.identity.auth.map.device.token.MAPCookie;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: CaseInsensitiveMap.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", MAPCookie.KEY_VALUE, "Lio/ktor/util/CaseInsensitiveString;", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class CaseInsensitiveMap$keys$1 extends Lambda implements Function1<CaseInsensitiveString, String> {
    public static final CaseInsensitiveMap$keys$1 INSTANCE = new CaseInsensitiveMap$keys$1();

    CaseInsensitiveMap$keys$1() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final String mo12165invoke(@NotNull CaseInsensitiveString receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return receiver$0.getContent();
    }
}
