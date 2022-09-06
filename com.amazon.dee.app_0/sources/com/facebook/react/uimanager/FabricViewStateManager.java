package com.facebook.react.uimanager;

import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.config.ReactFeatureFlags;
/* loaded from: classes2.dex */
public class FabricViewStateManager {
    private static final String TAG = "FabricViewStateManager";
    @Nullable
    private StateWrapper mStateWrapper = null;

    /* loaded from: classes2.dex */
    public interface HasFabricViewStateManager {
        FabricViewStateManager getFabricViewStateManager();
    }

    /* loaded from: classes2.dex */
    public interface StateUpdateCallback {
        WritableMap getStateUpdate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setState(@Nullable final StateWrapper stateWrapper, final StateUpdateCallback stateUpdateCallback, final int i) {
        if (stateWrapper == null) {
            FLog.e(TAG, "setState called without a StateWrapper");
        } else if (stateWrapper != this.mStateWrapper || i > 60) {
        } else {
            Runnable runnable = null;
            if (ReactFeatureFlags.enableExperimentalStateUpdateRetry) {
                runnable = new Runnable() { // from class: com.facebook.react.uimanager.FabricViewStateManager.1
                    @Override // java.lang.Runnable
                    public void run() {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UpdateState failed - retrying! ");
                        outline107.append(i);
                        FLog.e(FabricViewStateManager.TAG, outline107.toString());
                        FabricViewStateManager.this.setState(stateWrapper, stateUpdateCallback, i + 1);
                    }
                };
            }
            WritableMap stateUpdate = stateUpdateCallback.getStateUpdate();
            if (stateUpdate == null) {
                return;
            }
            stateWrapper.updateState(stateUpdate, runnable);
        }
    }

    @Nullable
    public ReadableMap getState() {
        StateWrapper stateWrapper = this.mStateWrapper;
        if (stateWrapper != null) {
            return stateWrapper.getState();
        }
        return null;
    }

    public boolean hasStateWrapper() {
        return this.mStateWrapper != null;
    }

    public void setStateWrapper(StateWrapper stateWrapper) {
        this.mStateWrapper = stateWrapper;
    }

    public void setState(StateUpdateCallback stateUpdateCallback) {
        setState(this.mStateWrapper, stateUpdateCallback, 0);
    }
}
