package com.amazon.alexa.accessory.engagement;
@FunctionalInterface
/* loaded from: classes.dex */
interface AdapterFactory<S, T> {
    T createFrom(S s);
}
