package org.aspectj.runtime.reflect;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.aspectj.lang.reflect.CatchClauseSignature;
/* loaded from: classes4.dex */
class CatchClauseSignatureImpl extends SignatureImpl implements CatchClauseSignature {
    String parameterName;
    Class parameterType;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CatchClauseSignatureImpl(Class cls, Class cls2, String str) {
        super(0, "catch", cls);
        this.parameterType = cls2;
        this.parameterName = str;
    }

    @Override // org.aspectj.runtime.reflect.SignatureImpl
    protected String createToString(StringMaker stringMaker) {
        return GeneratedOutlineSupport1.outline83(GeneratedOutlineSupport1.outline103("catch("), stringMaker.makeTypeName(getParameterType()), ")");
    }

    @Override // org.aspectj.lang.reflect.CatchClauseSignature
    public String getParameterName() {
        if (this.parameterName == null) {
            this.parameterName = extractString(4);
        }
        return this.parameterName;
    }

    @Override // org.aspectj.lang.reflect.CatchClauseSignature
    public Class getParameterType() {
        if (this.parameterType == null) {
            this.parameterType = extractType(3);
        }
        return this.parameterType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CatchClauseSignatureImpl(String str) {
        super(str);
    }
}
