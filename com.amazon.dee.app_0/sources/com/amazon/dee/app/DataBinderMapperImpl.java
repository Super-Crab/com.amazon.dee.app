package com.amazon.dee.app;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.dee.app.databinding.AlexaDeviceBackgroundImageBindingImpl;
import com.amazon.dee.app.databinding.ExternalUiBindingImpl;
import com.amazon.dee.app.databinding.LoadingProgressDialogBindingImpl;
import com.amazon.dee.app.databinding.MainBindingImpl;
import com.amazon.dee.app.databinding.MainDeviceItemBindingImpl;
import com.amazon.dee.app.databinding.MainDeviceMenuItemBindingImpl;
import com.amazon.dee.app.databinding.MainDeviceMenuItemForMusicBindingImpl;
import com.amazon.dee.app.databinding.MainHouseholdItemBindingImpl;
import com.amazon.dee.app.databinding.MainHouseholdMenuItemBindingImpl;
import com.amazon.dee.app.databinding.MainMenuItemBindingImpl;
import com.amazon.dee.app.databinding.VideoBindingImpl;
import com.amazon.dee.app.databinding.ViewboxFragmentBindingImpl;
import com.amazon.dee.app.databinding.ViewboxFragmentBindingLandImpl;
import com.amazon.dee.app.databinding.VoiceActivityBindingImpl;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes12.dex */
public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(13);
    private static final int LAYOUT_ALEXADEVICEBACKGROUNDIMAGE = 1;
    private static final int LAYOUT_EXTERNALUI = 2;
    private static final int LAYOUT_LOADINGPROGRESSDIALOG = 3;
    private static final int LAYOUT_MAIN = 4;
    private static final int LAYOUT_MAINDEVICEITEM = 5;
    private static final int LAYOUT_MAINDEVICEMENUITEM = 6;
    private static final int LAYOUT_MAINDEVICEMENUITEMFORMUSIC = 7;
    private static final int LAYOUT_MAINHOUSEHOLDITEM = 8;
    private static final int LAYOUT_MAINHOUSEHOLDMENUITEM = 9;
    private static final int LAYOUT_MAINMENUITEM = 10;
    private static final int LAYOUT_VIDEO = 11;
    private static final int LAYOUT_VIEWBOXFRAGMENT = 12;
    private static final int LAYOUT_VOICEACTIVITY = 13;

    /* loaded from: classes12.dex */
    private static class InnerBrLookup {
        static final SparseArray<String> sKeys = new SparseArray<>(31);

        static {
            sKeys.put(0, "_all");
            sKeys.put(1, "controlButtonState");
            sKeys.put(2, "messageText");
            sKeys.put(3, "borderVisible");
            sKeys.put(4, "barButtonHandlers");
            sKeys.put(5, "pauseResumeClickable");
            sKeys.put(6, "barBorderVisible");
            sKeys.put(7, HttpClientModule.WritableMapEncodingDataServiceObserver.ElementsResponseKey.STATUS_TEXT);
            sKeys.put(8, "overflowMenuVisible");
            sKeys.put(9, "buttonHandlers");
            sKeys.put(10, "notificationVisible");
            sKeys.put(11, "handler");
            sKeys.put(12, "viewmodel");
            sKeys.put(13, "timezone_view");
            sKeys.put(14, "membershipView");
            sKeys.put(15, "activityItem");
            sKeys.put(16, "membership_view");
            sKeys.put(17, "alert");
            sKeys.put(18, "noticeItem");
            sKeys.put(19, "viewModel");
            sKeys.put(20, "activityMessage");
            sKeys.put(21, "profile_settings_view");
            sKeys.put(22, "activityHeader");
            sKeys.put(23, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_CARD);
            sKeys.put(24, "timezoneView");
            sKeys.put(25, "profileSettingsView");
            sKeys.put(26, "toolbarViewModel");
            sKeys.put(27, "library");
            sKeys.put(28, "model");
            sKeys.put(29, "menuItem");
        }

        private InnerBrLookup() {
        }
    }

    /* loaded from: classes12.dex */
    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys = new HashMap<>(14);

        static {
            sKeys.put("layout/alexa_device_background_image_0", Integer.valueOf((int) R.layout.alexa_device_background_image));
            sKeys.put("layout/external_ui_0", Integer.valueOf((int) R.layout.external_ui));
            sKeys.put("layout/loading_progress_dialog_0", Integer.valueOf((int) R.layout.loading_progress_dialog));
            sKeys.put("layout/main_0", Integer.valueOf((int) R.layout.main));
            sKeys.put("layout/main_device_item_0", Integer.valueOf((int) R.layout.main_device_item));
            sKeys.put("layout/main_device_menu_item_0", Integer.valueOf((int) R.layout.main_device_menu_item));
            sKeys.put("layout/main_device_menu_item_for_music_0", Integer.valueOf((int) R.layout.main_device_menu_item_for_music));
            sKeys.put("layout/main_household_item_0", Integer.valueOf((int) R.layout.main_household_item));
            sKeys.put("layout/main_household_menu_item_0", Integer.valueOf((int) R.layout.main_household_menu_item));
            sKeys.put("layout/main_menu_item_0", Integer.valueOf((int) R.layout.main_menu_item));
            sKeys.put("layout/video_0", Integer.valueOf((int) R.layout.video));
            HashMap<String, Integer> hashMap = sKeys;
            Integer valueOf = Integer.valueOf((int) R.layout.viewbox_fragment);
            hashMap.put("layout/viewbox_fragment_0", valueOf);
            sKeys.put("layout-land/viewbox_fragment_0", valueOf);
            sKeys.put("layout/voice_activity_0", Integer.valueOf((int) R.layout.voice_activity));
        }

        private InnerLayoutIdLookup() {
        }
    }

    static {
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.alexa_device_background_image, 1);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.external_ui, 2);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.loading_progress_dialog, 3);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.main, 4);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.main_device_item, 5);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.main_device_menu_item, 6);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.main_device_menu_item_for_music, 7);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.main_household_item, 8);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.main_household_menu_item, 9);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.main_menu_item, 10);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.video, 11);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.viewbox_fragment, 12);
        INTERNAL_LAYOUT_ID_LOOKUP.put(R.layout.voice_activity, 13);
    }

    @Override // androidx.databinding.DataBinderMapper
    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.amazon.alexa.biloba.DataBinderMapperImpl());
        arrayList.add(new com.amazon.alexa.tarazed.service.DataBinderMapperImpl());
        arrayList.add(new com.amazon.tarazed.DataBinderMapperImpl());
        arrayList.add(new com.amazon.tarazed.core.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.DataBinderMapper
    public String convertBrIdToString(int i) {
        return InnerBrLookup.sKeys.get(i);
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 > 0) {
            Object tag = view.getTag();
            if (tag != null) {
                switch (i2) {
                    case 1:
                        if ("layout/alexa_device_background_image_0".equals(tag)) {
                            return new AlexaDeviceBackgroundImageBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for alexa_device_background_image is invalid. Received: ", tag));
                    case 2:
                        if ("layout/external_ui_0".equals(tag)) {
                            return new ExternalUiBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for external_ui is invalid. Received: ", tag));
                    case 3:
                        if ("layout/loading_progress_dialog_0".equals(tag)) {
                            return new LoadingProgressDialogBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for loading_progress_dialog is invalid. Received: ", tag));
                    case 4:
                        if ("layout/main_0".equals(tag)) {
                            return new MainBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for main is invalid. Received: ", tag));
                    case 5:
                        if ("layout/main_device_item_0".equals(tag)) {
                            return new MainDeviceItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for main_device_item is invalid. Received: ", tag));
                    case 6:
                        if ("layout/main_device_menu_item_0".equals(tag)) {
                            return new MainDeviceMenuItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for main_device_menu_item is invalid. Received: ", tag));
                    case 7:
                        if ("layout/main_device_menu_item_for_music_0".equals(tag)) {
                            return new MainDeviceMenuItemForMusicBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for main_device_menu_item_for_music is invalid. Received: ", tag));
                    case 8:
                        if ("layout/main_household_item_0".equals(tag)) {
                            return new MainHouseholdItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for main_household_item is invalid. Received: ", tag));
                    case 9:
                        if ("layout/main_household_menu_item_0".equals(tag)) {
                            return new MainHouseholdMenuItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for main_household_menu_item is invalid. Received: ", tag));
                    case 10:
                        if ("layout/main_menu_item_0".equals(tag)) {
                            return new MainMenuItemBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for main_menu_item is invalid. Received: ", tag));
                    case 11:
                        if ("layout/video_0".equals(tag)) {
                            return new VideoBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for video is invalid. Received: ", tag));
                    case 12:
                        if ("layout/viewbox_fragment_0".equals(tag)) {
                            return new ViewboxFragmentBindingImpl(dataBindingComponent, view);
                        }
                        if ("layout-land/viewbox_fragment_0".equals(tag)) {
                            return new ViewboxFragmentBindingLandImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for viewbox_fragment is invalid. Received: ", tag));
                    case 13:
                        if ("layout/voice_activity_0".equals(tag)) {
                            return new VoiceActivityBindingImpl(dataBindingComponent, view);
                        }
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline70("The tag for voice_activity is invalid. Received: ", tag));
                    default:
                        return null;
                }
            }
            throw new RuntimeException("view must have a tag");
        }
        return null;
    }

    @Override // androidx.databinding.DataBinderMapper
    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = InnerLayoutIdLookup.sKeys.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    @Override // androidx.databinding.DataBinderMapper
    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }
}
