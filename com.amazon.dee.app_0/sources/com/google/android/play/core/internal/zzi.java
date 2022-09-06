package com.google.android.play.core.internal;

import android.util.Pair;
import com.amazon.whispercloak.KeyUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
public final class zzi {
    public static X509Certificate[][] zza(String str) throws zzf, SecurityException, IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(str, "r");
        try {
            Pair zzc = zzj.zzc(randomAccessFile);
            if (zzc != null) {
                ByteBuffer byteBuffer = (ByteBuffer) zzc.first;
                long longValue = ((Long) zzc.second).longValue();
                long j = (-20) + longValue;
                if (j >= 0) {
                    randomAccessFile.seek(j);
                    if (randomAccessFile.readInt() == 1347094023) {
                        throw new zzf("ZIP64 APK not supported");
                    }
                }
                long zza = zzj.zza(byteBuffer);
                if (zza < longValue) {
                    if (zzj.zzb(byteBuffer) + zza != longValue) {
                        throw new zzf("ZIP Central Directory is not immediately followed by End of Central Directory");
                    }
                    if (zza >= 32) {
                        ByteBuffer allocate = ByteBuffer.allocate(24);
                        allocate.order(ByteOrder.LITTLE_ENDIAN);
                        randomAccessFile.seek(zza - allocate.capacity());
                        randomAccessFile.readFully(allocate.array(), allocate.arrayOffset(), allocate.capacity());
                        if (allocate.getLong(8) == 2334950737559900225L && allocate.getLong(16) == 3617552046287187010L) {
                            int i = 0;
                            long j2 = allocate.getLong(0);
                            if (j2 < allocate.capacity() || j2 > 2147483639) {
                                StringBuilder sb = new StringBuilder(57);
                                sb.append("APK Signing Block size out of range: ");
                                sb.append(j2);
                                throw new zzf(sb.toString());
                            }
                            int i2 = (int) (8 + j2);
                            long j3 = zza - i2;
                            if (j3 >= 0) {
                                ByteBuffer allocate2 = ByteBuffer.allocate(i2);
                                allocate2.order(ByteOrder.LITTLE_ENDIAN);
                                randomAccessFile.seek(j3);
                                randomAccessFile.readFully(allocate2.array(), allocate2.arrayOffset(), allocate2.capacity());
                                long j4 = allocate2.getLong(0);
                                if (j4 == j2) {
                                    Pair create = Pair.create(allocate2, Long.valueOf(j3));
                                    ByteBuffer byteBuffer2 = (ByteBuffer) create.first;
                                    long longValue2 = ((Long) create.second).longValue();
                                    if (byteBuffer2.order() == ByteOrder.LITTLE_ENDIAN) {
                                        int capacity = byteBuffer2.capacity() - 24;
                                        if (capacity >= 8) {
                                            int capacity2 = byteBuffer2.capacity();
                                            if (capacity <= byteBuffer2.capacity()) {
                                                int limit = byteBuffer2.limit();
                                                int position = byteBuffer2.position();
                                                byteBuffer2.position(0);
                                                byteBuffer2.limit(capacity);
                                                byteBuffer2.position(8);
                                                ByteBuffer slice = byteBuffer2.slice();
                                                slice.order(byteBuffer2.order());
                                                byteBuffer2.position(0);
                                                byteBuffer2.limit(limit);
                                                byteBuffer2.position(position);
                                                while (slice.hasRemaining()) {
                                                    i++;
                                                    if (slice.remaining() >= 8) {
                                                        long j5 = slice.getLong();
                                                        if (j5 >= 4 && j5 <= 2147483647L) {
                                                            int i3 = (int) j5;
                                                            int position2 = slice.position() + i3;
                                                            if (i3 <= slice.remaining()) {
                                                                if (slice.getInt() == 1896449818) {
                                                                    X509Certificate[][] zzl = zzl(randomAccessFile.getChannel(), new zze(zze(slice, i3 - 4), longValue2, zza, longValue, byteBuffer, null));
                                                                    randomAccessFile.close();
                                                                    return zzl;
                                                                }
                                                                slice.position(position2);
                                                            } else {
                                                                int remaining = slice.remaining();
                                                                StringBuilder sb2 = new StringBuilder(91);
                                                                sb2.append("APK Signing Block entry #");
                                                                sb2.append(i);
                                                                sb2.append(" size out of range: ");
                                                                sb2.append(i3);
                                                                sb2.append(", available: ");
                                                                sb2.append(remaining);
                                                                throw new zzf(sb2.toString());
                                                            }
                                                        } else {
                                                            StringBuilder sb3 = new StringBuilder(76);
                                                            sb3.append("APK Signing Block entry #");
                                                            sb3.append(i);
                                                            sb3.append(" size out of range: ");
                                                            sb3.append(j5);
                                                            throw new zzf(sb3.toString());
                                                        }
                                                    } else {
                                                        StringBuilder sb4 = new StringBuilder(70);
                                                        sb4.append("Insufficient data to read size of APK Signing Block entry #");
                                                        sb4.append(i);
                                                        throw new zzf(sb4.toString());
                                                    }
                                                }
                                                throw new zzf("No APK Signature Scheme v2 block in APK Signing Block");
                                            }
                                            StringBuilder sb5 = new StringBuilder(41);
                                            sb5.append("end > capacity: ");
                                            sb5.append(capacity);
                                            sb5.append(" > ");
                                            sb5.append(capacity2);
                                            throw new IllegalArgumentException(sb5.toString());
                                        }
                                        StringBuilder sb6 = new StringBuilder(38);
                                        sb6.append("end < start: ");
                                        sb6.append(capacity);
                                        sb6.append(" < ");
                                        sb6.append(8);
                                        throw new IllegalArgumentException(sb6.toString());
                                    }
                                    throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
                                }
                                StringBuilder sb7 = new StringBuilder(103);
                                sb7.append("APK Signing Block sizes in header and footer do not match: ");
                                sb7.append(j4);
                                sb7.append(" vs ");
                                sb7.append(j2);
                                throw new zzf(sb7.toString());
                            }
                            StringBuilder sb8 = new StringBuilder(59);
                            sb8.append("APK Signing Block offset out of range: ");
                            sb8.append(j3);
                            throw new zzf(sb8.toString());
                        }
                        throw new zzf("No APK Signing Block before ZIP Central Directory");
                    }
                    StringBuilder sb9 = new StringBuilder(87);
                    sb9.append("APK too small for APK Signing Block. ZIP Central Directory offset: ");
                    sb9.append(zza);
                    throw new zzf(sb9.toString());
                }
                StringBuilder sb10 = new StringBuilder(122);
                sb10.append("ZIP Central Directory offset out of range: ");
                sb10.append(zza);
                sb10.append(". ZIP End of Central Directory offset: ");
                sb10.append(longValue);
                throw new zzf(sb10.toString());
            }
            long length = randomAccessFile.length();
            StringBuilder sb11 = new StringBuilder(102);
            sb11.append("Not an APK file: ZIP End of Central Directory record not found in file with ");
            sb11.append(length);
            sb11.append(" bytes");
            throw new zzf(sb11.toString());
        } finally {
            try {
                randomAccessFile.close();
            } catch (IOException unused) {
            }
        }
    }

    private static int zzb(int i) {
        if (i != 1) {
            if (i != 2) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline27(44, "Unknown content digest algorthm: ", i));
            }
            return 64;
        }
        return 32;
    }

    private static int zzc(int i) {
        if (i != 513) {
            if (i == 514) {
                return 2;
            }
            if (i == 769) {
                return 1;
            }
            switch (i) {
                case 257:
                case 259:
                    return 1;
                case 258:
                case 260:
                    return 2;
                default:
                    String valueOf = String.valueOf(Long.toHexString(i));
                    throw new IllegalArgumentException(valueOf.length() != 0 ? "Unknown signature algorithm: 0x".concat(valueOf) : new String("Unknown signature algorithm: 0x"));
            }
        }
        return 1;
    }

    private static String zzd(int i) {
        if (i != 1) {
            if (i != 2) {
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline27(44, "Unknown content digest algorthm: ", i));
            }
            return "SHA-512";
        }
        return "SHA-256";
    }

    private static ByteBuffer zze(ByteBuffer byteBuffer, int i) throws BufferUnderflowException {
        int limit = byteBuffer.limit();
        int position = byteBuffer.position();
        int i2 = i + position;
        if (i2 >= position && i2 <= limit) {
            byteBuffer.limit(i2);
            try {
                ByteBuffer slice = byteBuffer.slice();
                slice.order(byteBuffer.order());
                byteBuffer.position(i2);
                return slice;
            } finally {
                byteBuffer.limit(limit);
            }
        }
        throw new BufferUnderflowException();
    }

    private static ByteBuffer zzf(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.remaining() >= 4) {
            int i = byteBuffer.getInt();
            if (i >= 0) {
                if (i <= byteBuffer.remaining()) {
                    return zze(byteBuffer, i);
                }
                throw new IOException(GeneratedOutlineSupport1.outline28(101, "Length-prefixed field longer than remaining buffer. Field length: ", i, ", remaining: ", byteBuffer.remaining()));
            }
            throw new IllegalArgumentException("Negative length");
        }
        throw new IOException(GeneratedOutlineSupport1.outline27(93, "Remaining buffer too short to contain length of length-prefixed field. Remaining: ", byteBuffer.remaining()));
    }

    private static void zzg(int i, byte[] bArr, int i2) {
        bArr[1] = (byte) (i & 255);
        bArr[2] = (byte) ((i >>> 8) & 255);
        bArr[3] = (byte) ((i >>> 16) & 255);
        bArr[4] = (byte) (i >> 24);
    }

    private static void zzh(Map map, FileChannel fileChannel, long j, long j2, long j3, ByteBuffer byteBuffer) throws SecurityException {
        if (!map.isEmpty()) {
            zzd zzdVar = new zzd(fileChannel, 0L, j);
            zzd zzdVar2 = new zzd(fileChannel, j2, j3 - j2);
            ByteBuffer duplicate = byteBuffer.duplicate();
            duplicate.order(ByteOrder.LITTLE_ENDIAN);
            zzj.zzd(duplicate, j);
            zzb zzbVar = new zzb(duplicate);
            int[] iArr = new int[map.size()];
            int i = 0;
            for (Integer num : map.keySet()) {
                iArr[i] = num.intValue();
                i++;
            }
            try {
                byte[][] zzk = zzk(iArr, new zzc[]{zzdVar, zzdVar2, zzbVar});
                for (int i2 = 0; i2 < iArr.length; i2++) {
                    int i3 = iArr[i2];
                    if (!MessageDigest.isEqual((byte[]) map.get(Integer.valueOf(i3)), zzk[i2])) {
                        throw new SecurityException(zzd(i3).concat(" digest of contents did not verify"));
                    }
                }
                return;
            } catch (DigestException e) {
                throw new SecurityException("Failed to compute digest(s) of contents", e);
            }
        }
        throw new SecurityException("No digests provided");
    }

    private static byte[] zzi(ByteBuffer byteBuffer) throws IOException {
        int i = byteBuffer.getInt();
        if (i >= 0) {
            if (i <= byteBuffer.remaining()) {
                byte[] bArr = new byte[i];
                byteBuffer.get(bArr);
                return bArr;
            }
            throw new IOException(GeneratedOutlineSupport1.outline28(90, "Underflow while reading length-prefixed value. Length: ", i, ", available: ", byteBuffer.remaining()));
        }
        throw new IOException("Negative length");
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0049, code lost:
        r11 = zzc(r10);
        r12 = zzc(r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0051, code lost:
        if (r11 == 1) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0053, code lost:
        if (r12 == 1) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.security.cert.X509Certificate[] zzj(java.nio.ByteBuffer r21, java.util.Map r22, java.security.cert.CertificateFactory r23) throws java.lang.SecurityException, java.io.IOException {
        /*
            Method dump skipped, instructions count: 684
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.play.core.internal.zzi.zzj(java.nio.ByteBuffer, java.util.Map, java.security.cert.CertificateFactory):java.security.cert.X509Certificate[]");
    }

    private static byte[][] zzk(int[] iArr, zzc[] zzcVarArr) throws DigestException {
        long j;
        int i;
        int length;
        int i2;
        String str;
        long j2 = 0;
        int i3 = 0;
        long j3 = 0;
        int i4 = 0;
        while (true) {
            j = 1048576;
            i = 3;
            if (i4 >= 3) {
                break;
            }
            j3 += (zzcVarArr[i4].zza() + 1048575) / 1048576;
            i4++;
        }
        if (j3 < 2097151) {
            int i5 = (int) j3;
            byte[][] bArr = new byte[iArr.length];
            int i6 = 0;
            while (true) {
                length = iArr.length;
                i2 = 1;
                if (i6 >= length) {
                    break;
                }
                byte[] bArr2 = new byte[(zzb(iArr[i6]) * i5) + 5];
                bArr2[0] = 90;
                zzg(i5, bArr2, 1);
                bArr[i6] = bArr2;
                i6++;
            }
            byte[] bArr3 = new byte[5];
            bArr3[0] = -91;
            MessageDigest[] messageDigestArr = new MessageDigest[length];
            int i7 = 0;
            while (true) {
                str = " digest not supported";
                if (i7 >= iArr.length) {
                    break;
                }
                String zzd = zzd(iArr[i7]);
                try {
                    messageDigestArr[i7] = MessageDigest.getInstance(zzd);
                    i7++;
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(zzd.concat(str), e);
                }
            }
            int i8 = 0;
            int i9 = 0;
            while (i3 < i) {
                zzc zzcVar = zzcVarArr[i3];
                long zza = zzcVar.zza();
                int i10 = i9;
                String str2 = str;
                int i11 = i8;
                long j4 = j2;
                while (zza > j2) {
                    int min = (int) Math.min(zza, j);
                    zzg(min, bArr3, i2);
                    for (MessageDigest messageDigest : messageDigestArr) {
                        messageDigest.update(bArr3);
                    }
                    try {
                        zzcVar.zzb(messageDigestArr, j4, min);
                        int i12 = 0;
                        while (i12 < iArr.length) {
                            int i13 = iArr[i12];
                            byte[] bArr4 = bArr[i12];
                            int zzb = zzb(i13);
                            MessageDigest messageDigest2 = messageDigestArr[i12];
                            byte[] bArr5 = bArr3;
                            int digest = messageDigest2.digest(bArr4, (i11 * zzb) + 5, zzb);
                            if (digest != zzb) {
                                String algorithm = messageDigest2.getAlgorithm();
                                StringBuilder sb = new StringBuilder(String.valueOf(algorithm).length() + 46);
                                sb.append("Unexpected output size of ");
                                sb.append(algorithm);
                                sb.append(" digest: ");
                                sb.append(digest);
                                throw new RuntimeException(sb.toString());
                            }
                            i12++;
                            bArr3 = bArr5;
                        }
                        long j5 = min;
                        j4 += j5;
                        zza -= j5;
                        i11++;
                        j2 = 0;
                        j = 1048576;
                        i2 = 1;
                    } catch (IOException e2) {
                        throw new DigestException(GeneratedOutlineSupport1.outline28(59, "Failed to digest chunk #", i11, " of section #", i10), e2);
                    }
                }
                i9 = i10 + 1;
                i3++;
                j2 = 0;
                j = 1048576;
                i = 3;
                i2 = 1;
                i8 = i11;
                str = str2;
            }
            String str3 = str;
            byte[][] bArr6 = new byte[iArr.length];
            for (int i14 = 0; i14 < iArr.length; i14++) {
                int i15 = iArr[i14];
                byte[] bArr7 = bArr[i14];
                String zzd2 = zzd(i15);
                try {
                    bArr6[i14] = MessageDigest.getInstance(zzd2).digest(bArr7);
                } catch (NoSuchAlgorithmException e3) {
                    throw new RuntimeException(zzd2.concat(str3), e3);
                }
            }
            return bArr6;
        }
        StringBuilder sb2 = new StringBuilder(37);
        sb2.append("Too many chunks: ");
        sb2.append(j3);
        throw new DigestException(sb2.toString());
    }

    private static X509Certificate[][] zzl(FileChannel fileChannel, zze zzeVar) throws SecurityException {
        ByteBuffer byteBuffer;
        long j;
        long j2;
        long j3;
        ByteBuffer byteBuffer2;
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance(KeyUtils.X509_CERITIFATE_FACTORY);
            try {
                byteBuffer = zzeVar.zza;
                ByteBuffer zzf = zzf(byteBuffer);
                int i = 0;
                while (zzf.hasRemaining()) {
                    i++;
                    try {
                        arrayList.add(zzj(zzf(zzf), hashMap, certificateFactory));
                    } catch (IOException | SecurityException | BufferUnderflowException e) {
                        StringBuilder sb = new StringBuilder(48);
                        sb.append("Failed to parse/verify signer #");
                        sb.append(i);
                        sb.append(" block");
                        throw new SecurityException(sb.toString(), e);
                    }
                }
                if (i > 0) {
                    if (!hashMap.isEmpty()) {
                        j = zzeVar.zzb;
                        j2 = zzeVar.zzc;
                        j3 = zzeVar.zzd;
                        byteBuffer2 = zzeVar.zze;
                        zzh(hashMap, fileChannel, j, j2, j3, byteBuffer2);
                        return (X509Certificate[][]) arrayList.toArray(new X509Certificate[arrayList.size()]);
                    }
                    throw new SecurityException("No content digests found");
                }
                throw new SecurityException("No signers found");
            } catch (IOException e2) {
                throw new SecurityException("Failed to read list of signers", e2);
            }
        } catch (CertificateException e3) {
            throw new RuntimeException("Failed to obtain X.509 CertificateFactory", e3);
        }
    }
}
