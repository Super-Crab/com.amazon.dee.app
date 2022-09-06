package com.amazon.alexa.biloba.storage;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.alexa.biloba.dependency.BilobaScope;
import com.amazon.alexa.biloba.generated.models.PermissionGroup;
import com.amazon.alexa.biloba.generated.network.api.GroupApi;
import com.amazon.alexa.biloba.membership.PermissionConstants;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.biloba.model.ListGroupsResponse;
import com.amazon.alexa.biloba.model.RemoteManagementRoutingContext;
import com.amazon.alexa.biloba.model.ResourceOwner;
import com.amazon.alexa.biloba.utils.CareActorUtil;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.google.gson.Gson;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.functions.Action1;
@Singleton
@BilobaScope
/* loaded from: classes6.dex */
public class CareActorsStoreV1 extends DataStore {
    private static final String PERSON_ID = "PERSON_ID";
    private static final String TAG = "CareActorsStoreV1";
    @Inject
    Lazy<BilobaMetricsService> bilobaMetricsService;
    private Map<String, CareActor> careActors;
    private MutableLiveData<CareActor> careContact;
    private MutableLiveData<CareActor> careGiver;
    private List<CareActor> careGivers;
    private MutableLiveData<CareActor> careRecipient;
    private List<CareActor> careRecipients;
    @Inject
    Lazy<CrashMetadata> crashMetadata;
    @Inject
    Lazy<CrashReporter> crashReporter;
    private MutableLiveData<CareActor> currentActor;
    private String currentGroupId;
    @Inject
    Lazy<GroupApi> groupApi;
    @Inject
    Lazy<Gson> gson;
    private MutableLiveData<Boolean> hasSubscription;
    private MutableLiveData<Boolean> isCareGiver;
    private final MutableLiveData<Boolean> leaveGroupSuccess;
    private String remoteManagementRoutingContext;
    private String subscriptionId;
    private MutableLiveData<String> subscriptionStatus;

    /* loaded from: classes6.dex */
    public static final class SubscriptionStatus {
        public static final String ACTIVE = "ACTIVE";
        public static final String PAUSED = "PAUSED";

        private SubscriptionStatus() {
        }
    }

    @Inject
    public CareActorsStoreV1() {
        this.currentActor = new MutableLiveData<>();
        this.careGiver = new MutableLiveData<>();
        this.careRecipient = new MutableLiveData<>();
        this.careContact = new MutableLiveData<>();
        this.isCareGiver = new MutableLiveData<>();
        this.hasSubscription = new MutableLiveData<>();
        this.subscriptionStatus = new MutableLiveData<>();
        this.leaveGroupSuccess = new MutableLiveData<>();
        this.careActors = new HashMap();
        this.careGivers = new ArrayList();
        this.careRecipients = new ArrayList();
    }

    private String constructRemoteManagementRoutingContext(CareActor careActor) {
        String str;
        if (careActor != null) {
            str = this.gson.mo358get().toJson(new RemoteManagementRoutingContext(new ResourceOwner(careActor.getDirectedPersonIdV2(), "PERSON_ID", careActor.getFirstName(), careActor.getLastName(), careActor.getPersonIdV1()), "biloba", "biloba"));
        } else {
            str = null;
        }
        String str2 = TAG;
        LogUtils.d(str2, "RM routing context: " + str);
        return str;
    }

    @Override // com.amazon.alexa.biloba.storage.DataStore
    public void clear() {
        super.clear();
        this.careActors.clear();
        this.careGivers.clear();
        this.careRecipients.clear();
        setCurrentGroupId(null);
        this.currentActor = new MutableLiveData<>();
        this.careGiver = new MutableLiveData<>();
        this.careRecipient = new MutableLiveData<>();
        this.careContact = new MutableLiveData<>();
        this.isCareGiver = new MutableLiveData<>();
        this.hasSubscription = new MutableLiveData<>();
        this.subscriptionStatus = new MutableLiveData<>();
        this.bilobaMetricsService.mo358get().updateRoleSuffix(null);
        this.subscriptionId = null;
    }

    public LiveData<CareActor> getCareContact() {
        return this.careContact;
    }

    public String getCareContactCommsId() {
        CareActor value = getCareContact().getValue();
        return value != null ? value.getCommsId() : "";
    }

    public LiveData<CareActor> getCareGiver() {
        return this.careGiver;
    }

    public LiveData<CareActor> getCareRecipient() {
        return this.careRecipient;
    }

    public LiveData<CareActor> getCurrentActor() {
        return this.currentActor;
    }

    public String getCurrentGroupId() {
        return this.currentGroupId;
    }

    public LiveData<Boolean> getHasSubscription() {
        return this.hasSubscription;
    }

    public LiveData<Boolean> getIsCareGiver() {
        return this.isCareGiver;
    }

    public LiveData<Boolean> getLeaveGroupSuccess() {
        return this.leaveGroupSuccess;
    }

    public String getRemoteManagementRoutingContext() {
        return this.remoteManagementRoutingContext;
    }

    public String getSlicedCareContactContactId() {
        return CareActorUtil.getSlicedContactId(getCareContact().getValue());
    }

    public String getSlicedCurrentActorContactId() {
        return CareActorUtil.getSlicedContactId(getCurrentActor().getValue());
    }

    public String getSubscriptionId() {
        return this.subscriptionId;
    }

    synchronized void handleListGroupsResponse(ListGroupsResponse listGroupsResponse) {
        CareActor careActor;
        CareActor careActor2;
        this.careActors.clear();
        this.careGivers.clear();
        this.careRecipients.clear();
        if (listGroupsResponse != null) {
            if (listGroupsResponse.getCareGivers() == null || listGroupsResponse.getCareGivers().size() <= 0) {
                careActor = null;
            } else {
                for (CareActor careActor3 : listGroupsResponse.getCareGivers()) {
                    this.careActors.put(careActor3.getPersonIdV1(), careActor3);
                    this.careGivers.add(careActor3);
                }
                careActor = listGroupsResponse.getCareGivers().get(0);
            }
            if (listGroupsResponse.getCareRecipients() == null || listGroupsResponse.getCareRecipients().size() <= 0) {
                careActor2 = null;
            } else {
                for (CareActor careActor4 : listGroupsResponse.getCareRecipients()) {
                    this.careActors.put(careActor4.getPersonIdV1(), careActor4);
                    this.careRecipients.add(careActor4);
                }
                careActor2 = listGroupsResponse.getCareRecipients().get(0);
            }
            if (listGroupsResponse.getCurrentActor() != null) {
                this.careActors.put(listGroupsResponse.getCurrentActor().getPersonIdV1(), listGroupsResponse.getCurrentActor());
                this.subscriptionId = listGroupsResponse.getCurrentActor().getSubscriptionId();
                this.subscriptionStatus.setValue(listGroupsResponse.getCurrentActor().getSubscriptionStatus());
                this.hasSubscription.setValue(Boolean.valueOf(hasSubscription(this.subscriptionStatus.getValue())));
            }
            if (careActor2 != null) {
                this.currentGroupId = careActor2.getGroupId();
                this.careContact.setValue(careActor2);
                this.careGiver.setValue(listGroupsResponse.getCurrentActor());
                this.careRecipient.setValue(careActor2);
                this.isCareGiver.setValue(true);
                this.remoteManagementRoutingContext = constructRemoteManagementRoutingContext(this.careContact.getValue());
                this.bilobaMetricsService.mo358get().updateRoleSuffix(MetricsConstants.CARE_GIVER);
            } else if (careActor != null) {
                this.currentGroupId = careActor.getGroupId();
                this.careContact.setValue(careActor);
                this.careGiver.setValue(careActor);
                this.careRecipient.setValue(listGroupsResponse.getCurrentActor());
                this.isCareGiver.setValue(false);
                this.bilobaMetricsService.mo358get().updateRoleSuffix(MetricsConstants.CARE_RECEIVER);
            } else {
                this.currentGroupId = null;
                this.careContact.setValue(null);
                this.careGiver.setValue(null);
                this.careRecipient.setValue(null);
                this.isCareGiver.setValue(null);
                this.bilobaMetricsService.mo358get().updateRoleSuffix(null);
                this.remoteManagementRoutingContext = null;
            }
            this.currentActor.setValue(listGroupsResponse.getCurrentActor());
        } else {
            this.currentGroupId = null;
            this.subscriptionId = null;
            this.careContact.setValue(null);
            this.careGiver.setValue(null);
            this.careRecipient.setValue(null);
            this.currentActor.setValue(null);
            this.subscriptionStatus.setValue(null);
            this.bilobaMetricsService.mo358get().updateRoleSuffix(null);
            this.remoteManagementRoutingContext = null;
        }
    }

    public boolean hasActiveSubscription() {
        MutableLiveData<String> mutableLiveData = this.subscriptionStatus;
        return (mutableLiveData == null || mutableLiveData.getValue() == null || !this.subscriptionStatus.getValue().equals("ACTIVE")) ? false : true;
    }

    public boolean hasSubscription(String str) {
        return str != null && (str.equals("ACTIVE") || str.equals("PAUSED"));
    }

    public boolean isCommsEnabledForActors() {
        CareActor value = getCareContact().getValue();
        CareActor value2 = getCurrentActor().getValue();
        if (value2 == null || value == null) {
            return Boolean.FALSE.booleanValue();
        }
        return value2.getIsCommsProvisioned() == Boolean.TRUE && value.getIsCommsProvisioned() == Boolean.TRUE;
    }

    public boolean isCommsEnabledForCurrentActor() {
        CareActor value = getCurrentActor().getValue();
        return value != null && value.getIsCommsProvisioned() == Boolean.TRUE;
    }

    public boolean isCommsProvisionedWithCommsId() {
        CareActor value = getCareContact().getValue();
        return (isCommsEnabledForActors() != Boolean.TRUE.booleanValue() || value == null || value.getCommsId() == null) ? false : true;
    }

    public boolean isCommsProvisionedWithContactId() {
        CareActor value = getCareContact().getValue();
        return (isCommsEnabledForActors() != Boolean.TRUE.booleanValue() || value == null || value.getContactId() == null) ? false : true;
    }

    public boolean isDropInEnabledForActors() {
        CareActor value = getCareContact().getValue();
        CareActor value2 = getCurrentActor().getValue();
        if (value2 == null || value == null) {
            return Boolean.FALSE.booleanValue();
        }
        return value2.getIsDropInEnabled() == Boolean.TRUE && value.getIsDropInEnabled() == Boolean.TRUE;
    }

    public boolean isDropInEnabledForCareContact() {
        CareActor value = getCareContact().getValue();
        return value != null && value.getIsDropInEnabled() == Boolean.TRUE;
    }

    public boolean isPermissionEnabled(@PermissionConstants.Permissions String str) {
        List<PermissionGroup> permissionGroups;
        if (this.currentActor.getValue() == null || (permissionGroups = this.currentActor.getValue().getPermissionGroups()) == null) {
            return false;
        }
        for (PermissionGroup permissionGroup : permissionGroups) {
            if (permissionGroup.getPermissions().contains(str)) {
                return true;
            }
        }
        return false;
    }

    public /* synthetic */ void lambda$leaveGroupAsync$2$CareActorsStoreV1(MobilyticsMetricsTimer mobilyticsMetricsTimer, Void r4) {
        this.crashMetadata.mo358get().put("leave_group_success", true);
        requestCareActors();
        postError(null);
        setIsLoading(false);
        this.leaveGroupSuccess.setValue(true);
        this.bilobaMetricsService.mo358get().finishTimer(mobilyticsMetricsTimer);
        this.bilobaMetricsService.mo358get().recordOccurrence("leaveRelationshipApi.Success", true);
    }

    public /* synthetic */ void lambda$leaveGroupAsync$3$CareActorsStoreV1(Throwable th) {
        LogUtils.e(TAG, th.getMessage());
        this.crashMetadata.mo358get().put("leave_group_success", false);
        this.crashReporter.mo358get().reportNonFatal(th);
        postError(th);
        setIsLoading(false);
        this.leaveGroupSuccess.setValue(false);
        this.bilobaMetricsService.mo358get().recordOccurrence("leaveRelationshipApi.Success", false);
    }

    public /* synthetic */ void lambda$requestCareActors$0$CareActorsStoreV1(MobilyticsMetricsTimer mobilyticsMetricsTimer, ListGroupsResponse listGroupsResponse) {
        this.crashMetadata.mo358get().put("list_groups_success", true);
        this.crashMetadata.mo358get().put("list_groups_care_givers_size", listGroupsResponse.getCareGivers() != null ? listGroupsResponse.getCareGivers().size() : 0);
        this.crashMetadata.mo358get().put("list_groups_care_recepients_size", listGroupsResponse.getCareRecipients() != null ? listGroupsResponse.getCareRecipients().size() : 0);
        handleListGroupsResponse(listGroupsResponse);
        postError(null);
        setIsLoading(false);
        this.bilobaMetricsService.mo358get().finishTimer(mobilyticsMetricsTimer);
        this.bilobaMetricsService.mo358get().recordOccurrence("getGroupsApi.Success", true);
    }

    public /* synthetic */ void lambda$requestCareActors$1$CareActorsStoreV1(Throwable th) {
        LogUtils.e(TAG, th.getMessage());
        this.crashMetadata.mo358get().put("list_groups_success", false);
        this.crashReporter.mo358get().reportNonFatal(th);
        postError(th);
        setIsLoading(false);
        this.bilobaMetricsService.mo358get().recordOccurrence("getGroupsApi.Success", false);
    }

    public void leaveGroupAsync() {
        setIsLoading(true);
        CareActor value = getCareContact().getValue();
        this.leaveGroupSuccess.setValue(null);
        final MobilyticsMetricsTimer startTimer = this.bilobaMetricsService.mo358get().startTimer("leaveRelationshipApi.Time");
        if (value != null) {
            this.groupApi.mo358get().leaveGroupObservable(value.getGroupId()).subscribe(new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$CareActorsStoreV1$tjTiWi23qGWtXpbs45TsJhfuyG4
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CareActorsStoreV1.this.lambda$leaveGroupAsync$2$CareActorsStoreV1(startTimer, (Void) obj);
                }
            }, new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$CareActorsStoreV1$bZcCx9euX87EEYUk145Dafs8CDk
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CareActorsStoreV1.this.lambda$leaveGroupAsync$3$CareActorsStoreV1((Throwable) obj);
                }
            });
        }
    }

    public void requestCareActors() {
        setIsLoading(true);
        LogUtils.i(TAG, "request for care actors");
        final MobilyticsMetricsTimer startTimer = this.bilobaMetricsService.mo358get().startTimer("getGroupsApi.Time");
        this.groupApi.mo358get().listGroupsObservable().subscribe(new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$CareActorsStoreV1$h82XeDezgNIbBKvgc64UN8Tv-9U
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CareActorsStoreV1.this.lambda$requestCareActors$0$CareActorsStoreV1(startTimer, (ListGroupsResponse) obj);
            }
        }, new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$CareActorsStoreV1$PmA7DpBOur-Cq7MmZnNMIWzcQo0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CareActorsStoreV1.this.lambda$requestCareActors$1$CareActorsStoreV1((Throwable) obj);
            }
        });
    }

    public void setCurrentGroupId(String str) {
        this.currentGroupId = str;
    }

    @VisibleForTesting
    public CareActorsStoreV1(Lazy<GroupApi> lazy, Lazy<CrashReporter> lazy2, Lazy<CrashMetadata> lazy3, Lazy<BilobaMetricsService> lazy4, Lazy<Gson> lazy5) {
        this();
        this.groupApi = lazy;
        this.crashReporter = lazy2;
        this.crashMetadata = lazy3;
        this.bilobaMetricsService = lazy4;
        this.gson = lazy5;
    }
}
