package com.amazon.identity.auth.device;

import com.amazon.identity.auth.device.utils.ReflectionHelper;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class em {
    private static final String TAG = "com.amazon.identity.auth.device.em";
    private final ds bb;
    private final ReflectionHelper lG = new ReflectionHelper();
    private final Object lH;
    private final ed o;

    public em(ed edVar) {
        this.o = edVar;
        this.lH = this.o.getSystemService("user");
        this.bb = (ds) this.o.getSystemService("sso_platform");
    }

    private void ei() {
        if (S()) {
            return;
        }
        io.e(TAG, "checkIsUserManagerSupportedOnThisPlatform failed. IMP is going to crash. This issue is tracked in SSO-150 and FWK-10172");
        throw new IllegalStateException("This platform does not support UserManager");
    }

    public boolean S() {
        return this.bb.dq() && hx.aj(this.o);
    }

    public da eg() {
        ei();
        return l(da.cy());
    }

    public da eh() {
        ei();
        return l(da.cB());
    }

    public da l(int i) {
        try {
            return da.c(this.lG.a("getUserInfo", this.lH, new Class[]{Integer.TYPE}, Integer.valueOf(i)));
        } catch (ReflectionHelper.CannotCallMethodException e) {
            io.e(TAG, "Cannot get user info for my user id", e);
            return null;
        }
    }
}
