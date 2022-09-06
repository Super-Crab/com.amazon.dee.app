package com.amazon.alexa.biloba.storage;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.alexa.biloba.dependency.BilobaScope;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.model.AlertCondition;
import com.amazon.alexa.biloba.model.AlertConfiguration;
import com.amazon.alexa.biloba.model.AlertConfigurationViewItemModel;
import com.amazon.alexa.biloba.model.AlertConfigurationsResponse;
import com.amazon.alexa.biloba.model.DeviceInfo;
import com.amazon.alexa.biloba.service.AlertConfigurationApi;
import com.amazon.alexa.biloba.service.AlertConfigurationException;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.alerts.AlertConfigRecyclerItem;
import com.amazon.alexa.biloba.view.common.recycler.BaseRecyclerItem;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.functions.Action1;
@Singleton
@BilobaScope
/* loaded from: classes6.dex */
public class AlertConfigurationRepo extends DataStore {
    private static final String TAG = "AlertConfigurationRepo";
    @Inject
    Lazy<AlertConfigurationApi> alertConfigurationApi;
    @Inject
    Lazy<BilobaMetricsService> bilobaMetricsService;
    @Inject
    Lazy<CrashMetadata> crashMetadata;
    @Inject
    Lazy<CrashReporter> crashReporter;
    private String relationshipId = null;
    private final MutableLiveData<List<BaseRecyclerItem>> displaySideAlertItems = new MutableLiveData<>();
    private List<AlertConfiguration> serviceSideAlertConfigurations = new ArrayList();

    @Inject
    public AlertConfigurationRepo() {
    }

    private void checkRelationshipId(String str) {
        String str2 = this.relationshipId;
        if (str2 == null || !str2.equals(str)) {
            LogUtils.d(TAG, "relationship ID mismatch; resetting alert config repository");
            this.relationshipId = str;
            updateAlertConfigurationItems(null);
        }
    }

    private List<BaseRecyclerItem> convertToAlertConfigurationViewItemModelList(@Nullable List<AlertConfiguration> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            for (AlertConfiguration alertConfiguration : list) {
                AlertCondition alertCondition = new AlertCondition("0", alertConfiguration.getTriggerCondition().getConditionType(), true);
                DeviceInfo deviceInfo = new DeviceInfo("0", "All devices", true);
                if (alertConfiguration.getId() == null) {
                    LogUtils.w(TAG, "Got a null alert configuration ID. This property should never be null.");
                } else {
                    AlertConfigurationViewItemModel alertConfigurationViewItemModel = new AlertConfigurationViewItemModel(alertConfiguration.getId(), alertCondition, deviceInfo, alertConfiguration.getStart(), alertConfiguration.getEnd(), alertConfiguration.getStatus().equals(AlertConfiguration.StatusEnum.ENABLED));
                    alertConfigurationViewItemModel.setTitle(alertConfiguration.getTitle());
                    alertConfigurationViewItemModel.setDescription(alertConfiguration.getDescription());
                    arrayList.add(new AlertConfigRecyclerItem(alertConfigurationViewItemModel));
                }
            }
            setIsLoading(false);
        } catch (Exception e) {
            LogUtils.e(TAG, "Failed to parse Alert Configuration with exception: ", e);
            this.crashMetadata.mo358get().put("parse_all_alertconfig_success", false);
            this.crashReporter.mo358get().reportNonFatal(e);
            setIsLoading(false);
            postError(new AlertConfigurationException(e, -2));
        }
        return arrayList;
    }

    private AlertConfiguration getUpdatedAlertConfiguration(AlertConfigurationViewItemModel alertConfigurationViewItemModel) {
        if (alertConfigurationViewItemModel == null) {
            LogUtils.w(TAG, "could not update null alert configuration");
            return null;
        }
        List<AlertConfiguration> list = this.serviceSideAlertConfigurations;
        if (list == null) {
            LogUtils.e(TAG, "unexpected null value for the alert configurations");
            return null;
        }
        AlertConfiguration alertConfiguration = null;
        for (AlertConfiguration alertConfiguration2 : list) {
            String id = alertConfiguration2.getId();
            if (id != null && id.equals(alertConfigurationViewItemModel.getId())) {
                alertConfiguration = updateAlertConfigurationFromViewItemModel(alertConfiguration2, alertConfigurationViewItemModel);
            }
        }
        if (alertConfiguration != null) {
            return alertConfiguration;
        }
        LogUtils.e(TAG, "Could not find an alert configuration with a matching ID");
        return null;
    }

    private void handleUpdated(AlertConfiguration alertConfiguration) {
        if (alertConfiguration == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (AlertConfiguration alertConfiguration2 : this.serviceSideAlertConfigurations) {
            String id = alertConfiguration2.getId();
            if (id != null && id.equals(alertConfiguration.getId())) {
                arrayList.add(alertConfiguration);
            } else {
                arrayList.add(alertConfiguration2);
            }
        }
        updateAlertConfigurationItems(arrayList);
    }

    private void reportFailedUpdate(@Nullable Throwable th) {
        this.crashMetadata.mo358get().put("update_alertconfig_success", false);
        if (th != null) {
            this.crashReporter.mo358get().reportNonFatal(th);
        }
        setIsLoading(false);
        postError(new AlertConfigurationException(-3));
    }

    private AlertConfiguration updateAlertConfigurationFromViewItemModel(AlertConfiguration alertConfiguration, AlertConfigurationViewItemModel alertConfigurationViewItemModel) {
        alertConfiguration.title(alertConfigurationViewItemModel.getTitle());
        alertConfiguration.description(alertConfigurationViewItemModel.getDescription());
        alertConfiguration.start(alertConfigurationViewItemModel.getStartTime());
        alertConfiguration.end(alertConfigurationViewItemModel.getEndTime());
        alertConfiguration.setStatus(alertConfigurationViewItemModel.isEnabled() ? AlertConfiguration.StatusEnum.ENABLED : AlertConfiguration.StatusEnum.DISABLED);
        return alertConfiguration;
    }

    private synchronized void updateAlertConfigurationItems(List<AlertConfiguration> list) {
        updateDisplaySideAlertItems(list);
        this.serviceSideAlertConfigurations = list;
    }

    private synchronized void updateDisplaySideAlertItems(List<AlertConfiguration> list) {
        this.displaySideAlertItems.setValue(convertToAlertConfigurationViewItemModelList(list));
    }

    public LiveData<List<BaseRecyclerItem>> getAlertItems() {
        return this.displaySideAlertItems;
    }

    public /* synthetic */ void lambda$requestAlertConfigurations$0$AlertConfigurationRepo(MobilyticsMetricsTimer mobilyticsMetricsTimer, AlertConfigurationsResponse alertConfigurationsResponse) {
        LogUtils.d(TAG, "got alert configs");
        updateAlertConfigurationItems(alertConfigurationsResponse.getAlertConfigurations());
        setIsLoading(false);
        postError(null);
        this.bilobaMetricsService.mo358get().finishTimer(mobilyticsMetricsTimer);
        this.bilobaMetricsService.mo358get().recordOccurrence("getAlertConfigurationsApi.Success", true);
    }

    public /* synthetic */ void lambda$requestAlertConfigurations$1$AlertConfigurationRepo(Throwable th) {
        LogUtils.e(TAG, th.getMessage());
        this.crashMetadata.mo358get().put("fetch_all_alertconfig_success", false);
        this.crashReporter.mo358get().reportNonFatal(th);
        setIsLoading(false);
        postError(new AlertConfigurationException(th, -2));
        this.bilobaMetricsService.mo358get().recordOccurrence("getAlertConfigurationsApi.Success", false);
    }

    public /* synthetic */ void lambda$updateAlertConfig$2$AlertConfigurationRepo(MobilyticsMetricsTimer mobilyticsMetricsTimer, AlertConfiguration alertConfiguration) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("updated alert configuration for: \"");
        outline107.append(alertConfiguration.getTitle());
        outline107.append(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
        LogUtils.d(str, outline107.toString());
        handleUpdated(alertConfiguration);
        setIsLoading(false);
        postError(null);
        this.bilobaMetricsService.mo358get().finishTimer(mobilyticsMetricsTimer);
        this.bilobaMetricsService.mo358get().recordOccurrence("updateAlertConfigurationsApi.Success", true);
    }

    public /* synthetic */ void lambda$updateAlertConfig$3$AlertConfigurationRepo(Throwable th) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("update alert configuration failure = ");
        outline107.append(th == null ? "null" : th.getMessage());
        LogUtils.e(str, outline107.toString());
        reportFailedUpdate(th);
        this.bilobaMetricsService.mo358get().recordOccurrence("updateAlertConfigurationsApi.Success", false);
    }

    public void requestAlertConfigurations(String str) {
        LogUtils.d(TAG, "requesting alert configurations from web service");
        checkRelationshipId(str);
        if (getIsLoading().getValue() == Boolean.TRUE) {
            LogUtils.d(TAG, "Already loading");
            return;
        }
        setIsLoading(true);
        final MobilyticsMetricsTimer startTimer = this.bilobaMetricsService.mo358get().startTimer("getAlertConfigurationsApi.Time");
        this.alertConfigurationApi.mo358get().getAlertConfigurationsObservable(str, null, null).subscribe(new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$AlertConfigurationRepo$bw1uvmLG5bzj07DKNPy-cW5aQRw
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AlertConfigurationRepo.this.lambda$requestAlertConfigurations$0$AlertConfigurationRepo(startTimer, (AlertConfigurationsResponse) obj);
            }
        }, new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$AlertConfigurationRepo$6Sc2uvTc1mPN7tsMFasKfzn-Ri4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AlertConfigurationRepo.this.lambda$requestAlertConfigurations$1$AlertConfigurationRepo((Throwable) obj);
            }
        });
    }

    public void updateAlertConfig(String str, AlertConfigurationViewItemModel alertConfigurationViewItemModel) {
        checkRelationshipId(str);
        setIsLoading(true);
        AlertConfiguration updatedAlertConfiguration = getUpdatedAlertConfiguration(alertConfigurationViewItemModel);
        if (updatedAlertConfiguration == null) {
            reportFailedUpdate(null);
            return;
        }
        final MobilyticsMetricsTimer startTimer = this.bilobaMetricsService.mo358get().startTimer("updateAlertConfigurationsApi.Time");
        this.alertConfigurationApi.mo358get().updateAlertConfigurationObservable(updatedAlertConfiguration.getId(), str, updatedAlertConfiguration).subscribe(new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$AlertConfigurationRepo$9VHQHNmZlbdQ8DJyLceoedRDDLQ
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AlertConfigurationRepo.this.lambda$updateAlertConfig$2$AlertConfigurationRepo(startTimer, (AlertConfiguration) obj);
            }
        }, new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$AlertConfigurationRepo$r37E3ZF6Sy7yetvS5P1h5GNwFoc
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                AlertConfigurationRepo.this.lambda$updateAlertConfig$3$AlertConfigurationRepo((Throwable) obj);
            }
        });
    }

    @VisibleForTesting
    public AlertConfigurationRepo(Lazy<AlertConfigurationApi> lazy, Lazy<BilobaMetricsService> lazy2, Lazy<CrashMetadata> lazy3, Lazy<CrashReporter> lazy4) {
        this.alertConfigurationApi = lazy;
        this.bilobaMetricsService = lazy2;
        this.crashMetadata = lazy3;
        this.crashReporter = lazy4;
    }
}
