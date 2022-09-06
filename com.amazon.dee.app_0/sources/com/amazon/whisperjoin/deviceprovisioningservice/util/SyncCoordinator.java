package com.amazon.whisperjoin.deviceprovisioningservice.util;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.deviceprovisioningservice.service.ProvisionerClientData;
import com.amazon.whisperjoin.util.rx.RxLog;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
/* loaded from: classes13.dex */
public abstract class SyncCoordinator<T> {
    protected final Clock mClock;
    protected final Context mContext;
    private final Observable mDataSourceObservable = Observable.concat(getSavedInstanceData(), getPersistedData(), getDataFromNetwork()).take(1).doOnSubscribe(RxLog.doOnSubscribe(tag(), "Creating new subscription to getData request")).doOnNext(RxLog.doOnNext(tag(), "Data retrieved")).doOnComplete(RxLog.doOnComplete(tag(), "Data fetch complete")).doOnError(RxLog.doOnError(tag(), "Error fetching data")).share();
    protected final JobInfoProvider mJobInfoProvider;
    protected final JobScheduler mJobScheduler;
    protected ProvisionerClientData mProvisionerClientData;
    private T mSavedData;
    protected final SharedPreferencesProvider mSharedPreferencesProvider;

    public SyncCoordinator(JobScheduler jobScheduler, JobInfoProvider jobInfoProvider, Context context, SharedPreferencesProvider sharedPreferencesProvider, Clock clock) {
        this.mJobScheduler = jobScheduler;
        this.mJobInfoProvider = jobInfoProvider;
        this.mContext = context;
        this.mSharedPreferencesProvider = sharedPreferencesProvider;
        this.mClock = clock;
    }

    private Observable<T> getPersistedData() {
        return Observable.defer(new Supplier<ObservableSource<? extends T>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public ObservableSource<? extends T> mo10365get() throws Exception {
                Object mo5689readPersistedDataFromSharedPreferences = SyncCoordinator.this.mo5689readPersistedDataFromSharedPreferences();
                if (SyncCoordinator.this.isDataValid(mo5689readPersistedDataFromSharedPreferences, SyncCoordinator.this.readPersistedProvisionerClientData())) {
                    WJLog.d(SyncCoordinator.this.tag(), "Using persisted data");
                    SyncCoordinator.this.mSavedData = mo5689readPersistedDataFromSharedPreferences;
                    SyncCoordinator.this.onPersistedDataFetched(mo5689readPersistedDataFromSharedPreferences);
                    return Observable.just(mo5689readPersistedDataFromSharedPreferences);
                }
                WJLog.d(SyncCoordinator.this.tag(), "No Persisted data");
                return Observable.empty();
            }
        }).doOnSubscribe(RxLog.doOnSubscribe(tag(), "Trying to get persisted data"));
    }

    private Observable<T> getSavedInstanceData() {
        return Observable.defer(new Supplier<ObservableSource<? extends T>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator.1
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public ObservableSource<? extends T> mo10365get() throws Exception {
                SyncCoordinator syncCoordinator = SyncCoordinator.this;
                if (syncCoordinator.isDataValid(syncCoordinator.mSavedData, SyncCoordinator.this.mProvisionerClientData)) {
                    WJLog.d(SyncCoordinator.this.tag(), "Using locally stored data");
                    return Observable.just(SyncCoordinator.this.mSavedData);
                }
                WJLog.d(SyncCoordinator.this.tag(), "No locally stored data");
                return Observable.empty();
            }
        }).doOnSubscribe(RxLog.doOnSubscribe(tag(), "Trying to get data from saved instances"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDataValid(T t, ProvisionerClientData provisionerClientData) {
        if (t == null) {
            WJLog.v(tag(), "data is null");
            return false;
        } else if (provisionerClientData == null) {
            WJLog.d(tag(), "Client data is null");
            return false;
        } else if (isDataInvalid(t)) {
            WJLog.v(tag(), "Data is expired");
            return false;
        } else if (this.mProvisionerClientData.equals(provisionerClientData)) {
            return true;
        } else {
            WJLog.v(tag(), "Policy not for current provisioner client");
            return false;
        }
    }

    protected abstract Single<T> fetchDataFromNetwork();

    public Single<T> getData() {
        if (this.mProvisionerClientData != null) {
            WJLog.d(tag(), "getData");
            return this.mDataSourceObservable.firstOrError();
        }
        throw new IllegalStateException("Class not yet initialized with client data");
    }

    protected Observable<T> getDataFromNetwork() {
        return Observable.defer(new Supplier<ObservableSource<T>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator.3
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public ObservableSource<T> mo10365get() throws Exception {
                return SyncCoordinator.this.fetchDataFromNetwork().flatMapObservable((Function<T, ObservableSource<T>>) new Function<T, ObservableSource<T>>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.util.SyncCoordinator.3.1
                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply  reason: collision with other method in class */
                    public /* bridge */ /* synthetic */ Object mo10358apply(Object obj) throws Throwable {
                        return mo10358apply((AnonymousClass1) obj);
                    }

                    @Override // io.reactivex.rxjava3.functions.Function
                    /* renamed from: apply */
                    public ObservableSource<T> mo10358apply(T t) throws Exception {
                        WJLog.d(SyncCoordinator.this.tag(), "Persisting data retrieved from network");
                        SyncCoordinator.this.mSavedData = t;
                        SyncCoordinator syncCoordinator = SyncCoordinator.this;
                        syncCoordinator.persistProvisionerClientData(syncCoordinator.mProvisionerClientData);
                        SyncCoordinator.this.persistDataToSharedPreferences(t);
                        SyncCoordinator.this.onDataFetchedFromNetwork(t);
                        return Observable.just(t);
                    }
                });
            }
        }).doOnSubscribe(RxLog.doOnSubscribe(tag(), "Trying to get data from network"));
    }

    protected JobInfo getPendingJobInfo(int i) {
        for (JobInfo jobInfo : this.mJobScheduler.getAllPendingJobs()) {
            if (jobInfo.getId() == i) {
                return jobInfo;
            }
        }
        return null;
    }

    protected abstract SharedPreferences getSharedPreferences();

    public void initializeWithProvisionerClientData(ProvisionerClientData provisionerClientData) {
        if (provisionerClientData != null) {
            ProvisionerClientData provisionerClientData2 = this.mProvisionerClientData;
            if (provisionerClientData2 == null) {
                this.mProvisionerClientData = provisionerClientData;
                return;
            } else if (!provisionerClientData2.equals(provisionerClientData)) {
                WJLog.w(tag(), "Class already initialized with different clientData");
                return;
            } else {
                WJLog.d(tag(), "Class already initialized");
                return;
            }
        }
        throw new IllegalArgumentException("clientData can not be null");
    }

    protected abstract boolean isDataInvalid(T t);

    protected abstract void onDataFetchedFromNetwork(T t);

    protected abstract void onPersistedDataFetched(T t);

    protected abstract void persistDataToSharedPreferences(T t);

    protected void persistProvisionerClientData(ProvisionerClientData provisionerClientData) {
        ProvisionerClientData.writeToSharedPreferences(provisionerClientData, getSharedPreferences());
    }

    /* renamed from: readPersistedDataFromSharedPreferences */
    protected abstract T mo5689readPersistedDataFromSharedPreferences();

    protected ProvisionerClientData readPersistedProvisionerClientData() {
        return ProvisionerClientData.readFromSharedPreferences(getSharedPreferences());
    }

    protected abstract String tag();
}
