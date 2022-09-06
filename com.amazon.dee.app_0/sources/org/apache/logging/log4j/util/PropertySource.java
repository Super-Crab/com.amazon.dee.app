package org.apache.logging.log4j.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes4.dex */
public interface PropertySource {

    /* loaded from: classes4.dex */
    public static class Comparator implements java.util.Comparator<PropertySource>, Serializable {
        private static final long serialVersionUID = 1;

        @Override // java.util.Comparator
        public int compare(PropertySource propertySource, PropertySource propertySource2) {
            return Integer.compare(((PropertySource) Objects.requireNonNull(propertySource)).getPriority(), ((PropertySource) Objects.requireNonNull(propertySource2)).getPriority());
        }
    }

    /* loaded from: classes4.dex */
    public static final class Util {
        private static final String PREFIXES = "(?i:^log4j2?[-._/]?|^org\\.apache\\.logging\\.log4j\\.)?";
        private static final Pattern PROPERTY_TOKENIZER = Pattern.compile("(?i:^log4j2?[-._/]?|^org\\.apache\\.logging\\.log4j\\.)?([A-Z]*[a-z0-9]+|[A-Z0-9]+)[-._/]?");
        private static final Map<CharSequence, List<CharSequence>> CACHE = new ConcurrentHashMap();

        private Util() {
        }

        public static CharSequence joinAsCamelCase(Iterable<? extends CharSequence> iterable) {
            StringBuilder sb = new StringBuilder();
            boolean z = true;
            for (CharSequence charSequence : iterable) {
                if (z) {
                    sb.append(charSequence);
                } else {
                    sb.append(Character.toUpperCase(charSequence.charAt(0)));
                    if (charSequence.length() > 1) {
                        sb.append(charSequence.subSequence(1, charSequence.length()));
                    }
                }
                z = false;
            }
            return sb.toString();
        }

        public static List<CharSequence> tokenize(CharSequence charSequence) {
            if (CACHE.containsKey(charSequence)) {
                return CACHE.get(charSequence);
            }
            ArrayList arrayList = new ArrayList();
            Matcher matcher = PROPERTY_TOKENIZER.matcher(charSequence);
            while (matcher.find()) {
                arrayList.add(matcher.group(1).toLowerCase());
            }
            CACHE.put(charSequence, arrayList);
            return arrayList;
        }
    }

    default boolean containsProperty(String str) {
        return false;
    }

    default void forEach(BiConsumer<String, String> biConsumer) {
    }

    default CharSequence getNormalForm(Iterable<? extends CharSequence> iterable) {
        return null;
    }

    int getPriority();

    default String getProperty(String str) {
        return null;
    }
}
