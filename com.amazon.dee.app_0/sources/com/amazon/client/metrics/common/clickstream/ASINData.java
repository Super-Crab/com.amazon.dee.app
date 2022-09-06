package com.amazon.client.metrics.common.clickstream;

import com.amazon.client.metrics.common.DataPoint;
import com.amazon.client.metrics.common.clickstream.internal.IASINData;
import com.amazon.client.metrics.common.clickstream.internal.android.AndroidASINData;
import com.amazon.client.metrics.common.clickstream.internal.fireos.FireOSASINData;
import com.amazon.client.metrics.common.internal.util.DevicePlatformIdentifierUtil;
import java.util.List;
/* loaded from: classes11.dex */
public class ASINData implements IASINData {
    private final IASINData mDelegateASINData;

    public ASINData(String str, long j) {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformFireOS()) {
            this.mDelegateASINData = new FireOSASINData(str, j);
        } else {
            this.mDelegateASINData = new AndroidASINData(str, j);
        }
    }

    @Override // com.amazon.client.metrics.common.clickstream.internal.IASINData
    public List<DataPoint> getDatapoints() {
        return this.mDelegateASINData.getDatapoints();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IASINData getDelegateASINData() {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public ASINData(IASINData iASINData) {
        if (iASINData != null) {
            this.mDelegateASINData = iASINData;
            return;
        }
        throw new NullPointerException("Delegate ASINData may not be null");
    }
}
