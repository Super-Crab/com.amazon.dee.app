package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery;

import android.util.Log;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.AnalyticsContext;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.FileManager;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.EventStore;
import com.amazonaws.util.StringUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.concurrent.locks.ReentrantLock;
@Deprecated
/* loaded from: classes13.dex */
public class FileEventStore implements EventStore {
    static final double ERROR_LENGTH_THRESHOLD_PERCENTAGE = 1.1d;
    static final String EVENTS_DIRECTORY = "events";
    static final String EVENT_FILE_NAME = "eventsFile";
    static final String KEY_MAX_STORAGE_SIZE = "maxStorageSize";
    static long MAX_STORAGE_SIZE = 5242880;
    private static final String TAG = "FileEventStore";
    private final ReentrantLock accessLock = new ReentrantLock(true);
    private final AnalyticsContext context;
    private File eventsFile;

    public FileEventStore(AnalyticsContext analyticsContext) {
        this.context = analyticsContext;
        tryCreateEventsFile();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00c1, code lost:
        if (r14 != null) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00c3, code lost:
        r14.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00d6, code lost:
        if (r14 != null) goto L62;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:112:0x00fb A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0100  */
    /* JADX WARN: Type inference failed for: r11v10, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v12 */
    /* JADX WARN: Type inference failed for: r11v14 */
    /* JADX WARN: Type inference failed for: r11v3, types: [java.io.PrintWriter] */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.io.File deleteReadEvents(int r18) {
        /*
            Method dump skipped, instructions count: 308
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.FileEventStore.deleteReadEvents(int):java.io.File");
    }

    public static FileEventStore newInstance(AnalyticsContext analyticsContext) {
        return new FileEventStore(analyticsContext);
    }

    private void tryCloseWriter(Writer writer) throws EventStoreException {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                Log.e(TAG, "Unable to close writer for events file", e);
            }
        }
    }

    private boolean tryCreateEventsFile() {
        File file = this.eventsFile;
        if (file == null || !file.exists()) {
            synchronized (this) {
                if (this.eventsFile != null && this.eventsFile.exists()) {
                    return true;
                }
                try {
                    FileManager fileManager = this.context.getSystem().getFileManager();
                    this.eventsFile = fileManager.createFile(new File(fileManager.createDirectory("events"), EVENT_FILE_NAME));
                    return true;
                } catch (IOException e) {
                    Log.e(TAG, "Unable to open events file");
                    Log.e(TAG, "An error occurred while attempting to create/open the events file", e);
                    return false;
                }
            }
        }
        return true;
    }

    private BufferedWriter tryInitializeWriter() throws EventStoreException {
        try {
            if (tryCreateEventsFile()) {
                return new BufferedWriter(new OutputStreamWriter(this.context.getSystem().getFileManager().newOutputStream(this.eventsFile, true), StringUtils.UTF8));
            }
            throw new EventStoreException("Unable to create eventsFile");
        } catch (EventStoreException e) {
            throw e;
        } catch (FileNotFoundException e2) {
            Log.e(TAG, "Events file not found to persist event to", e2);
            throw new EventStoreException("Unable to open events file writer", e2);
        } catch (Exception e3) {
            Log.e(TAG, "Unexpected exception", e3);
            throw new EventStoreException("Unexpected error while creating eventsFile writer", e3);
        }
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.EventStore
    public EventStore.EventIterator iterator() {
        return new EventStore.EventIterator() { // from class: com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.FileEventStore.1
            int linesRead = 0;
            String nextBuffer = null;
            BufferedReader reader = null;
            boolean isEndOfFile = false;

            private void resetReader() {
                tryCloseReader();
                this.linesRead = 0;
                this.nextBuffer = null;
            }

            private void tryCloseReader() {
                BufferedReader bufferedReader = this.reader;
                if (bufferedReader != null) {
                    try {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            Log.e(FileEventStore.TAG, "Unable to close reader for events file", e);
                        }
                    } finally {
                        this.reader = null;
                    }
                }
            }

            private boolean tryOpenReader() {
                if (this.reader != null) {
                    return true;
                }
                if (this.isEndOfFile) {
                    return false;
                }
                InputStreamReader inputStreamReader = null;
                try {
                    inputStreamReader = new InputStreamReader(FileEventStore.this.context.getSystem().getFileManager().newInputStream(FileEventStore.this.eventsFile), StringUtils.UTF8);
                } catch (FileNotFoundException e) {
                    Log.e(FileEventStore.TAG, "Could not open the events file", e);
                }
                if (inputStreamReader == null) {
                    return false;
                }
                this.reader = new BufferedReader(inputStreamReader);
                return true;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                FileEventStore.this.accessLock.lock();
                try {
                    boolean z = true;
                    if (this.nextBuffer == null) {
                        if (!tryOpenReader()) {
                            return false;
                        }
                        for (boolean z2 = false; !z2; z2 = true) {
                            try {
                                this.nextBuffer = this.reader.readLine();
                            } catch (IOException unused) {
                                this.nextBuffer = null;
                            }
                        }
                        if (this.nextBuffer == null) {
                            this.isEndOfFile = true;
                            tryCloseReader();
                            z = false;
                        }
                    }
                    return z;
                } finally {
                    FileEventStore.this.accessLock.unlock();
                }
            }

            @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.EventStore.EventIterator
            public String peek() {
                FileEventStore.this.accessLock.lock();
                try {
                    hasNext();
                    return this.nextBuffer;
                } finally {
                    FileEventStore.this.accessLock.unlock();
                }
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("The remove() operation is not supported for this iterator");
            }

            @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.EventStore.EventIterator
            public void removeReadEvents() {
                FileEventStore.this.accessLock.lock();
                try {
                    FileEventStore.this.deleteReadEvents(this.linesRead);
                    resetReader();
                } finally {
                    FileEventStore.this.accessLock.unlock();
                }
            }

            @Override // java.util.Iterator
            public String next() {
                String str;
                FileEventStore.this.accessLock.lock();
                try {
                    if (this.nextBuffer != null) {
                        str = this.nextBuffer;
                        this.linesRead++;
                        this.nextBuffer = null;
                    } else if (!tryOpenReader()) {
                        return null;
                    } else {
                        String str2 = null;
                        for (boolean z = false; !z; z = true) {
                            try {
                                str2 = this.reader.readLine();
                            } catch (IOException unused) {
                                str2 = null;
                            }
                        }
                        if (str2 != null) {
                            this.linesRead++;
                        } else {
                            this.isEndOfFile = true;
                            tryCloseReader();
                        }
                        str = str2;
                    }
                    return str;
                } finally {
                    FileEventStore.this.accessLock.unlock();
                }
            }
        };
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.EventStore
    public boolean put(String str) throws EventStoreException {
        this.accessLock.lock();
        boolean z = false;
        BufferedWriter bufferedWriter = null;
        try {
            try {
                bufferedWriter = tryInitializeWriter();
                if (bufferedWriter != null) {
                    if (this.eventsFile.length() + str.length() <= this.context.getConfiguration().optLong(KEY_MAX_STORAGE_SIZE, Long.valueOf(MAX_STORAGE_SIZE)).longValue()) {
                        bufferedWriter.write(str);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                        z = true;
                    }
                }
            } catch (IOException e) {
                Log.e(TAG, "Failed to persist the event", e);
            }
            return z;
        } finally {
            tryCloseWriter(bufferedWriter);
            this.accessLock.unlock();
        }
    }
}
