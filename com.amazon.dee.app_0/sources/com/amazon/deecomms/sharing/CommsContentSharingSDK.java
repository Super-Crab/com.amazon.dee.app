package com.amazon.deecomms.sharing;

import android.content.Intent;
import androidx.annotation.Nullable;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.deecomms.sharing.templates.SharedMessageTemplate;
/* loaded from: classes12.dex */
public interface CommsContentSharingSDK {
    Intent handleAndroidShareIntent(Intent intent);

    boolean onReceiveSharingParseEvent(Message message, EventBus eventBus);

    boolean onSendSharingParseEvent(Message message, EventBus eventBus);

    @Nullable
    SharedMessageTemplate parseTemplateFromMessage(String str);
}
