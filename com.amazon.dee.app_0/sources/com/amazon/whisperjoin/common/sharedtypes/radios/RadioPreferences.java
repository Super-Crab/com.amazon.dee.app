package com.amazon.whisperjoin.common.sharedtypes.radios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
/* loaded from: classes13.dex */
public class RadioPreferences {
    private final Collection<RadioPreference> mPreferences;

    /* loaded from: classes13.dex */
    public interface RadioPreference {
        long getMaximumWaitTime(TimeUnit timeUnit);

        long getMinimumSignalStrength();

        String getRadioType();
    }

    public RadioPreferences(Collection<RadioPreference> collection) {
        if (collection != null && collection.size() != 0) {
            this.mPreferences = new ArrayList(collection);
            return;
        }
        throw new IllegalArgumentException("preferences must not be null");
    }

    public Collection<RadioPreference> getRadioPreferences() {
        return Collections.unmodifiableCollection(this.mPreferences);
    }
}
