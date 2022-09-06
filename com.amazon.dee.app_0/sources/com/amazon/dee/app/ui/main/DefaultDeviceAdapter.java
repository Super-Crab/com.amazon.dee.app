package com.amazon.dee.app.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import com.amazon.dee.app.R;
import com.amazon.dee.app.databinding.MainDeviceMenuItemBinding;
/* loaded from: classes12.dex */
public class DefaultDeviceAdapter extends BaseDeviceAdapter {
    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        MainDeviceMenuItemBinding mainDeviceMenuItemBinding = (MainDeviceMenuItemBinding) DataBindingUtil.getBinding(view);
        if (mainDeviceMenuItemBinding == null) {
            mainDeviceMenuItemBinding = (MainDeviceMenuItemBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.main_device_menu_item, viewGroup, false);
        }
        mainDeviceMenuItemBinding.setModel(this.items.get(i));
        mainDeviceMenuItemBinding.executePendingBindings();
        return mainDeviceMenuItemBinding.getRoot();
    }
}
