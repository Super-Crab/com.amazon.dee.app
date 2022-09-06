package org.aspectj.runtime.reflect;

import java.lang.reflect.Method;
import org.aspectj.lang.reflect.AdviceSignature;
/* loaded from: classes4.dex */
class AdviceSignatureImpl extends CodeSignatureImpl implements AdviceSignature {
    private Method adviceMethod;
    Class returnType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdviceSignatureImpl(int i, String str, Class cls, Class[] clsArr, String[] strArr, Class[] clsArr2, Class cls2) {
        super(i, str, cls, clsArr, strArr, clsArr2);
        this.adviceMethod = null;
        this.returnType = cls2;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String toAdviceName(java.lang.String r4) {
        /*
            r3 = this;
            r0 = 36
            int r0 = r4.indexOf(r0)
            r1 = -1
            if (r0 != r1) goto La
            return r4
        La:
            java.util.StringTokenizer r0 = new java.util.StringTokenizer
            java.lang.String r1 = "$"
            r0.<init>(r4, r1)
        L11:
            boolean r1 = r0.hasMoreTokens()
            if (r1 == 0) goto L34
            java.lang.String r1 = r0.nextToken()
            java.lang.String r2 = "before"
            boolean r2 = r1.startsWith(r2)
            if (r2 != 0) goto L33
            java.lang.String r2 = "after"
            boolean r2 = r1.startsWith(r2)
            if (r2 != 0) goto L33
            java.lang.String r2 = "around"
            boolean r2 = r1.startsWith(r2)
            if (r2 == 0) goto L11
        L33:
            return r1
        L34:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.aspectj.runtime.reflect.AdviceSignatureImpl.toAdviceName(java.lang.String):java.lang.String");
    }

    @Override // org.aspectj.runtime.reflect.SignatureImpl
    protected String createToString(StringMaker stringMaker) {
        StringBuffer stringBuffer = new StringBuffer();
        if (stringMaker.includeArgs) {
            stringBuffer.append(stringMaker.makeTypeName(getReturnType()));
        }
        if (stringMaker.includeArgs) {
            stringBuffer.append(" ");
        }
        stringBuffer.append(stringMaker.makePrimaryTypeName(getDeclaringType(), getDeclaringTypeName()));
        stringBuffer.append(".");
        stringBuffer.append(toAdviceName(getName()));
        stringMaker.addSignature(stringBuffer, getParameterTypes());
        stringMaker.addThrows(stringBuffer, getExceptionTypes());
        return stringBuffer.toString();
    }

    @Override // org.aspectj.lang.reflect.AdviceSignature
    public Method getAdvice() {
        if (this.adviceMethod == null) {
            try {
                this.adviceMethod = getDeclaringType().getDeclaredMethod(getName(), getParameterTypes());
            } catch (Exception unused) {
            }
        }
        return this.adviceMethod;
    }

    @Override // org.aspectj.lang.reflect.AdviceSignature
    public Class getReturnType() {
        if (this.returnType == null) {
            this.returnType = extractType(6);
        }
        return this.returnType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AdviceSignatureImpl(String str) {
        super(str);
        this.adviceMethod = null;
    }
}
