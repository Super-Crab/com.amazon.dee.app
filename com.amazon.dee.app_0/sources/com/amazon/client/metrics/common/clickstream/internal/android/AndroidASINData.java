package com.amazon.client.metrics.common.clickstream.internal.android;

import com.amazon.client.metrics.common.DataPoint;
import com.amazon.client.metrics.common.DataPointConverter;
import com.amazon.client.metrics.common.clickstream.internal.IASINData;
import com.amazon.client.metrics.thirdparty.clickstream.ASINData;
import java.util.List;
/* loaded from: classes11.dex */
public class AndroidASINData implements IASINData {
    private final ASINData mDelegateThirdPartyASINData;

    public AndroidASINData(String str, long j) {
        this.mDelegateThirdPartyASINData = new ASINData(str, j);
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IASINData
    public List<DataPoint> getDatapoints() {
        return DataPointConverter.convertThirdPartyToCommon(this.mDelegateThirdPartyASINData.getDatapoints());
    }

    public ASINData getDelegateASINData() {
        return this.mDelegateThirdPartyASINData;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IASINData
    public String getGroupingASIN() {
        return this.mDelegateThirdPartyASINData.getGroupingASIN();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IASINData
    public String getProductGLID() {
        return this.mDelegateThirdPartyASINData.getProductGLID();
    }

    public AndroidASINData(ASINData aSINData) {
        if (aSINData != null) {
            this.mDelegateThirdPartyASINData = aSINData;
            return;
        }
        throw new NullPointerException("Delegate ThirdParty ASINData may not be null");
    }
}
