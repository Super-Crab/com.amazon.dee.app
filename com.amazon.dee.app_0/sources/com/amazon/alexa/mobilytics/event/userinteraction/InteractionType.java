package com.amazon.alexa.mobilytics.event.userinteraction;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes9.dex */
public @interface InteractionType {
    public static final String APP_START = "appStart";
    public static final String CLICK = "click";
    public static final String COMMS_CALL = "commsCall";
    public static final String COMMS_MESSAGE = "commsMessage";
    public static final String DEEP_LINK_CLICK = "deepLinkClick";
    public static final String LONG_PRESS = "longPress";
    public static final String PAGE_VIEW = "pageView";
    public static final String SLIDER = "slider";
    public static final String UTTERANCE = "utterance";
    public static final String VIEW = "view";
}
