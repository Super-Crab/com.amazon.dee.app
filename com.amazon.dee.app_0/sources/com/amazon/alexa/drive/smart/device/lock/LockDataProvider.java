package com.amazon.alexa.drive.smart.device.lock;

import android.content.Context;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.smart.device.contract.SmartDeviceContract;
import com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider;
import com.amazon.alexa.drive.smart.device.data.SmartDeviceEndpoint;
import java.net.MalformedURLException;
/* loaded from: classes7.dex */
public class LockDataProvider extends SmartDeviceDataProvider {
    private static final String LOCK_OPERATION_LOCK = "lock";
    private static final String LOCK_OPERATION_UNLOCK = "unlock";

    public LockDataProvider(Context context, SmartDeviceContract.ServerResponseListener serverResponseListener) {
        super(context, serverResponseListener);
    }

    public void getLockDevice() throws MalformedURLException {
        startTask(SmartDeviceDataProvider.Task.Builder.newInstance().url(SmartDeviceEndpoint.getLockApi()).method("POST").message(this.context.getString(R.string.dm_smart_home_graphql_get_lock_state)).build());
    }

    public void setLockDevice(String str, boolean z) throws MalformedURLException {
        Context context = this.context;
        int i = R.string.dm_smart_home_graphql_set_lock_state;
        Object[] objArr = new Object[2];
        objArr[0] = str;
        objArr[1] = z ? "lock" : "unlock";
        startTask(SmartDeviceDataProvider.Task.Builder.newInstance().url(SmartDeviceEndpoint.getLockApi()).method("POST").message(context.getString(i, objArr)).build());
    }
}
