package com.amazonaws.services.s3.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public class TagSet {
    private Map<String, String> tags = new HashMap(1);

    public TagSet() {
    }

    public Map<String, String> getAllTags() {
        return this.tags;
    }

    public String getTag(String str) {
        return this.tags.get(str);
    }

    public void setTag(String str, String str2) {
        this.tags.put(str, str2);
    }

    public String toString() {
        StringBuffer outline103 = GeneratedOutlineSupport1.outline103(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Tags: ");
        outline107.append(getAllTags());
        outline103.append(outline107.toString());
        outline103.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline103.toString();
    }

    public TagSet(Map<String, String> map) {
        this.tags.putAll(map);
    }
}
