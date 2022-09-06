package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.CustomerAttributeStore;
import com.amazon.identity.auth.device.api.MAPError;
import com.amazon.identity.auth.device.metrics.SSOMetrics;
import com.amazon.identity.kcpsdk.common.WebResponseParser;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ae {
    private static final String TAG = "com.amazon.identity.auth.device.ae";
    private Context mContext;
    private final AmazonAccountManager s;

    public ae(Context context) {
        if (context != null) {
            this.mContext = ed.M(context);
            this.s = (AmazonAccountManager) this.mContext.getSystemService("dcp_amazon_account_man");
            return;
        }
        throw new IllegalArgumentException("One or more arguments are null");
    }

    private String Z(String str) {
        return ie.s(this.mContext, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Callback callback, MAPError mAPError, String str, int i, String str2) {
        io.e(TAG, "Rename Device Error: ".concat(String.valueOf(str2)));
        SSOMetrics.m(i);
        callback.onError(d(mAPError, str, i, str2));
    }

    private boolean k(Bundle bundle) {
        return bundle != null && !TextUtils.isEmpty(bundle.getString(CustomerAttributeStore.KEY_PACKAGE_NAME_FOR_RENAMING_CHILD_DEVICE_3P_DEVICES));
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0083 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void d(final java.lang.String r13, java.lang.String r14, android.os.Bundle r15, final com.amazon.identity.auth.device.api.Callback r16, com.amazon.identity.auth.device.ej r17) {
        /*
            Method dump skipped, instructions count: 329
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.ae.d(java.lang.String, java.lang.String, android.os.Bundle, com.amazon.identity.auth.device.api.Callback, com.amazon.identity.auth.device.ej):void");
    }

    protected void a(String str, md mdVar, WebResponseParser webResponseParser, ko koVar, Context context, String str2, ej ejVar) {
        new dc(context, str, str2, ejVar).b(mdVar, webResponseParser, koVar).cD();
    }

    static /* synthetic */ String a(ae aeVar, String str) {
        String str2 = "com.amazon.dcp.sso.property.devicename";
        if (str != null) {
            str2 = gv.i(aeVar.mContext, str, str2);
        }
        String str3 = TAG;
        String.format("Store the new device name with key: %s", str2);
        io.dm(str3);
        return str2;
    }

    private Bundle d(MAPError mAPError, String str, int i, String str2) {
        Bundle bundle = new Bundle();
        bundle.putInt("error_code_key", i);
        bundle.putString("error_message_key", str2);
        bundle.putInt(MAPError.KEY_ERROR_CODE, mAPError.getErrorCode());
        bundle.putString(MAPError.KEY_ERROR_MESSAGE, str);
        bundle.putString(MAPError.KEY_ERROR_TYPE, mAPError.getErrorType());
        return bundle;
    }
}
