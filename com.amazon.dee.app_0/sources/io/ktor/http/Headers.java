package io.ktor.http;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.ktor.util.StringValues;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Headers.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/http/Headers;", "Lio/ktor/util/StringValues;", "Companion", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public interface Headers extends StringValues {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* compiled from: Headers.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\b\u001a\u00020\u00042\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\rH\u0086\bR\u0017\u0010\u0003\u001a\u00020\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"Lio/ktor/http/Headers$Companion;", "", "()V", "Empty", "Lio/ktor/http/Headers;", "Empty$annotations", "getEmpty", "()Lio/ktor/http/Headers;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "builder", "Lkotlin/Function1;", "Lio/ktor/http/HeadersBuilder;", "", "Lkotlin/ExtensionFunctionType;", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        @NotNull
        private static final Headers Empty = EmptyHeaders.INSTANCE;

        private Companion() {
        }

        public static /* synthetic */ void Empty$annotations() {
        }

        @NotNull
        public final Headers build(@NotNull Function1<? super HeadersBuilder, Unit> builder) {
            Intrinsics.checkParameterIsNotNull(builder, "builder");
            HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, null);
            builder.mo12165invoke(headersBuilder);
            return headersBuilder.mo10292build();
        }

        @NotNull
        public final Headers getEmpty() {
            return Empty;
        }
    }

    /* compiled from: Headers.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static boolean contains(Headers headers, @NotNull String name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            return StringValues.DefaultImpls.contains(headers, name);
        }

        public static boolean contains(Headers headers, @NotNull String name, @NotNull String value) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(value, "value");
            return StringValues.DefaultImpls.contains(headers, name, value);
        }

        public static void forEach(Headers headers, @NotNull Function2<? super String, ? super List<String>, Unit> body) {
            Intrinsics.checkParameterIsNotNull(body, "body");
            StringValues.DefaultImpls.forEach(headers, body);
        }

        @Nullable
        public static String get(Headers headers, @NotNull String name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            return StringValues.DefaultImpls.get(headers, name);
        }
    }
}
