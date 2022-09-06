package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.accounts.AmazonAccountManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.identity.kcpsdk.auth.RegisterDeviceRequest;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ac {
    private final ed o;
    private final AmazonAccountManager s;
    private final gg w;

    public ac(Context context) {
        this.o = ed.M(context);
        this.s = (AmazonAccountManager) this.o.getSystemService("dcp_amazon_account_man");
        this.w = ((gh) this.o.getSystemService("dcp_data_storage_factory")).dV();
    }

    public boolean X(String str) {
        return this.s.C(str);
    }

    String Y() {
        for (String str : this.s.p()) {
            if (this.s.A(str) && hasPrimaryRole(str)) {
                return str;
            }
        }
        return null;
    }

    public void b(String str, Bundle bundle) {
        String j = j(bundle);
        if (!TextUtils.isEmpty(j)) {
            String.format("Promote account %s as the new default primary", j);
            io.gC();
            this.w.a(j, AccountConstants.KEY_SECONDARY_AMAZON_ACCOUNT, (String) null);
            bundle.putString("NewDefaultPrimary", j);
            if (this.s.B(str)) {
                this.w.a(j, AccountConstants.KEY_SESSION_USER_AMAZON_ACCOUNT, "true");
            }
        }
        String.format("Set status for account %s as %s", str, AmazonAccountManager.AccountRegistrationStatus.Deregistering.getValue());
        io.gC();
        this.s.a(str, AmazonAccountManager.AccountRegistrationStatus.Deregistering);
    }

    public boolean hasPrimaryRole(String str) {
        String b = this.w.b(str, AccountConstants.KEY_DEVICE_ACCOUNT_ROLE);
        return !TextUtils.isEmpty(b) && RegisterDeviceRequest.DeviceAccountRole.PRIMARY.name().equals(b);
    }

    String j(Bundle bundle) {
        if (bundle.getBoolean("DeregisteringDevice") || !bundle.getBoolean("DeregisteringDefaultPrimary")) {
            return null;
        }
        return Y();
    }
}
