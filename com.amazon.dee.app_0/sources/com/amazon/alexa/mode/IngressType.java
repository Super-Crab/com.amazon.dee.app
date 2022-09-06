package com.amazon.alexa.mode;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes9.dex */
public @interface IngressType {
    public static final int AUTO_INGRESS = 0;
    public static final int CARD_INGRESS = 1;
    public static final int FTUE_INGRESS = 3;
    public static final int NOTIFICATION_INGRESS = 2;
}
