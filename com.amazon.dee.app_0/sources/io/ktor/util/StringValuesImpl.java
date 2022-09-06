package io.ktor.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: StringValues.kt */
@InternalAPI
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b\u0017\u0018\u00002\u00020\u0001B+\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u001a\b\u0002\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00070\u0005¢\u0006\u0002\u0010\bJ\u0011\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0006H\u0096\u0002J\u0018\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006H\u0016J \u0010\u0012\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00070\u00140\u0013H\u0016J\u0013\u0010\u0015\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0096\u0002J(\u0010\u0018\u001a\u00020\u00192\u001e\u0010\u001a\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0007\u0012\u0004\u0012\u00020\u00190\u001bH\u0016J\u0013\u0010\u001c\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0010\u001a\u00020\u0006H\u0096\u0002J\u0018\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00072\u0006\u0010\u0010\u001a\u00020\u0006H\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u0003H\u0016J\u0018\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00072\u0006\u0010\u0010\u001a\u00020\u0006H\u0002J\u000e\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00060\u0013H\u0016J\b\u0010#\u001a\u00020\u0006H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR-\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00070\u00058DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006$"}, d2 = {"Lio/ktor/util/StringValuesImpl;", "Lio/ktor/util/StringValues;", "caseInsensitiveName", "", "values", "", "", "", "(ZLjava/util/Map;)V", "getCaseInsensitiveName", "()Z", "getValues", "()Ljava/util/Map;", "values$delegate", "Lkotlin/Lazy;", "contains", "name", "value", "entries", "", "", "equals", "other", "", "forEach", "", "body", "Lkotlin/Function2;", MetricsConstants.Method.CACHE_GET, "getAll", "hashCode", "", "isEmpty", "listForKey", "names", "toString", "ktor-utils"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public class StringValuesImpl implements StringValues {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(StringValuesImpl.class), "values", "getValues()Ljava/util/Map;"))};
    private final boolean caseInsensitiveName;
    @NotNull
    private final Lazy values$delegate;

    public StringValuesImpl() {
        this(false, null, 3, null);
    }

    public StringValuesImpl(boolean z, @NotNull Map<String, ? extends List<String>> values) {
        Lazy lazy;
        Intrinsics.checkParameterIsNotNull(values, "values");
        this.caseInsensitiveName = z;
        lazy = LazyKt__LazyJVMKt.lazy(new StringValuesImpl$values$2(this, values));
        this.values$delegate = lazy;
    }

    private final List<String> listForKey(String str) {
        return getValues().get(str);
    }

    @Override // io.ktor.util.StringValues
    public boolean contains(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return listForKey(name) != null;
    }

    @Override // io.ktor.util.StringValues
    @NotNull
    public Set<Map.Entry<String, List<String>>> entries() {
        return CollectionsJvmKt.unmodifiable(getValues().entrySet());
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StringValues)) {
            return false;
        }
        StringValues stringValues = (StringValues) obj;
        if (getCaseInsensitiveName() == stringValues.getCaseInsensitiveName()) {
            return StringValuesKt.access$entriesEquals(entries(), stringValues.entries());
        }
        return false;
    }

    @Override // io.ktor.util.StringValues
    public void forEach(@NotNull Function2<? super String, ? super List<String>, Unit> body) {
        Intrinsics.checkParameterIsNotNull(body, "body");
        for (Map.Entry<String, List<String>> entry : getValues().entrySet()) {
            body.mo12248invoke(entry.getKey(), entry.getValue());
        }
    }

    @Override // io.ktor.util.StringValues
    @Nullable
    public String get(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        List<String> listForKey = listForKey(name);
        if (listForKey != null) {
            return (String) kotlin.collections.CollectionsKt.firstOrNull((List<? extends Object>) listForKey);
        }
        return null;
    }

    @Override // io.ktor.util.StringValues
    @Nullable
    public List<String> getAll(@NotNull String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return listForKey(name);
    }

    @Override // io.ktor.util.StringValues
    public boolean getCaseInsensitiveName() {
        return this.caseInsensitiveName;
    }

    @NotNull
    protected final Map<String, List<String>> getValues() {
        Lazy lazy = this.values$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (Map) lazy.getValue();
    }

    public int hashCode() {
        return StringValuesKt.access$entriesHashCode(entries(), Boolean.valueOf(getCaseInsensitiveName()).hashCode() * 31);
    }

    @Override // io.ktor.util.StringValues
    public boolean isEmpty() {
        return getValues().isEmpty();
    }

    @Override // io.ktor.util.StringValues
    @NotNull
    public Set<String> names() {
        return CollectionsJvmKt.unmodifiable(getValues().keySet());
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
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(value, "value");
        List<String> listForKey = listForKey(name);
        if (listForKey != null) {
            return listForKey.contains(value);
        }
        return false;
    }

    public /* synthetic */ StringValuesImpl(boolean z, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? MapsKt__MapsKt.emptyMap() : map);
    }
}
