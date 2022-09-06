package org.apache.commons.io.input;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
/* loaded from: classes4.dex */
public class Tailer implements Runnable {
    private final long delay;
    private final boolean end;
    private final File file;
    private final TailerListener listener;
    private volatile boolean run;

    public Tailer(File file, TailerListener tailerListener) {
        this(file, tailerListener, 1000L);
    }

    public static Tailer create(File file, TailerListener tailerListener, long j, boolean z) {
        Tailer tailer = new Tailer(file, tailerListener, j, z);
        Thread thread = new Thread(tailer);
        thread.setDaemon(true);
        thread.start();
        return tailer;
    }

    private String readLine(RandomAccessFile randomAccessFile) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = false;
        while (true) {
            int read = randomAccessFile.read();
            if (read != -1) {
                if (read == 10) {
                    return stringBuffer.toString();
                }
                if (read != 13) {
                    if (z) {
                        stringBuffer.append('\r');
                        z = false;
                    }
                    stringBuffer.append((char) read);
                } else {
                    z = true;
                }
            } else {
                return null;
            }
        }
    }

    private long readLines(RandomAccessFile randomAccessFile) throws IOException {
        long filePointer = randomAccessFile.getFilePointer();
        String readLine = readLine(randomAccessFile);
        while (readLine != null) {
            filePointer = randomAccessFile.getFilePointer();
            this.listener.handle(readLine);
            readLine = readLine(randomAccessFile);
        }
        randomAccessFile.seek(filePointer);
        return filePointer;
    }

    public long getDelay() {
        return this.delay;
    }

    public File getFile() {
        return this.file;
    }

    @Override // java.lang.Runnable
    public void run() {
        RandomAccessFile randomAccessFile;
        RandomAccessFile randomAccessFile2 = null;
        long j = 0;
        long j2 = 0;
        while (this.run && randomAccessFile2 == null) {
            try {
                try {
                    try {
                        randomAccessFile2 = new RandomAccessFile(this.file, "r");
                    } catch (FileNotFoundException unused) {
                        this.listener.fileNotFound();
                    }
                    if (randomAccessFile2 == null) {
                        try {
                            Thread.sleep(this.delay);
                        } catch (InterruptedException unused2) {
                        }
                    } else {
                        j2 = this.end ? this.file.length() : 0L;
                        j = System.currentTimeMillis();
                        randomAccessFile2.seek(j2);
                    }
                } catch (Exception e) {
                    e = e;
                }
            } catch (Throwable th) {
                th = th;
            }
        }
        while (this.run) {
            int i = (this.file.length() > j2 ? 1 : (this.file.length() == j2 ? 0 : -1));
            if (i < 0) {
                this.listener.fileRotated();
                try {
                    randomAccessFile = new RandomAccessFile(this.file, "r");
                } catch (FileNotFoundException unused3) {
                }
                try {
                    IOUtils.closeQuietly(randomAccessFile2);
                    j2 = 0;
                    randomAccessFile2 = randomAccessFile;
                } catch (FileNotFoundException unused4) {
                    j2 = 0;
                    randomAccessFile2 = randomAccessFile;
                    this.listener.fileNotFound();
                } catch (Exception e2) {
                    e = e2;
                    randomAccessFile2 = randomAccessFile;
                    this.listener.handle(e);
                    IOUtils.closeQuietly(randomAccessFile2);
                } catch (Throwable th2) {
                    th = th2;
                    randomAccessFile2 = randomAccessFile;
                    IOUtils.closeQuietly(randomAccessFile2);
                    throw th;
                }
            } else {
                if (i > 0) {
                    j = System.currentTimeMillis();
                    j2 = readLines(randomAccessFile2);
                } else if (FileUtils.isFileNewer(this.file, j)) {
                    randomAccessFile2.seek(0L);
                    j = System.currentTimeMillis();
                    j2 = readLines(randomAccessFile2);
                }
                try {
                    Thread.sleep(this.delay);
                } catch (InterruptedException unused5) {
                }
            }
        }
        IOUtils.closeQuietly(randomAccessFile2);
    }

    public void stop() {
        this.run = false;
    }

    public Tailer(File file, TailerListener tailerListener, long j) {
        this(file, tailerListener, j, false);
    }

    public Tailer(File file, TailerListener tailerListener, long j, boolean z) {
        this.run = true;
        this.file = file;
        this.delay = j;
        this.end = z;
        this.listener = tailerListener;
        tailerListener.init(this);
    }

    public static Tailer create(File file, TailerListener tailerListener, long j) {
        return create(file, tailerListener, j, false);
    }

    public static Tailer create(File file, TailerListener tailerListener) {
        return create(file, tailerListener, 1000L, false);
    }
}
