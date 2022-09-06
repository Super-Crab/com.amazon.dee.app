package org.junit.runner;
/* loaded from: classes5.dex */
public final class FilterFactoryParams {
    private final String args;
    private final Description topLevelDescription;

    public FilterFactoryParams(Description description, String str) {
        if (str != null && description != null) {
            this.topLevelDescription = description;
            this.args = str;
            return;
        }
        throw new NullPointerException();
    }

    public String getArgs() {
        return this.args;
    }

    public Description getTopLevelDescription() {
        return this.topLevelDescription;
    }
}
