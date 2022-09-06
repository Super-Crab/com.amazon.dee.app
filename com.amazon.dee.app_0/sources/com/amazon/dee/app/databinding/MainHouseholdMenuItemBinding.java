package com.amazon.dee.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.dee.app.R;
/* loaded from: classes12.dex */
public abstract class MainHouseholdMenuItemBinding extends ViewDataBinding {
    /* JADX INFO: Access modifiers changed from: protected */
    public MainHouseholdMenuItemBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public static MainHouseholdMenuItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MainHouseholdMenuItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static MainHouseholdMenuItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MainHouseholdMenuItemBinding) ViewDataBinding.bind(obj, view, R.layout.main_household_menu_item);
    }

    @NonNull
    @Deprecated
    public static MainHouseholdMenuItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MainHouseholdMenuItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.main_household_menu_item, viewGroup, z, obj);
    }

    @NonNull
    public static MainHouseholdMenuItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MainHouseholdMenuItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MainHouseholdMenuItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.main_household_menu_item, null, false, obj);
    }
}
