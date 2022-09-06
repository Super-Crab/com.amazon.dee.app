package com.amazon.alexa.handsfree.audio.features;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.feature.consumer.DefaultFeatureFlagConsumer;
import com.amazon.alexa.feature.consumer.api.FeatureQuery;
import com.amazon.alexa.handsfree.devices.DeviceInformation;
import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import com.amazon.alexa.handsfree.devices.features.HandsFreeFeature;
import com.amazon.alexa.handsfree.protocols.features.FeatureChecker;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.utils.Log;
/* loaded from: classes8.dex */
public class ComponentEnablementResolver {
    private static final String TAG = "ComponentEnablementResolver";
    private final Context mContext;
    private final DeviceTypeInformationProvider mDeviceTypeInformationProvider;
    private final FeatureChecker mFeatureChecker;

    /* loaded from: classes8.dex */
    private static final class FeatureCheckerImpl implements FeatureChecker {
        private final FeatureQuery mFeatureQuery;

        @Override // com.amazon.alexa.handsfree.protocols.features.FeatureChecker
        public boolean isActive(@NonNull String str, boolean z) {
            return this.mFeatureQuery.isActive(str);
        }

        private FeatureCheckerImpl(FeatureQuery featureQuery) {
            this.mFeatureQuery = featureQuery;
        }
    }

    public ComponentEnablementResolver(Context context) {
        this(context, DeviceTypeInformationProvider.getInstance(context), new FeatureCheckerImpl(new DefaultFeatureFlagConsumer(context).getFeatureQuery()));
    }

    public boolean isComponentEnabled(@NonNull HandsFreeComponent handsFreeComponent) {
        DeviceInformation supportedDeviceInformation = this.mDeviceTypeInformationProvider.getSupportedDeviceInformation(this.mContext);
        if (supportedDeviceInformation == null) {
            String str = TAG;
            Log.d(str, handsFreeComponent.name() + " is not enabled as device information not found.");
            return false;
        }
        for (HandsFreeFeature handsFreeFeature : HandsFreeFeature.getListOfHandsFreeFeatures(handsFreeComponent)) {
            if (!handsFreeFeature.isEnabled(this.mFeatureChecker, supportedDeviceInformation, false)) {
                String str2 = TAG;
                Log.d(str2, handsFreeComponent.name() + " is not enabled as feature " + handsFreeFeature.getFeatureName() + " is not active.");
                return false;
            }
        }
        String str3 = TAG;
        Log.d(str3, handsFreeComponent.name() + " is enabled.");
        return true;
    }

    @VisibleForTesting
    ComponentEnablementResolver(Context context, DeviceTypeInformationProvider deviceTypeInformationProvider, FeatureChecker featureChecker) {
        this.mContext = context;
        this.mDeviceTypeInformationProvider = deviceTypeInformationProvider;
        this.mFeatureChecker = featureChecker;
    }
}
