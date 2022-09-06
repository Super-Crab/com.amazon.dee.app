package edu.umd.cs.findbugs.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.CLASS)
@Deprecated
/* loaded from: classes3.dex */
public @interface ExpectWarning {
    Confidence confidence() default Confidence.LOW;

    int num() default 1;

    int rank() default 20;

    String value();
}
