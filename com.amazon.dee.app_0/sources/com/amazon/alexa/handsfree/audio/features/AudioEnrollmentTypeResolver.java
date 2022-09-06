package com.amazon.alexa.handsfree.audio.features;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.devices.DeviceInformation;
import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import com.amazon.alexa.handsfree.devices.constants.VoiceApp;
import com.amazon.alexa.handsfree.devices.features.HandsFreeFeature;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentStatus;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentStatusManager;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes8.dex */
public class AudioEnrollmentTypeResolver extends EnrollmentTypeResolver {
    private static final String TAG = "AudioEnrollmentTypeResolver";
    private final AMPDInformationProvider mAMPDInformationProvider;
    private final ComponentEnablementResolver mComponentEnablementResolver;
    private final Context mContext;
    private final EnrollmentStatusManager mEnrollmentStatusManager;

    public AudioEnrollmentTypeResolver(Context context) {
        this(context, EnrollmentStatusManager.getInstance(context), new ComponentEnablementResolver(context), AMPDInformationProvider.getInstance(context));
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver
    public void check1PSVDecoupledState() {
        if (this.mEnrollmentStatusManager.getEnrollmentStatus() == EnrollmentStatus.SETUP_NOT_SET) {
            SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(EnrollmentTypeResolver.PREFERENCE_FILE_NAME, 0);
            boolean isComponentEnabled = isComponentEnabled(HandsFreeComponent.EDGESV_DECOUPLING);
            GeneratedOutlineSupport1.outline143(sharedPreferences, EnrollmentTypeResolver.IS_1PSV_DECOUPLING_ENABLED, isComponentEnabled);
            String str = TAG;
            Log.i(str, "check1PSVDecoupledState " + isComponentEnabled);
        }
    }

    @VisibleForTesting
    DeviceInformation getDeviceInformation() {
        return DeviceTypeInformationProvider.getInstance(getContext()).getDeviceInformationForCurrentDevice(getContext());
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver
    public EnrollmentType getNotSetupEnrollmentType() {
        if (this.mAMPDInformationProvider.isEdgeSV() && (isComponentEnabled(HandsFreeComponent.PROFILE_DECOUPLING) || isComponentEnabled(HandsFreeComponent.EDGESV_DECOUPLING))) {
            if (isDevice1psvLaunched(HandsFreeFeature.ALEXA_HANDSFREE_EDGE_SV_QC_GLOBAL) && VoiceApp.QUEBEC.equals(getDeviceInformation().getVoiceApp())) {
                Log.d(TAG, "Device is 1PSV and global QC device weblab is enabled. SV Enrollment type is 1PSV.");
                return EnrollmentType._1PSV;
            } else if (isDevice1psvLaunched(HandsFreeFeature.ALEXA_HANDSFREE_EDGE_SV_MTK_GLOBAL) && VoiceApp.METRO.equals(getDeviceInformation().getVoiceApp())) {
                Log.d(TAG, "Device is 1PSV and global MTK device weblab is enabled. SV Enrollment type is 1PSV.");
                return EnrollmentType._1PSV;
            } else {
                for (HandsFreeFeature handsFreeFeature : HandsFreeFeature.getListOf1psvDeviceSpecificWeblabs()) {
                    if (isDevice1psvLaunched(handsFreeFeature)) {
                        Log.d(TAG, "Device is 1PSV and device weblab is enabled. SV Enrollment type is 1PSV.");
                        return EnrollmentType._1PSV;
                    }
                }
                if (isComponentEnabled(HandsFreeComponent.EDGESV_UVR)) {
                    Log.d(TAG, "Device is 1PSV and Beta weblab is enabled. SV Enrollment type is 1PSV.");
                    return EnrollmentType._1PSV;
                }
                Log.d(TAG, "Device is 1PSV capable but both device weblab and beta weblab is disabled. SV Enrollment type is 3PSV.");
            }
        } else {
            Log.d(TAG, "Device is not 1PSV. SV Enrollment type is 3PSV.");
        }
        return EnrollmentType._3PSV;
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver
    protected boolean isComponentEnabled(@NonNull HandsFreeComponent handsFreeComponent) {
        return this.mComponentEnablementResolver.isComponentEnabled(handsFreeComponent);
    }

    @VisibleForTesting
    boolean isDevice1psvLaunched(HandsFreeFeature handsFreeFeature) {
        if (handsFreeFeature == null || !handsFreeFeature.containsDeviceType(getDeviceInformation().getType())) {
            return false;
        }
        for (HandsFreeComponent handsFreeComponent : handsFreeFeature.getComponentsList()) {
            if (!isComponentEnabled(handsFreeComponent)) {
                return false;
            }
        }
        return true;
    }

    @VisibleForTesting
    AudioEnrollmentTypeResolver(@NonNull Context context, @NonNull EnrollmentStatusManager enrollmentStatusManager, @NonNull ComponentEnablementResolver componentEnablementResolver, @NonNull AMPDInformationProvider aMPDInformationProvider) {
        super(context, enrollmentStatusManager);
        this.mContext = context;
        this.mComponentEnablementResolver = componentEnablementResolver;
        this.mAMPDInformationProvider = aMPDInformationProvider;
        this.mEnrollmentStatusManager = enrollmentStatusManager;
    }
}
