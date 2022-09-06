package com.drew.imaging.png;

import com.drew.lang.Charsets;
import com.drew.lang.StreamReader;
import com.drew.lang.annotations.NotNull;
import com.drew.metadata.Metadata;
import com.drew.metadata.file.FileSystemMetadataReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes2.dex */
public class PngMetadataReader {
    private static Set<PngChunkType> _desiredChunkTypes;
    private static Charset _latin1Encoding = Charsets.ISO_8859_1;

    static {
        HashSet hashSet = new HashSet();
        hashSet.add(PngChunkType.IHDR);
        hashSet.add(PngChunkType.PLTE);
        hashSet.add(PngChunkType.tRNS);
        hashSet.add(PngChunkType.cHRM);
        hashSet.add(PngChunkType.sRGB);
        hashSet.add(PngChunkType.gAMA);
        hashSet.add(PngChunkType.iCCP);
        hashSet.add(PngChunkType.bKGD);
        hashSet.add(PngChunkType.tEXt);
        hashSet.add(PngChunkType.zTXt);
        hashSet.add(PngChunkType.iTXt);
        hashSet.add(PngChunkType.tIME);
        hashSet.add(PngChunkType.pHYs);
        hashSet.add(PngChunkType.sBIT);
        _desiredChunkTypes = Collections.unmodifiableSet(hashSet);
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:99:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void processChunk(@com.drew.lang.annotations.NotNull com.drew.metadata.Metadata r14, @com.drew.lang.annotations.NotNull com.drew.imaging.png.PngChunk r15) throws com.drew.imaging.png.PngProcessingException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 1057
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.imaging.png.PngMetadataReader.processChunk(com.drew.metadata.Metadata, com.drew.imaging.png.PngChunk):void");
    }

    @NotNull
    public static Metadata readMetadata(@NotNull File file) throws PngProcessingException, IOException {
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
    public static Metadata readMetadata(@NotNull InputStream inputStream) throws PngProcessingException, IOException {
        Iterable<PngChunk> extract = new PngChunkReader().extract(new StreamReader(inputStream), _desiredChunkTypes);
        Metadata metadata = new Metadata();
        for (PngChunk pngChunk : extract) {
            try {
                processChunk(metadata, pngChunk);
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
        return metadata;
    }
}
