package com.amazon.alexa.sendtoapp.notification;

import android.content.Context;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;
@Module
/* loaded from: classes10.dex */
public class SendToAppMessagingReceiverModule {
    @Provides
    @IntoSet
    public MessagingReceiver providesSendToAppMessagingReceiver(Context context) {
        return new SendToAppMessagingReceiver(context, new SendToAppNotifications(context));
    }
}
