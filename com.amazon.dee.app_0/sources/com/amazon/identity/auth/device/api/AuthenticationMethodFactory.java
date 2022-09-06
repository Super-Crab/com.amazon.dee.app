package com.amazon.identity.auth.device.api;

import android.content.Context;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
import com.amazon.identity.auth.device.ds;
import com.amazon.identity.auth.device.ed;
import com.amazon.identity.auth.device.ie;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class AuthenticationMethodFactory {
    private static final String TAG = "com.amazon.identity.auth.device.api.AuthenticationMethodFactory";
    private final String bP;
    private final ds bb;
    private String bm;
    private final Context mContext;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: DCP */
    /* renamed from: com.amazon.identity.auth.device.api.AuthenticationMethodFactory$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] fK = new int[AuthenticationType.values().length];

        static {
            try {
                fK[AuthenticationType.ADPAuthenticator.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                fK[AuthenticationType.DeviceAuthenticator.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                fK[AuthenticationType.OAuth.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                fK[AuthenticationType.None.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @FireOsSdk
    public AuthenticationMethodFactory(Context context) {
        this(context, null);
    }

    private boolean bf() {
        if (this.bb.di() && !this.bb.dh()) {
            return ie.q(this.mContext, this.bm);
        }
        return false;
    }

    @FireOsSdk
    public AuthenticationMethod newAuthenticationMethod(AuthenticationType authenticationType) {
        if (authenticationType == null) {
            return null;
        }
        if (bf()) {
            int i = AnonymousClass1.fK[authenticationType.ordinal()];
            if (i != 1 && i != 2 && i != 3) {
                return new DefaultAuthenticationMethod(this.mContext, this.bP);
            }
            return new CentralDcpAuthenticationMethod(this.mContext, this.bP, authenticationType);
        }
        int i2 = AnonymousClass1.fK[authenticationType.ordinal()];
        if (i2 == 1 || i2 == 2) {
            return new InProcessAdpAuthenticationMethod(this.mContext, this.bP, this.bm, authenticationType);
        }
        if (i2 != 3) {
            return new DefaultAuthenticationMethod(this.mContext, this.bP);
        }
        return new InProcessOauthAuthenticationMethod(this.mContext, this.bP, this.bm, authenticationType);
    }

    @FireOsSdk
    public void setPackageName(String str) {
        this.bm = str;
    }

    @FireOsSdk
    public AuthenticationMethodFactory(Context context, String str) {
        MAPInit.getInstance(context).initialize();
        this.mContext = ed.M(context);
        this.bP = str;
        this.bm = this.mContext.getPackageName();
        this.bb = (ds) this.mContext.getSystemService("sso_platform");
    }

    @FireOsSdk
    public AuthenticationMethod newAuthenticationMethod(String str) {
        AuthenticationType parse = AuthenticationType.parse(str);
        if (parse != null) {
            return newAuthenticationMethod(parse);
        }
        if (bf()) {
            return new CentralDcpAuthenticationMethod(this.mContext, this.bP, str);
        }
        if ("BustedIdentityADPAuthenticator".equals(str)) {
            return new InProcessAdpAuthenticationMethod(this.mContext, this.bP, this.bm, str);
        }
        throw new UnsupportedOperationException(String.format("Authentication Type %s is not supported on this build", str));
    }
}
