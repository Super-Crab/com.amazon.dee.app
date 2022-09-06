package com.drew.metadata.mov.media;

import com.amazon.deecomms.common.Constants;
import com.drew.metadata.mov.QuickTimeDirectory;
import java.util.HashMap;
/* loaded from: classes2.dex */
public abstract class QuickTimeMediaDirectory extends QuickTimeDirectory {
    public static final int TAG_CREATION_TIME = 20481;
    public static final int TAG_DURATION = 20483;
    public static final int TAG_MODIFICATION_TIME = 20482;

    /* JADX INFO: Access modifiers changed from: protected */
    public static void addQuickTimeMediaTags(HashMap<Integer, String> hashMap) {
        hashMap.put(Integer.valueOf((int) TAG_CREATION_TIME), "Creation Time");
        hashMap.put(Integer.valueOf((int) TAG_MODIFICATION_TIME), "Modification Time");
        hashMap.put(Integer.valueOf((int) TAG_DURATION), Constants.CALL_DURATION_KEY);
    }
}
