package com.amazonaws.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.jar.JarFile;
/* loaded from: classes.dex */
public enum Classes {
    ;

    public static Class<?> childClassOf(Class<?> cls, Object obj) {
        if (obj == null || obj == Object.class) {
            return null;
        }
        if (cls != null && cls.isInterface()) {
            return null;
        }
        Class<?> cls2 = obj.getClass();
        while (true) {
            Class<? super Object> superclass = cls2.getSuperclass();
            if (superclass == cls) {
                return cls2;
            }
            if (superclass == null) {
                return null;
            }
            cls2 = superclass;
        }
    }

    public static JarFile jarFileOf(Class<?> cls) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("/");
        outline107.append(cls.getName().replace('.', '/'));
        outline107.append(".class");
        URL resource = cls.getResource(outline107.toString());
        if (resource == null) {
            return null;
        }
        String file = resource.getFile();
        int indexOf = file.indexOf("file:") + 5;
        int indexOf2 = file.indexOf(".jar!");
        if (indexOf2 == -1) {
            return null;
        }
        File file2 = new File(file.substring(indexOf, indexOf2 + 4));
        try {
            if (!file2.exists()) {
                return null;
            }
            return new JarFile(file2);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
