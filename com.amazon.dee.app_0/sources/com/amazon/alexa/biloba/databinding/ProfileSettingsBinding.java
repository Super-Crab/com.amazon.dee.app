package com.amazon.alexa.biloba.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.view.account.ProfileSettingsView;
import com.amazon.alexa.biloba.view.account.ProfileSettingsViewModel;
import com.amazon.alexa.font.FontTextView;
import com.amazon.alexa.mosaic.view.ErrorView;
import com.amazon.alexa.mosaic.view.ListItemLink;
/* loaded from: classes6.dex */
public abstract class ProfileSettingsBinding extends ViewDataBinding {
    @NonNull
    public final FontTextView dropInDescription;
    @NonNull
    public final FontTextView dropInDisabledLearnMoreDescripiton;
    @NonNull
    public final FontTextView dropInDisabledLearnMoreLink;
    @NonNull
    public final FontTextView dropInManageDescription;
    @NonNull
    public final FontTextView dropInManageLink;
    @NonNull
    public final FontTextView dropInWhenCommsDisabled;
    @NonNull
    public final LinearLayout dropInWhenCommsEnabled;
    @NonNull
    public final ErrorView errorView;
    @NonNull
    public final View loadingView;
    @Bindable
    protected ProfileSettingsView mProfileSettingsView;
    @Bindable
    protected ProfileSettingsViewModel mViewmodel;
    @NonNull
    public final ListItemLink profileSettingsAlexaCommunicationsEnabled;
    @NonNull
    public final ListItemLink profileSettingsAlexaEmergencySettingsEnabled;
    @NonNull
    public final ListItemLink profileSettingsCareRelationshipName;
    @NonNull
    public final ListItemLink profileSettingsTimezone;
    @NonNull
    public final FontTextView subscriptionAndPaymentDescription;

    /* JADX INFO: Access modifiers changed from: protected */
    public ProfileSettingsBinding(Object obj, View view, int i, FontTextView fontTextView, FontTextView fontTextView2, FontTextView fontTextView3, FontTextView fontTextView4, FontTextView fontTextView5, FontTextView fontTextView6, LinearLayout linearLayout, ErrorView errorView, View view2, ListItemLink listItemLink, ListItemLink listItemLink2, ListItemLink listItemLink3, ListItemLink listItemLink4, FontTextView fontTextView7) {
        super(obj, view, i);
        this.dropInDescription = fontTextView;
        this.dropInDisabledLearnMoreDescripiton = fontTextView2;
        this.dropInDisabledLearnMoreLink = fontTextView3;
        this.dropInManageDescription = fontTextView4;
        this.dropInManageLink = fontTextView5;
        this.dropInWhenCommsDisabled = fontTextView6;
        this.dropInWhenCommsEnabled = linearLayout;
        this.errorView = errorView;
        this.loadingView = view2;
        this.profileSettingsAlexaCommunicationsEnabled = listItemLink;
        this.profileSettingsAlexaEmergencySettingsEnabled = listItemLink2;
        this.profileSettingsCareRelationshipName = listItemLink3;
        this.profileSettingsTimezone = listItemLink4;
        this.subscriptionAndPaymentDescription = fontTextView7;
    }

    public static ProfileSettingsBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ProfileSettingsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public ProfileSettingsView getProfileSettingsView() {
        return this.mProfileSettingsView;
    }

    @Nullable
    public ProfileSettingsViewModel getViewmodel() {
        return this.mViewmodel;
    }

    public abstract void setProfileSettingsView(@Nullable ProfileSettingsView profileSettingsView);

    public abstract void setViewmodel(@Nullable ProfileSettingsViewModel profileSettingsViewModel);

    @Deprecated
    public static ProfileSettingsBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ProfileSettingsBinding) ViewDataBinding.bind(obj, view, R.layout.profile_settings);
    }

    @NonNull
    @Deprecated
    public static ProfileSettingsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ProfileSettingsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.profile_settings, viewGroup, z, obj);
    }

    @NonNull
    public static ProfileSettingsBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ProfileSettingsBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ProfileSettingsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.profile_settings, null, false, obj);
    }
}
