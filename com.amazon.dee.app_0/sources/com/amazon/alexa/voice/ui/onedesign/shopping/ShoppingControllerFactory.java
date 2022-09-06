package com.amazon.alexa.voice.ui.onedesign.shopping;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.voice.ui.onedesign.shopping.Product;
import com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingCard;
import com.amazon.alexa.voice.ui.superbowl.ControllerFactory;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.dee.app.BuildConfig;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public final class ShoppingControllerFactory implements ControllerFactory<ShoppingController> {
    private static final String CARD_TYPE_MULTI = "MultiProductInfoCard";
    private static final String CARD_TYPE_SINGLE = "ProductInfoCard";

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes11.dex */
    public @interface ShoppingCardType {
    }

    private boolean hasAnyPrime(JSONObject jSONObject) throws JSONException {
        for (String str : new String[]{"primeShipping", "primePantry", "primeNow"}) {
            if (jSONObject.getBoolean(str)) {
                return true;
            }
        }
        return false;
    }

    private long makeAvailableDate(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2;
        if (!jSONObject.isNull("deliveryEstimate") && (jSONObject2 = jSONObject.getJSONObject("deliveryEstimate")) != null) {
            return jSONObject2.getLong("endTimeStamp");
        }
        return -1L;
    }

    private String makeLinkUrl(JSONObject jSONObject) throws JSONException {
        return String.format("https://www.%s/dp/%s", BuildConfig.RETAIL_HOST, jSONObject.getString(EntertainmentConstants.ENTERTAINMENT_PLAY_PAYLOAD_JSON_ATTR_ASIN));
    }

    private CharSequence makePrice(JSONObject jSONObject) throws JSONException {
        if (!jSONObject.isNull("grandTotalText")) {
            return jSONObject.getString("grandTotalText");
        }
        return jSONObject.getString("ourPriceText");
    }

    private Product makeProduct(JSONObject jSONObject) throws JSONException {
        String str = null;
        String string = jSONObject.isNull("unitPriceText") ? null : jSONObject.getString("unitPriceText");
        if (!jSONObject.isNull("shippedAndSoldBy")) {
            str = jSONObject.getString("shippedAndSoldBy");
        }
        return new Product.Builder().imageUrl(jSONObject.getString("imageUrl")).name(jSONObject.getString("title")).rating(makeRating(jSONObject)).reviewCount(jSONObject.optInt("ratingCount", 0)).price(makePrice(jSONObject)).unitPriceText(string).shippedAndSoldBy(str).primeAvailable(hasAnyPrime(jSONObject.getJSONObject("badges"))).availableDate(makeAvailableDate(jSONObject)).linkUrl(makeLinkUrl(jSONObject)).build();
    }

    private List<Product> makeProductList(JSONObject jSONObject, String str) throws JSONException {
        if (str.equals(CARD_TYPE_SINGLE)) {
            return Collections.singletonList(makeProduct(jSONObject.getJSONObject("productInfo")));
        }
        if (str.equals(CARD_TYPE_MULTI)) {
            JSONArray jSONArray = jSONObject.getJSONArray("productInfoList");
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(makeProduct(jSONArray.getJSONObject(i)));
            }
            return arrayList;
        }
        throw new IllegalStateException("Card type not single or multiple product info.");
    }

    private float makeRating(JSONObject jSONObject) {
        return ((float) jSONObject.optDouble("ratingStars", FrostVideoEffectController.VIDEO_STRENGTH_CLEAR)) / 10.0f;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.voice.ui.superbowl.ControllerFactory
    /* renamed from: create */
    public ShoppingController mo2780create(JSONObject jSONObject) throws JSONException {
        return ShoppingController.create(new ShoppingCard.Builder().productList(makeProductList(jSONObject, jSONObject.getString("cardType"))).build());
    }
}
