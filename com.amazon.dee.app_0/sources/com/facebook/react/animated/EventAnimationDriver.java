package com.facebook.react.animated;

import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import java.util.List;
/* loaded from: classes2.dex */
class EventAnimationDriver implements RCTEventEmitter {
    private List<String> mEventPath;
    ValueAnimatedNode mValueNode;

    public EventAnimationDriver(List<String> list, ValueAnimatedNode valueAnimatedNode) {
        this.mEventPath = list;
        this.mValueNode = valueAnimatedNode;
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveEvent(int i, String str, @Nullable WritableMap writableMap) {
        if (writableMap != null) {
            int i2 = 0;
            WritableMap writableMap2 = writableMap;
            while (i2 < this.mEventPath.size() - 1) {
                i2++;
                writableMap2 = writableMap2.mo6945getMap(this.mEventPath.get(i2));
            }
            this.mValueNode.mValue = writableMap2.getDouble((String) GeneratedOutlineSupport1.outline24(this.mEventPath, -1));
            return;
        }
        throw new IllegalArgumentException("Native animated events must have event data.");
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveTouches(String str, WritableArray writableArray, WritableArray writableArray2) {
        throw new RuntimeException("receiveTouches is not support by native animated events");
    }
}
