package org.apache.logging.log4j.spi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.util.Chars;
import org.apache.logging.log4j.util.StringBuilderFormattable;
/* loaded from: classes4.dex */
public class MutableThreadContextStack implements ThreadContextStack, StringBuilderFormattable {
    private static final long serialVersionUID = 50505011;
    private boolean frozen;
    private final List<String> list;

    public MutableThreadContextStack() {
        this(new ArrayList());
    }

    private void checkInvariants() {
        if (!this.frozen) {
            return;
        }
        throw new UnsupportedOperationException("context stack has been frozen");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends String> collection) {
        checkInvariants();
        return this.list.addAll(collection);
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    public List<String> asList() {
        return this.list;
    }

    @Override // java.util.Collection
    public void clear() {
        checkInvariants();
        this.list.clear();
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        return this.list.contains(obj);
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        return this.list.containsAll(collection);
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ThreadContextStack)) {
            return false;
        }
        List<String> asList = ((ThreadContextStack) obj).asList();
        List<String> list = this.list;
        if (list == null) {
            if (asList != null) {
                return false;
            }
        } else if (!list.equals(asList)) {
            return false;
        }
        return true;
    }

    @Override // org.apache.logging.log4j.util.StringBuilderFormattable
    public void formatTo(StringBuilder sb) {
        sb.append(JsonReaderKt.BEGIN_LIST);
        for (int i = 0; i < this.list.size(); i++) {
            if (i > 0) {
                sb.append(JsonReaderKt.COMMA);
                sb.append(Chars.SPACE);
            }
            sb.append(this.list.get(i));
        }
        sb.append(JsonReaderKt.END_LIST);
    }

    public void freeze() {
        this.frozen = true;
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    public int getDepth() {
        return this.list.size();
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    public ThreadContext.ContextStack getImmutableStackOrNull() {
        return mo12842copy();
    }

    @Override // java.util.Collection
    public int hashCode() {
        List<String> list = this.list;
        return 31 + (list == null ? 0 : list.hashCode());
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public boolean isFrozen() {
        return this.frozen;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<String> iterator() {
        return this.list.iterator();
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    public String peek() {
        if (this.list.isEmpty()) {
            return null;
        }
        return this.list.get(this.list.size() - 1);
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    public String pop() {
        checkInvariants();
        if (this.list.isEmpty()) {
            return null;
        }
        return this.list.remove(this.list.size() - 1);
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    public void push(String str) {
        checkInvariants();
        this.list.add(str);
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        checkInvariants();
        return this.list.remove(obj);
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        checkInvariants();
        return this.list.removeAll(collection);
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        checkInvariants();
        return this.list.retainAll(collection);
    }

    @Override // java.util.Collection
    public int size() {
        return this.list.size();
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        return this.list.toArray();
    }

    public String toString() {
        return String.valueOf(this.list);
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    public void trim(int i) {
        checkInvariants();
        if (i >= 0) {
            List<String> list = this.list;
            if (list == null) {
                return;
            }
            ArrayList arrayList = new ArrayList(list.size());
            int min = Math.min(i, this.list.size());
            for (int i2 = 0; i2 < min; i2++) {
                arrayList.add(this.list.get(i2));
            }
            this.list.clear();
            this.list.addAll(arrayList);
            return;
        }
        throw new IllegalArgumentException("Maximum stack depth cannot be negative");
    }

    public MutableThreadContextStack(List<String> list) {
        this.list = new ArrayList(list);
    }

    @Override // java.util.Collection
    public boolean add(String str) {
        checkInvariants();
        return this.list.add(str);
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    /* renamed from: copy  reason: collision with other method in class */
    public ThreadContextStack mo12842copy() {
        return new MutableThreadContextStack(this);
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) this.list.toArray(tArr);
    }

    private MutableThreadContextStack(MutableThreadContextStack mutableThreadContextStack) {
        this.list = new ArrayList(mutableThreadContextStack.list);
    }
}
