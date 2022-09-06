package com.amazon.deecomms.smsmessaging.messagingcontroller;

import android.content.Context;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MessagingControllerContextProvider_MembersInjector implements MembersInjector<MessagingControllerContextProvider> {
    private final Provider<Context> mContextProvider;

    public MessagingControllerContextProvider_MembersInjector(Provider<Context> provider) {
        this.mContextProvider = provider;
    }

    public static MembersInjector<MessagingControllerContextProvider> create(Provider<Context> provider) {
        return new MessagingControllerContextProvider_MembersInjector(provider);
    }

    public static void injectMContext(MessagingControllerContextProvider messagingControllerContextProvider, Context context) {
        messagingControllerContextProvider.mContext = context;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MessagingControllerContextProvider messagingControllerContextProvider) {
        messagingControllerContextProvider.mContext = this.mContextProvider.mo10268get();
    }
}
