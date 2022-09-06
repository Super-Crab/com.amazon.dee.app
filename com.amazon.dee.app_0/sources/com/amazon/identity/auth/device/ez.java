package com.amazon.identity.auth.device;

import android.content.Context;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ez implements ex {
    private static final String TAG = "ez";

    @Override // com.amazon.identity.auth.device.ex
    public boolean a(Context context, String str, boolean z) {
        boolean z2 = new ek(context).bD(str) == 0;
        if (z) {
            if (z2) {
                String str2 = TAG;
                StringBuilder sb = new StringBuilder("MAP trust ");
                sb.append(str);
                sb.append(", since the signature check passed");
                io.dm(str2);
            } else {
                io.e(TAG, "MAP doesn't trust the package. Usually it is because the app is signed with a different cert comparing to current package");
            }
        }
        return z2;
    }

    @Override // com.amazon.identity.auth.device.ex
    public String getDescription() {
        return "signature based package trust logic";
    }
}
