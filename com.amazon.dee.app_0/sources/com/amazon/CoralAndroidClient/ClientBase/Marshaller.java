package com.amazon.CoralAndroidClient.ClientBase;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.Exception.NativeException;
/* loaded from: classes.dex */
public interface Marshaller<T extends ClientInput> {
    ClientRequest marshal(T t) throws NativeException;
}
