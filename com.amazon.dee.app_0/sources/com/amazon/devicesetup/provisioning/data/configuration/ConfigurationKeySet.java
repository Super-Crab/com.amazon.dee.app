package com.amazon.devicesetup.provisioning.data.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
/* loaded from: classes12.dex */
public class ConfigurationKeySet {
    private final Collection<String> keySet;

    public ConfigurationKeySet(Collection<String> collection) {
        if (collection != null) {
            this.keySet = new ArrayList(collection);
            return;
        }
        throw new IllegalArgumentException("configurations can not be null or empty");
    }

    public Collection<String> getKeySet() {
        return Collections.unmodifiableCollection(this.keySet);
    }

    protected void validate() {
        if (this.keySet != null) {
            return;
        }
        throw new IllegalArgumentException("configurations can not be null or empty");
    }
}
