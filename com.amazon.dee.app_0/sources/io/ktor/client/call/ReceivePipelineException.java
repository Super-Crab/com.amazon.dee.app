package io.ktor.client.call;

import com.amazon.alexa.mobilytics.event.LogLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpClientCall.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\b\u0018\u00002\u00060\u0001j\u0002`\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lio/ktor/client/call/ReceivePipelineException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "request", "Lio/ktor/client/call/HttpClientCall;", LogLevel.INFO, "Lio/ktor/client/call/TypeInfo;", "cause", "", "(Lio/ktor/client/call/HttpClientCall;Lio/ktor/client/call/TypeInfo;Ljava/lang/Throwable;)V", "getCause", "()Ljava/lang/Throwable;", "getInfo", "()Lio/ktor/client/call/TypeInfo;", "getRequest", "()Lio/ktor/client/call/HttpClientCall;", "ktor-client-core"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class ReceivePipelineException extends IllegalStateException {
    @NotNull
    private final Throwable cause;
    @NotNull
    private final TypeInfo info;
    @NotNull
    private final HttpClientCall request;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReceivePipelineException(@NotNull HttpClientCall request, @NotNull TypeInfo info, @NotNull Throwable cause) {
        super("Fail to run receive pipeline: " + cause);
        Intrinsics.checkParameterIsNotNull(request, "request");
        Intrinsics.checkParameterIsNotNull(info, "info");
        Intrinsics.checkParameterIsNotNull(cause, "cause");
        this.request = request;
        this.info = info;
        this.cause = cause;
    }

    @Override // java.lang.Throwable
    @NotNull
    public Throwable getCause() {
        return this.cause;
    }

    @NotNull
    public final TypeInfo getInfo() {
        return this.info;
    }

    @NotNull
    public final HttpClientCall getRequest() {
        return this.request;
    }
}
