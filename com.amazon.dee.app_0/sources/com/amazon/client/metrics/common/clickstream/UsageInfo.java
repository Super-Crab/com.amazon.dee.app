package com.amazon.client.metrics.common.clickstream;

import com.amazon.client.metrics.common.DataPoint;
import com.amazon.client.metrics.common.clickstream.internal.IUsageInfo;
import com.amazon.client.metrics.common.clickstream.internal.android.AndroidUsageInfo;
import com.amazon.client.metrics.common.clickstream.internal.fireos.FireOSUsageInfo;
import com.amazon.client.metrics.common.internal.util.DevicePlatformIdentifierUtil;
import java.util.List;
/* loaded from: classes11.dex */
public class UsageInfo implements IUsageInfo {
    private final IUsageInfo mDelegateUsageInfo;

    public UsageInfo(String str, String str2, String str3, String str4) {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformFireOS()) {
            this.mDelegateUsageInfo = new FireOSUsageInfo(str, str2, str3, str4);
        } else {
            this.mDelegateUsageInfo = new AndroidUsageInfo(str, str2, str3, str4);
        }
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public ASINData getASINData() {
        return this.mDelegateUsageInfo.getASINData();
    }

    @Override // com.amazon.client.metrics.common.clickstream.ClickStreamInfo
    public List<DataPoint> getDataPoints() {
        return this.mDelegateUsageInfo.getDataPoints();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IUsageInfo getDelegateUsageInfo() {
        return this.mDelegateUsageInfo;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getHitType() {
        return this.mDelegateUsageInfo.getHitType();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getPageAction() {
        return this.mDelegateUsageInfo.getPageAction();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getPageAssemblyType() {
        return this.mDelegateUsageInfo.getPageAssemblyType();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getPageType() {
        return this.mDelegateUsageInfo.getPageType();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getPageTypeID() {
        return this.mDelegateUsageInfo.getPageTypeID();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getSiteVariant() {
        return this.mDelegateUsageInfo.getSiteVariant();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getSubPageType() {
        return this.mDelegateUsageInfo.getSubPageType();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getTabID() {
        return this.mDelegateUsageInfo.getTabID();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public String getTeamName() {
        return this.mDelegateUsageInfo.getTeamName();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public boolean isCustomerHit() {
        return this.mDelegateUsageInfo.isCustomerHit();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public boolean isPrimeCustomer() {
        return this.mDelegateUsageInfo.isPrimeCustomer();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    @Deprecated
    public UsageInfo setASINData(ASINData aSINData) {
        this.mDelegateUsageInfo.setASINData(aSINData);
        return this;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public UsageInfo setIsCustomerHit(boolean z) {
        this.mDelegateUsageInfo.setIsCustomerHit(z);
        return this;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public UsageInfo setIsPrimeCustomer(boolean z) {
        this.mDelegateUsageInfo.setIsPrimeCustomer(z);
        return this;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public UsageInfo setPageAction(String str) {
        this.mDelegateUsageInfo.setPageAction(str);
        return this;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public UsageInfo setPageAssemblyType(String str) {
        this.mDelegateUsageInfo.setPageAssemblyType(str);
        return this;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public UsageInfo setPageTypeID(String str) {
        this.mDelegateUsageInfo.setPageTypeID(str);
        return this;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public UsageInfo setSubPageType(String str) {
        this.mDelegateUsageInfo.setSubPageType(str);
        return this;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public UsageInfo setTabID(String str) {
        this.mDelegateUsageInfo.setTabID(str);
        return this;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IUsageInfo
    public UsageInfo setASINData(String str, Long l) {
        this.mDelegateUsageInfo.setASINData(str, l);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UsageInfo(IUsageInfo iUsageInfo) {
        if (iUsageInfo != null) {
            this.mDelegateUsageInfo = iUsageInfo;
            return;
        }
        throw new NullPointerException("Delegate UsageInfo may not be null");
    }
}
