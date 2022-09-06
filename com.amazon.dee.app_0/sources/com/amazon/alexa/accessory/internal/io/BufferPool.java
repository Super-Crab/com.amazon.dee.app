package com.amazon.alexa.accessory.internal.io;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.Buffer;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
/* loaded from: classes.dex */
public final class BufferPool {
    private static final BufferPool INSTANCE = new BufferPool();
    private final Deque<Buffer> deque = new LinkedList();

    public static BufferPool shared() {
        return INSTANCE;
    }

    public void recycle(Buffer buffer) {
        Preconditions.notNull(buffer, "buffer");
        synchronized (this.deque) {
            this.deque.add(buffer);
        }
    }

    public Buffer request(int i) {
        synchronized (this.deque) {
            Iterator<Buffer> it2 = this.deque.iterator();
            while (it2.hasNext()) {
                Buffer next = it2.next();
                if (next.capacity() >= i) {
                    it2.remove();
                    next.close();
                    return next;
                }
            }
            Logger.d("Allocation in a buffer pool of %d bytes, size %d", Integer.valueOf(i), Integer.valueOf(this.deque.size()));
            return new Buffer(i);
        }
    }
}
