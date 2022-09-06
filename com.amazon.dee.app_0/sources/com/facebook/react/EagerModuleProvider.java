package com.facebook.react;

import com.facebook.react.bridge.NativeModule;
import javax.inject.Provider;
/* loaded from: classes2.dex */
public class EagerModuleProvider implements Provider<NativeModule> {
    private final NativeModule mModule;

    public EagerModuleProvider(NativeModule nativeModule) {
        this.mModule = nativeModule;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // javax.inject.Provider
    /* renamed from: get */
    public NativeModule mo10268get() {
        return this.mModule;
    }
}
