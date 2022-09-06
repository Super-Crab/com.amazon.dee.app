package com.amazon.clouddrive.internal.utils;

import java.io.Closeable;
import java.io.IOException;
/* loaded from: classes11.dex */
public class Closer {
    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
