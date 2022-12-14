package okio;

import com.amazon.alexa.presence.service.PresenceJobService;
import com.facebook.common.callercontext.ContextChain;
import com.facebook.imagepipeline.producers.DecodeProducer;
import io.ktor.http.auth.HttpAuthHeader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;
/* compiled from: BufferedSink.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\u0003\u001a\u00020\u0004H'J\b\u0010\u0007\u001a\u00020\u0000H&J\b\u0010\b\u001a\u00020\u0000H&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fH&J \u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H&J\u0010\u0010\r\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0014H&J \u0010\r\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H&J\u0018\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u0016H&J\u0010\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u000e\u001a\u00020\u0015H&J\u0010\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0011H&J\u0010\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0016H&J\u0010\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0016H&J\u0010\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u0011H&J\u0010\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u0011H&J\u0010\u0010 \u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0016H&J\u0010\u0010!\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0016H&J\u0010\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\u0011H&J\u0010\u0010$\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\u0011H&J\u0018\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)H&J(\u0010%\u001a\u00020\u00002\u0006\u0010&\u001a\u00020'2\u0006\u0010*\u001a\u00020\u00112\u0006\u0010+\u001a\u00020\u00112\u0006\u0010(\u001a\u00020)H&J\u0010\u0010,\u001a\u00020\u00002\u0006\u0010&\u001a\u00020'H&J \u0010,\u001a\u00020\u00002\u0006\u0010&\u001a\u00020'2\u0006\u0010*\u001a\u00020\u00112\u0006\u0010+\u001a\u00020\u0011H&J\u0010\u0010-\u001a\u00020\u00002\u0006\u0010.\u001a\u00020\u0011H&R\u0012\u0010\u0003\u001a\u00020\u0004X??\u0004??\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006??\u0006/"}, d2 = {"Lokio/BufferedSink;", "Lokio/Sink;", "Ljava/nio/channels/WritableByteChannel;", "buffer", "Lokio/Buffer;", "getBuffer", "()Lokio/Buffer;", "emit", "emitCompleteSegments", PresenceJobService.ACTION_REFRESH_FLUSH_KEY, "", "outputStream", "Ljava/io/OutputStream;", "write", "source", "", "offset", "", DecodeProducer.EXTRA_BITMAP_BYTES, "byteString", "Lokio/ByteString;", "Lokio/Source;", "", "writeAll", "writeByte", "b", "writeDecimalLong", "v", "writeHexadecimalUnsignedLong", "writeInt", ContextChain.TAG_INFRA, "writeIntLe", "writeLong", "writeLongLe", "writeShort", "s", "writeShortLe", "writeString", "string", "", HttpAuthHeader.Parameters.Charset, "Ljava/nio/charset/Charset;", "beginIndex", "endIndex", "writeUtf8", "writeUtf8CodePoint", "codePoint", "okio"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public interface BufferedSink extends Sink, WritableByteChannel {
    @Deprecated(level = DeprecationLevel.WARNING, message = "moved to val: use getBuffer() instead", replaceWith = @ReplaceWith(expression = "buffer", imports = {}))
    @NotNull
    Buffer buffer();

    @NotNull
    /* renamed from: emit */
    BufferedSink mo12589emit() throws IOException;

    @NotNull
    /* renamed from: emitCompleteSegments */
    BufferedSink mo12590emitCompleteSegments() throws IOException;

    @Override // okio.Sink, java.io.Flushable
    void flush() throws IOException;

    @NotNull
    Buffer getBuffer();

    @NotNull
    OutputStream outputStream();

    @NotNull
    /* renamed from: write */
    BufferedSink mo12591write(@NotNull ByteString byteString) throws IOException;

    @NotNull
    /* renamed from: write */
    BufferedSink mo12592write(@NotNull ByteString byteString, int i, int i2) throws IOException;

    @NotNull
    /* renamed from: write */
    BufferedSink mo12593write(@NotNull Source source, long j) throws IOException;

    @NotNull
    /* renamed from: write */
    BufferedSink mo12594write(@NotNull byte[] bArr) throws IOException;

    @NotNull
    /* renamed from: write */
    BufferedSink mo12595write(@NotNull byte[] bArr, int i, int i2) throws IOException;

    long writeAll(@NotNull Source source) throws IOException;

    @NotNull
    /* renamed from: writeByte */
    BufferedSink mo12596writeByte(int i) throws IOException;

    @NotNull
    /* renamed from: writeDecimalLong */
    BufferedSink mo12597writeDecimalLong(long j) throws IOException;

    @NotNull
    /* renamed from: writeHexadecimalUnsignedLong */
    BufferedSink mo12598writeHexadecimalUnsignedLong(long j) throws IOException;

    @NotNull
    /* renamed from: writeInt */
    BufferedSink mo12599writeInt(int i) throws IOException;

    @NotNull
    /* renamed from: writeIntLe */
    BufferedSink mo12600writeIntLe(int i) throws IOException;

    @NotNull
    /* renamed from: writeLong */
    BufferedSink mo12601writeLong(long j) throws IOException;

    @NotNull
    /* renamed from: writeLongLe */
    BufferedSink mo12602writeLongLe(long j) throws IOException;

    @NotNull
    /* renamed from: writeShort */
    BufferedSink mo12603writeShort(int i) throws IOException;

    @NotNull
    /* renamed from: writeShortLe */
    BufferedSink mo12604writeShortLe(int i) throws IOException;

    @NotNull
    /* renamed from: writeString */
    BufferedSink mo12605writeString(@NotNull String str, int i, int i2, @NotNull Charset charset) throws IOException;

    @NotNull
    /* renamed from: writeString */
    BufferedSink mo12606writeString(@NotNull String str, @NotNull Charset charset) throws IOException;

    @NotNull
    /* renamed from: writeUtf8 */
    BufferedSink mo12607writeUtf8(@NotNull String str) throws IOException;

    @NotNull
    /* renamed from: writeUtf8 */
    BufferedSink mo12608writeUtf8(@NotNull String str, int i, int i2) throws IOException;

    @NotNull
    /* renamed from: writeUtf8CodePoint */
    BufferedSink mo12609writeUtf8CodePoint(int i) throws IOException;
}
