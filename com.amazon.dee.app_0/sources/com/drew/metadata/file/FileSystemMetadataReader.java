package com.drew.metadata.file;

import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import java.io.File;
import java.io.IOException;
import java.util.Date;
/* loaded from: classes2.dex */
public class FileSystemMetadataReader {
    public void read(@NotNull File file, @NotNull Metadata metadata) throws IOException {
        if (file.isFile()) {
            if (!file.exists()) {
                throw new IOException("File does not exist");
            }
            if (!file.canRead()) {
                throw new IOException("File is not readable");
            }
            FileSystemDirectory fileSystemDirectory = (FileSystemDirectory) metadata.getFirstDirectoryOfType(FileSystemDirectory.class);
            if (fileSystemDirectory == null) {
                fileSystemDirectory = new FileSystemDirectory();
                metadata.addDirectory(fileSystemDirectory);
            }
            fileSystemDirectory.setString(1, file.getName());
            fileSystemDirectory.setLong(2, file.length());
            fileSystemDirectory.setDate(3, new Date(file.lastModified()));
            return;
        }
        throw new IOException("File object must reference a file");
    }
}
