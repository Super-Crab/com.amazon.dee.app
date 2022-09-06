package com.amazon.alexa.accessorykit.accessories;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import java.io.NotSerializableException;
import java.text.ParseException;
/* loaded from: classes6.dex */
public interface MapModelTransformer<T> {
    /* renamed from: transform */
    T mo626transform(ReadableMap readableMap) throws ParseException;

    WritableMap transformToMap(T t) throws NotSerializableException;
}
