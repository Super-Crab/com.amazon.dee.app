package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
/* loaded from: classes2.dex */
public class MemoryChunkUtil {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int adjustByteCount(final int offset, final int count, final int memorySize) {
        return Math.min(Math.max(0, memorySize - offset), count);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkBounds(final int offset, final int otherLength, final int otherOffset, final int count, final int memorySize) {
        boolean z = true;
        Preconditions.checkArgument(Boolean.valueOf(count >= 0));
        Preconditions.checkArgument(Boolean.valueOf(offset >= 0));
        Preconditions.checkArgument(Boolean.valueOf(otherOffset >= 0));
        Preconditions.checkArgument(Boolean.valueOf(offset + count <= memorySize));
        if (otherOffset + count > otherLength) {
            z = false;
        }
        Preconditions.checkArgument(Boolean.valueOf(z));
    }
}
