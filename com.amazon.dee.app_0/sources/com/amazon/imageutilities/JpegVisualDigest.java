package com.amazon.imageutilities;

import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
/* loaded from: classes12.dex */
public class JpegVisualDigest {
    private static final int BUFFER_SIZE = 32768;
    private static final byte[] EMPTY_TWO_BYTE_BUFFER = {0, 0};
    private final SafeMessageDigestFactory safeMessageDigestFactory;

    public JpegVisualDigest(String str) throws UnsupportedAlgorithmException {
        this.safeMessageDigestFactory = new SafeMessageDigestFactory(str);
    }

    private MarkerSearchResult findNextMarker(InputStream inputStream) throws IOException {
        int i;
        byte b;
        inputStream.mark(Integer.MAX_VALUE);
        boolean z = false;
        if (readBytes(1, inputStream)[0] == -1) {
            i = 1;
            do {
                b = readBytes(1, inputStream)[0];
                i++;
            } while (-1 == b);
            if (b == -2 || b == -37 || b == -35 || ((b >= -32 && b <= -17) || (b >= -64 && b <= -49))) {
                z = true;
            }
        } else {
            i = 1;
        }
        inputStream.reset();
        return new MarkerSearchResult(i, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ byte lambda$digest$0(byte[] bArr, int i) {
        return bArr[i];
    }

    private byte[] readBytes(int i, InputStream inputStream) throws IncompleteBytesReadException, IOException {
        byte[] bArr = new byte[i];
        int i2 = i;
        while (i2 > 0) {
            int i3 = i - i2;
            int i4 = i - i3;
            long read = inputStream.read(bArr, i3, i4);
            if (read <= 0) {
                throw new IncompleteBytesReadException(String.format("Only managed to read %d bytes instead of %d", Long.valueOf(read), Integer.valueOf(i4)));
            }
            i2 = (int) (i2 - read);
        }
        return bArr;
    }

    private void skipMetadata(InputStream inputStream, DigestResultBuilder digestResultBuilder) throws IOException {
        while (true) {
            MarkerSearchResult findNextMarker = findNextMarker(inputStream);
            if (findNextMarker.isSkipForVisualDigest()) {
                skipSegment(inputStream, findNextMarker, digestResultBuilder);
            } else {
                return;
            }
        }
    }

    private void skipSegment(InputStream inputStream, MarkerSearchResult markerSearchResult, DigestResultBuilder digestResultBuilder) throws IOException, IncompleteBytesReadException, InvalidMetadataLengthException {
        byte[] readBytes = readBytes(markerSearchResult.getPaddedLength(), inputStream);
        byte[] readBytes2 = readBytes(2, inputStream);
        int fromByteArray = Ints.fromByteArray(Bytes.concat(EMPTY_TWO_BYTE_BUFFER, readBytes2)) - readBytes2.length;
        if (fromByteArray >= 0) {
            digestResultBuilder.updateFileOnly(readBytes).updateFileOnly(readBytes2).updateFileOnly(readBytes(fromByteArray, inputStream));
            return;
        }
        throw new InvalidMetadataLengthException(String.format("Invalid metadata length of %d.", Integer.valueOf(fromByteArray)));
    }

    private void skipSoi(InputStream inputStream, String str, DigestResultBuilder digestResultBuilder) throws IOException, JpegFormatException {
        byte[] readBytes = readBytes(2, inputStream);
        if (Arrays.equals(readBytes, JpegSegmentMarker.SOI)) {
            digestResultBuilder.updateFileOnly(readBytes);
            return;
        }
        throw new JpegFormatException(String.format("%s is not a valid JPEG file - no SOI marker found.", str));
    }

    public DigestResult digest(File file) throws IOException, JpegFormatException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 32768);
        try {
            DigestResult digest = digest(bufferedInputStream, file.getAbsolutePath());
            bufferedInputStream.close();
            return digest;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    bufferedInputStream.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public DigestResult digest(InputStream inputStream) throws IOException, JpegFormatException {
        return digest(inputStream, inputStream.toString());
    }

    private DigestResult digest(InputStream inputStream, String str) throws IOException, JpegFormatException {
        DigestResultBuilder digestResultBuilder = new DigestResultBuilder(this.safeMessageDigestFactory);
        skipSoi(inputStream, str, digestResultBuilder);
        skipMetadata(inputStream, digestResultBuilder);
        BacktrackingByteSearch backtrackingByteSearch = new BacktrackingByteSearch(JpegSegmentMarker.EOI);
        final byte[] bArr = new byte[32768];
        while (true) {
            int read = inputStream.read(bArr);
            if (read > 0) {
                int indexOfLastByteOfLastOccurence = backtrackingByteSearch.indexOfLastByteOfLastOccurence(new ByteProvider() { // from class: com.amazon.imageutilities.-$$Lambda$JpegVisualDigest$LQATQpzzEx0fHXgoANcXxfPBS0A
                    @Override // com.amazon.imageutilities.ByteProvider
                    public final byte get(int i) {
                        return JpegVisualDigest.lambda$digest$0(bArr, i);
                    }
                }, read);
                if (indexOfLastByteOfLastOccurence != -1) {
                    digestResultBuilder.updateWithEOI(bArr, read, indexOfLastByteOfLastOccurence);
                } else {
                    digestResultBuilder.update(bArr, read);
                }
            } else {
                return digestResultBuilder.build();
            }
        }
    }
}
