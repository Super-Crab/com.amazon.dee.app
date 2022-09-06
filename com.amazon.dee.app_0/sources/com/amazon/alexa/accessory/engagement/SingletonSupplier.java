package com.amazon.alexa.accessory.engagement;
/* loaded from: classes.dex */
abstract class SingletonSupplier<T> {
    private volatile T instance;

    /* renamed from: generate */
    abstract T mo304generate();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized T supply() {
        if (this.instance == null) {
            this.instance = mo304generate();
        }
        return this.instance;
    }
}
