package com.google.zxing.pdf417.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.detector.MathUtils;
import com.google.zxing.pdf417.PDF417Common;
import com.google.zxing.pdf417.decoder.ec.ErrorCorrection;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Formatter;
/* loaded from: classes3.dex */
public final class PDF417ScanningDecoder {
    private static final int CODEWORD_SKEW_SIZE = 2;
    private static final int MAX_EC_CODEWORDS = 512;
    private static final int MAX_ERRORS = 3;
    private static final ErrorCorrection errorCorrection = new ErrorCorrection();

    private PDF417ScanningDecoder() {
    }

    private static BoundingBox adjustBoundingBox(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn) throws NotFoundException {
        int[] rowHeights;
        if (detectionResultRowIndicatorColumn == null || (rowHeights = detectionResultRowIndicatorColumn.getRowHeights()) == null) {
            return null;
        }
        int max = getMax(rowHeights);
        int i = 0;
        int i2 = 0;
        for (int i3 : rowHeights) {
            i2 += max - i3;
            if (i3 > 0) {
                break;
            }
        }
        Codeword[] codewords = detectionResultRowIndicatorColumn.getCodewords();
        for (int i4 = 0; i2 > 0 && codewords[i4] == null; i4++) {
            i2--;
        }
        for (int length = rowHeights.length - 1; length >= 0; length--) {
            i += max - rowHeights[length];
            if (rowHeights[length] > 0) {
                break;
            }
        }
        for (int length2 = codewords.length - 1; i > 0 && codewords[length2] == null; length2--) {
            i--;
        }
        return detectionResultRowIndicatorColumn.getBoundingBox().addMissingRows(i2, i, detectionResultRowIndicatorColumn.isLeft());
    }

    private static void adjustCodewordCount(DetectionResult detectionResult, BarcodeValue[][] barcodeValueArr) throws NotFoundException {
        BarcodeValue barcodeValue = barcodeValueArr[0][1];
        int[] value = barcodeValue.getValue();
        int barcodeColumnCount = (detectionResult.getBarcodeColumnCount() * detectionResult.getBarcodeRowCount()) - getNumberOfECCodeWords(detectionResult.getBarcodeECLevel());
        if (value.length != 0) {
            if (value[0] == barcodeColumnCount) {
                return;
            }
            barcodeValue.setValue(barcodeColumnCount);
        } else if (barcodeColumnCount >= 1 && barcodeColumnCount <= 928) {
            barcodeValue.setValue(barcodeColumnCount);
        } else {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0023, code lost:
        r0 = -r0;
        r2 = !r2;
        r1 = r1 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0023, code lost:
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0023, code lost:
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0023, code lost:
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int adjustCodewordStartColumn(com.google.zxing.common.BitMatrix r5, int r6, int r7, boolean r8, int r9, int r10) {
        /*
            if (r8 == 0) goto L4
            r0 = -1
            goto L5
        L4:
            r0 = 1
        L5:
            r1 = 0
            r2 = r8
            r8 = r9
        L8:
            r3 = 2
            if (r1 >= r3) goto L29
        Lb:
            if (r2 == 0) goto L10
            if (r8 < r6) goto L23
            goto L12
        L10:
            if (r8 >= r7) goto L23
        L12:
            boolean r4 = r5.get(r8, r10)
            if (r2 != r4) goto L23
            int r4 = r9 - r8
            int r4 = java.lang.Math.abs(r4)
            if (r4 <= r3) goto L21
            return r9
        L21:
            int r8 = r8 + r0
            goto Lb
        L23:
            int r0 = -r0
            r2 = r2 ^ 1
            int r1 = r1 + 1
            goto L8
        L29:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.PDF417ScanningDecoder.adjustCodewordStartColumn(com.google.zxing.common.BitMatrix, int, int, boolean, int, int):int");
    }

    private static boolean checkCodewordSkew(int i, int i2, int i3) {
        return i2 + (-2) <= i && i <= i3 + 2;
    }

    private static int correctErrors(int[] iArr, int[] iArr2, int i) throws ChecksumException {
        if ((iArr2 == null || iArr2.length <= (i / 2) + 3) && i >= 0 && i <= 512) {
            return errorCorrection.decode(iArr, i, iArr2);
        }
        throw ChecksumException.getChecksumInstance();
    }

    private static BarcodeValue[][] createBarcodeMatrix(DetectionResult detectionResult) {
        DetectionResultColumn[] detectionResultColumns;
        Codeword[] codewords;
        int rowNumber;
        BarcodeValue[][] barcodeValueArr = (BarcodeValue[][]) Array.newInstance(BarcodeValue.class, detectionResult.getBarcodeRowCount(), detectionResult.getBarcodeColumnCount() + 2);
        for (int i = 0; i < barcodeValueArr.length; i++) {
            for (int i2 = 0; i2 < barcodeValueArr[i].length; i2++) {
                barcodeValueArr[i][i2] = new BarcodeValue();
            }
        }
        int i3 = 0;
        for (DetectionResultColumn detectionResultColumn : detectionResult.getDetectionResultColumns()) {
            if (detectionResultColumn != null) {
                for (Codeword codeword : detectionResultColumn.getCodewords()) {
                    if (codeword != null && (rowNumber = codeword.getRowNumber()) >= 0 && rowNumber < barcodeValueArr.length) {
                        barcodeValueArr[rowNumber][i3].setValue(codeword.getValue());
                    }
                }
            }
            i3++;
        }
        return barcodeValueArr;
    }

    private static DecoderResult createDecoderResult(DetectionResult detectionResult) throws FormatException, ChecksumException, NotFoundException {
        BarcodeValue[][] createBarcodeMatrix = createBarcodeMatrix(detectionResult);
        adjustCodewordCount(detectionResult, createBarcodeMatrix);
        ArrayList arrayList = new ArrayList();
        int[] iArr = new int[detectionResult.getBarcodeRowCount() * detectionResult.getBarcodeColumnCount()];
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i = 0; i < detectionResult.getBarcodeRowCount(); i++) {
            int i2 = 0;
            while (i2 < detectionResult.getBarcodeColumnCount()) {
                int i3 = i2 + 1;
                int[] value = createBarcodeMatrix[i][i3].getValue();
                int barcodeColumnCount = (detectionResult.getBarcodeColumnCount() * i) + i2;
                if (value.length == 0) {
                    arrayList.add(Integer.valueOf(barcodeColumnCount));
                } else if (value.length == 1) {
                    iArr[barcodeColumnCount] = value[0];
                } else {
                    arrayList3.add(Integer.valueOf(barcodeColumnCount));
                    arrayList2.add(value);
                }
                i2 = i3;
            }
        }
        int[][] iArr2 = new int[arrayList2.size()];
        for (int i4 = 0; i4 < iArr2.length; i4++) {
            iArr2[i4] = (int[]) arrayList2.get(i4);
        }
        return createDecoderResultFromAmbiguousValues(detectionResult.getBarcodeECLevel(), iArr, PDF417Common.toIntArray(arrayList), PDF417Common.toIntArray(arrayList3), iArr2);
    }

    private static DecoderResult createDecoderResultFromAmbiguousValues(int i, int[] iArr, int[] iArr2, int[] iArr3, int[][] iArr4) throws FormatException, ChecksumException {
        int[] iArr5 = new int[iArr3.length];
        int i2 = 100;
        while (true) {
            int i3 = i2 - 1;
            if (i2 > 0) {
                for (int i4 = 0; i4 < iArr5.length; i4++) {
                    iArr[iArr3[i4]] = iArr4[i4][iArr5[i4]];
                }
                try {
                    return decodeCodewords(iArr, i, iArr2);
                } catch (ChecksumException unused) {
                    if (iArr5.length == 0) {
                        throw ChecksumException.getChecksumInstance();
                    }
                    int i5 = 0;
                    while (true) {
                        if (i5 >= iArr5.length) {
                            break;
                        } else if (iArr5[i5] < iArr4[i5].length - 1) {
                            iArr5[i5] = iArr5[i5] + 1;
                            break;
                        } else {
                            iArr5[i5] = 0;
                            if (i5 == iArr5.length - 1) {
                                throw ChecksumException.getChecksumInstance();
                            }
                            i5++;
                        }
                    }
                    i2 = i3;
                }
            } else {
                throw ChecksumException.getChecksumInstance();
            }
        }
    }

    public static DecoderResult decode(BitMatrix bitMatrix, ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3, ResultPoint resultPoint4, int i, int i2) throws NotFoundException, FormatException, ChecksumException {
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn;
        DetectionResultColumn detectionResultRowIndicatorColumn2;
        int i3;
        int i4;
        int i5;
        int i6;
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn3 = null;
        DetectionResult detectionResult = null;
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn4 = null;
        int i7 = 0;
        BoundingBox boundingBox = new BoundingBox(bitMatrix, resultPoint, resultPoint2, resultPoint3, resultPoint4);
        while (i7 < 2) {
            if (resultPoint != null) {
                detectionResultRowIndicatorColumn3 = getRowIndicatorColumn(bitMatrix, boundingBox, resultPoint, true, i, i2);
            }
            detectionResultRowIndicatorColumn = detectionResultRowIndicatorColumn3;
            if (resultPoint3 != null) {
                detectionResultRowIndicatorColumn4 = getRowIndicatorColumn(bitMatrix, boundingBox, resultPoint3, false, i, i2);
            }
            detectionResult = merge(detectionResultRowIndicatorColumn, detectionResultRowIndicatorColumn4);
            if (detectionResult != null) {
                if (i7 == 0 && detectionResult.getBoundingBox() != null && (detectionResult.getBoundingBox().getMinY() < boundingBox.getMinY() || detectionResult.getBoundingBox().getMaxY() > boundingBox.getMaxY())) {
                    boundingBox = detectionResult.getBoundingBox();
                    i7++;
                    detectionResultRowIndicatorColumn3 = detectionResultRowIndicatorColumn;
                } else {
                    detectionResult.setBoundingBox(boundingBox);
                    break;
                }
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        }
        detectionResultRowIndicatorColumn = detectionResultRowIndicatorColumn3;
        int barcodeColumnCount = detectionResult.getBarcodeColumnCount() + 1;
        detectionResult.setDetectionResultColumn(0, detectionResultRowIndicatorColumn);
        detectionResult.setDetectionResultColumn(barcodeColumnCount, detectionResultRowIndicatorColumn4);
        boolean z = detectionResultRowIndicatorColumn != null;
        int i8 = i;
        int i9 = i2;
        for (int i10 = 1; i10 <= barcodeColumnCount; i10++) {
            int i11 = z ? i10 : barcodeColumnCount - i10;
            if (detectionResult.getDetectionResultColumn(i11) == null) {
                if (i11 != 0 && i11 != barcodeColumnCount) {
                    detectionResultRowIndicatorColumn2 = new DetectionResultColumn(boundingBox);
                } else {
                    detectionResultRowIndicatorColumn2 = new DetectionResultRowIndicatorColumn(boundingBox, i11 == 0);
                }
                detectionResult.setDetectionResultColumn(i11, detectionResultRowIndicatorColumn2);
                int i12 = -1;
                int i13 = i9;
                int i14 = -1;
                int i15 = i8;
                int minY = boundingBox.getMinY();
                while (minY <= boundingBox.getMaxY()) {
                    int startColumn = getStartColumn(detectionResult, i11, minY, z);
                    if (startColumn >= 0 && startColumn <= boundingBox.getMaxX()) {
                        i3 = startColumn;
                    } else if (i14 == i12) {
                        i4 = i14;
                        i5 = i13;
                        i6 = i12;
                        i13 = i5;
                        i14 = i4;
                        minY++;
                        i12 = i6;
                    } else {
                        i3 = i14;
                    }
                    i4 = i14;
                    int i16 = i13;
                    i6 = i12;
                    Codeword detectCodeword = detectCodeword(bitMatrix, boundingBox.getMinX(), boundingBox.getMaxX(), z, i3, minY, i15, i16);
                    if (detectCodeword != null) {
                        detectionResultRowIndicatorColumn2.setCodeword(minY, detectCodeword);
                        i15 = Math.min(i15, detectCodeword.getWidth());
                        i13 = Math.max(i16, detectCodeword.getWidth());
                        i14 = i3;
                        minY++;
                        i12 = i6;
                    } else {
                        i5 = i16;
                        i13 = i5;
                        i14 = i4;
                        minY++;
                        i12 = i6;
                    }
                }
                i8 = i15;
                i9 = i13;
            }
        }
        return createDecoderResult(detectionResult);
    }

    private static DecoderResult decodeCodewords(int[] iArr, int i, int[] iArr2) throws FormatException, ChecksumException {
        if (iArr.length != 0) {
            int i2 = 1 << (i + 1);
            int correctErrors = correctErrors(iArr, iArr2, i2);
            verifyCodewordCount(iArr, i2);
            DecoderResult decode = DecodedBitStreamParser.decode(iArr, String.valueOf(i));
            decode.setErrorsCorrected(Integer.valueOf(correctErrors));
            decode.setErasures(Integer.valueOf(iArr2.length));
            return decode;
        }
        throw FormatException.getFormatInstance();
    }

    private static Codeword detectCodeword(BitMatrix bitMatrix, int i, int i2, boolean z, int i3, int i4, int i5, int i6) {
        int i7;
        int decodedValue;
        int codeword;
        int adjustCodewordStartColumn = adjustCodewordStartColumn(bitMatrix, i, i2, z, i3, i4);
        int[] moduleBitCount = getModuleBitCount(bitMatrix, i, i2, z, adjustCodewordStartColumn, i4);
        if (moduleBitCount == null) {
            return null;
        }
        int sum = MathUtils.sum(moduleBitCount);
        if (z) {
            adjustCodewordStartColumn += sum;
            i7 = adjustCodewordStartColumn;
        } else {
            for (int i8 = 0; i8 < moduleBitCount.length / 2; i8++) {
                int i9 = moduleBitCount[i8];
                moduleBitCount[i8] = moduleBitCount[(moduleBitCount.length - 1) - i8];
                moduleBitCount[(moduleBitCount.length - 1) - i8] = i9;
            }
            i7 = adjustCodewordStartColumn - sum;
        }
        if (!checkCodewordSkew(sum, i5, i6) || (codeword = PDF417Common.getCodeword((decodedValue = PDF417CodewordDecoder.getDecodedValue(moduleBitCount)))) == -1) {
            return null;
        }
        return new Codeword(i7, adjustCodewordStartColumn, getCodewordBucketNumber(decodedValue), codeword);
    }

    private static BarcodeMetadata getBarcodeMetadata(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn, DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2) {
        BarcodeMetadata barcodeMetadata;
        BarcodeMetadata barcodeMetadata2;
        if (detectionResultRowIndicatorColumn == null || (barcodeMetadata = detectionResultRowIndicatorColumn.getBarcodeMetadata()) == null) {
            if (detectionResultRowIndicatorColumn2 != null) {
                return detectionResultRowIndicatorColumn2.getBarcodeMetadata();
            }
            return null;
        } else if (detectionResultRowIndicatorColumn2 != null && (barcodeMetadata2 = detectionResultRowIndicatorColumn2.getBarcodeMetadata()) != null && barcodeMetadata.getColumnCount() != barcodeMetadata2.getColumnCount() && barcodeMetadata.getErrorCorrectionLevel() != barcodeMetadata2.getErrorCorrectionLevel() && barcodeMetadata.getRowCount() != barcodeMetadata2.getRowCount()) {
            return null;
        } else {
            return barcodeMetadata;
        }
    }

    private static int[] getBitCountForCodeword(int i) {
        int[] iArr = new int[8];
        int length = iArr.length - 1;
        int i2 = 0;
        while (true) {
            int i3 = i & 1;
            if (i3 != i2) {
                length--;
                if (length < 0) {
                    return iArr;
                }
                i2 = i3;
            }
            iArr[length] = iArr[length] + 1;
            i >>= 1;
        }
    }

    private static int getCodewordBucketNumber(int i) {
        return getCodewordBucketNumber(getBitCountForCodeword(i));
    }

    private static int getMax(int[] iArr) {
        int i = -1;
        for (int i2 : iArr) {
            i = Math.max(i, i2);
        }
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0016  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0028 A[EDGE_INSN: B:29:0x0028->B:17:0x0028 ?: BREAK  , SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int[] getModuleBitCount(com.google.zxing.common.BitMatrix r6, int r7, int r8, boolean r9, int r10, int r11) {
        /*
            r0 = 8
            int[] r0 = new int[r0]
            r1 = 1
            if (r9 == 0) goto L9
            r2 = r1
            goto La
        L9:
            r2 = -1
        La:
            r3 = 0
            r4 = r9
        Lc:
            if (r9 == 0) goto L11
            if (r10 >= r8) goto L28
            goto L13
        L11:
            if (r10 < r7) goto L28
        L13:
            int r5 = r0.length
            if (r3 >= r5) goto L28
            boolean r5 = r6.get(r10, r11)
            if (r5 != r4) goto L23
            r5 = r0[r3]
            int r5 = r5 + r1
            r0[r3] = r5
            int r10 = r10 + r2
            goto Lc
        L23:
            int r3 = r3 + 1
            r4 = r4 ^ 1
            goto Lc
        L28:
            int r6 = r0.length
            if (r3 == r6) goto L37
            if (r9 == 0) goto L2e
            r7 = r8
        L2e:
            if (r10 != r7) goto L35
            int r6 = r0.length
            int r6 = r6 - r1
            if (r3 != r6) goto L35
            goto L37
        L35:
            r6 = 0
            return r6
        L37:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.decoder.PDF417ScanningDecoder.getModuleBitCount(com.google.zxing.common.BitMatrix, int, int, boolean, int, int):int[]");
    }

    private static int getNumberOfECCodeWords(int i) {
        return 2 << i;
    }

    private static DetectionResultRowIndicatorColumn getRowIndicatorColumn(BitMatrix bitMatrix, BoundingBox boundingBox, ResultPoint resultPoint, boolean z, int i, int i2) {
        DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn = new DetectionResultRowIndicatorColumn(boundingBox, z);
        int i3 = 0;
        while (i3 < 2) {
            int i4 = i3 == 0 ? 1 : -1;
            int x = (int) resultPoint.getX();
            for (int y = (int) resultPoint.getY(); y <= boundingBox.getMaxY() && y >= boundingBox.getMinY(); y += i4) {
                Codeword detectCodeword = detectCodeword(bitMatrix, 0, bitMatrix.getWidth(), z, x, y, i, i2);
                if (detectCodeword != null) {
                    detectionResultRowIndicatorColumn.setCodeword(y, detectCodeword);
                    if (z) {
                        x = detectCodeword.getStartX();
                    } else {
                        x = detectCodeword.getEndX();
                    }
                }
            }
            i3++;
        }
        return detectionResultRowIndicatorColumn;
    }

    private static int getStartColumn(DetectionResult detectionResult, int i, int i2, boolean z) {
        Codeword[] codewords;
        int i3 = z ? 1 : -1;
        Codeword codeword = null;
        int i4 = i - i3;
        if (isValidBarcodeColumn(detectionResult, i4)) {
            codeword = detectionResult.getDetectionResultColumn(i4).getCodeword(i2);
        }
        if (codeword != null) {
            return z ? codeword.getEndX() : codeword.getStartX();
        }
        Codeword codewordNearby = detectionResult.getDetectionResultColumn(i).getCodewordNearby(i2);
        if (codewordNearby != null) {
            return z ? codewordNearby.getStartX() : codewordNearby.getEndX();
        }
        if (isValidBarcodeColumn(detectionResult, i4)) {
            codewordNearby = detectionResult.getDetectionResultColumn(i4).getCodewordNearby(i2);
        }
        if (codewordNearby != null) {
            return z ? codewordNearby.getEndX() : codewordNearby.getStartX();
        }
        int i5 = 0;
        while (true) {
            i -= i3;
            if (isValidBarcodeColumn(detectionResult, i)) {
                for (Codeword codeword2 : detectionResult.getDetectionResultColumn(i).getCodewords()) {
                    if (codeword2 != null) {
                        return ((codeword2.getEndX() - codeword2.getStartX()) * i3 * i5) + (z ? codeword2.getEndX() : codeword2.getStartX());
                    }
                }
                i5++;
            } else {
                BoundingBox boundingBox = detectionResult.getBoundingBox();
                return z ? boundingBox.getMinX() : boundingBox.getMaxX();
            }
        }
    }

    private static boolean isValidBarcodeColumn(DetectionResult detectionResult, int i) {
        return i >= 0 && i <= detectionResult.getBarcodeColumnCount() + 1;
    }

    private static DetectionResult merge(DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn, DetectionResultRowIndicatorColumn detectionResultRowIndicatorColumn2) throws NotFoundException {
        BarcodeMetadata barcodeMetadata;
        if ((detectionResultRowIndicatorColumn == null && detectionResultRowIndicatorColumn2 == null) || (barcodeMetadata = getBarcodeMetadata(detectionResultRowIndicatorColumn, detectionResultRowIndicatorColumn2)) == null) {
            return null;
        }
        return new DetectionResult(barcodeMetadata, BoundingBox.merge(adjustBoundingBox(detectionResultRowIndicatorColumn), adjustBoundingBox(detectionResultRowIndicatorColumn2)));
    }

    public static String toString(BarcodeValue[][] barcodeValueArr) {
        Formatter formatter = new Formatter();
        for (int i = 0; i < barcodeValueArr.length; i++) {
            try {
                formatter.format("Row %2d: ", Integer.valueOf(i));
                for (int i2 = 0; i2 < barcodeValueArr[i].length; i2++) {
                    BarcodeValue barcodeValue = barcodeValueArr[i][i2];
                    if (barcodeValue.getValue().length == 0) {
                        formatter.format("        ", null);
                    } else {
                        formatter.format("%4d(%2d)", Integer.valueOf(barcodeValue.getValue()[0]), barcodeValue.getConfidence(barcodeValue.getValue()[0]));
                    }
                }
                formatter.format("%n", new Object[0]);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        formatter.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }

    private static void verifyCodewordCount(int[] iArr, int i) throws FormatException {
        if (iArr.length >= 4) {
            int i2 = iArr[0];
            if (i2 > iArr.length) {
                throw FormatException.getFormatInstance();
            }
            if (i2 != 0) {
                return;
            }
            if (i < iArr.length) {
                iArr[0] = iArr.length - i;
                return;
            }
            throw FormatException.getFormatInstance();
        }
        throw FormatException.getFormatInstance();
    }

    private static int getCodewordBucketNumber(int[] iArr) {
        return ((((iArr[0] - iArr[2]) + iArr[4]) - iArr[6]) + 9) % 9;
    }
}
