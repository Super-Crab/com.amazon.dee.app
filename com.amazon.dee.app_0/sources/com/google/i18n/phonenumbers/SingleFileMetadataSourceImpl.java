package com.google.i18n.phonenumbers;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.i18n.phonenumbers.Phonemetadata;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
/* loaded from: classes3.dex */
final class SingleFileMetadataSourceImpl implements MetadataSource {
    private static final String META_DATA_FILE_NAME = "/com/google/i18n/phonenumbers/data/SingleFilePhoneNumberMetadataProto";
    private static final Logger logger = Logger.getLogger(SingleFileMetadataSourceImpl.class.getName());
    private final Map<Integer, Phonemetadata.PhoneMetadata> countryCodeToNonGeographicalMetadataMap;
    private final String fileName;
    private final MetadataLoader metadataLoader;
    private final Map<String, Phonemetadata.PhoneMetadata> regionToMetadataMap;

    public SingleFileMetadataSourceImpl(String str, MetadataLoader metadataLoader) {
        this.regionToMetadataMap = GeneratedOutlineSupport1.outline136();
        this.countryCodeToNonGeographicalMetadataMap = GeneratedOutlineSupport1.outline136();
        this.fileName = str;
        this.metadataLoader = metadataLoader;
    }

    @Override // com.google.i18n.phonenumbers.MetadataSource
    public Phonemetadata.PhoneMetadata getMetadataForNonGeographicalRegion(int i) {
        synchronized (this.countryCodeToNonGeographicalMetadataMap) {
            if (!this.countryCodeToNonGeographicalMetadataMap.containsKey(Integer.valueOf(i))) {
                loadMetadataFromFile();
            }
        }
        return this.countryCodeToNonGeographicalMetadataMap.get(Integer.valueOf(i));
    }

    @Override // com.google.i18n.phonenumbers.MetadataSource
    public Phonemetadata.PhoneMetadata getMetadataForRegion(String str) {
        synchronized (this.regionToMetadataMap) {
            if (!this.regionToMetadataMap.containsKey(str)) {
                loadMetadataFromFile();
            }
        }
        return this.regionToMetadataMap.get(str);
    }

    void loadMetadataFromFile() {
        InputStream loadMetadata = this.metadataLoader.loadMetadata(this.fileName);
        if (loadMetadata == null) {
            String valueOf = String.valueOf(this.fileName);
            throw new IllegalStateException(valueOf.length() != 0 ? "missing metadata: ".concat(valueOf) : new String("missing metadata: "));
        }
        List<Phonemetadata.PhoneMetadata> metadataList = MetadataManager.loadMetadataAndCloseInput(loadMetadata).getMetadataList();
        if (metadataList.isEmpty()) {
            String valueOf2 = String.valueOf(this.fileName);
            throw new IllegalStateException(valueOf2.length() != 0 ? "empty metadata: ".concat(valueOf2) : new String("empty metadata: "));
        }
        for (Phonemetadata.PhoneMetadata phoneMetadata : metadataList) {
            String id = phoneMetadata.getId();
            int countryCode = phoneMetadata.getCountryCode();
            if (PhoneNumberUtil.REGION_CODE_FOR_NON_GEO_ENTITY.equals(id)) {
                this.countryCodeToNonGeographicalMetadataMap.put(Integer.valueOf(countryCode), phoneMetadata);
            } else {
                this.regionToMetadataMap.put(id, phoneMetadata);
            }
        }
    }

    public SingleFileMetadataSourceImpl(MetadataLoader metadataLoader) {
        this(META_DATA_FILE_NAME, metadataLoader);
    }
}
