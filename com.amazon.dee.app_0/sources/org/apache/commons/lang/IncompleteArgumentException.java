package org.apache.commons.lang;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
/* loaded from: classes4.dex */
public class IncompleteArgumentException extends IllegalArgumentException {
    private static final long serialVersionUID = 4954193403612068178L;

    public IncompleteArgumentException(String str) {
        super(GeneratedOutlineSupport1.outline71(str, " is incomplete."));
    }

    private static final String safeArrayToString(Object[] objArr) {
        if (objArr == null) {
            return null;
        }
        return Arrays.asList(objArr).toString();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public IncompleteArgumentException(java.lang.String r2, java.lang.String[] r3) {
        /*
            r1 = this;
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            r0.append(r2)
            java.lang.String r2 = " is missing the following items: "
            r0.append(r2)
            java.lang.String r2 = safeArrayToString(r3)
            r0.append(r2)
            java.lang.String r2 = r0.toString()
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.IncompleteArgumentException.<init>(java.lang.String, java.lang.String[]):void");
    }
}
