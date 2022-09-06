package com.amazon.alexa.voice.handsfree.features;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.DeviceInformation;
import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import com.amazon.alexa.handsfree.devices.features.HandsFreeFeature;
import com.amazon.alexa.handsfree.protocols.features.FeatureChecker;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentity;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.identity.api.UserIdentity;
import java.util.List;
/* loaded from: classes11.dex */
public class HandsFreeUser implements HandsFreeUserIdentity {
    private static final String TAG = "HandsFreeUser";
    private final ComponentStateProvider mComponentStateProvider;
    private final Context mContext;
    private final DeviceInformation mDeviceInformation;
    private final FeatureChecker mFeatureChecker;
    private final MetricsBuilderProvider mMetricsBuilderProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HandsFreeUser(@NonNull Context context, @Nullable UserIdentity userIdentity) {
        this(context, new UserIdentityFeatureChecker(userIdentity), DeviceTypeInformationProvider.getInstance(context).getDeviceInformationForCurrentDevice(context), MetricsBuilderProvider.getInstance(context), new ComponentStateProvider(context));
    }

    @Override // com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentity
    public boolean hasComponent(@NonNull HandsFreeComponent handsFreeComponent) {
        List<HandsFreeFeature> listOfHandsFreeFeatures = HandsFreeFeature.getListOfHandsFreeFeatures(handsFreeComponent);
        boolean hasRemainedAvailable = this.mComponentStateProvider.hasRemainedAvailable(handsFreeComponent);
        if (isTestModeWeblabUp(hasRemainedAvailable, handsFreeComponent)) {
            return true;
        }
        boolean isEnabled = HandsFreeFeature.HANDS_FREE_EXPERIENCE.isEnabled(this.mFeatureChecker, this.mDeviceInformation, hasRemainedAvailable);
        String featureName = HandsFreeFeature.HANDS_FREE_EXPERIENCE.getFeatureName();
        for (int i = 0; i < listOfHandsFreeFeatures.size() && isEnabled; i++) {
            HandsFreeFeature handsFreeFeature = listOfHandsFreeFeatures.get(i);
            featureName = handsFreeFeature.getFeatureName();
            isEnabled &= handsFreeFeature.isEnabled(this.mFeatureChecker, this.mDeviceInformation, hasRemainedAvailable);
        }
        recordStateChanges(handsFreeComponent, featureName, isEnabled, hasRemainedAvailable, true);
        return isEnabled;
    }

    @VisibleForTesting
    boolean isTestModeWeblabUp(@NonNull boolean z, @NonNull HandsFreeComponent handsFreeComponent) {
        if (handsFreeComponent != HandsFreeComponent.TEST_MODE_HANDS_FREE_EXPERIENCE) {
            return false;
        }
        boolean isEnabledForSDL = HandsFreeFeature.TEST_MODE_HANDS_FREE_EXPERIENCE_ALL.isEnabledForSDL(this.mFeatureChecker, z);
        recordStateChanges(handsFreeComponent, HandsFreeFeature.TEST_MODE_HANDS_FREE_EXPERIENCE_ALL.getFeatureName(), isEnabledForSDL, z, false);
        return isEnabledForSDL;
    }

    @VisibleForTesting
    void recordStateChanges(@NonNull HandsFreeComponent handsFreeComponent, @NonNull String str, boolean z, boolean z2, boolean z3) {
        if (z2 != z) {
            this.mComponentStateProvider.storeCurrentState(handsFreeComponent, z);
            String str2 = TAG;
            Log.d(str2, "Changing " + handsFreeComponent + " availability to " + z);
            if (!z2 || !z3) {
                return;
            }
            this.mMetricsBuilderProvider.newBuilder().withFeatureGateDialedDownMetric(TAG, str, handsFreeComponent.toString()).emit(this.mContext);
        }
    }

    @VisibleForTesting
    HandsFreeUser(@NonNull Context context, @Nullable FeatureChecker featureChecker, @NonNull DeviceInformation deviceInformation, @NonNull MetricsBuilderProvider metricsBuilderProvider, @NonNull ComponentStateProvider componentStateProvider) {
        this.mContext = context;
        this.mFeatureChecker = featureChecker;
        this.mDeviceInformation = deviceInformation;
        this.mMetricsBuilderProvider = metricsBuilderProvider;
        this.mComponentStateProvider = componentStateProvider;
    }
}
