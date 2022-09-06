package io.ktor.http;

import com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider;
import com.amazon.alexa.fitness.metrics.MetricsOperation;
import com.amazon.alexa.redesign.utils.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HttpMethod.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lio/ktor/http/HttpMethod;", "", "value", "", "(Ljava/lang/String;)V", "getValue", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpMethod {
    @NotNull
    private static final List<HttpMethod> DefaultMethods;
    @NotNull
    private final String value;
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final HttpMethod Get = new HttpMethod("GET");
    @NotNull
    private static final HttpMethod Post = new HttpMethod("POST");
    @NotNull
    private static final HttpMethod Put = new HttpMethod(SmartDeviceDataProvider.METHOD_HTTP_PUT);
    @NotNull
    private static final HttpMethod Patch = new HttpMethod("PATCH");
    @NotNull
    private static final HttpMethod Delete = new HttpMethod(Constants.REQUEST_METHOD_DELETE);
    @NotNull
    private static final HttpMethod Head = new HttpMethod("HEAD");
    @NotNull
    private static final HttpMethod Options = new HttpMethod("OPTIONS");

    /* compiled from: HttpMethod.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0019R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nR\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\nR\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\nR\u0011\u0010\u0013\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\nR\u0011\u0010\u0015\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\n¨\u0006\u001a"}, d2 = {"Lio/ktor/http/HttpMethod$Companion;", "", "()V", "DefaultMethods", "", "Lio/ktor/http/HttpMethod;", "getDefaultMethods", "()Ljava/util/List;", "Delete", "getDelete", "()Lio/ktor/http/HttpMethod;", MetricsOperation.GET, "getGet", "Head", "getHead", "Options", "getOptions", "Patch", "getPatch", "Post", "getPost", "Put", "getPut", "parse", MetricsConstants.NativeFetch.METHOD, "", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final List<HttpMethod> getDefaultMethods() {
            return HttpMethod.DefaultMethods;
        }

        @NotNull
        public final HttpMethod getDelete() {
            return HttpMethod.Delete;
        }

        @NotNull
        public final HttpMethod getGet() {
            return HttpMethod.Get;
        }

        @NotNull
        public final HttpMethod getHead() {
            return HttpMethod.Head;
        }

        @NotNull
        public final HttpMethod getOptions() {
            return HttpMethod.Options;
        }

        @NotNull
        public final HttpMethod getPatch() {
            return HttpMethod.Patch;
        }

        @NotNull
        public final HttpMethod getPost() {
            return HttpMethod.Post;
        }

        @NotNull
        public final HttpMethod getPut() {
            return HttpMethod.Put;
        }

        @NotNull
        public final HttpMethod parse(@NotNull String method) {
            Intrinsics.checkParameterIsNotNull(method, "method");
            return Intrinsics.areEqual(method, getGet().getValue()) ? getGet() : Intrinsics.areEqual(method, getPost().getValue()) ? getPost() : Intrinsics.areEqual(method, getPut().getValue()) ? getPut() : Intrinsics.areEqual(method, getPatch().getValue()) ? getPatch() : Intrinsics.areEqual(method, getDelete().getValue()) ? getDelete() : Intrinsics.areEqual(method, getHead().getValue()) ? getHead() : Intrinsics.areEqual(method, getOptions().getValue()) ? getOptions() : new HttpMethod(method);
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        List<HttpMethod> listOf;
        listOf = CollectionsKt__CollectionsKt.listOf((Object[]) new HttpMethod[]{Get, Post, Put, Patch, Delete, Head, Options});
        DefaultMethods = listOf;
    }

    public HttpMethod(@NotNull String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        this.value = value;
    }

    @NotNull
    public static /* synthetic */ HttpMethod copy$default(HttpMethod httpMethod, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = httpMethod.value;
        }
        return httpMethod.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.value;
    }

    @NotNull
    public final HttpMethod copy(@NotNull String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        return new HttpMethod(value);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof HttpMethod) && Intrinsics.areEqual(this.value, ((HttpMethod) obj).value);
        }
        return true;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }

    public int hashCode() {
        String str = this.value;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("HttpMethod(value="), this.value, ")");
    }
}
