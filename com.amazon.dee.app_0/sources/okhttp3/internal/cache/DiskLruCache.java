package okhttp3.internal.cache;

import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.fitness.utils.MetricName;
import com.amazon.alexa.presence.service.PresenceJobService;
import com.amazon.clouddrive.extended.model.BulkOperationType;
import com.amazon.dee.app.elements.bridges.MetricsModule;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import com.facebook.cache.disk.DefaultDiskStorage;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.serialization.json.internal.JsonReaderKt;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DiskLruCache.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000{\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010)\n\u0002\b\u0007*\u0001\u0012\u0018\u0000 Y2\u00020\u00012\u00020\u0002:\u0004YZ[\\B7\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\b\u00107\u001a\u000208H\u0002J\b\u00109\u001a\u000208H\u0016J!\u0010:\u001a\u0002082\n\u0010;\u001a\u00060<R\u00020\u00002\u0006\u0010=\u001a\u00020\u0015H\u0000¢\u0006\u0002\b>J\u0006\u0010?\u001a\u000208J \u0010@\u001a\b\u0018\u00010<R\u00020\u00002\u0006\u0010A\u001a\u00020'2\b\b\u0002\u0010B\u001a\u00020\u000bH\u0007J\u0006\u0010C\u001a\u000208J\b\u0010D\u001a\u000208H\u0016J\u0017\u0010E\u001a\b\u0018\u00010FR\u00020\u00002\u0006\u0010A\u001a\u00020'H\u0086\u0002J\u0006\u0010G\u001a\u000208J\u0006\u0010H\u001a\u00020\u0015J\b\u0010I\u001a\u00020\u0015H\u0002J\b\u0010J\u001a\u00020$H\u0002J\b\u0010K\u001a\u000208H\u0002J\b\u0010L\u001a\u000208H\u0002J\u0010\u0010M\u001a\u0002082\u0006\u0010N\u001a\u00020'H\u0002J\r\u0010O\u001a\u000208H\u0000¢\u0006\u0002\bPJ\u000e\u0010Q\u001a\u00020\u00152\u0006\u0010A\u001a\u00020'J\u0019\u0010R\u001a\u00020\u00152\n\u0010S\u001a\u00060(R\u00020\u0000H\u0000¢\u0006\u0002\bTJ\u0006\u00104\u001a\u00020\u000bJ\u0010\u0010U\u001a\f\u0012\b\u0012\u00060FR\u00020\u00000VJ\u0006\u0010W\u001a\u000208J\u0010\u0010X\u001a\u0002082\u0006\u0010A\u001a\u00020'H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010%\u001a\u0012\u0012\u0004\u0012\u00020'\u0012\b\u0012\u00060(R\u00020\u00000&X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R&\u0010\n\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u000b8F@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u000e\u00100\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u00106¨\u0006]"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache;", "Ljava/io/Closeable;", "Ljava/io/Flushable;", "fileSystem", "Lokhttp3/internal/io/FileSystem;", "directory", "Ljava/io/File;", "appVersion", "", "valueCount", "maxSize", "", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "(Lokhttp3/internal/io/FileSystem;Ljava/io/File;IIJLokhttp3/internal/concurrent/TaskRunner;)V", "cleanupQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "cleanupTask", "okhttp3/internal/cache/DiskLruCache$cleanupTask$1", "Lokhttp3/internal/cache/DiskLruCache$cleanupTask$1;", "closed", "", "getClosed$okhttp", "()Z", "setClosed$okhttp", "(Z)V", "getDirectory", "()Ljava/io/File;", "getFileSystem$okhttp", "()Lokhttp3/internal/io/FileSystem;", "hasJournalErrors", "initialized", "journalFile", "journalFileBackup", "journalFileTmp", "journalWriter", "Lokio/BufferedSink;", "lruEntries", "Ljava/util/LinkedHashMap;", "", "Lokhttp3/internal/cache/DiskLruCache$Entry;", "getLruEntries$okhttp", "()Ljava/util/LinkedHashMap;", "value", "getMaxSize", "()J", "setMaxSize", "(J)V", "mostRecentRebuildFailed", "mostRecentTrimFailed", "nextSequenceNumber", "redundantOpCount", "size", "getValueCount$okhttp", "()I", "checkNotClosed", "", "close", "completeEdit", "editor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "success", "completeEdit$okhttp", "delete", "edit", "key", "expectedSequenceNumber", "evictAll", PresenceJobService.ACTION_REFRESH_FLUSH_KEY, MetricsConstants.Method.CACHE_GET, "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "initialize", "isClosed", "journalRebuildRequired", "newJournalWriter", "processJournal", "readJournal", "readJournalLine", "line", "rebuildJournal", "rebuildJournal$okhttp", BulkOperationType.remove, "removeEntry", "entry", "removeEntry$okhttp", "snapshots", "", "trimToSize", "validateKey", "Companion", "Editor", "Entry", "Snapshot", "okhttp"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class DiskLruCache implements Closeable, Flushable {
    private final int appVersion;
    private final TaskQueue cleanupQueue;
    private final DiskLruCache$cleanupTask$1 cleanupTask;
    private boolean closed;
    @NotNull
    private final File directory;
    @NotNull
    private final FileSystem fileSystem;
    private boolean hasJournalErrors;
    private boolean initialized;
    private final File journalFile;
    private final File journalFileBackup;
    private final File journalFileTmp;
    private BufferedSink journalWriter;
    @NotNull
    private final LinkedHashMap<String, Entry> lruEntries;
    private long maxSize;
    private boolean mostRecentRebuildFailed;
    private boolean mostRecentTrimFailed;
    private long nextSequenceNumber;
    private int redundantOpCount;
    private long size;
    private final int valueCount;
    public static final Companion Companion = new Companion(null);
    @JvmField
    @NotNull
    public static final String JOURNAL_FILE = JOURNAL_FILE;
    @JvmField
    @NotNull
    public static final String JOURNAL_FILE = JOURNAL_FILE;
    @JvmField
    @NotNull
    public static final String JOURNAL_FILE_TEMP = JOURNAL_FILE_TEMP;
    @JvmField
    @NotNull
    public static final String JOURNAL_FILE_TEMP = JOURNAL_FILE_TEMP;
    @JvmField
    @NotNull
    public static final String JOURNAL_FILE_BACKUP = JOURNAL_FILE_BACKUP;
    @JvmField
    @NotNull
    public static final String JOURNAL_FILE_BACKUP = JOURNAL_FILE_BACKUP;
    @JvmField
    @NotNull
    public static final String MAGIC = MAGIC;
    @JvmField
    @NotNull
    public static final String MAGIC = MAGIC;
    @JvmField
    @NotNull
    public static final String VERSION_1 = "1";
    @JvmField
    public static final long ANY_SEQUENCE_NUMBER = -1;
    @JvmField
    @NotNull
    public static final Regex LEGAL_KEY_PATTERN = new Regex("[a-z0-9_-]{1,120}");
    @JvmField
    @NotNull
    public static final String CLEAN = CLEAN;
    @JvmField
    @NotNull
    public static final String CLEAN = CLEAN;
    @JvmField
    @NotNull
    public static final String DIRTY = DIRTY;
    @JvmField
    @NotNull
    public static final String DIRTY = DIRTY;
    @JvmField
    @NotNull
    public static final String REMOVE = "REMOVE";
    @JvmField
    @NotNull
    public static final String READ = "READ";

    /* compiled from: DiskLruCache.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00068\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00068\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00068\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00068\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u00068\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u00068\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00068\u0006X\u0087D¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00068\u0006X\u0087D¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Companion;", "", "()V", "ANY_SEQUENCE_NUMBER", "", DiskLruCache.CLEAN, "", DiskLruCache.DIRTY, "JOURNAL_FILE", "JOURNAL_FILE_BACKUP", "JOURNAL_FILE_TEMP", "LEGAL_KEY_PATTERN", "Lkotlin/text/Regex;", "MAGIC", "READ", "REMOVE", "VERSION_1", "okhttp"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: DiskLruCache.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0018\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0013\b\u0000\u0012\n\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\r\u0010\u0011\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\u0012J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0015\u001a\u00020\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0002\u001a\u00060\u0003R\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Editor;", "", "entry", "Lokhttp3/internal/cache/DiskLruCache$Entry;", "Lokhttp3/internal/cache/DiskLruCache;", "(Lokhttp3/internal/cache/DiskLruCache;Lokhttp3/internal/cache/DiskLruCache$Entry;)V", MetricName.DONE, "", "getEntry$okhttp", "()Lokhttp3/internal/cache/DiskLruCache$Entry;", "written", "", "getWritten$okhttp", "()[Z", MetricsModule.TimelineState.ABORT, "", "commit", "detach", "detach$okhttp", "newSink", "Lokio/Sink;", "index", "", "newSource", "Lokio/Source;", "okhttp"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final class Editor {
        private boolean done;
        @NotNull
        private final Entry entry;
        final /* synthetic */ DiskLruCache this$0;
        @Nullable
        private final boolean[] written;

        public Editor(@NotNull DiskLruCache diskLruCache, Entry entry) {
            Intrinsics.checkParameterIsNotNull(entry, "entry");
            this.this$0 = diskLruCache;
            this.entry = entry;
            this.written = this.entry.getReadable$okhttp() ? null : new boolean[diskLruCache.getValueCount$okhttp()];
        }

        public final void abort() throws IOException {
            synchronized (this.this$0) {
                if (!this.done) {
                    if (Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this)) {
                        this.this$0.completeEdit$okhttp(this, false);
                    }
                    this.done = true;
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new IllegalStateException("Check failed.".toString());
                }
            }
        }

        public final void commit() throws IOException {
            synchronized (this.this$0) {
                if (!this.done) {
                    if (Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this)) {
                        this.this$0.completeEdit$okhttp(this, true);
                    }
                    this.done = true;
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new IllegalStateException("Check failed.".toString());
                }
            }
        }

        public final void detach$okhttp() {
            if (Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this)) {
                int valueCount$okhttp = this.this$0.getValueCount$okhttp();
                for (int i = 0; i < valueCount$okhttp; i++) {
                    try {
                        this.this$0.getFileSystem$okhttp().delete(this.entry.getDirtyFiles$okhttp().get(i));
                    } catch (IOException unused) {
                    }
                }
                this.entry.setCurrentEditor$okhttp(null);
            }
        }

        @NotNull
        public final Entry getEntry$okhttp() {
            return this.entry;
        }

        @Nullable
        public final boolean[] getWritten$okhttp() {
            return this.written;
        }

        @NotNull
        public final Sink newSink(int i) {
            synchronized (this.this$0) {
                if (!this.done) {
                    if (!Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this)) {
                        return Okio.blackhole();
                    }
                    if (!this.entry.getReadable$okhttp()) {
                        boolean[] zArr = this.written;
                        if (zArr == null) {
                            Intrinsics.throwNpe();
                        }
                        zArr[i] = true;
                    }
                    try {
                        return new FaultHidingSink(this.this$0.getFileSystem$okhttp().sink(this.entry.getDirtyFiles$okhttp().get(i)), new DiskLruCache$Editor$newSink$$inlined$synchronized$lambda$1(this, i));
                    } catch (FileNotFoundException unused) {
                        return Okio.blackhole();
                    }
                }
                throw new IllegalStateException("Check failed.".toString());
            }
        }

        @Nullable
        public final Source newSource(int i) {
            synchronized (this.this$0) {
                if (!this.done) {
                    Source source = null;
                    if (!this.entry.getReadable$okhttp() || (!Intrinsics.areEqual(this.entry.getCurrentEditor$okhttp(), this))) {
                        return null;
                    }
                    try {
                        source = this.this$0.getFileSystem$okhttp().source(this.entry.getCleanFiles$okhttp().get(i));
                    } catch (FileNotFoundException unused) {
                    }
                    return source;
                }
                throw new IllegalStateException("Check failed.".toString());
            }
        }
    }

    /* compiled from: DiskLruCache.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0016\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010%\u001a\u00020&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00030(H\u0002J\u001b\u0010)\u001a\u00020*2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00030(H\u0000¢\u0006\u0002\b+J\u0013\u0010,\u001a\b\u0018\u00010-R\u00020\fH\u0000¢\u0006\u0002\b.J\u0015\u0010/\u001a\u00020*2\u0006\u00100\u001a\u000201H\u0000¢\u0006\u0002\b2R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0018\u00010\u000bR\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u0016X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020 X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$¨\u00063"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Entry;", "", "key", "", "(Lokhttp3/internal/cache/DiskLruCache;Ljava/lang/String;)V", "cleanFiles", "", "Ljava/io/File;", "getCleanFiles$okhttp", "()Ljava/util/List;", "currentEditor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Lokhttp3/internal/cache/DiskLruCache;", "getCurrentEditor$okhttp", "()Lokhttp3/internal/cache/DiskLruCache$Editor;", "setCurrentEditor$okhttp", "(Lokhttp3/internal/cache/DiskLruCache$Editor;)V", "dirtyFiles", "getDirtyFiles$okhttp", "getKey$okhttp", "()Ljava/lang/String;", "lengths", "", "getLengths$okhttp", "()[J", "readable", "", "getReadable$okhttp", "()Z", "setReadable$okhttp", "(Z)V", AccessoryMetricsConstants.SEQUENCE_NUMBER, "", "getSequenceNumber$okhttp", "()J", "setSequenceNumber$okhttp", "(J)V", "invalidLengths", "Ljava/io/IOException;", "strings", "", "setLengths", "", "setLengths$okhttp", "snapshot", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "snapshot$okhttp", "writeLengths", "writer", "Lokio/BufferedSink;", "writeLengths$okhttp", "okhttp"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final class Entry {
        @NotNull
        private final List<File> cleanFiles;
        @Nullable
        private Editor currentEditor;
        @NotNull
        private final List<File> dirtyFiles;
        @NotNull
        private final String key;
        @NotNull
        private final long[] lengths;
        private boolean readable;
        private long sequenceNumber;
        final /* synthetic */ DiskLruCache this$0;

        public Entry(@NotNull DiskLruCache diskLruCache, String key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            this.this$0 = diskLruCache;
            this.key = key;
            this.lengths = new long[diskLruCache.getValueCount$okhttp()];
            this.cleanFiles = new ArrayList();
            this.dirtyFiles = new ArrayList();
            StringBuilder sb = new StringBuilder(this.key);
            sb.append('.');
            int length = sb.length();
            int valueCount$okhttp = diskLruCache.getValueCount$okhttp();
            for (int i = 0; i < valueCount$okhttp; i++) {
                sb.append(i);
                this.cleanFiles.add(new File(diskLruCache.getDirectory(), sb.toString()));
                sb.append(DefaultDiskStorage.FileType.TEMP);
                this.dirtyFiles.add(new File(diskLruCache.getDirectory(), sb.toString()));
                sb.setLength(length);
            }
        }

        private final IOException invalidLengths(List<String> list) throws IOException {
            throw new IOException("unexpected journal line: " + list);
        }

        @NotNull
        public final List<File> getCleanFiles$okhttp() {
            return this.cleanFiles;
        }

        @Nullable
        public final Editor getCurrentEditor$okhttp() {
            return this.currentEditor;
        }

        @NotNull
        public final List<File> getDirtyFiles$okhttp() {
            return this.dirtyFiles;
        }

        @NotNull
        public final String getKey$okhttp() {
            return this.key;
        }

        @NotNull
        public final long[] getLengths$okhttp() {
            return this.lengths;
        }

        public final boolean getReadable$okhttp() {
            return this.readable;
        }

        public final long getSequenceNumber$okhttp() {
            return this.sequenceNumber;
        }

        public final void setCurrentEditor$okhttp(@Nullable Editor editor) {
            this.currentEditor = editor;
        }

        public final void setLengths$okhttp(@NotNull List<String> strings) throws IOException {
            Intrinsics.checkParameterIsNotNull(strings, "strings");
            if (strings.size() == this.this$0.getValueCount$okhttp()) {
                try {
                    int size = strings.size();
                    for (int i = 0; i < size; i++) {
                        this.lengths[i] = Long.parseLong(strings.get(i));
                    }
                    return;
                } catch (NumberFormatException unused) {
                    throw invalidLengths(strings);
                }
            }
            throw invalidLengths(strings);
        }

        public final void setReadable$okhttp(boolean z) {
            this.readable = z;
        }

        public final void setSequenceNumber$okhttp(long j) {
            this.sequenceNumber = j;
        }

        @Nullable
        public final Snapshot snapshot$okhttp() {
            DiskLruCache diskLruCache = this.this$0;
            if (Util.assertionsEnabled && !Thread.holdsLock(diskLruCache)) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Thread ");
                Thread currentThread = Thread.currentThread();
                Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
                outline107.append(currentThread.getName());
                outline107.append(" MUST hold lock on ");
                outline107.append(diskLruCache);
                throw new AssertionError(outline107.toString());
            }
            ArrayList<Source> arrayList = new ArrayList();
            long[] jArr = (long[]) this.lengths.clone();
            try {
                int valueCount$okhttp = this.this$0.getValueCount$okhttp();
                for (int i = 0; i < valueCount$okhttp; i++) {
                    arrayList.add(this.this$0.getFileSystem$okhttp().source(this.cleanFiles.get(i)));
                }
                return new Snapshot(this.this$0, this.key, this.sequenceNumber, arrayList, jArr);
            } catch (FileNotFoundException unused) {
                for (Source source : arrayList) {
                    Util.closeQuietly(source);
                }
                try {
                    this.this$0.removeEntry$okhttp(this);
                    return null;
                } catch (IOException unused2) {
                    return null;
                }
            }
        }

        public final void writeLengths$okhttp(@NotNull BufferedSink writer) throws IOException {
            Intrinsics.checkParameterIsNotNull(writer, "writer");
            for (long j : this.lengths) {
                writer.mo12596writeByte(32).mo12597writeDecimalLong(j);
            }
        }
    }

    /* compiled from: DiskLruCache.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B-\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\rH\u0016J\f\u0010\u000e\u001a\b\u0018\u00010\u000fR\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0002\u001a\u00020\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Ljava/io/Closeable;", "key", "", AccessoryMetricsConstants.SEQUENCE_NUMBER, "", "sources", "", "Lokio/Source;", "lengths", "", "(Lokhttp3/internal/cache/DiskLruCache;Ljava/lang/String;JLjava/util/List;[J)V", "close", "", "edit", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Lokhttp3/internal/cache/DiskLruCache;", "getLength", "index", "", "getSource", "okhttp"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final class Snapshot implements Closeable {
        private final String key;
        private final long[] lengths;
        private final long sequenceNumber;
        private final List<Source> sources;
        final /* synthetic */ DiskLruCache this$0;

        /* JADX WARN: Multi-variable type inference failed */
        public Snapshot(@NotNull DiskLruCache diskLruCache, String key, @NotNull long j, @NotNull List<? extends Source> sources, long[] lengths) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            Intrinsics.checkParameterIsNotNull(sources, "sources");
            Intrinsics.checkParameterIsNotNull(lengths, "lengths");
            this.this$0 = diskLruCache;
            this.key = key;
            this.sequenceNumber = j;
            this.sources = sources;
            this.lengths = lengths;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            for (Source source : this.sources) {
                Util.closeQuietly(source);
            }
        }

        @Nullable
        public final Editor edit() throws IOException {
            return this.this$0.edit(this.key, this.sequenceNumber);
        }

        public final long getLength(int i) {
            return this.lengths[i];
        }

        @NotNull
        public final Source getSource(int i) {
            return this.sources.get(i);
        }

        @NotNull
        public final String key() {
            return this.key;
        }
    }

    /* JADX WARN: Type inference failed for: r3v3, types: [okhttp3.internal.cache.DiskLruCache$cleanupTask$1] */
    public DiskLruCache(@NotNull FileSystem fileSystem, @NotNull File directory, int i, int i2, long j, @NotNull TaskRunner taskRunner) {
        Intrinsics.checkParameterIsNotNull(fileSystem, "fileSystem");
        Intrinsics.checkParameterIsNotNull(directory, "directory");
        Intrinsics.checkParameterIsNotNull(taskRunner, "taskRunner");
        this.fileSystem = fileSystem;
        this.directory = directory;
        this.appVersion = i;
        this.valueCount = i2;
        this.maxSize = j;
        boolean z = true;
        this.lruEntries = new LinkedHashMap<>(0, 0.75f, true);
        this.cleanupQueue = taskRunner.newQueue();
        this.cleanupTask = new Task("OkHttp Cache") { // from class: okhttp3.internal.cache.DiskLruCache$cleanupTask$1
            @Override // okhttp3.internal.concurrent.Task
            public long runOnce() {
                boolean z2;
                boolean journalRebuildRequired;
                synchronized (DiskLruCache.this) {
                    z2 = DiskLruCache.this.initialized;
                    if (!z2 || DiskLruCache.this.getClosed$okhttp()) {
                        return -1L;
                    }
                    try {
                        DiskLruCache.this.trimToSize();
                    } catch (IOException unused) {
                        DiskLruCache.this.mostRecentTrimFailed = true;
                    }
                    try {
                        journalRebuildRequired = DiskLruCache.this.journalRebuildRequired();
                        if (journalRebuildRequired) {
                            DiskLruCache.this.rebuildJournal$okhttp();
                            DiskLruCache.this.redundantOpCount = 0;
                        }
                    } catch (IOException unused2) {
                        DiskLruCache.this.mostRecentRebuildFailed = true;
                        DiskLruCache.this.journalWriter = Okio.buffer(Okio.blackhole());
                    }
                    return -1L;
                }
            }
        };
        if (j > 0) {
            if (this.valueCount <= 0 ? false : z) {
                this.journalFile = new File(this.directory, JOURNAL_FILE);
                this.journalFileTmp = new File(this.directory, JOURNAL_FILE_TEMP);
                this.journalFileBackup = new File(this.directory, JOURNAL_FILE_BACKUP);
                return;
            }
            throw new IllegalArgumentException("valueCount <= 0".toString());
        }
        throw new IllegalArgumentException("maxSize <= 0".toString());
    }

    private final synchronized void checkNotClosed() {
        if (!(!this.closed)) {
            throw new IllegalStateException("cache is closed".toString());
        }
    }

    public static /* synthetic */ Editor edit$default(DiskLruCache diskLruCache, String str, long j, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            j = ANY_SEQUENCE_NUMBER;
        }
        return diskLruCache.edit(str, j);
    }

    public final boolean journalRebuildRequired() {
        int i = this.redundantOpCount;
        return i >= 2000 && i >= this.lruEntries.size();
    }

    private final BufferedSink newJournalWriter() throws FileNotFoundException {
        return Okio.buffer(new FaultHidingSink(this.fileSystem.appendingSink(this.journalFile), new DiskLruCache$newJournalWriter$faultHidingSink$1(this)));
    }

    private final void processJournal() throws IOException {
        this.fileSystem.delete(this.journalFileTmp);
        Iterator<Entry> it2 = this.lruEntries.values().iterator();
        while (it2.hasNext()) {
            Entry next = it2.next();
            Intrinsics.checkExpressionValueIsNotNull(next, "i.next()");
            Entry entry = next;
            int i = 0;
            if (entry.getCurrentEditor$okhttp() == null) {
                int i2 = this.valueCount;
                while (i < i2) {
                    this.size += entry.getLengths$okhttp()[i];
                    i++;
                }
            } else {
                entry.setCurrentEditor$okhttp(null);
                int i3 = this.valueCount;
                while (i < i3) {
                    this.fileSystem.delete(entry.getCleanFiles$okhttp().get(i));
                    this.fileSystem.delete(entry.getDirtyFiles$okhttp().get(i));
                    i++;
                }
                it2.remove();
            }
        }
    }

    private final void readJournal() throws IOException {
        BufferedSource buffer = Okio.buffer(this.fileSystem.source(this.journalFile));
        try {
            String readUtf8LineStrict = buffer.readUtf8LineStrict();
            String readUtf8LineStrict2 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict3 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict4 = buffer.readUtf8LineStrict();
            String readUtf8LineStrict5 = buffer.readUtf8LineStrict();
            boolean z = true;
            if (!(!Intrinsics.areEqual(MAGIC, readUtf8LineStrict)) && !(!Intrinsics.areEqual(VERSION_1, readUtf8LineStrict2)) && !(!Intrinsics.areEqual(String.valueOf(this.appVersion), readUtf8LineStrict3)) && !(!Intrinsics.areEqual(String.valueOf(this.valueCount), readUtf8LineStrict4))) {
                int i = 0;
                if (readUtf8LineStrict5.length() <= 0) {
                    z = false;
                }
                if (!z) {
                    while (true) {
                        try {
                            readJournalLine(buffer.readUtf8LineStrict());
                            i++;
                        } catch (EOFException unused) {
                            this.redundantOpCount = i - this.lruEntries.size();
                            if (!buffer.exhausted()) {
                                rebuildJournal$okhttp();
                            } else {
                                this.journalWriter = newJournalWriter();
                            }
                            Unit unit = Unit.INSTANCE;
                            CloseableKt.closeFinally(buffer, null);
                            return;
                        }
                    }
                }
            }
            throw new IOException("unexpected journal header: [" + readUtf8LineStrict + ", " + readUtf8LineStrict2 + ", " + readUtf8LineStrict4 + ", " + readUtf8LineStrict5 + JsonReaderKt.END_LIST);
        } finally {
        }
    }

    private final void readJournalLine(String str) throws IOException {
        int indexOf$default;
        int indexOf$default2;
        String substring;
        boolean startsWith$default;
        boolean startsWith$default2;
        boolean startsWith$default3;
        List<String> split$default;
        boolean startsWith$default4;
        indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) str, (char) Chars.SPACE, 0, false, 6, (Object) null);
        if (indexOf$default != -1) {
            int i = indexOf$default + 1;
            indexOf$default2 = StringsKt__StringsKt.indexOf$default((CharSequence) str, (char) Chars.SPACE, i, false, 4, (Object) null);
            if (indexOf$default2 == -1) {
                if (str != null) {
                    substring = str.substring(i);
                    Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
                    if (indexOf$default == REMOVE.length()) {
                        startsWith$default4 = StringsKt__StringsJVMKt.startsWith$default(str, REMOVE, false, 2, null);
                        if (startsWith$default4) {
                            this.lruEntries.remove(substring);
                            return;
                        }
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
            } else if (str != null) {
                substring = str.substring(i, indexOf$default2);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            Entry entry = this.lruEntries.get(substring);
            if (entry == null) {
                entry = new Entry(this, substring);
                this.lruEntries.put(substring, entry);
            }
            if (indexOf$default2 != -1 && indexOf$default == CLEAN.length()) {
                startsWith$default3 = StringsKt__StringsJVMKt.startsWith$default(str, CLEAN, false, 2, null);
                if (startsWith$default3) {
                    String substring2 = str.substring(indexOf$default2 + 1);
                    Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
                    split$default = StringsKt__StringsKt.split$default((CharSequence) substring2, new char[]{Chars.SPACE}, false, 0, 6, (Object) null);
                    entry.setReadable$okhttp(true);
                    entry.setCurrentEditor$okhttp(null);
                    entry.setLengths$okhttp(split$default);
                    return;
                }
            }
            if (indexOf$default2 == -1 && indexOf$default == DIRTY.length()) {
                startsWith$default2 = StringsKt__StringsJVMKt.startsWith$default(str, DIRTY, false, 2, null);
                if (startsWith$default2) {
                    entry.setCurrentEditor$okhttp(new Editor(this, entry));
                    return;
                }
            }
            if (indexOf$default2 == -1 && indexOf$default == READ.length()) {
                startsWith$default = StringsKt__StringsJVMKt.startsWith$default(str, READ, false, 2, null);
                if (startsWith$default) {
                    return;
                }
            }
            throw new IOException(GeneratedOutlineSupport1.outline72("unexpected journal line: ", str));
        }
        throw new IOException(GeneratedOutlineSupport1.outline72("unexpected journal line: ", str));
    }

    private final void validateKey(String str) {
        if (LEGAL_KEY_PATTERN.matches(str)) {
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline73("keys must match regex [a-z0-9_-]{1,120}: \"", str, '\"').toString());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        Entry[] entryArr;
        if (this.initialized && !this.closed) {
            Collection<Entry> values = this.lruEntries.values();
            Intrinsics.checkExpressionValueIsNotNull(values, "lruEntries.values");
            Object[] array = values.toArray(new Entry[0]);
            if (array != null) {
                for (Entry entry : (Entry[]) array) {
                    if (entry.getCurrentEditor$okhttp() != null) {
                        Editor currentEditor$okhttp = entry.getCurrentEditor$okhttp();
                        if (currentEditor$okhttp == null) {
                            Intrinsics.throwNpe();
                        }
                        currentEditor$okhttp.abort();
                    }
                }
                trimToSize();
                BufferedSink bufferedSink = this.journalWriter;
                if (bufferedSink == null) {
                    Intrinsics.throwNpe();
                }
                bufferedSink.close();
                this.journalWriter = null;
                this.closed = true;
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        this.closed = true;
    }

    public final synchronized void completeEdit$okhttp(@NotNull Editor editor, boolean z) throws IOException {
        Intrinsics.checkParameterIsNotNull(editor, "editor");
        Entry entry$okhttp = editor.getEntry$okhttp();
        if (Intrinsics.areEqual(entry$okhttp.getCurrentEditor$okhttp(), editor)) {
            if (z && !entry$okhttp.getReadable$okhttp()) {
                int i = this.valueCount;
                for (int i2 = 0; i2 < i; i2++) {
                    boolean[] written$okhttp = editor.getWritten$okhttp();
                    if (written$okhttp == null) {
                        Intrinsics.throwNpe();
                    }
                    if (written$okhttp[i2]) {
                        if (!this.fileSystem.exists(entry$okhttp.getDirtyFiles$okhttp().get(i2))) {
                            editor.abort();
                            return;
                        }
                    } else {
                        editor.abort();
                        throw new IllegalStateException("Newly created entry didn't create value for index " + i2);
                    }
                }
            }
            int i3 = this.valueCount;
            for (int i4 = 0; i4 < i3; i4++) {
                File file = entry$okhttp.getDirtyFiles$okhttp().get(i4);
                if (z) {
                    if (this.fileSystem.exists(file)) {
                        File file2 = entry$okhttp.getCleanFiles$okhttp().get(i4);
                        this.fileSystem.rename(file, file2);
                        long j = entry$okhttp.getLengths$okhttp()[i4];
                        long size = this.fileSystem.size(file2);
                        entry$okhttp.getLengths$okhttp()[i4] = size;
                        this.size = (this.size - j) + size;
                    }
                } else {
                    this.fileSystem.delete(file);
                }
            }
            this.redundantOpCount++;
            entry$okhttp.setCurrentEditor$okhttp(null);
            BufferedSink bufferedSink = this.journalWriter;
            if (bufferedSink == null) {
                Intrinsics.throwNpe();
            }
            if (!entry$okhttp.getReadable$okhttp() && !z) {
                this.lruEntries.remove(entry$okhttp.getKey$okhttp());
                bufferedSink.mo12607writeUtf8(REMOVE).mo12596writeByte(32);
                bufferedSink.mo12607writeUtf8(entry$okhttp.getKey$okhttp());
                bufferedSink.mo12596writeByte(10);
                bufferedSink.flush();
                if (this.size <= this.maxSize || journalRebuildRequired()) {
                    TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
                }
                return;
            }
            entry$okhttp.setReadable$okhttp(true);
            bufferedSink.mo12607writeUtf8(CLEAN).mo12596writeByte(32);
            bufferedSink.mo12607writeUtf8(entry$okhttp.getKey$okhttp());
            entry$okhttp.writeLengths$okhttp(bufferedSink);
            bufferedSink.mo12596writeByte(10);
            if (z) {
                long j2 = this.nextSequenceNumber;
                this.nextSequenceNumber = 1 + j2;
                entry$okhttp.setSequenceNumber$okhttp(j2);
            }
            bufferedSink.flush();
            if (this.size <= this.maxSize) {
            }
            TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public final void delete() throws IOException {
        close();
        this.fileSystem.deleteContents(this.directory);
    }

    @JvmOverloads
    @Nullable
    public final Editor edit(@NotNull String str) throws IOException {
        return edit$default(this, str, 0L, 2, null);
    }

    @JvmOverloads
    @Nullable
    public final synchronized Editor edit(@NotNull String key, long j) throws IOException {
        Intrinsics.checkParameterIsNotNull(key, "key");
        initialize();
        checkNotClosed();
        validateKey(key);
        Entry entry = this.lruEntries.get(key);
        if (j == ANY_SEQUENCE_NUMBER || (entry != null && entry.getSequenceNumber$okhttp() == j)) {
            if ((entry != null ? entry.getCurrentEditor$okhttp() : null) != null) {
                return null;
            }
            if (!this.mostRecentTrimFailed && !this.mostRecentRebuildFailed) {
                BufferedSink bufferedSink = this.journalWriter;
                if (bufferedSink == null) {
                    Intrinsics.throwNpe();
                }
                bufferedSink.mo12607writeUtf8(DIRTY).mo12596writeByte(32).mo12607writeUtf8(key).mo12596writeByte(10);
                bufferedSink.flush();
                if (this.hasJournalErrors) {
                    return null;
                }
                if (entry == null) {
                    entry = new Entry(this, key);
                    this.lruEntries.put(key, entry);
                }
                Editor editor = new Editor(this, entry);
                entry.setCurrentEditor$okhttp(editor);
                return editor;
            }
            TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
            return null;
        }
        return null;
    }

    public final synchronized void evictAll() throws IOException {
        Entry[] entryArr;
        initialize();
        Collection<Entry> values = this.lruEntries.values();
        Intrinsics.checkExpressionValueIsNotNull(values, "lruEntries.values");
        Object[] array = values.toArray(new Entry[0]);
        if (array != null) {
            for (Entry entry : (Entry[]) array) {
                Intrinsics.checkExpressionValueIsNotNull(entry, "entry");
                removeEntry$okhttp(entry);
            }
            this.mostRecentTrimFailed = false;
        } else {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
    }

    @Override // java.io.Flushable
    public synchronized void flush() throws IOException {
        if (!this.initialized) {
            return;
        }
        checkNotClosed();
        trimToSize();
        BufferedSink bufferedSink = this.journalWriter;
        if (bufferedSink == null) {
            Intrinsics.throwNpe();
        }
        bufferedSink.flush();
    }

    @Nullable
    public final synchronized Snapshot get(@NotNull String key) throws IOException {
        Intrinsics.checkParameterIsNotNull(key, "key");
        initialize();
        checkNotClosed();
        validateKey(key);
        Entry entry = this.lruEntries.get(key);
        if (entry != null) {
            Intrinsics.checkExpressionValueIsNotNull(entry, "lruEntries[key] ?: return null");
            if (!entry.getReadable$okhttp()) {
                return null;
            }
            Snapshot snapshot$okhttp = entry.snapshot$okhttp();
            if (snapshot$okhttp == null) {
                return null;
            }
            this.redundantOpCount++;
            BufferedSink bufferedSink = this.journalWriter;
            if (bufferedSink == null) {
                Intrinsics.throwNpe();
            }
            bufferedSink.mo12607writeUtf8(READ).mo12596writeByte(32).mo12607writeUtf8(key).mo12596writeByte(10);
            if (journalRebuildRequired()) {
                TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
            }
            return snapshot$okhttp;
        }
        return null;
    }

    public final boolean getClosed$okhttp() {
        return this.closed;
    }

    @NotNull
    public final File getDirectory() {
        return this.directory;
    }

    @NotNull
    public final FileSystem getFileSystem$okhttp() {
        return this.fileSystem;
    }

    @NotNull
    public final LinkedHashMap<String, Entry> getLruEntries$okhttp() {
        return this.lruEntries;
    }

    public final synchronized long getMaxSize() {
        return this.maxSize;
    }

    public final int getValueCount$okhttp() {
        return this.valueCount;
    }

    public final synchronized void initialize() throws IOException {
        if (Util.assertionsEnabled && !Thread.holdsLock(this)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Thread ");
            Thread currentThread = Thread.currentThread();
            Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
            sb.append(currentThread.getName());
            sb.append(" MUST hold lock on ");
            sb.append(this);
            throw new AssertionError(sb.toString());
        }
        if (this.initialized) {
            return;
        }
        if (this.fileSystem.exists(this.journalFileBackup)) {
            if (this.fileSystem.exists(this.journalFile)) {
                this.fileSystem.delete(this.journalFileBackup);
            } else {
                this.fileSystem.rename(this.journalFileBackup, this.journalFile);
            }
        }
        if (this.fileSystem.exists(this.journalFile)) {
            try {
                readJournal();
                processJournal();
                this.initialized = true;
                return;
            } catch (IOException e) {
                Platform platform = Platform.Companion.get();
                platform.log("DiskLruCache " + this.directory + " is corrupt: " + e.getMessage() + ", removing", 5, e);
                delete();
                this.closed = false;
            }
        }
        rebuildJournal$okhttp();
        this.initialized = true;
    }

    public final synchronized boolean isClosed() {
        return this.closed;
    }

    public final synchronized void rebuildJournal$okhttp() throws IOException {
        BufferedSink bufferedSink = this.journalWriter;
        if (bufferedSink != null) {
            bufferedSink.close();
        }
        BufferedSink buffer = Okio.buffer(this.fileSystem.sink(this.journalFileTmp));
        buffer.mo12607writeUtf8(MAGIC).mo12596writeByte(10);
        buffer.mo12607writeUtf8(VERSION_1).mo12596writeByte(10);
        buffer.mo12597writeDecimalLong(this.appVersion).mo12596writeByte(10);
        buffer.mo12597writeDecimalLong(this.valueCount).mo12596writeByte(10);
        buffer.mo12596writeByte(10);
        for (Entry entry : this.lruEntries.values()) {
            if (entry.getCurrentEditor$okhttp() != null) {
                buffer.mo12607writeUtf8(DIRTY).mo12596writeByte(32);
                buffer.mo12607writeUtf8(entry.getKey$okhttp());
                buffer.mo12596writeByte(10);
            } else {
                buffer.mo12607writeUtf8(CLEAN).mo12596writeByte(32);
                buffer.mo12607writeUtf8(entry.getKey$okhttp());
                entry.writeLengths$okhttp(buffer);
                buffer.mo12596writeByte(10);
            }
        }
        Unit unit = Unit.INSTANCE;
        CloseableKt.closeFinally(buffer, null);
        if (this.fileSystem.exists(this.journalFile)) {
            this.fileSystem.rename(this.journalFile, this.journalFileBackup);
        }
        this.fileSystem.rename(this.journalFileTmp, this.journalFile);
        this.fileSystem.delete(this.journalFileBackup);
        this.journalWriter = newJournalWriter();
        this.hasJournalErrors = false;
        this.mostRecentRebuildFailed = false;
    }

    public final synchronized boolean remove(@NotNull String key) throws IOException {
        Intrinsics.checkParameterIsNotNull(key, "key");
        initialize();
        checkNotClosed();
        validateKey(key);
        Entry entry = this.lruEntries.get(key);
        if (entry != null) {
            Intrinsics.checkExpressionValueIsNotNull(entry, "lruEntries[key] ?: return false");
            boolean removeEntry$okhttp = removeEntry$okhttp(entry);
            if (removeEntry$okhttp && this.size <= this.maxSize) {
                this.mostRecentTrimFailed = false;
            }
            return removeEntry$okhttp;
        }
        return false;
    }

    public final boolean removeEntry$okhttp(@NotNull Entry entry) throws IOException {
        Intrinsics.checkParameterIsNotNull(entry, "entry");
        Editor currentEditor$okhttp = entry.getCurrentEditor$okhttp();
        if (currentEditor$okhttp != null) {
            currentEditor$okhttp.detach$okhttp();
        }
        int i = this.valueCount;
        for (int i2 = 0; i2 < i; i2++) {
            this.fileSystem.delete(entry.getCleanFiles$okhttp().get(i2));
            this.size -= entry.getLengths$okhttp()[i2];
            entry.getLengths$okhttp()[i2] = 0;
        }
        this.redundantOpCount++;
        BufferedSink bufferedSink = this.journalWriter;
        if (bufferedSink == null) {
            Intrinsics.throwNpe();
        }
        bufferedSink.mo12607writeUtf8(REMOVE).mo12596writeByte(32).mo12607writeUtf8(entry.getKey$okhttp()).mo12596writeByte(10);
        this.lruEntries.remove(entry.getKey$okhttp());
        if (journalRebuildRequired()) {
            TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
        }
        return true;
    }

    public final void setClosed$okhttp(boolean z) {
        this.closed = z;
    }

    public final synchronized void setMaxSize(long j) {
        this.maxSize = j;
        if (this.initialized) {
            TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0L, 2, null);
        }
    }

    public final synchronized long size() throws IOException {
        initialize();
        return this.size;
    }

    @NotNull
    public final synchronized Iterator<Snapshot> snapshots() throws IOException {
        initialize();
        return new DiskLruCache$snapshots$1(this);
    }

    public final void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            Entry next = this.lruEntries.values().iterator().next();
            Intrinsics.checkExpressionValueIsNotNull(next, "lruEntries.values.iterator().next()");
            removeEntry$okhttp(next);
        }
        this.mostRecentTrimFailed = false;
    }
}
