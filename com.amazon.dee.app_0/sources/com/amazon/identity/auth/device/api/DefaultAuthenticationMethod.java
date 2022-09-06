package com.amazon.identity.auth.device.api;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.amazon.identity.auth.device.bl;
import com.amazon.identity.auth.device.io;
import java.io.IOException;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class DefaultAuthenticationMethod extends AuthenticationMethod {
    private static final String TAG = "com.amazon.identity.auth.device.api.DefaultAuthenticationMethod";

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultAuthenticationMethod(Context context, String str) {
        super(context, str, AuthenticationType.None);
    }

    @Override // com.amazon.identity.auth.device.api.AuthenticationMethod
    MAPFuture<Bundle> getAuthenticationBundle(Uri uri, String str, Map map, byte[] bArr, bl blVar) throws IOException {
        io.w(TAG, "The AuthenticationType is not supported, so using DefaultAuthenticationMethod but doing nothing.");
        if (blVar != null) {
            AuthenticationMethod.a(blVar, 5, "Probably you are passing non-exist authentication type. Please check AuthenticationType", null);
        }
        return blVar;
    }
}
