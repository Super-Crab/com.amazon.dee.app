package io.ktor.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt__SetsJVMKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: StringValues.kt */
@InternalAPI
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\u0002\u0010\bJ\u0011\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0096\u0002J\u0018\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0016J \u0010\u0011\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00070\u00130\u0012H\u0016J\u0013\u0010\u0014\u001a\u00020\u00032\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0002J(\u0010\u0017\u001a\u00020\u00182\u001e\u0010\u0019\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0007\u0012\u0004\u0012\u00020\u00180\u001aH\u0016J\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0096\u0002J\u0018\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00072\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0003H\u0016J\u000e\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00050\u0012H\u0016J\b\u0010!\u001a\u00020\u0005H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\""}, d2 = {"Lio/ktor/util/StringValuesSingleImpl;", "Lio/ktor/util/StringValues;", "caseInsensitiveName", "", "name", "", "values", "", "(ZLjava/lang/String;Ljava/util/List;)V", "getCaseInsensitiveName", "()Z", "getName", "()Ljava/lang/String;", "getValues", "()Ljava/util/List;", "contains", "value", "entries", "", "", "equals", "other", "", "forEach", "", "body", "Lkotlin/Function2;", MetricsConstants.Method.CACHE_GET, "getAll", "hashCode", "", "isEmpty", "names", "toString", "ktor-utils"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public class StringValuesSingleImpl implements StringValues {
    private final boolean caseInsensitiveName;
    @NotNull
    private final String name;
    @NotNull
    private final List<String> values;

    public StringValuesSingleImpl(boolean z, @NotNull String name, @NotNull List<String> values) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(values, "values");
        this.caseInsensitiveName = z;
        this.name = name;
        this.values = values;
    }

    @Override // io.ktor.util.StringValues
    public boolean contains(@NotNull String name) {
        boolean equals;
        Intrinsics.checkParameterIsNotNull(name, "name");
        equals = StringsKt__StringsJVMKt.equals(name, this.name, getCaseInsensitiveName());
        return equals;
    }

    @Override // io.ktor.util.StringValues
    @NotNull
    public Set<Map.Entry<String, List<String>>> entries() {
        Set<Map.Entry<String, List<String>>> of;
        of = SetsKt__SetsJVMKt.setOf(new StringValuesSingleImpl$entries$1(this));
        return of;
    }

    public boolean equals(@Nullable Object obj) {
        boolean entriesEquals;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StringValues)) {
            return false;
        }
        StringValues stringValues = (StringValues) obj;
        if (getCaseInsensitiveName() != stringValues.getCaseInsensitiveName()) {
            return false;
        }
        entriesEquals = StringValuesKt.entriesEquals(entries(), stringValues.entries());
        return entriesEquals;
    }

    @Override // io.ktor.util.StringValues
    public void forEach(@NotNull Function2<? super String, ? super List<String>, Unit> body) {
        Intrinsics.checkParameterIsNotNull(body, "body");
        body.mo12248invoke(this.name, this.values);
    }

    @Override // io.ktor.util.StringValues
    @Nullable
    public String get(@NotNull String name) {
        boolean equals;
        Intrinsics.checkParameterIsNotNull(name, "name");
        equals = StringsKt__StringsJVMKt.equals(name, this.name, getCaseInsensitiveName());
        if (equals) {
            return (String) kotlin.collections.CollectionsKt.firstOrNull((List<? extends Object>) this.values);
        }
        return null;
    }

    @Override // io.ktor.util.StringValues
    @Nullable
    public List<String> getAll(@NotNull String name) {
        boolean equals;
        Intrinsics.checkParameterIsNotNull(name, "name");
        equals = StringsKt__StringsJVMKt.equals(this.name, name, getCaseInsensitiveName());
        if (equals) {
            return this.values;
        }
        return null;
    }

    @Override // io.ktor.util.StringValues
    public boolean getCaseInsensitiveName() {
        return this.caseInsensitiveName;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final List<String> getValues() {
        return this.values;
    }

    public int hashCode() {
        int entriesHashCode;
        entriesHashCode = StringValuesKt.entriesHashCode(entries(), Boolean.valueOf(getCaseInsensitiveName()).hashCode() * 31);
        return entriesHashCode;
    }

    @Override // io.ktor.util.StringValues
    public boolean isEmpty() {
        return false;
    }

    @Override // io.ktor.util.StringValues
    @NotNull
    public Set<String> names() {
        Set<String> of;
        of = SetsKt__SetsJVMKt.setOf(this.name);
        return of;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("StringValues(case=");
        outline107.append(!getCaseInsensitiveName());
        outline107.append(") ");
        outline107.append(entries());
        return outline107.toString();
    }

    @Override // io.ktor.util.StringValues
    public boolean contains(@NotNull String name, @NotNull String value) {
        boolean equals;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(value, "value");
        equals = StringsKt__StringsJVMKt.equals(name, this.name, getCaseInsensitiveName());
        return equals && this.values.contains(value);
    }
}
