package com.drew.imaging.tiff;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.lang.RandomAccessReader;
import com.drew.lang.Rational;
import com.drew.lang.annotations.NotNull;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes2.dex */
public class TiffReader {
    private static int calculateTagOffset(int i, int i2) {
        return (i2 * 12) + i + 2;
    }

    public static void processIfd(@NotNull TiffHandler tiffHandler, @NotNull RandomAccessReader randomAccessReader, @NotNull Set<Integer> set, int i, int i2) throws IOException {
        Boolean bool;
        int i3;
        int i4;
        long componentSizeBytes;
        int i5;
        long j;
        int i6;
        int i7;
        int i8;
        Boolean bool2 = null;
        try {
            if (set.contains(Integer.valueOf(i))) {
                tiffHandler.endingIFD();
                return;
            }
            set.add(Integer.valueOf(i));
            if (i < randomAccessReader.getLength() && i >= 0) {
                int uInt16 = randomAccessReader.getUInt16(i);
                if (uInt16 > 255 && (uInt16 & 255) == 0) {
                    bool2 = Boolean.valueOf(randomAccessReader.isMotorolaByteOrder());
                    uInt16 >>= 8;
                    randomAccessReader.setMotorolaByteOrder(!randomAccessReader.isMotorolaByteOrder());
                }
                Boolean bool3 = bool2;
                int i9 = uInt16;
                int i10 = 2;
                try {
                    if ((i9 * 12) + 2 + 4 + i > randomAccessReader.getLength()) {
                        tiffHandler.error("Illegally sized IFD");
                        tiffHandler.endingIFD();
                        if (bool3 == null) {
                            return;
                        }
                        randomAccessReader.setMotorolaByteOrder(bool3.booleanValue());
                        return;
                    }
                    int i11 = 0;
                    int i12 = 0;
                    while (i11 < i9) {
                        int calculateTagOffset = calculateTagOffset(i, i11);
                        int uInt162 = randomAccessReader.getUInt16(calculateTagOffset);
                        int uInt163 = randomAccessReader.getUInt16(calculateTagOffset + 2);
                        TiffDataFormat fromTiffFormatCode = TiffDataFormat.fromTiffFormatCode(uInt163);
                        long uInt32 = randomAccessReader.getUInt32(calculateTagOffset + 4);
                        if (fromTiffFormatCode == null) {
                            Long tryCustomProcessFormat = tiffHandler.tryCustomProcessFormat(uInt162, uInt163, uInt32);
                            if (tryCustomProcessFormat == null) {
                                Object[] objArr = new Object[i10];
                                objArr[0] = Integer.valueOf(uInt163);
                                objArr[1] = Integer.valueOf(uInt162);
                                tiffHandler.error(String.format("Invalid TIFF tag format code %d for tag 0x%04X", objArr));
                                int i13 = i12 + 1;
                                if (i13 > 5) {
                                    tiffHandler.error("Stopping processing as too many errors seen in TIFF IFD");
                                    tiffHandler.endingIFD();
                                    if (bool3 == null) {
                                        return;
                                    }
                                    randomAccessReader.setMotorolaByteOrder(bool3.booleanValue());
                                    return;
                                }
                                i12 = i13;
                                i6 = i11;
                                i7 = i10;
                                i5 = i9;
                                bool = bool3;
                                i11 = i6 + 1;
                                i9 = i5;
                                i10 = i7;
                                bool3 = bool;
                            } else {
                                i3 = 1;
                                i4 = 0;
                                componentSizeBytes = tryCustomProcessFormat.longValue();
                            }
                        } else {
                            i3 = 1;
                            i4 = 0;
                            componentSizeBytes = fromTiffFormatCode.getComponentSizeBytes() * uInt32;
                        }
                        if (componentSizeBytes > 4) {
                            long uInt322 = randomAccessReader.getUInt32(calculateTagOffset + 8);
                            if (uInt322 + componentSizeBytes > randomAccessReader.getLength()) {
                                tiffHandler.error("Illegal TIFF tag pointer offset");
                                i6 = i11;
                                i5 = i9;
                                bool = bool3;
                                i7 = 2;
                                i11 = i6 + 1;
                                i9 = i5;
                                i10 = i7;
                                bool3 = bool;
                            } else {
                                i5 = i9;
                                bool = bool3;
                                j = i2 + uInt322;
                            }
                        } else {
                            i5 = i9;
                            bool = bool3;
                            j = calculateTagOffset + 8;
                        }
                        if (j >= 0) {
                            try {
                                if (j <= randomAccessReader.getLength()) {
                                    if (componentSizeBytes >= 0 && j + componentSizeBytes <= randomAccessReader.getLength()) {
                                        i6 = i11;
                                        if (componentSizeBytes == 4 * uInt32) {
                                            int i14 = i4;
                                            i8 = i14;
                                            while (i14 < uInt32) {
                                                if (tiffHandler.tryEnterSubIfd(uInt162)) {
                                                    processIfd(tiffHandler, randomAccessReader, set, randomAccessReader.getInt32((int) ((i14 * 4) + j)) + i2, i2);
                                                    i8 = i3;
                                                }
                                                i14++;
                                            }
                                        } else {
                                            i8 = i4;
                                        }
                                        if (i8 == 0) {
                                            int i15 = (int) j;
                                            i7 = 2;
                                            if (!tiffHandler.customProcessTag(i15, set, i2, randomAccessReader, uInt162, (int) componentSizeBytes)) {
                                                processTag(tiffHandler, uInt162, i15, (int) uInt32, uInt163, randomAccessReader);
                                            }
                                            i11 = i6 + 1;
                                            i9 = i5;
                                            i10 = i7;
                                            bool3 = bool;
                                        }
                                        i7 = 2;
                                        i11 = i6 + 1;
                                        i9 = i5;
                                        i10 = i7;
                                        bool3 = bool;
                                    }
                                    i6 = i11;
                                    i7 = 2;
                                    tiffHandler.error("Illegal number of bytes for TIFF tag data: " + componentSizeBytes);
                                    i11 = i6 + 1;
                                    i9 = i5;
                                    i10 = i7;
                                    bool3 = bool;
                                }
                            } catch (Throwable th) {
                                th = th;
                                tiffHandler.endingIFD();
                                if (bool != null) {
                                    randomAccessReader.setMotorolaByteOrder(bool.booleanValue());
                                }
                                throw th;
                            }
                        }
                        i6 = i11;
                        i7 = 2;
                        tiffHandler.error("Illegal TIFF tag pointer offset");
                        i11 = i6 + 1;
                        i9 = i5;
                        i10 = i7;
                        bool3 = bool;
                    }
                    bool = bool3;
                    int int32 = randomAccessReader.getInt32(calculateTagOffset(i, i9));
                    if (int32 != 0) {
                        int i16 = int32 + i2;
                        if (i16 >= randomAccessReader.getLength()) {
                            tiffHandler.endingIFD();
                            if (bool == null) {
                                return;
                            }
                            randomAccessReader.setMotorolaByteOrder(bool.booleanValue());
                            return;
                        } else if (i16 < i) {
                            tiffHandler.endingIFD();
                            if (bool == null) {
                                return;
                            }
                            randomAccessReader.setMotorolaByteOrder(bool.booleanValue());
                            return;
                        } else if (tiffHandler.hasFollowerIfd()) {
                            processIfd(tiffHandler, randomAccessReader, set, i16, i2);
                        }
                    }
                    tiffHandler.endingIFD();
                    if (bool == null) {
                        return;
                    }
                    randomAccessReader.setMotorolaByteOrder(bool.booleanValue());
                    return;
                } catch (Throwable th2) {
                    th = th2;
                    bool = bool3;
                }
            }
            tiffHandler.error("Ignored IFD marked to start outside data segment");
            tiffHandler.endingIFD();
        } catch (Throwable th3) {
            th = th3;
            bool = null;
        }
    }

    private static void processTag(@NotNull TiffHandler tiffHandler, int i, int i2, int i3, int i4, @NotNull RandomAccessReader randomAccessReader) throws IOException {
        Rational rational;
        int i5 = 0;
        switch (i4) {
            case 1:
                if (i3 == 1) {
                    tiffHandler.setInt8u(i, randomAccessReader.getUInt8(i2));
                    return;
                }
                short[] sArr = new short[i3];
                while (i5 < i3) {
                    sArr[i5] = randomAccessReader.getUInt8(i2 + i5);
                    i5++;
                }
                tiffHandler.setInt8uArray(i, sArr);
                return;
            case 2:
                tiffHandler.setString(i, randomAccessReader.getNullTerminatedStringValue(i2, i3, null));
                return;
            case 3:
                if (i3 == 1) {
                    tiffHandler.setInt16u(i, randomAccessReader.getUInt16(i2));
                    return;
                }
                int[] iArr = new int[i3];
                while (i5 < i3) {
                    iArr[i5] = randomAccessReader.getUInt16((i5 * 2) + i2);
                    i5++;
                }
                tiffHandler.setInt16uArray(i, iArr);
                return;
            case 4:
                if (i3 == 1) {
                    tiffHandler.setInt32u(i, randomAccessReader.getUInt32(i2));
                    return;
                }
                long[] jArr = new long[i3];
                while (i5 < i3) {
                    jArr[i5] = randomAccessReader.getUInt32((i5 * 4) + i2);
                    i5++;
                }
                tiffHandler.setInt32uArray(i, jArr);
                return;
            case 5:
                if (i3 != 1) {
                    if (i3 <= 1) {
                        return;
                    }
                    Rational[] rationalArr = new Rational[i3];
                    while (i5 < i3) {
                        int i6 = i5 * 8;
                        rationalArr[i5] = new Rational(randomAccessReader.getUInt32(i2 + i6), randomAccessReader.getUInt32(i2 + 4 + i6));
                        i5++;
                    }
                    tiffHandler.setRationalArray(i, rationalArr);
                    return;
                }
                rational = new Rational(randomAccessReader.getUInt32(i2), randomAccessReader.getUInt32(i2 + 4));
                break;
            case 6:
                if (i3 == 1) {
                    tiffHandler.setInt8s(i, randomAccessReader.getInt8(i2));
                    return;
                }
                byte[] bArr = new byte[i3];
                while (i5 < i3) {
                    bArr[i5] = randomAccessReader.getInt8(i2 + i5);
                    i5++;
                }
                tiffHandler.setInt8sArray(i, bArr);
                return;
            case 7:
                tiffHandler.setByteArray(i, randomAccessReader.getBytes(i2, i3));
                return;
            case 8:
                if (i3 == 1) {
                    tiffHandler.setInt16s(i, randomAccessReader.getInt16(i2));
                    return;
                }
                short[] sArr2 = new short[i3];
                while (i5 < i3) {
                    sArr2[i5] = randomAccessReader.getInt16((i5 * 2) + i2);
                    i5++;
                }
                tiffHandler.setInt16sArray(i, sArr2);
                return;
            case 9:
                if (i3 == 1) {
                    tiffHandler.setInt32s(i, randomAccessReader.getInt32(i2));
                    return;
                }
                int[] iArr2 = new int[i3];
                while (i5 < i3) {
                    iArr2[i5] = randomAccessReader.getInt32((i5 * 4) + i2);
                    i5++;
                }
                tiffHandler.setInt32sArray(i, iArr2);
                return;
            case 10:
                if (i3 != 1) {
                    if (i3 <= 1) {
                        return;
                    }
                    Rational[] rationalArr2 = new Rational[i3];
                    while (i5 < i3) {
                        int i7 = i5 * 8;
                        rationalArr2[i5] = new Rational(randomAccessReader.getInt32(i2 + i7), randomAccessReader.getInt32(i2 + 4 + i7));
                        i5++;
                    }
                    tiffHandler.setRationalArray(i, rationalArr2);
                    return;
                }
                rational = new Rational(randomAccessReader.getInt32(i2), randomAccessReader.getInt32(i2 + 4));
                break;
            case 11:
                if (i3 == 1) {
                    tiffHandler.setFloat(i, randomAccessReader.getFloat32(i2));
                    return;
                }
                float[] fArr = new float[i3];
                while (i5 < i3) {
                    fArr[i5] = randomAccessReader.getFloat32((i5 * 4) + i2);
                    i5++;
                }
                tiffHandler.setFloatArray(i, fArr);
                return;
            case 12:
                if (i3 == 1) {
                    tiffHandler.setDouble(i, randomAccessReader.getDouble64(i2));
                    return;
                }
                double[] dArr = new double[i3];
                while (i5 < i3) {
                    dArr[i5] = randomAccessReader.getDouble64((i5 * 4) + i2);
                    i5++;
                }
                tiffHandler.setDoubleArray(i, dArr);
                return;
            default:
                tiffHandler.error(String.format("Invalid TIFF tag format code %d for tag 0x%04X", Integer.valueOf(i4), Integer.valueOf(i)));
                return;
        }
        tiffHandler.setRational(i, rational);
    }

    public void processTiff(@NotNull RandomAccessReader randomAccessReader, @NotNull TiffHandler tiffHandler, int i) throws TiffProcessingException, IOException {
        boolean z;
        short int16 = randomAccessReader.getInt16(i);
        if (int16 == 19789) {
            z = true;
        } else if (int16 != 18761) {
            throw new TiffProcessingException(GeneratedOutlineSupport1.outline49("Unclear distinction between Motorola/Intel byte ordering: ", int16));
        } else {
            z = false;
        }
        randomAccessReader.setMotorolaByteOrder(z);
        int i2 = i + 2;
        tiffHandler.setTiffMarker(randomAccessReader.getUInt16(i2));
        int int32 = randomAccessReader.getInt32(i + 4) + i;
        if (int32 >= randomAccessReader.getLength() - 1) {
            tiffHandler.warn("First IFD offset is beyond the end of the TIFF data segment -- trying default offset");
            int32 = i2 + 2 + 4;
        }
        processIfd(tiffHandler, randomAccessReader, new HashSet(), int32, i);
    }
}
