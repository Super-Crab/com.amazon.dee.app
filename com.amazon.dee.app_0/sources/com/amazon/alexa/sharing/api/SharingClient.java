package com.amazon.alexa.sharing.api;

import android.content.Intent;
import com.amazon.alexa.sharing.api.exceptions.AlexaSharingException;
import com.amazon.alexa.sharing.api.models.Payload;
import com.amazon.alexa.sharing.repo.models.acms.ACMSMessage;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableMap;
import java.util.List;
/* loaded from: classes10.dex */
public interface SharingClient {
    Intent handleShareIntent(Intent intent);

    void initialize();

    void openCamera(ReactApplicationContext reactApplicationContext, ReadableMap readableMap, Promise promise);

    void openPicker(ReactApplicationContext reactApplicationContext, ReadableMap readableMap, Promise promise);

    ACMSMessage sendNewMessage(List<String> list, Payload payload) throws AlexaSharingException;

    void startListening();
}
