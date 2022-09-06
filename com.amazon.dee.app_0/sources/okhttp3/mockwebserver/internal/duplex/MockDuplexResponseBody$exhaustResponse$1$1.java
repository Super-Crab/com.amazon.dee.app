package okhttp3.mockwebserver.internal.duplex;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import okhttp3.internal.http2.Http2Stream;
import okhttp3.mockwebserver.RecordedRequest;
import okio.BufferedSink;
import okio.BufferedSource;
import org.jetbrains.annotations.NotNull;
/* compiled from: MockDuplexResponseBody.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\n¢\u0006\u0002\b\n"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lokhttp3/mockwebserver/RecordedRequest;", "<anonymous parameter 1>", "Lokio/BufferedSource;", "responseBody", "Lokio/BufferedSink;", "<anonymous parameter 3>", "Lokhttp3/internal/http2/Http2Stream;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes4.dex */
final class MockDuplexResponseBody$exhaustResponse$1$1 extends Lambda implements Function4<RecordedRequest, BufferedSource, BufferedSink, Http2Stream, Unit> {
    public static final MockDuplexResponseBody$exhaustResponse$1$1 INSTANCE = new MockDuplexResponseBody$exhaustResponse$1$1();

    MockDuplexResponseBody$exhaustResponse$1$1() {
        super(4);
    }

    @Override // kotlin.jvm.functions.Function4
    public /* bridge */ /* synthetic */ Unit invoke(RecordedRequest recordedRequest, BufferedSource bufferedSource, BufferedSink bufferedSink, Http2Stream http2Stream) {
        invoke2(recordedRequest, bufferedSource, bufferedSink, http2Stream);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull RecordedRequest recordedRequest, @NotNull BufferedSource bufferedSource, @NotNull BufferedSink responseBody, @NotNull Http2Stream http2Stream) {
        Intrinsics.checkParameterIsNotNull(recordedRequest, "<anonymous parameter 0>");
        Intrinsics.checkParameterIsNotNull(bufferedSource, "<anonymous parameter 1>");
        Intrinsics.checkParameterIsNotNull(responseBody, "responseBody");
        Intrinsics.checkParameterIsNotNull(http2Stream, "<anonymous parameter 3>");
        responseBody.close();
    }
}
