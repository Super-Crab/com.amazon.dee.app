package com.google.android.gms.internal.vision;

import com.google.android.gms.internal.vision.zzcr;
import com.google.android.gms.internal.vision.zzcr.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes2.dex */
public abstract class zzcr<MessageType extends zzcr<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzbf<MessageType, BuilderType> {
    private static Map<Object, zzcr<?, ?>> zzkt = new ConcurrentHashMap();
    protected zzfg zzkr = zzfg.zzdu();
    private int zzks = -1;

    /* loaded from: classes2.dex */
    public static abstract class zza<MessageType extends zzcr<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzbg<MessageType, BuilderType> {
        private final MessageType zzku;
        protected MessageType zzkv;
        private boolean zzkw = false;

        protected zza(MessageType messagetype) {
            this.zzku = messagetype;
            this.zzkv = (MessageType) messagetype.zza(zzd.zzlb, null, null);
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzek.zzdc().zzq(messagetype).zzc(messagetype, messagetype2);
        }

        @Override // com.google.android.gms.internal.vision.zzbg
        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zza zzaVar = (zza) this.zzku.zza(zzd.zzlc, null, null);
            if (!this.zzkw) {
                MessageType messagetype = this.zzkv;
                zzek.zzdc().zzq(messagetype).zzd(messagetype);
                this.zzkw = true;
            }
            zzaVar.zza((zza) this.zzkv);
            return zzaVar;
        }

        @Override // com.google.android.gms.internal.vision.zzdz
        public final boolean isInitialized() {
            return zzcr.zza(this.zzkv, false);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.vision.zzbg
        protected final /* synthetic */ zzbg zza(zzbf zzbfVar) {
            return zza((zza<MessageType, BuilderType>) ((zzcr) zzbfVar));
        }

        public final BuilderType zza(MessageType messagetype) {
            zzbx();
            zza(this.zzkv, messagetype);
            return this;
        }

        @Override // com.google.android.gms.internal.vision.zzbg
        public final /* synthetic */ zzbg zzam() {
            return (zza) clone();
        }

        @Override // com.google.android.gms.internal.vision.zzdz
        public final /* synthetic */ zzdx zzbw() {
            return this.zzku;
        }

        protected final void zzbx() {
            if (this.zzkw) {
                MessageType messagetype = (MessageType) this.zzkv.zza(zzd.zzlb, null, null);
                zza(messagetype, this.zzkv);
                this.zzkv = messagetype;
                this.zzkw = false;
            }
        }

        public final MessageType zzby() {
            boolean z = true;
            if (!this.zzkw) {
                MessageType messagetype = this.zzkv;
                zzek.zzdc().zzq(messagetype).zzd(messagetype);
                this.zzkw = true;
            }
            MessageType messagetype2 = this.zzkv;
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) messagetype2.zza(zzd.zzky, null, null)).byteValue();
            if (byteValue != 1) {
                if (byteValue == 0) {
                    z = false;
                } else {
                    z = zzek.zzdc().zzq(messagetype2).zzp(messagetype2);
                    if (booleanValue) {
                        messagetype2.zza(zzd.zzkz, z ? messagetype2 : null, null);
                    }
                }
            }
            if (z) {
                return messagetype2;
            }
            throw new zzfe(messagetype2);
        }

        @Override // com.google.android.gms.internal.vision.zzdy
        public final /* synthetic */ zzdx zzbz() {
            if (this.zzkw) {
                return this.zzkv;
            }
            MessageType messagetype = this.zzkv;
            zzek.zzdc().zzq(messagetype).zzd(messagetype);
            this.zzkw = true;
            return this.zzkv;
        }

        @Override // com.google.android.gms.internal.vision.zzdy
        public final /* synthetic */ zzdx zzca() {
            boolean z = true;
            if (!this.zzkw) {
                MessageType messagetype = this.zzkv;
                zzek.zzdc().zzq(messagetype).zzd(messagetype);
                this.zzkw = true;
            }
            MessageType messagetype2 = this.zzkv;
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) messagetype2.zza(zzd.zzky, null, null)).byteValue();
            if (byteValue != 1) {
                if (byteValue == 0) {
                    z = false;
                } else {
                    z = zzek.zzdc().zzq(messagetype2).zzp(messagetype2);
                    if (booleanValue) {
                        messagetype2.zza(zzd.zzkz, z ? messagetype2 : null, null);
                    }
                }
            }
            if (z) {
                return messagetype2;
            }
            throw new zzfe(messagetype2);
        }
    }

    /* loaded from: classes2.dex */
    public static class zzb<T extends zzcr<T, ?>> extends zzbh<T> {
        private T zzku;

        public zzb(T t) {
            this.zzku = t;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class zzc<MessageType extends zzc<MessageType, BuilderType>, BuilderType> extends zzcr<MessageType, BuilderType> implements zzdz {
        protected zzcj<Object> zzkx = zzcj.zzbk();
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier removed */
    /* loaded from: classes2.dex */
    public static final class zzd {
        public static final int zzky = 1;
        public static final int zzkz = 2;
        public static final int zzla = 3;
        public static final int zzlb = 4;
        public static final int zzlc = 5;
        public static final int zzld = 6;
        public static final int zzle = 7;
        private static final /* synthetic */ int[] zzlf = {zzky, zzkz, zzla, zzlb, zzlc, zzld, zzle};
        public static final int zzlg = 1;
        public static final int zzlh = 2;
        private static final /* synthetic */ int[] zzli = {zzlg, zzlh};
        public static final int zzlj = 1;
        public static final int zzlk = 2;
        private static final /* synthetic */ int[] zzll = {zzlj, zzlk};

        public static int[] values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0() {
            return (int[]) zzlf.clone();
        }
    }

    private static <T extends zzcr<T, ?>> T zza(T t, byte[] bArr) throws zzcx {
        T t2 = (T) t.zza(zzd.zzlb, null, null);
        try {
            zzek.zzdc().zzq(t2).zza(t2, bArr, 0, bArr.length, new zzbl());
            zzek.zzdc().zzq(t2).zzd(t2);
            if (t2.zzgi == 0) {
                return t2;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            if (!(e.getCause() instanceof zzcx)) {
                throw new zzcx(e.getMessage()).zzg(t2);
            }
            throw ((zzcx) e.getCause());
        } catch (IndexOutOfBoundsException unused) {
            throw zzcx.zzcb().zzg(t2);
        }
    }

    protected static Object zza(zzdx zzdxVar, String str, Object[] objArr) {
        return new zzem(zzdxVar, str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zza(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (!(cause instanceof Error)) {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
            throw ((Error) cause);
        }
    }

    protected static <T extends zzcr<?, ?>> void zza(Class<T> cls, T t) {
        zzkt.put(cls, t);
    }

    protected static final <T extends zzcr<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zzd.zzky, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue != 0) {
            return zzek.zzdc().zzq(t).zzp(t);
        }
        return false;
    }

    protected static <T extends zzcr<T, ?>> T zzb(T t, byte[] bArr) throws zzcx {
        T t2 = (T) zza(t, bArr);
        if (t2 != null) {
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) t2.zza(zzd.zzky, null, null)).byteValue();
            boolean z = true;
            if (byteValue != 1) {
                if (byteValue == 0) {
                    z = false;
                } else {
                    z = zzek.zzdc().zzq(t2).zzp(t2);
                    if (booleanValue) {
                        t2.zza(zzd.zzkz, z ? t2 : null, null);
                    }
                }
            }
            if (!z) {
                throw new zzcx(new zzfe(t2).getMessage()).zzg(t2);
            }
        }
        return t2;
    }

    protected static <E> zzcw<E> zzbt() {
        return zzel.zzdd();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends zzcr<?, ?>> T zzc(Class<T> cls) {
        T t = (T) zzkt.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (T) zzkt.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            String name = cls.getName();
            throw new IllegalStateException(name.length() != 0 ? "Unable to get default instance for: ".concat(name) : new String("Unable to get default instance for: "));
        }
        return t;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (((zzcr) zza(zzd.zzld, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return zzek.zzdc().zzq(this).equals(this, (zzcr) obj);
        }
        return false;
    }

    public int hashCode() {
        int i = this.zzgi;
        if (i != 0) {
            return i;
        }
        this.zzgi = zzek.zzdc().zzq(this).hashCode(this);
        return this.zzgi;
    }

    @Override // com.google.android.gms.internal.vision.zzdz
    public final boolean isInitialized() {
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) zza(zzd.zzky, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzp = zzek.zzdc().zzq(this).zzp(this);
        if (booleanValue) {
            zza(zzd.zzkz, zzp ? this : null, (Object) null);
        }
        return zzp;
    }

    public String toString() {
        return zzea.zza(this, super.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object zza(int i, Object obj, Object obj2);

    @Override // com.google.android.gms.internal.vision.zzbf
    final int zzal() {
        return this.zzks;
    }

    @Override // com.google.android.gms.internal.vision.zzdx
    public final void zzb(zzca zzcaVar) throws IOException {
        zzek.zzdc().zze(getClass()).zza(this, zzcc.zza(zzcaVar));
    }

    @Override // com.google.android.gms.internal.vision.zzdx
    public final int zzbl() {
        if (this.zzks == -1) {
            this.zzks = zzek.zzdc().zzq(this).zzn(this);
        }
        return this.zzks;
    }

    @Override // com.google.android.gms.internal.vision.zzdx
    public final /* synthetic */ zzdy zzbu() {
        zza zzaVar = (zza) zza(zzd.zzlc, (Object) null, (Object) null);
        zzaVar.zza((zza) this);
        return zzaVar;
    }

    @Override // com.google.android.gms.internal.vision.zzdx
    public final /* synthetic */ zzdy zzbv() {
        return (zza) zza(zzd.zzlc, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.vision.zzdz
    public final /* synthetic */ zzdx zzbw() {
        return (zzcr) zza(zzd.zzld, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.vision.zzbf
    final void zzh(int i) {
        this.zzks = i;
    }
}
