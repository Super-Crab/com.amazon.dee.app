package com.amazon.identity.auth.device;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class ax {
    private static final String TAG = "com.amazon.identity.auth.device.ax";

    private ax() {
    }

    public static Bundle a(Context context, String str, String str2) {
        new AmazonAccountManager(context).a(str, "com.amazon.dcp.sso.property.devicename", str2);
        return b(context, str, str2);
    }

    public static Bundle b(Context context, String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("com.amazon.dcp.sso.property.devicename", str2);
        a(context, str, bundle);
        return bundle;
    }

    public static Bundle c(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str2)) {
            io.dm(TAG);
        }
        io.a("Device Email has been set to ", str2);
        Bundle bundle = new Bundle();
        bundle.putString("com.amazon.dcp.sso.property.deviceemail", str2);
        a(context, str, bundle);
        return bundle;
    }

    public static String j(Context context) {
        return aa.f(context).A();
    }

    public static void a(Context context, String str, Bundle bundle) {
        io.dm(TAG);
        if (str != null && !str.equals(j(context))) {
            io.i(TAG, "Not notifying that the given accounts property has changed because it is not currently visibile");
        } else {
            b(context, str, bundle);
        }
    }

    public static void b(Context context, String str, Bundle bundle) {
        Intent dy = iz.dy("com.amazon.dcp.sso.action.AmazonAccountPropertyService.property.changed");
        for (String str2 : bundle.keySet()) {
            String str3 = TAG;
            String.format("Key = %s, Value = %s", str2, bundle.getString(str2));
            io.dm(str3);
            dy.putExtra(str2, bundle.getString(str2));
        }
        Account o = hu.o(context, str);
        if (o != null) {
            dy.putExtra("account.property.changed", o);
        }
        dy.putExtra(CustomerAttributeStore.KEY_DIRECTED_ID, str);
        aa.f(context).b(str, dy, "com.amazon.dcp.sso.permission.AmazonAccountPropertyService.property.changed");
        io.dm(TAG);
    }
}
