package com.google.android.exoplayer2.extractor.mp4;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.io.IOException;
/* loaded from: classes2.dex */
final class Sniffer {
    public static final int BRAND_HEIC = 1751476579;
    public static final int BRAND_QUICKTIME = 1903435808;
    private static final int[] COMPATIBLE_BRANDS = {1769172845, 1769172786, 1769172787, 1769172788, 1769172789, 1769172790, 1769172793, Atom.TYPE_avc1, Atom.TYPE_hvc1, Atom.TYPE_hev1, Atom.TYPE_av01, 1836069937, 1836069938, 862401121, 862401122, 862417462, 862417718, 862414134, 862414646, 1295275552, 1295270176, 1714714144, 1801741417, 1295275600, BRAND_QUICKTIME, 1297305174, 1684175153, 1769172332, 1885955686};
    private static final int SEARCH_LENGTH = 4096;

    private Sniffer() {
    }

    private static boolean isCompatibleBrand(int i, boolean z) {
        if ((i >>> 8) == 3368816) {
            return true;
        }
        if (i == 1751476579 && z) {
            return true;
        }
        for (int i2 : COMPATIBLE_BRANDS) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public static boolean sniffFragmented(ExtractorInput extractorInput) throws IOException {
        return sniffInternal(extractorInput, true, false);
    }

    private static boolean sniffInternal(ExtractorInput extractorInput, boolean z, boolean z2) throws IOException {
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        long length = extractorInput.getLength();
        long j = -1;
        int i = (length > (-1L) ? 1 : (length == (-1L) ? 0 : -1));
        long j2 = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        if (i != 0 && length <= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
            j2 = length;
        }
        ParsableByteArray parsableByteArray = new ParsableByteArray(64);
        boolean z8 = false;
        int i2 = (int) j2;
        int i3 = 0;
        boolean z9 = false;
        while (i3 < i2) {
            parsableByteArray.reset(8);
            byte[] data = parsableByteArray.getData();
            int i4 = z8 ? 1 : 0;
            int i5 = z8 ? 1 : 0;
            int i6 = z8 ? 1 : 0;
            int i7 = z8 ? 1 : 0;
            if (!extractorInput.peekFully(data, i4, 8, true)) {
                break;
            }
            long readUnsignedInt = parsableByteArray.readUnsignedInt();
            int readInt = parsableByteArray.readInt();
            int i8 = 16;
            if (readUnsignedInt == 1) {
                extractorInput.peekFully(parsableByteArray.getData(), 8, 8);
                parsableByteArray.setLimit(16);
                readUnsignedInt = parsableByteArray.readLong();
            } else {
                if (readUnsignedInt == 0) {
                    long length2 = extractorInput.getLength();
                    if (length2 != j) {
                        readUnsignedInt = (length2 - extractorInput.getPeekPosition()) + 8;
                    }
                }
                i8 = 8;
            }
            long j3 = i8;
            if (readUnsignedInt < j3) {
                return z8;
            }
            i3 += i8;
            if (readInt == 1836019574) {
                i2 += (int) readUnsignedInt;
                if (i != 0 && i2 > length) {
                    i2 = (int) length;
                }
            } else if (readInt == 1836019558 || readInt == 1836475768) {
                z3 = z8;
                z4 = true;
                z5 = true;
                break;
            } else {
                int i9 = i2;
                long j4 = readUnsignedInt;
                if ((i3 + readUnsignedInt) - j3 >= i9) {
                    z4 = true;
                    z3 = false;
                    break;
                }
                int i10 = (int) (j4 - j3);
                i3 += i10;
                if (readInt != 1718909296) {
                    z6 = false;
                    if (i10 != 0) {
                        extractorInput.advancePeekPosition(i10);
                    }
                } else if (i10 < 8) {
                    return false;
                } else {
                    parsableByteArray.reset(i10);
                    extractorInput.peekFully(parsableByteArray.getData(), 0, i10);
                    int i11 = i10 / 4;
                    int i12 = 0;
                    while (true) {
                        if (i12 >= i11) {
                            z7 = z9;
                            break;
                        }
                        z7 = true;
                        if (i12 == 1) {
                            parsableByteArray.skipBytes(4);
                        } else if (isCompatibleBrand(parsableByteArray.readInt(), z2)) {
                            break;
                        }
                        i12++;
                    }
                    if (!z7) {
                        return false;
                    }
                    z6 = false;
                    z9 = z7;
                }
                i2 = i9;
                z8 = z6;
            }
            j = -1;
        }
        boolean z10 = z8 ? 1 : 0;
        Object[] objArr = z8 ? 1 : 0;
        Object[] objArr2 = z8 ? 1 : 0;
        Object[] objArr3 = z8 ? 1 : 0;
        z3 = z10;
        z4 = true;
        z5 = z3;
        return (!z9 || z != z5) ? z3 : z4;
    }

    public static boolean sniffUnfragmented(ExtractorInput extractorInput) throws IOException {
        return sniffInternal(extractorInput, false, false);
    }

    public static boolean sniffUnfragmented(ExtractorInput extractorInput, boolean z) throws IOException {
        return sniffInternal(extractorInput, false, z);
    }
}
