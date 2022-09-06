package org.apache.commons.io.output;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
/* loaded from: classes4.dex */
public class LockableFileWriter extends Writer {
    private static final String LCK = ".lck";
    private final File lockFile;
    private final Writer out;

    public LockableFileWriter(String str) throws IOException {
        this(str, false, (String) null);
    }

    private void createLock() throws IOException {
        synchronized (LockableFileWriter.class) {
            if (this.lockFile.createNewFile()) {
                this.lockFile.deleteOnExit();
            } else {
                throw new IOException("Can't write file, lock " + this.lockFile.getAbsolutePath() + " exists");
            }
        }
    }

    private Writer initWriter(File file, String str, boolean z) throws IOException {
        FileOutputStream fileOutputStream;
        boolean exists = file.exists();
        try {
            if (str == null) {
                return new FileWriter(file.getAbsolutePath(), z);
            }
            fileOutputStream = new FileOutputStream(file.getAbsolutePath(), z);
            try {
                return new OutputStreamWriter(fileOutputStream, str);
            } catch (IOException e) {
                e = e;
                IOUtils.closeQuietly((Writer) null);
                IOUtils.closeQuietly((OutputStream) fileOutputStream);
                FileUtils.deleteQuietly(this.lockFile);
                if (!exists) {
                    FileUtils.deleteQuietly(file);
                }
                throw e;
            } catch (RuntimeException e2) {
                e = e2;
                IOUtils.closeQuietly((Writer) null);
                IOUtils.closeQuietly((OutputStream) fileOutputStream);
                FileUtils.deleteQuietly(this.lockFile);
                if (!exists) {
                    FileUtils.deleteQuietly(file);
                }
                throw e;
            }
        } catch (IOException e3) {
            e = e3;
            fileOutputStream = null;
        } catch (RuntimeException e4) {
            e = e4;
            fileOutputStream = null;
        }
    }

    private void testLockDir(File file) throws IOException {
        if (file.exists()) {
            if (file.canWrite()) {
                return;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Could not write to lockDir: ");
            outline107.append(file.getAbsolutePath());
            throw new IOException(outline107.toString());
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Could not find lockDir: ");
        outline1072.append(file.getAbsolutePath());
        throw new IOException(outline1072.toString());
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            this.out.close();
        } finally {
            this.lockFile.delete();
        }
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override // java.io.Writer
    public void write(int i) throws IOException {
        this.out.write(i);
    }

    public LockableFileWriter(String str, boolean z) throws IOException {
        this(str, z, (String) null);
    }

    @Override // java.io.Writer
    public void write(char[] cArr) throws IOException {
        this.out.write(cArr);
    }

    public LockableFileWriter(String str, boolean z, String str2) throws IOException {
        this(new File(str), z, str2);
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) throws IOException {
        this.out.write(cArr, i, i2);
    }

    public LockableFileWriter(File file) throws IOException {
        this(file, false, (String) null);
    }

    @Override // java.io.Writer
    public void write(String str) throws IOException {
        this.out.write(str);
    }

    public LockableFileWriter(File file, boolean z) throws IOException {
        this(file, z, (String) null);
    }

    @Override // java.io.Writer
    public void write(String str, int i, int i2) throws IOException {
        this.out.write(str, i, i2);
    }

    public LockableFileWriter(File file, boolean z, String str) throws IOException {
        this(file, null, z, str);
    }

    public LockableFileWriter(File file, String str) throws IOException {
        this(file, str, false, null);
    }

    public LockableFileWriter(File file, String str, boolean z, String str2) throws IOException {
        File absoluteFile = file.getAbsoluteFile();
        if (absoluteFile.getParentFile() != null) {
            FileUtils.forceMkdir(absoluteFile.getParentFile());
        }
        if (!absoluteFile.isDirectory()) {
            File file2 = new File(str2 == null ? System.getProperty("java.io.tmpdir") : str2);
            FileUtils.forceMkdir(file2);
            testLockDir(file2);
            this.lockFile = new File(file2, absoluteFile.getName() + LCK);
            createLock();
            this.out = initWriter(absoluteFile, str, z);
            return;
        }
        throw new IOException("File specified is a directory");
    }
}
