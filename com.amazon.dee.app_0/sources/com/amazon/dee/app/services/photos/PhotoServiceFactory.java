package com.amazon.dee.app.services.photos;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.dee.app.services.clouddrive.CloudDriveService;
import com.amazon.dee.app.util.Utils;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes12.dex */
public class PhotoServiceFactory {
    private static final String TAG = Utils.safeTag(PhotoServiceFactory.class.getSimpleName());
    private final AlexaPhotosBackgroundService alexaPhotosBackgroundService;
    private final List<String> cloudDriveNotSupportedCountries = Arrays.asList("IN", "BR", "MX");
    private final CloudDriveService cloudDriveService;
    private PhotoService photoService;

    public PhotoServiceFactory(AlexaPhotosBackgroundService alexaPhotosBackgroundService, CloudDriveService cloudDriveService, final IdentityService identityService, EventBus eventBus) {
        this.alexaPhotosBackgroundService = alexaPhotosBackgroundService;
        this.cloudDriveService = cloudDriveService;
        this.photoService = cloudDriveService;
        UserIdentity user = identityService.getUser(TAG);
        if (user != null) {
            updatePhotoService(user.getMarketplace());
        }
        eventBus.getSubscriber().subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.dee.app.services.photos.-$$Lambda$PhotoServiceFactory$Qd5a3nqBCm_s-rT9wSuXSDvJXi8
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                PhotoServiceFactory.this.lambda$new$0$PhotoServiceFactory(identityService, message);
            }
        });
    }

    private void updatePhotoService(Marketplace marketplace) {
        if (marketplace != null && this.cloudDriveNotSupportedCountries.contains(marketplace.getCountryCode().toString())) {
            this.photoService = this.alexaPhotosBackgroundService;
        } else {
            this.photoService = this.cloudDriveService;
        }
    }

    public PhotoService getPhotoService() {
        return this.photoService;
    }

    public /* synthetic */ void lambda$new$0$PhotoServiceFactory(IdentityService identityService, Message message) {
        userChanged(identityService.getUser(TAG));
    }

    @VisibleForTesting
    void userChanged(UserIdentity userIdentity) {
        if (userIdentity != null) {
            updatePhotoService(userIdentity.getMarketplace());
        }
    }
}
