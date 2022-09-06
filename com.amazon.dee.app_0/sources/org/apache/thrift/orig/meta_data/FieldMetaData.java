package org.apache.thrift.orig.meta_data;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.thrift.orig.TBase;
import org.apache.thrift.orig.TFieldIdEnum;
/* loaded from: classes4.dex */
public class FieldMetaData implements Serializable {
    private static Map<Class<? extends TBase>, Map<? extends TFieldIdEnum, FieldMetaData>> structMap = new HashMap();
    public final String fieldName;
    public final byte requirementType;
    public final FieldValueMetaData valueMetaData;

    public FieldMetaData(String str, byte b, FieldValueMetaData fieldValueMetaData) {
        this.fieldName = str;
        this.requirementType = b;
        this.valueMetaData = fieldValueMetaData;
    }

    public static void addStructMetaDataMap(Class<? extends TBase> cls, Map<? extends TFieldIdEnum, FieldMetaData> map) {
        structMap.put(cls, map);
    }

    public static Map<? extends TFieldIdEnum, FieldMetaData> getStructMetaDataMap(Class<? extends TBase> cls) {
        if (!structMap.containsKey(cls)) {
            try {
                cls.newInstance();
            } catch (IllegalAccessException e) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("IllegalAccessException for TBase class: ");
                outline107.append(cls.getName());
                outline107.append(", message: ");
                outline107.append(e.getMessage());
                throw new RuntimeException(outline107.toString());
            } catch (InstantiationException e2) {
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("InstantiationException for TBase class: ");
                outline1072.append(cls.getName());
                outline1072.append(", message: ");
                outline1072.append(e2.getMessage());
                throw new RuntimeException(outline1072.toString());
            }
        }
        return structMap.get(cls);
    }
}
