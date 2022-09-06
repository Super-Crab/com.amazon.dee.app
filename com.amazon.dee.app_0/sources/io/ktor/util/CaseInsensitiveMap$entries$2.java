package io.ktor.util;

import com.amazon.identity.auth.map.device.token.MAPCookie;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: CaseInsensitiveMap.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010'\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0003*\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u00030\u0004H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/ktor/util/Entry;", "Lio/ktor/util/CaseInsensitiveString;", MAPCookie.KEY_VALUE, "", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
final class CaseInsensitiveMap$entries$2 extends Lambda implements Function1<Map.Entry<String, Value>, Entry<CaseInsensitiveString, Value>> {
    public static final CaseInsensitiveMap$entries$2 INSTANCE = new CaseInsensitiveMap$entries$2();

    CaseInsensitiveMap$entries$2() {
        super(1);
    }

    @Override // kotlin.jvm.functions.Function1
    @NotNull
    /* renamed from: invoke */
    public final Entry<CaseInsensitiveString, Value> mo12165invoke(@NotNull Map.Entry<String, Value> receiver$0) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        return new Entry<>(TextKt.caseInsensitive(receiver$0.getKey()), receiver$0.getValue());
    }
}
