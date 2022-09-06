package com.amazon.dee.app.ui.menu;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import com.amazon.dee.app.R;
import com.amazon.dee.app.databinding.MainMenuItemBinding;
import com.amazon.dee.app.databinding.ViewBindingHolder;
import com.amazon.dee.app.ui.main.MainViewModel;
import com.amazon.dee.app.ui.util.ViewDelegate;
/* loaded from: classes12.dex */
public class MenuItemDelegate extends ViewDelegate<ViewBindingHolder<MainMenuItemBinding>> {
    MenuItemHandler handler;
    MainViewModel viewModel;

    public MenuItemDelegate(MenuItemHandler menuItemHandler, MainViewModel mainViewModel) {
        this.handler = menuItemHandler;
        this.viewModel = mainViewModel;
    }

    @Override // com.amazon.dee.app.ui.util.ViewDelegate
    public boolean isForItem(Object obj, int i) {
        return obj instanceof MenuItem;
    }

    @Override // com.amazon.dee.app.ui.util.ViewDelegate
    public void onBindViewHolder(ViewBindingHolder<MainMenuItemBinding> viewBindingHolder, Object obj, int i) {
        viewBindingHolder.binding.setMenuItem((MenuItem) obj);
        viewBindingHolder.binding.executePendingBindings();
    }

    @Override // com.amazon.dee.app.ui.util.ViewDelegate
    public ViewBindingHolder<MainMenuItemBinding> onCreateViewHolder(ViewGroup viewGroup) {
        MainMenuItemBinding mainMenuItemBinding = (MainMenuItemBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.main_menu_item, viewGroup, false);
        mainMenuItemBinding.setHandler(this.handler);
        mainMenuItemBinding.setViewModel(this.viewModel);
        return new ViewBindingHolder<>(mainMenuItemBinding);
    }
}
