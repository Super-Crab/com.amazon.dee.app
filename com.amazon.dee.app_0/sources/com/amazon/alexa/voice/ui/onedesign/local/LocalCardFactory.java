package com.amazon.alexa.voice.ui.onedesign.local;

import android.text.TextUtils;
import com.amazon.alexa.voice.ui.onedesign.local.LocalCard;
import com.amazon.alexa.voice.ui.onedesign.local.LocalCardModel;
import com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchSortKey;
import com.amazon.alexa.voice.ui.onedesign.util.CharSequenceUtils;
import com.amazon.alexa.voice.ui.onedesign.util.DateUtils;
import com.amazon.alexa.voice.ui.onedesign.util.JSONUtils;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.facebook.react.uimanager.ViewProps;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public final class LocalCardFactory {
    private LocalCardFactory() {
    }

    public static LocalCard fromJson(JSONObject jSONObject) throws JSONException {
        List<LocalCardModel.BusinessModel> makeBusinessList = makeBusinessList(jSONObject);
        if (!makeBusinessList.isEmpty()) {
            return new LocalCard.Builder().sortKey(LocalSearchSortKey.from(jSONObject.getString("sortedBy")).toString()).title(CharSequenceUtils.capitalize(jSONObject.getString("categoryName"))).businessList(makeBusinessList).build();
        }
        throw new JSONException("Refusing to construct a local list card for empty business list.");
    }

    private static LocalCardModel.BusinessModel makeBusiness(JSONObject jSONObject) throws JSONException {
        return new LocalCard.Business.Builder().name(jSONObject.getString("name")).dataSource(JSONUtils.optionalString(jSONObject, "dataSource", "")).reviewCount(makeReviewCount(jSONObject)).distanceValue(Float.parseFloat(jSONObject.getString("distance"))).distanceUnit(jSONObject.getString("distanceUnit")).goToActionText(makeGoToActionText(jSONObject)).goToActionUrl(makeGoToActionUrl(jSONObject)).spendiness(makeSpendiness(jSONObject)).imageUrl(jSONObject.getJSONObject("puffinImageReference").getString("url")).mergedProviderInfo(JSONUtils.optionalString(jSONObject, "mergedProviderInfo", "")).providerLogoUrl(makeProviderLogo(jSONObject)).rating(makeRating(jSONObject)).starRatingUrl(makeStarRating(jSONObject)).categories(makeCategories(jSONObject)).address(jSONObject.getJSONObject("address").getString("multipleLineDisplayAddress")).geoLocation(JSONUtils.optionalString(jSONObject, ViewProps.POSITION)).phoneNumber(JSONUtils.optionalString(jSONObject, "phoneNumber")).openingHours(makeOpeningHours(jSONObject.getJSONArray("businessHoursList"))).specialBusinessHoursDisplayMessage(JSONUtils.optionalString(jSONObject, "specialBusinessHoursDisplayMessage")).specialBusinessHoursDisplayType(JSONUtils.optionalString(jSONObject, "specialBusinessHoursDisplayType")).linkUrl(JSONUtils.optionalString(jSONObject, "dataSourceListingURL")).build();
    }

    private static List<LocalCardModel.BusinessModel> makeBusinessList(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArray = jSONObject.getJSONArray("puffinList");
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(makeBusiness(jSONArray.getJSONObject(i)));
        }
        return arrayList;
    }

    private static List<CharSequence> makeCategories(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("categoryList");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        return arrayList;
    }

    private static String makeGoToActionText(JSONObject jSONObject) {
        return JSONUtils.optionalString(jSONObject.optJSONObject("goToAction"), "mainText", "");
    }

    private static String makeGoToActionUrl(JSONObject jSONObject) {
        return JSONUtils.optionalString(jSONObject.optJSONObject("goToAction"), "url", "");
    }

    private static List<CharSequence> makeOpeningHours(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        int dayOfWeek = DateUtils.getDayOfWeek(System.currentTimeMillis());
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            if (dayOfWeek >= jSONObject.getInt("startDay") && dayOfWeek <= jSONObject.getInt("endDay")) {
                arrayList.add(TextUtils.concat(jSONObject.getString("startHours"), " - ", jSONObject.getString("endHours")));
            }
        }
        return arrayList;
    }

    private static String makeProviderLogo(JSONObject jSONObject) {
        return JSONUtils.optionalString(jSONObject, "providerLogo", "");
    }

    private static float makeRating(JSONObject jSONObject) throws JSONException {
        return (float) jSONObject.getJSONObject("ratings").optDouble("rating", FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
    }

    private static int makeReviewCount(JSONObject jSONObject) throws JSONException {
        return jSONObject.getJSONObject("ratings").optInt("reviewCount", 0);
    }

    private static CharSequence makeSpendiness(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("priceRangeWithSymbol");
        String string = jSONObject2.getString("symbol");
        int optInt = jSONObject2.optInt("priceRange", 0);
        if (optInt == 0) {
            return null;
        }
        CharSequence charSequence = "";
        for (int i = 0; i < optInt; i++) {
            charSequence = TextUtils.concat(charSequence, string);
        }
        return charSequence;
    }

    private static String makeStarRating(JSONObject jSONObject) throws JSONException {
        return JSONUtils.optionalString(jSONObject.getJSONObject("ratings"), "starRating", "");
    }
}
