package com.amazon.identity.auth.device;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.UserManager;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ds {
    private final Context mContext;

    public ds(Context context) {
        this.mContext = context;
    }

    public boolean bu(String str) {
        return mz.E(this.mContext, str);
    }

    @TargetApi(24)
    public boolean dA() {
        if (dz()) {
            UserManager userManager = (UserManager) this.mContext.getSystemService("user");
            if (userManager == null) {
                io.e("PlatformWrapper", "Cannot get UserManager while the OS supports direct boot!");
                return false;
            }
            boolean z = !userManager.isUserUnlocked();
            "User is locked in direct boot mode: ".concat(String.valueOf(z));
            io.dm("PlatformWrapper");
            return z;
        }
        return false;
    }

    public boolean dh() {
        return mz.bf(this.mContext);
    }

    public boolean di() {
        return hx.ak(this.mContext);
    }

    public boolean dj() {
        return hx.aj(this.mContext);
    }

    public boolean dk() {
        return mz.aV(this.mContext);
    }

    public boolean dl() {
        return mz.aW(this.mContext);
    }

    public boolean dm() {
        return mz.aX(this.mContext);
    }

    public boolean dn() {
        return mz.aW(this.mContext);
    }

    /* renamed from: do  reason: not valid java name */
    public boolean m4061do() {
        return mz.aY(this.mContext);
    }

    public boolean dp() {
        return mz.bc(this.mContext);
    }

    public boolean dq() {
        return mz.be(this.mContext);
    }

    public boolean dr() {
        return mz.bd(this.mContext);
    }

    public boolean ds() {
        return mz.bb(this.mContext);
    }

    public boolean dt() {
        return it.c("com.amazon.fv", this.mContext) && mz.ap(this.mContext);
    }

    public boolean du() {
        return it.c("com.amazon.canary", this.mContext) && mz.ap(this.mContext);
    }

    public boolean dv() {
        return it.c("com.amazon.fv", this.mContext) && hx.aq(this.mContext);
    }

    public boolean dw() {
        return it.c("com.amazon.canary", this.mContext) && hx.aq(this.mContext);
    }

    public boolean dx() {
        return !hx.an(this.mContext);
    }

    public boolean dy() {
        return "com.amazon.imp".equals(this.mContext.getApplicationContext().getPackageName());
    }

    public boolean dz() {
        int i = Build.VERSION.SDK_INT;
        return mz.aZ(this.mContext);
    }
}
