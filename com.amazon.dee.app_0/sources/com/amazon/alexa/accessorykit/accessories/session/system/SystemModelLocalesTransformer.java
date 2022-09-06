package com.amazon.alexa.accessorykit.accessories.session.system;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.System;
import com.amazon.alexa.accessorykit.ContainerProvider;
import com.amazon.alexa.accessorykit.accessories.MapModelTransformer;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.io.NotSerializableException;
import java.text.ParseException;
/* loaded from: classes6.dex */
public class SystemModelLocalesTransformer implements MapModelTransformer<System.Locales> {
    private static final String CURRENT_LOCALE_KEY = "current_locale";
    private static final String SUPPORTED_LOCALES_KEY = "supported_locales";
    private final ContainerProvider containerProvider;
    private final MapModelTransformer<System.Locale> localeTransformer;

    public SystemModelLocalesTransformer(ContainerProvider containerProvider, MapModelTransformer<System.Locale> mapModelTransformer) {
        Preconditions.notNull(containerProvider, "containerProvider");
        Preconditions.notNull(mapModelTransformer, "localeTransformer");
        this.containerProvider = containerProvider;
        this.localeTransformer = mapModelTransformer;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    /* renamed from: transform */
    public System.Locales mo626transform(ReadableMap readableMap) throws ParseException {
        throw new ParseException("Not supported", 0);
    }

    @Override // com.amazon.alexa.accessorykit.accessories.MapModelTransformer
    public WritableMap transformToMap(System.Locales locales) throws NotSerializableException {
        WritableMap map = this.containerProvider.getMap();
        map.putMap(CURRENT_LOCALE_KEY, this.localeTransformer.transformToMap(locales.getCurrentLocale()));
        WritableArray array = this.containerProvider.getArray();
        for (System.Locale locale : locales.getSupportedLocalesList()) {
            array.pushMap(this.localeTransformer.transformToMap(locale));
        }
        map.putArray(SUPPORTED_LOCALES_KEY, array);
        return map;
    }
}
