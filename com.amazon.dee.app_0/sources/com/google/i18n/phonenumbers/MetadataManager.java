package com.google.i18n.phonenumbers;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.i18n.phonenumbers.Phonemetadata;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class MetadataManager {
    private static final String ALTERNATE_FORMATS_FILE_PREFIX = "/com/google/i18n/phonenumbers/data/PhoneNumberAlternateFormatsProto";
    private static final String SHORT_NUMBER_METADATA_FILE_PREFIX = "/com/google/i18n/phonenumbers/data/ShortNumberMetadataProto";
    private static final Logger logger = Logger.getLogger(MetadataManager.class.getName());
    private static final Map<Integer, Phonemetadata.PhoneMetadata> callingCodeToAlternateFormatsMap = GeneratedOutlineSupport1.outline136();
    private static final Map<String, Phonemetadata.PhoneMetadata> regionCodeToShortNumberMetadataMap = GeneratedOutlineSupport1.outline136();
    private static final Set<Integer> countryCodeSet = AlternateFormatsCountryCodeSet.getCountryCodeSet();
    private static final Set<String> regionCodeSet = ShortNumbersRegionCodeSet.getRegionCodeSet();

    private MetadataManager() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Phonemetadata.PhoneMetadata getAlternateFormatsForCountry(int i) {
        if (!countryCodeSet.contains(Integer.valueOf(i))) {
            return null;
        }
        synchronized (callingCodeToAlternateFormatsMap) {
            if (!callingCodeToAlternateFormatsMap.containsKey(Integer.valueOf(i))) {
                loadAlternateFormatsMetadataFromFile(i);
            }
        }
        return callingCodeToAlternateFormatsMap.get(Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Phonemetadata.PhoneMetadata getShortNumberMetadataForRegion(String str) {
        if (!regionCodeSet.contains(str)) {
            return null;
        }
        synchronized (regionCodeToShortNumberMetadataMap) {
            if (!regionCodeToShortNumberMetadataMap.containsKey(str)) {
                loadShortNumberMetadataFromFile(str);
            }
        }
        return regionCodeToShortNumberMetadataMap.get(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Set<String> getShortNumberMetadataSupportedRegions() {
        return regionCodeSet;
    }

    private static void loadAlternateFormatsMetadataFromFile(int i) {
        StringBuilder sb = new StringBuilder("/com/google/i18n/phonenumbers/data/PhoneNumberAlternateFormatsProto_".length() + 11);
        sb.append("/com/google/i18n/phonenumbers/data/PhoneNumberAlternateFormatsProto_");
        sb.append(i);
        String sb2 = sb.toString();
        InputStream resourceAsStream = MetadataManager.class.getResourceAsStream(sb2);
        if (resourceAsStream == null) {
            String valueOf = String.valueOf(sb2);
            throw new IllegalStateException(valueOf.length() != 0 ? "missing metadata: ".concat(valueOf) : new String("missing metadata: "));
        }
        for (Phonemetadata.PhoneMetadata phoneMetadata : loadMetadataAndCloseInput(resourceAsStream).getMetadataList()) {
            callingCodeToAlternateFormatsMap.put(Integer.valueOf(phoneMetadata.getCountryCode()), phoneMetadata);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Phonemetadata.PhoneMetadataCollection loadMetadataAndCloseInput(InputStream inputStream) {
        ObjectInputStream objectInputStream;
        try {
            try {
                objectInputStream = new ObjectInputStream(inputStream);
                try {
                    Phonemetadata.PhoneMetadataCollection phoneMetadataCollection = new Phonemetadata.PhoneMetadataCollection();
                    try {
                        phoneMetadataCollection.readExternal(objectInputStream);
                        try {
                            objectInputStream.close();
                        } catch (IOException e) {
                            logger.log(Level.WARNING, "error closing input stream (ignored)", (Throwable) e);
                        }
                        return phoneMetadataCollection;
                    } catch (IOException e2) {
                        throw new RuntimeException("cannot load/parse metadata", e2);
                    }
                } catch (Throwable th) {
                    th = th;
                    try {
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        } else {
                            inputStream.close();
                        }
                    } catch (IOException e3) {
                        logger.log(Level.WARNING, "error closing input stream (ignored)", (Throwable) e3);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                objectInputStream = null;
            }
        } catch (IOException e4) {
            throw new RuntimeException("cannot load/parse metadata", e4);
        }
    }

    private static void loadShortNumberMetadataFromFile(String str) {
        String valueOf = String.valueOf(str);
        String concat = valueOf.length() != 0 ? "/com/google/i18n/phonenumbers/data/ShortNumberMetadataProto_".concat(valueOf) : new String("/com/google/i18n/phonenumbers/data/ShortNumberMetadataProto_");
        InputStream resourceAsStream = MetadataManager.class.getResourceAsStream(concat);
        if (resourceAsStream == null) {
            String valueOf2 = String.valueOf(concat);
            throw new IllegalStateException(valueOf2.length() != 0 ? "missing metadata: ".concat(valueOf2) : new String("missing metadata: "));
        }
        for (Phonemetadata.PhoneMetadata phoneMetadata : loadMetadataAndCloseInput(resourceAsStream).getMetadataList()) {
            regionCodeToShortNumberMetadataMap.put(str, phoneMetadata);
        }
    }
}
