package com.amazon.alexa;

import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.alexaservice.networking.adapters.ComponentStateAdapter;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.HashMap;
/* compiled from: NetworkingModule_ProvidesComponentStateAdapterFactory.java */
/* loaded from: classes.dex */
public final class kUv implements Factory<ComponentStateAdapter> {
    public static final kUv zZm = new kUv();

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Object mo10268get() {
        HashMap hashMap = new HashMap();
        hashMap.put(AvsApiConstants.Alexa.IOComponents.zZm, new Pps());
        hashMap.put(AvsApiConstants.Alexa.Display.Window.zZm, new tAd());
        return (ComponentStateAdapter) Preconditions.checkNotNull(new ComponentStateAdapter(hashMap), "Cannot return null from a non-@Nullable @Provides method");
    }
}
