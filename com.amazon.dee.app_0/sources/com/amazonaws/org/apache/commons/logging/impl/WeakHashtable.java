package com.amazonaws.org.apache.commons.logging.impl;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
/* loaded from: classes13.dex */
public final class WeakHashtable extends Hashtable {
    private static final int MAX_CHANGES_BEFORE_PURGE = 100;
    private static final int PARTIAL_PURGE_COUNT = 10;
    private ReferenceQueue queue = new ReferenceQueue();
    private int changeCount = 0;

    /* loaded from: classes13.dex */
    private static final class Entry implements Map.Entry {
        private final Object key;
        private final Object value;

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            if (getKey() == null) {
                if (entry.getKey() != null) {
                    return false;
                }
            } else if (!getKey().equals(entry.getKey())) {
                return false;
            }
            if (getValue() == null) {
                if (entry.getValue() != null) {
                    return false;
                }
            } else if (!getValue().equals(entry.getValue())) {
                return false;
            }
            return true;
        }

        @Override // java.util.Map.Entry
        public Object getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public Object getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            int i = 0;
            int hashCode = getKey() == null ? 0 : getKey().hashCode();
            if (getValue() != null) {
                i = getValue().hashCode();
            }
            return hashCode ^ i;
        }

        @Override // java.util.Map.Entry
        public Object setValue(Object obj) {
            throw new UnsupportedOperationException("Entry.setValue is not supported.");
        }

        private Entry(Object obj, Object obj2) {
            this.key = obj;
            this.value = obj2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static final class WeakKey extends WeakReference {
        private final Referenced referenced;

        /* JADX INFO: Access modifiers changed from: private */
        public Referenced getReferenced() {
            return this.referenced;
        }

        private WeakKey(Object obj, ReferenceQueue referenceQueue, Referenced referenced) {
            super(obj, referenceQueue);
            this.referenced = referenced;
        }
    }

    private void purge() {
        synchronized (this.queue) {
            while (true) {
                WeakKey weakKey = (WeakKey) this.queue.poll();
                if (weakKey != null) {
                    super.remove(weakKey.getReferenced());
                }
            }
        }
    }

    private void purgeOne() {
        synchronized (this.queue) {
            WeakKey weakKey = (WeakKey) this.queue.poll();
            if (weakKey != null) {
                super.remove(weakKey.getReferenced());
            }
        }
    }

    @Override // java.util.Hashtable, java.util.Map
    public boolean containsKey(Object obj) {
        return super.containsKey(new Referenced(obj));
    }

    @Override // java.util.Hashtable, java.util.Dictionary
    public Enumeration elements() {
        purge();
        return super.elements();
    }

    @Override // java.util.Hashtable, java.util.Map
    public Set entrySet() {
        purge();
        Set<Map.Entry> entrySet = super.entrySet();
        HashSet hashSet = new HashSet();
        for (Map.Entry entry : entrySet) {
            Object value = ((Referenced) entry.getKey()).getValue();
            Object value2 = entry.getValue();
            if (value != null) {
                hashSet.add(new Entry(value, value2));
            }
        }
        return hashSet;
    }

    @Override // java.util.Hashtable, java.util.Dictionary, java.util.Map
    public Object get(Object obj) {
        return super.get(new Referenced(obj));
    }

    @Override // java.util.Hashtable, java.util.Dictionary, java.util.Map
    public boolean isEmpty() {
        purge();
        return super.isEmpty();
    }

    @Override // java.util.Hashtable, java.util.Map
    public Set keySet() {
        purge();
        Set<Referenced> keySet = super.keySet();
        HashSet hashSet = new HashSet();
        for (Referenced referenced : keySet) {
            Object value = referenced.getValue();
            if (value != null) {
                hashSet.add(value);
            }
        }
        return hashSet;
    }

    @Override // java.util.Hashtable, java.util.Dictionary
    public Enumeration keys() {
        purge();
        final Enumeration keys = super.keys();
        return new Enumeration() { // from class: com.amazonaws.org.apache.commons.logging.impl.WeakHashtable.1
            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return keys.hasMoreElements();
            }

            @Override // java.util.Enumeration
            public Object nextElement() {
                return ((Referenced) keys.nextElement()).getValue();
            }
        };
    }

    @Override // java.util.Hashtable, java.util.Dictionary, java.util.Map
    public Object put(Object obj, Object obj2) {
        if (obj != null) {
            if (obj2 != null) {
                int i = this.changeCount;
                this.changeCount = i + 1;
                if (i > 100) {
                    purge();
                    this.changeCount = 0;
                } else if (this.changeCount % 10 == 0) {
                    purgeOne();
                }
                return super.put(new Referenced(obj, this.queue), obj2);
            }
            throw new NullPointerException("Null values are not allowed");
        }
        throw new NullPointerException("Null keys are not allowed");
    }

    @Override // java.util.Hashtable, java.util.Map
    public void putAll(Map map) {
        if (map != null) {
            for (Map.Entry entry : map.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override // java.util.Hashtable
    protected void rehash() {
        purge();
        super.rehash();
    }

    @Override // java.util.Hashtable, java.util.Dictionary, java.util.Map
    public Object remove(Object obj) {
        int i = this.changeCount;
        this.changeCount = i + 1;
        if (i > 100) {
            purge();
            this.changeCount = 0;
        } else if (this.changeCount % 10 == 0) {
            purgeOne();
        }
        return super.remove(new Referenced(obj));
    }

    @Override // java.util.Hashtable, java.util.Dictionary, java.util.Map
    public int size() {
        purge();
        return super.size();
    }

    @Override // java.util.Hashtable
    public String toString() {
        purge();
        return super.toString();
    }

    @Override // java.util.Hashtable, java.util.Map
    public Collection values() {
        purge();
        return super.values();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static final class Referenced {
        private final int hashCode;
        private final WeakReference reference;

        /* JADX INFO: Access modifiers changed from: private */
        public Object getValue() {
            return this.reference.get();
        }

        public boolean equals(Object obj) {
            if (obj instanceof Referenced) {
                Referenced referenced = (Referenced) obj;
                Object value = getValue();
                Object value2 = referenced.getValue();
                if (value != null) {
                    return value.equals(value2);
                }
                boolean z = value2 == null;
                return z ? hashCode() == referenced.hashCode() : z;
            }
            return false;
        }

        public int hashCode() {
            return this.hashCode;
        }

        private Referenced(Object obj) {
            this.reference = new WeakReference(obj);
            this.hashCode = obj.hashCode();
        }

        private Referenced(Object obj, ReferenceQueue referenceQueue) {
            this.reference = new WeakKey(obj, referenceQueue, this);
            this.hashCode = obj.hashCode();
        }
    }
}
