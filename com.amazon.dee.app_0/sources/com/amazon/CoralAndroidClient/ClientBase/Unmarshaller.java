package com.amazon.CoralAndroidClient.ClientBase;

import com.amazon.CoralAndroidClient.Exception.CoralException;
import com.amazon.CoralAndroidClient.Exception.NativeException;
/* loaded from: classes.dex */
public interface Unmarshaller {
    CoralException UnmarshalException(String str) throws NativeException;

    ClientOutput UnmarshalOutput(String str) throws NativeException;
}
