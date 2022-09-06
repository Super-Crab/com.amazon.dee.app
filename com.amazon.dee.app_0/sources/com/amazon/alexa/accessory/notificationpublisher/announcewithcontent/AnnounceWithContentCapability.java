package com.amazon.alexa.accessory.notificationpublisher.announcewithcontent;

import com.amazon.alexa.api.AlexaCapability;
/* loaded from: classes.dex */
public final class AnnounceWithContentCapability {
    static final String DIRECTIVE_NAME = "AnnounceWithContent";
    public static final String INTERFACE_NAME = "Alexa.Notifications.Multipart";
    static final String TYPE = "AlexaInterface";
    static final String VERSION = "0.1";

    private AnnounceWithContentCapability() {
    }

    public static AlexaCapability create() {
        return AlexaCapability.create(TYPE, INTERFACE_NAME, "0.1");
    }
}
