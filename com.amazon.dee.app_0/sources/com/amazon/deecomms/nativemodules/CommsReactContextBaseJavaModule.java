package com.amazon.deecomms.nativemodules;

import android.app.Activity;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.CommsMasterFragment;
import com.amazon.deecomms.common.Constants;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
/* loaded from: classes12.dex */
abstract class CommsReactContextBaseJavaModule extends ReactContextBaseJavaModule {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsReactContextBaseJavaModule.class);

    /* JADX INFO: Access modifiers changed from: package-private */
    public CommsReactContextBaseJavaModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Nullable
    CommsMasterFragment findCommsMasterFragment() {
        return getCommsMasterFragment(getFragmentManagerFromActivity(getCurrentActivity()));
    }

    @Nullable
    protected CommsMasterFragment getCommsMasterFragment(@Nullable FragmentManager fragmentManager) {
        if (fragmentManager == null) {
            LOG.d("Fragment manager is null");
            return null;
        }
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (fragment instanceof CommsMasterFragment) {
                return (CommsMasterFragment) fragment;
            }
        }
        LOG.d("Could not find comms master fragment");
        return null;
    }

    @Nullable
    protected FragmentManager getFragmentManagerFromActivity(@Nullable Activity activity) {
        if (!(activity instanceof FragmentActivity)) {
            LOG.d("currentActivity is not a fragment activity");
            return null;
        }
        return ((FragmentActivity) activity).getSupportFragmentManager();
    }
}
