package amazon.speech.requestid;

import android.text.TextUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.UUID;
/* loaded from: classes.dex */
public class RequestId {
    private static final int NUM_OF_PARTS_IN_SINGLE_STRING = 1;
    private static final int NUM_VALID_REQUEST_IN_PARTS = 4;
    protected static final int ORIGIN_DEPTH = 0;
    protected static final String REGEX_PREFIX = "\\";
    protected static final String REQUEST_ID_DELIMITER = ".";
    protected static final String REQUEST_ID_PARTS_DELIMITER = "_";
    private int mDepth;
    private String mInteractionId;
    private String mOriginId;
    private String mParentInteractionId;

    public RequestId(String str) {
        this.mInteractionId = getInteractionId(str);
        this.mOriginId = this.mInteractionId;
        this.mParentInteractionId = "";
        this.mDepth = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String getInteractionId(String str) {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, "_");
        outline113.append(UUID.randomUUID().toString());
        outline113.append("_");
        outline113.append(System.currentTimeMillis());
        return outline113.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isASimpleStringRequestId(String[] strArr) {
        return 1 == strArr.length;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isFromSameService(String[] strArr, String str) {
        String str2 = strArr[3];
        if (!TextUtils.isEmpty(str2)) {
            String[] split = str2.split("\\_");
            if (3 == split.length && !TextUtils.isEmpty(split[0])) {
                return str.equals(split[0]);
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean isValid(String[] strArr) {
        return 4 == strArr.length;
    }

    public String toString() {
        return this.mOriginId + "." + this.mDepth + "." + this.mParentInteractionId + "." + this.mInteractionId;
    }

    public RequestId(String str, String str2, String str3, int i) {
        this.mOriginId = str;
        this.mParentInteractionId = str2;
        this.mInteractionId = str3;
        this.mDepth = i;
    }
}
