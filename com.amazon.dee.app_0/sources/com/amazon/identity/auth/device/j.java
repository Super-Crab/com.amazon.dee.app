package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class j {
    private final Context mContext;
    private final gg w;

    public j(Context context) {
        this.mContext = ed.M(context.getApplicationContext());
        this.w = ((gh) this.mContext.getSystemService("dcp_data_storage_factory")).dV();
    }

    public void a(String str, String str2) {
        HashMap outline134 = GeneratedOutlineSupport1.outline134(AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN, str, AccountConstants.TOKEN_TYPE_DEVICE_PRIVATE_KEY, str2);
        io.i("AccountRegistrarAuthenticator", String.format("Setting anonymous credentials adp token: %s, private key: %s", io.m4069do(str), io.m4069do(str2)));
        this.w.c("com.amazon.identity.auth.device.credentials.AnonymousAccountCredentials.SHARED_PREFS", outline134);
    }
}
