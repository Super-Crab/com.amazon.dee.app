package org.apache.logging.log4j.spi;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.apache.logging.log4j.util.StringBuilders;
/* loaded from: classes4.dex */
public class DefaultThreadContextStack implements ThreadContextStack, StringBuilderFormattable {
    private static final ThreadLocal<MutableThreadContextStack> STACK = new ThreadLocal<>();
    private static final long serialVersionUID = 5050501;
    private final boolean useStack;

    public DefaultThreadContextStack(boolean z) {
        this.useStack = z;
    }

    private MutableThreadContextStack getNonNullStackCopy() {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        return (MutableThreadContextStack) (mutableThreadContextStack == null ? new MutableThreadContextStack() : mutableThreadContextStack.mo12842copy());
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends String> collection) {
        if (!this.useStack || collection.isEmpty()) {
            return false;
        }
        MutableThreadContextStack nonNullStackCopy = getNonNullStackCopy();
        nonNullStackCopy.addAll(collection);
        nonNullStackCopy.freeze();
        STACK.set(nonNullStackCopy);
        return true;
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    public List<String> asList() {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        if (mutableThreadContextStack == null) {
            return Collections.emptyList();
        }
        return mutableThreadContextStack.asList();
    }

    @Override // java.util.Collection
    public void clear() {
        STACK.remove();
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        return mutableThreadContextStack != null && mutableThreadContextStack.contains(obj);
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        if (collection.isEmpty()) {
            return true;
        }
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        return mutableThreadContextStack != null && mutableThreadContextStack.containsAll(collection);
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (((obj instanceof DefaultThreadContextStack) && this.useStack != ((DefaultThreadContextStack) obj).useStack) || !(obj instanceof ThreadContextStack)) {
            return false;
        }
        ThreadContextStack threadContextStack = (ThreadContextStack) obj;
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        if (mutableThreadContextStack != null) {
            return mutableThreadContextStack.equals(threadContextStack);
        }
        return false;
    }

    @Override // org.apache.logging.log4j.util.StringBuilderFormattable
    public void formatTo(StringBuilder sb) {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        if (mutableThreadContextStack == null) {
            sb.append("[]");
        } else {
            StringBuilders.appendValue(sb, mutableThreadContextStack);
        }
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    public int getDepth() {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        if (mutableThreadContextStack == null) {
            return 0;
        }
        return mutableThreadContextStack.getDepth();
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    public ThreadContext.ContextStack getImmutableStackOrNull() {
        return STACK.get();
    }

    @Override // java.util.Collection
    public int hashCode() {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        return 31 + (mutableThreadContextStack == null ? 0 : mutableThreadContextStack.hashCode());
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        return mutableThreadContextStack == null || mutableThreadContextStack.isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<String> iterator() {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        if (mutableThreadContextStack == null) {
            return Collections.emptyList().iterator();
        }
        return mutableThreadContextStack.iterator();
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    public String peek() {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        return (mutableThreadContextStack == null || mutableThreadContextStack.size() == 0) ? "" : mutableThreadContextStack.peek();
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    public String pop() {
        MutableThreadContextStack mutableThreadContextStack;
        if (!this.useStack || (mutableThreadContextStack = STACK.get()) == null || mutableThreadContextStack.size() == 0) {
            return "";
        }
        MutableThreadContextStack mutableThreadContextStack2 = (MutableThreadContextStack) mutableThreadContextStack.mo12842copy();
        String pop = mutableThreadContextStack2.pop();
        mutableThreadContextStack2.freeze();
        STACK.set(mutableThreadContextStack2);
        return pop;
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    public void push(String str) {
        if (!this.useStack) {
            return;
        }
        add(str);
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        MutableThreadContextStack mutableThreadContextStack;
        if (!this.useStack || (mutableThreadContextStack = STACK.get()) == null || mutableThreadContextStack.size() == 0) {
            return false;
        }
        MutableThreadContextStack mutableThreadContextStack2 = (MutableThreadContextStack) mutableThreadContextStack.mo12842copy();
        boolean remove = mutableThreadContextStack2.remove(obj);
        mutableThreadContextStack2.freeze();
        STACK.set(mutableThreadContextStack2);
        return remove;
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        MutableThreadContextStack mutableThreadContextStack;
        if (!this.useStack || collection.isEmpty() || (mutableThreadContextStack = STACK.get()) == null || mutableThreadContextStack.isEmpty()) {
            return false;
        }
        MutableThreadContextStack mutableThreadContextStack2 = (MutableThreadContextStack) mutableThreadContextStack.mo12842copy();
        boolean removeAll = mutableThreadContextStack2.removeAll(collection);
        mutableThreadContextStack2.freeze();
        STACK.set(mutableThreadContextStack2);
        return removeAll;
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        MutableThreadContextStack mutableThreadContextStack;
        if (!this.useStack || collection.isEmpty() || (mutableThreadContextStack = STACK.get()) == null || mutableThreadContextStack.isEmpty()) {
            return false;
        }
        MutableThreadContextStack mutableThreadContextStack2 = (MutableThreadContextStack) mutableThreadContextStack.mo12842copy();
        boolean retainAll = mutableThreadContextStack2.retainAll(collection);
        mutableThreadContextStack2.freeze();
        STACK.set(mutableThreadContextStack2);
        return retainAll;
    }

    @Override // java.util.Collection
    public int size() {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        if (mutableThreadContextStack == null) {
            return 0;
        }
        return mutableThreadContextStack.size();
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        return mutableThreadContextStack == null ? new String[0] : mutableThreadContextStack.toArray(new Object[mutableThreadContextStack.size()]);
    }

    public String toString() {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        return mutableThreadContextStack == null ? "[]" : mutableThreadContextStack.toString();
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    public void trim(int i) {
        if (i >= 0) {
            MutableThreadContextStack mutableThreadContextStack = STACK.get();
            if (mutableThreadContextStack == null) {
                return;
            }
            MutableThreadContextStack mutableThreadContextStack2 = (MutableThreadContextStack) mutableThreadContextStack.mo12842copy();
            mutableThreadContextStack2.trim(i);
            mutableThreadContextStack2.freeze();
            STACK.set(mutableThreadContextStack2);
            return;
        }
        throw new IllegalArgumentException("Maximum stack depth cannot be negative");
    }

    @Override // java.util.Collection
    public boolean add(String str) {
        if (!this.useStack) {
            return false;
        }
        MutableThreadContextStack nonNullStackCopy = getNonNullStackCopy();
        nonNullStackCopy.add(str);
        nonNullStackCopy.freeze();
        STACK.set(nonNullStackCopy);
        return true;
    }

    @Override // org.apache.logging.log4j.ThreadContext.ContextStack
    /* renamed from: copy  reason: collision with other method in class */
    public ThreadContextStack mo12842copy() {
        MutableThreadContextStack mutableThreadContextStack;
        if (this.useStack && (mutableThreadContextStack = STACK.get()) != null) {
            return mutableThreadContextStack.mo12842copy();
        }
        return new MutableThreadContextStack();
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        MutableThreadContextStack mutableThreadContextStack = STACK.get();
        if (mutableThreadContextStack == null) {
            if (tArr.length > 0) {
                tArr[0] = null;
            }
            return tArr;
        }
        return (T[]) mutableThreadContextStack.toArray(tArr);
    }
}
