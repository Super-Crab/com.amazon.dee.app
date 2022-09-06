package com.amazon.alexa.biloba.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.alerts.edit.AlertSettingsView;
import com.amazon.alexa.biloba.view.alerts.edit.AlertSettingsViewModel;
import com.amazon.alexa.mosaic.view.ListItemLink;
import com.amazon.alexa.mosaic.view.SectionHeader;
/* loaded from: classes6.dex */
public abstract class AlertFormBinding extends ViewDataBinding {
    @NonNull
    public final ListItemLink alertConditionListItem;
    @NonNull
    public final LinearLayout buttonContainer;
    @NonNull
    public final AppCompatButton deleteButton;
    @NonNull
    public final ListItemLink deviceTypeWrapper;
    @NonNull
    public final TimePicker endTimeInputPicker;
    @NonNull
    public final ListItemLink endTimeWrapper;
    @Bindable
    protected AlertSettingsView mHandler;
    @Bindable
    protected AlertSettingsViewModel mViewmodel;
    @NonNull
    public final AppCompatButton saveButton;
    @NonNull
    public final TimePicker startTimeInputPicker;
    @NonNull
    public final ListItemLink startTimeWrapper;
    @NonNull
    public final SectionHeader timesHeading;

    /* JADX INFO: Access modifiers changed from: protected */
    public AlertFormBinding(Object obj, View view, int i, ListItemLink listItemLink, LinearLayout linearLayout, AppCompatButton appCompatButton, ListItemLink listItemLink2, TimePicker timePicker, ListItemLink listItemLink3, AppCompatButton appCompatButton2, TimePicker timePicker2, ListItemLink listItemLink4, SectionHeader sectionHeader) {
        super(obj, view, i);
        this.alertConditionListItem = listItemLink;
        this.buttonContainer = linearLayout;
        this.deleteButton = appCompatButton;
        this.deviceTypeWrapper = listItemLink2;
        this.endTimeInputPicker = timePicker;
        this.endTimeWrapper = listItemLink3;
        this.saveButton = appCompatButton2;
        this.startTimeInputPicker = timePicker2;
        this.startTimeWrapper = listItemLink4;
        this.timesHeading = sectionHeader;
    }

    public static AlertFormBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static AlertFormBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public AlertSettingsView getHandler() {
        return this.mHandler;
    }

    @Nullable
    public AlertSettingsViewModel getViewmodel() {
        return this.mViewmodel;
    }

    public abstract void setHandler(@Nullable AlertSettingsView alertSettingsView);

    public abstract void setViewmodel(@Nullable AlertSettingsViewModel alertSettingsViewModel);

    @Deprecated
    public static AlertFormBinding bind(@NonNull View view, @Nullable Object obj) {
        return (AlertFormBinding) ViewDataBinding.bind(obj, view, R.layout.alert_form);
    }

    @NonNull
    @Deprecated
    public static AlertFormBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (AlertFormBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.alert_form, viewGroup, z, obj);
    }

    @NonNull
    public static AlertFormBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static AlertFormBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (AlertFormBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.alert_form, null, false, obj);
    }
}
