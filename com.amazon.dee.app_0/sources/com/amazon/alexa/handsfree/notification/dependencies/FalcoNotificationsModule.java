package com.amazon.alexa.handsfree.notification.dependencies;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.notification.NotificationOccurrenceCounter;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes8.dex */
public class FalcoNotificationsModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    public NotificationModule provideNotificationModule() {
        return NotificationModule.getInstance();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    @Provides
    public NotificationOccurrenceCounter provideNotificationOccurrenceCounter() {
        return NotificationOccurrenceCounter.getInstance();
    }
}
