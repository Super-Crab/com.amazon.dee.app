package com.amazon.alexa.biloba.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.account.timeZonePicker.TimeZonePickerView;
import com.amazon.alexa.biloba.view.account.timeZonePicker.TimeZonePickerViewModel;
import com.amazon.alexa.mosaic.view.ListItemLink;
import com.amazon.alexa.mosaic.view.SectionHeader;
/* loaded from: classes6.dex */
public abstract class TimezoneListBinding extends ViewDataBinding {
    @NonNull
    public final SectionHeader careCommunicationHeading;
    @Bindable
    protected TimeZonePickerView mTimezoneView;
    @Bindable
    protected TimeZonePickerViewModel mViewmodel;
    @NonNull
    public final RecyclerView timezoneList;
    @NonNull
    public final ListItemLink timezoneRegion;

    /* JADX INFO: Access modifiers changed from: protected */
    public TimezoneListBinding(Object obj, View view, int i, SectionHeader sectionHeader, RecyclerView recyclerView, ListItemLink listItemLink) {
        super(obj, view, i);
        this.careCommunicationHeading = sectionHeader;
        this.timezoneList = recyclerView;
        this.timezoneRegion = listItemLink;
    }

    public static TimezoneListBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static TimezoneListBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public TimeZonePickerView getTimezoneView() {
        return this.mTimezoneView;
    }

    @Nullable
    public TimeZonePickerViewModel getViewmodel() {
        return this.mViewmodel;
    }

    public abstract void setTimezoneView(@Nullable TimeZonePickerView timeZonePickerView);

    public abstract void setViewmodel(@Nullable TimeZonePickerViewModel timeZonePickerViewModel);

    @Deprecated
    public static TimezoneListBinding bind(@NonNull View view, @Nullable Object obj) {
        return (TimezoneListBinding) ViewDataBinding.bind(obj, view, R.layout.timezone_list);
    }

    @NonNull
    @Deprecated
    public static TimezoneListBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (TimezoneListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.timezone_list, viewGroup, z, obj);
    }

    @NonNull
    public static TimezoneListBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static TimezoneListBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (TimezoneListBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.timezone_list, null, false, obj);
    }
}
