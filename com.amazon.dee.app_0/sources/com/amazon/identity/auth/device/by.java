package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.amazon.identity.auth.device.bw;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class by implements bw.a {
    @Override // com.amazon.identity.auth.device.bw.a
    public void a(Context context, Uri uri) {
        mq.incrementCounterAndRecord("OpenExternalBrowerWhenChromeCustomNotPresent:OpenFederatedAuthSignInRequest:Url=" + uri.toString(), new String[0]);
        new StringBuilder("Opening in external browser because device browser does not support chromeCustomTab - url: ").append(uri.toString());
        io.dm("ExternalBrowserFallback");
        context.startActivity(new Intent("android.intent.action.VIEW", uri));
    }
}
