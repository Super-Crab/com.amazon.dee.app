package com.amazon.client.metrics.thirdparty.clickstream.internal;

import com.amazon.client.metrics.thirdparty.MetricsException;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public enum ClickStreamData {
    REQUEST_ID("RequestId"),
    SESSION_ID(MetricsConfiguration.SESSION_ID),
    CUSTOMER_ID(MetricsConfiguration.CUSTOMER_ID),
    END_TIME("EndTime"),
    START_TIME("StartTime"),
    IP_ADDRESS(MetricsConfiguration.IP_ADDRESS),
    USER_AGENT(MetricsConfiguration.USER_AGENT),
    MARKETPLACE_ID("Marketplaceid"),
    MARKETPLACE("Marketplace"),
    DEVICE_TYPE_ID("deviceTypeID"),
    INFO("Info"),
    COUNTRY_OF_RESIDENCE("cor"),
    NON_ANONYMOUS_SESSION_ID("nonAnonymousSessionId"),
    NON_ANONYMOUS_CUSTOMER_ID("nonAnonymousCustomerId"),
    ANONYMOUS("anonymous"),
    PAGE_TYPE("page-type"),
    HIT_TYPE("hitType"),
    TEAM_NAME("team-name"),
    SITE_VARIANT("site_variant"),
    PAGE_ACTION("page-action"),
    SUB_PAGE_TYPE("sub-page-type"),
    PAGE_TYPE_ID("page-type-id"),
    TAB_ID("tab-id"),
    PAGE_ASSEMBLY_TYPE("pageAssemblyType"),
    IS_CUSTOMER_HIT("is-customer-hit"),
    IS_PRIME_CUSTOMER("is-prime-customer"),
    GROUPING_ASIN("groupingASIN"),
    PRODUCT_GLID("ProductGlId"),
    WEBLAB_CLIENT_ID("wl-client-id"),
    WEBLAB("wl"),
    ECOMMERCE_PAGE_ACTION("ecommerce-page-action"),
    ORDER_DATA("order-data"),
    IS_PRIME_ELIGIBLE_ITEM("is-prime-eligible-item"),
    IS_GLANCE_VIEW("is-glance-view"),
    IMPRESSION_PROGRAM_GROUP("programGroup"),
    IMPRESSION_TYPE("impressionType"),
    IMPRESSION_DATA("impressionData"),
    IMPRESSION_METADATA("impressionMetadata");
    
    public static final List<ClickStreamData> ALLOWED_OVERRIDES = new ArrayList();
    private final String mName;

    static {
        ALLOWED_OVERRIDES.add(USER_AGENT);
    }

    ClickStreamData(String str) {
        this.mName = str;
    }

    public static ClickStreamData fromString(String str) throws MetricsException {
        ClickStreamData[] values;
        for (ClickStreamData clickStreamData : values()) {
            if (clickStreamData.getName().equalsIgnoreCase(str)) {
                return clickStreamData;
            }
        }
        throw new MetricsException(GeneratedOutlineSupport1.outline72(str, " is not a valid ClickStreamData"));
    }

    public static boolean isOverrideAllowed(ClickStreamData clickStreamData) {
        return ALLOWED_OVERRIDES.contains(clickStreamData);
    }

    public String getName() {
        return this.mName;
    }
}
