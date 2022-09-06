package com.amazon.deecomms;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.amazon.deecomms.api.CommsUIDelegateBase;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.api.navigation.FragmentRequirements;
/* loaded from: classes12.dex */
public class DefaultCommsUIDelegate extends CommsUIDelegateBase {
    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public void addHomeToBackStackIfEmpty() {
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public void configurePageForFragment(FragmentRequirements fragmentRequirements) {
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public void fragmentNavigationRequested(CommsView commsView, Fragment fragment, Bundle bundle, boolean z) {
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public boolean isCurrentlyOnCommsScreen() {
        return false;
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public void navigateBackward() {
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public void navigateHome() {
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public void navigateSettings() {
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public void navigateToView(CommsView commsView, Bundle bundle, boolean z) {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public void navigateUpward() {
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public boolean removeCommsRoutesFromBackstack() {
        return false;
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public void setIndicatorIconVisible(boolean z) {
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public void setUserName(String str) {
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    public void signOut() {
    }

    @Override // com.amazon.deecomms.api.CommsUIDelegateBase
    @Deprecated
    public void voxCallInitiated() {
    }
}
