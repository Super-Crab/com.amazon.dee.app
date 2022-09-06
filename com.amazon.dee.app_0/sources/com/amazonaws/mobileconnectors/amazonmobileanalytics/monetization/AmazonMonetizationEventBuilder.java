package com.amazonaws.mobileconnectors.amazonmobileanalytics.monetization;

import android.util.Log;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.EventClient;
@Deprecated
/* loaded from: classes13.dex */
public class AmazonMonetizationEventBuilder extends MonetizationEventBuilder {
    private static final String TAG = "AmazonMonetizationEventBuilder";

    protected AmazonMonetizationEventBuilder(EventClient eventClient) {
        super(eventClient);
        setStore("Amazon");
    }

    public static AmazonMonetizationEventBuilder create(EventClient eventClient) {
        return new AmazonMonetizationEventBuilder(eventClient);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.monetization.MonetizationEventBuilder
    protected boolean isValid() {
        if (getProductId() == null) {
            Log.w(TAG, "Amazon Monetization event is not valid: it is missing the product id");
            return false;
        } else if (getQuantity() == null) {
            Log.w(TAG, "Amazon Monetization event is not valid: it is missing the quantity");
            return false;
        } else if (getFormattedItemPrice() != null) {
            return true;
        } else {
            Log.w(TAG, "Amazon Monetization event is not valid: it is missing the formatted localized price");
            return false;
        }
    }

    public AmazonMonetizationEventBuilder withFormattedItemPrice(String str) {
        setFormattedItemPrice(str);
        return this;
    }

    public AmazonMonetizationEventBuilder withProductId(String str) {
        setProductId(str);
        return this;
    }

    public AmazonMonetizationEventBuilder withQuantity(Double d) {
        setQuantity(d);
        return this;
    }
}
