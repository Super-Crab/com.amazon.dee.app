package com.amazon.whisperjoin.common.sharedtypes.radios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
/* loaded from: classes13.dex */
public class RadioCollection {
    private ArrayList<String> mRadios;

    public RadioCollection(Collection<String> collection) {
        if (collection != null && collection.size() != 0) {
            this.mRadios = new ArrayList<>(collection);
            return;
        }
        throw new IllegalArgumentException("radios can not be null, and must have size > 0");
    }

    public Collection<String> getRadios() {
        return Collections.unmodifiableList(this.mRadios);
    }
}
