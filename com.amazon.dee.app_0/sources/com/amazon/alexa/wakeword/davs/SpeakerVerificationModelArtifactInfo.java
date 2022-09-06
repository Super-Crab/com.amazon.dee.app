package com.amazon.alexa.wakeword.davs;

import com.amazon.alexa.utils.validation.Assertions;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class SpeakerVerificationModelArtifactInfo extends ArtifactInfo {
    private static final String ARTIFACT_KEY = "speaker-verification-model";
    private static final String ARTIFACT_TYPE = "lowpower-speaker-verification";
    private static final String FILTER_KEY_ENGINE_COMPATIBILITY_ID_LIST = "engineCompatibilityIdList";
    private static final String FILTER_KEY_FILTER_VERSION = "filterVersion";
    private static final String FILTER_KEY_LOCALE = "locale";
    private static final String FILTER_KEY_USE_CASE = "useCase";
    private static final String FILTER_KEY_WAKEWORD1 = "wakeword1";
    private final String locale;
    private static final List<String> ENGINE_COMPAT_ID_LIST_VALUE = Collections.singletonList("4");
    private static final Map<String, List<String>> DEFAULT_FILTERS = new HashMap();

    static {
        DEFAULT_FILTERS.put(FILTER_KEY_USE_CASE, Collections.singletonList("SV_1400K-far-field-standalone-arm"));
        DEFAULT_FILTERS.put(FILTER_KEY_ENGINE_COMPATIBILITY_ID_LIST, ENGINE_COMPAT_ID_LIST_VALUE);
        DEFAULT_FILTERS.put(FILTER_KEY_FILTER_VERSION, Collections.singletonList("2"));
        DEFAULT_FILTERS.put(FILTER_KEY_WAKEWORD1, Collections.singletonList("alexa"));
    }

    public SpeakerVerificationModelArtifactInfo(String str) {
        super(ARTIFACT_TYPE, ARTIFACT_KEY);
        Assertions.notEmpty(str, "locale is empty");
        this.locale = str;
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactInfo
    public Map<String, List<String>> getDavsFilters() {
        HashMap hashMap = new HashMap(DEFAULT_FILTERS);
        hashMap.put("locale", Collections.singletonList(this.locale));
        return Collections.unmodifiableMap(hashMap);
    }

    public String getEngineCompatibilityId() {
        return ENGINE_COMPAT_ID_LIST_VALUE.get(0);
    }

    public String getLocale() {
        return this.locale;
    }
}
