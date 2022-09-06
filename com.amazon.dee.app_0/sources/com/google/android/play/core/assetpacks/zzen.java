package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Properties;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzen {
    private static final com.google.android.play.core.internal.zzag zza = new com.google.android.play.core.internal.zzag("SliceMetadataManager");
    private final zzbh zzc;
    private final String zzd;
    private final int zze;
    private final long zzf;
    private final String zzg;
    private final byte[] zzb = new byte[8192];
    private int zzh = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzen(zzbh zzbhVar, String str, int i, long j, String str2) {
        this.zzc = zzbhVar;
        this.zzd = str;
        this.zze = i;
        this.zzf = j;
        this.zzg = str2;
    }

    private final File zzn() {
        File zzo = this.zzc.zzo(this.zzd, this.zze, this.zzf, this.zzg);
        if (!zzo.exists()) {
            zzo.mkdirs();
        }
        return zzo;
    }

    private final File zzo() throws IOException {
        File zzn = this.zzc.zzn(this.zzd, this.zze, this.zzf, this.zzg);
        zzn.getParentFile().mkdirs();
        zzn.createNewFile();
        return zzn;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int zza() throws IOException {
        File zzn = this.zzc.zzn(this.zzd, this.zze, this.zzf, this.zzg);
        if (!zzn.exists()) {
            return 0;
        }
        FileInputStream fileInputStream = new FileInputStream(zzn);
        try {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
            if (Integer.parseInt(properties.getProperty("fileStatus", "-1")) == 4) {
                return -1;
            }
            if (properties.getProperty("previousChunk") != null) {
                return Integer.parseInt(properties.getProperty("previousChunk")) + 1;
            }
            throw new zzck("Slice checkpoint file corrupt.");
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzem zzb() throws IOException {
        File zzn = this.zzc.zzn(this.zzd, this.zze, this.zzf, this.zzg);
        if (zzn.exists()) {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream(zzn);
            try {
                properties.load(fileInputStream);
                fileInputStream.close();
                if (properties.getProperty("fileStatus") != null && properties.getProperty("previousChunk") != null) {
                    try {
                        int parseInt = Integer.parseInt(properties.getProperty("fileStatus"));
                        String property = properties.getProperty("fileName");
                        long parseLong = Long.parseLong(properties.getProperty("fileOffset", "-1"));
                        long parseLong2 = Long.parseLong(properties.getProperty("remainingBytes", "-1"));
                        int parseInt2 = Integer.parseInt(properties.getProperty("previousChunk"));
                        this.zzh = Integer.parseInt(properties.getProperty("metadataFileCounter", "0"));
                        return new zzbp(parseInt, property, parseLong, parseLong2, parseInt2);
                    } catch (NumberFormatException e) {
                        throw new zzck("Slice checkpoint file corrupt.", e);
                    }
                }
                throw new zzck("Slice checkpoint file corrupt.");
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        }
        throw new zzck("Slice checkpoint file does not exist.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final File zzc() {
        return new File(zzn(), String.format("%s-NAM.dat", Integer.valueOf(this.zzh)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzd(InputStream inputStream, long j) throws IOException {
        int read;
        RandomAccessFile randomAccessFile = new RandomAccessFile(zzc(), "rw");
        try {
            randomAccessFile.seek(j);
            do {
                read = inputStream.read(this.zzb);
                if (read > 0) {
                    randomAccessFile.write(this.zzb, 0, read);
                }
            } while (read == 8192);
            randomAccessFile.close();
        } catch (Throwable th) {
            try {
                randomAccessFile.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zze(long j, byte[] bArr, int i, int i2) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(zzc(), "rw");
        try {
            randomAccessFile.seek(j);
            randomAccessFile.write(bArr, i, i2);
            randomAccessFile.close();
        } catch (Throwable th) {
            try {
                randomAccessFile.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzf(int i) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", "3");
        properties.put("fileOffset", String.valueOf(zzc().length()));
        properties.put("previousChunk", String.valueOf(i));
        properties.put("metadataFileCounter", String.valueOf(this.zzh));
        FileOutputStream fileOutputStream = new FileOutputStream(zzo());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzg(String str, long j, long j2, int i) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", "1");
        properties.put("fileName", str);
        properties.put("fileOffset", String.valueOf(j));
        properties.put("remainingBytes", String.valueOf(j2));
        properties.put("previousChunk", String.valueOf(i));
        properties.put("metadataFileCounter", String.valueOf(this.zzh));
        FileOutputStream fileOutputStream = new FileOutputStream(zzo());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzh(byte[] bArr, int i) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", "2");
        properties.put("previousChunk", String.valueOf(i));
        properties.put("metadataFileCounter", String.valueOf(this.zzh));
        FileOutputStream fileOutputStream = new FileOutputStream(zzo());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
            File zzm = this.zzc.zzm(this.zzd, this.zze, this.zzf, this.zzg);
            if (zzm.exists()) {
                zzm.delete();
            }
            fileOutputStream = new FileOutputStream(zzm);
            try {
                fileOutputStream.write(bArr);
            } finally {
                try {
                    fileOutputStream.close();
                } catch (Throwable unused) {
                }
            }
        } finally {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzi(int i) throws IOException {
        Properties properties = new Properties();
        properties.put("fileStatus", "4");
        properties.put("previousChunk", String.valueOf(i));
        properties.put("metadataFileCounter", String.valueOf(this.zzh));
        FileOutputStream fileOutputStream = new FileOutputStream(zzo());
        try {
            properties.store(fileOutputStream, (String) null);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzj(byte[] bArr) throws IOException {
        this.zzh++;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(zzn(), String.format("%s-LFH.dat", Integer.valueOf(this.zzh))));
            fileOutputStream.write(bArr);
            fileOutputStream.close();
        } catch (IOException e) {
            throw new zzck("Could not write metadata file.", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzk(byte[] bArr, InputStream inputStream) throws IOException {
        this.zzh++;
        FileOutputStream fileOutputStream = new FileOutputStream(zzc());
        try {
            fileOutputStream.write(bArr);
            int read = inputStream.read(this.zzb);
            while (read > 0) {
                fileOutputStream.write(this.zzb, 0, read);
                read = inputStream.read(this.zzb);
            }
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzl(byte[] bArr, int i, int i2) throws IOException {
        this.zzh++;
        FileOutputStream fileOutputStream = new FileOutputStream(zzc());
        try {
            fileOutputStream.write(bArr, 0, i2);
            fileOutputStream.close();
        } catch (Throwable th) {
            try {
                fileOutputStream.close();
            } catch (Throwable unused) {
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzm() {
        File zzn = this.zzc.zzn(this.zzd, this.zze, this.zzf, this.zzg);
        if (!zzn.exists()) {
            return false;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(zzn);
            Properties properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
            if (properties.getProperty("fileStatus") != null) {
                return Integer.parseInt(properties.getProperty("fileStatus")) == 4;
            }
            zza.zzb("Slice checkpoint file corrupt while checking if extraction finished.", new Object[0]);
            return false;
        } catch (IOException e) {
            zza.zzb("Could not read checkpoint while checking if extraction finished. %s", e);
            return false;
        }
    }
}
