package com.amazon.alexa.voice.elements;

import androidx.annotation.NonNull;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
/* loaded from: classes11.dex */
public final class ReactCollectionsFactory implements CollectionsFactory {
    @Override // com.amazon.alexa.voice.elements.CollectionsFactory
    @NonNull
    public WritableArray createArray() {
        return Arguments.createArray();
    }

    @Override // com.amazon.alexa.voice.elements.CollectionsFactory
    @NonNull
    public WritableMap createMap() {
        return Arguments.createMap();
    }
}
