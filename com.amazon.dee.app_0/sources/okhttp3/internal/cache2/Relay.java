package okhttp3.internal.cache2;

import com.facebook.imagepipeline.producers.DecodeProducer;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.ByteString;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Relay.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u0000 :2\u00020\u0001:\u0002:;B3\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bJ\u000e\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\tJ\b\u00105\u001a\u0004\u0018\u00010\u0005J \u00106\u001a\u0002032\u0006\u00107\u001a\u00020\t2\u0006\u00104\u001a\u00020\u00072\u0006\u00108\u001a\u00020\u0007H\u0002J\u0010\u00109\u001a\u0002032\u0006\u00104\u001a\u00020\u0007H\u0002R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001c\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0015R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u001eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0011\u0010'\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0011\"\u0004\b*\u0010+R\u001c\u0010,\u001a\u0004\u0018\u00010-X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101¨\u0006<"}, d2 = {"Lokhttp3/internal/cache2/Relay;", "", "file", "Ljava/io/RandomAccessFile;", "upstream", "Lokio/Source;", "upstreamPos", "", "metadata", "Lokio/ByteString;", "bufferMaxSize", "(Ljava/io/RandomAccessFile;Lokio/Source;JLokio/ByteString;J)V", "buffer", "Lokio/Buffer;", "getBuffer", "()Lokio/Buffer;", "getBufferMaxSize", "()J", "complete", "", "getComplete", "()Z", "setComplete", "(Z)V", "getFile", "()Ljava/io/RandomAccessFile;", "setFile", "(Ljava/io/RandomAccessFile;)V", "isClosed", "sourceCount", "", "getSourceCount", "()I", "setSourceCount", "(I)V", "getUpstream", "()Lokio/Source;", "setUpstream", "(Lokio/Source;)V", "upstreamBuffer", "getUpstreamBuffer", "getUpstreamPos", "setUpstreamPos", "(J)V", "upstreamReader", "Ljava/lang/Thread;", "getUpstreamReader", "()Ljava/lang/Thread;", "setUpstreamReader", "(Ljava/lang/Thread;)V", "commit", "", "upstreamSize", "newSource", "writeHeader", "prefix", "metadataSize", "writeMetadata", "Companion", "RelaySource", "okhttp"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class Relay {
    private static final long FILE_HEADER_SIZE = 32;
    private static final int SOURCE_FILE = 2;
    private static final int SOURCE_UPSTREAM = 1;
    @NotNull
    private final Buffer buffer;
    private final long bufferMaxSize;
    private boolean complete;
    @Nullable
    private RandomAccessFile file;
    private final ByteString metadata;
    private int sourceCount;
    @Nullable
    private Source upstream;
    @NotNull
    private final Buffer upstreamBuffer;
    private long upstreamPos;
    @Nullable
    private Thread upstreamReader;
    public static final Companion Companion = new Companion(null);
    @JvmField
    @NotNull
    public static final ByteString PREFIX_CLEAN = ByteString.Companion.encodeUtf8("OkHttp cache v1\n");
    @JvmField
    @NotNull
    public static final ByteString PREFIX_DIRTY = ByteString.Companion.encodeUtf8("OkHttp DIRTY :(\n");

    /* compiled from: Relay.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0004J\u000e\u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lokhttp3/internal/cache2/Relay$Companion;", "", "()V", "FILE_HEADER_SIZE", "", "PREFIX_CLEAN", "Lokio/ByteString;", "PREFIX_DIRTY", "SOURCE_FILE", "", "SOURCE_UPSTREAM", "edit", "Lokhttp3/internal/cache2/Relay;", "file", "Ljava/io/File;", "upstream", "Lokio/Source;", "metadata", "bufferMaxSize", "read", "okhttp"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final Relay edit(@NotNull File file, @NotNull Source upstream, @NotNull ByteString metadata, long j) throws IOException {
            Intrinsics.checkParameterIsNotNull(file, "file");
            Intrinsics.checkParameterIsNotNull(upstream, "upstream");
            Intrinsics.checkParameterIsNotNull(metadata, "metadata");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            Relay relay = new Relay(randomAccessFile, upstream, 0L, metadata, j, null);
            randomAccessFile.setLength(0L);
            relay.writeHeader(Relay.PREFIX_DIRTY, -1L, -1L);
            return relay;
        }

        @NotNull
        public final Relay read(@NotNull File file) throws IOException {
            Intrinsics.checkParameterIsNotNull(file, "file");
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            FileChannel channel = randomAccessFile.getChannel();
            Intrinsics.checkExpressionValueIsNotNull(channel, "randomAccessFile.channel");
            FileOperator fileOperator = new FileOperator(channel);
            Buffer buffer = new Buffer();
            fileOperator.read(0L, buffer, 32L);
            if (!(!Intrinsics.areEqual(buffer.readByteString(Relay.PREFIX_CLEAN.size()), Relay.PREFIX_CLEAN))) {
                long readLong = buffer.readLong();
                long readLong2 = buffer.readLong();
                Buffer buffer2 = new Buffer();
                fileOperator.read(readLong + 32, buffer2, readLong2);
                return new Relay(randomAccessFile, null, readLong, buffer2.readByteString(), 0L, null);
            }
            throw new IOException("unreadable cache file");
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: Relay.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lokhttp3/internal/cache2/Relay$RelaySource;", "Lokio/Source;", "(Lokhttp3/internal/cache2/Relay;)V", "fileOperator", "Lokhttp3/internal/cache2/FileOperator;", "sourcePos", "", "timeout", "Lokio/Timeout;", "close", "", "read", "sink", "Lokio/Buffer;", DecodeProducer.EXTRA_BITMAP_BYTES, "okhttp"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final class RelaySource implements Source {
        private FileOperator fileOperator;
        private long sourcePos;
        private final Timeout timeout = new Timeout();

        public RelaySource() {
            RandomAccessFile file = Relay.this.getFile();
            if (file == null) {
                Intrinsics.throwNpe();
            }
            FileChannel channel = file.getChannel();
            Intrinsics.checkExpressionValueIsNotNull(channel, "file!!.channel");
            this.fileOperator = new FileOperator(channel);
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.fileOperator == null) {
                return;
            }
            RandomAccessFile randomAccessFile = null;
            this.fileOperator = null;
            synchronized (Relay.this) {
                Relay relay = Relay.this;
                relay.setSourceCount(relay.getSourceCount() - 1);
                if (Relay.this.getSourceCount() == 0) {
                    RandomAccessFile file = Relay.this.getFile();
                    Relay.this.setFile(null);
                    randomAccessFile = file;
                }
                Unit unit = Unit.INSTANCE;
            }
            if (randomAccessFile == null) {
                return;
            }
            Util.closeQuietly(randomAccessFile);
        }

        /* JADX WARN: Code restructure failed: missing block: B:27:0x008b, code lost:
            if (r4 != true) goto L26;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x008d, code lost:
            r8 = java.lang.Math.min(r21, r19.this$0.getUpstreamPos() - r19.sourcePos);
            r2 = r19.fileOperator;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x009c, code lost:
            if (r2 != null) goto L23;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x009e, code lost:
            kotlin.jvm.internal.Intrinsics.throwNpe();
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x00a1, code lost:
            r2.read(r19.sourcePos + 32, r20, r8);
            r19.sourcePos += r8;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x00af, code lost:
            return r8;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x00b1, code lost:
            r0 = r19.this$0.getUpstream();
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x00b7, code lost:
            if (r0 != null) goto L31;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x00b9, code lost:
            kotlin.jvm.internal.Intrinsics.throwNpe();
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x00bc, code lost:
            r14 = r0.read(r19.this$0.getUpstreamBuffer(), r19.this$0.getBufferMaxSize());
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x00ce, code lost:
            if (r14 != (-1)) goto L46;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x00d0, code lost:
            r19.this$0.commit(r19.this$0.getUpstreamPos());
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x00db, code lost:
            r2 = r19.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x00dd, code lost:
            monitor-enter(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x00de, code lost:
            r19.this$0.setUpstreamReader(null);
            r0 = r19.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x00e5, code lost:
            if (r0 == null) goto L42;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x00e7, code lost:
            r0.notifyAll();
            r0 = kotlin.Unit.INSTANCE;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x00ec, code lost:
            monitor-exit(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x00ed, code lost:
            return -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x00f5, code lost:
            throw new kotlin.TypeCastException("null cannot be cast to non-null type java.lang.Object");
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x00f9, code lost:
            r9 = java.lang.Math.min(r14, r21);
            r19.this$0.getUpstreamBuffer().copyTo(r20, 0, r9);
            r19.sourcePos += r9;
            r13 = r19.fileOperator;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x0113, code lost:
            if (r13 != null) goto L49;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x0115, code lost:
            kotlin.jvm.internal.Intrinsics.throwNpe();
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x0118, code lost:
            r13.write(r19.this$0.getUpstreamPos() + 32, r19.this$0.getUpstreamBuffer().clone(), r14);
            r2 = r19.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x0132, code lost:
            monitor-enter(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x0133, code lost:
            r19.this$0.getBuffer().write(r19.this$0.getUpstreamBuffer(), r14);
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x0154, code lost:
            if (r19.this$0.getBuffer().size() <= r19.this$0.getBufferMaxSize()) goto L54;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x0156, code lost:
            r19.this$0.getBuffer().skip(r19.this$0.getBuffer().size() - r19.this$0.getBufferMaxSize());
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x0170, code lost:
            r0 = r19.this$0;
            r0.setUpstreamPos(r0.getUpstreamPos() + r14);
            r0 = kotlin.Unit.INSTANCE;
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x017c, code lost:
            monitor-exit(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x017d, code lost:
            r2 = r19.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x017f, code lost:
            monitor-enter(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x0180, code lost:
            r19.this$0.setUpstreamReader(null);
            r0 = r19.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x0187, code lost:
            if (r0 == null) goto L64;
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x0189, code lost:
            r0.notifyAll();
            r0 = kotlin.Unit.INSTANCE;
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:0x018e, code lost:
            monitor-exit(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x018f, code lost:
            return r9;
         */
        /* JADX WARN: Code restructure failed: missing block: B:70:0x0197, code lost:
            throw new kotlin.TypeCastException("null cannot be cast to non-null type java.lang.Object");
         */
        /* JADX WARN: Code restructure failed: missing block: B:77:0x019e, code lost:
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:79:0x01a1, code lost:
            monitor-enter(r19.this$0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:80:0x01a2, code lost:
            r19.this$0.setUpstreamReader(null);
            r3 = r19.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:81:0x01a9, code lost:
            if (r3 == null) goto L74;
         */
        /* JADX WARN: Code restructure failed: missing block: B:83:0x01b2, code lost:
            throw new kotlin.TypeCastException("null cannot be cast to non-null type java.lang.Object");
         */
        /* JADX WARN: Code restructure failed: missing block: B:84:0x01b3, code lost:
            r3.notifyAll();
            r3 = kotlin.Unit.INSTANCE;
         */
        /* JADX WARN: Code restructure failed: missing block: B:86:0x01b9, code lost:
            throw r0;
         */
        @Override // okio.Source
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public long read(@org.jetbrains.annotations.NotNull okio.Buffer r20, long r21) throws java.io.IOException {
            /*
                Method dump skipped, instructions count: 460
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache2.Relay.RelaySource.read(okio.Buffer, long):long");
        }

        @Override // okio.Source
        @NotNull
        /* renamed from: timeout */
        public Timeout mo12585timeout() {
            return this.timeout;
        }
    }

    private Relay(RandomAccessFile randomAccessFile, Source source, long j, ByteString byteString, long j2) {
        this.file = randomAccessFile;
        this.upstream = source;
        this.upstreamPos = j;
        this.metadata = byteString;
        this.bufferMaxSize = j2;
        this.upstreamBuffer = new Buffer();
        this.complete = this.upstream == null;
        this.buffer = new Buffer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void writeHeader(ByteString byteString, long j, long j2) throws IOException {
        Buffer buffer = new Buffer();
        buffer.mo12591write(byteString);
        buffer.mo12601writeLong(j);
        buffer.mo12601writeLong(j2);
        if (buffer.size() == 32) {
            RandomAccessFile randomAccessFile = this.file;
            if (randomAccessFile == null) {
                Intrinsics.throwNpe();
            }
            FileChannel channel = randomAccessFile.getChannel();
            Intrinsics.checkExpressionValueIsNotNull(channel, "file!!.channel");
            new FileOperator(channel).write(0L, buffer, 32L);
            return;
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    private final void writeMetadata(long j) throws IOException {
        Buffer buffer = new Buffer();
        buffer.mo12591write(this.metadata);
        RandomAccessFile randomAccessFile = this.file;
        if (randomAccessFile == null) {
            Intrinsics.throwNpe();
        }
        FileChannel channel = randomAccessFile.getChannel();
        Intrinsics.checkExpressionValueIsNotNull(channel, "file!!.channel");
        new FileOperator(channel).write(32 + j, buffer, this.metadata.size());
    }

    public final void commit(long j) throws IOException {
        writeMetadata(j);
        RandomAccessFile randomAccessFile = this.file;
        if (randomAccessFile == null) {
            Intrinsics.throwNpe();
        }
        randomAccessFile.getChannel().force(false);
        writeHeader(PREFIX_CLEAN, j, this.metadata.size());
        RandomAccessFile randomAccessFile2 = this.file;
        if (randomAccessFile2 == null) {
            Intrinsics.throwNpe();
        }
        randomAccessFile2.getChannel().force(false);
        synchronized (this) {
            this.complete = true;
            Unit unit = Unit.INSTANCE;
        }
        Source source = this.upstream;
        if (source != null) {
            Util.closeQuietly(source);
        }
        this.upstream = null;
    }

    @NotNull
    public final Buffer getBuffer() {
        return this.buffer;
    }

    public final long getBufferMaxSize() {
        return this.bufferMaxSize;
    }

    public final boolean getComplete() {
        return this.complete;
    }

    @Nullable
    public final RandomAccessFile getFile() {
        return this.file;
    }

    public final int getSourceCount() {
        return this.sourceCount;
    }

    @Nullable
    public final Source getUpstream() {
        return this.upstream;
    }

    @NotNull
    public final Buffer getUpstreamBuffer() {
        return this.upstreamBuffer;
    }

    public final long getUpstreamPos() {
        return this.upstreamPos;
    }

    @Nullable
    public final Thread getUpstreamReader() {
        return this.upstreamReader;
    }

    public final boolean isClosed() {
        return this.file == null;
    }

    @NotNull
    public final ByteString metadata() {
        return this.metadata;
    }

    @Nullable
    public final Source newSource() {
        synchronized (this) {
            if (this.file == null) {
                return null;
            }
            this.sourceCount++;
            return new RelaySource();
        }
    }

    public final void setComplete(boolean z) {
        this.complete = z;
    }

    public final void setFile(@Nullable RandomAccessFile randomAccessFile) {
        this.file = randomAccessFile;
    }

    public final void setSourceCount(int i) {
        this.sourceCount = i;
    }

    public final void setUpstream(@Nullable Source source) {
        this.upstream = source;
    }

    public final void setUpstreamPos(long j) {
        this.upstreamPos = j;
    }

    public final void setUpstreamReader(@Nullable Thread thread) {
        this.upstreamReader = thread;
    }

    public /* synthetic */ Relay(RandomAccessFile randomAccessFile, Source source, long j, ByteString byteString, long j2, DefaultConstructorMarker defaultConstructorMarker) {
        this(randomAccessFile, source, j, byteString, j2);
    }
}
