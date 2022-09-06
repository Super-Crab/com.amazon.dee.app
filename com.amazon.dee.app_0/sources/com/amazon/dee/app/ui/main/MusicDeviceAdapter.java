package com.amazon.dee.app.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import com.amazon.dee.app.R;
import com.amazon.dee.app.databinding.MainDeviceMenuItemForMusicBinding;
/* loaded from: classes12.dex */
public class MusicDeviceAdapter extends BaseDeviceAdapter {
    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        MainDeviceMenuItemForMusicBinding mainDeviceMenuItemForMusicBinding = (MainDeviceMenuItemForMusicBinding) DataBindingUtil.getBinding(view);
        if (mainDeviceMenuItemForMusicBinding == null) {
            mainDeviceMenuItemForMusicBinding = (MainDeviceMenuItemForMusicBinding) DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.main_device_menu_item_for_music, viewGroup, false);
        }
        mainDeviceMenuItemForMusicBinding.setModel(this.items.get(i));
        mainDeviceMenuItemForMusicBinding.executePendingBindings();
        return mainDeviceMenuItemForMusicBinding.getRoot();
    }
}
