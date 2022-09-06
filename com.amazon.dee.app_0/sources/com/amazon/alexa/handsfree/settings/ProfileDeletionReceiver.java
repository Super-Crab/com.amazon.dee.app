package com.amazon.alexa.handsfree.settings;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.UVRModule;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public class ProfileDeletionReceiver extends BroadcastReceiver {
    private static final String TAG = ProfileDeletionReceiver.class.getSimpleName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        Log.e(TAG, "onReceive: ProfileDeletionReceiver Received!");
        UVRVendorSettings vendorSettings = UVRModule.INSTANCE.getUVRContract().getVendorSettings();
        if (vendorSettings.isUVREnrolled(UserInfo.DEFAULT_USER)) {
            vendorSettings.removeUVRModel(UserInfo.DEFAULT_USER, new ResponseCallback() { // from class: com.amazon.alexa.handsfree.settings.ProfileDeletionReceiver.1
                @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
                public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                    Log.e(ProfileDeletionReceiver.TAG, "onError: profile deletion from hacky broadcast FAILED!");
                }

                @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
                public void onSuccess() {
                    Log.e(ProfileDeletionReceiver.TAG, "onSuccess: profile deletion from hacky broadcast SUCCEEDED!");
                }
            });
        }
    }
}
