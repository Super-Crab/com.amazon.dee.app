package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.identity.auth.device.storage.BackwardsCompatiableDataStorage;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class cs extends cx {
    private static final String TAG = "com.amazon.identity.auth.device.cs";
    private final MAPAccountManager av;
    private final MultipleAccountManager hT;

    public cs(Context context, MultipleAccountManager multipleAccountManager) {
        super(context);
        this.hT = multipleAccountManager;
        this.av = new MAPAccountManager(this.o);
    }

    @Override // com.amazon.identity.auth.device.cx, com.amazon.identity.auth.device.ea
    public String getDeviceSerialNumber() {
        String accountForMapping = this.hT.getAccountForMapping(new MultipleAccountManager.PrimaryUserMappingType());
        if (accountForMapping == null) {
            accountForMapping = this.av.getAccount();
        }
        if (accountForMapping != null) {
            return new BackwardsCompatiableDataStorage(this.o).b(accountForMapping, "com.amazon.dcp.sso.token.device.deviceserialname");
        }
        io.e(TAG, "Cannot return DSN on this platform (Grover, Canary V1). We can only return it while the device is registered. Please use MAPAccountManager.getAccount() to ensure an account is existed.");
        throw new UnsupportedOperationException("Cannot return DSN on this platform (Grover, Canary V1). We can only return it while the device is registered. Please use MAPAccountManager.getAccount() to ensure an account is existed.");
    }
}
