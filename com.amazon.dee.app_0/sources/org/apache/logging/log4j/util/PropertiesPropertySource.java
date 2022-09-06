package org.apache.logging.log4j.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import java.util.Properties;
import org.apache.logging.log4j.util.PropertySource;
/* loaded from: classes4.dex */
public class PropertiesPropertySource implements PropertySource {
    private static final String PREFIX = "log4j2.";
    private final Properties properties;

    public PropertiesPropertySource(Properties properties) {
        this.properties = properties;
    }

    @Override // org.apache.logging.log4j.util.PropertySource
    public void forEach(BiConsumer<String, String> biConsumer) {
        for (Map.Entry entry : this.properties.entrySet()) {
            biConsumer.accept((String) entry.getKey(), (String) entry.getValue());
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
        return 0;
    }
}
