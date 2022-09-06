package com.amazon.identity.auth.device;

import android.text.TextUtils;
import com.amazon.identity.auth.device.features.Feature;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class q {
    public static boolean a(String str, co coVar) {
        return !TextUtils.isEmpty(str) && coVar.a(Feature.OverrideDeviceAttributes);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void a(fz fzVar, String str) {
        synchronized (q.class) {
            Set<String> at = jf.at(fzVar.ca("overriding_dsn_child_device_types_key"), ";");
            at.add(str);
            fzVar.u("overriding_dsn_child_device_types_key", jf.a(";", at));
        }
    }

    public static synchronized Collection<String> a(String str, gg ggVar) {
        Collection<String> hashSet;
        synchronized (q.class) {
            String z = ggVar.z(str, "overriding_dsn_child_device_types_key");
            if (!TextUtils.isEmpty(z)) {
                hashSet = jf.at(z, ";");
            } else {
                hashSet = new HashSet<>();
            }
        }
        return hashSet;
    }
}
