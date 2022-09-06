package it.innove;

import java.nio.ByteBuffer;
/* loaded from: classes3.dex */
public class NotifyBufferContainer {
    private Integer bufferCount;
    public ByteBuffer items;
    public final String key;
    public final Integer maxCount;

    public NotifyBufferContainer(String str, Integer num) {
        this.key = str;
        this.maxCount = num;
        resetBuffer();
    }

    public void put(byte[] bArr) {
        this.bufferCount = Integer.valueOf(this.bufferCount.intValue() + 1);
        this.items.put(bArr);
    }

    public void resetBuffer() {
        this.bufferCount = 0;
        this.items = ByteBuffer.wrap(new byte[this.maxCount.intValue() * 20]);
    }

    public Integer size() {
        return this.bufferCount;
    }
}
