package com.amazonaws.mobileconnectors.amazonmobileanalytics.monetization;

import android.util.Log;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.EventClient;
@Deprecated
/* loaded from: classes13.dex */
public class VirtualMonetizationEventBuilder extends MonetizationEventBuilder {
    private static final String TAG = "VirtualMonetizationEventBuilder";

    protected VirtualMonetizationEventBuilder(EventClient eventClient) {
        super(eventClient);
        setStore("Virtual");
    }

    public static VirtualMonetizationEventBuilder create(EventClient eventClient) {
        return new VirtualMonetizationEventBuilder(eventClient);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.monetization.MonetizationEventBuilder
    protected boolean isValid() {
        if (getProductId() == null) {
            Log.w(TAG, "Virtual Monetization event is not valid: it is missing the product id");
            return false;
        } else if (getQuantity() == null) {
            Log.w(TAG, "Virtual Monetization event is not valid: it is missing the quantity");
            return false;
        } else if (getItemPrice() == null) {
            Log.w(TAG, "Virtual Monetization event is not valid: it is missing the numerical price");
            return false;
        } else if (getCurrency() != null) {
            return true;
        } else {
            Log.w(TAG, "Virtual Monetization event is not valid: it is missing the currency");
            return false;
        }
    }

    public VirtualMonetizationEventBuilder withCurrency(String str) {
        setCurrency(str);
        return this;
    }

    public VirtualMonetizationEventBuilder withItemPrice(double d) {
        setItemPrice(Double.valueOf(d));
        return this;
    }

    public VirtualMonetizationEventBuilder withProductId(String str) {
        setProductId(str);
        return this;
    }

    public VirtualMonetizationEventBuilder withQuantity(Double d) {
        setQuantity(d);
        return this;
    }
}
