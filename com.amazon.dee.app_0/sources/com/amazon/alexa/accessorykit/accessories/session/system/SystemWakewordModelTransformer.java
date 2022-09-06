package com.amazon.alexa.accessorykit.accessories.session.system;

import com.amazon.alexa.accessory.davs.DavsI18nWakeword;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.io.NotSerializableException;
import java.text.ParseException;
import java.util.List;
/* loaded from: classes6.dex */
public class SystemWakewordModelTransformer implements MapModelTransformer<DavsI18nWakeword> {
    public static final String KEY_SCOPES = "scopes";
    public static final String KEY_VALUES = "values";
    private final ContainerProvider containerProvider;
    private final StringArrayModelTransformer stringArrayModelTransformer;

    public SystemWakewordModelTransformer(ContainerProvider containerProvider) {
        this.containerProvider = containerProvider;
        this.stringArrayModelTransformer = new StringArrayModelTransformer(containerProvider);
    }

    private WritableArray toArray(List<List<String>> list) throws NotSerializableException {
        WritableArray array = this.containerProvider.getArray();
        for (List<String> list2 : list) {
            array.pushArray(this.stringArrayModelTransformer.transformToArray(list2));
        }
        return array;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public DavsI18nWakeword mo626transform(ReadableMap readableMap) throws ParseException {
        throw new ParseException("Not supported", 0);
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(DavsI18nWakeword davsI18nWakeword) throws NotSerializableException {
        WritableMap map = this.containerProvider.getMap();
        map.putArray(KEY_SCOPES, this.stringArrayModelTransformer.transformToArray(davsI18nWakeword.getScopes()));
        map.putArray("values", toArray(davsI18nWakeword.getValues()));
        return map;
    }
}
