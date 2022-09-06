package org.apache.logging.log4j;

import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.message.ParameterizedMessage;
import org.apache.logging.log4j.spi.CleanableThreadContextMap;
import org.apache.logging.log4j.spi.DefaultThreadContextMap;
import org.apache.logging.log4j.spi.DefaultThreadContextStack;
import org.apache.logging.log4j.spi.NoOpThreadContextMap;
import org.apache.logging.log4j.spi.ReadOnlyThreadContextMap;
import org.apache.logging.log4j.spi.ThreadContextMap;
import org.apache.logging.log4j.spi.ThreadContextMap2;
import org.apache.logging.log4j.spi.ThreadContextMapFactory;
import org.apache.logging.log4j.spi.ThreadContextStack;
import org.apache.logging.log4j.util.PropertiesUtil;
/* loaded from: classes4.dex */
public final class ThreadContext {
    private static final String DISABLE_ALL = "disableThreadContext";
    private static final String DISABLE_MAP = "disableThreadContextMap";
    private static final String DISABLE_STACK = "disableThreadContextStack";
    public static final Map<String, String> EMPTY_MAP = Collections.emptyMap();
    public static final ThreadContextStack EMPTY_STACK = new EmptyThreadContextStack();
    private static ThreadContextMap contextMap;
    private static ThreadContextStack contextStack;
    private static ReadOnlyThreadContextMap readOnlyContextMap;
    private static boolean useStack;

    /* loaded from: classes4.dex */
    public interface ContextStack extends Serializable, Collection<String> {
        List<String> asList();

        /* renamed from: copy */
        ContextStack mo12842copy();

        int getDepth();

        ContextStack getImmutableStackOrNull();

        String peek();

        String pop();

        void push(String str);

        void trim(int i);
    }

    /* loaded from: classes4.dex */
    private static class EmptyIterator<E> implements Iterator<E> {
        private EmptyIterator() {
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public E next() {
            throw new NoSuchElementException("This is an empty iterator!");
        }

        @Override // java.util.Iterator
        public void remove() {
        }
    }

    /* loaded from: classes4.dex */
    private static class EmptyThreadContextStack extends AbstractCollection<String> implements ThreadContextStack {
        private static final Iterator<String> EMPTY_ITERATOR = new EmptyIterator();
        private static final long serialVersionUID = 1;

        private EmptyThreadContextStack() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean addAll(Collection<? extends String> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // org.apache.logging.log4j.ThreadContext.ContextStack
        public List<String> asList() {
            return Collections.emptyList();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            return false;
        }

        @Override // org.apache.logging.log4j.ThreadContext.ContextStack
        /* renamed from: copy */
        public ContextStack mo12842copy() {
            return this;
        }

        @Override // java.util.Collection
        public boolean equals(Object obj) {
            return (obj instanceof Collection) && ((Collection) obj).isEmpty();
        }

        @Override // org.apache.logging.log4j.ThreadContext.ContextStack
        public int getDepth() {
            return 0;
        }

        @Override // org.apache.logging.log4j.ThreadContext.ContextStack
        public ContextStack getImmutableStackOrNull() {
            return this;
        }

        @Override // java.util.Collection
        public int hashCode() {
            return 1;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<String> iterator() {
            return EMPTY_ITERATOR;
        }

        @Override // org.apache.logging.log4j.ThreadContext.ContextStack
        public String peek() {
            return null;
        }

        @Override // org.apache.logging.log4j.ThreadContext.ContextStack
        public String pop() {
            return null;
        }

        @Override // org.apache.logging.log4j.ThreadContext.ContextStack
        public void push(String str) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return 0;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            throw new UnsupportedOperationException();
        }

        @Override // org.apache.logging.log4j.ThreadContext.ContextStack
        public void trim(int i) {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean add(String str) {
            throw new UnsupportedOperationException();
        }
    }

    static {
        init();
    }

    private ThreadContext() {
    }

    public static void clearAll() {
        clearMap();
        clearStack();
    }

    public static void clearMap() {
        contextMap.clear();
    }

    public static void clearStack() {
        contextStack.clear();
    }

    public static ContextStack cloneStack() {
        return contextStack.mo12842copy();
    }

    public static boolean containsKey(String str) {
        return contextMap.containsKey(str);
    }

    public static String get(String str) {
        return contextMap.get(str);
    }

    public static Map<String, String> getContext() {
        return contextMap.getCopy();
    }

    public static int getDepth() {
        return contextStack.getDepth();
    }

    public static Map<String, String> getImmutableContext() {
        Map<String, String> immutableMapOrNull = contextMap.getImmutableMapOrNull();
        return immutableMapOrNull == null ? EMPTY_MAP : immutableMapOrNull;
    }

    public static ContextStack getImmutableStack() {
        ContextStack immutableStackOrNull = contextStack.getImmutableStackOrNull();
        return immutableStackOrNull == null ? EMPTY_STACK : immutableStackOrNull;
    }

    public static ReadOnlyThreadContextMap getThreadContextMap() {
        return readOnlyContextMap;
    }

    static void init() {
        ThreadContextMapFactory.init();
        contextMap = null;
        PropertiesUtil properties = PropertiesUtil.getProperties();
        boolean booleanProperty = properties.getBooleanProperty(DISABLE_ALL);
        boolean z = true;
        useStack = !properties.getBooleanProperty(DISABLE_STACK) && !booleanProperty;
        if (properties.getBooleanProperty(DISABLE_MAP) || booleanProperty) {
            z = false;
        }
        contextStack = new DefaultThreadContextStack(useStack);
        if (!z) {
            contextMap = new NoOpThreadContextMap();
        } else {
            contextMap = ThreadContextMapFactory.createThreadContextMap();
        }
        ThreadContextMap threadContextMap = contextMap;
        if (threadContextMap instanceof ReadOnlyThreadContextMap) {
            readOnlyContextMap = (ReadOnlyThreadContextMap) threadContextMap;
        } else {
            readOnlyContextMap = null;
        }
    }

    public static boolean isEmpty() {
        return contextMap.isEmpty();
    }

    public static String peek() {
        return contextStack.peek();
    }

    public static String pop() {
        return contextStack.pop();
    }

    public static void push(String str) {
        contextStack.push(str);
    }

    public static void put(String str, String str2) {
        contextMap.put(str, str2);
    }

    public static void putAll(Map<String, String> map) {
        ThreadContextMap threadContextMap = contextMap;
        if (threadContextMap instanceof ThreadContextMap2) {
            ((ThreadContextMap2) threadContextMap).putAll(map);
        } else if (threadContextMap instanceof DefaultThreadContextMap) {
            ((DefaultThreadContextMap) threadContextMap).putAll(map);
        } else {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                contextMap.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public static void putIfNull(String str, String str2) {
        if (!contextMap.containsKey(str)) {
            contextMap.put(str, str2);
        }
    }

    public static void remove(String str) {
        contextMap.remove(str);
    }

    public static void removeAll(Iterable<String> iterable) {
        ThreadContextMap threadContextMap = contextMap;
        if (threadContextMap instanceof CleanableThreadContextMap) {
            ((CleanableThreadContextMap) threadContextMap).removeAll(iterable);
        } else if (threadContextMap instanceof DefaultThreadContextMap) {
            ((DefaultThreadContextMap) threadContextMap).removeAll(iterable);
        } else {
            for (String str : iterable) {
                contextMap.remove(str);
            }
        }
    }

    public static void removeStack() {
        contextStack.clear();
    }

    public static void setStack(Collection<String> collection) {
        if (collection.isEmpty() || !useStack) {
            return;
        }
        contextStack.clear();
        contextStack.addAll(collection);
    }

    public static void trim(int i) {
        contextStack.trim(i);
    }

    public static void push(String str, Object... objArr) {
        contextStack.push(ParameterizedMessage.format(str, objArr));
    }
}
