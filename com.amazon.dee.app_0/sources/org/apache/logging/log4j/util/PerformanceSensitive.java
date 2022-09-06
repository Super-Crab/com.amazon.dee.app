package org.apache.logging.log4j.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes4.dex */
public @interface PerformanceSensitive {
    String[] value() default {""};
}
