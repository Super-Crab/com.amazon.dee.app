package com.amazon.alexa.voice.handsfree.reactnative;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.feature.consumer.DefaultFeatureFlagConsumer;
import com.amazon.alexa.feature.consumer.api.FeatureQuery;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentity;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentityProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.UVRModule;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.preload.attribution.DefaultPreloadAttributionManager;
import com.amazon.alexa.preload.attribution.FeatureQueryBridge;
import com.amazon.alexa.voice.handsfree.features.VoxEnrollmentTypeResolver;
import com.amazon.alexa.voice.handsfree.settings.VoiceAppInfo;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import dagger.Lazy;
/* loaded from: classes11.dex */
public class ReactMethodsAMPDInfoAdapter {
    private static ReactMethodsAMPDInfoAdapter mInstance;
    private final AMPDInformationProvider mAMPDInformationProvider;
    private final Context mContext;
    private final Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;
    private final HandsFreeUserIdentityProvider mHandsFreeUserIdentityProvider;
    @Nullable
    private final UVRVendorSettings mUVRVendorSettings;

    @VisibleForTesting
    ReactMethodsAMPDInfoAdapter(@NonNull Context context, @NonNull AMPDInformationProvider aMPDInformationProvider, @NonNull HandsFreeUserIdentityProvider handsFreeUserIdentityProvider, @NonNull Lazy<EnrollmentTypeResolver> lazy, @Nullable UVRVendorSettings uVRVendorSettings) {
        this.mContext = context;
        this.mAMPDInformationProvider = aMPDInformationProvider;
        this.mHandsFreeUserIdentityProvider = handsFreeUserIdentityProvider;
        this.mEnrollmentTypeResolverLazy = lazy;
        this.mUVRVendorSettings = uVRVendorSettings;
    }

    public static synchronized ReactMethodsAMPDInfoAdapter getInstance(@NonNull Context context) {
        ReactMethodsAMPDInfoAdapter reactMethodsAMPDInfoAdapter;
        synchronized (ReactMethodsAMPDInfoAdapter.class) {
            if (mInstance == null) {
                mInstance = new ReactMethodsAMPDInfoAdapter(context, AMPDInformationProvider.getInstance(context), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).handsFreeUserIdentityProvider(), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).enrollmentTypeResolverLazy(), UVRModule.INSTANCE.isUVRAvailable() ? UVRModule.INSTANCE.getUVRContract().getVendorSettings() : null);
            }
            reactMethodsAMPDInfoAdapter = mInstance;
        }
        return reactMethodsAMPDInfoAdapter;
    }

    @NonNull
    public WritableMap getDevicePartnerInfo() {
        WritableMap createMap = Arguments.createMap();
        DefaultPreloadAttributionManager preloadAttributionManager = getPreloadAttributionManager(this.mContext);
        createMap.putBoolean(BridgeKeyConstants.IS_AMPD.toString(), this.mAMPDInformationProvider.isAMPDDevice());
        createMap.putBoolean(BridgeKeyConstants.SHOW_DEVICE_HF_SETTINGS.toString(), shouldShowDeviceHandsFreeSettings());
        createMap.putBoolean(BridgeKeyConstants.IS_1PSV.toString(), isEdgeSV());
        createMap.putBoolean(BridgeKeyConstants.IS_UVR_ENROLLED.toString(), isUVREnrolled());
        createMap.putString(BridgeKeyConstants.ATTRIBUTION_TAG.toString(), preloadAttributionManager.getAttributionTag());
        return createMap;
    }

    @VisibleForTesting
    DefaultPreloadAttributionManager getPreloadAttributionManager(Context context) {
        final FeatureQuery featureQuery = new DefaultFeatureFlagConsumer(context).getFeatureQuery();
        return new DefaultPreloadAttributionManager(context, null, new FeatureQueryBridge() { // from class: com.amazon.alexa.voice.handsfree.reactnative.ReactMethodsAMPDInfoAdapter.1
            @Override // com.amazon.alexa.preload.attribution.FeatureQueryBridge
            public boolean isActive(String str) {
                return featureQuery.isActive(str);
            }
        });
    }

    @VisibleForTesting
    boolean isEdgeSV() {
        VoxEnrollmentTypeResolver voxEnrollmentTypeResolver = (VoxEnrollmentTypeResolver) this.mEnrollmentTypeResolverLazy.mo358get();
        return voxEnrollmentTypeResolver.getSpeakerVerificationEnrollmentType() == EnrollmentType._1PSV && voxEnrollmentTypeResolver.getEnrollmentType() != EnrollmentType._1PSV_DECOUPLED;
    }

    @VisibleForTesting
    boolean isHandsFreeSettingsCompatibleAmokDevice() {
        VoiceAppInfo voiceAppInfo;
        String str = this.mAMPDInformationProvider.getDeviceInformation().get("manufacturer");
        if (str == null || (voiceAppInfo = VoiceAppInfo.getVoiceAppInfo(str)) == null) {
            return false;
        }
        return voiceAppInfo.shouldPromptCustomerDownload() || voiceAppInfo.isInstalledAndSupportsHandsFreeSetting(this.mContext.getPackageManager());
    }

    @VisibleForTesting
    boolean isUVREnrolled() {
        UVRVendorSettings uVRVendorSettings = this.mUVRVendorSettings;
        return uVRVendorSettings != null && uVRVendorSettings.isUVREnrolled(UserInfo.DEFAULT_USER);
    }

    @VisibleForTesting
    boolean shouldShowDeviceHandsFreeSettings() {
        return weblabDialedUp() && (this.mAMPDInformationProvider.isTrueTurnKey() || isHandsFreeSettingsCompatibleAmokDevice());
    }

    @VisibleForTesting
    boolean weblabDialedUp() {
        HandsFreeUserIdentity handsFreeUserIdentity = this.mHandsFreeUserIdentityProvider.getHandsFreeUserIdentity();
        if (handsFreeUserIdentity == null) {
            return false;
        }
        return handsFreeUserIdentity.hasComponent(HandsFreeComponent.VOX_APP_HANDS_FREE_SETTING);
    }
}
