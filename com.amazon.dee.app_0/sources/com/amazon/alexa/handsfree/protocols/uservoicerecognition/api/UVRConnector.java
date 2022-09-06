package com.amazon.alexa.handsfree.protocols.uservoicerecognition.api;

import android.content.Context;
import android.content.ServiceConnection;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes8.dex */
public abstract class UVRConnector {
    private static final String TAG = "UVRConnector";
    private final ServiceConnection mEnrollmentConnection;
    private final ServiceConnection mTuningSettingsConnection;
    private final ServiceConnection mVendorSettingsConnection;
    private final Map<Context, Integer> mEnrollmentConnectedContexts = new HashMap();
    private final Map<Context, Integer> mVendorSettingsConnectedContexts = new HashMap();

    public UVRConnector(@NonNull ServiceConnection serviceConnection, @NonNull ServiceConnection serviceConnection2, @NonNull ServiceConnection serviceConnection3) {
        this.mEnrollmentConnection = serviceConnection;
        this.mVendorSettingsConnection = serviceConnection2;
        this.mTuningSettingsConnection = serviceConnection3;
    }

    private boolean decreaseConnectionCount(@NonNull Context context, @NonNull Map<Context, Integer> map) {
        if (map.containsKey(context)) {
            map.put(context, Integer.valueOf(map.get(context).intValue() - 1));
            if (map.get(context).intValue() <= 0) {
                map.put(context, 0);
                return true;
            }
        }
        return false;
    }

    private void increaseConnectionCount(@NonNull Context context, @NonNull Map<Context, Integer> map) {
        if (map.containsKey(context)) {
            map.put(context, Integer.valueOf(map.get(context).intValue() + 1));
        } else {
            map.put(context, 1);
        }
    }

    private boolean isEnrollmentServiceConnected(@NonNull Context context) {
        return this.mEnrollmentConnectedContexts.containsKey(context) && this.mEnrollmentConnectedContexts.get(context).intValue() > 0;
    }

    private boolean isVendorSettingsConnected(@NonNull Context context) {
        return this.mVendorSettingsConnectedContexts.containsKey(context) && this.mVendorSettingsConnectedContexts.get(context).intValue() > 0;
    }

    protected abstract void bindToEnrollmentService(@NonNull Context context);

    protected abstract void bindToVendorSettingsService(@NonNull Context context);

    public synchronized void endConnection(@NonNull Context context) {
        if (decreaseConnectionCount(context, this.mEnrollmentConnectedContexts)) {
            context.unbindService(this.mEnrollmentConnection);
        } else {
            Log.d(TAG, "Context was not connected to enroll service, nothing to disconnect.");
        }
        if (decreaseConnectionCount(context, this.mVendorSettingsConnectedContexts)) {
            context.unbindService(this.mVendorSettingsConnection);
        } else {
            Log.d(TAG, "Context was not connected to vendor settings service, nothing to disconnect.");
        }
    }

    public ServiceConnection getEnrollmentConnection() {
        return this.mEnrollmentConnection;
    }

    public ServiceConnection getTuningSettingsConnection() {
        return this.mTuningSettingsConnection;
    }

    public ServiceConnection getVendorSettingsConnection() {
        return this.mVendorSettingsConnection;
    }

    public synchronized void startConnection(@NonNull Context context, boolean z) {
        if (!isEnrollmentServiceConnected(context)) {
            bindToEnrollmentService(context);
        }
        increaseConnectionCount(context, this.mEnrollmentConnectedContexts);
        if (!isVendorSettingsConnected(context)) {
            bindToVendorSettingsService(context);
        }
        increaseConnectionCount(context, this.mVendorSettingsConnectedContexts);
    }
}
