package com.facebook.react.modules.storage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.ReadableArray;
import java.util.Arrays;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class AsyncLocalStorageUtil {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String buildKeySelection(int i) {
        String[] strArr = new String[i];
        Arrays.fill(strArr, WebConstants.UriConstants.QUESTIONMARK_KEY);
        return GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("key IN ("), TextUtils.join(", ", strArr), ")");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String[] buildKeySelectionArgs(ReadableArray readableArray, int i, int i2) {
        String[] strArr = new String[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            strArr[i3] = readableArray.getString(i + i3);
        }
        return strArr;
    }

    private static void deepMergeInto(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        Iterator keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            JSONObject optJSONObject = jSONObject2.optJSONObject(str);
            JSONObject optJSONObject2 = jSONObject.optJSONObject(str);
            if (optJSONObject != null && optJSONObject2 != null) {
                deepMergeInto(optJSONObject2, optJSONObject);
                jSONObject.put(str, optJSONObject2);
            } else {
                jSONObject.put(str, jSONObject2.get(str));
            }
        }
    }

    @Nullable
    public static String getItemImpl(SQLiteDatabase sQLiteDatabase, String str) {
        Cursor query = sQLiteDatabase.query("catalystLocalStorage", new String[]{"value"}, "key=?", new String[]{str}, null, null, null);
        try {
            if (!query.moveToFirst()) {
                return null;
            }
            return query.getString(0);
        } finally {
            query.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean mergeImpl(SQLiteDatabase sQLiteDatabase, String str, String str2) throws JSONException {
        String itemImpl = getItemImpl(sQLiteDatabase, str);
        if (itemImpl != null) {
            JSONObject jSONObject = new JSONObject(itemImpl);
            deepMergeInto(jSONObject, new JSONObject(str2));
            str2 = jSONObject.toString();
        }
        return setItemImpl(sQLiteDatabase, str, str2);
    }

    static boolean setItemImpl(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("key", str);
        contentValues.put("value", str2);
        return -1 != sQLiteDatabase.insertWithOnConflict("catalystLocalStorage", null, contentValues, 5);
    }
}
