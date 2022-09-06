package com.amazonaws.mobileconnectors.kinesis.kinesisrecorder;

import com.amazonaws.AmazonClientException;
import com.amazonaws.logging.Log;
import com.amazonaws.logging.LogFactory;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.cache.disk.DefaultDiskStorage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantLock;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes13.dex */
public class FileRecordStore {
    private static final Log LOGGER = LogFactory.getLog(FileRecordStore.class);
    private final ReentrantLock accessLock = new ReentrantLock(true);
    private final FileManager fileManager;
    private final long maxStorageSize;
    private File recordFile;
    private final String recordFileName;

    /* loaded from: classes13.dex */
    public class RecordIterator implements Iterator<String> {
        int linesRead = 0;
        String nextBuffer = null;
        BufferedReader reader = null;
        boolean isEndOfFile = false;

        public RecordIterator() {
        }

        private void resetReader() throws IOException {
            tryCloseReader();
            this.linesRead = 0;
            this.nextBuffer = null;
            this.isEndOfFile = false;
        }

        private void tryCloseReader() throws IOException {
            BufferedReader bufferedReader = this.reader;
            if (bufferedReader != null) {
                bufferedReader.close();
                this.reader = null;
            }
        }

        private boolean tryOpenReader() throws FileNotFoundException {
            if (this.reader != null) {
                return true;
            }
            if (this.isEndOfFile) {
                return false;
            }
            this.reader = new BufferedReader(new InputStreamReader(FileRecordStore.this.fileManager.newInputStream(FileRecordStore.this.recordFile), StringUtils.UTF8));
            return true;
        }

        public void close() throws IOException {
            tryCloseReader();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            FileRecordStore.this.accessLock.lock();
            try {
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
                    FileRecordStore.this.accessLock.unlock();
                }
            } catch (FileNotFoundException e) {
                throw new AmazonClientException("Cannot find records file", e);
            } catch (IOException e2) {
                throw new AmazonClientException("IO Error", e2);
            }
        }

        public String peek() {
            FileRecordStore.this.accessLock.lock();
            try {
                hasNext();
                return this.nextBuffer;
            } finally {
                FileRecordStore.this.accessLock.unlock();
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("The remove() operation is not supported for this iterator");
        }

        public void removeAllRecords() throws IOException {
            FileRecordStore.this.accessLock.lock();
            try {
                FileRecordStore.this.deleteAllRecords();
                resetReader();
            } finally {
                FileRecordStore.this.accessLock.unlock();
            }
        }

        public void removeReadRecords() throws IOException {
            FileRecordStore.this.accessLock.lock();
            try {
                FileRecordStore.this.deleteReadRecords(this.linesRead);
                resetReader();
            } finally {
                FileRecordStore.this.accessLock.unlock();
            }
        }

        @Override // java.util.Iterator
        public String next() {
            String str;
            FileRecordStore.this.accessLock.lock();
            try {
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
                } catch (FileNotFoundException e) {
                    throw new AmazonClientException("Cannot find records file", e);
                } catch (IOException e2) {
                    throw new AmazonClientException("IO Error", e2);
                }
            } finally {
                FileRecordStore.this.accessLock.unlock();
            }
        }
    }

    public FileRecordStore(File file, String str, long j) {
        this.fileManager = new FileManager(file);
        this.recordFileName = str;
        this.maxStorageSize = j;
        try {
            tryCreateRecordsFile();
        } catch (IOException e) {
            throw new AmazonClientException("Failed to create file store", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File deleteAllRecords() throws IOException {
        File createDirectory = this.fileManager.createDirectory(Constants.RECORDS_DIRECTORY);
        this.recordFile.delete();
        this.recordFile = this.fileManager.createFile(new File(createDirectory, this.recordFileName));
        return this.recordFile;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File deleteReadRecords(int i) throws IOException {
        BufferedReader bufferedReader;
        File file = new File(this.fileManager.createDirectory(Constants.RECORDS_DIRECTORY), GeneratedOutlineSupport1.outline91(new StringBuilder(), this.recordFileName, DefaultDiskStorage.FileType.TEMP));
        if (file.exists() && !file.delete()) {
            throw new IOException("Failed to delete previous temp file");
        }
        File createFile = this.fileManager.createFile(file);
        if (createFile != null && this.recordFile.exists() && createFile.exists()) {
            PrintWriter printWriter = null;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.recordFile), StringUtils.UTF8));
                try {
                    PrintWriter printWriter2 = new PrintWriter(new OutputStreamWriter(new FileOutputStream(createFile, true), StringUtils.UTF8));
                    int i2 = 0;
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            i2++;
                            if (i2 > i) {
                                printWriter2.println(readLine);
                                printWriter2.flush();
                            }
                        } catch (Throwable th) {
                            th = th;
                            printWriter = printWriter2;
                            if (printWriter != null) {
                                printWriter.close();
                            }
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException e) {
                                    LOGGER.error("failed to close reader", e);
                                }
                            }
                            if (this.recordFile.delete() && createFile.renameTo(this.recordFile)) {
                                throw th;
                            }
                            throw new IOException("Failed to delete read records and persist unread records");
                        }
                    }
                    printWriter2.close();
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        LOGGER.error("failed to close reader", e2);
                    }
                    if (this.recordFile.delete() && createFile.renameTo(this.recordFile)) {
                        if (file.exists() && !file.delete()) {
                            LOGGER.error("Failed to delete temp file");
                        }
                    } else {
                        throw new IOException("Failed to delete read records and persist unread records");
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
            }
        }
        return this.recordFile;
    }

    private void tryCreateRecordsFile() throws IOException {
        File file = this.recordFile;
        if (file == null || !file.exists()) {
            synchronized (this) {
                if (this.recordFile != null && this.recordFile.exists()) {
                    return;
                }
                this.recordFile = this.fileManager.createFile(new File(this.fileManager.createDirectory(Constants.RECORDS_DIRECTORY), this.recordFileName));
            }
        }
    }

    private BufferedWriter tryInitializeWriter() throws IOException {
        tryCreateRecordsFile();
        return new BufferedWriter(new OutputStreamWriter(this.fileManager.newOutputStream(this.recordFile, true), StringUtils.UTF8));
    }

    public long getFileSize() {
        File file = this.recordFile;
        if (file == null) {
            return 0L;
        }
        return file.length();
    }

    public RecordIterator iterator() {
        return new RecordIterator();
    }

    public boolean put(String str) throws IOException {
        BufferedWriter bufferedWriter;
        boolean z;
        this.accessLock.lock();
        try {
            bufferedWriter = tryInitializeWriter();
            try {
                if (this.recordFile.length() + str.getBytes(StringUtils.UTF8).length <= this.maxStorageSize) {
                    bufferedWriter.write(str);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                    z = true;
                } else {
                    z = false;
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                this.accessLock.unlock();
                return z;
            } catch (Throwable th) {
                th = th;
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                this.accessLock.unlock();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedWriter = null;
        }
    }
}
