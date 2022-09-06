package com.drew.lang;

import com.drew.lang.annotations.Nullable;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public class ByteTrie<T> {
    private int _maxDepth;
    private final ByteTrieNode<T> _root = new ByteTrieNode<>();

    /* loaded from: classes2.dex */
    static class ByteTrieNode<T> {
        private final Map<Byte, ByteTrieNode<T>> _children = new HashMap();
        private T _value = null;

        ByteTrieNode() {
        }

        public void setValue(T t) {
            if (this._value == null) {
                this._value = t;
                return;
            }
            throw new RuntimeException("Value already set for this trie node");
        }
    }

    public void addPath(T t, byte[]... bArr) {
        byte[] bArr2;
        ByteTrieNode<T> byteTrieNode = this._root;
        int length = bArr.length;
        ByteTrieNode<T> byteTrieNode2 = byteTrieNode;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            ByteTrieNode<T> byteTrieNode3 = byteTrieNode2;
            int i3 = i2;
            for (byte b : bArr[i]) {
                ByteTrieNode<T> byteTrieNode4 = (ByteTrieNode) ((ByteTrieNode) byteTrieNode3)._children.get(Byte.valueOf(b));
                if (byteTrieNode4 == null) {
                    byteTrieNode4 = new ByteTrieNode<>();
                    ((ByteTrieNode) byteTrieNode3)._children.put(Byte.valueOf(b), byteTrieNode4);
                }
                byteTrieNode3 = byteTrieNode4;
                i3++;
            }
            i++;
            i2 = i3;
            byteTrieNode2 = byteTrieNode3;
        }
        if (i2 != 0) {
            byteTrieNode2.setValue(t);
            this._maxDepth = Math.max(this._maxDepth, i2);
            return;
        }
        throw new IllegalArgumentException("Parts must contain at least one byte.");
    }

    @Nullable
    public T find(byte[] bArr) {
        ByteTrieNode<T> byteTrieNode = this._root;
        T t = (T) ((ByteTrieNode) byteTrieNode)._value;
        for (byte b : bArr) {
            byteTrieNode = (ByteTrieNode) ((ByteTrieNode) byteTrieNode)._children.get(Byte.valueOf(b));
            if (byteTrieNode == null) {
                break;
            }
            if (((ByteTrieNode) byteTrieNode)._value != null) {
                t = (T) ((ByteTrieNode) byteTrieNode)._value;
            }
        }
        return t;
    }

    public int getMaxDepth() {
        return this._maxDepth;
    }

    public void setDefaultValue(T t) {
        this._root.setValue(t);
    }
}
