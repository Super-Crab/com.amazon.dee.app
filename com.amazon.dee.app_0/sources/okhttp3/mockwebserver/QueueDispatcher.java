package okhttp3.mockwebserver;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: QueueDispatcher.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0016\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\b\u0010\u000f\u001a\u00020\u0004H\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0012\u0010\u0010\u001a\u00020\r2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u0013\u001a\u00020\rH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0015"}, d2 = {"Lokhttp3/mockwebserver/QueueDispatcher;", "Lokhttp3/mockwebserver/Dispatcher;", "()V", "failFastResponse", "Lokhttp3/mockwebserver/MockResponse;", "responseQueue", "Ljava/util/concurrent/BlockingQueue;", "getResponseQueue", "()Ljava/util/concurrent/BlockingQueue;", "dispatch", "request", "Lokhttp3/mockwebserver/RecordedRequest;", "enqueueResponse", "", "response", "peek", "setFailFast", "failFast", "", "shutdown", "Companion", "mockwebserver"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public class QueueDispatcher extends Dispatcher {
    public static final Companion Companion = new Companion(null);
    private static final MockResponse DEAD_LETTER;
    private static final Logger logger;
    private MockResponse failFastResponse;
    @NotNull
    private final BlockingQueue<MockResponse> responseQueue = new LinkedBlockingQueue();

    /* compiled from: QueueDispatcher.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lokhttp3/mockwebserver/QueueDispatcher$Companion;", "", "()V", "DEAD_LETTER", "Lokhttp3/mockwebserver/MockResponse;", "logger", "Ljava/util/logging/Logger;", "kotlin.jvm.PlatformType", "mockwebserver"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        MockResponse mockResponse = new MockResponse();
        mockResponse.status("HTTP/1.1 503 shutting down");
        DEAD_LETTER = mockResponse;
        logger = Logger.getLogger(QueueDispatcher.class.getName());
    }

    @Override // okhttp3.mockwebserver.Dispatcher
    @NotNull
    public MockResponse dispatch(@NotNull RecordedRequest request) throws InterruptedException {
        Intrinsics.checkParameterIsNotNull(request, "request");
        String requestLine = request.getRequestLine();
        if (Intrinsics.areEqual(requestLine, "GET /favicon.ico HTTP/1.1")) {
            Logger logger2 = logger;
            logger2.info("served " + requestLine);
            return new MockResponse().setResponseCode(404);
        } else if (this.failFastResponse != null && this.responseQueue.peek() == null) {
            MockResponse mockResponse = this.failFastResponse;
            if (mockResponse == null) {
                Intrinsics.throwNpe();
            }
            return mockResponse;
        } else {
            MockResponse result = this.responseQueue.take();
            if (Intrinsics.areEqual(result, DEAD_LETTER)) {
                this.responseQueue.add(DEAD_LETTER);
            }
            Intrinsics.checkExpressionValueIsNotNull(result, "result");
            return result;
        }
    }

    public void enqueueResponse(@NotNull MockResponse response) {
        Intrinsics.checkParameterIsNotNull(response, "response");
        this.responseQueue.add(response);
    }

    @NotNull
    protected final BlockingQueue<MockResponse> getResponseQueue() {
        return this.responseQueue;
    }

    @Override // okhttp3.mockwebserver.Dispatcher
    @NotNull
    public MockResponse peek() {
        MockResponse peek = this.responseQueue.peek();
        if (peek == null) {
            peek = this.failFastResponse;
        }
        return peek != null ? peek : super.peek();
    }

    public void setFailFast(boolean z) {
        setFailFast(z ? new MockResponse().setResponseCode(404) : null);
    }

    @Override // okhttp3.mockwebserver.Dispatcher
    public void shutdown() {
        this.responseQueue.add(DEAD_LETTER);
    }

    public void setFailFast(@Nullable MockResponse mockResponse) {
        this.failFastResponse = mockResponse;
    }
}
