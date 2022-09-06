package com.amazonaws.com.google.gson;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Field;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes13.dex */
public enum FieldNamingPolicy implements FieldNamingStrategy {
    IDENTITY { // from class: com.amazonaws.com.google.gson.FieldNamingPolicy.1
        @Override // com.amazonaws.com.google.gson.FieldNamingStrategy
        public String translateName(Field field) {
            return field.getName();
        }
    },
    UPPER_CAMEL_CASE { // from class: com.amazonaws.com.google.gson.FieldNamingPolicy.2
        @Override // com.amazonaws.com.google.gson.FieldNamingStrategy
        public String translateName(Field field) {
            return FieldNamingPolicy.upperCaseFirstLetter(field.getName());
        }
    },
    UPPER_CAMEL_CASE_WITH_SPACES { // from class: com.amazonaws.com.google.gson.FieldNamingPolicy.3
        @Override // com.amazonaws.com.google.gson.FieldNamingStrategy
        public String translateName(Field field) {
            return FieldNamingPolicy.upperCaseFirstLetter(FieldNamingPolicy.separateCamelCase(field.getName(), " "));
        }
    },
    LOWER_CASE_WITH_UNDERSCORES { // from class: com.amazonaws.com.google.gson.FieldNamingPolicy.4
        @Override // com.amazonaws.com.google.gson.FieldNamingStrategy
        public String translateName(Field field) {
            return FieldNamingPolicy.separateCamelCase(field.getName(), "_").toLowerCase();
        }
    },
    LOWER_CASE_WITH_DASHES { // from class: com.amazonaws.com.google.gson.FieldNamingPolicy.5
        @Override // com.amazonaws.com.google.gson.FieldNamingStrategy
        public String translateName(Field field) {
            return FieldNamingPolicy.separateCamelCase(field.getName(), ProcessIdUtil.DEFAULT_PROCESSID).toLowerCase();
        }
    };

    private static String modifyString(char c, String str, int i) {
        return i < str.length() ? GeneratedOutlineSupport1.outline55(str, i, GeneratedOutlineSupport1.outline104(c)) : String.valueOf(c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String separateCamelCase(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt) && sb.length() != 0) {
                sb.append(str2);
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String upperCaseFirstLetter(String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        char charAt = str.charAt(0);
        while (i < str.length() - 1 && !Character.isLetter(charAt)) {
            sb.append(charAt);
            i++;
            charAt = str.charAt(i);
        }
        if (i == str.length()) {
            return sb.toString();
        }
        if (Character.isUpperCase(charAt)) {
            return str;
        }
        sb.append(modifyString(Character.toUpperCase(charAt), str, i + 1));
        return sb.toString();
    }
}
