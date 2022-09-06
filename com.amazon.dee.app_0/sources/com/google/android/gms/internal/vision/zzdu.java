package com.google.android.gms.internal.vision;
/* loaded from: classes2.dex */
final class zzdu {
    private static final zzds zzna = zzcu();
    private static final zzds zznb = new zzdt();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzds zzcs() {
        return zzna;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzds zzct() {
        return zznb;
    }

    private static zzds zzcu() {
        try {
            return (zzds) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
