package com.amazon.alexa.biloba.storage;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.alexa.biloba.dependency.BilobaScope;
import com.amazon.alexa.biloba.membership.MembershipConstants;
import com.amazon.alexa.biloba.membership.PermissionConstants;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.biloba.model.ListGroupsResponse;
import com.amazon.alexa.biloba.model.RemoteManagementRoutingContext;
import com.amazon.alexa.biloba.model.ResourceOwner;
import com.amazon.alexa.biloba.network.api.GroupV2Api;
import com.amazon.alexa.biloba.utils.CareActorUtil;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.google.gson.Gson;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.functions.Action1;
@Singleton
@BilobaScope
/* loaded from: classes6.dex */
public class CareActorsStoreV2 extends DataStore {
    private static final String PERSON_ID = "PERSON_ID";
    private static final String TAG = "CareActorsStoreV2";
    @Inject
    Lazy<BilobaMetricsService> bilobaMetricsService;
    private MutableLiveData<CareActor> careContact;
    private MutableLiveData<CareActor> careGiver;
    private MutableLiveData<CareActor> careGiverAdmin;
    private List<CareActor> careGivers;
    private MutableLiveData<CareActor> careRecipient;
    @Inject
    Lazy<CrashMetadata> crashMetadata;
    @Inject
    Lazy<CrashReporter> crashReporter;
    private MutableLiveData<CareActor> currentActor;
    private String currentGroupId;
    @Inject
    Lazy<GroupV2Api> groupApi;
    @Inject
    Lazy<Gson> gson;
    private MutableLiveData<Boolean> hasSubscription;
    private MutableLiveData<Boolean> isCareGiver;
    private MutableLiveData<Boolean> isPayer;
    private final MutableLiveData<Boolean> leaveGroupSuccess;
    private String remoteManagementRoutingContext;
    private MutableLiveData<String> role;
    private MutableLiveData<String> status;
    private String subscriptionId;
    private MutableLiveData<String> subscriptionStatus;

    /* loaded from: classes6.dex */
    public static final class CommsStatus {
        public static final String DISABLED = "DISABLED";
        public static final String ENABLED = "ENABLED";

        private CommsStatus() {
        }
    }

    /* loaded from: classes6.dex */
    public static final class Status {
        public static final String DISABLED = "DISABLED";
        public static final String ENABLED = "ENABLED";

        private Status() {
        }
    }

    /* loaded from: classes6.dex */
    public static final class SubscriptionStatus {
        public static final String ACTIVE = "ACTIVE";
        public static final String PAUSED = "PAUSED";

        private SubscriptionStatus() {
        }
    }

    @Inject
    public CareActorsStoreV2() {
        this.currentActor = new MutableLiveData<>();
        this.careGiver = new MutableLiveData<>();
        this.careGiverAdmin = new MutableLiveData<>();
        this.careRecipient = new MutableLiveData<>();
        this.careContact = new MutableLiveData<>();
        this.isCareGiver = new MutableLiveData<>();
        this.hasSubscription = new MutableLiveData<>();
        this.subscriptionStatus = new MutableLiveData<>();
        this.isPayer = new MutableLiveData<>();
        this.role = new MutableLiveData<>();
        this.status = new MutableLiveData<>();
        this.leaveGroupSuccess = new MutableLiveData<>();
        this.careGivers = new ArrayList();
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
        this.careGivers.clear();
        setCurrentGroupId(null);
        this.currentActor = new MutableLiveData<>();
        this.careGiver = new MutableLiveData<>();
        this.careRecipient = new MutableLiveData<>();
        this.careContact = new MutableLiveData<>();
        this.isCareGiver = new MutableLiveData<>();
        this.careGiverAdmin = new MutableLiveData<>();
        this.role = new MutableLiveData<>();
        this.status = new MutableLiveData<>();
        this.hasSubscription = new MutableLiveData<>();
        this.subscriptionStatus = new MutableLiveData<>();
        this.isPayer = new MutableLiveData<>();
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

    public LiveData<CareActor> getCareGiverAdmin() {
        return this.careGiverAdmin;
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

    public boolean getIsCareGiverAdmin() {
        return MembershipConstants.ADMIN_CG.equals(this.role.getValue());
    }

    public boolean getIsCareGiverVOR() {
        return MembershipConstants.VOR_CG.equals(this.role.getValue());
    }

    public boolean getIsCareRecipient() {
        return MembershipConstants.CR.equals(this.role.getValue());
    }

    public LiveData<Boolean> getIsPayer() {
        return this.isPayer;
    }

    public LiveData<Boolean> getLeaveGroupSuccess() {
        return this.leaveGroupSuccess;
    }

    public String getRemoteManagementRoutingContext() {
        return this.remoteManagementRoutingContext;
    }

    public LiveData<String> getRole() {
        return this.role;
    }

    public String getSlicedCareContactContactId() {
        return CareActorUtil.getSlicedContactId(getCareContact().getValue());
    }

    public String getSlicedCurrentActorContactId() {
        return CareActorUtil.getSlicedContactId(getCurrentActor().getValue());
    }

    public String getStatus() {
        return this.status.getValue();
    }

    public String getSubscriptionId() {
        return this.subscriptionId;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0070 A[Catch: all -> 0x01e5, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0009, B:7:0x0010, B:8:0x0018, B:10:0x001e, B:12:0x0030, B:13:0x0036, B:15:0x0046, B:17:0x0052, B:19:0x0070, B:20:0x00a9, B:22:0x00af, B:24:0x00d5, B:27:0x00e4, B:29:0x00f9, B:32:0x0108, B:34:0x0116, B:35:0x0138, B:36:0x0156, B:40:0x0193, B:41:0x0197, B:42:0x01b4), top: B:48:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00af A[Catch: all -> 0x01e5, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0009, B:7:0x0010, B:8:0x0018, B:10:0x001e, B:12:0x0030, B:13:0x0036, B:15:0x0046, B:17:0x0052, B:19:0x0070, B:20:0x00a9, B:22:0x00af, B:24:0x00d5, B:27:0x00e4, B:29:0x00f9, B:32:0x0108, B:34:0x0116, B:35:0x0138, B:36:0x0156, B:40:0x0193, B:41:0x0197, B:42:0x01b4), top: B:48:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0197 A[Catch: all -> 0x01e5, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x0009, B:7:0x0010, B:8:0x0018, B:10:0x001e, B:12:0x0030, B:13:0x0036, B:15:0x0046, B:17:0x0052, B:19:0x0070, B:20:0x00a9, B:22:0x00af, B:24:0x00d5, B:27:0x00e4, B:29:0x00f9, B:32:0x0108, B:34:0x0116, B:35:0x0138, B:36:0x0156, B:40:0x0193, B:41:0x0197, B:42:0x01b4), top: B:48:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    synchronized void handleListGroupsResponse(com.amazon.alexa.biloba.model.ListGroupsResponse r7) {
        /*
            Method dump skipped, instructions count: 488
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.biloba.storage.CareActorsStoreV2.handleListGroupsResponse(com.amazon.alexa.biloba.model.ListGroupsResponse):void");
    }

    public boolean hasActiveSubscription() {
        return "ACTIVE".equals(this.subscriptionStatus.getValue());
    }

    public boolean hasSubscription(String str) {
        return "ACTIVE".equals(str) || "PAUSED".equals(str);
    }

    public boolean isCommsEnabledForActors() {
        CareActor value = getCareContact().getValue();
        CareActor value2 = getCurrentActor().getValue();
        return value2 != null && value != null && "ENABLED".equals(value.getCommsStatus()) && "ENABLED".equals(value2.getCommsStatus());
    }

    public boolean isCommsEnabledForCurrentActor() {
        CareActor value = getCurrentActor().getValue();
        return value != null && "ENABLED".equals(value.getCommsStatus());
    }

    public boolean isCommsProvisionedWithCommsId() {
        CareActor value = getCareContact().getValue();
        return (!isCommsEnabledForActors() || value == null || value.getCommsId() == null) ? false : true;
    }

    public boolean isCommsProvisionedWithContactId() {
        CareActor value = getCareContact().getValue();
        return (!isCommsEnabledForActors() || value == null || value.getContactId() == null) ? false : true;
    }

    public boolean isDropInEnabledForActors() {
        CareActor value = getCareContact().getValue();
        CareActor value2 = getCurrentActor().getValue();
        return value2 != null && value != null && "ENABLED".equals(value.getDropInStatus()) && "ENABLED".equals(value2.getDropInStatus());
    }

    public boolean isDropInEnabledForCareContact() {
        CareActor value = getCareContact().getValue();
        return "ENABLED".equals(value == null ? null : value.getDropInStatus());
    }

    public boolean isPermissionEnabled(@PermissionConstants.Permissions String str) {
        if (this.currentActor.getValue() == null || this.currentActor.getValue().getPermissions() == null) {
            return false;
        }
        return this.currentActor.getValue().getPermissions().contains(str);
    }

    public /* synthetic */ void lambda$leaveGroupAsync$2$CareActorsStoreV2(MobilyticsMetricsTimer mobilyticsMetricsTimer, Void r4) {
        this.crashMetadata.mo358get().put("leave_group_success", true);
        requestCareActors();
        postError(null);
        setIsLoading(false);
        this.leaveGroupSuccess.setValue(true);
        this.bilobaMetricsService.mo358get().finishTimer(mobilyticsMetricsTimer);
        this.bilobaMetricsService.mo358get().recordOccurrence("leaveRelationshipApi.Success", true);
    }

    public /* synthetic */ void lambda$leaveGroupAsync$3$CareActorsStoreV2(Throwable th) {
        LogUtils.e(TAG, th.getMessage());
        this.crashMetadata.mo358get().put("leave_group_success", false);
        this.crashReporter.mo358get().reportNonFatal(th);
        postError(th);
        setIsLoading(false);
        this.leaveGroupSuccess.setValue(false);
        this.bilobaMetricsService.mo358get().recordOccurrence("leaveRelationshipApi.Success", false);
    }

    public /* synthetic */ void lambda$requestCareActors$0$CareActorsStoreV2(MobilyticsMetricsTimer mobilyticsMetricsTimer, ListGroupsResponse listGroupsResponse) {
        this.crashMetadata.mo358get().put("list_groups_success", true);
        this.crashMetadata.mo358get().put("list_groups_care_givers_size", listGroupsResponse.getCareGivers() != null ? listGroupsResponse.getCareGivers().size() : 0);
        this.crashMetadata.mo358get().put("list_groups_care_recepients_size", listGroupsResponse.getCareRecipient() != null ? 1 : 0);
        handleListGroupsResponse(listGroupsResponse);
        postError(null);
        setIsLoading(false);
        this.bilobaMetricsService.mo358get().finishTimer(mobilyticsMetricsTimer);
        this.bilobaMetricsService.mo358get().recordOccurrence("getGroupsV2Api.Success", true);
    }

    public /* synthetic */ void lambda$requestCareActors$1$CareActorsStoreV2(Throwable th) {
        LogUtils.e(TAG, th.getMessage());
        this.crashMetadata.mo358get().put("list_groups_success", false);
        this.crashReporter.mo358get().reportNonFatal(th);
        postError(th);
        setIsLoading(false);
        this.bilobaMetricsService.mo358get().recordOccurrence("getGroupsV2Api.Success", false);
    }

    public void leaveGroupAsync() {
        setIsLoading(true);
        CareActor value = getCareContact().getValue();
        this.leaveGroupSuccess.setValue(null);
        final MobilyticsMetricsTimer startTimer = this.bilobaMetricsService.mo358get().startTimer("leaveRelationshipApi.Time");
        if (value != null) {
            this.groupApi.mo358get().leaveGroupObservable(this.currentGroupId).subscribe(new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$CareActorsStoreV2$JcYJIkcgFj2V3PI9auyUslDYSCc
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CareActorsStoreV2.this.lambda$leaveGroupAsync$2$CareActorsStoreV2(startTimer, (Void) obj);
                }
            }, new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$CareActorsStoreV2$8DjGvJT-hHqdtrUjCXnU_XcScdw
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    CareActorsStoreV2.this.lambda$leaveGroupAsync$3$CareActorsStoreV2((Throwable) obj);
                }
            });
        }
    }

    public void requestCareActors() {
        setIsLoading(true);
        LogUtils.i(TAG, "request for care actors");
        final MobilyticsMetricsTimer startTimer = this.bilobaMetricsService.mo358get().startTimer("getGroupsV2Api.Time");
        this.groupApi.mo358get().getGroupsObservable(this.currentGroupId).subscribe(new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$CareActorsStoreV2$rvIuGzxQgnCDx2cKO8OJmyesMnI
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CareActorsStoreV2.this.lambda$requestCareActors$0$CareActorsStoreV2(startTimer, (ListGroupsResponse) obj);
            }
        }, new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$CareActorsStoreV2$V6IrYZnjGl88GMJxmEK1YP3xAJQ
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CareActorsStoreV2.this.lambda$requestCareActors$1$CareActorsStoreV2((Throwable) obj);
            }
        });
    }

    public void setCurrentGroupId(String str) {
        this.currentGroupId = str;
    }

    @VisibleForTesting
    public CareActorsStoreV2(Lazy<GroupV2Api> lazy, Lazy<CrashReporter> lazy2, Lazy<CrashMetadata> lazy3, Lazy<BilobaMetricsService> lazy4, Lazy<Gson> lazy5) {
        this();
        this.groupApi = lazy;
        this.crashReporter = lazy2;
        this.crashMetadata = lazy3;
        this.bilobaMetricsService = lazy4;
        this.gson = lazy5;
    }
}
