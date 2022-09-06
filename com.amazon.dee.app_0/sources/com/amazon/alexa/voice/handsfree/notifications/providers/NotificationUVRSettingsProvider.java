package com.amazon.alexa.voice.handsfree.notifications.providers;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.notification.api.UVRSettingsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentity;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.PreferenceCallback;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.ResponsePreferenceCallback;
import com.amazon.alexa.handsfree.settings.voxsettings.ShowOnLockscreenResultReceiver;
import com.amazon.alexa.handsfree.settings.voxsettings.VoxSettingsEnqueuer;
import com.amazon.comms.config.util.DeviceConfigConstants;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes11.dex */
public class NotificationUVRSettingsProvider implements UVRSettingsProvider {
    private static final String BLOCK_ALL_RESPONSES = "BLOCK_ALL";
    private static final String TAG = "NotiUVRSettingsProvider";
    private static final int TIME_OUT_MILLIS = 3000;
    private final AMPDInformationProvider mAMPDInformationProvider;
    private final HandsFreeUserIdentity mHandsFreeUser;
    private final UVRVendorSettings mUVRVendorSettings;
    private final VoxSettingsEnqueuer mVoxSettingsEnqueuer;

    public NotificationUVRSettingsProvider(@Nullable UVRVendorSettings uVRVendorSettings, @NonNull AMPDInformationProvider aMPDInformationProvider, @NonNull Context context) {
        this(uVRVendorSettings, new VoxSettingsEnqueuer(), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).handsFreeUserIdentityProvider().getHandsFreeUserIdentity(), aMPDInformationProvider);
    }

    public boolean isBlockSensitiveRequest() {
        HandsFreeUserIdentity handsFreeUserIdentity = this.mHandsFreeUser;
        return handsFreeUserIdentity != null && handsFreeUserIdentity.hasComponent(HandsFreeComponent.HANDS_FREE_BLOCK_SENSITIVE_REQUEST) && this.mAMPDInformationProvider.isBSR();
    }

    @Override // com.amazon.alexa.handsfree.notification.api.UVRSettingsProvider
    @RequiresApi(24)
    public boolean isShowOnLockscreenEnabled(@NonNull Context context) {
        ShowOnLockscreenResultReceiver showOnLockscreenResultReceiver;
        final CompletableFuture completableFuture = new CompletableFuture();
        final CompletableFuture completableFuture2 = new CompletableFuture();
        ResponsePreferenceCallback responsePreferenceCallback = new ResponsePreferenceCallback() { // from class: com.amazon.alexa.voice.handsfree.notifications.providers.NotificationUVRSettingsProvider.1
            @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.ResponsePreferenceCallback
            public void onPreferenceChanged(String str) {
                completableFuture.complete(str);
            }
        };
        PreferenceCallback preferenceCallback = new PreferenceCallback() { // from class: com.amazon.alexa.voice.handsfree.notifications.providers.NotificationUVRSettingsProvider.2
            @Override // com.amazon.alexa.handsfree.settings.contract.handsfreesettings.PreferenceCallback
            public void onPreferenceChanged(boolean z) {
                completableFuture2.complete(Boolean.valueOf(z));
            }
        };
        if (isBlockSensitiveRequest()) {
            showOnLockscreenResultReceiver = new ShowOnLockscreenResultReceiver(responsePreferenceCallback);
        } else {
            showOnLockscreenResultReceiver = new ShowOnLockscreenResultReceiver(preferenceCallback);
        }
        this.mVoxSettingsEnqueuer.getShowOnLockscreen(context, showOnLockscreenResultReceiver);
        try {
            if (isBlockSensitiveRequest()) {
                return true ^ ((String) completableFuture.get(DeviceConfigConstants.DEFAULT_CALL_END_TO_SHUTDOWN_TIMEOUT_MS, TimeUnit.MILLISECONDS)).equals("BLOCK_ALL");
            }
            return ((Boolean) completableFuture2.get(DeviceConfigConstants.DEFAULT_CALL_END_TO_SHUTDOWN_TIMEOUT_MS, TimeUnit.MILLISECONDS)).booleanValue();
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            Log.e(TAG, "isShowOnLockScreenEnabled: ", e, new Object[0]);
            return true;
        }
    }

    @Override // com.amazon.alexa.handsfree.notification.api.UVRSettingsProvider
    public boolean isUVRAvailable() {
        UVRVendorSettings uVRVendorSettings = this.mUVRVendorSettings;
        return uVRVendorSettings != null && uVRVendorSettings.isUVRAvailable();
    }

    @Override // com.amazon.alexa.handsfree.notification.api.UVRSettingsProvider
    public boolean isUVREnabled() {
        UVRVendorSettings uVRVendorSettings = this.mUVRVendorSettings;
        return uVRVendorSettings != null && uVRVendorSettings.isUVREnrolled(UserInfo.DEFAULT_USER);
    }

    @VisibleForTesting
    NotificationUVRSettingsProvider(@Nullable UVRVendorSettings uVRVendorSettings, @NonNull VoxSettingsEnqueuer voxSettingsEnqueuer, @NonNull HandsFreeUserIdentity handsFreeUserIdentity, @NonNull AMPDInformationProvider aMPDInformationProvider) {
        this.mUVRVendorSettings = uVRVendorSettings;
        this.mVoxSettingsEnqueuer = voxSettingsEnqueuer;
        this.mHandsFreeUser = handsFreeUserIdentity;
        this.mAMPDInformationProvider = aMPDInformationProvider;
    }
}
