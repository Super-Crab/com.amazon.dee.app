package com.amazon.identity.auth.device;

import android.content.Context;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class cz {
    private final Context mContext;

    public cz(Context context) {
        this.mContext = context;
    }

    public dc a(br brVar, ej ejVar) {
        return new dc(this.mContext, brVar, ejVar);
    }

    public kp b(br brVar, ej ejVar) {
        return new jz(this.mContext, new lk(brVar), ejVar);
    }

    public dc c(String str, ej ejVar) {
        return new dc(this.mContext, str, ejVar);
    }

    public kp d(String str, ej ejVar) {
        Context context = this.mContext;
        return new jz(context, new lk(bs.d(context, str)), ejVar);
    }
}
