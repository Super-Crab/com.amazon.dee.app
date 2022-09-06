package com.google.android.gms.internal.vision;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.internal.vision.zzbf;
import com.google.android.gms.internal.vision.zzbg;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes2.dex */
public abstract class zzbf<MessageType extends zzbf<MessageType, BuilderType>, BuilderType extends zzbg<MessageType, BuilderType>> implements zzdx {
    private static boolean zzgj = false;
    protected int zzgi = 0;

    protected static <T> void zza(Iterable<T> iterable, List<? super T> list) {
        zzct.checkNotNull(iterable);
        if (iterable instanceof zzdg) {
            List<?> zzck = ((zzdg) iterable).zzck();
            zzdg zzdgVar = (zzdg) list;
            int size = list.size();
            for (Object obj : zzck) {
                if (obj == null) {
                    StringBuilder sb = new StringBuilder(37);
                    sb.append("Element at index ");
                    sb.append(zzdgVar.size() - size);
                    sb.append(" is null.");
                    String sb2 = sb.toString();
                    for (int size2 = zzdgVar.size() - 1; size2 >= size; size2--) {
                        zzdgVar.remove(size2);
                    }
                    throw new NullPointerException(sb2);
                } else if (obj instanceof zzbo) {
                    zzdgVar.zzc((zzbo) obj);
                } else {
                    zzdgVar.add((String) obj);
                }
            }
        } else if (iterable instanceof zzej) {
            list.addAll((Collection) iterable);
        } else {
            if ((list instanceof ArrayList) && (iterable instanceof Collection)) {
                ((ArrayList) list).ensureCapacity(((Collection) iterable).size() + list.size());
            }
            int size3 = list.size();
            for (T t : iterable) {
                if (t == null) {
                    StringBuilder sb3 = new StringBuilder(37);
                    sb3.append("Element at index ");
                    sb3.append(list.size() - size3);
                    sb3.append(" is null.");
                    String sb4 = sb3.toString();
                    for (int size4 = list.size() - 1; size4 >= size3; size4--) {
                        list.remove(size4);
                    }
                    throw new NullPointerException(sb4);
                }
                list.add(t);
            }
        }
    }

    public final byte[] toByteArray() {
        try {
            byte[] bArr = new byte[zzbl()];
            zzca zzd = zzca.zzd(bArr);
            zzb(zzd);
            zzd.zzba();
            return bArr;
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder outline106 = GeneratedOutlineSupport1.outline106("byte array".length() + name.length() + 62, "Serializing ", name, " to a ", "byte array");
            outline106.append(" threw an IOException (should never happen).");
            throw new RuntimeException(outline106.toString(), e);
        }
    }

    @Override // com.google.android.gms.internal.vision.zzdx
    public final zzbo zzak() {
        try {
            zzbt zzm = zzbo.zzm(zzbl());
            zzb(zzm.zzax());
            return zzm.zzaw();
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder outline106 = GeneratedOutlineSupport1.outline106("ByteString".length() + name.length() + 62, "Serializing ", name, " to a ", "ByteString");
            outline106.append(" threw an IOException (should never happen).");
            throw new RuntimeException(outline106.toString(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int zzal() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void zzh(int i) {
        throw new UnsupportedOperationException();
    }
}
