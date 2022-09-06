package com.amazon.alexa.accessorykit.accessories.session.system;

import com.amazon.alexa.accessory.davs.DavsI18nConfig;
import com.amazon.alexa.accessory.davs.DavsI18nWakeword;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.ArrayModelTransformer;
import com.amazon.alexa.accessorykit.accessories.ArrayModelTransformerHelper;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.io.NotSerializableException;
import java.text.ParseException;
import java.util.List;
/* loaded from: classes6.dex */
public class SystemModelI18nConfigTransformer implements MapModelTransformer<DavsI18nConfig> {
    public static final String KEY_LOCALES = "locales";
    public static final String KEY_LOCALE_COMBINATIONS = "localeCombinations";
    public static final String KEY_WAKEWORDS = "wakeWords";
    private final ContainerProvider containerProvider;
    private final StringArrayModelTransformer stringArrayModelTransformer;
    private final ArrayModelTransformer<List<DavsI18nWakeword>> systemWakewordArrayModelTransformer;

    public SystemModelI18nConfigTransformer(ContainerProvider containerProvider) {
        this.containerProvider = containerProvider;
        this.stringArrayModelTransformer = new StringArrayModelTransformer(containerProvider);
        this.systemWakewordArrayModelTransformer = new ArrayModelTransformerHelper(containerProvider, new SystemWakewordModelTransformer(containerProvider));
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
    public DavsI18nConfig mo626transform(ReadableMap readableMap) throws ParseException {
        throw new ParseException("Not supported", 0);
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(DavsI18nConfig davsI18nConfig) throws NotSerializableException {
        WritableMap map = this.containerProvider.getMap();
        map.putArray(KEY_LOCALES, this.stringArrayModelTransformer.transformToArray(davsI18nConfig.getLocales()));
        map.putArray(KEY_LOCALE_COMBINATIONS, toArray(davsI18nConfig.getLocaleCombinations()));
        map.putArray(KEY_WAKEWORDS, this.systemWakewordArrayModelTransformer.transformToArray(davsI18nConfig.getWakeWords()));
        return map;
    }
}
