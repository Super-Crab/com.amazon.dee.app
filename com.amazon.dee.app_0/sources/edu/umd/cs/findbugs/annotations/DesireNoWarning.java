package edu.umd.cs.findbugs.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.CLASS)
@Deprecated
/* loaded from: classes3.dex */
public @interface DesireNoWarning {
    Confidence confidence() default Confidence.LOW;

    int num() default 0;

    @Deprecated
    Priority priority() default Priority.LOW;

    String value();
}
