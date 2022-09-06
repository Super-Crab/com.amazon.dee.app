package com.drew.imaging.jpeg;

import com.drew.lang.StreamReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Metadata;
import com.drew.metadata.adobe.AdobeJpegReader;
import com.drew.metadata.exif.ExifReader;
import com.drew.metadata.file.FileSystemMetadataReader;
import com.drew.metadata.icc.IccReader;
import com.drew.metadata.iptc.IptcReader;
import com.drew.metadata.jfif.JfifReader;
import com.drew.metadata.jfxx.JfxxReader;
import com.drew.metadata.jpeg.JpegCommentReader;
import com.drew.metadata.jpeg.JpegDhtReader;
import com.drew.metadata.jpeg.JpegDnlReader;
import com.drew.metadata.jpeg.JpegReader;
import com.drew.metadata.photoshop.DuckyReader;
import com.drew.metadata.photoshop.PhotoshopReader;
import com.drew.metadata.xmp.XmpReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
/* loaded from: classes2.dex */
public class JpegMetadataReader {
    public static final Iterable<JpegSegmentMetadataReader> ALL_READERS = Arrays.asList(new JpegReader(), new JpegCommentReader(), new JfifReader(), new JfxxReader(), new ExifReader(), new XmpReader(), new IccReader(), new PhotoshopReader(), new DuckyReader(), new IptcReader(), new AdobeJpegReader(), new JpegDhtReader(), new JpegDnlReader());

    private JpegMetadataReader() throws Exception {
        throw new Exception("Not intended for instantiation");
    }

    public static void process(@NotNull Metadata metadata, @NotNull InputStream inputStream) throws JpegProcessingException, IOException {
        process(metadata, inputStream, null);
    }

    public static void process(@NotNull Metadata metadata, @NotNull InputStream inputStream, @Nullable Iterable<JpegSegmentMetadataReader> iterable) throws JpegProcessingException, IOException {
        if (iterable == null) {
            iterable = ALL_READERS;
        }
        HashSet hashSet = new HashSet();
        for (JpegSegmentMetadataReader jpegSegmentMetadataReader : iterable) {
            for (JpegSegmentType jpegSegmentType : jpegSegmentMetadataReader.getSegmentTypes()) {
                hashSet.add(jpegSegmentType);
            }
        }
        processJpegSegmentData(metadata, iterable, JpegSegmentReader.readSegments(new StreamReader(inputStream), hashSet));
    }

    public static void processJpegSegmentData(Metadata metadata, Iterable<JpegSegmentMetadataReader> iterable, JpegSegmentData jpegSegmentData) {
        for (JpegSegmentMetadataReader jpegSegmentMetadataReader : iterable) {
            for (JpegSegmentType jpegSegmentType : jpegSegmentMetadataReader.getSegmentTypes()) {
                jpegSegmentMetadataReader.readJpegSegments(jpegSegmentData.getSegments(jpegSegmentType), metadata, jpegSegmentType);
            }
        }
    }

    @NotNull
    public static Metadata readMetadata(@NotNull File file) throws JpegProcessingException, IOException {
        return readMetadata(file, (Iterable<JpegSegmentMetadataReader>) null);
    }

    @NotNull
    public static Metadata readMetadata(@NotNull File file, @Nullable Iterable<JpegSegmentMetadataReader> iterable) throws JpegProcessingException, IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            Metadata readMetadata = readMetadata(fileInputStream, iterable);
            fileInputStream.close();
            new FileSystemMetadataReader().read(file, readMetadata);
            return readMetadata;
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }

    @NotNull
    public static Metadata readMetadata(@NotNull InputStream inputStream) throws JpegProcessingException, IOException {
        return readMetadata(inputStream, (Iterable<JpegSegmentMetadataReader>) null);
    }

    @NotNull
    public static Metadata readMetadata(@NotNull InputStream inputStream, @Nullable Iterable<JpegSegmentMetadataReader> iterable) throws JpegProcessingException, IOException {
        Metadata metadata = new Metadata();
        process(metadata, inputStream, iterable);
        return metadata;
    }
}
