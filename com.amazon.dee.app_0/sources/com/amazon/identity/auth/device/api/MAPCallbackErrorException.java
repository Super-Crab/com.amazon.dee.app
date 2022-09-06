package com.amazon.identity.auth.device.api;

import android.os.Bundle;
import android.os.Parcel;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class MAPCallbackErrorException extends Exception {
    private static final long serialVersionUID = 1;
    private final MAPError mError;
    private final byte[] mErrorBundleBytes;
    private final String mErrorMessage;

    @FireOsSdk
    public MAPCallbackErrorException(Bundle bundle) {
        this(bundle, null, null);
    }

    public MAPError getError() {
        return this.mError;
    }

    @FireOsSdk
    public Bundle getErrorBundle() {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.unmarshall(this.mErrorBundleBytes, 0, this.mErrorBundleBytes.length);
            obtain.setDataPosition(0);
            return obtain.readBundle();
        } finally {
            obtain.recycle();
        }
    }

    public String getErrorMessage() {
        return this.mErrorMessage;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public MAPCallbackErrorException(android.os.Bundle r4, com.amazon.identity.auth.device.api.MAPError r5, java.lang.String r6) {
        /*
            r3 = this;
            r0 = 0
            if (r4 != 0) goto L4
            goto L2d
        L4:
            java.lang.String r1 = "com.amazon.dcp.sso.ErrorMessage"
            java.lang.String r1 = r4.getString(r1)
            if (r1 != 0) goto Ld
            goto L2d
        Ld:
            java.lang.String r0 = "com.amazon.dcp.sso.ErrorCode"
            boolean r2 = r4.containsKey(r0)
            if (r2 == 0) goto L2c
            java.lang.String r2 = " ("
            java.lang.StringBuilder r1 = com.android.tools.r8.GeneratedOutlineSupport1.outline113(r1, r2)
            int r0 = r4.getInt(r0)
            r1.append(r0)
            java.lang.String r0 = ")"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            goto L2d
        L2c:
            r0 = r1
        L2d:
            r3.<init>(r0)
            if (r4 == 0) goto L33
            goto L38
        L33:
            android.os.Bundle r4 = new android.os.Bundle
            r4.<init>()
        L38:
            if (r5 == 0) goto L3d
            r3.mError = r5
            goto L4f
        L3d:
            com.amazon.identity.auth.device.api.MAPError$CommonError r5 = com.amazon.identity.auth.device.api.MAPError.CommonError.INTERNAL_ERROR
            int r5 = r5.getErrorCode()
            java.lang.String r0 = "com.amazon.map.error.errorCode"
            int r5 = r4.getInt(r0, r5)
            com.amazon.identity.auth.device.api.MAPError r5 = com.amazon.identity.auth.device.api.MAPError.getErrorFromValue(r5)
            r3.mError = r5
        L4f:
            boolean r5 = android.text.TextUtils.isEmpty(r6)
            if (r5 != 0) goto L58
            r3.mErrorMessage = r6
            goto L62
        L58:
            java.lang.String r5 = "com.amazon.map.error.errorMessage"
            java.lang.String r6 = "An internal error occurs!"
            java.lang.String r5 = r4.getString(r5, r6)
            r3.mErrorMessage = r5
        L62:
            android.os.Parcel r5 = android.os.Parcel.obtain()
            r5.writeBundle(r4)     // Catch: java.lang.Throwable -> L73
            byte[] r4 = r5.marshall()     // Catch: java.lang.Throwable -> L73
            r3.mErrorBundleBytes = r4     // Catch: java.lang.Throwable -> L73
            r5.recycle()
            return
        L73:
            r4 = move-exception
            r5.recycle()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.identity.auth.device.api.MAPCallbackErrorException.<init>(android.os.Bundle, com.amazon.identity.auth.device.api.MAPError, java.lang.String):void");
    }
}
