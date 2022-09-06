package com.amazon.alexa.ttcf;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Iterator;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public class PushOnlyStack<Element> implements Iterable<Element> {
    private int capacity;
    private final Element[] items;
    private int lastStoredIndex = -1;

    /* loaded from: classes10.dex */
    class StackIterator implements Iterator {
        private final int start;
        private int times = 0;

        StackIterator() {
            this.start = PushOnlyStack.this.lastStoredIndex;
        }

        private int getEffectiveIndex() {
            int length = PushOnlyStack.this.items.length;
            return ((this.start - this.times) + length) % length;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.times < PushOnlyStack.this.items.length && PushOnlyStack.this.items[getEffectiveIndex()] != null;
        }

        @Override // java.util.Iterator
        @NonNull
        public Element next() {
            int effectiveIndex = getEffectiveIndex();
            this.times++;
            return (Element) PushOnlyStack.this.items[effectiveIndex];
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PushOnlyStack(int i) {
        this.capacity = i;
        this.items = (Element[]) new Object[i];
    }

    @Override // java.lang.Iterable
    public Iterator<Element> iterator() {
        return new StackIterator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Element peek() {
        int i = this.lastStoredIndex;
        if (i >= 0) {
            return this.items[i];
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void push(@NonNull Element element) {
        this.lastStoredIndex = (this.lastStoredIndex + 1) % this.capacity;
        this.items[this.lastStoredIndex] = element;
    }
}
