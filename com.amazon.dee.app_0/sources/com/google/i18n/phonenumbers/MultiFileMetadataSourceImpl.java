package com.google.i18n.phonenumbers;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.i18n.phonenumbers.Phonemetadata;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class MultiFileMetadataSourceImpl implements MetadataSource {
    private static final String META_DATA_FILE_PREFIX = "/com/google/i18n/phonenumbers/data/PhoneNumberMetadataProto";
    private static final Logger logger = Logger.getLogger(MultiFileMetadataSourceImpl.class.getName());
    private final String filePrefix;
    private final ConcurrentHashMap<String, Phonemetadata.PhoneMetadata> geographicalRegions;
    private final MetadataLoader metadataLoader;
    private final ConcurrentHashMap<Integer, Phonemetadata.PhoneMetadata> nonGeographicalRegions;

    MultiFileMetadataSourceImpl(String str, MetadataLoader metadataLoader) {
        this.geographicalRegions = new ConcurrentHashMap<>();
        this.nonGeographicalRegions = new ConcurrentHashMap<>();
        this.filePrefix = str;
        this.metadataLoader = metadataLoader;
    }

    private boolean isNonGeographical(int i) {
        List<String> list = CountryCodeToRegionCodeMap.getCountryCodeToRegionCodeMap().get(Integer.valueOf(i));
        return list.size() == 1 && PhoneNumberUtil.REGION_CODE_FOR_NON_GEO_ENTITY.equals(list.get(0));
    }

    static <T> Phonemetadata.PhoneMetadata loadMetadataFromFile(T t, ConcurrentHashMap<T, Phonemetadata.PhoneMetadata> concurrentHashMap, String str, MetadataLoader metadataLoader) {
        String valueOf = String.valueOf(str);
        String valueOf2 = String.valueOf(t);
        String outline92 = GeneratedOutlineSupport1.outline92(new StringBuilder(valueOf2.length() + valueOf.length() + 1), valueOf, "_", valueOf2);
        InputStream loadMetadata = metadataLoader.loadMetadata(outline92);
        if (loadMetadata == null) {
            String valueOf3 = String.valueOf(outline92);
            throw new IllegalStateException(valueOf3.length() != 0 ? "missing metadata: ".concat(valueOf3) : new String("missing metadata: "));
        }
        List<Phonemetadata.PhoneMetadata> metadataList = MetadataManager.loadMetadataAndCloseInput(loadMetadata).getMetadataList();
        if (metadataList.isEmpty()) {
            String valueOf4 = String.valueOf(outline92);
            throw new IllegalStateException(valueOf4.length() != 0 ? "empty metadata: ".concat(valueOf4) : new String("empty metadata: "));
        }
        if (metadataList.size() > 1) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String valueOf5 = String.valueOf(outline92);
            logger2.log(level, valueOf5.length() != 0 ? "invalid metadata (too many entries): ".concat(valueOf5) : new String("invalid metadata (too many entries): "));
        }
        Phonemetadata.PhoneMetadata phoneMetadata = metadataList.get(0);
        Phonemetadata.PhoneMetadata putIfAbsent = concurrentHashMap.putIfAbsent(t, phoneMetadata);
        return putIfAbsent != null ? putIfAbsent : phoneMetadata;
    }

    @Override // com.google.i18n.phonenumbers.MetadataSource
    public Phonemetadata.PhoneMetadata getMetadataForNonGeographicalRegion(int i) {
        Phonemetadata.PhoneMetadata phoneMetadata = this.nonGeographicalRegions.get(Integer.valueOf(i));
        if (phoneMetadata != null) {
            return phoneMetadata;
        }
        if (!isNonGeographical(i)) {
            return null;
        }
        return loadMetadataFromFile(Integer.valueOf(i), this.nonGeographicalRegions, this.filePrefix, this.metadataLoader);
    }

    @Override // com.google.i18n.phonenumbers.MetadataSource
    public Phonemetadata.PhoneMetadata getMetadataForRegion(String str) {
        Phonemetadata.PhoneMetadata phoneMetadata = this.geographicalRegions.get(str);
        return phoneMetadata != null ? phoneMetadata : loadMetadataFromFile(str, this.geographicalRegions, this.filePrefix, this.metadataLoader);
    }

    public MultiFileMetadataSourceImpl(MetadataLoader metadataLoader) {
        this(META_DATA_FILE_PREFIX, metadataLoader);
    }
}
