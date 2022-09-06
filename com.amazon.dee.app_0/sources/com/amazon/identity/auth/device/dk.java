package com.amazon.identity.auth.device;

import android.content.Context;
import android.util.Base64;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class dk extends fw {
    private static final String TAG = "com.amazon.identity.auth.device.dk";
    private static byte[] jD;
    private final Context mContext;

    public dk(Context context) {
        this.mContext = ed.M(context.getApplicationContext());
    }

    @Override // com.amazon.identity.auth.device.fw
    public synchronized byte[] cq() {
        byte[] decode;
        if (jD == null) {
            String cP = di.A(this.mContext).cP();
            if (cP == null) {
                io.e(TAG, "Could not generate a MAP only encryption key. Aborting.");
                decode = null;
            } else {
                decode = Base64.decode(cP, 0);
            }
            jD = decode;
        }
        return jD;
    }
}
