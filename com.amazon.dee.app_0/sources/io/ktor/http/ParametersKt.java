package io.ktor.http;

import io.ktor.http.Parameters;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: Parameters.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0007\u001a\u0006\u0010\u0000\u001a\u00020\u0001\u001aC\u0010\u0000\u001a\u00020\u000126\u0010\u0002\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u00040\u0003\"\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u0004¢\u0006\u0002\u0010\u0007\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005\u001a\u001c\u0010\u0000\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u00052\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0006\u001a\u0015\u0010\u000b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001a\u00020\u0001H\u0086\u0002¨\u0006\r"}, d2 = {"parametersOf", "Lio/ktor/http/Parameters;", "pairs", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Lio/ktor/http/Parameters;", "name", "value", "values", "plus", "other", "ktor-http"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class ParametersKt {
    @NotNull
    public static final Parameters parametersOf() {
        return Parameters.Companion.getEmpty();
    }

    @NotNull
    public static final Parameters plus(@NotNull Parameters receiver$0, @NotNull Parameters other) {
        Intrinsics.checkParameterIsNotNull(receiver$0, "receiver$0");
        Intrinsics.checkParameterIsNotNull(other, "other");
        if (receiver$0.getCaseInsensitiveName() == other.getCaseInsensitiveName()) {
            if (receiver$0.isEmpty()) {
                return other;
            }
            if (other.isEmpty()) {
                return receiver$0;
            }
            Parameters.Companion companion = Parameters.Companion;
            ParametersBuilder parametersBuilder = new ParametersBuilder(0, 1, null);
            parametersBuilder.appendAll(receiver$0);
            parametersBuilder.appendAll(other);
            return parametersBuilder.mo10292build();
        }
        throw new IllegalArgumentException("Cannot concatenate Parameters with case-sensitive and case-insensitive names");
    }

    @NotNull
    public static final Parameters parametersOf(@NotNull String name, @NotNull String value) {
        List listOf;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(value, "value");
        listOf = CollectionsKt__CollectionsJVMKt.listOf(value);
        return new ParametersSingleImpl(name, listOf);
    }

    @NotNull
    public static final Parameters parametersOf(@NotNull String name, @NotNull List<String> values) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(values, "values");
        return new ParametersSingleImpl(name, values);
    }

    @NotNull
    public static final Parameters parametersOf(@NotNull Pair<String, ? extends List<String>>... pairs) {
        List asList;
        Map map;
        Intrinsics.checkParameterIsNotNull(pairs, "pairs");
        asList = ArraysKt___ArraysJvmKt.asList(pairs);
        map = MapsKt__MapsKt.toMap(asList);
        return new ParametersImpl(map);
    }
}
