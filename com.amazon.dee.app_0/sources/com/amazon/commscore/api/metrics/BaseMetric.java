package com.amazon.commscore.api.metrics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.commscore.api.metrics.BaseMetric;
import java.util.HashMap;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class BaseMetric<T extends BaseMetric<?>> {
    private final String component;
    private Map<String, Object> customEntries;
    private final String name;
    private final String ownerIdentifier;
    private final String subComponent;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseMetric(@NonNull String str, @Nullable String str2, @Nullable String str3, @NonNull String str4) {
        this.name = str;
        this.component = str2;
        this.subComponent = str3;
        this.customEntries = new HashMap();
        this.ownerIdentifier = str4;
    }

    public String getComponent() {
        return this.component;
    }

    public Map<String, Object> getCustomEntries() {
        return this.customEntries;
    }

    public String getName() {
        return this.name;
    }

    public String getOwnerIdentifier() {
        return this.ownerIdentifier;
    }

    public String getSubComponent() {
        return this.subComponent;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T setCustomEntries(@Nullable Map<String, Object> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        this.customEntries = map;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseMetric(@NonNull String str, @Nullable String str2, @Nullable String str3) {
        this(str, str2, str3, OwnerIdentifier.ALEXA_MOBILE_COMMS);
    }
}
