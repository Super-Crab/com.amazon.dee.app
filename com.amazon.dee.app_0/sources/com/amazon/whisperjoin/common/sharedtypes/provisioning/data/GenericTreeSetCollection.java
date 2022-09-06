package com.amazon.whisperjoin.common.sharedtypes.provisioning.data;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/* loaded from: classes13.dex */
public class GenericTreeSetCollection<T> {
    protected final Set<T> values;

    public GenericTreeSetCollection() {
        this(Collections.emptySet());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return new EqualsBuilder().append(this.values, ((GenericTreeSetCollection) obj).values).isEquals();
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

    public GenericTreeSetCollection(Collection<T> collection) {
        if (collection != null) {
            this.values = new TreeSet();
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
        throw new IllegalArgumentException("Values cannot be null");
    }
}
