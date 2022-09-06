package org.objenesis.instantiator;
/* loaded from: classes5.dex */
public class SerializationInstantiatorHelper {
    /* JADX WARN: Code restructure failed: missing block: B:0:?, code lost:
        r1 = r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <T> java.lang.Class<? super T> getNonSerializableSuperClass(java.lang.Class<T> r1) {
        /*
        L0:
            java.lang.Class<java.io.Serializable> r0 = java.io.Serializable.class
            boolean r0 = r0.isAssignableFrom(r1)
            if (r0 == 0) goto L17
            java.lang.Class r1 = r1.getSuperclass()
            if (r1 == 0) goto Lf
            goto L0
        Lf:
            java.lang.Error r1 = new java.lang.Error
            java.lang.String r0 = "Bad class hierarchy: No non-serializable parents"
            r1.<init>(r0)
            throw r1
        L17:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.objenesis.instantiator.SerializationInstantiatorHelper.getNonSerializableSuperClass(java.lang.Class):java.lang.Class");
    }
}
