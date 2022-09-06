package com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
/* loaded from: classes13.dex */
public class ConfigurationKeySet {
    private final Collection<String> mKeySet;

    public ConfigurationKeySet(Collection<String> collection) {
        if (collection != null) {
            this.mKeySet = new ArrayList(collection);
            return;
        }
        throw new IllegalArgumentException("configurations can not be null or empty");
    }

    public Collection<String> getKeySet() {
        return Collections.unmodifiableCollection(this.mKeySet);
    }

    protected void validate() {
        if (this.mKeySet != null) {
            return;
        }
        throw new IllegalArgumentException("configurations can not be null or empty");
    }
}
