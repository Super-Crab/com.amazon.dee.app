package com.amazon.client.metrics.common.clickstream.internal;

import com.amazon.client.metrics.common.clickstream.ASINData;
import com.amazon.client.metrics.common.clickstream.ClickStreamInfo;
import com.amazon.client.metrics.common.clickstream.UsageInfo;
/* loaded from: classes11.dex */
public interface IUsageInfo extends ClickStreamInfo {
    ASINData getASINData();

    String getHitType();

    String getPageAction();

    String getPageAssemblyType();

    String getPageType();

    String getPageTypeID();

    String getSiteVariant();

    String getSubPageType();

    String getTabID();

    String getTeamName();

    boolean isCustomerHit();

    boolean isPrimeCustomer();

    @Deprecated
    UsageInfo setASINData(ASINData aSINData);

    UsageInfo setASINData(String str, Long l);

    UsageInfo setIsCustomerHit(boolean z);

    UsageInfo setIsPrimeCustomer(boolean z);

    UsageInfo setPageAction(String str);

    UsageInfo setPageAssemblyType(String str);

    UsageInfo setPageTypeID(String str);

    UsageInfo setSubPageType(String str);

    UsageInfo setTabID(String str);
}
