package com.drew.imaging.mp4;

import com.drew.imaging.ImageProcessingException;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.file.FileSystemMetadataReader;
import com.drew.metadata.mp4.Mp4BoxHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes2.dex */
public class Mp4MetadataReader {
    @NotNull
    public static Metadata readMetadata(@NotNull File file) throws ImageProcessingException, IOException {
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
    public static Metadata readMetadata(@NotNull InputStream inputStream) throws IOException {
        Metadata metadata = new Metadata();
        Mp4Reader.extract(inputStream, new Mp4BoxHandler(metadata));
        return metadata;
    }
}
