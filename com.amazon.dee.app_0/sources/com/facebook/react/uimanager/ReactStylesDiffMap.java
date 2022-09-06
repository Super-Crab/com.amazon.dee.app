package com.facebook.react.uimanager;

import androidx.annotation.Nullable;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class ReactStylesDiffMap {
    final ReadableMap mBackingMap;

    public ReactStylesDiffMap(ReadableMap readableMap) {
        this.mBackingMap = readableMap;
    }

    @Nullable
    public ReadableArray getArray(String str) {
        return this.mBackingMap.getArray(str);
    }

    public boolean getBoolean(String str, boolean z) {
        return this.mBackingMap.isNull(str) ? z : this.mBackingMap.getBoolean(str);
    }

    public double getDouble(String str, double d) {
        return this.mBackingMap.isNull(str) ? d : this.mBackingMap.getDouble(str);
    }

    @Nullable
    public Dynamic getDynamic(String str) {
        return this.mBackingMap.getDynamic(str);
    }

    public float getFloat(String str, float f) {
        return this.mBackingMap.isNull(str) ? f : (float) this.mBackingMap.getDouble(str);
    }

    public int getInt(String str, int i) {
        return this.mBackingMap.isNull(str) ? i : this.mBackingMap.getInt(str);
    }

    @Nullable
    public ReadableMap getMap(String str) {
        return this.mBackingMap.mo6945getMap(str);
    }

    @Nullable
    public String getString(String str) {
        return this.mBackingMap.getString(str);
    }

    public boolean hasKey(String str) {
        return this.mBackingMap.hasKey(str);
    }

    public boolean isNull(String str) {
        return this.mBackingMap.isNull(str);
    }

    public Map<String, Object> toMap() {
        return this.mBackingMap.toHashMap();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("{ ");
        outline107.append(ReactStylesDiffMap.class.getSimpleName());
        outline107.append(RealTimeTextConstants.COLON_SPACE);
        outline107.append(this.mBackingMap.toString());
        outline107.append(" }");
        return outline107.toString();
    }
}
