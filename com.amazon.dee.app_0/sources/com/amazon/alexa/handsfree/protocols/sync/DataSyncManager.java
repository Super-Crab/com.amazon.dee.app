package com.amazon.alexa.handsfree.protocols.sync;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Message;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResultCallback;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentity;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentityProvider;
import com.amazon.alexa.handsfree.protocols.utils.HandsFreeSupportedPackageName;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
@AhfScope
/* loaded from: classes8.dex */
public class DataSyncManager implements DataSyncServiceConnectionListener {
    private static final String TAG = "DataSyncManager";
    private final Context mContext;
    private final Map<ComponentName, DataSyncPackage> mDataSyncPackageMap;
    private final HandsFreeUserIdentityProvider mHandsFreeUserIdentityProvider;

    @Inject
    public DataSyncManager(@NonNull Context context, @NonNull HandsFreeUserIdentityProvider handsFreeUserIdentityProvider) {
        this.mContext = context;
        this.mHandsFreeUserIdentityProvider = handsFreeUserIdentityProvider;
        this.mDataSyncPackageMap = initDataSyncPackageMap();
    }

    private void bindService(@NonNull DataSyncPackage dataSyncPackage) {
        try {
            ComponentName componentName = dataSyncPackage.getComponentName();
            String packageName = componentName.getPackageName();
            if (!isPackageInstalled(packageName)) {
                handleBindingFailure(dataSyncPackage, "Package not installed");
                return;
            }
            DataSyncServiceConnection dataSyncServiceConnection = new DataSyncServiceConnection(this);
            Intent intent = new Intent();
            intent.setPackage(packageName);
            intent.setComponent(componentName);
            this.mContext.bindService(intent, dataSyncServiceConnection, 1);
            dataSyncPackage.setDataSyncServiceConnection(dataSyncServiceConnection);
        } catch (SecurityException e) {
            handleBindingFailure(dataSyncPackage, e.getMessage());
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Binding error: ");
            outline107.append(e.getMessage());
            Log.e(str, outline107.toString());
        }
    }

    private void handleBindingFailure(@NonNull DataSyncPackage dataSyncPackage, @NonNull String str) {
        unbindService(dataSyncPackage);
        ResultCallback<ComponentName> resultCallback = dataSyncPackage.getResultCallback();
        String packageName = dataSyncPackage.getComponentName().getPackageName();
        if (resultCallback != null) {
            resultCallback.onError(new CallbackErrorMetadata(String.format(DataSyncConstants.ERROR_MESSAGE_DATA_SEND, packageName, str)));
            dataSyncPackage.setMessage(null);
            dataSyncPackage.setResultCallback(null);
        }
    }

    private void handleBindingSuccess(@NonNull DataSyncPackage dataSyncPackage) {
        ComponentName componentName = dataSyncPackage.getComponentName();
        Message message = dataSyncPackage.getMessage();
        ResultCallback<ComponentName> resultCallback = dataSyncPackage.getResultCallback();
        DataSyncServiceConnection dataSyncServiceConnection = dataSyncPackage.getDataSyncServiceConnection();
        if (dataSyncServiceConnection == null || message == null || resultCallback == null) {
            return;
        }
        dataSyncServiceConnection.send(message, componentName, resultCallback);
        dataSyncPackage.setMessage(null);
        dataSyncPackage.setResultCallback(null);
    }

    private Map<ComponentName, DataSyncPackage> initDataSyncPackageMap() {
        HandsFreeSupportedPackageName[] values;
        HashMap hashMap = new HashMap();
        for (HandsFreeSupportedPackageName handsFreeSupportedPackageName : HandsFreeSupportedPackageName.values()) {
            if (!this.mContext.getPackageName().startsWith(handsFreeSupportedPackageName.toString())) {
                ComponentName componentName = new ComponentName(handsFreeSupportedPackageName.toString(), DataSyncConstants.TARGET_SERVICE_CLASS_NAME);
                hashMap.put(componentName, new DataSyncPackage(componentName));
            }
        }
        return hashMap;
    }

    private boolean isFeatureEnabled() {
        HandsFreeUserIdentity handsFreeUserIdentity = this.mHandsFreeUserIdentityProvider.getHandsFreeUserIdentity();
        return handsFreeUserIdentity != null && handsFreeUserIdentity.hasComponent(HandsFreeComponent.ALEXA_HANDS_FREE_DATA_SYNC);
    }

    private boolean isPackageInstalled(@NonNull String str) {
        try {
            this.mContext.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            String str2 = TAG;
            Log.e(str2, "Unable to find package: " + str, e);
            return false;
        }
    }

    private void unbindService(@NonNull DataSyncPackage dataSyncPackage) {
        DataSyncServiceConnection dataSyncServiceConnection = dataSyncPackage.getDataSyncServiceConnection();
        if (dataSyncServiceConnection != null) {
            this.mContext.unbindService(dataSyncServiceConnection);
            dataSyncPackage.setDataSyncServiceConnection(null);
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.sync.DataSyncServiceConnectionListener
    public void onBindingFailure(ComponentName componentName, String str) {
        DataSyncPackage dataSyncPackage = this.mDataSyncPackageMap.get(componentName);
        if (dataSyncPackage != null) {
            handleBindingFailure(dataSyncPackage, str);
        }
    }

    @Override // com.amazon.alexa.handsfree.protocols.sync.DataSyncServiceConnectionListener
    public void onBindingSuccess(ComponentName componentName) {
        DataSyncPackage dataSyncPackage = this.mDataSyncPackageMap.get(componentName);
        if (dataSyncPackage != null) {
            handleBindingSuccess(dataSyncPackage);
        }
    }

    public synchronized void send(@NonNull Message message, @NonNull ResultCallback<ComponentName> resultCallback) {
        if (!isFeatureEnabled()) {
            resultCallback.onError(new CallbackErrorMetadata(String.format(DataSyncConstants.ERROR_MESSAGE_DATA_SEND, "all apps", "Data Sync feature disabled")));
            return;
        }
        for (DataSyncPackage dataSyncPackage : this.mDataSyncPackageMap.values()) {
            ComponentName componentName = dataSyncPackage.getComponentName();
            DataSyncServiceConnection dataSyncServiceConnection = dataSyncPackage.getDataSyncServiceConnection();
            if (dataSyncServiceConnection != null && dataSyncServiceConnection.isServiceBound()) {
                dataSyncServiceConnection.send(message, componentName, resultCallback);
            } else {
                unbindService(dataSyncPackage);
                dataSyncPackage.setMessage(message);
                dataSyncPackage.setResultCallback(resultCallback);
                bindService(dataSyncPackage);
            }
        }
    }

    @VisibleForTesting
    DataSyncManager(@NonNull Context context, @NonNull HandsFreeUserIdentityProvider handsFreeUserIdentityProvider, @NonNull Map<ComponentName, DataSyncPackage> map) {
        this.mContext = context;
        this.mHandsFreeUserIdentityProvider = handsFreeUserIdentityProvider;
        this.mDataSyncPackageMap = map;
    }
}
