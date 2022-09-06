package com.amazon.alexa.biloba.view.alerts;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.generated.models.Message;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.model.AlertConfigurationViewItemModel;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.biloba.model.Device;
import com.amazon.alexa.biloba.storage.AlertConfigurationRepo;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.storage.DevicesStore;
import com.amazon.alexa.biloba.utils.CareActorUtil;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.BilobaViewModel;
import com.amazon.alexa.biloba.view.common.recycler.BaseRecyclerItem;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.Lazy;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class AlertsListViewModel implements BilobaViewModel {
    private static final String TAG = "AlertsListViewModel";
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
    @Inject
    Lazy<DevicesStore> devicesStore;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlertsListViewModel() {
        BilobaDependencies.inject(this);
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void create(@Nullable Bundle bundle) {
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void destroy() {
    }

    public LiveData<List<BaseRecyclerItem>> getAlertItems() {
        return this.alertConfigurationRepo.mo358get().getAlertItems();
    }

    public LiveData<CareActor> getCareContact() {
        return this.careActorsStore.mo358get().getCareContact();
    }

    public LiveData<Map<String, Message>> getDeviceMessages() {
        return this.devicesStore.mo358get().getMessages();
    }

    public LiveData<List<Device>> getDevices() {
        return this.devicesStore.mo358get().getDevices();
    }

    public String getDisplayName(LiveData<CareActor> liveData) {
        return CareActorUtil.getDisplayName(liveData.getValue());
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

    public boolean isCareGiver() {
        return this.careActorsStore.mo358get().getIsCareGiver().getValue() == Boolean.TRUE;
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void sendRequest() {
        LogUtils.i(TAG, "sendRequest for alert configs");
        this.alertConfigurationRepo.mo358get().requestAlertConfigurations(this.careActorsStore.mo358get().getCurrentGroupId());
        this.devicesStore.mo358get().requestDevices(this.careActorsStore.mo358get().getCurrentGroupId());
        LogUtils.d(TAG, "sendRequest for alert configurations through service");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void updateAlertConfig(AlertConfigurationViewItemModel alertConfigurationViewItemModel) {
        this.alertConfigurationRepo.mo358get().updateAlertConfig(this.careActorsStore.mo358get().getCurrentGroupId(), alertConfigurationViewItemModel);
    }

    @VisibleForTesting
    AlertsListViewModel(Lazy<AlertConfigurationRepo> lazy, Lazy<CareActorsStore> lazy2, Lazy<DevicesStore> lazy3) {
        this.alertConfigurationRepo = lazy;
        this.careActorsStore = lazy2;
        this.devicesStore = lazy3;
    }
}
