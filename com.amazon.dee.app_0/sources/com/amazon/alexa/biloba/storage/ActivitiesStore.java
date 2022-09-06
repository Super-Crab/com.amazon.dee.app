package com.amazon.alexa.biloba.storage;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.alexa.biloba.dependency.BilobaScope;
import com.amazon.alexa.biloba.generated.models.ActivitiesResponse;
import com.amazon.alexa.biloba.generated.models.Activity;
import com.amazon.alexa.biloba.generated.models.PaginationContext;
import com.amazon.alexa.biloba.generated.network.api.ActivityApi;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.common.recycler.BaseRecyclerItem;
import com.amazon.alexa.biloba.view.recent.ActivityContainerByDate;
import com.amazon.alexa.biloba.view.recent.models.LoadingItem;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.functions.Action1;
@Singleton
@BilobaScope
/* loaded from: classes6.dex */
public class ActivitiesStore extends DataStore {
    private static final String TAG = "ActivitiesStore";
    private final MutableLiveData<List<BaseRecyclerItem>> activities;
    private final ActivityContainerByDate activitiesContainer;
    @Inject
    Lazy<ActivityApi> activityApi;
    @Inject
    Lazy<BilobaMetricsService> bilobaMetricsService;
    @Inject
    Lazy<CrashMetadata> crashMetadata;
    @Inject
    Lazy<CrashReporter> crashReporter;
    private boolean hasMore;
    private PaginationContext paginationContext;

    @Inject
    public ActivitiesStore() {
        this.activities = new MutableLiveData<>(new ArrayList());
        this.activitiesContainer = new ActivityContainerByDate();
    }

    private List<BaseRecyclerItem> convertToRecentActivityListModel(ActivitiesResponse activitiesResponse) {
        ArrayList arrayList = new ArrayList();
        if (activitiesResponse == null) {
            LogUtils.e(TAG, "Http response is null or unsuccessful");
            return arrayList;
        }
        if (activitiesResponse.getMessages() != null) {
            this.activitiesContainer.addMessages(activitiesResponse.getMessages());
        }
        if (activitiesResponse.getActivities() != null) {
            for (Activity activity : activitiesResponse.getActivities()) {
                this.activitiesContainer.addActivity(activity);
            }
        }
        List<BaseRecyclerItem> allActivities = this.activitiesContainer.getAllActivities();
        if (getHasMore()) {
            allActivities.add(new LoadingItem());
        }
        return allActivities;
    }

    @Override // com.amazon.alexa.biloba.storage.DataStore
    public void clear() {
        setHasMore(false);
        this.paginationContext = null;
        this.activitiesContainer.clear();
        this.activities.setValue(new ArrayList());
    }

    public LiveData<List<BaseRecyclerItem>> getActivities() {
        return this.activities;
    }

    public boolean getHasMore() {
        return this.hasMore;
    }

    public String getNextToken() {
        PaginationContext paginationContext = this.paginationContext;
        if (paginationContext == null) {
            return null;
        }
        return paginationContext.getNextToken();
    }

    synchronized void handleActivitiesResponse(ActivitiesResponse activitiesResponse) {
        setHasMore((activitiesResponse.getPaginationContext() == null || activitiesResponse.getPaginationContext().getNextToken() == null || activitiesResponse.getPaginationContext().getNextToken().equals(getNextToken())) ? false : true);
        this.paginationContext = activitiesResponse.getPaginationContext();
        this.activities.setValue(convertToRecentActivityListModel(activitiesResponse));
    }

    public /* synthetic */ void lambda$requestActivities$0$ActivitiesStore(String str, MobilyticsMetricsTimer mobilyticsMetricsTimer, ActivitiesResponse activitiesResponse) {
        this.crashMetadata.mo358get().put("fetch_all_activities_success", true);
        this.crashMetadata.mo358get().put("fetch_all_activities_size", activitiesResponse.getActivities() != null ? activitiesResponse.getActivities().size() : 0);
        if (str == null) {
            clear();
        }
        handleActivitiesResponse(activitiesResponse);
        setIsLoading(false);
        postError(null);
        this.bilobaMetricsService.mo358get().finishTimer(mobilyticsMetricsTimer);
        this.bilobaMetricsService.mo358get().recordOccurrence("getAllActivityApi.Success", true);
    }

    public /* synthetic */ void lambda$requestActivities$1$ActivitiesStore(Throwable th) {
        LogUtils.e(TAG, th.getMessage());
        this.crashMetadata.mo358get().put("fetch_all_activities_success", false);
        this.crashReporter.mo358get().reportNonFatal(th);
        setIsLoading(false);
        postError(th);
        this.bilobaMetricsService.mo358get().recordOccurrence("getAllActivityApi.Success", false);
    }

    public void requestActivities(String str, final String str2, Integer num, List<String> list) {
        LogUtils.i(TAG, "sendRequest for all activities");
        setIsLoading(true);
        LogUtils.i(TAG, "sendRequest for all activities through service");
        final MobilyticsMetricsTimer startTimer = this.bilobaMetricsService.mo358get().startTimer("getAllActivityApi.Time");
        this.activityApi.mo358get().getActivitiesObservable(str, str2, num, list).subscribe(new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$ActivitiesStore$h63pUl7eIG1KIryArMMDeUhORgc
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ActivitiesStore.this.lambda$requestActivities$0$ActivitiesStore(str2, startTimer, (ActivitiesResponse) obj);
            }
        }, new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$ActivitiesStore$yanH97a4dVhWNNG-9iSlt2muCb8
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                ActivitiesStore.this.lambda$requestActivities$1$ActivitiesStore((Throwable) obj);
            }
        });
    }

    public void setHasMore(boolean z) {
        this.hasMore = z;
    }

    @VisibleForTesting
    public ActivitiesStore(Lazy<ActivityApi> lazy, Lazy<CrashReporter> lazy2, Lazy<CrashMetadata> lazy3, Lazy<BilobaMetricsService> lazy4) {
        this();
        this.activityApi = lazy;
        this.crashReporter = lazy2;
        this.crashMetadata = lazy3;
        this.bilobaMetricsService = lazy4;
    }
}
