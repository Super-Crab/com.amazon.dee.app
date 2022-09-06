package org.aspectj.runtime.reflect;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.LockSignature;
/* loaded from: classes4.dex */
class LockSignatureImpl extends SignatureImpl implements LockSignature {
    private Class parameterType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LockSignatureImpl(Class cls) {
        super(8, JoinPoint.SYNCHRONIZATION_LOCK, cls);
        this.parameterType = cls;
    }

    @Override // org.aspectj.runtime.reflect.SignatureImpl
    protected String createToString(StringMaker stringMaker) {
        if (this.parameterType == null) {
            this.parameterType = extractType(3);
        }
        return GeneratedOutlineSupport1.outline83(GeneratedOutlineSupport1.outline103("lock("), stringMaker.makeTypeName(this.parameterType), ")");
    }

    public Class getParameterType() {
        if (this.parameterType == null) {
            this.parameterType = extractType(3);
        }
        return this.parameterType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LockSignatureImpl(String str) {
        super(str);
    }
}
