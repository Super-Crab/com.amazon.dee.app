package org.aspectj.runtime.reflect;

import org.aspectj.lang.reflect.MemberSignature;
/* loaded from: classes4.dex */
abstract class MemberSignatureImpl extends SignatureImpl implements MemberSignature {
    /* JADX INFO: Access modifiers changed from: package-private */
    public MemberSignatureImpl(int i, String str, Class cls) {
        super(i, str, cls);
    }

    public MemberSignatureImpl(String str) {
        super(str);
    }
}
