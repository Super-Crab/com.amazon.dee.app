package okhttp3.internal.ws;

import amazon.speech.simclient.settings.Settings;
import com.amazon.alexa.presence.service.PresenceJobService;
import com.facebook.imagepipeline.producers.DecodeProducer;
import java.io.IOException;
import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Sink;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: WebSocketWriter.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001:\u00012B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#J\u0018\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020!2\b\u0010'\u001a\u0004\u0018\u00010(J\u0018\u0010)\u001a\u00020%2\u0006\u0010*\u001a\u00020!2\u0006\u0010+\u001a\u00020(H\u0002J&\u0010,\u001a\u00020%2\u0006\u0010 \u001a\u00020!2\u0006\u0010-\u001a\u00020#2\u0006\u0010.\u001a\u00020\u00032\u0006\u0010/\u001a\u00020\u0003J\u000e\u00100\u001a\u00020%2\u0006\u0010+\u001a\u00020(J\u000e\u00101\u001a\u00020%2\u0006\u0010+\u001a\u00020(R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00060\u0013R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00063"}, d2 = {"Lokhttp3/internal/ws/WebSocketWriter;", "", "isClient", "", "sink", "Lokio/BufferedSink;", "random", "Ljava/util/Random;", "(ZLokio/BufferedSink;Ljava/util/Random;)V", "activeWriter", "getActiveWriter", "()Z", "setActiveWriter", "(Z)V", "buffer", "Lokio/Buffer;", "getBuffer", "()Lokio/Buffer;", "frameSink", "Lokhttp3/internal/ws/WebSocketWriter$FrameSink;", "maskCursor", "Lokio/Buffer$UnsafeCursor;", "maskKey", "", "getRandom", "()Ljava/util/Random;", "getSink", "()Lokio/BufferedSink;", "sinkBuffer", "writerClosed", "newMessageSink", "Lokio/Sink;", "formatOpcode", "", "contentLength", "", "writeClose", "", "code", Settings.ListeningSettings.EXTRA_REASON, "Lokio/ByteString;", "writeControlFrame", "opcode", "payload", "writeMessageFrame", DecodeProducer.EXTRA_BITMAP_BYTES, "isFirstFrame", DecodeProducer.EXTRA_IS_FINAL, "writePing", "writePong", "FrameSink", "okhttp"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class WebSocketWriter {
    private boolean activeWriter;
    @NotNull
    private final Buffer buffer;
    private final FrameSink frameSink;
    private final boolean isClient;
    private final Buffer.UnsafeCursor maskCursor;
    private final byte[] maskKey;
    @NotNull
    private final Random random;
    @NotNull
    private final BufferedSink sink;
    private final Buffer sinkBuffer;
    private boolean writerClosed;

    /* compiled from: WebSocketWriter.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u0018H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0018\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\nH\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0006\"\u0004\b\u0016\u0010\b¨\u0006 "}, d2 = {"Lokhttp3/internal/ws/WebSocketWriter$FrameSink;", "Lokio/Sink;", "(Lokhttp3/internal/ws/WebSocketWriter;)V", "closed", "", "getClosed", "()Z", "setClosed", "(Z)V", "contentLength", "", "getContentLength", "()J", "setContentLength", "(J)V", "formatOpcode", "", "getFormatOpcode", "()I", "setFormatOpcode", "(I)V", "isFirstFrame", "setFirstFrame", "close", "", PresenceJobService.ACTION_REFRESH_FLUSH_KEY, "timeout", "Lokio/Timeout;", "write", "source", "Lokio/Buffer;", DecodeProducer.EXTRA_BITMAP_BYTES, "okhttp"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final class FrameSink implements Sink {
        private boolean closed;
        private long contentLength;
        private int formatOpcode;
        private boolean isFirstFrame;

        public FrameSink() {
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (!this.closed) {
                WebSocketWriter webSocketWriter = WebSocketWriter.this;
                webSocketWriter.writeMessageFrame(this.formatOpcode, webSocketWriter.getBuffer().size(), this.isFirstFrame, true);
                this.closed = true;
                WebSocketWriter.this.setActiveWriter(false);
                return;
            }
            throw new IOException("closed");
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            if (!this.closed) {
                WebSocketWriter webSocketWriter = WebSocketWriter.this;
                webSocketWriter.writeMessageFrame(this.formatOpcode, webSocketWriter.getBuffer().size(), this.isFirstFrame, false);
                this.isFirstFrame = false;
                return;
            }
            throw new IOException("closed");
        }

        public final boolean getClosed() {
            return this.closed;
        }

        public final long getContentLength() {
            return this.contentLength;
        }

        public final int getFormatOpcode() {
            return this.formatOpcode;
        }

        public final boolean isFirstFrame() {
            return this.isFirstFrame;
        }

        public final void setClosed(boolean z) {
            this.closed = z;
        }

        public final void setContentLength(long j) {
            this.contentLength = j;
        }

        public final void setFirstFrame(boolean z) {
            this.isFirstFrame = z;
        }

        public final void setFormatOpcode(int i) {
            this.formatOpcode = i;
        }

        @Override // okio.Sink
        @NotNull
        /* renamed from: timeout */
        public Timeout mo12584timeout() {
            return WebSocketWriter.this.getSink().mo12584timeout();
        }

        @Override // okio.Sink
        public void write(@NotNull Buffer source, long j) throws IOException {
            Intrinsics.checkParameterIsNotNull(source, "source");
            if (!this.closed) {
                WebSocketWriter.this.getBuffer().write(source, j);
                boolean z = this.isFirstFrame && this.contentLength != -1 && WebSocketWriter.this.getBuffer().size() > this.contentLength - ((long) 8192);
                long completeSegmentByteCount = WebSocketWriter.this.getBuffer().completeSegmentByteCount();
                if (completeSegmentByteCount <= 0 || z) {
                    return;
                }
                WebSocketWriter.this.writeMessageFrame(this.formatOpcode, completeSegmentByteCount, this.isFirstFrame, false);
                this.isFirstFrame = false;
                return;
            }
            throw new IOException("closed");
        }
    }

    public WebSocketWriter(boolean z, @NotNull BufferedSink sink, @NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        Intrinsics.checkParameterIsNotNull(random, "random");
        this.isClient = z;
        this.sink = sink;
        this.random = random;
        this.sinkBuffer = this.sink.getBuffer();
        this.buffer = new Buffer();
        this.frameSink = new FrameSink();
        Buffer.UnsafeCursor unsafeCursor = null;
        this.maskKey = this.isClient ? new byte[4] : null;
        this.maskCursor = this.isClient ? new Buffer.UnsafeCursor() : unsafeCursor;
    }

    private final void writeControlFrame(int i, ByteString byteString) throws IOException {
        if (!this.writerClosed) {
            int size = byteString.size();
            if (((long) size) <= 125) {
                this.sinkBuffer.mo12596writeByte(i | 128);
                if (this.isClient) {
                    this.sinkBuffer.mo12596writeByte(size | 128);
                    Random random = this.random;
                    byte[] bArr = this.maskKey;
                    if (bArr == null) {
                        Intrinsics.throwNpe();
                    }
                    random.nextBytes(bArr);
                    this.sinkBuffer.mo12594write(this.maskKey);
                    if (size > 0) {
                        long size2 = this.sinkBuffer.size();
                        this.sinkBuffer.mo12591write(byteString);
                        Buffer buffer = this.sinkBuffer;
                        Buffer.UnsafeCursor unsafeCursor = this.maskCursor;
                        if (unsafeCursor == null) {
                            Intrinsics.throwNpe();
                        }
                        buffer.readAndWriteUnsafe(unsafeCursor);
                        this.maskCursor.seek(size2);
                        WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
                        this.maskCursor.close();
                    }
                } else {
                    this.sinkBuffer.mo12596writeByte(size);
                    this.sinkBuffer.mo12591write(byteString);
                }
                this.sink.flush();
                return;
            }
            throw new IllegalArgumentException("Payload size must be less than or equal to 125".toString());
        }
        throw new IOException("closed");
    }

    public final boolean getActiveWriter() {
        return this.activeWriter;
    }

    @NotNull
    public final Buffer getBuffer() {
        return this.buffer;
    }

    @NotNull
    public final Random getRandom() {
        return this.random;
    }

    @NotNull
    public final BufferedSink getSink() {
        return this.sink;
    }

    @NotNull
    public final Sink newMessageSink(int i, long j) {
        if (!this.activeWriter) {
            this.activeWriter = true;
            this.frameSink.setFormatOpcode(i);
            this.frameSink.setContentLength(j);
            this.frameSink.setFirstFrame(true);
            this.frameSink.setClosed(false);
            return this.frameSink;
        }
        throw new IllegalStateException("Another message writer is active. Did you call close()?".toString());
    }

    public final void setActiveWriter(boolean z) {
        this.activeWriter = z;
    }

    public final void writeClose(int i, @Nullable ByteString byteString) throws IOException {
        ByteString byteString2 = ByteString.EMPTY;
        if (i != 0 || byteString != null) {
            if (i != 0) {
                WebSocketProtocol.INSTANCE.validateCloseCode(i);
            }
            Buffer buffer = new Buffer();
            buffer.mo12603writeShort(i);
            if (byteString != null) {
                buffer.mo12591write(byteString);
            }
            byteString2 = buffer.readByteString();
        }
        try {
            writeControlFrame(8, byteString2);
        } finally {
            this.writerClosed = true;
        }
    }

    public final void writeMessageFrame(int i, long j, boolean z, boolean z2) throws IOException {
        if (!this.writerClosed) {
            int i2 = 0;
            if (!z) {
                i = 0;
            }
            if (z2) {
                i |= 128;
            }
            this.sinkBuffer.mo12596writeByte(i);
            if (this.isClient) {
                i2 = 128;
            }
            if (j <= 125) {
                this.sinkBuffer.mo12596writeByte(((int) j) | i2);
            } else if (j <= WebSocketProtocol.PAYLOAD_SHORT_MAX) {
                this.sinkBuffer.mo12596writeByte(i2 | 126);
                this.sinkBuffer.mo12603writeShort((int) j);
            } else {
                this.sinkBuffer.mo12596writeByte(i2 | 127);
                this.sinkBuffer.mo12601writeLong(j);
            }
            if (this.isClient) {
                Random random = this.random;
                byte[] bArr = this.maskKey;
                if (bArr == null) {
                    Intrinsics.throwNpe();
                }
                random.nextBytes(bArr);
                this.sinkBuffer.mo12594write(this.maskKey);
                if (j > 0) {
                    long size = this.sinkBuffer.size();
                    this.sinkBuffer.write(this.buffer, j);
                    Buffer buffer = this.sinkBuffer;
                    Buffer.UnsafeCursor unsafeCursor = this.maskCursor;
                    if (unsafeCursor == null) {
                        Intrinsics.throwNpe();
                    }
                    buffer.readAndWriteUnsafe(unsafeCursor);
                    this.maskCursor.seek(size);
                    WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
                    this.maskCursor.close();
                }
            } else {
                this.sinkBuffer.write(this.buffer, j);
            }
            this.sink.mo12589emit();
            return;
        }
        throw new IOException("closed");
    }

    public final void writePing(@NotNull ByteString payload) throws IOException {
        Intrinsics.checkParameterIsNotNull(payload, "payload");
        writeControlFrame(9, payload);
    }

    public final void writePong(@NotNull ByteString payload) throws IOException {
        Intrinsics.checkParameterIsNotNull(payload, "payload");
        writeControlFrame(10, payload);
    }
}
