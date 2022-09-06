package edu.umd.cs.findbugs.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.CLASS)
@Deprecated
/* loaded from: classes3.dex */
public @interface NoWarning {
    Confidence confidence() default Confidence.LOW;

    int num() default 0;

    int rank() default 20;

    String value();
}
