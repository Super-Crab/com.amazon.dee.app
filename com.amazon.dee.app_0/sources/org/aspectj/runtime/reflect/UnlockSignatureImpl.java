package org.aspectj.runtime.reflect;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.UnlockSignature;
/* loaded from: classes4.dex */
class UnlockSignatureImpl extends SignatureImpl implements UnlockSignature {
    private Class parameterType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UnlockSignatureImpl(Class cls) {
        super(8, JoinPoint.SYNCHRONIZATION_UNLOCK, cls);
        this.parameterType = cls;
    }

    @Override // org.aspectj.runtime.reflect.SignatureImpl
    protected String createToString(StringMaker stringMaker) {
        if (this.parameterType == null) {
            this.parameterType = extractType(3);
        }
        return GeneratedOutlineSupport1.outline83(GeneratedOutlineSupport1.outline103("unlock("), stringMaker.makeTypeName(this.parameterType), ")");
    }

    public Class getParameterType() {
        if (this.parameterType == null) {
            this.parameterType = extractType(3);
        }
        return this.parameterType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UnlockSignatureImpl(String str) {
        super(str);
    }
}
