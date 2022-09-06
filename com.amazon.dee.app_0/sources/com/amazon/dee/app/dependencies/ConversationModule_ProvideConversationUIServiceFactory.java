package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.CommsUIDelegateBase;
import com.amazon.deecomms.conversation.CommsDeviceSupport;
import com.amazon.deecomms.services.ConversationUIService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ConversationModule_ProvideConversationUIServiceFactory implements Factory<ConversationUIService> {
    private final Provider<Activity> activityProvider;
    private final Provider<CommsDeviceSupport> commsDeviceSupportProvider;
    private final Provider<CommsManager> commsManagerProvider;
    private final Provider<CommsUIDelegateBase> commsUIDelegateProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final ConversationModule module;

    public ConversationModule_ProvideConversationUIServiceFactory(ConversationModule conversationModule, Provider<Activity> provider, Provider<IdentityService> provider2, Provider<CommsManager> provider3, Provider<CommsUIDelegateBase> provider4, Provider<CommsDeviceSupport> provider5) {
        this.module = conversationModule;
        this.activityProvider = provider;
        this.identityServiceProvider = provider2;
        this.commsManagerProvider = provider3;
        this.commsUIDelegateProvider = provider4;
        this.commsDeviceSupportProvider = provider5;
    }

    public static ConversationModule_ProvideConversationUIServiceFactory create(ConversationModule conversationModule, Provider<Activity> provider, Provider<IdentityService> provider2, Provider<CommsManager> provider3, Provider<CommsUIDelegateBase> provider4, Provider<CommsDeviceSupport> provider5) {
        return new ConversationModule_ProvideConversationUIServiceFactory(conversationModule, provider, provider2, provider3, provider4, provider5);
    }

    public static ConversationUIService provideInstance(ConversationModule conversationModule, Provider<Activity> provider, Provider<IdentityService> provider2, Provider<CommsManager> provider3, Provider<CommsUIDelegateBase> provider4, Provider<CommsDeviceSupport> provider5) {
        return proxyProvideConversationUIService(conversationModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    public static ConversationUIService proxyProvideConversationUIService(ConversationModule conversationModule, Activity activity, IdentityService identityService, CommsManager commsManager, CommsUIDelegateBase commsUIDelegateBase, CommsDeviceSupport commsDeviceSupport) {
        return (ConversationUIService) Preconditions.checkNotNull(conversationModule.provideConversationUIService(activity, identityService, commsManager, commsUIDelegateBase, commsDeviceSupport), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ConversationUIService mo10268get() {
        return provideInstance(this.module, this.activityProvider, this.identityServiceProvider, this.commsManagerProvider, this.commsUIDelegateProvider, this.commsDeviceSupportProvider);
    }
}
