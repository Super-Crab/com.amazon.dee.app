package com.amazon.alexa.biloba.view.account.timeZonePicker;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.model.CareActor;
import com.amazon.alexa.biloba.model.TimeZoneItem;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.storage.SettingsStore;
import com.amazon.alexa.biloba.storage.TimeZoneStore;
import com.amazon.alexa.biloba.utils.CareActorUtil;
import com.amazon.alexa.biloba.utils.LiveDataUtils;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.view.BilobaViewModel;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class TimeZonePickerViewModel implements BilobaViewModel {
    private static final String TAG = "TimeZonePickerViewModel";
    @Inject
    Lazy<CareActorsStore> careActorsStore;
    private MediatorLiveData<String> currentRegion;
    private int currentRegionIndex;
    private final MediatorLiveData<Boolean> isLoading;
    @Inject
    Lazy<SettingsStore> settingsStore;
    private final MediatorLiveData<Throwable> timeZonePageError;
    @Inject
    Lazy<TimeZoneStore> timeZoneStore;

    public TimeZonePickerViewModel() {
        this.timeZonePageError = new MediatorLiveData<>();
        this.isLoading = new MediatorLiveData<>();
        BilobaDependencies.inject(this);
        this.currentRegion = new MediatorLiveData<>();
        this.currentRegionIndex = -1;
        initLiveData();
    }

    private void initLiveData() {
        LiveDataUtils.mergeLiveDataNotNull(this.timeZonePageError, this.timeZoneStore.mo358get().getError(), this.settingsStore.mo358get().getError());
        LiveDataUtils.mergeLiveDataEqualTrue(this.isLoading, this.timeZoneStore.mo358get().getIsLoading(), this.settingsStore.mo358get().getIsLoading());
        this.currentRegion.addSource(this.timeZoneStore.mo358get().getTimeZoneRegions(), new Observer() { // from class: com.amazon.alexa.biloba.view.account.timeZonePicker.-$$Lambda$TimeZonePickerViewModel$6qjYYBgJ5ZbxAIO4SJsmFG6G5co
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                TimeZonePickerViewModel.this.lambda$initLiveData$0$TimeZonePickerViewModel((List) obj);
            }
        });
        this.currentRegion.addSource(this.settingsStore.mo358get().getTimeZone(), new Observer() { // from class: com.amazon.alexa.biloba.view.account.timeZonePicker.-$$Lambda$TimeZonePickerViewModel$baFZAj64tE21LALCjk95TKY5RJc
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                TimeZonePickerViewModel.this.lambda$initLiveData$1$TimeZonePickerViewModel((String) obj);
            }
        });
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void create(@Nullable Bundle bundle) {
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void destroy() {
    }

    public MediatorLiveData<String> getCurrentRegion() {
        return this.currentRegion;
    }

    public int getCurrentRegionIndex() {
        return this.currentRegionIndex;
    }

    public MutableLiveData<String> getCurrentTimeZone() {
        return this.settingsStore.mo358get().getTimeZone();
    }

    public String getDisplayName(LiveData<CareActor> liveData) {
        return CareActorUtil.getDisplayName(liveData.getValue());
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public LiveData<Throwable> getError() {
        return this.timeZonePageError;
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public String getErrorViewMetricName() {
        return null;
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public LiveData<Boolean> getIsLoading() {
        return this.isLoading;
    }

    public String getTimeZoneDisplayName(MutableLiveData<String> mutableLiveData) {
        return this.settingsStore.mo358get().getTimeZoneDisplayName(mutableLiveData);
    }

    public LiveData<Throwable> getTimeZonePageError() {
        return this.timeZonePageError;
    }

    public List<TimeZoneItem> getTimeZonesForCurrentRegion() {
        String value = this.currentRegion.getValue();
        if (value != null) {
            return this.timeZoneStore.mo358get().getTimeZonesForRegion(value);
        }
        String str = TAG;
        LogUtils.i(str, "Time zones for the region " + value + " is null");
        return new ArrayList();
    }

    public CharSequence[] getTimezoneRegionsAsCharSequence() {
        List<String> value = this.timeZoneStore.mo358get().getTimeZoneRegions().getValue();
        return value == null ? new CharSequence[0] : (CharSequence[]) value.toArray(new CharSequence[value.size()]);
    }

    public boolean isCareGiver() {
        return this.careActorsStore.mo358get().getIsCareGiver().getValue() == Boolean.TRUE;
    }

    public /* synthetic */ void lambda$initLiveData$0$TimeZonePickerViewModel(List list) {
        this.currentRegion.setValue(this.timeZoneStore.mo358get().getRegionForTimeZone(this.settingsStore.mo358get().getTimeZone().getValue()));
        this.currentRegionIndex = this.timeZoneStore.mo358get().getRegionIndex(this.currentRegion.getValue());
    }

    public /* synthetic */ void lambda$initLiveData$1$TimeZonePickerViewModel(String str) {
        this.currentRegion.setValue(this.timeZoneStore.mo358get().getRegionForTimeZone(str));
        this.currentRegionIndex = this.timeZoneStore.mo358get().getRegionIndex(this.currentRegion.getValue());
    }

    @Override // com.amazon.alexa.biloba.view.BilobaViewModel
    public void sendRequest() {
        this.settingsStore.mo358get().getTimeZoneSettings(this.careActorsStore.mo358get().getCurrentGroupId());
        this.timeZoneStore.mo358get().getAllAvailableTimeZones();
    }

    public void setTimeZoneSettings(TimeZoneItem timeZoneItem) {
        this.settingsStore.mo358get().setTimeZoneSettings(this.careActorsStore.mo358get().getCurrentGroupId(), timeZoneItem);
    }

    public void updateCurrentTimeZoneRegion(int i) {
        List<String> value = this.timeZoneStore.mo358get().getTimeZoneRegions().getValue();
        String str = TAG;
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Updating the Current region from the region list from position: ", i, "and the value ");
        outline109.append(value.get(i));
        LogUtils.i(str, outline109.toString());
        this.currentRegionIndex = i;
        this.currentRegion.setValue(value.get(i));
    }

    @VisibleForTesting
    public TimeZonePickerViewModel(Lazy<CareActorsStore> lazy, Lazy<SettingsStore> lazy2, Lazy<TimeZoneStore> lazy3) {
        this.timeZonePageError = new MediatorLiveData<>();
        this.isLoading = new MediatorLiveData<>();
        this.careActorsStore = lazy;
        this.currentRegionIndex = -1;
        this.currentRegion = new MediatorLiveData<>();
        this.settingsStore = lazy2;
        this.timeZoneStore = lazy3;
        initLiveData();
    }
}
