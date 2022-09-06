package com.amazon.dee.app.elements;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes12.dex */
class ReactRouteAliasProp {
    public int type;
    public String value;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes12.dex */
    public @interface Type {
        public static final int CONSTANT = 0;
        public static final int PARAMETER = 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReactRouteAliasProp(String str) {
        if (str.startsWith(":")) {
            this.type = 1;
            this.value = str.substring(1);
            return;
        }
        this.type = 0;
        this.value = str;
    }
}
