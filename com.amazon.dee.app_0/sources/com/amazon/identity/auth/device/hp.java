package com.amazon.identity.auth.device;

import android.os.Bundle;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class hp {
    @Deprecated
    public static void a(bl blVar, int i, String str) {
        blVar.onError(a(i, str, (Bundle) null));
    }

    @Deprecated
    public static void b(bl blVar, String str) {
        io.e(str, "Cannot sign a null corpus.");
        a(blVar, 3, "Cannot sign a null corpus.");
    }

    @Deprecated
    public static void a(bl blVar, String str, Bundle bundle) {
        blVar.onError(a(5, str, bundle));
    }

    @Deprecated
    public static Bundle a(int i, String str, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putInt("error_code_key", i);
        bundle2.putString("error_message_key", str);
        if (bundle != null) {
            bundle2.putAll(bundle);
        }
        return bundle2;
    }
}
