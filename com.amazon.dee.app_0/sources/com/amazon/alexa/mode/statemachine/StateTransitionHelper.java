package com.amazon.alexa.mode.statemachine;

import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.mode.statemachine.state.BaseState;
import com.amazon.alexa.mode.util.LogTag;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class StateTransitionHelper {
    private static final String TAG = LogTag.forClass(StateTransitionHelper.class);
    private final List<StateTransitionCallback> mCallbacks = new ArrayList();

    /* loaded from: classes9.dex */
    public interface StateTransitionCallback {
        void onStateChange(BaseState baseState);
    }

    public void registerStateTransitionCallback(StateTransitionCallback stateTransitionCallback) {
        if (stateTransitionCallback != null && !this.mCallbacks.contains(stateTransitionCallback)) {
            this.mCallbacks.add(stateTransitionCallback);
        } else {
            Log.e(TAG, "Cannot add null or pre-existing callback");
        }
    }

    public void transition(@NonNull BaseState baseState) {
        for (StateTransitionCallback stateTransitionCallback : this.mCallbacks) {
            stateTransitionCallback.onStateChange(baseState);
        }
    }
}
