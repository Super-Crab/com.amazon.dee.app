package com.amazon.client.metrics.common.clickstream;

import com.amazon.client.metrics.common.clickstream.internal.IASINData;
import com.amazon.client.metrics.common.clickstream.internal.android.AndroidASINData;
import com.amazon.client.metrics.common.clickstream.internal.fireos.FireOSASINData;
/* loaded from: classes11.dex */
public class ASINDataConverter {
    public static com.amazon.client.metrics.clickstream.ASINData convertCommonToFirstParty(ASINData aSINData) {
        if (aSINData == null) {
            return null;
        }
        IASINData delegateASINData = aSINData.getDelegateASINData();
        if (!(delegateASINData instanceof FireOSASINData)) {
            return null;
        }
        return ((FireOSASINData) delegateASINData).getDelegateASINData();
    }

    public static com.amazon.client.metrics.thirdparty.clickstream.ASINData convertCommonToThirdParty(ASINData aSINData) {
        if (aSINData == null) {
            return null;
        }
        IASINData delegateASINData = aSINData.getDelegateASINData();
        if (!(delegateASINData instanceof AndroidASINData)) {
            return null;
        }
        return ((AndroidASINData) delegateASINData).getDelegateASINData();
    }

    public static ASINData convertFirstPartyToCommon(com.amazon.client.metrics.clickstream.ASINData aSINData) {
        if (aSINData == null) {
            return null;
        }
        return new ASINData(new FireOSASINData(aSINData));
    }

    public static FireOSASINData convertFirstPartyToFireOS(com.amazon.client.metrics.clickstream.ASINData aSINData) {
        if (aSINData == null) {
            return null;
        }
        return new FireOSASINData(aSINData);
    }

    public static AndroidASINData convertThirdPartyToAndroid(com.amazon.client.metrics.thirdparty.clickstream.ASINData aSINData) {
        if (aSINData == null) {
            return null;
        }
        return new AndroidASINData(aSINData);
    }

    public static ASINData convertThirdPartyToCommon(com.amazon.client.metrics.thirdparty.clickstream.ASINData aSINData) {
        if (aSINData == null) {
            return null;
        }
        return new ASINData(new AndroidASINData(aSINData));
    }
}
