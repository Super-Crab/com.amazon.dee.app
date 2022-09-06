package com.amazon.identity.auth.device.authorization;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import com.amazon.identity.auth.device.AuthError;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public abstract class MAPServiceConnection<T> implements ServiceConnection {
    private static final String LOG_TAG = MAPServiceConnection.class.getName();
    protected AmazonServiceListener mListener;
    protected IInterface mService = null;

    public abstract IInterface getServiceInterface(IBinder iBinder);

    public abstract Class<T> getServiceInterfaceClass();

    protected boolean isValidService(IBinder iBinder) {
        try {
            return iBinder.getInterfaceDescriptor().equals(getServiceInterfaceClass().getName());
        } catch (Exception e) {
            String str = LOG_TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("");
            outline107.append(e.getMessage());
            MAPLog.e(str, outline107.toString(), e);
            return false;
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        MAPLog.i(LOG_TAG, "onServiceConnected called");
        if (isValidService(iBinder)) {
            this.mService = getServiceInterface(iBinder);
            this.mListener.onBindSuccess(this.mService);
            return;
        }
        this.mListener.onBindError(new AuthError("Returned service's interface doesn't match authorization service", AuthError.ERROR_TYPE.ERROR_UNKNOWN));
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        MAPLog.i(LOG_TAG, "onServiceDisconnected called");
        this.mService = null;
    }

    public void setServiceListener(AmazonServiceListener amazonServiceListener) {
        if (amazonServiceListener != null) {
            this.mListener = amazonServiceListener;
            return;
        }
        throw new IllegalArgumentException("listener cannot be null!");
    }
}
