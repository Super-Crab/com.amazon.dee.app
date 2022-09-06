package com.amazon.fireos.sdk.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
@Target({ElementType.TYPE})
/* loaded from: classes12.dex */
public @interface HideImplements {
    Class[] interfaces();
}
