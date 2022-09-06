package com.amazon.alexa.sharing.sharingsdk;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.sharing.sharingsdk.templates.SharedMessageTemplate;
import com.amazon.commscore.api.commsbridge.ResponseResolver;
/* loaded from: classes10.dex */
public interface ContentSharingSDK {
    Intent handleAndroidShareIntent(@NonNull Intent intent);

    void onReceiveSharingParseEvent(String str, ResponseResolver responseResolver);

    void onSendSharingParseEvent(String str, ResponseResolver responseResolver);

    @Nullable
    SharedMessageTemplate parseTemplateFromMessage(String str);
}
