package com.amazon.whisperjoin.common.sharedtypes.provisioning.data;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/* loaded from: classes13.dex */
public class GenericHashSetCollection<T> {
    protected final Set<T> values;

    public GenericHashSetCollection() {
        this(Collections.emptySet());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return new EqualsBuilder().append(this.values, ((GenericHashSetCollection) obj).values).isEquals();
        }
        return false;
    }

    public Collection<T> getSetCollection() {
        return Collections.unmodifiableSet(this.values);
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.values).toHashCode();
    }

    public int size() {
        return this.values.size();
    }

    public GenericHashSetCollection(Collection<T> collection) {
        if (collection != null) {
            this.values = new HashSet();
            for (T t : collection) {
                if (t != null) {
                    if (!this.values.contains(t)) {
                        this.values.add(t);
                    }
                } else {
                    throw new IllegalArgumentException("Values in the set cannot contain any null references.");
                }
            }
            return;
        }
        throw new IllegalArgumentException("Values can not be null");
    }
}
