package io.ktor.client.request;

import androidx.core.app.FrameMetricsAggregator;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.dee.app.metrics.MetricsConstants;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.ktor.client.utils.EmptyContent;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpMessageBuilder;
import io.ktor.http.HttpMethod;
import io.ktor.http.URLBuilder;
import io.ktor.http.URLUtilsKt;
import io.ktor.http.Url;
import io.ktor.util.Attributes;
import io.ktor.util.AttributesJvmKt;
import io.ktor.util.KtorExperimentalAPI;
import io.ktor.util.StringValuesKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 (2\u00020\u0001:\u0001(B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010!\u001a\u00020\"J\u001f\u0010#\u001a\u00020\u00062\u0017\u0010$\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007J\u000e\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0000J%\u0010\u001d\u001a\u00020\u00062\u001d\u0010$\u001a\u0019\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00060'¢\u0006\u0002\b\u0007R\u001f\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u00020\u000f8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0002\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u0006)"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder;", "Lio/ktor/http/HttpMessageBuilder;", "()V", "attributesBuilder", "Lkotlin/Function1;", "Lio/ktor/util/Attributes;", "", "Lkotlin/ExtensionFunctionType;", "body", "", "getBody", "()Ljava/lang/Object;", "setBody", "(Ljava/lang/Object;)V", "executionContext", "Lkotlinx/coroutines/Job;", "executionContext$annotations", "getExecutionContext", "()Lkotlinx/coroutines/Job;", HttpClientModule.ElementsRequestKey.HEADERS, "Lio/ktor/http/HeadersBuilder;", "getHeaders", "()Lio/ktor/http/HeadersBuilder;", MetricsConstants.NativeFetch.METHOD, "Lio/ktor/http/HttpMethod;", "getMethod", "()Lio/ktor/http/HttpMethod;", "setMethod", "(Lio/ktor/http/HttpMethod;)V", "url", "Lio/ktor/http/URLBuilder;", "getUrl", "()Lio/ktor/http/URLBuilder;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lio/ktor/client/request/HttpRequestData;", "setAttributes", "block", "takeFrom", "builder", "Lkotlin/Function2;", "Companion", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpRequestBuilder implements HttpMessageBuilder {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final URLBuilder url = new URLBuilder(null, null, 0, null, null, null, null, null, false, FrameMetricsAggregator.EVERY_DURATION, null);
    @NotNull
    private HttpMethod method = HttpMethod.Companion.getGet();
    @NotNull
    private final HeadersBuilder headers = new HeadersBuilder(0, 1, null);
    @NotNull
    private Object body = EmptyContent.INSTANCE;
    @NotNull
    private final Job executionContext = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
    private Function1<? super Attributes, Unit> attributesBuilder = HttpRequestBuilder$attributesBuilder$1.INSTANCE;

    /* compiled from: HttpRequest.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/client/request/HttpRequestBuilder$Companion;", "", "()V", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @KtorExperimentalAPI
    public static /* synthetic */ void executionContext$annotations() {
    }

    @NotNull
    public final HttpRequestData build() {
        Url build = this.url.build();
        HttpMethod httpMethod = this.method;
        Headers mo10292build = getHeaders().mo10292build();
        Object obj = this.body;
        Job job = this.executionContext;
        Attributes Attributes$default = AttributesJvmKt.Attributes$default(false, 1, null);
        this.attributesBuilder.mo12165invoke(Attributes$default);
        return new HttpRequestData(build, httpMethod, mo10292build, obj, job, Attributes$default);
    }

    @NotNull
    public final Object getBody() {
        return this.body;
    }

    @NotNull
    public final Job getExecutionContext() {
        return this.executionContext;
    }

    @Override // io.ktor.http.HttpMessageBuilder
    @NotNull
    public HeadersBuilder getHeaders() {
        return this.headers;
    }

    @NotNull
    public final HttpMethod getMethod() {
        return this.method;
    }

    @NotNull
    public final URLBuilder getUrl() {
        return this.url;
    }

    public final void setAttributes(@NotNull Function1<? super Attributes, Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        this.attributesBuilder = new HttpRequestBuilder$setAttributes$1(this.attributesBuilder, block);
    }

    public final void setBody(@NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "<set-?>");
        this.body = obj;
    }

    public final void setMethod(@NotNull HttpMethod httpMethod) {
        Intrinsics.checkParameterIsNotNull(httpMethod, "<set-?>");
        this.method = httpMethod;
    }

    @NotNull
    public final HttpRequestBuilder takeFrom(@NotNull HttpRequestBuilder builder) {
        Intrinsics.checkParameterIsNotNull(builder, "builder");
        this.method = builder.method;
        this.body = builder.body;
        URLUtilsKt.takeFrom(this.url, builder.url);
        StringValuesKt.appendAll(getHeaders(), builder.getHeaders());
        this.attributesBuilder = builder.attributesBuilder;
        return this;
    }

    public final void url(@NotNull Function2<? super URLBuilder, ? super URLBuilder, Unit> block) {
        Intrinsics.checkParameterIsNotNull(block, "block");
        URLBuilder uRLBuilder = this.url;
        block.mo12248invoke(uRLBuilder, uRLBuilder);
    }
}
