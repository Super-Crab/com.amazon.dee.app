package com.amazon.identity.auth.device.userdictionary;

import android.content.Context;
import android.text.TextUtils;
import com.amazon.identity.auth.device.hl;
import com.amazon.identity.auth.device.hm;
import com.amazon.identity.auth.device.ho;
import com.amazon.identity.auth.device.io;
import com.amazon.identity.auth.device.mq;
import com.amazon.identity.auth.device.mv;
import com.amazon.identity.auth.device.mz;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class UserDictionaryHelper {
    private static final String TAG = "UserDictionaryHelper";
    private static final String fL = "UserDictionaryHelper";
    private static UserDictionaryHelper qF;
    private hm qG;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class UserDictionaryInvalidUserLoginException extends Exception {
        public UserDictionaryInvalidUserLoginException(String str) {
            super(str);
        }
    }

    private UserDictionaryHelper(Context context) {
        hm hlVar;
        if (mz.bb(context)) {
            hlVar = ho.ag(context);
        } else {
            hlVar = new hl();
        }
        this.qG = hlVar;
        if (this.qG instanceof ho) {
            gl();
        }
    }

    public static synchronized UserDictionaryHelper af(Context context) {
        UserDictionaryHelper userDictionaryHelper;
        synchronized (UserDictionaryHelper.class) {
            if (qF == null) {
                qF = new UserDictionaryHelper(context);
            }
            userDictionaryHelper = qF;
        }
        return userDictionaryHelper;
    }

    private static String cV(String str) {
        return TextUtils.isEmpty(str) ? fL : String.format("%s_%s", fL, str);
    }

    public boolean cU(String str) {
        if (this.qG instanceof ho) {
            String cV = cV("addNewLogin");
            mv aD = mq.aD(fL, "addNewLogin");
            try {
                try {
                    ((ho) this.qG).cW(str);
                    mq.b(cV, "Success");
                    aD.stop();
                    return true;
                } catch (UserDictionaryInvalidUserLoginException e) {
                    io.e(TAG, "username is invalid", e);
                    mq.b(cV, "InvalidUserLoginException");
                    aD.stop();
                    return false;
                }
            } catch (Throwable th) {
                aD.stop();
                throw th;
            }
        }
        return false;
    }

    public List<String> gl() {
        if (this.qG instanceof ho) {
            String cV = cV("getUserDictionary");
            mv aD = mq.aD(fL, "getUserDictionary");
            try {
                List<String> go = ((ho) this.qG).go();
                mq.b(cV, "Success");
                if (go == null) {
                    go = new ArrayList<>();
                }
                return go;
            } catch (JSONException e) {
                io.e(TAG, "JSONException when tyring to get user dict cache", e);
                mq.b(cV, "JSONException");
                return null;
            } finally {
                aD.stop();
            }
        }
        return null;
    }
}
