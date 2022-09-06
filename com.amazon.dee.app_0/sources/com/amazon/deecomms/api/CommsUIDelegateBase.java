package com.amazon.deecomms.api;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.api.navigation.FragmentRequirements;
/* loaded from: classes12.dex */
public abstract class CommsUIDelegateBase {
    public abstract void addHomeToBackStackIfEmpty();

    public abstract void configurePageForFragment(FragmentRequirements fragmentRequirements);

    public abstract void fragmentNavigationRequested(CommsView commsView, Fragment fragment, Bundle bundle, boolean z);

    public abstract boolean isCurrentlyOnCommsScreen();

    public abstract void navigateBackward();

    public abstract void navigateHome();

    public abstract void navigateSettings();

    public abstract void navigateToView(CommsView commsView, Bundle bundle, boolean z);

    public abstract void navigateUpward();

    public abstract boolean removeCommsRoutesFromBackstack();

    public abstract void setIndicatorIconVisible(boolean z);

    public abstract void setUserName(String str);

    public abstract void signOut();

    @Deprecated
    public abstract void voxCallInitiated();
}
