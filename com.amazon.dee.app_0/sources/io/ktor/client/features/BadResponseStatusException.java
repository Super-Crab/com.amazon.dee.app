package io.ktor.client.features;

import io.ktor.client.response.HttpResponse;
import io.ktor.http.HttpStatusCode;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: ExpectSuccess.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/client/features/BadResponseStatusException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "statusCode", "Lio/ktor/http/HttpStatusCode;", "response", "Lio/ktor/client/response/HttpResponse;", "(Lio/ktor/http/HttpStatusCode;Lio/ktor/client/response/HttpResponse;)V", "getResponse", "()Lio/ktor/client/response/HttpResponse;", "getStatusCode", "()Lio/ktor/http/HttpStatusCode;", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class BadResponseStatusException extends IllegalStateException {
    @NotNull
    private final HttpResponse response;
    @NotNull
    private final HttpStatusCode statusCode;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BadResponseStatusException(@NotNull HttpStatusCode statusCode, @NotNull HttpResponse response) {
        super("Received bad status code: " + statusCode + ". Expected status code < 300.");
        Intrinsics.checkParameterIsNotNull(statusCode, "statusCode");
        Intrinsics.checkParameterIsNotNull(response, "response");
        this.statusCode = statusCode;
        this.response = response;
    }

    @NotNull
    public final HttpResponse getResponse() {
        return this.response;
    }

    @NotNull
    public final HttpStatusCode getStatusCode() {
        return this.statusCode;
    }
}
