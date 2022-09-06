package com.amazon.scxml.internal;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.xml.sax.Attributes;
/* compiled from: SimpleXMLReader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0002¨\u0006\u0004"}, d2 = {"toMap", "", "", "Lorg/xml/sax/Attributes;", "AlexaMobileAndroidVoice-TTA_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class SimpleXMLReaderKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Map<String, String> toMap(@NotNull Attributes attributes) {
        Map<String, String> map;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            String key = attributes.getLocalName(i);
            String value = attributes.getValue(i);
            Intrinsics.checkExpressionValueIsNotNull(key, "key");
            Intrinsics.checkExpressionValueIsNotNull(value, "value");
            linkedHashMap.put(key, value);
        }
        map = MapsKt__MapsKt.toMap(linkedHashMap);
        return map;
    }
}
