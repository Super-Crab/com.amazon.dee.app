package com.amazon.alexa.biloba.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.alexa.biloba.BR;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.model.AlertConfigurationViewItemModel;
import com.amazon.alexa.biloba.view.alerts.edit.AlertSettingsView;
import com.amazon.alexa.biloba.view.alerts.edit.AlertSettingsViewModel;
import com.amazon.alexa.mosaic.view.ListItemLink;
import com.amazon.alexa.mosaic.view.SectionHeader;
/* loaded from: classes6.dex */
public class AlertFormBindingImpl extends AlertFormBinding {
    @Nullable
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    private OnClickListenerImpl3 mHandlerOnAlertConditionClickedAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mHandlerOnDeleteButtonClickedAndroidViewViewOnClickListener;
    private OnClickListenerImpl2 mHandlerOnDeviceTypeClickedAndroidViewViewOnClickListener;
    private OnClickListenerImpl4 mHandlerOnEndTimeViewClickedAndroidViewViewOnClickListener;
    private OnClickListenerImpl5 mHandlerOnSaveButtonClickedAndroidViewViewOnClickListener;
    private OnClickListenerImpl mHandlerOnStartTimeViewClickedAndroidViewViewOnClickListener;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    private final LinearLayout mboundView1;

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl implements View.OnClickListener {
        private AlertSettingsView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.onStartTimeViewClicked(view);
        }

        public OnClickListenerImpl setValue(AlertSettingsView alertSettingsView) {
            this.value = alertSettingsView;
            if (alertSettingsView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private AlertSettingsView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.onDeleteButtonClicked(view);
        }

        public OnClickListenerImpl1 setValue(AlertSettingsView alertSettingsView) {
            this.value = alertSettingsView;
            if (alertSettingsView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl2 implements View.OnClickListener {
        private AlertSettingsView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.onDeviceTypeClicked(view);
        }

        public OnClickListenerImpl2 setValue(AlertSettingsView alertSettingsView) {
            this.value = alertSettingsView;
            if (alertSettingsView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl3 implements View.OnClickListener {
        private AlertSettingsView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.onAlertConditionClicked(view);
        }

        public OnClickListenerImpl3 setValue(AlertSettingsView alertSettingsView) {
            this.value = alertSettingsView;
            if (alertSettingsView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl4 implements View.OnClickListener {
        private AlertSettingsView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.onEndTimeViewClicked(view);
        }

        public OnClickListenerImpl4 setValue(AlertSettingsView alertSettingsView) {
            this.value = alertSettingsView;
            if (alertSettingsView == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes6.dex */
    public static class OnClickListenerImpl5 implements View.OnClickListener {
        private AlertSettingsView value;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.value.onSaveButtonClicked(view);
        }

        public OnClickListenerImpl5 setValue(AlertSettingsView alertSettingsView) {
            this.value = alertSettingsView;
            if (alertSettingsView == null) {
                return null;
            }
            return this;
        }
    }

    static {
        sViewsWithIds.put(R.id.times_heading, 8);
        sViewsWithIds.put(R.id.start_time_input_picker, 9);
        sViewsWithIds.put(R.id.end_time_input_picker, 10);
        sViewsWithIds.put(R.id.button_container, 11);
    }

    public AlertFormBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 12, sIncludes, sViewsWithIds));
    }

    private boolean onChangeViewmodelGetAlertConfig(MutableLiveData<AlertConfigurationViewItemModel> mutableLiveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 2;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetDisplayEndTime(LiveData<String> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 4;
            }
            return true;
        }
        return false;
    }

    private boolean onChangeViewmodelGetDisplayStartTime(LiveData<String> liveData, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.mDirtyFlags |= 1;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00dd  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void executeBindings() {
        /*
            Method dump skipped, instructions count: 398
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.biloba.databinding.AlertFormBindingImpl.executeBindings():void");
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
            this.mDirtyFlags = 32L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            if (i == 1) {
                return onChangeViewmodelGetAlertConfig((MutableLiveData) obj, i2);
            }
            if (i == 2) {
                return onChangeViewmodelGetDisplayEndTime((LiveData) obj, i2);
            }
            return false;
        }
        return onChangeViewmodelGetDisplayStartTime((LiveData) obj, i2);
    }

    @Override // com.amazon.alexa.biloba.databinding.AlertFormBinding
    public void setHandler(@Nullable AlertSettingsView alertSettingsView) {
        this.mHandler = alertSettingsView;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.handler);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.handler == i) {
            setHandler((AlertSettingsView) obj);
        } else if (BR.viewmodel != i) {
            return false;
        } else {
            setViewmodel((AlertSettingsViewModel) obj);
        }
        return true;
    }

    @Override // com.amazon.alexa.biloba.databinding.AlertFormBinding
    public void setViewmodel(@Nullable AlertSettingsViewModel alertSettingsViewModel) {
        this.mViewmodel = alertSettingsViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.viewmodel);
        super.requestRebind();
    }

    private AlertFormBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 3, (ListItemLink) objArr[3], (LinearLayout) objArr[11], (AppCompatButton) objArr[6], (ListItemLink) objArr[2], (TimePicker) objArr[10], (ListItemLink) objArr[5], (AppCompatButton) objArr[7], (TimePicker) objArr[9], (ListItemLink) objArr[4], (SectionHeader) objArr[8]);
        this.mDirtyFlags = -1L;
        this.alertConditionListItem.setTag(null);
        this.deleteButton.setTag(null);
        this.deviceTypeWrapper.setTag(null);
        this.endTimeWrapper.setTag(null);
        this.mboundView0 = (LinearLayout) objArr[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (LinearLayout) objArr[1];
        this.mboundView1.setTag(null);
        this.saveButton.setTag(null);
        this.startTimeWrapper.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
