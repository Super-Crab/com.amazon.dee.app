package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
@Deprecated
/* loaded from: classes13.dex */
public class DefaultFileManager implements FileManager {
    private final File directory;

    public DefaultFileManager(File file) {
        this.directory = file;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.FileManager
    public synchronized File createDirectory(String str) {
        File file = new File(this.directory, str);
        if (!file.mkdirs()) {
            if (!file.isDirectory()) {
                return null;
            }
        }
        return file;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.FileManager
    public synchronized File createFile(String str) throws IOException {
        return createFile(new File(this.directory, str));
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.FileManager
    public synchronized boolean deleteFile(String str) {
        return deleteFile(new File(this.directory, str));
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.FileManager
    public File getDirectory(String str) {
        return new File(this.directory, str);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.FileManager
    public Set<File> listFilesInDirectory(String str) {
        return listFilesInDirectory(new File(this.directory, str));
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.FileManager
    public synchronized InputStream newInputStream(String str) throws FileNotFoundException {
        return newInputStream(new File(this.directory, str));
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.FileManager
    public synchronized OutputStream newOutputStream(String str, boolean z) throws FileNotFoundException {
        return newOutputStream(new File(this.directory, str), z);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.FileManager
    public synchronized File createFile(File file) throws IOException {
        if (!file.createNewFile()) {
            if (!file.isFile()) {
                return null;
            }
        }
        return file;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.FileManager
    public synchronized boolean deleteFile(File file) {
        if (!file.delete()) {
            if (file.exists()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.FileManager
    public Set<File> listFilesInDirectory(File file) {
        HashSet hashSet = new HashSet();
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                hashSet.add(file2);
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.FileManager
    public synchronized InputStream newInputStream(File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.FileManager
    public synchronized OutputStream newOutputStream(File file, boolean z) throws FileNotFoundException {
        return new FileOutputStream(file, z);
    }
}
