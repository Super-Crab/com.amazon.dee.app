package com.amazon.alexa.accessory.speechapi.events;

import com.amazon.device.messaging.ADMRegistrationConstants;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
/* compiled from: ApiCallbacks.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006H&J\b\u0010\u0007\u001a\u00020\u0003H&¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/events/ApiCallbacks;", "", "onFailure", "", ADMRegistrationConstants.CALL_EXCEPTION, "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public interface ApiCallbacks {
    void onFailure(@Nullable Exception exc);

    void onSuccess();
}
