package okhttp3.mockwebserver.internal.duplex;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.mockwebserver.RecordedRequest;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MockDuplexResponseBody.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\nJ\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0000J\u0006\u0010\u0014\u001a\u00020\u0000J\u0018\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\tH\u0016J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u0000J\u001a\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u001a2\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0007J\u001e\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\tH\u0002J\u0016\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%R6\u0010\u0003\u001a*\u0012&\u0012$\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000b0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lokhttp3/mockwebserver/internal/duplex/MockDuplexResponseBody;", "Lokhttp3/mockwebserver/internal/duplex/DuplexResponseBody;", "()V", EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ACTIONS, "Ljava/util/concurrent/LinkedBlockingQueue;", "Lkotlin/Function4;", "Lokhttp3/mockwebserver/RecordedRequest;", "Lokio/BufferedSource;", "Lokio/BufferedSink;", "Lokhttp3/internal/http2/Http2Stream;", "", "Lokhttp3/mockwebserver/internal/duplex/Action;", "results", "Ljava/util/concurrent/FutureTask;", "Ljava/lang/Void;", "awaitSuccess", "cancelStream", "errorCode", "Lokhttp3/internal/http2/ErrorCode;", "exhaustRequest", "exhaustResponse", "onRequest", "request", "http2Stream", "receiveRequest", "expected", "", "requestIOException", "sendResponse", "s", "responseSent", "Ljava/util/concurrent/CountDownLatch;", "serviceStreamTask", "sleep", "duration", "", "unit", "Ljava/util/concurrent/TimeUnit;", "mockwebserver"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MockDuplexResponseBody implements DuplexResponseBody {
    private final LinkedBlockingQueue<Function4<RecordedRequest, BufferedSource, BufferedSink, Http2Stream, Unit>> actions = new LinkedBlockingQueue<>();
    private final LinkedBlockingQueue<FutureTask<Void>> results = new LinkedBlockingQueue<>();

    public static /* synthetic */ MockDuplexResponseBody sendResponse$default(MockDuplexResponseBody mockDuplexResponseBody, String str, CountDownLatch countDownLatch, int i, Object obj) {
        if ((i & 2) != 0) {
            countDownLatch = new CountDownLatch(0);
        }
        return mockDuplexResponseBody.sendResponse(str, countDownLatch);
    }

    private final FutureTask<Void> serviceStreamTask(final RecordedRequest recordedRequest, final Http2Stream http2Stream) {
        return new FutureTask<>(new Callable<V>() { // from class: okhttp3.mockwebserver.internal.duplex.MockDuplexResponseBody$serviceStreamTask$1
            @Override // java.util.concurrent.Callable
            @Nullable
            public final Void call() {
                LinkedBlockingQueue linkedBlockingQueue;
                BufferedSource buffer = Okio.buffer(http2Stream.getSource());
                try {
                    BufferedSink buffer2 = Okio.buffer(http2Stream.getSink());
                    while (true) {
                        linkedBlockingQueue = MockDuplexResponseBody.this.actions;
                        Function4 function4 = (Function4) linkedBlockingQueue.poll();
                        if (function4 != null) {
                            function4.invoke(recordedRequest, buffer, buffer2, http2Stream);
                        } else {
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(buffer2, null);
                            Unit unit2 = Unit.INSTANCE;
                            CloseableKt.closeFinally(buffer, null);
                            return null;
                        }
                    }
                } finally {
                }
            }
        });
    }

    public final void awaitSuccess() {
        FutureTask<Void> poll = this.results.poll(5L, TimeUnit.SECONDS);
        if (poll != null) {
            poll.get(5L, TimeUnit.SECONDS);
            return;
        }
        throw new AssertionError("no onRequest call received");
    }

    @NotNull
    public final MockDuplexResponseBody cancelStream(@NotNull ErrorCode errorCode) {
        Intrinsics.checkParameterIsNotNull(errorCode, "errorCode");
        this.actions.add(new MockDuplexResponseBody$cancelStream$$inlined$apply$lambda$1(errorCode));
        return this;
    }

    @NotNull
    public final MockDuplexResponseBody exhaustRequest() {
        this.actions.add(MockDuplexResponseBody$exhaustRequest$1$1.INSTANCE);
        return this;
    }

    @NotNull
    public final MockDuplexResponseBody exhaustResponse() {
        this.actions.add(MockDuplexResponseBody$exhaustResponse$1$1.INSTANCE);
        return this;
    }

    @Override // okhttp3.mockwebserver.internal.duplex.DuplexResponseBody
    public void onRequest(@NotNull RecordedRequest request, @NotNull Http2Stream http2Stream) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        Intrinsics.checkParameterIsNotNull(http2Stream, "http2Stream");
        FutureTask<Void> serviceStreamTask = serviceStreamTask(request, http2Stream);
        this.results.add(serviceStreamTask);
        serviceStreamTask.run();
    }

    @NotNull
    public final MockDuplexResponseBody receiveRequest(@NotNull String expected) {
        Intrinsics.checkParameterIsNotNull(expected, "expected");
        this.actions.add(new MockDuplexResponseBody$receiveRequest$$inlined$apply$lambda$1(expected));
        return this;
    }

    @NotNull
    public final MockDuplexResponseBody requestIOException() {
        this.actions.add(MockDuplexResponseBody$requestIOException$1$1.INSTANCE);
        return this;
    }

    @JvmOverloads
    @NotNull
    public final MockDuplexResponseBody sendResponse(@NotNull String str) {
        return sendResponse$default(this, str, null, 2, null);
    }

    @JvmOverloads
    @NotNull
    public final MockDuplexResponseBody sendResponse(@NotNull String s, @NotNull CountDownLatch responseSent) {
        Intrinsics.checkParameterIsNotNull(s, "s");
        Intrinsics.checkParameterIsNotNull(responseSent, "responseSent");
        this.actions.add(new MockDuplexResponseBody$sendResponse$$inlined$apply$lambda$1(s, responseSent));
        return this;
    }

    @NotNull
    public final MockDuplexResponseBody sleep(long j, @NotNull TimeUnit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "unit");
        this.actions.add(new MockDuplexResponseBody$sleep$$inlined$apply$lambda$1(unit, j));
        return this;
    }
}
