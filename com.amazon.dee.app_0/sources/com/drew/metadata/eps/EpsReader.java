package com.drew.metadata.eps;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.imaging.tiff.TiffProcessingException;
import com.drew.imaging.tiff.TiffReader;
import com.drew.lang.ByteArrayReader;
import com.drew.lang.Charsets;
import com.drew.lang.RandomAccessStreamReader;
import com.drew.lang.SequentialByteArrayReader;
import com.drew.lang.SequentialReader;
import com.drew.lang.StreamReader;
import com.drew.lang.annotations.NotNull;
import com.drew.lang.annotations.Nullable;
import com.drew.metadata.Metadata;
import com.drew.metadata.icc.IccReader;
import com.drew.metadata.photoshop.PhotoshopReader;
import com.drew.metadata.photoshop.PhotoshopTiffHandler;
import com.drew.metadata.xmp.XmpReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes2.dex */
public class EpsReader {
    private int _previousTag;

    private void addToDirectory(@NotNull EpsDirectory epsDirectory, String str, String str2) throws IOException {
        int i;
        Integer num = EpsDirectory._tagIntegerMap.get(str);
        if (num == null) {
            return;
        }
        int intValue = num.intValue();
        if (intValue == 8) {
            extractImageData(epsDirectory, str2);
        } else if (intValue != 36) {
            if (!EpsDirectory._tagNameMap.containsKey(num) || epsDirectory.containsTag(num.intValue())) {
                i = 0;
            } else {
                epsDirectory.setString(num.intValue(), str2);
                i = num.intValue();
            }
            this._previousTag = i;
        } else {
            int i2 = this._previousTag;
            epsDirectory.setString(i2, epsDirectory.getString(this._previousTag) + " " + str2);
        }
        this._previousTag = num.intValue();
    }

    @Nullable
    private static byte[] decodeHexCommentBlock(@NotNull SequentialReader sequentialReader) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        boolean z = false;
        boolean z2 = false;
        byte b = 0;
        int i = 0;
        while (!z) {
            b = sequentialReader.getByte();
            if (z2) {
                if (!z2) {
                    if (z2) {
                        int tryHexToInt = tryHexToInt(b);
                        if (tryHexToInt != -1) {
                            i = tryHexToInt * 16;
                            z2 = true;
                        } else if (b != 13 && b != 10) {
                            return null;
                        } else {
                            z2 = false;
                        }
                    } else if (z2) {
                        int tryHexToInt2 = tryHexToInt(b);
                        if (tryHexToInt2 == -1) {
                            return null;
                        }
                        byteArrayOutputStream.write(tryHexToInt2 + i);
                        z2 = true;
                    } else {
                        continue;
                    }
                } else if (b != 32) {
                    z = true;
                } else {
                    z2 = true;
                }
            } else if (b != 10 && b != 13 && b != 32) {
                if (b != 37) {
                    return null;
                }
                z2 = true;
            }
        }
        while (b != 10) {
            b = sequentialReader.getByte();
        }
        return byteArrayOutputStream.toByteArray();
    }

    private void extract(@NotNull EpsDirectory epsDirectory, @NotNull Metadata metadata, @NotNull SequentialReader sequentialReader) throws IOException {
        String trim;
        StringBuilder sb = new StringBuilder();
        while (true) {
            sb.setLength(0);
            while (true) {
                char c = (char) sequentialReader.getByte();
                if (c == '\r' || c == '\n') {
                    break;
                }
                sb.append(c);
            }
            if (sb.length() == 0 || sb.charAt(0) == '%') {
                int indexOf = sb.indexOf(":");
                if (indexOf != -1) {
                    trim = sb.substring(0, indexOf).trim();
                    addToDirectory(epsDirectory, trim, sb.substring(indexOf + 1).trim());
                } else {
                    trim = sb.toString().trim();
                }
                if (trim.equals("%BeginPhotoshop")) {
                    extractPhotoshopData(metadata, sequentialReader);
                } else if (trim.equals("%%BeginICCProfile")) {
                    extractIccData(metadata, sequentialReader);
                } else if (trim.equals("%begin_xml_packet")) {
                    extractXmpData(metadata, sequentialReader);
                }
            } else {
                return;
            }
        }
    }

    private static void extractIccData(@NotNull Metadata metadata, @NotNull SequentialReader sequentialReader) throws IOException {
        byte[] decodeHexCommentBlock = decodeHexCommentBlock(sequentialReader);
        if (decodeHexCommentBlock != null) {
            new IccReader().extract(new ByteArrayReader(decodeHexCommentBlock), metadata);
        }
    }

    private static void extractImageData(@NotNull EpsDirectory epsDirectory, String str) throws IOException {
        epsDirectory.setString(8, str.trim());
        String[] split = str.split(" ");
        int parseInt = Integer.parseInt(split[0]);
        int parseInt2 = Integer.parseInt(split[1]);
        int i = 3;
        int parseInt3 = Integer.parseInt(split[3]);
        if (!epsDirectory.containsTag(28)) {
            epsDirectory.setInt(28, parseInt);
        }
        if (!epsDirectory.containsTag(29)) {
            epsDirectory.setInt(29, parseInt2);
        }
        if (!epsDirectory.containsTag(30)) {
            epsDirectory.setInt(30, parseInt3);
        }
        if (!epsDirectory.containsTag(31)) {
            if (parseInt3 == 1) {
                i = 1;
            } else if (parseInt3 != 2 && parseInt3 != 3 && parseInt3 != 4) {
                i = 0;
            }
            if (i == 0) {
                return;
            }
            epsDirectory.setInt(31, i * parseInt * parseInt2);
        }
    }

    private static void extractPhotoshopData(@NotNull Metadata metadata, @NotNull SequentialReader sequentialReader) throws IOException {
        byte[] decodeHexCommentBlock = decodeHexCommentBlock(sequentialReader);
        if (decodeHexCommentBlock != null) {
            new PhotoshopReader().extract(new SequentialByteArrayReader(decodeHexCommentBlock), decodeHexCommentBlock.length, metadata);
        }
    }

    private static void extractXmpData(@NotNull Metadata metadata, @NotNull SequentialReader sequentialReader) throws IOException {
        new XmpReader().extract(new String(readUntil(sequentialReader, "<?xpacket end=\"w\"?>".getBytes()), Charsets.UTF_8), metadata);
    }

    private static byte[] readUntil(@NotNull SequentialReader sequentialReader, @NotNull byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int length = bArr.length;
        int i = 0;
        while (i != length) {
            byte b = sequentialReader.getByte();
            i = b == bArr[i] ? i + 1 : 0;
            byteArrayOutputStream.write(b);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private static int tryHexToInt(byte b) {
        if (b < 48 || b > 57) {
            byte b2 = 65;
            if (b < 65 || b > 70) {
                b2 = 97;
                if (b < 97 || b > 102) {
                    return -1;
                }
            }
            return (b - b2) + 10;
        }
        return b - 48;
    }

    public void extract(@NotNull InputStream inputStream, @NotNull Metadata metadata) throws IOException {
        RandomAccessStreamReader randomAccessStreamReader = new RandomAccessStreamReader(inputStream);
        EpsDirectory epsDirectory = new EpsDirectory();
        metadata.addDirectory(epsDirectory);
        int int32 = randomAccessStreamReader.getInt32(0);
        if (int32 != -976170042) {
            if (int32 != 622940243) {
                epsDirectory.addError("File type not supported.");
                return;
            }
            inputStream.reset();
            extract(epsDirectory, metadata, new StreamReader(inputStream));
            return;
        }
        randomAccessStreamReader.setMotorolaByteOrder(false);
        int int322 = randomAccessStreamReader.getInt32(4);
        int int323 = randomAccessStreamReader.getInt32(8);
        int int324 = randomAccessStreamReader.getInt32(12);
        int int325 = randomAccessStreamReader.getInt32(16);
        int int326 = randomAccessStreamReader.getInt32(20);
        int int327 = randomAccessStreamReader.getInt32(24);
        if (int327 != 0) {
            epsDirectory.setInt(32, int327);
            epsDirectory.setInt(33, int326);
            try {
                new TiffReader().processTiff(new ByteArrayReader(randomAccessStreamReader.getBytes(int326, int327)), new PhotoshopTiffHandler(metadata, null), 0);
            } catch (TiffProcessingException e) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to process TIFF data: ");
                outline107.append(e.getMessage());
                epsDirectory.addError(outline107.toString());
            }
        } else if (int325 != 0) {
            epsDirectory.setInt(34, int325);
            epsDirectory.setInt(35, int324);
        }
        extract(epsDirectory, metadata, new SequentialByteArrayReader(randomAccessStreamReader.getBytes(int322, int323)));
    }
}
