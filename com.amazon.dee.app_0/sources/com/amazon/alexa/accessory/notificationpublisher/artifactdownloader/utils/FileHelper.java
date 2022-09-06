package com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.utils;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
/* loaded from: classes.dex */
public final class FileHelper {
    private static final int BUFFER_SIZE = 1024;
    private static final String TAG = "FileHelper";

    private FileHelper() {
    }

    @RequiresApi(api = 26)
    public static boolean deleteDirectory(String str) {
        try {
            Files.walkFileTree(Paths.get(str, new String[0]), new SimpleFileVisitor<Path>() { // from class: com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.utils.FileHelper.1
                @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                public FileVisitResult postVisitDirectory(Path path, @Nullable IOException iOException) throws IOException {
                    String str2 = FileHelper.TAG;
                    Log.d(str2, "deleteDirectory: Deleting directory: " + path);
                    if (iOException == null) {
                        Files.delete(path);
                        return FileVisitResult.CONTINUE;
                    }
                    throw iOException;
                }

                @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                public FileVisitResult visitFile(Path path, BasicFileAttributes basicFileAttributes) throws IOException {
                    String str2 = FileHelper.TAG;
                    Log.d(str2, "deleteDirectory: Deleting file: " + path);
                    Files.delete(path);
                    return FileVisitResult.CONTINUE;
                }
            });
            return true;
        } catch (IOException e) {
            Log.e(TAG, "deleteDirectory: Unable to delete contents of %s, errorMessage", str, e.getMessage());
            return false;
        }
    }

    public static boolean deleteFile(String str) {
        try {
            Log.d(TAG, "deleteFile: Deleting File: %s", str);
            new File(str).delete();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "deleteFile: Unable to delete file: %s, errorMessage: %s", str, e.getMessage());
            return false;
        }
    }

    public static void logDirectoryContents(String str) {
        File[] listFiles;
        Log.d(TAG, "printDirectoryContents: directory path: %s", str);
        try {
            for (File file : new File(str).listFiles()) {
                if (file.isDirectory()) {
                    Log.d(TAG, "directory: %s", file.getCanonicalPath());
                    logDirectoryContents(file.getCanonicalPath());
                } else {
                    Log.d(TAG, "file: %s", file.getCanonicalPath());
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "Error in logging directory contents, errorMessage: %s", e.getMessage());
        }
    }

    public static boolean moveFile(String str, String str2, String str3) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        File file = new File(str3);
        boolean z = true;
        if (!(!file.exists() ? file.mkdirs() : true)) {
            Log.e(TAG, "Unable to create directory.");
            return false;
        }
        try {
            try {
                fileInputStream = new FileInputStream(str + "/" + str2);
                try {
                    fileOutputStream = new FileOutputStream(str3 + "/" + str2);
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileInputStream.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    new File(str + "/" + str2).delete();
                } catch (Throwable th) {
                    th = th;
                }
            } catch (FileNotFoundException e) {
                e = e;
                z = false;
                Log.e(TAG, e.getMessage());
                return z;
            } catch (Exception e2) {
                e = e2;
                z = false;
                Log.e(TAG, e.getMessage());
                return z;
            }
        } catch (FileNotFoundException e3) {
            e = e3;
            Log.e(TAG, e.getMessage());
            return z;
        } catch (Exception e4) {
            e = e4;
            Log.e(TAG, e.getMessage());
            return z;
        }
        try {
            fileOutputStream.close();
            fileInputStream.close();
            return z;
        } catch (Throwable th2) {
            th = th2;
            try {
                throw th;
            } catch (Throwable th3) {
                try {
                    fileInputStream.close();
                } catch (Throwable th4) {
                    th.addSuppressed(th4);
                }
                throw th3;
            }
        }
    }

    private static File newFile(File file, ZipEntry zipEntry) throws IOException {
        File file2 = new File(file, zipEntry.getName());
        String canonicalPath = file.getCanonicalPath();
        String canonicalPath2 = file2.getCanonicalPath();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(canonicalPath);
        outline107.append(File.separator);
        if (canonicalPath2.startsWith(outline107.toString())) {
            return file2;
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Entry is outside of the target dir: ");
        outline1072.append(zipEntry.getName());
        throw new IOException(outline1072.toString());
    }

    public static boolean unzipFile(String str, String str2) {
        boolean z;
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(str));
            File file = new File(str2);
            byte[] bArr = new byte[1024];
            for (ZipEntry nextEntry = zipInputStream.getNextEntry(); nextEntry != null; nextEntry = zipInputStream.getNextEntry()) {
                File newFile = newFile(file, nextEntry);
                if (nextEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs()) {
                        throw new IOException("Failed to create directory " + newFile);
                    }
                } else {
                    File parentFile = newFile.getParentFile();
                    if (!parentFile.isDirectory() && !parentFile.mkdirs()) {
                        throw new IOException("Failed to create directory " + parentFile);
                    }
                    try {
                        FileOutputStream fileOutputStream = new FileOutputStream(newFile);
                        while (true) {
                            try {
                                int read = zipInputStream.read(bArr);
                                if (read <= 0) {
                                    break;
                                }
                                fileOutputStream.write(bArr, 0, read);
                            } catch (Throwable th) {
                                try {
                                    throw th;
                                } catch (Throwable th2) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (Throwable th3) {
                                        th.addSuppressed(th3);
                                    }
                                    throw th2;
                                }
                            }
                        }
                        fileOutputStream.close();
                    } catch (IOException e) {
                        throw e;
                    }
                }
            }
            zipInputStream.closeEntry();
            zipInputStream.close();
            try {
                zipInputStream.close();
                return true;
            } catch (IOException e2) {
                e = e2;
                z = true;
                Log.e(TAG, "Got an exception while unzipping the file. Error: %s", e.getMessage());
                return z;
            }
        } catch (IOException e3) {
            e = e3;
            z = false;
        }
    }
}
