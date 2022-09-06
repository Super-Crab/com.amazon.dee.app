package com.amazon.client.metrics.common.clickstream.internal.fireos;

import com.amazon.client.metrics.clickstream.UsageInfo;
import com.amazon.client.metrics.common.DataPoint;
import com.amazon.client.metrics.common.DataPointConverter;
import com.amazon.client.metrics.common.clickstream.ASINData;
import com.amazon.client.metrics.common.clickstream.ASINDataConverter;
import com.amazon.client.metrics.common.clickstream.internal.IUsageInfo;
import java.util.List;
/* loaded from: classes11.dex */
public class FireOSUsageInfo implements IUsageInfo {
    private final UsageInfo mDelegateFirstPartyUsageInfo;

    public FireOSUsageInfo(String str, String str2, String str3, String str4) {
        this.mDelegateFirstPartyUsageInfo = new UsageInfo(str, str2, str3, str4);
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public ASINData getASINData() {
        return ASINDataConverter.convertFirstPartyToCommon(this.mDelegateFirstPartyUsageInfo.getASINData());
    }

    @Override // com.amazon.client.metrics.common.clickstream.ClickStreamInfo
    public List<DataPoint> getDataPoints() {
        return DataPointConverter.convertFirstPartyToCommon(this.mDelegateFirstPartyUsageInfo.getDataPoints());
    }

    public UsageInfo getDelegateUsageInfo() {
        return this.mDelegateFirstPartyUsageInfo;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getHitType() {
        return this.mDelegateFirstPartyUsageInfo.getHitType();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getPageAction() {
        return this.mDelegateFirstPartyUsageInfo.getPageAction();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getPageAssemblyType() {
        return this.mDelegateFirstPartyUsageInfo.getPageAssemblyType();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getPageType() {
        return this.mDelegateFirstPartyUsageInfo.getPageType();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getPageTypeID() {
        return this.mDelegateFirstPartyUsageInfo.getPageTypeID();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getSiteVariant() {
        return this.mDelegateFirstPartyUsageInfo.getSiteVariant();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getSubPageType() {
        return this.mDelegateFirstPartyUsageInfo.getSubPageType();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getTabID() {
        return this.mDelegateFirstPartyUsageInfo.getTabID();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getTeamName() {
        return this.mDelegateFirstPartyUsageInfo.getTeamName();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public boolean isCustomerHit() {
        return this.mDelegateFirstPartyUsageInfo.isCustomerHit();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public boolean isPrimeCustomer() {
        return this.mDelegateFirstPartyUsageInfo.isPrimeCustomer();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public com.amazon.client.metrics.common.clickstream.UsageInfo setASINData(ASINData aSINData) {
        return null;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public com.amazon.client.metrics.common.clickstream.UsageInfo setASINData(String str, Long l) {
        this.mDelegateFirstPartyUsageInfo.setASINData(str, l);
        return null;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public com.amazon.client.metrics.common.clickstream.UsageInfo setIsCustomerHit(boolean z) {
        this.mDelegateFirstPartyUsageInfo.setIsCustomerHit(z);
        return null;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public com.amazon.client.metrics.common.clickstream.UsageInfo setIsPrimeCustomer(boolean z) {
        this.mDelegateFirstPartyUsageInfo.setIsPrimeCustomer(z);
        return null;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public com.amazon.client.metrics.common.clickstream.UsageInfo setPageAction(String str) {
        this.mDelegateFirstPartyUsageInfo.setPageAction(str);
        return null;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public com.amazon.client.metrics.common.clickstream.UsageInfo setPageAssemblyType(String str) {
        this.mDelegateFirstPartyUsageInfo.setPageAssemblyType(str);
        return null;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public com.amazon.client.metrics.common.clickstream.UsageInfo setPageTypeID(String str) {
        this.mDelegateFirstPartyUsageInfo.setPageTypeID(str);
        return null;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public com.amazon.client.metrics.common.clickstream.UsageInfo setSubPageType(String str) {
        this.mDelegateFirstPartyUsageInfo.setSubPageType(str);
        return null;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public com.amazon.client.metrics.common.clickstream.UsageInfo setTabID(String str) {
        this.mDelegateFirstPartyUsageInfo.setTabID(str);
        return null;
    }

    public FireOSUsageInfo(UsageInfo usageInfo) {
        if (usageInfo != null) {
            this.mDelegateFirstPartyUsageInfo = usageInfo;
            return;
        }
        throw new NullPointerException("Delegate FirstParty UsageInfo may not be null");
    }
}
