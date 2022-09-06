package com.amazon.identity.auth.device;

import android.text.TextUtils;
import android.util.Base64;
import com.amazon.identity.auth.device.userdictionary.UserDictionaryHelper;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import javax.crypto.BadPaddingException;
import org.json.JSONArray;
import org.json.JSONException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class hn {
    private static final String TAG = "com.amazon.identity.auth.device.hn";
    private static final Object qA = new Object();
    private fw qB;
    private final gg w;

    public hn(final ed edVar) {
        this.w = edVar.dV();
        this.qB = new fw() { // from class: com.amazon.identity.auth.device.hn.1
            @Override // com.amazon.identity.auth.device.fw
            public byte[] cq() {
                String cP = di.A(edVar).cP();
                if (cP == null) {
                    io.e(hn.TAG, "Could not generate a MAP only encryption key. Aborting.");
                    return null;
                }
                return Base64.decode(cP, 0);
            }
        };
    }

    private a cS(String str) throws JSONException {
        a aVar;
        synchronized (qA) {
            aVar = new a(cT(str));
        }
        return aVar;
    }

    private JSONArray cT(String str) throws JSONException {
        String str2;
        try {
            str2 = this.qB.bY(str);
        } catch (BadPaddingException unused) {
            io.e(TAG, "Cannot get encrypted data due to BadPaddingException");
            str2 = null;
        }
        if (TextUtils.isEmpty(str2)) {
            return new JSONArray();
        }
        String str3 = TAG;
        "Parsed user dictionary content: ".concat(String.valueOf(str2));
        io.dm(str3);
        return new JSONArray(str2);
    }

    public void cR(String str) throws UserDictionaryHelper.UserDictionaryInvalidUserLoginException {
        if (!TextUtils.isEmpty(str)) {
            if (str.length() <= 64) {
                synchronized (qA) {
                    a gn = gn();
                    gn.addElement(str);
                    this.w.g("user_dictionary", "user_dictionary_content", this.qB.bX(new JSONArray((Collection) gn.getList()).toString()));
                }
                return;
            }
            io.e(TAG, "username exceeds the size limit 64");
            throw new UserDictionaryHelper.UserDictionaryInvalidUserLoginException("Username exceeds size limit 64");
        }
        io.e(TAG, "Empty username");
        throw new UserDictionaryHelper.UserDictionaryInvalidUserLoginException("Try to write an empty username");
    }

    public List<String> gl() throws JSONException {
        return gn().getList();
    }

    public void gm() {
        io.dm(TAG);
        synchronized (qA) {
            this.w.g("user_dictionary", "user_dictionary_content", null);
        }
    }

    protected a gn() {
        try {
            return cS(this.w.C("user_dictionary", "user_dictionary_content"));
        } catch (Exception unused) {
            gm();
            return new a();
        }
    }

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static class a {
        private int qE = 5;
        private LinkedList<String> qD = new LinkedList<>();

        public a(JSONArray jSONArray) throws JSONException {
            int i = 5;
            if (jSONArray == null) {
                i = 0;
            } else if (5 >= jSONArray.length()) {
                i = jSONArray.length();
            }
            for (int i2 = 0; i2 < i; i2++) {
                this.qD.add(jSONArray.getString(i2));
            }
        }

        public void addElement(String str) {
            this.qD.remove(str);
            if (this.qD.size() >= this.qE) {
                this.qD.removeLast();
            }
            this.qD.addFirst(str);
        }

        public List<String> getList() {
            return this.qD;
        }

        public a() {
        }
    }
}
