package com.drew.metadata.mp4.media;

import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.metadata.mp4.Mp4Directory;
import java.util.HashMap;
/* loaded from: classes2.dex */
public abstract class Mp4MediaDirectory extends Mp4Directory {
    public static final int TAG_CREATION_TIME = 101;
    public static final int TAG_DURATION = 103;
    public static final int TAG_LANGUAGE_CODE = 104;
    public static final int TAG_MODIFICATION_TIME = 102;

    /* JADX INFO: Access modifiers changed from: protected */
    public static void addMp4MediaTags(HashMap<Integer, String> hashMap) {
        GeneratedOutlineSupport1.outline141(101, hashMap, "Creation Time", 102, "Modification Time", 103, Constants.CALL_DURATION_KEY, 104, "ISO 639-2 Language Code");
    }
}
