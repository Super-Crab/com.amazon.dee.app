package com.amazon.whisperjoin.common.sharedtypes.exceptions;
/* loaded from: classes13.dex */
public class RadioNotEnabledException extends Exception {
    private final String mRadio;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public RadioNotEnabledException(java.lang.String r3, java.lang.Throwable r4) {
        /*
            r2 = this;
            java.lang.String r0 = "Radio not enabled "
            java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r0)
            if (r3 != 0) goto Lb
            java.lang.String r1 = ""
            goto Lc
        Lb:
            r1 = r3
        Lc:
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r2.<init>(r0, r4)
            if (r3 == 0) goto L1b
            r2.mRadio = r3
            return
        L1b:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "radio must not be null"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.common.sharedtypes.exceptions.RadioNotEnabledException.<init>(java.lang.String, java.lang.Throwable):void");
    }

    public String getRadio() {
        return this.mRadio;
    }

    public RadioNotEnabledException(String str) {
        this(str, null);
    }
}
