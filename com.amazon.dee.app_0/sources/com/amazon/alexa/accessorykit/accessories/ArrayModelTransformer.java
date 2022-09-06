package com.amazon.alexa.accessorykit.accessories;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import java.io.NotSerializableException;
import java.text.ParseException;
/* loaded from: classes6.dex */
public interface ArrayModelTransformer<T> {
    /* renamed from: transform */
    T mo597transform(ReadableArray readableArray) throws ParseException;

    WritableArray transformToArray(T t) throws NotSerializableException;
}
