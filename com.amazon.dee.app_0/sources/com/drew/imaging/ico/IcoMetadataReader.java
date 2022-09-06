package com.drew.imaging.ico;

import com.drew.lang.StreamReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.file.FileSystemMetadataReader;
import com.drew.metadata.ico.IcoReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes2.dex */
public class IcoMetadataReader {
    @NotNull
    public static Metadata readMetadata(@NotNull File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            Metadata readMetadata = readMetadata(fileInputStream);
            fileInputStream.close();
            new FileSystemMetadataReader().read(file, readMetadata);
            return readMetadata;
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }

    @NotNull
    public static Metadata readMetadata(@NotNull InputStream inputStream) {
        Metadata metadata = new Metadata();
        new IcoReader().extract(new StreamReader(inputStream), metadata);
        return metadata;
    }
}
