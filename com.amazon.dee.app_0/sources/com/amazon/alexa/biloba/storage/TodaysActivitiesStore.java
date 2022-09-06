package com.amazon.alexa.biloba.storage;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.alexa.biloba.dependency.BilobaScope;
import com.amazon.alexa.biloba.generated.models.Activity;
import com.amazon.alexa.biloba.generated.models.Message;
import com.amazon.alexa.biloba.generated.models.TodaysActivitiesResponse;
import com.amazon.alexa.biloba.generated.network.api.TodaysActivitiesApi;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.common.recycler.BaseRecyclerItem;
import com.amazon.alexa.biloba.view.recent.models.ActivityItem;
import com.amazon.alexa.biloba.view.recent.models.ActivityMessage;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
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
public class TodaysActivitiesStore extends DataStore {
    private static final String TAG = "TodaysActivitiesStore";
    @Inject
    Lazy<BilobaMetricsService> bilobaMetricsService;
    @Inject
    Lazy<CrashMetadata> crashMetadata;
    @Inject
    Lazy<CrashReporter> crashReporter;
    private MutableLiveData<List<BaseRecyclerItem>> todaysActivities;
    @Inject
    Lazy<TodaysActivitiesApi> todaysActivitiesApi;

    @Inject
    public TodaysActivitiesStore() {
        this.todaysActivities = new MutableLiveData<>();
    }

    private List<BaseRecyclerItem> convertToRecentActivityListModel(TodaysActivitiesResponse todaysActivitiesResponse) {
        ArrayList arrayList = new ArrayList();
        if (todaysActivitiesResponse == null) {
            LogUtils.e(TAG, "Http response is null or unsuccessful");
            return arrayList;
        }
        try {
            if (todaysActivitiesResponse.getMessages() != null) {
                for (Message message : todaysActivitiesResponse.getMessages().values()) {
                    arrayList.add(new ActivityMessage(message.getLocalizedPrimaryMessage()));
                }
            }
            if (todaysActivitiesResponse.getActivities() != null) {
                for (Activity activity : todaysActivitiesResponse.getActivities()) {
                    arrayList.add(new ActivityItem(activity));
                }
            }
        } catch (Exception e) {
            LogUtils.e(TAG, "parse getActivity response with exception ", e);
        }
        return arrayList;
    }

    public LiveData<List<BaseRecyclerItem>> getTodaysActivities() {
        return this.todaysActivities;
    }

    synchronized void handleTodaysActivitiesResponse(TodaysActivitiesResponse todaysActivitiesResponse) {
        this.todaysActivities.setValue(convertToRecentActivityListModel(todaysActivitiesResponse));
    }

    public /* synthetic */ void lambda$requestTodaysActivities$0$TodaysActivitiesStore(MobilyticsMetricsTimer mobilyticsMetricsTimer, TodaysActivitiesResponse todaysActivitiesResponse) {
        this.crashMetadata.mo358get().put("list_todays_activities_success", true);
        this.crashMetadata.mo358get().put("list_todays_activities_size", todaysActivitiesResponse.getActivities() != null ? todaysActivitiesResponse.getActivities().size() : 0);
        handleTodaysActivitiesResponse(todaysActivitiesResponse);
        setIsLoading(false);
        postError(null);
        this.bilobaMetricsService.mo358get().finishTimer(mobilyticsMetricsTimer);
        this.bilobaMetricsService.mo358get().recordOccurrence("getTodaysActivityApi.Success", true);
    }

    public /* synthetic */ void lambda$requestTodaysActivities$1$TodaysActivitiesStore(Throwable th) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error: ");
        outline107.append(th.getMessage());
        LogUtils.e(str, outline107.toString());
        this.crashMetadata.mo358get().put("list_todays_activities_success", false);
        this.crashReporter.mo358get().reportNonFatal(th);
        setIsLoading(false);
        postError(th);
        this.bilobaMetricsService.mo358get().recordOccurrence("getTodaysActivityApi.Success", false);
    }

    public void requestTodaysActivities(String str) {
        LogUtils.i(TAG, "request for care actors");
        final MobilyticsMetricsTimer startTimer = this.bilobaMetricsService.mo358get().startTimer("getTodaysActivityApi.Time");
        setIsLoading(true);
        this.todaysActivitiesApi.mo358get().getTodaysActivitiesObservable(str).subscribe(new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$TodaysActivitiesStore$uRc-0yAp95KusKsgGpxrsUYQ5Mo
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                TodaysActivitiesStore.this.lambda$requestTodaysActivities$0$TodaysActivitiesStore(startTimer, (TodaysActivitiesResponse) obj);
            }
        }, new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$TodaysActivitiesStore$wAkOhMU1SffRMvKXQDpOCItzxas
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                TodaysActivitiesStore.this.lambda$requestTodaysActivities$1$TodaysActivitiesStore((Throwable) obj);
            }
        });
    }

    @VisibleForTesting
    public TodaysActivitiesStore(Lazy<TodaysActivitiesApi> lazy, Lazy<CrashReporter> lazy2, Lazy<CrashMetadata> lazy3, Lazy<BilobaMetricsService> lazy4) {
        this();
        this.todaysActivitiesApi = lazy;
        this.crashReporter = lazy2;
        this.crashMetadata = lazy3;
        this.bilobaMetricsService = lazy4;
    }
}
