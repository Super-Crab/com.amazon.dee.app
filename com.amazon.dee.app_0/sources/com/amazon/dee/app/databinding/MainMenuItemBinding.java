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
import com.amazon.dee.app.ui.main.MainViewModel;
import com.amazon.dee.app.ui.menu.MenuItem;
import com.amazon.dee.app.ui.menu.MenuItemHandler;
/* loaded from: classes12.dex */
public abstract class MainMenuItemBinding extends ViewDataBinding {
    @Bindable
    protected MenuItemHandler mHandler;
    @Bindable
    protected MenuItem mMenuItem;
    @Bindable
    protected MainViewModel mViewModel;

    /* JADX INFO: Access modifiers changed from: protected */
    public MainMenuItemBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public static MainMenuItemBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static MainMenuItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public MenuItemHandler getHandler() {
        return this.mHandler;
    }

    @Nullable
    public MenuItem getMenuItem() {
        return this.mMenuItem;
    }

    @Nullable
    public MainViewModel getViewModel() {
        return this.mViewModel;
    }

    public abstract void setHandler(@Nullable MenuItemHandler menuItemHandler);

    public abstract void setMenuItem(@Nullable MenuItem menuItem);

    public abstract void setViewModel(@Nullable MainViewModel mainViewModel);

    @Deprecated
    public static MainMenuItemBinding bind(@NonNull View view, @Nullable Object obj) {
        return (MainMenuItemBinding) ViewDataBinding.bind(obj, view, R.layout.main_menu_item);
    }

    @NonNull
    @Deprecated
    public static MainMenuItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (MainMenuItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.main_menu_item, viewGroup, z, obj);
    }

    @NonNull
    public static MainMenuItemBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static MainMenuItemBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (MainMenuItemBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.main_menu_item, null, false, obj);
    }
}
