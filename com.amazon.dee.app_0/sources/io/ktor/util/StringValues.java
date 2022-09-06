package io.ktor.util;

import com.dee.app.metrics.MetricsConstants;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: StringValues.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016J\u0011\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0096\u0002J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J \u0010\n\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\r0\f0\u000bH&J(\u0010\u000e\u001a\u00020\u000f2\u001e\u0010\u0010\u001a\u001a\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\r\u0012\u0004\u0012\u00020\u000f0\u0011H\u0016J\u0013\u0010\u0012\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\bH\u0096\u0002J\u0018\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\r2\u0006\u0010\u0007\u001a\u00020\bH&J\b\u0010\u0014\u001a\u00020\u0003H&J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0017"}, d2 = {"Lio/ktor/util/StringValues;", "", "caseInsensitiveName", "", "getCaseInsensitiveName", "()Z", "contains", "name", "", "value", "entries", "", "", "", "forEach", "", "body", "Lkotlin/Function2;", MetricsConstants.Method.CACHE_GET, "getAll", "isEmpty", "names", "Companion", "ktor-utils"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public interface StringValues {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: StringValues.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J,\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\t2\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0086\bR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lio/ktor/util/StringValues$Companion;", "", "()V", "Empty", "Lio/ktor/util/StringValues;", "getEmpty", "()Lio/ktor/util/StringValues;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "caseInsensitiveName", "", "builder", "Lkotlin/Function1;", "Lio/ktor/util/StringValuesBuilder;", "", "Lkotlin/ExtensionFunctionType;", "ktor-utils"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        @NotNull
        private static final StringValues Empty = new StringValuesImpl(false, null, 3, null);

        private Companion() {
        }

        @NotNull
        public static /* synthetic */ StringValues build$default(Companion companion, boolean z, Function1 builder, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            Intrinsics.checkParameterIsNotNull(builder, "builder");
            StringValuesBuilder stringValuesBuilder = new StringValuesBuilder(z, 0, 2, null);
            builder.mo12165invoke(stringValuesBuilder);
            return stringValuesBuilder.mo10292build();
        }

        @NotNull
        public final StringValues build(boolean z, @NotNull Function1<? super StringValuesBuilder, Unit> builder) {
            Intrinsics.checkParameterIsNotNull(builder, "builder");
            StringValuesBuilder stringValuesBuilder = new StringValuesBuilder(z, 0, 2, null);
            builder.mo12165invoke(stringValuesBuilder);
            return stringValuesBuilder.mo10292build();
        }

        @NotNull
        public final StringValues getEmpty() {
            return Empty;
        }
    }

    /* compiled from: StringValues.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static boolean contains(StringValues stringValues, @NotNull String name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            return stringValues.getAll(name) != null;
        }

        public static void forEach(StringValues stringValues, @NotNull Function2<? super String, ? super List<String>, Unit> body) {
            Intrinsics.checkParameterIsNotNull(body, "body");
            Iterator<T> it2 = stringValues.entries().iterator();
            while (it2.hasNext()) {
                Map.Entry entry = (Map.Entry) it2.next();
                body.mo12248invoke((String) entry.getKey(), (List) entry.getValue());
            }
        }

        @Nullable
        public static String get(StringValues stringValues, @NotNull String name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            List<String> all = stringValues.getAll(name);
            if (all != null) {
                return (String) kotlin.collections.CollectionsKt.firstOrNull((List<? extends Object>) all);
            }
            return null;
        }

        public static boolean contains(StringValues stringValues, @NotNull String name, @NotNull String value) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(value, "value");
            List<String> all = stringValues.getAll(name);
            if (all != null) {
                return all.contains(value);
            }
            return false;
        }
    }

    boolean contains(@NotNull String str);

    boolean contains(@NotNull String str, @NotNull String str2);

    @NotNull
    Set<Map.Entry<String, List<String>>> entries();

    void forEach(@NotNull Function2<? super String, ? super List<String>, Unit> function2);

    @Nullable
    String get(@NotNull String str);

    @Nullable
    List<String> getAll(@NotNull String str);

    boolean getCaseInsensitiveName();

    boolean isEmpty();

    @NotNull
    Set<String> names();
}
