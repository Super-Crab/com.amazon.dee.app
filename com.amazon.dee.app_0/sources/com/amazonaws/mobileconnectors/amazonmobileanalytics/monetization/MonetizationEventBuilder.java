package com.amazonaws.mobileconnectors.amazonmobileanalytics.monetization;

import android.util.Log;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.AnalyticsEvent;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.EventClient;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.StringUtil;
@Deprecated
/* loaded from: classes13.dex */
public abstract class MonetizationEventBuilder {
    static final String AMAZON_STORE = "Amazon";
    static final String GOOGLE_PLAY_STORE = "Google Play";
    static final String PURCHASE_EVENT_CURRENCY_ATTR = "_currency";
    static final String PURCHASE_EVENT_ITEM_PRICE_METRIC = "_item_price";
    static final String PURCHASE_EVENT_LOCALE_ATTR = "_locale";
    static final String PURCHASE_EVENT_NAME = "_monetization.purchase";
    static final String PURCHASE_EVENT_PRICE_FORMATTED_ATTR = "_item_price_formatted";
    static final String PURCHASE_EVENT_PRODUCT_ID_ATTR = "_product_id";
    static final String PURCHASE_EVENT_QUANTITY_METRIC = "_quantity";
    static final String PURCHASE_EVENT_STORE_ATTR = "_store";
    static final String PURCHASE_EVENT_TRANSACTION_ID_ATTR = "_transaction_id";
    private static final String TAG = "MonetizationEventBuilder";
    static final String VIRTUAL_STORE = "Virtual";
    private String currency;
    private final EventClient eventClient;
    private String formattedItemPrice;
    private Double itemPrice;
    private String productId;
    private Double quantity;
    private String store;
    private String transactionId;

    /* JADX INFO: Access modifiers changed from: protected */
    public MonetizationEventBuilder(EventClient eventClient) {
        this.eventClient = eventClient;
    }

    private boolean doBaseValidation() {
        if (this.eventClient == null) {
            Log.w(TAG, "Cannot build Monetization event: the eventClient is null");
            return false;
        } else if (StringUtil.isNullOrEmpty(this.productId)) {
            Log.w(TAG, "Base Monetization event is not valid: it is missing the product id");
            return false;
        } else if (this.quantity == null) {
            Log.w(TAG, "Base Monetization event is not valid: it is missing the quantity");
            return false;
        } else if (StringUtil.isNullOrEmpty(this.store)) {
            Log.w(TAG, "Base Monetization event is not valid: it is missing the store");
            return false;
        } else if ((!StringUtil.isNullOrEmpty(this.currency) && this.itemPrice != null) || !StringUtil.isNullOrEmpty(this.formattedItemPrice)) {
            return true;
        } else {
            Log.w(TAG, "Base Monetization event is not valid: it requires the formatted price or the currency and price");
            return false;
        }
    }

    public AnalyticsEvent build() {
        if (!isValid() || !doBaseValidation()) {
            return null;
        }
        AnalyticsEvent createEvent = this.eventClient.createEvent(PURCHASE_EVENT_NAME);
        createEvent.addAttribute(PURCHASE_EVENT_PRODUCT_ID_ATTR, this.productId);
        createEvent.addAttribute(PURCHASE_EVENT_STORE_ATTR, this.store);
        createEvent.addMetric(PURCHASE_EVENT_QUANTITY_METRIC, this.quantity);
        String str = this.formattedItemPrice;
        if (str != null) {
            createEvent.addAttribute(PURCHASE_EVENT_PRICE_FORMATTED_ATTR, str);
        }
        Double d = this.itemPrice;
        if (d != null) {
            createEvent.addMetric(PURCHASE_EVENT_ITEM_PRICE_METRIC, d);
        }
        String str2 = this.transactionId;
        if (str2 != null) {
            createEvent.addAttribute(PURCHASE_EVENT_TRANSACTION_ID_ATTR, str2);
        }
        String str3 = this.currency;
        if (str3 == null) {
            return createEvent;
        }
        createEvent.addAttribute(PURCHASE_EVENT_CURRENCY_ATTR, str3);
        return createEvent;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getCurrency() {
        return this.currency;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getFormattedItemPrice() {
        return this.formattedItemPrice;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Double getItemPrice() {
        return this.itemPrice;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getProductId() {
        return this.productId;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Double getQuantity() {
        return this.quantity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getStore() {
        return this.store;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String getTransactionId() {
        return this.transactionId;
    }

    protected abstract boolean isValid();

    /* JADX INFO: Access modifiers changed from: protected */
    public void setCurrency(String str) {
        this.currency = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setFormattedItemPrice(String str) {
        this.formattedItemPrice = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setItemPrice(Double d) {
        this.itemPrice = d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setProductId(String str) {
        this.productId = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setQuantity(Double d) {
        this.quantity = d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setStore(String str) {
        this.store = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setTransactionId(String str) {
        this.transactionId = str;
    }
}
