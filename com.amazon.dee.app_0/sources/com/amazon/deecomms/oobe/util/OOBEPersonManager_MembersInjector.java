package com.amazon.deecomms.oobe.util;

import android.content.Context;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class OOBEPersonManager_MembersInjector implements MembersInjector<OOBEPersonManager> {
    private final Provider<Context> mContextProvider;

    public OOBEPersonManager_MembersInjector(Provider<Context> provider) {
        this.mContextProvider = provider;
    }

    public static MembersInjector<OOBEPersonManager> create(Provider<Context> provider) {
        return new OOBEPersonManager_MembersInjector(provider);
    }

    public static void injectMContext(OOBEPersonManager oOBEPersonManager, Context context) {
        oOBEPersonManager.mContext = context;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(OOBEPersonManager oOBEPersonManager) {
        oOBEPersonManager.mContext = this.mContextProvider.mo10268get();
    }
}
