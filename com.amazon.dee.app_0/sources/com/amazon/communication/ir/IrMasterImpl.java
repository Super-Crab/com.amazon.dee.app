package com.amazon.communication.ir;
/* loaded from: classes12.dex */
public class IrMasterImpl implements IrMaster {
    private final String mDomain;
    private final String mRealm;

    public IrMasterImpl(String str, String str2) {
        if (str != null && str.trim().length() != 0) {
            if (str2 != null && str2.trim().length() != 0) {
                this.mDomain = str;
                this.mRealm = str2;
                return;
            }
            throw new IllegalArgumentException("realm is NULL or empty");
        }
        throw new IllegalArgumentException("domain is NULL or empty");
    }

    @Override // com.amazon.communication.ir.IrMaster
    public String getDomain() {
        return this.mDomain;
    }

    @Override // com.amazon.communication.ir.IrMaster
    public String getRealm() {
        return this.mRealm;
    }
}
