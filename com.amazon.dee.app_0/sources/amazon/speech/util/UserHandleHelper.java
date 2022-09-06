package amazon.speech.util;

import amazon.speech.util.DebugUtil;
import android.content.Context;
import android.os.UserHandle;
/* loaded from: classes.dex */
public class UserHandleHelper {
    private static final String FIELD_USER_ALL = "USER_ALL";
    private static final String FIELD_USER_CURRENT = "USER_CURRENT";
    public static final String TAG = DebugUtil.getTag(DebugUtil.Module.SIM, UserHandleHelper.class);
    private Context mContext;

    public UserHandleHelper(Context context) {
        this.mContext = context;
    }

    public int getAllUserHandleConstant() {
        try {
            return UserHandle.class.getField(FIELD_USER_ALL).getInt(null);
        } catch (ReflectiveOperationException unused) {
            Log.e(TAG, "Could not get field USER_ALL, returning 0");
            return 0;
        }
    }

    public int getCurrentUserHandleConstant() {
        try {
            return UserHandle.class.getField(FIELD_USER_CURRENT).getInt(null);
        } catch (ReflectiveOperationException unused) {
            Log.e(TAG, "Could not get field USER_CURRENT, returning 0");
            return 0;
        }
    }
}
