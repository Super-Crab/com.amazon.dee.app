package com.amazon.alexa.biloba.view.alerts.edit;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.model.AlertCondition;
import com.amazon.alexa.biloba.model.AlertConfigurationViewItemModel;
import com.amazon.alexa.biloba.model.DeviceInfo;
import com.amazon.alexa.biloba.storage.AlertConfigurationRepo;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.BilobaViewModel;
import com.amazon.alexa.biloba.view.common.recycler.BaseRecyclerItem;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class AlertSettingsViewModel implements BilobaViewModel {
    public static final String ALERT_CONFIG_ID = "alert_config_id";
    public static final boolean CONDITION_PICKING_ENABLED = false;
    public static final boolean DELETE_BUTTON_ENABLED = false;
    public static final boolean DEVICE_PICKING_ENABLED = false;
    private static final String TAG = "AlertSettingsViewModel";
    String alertConfigId;
    @Inject
    Lazy<AlertConfigurationRepo> alertConfigurationRepo;
    @Inject
    Lazy<BilobaMetricsService> bilobaMetricsService;
    @Inject
    Lazy<CareActorsStore> careActorsStore;
    @Inject
    Lazy<CrashMetadata> crashMetadata;
    @Inject
    Lazy<CrashReporter> crashReporter;
    boolean devicesDialogWasCancelled;
    private boolean endTimePickerUpdated;
    private boolean startTimePickerUpdated;
    private MutableLiveData<AlertConfigurationViewItemModel> alertConfigModel = new MutableLiveData<>();
    private MutableLiveData<List<DeviceInfo>> echoDevices = new MutableLiveData<>();
    private MutableLiveData<List<DeviceInfo>> smarthomeDevices = new MutableLiveData<>();
    private MutableLiveData<List<AlertCondition>> alertConditions = new MutableLiveData<>();
    private MutableLiveData<String> displayStartTime = new MutableLiveData<>();
    private MutableLiveData<String> displayEndTime = new MutableLiveData<>();

    public AlertSettingsViewModel() {
        this.alertConfigModel.setValue(new AlertConfigurationViewItemModel(this.alertConfigId));
        this.echoDevices.setValue(new ArrayList());
        this.smarthomeDevices.setValue(new ArrayList());
        this.alertConditions.setValue(new ArrayList());
        this.startTimePickerUpdated = false;
        this.endTimePickerUpdated = false;
        LogUtils.d(TAG, String.format("Creating Alert Settings view model for alertConfigId=%s", this.alertConfigId));
        BilobaDependencies.inject(this);
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void create(@Nullable Bundle bundle) {
        this.devicesDialogWasCancelled = false;
        if (bundle != null) {
            this.alertConfigId = bundle.getString(ALERT_CONFIG_ID, null);
            LogUtils.d(TAG, String.format("Updating values: alertConfigId=%s", this.alertConfigId));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void deleteAlertConfiguration() {
        LogUtils.w(TAG, "delete operation not yet implemented");
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void destroy() {
    }

    public MutableLiveData<AlertConfigurationViewItemModel> getAlertConfig() {
        this.startTimePickerUpdated = false;
        this.endTimePickerUpdated = false;
        return this.alertConfigModel;
    }

    public LiveData<List<BaseRecyclerItem>> getAlertItems() {
        return this.alertConfigurationRepo.mo358get().getAlertItems();
    }

    public LiveData<String> getDisplayEndTime() {
        return this.displayEndTime;
    }

    public LiveData<String> getDisplayStartTime() {
        return this.displayStartTime;
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public LiveData<Throwable> getError() {
        return this.alertConfigurationRepo.mo358get().getError();
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public String getErrorViewMetricName() {
        return null;
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public LiveData<Boolean> getIsLoading() {
        return this.alertConfigurationRepo.mo358get().getIsLoading();
    }

    public AlertCondition getSelectedAlertCondition() {
        AlertConfigurationViewItemModel value = this.alertConfigModel.getValue();
        if (value != null) {
            return value.getAlertCondition();
        }
        return null;
    }

    public DeviceInfo getSelectedDevice() {
        if (this.alertConfigModel.getValue() != null) {
            return this.alertConfigModel.getValue().getDevice();
        }
        return null;
    }

    public boolean isEndTimePickerUpdated() {
        return this.endTimePickerUpdated;
    }

    public boolean isStartTimePickerUpdated() {
        return this.startTimePickerUpdated;
    }

    public void notifyAlertListUpdated(List<BaseRecyclerItem> list) {
        if (this.alertConfigId == null || list == null || list.isEmpty()) {
            return;
        }
        LogUtils.d(TAG, "Got updated values from the repo");
        for (BaseRecyclerItem baseRecyclerItem : list) {
            AlertConfigurationViewItemModel alertConfigurationViewItemModel = (AlertConfigurationViewItemModel) baseRecyclerItem.getData();
            if (this.alertConfigId.equals(alertConfigurationViewItemModel.getId())) {
                LogUtils.d(TAG, "Found an alert that matches our ID. Using this as the model under edit");
                this.alertConfigModel.setValue(alertConfigurationViewItemModel);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void saveAlertConfiguration() {
        AlertConfigurationViewItemModel value = this.alertConfigModel.getValue();
        if (value != null) {
            value.setEnabled(true);
            this.alertConfigurationRepo.mo358get().updateAlertConfig(this.careActorsStore.mo358get().getCurrentGroupId(), value);
            return;
        }
        LogUtils.e(TAG, "Could not update the alert configuration because it was null");
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void sendRequest() {
        getAlertConfig();
    }

    public void setEndTimePickerUpdated(boolean z) {
        this.endTimePickerUpdated = z;
    }

    public void setStartTimePickerUpdated(boolean z) {
        this.startTimePickerUpdated = z;
    }

    public void updateDisplayEndTime(String str) {
        this.displayEndTime.setValue(str);
    }

    public void updateDisplayStartTime(String str) {
        this.displayStartTime.setValue(str);
    }

    public void updateEndTime(int i, int i2) {
        AlertConfigurationViewItemModel value = this.alertConfigModel.getValue();
        if (value != null) {
            value.setEndTime(i, i2);
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Updating alert config end time to ");
            outline107.append(value.getEndTime());
            LogUtils.d(str, outline107.toString());
            this.alertConfigModel.setValue(value);
            return;
        }
        LogUtils.w(TAG, "Failed to update end time because the alert configuration was null");
    }

    public void updateStartTime(int i, int i2) {
        AlertConfigurationViewItemModel value = this.alertConfigModel.getValue();
        if (value != null) {
            value.setStartTime(i, i2);
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Updated alert config start time to ");
            outline107.append(value.getStartTime());
            LogUtils.d(str, outline107.toString());
            this.alertConfigModel.setValue(value);
            return;
        }
        LogUtils.w(TAG, "Failed to update start time because the alert configuration was null");
    }

    @VisibleForTesting
    AlertSettingsViewModel(Lazy<AlertConfigurationRepo> lazy, Lazy<CareActorsStore> lazy2) {
        this.alertConfigurationRepo = lazy;
        this.careActorsStore = lazy2;
        this.alertConfigModel.setValue(new AlertConfigurationViewItemModel(this.alertConfigId));
        this.echoDevices.setValue(new ArrayList());
        this.smarthomeDevices.setValue(new ArrayList());
        this.alertConditions.setValue(new ArrayList());
        this.startTimePickerUpdated = false;
        this.endTimePickerUpdated = false;
        LogUtils.d(TAG, String.format("Creating Alert Settings view model for alertConfigId=%s", this.alertConfigId));
    }
}
