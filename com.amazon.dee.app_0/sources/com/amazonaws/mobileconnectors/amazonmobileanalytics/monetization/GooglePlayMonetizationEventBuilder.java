package com.amazonaws.mobileconnectors.amazonmobileanalytics.monetization;

import android.util.Log;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.EventClient;
@Deprecated
/* loaded from: classes13.dex */
public class GooglePlayMonetizationEventBuilder extends MonetizationEventBuilder {
    private static final String TAG = "GooglePlayMonetizationEventBuilder";

    protected GooglePlayMonetizationEventBuilder(EventClient eventClient) {
        super(eventClient);
        setStore("Google Play");
    }

    public static GooglePlayMonetizationEventBuilder create(EventClient eventClient) {
        return new GooglePlayMonetizationEventBuilder(eventClient);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.monetization.MonetizationEventBuilder
    protected boolean isValid() {
        if (getProductId() == null) {
            Log.w(TAG, "Cannot build Google Monetization event: it is missing the product id");
            return false;
        } else if (getQuantity() == null) {
            Log.w(TAG, "Google Monetization event is not valid: it is missing the quantity");
            return false;
        } else if (getFormattedItemPrice() == null) {
            Log.w(TAG, "Google Monetization event is not valid: it is missing the formatted localized price");
            return false;
        } else if (getTransactionId() != null) {
            return true;
        } else {
            Log.w(TAG, "Google Monetization event is not valid: it is missing the transaction id");
            return false;
        }
    }

    public GooglePlayMonetizationEventBuilder withFormattedItemPrice(String str) {
        setFormattedItemPrice(str);
        return this;
    }

    public GooglePlayMonetizationEventBuilder withProductId(String str) {
        setProductId(str);
        return this;
    }

    public GooglePlayMonetizationEventBuilder withQuantity(Double d) {
        setQuantity(d);
        return this;
    }

    public GooglePlayMonetizationEventBuilder withTransactionId(String str) {
        setTransactionId(str);
        return this;
    }
}
