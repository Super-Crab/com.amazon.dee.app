package com.amazon.org.codehaus.jackson.map.deser.impl;

import com.amazon.org.codehaus.jackson.map.deser.SettableBeanProperty;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes13.dex */
public final class BeanPropertyMap {
    private final Bucket[] _buckets;
    private final int _hashMask;
    private final int _size;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public static final class Bucket {
        public final String key;
        public final Bucket next;
        public final SettableBeanProperty value;

        public Bucket(Bucket bucket, String str, SettableBeanProperty settableBeanProperty) {
            this.next = bucket;
            this.key = str;
            this.value = settableBeanProperty;
        }
    }

    /* loaded from: classes13.dex */
    private static final class IteratorImpl implements Iterator<SettableBeanProperty> {
        private final Bucket[] _buckets;
        private Bucket _currentBucket;
        private int _nextBucketIndex;

        public IteratorImpl(Bucket[] bucketArr) {
            this._buckets = bucketArr;
            int length = this._buckets.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                int i2 = i + 1;
                Bucket bucket = this._buckets[i];
                if (bucket != null) {
                    this._currentBucket = bucket;
                    i = i2;
                    break;
                }
                i = i2;
            }
            this._nextBucketIndex = i;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this._currentBucket != null;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Iterator
        /* renamed from: next */
        public SettableBeanProperty mo4139next() {
            Bucket bucket = this._currentBucket;
            if (bucket != null) {
                Bucket bucket2 = bucket.next;
                while (bucket2 == null) {
                    int i = this._nextBucketIndex;
                    Bucket[] bucketArr = this._buckets;
                    if (i >= bucketArr.length) {
                        break;
                    }
                    this._nextBucketIndex = i + 1;
                    bucket2 = bucketArr[i];
                }
                this._currentBucket = bucket2;
                return bucket.value;
            }
            throw new NoSuchElementException();
        }
    }

    public BeanPropertyMap(Collection<SettableBeanProperty> collection) {
        this._size = collection.size();
        int findSize = findSize(this._size);
        this._hashMask = findSize - 1;
        Bucket[] bucketArr = new Bucket[findSize];
        for (SettableBeanProperty settableBeanProperty : collection) {
            String name = settableBeanProperty.getName();
            int hashCode = name.hashCode() & this._hashMask;
            bucketArr[hashCode] = new Bucket(bucketArr[hashCode], name, settableBeanProperty);
        }
        this._buckets = bucketArr;
    }

    private SettableBeanProperty _findWithEquals(String str, int i) {
        for (Bucket bucket = this._buckets[i]; bucket != null; bucket = bucket.next) {
            if (str.equals(bucket.key)) {
                return bucket.value;
            }
        }
        return null;
    }

    private static final int findSize(int i) {
        int i2 = 2;
        while (i2 < (i <= 32 ? i + i : i + (i >> 2))) {
            i2 += i2;
        }
        return i2;
    }

    public Iterator<SettableBeanProperty> allProperties() {
        return new IteratorImpl(this._buckets);
    }

    public void assignIndexes() {
        Bucket[] bucketArr;
        int i = 0;
        for (Bucket bucket : this._buckets) {
            while (bucket != null) {
                bucket.value.assignIndex(i);
                bucket = bucket.next;
                i++;
            }
        }
    }

    public SettableBeanProperty find(String str) {
        int hashCode = str.hashCode() & this._hashMask;
        Bucket bucket = this._buckets[hashCode];
        if (bucket == null) {
            return null;
        }
        if (bucket.key == str) {
            return bucket.value;
        }
        do {
            bucket = bucket.next;
            if (bucket == null) {
                return _findWithEquals(str, hashCode);
            }
        } while (bucket.key != str);
        return bucket.value;
    }

    public void remove(SettableBeanProperty settableBeanProperty) {
        String name = settableBeanProperty.getName();
        int hashCode = name.hashCode();
        Bucket[] bucketArr = this._buckets;
        int length = hashCode & (bucketArr.length - 1);
        Bucket bucket = null;
        boolean z = false;
        for (Bucket bucket2 = bucketArr[length]; bucket2 != null; bucket2 = bucket2.next) {
            if (z || !bucket2.key.equals(name)) {
                bucket = new Bucket(bucket, bucket2.key, bucket2.value);
            } else {
                z = true;
            }
        }
        if (z) {
            this._buckets[length] = bucket;
            return;
        }
        throw new NoSuchElementException("No entry '" + settableBeanProperty + "' found, can't remove");
    }

    public void replace(SettableBeanProperty settableBeanProperty) {
        String name = settableBeanProperty.getName();
        int hashCode = name.hashCode();
        Bucket[] bucketArr = this._buckets;
        int length = hashCode & (bucketArr.length - 1);
        Bucket bucket = null;
        boolean z = false;
        for (Bucket bucket2 = bucketArr[length]; bucket2 != null; bucket2 = bucket2.next) {
            if (z || !bucket2.key.equals(name)) {
                bucket = new Bucket(bucket, bucket2.key, bucket2.value);
            } else {
                z = true;
            }
        }
        if (z) {
            this._buckets[length] = new Bucket(bucket, name, settableBeanProperty);
            return;
        }
        throw new NoSuchElementException("No entry '" + settableBeanProperty + "' found, can't replace");
    }

    public int size() {
        return this._size;
    }
}
