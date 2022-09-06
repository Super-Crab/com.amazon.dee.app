package com.amazon.alexa.accessorykit;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
/* loaded from: classes6.dex */
public class NativeContainerProvider implements ContainerProvider {
    @Override // com.amazon.alexa.accessorykit.ContainerProvider
    public WritableArray getArray() {
        return Arguments.createArray();
    }

    @Override // com.amazon.alexa.accessorykit.ContainerProvider
    public WritableMap getMap() {
        return Arguments.createMap();
    }
}
