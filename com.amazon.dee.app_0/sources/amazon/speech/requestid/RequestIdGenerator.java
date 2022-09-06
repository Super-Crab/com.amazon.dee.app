package amazon.speech.requestid;

import android.text.TextUtils;
import android.util.Log;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
/* loaded from: classes.dex */
public class RequestIdGenerator {
    private static final String TAG = "RequestIdGenerator";

    private static int extractDepthAsNumber(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            Log.e(TAG, "Depth in incoming requestId is not a number,setting the incoming depth to zero by default");
            return 0;
        }
    }

    public static String generateRequestId(String str, String str2) {
        RequestId generateRequestIdFromValidIncomingRequestId;
        if (str != null && !str.isEmpty()) {
            String[] split = str.split(ArcusConfig.PATH_SEPARATOR);
            if (!RequestId.isValid(split)) {
                generateRequestIdFromValidIncomingRequestId = generateRequestIdFromInvalidIncomingRequestId(str2, split);
            } else if (RequestId.isFromSameService(split, str2)) {
                return str;
            } else {
                generateRequestIdFromValidIncomingRequestId = generateRequestIdFromValidIncomingRequestId(str2, split);
            }
            return generateRequestIdFromValidIncomingRequestId.toString();
        }
        return new RequestId(str2).toString();
    }

    private static RequestId generateRequestIdFromInvalidIncomingRequestId(String str, String[] strArr) {
        if (RequestId.isASimpleStringRequestId(strArr)) {
            return getANewRequestIdWithSingleStringIncomingId(str, strArr[0], 0);
        }
        return getARandomRequestId(str);
    }

    private static RequestId generateRequestIdFromValidIncomingRequestId(String str, String[] strArr) {
        int extractDepthAsNumber = extractDepthAsNumber(strArr[1]);
        if (TextUtils.isEmpty(strArr[0])) {
            if (TextUtils.isEmpty(strArr[3])) {
                return new RequestId(str);
            }
            return getRequestIdWhenIncomingOriginIdMissingAndInteractionIdExisting(str, strArr[3], extractDepthAsNumber);
        } else if (TextUtils.isEmpty(strArr[3])) {
            return getRequestIdWithOnlyExistingOriginId(str, strArr[3], extractDepthAsNumber);
        } else {
            return getANewRequestIdWithExistingOriginIdAndInteractionId(str, strArr[0], strArr[3], extractDepthAsNumber);
        }
    }

    private static RequestId getANewRequestIdWithExistingOriginIdAndInteractionId(String str, String str2, String str3, int i) {
        return new RequestId(str2, str3, RequestId.getInteractionId(str), i + 1);
    }

    private static RequestId getANewRequestIdWithSingleStringIncomingId(String str, String str2, int i) {
        return new RequestId(str2, str2, RequestId.getInteractionId(str), i + 1);
    }

    private static RequestId getARandomRequestId(String str) {
        return new RequestId(str);
    }

    private static RequestId getRequestIdWhenIncomingOriginIdMissingAndInteractionIdExisting(String str, String str2, int i) {
        return new RequestId(str2, str2, RequestId.getInteractionId(str), i + 1);
    }

    private static RequestId getRequestIdWithOnlyExistingOriginId(String str, String str2, int i) {
        return new RequestId(str2, str2, RequestId.getInteractionId(str), i + 1);
    }
}
