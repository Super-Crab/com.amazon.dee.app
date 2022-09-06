package com.amazon.alexa.redesign.dependency;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.usermessage.UserMessageService;
import com.amazon.alexa.redesign.DismissedCardDataStore;
import com.amazon.alexa.redesign.actions.ActionFactory;
import com.amazon.alexa.redesign.cache.HomeCacheService;
import com.amazon.alexa.redesign.client.HomeFeedServiceClient;
import com.amazon.alexa.redesign.repository.DismissedCardDataStoreRepository;
import com.amazon.alexa.redesign.repository.HomeCardsRepository;
import com.amazon.alexa.redesign.repository.IsAppOnlyRepository;
import com.amazon.alexa.redesign.repository.LocaleRepository;
import com.amazon.alexa.redesign.repository.VoxIngressRepository;
import com.amazon.alexa.redesign.utils.HomeOEInteractor;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.voice.model.VoiceService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes10.dex */
public final class RepositoryModule {
    @Provides
    @Singleton
    public ActionFactory provideActionFactory(RoutingService routingService, EventBus eventBus, HomeOEInteractor homeOEInteractor, HomeFeedServiceClient homeFeedServiceClient, IdentityService identityService, UserMessageService userMessageService, Context context) {
        return new ActionFactory.Builder().withRoutingService(routingService).withEventBus(eventBus).withHomeOEInteractor(homeOEInteractor).withUserMessageService(userMessageService).withContext(context).withNetworkClient(homeFeedServiceClient).withIdentityService(identityService).build();
    }

    @Provides
    public DismissedCardDataStore provideDismissedCardDataStore(Context context) {
        return new DismissedCardDataStoreRepository(context);
    }

    @Provides
    @Singleton
    public HomeCardsRepository provideHomeCardsRepository(HomeFeedServiceClient homeFeedServiceClient, HomeCacheService homeCacheService, Mobilytics mobilytics, ActionFactory actionFactory, Context context, IdentityService identityService, IsAppOnlyRepository isAppOnlyRepository) {
        return new HomeCardsRepository(homeFeedServiceClient, homeCacheService, mobilytics, actionFactory, context, identityService, isAppOnlyRepository);
    }

    @Provides
    @Singleton
    public HomeOEInteractor provideHomeOEInteractor(Mobilytics mobilytics) {
        return new HomeOEInteractor(mobilytics);
    }

    @Provides
    @Singleton
    public IsAppOnlyRepository provideIsAppOnlyRepository(EventBus eventBus, Context context) {
        return new IsAppOnlyRepository(eventBus, context);
    }

    @Provides
    @Singleton
    public LocaleRepository provideLocaleRepository() {
        return new LocaleRepository();
    }

    @Provides
    @Singleton
    public VoxIngressRepository provideVoxIngressRepository(ActionFactory actionFactory, VoiceService voiceService, LocaleRepository localeRepository, Context context) {
        return new VoxIngressRepository(actionFactory, voiceService, localeRepository, context);
    }
}
