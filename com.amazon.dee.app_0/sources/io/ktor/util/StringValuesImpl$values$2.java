package io.ktor.util;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: StringValues.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0000\u0010\u0000\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "", "", "invoke"}, k = 3, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class StringValuesImpl$values$2 extends Lambda implements Function0<Map<String, ? extends List<? extends String>>> {
    final /* synthetic */ Map $values;
    final /* synthetic */ StringValuesImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StringValuesImpl$values$2(StringValuesImpl stringValuesImpl, Map map) {
        super(0);
        this.this$0 = stringValuesImpl;
        this.$values = map;
    }

    @Override // kotlin.jvm.functions.Function0
    @NotNull
    /* renamed from: invoke  reason: collision with other method in class */
    public final Map<String, ? extends List<? extends String>> mo12560invoke() {
        Map<String, ? extends List<? extends String>> map;
        if (!this.this$0.getCaseInsensitiveName()) {
            map = MapsKt__MapsKt.toMap(this.$values);
            return map;
        }
        Map<String, ? extends List<? extends String>> caseInsensitiveMap = CollectionsKt.caseInsensitiveMap();
        caseInsensitiveMap.putAll(this.$values);
        return caseInsensitiveMap;
    }
}
