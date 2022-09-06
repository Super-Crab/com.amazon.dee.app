package com.facebook.react.bridge;

import androidx.annotation.NonNull;
import java.util.ArrayList;
/* loaded from: classes2.dex */
public interface ReadableArray {
    @NonNull
    /* renamed from: getArray */
    ReadableArray mo6943getArray(int i);

    boolean getBoolean(int i);

    double getDouble(int i);

    @NonNull
    Dynamic getDynamic(int i);

    int getInt(int i);

    @NonNull
    /* renamed from: getMap */
    ReadableMap mo6944getMap(int i);

    @NonNull
    String getString(int i);

    @NonNull
    ReadableType getType(int i);

    boolean isNull(int i);

    int size();

    @NonNull
    ArrayList<Object> toArrayList();
}
