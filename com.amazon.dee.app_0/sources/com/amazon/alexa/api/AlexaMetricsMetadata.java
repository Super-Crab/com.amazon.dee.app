package com.amazon.alexa.api;

import android.os.Bundle;
import java.util.Set;
/* loaded from: classes6.dex */
public class AlexaMetricsMetadata {
    private final Bundle metadata;

    AlexaMetricsMetadata(Bundle bundle) {
        this.metadata = bundle;
    }

    public static AlexaMetricsMetadata create(Bundle bundle) {
        return new AlexaMetricsMetadata(bundle);
    }

    public boolean containsKey(String str) {
        return this.metadata.containsKey(str);
    }

    public String get(String str) {
        return this.metadata.getString(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bundle getBundle() {
        return this.metadata;
    }

    public Set<String> keySet() {
        return this.metadata.keySet();
    }

    public void put(String str, String str2) {
        this.metadata.putString(str, str2);
    }
}
