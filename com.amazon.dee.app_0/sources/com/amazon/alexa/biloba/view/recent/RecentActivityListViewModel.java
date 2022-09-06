package com.amazon.alexa.biloba.view.recent;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.storage.ActivitiesStore;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.view.BilobaViewModel;
import com.amazon.alexa.biloba.view.common.recycler.BaseRecyclerItem;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import dagger.Lazy;
import java.util.List;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class RecentActivityListViewModel implements BilobaViewModel {
    private static final int PAGE_SIZE = 30;
    private static final String TAG = "RecentActivityListViewModel";
    @Inject
    Lazy<ActivitiesStore> activitiesStore;
    @Inject
    Lazy<BilobaMetricsService> bilobaMetricsService;
    @Inject
    Lazy<CareActorsStore> careActorsStore;
    @Inject
    Lazy<CrashMetadata> crashMetadata;
    @Inject
    Lazy<CrashReporter> crashReporter;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RecentActivityListViewModel() {
        BilobaDependencies.inject(this);
    }

    public void clear() {
        this.activitiesStore.mo358get().clear();
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void create(@Nullable Bundle bundle) {
        clear();
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void destroy() {
        clear();
    }

    public LiveData<List<BaseRecyclerItem>> getActivities() {
        return this.activitiesStore.mo358get().getActivities();
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public LiveData<Throwable> getError() {
        return this.activitiesStore.mo358get().getError();
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public String getErrorViewMetricName() {
        return "AllActivityView.Error";
    }

    public boolean getHasMore() {
        return this.activitiesStore.mo358get().getHasMore();
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public LiveData<Boolean> getIsLoading() {
        return this.activitiesStore.mo358get().getIsLoading();
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void sendRequest() {
        this.activitiesStore.mo358get().requestActivities(this.careActorsStore.mo358get().getCurrentGroupId(), this.activitiesStore.mo358get().getNextToken(), 30, null);
    }

    @VisibleForTesting
    RecentActivityListViewModel(Lazy<ActivitiesStore> lazy, Lazy<CareActorsStore> lazy2, Lazy<CrashMetadata> lazy3, Lazy<CrashReporter> lazy4) {
        this.activitiesStore = lazy;
        this.careActorsStore = lazy2;
        this.crashMetadata = lazy3;
        this.crashReporter = lazy4;
    }
}
