package com.amazon.client.metrics.common.clickstream.internal.android;

import com.amazon.client.metrics.common.DataPoint;
import com.amazon.client.metrics.common.DataPointConverter;
import com.amazon.client.metrics.common.clickstream.ASINData;
import com.amazon.client.metrics.common.clickstream.ASINDataConverter;
import com.amazon.client.metrics.common.clickstream.internal.IUsageInfo;
import com.amazon.client.metrics.thirdparty.clickstream.UsageInfo;
import java.util.List;
/* loaded from: classes11.dex */
public class AndroidUsageInfo implements IUsageInfo {
    private final UsageInfo mDelegateThirdPartyUsageInfo;

    public AndroidUsageInfo(String str, String str2, String str3, String str4) {
        this.mDelegateThirdPartyUsageInfo = new UsageInfo(str, str2, str3, str4);
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public ASINData getASINData() {
        return ASINDataConverter.convertThirdPartyToCommon(this.mDelegateThirdPartyUsageInfo.getASINData());
    }

    @Override // com.amazon.client.metrics.common.clickstream.ClickStreamInfo
    public List<DataPoint> getDataPoints() {
        return DataPointConverter.convertThirdPartyToCommon(this.mDelegateThirdPartyUsageInfo.getDataPoints());
    }

    public UsageInfo getDelegateUsageInfo() {
        return this.mDelegateThirdPartyUsageInfo;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getHitType() {
        return this.mDelegateThirdPartyUsageInfo.getHitType();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getPageAction() {
        return this.mDelegateThirdPartyUsageInfo.getPageAction();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getPageAssemblyType() {
        return this.mDelegateThirdPartyUsageInfo.getPageAssemblyType();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getPageType() {
        return this.mDelegateThirdPartyUsageInfo.getPageType();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getPageTypeID() {
        return this.mDelegateThirdPartyUsageInfo.getPageTypeID();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getSiteVariant() {
        return this.mDelegateThirdPartyUsageInfo.getSiteVariant();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getSubPageType() {
        return this.mDelegateThirdPartyUsageInfo.getSubPageType();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getTabID() {
        return this.mDelegateThirdPartyUsageInfo.getTabID();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getTeamName() {
        return this.mDelegateThirdPartyUsageInfo.getTeamName();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public boolean isCustomerHit() {
        return this.mDelegateThirdPartyUsageInfo.isCustomerHit();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public boolean isPrimeCustomer() {
        return this.mDelegateThirdPartyUsageInfo.isPrimeCustomer();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    @Deprecated
    public com.amazon.client.metrics.common.clickstream.UsageInfo setASINData(ASINData aSINData) {
        com.amazon.client.metrics.thirdparty.clickstream.ASINData convertCommonToThirdParty = ASINDataConverter.convertCommonToThirdParty(aSINData);
        if (convertCommonToThirdParty != null) {
            this.mDelegateThirdPartyUsageInfo.setASINData(convertCommonToThirdParty);
            return null;
        }
        return null;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public com.amazon.client.metrics.common.clickstream.UsageInfo setIsCustomerHit(boolean z) {
        this.mDelegateThirdPartyUsageInfo.setIsCustomerHit(z);
        return null;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public com.amazon.client.metrics.common.clickstream.UsageInfo setIsPrimeCustomer(boolean z) {
        this.mDelegateThirdPartyUsageInfo.setIsPrimeCustomer(z);
        return null;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public com.amazon.client.metrics.common.clickstream.UsageInfo setPageAction(String str) {
        this.mDelegateThirdPartyUsageInfo.setPageAction(str);
        return null;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public com.amazon.client.metrics.common.clickstream.UsageInfo setPageAssemblyType(String str) {
        this.mDelegateThirdPartyUsageInfo.setPageAssemblyType(str);
        return null;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public com.amazon.client.metrics.common.clickstream.UsageInfo setPageTypeID(String str) {
        this.mDelegateThirdPartyUsageInfo.setPageTypeID(str);
        return null;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public com.amazon.client.metrics.common.clickstream.UsageInfo setSubPageType(String str) {
        this.mDelegateThirdPartyUsageInfo.setSubPageType(str);
        return null;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public com.amazon.client.metrics.common.clickstream.UsageInfo setTabID(String str) {
        this.mDelegateThirdPartyUsageInfo.setTabID(str);
        return null;
    }

    public AndroidUsageInfo(UsageInfo usageInfo) {
        if (usageInfo != null) {
            this.mDelegateThirdPartyUsageInfo = usageInfo;
            return;
        }
        throw new NullPointerException("ThirdParty delegate UsageInfo may not be null");
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public com.amazon.client.metrics.common.clickstream.UsageInfo setASINData(String str, Long l) {
        this.mDelegateThirdPartyUsageInfo.setASINData(str, l);
        return null;
    }
}
