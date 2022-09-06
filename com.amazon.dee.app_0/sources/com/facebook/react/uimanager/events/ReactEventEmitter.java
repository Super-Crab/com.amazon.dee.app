package com.facebook.react.uimanager.events;

import android.util.SparseArray;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftException;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.common.ViewUtil;
/* loaded from: classes2.dex */
public class ReactEventEmitter implements RCTEventEmitter {
    private static final String TAG = "ReactEventEmitter";
    private final SparseArray<RCTEventEmitter> mEventEmitters = new SparseArray<>();
    private final ReactApplicationContext mReactContext;

    public ReactEventEmitter(ReactApplicationContext reactApplicationContext) {
        this.mReactContext = reactApplicationContext;
    }

    @Nullable
    private RCTEventEmitter getEventEmitter(int i) {
        int uIManagerType = ViewUtil.getUIManagerType(i);
        RCTEventEmitter rCTEventEmitter = this.mEventEmitters.get(uIManagerType);
        if (rCTEventEmitter == null) {
            FLog.e(TAG, "Unable to find event emitter for reactTag: %d - uiManagerType: %d", Integer.valueOf(i), Integer.valueOf(uIManagerType));
            if (this.mReactContext.hasActiveCatalystInstance()) {
                return (RCTEventEmitter) this.mReactContext.getJSModule(RCTEventEmitter.class);
            }
            ReactSoftException.logSoftException(TAG, new ReactNoCrashSoftException(GeneratedOutlineSupport1.outline54("Cannot get RCTEventEmitter from Context for reactTag: ", i, " - uiManagerType: ", uIManagerType, " - No active Catalyst instance!")));
            return rCTEventEmitter;
        }
        return rCTEventEmitter;
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveEvent(int i, String str, @Nullable WritableMap writableMap) {
        RCTEventEmitter eventEmitter = getEventEmitter(i);
        if (eventEmitter != null) {
            eventEmitter.receiveEvent(i, str, writableMap);
        }
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveTouches(String str, WritableArray writableArray, WritableArray writableArray2) {
        Assertions.assertCondition(writableArray.size() > 0);
        RCTEventEmitter eventEmitter = getEventEmitter(writableArray.mo6944getMap(0).getInt("target"));
        if (eventEmitter != null) {
            eventEmitter.receiveTouches(str, writableArray, writableArray2);
        }
    }

    public void register(int i, RCTEventEmitter rCTEventEmitter) {
        this.mEventEmitters.put(i, rCTEventEmitter);
    }

    public void unregister(int i) {
        this.mEventEmitters.remove(i);
    }
}
