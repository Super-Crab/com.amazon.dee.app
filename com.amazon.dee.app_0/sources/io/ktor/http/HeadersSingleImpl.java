package io.ktor.http;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.util.InternalAPI;
import io.ktor.util.StringValuesSingleImpl;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Headers.kt */
@InternalAPI
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\u0004H\u0016¨\u0006\t"}, d2 = {"Lio/ktor/http/HeadersSingleImpl;", "Lio/ktor/http/Headers;", "Lio/ktor/util/StringValuesSingleImpl;", "name", "", "values", "", "(Ljava/lang/String;Ljava/util/List;)V", "toString", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HeadersSingleImpl extends StringValuesSingleImpl implements Headers {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeadersSingleImpl(@NotNull String name, @NotNull List<String> values) {
        super(true, name, values);
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(values, "values");
    }

    @Override // io.ktor.util.StringValuesSingleImpl
    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Headers ");
        outline107.append(entries());
        return outline107.toString();
    }
}
