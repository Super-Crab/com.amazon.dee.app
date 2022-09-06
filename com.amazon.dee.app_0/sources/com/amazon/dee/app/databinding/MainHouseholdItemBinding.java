package com.amazon.dee.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.dee.app.R;
import com.amazon.dee.app.ui.web.HouseholdLibraryInfo;
/* loaded from: classes12.dex */
public abstract class MainHouseholdItemBinding extends ViewDataBinding {
    @Bindable
    protected HouseholdLibraryInfo mLibrary;

    /* JADX INFO: Access modifiers changed from: protected */
    public MainHouseholdItemBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public static MainHouseholdItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MainHouseholdItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public HouseholdLibraryInfo getLibrary() {
        return this.mLibrary;
    }

    public abstract void setLibrary(@Nullable HouseholdLibraryInfo householdLibraryInfo);

    @Deprecated
    public static MainHouseholdItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MainHouseholdItemBinding) ViewDataBinding.bind(obj, view, R.layout.main_household_item);
    }

    @NonNull
    @Deprecated
    public static MainHouseholdItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MainHouseholdItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.main_household_item, viewGroup, z, obj);
    }

    @NonNull
    public static MainHouseholdItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MainHouseholdItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MainHouseholdItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.main_household_item, null, false, obj);
    }
}
