package com.typesafe.config.impl;

import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.typesafe.config.ConfigValueType;
import com.typesafe.config.impl.ConfigString;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class DefaultTransformer {

    /* renamed from: com.typesafe.config.impl.DefaultTransformer$2  reason: invalid class name */
    /* loaded from: classes3.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$typesafe$config$ConfigValueType = new int[ConfigValueType.values().length];

        static {
            try {
                $SwitchMap$com$typesafe$config$ConfigValueType[ConfigValueType.NUMBER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$typesafe$config$ConfigValueType[ConfigValueType.NULL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$typesafe$config$ConfigValueType[ConfigValueType.BOOLEAN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$typesafe$config$ConfigValueType[ConfigValueType.LIST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$typesafe$config$ConfigValueType[ConfigValueType.OBJECT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$typesafe$config$ConfigValueType[ConfigValueType.STRING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    DefaultTransformer() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public static AbstractConfigValue transform(AbstractConfigValue abstractConfigValue, ConfigValueType configValueType) {
        ConfigValueType valueType = abstractConfigValue.valueType();
        ConfigValueType configValueType2 = ConfigValueType.STRING;
        if (valueType == configValueType2) {
            String str = (String) abstractConfigValue.mo10253unwrapped();
            int ordinal = configValueType.ordinal();
            if (ordinal != 0 && ordinal != 1) {
                if (ordinal == 2) {
                    try {
                        try {
                            return new ConfigLong(abstractConfigValue.mo10176origin(), Long.valueOf(Long.parseLong(str)).longValue(), str);
                        } catch (NumberFormatException unused) {
                        }
                    } catch (NumberFormatException unused2) {
                        return new ConfigDouble(abstractConfigValue.mo10176origin(), Double.valueOf(Double.parseDouble(str)).doubleValue(), str);
                    }
                } else if (ordinal != 3) {
                    if (ordinal == 4 && str.equals("null")) {
                        return new ConfigNull(abstractConfigValue.mo10176origin());
                    }
                } else if (!str.equals("true") && !str.equals("yes") && !str.equals("on")) {
                    if (str.equals(PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE) || str.equals("no") || str.equals("off")) {
                        return new ConfigBoolean(abstractConfigValue.mo10176origin(), false);
                    }
                } else {
                    return new ConfigBoolean(abstractConfigValue.mo10176origin(), true);
                }
            }
        } else if (configValueType == configValueType2) {
            int ordinal2 = abstractConfigValue.valueType().ordinal();
            if (ordinal2 != 0 && ordinal2 != 1 && (ordinal2 == 2 || ordinal2 == 3)) {
                return new ConfigString.Quoted(abstractConfigValue.mo10176origin(), abstractConfigValue.transformToString());
            }
        } else if (configValueType == ConfigValueType.LIST && abstractConfigValue.valueType() == ConfigValueType.OBJECT) {
            AbstractConfigObject abstractConfigObject = (AbstractConfigObject) abstractConfigValue;
            HashMap hashMap = new HashMap();
            for (String str2 : abstractConfigObject.keySet()) {
                try {
                    int parseInt = Integer.parseInt(str2, 10);
                    if (parseInt >= 0) {
                        hashMap.put(Integer.valueOf(parseInt), abstractConfigObject.mo10248get((Object) str2));
                    }
                } catch (NumberFormatException unused3) {
                }
            }
            if (!hashMap.isEmpty()) {
                ArrayList arrayList = new ArrayList(hashMap.entrySet());
                Collections.sort(arrayList, new Comparator<Map.Entry<Integer, AbstractConfigValue>>() { // from class: com.typesafe.config.impl.DefaultTransformer.1
                    @Override // java.util.Comparator
                    public int compare(Map.Entry<Integer, AbstractConfigValue> entry, Map.Entry<Integer, AbstractConfigValue> entry2) {
                        return Integer.compare(entry.getKey().intValue(), entry2.getKey().intValue());
                    }
                });
                ArrayList arrayList2 = new ArrayList();
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(((Map.Entry) it2.next()).getValue());
                }
                return new SimpleConfigList(abstractConfigValue.mo10176origin(), arrayList2);
            }
        }
        return abstractConfigValue;
    }
}
