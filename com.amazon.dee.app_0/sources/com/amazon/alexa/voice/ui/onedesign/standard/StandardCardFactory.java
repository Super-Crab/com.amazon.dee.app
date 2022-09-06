package com.amazon.alexa.voice.ui.onedesign.standard;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.ui.onedesign.standard.StandardCard;
import com.amazon.alexa.voice.ui.onedesign.util.JSONUtils;
import com.amazon.regulator.internal.Preconditions;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public final class StandardCardFactory {

    @FunctionalInterface
    /* loaded from: classes11.dex */
    public interface Formatter {
        CharSequence fromHtml(String str);
    }

    private StandardCardFactory() {
    }

    @Nullable
    public static StandardCardModel fromJson(@NonNull JSONObject jSONObject) throws JSONException {
        return fromJson(jSONObject, $$Lambda$wn_75xe3zhnfzMLLerB54DD6vG0.INSTANCE);
    }

    private static JSONObject getBestAction(@Nullable JSONArray jSONArray) throws JSONException {
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String optionalString = JSONUtils.optionalString(jSONObject, "url");
                if (!TextUtils.isEmpty(optionalString) && !optionalString.contains("bing.com")) {
                    return jSONObject;
                }
            }
        }
        return null;
    }

    private static CharSequence getDescription(@Nullable JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        int length = jSONArray.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            String optionalString = JSONUtils.optionalString(jSONArray, i);
            if (!TextUtils.isEmpty(optionalString)) {
                sb.append(optionalString);
                if (i != length - 1) {
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }

    @Nullable
    @VisibleForTesting
    public static StandardCardModel fromJson(@NonNull JSONObject jSONObject, @NonNull Formatter formatter) throws JSONException {
        CharSequence charSequence;
        String str;
        String str2;
        String str3;
        Preconditions.nonNull(jSONObject, "jsonObject can not be null");
        String optionalString = JSONUtils.optionalString(jSONObject, "title");
        String optionalString2 = JSONUtils.optionalString(jSONObject, "subtitle");
        CharSequence description = getDescription(jSONObject.optJSONArray("descriptiveText"));
        String optionalString3 = JSONUtils.optionalString(jSONObject, "cardType");
        JSONObject optJSONObject = jSONObject.optJSONObject("imageReference");
        ArrayList arrayList = null;
        if (optJSONObject != null) {
            str = JSONUtils.optionalString(optJSONObject, "url");
            String optionalString4 = JSONUtils.optionalString(jSONObject, "imageCaption");
            charSequence = optionalString4 != null ? formatter.fromHtml(optionalString4) : null;
        } else {
            charSequence = null;
            str = null;
        }
        JSONObject bestAction = getBestAction(jSONObject.optJSONArray("primaryActions"));
        if (bestAction != null) {
            str2 = JSONUtils.optionalString(bestAction, "url");
            str3 = JSONUtils.optionalString(bestAction, "mainText");
        } else {
            str2 = null;
            str3 = null;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("domains");
        if (optJSONArray != null) {
            arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                arrayList.add(optJSONArray.getString(i));
            }
        }
        return new StandardCard.Builder().title(optionalString).content(description).contentImageUrl(str).contentImageAttribution(charSequence).linkUrl(str2).linkText(str3).domains(arrayList).subTitle(optionalString2).cardType(optionalString3).build();
    }
}
