package io.ktor.http;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.util.InternalAPI;
import io.ktor.util.StringValuesImpl;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Headers.kt */
@InternalAPI
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B!\u0012\u001a\b\u0002\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u0004¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0005H\u0016¨\u0006\t"}, d2 = {"Lio/ktor/http/HeadersImpl;", "Lio/ktor/http/Headers;", "Lio/ktor/util/StringValuesImpl;", "values", "", "", "", "(Ljava/util/Map;)V", "toString", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HeadersImpl extends StringValuesImpl implements Headers {
    public HeadersImpl() {
        this(null, 1, null);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeadersImpl(@NotNull Map<String, ? extends List<String>> values) {
        super(true, values);
        Intrinsics.checkParameterIsNotNull(values, "values");
    }

    @Override // io.ktor.util.StringValuesImpl
    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Headers ");
        outline107.append(entries());
        return outline107.toString();
    }

    public /* synthetic */ HeadersImpl(Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? MapsKt.emptyMap() : map);
    }
}
