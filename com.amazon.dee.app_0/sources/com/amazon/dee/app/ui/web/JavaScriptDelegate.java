package com.amazon.dee.app.ui.web;

import androidx.annotation.Nullable;
import java.util.List;
/* loaded from: classes12.dex */
public interface JavaScriptDelegate {
    void notifyLoadingDialogDismissed();

    void onWebAppReady();

    void setDevices(@Nullable List<DeviceInfo> list);

    void setFullScreen(boolean z);

    void setHeaderTitle(@Nullable CharSequence charSequence);

    void setHeaderVisible(boolean z);

    void setHouseholdLibraries(@Nullable List<HouseholdLibraryInfo> list);

    void setHouseholdVisible(boolean z);

    void setNowPlaying(boolean z);

    void setSelectedDevice(@Nullable DeviceInfo deviceInfo);

    void setSelectedLibrary(@Nullable HouseholdLibraryInfo householdLibraryInfo);

    void setYourSkillsTitleAndDisplay(@Nullable CharSequence charSequence, boolean z);

    void setYourSkillsVisible(boolean z);
}
