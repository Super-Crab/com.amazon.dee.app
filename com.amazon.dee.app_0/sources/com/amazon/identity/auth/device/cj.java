package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.cd;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class cj {
    private static volatile cj ih;
    private ea ii;
    private ci ij;

    private cj(Context context) {
        boolean b = mz.b(context, ce.hW);
        if (b && mz.aW(context)) {
            this.ii = new cx(context);
            this.ij = new ck(new ce(ed.M(context)), context);
        } else if (mz.bh(context)) {
            cc a = cc.a(context, b);
            this.ii = a.bJ();
            this.ij = a;
        } else if (mz.bi(context)) {
            this.ii = new cd.a(context);
            this.ij = cg.u(context);
        } else {
            this.ii = new cx(context);
            this.ij = cg.u(context);
        }
    }

    public static void generateNewInstance(Context context) {
        ih = new cj(context);
    }

    static cj v(Context context) {
        if (ih == null) {
            synchronized (cj.class) {
                if (ih == null) {
                    generateNewInstance(context);
                }
            }
        }
        return ih;
    }

    public static ci w(Context context) {
        return v(context).ij;
    }

    public static ea x(Context context) {
        return v(context).ii;
    }
}
