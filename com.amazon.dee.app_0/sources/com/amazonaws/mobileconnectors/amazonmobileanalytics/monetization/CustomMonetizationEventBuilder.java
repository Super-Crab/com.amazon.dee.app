package com.amazonaws.mobileconnectors.amazonmobileanalytics.monetization;

import android.util.Log;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.EventClient;
@Deprecated
/* loaded from: classes13.dex */
public class CustomMonetizationEventBuilder extends MonetizationEventBuilder {
    private static final String TAG = "CustomMonetizationEventBuilder";

    protected CustomMonetizationEventBuilder(EventClient eventClient) {
        super(eventClient);
    }

    public static CustomMonetizationEventBuilder create(EventClient eventClient) {
        return new CustomMonetizationEventBuilder(eventClient);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.monetization.MonetizationEventBuilder
    protected boolean isValid() {
        if (getStore() == null) {
            Log.w(TAG, "Custom Monetization event is not valid: it is missing the store");
            return false;
        } else if (getProductId() == null) {
            Log.w(TAG, "Custom Monetization event is not valid: it is missing the product id");
            return false;
        } else if (getQuantity() == null) {
            Log.w(TAG, "Custom Monetization event is not valid: it is missing the quantity");
            return false;
        } else if ((getCurrency() != null && getItemPrice() != null) || getFormattedItemPrice() != null) {
            return true;
        } else {
            Log.w(TAG, "Custom Monetization event is not valid: it requires the formatted localized price or the currency and price");
            return false;
        }
    }

    public CustomMonetizationEventBuilder withCurrency(String str) {
        setCurrency(str);
        return this;
    }

    public CustomMonetizationEventBuilder withFormattedItemPrice(String str) {
        setFormattedItemPrice(str);
        return this;
    }

    public CustomMonetizationEventBuilder withItemPrice(double d) {
        setItemPrice(Double.valueOf(d));
        return this;
    }

    public CustomMonetizationEventBuilder withProductId(String str) {
        setProductId(str);
        return this;
    }

    public CustomMonetizationEventBuilder withQuantity(Double d) {
        setQuantity(d);
        return this;
    }

    public CustomMonetizationEventBuilder withStore(String str) {
        setStore(str);
        return this;
    }

    public CustomMonetizationEventBuilder withTransactionId(String str) {
        setTransactionId(str);
        return this;
    }
}
