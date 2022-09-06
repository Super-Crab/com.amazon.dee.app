package com.google.android.play.core.assetpacks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
/* compiled from: com.google.android.play:core@@1.10.3 */
/* loaded from: classes2.dex */
final class zzbk extends com.google.android.play.core.internal.zzcm {
    private final File zza;
    private final File zzb;
    private final NavigableMap zzc = new TreeMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbk(File file, File file2) throws IOException {
        this.zza = file;
        this.zzb = file2;
        List<File> zza = zzep.zza(this.zza, this.zzb);
        if (!zza.isEmpty()) {
            long j = 0;
            for (File file3 : zza) {
                this.zzc.put(Long.valueOf(j), file3);
                j += file3.length();
            }
            return;
        }
        throw new zzck(String.format("Virtualized slice archive empty for %s, %s", this.zza, this.zzb));
    }

    private final InputStream zzd(long j, Long l) throws IOException {
        FileInputStream fileInputStream = new FileInputStream((File) this.zzc.get(l));
        if (fileInputStream.skip(j - l.longValue()) == j - l.longValue()) {
            return fileInputStream;
        }
        throw new zzck(String.format("Virtualized slice archive corrupt, could not skip in file with key %s", l));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // com.google.android.play.core.internal.zzcm
    public final long zza() {
        Map.Entry lastEntry = this.zzc.lastEntry();
        return ((File) lastEntry.getValue()).length() + ((Long) lastEntry.getKey()).longValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.play.core.internal.zzcm
    public final InputStream zzb(long j, long j2) throws IOException {
        if (j >= 0 && j2 >= 0) {
            long j3 = j + j2;
            if (j3 <= zza()) {
                Long l = (Long) this.zzc.floorKey(Long.valueOf(j));
                Long l2 = (Long) this.zzc.floorKey(Long.valueOf(j3));
                if (l.equals(l2)) {
                    return new zzbj(zzd(j, l), j2);
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add(zzd(j, l));
                Collection values = this.zzc.subMap(l, false, l2, false).values();
                if (!values.isEmpty()) {
                    arrayList.add(new zzdr(Collections.enumeration(values)));
                }
                arrayList.add(new zzbj(new FileInputStream((File) this.zzc.get(l2)), j2 - (l2.longValue() - j)));
                return new SequenceInputStream(Collections.enumeration(arrayList));
            }
            throw new zzck(String.format("Trying to access archive out of bounds. Archive ends at: %s. Tried accessing: %s", Long.valueOf(zza()), Long.valueOf(j3)));
        }
        throw new zzck(String.format("Invalid input parameters %s, %s", Long.valueOf(j), Long.valueOf(j2)));
    }
}
