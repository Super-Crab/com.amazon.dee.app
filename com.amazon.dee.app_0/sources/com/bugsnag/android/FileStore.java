package com.bugsnag.android;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bugsnag.android.JsonStream;
import com.bugsnag.android.JsonStream.Streamable;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class FileStore<T extends JsonStream.Streamable> {
    private final Comparator<File> comparator;
    @NonNull
    protected final Configuration config;
    protected final Delegate delegate;
    private final int maxStoreCount;
    @Nullable
    final String storeDirectory;
    final Lock lock = new ReentrantLock();
    final Collection<File> queuedFiles = new ConcurrentSkipListSet();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface Delegate {
        void onErrorIOFailure(Exception exc, File file, String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FileStore(@NonNull Configuration configuration, @NonNull Context context, String str, int i, Comparator<File> comparator, Delegate delegate) {
        this.config = configuration;
        this.maxStoreCount = i;
        this.comparator = comparator;
        this.delegate = delegate;
        String str2 = null;
        try {
            String str3 = context.getCacheDir().getAbsolutePath() + str;
            File file = new File(str3);
            file.mkdirs();
            if (!file.exists()) {
                Logger.warn("Could not prepare file storage directory");
            } else {
                str2 = str3;
            }
        } catch (Exception e) {
            Logger.warn("Could not prepare file storage directory", e);
        }
        this.storeDirectory = str2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cancelQueuedFiles(Collection<File> collection) {
        this.lock.lock();
        if (collection != null) {
            try {
                this.queuedFiles.removeAll(collection);
            } finally {
                this.lock.unlock();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void deleteStoredFiles(Collection<File> collection) {
        this.lock.lock();
        if (collection != null) {
            try {
                this.queuedFiles.removeAll(collection);
                for (File file : collection) {
                    if (!file.delete()) {
                        file.deleteOnExit();
                    }
                }
            } finally {
                this.lock.unlock();
            }
        }
    }

    void discardOldestFileIfNeeded() {
        File[] listFiles;
        File file = new File(this.storeDirectory);
        if (!file.isDirectory() || (listFiles = file.listFiles()) == null || listFiles.length < this.maxStoreCount) {
            return;
        }
        Arrays.sort(listFiles, this.comparator);
        for (int i = 0; i < listFiles.length && listFiles.length >= this.maxStoreCount; i++) {
            File file2 = listFiles[i];
            if (!this.queuedFiles.contains(file2)) {
                Logger.warn(String.format("Discarding oldest error as stored error limit reached (%s)", file2.getPath()));
                deleteStoredFiles(Collections.singleton(file2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void enqueueContentForDelivery(String str) {
        String format;
        BufferedWriter bufferedWriter;
        if (this.storeDirectory == null) {
            return;
        }
        String filename = getFilename(str);
        discardOldestFileIfNeeded();
        this.lock.lock();
        BufferedWriter bufferedWriter2 = null;
        try {
            try {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8"));
            } catch (Exception e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            bufferedWriter.write(str);
            try {
                bufferedWriter.close();
            } catch (Exception e2) {
                e = e2;
                format = String.format("Failed to close unsent payload writer (%s) ", filename);
                Logger.warn(format, e);
                this.lock.unlock();
            }
        } catch (Exception e3) {
            e = e3;
            bufferedWriter2 = bufferedWriter;
            File file = new File(filename);
            if (this.delegate != null) {
                this.delegate.onErrorIOFailure(e, file, "NDK Crash report copy");
            }
            IOUtils.deleteFile(file);
            if (bufferedWriter2 != null) {
                try {
                    bufferedWriter2.close();
                } catch (Exception e4) {
                    e = e4;
                    format = String.format("Failed to close unsent payload writer (%s) ", filename);
                    Logger.warn(format, e);
                    this.lock.unlock();
                }
            }
            this.lock.unlock();
        } catch (Throwable th2) {
            th = th2;
            bufferedWriter2 = bufferedWriter;
            if (bufferedWriter2 != null) {
                try {
                    bufferedWriter2.close();
                } catch (Exception e5) {
                    Logger.warn(String.format("Failed to close unsent payload writer (%s) ", filename), e5);
                }
            }
            this.lock.unlock();
            throw th;
        }
        this.lock.unlock();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<File> findStoredFiles() {
        File[] listFiles;
        this.lock.lock();
        try {
            ArrayList arrayList = new ArrayList();
            if (this.storeDirectory != null) {
                File file = new File(this.storeDirectory);
                if (file.exists() && file.isDirectory() && (listFiles = file.listFiles()) != null) {
                    for (File file2 : listFiles) {
                        if (file2.length() == 0) {
                            if (!file2.delete()) {
                                file2.deleteOnExit();
                            }
                        } else if (file2.isFile() && !this.queuedFiles.contains(file2)) {
                            arrayList.add(file2);
                        }
                    }
                }
            }
            this.queuedFiles.addAll(arrayList);
            return arrayList;
        } finally {
            this.lock.unlock();
        }
    }

    @NonNull
    abstract String getFilename(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.util.concurrent.locks.Lock] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.Closeable] */
    @Nullable
    public String write(@NonNull JsonStream.Streamable streamable) {
        JsonStream jsonStream;
        if (this.storeDirectory == null) {
            return null;
        }
        discardOldestFileIfNeeded();
        String filename = getFilename(streamable);
        ?? r2 = this.lock;
        r2.lock();
        try {
            try {
                jsonStream = new JsonStream(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "UTF-8")));
                try {
                    jsonStream.value(streamable);
                    Logger.info(String.format("Saved unsent payload to disk (%s) ", filename));
                    IOUtils.closeQuietly(jsonStream);
                    this.lock.unlock();
                    return filename;
                } catch (FileNotFoundException e) {
                    e = e;
                    Logger.warn("Ignoring FileNotFoundException - unable to create file", e);
                    IOUtils.closeQuietly(jsonStream);
                    this.lock.unlock();
                    return null;
                } catch (Exception e2) {
                    e = e2;
                    File file = new File(filename);
                    if (this.delegate != null) {
                        this.delegate.onErrorIOFailure(e, file, "Crash report serialization");
                    }
                    IOUtils.deleteFile(file);
                    IOUtils.closeQuietly(jsonStream);
                    this.lock.unlock();
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                IOUtils.closeQuietly(r2);
                this.lock.unlock();
                throw th;
            }
        } catch (FileNotFoundException e3) {
            e = e3;
            jsonStream = null;
        } catch (Exception e4) {
            e = e4;
            jsonStream = null;
        } catch (Throwable th2) {
            th = th2;
            r2 = 0;
            IOUtils.closeQuietly(r2);
            this.lock.unlock();
            throw th;
        }
    }
}
