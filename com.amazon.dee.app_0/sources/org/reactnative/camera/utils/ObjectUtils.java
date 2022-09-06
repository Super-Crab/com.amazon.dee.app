package org.reactnative.camera.utils;
/* loaded from: classes5.dex */
public class ObjectUtils {
    public static boolean equals(Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return true;
        }
        if (obj != null) {
            return obj.equals(obj2);
        }
        return false;
    }
}
