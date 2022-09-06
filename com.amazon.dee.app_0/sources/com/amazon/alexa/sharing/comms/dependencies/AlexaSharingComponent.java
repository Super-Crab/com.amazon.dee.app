package com.amazon.alexa.sharing.comms.dependencies;

import com.amazon.alexa.sharing.activity.ShareSheetActivity;
import com.amazon.alexa.sharing.comms.AlexaSharingClient;
import com.amazon.alexa.sharing.sharingsdk.ContentSharingService;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import dagger.Component;
import dagger.Lazy;
import javax.inject.Singleton;
@Component(modules = {AlexaSharingModule.class})
@Singleton
/* loaded from: classes10.dex */
public interface AlexaSharingComponent {
    ContentSharingService getContentSharingService();

    AlexaSharingClient getSharingClient();

    void inject(ShareSheetActivity shareSheetActivity);

    void inject(ContentSharingService contentSharingService);

    void inject(Lazy<AlexaCommsCoreMetricsService> lazy);
}
