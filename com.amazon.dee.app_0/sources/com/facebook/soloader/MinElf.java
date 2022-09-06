package com.facebook.soloader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import kotlin.UShort;
import org.bouncycastle.asn1.cmc.BodyPartID;
/* loaded from: classes2.dex */
public final class MinElf {
    public static final int DT_NEEDED = 1;
    public static final int DT_NULL = 0;
    public static final int DT_STRTAB = 5;
    public static final int ELF_MAGIC = 1179403647;
    public static final int PN_XNUM = 65535;
    public static final int PT_DYNAMIC = 2;
    public static final int PT_LOAD = 1;
    private static final String TAG = "MinElf";

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class ElfError extends RuntimeException {
        ElfError(String str) {
            super(str);
        }
    }

    /* loaded from: classes2.dex */
    public enum ISA {
        NOT_SO("not_so"),
        X86("x86"),
        ARM("armeabi-v7a"),
        X86_64("x86_64"),
        AARCH64("arm64-v8a"),
        OTHERS("others");
        
        private final String value;

        ISA(String str) {
            this.value = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.value;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(3:13|14|15) */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0015, code lost:
        r0 = r0 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0018, code lost:
        if (r0 <= 3) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001a, code lost:
        java.lang.Thread.interrupted();
        android.util.Log.e(com.facebook.soloader.MinElf.TAG, "retrying extract_DT_NEEDED due to ClosedByInterruptException", r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0029, code lost:
        throw r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x002a, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002d, code lost:
        throw r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0012, code lost:
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0014, code lost:
        r2 = move-exception;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String[] extract_DT_NEEDED(java.io.File r5) throws java.io.IOException {
        /*
            r0 = 0
        L1:
            java.io.FileInputStream r1 = new java.io.FileInputStream
            r1.<init>(r5)
            java.nio.channels.FileChannel r2 = r1.getChannel()     // Catch: java.lang.Throwable -> L12 java.nio.channels.ClosedByInterruptException -> L14
            java.lang.String[] r5 = extract_DT_NEEDED(r2)     // Catch: java.lang.Throwable -> L12 java.nio.channels.ClosedByInterruptException -> L14
            r1.close()
            return r5
        L12:
            r5 = move-exception
            goto L2a
        L14:
            r2 = move-exception
            int r0 = r0 + 1
            r3 = 3
            if (r0 > r3) goto L29
            java.lang.Thread.interrupted()     // Catch: java.lang.Throwable -> L12
            java.lang.String r3 = "MinElf"
            java.lang.String r4 = "retrying extract_DT_NEEDED due to ClosedByInterruptException"
            android.util.Log.e(r3, r4, r2)     // Catch: java.lang.Throwable -> L12
            r1.close()
            goto L1
        L29:
            throw r2     // Catch: java.lang.Throwable -> L12
        L2a:
            r1.close()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.MinElf.extract_DT_NEEDED(java.io.File):java.lang.String[]");
    }

    private static long get64(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(fileChannel, byteBuffer, 8, j);
        return byteBuffer.getLong();
    }

    private static String getSz(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j2 = 1 + j;
            short u8Var = getu8(fileChannel, byteBuffer, j);
            if (u8Var != 0) {
                sb.append((char) u8Var);
                j = j2;
            } else {
                return sb.toString();
            }
        }
    }

    private static int getu16(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(fileChannel, byteBuffer, 2, j);
        return byteBuffer.getShort() & UShort.MAX_VALUE;
    }

    private static long getu32(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(fileChannel, byteBuffer, 4, j);
        return byteBuffer.getInt() & BodyPartID.bodyIdMax;
    }

    private static short getu8(FileChannel fileChannel, ByteBuffer byteBuffer, long j) throws IOException {
        read(fileChannel, byteBuffer, 1, j);
        return (short) (byteBuffer.get() & 255);
    }

    private static void read(FileChannel fileChannel, ByteBuffer byteBuffer, int i, long j) throws IOException {
        int read;
        byteBuffer.position(0);
        byteBuffer.limit(i);
        while (byteBuffer.remaining() > 0 && (read = fileChannel.read(byteBuffer, j)) != -1) {
            j += read;
        }
        if (byteBuffer.remaining() <= 0) {
            byteBuffer.position(0);
            return;
        }
        throw new ElfError("ELF file truncated");
    }

    /* JADX WARN: Removed duplicated region for block: B:129:0x01f7 A[LOOP:1: B:48:0x00c3->B:129:0x01f7, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0119 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x010f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String[] extract_DT_NEEDED(java.nio.channels.FileChannel r27) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 540
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.MinElf.extract_DT_NEEDED(java.nio.channels.FileChannel):java.lang.String[]");
    }
}
