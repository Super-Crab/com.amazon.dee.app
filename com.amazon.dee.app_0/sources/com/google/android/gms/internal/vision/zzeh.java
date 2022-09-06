package com.google.android.gms.internal.vision;
/* loaded from: classes2.dex */
final class zzeh {
    private static final zzef zznv = zzdb();
    private static final zzef zznw = new zzeg();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzef zzcz() {
        return zznv;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzef zzda() {
        return zznw;
    }

    private static zzef zzdb() {
        try {
            return (zzef) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
