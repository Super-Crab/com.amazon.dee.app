package com.amazon.matter;

import android.content.Context;
import chip.devicecontroller.ChipDeviceController;
import chip.devicecontroller.PreferencesKeyValueStoreManager;
import chip.devicecontroller.mdns.ChipMdnsCallbackImpl;
import chip.devicecontroller.mdns.NsdManagerServiceResolver;
/* loaded from: classes8.dex */
public final class MatterClient {
    private static ChipDeviceController mMatterDeviceController;

    private MatterClient() {
    }

    public static ChipDeviceController getMatterClient(Context context) {
        if (mMatterDeviceController == null) {
            mMatterDeviceController = new ChipDeviceController(new PreferencesKeyValueStoreManager(context), new NsdManagerServiceResolver(context), new ChipMdnsCallbackImpl());
        }
        return mMatterDeviceController;
    }
}
