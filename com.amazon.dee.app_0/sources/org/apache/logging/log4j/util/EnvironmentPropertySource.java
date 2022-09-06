package org.apache.logging.log4j.util;

import java.util.Map;
/* loaded from: classes4.dex */
public class EnvironmentPropertySource implements PropertySource {
    private static final int DEFAULT_PRIORITY = -100;
    private static final String PREFIX = "LOG4J_";

    @Override // org.apache.logging.log4j.util.PropertySource
    public void forEach(BiConsumer<String, String> biConsumer) {
        try {
            for (Map.Entry<String, String> entry : System.getenv().entrySet()) {
                String key = entry.getKey();
                if (key.startsWith(PREFIX)) {
                    biConsumer.accept(key.substring(6), entry.getValue());
                }
            }
        } catch (SecurityException e) {
            LowLevelLogUtil.logException("The system environment variables are not available to Log4j due to security restrictions: " + e, e);
        }
    }

    @Override // org.apache.logging.log4j.util.PropertySource
    public CharSequence getNormalForm(Iterable<? extends CharSequence> iterable) {
        StringBuilder sb = new StringBuilder("LOG4J");
        for (CharSequence charSequence : iterable) {
            sb.append('_');
            for (int i = 0; i < charSequence.length(); i++) {
                sb.append(Character.toUpperCase(charSequence.charAt(i)));
            }
        }
        return sb.toString();
    }

    @Override // org.apache.logging.log4j.util.PropertySource
    public int getPriority() {
        return -100;
    }
}
