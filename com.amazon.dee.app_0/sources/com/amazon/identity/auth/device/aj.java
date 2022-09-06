package com.amazon.identity.auth.device;

import android.content.ComponentName;
import android.text.TextUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class aj {
    public final List<String> cV;
    public final boolean cW;
    public final String cX;
    public final boolean cY;
    public final String className;
    public final String packageName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public aj(String str, String str2, List<String> list) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && list != null && !list.isEmpty()) {
            this.packageName = str;
            this.className = str2;
            this.cW = false;
            this.cX = null;
            this.cV = Collections.unmodifiableList(new ArrayList(list));
            this.cY = false;
            return;
        }
        throw new IllegalArgumentException("One or more parameters are null or empty");
    }

    public static String ad(String str) {
        if (str == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline72(str, ".tokens.adp_token");
    }

    public static String ae(String str) {
        if (str == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline72(str, ".tokens.private_key");
    }

    public static String af(String str) {
        if (str == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline72(str, ".tokens.device_type");
    }

    public static String ag(String str) {
        if (str == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline72(str, ".tokens.dsn");
    }

    public static String ah(String str) {
        if (str == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline72(str, ".tokens.email");
    }

    public static String ai(String str) {
        if (str == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline72(str, ".tokens.storeAuthCookie");
    }

    public static String aj(String str) {
        if (str == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline72(str, ".tokens.xmain");
    }

    public static String ak(String str) {
        if (str == null) {
            return null;
        }
        return GeneratedOutlineSupport1.outline72(str, ".tokens.account_pool");
    }

    public ComponentName getComponentName() {
        String str = this.className;
        if (str == null) {
            return null;
        }
        return new ComponentName(this.packageName, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public aj(String str, String str2, String str3, boolean z) {
        if (!TextUtils.isEmpty(str) && !jg.dD(str2) && !TextUtils.isEmpty(str3)) {
            this.packageName = str;
            this.className = str2;
            this.cW = true;
            this.cX = str3;
            this.cV = Collections.unmodifiableList(Arrays.asList(ad(this.packageName), ae(this.packageName), af(this.packageName), ag(this.packageName), ah(this.packageName), ai(this.packageName), aj(this.packageName), ak(this.packageName)));
            this.cY = z;
            return;
        }
        throw new IllegalArgumentException("One or more parameters are null or empty");
    }
}
