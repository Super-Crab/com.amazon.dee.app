package com.amazon.alexa;

import com.amazon.alexa.utils.validation.Assertions;
import com.amazon.alexa.wakeword.davs.ArtifactInfo;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* compiled from: OfflinePromptsArtifactInfo.java */
/* loaded from: classes.dex */
public class RJu extends ArtifactInfo {
    public static final Map<String, List<String>> zZm = new HashMap();
    public final String BIo;

    static {
        zZm.put("deviceType", Collections.singletonList("A2TF17PFR55MTB"));
        zZm.put("filterVersion", Collections.singletonList("1"));
    }

    public RJu(String str) {
        super("alexa-voice-sdk", "offline-prompts");
        Assertions.notEmpty(str, "locale is empty");
        this.BIo = str;
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactInfo
    public Map<String, List<String>> getDavsFilters() {
        HashMap hashMap = new HashMap(zZm);
        hashMap.put("locale", Collections.singletonList(this.BIo));
        return Collections.unmodifiableMap(hashMap);
    }
}
