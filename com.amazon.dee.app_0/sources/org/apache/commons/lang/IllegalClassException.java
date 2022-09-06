package org.apache.commons.lang;
/* loaded from: classes4.dex */
public class IllegalClassException extends IllegalArgumentException {
    private static final long serialVersionUID = 8063272569377254819L;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public IllegalClassException(java.lang.Class r2, java.lang.Object r3) {
        /*
            r1 = this;
            java.lang.String r0 = "Expected: "
            java.lang.StringBuffer r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline103(r0)
            java.lang.String r2 = safeGetClassName(r2)
            r0.append(r2)
            java.lang.String r2 = ", actual: "
            r0.append(r2)
            if (r3 != 0) goto L17
            java.lang.String r2 = "null"
            goto L1f
        L17:
            java.lang.Class r2 = r3.getClass()
            java.lang.String r2 = r2.getName()
        L1f:
            r0.append(r2)
            java.lang.String r2 = r0.toString()
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.IllegalClassException.<init>(java.lang.Class, java.lang.Object):void");
    }

    private static final String safeGetClassName(Class cls) {
        if (cls == null) {
            return null;
        }
        return cls.getName();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public IllegalClassException(java.lang.Class r2, java.lang.Class r3) {
        /*
            r1 = this;
            java.lang.String r0 = "Expected: "
            java.lang.StringBuffer r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline103(r0)
            java.lang.String r2 = safeGetClassName(r2)
            r0.append(r2)
            java.lang.String r2 = ", actual: "
            r0.append(r2)
            java.lang.String r2 = safeGetClassName(r3)
            r0.append(r2)
            java.lang.String r2 = r0.toString()
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.IllegalClassException.<init>(java.lang.Class, java.lang.Class):void");
    }

    public IllegalClassException(String str) {
        super(str);
    }
}
