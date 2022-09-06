package com.amazon.alexa.handsfree.settings.handsfreesettings.voiceprofile;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.callback.CallbackErrorMetadata;
import com.amazon.alexa.handsfree.protocols.callback.ResponseCallback;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.UVRModule;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRVendorSettings;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentStatus;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentStatusManager;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.model.UserInfo;
import com.amazon.alexa.handsfree.settings.contract.handsfreesettings.voiceprofile.VoiceProfileSettingContract;
import dagger.Lazy;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes8.dex */
public class VoiceProfileSettingModel {
    private final EnrollmentStatusManager mEnrollmentStatusManager;
    private final Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;
    private final UVRVendorSettings mUVRVendorSettings;
    private final VoiceProfileSettingContract.Listener mVoiceProfileSettingListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    public VoiceProfileSettingModel(@NonNull VoiceProfileSettingContract.Listener listener, @NonNull Context context) {
        this(UVRModule.INSTANCE.getUVRContract().getVendorSettings(), listener, EnrollmentStatusManager.getInstance(context), ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).enrollmentTypeResolverLazy());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void deleteVoiceModel() {
        this.mUVRVendorSettings.removeUVRModel(UserInfo.DEFAULT_USER, new ResponseCallback() { // from class: com.amazon.alexa.handsfree.settings.handsfreesettings.voiceprofile.VoiceProfileSettingModel.1
            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onError(@NonNull CallbackErrorMetadata callbackErrorMetadata) {
                VoiceProfileSettingModel.this.mVoiceProfileSettingListener.onProfileDeletedWithError();
            }

            @Override // com.amazon.alexa.handsfree.protocols.callback.ResponseCallback
            public void onSuccess() {
                VoiceProfileSettingModel.this.mVoiceProfileSettingListener.onProfileDeletedSuccessfully();
            }
        });
        updateUvrEnrollmentStatusProviderToNotSetup();
        update1PSVDecouplingState();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isUVREnrolled() {
        return this.mUVRVendorSettings.isUVREnrolled(UserInfo.DEFAULT_USER);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isUVRMandatory() {
        return this.mUVRVendorSettings.isUVRMandatory();
    }

    @VisibleForTesting
    void update1PSVDecouplingState() {
        EnrollmentTypeResolver mo358get = this.mEnrollmentTypeResolverLazy.mo358get();
        if (mo358get != null) {
            mo358get.check1PSVDecoupledState();
        }
    }

    @VisibleForTesting
    void updateUvrEnrollmentStatusProviderToNotSetup() {
        this.mEnrollmentStatusManager.setEnrollmentStatus(EnrollmentStatus.SETUP_NOT_SET);
    }

    @VisibleForTesting
    VoiceProfileSettingModel(@NonNull UVRVendorSettings uVRVendorSettings, @NonNull VoiceProfileSettingContract.Listener listener, @NonNull EnrollmentStatusManager enrollmentStatusManager, @NonNull Lazy<EnrollmentTypeResolver> lazy) {
        this.mUVRVendorSettings = uVRVendorSettings;
        this.mVoiceProfileSettingListener = listener;
        this.mEnrollmentStatusManager = enrollmentStatusManager;
        this.mEnrollmentTypeResolverLazy = lazy;
    }
}
