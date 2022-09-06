package com.amazon.identity.auth.device;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
import com.amazon.identity.auth.device.utils.AccountConstants;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class bu extends br {
    private static final String TAG = "com.amazon.identity.auth.device.bu";
    private String bP;
    private final AmazonAccountManager bg;
    private a hE;
    private final BackwardsCompatiableDataStorage hF;
    private String hG;
    private String hH;
    private String hI;
    private String hJ;
    private final ed o;

    protected bu() {
        this.o = null;
        this.bg = null;
        this.hF = null;
        this.hJ = null;
    }

    private String aN(String str) {
        String accountDirectedId = getAccountDirectedId();
        if (accountDirectedId == null) {
            io.e(TAG, "Failed to locate an amazon account to retrieve amazon credentials from.");
            return null;
        }
        if (!TextUtils.isEmpty(this.hJ)) {
            str = gv.i(this.o, this.hJ, str);
        }
        return this.hF.z(accountDirectedId, str);
    }

    private String aO(String str) {
        String accountDirectedId = getAccountDirectedId();
        if (accountDirectedId == null) {
            io.e(TAG, "Failed to locate an amazon account to retrieve amazon credentials from.");
            return null;
        }
        return this.hF.x(accountDirectedId, str);
    }

    public static bu g(Context context, String str, String str2) {
        if (!new AmazonAccountManager(context).n()) {
            return null;
        }
        return new bu(context, str, str2);
    }

    private String getAccountDirectedId() {
        if (this.bP == null) {
            this.bP = this.bg.o();
        }
        return this.bP;
    }

    public static bu h(Context context, String str, String str2) {
        AmazonAccountManager amazonAccountManager = new AmazonAccountManager(context);
        if (str != null && amazonAccountManager.D(str)) {
            return new bu(context, str, str2);
        }
        io.e(TAG, "Error, the account given does not exist. Cannot construct account credentials");
        return null;
    }

    protected String bx() {
        String accountDirectedId = getAccountDirectedId();
        if (accountDirectedId == null) {
            io.dm(TAG);
            return null;
        }
        return this.hF.b(accountDirectedId, AccountConstants.KEY_ACCOUNT_UUID);
    }

    @Override // com.amazon.identity.auth.device.kq
    public a y() {
        if (this.hE == null) {
            this.hE = new a(aN(AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN), aN(AccountConstants.TOKEN_TYPE_DEVICE_PRIVATE_KEY));
        }
        return this.hE;
    }

    @Override // com.amazon.identity.auth.device.br
    public boolean z() {
        String bx = bx();
        return bx == null || !bx.equals(this.hG) || !TextUtils.equals(this.hH, aO(AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN)) || !TextUtils.equals(this.hI, aO(AccountConstants.TOKEN_TYPE_DEVICE_PRIVATE_KEY));
    }

    protected bu(Context context, String str, String str2) {
        this.o = ed.M(context.getApplicationContext());
        this.hJ = str2;
        this.bg = (AmazonAccountManager) this.o.getSystemService("dcp_amazon_account_man");
        this.hF = new BackwardsCompatiableDataStorage(this.o);
        this.bP = str;
        this.hG = bx();
        this.hH = aO(AccountConstants.TOKEN_TYPE_DEVICE_ADP_TOKEN);
        this.hI = aO(AccountConstants.TOKEN_TYPE_DEVICE_PRIVATE_KEY);
    }
}
