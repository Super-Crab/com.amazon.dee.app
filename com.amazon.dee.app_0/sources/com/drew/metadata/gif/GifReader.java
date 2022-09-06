package com.drew.metadata.gif;

import com.drew.lang.ByteArrayReader;
import com.drew.lang.Charsets;
import com.drew.lang.SequentialReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Directory;
import com.drew.metadata.ErrorDirectory;
import com.drew.metadata.Metadata;
import com.drew.metadata.MetadataException;
import com.drew.metadata.StringValue;
import com.drew.metadata.gif.GifControlDirectory;
import com.drew.metadata.icc.IccReader;
import com.drew.metadata.xmp.XmpReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.bouncycastle.tls.AlertDescription;
/* loaded from: classes2.dex */
public class GifReader {
    private static final String GIF_87A_VERSION_IDENTIFIER = "87a";
    private static final String GIF_89A_VERSION_IDENTIFIER = "89a";

    private static byte[] gatherBytes(SequentialReader sequentialReader) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[257];
        while (true) {
            byte b = sequentialReader.getByte();
            if (b == 0) {
                return byteArrayOutputStream.toByteArray();
            }
            int i = b & 255;
            bArr[0] = b;
            sequentialReader.getBytes(bArr, 1, i);
            byteArrayOutputStream.write(bArr, 0, i + 1);
        }
    }

    private static byte[] gatherBytes(SequentialReader sequentialReader, int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (i > 0) {
            byteArrayOutputStream.write(sequentialReader.getBytes(i), 0, i);
            i = sequentialReader.getByte() & 255;
        }
        return byteArrayOutputStream.toByteArray();
    }

    private static void readApplicationExtensionBlock(SequentialReader sequentialReader, int i, Metadata metadata) throws IOException {
        if (i != 11) {
            metadata.addDirectory(new ErrorDirectory(String.format("Invalid GIF application extension block size. Expected 11, got %d.", Integer.valueOf(i))));
            return;
        }
        String string = sequentialReader.getString(i, Charsets.UTF_8);
        if (string.equals("XMP DataXMP")) {
            byte[] gatherBytes = gatherBytes(sequentialReader);
            new XmpReader().extract(gatherBytes, 0, gatherBytes.length - 257, metadata, null);
        } else if (string.equals("ICCRGBG1012")) {
            byte[] gatherBytes2 = gatherBytes(sequentialReader, sequentialReader.getByte() & 255);
            if (gatherBytes2.length == 0) {
                return;
            }
            new IccReader().extract(new ByteArrayReader(gatherBytes2), metadata);
        } else if (!string.equals("NETSCAPE2.0")) {
            skipBlocks(sequentialReader);
        } else {
            sequentialReader.skip(2L);
            int uInt16 = sequentialReader.getUInt16();
            sequentialReader.skip(1L);
            GifAnimationDirectory gifAnimationDirectory = new GifAnimationDirectory();
            gifAnimationDirectory.setInt(1, uInt16);
            metadata.addDirectory(gifAnimationDirectory);
        }
    }

    private static GifCommentDirectory readCommentBlock(SequentialReader sequentialReader, int i) throws IOException {
        return new GifCommentDirectory(new StringValue(gatherBytes(sequentialReader, i), Charsets.ASCII));
    }

    private static GifControlDirectory readControlBlock(SequentialReader sequentialReader, int i) throws IOException {
        GifControlDirectory gifControlDirectory = new GifControlDirectory();
        short uInt8 = sequentialReader.getUInt8();
        gifControlDirectory.setObject(2, GifControlDirectory.DisposalMethod.typeOf((uInt8 >> 2) & 7));
        boolean z = false;
        gifControlDirectory.setBoolean(3, ((uInt8 & 2) >> 1) == 1);
        if ((uInt8 & 1) == 1) {
            z = true;
        }
        gifControlDirectory.setBoolean(4, z);
        gifControlDirectory.setInt(1, sequentialReader.getUInt16());
        gifControlDirectory.setInt(5, sequentialReader.getUInt8());
        sequentialReader.skip(1L);
        return gifControlDirectory;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x003d, code lost:
        if (r0 != null) goto L18;
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void readGifExtensionBlock(com.drew.lang.SequentialReader r7, com.drew.metadata.Metadata r8) throws java.io.IOException {
        /*
            byte r0 = r7.getInt8()
            short r1 = r7.getUInt8()
            long r2 = r7.getPosition()
            r4 = -7
            if (r0 == r4) goto L40
            r4 = 1
            if (r0 == r4) goto L39
            r5 = -2
            if (r0 == r5) goto L34
            r5 = -1
            if (r0 == r5) goto L30
            com.drew.metadata.ErrorDirectory r5 = new com.drew.metadata.ErrorDirectory
            java.lang.Object[] r4 = new java.lang.Object[r4]
            r6 = 0
            java.lang.Byte r0 = java.lang.Byte.valueOf(r0)
            r4[r6] = r0
            java.lang.String r0 = "Unsupported GIF extension block with type 0x%02X."
            java.lang.String r0 = java.lang.String.format(r0, r4)
            r5.<init>(r0)
            r8.addDirectory(r5)
            goto L47
        L30:
            readApplicationExtensionBlock(r7, r1, r8)
            goto L47
        L34:
            com.drew.metadata.gif.GifCommentDirectory r0 = readCommentBlock(r7, r1)
            goto L44
        L39:
            com.drew.metadata.Directory r0 = readPlainTextBlock(r7, r1)
            if (r0 == 0) goto L47
            goto L44
        L40:
            com.drew.metadata.gif.GifControlDirectory r0 = readControlBlock(r7, r1)
        L44:
            r8.addDirectory(r0)
        L47:
            long r0 = (long) r1
            long r2 = r2 + r0
            long r0 = r7.getPosition()
            long r2 = r2 - r0
            r0 = 0
            int r8 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
            if (r8 <= 0) goto L57
            r7.skip(r2)
        L57:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.drew.metadata.gif.GifReader.readGifExtensionBlock(com.drew.lang.SequentialReader, com.drew.metadata.Metadata):void");
    }

    private static GifHeaderDirectory readGifHeader(@NotNull SequentialReader sequentialReader) throws IOException {
        String str;
        GifHeaderDirectory gifHeaderDirectory = new GifHeaderDirectory();
        if (!sequentialReader.getString(3).equals("GIF")) {
            str = "Invalid GIF file signature";
        } else {
            String string = sequentialReader.getString(3);
            if (string.equals(GIF_87A_VERSION_IDENTIFIER) || string.equals(GIF_89A_VERSION_IDENTIFIER)) {
                boolean z = true;
                gifHeaderDirectory.setString(1, string);
                gifHeaderDirectory.setInt(2, sequentialReader.getUInt16());
                gifHeaderDirectory.setInt(3, sequentialReader.getUInt16());
                short uInt8 = sequentialReader.getUInt8();
                int i = 1 << ((uInt8 & 7) + 1);
                int i2 = ((uInt8 & AlertDescription.unrecognized_name) >> 4) + 1;
                boolean z2 = (uInt8 >> 7) != 0;
                gifHeaderDirectory.setInt(4, i);
                if (string.equals(GIF_89A_VERSION_IDENTIFIER)) {
                    if ((uInt8 & 8) == 0) {
                        z = false;
                    }
                    gifHeaderDirectory.setBoolean(5, z);
                }
                gifHeaderDirectory.setInt(6, i2);
                gifHeaderDirectory.setBoolean(7, z2);
                gifHeaderDirectory.setInt(8, sequentialReader.getUInt8());
                short uInt82 = sequentialReader.getUInt8();
                if (uInt82 != 0) {
                    gifHeaderDirectory.setFloat(9, (float) ((uInt82 + 15.0d) / 64.0d));
                }
                return gifHeaderDirectory;
            }
            str = "Unexpected GIF version";
        }
        gifHeaderDirectory.addError(str);
        return gifHeaderDirectory;
    }

    private static GifImageDirectory readImageBlock(SequentialReader sequentialReader) throws IOException {
        int i;
        GifImageDirectory gifImageDirectory = new GifImageDirectory();
        boolean z = true;
        gifImageDirectory.setInt(1, sequentialReader.getUInt16());
        gifImageDirectory.setInt(2, sequentialReader.getUInt16());
        gifImageDirectory.setInt(3, sequentialReader.getUInt16());
        gifImageDirectory.setInt(4, sequentialReader.getUInt16());
        byte b = sequentialReader.getByte();
        boolean z2 = (b >> 7) != 0;
        boolean z3 = (b & 64) != 0;
        gifImageDirectory.setBoolean(5, z2);
        gifImageDirectory.setBoolean(6, z3);
        if (z2) {
            if ((b & 32) == 0) {
                z = false;
            }
            gifImageDirectory.setBoolean(7, z);
            gifImageDirectory.setInt(8, (b & 7) + 1);
            sequentialReader.skip((2 << i) * 3);
        }
        sequentialReader.getByte();
        return gifImageDirectory;
    }

    @Nullable
    private static Directory readPlainTextBlock(SequentialReader sequentialReader, int i) throws IOException {
        if (i != 12) {
            return new ErrorDirectory(String.format("Invalid GIF plain text block size. Expected 12, got %d.", Integer.valueOf(i)));
        }
        sequentialReader.skip(12L);
        skipBlocks(sequentialReader);
        return null;
    }

    private static void skipBlocks(SequentialReader sequentialReader) throws IOException {
        while (true) {
            short uInt8 = sequentialReader.getUInt8();
            if (uInt8 == 0) {
                return;
            }
            sequentialReader.skip(uInt8);
        }
    }

    public void extract(@NotNull SequentialReader sequentialReader, @NotNull Metadata metadata) {
        ErrorDirectory errorDirectory;
        byte int8;
        sequentialReader.setMotorolaByteOrder(false);
        try {
            GifHeaderDirectory readGifHeader = readGifHeader(sequentialReader);
            metadata.addDirectory(readGifHeader);
            if (readGifHeader.hasErrors()) {
                return;
            }
            Integer num = null;
            try {
                try {
                    if (readGifHeader.getBoolean(7)) {
                        num = readGifHeader.getInteger(4);
                    }
                } catch (IOException unused) {
                    errorDirectory = new ErrorDirectory("IOException processing GIF data");
                    metadata.addDirectory(errorDirectory);
                    return;
                }
            } catch (MetadataException unused2) {
                metadata.addDirectory(new ErrorDirectory("GIF did not had hasGlobalColorTable bit."));
            }
            if (num != null) {
                sequentialReader.skip(num.intValue() * 3);
            }
            while (true) {
                try {
                    int8 = sequentialReader.getInt8();
                    if (int8 == 33) {
                        readGifExtensionBlock(sequentialReader, metadata);
                    } else if (int8 != 44) {
                        break;
                    } else {
                        metadata.addDirectory(readImageBlock(sequentialReader));
                        skipBlocks(sequentialReader);
                    }
                } catch (IOException unused3) {
                    return;
                }
            }
            if (int8 == 59) {
                return;
            }
            metadata.addDirectory(new ErrorDirectory("Unknown gif block marker found."));
        } catch (IOException unused4) {
            errorDirectory = new ErrorDirectory("IOException processing GIF data");
        }
    }
}
