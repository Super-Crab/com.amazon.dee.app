package com.amazon.identity.auth.device;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.HashMap;
import java.util.Map;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class bv extends br {
    private final AmazonAccountManager bg;
    private a hE;
    private final Context mContext;
    private final gg w;

    public bv(Context context) {
        if (context != null) {
            this.mContext = ed.M(context.getApplicationContext());
            this.bg = (AmazonAccountManager) this.mContext.getSystemService("dcp_amazon_account_man");
            this.w = ((gh) this.mContext.getSystemService("dcp_data_storage_factory")).dV();
            return;
        }
        throw new IllegalArgumentException();
    }

    public static a a(gg ggVar) {
        a aVar;
        Map<String, String> a = ggVar.a("com.amazon.identity.auth.device.b.e.SHARED_PREFS", AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN_AND_PRIVATE_KEY_LIST);
        String str = a.get(AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN);
        String str2 = a.get(AccountConstants.TOKEN_TYPE_DEVICE_PRIVATE_KEY);
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            io.i("AnonymousAccountCredentials", "Migrating anonymous credentials from legacy name space to new one.");
            mq.incrementCounterAndRecord("obfuscated_ANONYMOUS_CREDENTIALS_NAMESPACE", new String[0]);
            ggVar.c("com.amazon.identity.auth.device.credentials.AnonymousAccountCredentials.SHARED_PREFS", a);
            HashMap hashMap = new HashMap();
            hashMap.put(AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN, "");
            hashMap.put(AccountConstants.TOKEN_TYPE_DEVICE_PRIVATE_KEY, "");
            ggVar.c("com.amazon.identity.auth.device.b.e.SHARED_PREFS", hashMap);
            aVar = new a(str, str2);
        } else {
            Map<String, String> a2 = ggVar.a("com.amazon.identity.auth.device.credentials.AnonymousAccountCredentials.SHARED_PREFS", AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN_AND_PRIVATE_KEY_LIST);
            aVar = new a(a2.get(AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN), a2.get(AccountConstants.TOKEN_TYPE_DEVICE_PRIVATE_KEY));
        }
        io.i("AnonymousAccountCredentials", String.format("Returning adp token: %s, private key: %s", io.m4069do(aVar.getToken()), io.m4069do(aVar.a())));
        return aVar;
    }

    protected void by() {
        a aVar = this.hE;
        if (aVar == null || TextUtils.isEmpty(aVar.a()) || TextUtils.isEmpty(this.hE.getToken())) {
            this.hE = a(this.w);
        }
    }

    @Override // com.amazon.identity.auth.device.kq
    public a y() {
        by();
        return this.hE;
    }

    @Override // com.amazon.identity.auth.device.br
    public boolean z() {
        if (this.bg.n()) {
            return true;
        }
        a a = a(this.w);
        return TextUtils.isEmpty(y().getToken()) || TextUtils.isEmpty(y().a()) || !y().getToken().equals(a.getToken()) || !y().a().equals(a.a());
    }
}
