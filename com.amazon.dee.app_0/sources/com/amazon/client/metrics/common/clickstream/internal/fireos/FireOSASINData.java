package com.amazon.client.metrics.common.clickstream.internal.fireos;

import com.amazon.client.metrics.clickstream.ASINData;
import com.amazon.client.metrics.common.DataPoint;
import com.amazon.client.metrics.common.DataPointConverter;
import com.amazon.client.metrics.common.clickstream.internal.IASINData;
import java.util.List;
/* loaded from: classes11.dex */
public class FireOSASINData implements IASINData {
    private final ASINData mDelegateASINData;

    public FireOSASINData(String str, long j) {
        this.mDelegateASINData = new ASINData(str, j);
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IASINData
    public List<DataPoint> getDatapoints() {
        return DataPointConverter.convertFirstPartyToCommon(this.mDelegateASINData.getDatapoints());
    }

    public ASINData getDelegateASINData() {
        return this.mDelegateASINData;
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IASINData
    public String getGroupingASIN() {
        return this.mDelegateASINData.getGroupingASIN();
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IASINData
    public String getProductGLID() {
        return this.mDelegateASINData.getProductGLID();
    }

    public FireOSASINData(ASINData aSINData) {
        if (aSINData != null) {
            this.mDelegateASINData = aSINData;
            return;
        }
        throw new NullPointerException("Delegate FirstParty ASINData may not be null");
    }
}
