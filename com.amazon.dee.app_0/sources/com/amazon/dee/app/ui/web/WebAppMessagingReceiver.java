package com.amazon.dee.app.ui.web;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.alexa.protocols.messaging.Message;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.dee.app.util.WebUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import java.util.HashMap;
/* loaded from: classes12.dex */
public class WebAppMessagingReceiver implements MessagingReceiver {
    static final String TAG = "WebAppMessagingReceiver";
    Gson gson;
    JavaScriptInjector javaScriptInjector;
    WebNavigator webNavigator;

    public WebAppMessagingReceiver(WebNavigator webNavigator, JavaScriptInjector javaScriptInjector, Gson gson) {
        this.webNavigator = webNavigator;
        this.javaScriptInjector = javaScriptInjector;
        this.gson = gson;
    }

    @Override // com.amazon.alexa.protocols.messaging.MessagingReceiver
    public void onReceive(@NonNull Message message) {
        Bundle bundle = message.get();
        String str = "Received message: " + bundle;
        HashMap hashMap = new HashMap(3);
        hashMap.put("title", bundle.getString("title"));
        hashMap.put("text", bundle.getString("text"));
        hashMap.put("url", bundle.getString("url"));
        JavaScriptInjector javaScriptInjector = this.javaScriptInjector;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Backbone.trigger('processPushNotification',");
        outline107.append(this.gson.toJson(hashMap));
        outline107.append(");");
        javaScriptInjector.inject(outline107.toString());
        if (shouldConsumeNotification(bundle)) {
            message.cancel();
        }
    }

    boolean shouldConsumeNotification(Bundle bundle) {
        String route = WebUtils.getRoute(bundle.getString("url"));
        if (TextUtils.isEmpty(route)) {
            return false;
        }
        String uri = this.webNavigator.getUri();
        return !TextUtils.isEmpty(uri) && TextUtils.equals(uri, route);
    }
}
