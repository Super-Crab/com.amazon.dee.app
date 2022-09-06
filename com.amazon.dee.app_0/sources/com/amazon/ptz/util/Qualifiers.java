package com.amazon.ptz.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;
/* loaded from: classes13.dex */
public class Qualifiers {

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes13.dex */
    public @interface EndTutorial {
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes13.dex */
    public @interface StartLoadingAnimation {
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes13.dex */
    public @interface StopLoadingAnimation {
    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes13.dex */
    public @interface UiThread {
    }
}
