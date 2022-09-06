package com.amazon.alexa.accessorykit.accessories.utils;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.facebook.react.bridge.WritableArray;
import java.util.List;
/* loaded from: classes6.dex */
public final class WritableArrayUtils {
    private WritableArrayUtils() {
    }

    public static <T extends Enum<T>> WritableArray createWritableArray(ContainerProvider containerProvider, List<T> list) {
        Preconditions.notNull(containerProvider, "containerProvider");
        Preconditions.notNull(list, "listOfEnums");
        WritableArray array = containerProvider.getArray();
        for (T t : list) {
            array.pushString(t.name());
        }
        return array;
    }
}
