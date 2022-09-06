package com.google.protobuf;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import com.google.protobuf.GeneratedMessageLite;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.apache.logging.log4j.util.Chars;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class MessageLiteToString {
    private static final String BUILDER_LIST_SUFFIX = "OrBuilderList";
    private static final String BYTES_SUFFIX = "Bytes";
    private static final String LIST_SUFFIX = "List";

    MessageLiteToString() {
    }

    private static final String camelCaseToSnakeCase(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }

    private static boolean isDefaultValue(Object obj) {
        Object obj2;
        if (obj instanceof Boolean) {
            return !((Boolean) obj).booleanValue();
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue() == 0;
        } else if (obj instanceof Float) {
            return ((Float) obj).floatValue() == 0.0f;
        } else if (obj instanceof Double) {
            return ((Double) obj).doubleValue() == FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        } else {
            if (obj instanceof String) {
                obj2 = "";
            } else if (!(obj instanceof ByteString)) {
                return obj instanceof MessageLite ? obj == ((MessageLite) obj).mo10094getDefaultInstanceForType() : (obj instanceof java.lang.Enum) && ((java.lang.Enum) obj).ordinal() == 0;
            } else {
                obj2 = ByteString.EMPTY;
            }
            return obj.equals(obj2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void printField(StringBuilder sb, int i, String str, Object obj) {
        String obj2;
        String escapeBytes;
        if (obj instanceof List) {
            for (Object obj3 : (List) obj) {
                printField(sb, i, str, obj3);
            }
            return;
        }
        sb.append('\n');
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(Chars.SPACE);
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"");
            escapeBytes = TextFormatEscaper.escapeText((String) obj);
        } else if (!(obj instanceof ByteString)) {
            if (obj instanceof GeneratedMessageLite) {
                sb.append(" {");
                reflectivePrintWithIndent((GeneratedMessageLite) obj, sb, i + 2);
                sb.append("\n");
                for (int i3 = 0; i3 < i; i3++) {
                    sb.append(Chars.SPACE);
                }
                obj2 = EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE;
            } else {
                sb.append(RealTimeTextConstants.COLON_SPACE);
                obj2 = obj.toString();
            }
            sb.append(obj2);
            return;
        } else {
            sb.append(": \"");
            escapeBytes = TextFormatEscaper.escapeBytes((ByteString) obj);
        }
        sb.append(escapeBytes);
        sb.append('\"');
    }

    private static void reflectivePrintWithIndent(MessageLite messageLite, StringBuilder sb, int i) {
        java.lang.reflect.Method[] declaredMethods;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        TreeSet<String> treeSet = new TreeSet();
        for (java.lang.reflect.Method method : messageLite.getClass().getDeclaredMethods()) {
            hashMap2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                hashMap.put(method.getName(), method);
                if (method.getName().startsWith(MetricsConstants.Method.CACHE_GET)) {
                    treeSet.add(method.getName());
                }
            }
        }
        for (String str : treeSet) {
            String replaceFirst = str.replaceFirst(MetricsConstants.Method.CACHE_GET, "");
            if (replaceFirst.endsWith(LIST_SUFFIX) && !replaceFirst.endsWith(BUILDER_LIST_SUFFIX)) {
                String str2 = replaceFirst.substring(0, 1).toLowerCase() + replaceFirst.substring(1, replaceFirst.length() - 4);
                java.lang.reflect.Method method2 = (java.lang.reflect.Method) hashMap.get(MetricsConstants.Method.CACHE_GET + replaceFirst);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    printField(sb, i, camelCaseToSnakeCase(str2), GeneratedMessageLite.invokeOrDie(method2, messageLite, new Object[0]));
                }
            }
            if (((java.lang.reflect.Method) hashMap2.get("set" + replaceFirst)) != null) {
                if (replaceFirst.endsWith(BYTES_SUFFIX)) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107(MetricsConstants.Method.CACHE_GET);
                    outline107.append(replaceFirst.substring(0, replaceFirst.length() - 5));
                    if (hashMap.containsKey(outline107.toString())) {
                    }
                }
                String str3 = replaceFirst.substring(0, 1).toLowerCase() + replaceFirst.substring(1);
                java.lang.reflect.Method method3 = (java.lang.reflect.Method) hashMap.get(MetricsConstants.Method.CACHE_GET + replaceFirst);
                java.lang.reflect.Method method4 = (java.lang.reflect.Method) hashMap.get("has" + replaceFirst);
                if (method3 != null) {
                    Object invokeOrDie = GeneratedMessageLite.invokeOrDie(method3, messageLite, new Object[0]);
                    if (method4 == null ? !isDefaultValue(invokeOrDie) : ((Boolean) GeneratedMessageLite.invokeOrDie(method4, messageLite, new Object[0])).booleanValue()) {
                        printField(sb, i, camelCaseToSnakeCase(str3), invokeOrDie);
                    }
                }
            }
        }
        if (messageLite instanceof GeneratedMessageLite.ExtendableMessage) {
            Iterator<Map.Entry<GeneratedMessageLite.ExtensionDescriptor, Object>> it2 = ((GeneratedMessageLite.ExtendableMessage) messageLite).extensions.iterator();
            while (it2.hasNext()) {
                Map.Entry<GeneratedMessageLite.ExtensionDescriptor, Object> next = it2.next();
                printField(sb, i, GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("["), next.getKey().getNumber(), "]"), next.getValue());
            }
        }
        UnknownFieldSetLite unknownFieldSetLite = ((GeneratedMessageLite) messageLite).unknownFields;
        if (unknownFieldSetLite != null) {
            unknownFieldSetLite.printWithIndent(sb, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String toString(MessageLite messageLite, String str) {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("# ", str);
        reflectivePrintWithIndent(messageLite, outline113, 0);
        return outline113.toString();
    }
}
