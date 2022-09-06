package com.drew.metadata.xmp;

import com.adobe.xmp.XMPException;
import com.adobe.xmp.XMPIterator;
import com.adobe.xmp.XMPMetaFactory;
import com.adobe.xmp.properties.XMPPropertyInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.imaging.jpeg.JpegSegmentMetadataReader;
import com.drew.imaging.jpeg.JpegSegmentType;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.StringValue;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
/* loaded from: classes2.dex */
public class XmpReader implements JpegSegmentMetadataReader {
    @NotNull
    private static final String ATTRIBUTE_EXTENDED_XMP = "xmpNote:HasExtendedXMP";
    private static final int EXTENDED_XMP_GUID_LENGTH = 32;
    private static final int EXTENDED_XMP_INT_LENGTH = 4;
    @NotNull
    private static final String SCHEMA_XMP_NOTES = "http://ns.adobe.com/xmp/note/";
    @NotNull
    private static final String XMP_EXTENSION_JPEG_PREAMBLE = "http://ns.adobe.com/xmp/extension/\u0000";
    @NotNull
    private static final String XMP_JPEG_PREAMBLE = "http://ns.adobe.com/xap/1.0/\u0000";

    @Nullable
    private static String getExtendedXMPGUID(@NotNull Metadata metadata) {
        Iterator it2 = metadata.getDirectoriesOfType(XmpDirectory.class).iterator();
        while (it2.hasNext()) {
            try {
                XMPIterator it3 = ((XmpDirectory) it2.next()).getXMPMeta().iterator("http://ns.adobe.com/xmp/note/", null, null);
                if (it3 != null) {
                    while (it3.hasNext()) {
                        XMPPropertyInfo xMPPropertyInfo = (XMPPropertyInfo) it3.next();
                        if (ATTRIBUTE_EXTENDED_XMP.equals(xMPPropertyInfo.getPath())) {
                            return xMPPropertyInfo.getValue();
                        }
                    }
                    continue;
                }
            } catch (XMPException unused) {
            }
        }
        return null;
    }

    @Nullable
    private static byte[] processExtendedXMPChunk(@NotNull Metadata metadata, @NotNull byte[] bArr, @NotNull String str, @Nullable byte[] bArr2) {
        int length = bArr.length;
        if (length >= 75) {
            try {
                SequentialByteArrayReader sequentialByteArrayReader = new SequentialByteArrayReader(bArr);
                sequentialByteArrayReader.skip(35);
                if (str.equals(sequentialByteArrayReader.getString(32))) {
                    int uInt32 = (int) sequentialByteArrayReader.getUInt32();
                    int uInt322 = (int) sequentialByteArrayReader.getUInt32();
                    if (bArr2 == null) {
                        bArr2 = new byte[uInt32];
                    }
                    if (bArr2.length == uInt32) {
                        System.arraycopy(bArr, 75, bArr2, uInt322, length - 75);
                    } else {
                        XmpDirectory xmpDirectory = new XmpDirectory();
                        xmpDirectory.addError(String.format("Inconsistent length for the Extended XMP buffer: %d instead of %d", Integer.valueOf(uInt32), Integer.valueOf(bArr2.length)));
                        metadata.addDirectory(xmpDirectory);
                    }
                }
            } catch (IOException e) {
                XmpDirectory xmpDirectory2 = new XmpDirectory();
                xmpDirectory2.addError(e.getMessage());
                metadata.addDirectory(xmpDirectory2);
            }
        }
        return bArr2;
    }

    public void extract(@NotNull StringValue stringValue, @NotNull Metadata metadata) {
        extract(stringValue.getBytes(), metadata, (Directory) null);
    }

    public void extract(@NotNull String str, @NotNull Metadata metadata) {
        extract(str, metadata, (Directory) null);
    }

    public void extract(@NotNull String str, @NotNull Metadata metadata, @Nullable Directory directory) {
        XmpDirectory xmpDirectory = new XmpDirectory();
        if (directory != null) {
            xmpDirectory.setParent(directory);
        }
        try {
            xmpDirectory.setXMPMeta(XMPMetaFactory.parseFromString(str));
        } catch (XMPException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error processing XMP data: ");
            outline107.append(e.getMessage());
            xmpDirectory.addError(outline107.toString());
        }
        if (!xmpDirectory.isEmpty()) {
            metadata.addDirectory(xmpDirectory);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void extract(@com.drew.lang.annotations.NotNull byte[] r2, int r3, int r4, @com.drew.lang.annotations.NotNull com.drew.metadata.Metadata r5, @com.drew.lang.annotations.Nullable com.drew.metadata.Directory r6) {
        /*
            r1 = this;
            com.drew.metadata.xmp.XmpDirectory r0 = new com.drew.metadata.xmp.XmpDirectory
            r0.<init>()
            if (r6 == 0) goto La
            r0.setParent(r6)
        La:
            if (r3 != 0) goto L14
            int r6 = r2.length     // Catch: com.adobe.xmp.XMPException -> L25
            if (r4 != r6) goto L14
            com.adobe.xmp.XMPMeta r2 = com.adobe.xmp.XMPMetaFactory.parseFromBuffer(r2)     // Catch: com.adobe.xmp.XMPException -> L25
            goto L21
        L14:
            com.adobe.xmp.impl.ByteBuffer r6 = new com.adobe.xmp.impl.ByteBuffer     // Catch: com.adobe.xmp.XMPException -> L25
            r6.<init>(r2, r3, r4)     // Catch: com.adobe.xmp.XMPException -> L25
            java.io.InputStream r2 = r6.getByteStream()     // Catch: com.adobe.xmp.XMPException -> L25
            com.adobe.xmp.XMPMeta r2 = com.adobe.xmp.XMPMetaFactory.parse(r2)     // Catch: com.adobe.xmp.XMPException -> L25
        L21:
            r0.setXMPMeta(r2)     // Catch: com.adobe.xmp.XMPException -> L25
            goto L3a
        L25:
            r2 = move-exception
            java.lang.String r3 = "Error processing XMP data: "
            java.lang.StringBuilder r3 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r3)
            java.lang.String r2 = r2.getMessage()
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r0.addError(r2)
        L3a:
            boolean r2 = r0.isEmpty()
            if (r2 != 0) goto L43
            r5.addDirectory(r0)
        L43:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.metadata.xmp.XmpReader.extract(byte[], int, int, com.drew.metadata.Metadata, com.drew.metadata.Directory):void");
    }

    public void extract(@NotNull byte[] bArr, @NotNull Metadata metadata) {
        extract(bArr, metadata, (Directory) null);
    }

    public void extract(@NotNull byte[] bArr, @NotNull Metadata metadata, @Nullable Directory directory) {
        extract(bArr, 0, bArr.length, metadata, directory);
    }

    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    @NotNull
    public Iterable<JpegSegmentType> getSegmentTypes() {
        return Collections.singletonList(JpegSegmentType.APP1);
    }

    @Override // com.drew.imaging.jpeg.JpegSegmentMetadataReader
    public void readJpegSegments(@NotNull Iterable<byte[]> iterable, @NotNull Metadata metadata, @NotNull JpegSegmentType jpegSegmentType) {
        byte[] bArr = null;
        String str = null;
        for (byte[] bArr2 : iterable) {
            if (bArr2.length >= 29 && (XMP_JPEG_PREAMBLE.equalsIgnoreCase(new String(bArr2, 0, 29)) || "XMP".equalsIgnoreCase(new String(bArr2, 0, 3)))) {
                byte[] bArr3 = new byte[bArr2.length - 29];
                System.arraycopy(bArr2, 29, bArr3, 0, bArr3.length);
                extract(bArr3, metadata);
                str = getExtendedXMPGUID(metadata);
            } else if (str != null && bArr2.length >= 35 && XMP_EXTENSION_JPEG_PREAMBLE.equalsIgnoreCase(new String(bArr2, 0, 35))) {
                bArr = processExtendedXMPChunk(metadata, bArr2, str, bArr);
            }
        }
        if (bArr != null) {
            extract(bArr, metadata);
        }
    }
}
