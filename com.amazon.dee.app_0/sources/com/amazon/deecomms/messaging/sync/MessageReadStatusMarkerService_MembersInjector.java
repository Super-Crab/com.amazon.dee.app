package com.amazon.deecomms.messaging.sync;

import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.common.controller.CommsNotificationManager;
import com.amazon.deecomms.common.service.ProvisioningManager;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MessageReadStatusMarkerService_MembersInjector implements MembersInjector<MessageReadStatusMarkerService> {
    private final Provider<CommsIdentityManager> commsIdentityManagerProvider;
    private final Provider<CommsNotificationManager> commsNotificationManagerProvider;
    private final Provider<ProvisioningManager> provisioningManagerProvider;

    public MessageReadStatusMarkerService_MembersInjector(Provider<CommsNotificationManager> provider, Provider<CommsIdentityManager> provider2, Provider<ProvisioningManager> provider3) {
        this.commsNotificationManagerProvider = provider;
        this.commsIdentityManagerProvider = provider2;
        this.provisioningManagerProvider = provider3;
    }

    public static MembersInjector<MessageReadStatusMarkerService> create(Provider<CommsNotificationManager> provider, Provider<CommsIdentityManager> provider2, Provider<ProvisioningManager> provider3) {
        return new MessageReadStatusMarkerService_MembersInjector(provider, provider2, provider3);
    }

    public static void injectCommsIdentityManager(MessageReadStatusMarkerService messageReadStatusMarkerService, CommsIdentityManager commsIdentityManager) {
        messageReadStatusMarkerService.commsIdentityManager = commsIdentityManager;
    }

    public static void injectCommsNotificationManager(MessageReadStatusMarkerService messageReadStatusMarkerService, CommsNotificationManager commsNotificationManager) {
        messageReadStatusMarkerService.commsNotificationManager = commsNotificationManager;
    }

    public static void injectProvisioningManager(MessageReadStatusMarkerService messageReadStatusMarkerService, ProvisioningManager provisioningManager) {
        messageReadStatusMarkerService.provisioningManager = provisioningManager;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(MessageReadStatusMarkerService messageReadStatusMarkerService) {
        messageReadStatusMarkerService.commsNotificationManager = this.commsNotificationManagerProvider.mo10268get();
        messageReadStatusMarkerService.commsIdentityManager = this.commsIdentityManagerProvider.mo10268get();
        messageReadStatusMarkerService.provisioningManager = this.provisioningManagerProvider.mo10268get();
    }
}
