package org.hamcrest;

import java.io.IOException;
/* loaded from: classes5.dex */
public class StringDescription extends BaseDescription {
    private final Appendable out;

    public StringDescription() {
        this(new StringBuilder());
    }

    public static String asString(SelfDescribing selfDescribing) {
        return toString(selfDescribing);
    }

    public static String toString(SelfDescribing selfDescribing) {
        return new StringDescription().appendDescriptionOf(selfDescribing).toString();
    }

    @Override // org.hamcrest.BaseDescription
    protected void append(String str) {
        try {
            this.out.append(str);
        } catch (IOException e) {
            throw new RuntimeException("Could not write description", e);
        }
    }

    public StringDescription(Appendable appendable) {
        this.out = appendable;
    }

    public String toString() {
        return this.out.toString();
    }

    @Override // org.hamcrest.BaseDescription
    protected void append(char c) {
        try {
            this.out.append(c);
        } catch (IOException e) {
            throw new RuntimeException("Could not write description", e);
        }
    }
}
