package com.google.android.gms.internal.vision;
/* loaded from: classes2.dex */
final class zzci {
    private static final zzcg<?> zzhs = new zzch();
    private static final zzcg<?> zzht = zzbh();

    private static zzcg<?> zzbh() {
        try {
            return (zzcg) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzcg<?> zzbi() {
        return zzhs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzcg<?> zzbj() {
        zzcg<?> zzcgVar = zzht;
        if (zzcgVar != null) {
            return zzcgVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
