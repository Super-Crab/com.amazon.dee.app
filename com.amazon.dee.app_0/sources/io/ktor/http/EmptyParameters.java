package io.ktor.http;

import com.android.tools.r8.GeneratedOutlineSupport1;
import io.ktor.http.Parameters;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Parameters.kt */
@Deprecated(level = DeprecationLevel.ERROR, message = "Empty parameters is internal", replaceWith = @ReplaceWith(expression = "Parameters.Empty", imports = {}))
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0007\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000b0\t0\bH\u0016J\u0013\u0010\f\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J\u0018\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u000b2\u0006\u0010\u0010\u001a\u00020\nH\u0016J\b\u0010\u0011\u001a\u00020\u0004H\u0016J\u000e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\bH\u0016J\b\u0010\u0013\u001a\u00020\nH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, d2 = {"Lio/ktor/http/EmptyParameters;", "Lio/ktor/http/Parameters;", "()V", "caseInsensitiveName", "", "getCaseInsensitiveName", "()Z", "entries", "", "", "", "", "equals", "other", "", "getAll", "name", "isEmpty", "names", "toString", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class EmptyParameters implements Parameters {
    public static final EmptyParameters INSTANCE = new EmptyParameters();

    private EmptyParameters() {
    }

    @Override // io.ktor.util.StringValues
    public boolean contains(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return Parameters.DefaultImpls.contains(this, name);
    }

    @Override // io.ktor.util.StringValues
    @NotNull
    public Set<Map.Entry<String, List<String>>> entries() {
        Set<Map.Entry<String, List<String>>> emptySet;
        emptySet = SetsKt__SetsKt.emptySet();
        return emptySet;
    }

    public boolean equals(@Nullable Object obj) {
        return (obj instanceof Parameters) && ((Parameters) obj).isEmpty();
    }

    @Override // io.ktor.util.StringValues
    public void forEach(@NotNull Function2<? super String, ? super List<String>, Unit> body) {
        Intrinsics.checkParameterIsNotNull(body, "body");
        Parameters.DefaultImpls.forEach(this, body);
    }

    @Override // io.ktor.util.StringValues
    @Nullable
    public String get(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return Parameters.DefaultImpls.get(this, name);
    }

    @Override // io.ktor.util.StringValues
    @Nullable
    public List<String> getAll(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return null;
    }

    @Override // io.ktor.util.StringValues
    public boolean getCaseInsensitiveName() {
        return true;
    }

    @Override // io.ktor.util.StringValues
    public boolean isEmpty() {
        return true;
    }

    @Override // io.ktor.util.StringValues
    @NotNull
    public Set<String> names() {
        Set<String> emptySet;
        emptySet = SetsKt__SetsKt.emptySet();
        return emptySet;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Parameters ");
        outline107.append(entries());
        return outline107.toString();
    }

    @Override // io.ktor.util.StringValues
    public boolean contains(@NotNull String name, @NotNull String value) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(value, "value");
        return Parameters.DefaultImpls.contains(this, name, value);
    }
}
