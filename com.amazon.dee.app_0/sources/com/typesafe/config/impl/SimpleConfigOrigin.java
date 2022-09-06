package com.typesafe.config.impl;

import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.impl.SerializedConfigValue;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class SimpleConfigOrigin implements ConfigOrigin {
    static final String MERGE_OF_PREFIX = "merge of ";
    private final List<String> commentsOrNull;
    private final String description;
    private final int endLineNumber;
    private final int lineNumber;
    private final OriginType originType;
    private final String resourceOrNull;
    private final String urlOrNull;

    /* renamed from: com.typesafe.config.impl.SimpleConfigOrigin$1  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField = new int[SerializedConfigValue.SerializedField.values().length];

        static {
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedConfigValue.SerializedField.ORIGIN_DESCRIPTION.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedConfigValue.SerializedField.ORIGIN_LINE_NUMBER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedConfigValue.SerializedField.ORIGIN_END_LINE_NUMBER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedConfigValue.SerializedField.ORIGIN_TYPE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedConfigValue.SerializedField.ORIGIN_URL.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedConfigValue.SerializedField.ORIGIN_RESOURCE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedConfigValue.SerializedField.ORIGIN_COMMENTS.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedConfigValue.SerializedField.ORIGIN_NULL_URL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedConfigValue.SerializedField.ORIGIN_NULL_RESOURCE.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedConfigValue.SerializedField.ORIGIN_NULL_COMMENTS.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedConfigValue.SerializedField.END_MARKER.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedConfigValue.SerializedField.ROOT_VALUE.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedConfigValue.SerializedField.ROOT_WAS_CONFIG.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedConfigValue.SerializedField.UNKNOWN.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedConfigValue.SerializedField.VALUE_DATA.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$typesafe$config$impl$SerializedConfigValue$SerializedField[SerializedConfigValue.SerializedField.VALUE_ORIGIN.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    protected SimpleConfigOrigin(String str, int i, int i2, OriginType originType, String str2, String str3, List<String> list) {
        if (str != null) {
            this.description = str;
            this.lineNumber = i;
            this.endLineNumber = i2;
            this.originType = originType;
            this.urlOrNull = str2;
            this.resourceOrNull = str3;
            this.commentsOrNull = list;
            return;
        }
        throw new ConfigException.BugOrBroken("description may not be null");
    }

    static Map<SerializedConfigValue.SerializedField, Object> applyFieldsDelta(Map<SerializedConfigValue.SerializedField, Object> map, Map<SerializedConfigValue.SerializedField, Object> map2) throws IOException {
        EnumMap enumMap = new EnumMap(map2);
        for (Map.Entry<SerializedConfigValue.SerializedField, Object> entry : map.entrySet()) {
            SerializedConfigValue.SerializedField key = entry.getKey();
            if (!map2.containsKey(key)) {
                switch (key.ordinal()) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        throw new ConfigException.BugOrBroken("should not appear here: " + key);
                    case 6:
                        enumMap.put((EnumMap) key, (SerializedConfigValue.SerializedField) map.get(key));
                        continue;
                    case 7:
                    case 8:
                    case 9:
                        enumMap.put((EnumMap) key, (SerializedConfigValue.SerializedField) map.get(key));
                        continue;
                    case 10:
                        if (map2.containsKey(SerializedConfigValue.SerializedField.ORIGIN_NULL_URL)) {
                            enumMap.remove(SerializedConfigValue.SerializedField.ORIGIN_NULL_URL);
                            break;
                        } else {
                            enumMap.put((EnumMap) key, (SerializedConfigValue.SerializedField) map.get(key));
                            continue;
                        }
                    case 11:
                        if (map2.containsKey(SerializedConfigValue.SerializedField.ORIGIN_NULL_COMMENTS)) {
                            enumMap.remove(SerializedConfigValue.SerializedField.ORIGIN_NULL_COMMENTS);
                            break;
                        } else {
                            enumMap.put((EnumMap) key, (SerializedConfigValue.SerializedField) map.get(key));
                            continue;
                        }
                    case 12:
                    case 13:
                    case 15:
                        throw new ConfigException.BugOrBroken("applying fields, base object should not contain " + key + " " + map);
                    case 14:
                        if (map2.containsKey(SerializedConfigValue.SerializedField.ORIGIN_NULL_RESOURCE)) {
                            enumMap.remove(SerializedConfigValue.SerializedField.ORIGIN_NULL_RESOURCE);
                            break;
                        } else {
                            enumMap.put((EnumMap) key, (SerializedConfigValue.SerializedField) map.get(key));
                            continue;
                        }
                }
            }
        }
        return enumMap;
    }

    static Map<SerializedConfigValue.SerializedField, Object> fieldsDelta(Map<SerializedConfigValue.SerializedField, Object> map, Map<SerializedConfigValue.SerializedField, Object> map2) {
        EnumMap enumMap = new EnumMap(map2);
        for (Map.Entry<SerializedConfigValue.SerializedField, Object> entry : map.entrySet()) {
            SerializedConfigValue.SerializedField key = entry.getKey();
            if (enumMap.containsKey(key) && ConfigImplUtil.equalsHandlingNull(entry.getValue(), enumMap.get(key))) {
                enumMap.remove(key);
            } else if (!enumMap.containsKey(key)) {
                switch (key.ordinal()) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        throw new ConfigException.BugOrBroken("should not appear here: " + key);
                    case 6:
                        throw new ConfigException.BugOrBroken("origin missing description field? " + map2);
                    case 7:
                        enumMap.put((EnumMap) SerializedConfigValue.SerializedField.ORIGIN_LINE_NUMBER, (SerializedConfigValue.SerializedField) (-1));
                        continue;
                    case 8:
                        enumMap.put((EnumMap) SerializedConfigValue.SerializedField.ORIGIN_END_LINE_NUMBER, (SerializedConfigValue.SerializedField) (-1));
                        continue;
                    case 9:
                        throw new ConfigException.BugOrBroken("should always be an ORIGIN_TYPE field");
                    case 10:
                        enumMap.put((EnumMap) SerializedConfigValue.SerializedField.ORIGIN_NULL_URL, (SerializedConfigValue.SerializedField) "");
                        continue;
                    case 11:
                        enumMap.put((EnumMap) SerializedConfigValue.SerializedField.ORIGIN_NULL_COMMENTS, (SerializedConfigValue.SerializedField) "");
                        continue;
                    case 12:
                    case 13:
                    case 15:
                        throw new ConfigException.BugOrBroken("computing delta, base object should not contain " + key + " " + map);
                    case 14:
                        enumMap.put((EnumMap) SerializedConfigValue.SerializedField.ORIGIN_NULL_RESOURCE, (SerializedConfigValue.SerializedField) "");
                        continue;
                }
            } else {
                continue;
            }
        }
        return enumMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SimpleConfigOrigin fromBase(SimpleConfigOrigin simpleConfigOrigin, Map<SerializedConfigValue.SerializedField, Object> map) throws IOException {
        Map<SerializedConfigValue.SerializedField, Object> emptyMap;
        if (simpleConfigOrigin != null) {
            emptyMap = simpleConfigOrigin.toFields();
        } else {
            emptyMap = Collections.emptyMap();
        }
        return fromFields(applyFieldsDelta(emptyMap, map));
    }

    static SimpleConfigOrigin fromFields(Map<SerializedConfigValue.SerializedField, Object> map) throws IOException {
        if (map.isEmpty()) {
            return null;
        }
        String str = (String) map.get(SerializedConfigValue.SerializedField.ORIGIN_DESCRIPTION);
        Integer num = (Integer) map.get(SerializedConfigValue.SerializedField.ORIGIN_LINE_NUMBER);
        Integer num2 = (Integer) map.get(SerializedConfigValue.SerializedField.ORIGIN_END_LINE_NUMBER);
        Number number = (Number) map.get(SerializedConfigValue.SerializedField.ORIGIN_TYPE);
        if (number != null) {
            OriginType originType = OriginType.values()[number.byteValue()];
            String str2 = (String) map.get(SerializedConfigValue.SerializedField.ORIGIN_URL);
            String str3 = (String) map.get(SerializedConfigValue.SerializedField.ORIGIN_RESOURCE);
            List list = (List) map.get(SerializedConfigValue.SerializedField.ORIGIN_COMMENTS);
            return new SimpleConfigOrigin(str, num != null ? num.intValue() : -1, num2 != null ? num2.intValue() : -1, originType, str2, (originType == OriginType.RESOURCE && str3 == null) ? str : str3, list);
        }
        throw new IOException("Missing ORIGIN_TYPE field");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ConfigOrigin mergeOrigins(ConfigOrigin configOrigin, ConfigOrigin configOrigin2) {
        return mergeTwo((SimpleConfigOrigin) configOrigin, (SimpleConfigOrigin) configOrigin2);
    }

    private static SimpleConfigOrigin mergeThree(SimpleConfigOrigin simpleConfigOrigin, SimpleConfigOrigin simpleConfigOrigin2, SimpleConfigOrigin simpleConfigOrigin3) {
        if (similarity(simpleConfigOrigin, simpleConfigOrigin2) >= similarity(simpleConfigOrigin2, simpleConfigOrigin3)) {
            return mergeTwo(mergeTwo(simpleConfigOrigin, simpleConfigOrigin2), simpleConfigOrigin3);
        }
        return mergeTwo(simpleConfigOrigin, mergeTwo(simpleConfigOrigin2, simpleConfigOrigin3));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static SimpleConfigOrigin mergeTwo(SimpleConfigOrigin simpleConfigOrigin, SimpleConfigOrigin simpleConfigOrigin2) {
        String outline76;
        int i;
        List list;
        OriginType originType = simpleConfigOrigin.originType;
        if (originType != simpleConfigOrigin2.originType) {
            originType = OriginType.GENERIC;
        }
        OriginType originType2 = originType;
        String str = simpleConfigOrigin.description;
        String str2 = simpleConfigOrigin2.description;
        if (str.startsWith(MERGE_OF_PREFIX)) {
            str = str.substring(9);
        }
        if (str2.startsWith(MERGE_OF_PREFIX)) {
            str2 = str2.substring(9);
        }
        int i2 = -1;
        if (str.equals(str2)) {
            int i3 = simpleConfigOrigin.lineNumber;
            if (i3 < 0) {
                i3 = simpleConfigOrigin2.lineNumber;
            } else {
                int i4 = simpleConfigOrigin2.lineNumber;
                if (i4 >= 0) {
                    i3 = Math.min(i3, i4);
                }
            }
            i2 = i3;
            i = Math.max(simpleConfigOrigin.endLineNumber, simpleConfigOrigin2.endLineNumber);
            outline76 = str;
        } else {
            String description = simpleConfigOrigin.description();
            String description2 = simpleConfigOrigin2.description();
            if (description.startsWith(MERGE_OF_PREFIX)) {
                description = description.substring(9);
            }
            if (description2.startsWith(MERGE_OF_PREFIX)) {
                description2 = description2.substring(9);
            }
            outline76 = GeneratedOutlineSupport1.outline76(MERGE_OF_PREFIX, description, ",", description2);
            i = -1;
        }
        String str3 = ConfigImplUtil.equalsHandlingNull(simpleConfigOrigin.urlOrNull, simpleConfigOrigin2.urlOrNull) ? simpleConfigOrigin.urlOrNull : null;
        String str4 = ConfigImplUtil.equalsHandlingNull(simpleConfigOrigin.resourceOrNull, simpleConfigOrigin2.resourceOrNull) ? simpleConfigOrigin.resourceOrNull : null;
        if (ConfigImplUtil.equalsHandlingNull(simpleConfigOrigin.commentsOrNull, simpleConfigOrigin2.commentsOrNull)) {
            list = simpleConfigOrigin.commentsOrNull;
        } else {
            List arrayList = new ArrayList();
            List<String> list2 = simpleConfigOrigin.commentsOrNull;
            if (list2 != null) {
                arrayList.addAll(list2);
            }
            List<String> list3 = simpleConfigOrigin2.commentsOrNull;
            if (list3 != null) {
                arrayList.addAll(list3);
            }
            list = arrayList;
        }
        return new SimpleConfigOrigin(outline76, i2, i, originType2, str3, str4, list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SimpleConfigOrigin newFile(String str) {
        String str2;
        try {
            str2 = new File(str).toURI().toURL().toExternalForm();
        } catch (MalformedURLException unused) {
            str2 = null;
        }
        return new SimpleConfigOrigin(str, -1, -1, OriginType.FILE, str2, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SimpleConfigOrigin newResource(String str, URL url) {
        String str2;
        if (url != null) {
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, " @ ");
            outline113.append(url.toExternalForm());
            str2 = outline113.toString();
        } else {
            str2 = str;
        }
        return new SimpleConfigOrigin(str2, -1, -1, OriginType.RESOURCE, url != null ? url.toExternalForm() : null, str, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SimpleConfigOrigin newSimple(String str) {
        return new SimpleConfigOrigin(str, -1, -1, OriginType.GENERIC, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SimpleConfigOrigin newURL(URL url) {
        String externalForm = url.toExternalForm();
        return new SimpleConfigOrigin(externalForm, -1, -1, OriginType.URL, externalForm, null, null);
    }

    private static int similarity(SimpleConfigOrigin simpleConfigOrigin, SimpleConfigOrigin simpleConfigOrigin2) {
        int i = simpleConfigOrigin.originType == simpleConfigOrigin2.originType ? 1 : 0;
        if (simpleConfigOrigin.description.equals(simpleConfigOrigin2.description)) {
            int i2 = i + 1;
            if (simpleConfigOrigin.lineNumber == simpleConfigOrigin2.lineNumber) {
                i2++;
            }
            if (simpleConfigOrigin.endLineNumber == simpleConfigOrigin2.endLineNumber) {
                i2++;
            }
            if (ConfigImplUtil.equalsHandlingNull(simpleConfigOrigin.urlOrNull, simpleConfigOrigin2.urlOrNull)) {
                i2++;
            }
            return ConfigImplUtil.equalsHandlingNull(simpleConfigOrigin.resourceOrNull, simpleConfigOrigin2.resourceOrNull) ? i2 + 1 : i2;
        }
        return i;
    }

    SimpleConfigOrigin addURL(URL url) {
        return new SimpleConfigOrigin(this.description, this.lineNumber, this.endLineNumber, this.originType, url != null ? url.toExternalForm() : null, this.resourceOrNull, this.commentsOrNull);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleConfigOrigin appendComments(List<String> list) {
        if (ConfigImplUtil.equalsHandlingNull(list, this.commentsOrNull) || list == null) {
            return this;
        }
        if (this.commentsOrNull == null) {
            return mo10262withComments(list);
        }
        ArrayList arrayList = new ArrayList(this.commentsOrNull.size() + list.size());
        arrayList.addAll(this.commentsOrNull);
        arrayList.addAll(list);
        return mo10262withComments((List<String>) arrayList);
    }

    @Override // com.typesafe.config.ConfigOrigin
    public List<String> comments() {
        List<String> list = this.commentsOrNull;
        if (list != null) {
            return Collections.unmodifiableList(list);
        }
        return Collections.emptyList();
    }

    @Override // com.typesafe.config.ConfigOrigin
    public String description() {
        int i = this.lineNumber;
        if (i < 0) {
            return this.description;
        }
        if (this.endLineNumber == i) {
            return this.description + RealTimeTextConstants.COLON_SPACE + this.lineNumber;
        }
        return this.description + RealTimeTextConstants.COLON_SPACE + this.lineNumber + ProcessIdUtil.DEFAULT_PROCESSID + this.endLineNumber;
    }

    public boolean equals(Object obj) {
        if (obj instanceof SimpleConfigOrigin) {
            SimpleConfigOrigin simpleConfigOrigin = (SimpleConfigOrigin) obj;
            return this.description.equals(simpleConfigOrigin.description) && this.lineNumber == simpleConfigOrigin.lineNumber && this.endLineNumber == simpleConfigOrigin.endLineNumber && this.originType == simpleConfigOrigin.originType && ConfigImplUtil.equalsHandlingNull(this.urlOrNull, simpleConfigOrigin.urlOrNull) && ConfigImplUtil.equalsHandlingNull(this.resourceOrNull, simpleConfigOrigin.resourceOrNull);
        }
        return false;
    }

    @Override // com.typesafe.config.ConfigOrigin
    public String filename() {
        if (this.originType == OriginType.FILE) {
            return this.description;
        }
        String str = this.urlOrNull;
        if (str != null) {
            try {
                URL url = new URL(str);
                if (url.getProtocol().equals("file")) {
                    return url.getFile();
                }
            } catch (MalformedURLException unused) {
            }
        }
        return null;
    }

    public int hashCode() {
        int hashCode = (this.originType.hashCode() + ((((GeneratedOutlineSupport1.outline7(this.description, 41, 41) + this.lineNumber) * 41) + this.endLineNumber) * 41)) * 41;
        String str = this.urlOrNull;
        if (str != null) {
            hashCode = GeneratedOutlineSupport1.outline7(str, hashCode, 41);
        }
        String str2 = this.resourceOrNull;
        return str2 != null ? GeneratedOutlineSupport1.outline7(str2, hashCode, 41) : hashCode;
    }

    @Override // com.typesafe.config.ConfigOrigin
    public int lineNumber() {
        return this.lineNumber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleConfigOrigin prependComments(List<String> list) {
        if (ConfigImplUtil.equalsHandlingNull(list, this.commentsOrNull) || list == null) {
            return this;
        }
        if (this.commentsOrNull == null) {
            return mo10262withComments(list);
        }
        ArrayList arrayList = new ArrayList(this.commentsOrNull.size() + list.size());
        arrayList.addAll(list);
        arrayList.addAll(this.commentsOrNull);
        return mo10262withComments((List<String>) arrayList);
    }

    @Override // com.typesafe.config.ConfigOrigin
    public String resource() {
        return this.resourceOrNull;
    }

    Map<SerializedConfigValue.SerializedField, Object> toFields() {
        EnumMap enumMap = new EnumMap(SerializedConfigValue.SerializedField.class);
        enumMap.put((EnumMap) SerializedConfigValue.SerializedField.ORIGIN_DESCRIPTION, (SerializedConfigValue.SerializedField) this.description);
        int i = this.lineNumber;
        if (i >= 0) {
            enumMap.put((EnumMap) SerializedConfigValue.SerializedField.ORIGIN_LINE_NUMBER, (SerializedConfigValue.SerializedField) Integer.valueOf(i));
        }
        int i2 = this.endLineNumber;
        if (i2 >= 0) {
            enumMap.put((EnumMap) SerializedConfigValue.SerializedField.ORIGIN_END_LINE_NUMBER, (SerializedConfigValue.SerializedField) Integer.valueOf(i2));
        }
        enumMap.put((EnumMap) SerializedConfigValue.SerializedField.ORIGIN_TYPE, (SerializedConfigValue.SerializedField) Integer.valueOf(this.originType.ordinal()));
        String str = this.urlOrNull;
        if (str != null) {
            enumMap.put((EnumMap) SerializedConfigValue.SerializedField.ORIGIN_URL, (SerializedConfigValue.SerializedField) str);
        }
        String str2 = this.resourceOrNull;
        if (str2 != null) {
            enumMap.put((EnumMap) SerializedConfigValue.SerializedField.ORIGIN_RESOURCE, (SerializedConfigValue.SerializedField) str2);
        }
        List<String> list = this.commentsOrNull;
        if (list != null) {
            enumMap.put((EnumMap) SerializedConfigValue.SerializedField.ORIGIN_COMMENTS, (SerializedConfigValue.SerializedField) list);
        }
        return enumMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<SerializedConfigValue.SerializedField, Object> toFieldsDelta(SimpleConfigOrigin simpleConfigOrigin) {
        Map<SerializedConfigValue.SerializedField, Object> emptyMap;
        if (simpleConfigOrigin != null) {
            emptyMap = simpleConfigOrigin.toFields();
        } else {
            emptyMap = Collections.emptyMap();
        }
        return fieldsDelta(emptyMap, toFields());
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("ConfigOrigin("), this.description, ")");
    }

    @Override // com.typesafe.config.ConfigOrigin
    public URL url() {
        String str = this.urlOrNull;
        if (str == null) {
            return null;
        }
        try {
            return new URL(str);
        } catch (MalformedURLException unused) {
            return null;
        }
    }

    @Override // com.typesafe.config.ConfigOrigin
    /* renamed from: withComments */
    public /* bridge */ /* synthetic */ ConfigOrigin mo10262withComments(List list) {
        return mo10262withComments((List<String>) list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ConfigOrigin mergeOrigins(List<? extends AbstractConfigValue> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (AbstractConfigValue abstractConfigValue : list) {
            arrayList.add(abstractConfigValue.mo10176origin());
        }
        return mergeOrigins((Collection<? extends ConfigOrigin>) arrayList);
    }

    @Override // com.typesafe.config.ConfigOrigin
    /* renamed from: withComments  reason: collision with other method in class */
    public SimpleConfigOrigin mo10262withComments(List<String> list) {
        return ConfigImplUtil.equalsHandlingNull(list, this.commentsOrNull) ? this : new SimpleConfigOrigin(this.description, this.lineNumber, this.endLineNumber, this.originType, this.urlOrNull, this.resourceOrNull, list);
    }

    @Override // com.typesafe.config.ConfigOrigin
    /* renamed from: withLineNumber  reason: collision with other method in class */
    public SimpleConfigOrigin mo10263withLineNumber(int i) {
        return (i == this.lineNumber && i == this.endLineNumber) ? this : new SimpleConfigOrigin(this.description, i, i, this.originType, this.urlOrNull, this.resourceOrNull, this.commentsOrNull);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SimpleConfigOrigin newResource(String str) {
        return newResource(str, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ConfigOrigin mergeOrigins(Collection<? extends ConfigOrigin> collection) {
        if (!collection.isEmpty()) {
            if (collection.size() == 1) {
                return collection.iterator().next();
            }
            if (collection.size() == 2) {
                Iterator<? extends ConfigOrigin> it2 = collection.iterator();
                return mergeTwo((SimpleConfigOrigin) it2.next(), (SimpleConfigOrigin) it2.next());
            }
            ArrayList arrayList = new ArrayList();
            Iterator<? extends ConfigOrigin> it3 = collection.iterator();
            while (it3.hasNext()) {
                arrayList.add((SimpleConfigOrigin) it3.next());
            }
            while (arrayList.size() > 2) {
                arrayList.remove(arrayList.size() - 1);
                SimpleConfigOrigin simpleConfigOrigin = (SimpleConfigOrigin) arrayList.get(arrayList.size() - 1);
                arrayList.remove(arrayList.size() - 1);
                SimpleConfigOrigin simpleConfigOrigin2 = (SimpleConfigOrigin) arrayList.get(arrayList.size() - 1);
                arrayList.remove(arrayList.size() - 1);
                arrayList.add(mergeThree(simpleConfigOrigin2, simpleConfigOrigin, (SimpleConfigOrigin) arrayList.get(arrayList.size() - 1)));
            }
            return mergeOrigins((Collection<? extends ConfigOrigin>) arrayList);
        }
        throw new ConfigException.BugOrBroken("can't merge empty list of origins");
    }
}
