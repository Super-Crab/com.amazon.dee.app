package com.amazon.alexa.voice.handsfree.features;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.devices.DeviceInformation;
import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import com.amazon.alexa.handsfree.devices.constants.VoiceApp;
import com.amazon.alexa.handsfree.devices.features.HandsFreeFeature;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeComponent;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentity;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentityProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.UVRModule;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRConnector;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRContract;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentStatus;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentStatusManager;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentType;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.handsfree.uservoicerecognition.edgesv.EdgeSVUVRModule;
import com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapper;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class VoxEnrollmentTypeResolver extends EnrollmentTypeResolver implements HandsFreeUserIdentity.Listener, EnrollmentStatusManager.StatusListener {
    private static final String TAG = "VoxEnrollmentTypeResolver";
    private final AMPDInformationProvider mAMPDInformationProvider;
    private final Context mContext;
    private final EdgeSVUVRModule mEdgeSVUVRModule;
    private final EnrollmentStatusManager mEnrollmentStatusManager;
    private final HandsFreeUserIdentityProvider mHandsFreeUserIdentityProvider;
    private final IdentityServiceProvider mIdentityServiceProvider;
    private final VendorAPIWrapper mVendorAPIWrapper;

    public VoxEnrollmentTypeResolver(@NonNull HandsFreeUserIdentityProvider handsFreeUserIdentityProvider, @NonNull Context context, @NonNull EdgeSVUVRModule edgeSVUVRModule, @Nullable VendorAPIWrapper vendorAPIWrapper, @NonNull EnrollmentStatusManager enrollmentStatusManager, @NonNull IdentityServiceProvider identityServiceProvider, @NonNull AMPDInformationProvider aMPDInformationProvider) {
        super(context, enrollmentStatusManager);
        this.mHandsFreeUserIdentityProvider = handsFreeUserIdentityProvider;
        this.mContext = context;
        this.mEdgeSVUVRModule = edgeSVUVRModule;
        this.mVendorAPIWrapper = vendorAPIWrapper;
        this.mEnrollmentStatusManager = enrollmentStatusManager;
        this.mIdentityServiceProvider = identityServiceProvider;
        this.mAMPDInformationProvider = aMPDInformationProvider;
    }

    private void check3PSVEnrollmentStatus() {
        if (this.mVendorAPIWrapper == null || this.mEnrollmentStatusManager.getEnrollmentStatus() != EnrollmentStatus.SETUP_NOT_SET) {
            return;
        }
        UVRConnector uVRConnector = this.mVendorAPIWrapper.getUVRContract(getContext()).getUVRConnector();
        uVRConnector.startConnection(getContext(), false);
        boolean isUVREnrolled = this.mVendorAPIWrapper.getUVRContract(getContext()).getVendorSettings().isUVREnrolled(UserInfo.DEFAULT_USER);
        uVRConnector.endConnection(getContext());
        if (!isUVREnrolled) {
            return;
        }
        this.mEnrollmentStatusManager.setEnrollmentStatus(EnrollmentStatus.SETUP_IN_3PSV);
        onHandsFreeUserIdentityChanged(this.mHandsFreeUserIdentityProvider.getHandsFreeUserIdentity());
    }

    private void startListeningForEnrollmentStatusChanges() {
        this.mEnrollmentStatusManager.addStatusListener(this);
    }

    private void startListeningForIdentityChanges() {
        this.mHandsFreeUserIdentityProvider.addListener(this);
    }

    private void updateUVRModule() {
        if (getSpeakerVerificationEnrollmentType() == EnrollmentType._1PSV) {
            Log.d(TAG, "Setting 1PSV UVRContract");
            UVRModule.INSTANCE.setUVRContract(this.mEdgeSVUVRModule.getUVRContract(getContext()));
            return;
        }
        Log.d(TAG, "Setting 3PSV UVRContract");
        UVRModule uVRModule = UVRModule.INSTANCE;
        VendorAPIWrapper vendorAPIWrapper = this.mVendorAPIWrapper;
        uVRModule.setUVRContract(vendorAPIWrapper == null ? null : vendorAPIWrapper.getUVRContract(getContext()));
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

    public boolean getSignInState() {
        IdentityService provideIdentityService = this.mIdentityServiceProvider.provideIdentityService();
        if (provideIdentityService == null) {
            Log.w(TAG, "isAlexaAppSignedIn: identity service not available.");
            return false;
        }
        UserIdentity user = provideIdentityService.getUser(TAG);
        return user != null && user.hasAcceptedEula() && provideIdentityService.isAuthenticated(TAG);
    }

    public UVRContract getUVRContract(@NonNull VendorAPIWrapper vendorAPIWrapper) {
        if (getSpeakerVerificationEnrollmentType() == EnrollmentType._1PSV) {
            return this.mEdgeSVUVRModule.getUVRContract(getContext());
        }
        return vendorAPIWrapper.getUVRContract(getContext());
    }

    public void initialize() {
        startListeningForIdentityChanges();
        startListeningForEnrollmentStatusChanges();
        check3PSVEnrollmentStatus();
        check1PSVDecoupledState();
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver
    protected boolean isComponentEnabled(@NonNull HandsFreeComponent handsFreeComponent) {
        HandsFreeUserIdentity handsFreeUserIdentity = this.mHandsFreeUserIdentityProvider.getHandsFreeUserIdentity();
        boolean z = handsFreeUserIdentity != null && handsFreeUserIdentity.hasComponent(handsFreeComponent) && getSignInState();
        Log.d(TAG, String.format("User %s has %s weblab enabled? : %s", handsFreeUserIdentity, handsFreeComponent.name(), Boolean.valueOf(z)));
        return z;
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

    @Override // com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentity.Listener
    public void onHandsFreeUserIdentityChanged(@Nullable HandsFreeUserIdentity handsFreeUserIdentity) {
        Log.d(TAG, "onHandsFreeUserIdentityChanged");
        updateUVRModule();
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentStatusManager.StatusListener
    public void onStatusChanged(EnrollmentStatus enrollmentStatus) {
        Log.d(TAG, "onStatusChanged");
        updateUVRModule();
    }
}
