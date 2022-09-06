package com.amazon.deecomms.smsmessaging.messagingcontroller;

import android.content.Context;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MessagingControllerManager_MembersInjector implements MembersInjector<MessagingControllerManager> {
    private final Provider<Context> mContextProvider;

    public MessagingControllerManager_MembersInjector(Provider<Context> provider) {
        this.mContextProvider = provider;
    }

    public static MembersInjector<MessagingControllerManager> create(Provider<Context> provider) {
        return new MessagingControllerManager_MembersInjector(provider);
    }

    public static void injectMContext(MessagingControllerManager messagingControllerManager, Context context) {
        messagingControllerManager.mContext = context;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MessagingControllerManager messagingControllerManager) {
        messagingControllerManager.mContext = this.mContextProvider.mo10268get();
    }
}
