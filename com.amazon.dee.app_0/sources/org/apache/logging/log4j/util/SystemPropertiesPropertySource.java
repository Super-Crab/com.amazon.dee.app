package org.apache.logging.log4j.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import java.util.Properties;
import org.apache.logging.log4j.util.PropertySource;
/* loaded from: classes4.dex */
public class SystemPropertiesPropertySource implements PropertySource {
    private static final int DEFAULT_PRIORITY = 100;
    private static final String PREFIX = "log4j2.";

    @Override // org.apache.logging.log4j.util.PropertySource
    public void forEach(BiConsumer<String, String> biConsumer) {
        Object[] array;
        try {
            Properties properties = System.getProperties();
            synchronized (properties) {
                array = properties.keySet().toArray();
            }
            for (Object obj : array) {
                String objects = Objects.toString(obj, null);
                biConsumer.accept(objects, properties.getProperty(objects));
            }
        } catch (SecurityException unused) {
        }
    }

    @Override // org.apache.logging.log4j.util.PropertySource
    public CharSequence getNormalForm(Iterable<? extends CharSequence> iterable) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(PREFIX);
        outline107.append((Object) PropertySource.Util.joinAsCamelCase(iterable));
        return outline107.toString();
    }

    @Override // org.apache.logging.log4j.util.PropertySource
    public int getPriority() {
        return 100;
    }
}
