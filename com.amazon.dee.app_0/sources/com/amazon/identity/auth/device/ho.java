package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.userdictionary.UserDictionaryHelper;
import java.util.List;
import org.json.JSONException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ho implements hm {
    private static final String TAG = "ho";
    private static ho qH;
    private final hn qI;

    private ho(Context context) {
        this.qI = new hn(ed.M(context));
    }

    public static synchronized ho ag(Context context) {
        ho hoVar;
        synchronized (ho.class) {
            if (qH == null) {
                qH = new ho(context);
            }
            hoVar = qH;
        }
        return hoVar;
    }

    public void cW(String str) throws UserDictionaryHelper.UserDictionaryInvalidUserLoginException {
        this.qI.cR(str);
    }

    public List<String> go() throws JSONException {
        return this.qI.gl();
    }
}
