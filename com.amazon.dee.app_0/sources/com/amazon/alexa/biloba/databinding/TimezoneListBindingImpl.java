package com.amazon.alexa.biloba.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MediatorLiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.biloba.BR;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.account.timeZonePicker.TimeZonePickerView;
import com.amazon.alexa.biloba.view.account.timeZonePicker.TimeZonePickerViewModel;
import com.amazon.alexa.mosaic.view.ListItemLink;
import com.amazon.alexa.mosaic.view.SectionHeader;
/* loaded from: classes6.dex */
public class TimezoneListBindingImpl extends TimezoneListBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private OnClickListenerImpl mTimezoneViewShowTimeZoneRegionsAndroidViewViewOnClickListener;
    @NonNull
    private final LinearLayout mboundView0;

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl implements View.OnClickListener {
        private TimeZonePickerView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.showTimeZoneRegions(view);
        }

        public OnClickListenerImpl setValue(TimeZonePickerView timeZonePickerView) {
            this.value = timeZonePickerView;
            if (timeZonePickerView == null) {
                return null;
            }
            return this;
        }
    }

    static {
        sViewsWithIds.put(R.id.care_communication_heading, 2);
        sViewsWithIds.put(R.id.timezone_list, 3);
    }

    public TimezoneListBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds));
    }

    private boolean onChangeViewmodelGetCurrentRegion(MediatorLiveData<String> mediatorLiveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        OnClickListenerImpl onClickListenerImpl;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        TimeZonePickerView timeZonePickerView = this.mTimezoneView;
        TimeZonePickerViewModel timeZonePickerViewModel = this.mViewmodel;
        int i = ((10 & j) > 0L ? 1 : ((10 & j) == 0L ? 0 : -1));
        String str = null;
        if (i == 0 || timeZonePickerView == null) {
            onClickListenerImpl = null;
        } else {
            OnClickListenerImpl onClickListenerImpl2 = this.mTimezoneViewShowTimeZoneRegionsAndroidViewViewOnClickListener;
            if (onClickListenerImpl2 == null) {
                onClickListenerImpl2 = new OnClickListenerImpl();
                this.mTimezoneViewShowTimeZoneRegionsAndroidViewViewOnClickListener = onClickListenerImpl2;
            }
            onClickListenerImpl = onClickListenerImpl2.setValue(timeZonePickerView);
        }
        int i2 = ((j & 13) > 0L ? 1 : ((j & 13) == 0L ? 0 : -1));
        if (i2 != 0) {
            MediatorLiveData<String> currentRegion = timeZonePickerViewModel != null ? timeZonePickerViewModel.getCurrentRegion() : null;
            updateLiveDataRegistration(0, currentRegion);
            if (currentRegion != null) {
                str = currentRegion.getValue();
            }
        }
        if (i != 0) {
            this.timezoneRegion.setOnClickListener(onClickListenerImpl);
        }
        if (i2 != 0) {
            this.timezoneRegion.setPrimaryText(str);
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeViewmodelGetCurrentRegion((MediatorLiveData) obj, i2);
    }

    @Override // com.amazon.alexa.biloba.databinding.TimezoneListBinding
    public void setTimezoneView(@Nullable TimeZonePickerView timeZonePickerView) {
        this.mTimezoneView = timeZonePickerView;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.timezone_view);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.timezone_view == i) {
            setTimezoneView((TimeZonePickerView) obj);
        } else if (BR.viewmodel != i) {
            return false;
        } else {
            setViewmodel((TimeZonePickerViewModel) obj);
        }
        return true;
    }

    @Override // com.amazon.alexa.biloba.databinding.TimezoneListBinding
    public void setViewmodel(@Nullable TimeZonePickerViewModel timeZonePickerViewModel) {
        this.mViewmodel = timeZonePickerViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.viewmodel);
        super.requestRebind();
    }

    private TimezoneListBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, (SectionHeader) objArr[2], (RecyclerView) objArr[3], (ListItemLink) objArr[1]);
        this.mDirtyFlags = -1L;
        this.mboundView0 = (LinearLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.timezoneRegion.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
