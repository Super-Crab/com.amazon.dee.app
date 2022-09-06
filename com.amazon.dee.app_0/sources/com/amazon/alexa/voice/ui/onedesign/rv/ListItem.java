package com.amazon.alexa.voice.ui.onedesign.rv;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes11.dex */
public @interface ListItem {
    Class<? extends RvViewHolder> viewHolderClass();
}
