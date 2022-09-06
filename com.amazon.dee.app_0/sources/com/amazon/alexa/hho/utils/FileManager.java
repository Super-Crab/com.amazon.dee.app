package com.amazon.alexa.hho.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public class FileManager {
    public void createDirectoryIfNeeded(@Nonnull String str) {
        File createFileObject = createFileObject(str);
        if (!createFileObject.exists() || !createFileObject.isDirectory()) {
            createFileObject.delete();
            createFileObject.mkdirs();
        }
    }

    @Nonnull
    File createFileObject(@Nonnull String str) {
        return new File(str);
    }

    @Nonnull
    public OutputStream createFileOutputStream(@Nonnull String str) throws FileNotFoundException {
        return new FileOutputStream(str);
    }

    public void deleteDirectory(@Nonnull String str) {
        File createFileObject = createFileObject(str);
        File[] listFiles = createFileObject.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                file.delete();
            }
        }
        createFileObject.delete();
    }

    public void deleteFile(@Nonnull String str) {
        if (fileExistsAtPath(str)) {
            createFileObject(str).delete();
        }
    }

    @Nullable
    public File[] directoryFiles(@Nonnull String str) {
        return createFileObject(str).listFiles();
    }

    public boolean fileExistsAtPath(@Nonnull String str) {
        File createFileObject = createFileObject(str);
        return createFileObject.exists() && createFileObject.isFile();
    }

    public void moveFile(@Nonnull String str, @Nonnull String str2) {
        if (!fileExistsAtPath(str)) {
            return;
        }
        if (fileExistsAtPath(str2)) {
            deleteFile(str2);
        }
        createFileObject(str).renameTo(createFileObject(str2));
    }

    public void touchFile(@Nonnull String str) {
        createFileObject(str).setLastModified(new Date().getTime());
    }

    public void writeToFile(@Nonnull String str, @Nonnull byte[] bArr) throws IOException {
        OutputStream outputStream;
        try {
            outputStream = createFileOutputStream(str);
        } catch (Throwable th) {
            th = th;
            outputStream = null;
        }
        try {
            outputStream.write(bArr);
            outputStream.flush();
            IOUtils.closeQuietly(outputStream);
        } catch (Throwable th2) {
            th = th2;
            IOUtils.closeQuietly(outputStream);
            throw th;
        }
    }
}
