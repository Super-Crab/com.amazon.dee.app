package com.amazon.alexa.biloba.storage;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.alexa.biloba.dependency.BilobaScope;
import com.amazon.alexa.biloba.membership.PermissionConstants;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
@BilobaScope
/* loaded from: classes6.dex */
public class CareActorsStore {
    private static final String TAG = "CareActorsStore";
    private Lazy<CareActorsStoreV1> careActorsStoreV1;
    private Lazy<CareActorsStoreV2> careActorsStoreV2;
    private FeatureServiceV2 featureServiceV2;

    @Inject
    @VisibleForTesting
    public CareActorsStore(Lazy<CareActorsStoreV1> lazy, Lazy<CareActorsStoreV2> lazy2, FeatureServiceV2 featureServiceV2) {
        this.careActorsStoreV1 = lazy;
        this.careActorsStoreV2 = lazy2;
        this.featureServiceV2 = featureServiceV2;
    }

    public void clear() {
        if (isMultiCGEnabled()) {
            this.careActorsStoreV2.mo358get().clear();
        } else {
            this.careActorsStoreV1.mo358get().clear();
        }
    }

    public LiveData<CareActor> getCareContact() {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().getCareContact();
        }
        return this.careActorsStoreV1.mo358get().getCareContact();
    }

    public String getCareContactCommsId() {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().getCareContactCommsId();
        }
        return this.careActorsStoreV1.mo358get().getCareContactCommsId();
    }

    public LiveData<CareActor> getCareGiver() {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().getCareGiver();
        }
        return this.careActorsStoreV1.mo358get().getCareGiver();
    }

    public LiveData<CareActor> getCareGiverAdmin() {
        return isMultiCGEnabled() ? this.careActorsStoreV2.mo358get().getCareGiverAdmin() : new MutableLiveData();
    }

    public LiveData<CareActor> getCareRecipient() {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().getCareRecipient();
        }
        return this.careActorsStoreV1.mo358get().getCareRecipient();
    }

    public LiveData<CareActor> getCurrentActor() {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().getCurrentActor();
        }
        return this.careActorsStoreV1.mo358get().getCurrentActor();
    }

    public String getCurrentGroupId() {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().getCurrentGroupId();
        }
        return this.careActorsStoreV1.mo358get().getCurrentGroupId();
    }

    public LiveData<Throwable> getError() {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().getError();
        }
        return this.careActorsStoreV1.mo358get().getError();
    }

    public LiveData<Boolean> getHasSubscription() {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().getHasSubscription();
        }
        return this.careActorsStoreV1.mo358get().getHasSubscription();
    }

    public LiveData<Boolean> getIsCareGiver() {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().getIsCareGiver();
        }
        return this.careActorsStoreV1.mo358get().getIsCareGiver();
    }

    public LiveData<Boolean> getIsLoading() {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().getIsLoading();
        }
        return this.careActorsStoreV1.mo358get().getIsLoading();
    }

    public LiveData<Boolean> getIsPayer() {
        return isMultiCGEnabled() ? this.careActorsStoreV2.mo358get().getIsPayer() : new MutableLiveData();
    }

    public LiveData<Boolean> getLeaveGroupSuccess() {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().getLeaveGroupSuccess();
        }
        return this.careActorsStoreV1.mo358get().getLeaveGroupSuccess();
    }

    public String getRemoteManagementRoutingContext() {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().getRemoteManagementRoutingContext();
        }
        return this.careActorsStoreV1.mo358get().getRemoteManagementRoutingContext();
    }

    public LiveData<String> getRole() {
        return isMultiCGEnabled() ? this.careActorsStoreV2.mo358get().getRole() : new MutableLiveData();
    }

    public String getSlicedCareContactContactId() {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().getSlicedCareContactContactId();
        }
        return this.careActorsStoreV1.mo358get().getSlicedCareContactContactId();
    }

    public String getSlicedCurrentActorContactId() {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().getSlicedCurrentActorContactId();
        }
        return this.careActorsStoreV1.mo358get().getSlicedCurrentActorContactId();
    }

    public String getSubscriptionId() {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().getSubscriptionId();
        }
        return this.careActorsStoreV1.mo358get().getSubscriptionId();
    }

    public boolean hasActiveSubscription() {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().hasActiveSubscription();
        }
        return this.careActorsStoreV1.mo358get().hasActiveSubscription();
    }

    public boolean hasSubscription(String str) {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().hasSubscription(str);
        }
        return this.careActorsStoreV1.mo358get().hasSubscription(str);
    }

    public boolean isCommsEnabledForActors() {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().isCommsEnabledForActors();
        }
        return this.careActorsStoreV1.mo358get().isCommsEnabledForActors();
    }

    public boolean isCommsEnabledForCurrentActor() {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().isCommsEnabledForCurrentActor();
        }
        return this.careActorsStoreV1.mo358get().isCommsEnabledForCurrentActor();
    }

    public boolean isCommsProvisionedWithCommsId() {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().isCommsProvisionedWithCommsId();
        }
        return this.careActorsStoreV1.mo358get().isCommsProvisionedWithCommsId();
    }

    public boolean isCommsProvisionedWithContactId() {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().isCommsProvisionedWithContactId();
        }
        return this.careActorsStoreV1.mo358get().isCommsProvisionedWithContactId();
    }

    public boolean isDropInEnabledForActors() {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().isDropInEnabledForActors();
        }
        return this.careActorsStoreV1.mo358get().isDropInEnabledForActors();
    }

    public boolean isDropInEnabledForCareContact() {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().isDropInEnabledForCareContact();
        }
        return this.careActorsStoreV1.mo358get().isDropInEnabledForCareContact();
    }

    public boolean isMultiCGEnabled() {
        FeatureServiceV2 featureServiceV2 = this.featureServiceV2;
        return featureServiceV2 != null && featureServiceV2.hasAccess("ALEXA_AGING_MULTI_CG_MOBILE", false);
    }

    public boolean isPermissionEnabled(@PermissionConstants.Permissions String str) {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().isPermissionEnabled(str);
        }
        return this.careActorsStoreV1.mo358get().isPermissionEnabled(str);
    }

    public boolean isRemoteManagementPermissionEnabled() {
        if (isMultiCGEnabled()) {
            return this.careActorsStoreV2.mo358get().isPermissionEnabled(PermissionConstants.PERFORM_REMOTE_MANAGEMENT);
        }
        return this.careActorsStoreV1.mo358get().isPermissionEnabled(PermissionConstants.REMOTE_MANAGEMENT);
    }

    public void leaveGroupAsync() {
        if (isMultiCGEnabled()) {
            this.careActorsStoreV2.mo358get().leaveGroupAsync();
        } else {
            this.careActorsStoreV1.mo358get().leaveGroupAsync();
        }
    }

    public void requestCareActors() {
        if (isMultiCGEnabled()) {
            this.careActorsStoreV2.mo358get().requestCareActors();
        } else {
            this.careActorsStoreV1.mo358get().requestCareActors();
        }
    }

    public void setCurrentGroupId(String str) {
        if (isMultiCGEnabled()) {
            this.careActorsStoreV2.mo358get().setCurrentGroupId(str);
        } else {
            this.careActorsStoreV1.mo358get().setCurrentGroupId(str);
        }
    }

    public void setIsLoading(Boolean bool) {
        if (isMultiCGEnabled()) {
            this.careActorsStoreV2.mo358get().setIsLoading(bool);
        } else {
            this.careActorsStoreV1.mo358get().setIsLoading(bool);
        }
    }
}
