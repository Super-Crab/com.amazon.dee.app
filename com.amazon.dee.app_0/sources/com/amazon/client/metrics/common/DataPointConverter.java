package com.amazon.client.metrics.common;

import com.amazon.client.metrics.common.internal.IDataPoint;
import com.amazon.client.metrics.common.internal.android.AndroidDataPoint;
import com.amazon.client.metrics.common.internal.fireos.FireOSDataPoint;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class DataPointConverter {
    public static com.amazon.client.metrics.thirdparty.DataPoint convertAndroidToThirdParty(AndroidDataPoint androidDataPoint) {
        if (androidDataPoint == null) {
            return null;
        }
        return androidDataPoint.getDelegateDataPoint();
    }

    public static com.amazon.client.metrics.DataPoint convertCommonToFirstParty(DataPoint dataPoint) {
        if (dataPoint == null) {
            return null;
        }
        IDataPoint delegateDataPoint = dataPoint.getDelegateDataPoint();
        if (!(delegateDataPoint instanceof FireOSDataPoint)) {
            return null;
        }
        return ((FireOSDataPoint) delegateDataPoint).getDelegateDataPoint();
    }

    public static com.amazon.client.metrics.thirdparty.DataPoint convertCommonToThirdParty(DataPoint dataPoint) {
        if (dataPoint == null) {
            return null;
        }
        IDataPoint delegateDataPoint = dataPoint.getDelegateDataPoint();
        if (!(delegateDataPoint instanceof AndroidDataPoint)) {
            return null;
        }
        return ((AndroidDataPoint) delegateDataPoint).getDelegateDataPoint();
    }

    public static com.amazon.client.metrics.DataPoint convertFireOSToFirstParty(FireOSDataPoint fireOSDataPoint) {
        if (fireOSDataPoint == null) {
            return null;
        }
        return fireOSDataPoint.getDelegateDataPoint();
    }

    public static DataPoint convertFirstPartyToCommon(com.amazon.client.metrics.DataPoint dataPoint) {
        if (dataPoint == null) {
            return null;
        }
        return new DataPoint(convertFirstPartyToFireOS(dataPoint));
    }

    public static FireOSDataPoint convertFirstPartyToFireOS(com.amazon.client.metrics.DataPoint dataPoint) {
        if (dataPoint == null) {
            return null;
        }
        return new FireOSDataPoint(dataPoint);
    }

    public static AndroidDataPoint convertThirdPartyToAndroid(com.amazon.client.metrics.thirdparty.DataPoint dataPoint) {
        if (dataPoint == null) {
            return null;
        }
        return new AndroidDataPoint(dataPoint);
    }

    public static DataPoint convertThirdPartyToCommon(com.amazon.client.metrics.thirdparty.DataPoint dataPoint) {
        if (dataPoint == null) {
            return null;
        }
        return new DataPoint(convertThirdPartyToAndroid(dataPoint));
    }

    public static List<com.amazon.client.metrics.thirdparty.DataPoint> convertAndroidToThirdParty(List<AndroidDataPoint> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (AndroidDataPoint androidDataPoint : list) {
            arrayList.add(androidDataPoint.getDelegateDataPoint());
        }
        return arrayList;
    }

    public static List<com.amazon.client.metrics.DataPoint> convertFireOSToFirstParty(List<FireOSDataPoint> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (FireOSDataPoint fireOSDataPoint : list) {
            arrayList.add(fireOSDataPoint.getDelegateDataPoint());
        }
        return arrayList;
    }

    public static List<FireOSDataPoint> convertFirstPartyToFireOS(List<com.amazon.client.metrics.DataPoint> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (com.amazon.client.metrics.DataPoint dataPoint : list) {
            arrayList.add(new FireOSDataPoint(dataPoint));
        }
        return arrayList;
    }

    public static List<AndroidDataPoint> convertThirdPartyToAndroid(List<com.amazon.client.metrics.thirdparty.DataPoint> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (com.amazon.client.metrics.thirdparty.DataPoint dataPoint : list) {
            arrayList.add(new AndroidDataPoint(dataPoint));
        }
        return arrayList;
    }

    public static List<DataPoint> convertFirstPartyToCommon(List<com.amazon.client.metrics.DataPoint> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (com.amazon.client.metrics.DataPoint dataPoint : list) {
            arrayList.add(new DataPoint(convertFirstPartyToFireOS(dataPoint)));
        }
        return arrayList;
    }

    public static List<DataPoint> convertThirdPartyToCommon(List<com.amazon.client.metrics.thirdparty.DataPoint> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (com.amazon.client.metrics.thirdparty.DataPoint dataPoint : list) {
            arrayList.add(new DataPoint(new AndroidDataPoint(dataPoint)));
        }
        return arrayList;
    }

    public static List<com.amazon.client.metrics.DataPoint> convertCommonToFirstParty(List<DataPoint> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (DataPoint dataPoint : list) {
            com.amazon.client.metrics.DataPoint convertCommonToFirstParty = convertCommonToFirstParty(dataPoint);
            if (convertCommonToFirstParty != null) {
                arrayList.add(convertCommonToFirstParty);
            }
        }
        return arrayList;
    }

    public static List<com.amazon.client.metrics.thirdparty.DataPoint> convertCommonToThirdParty(List<DataPoint> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (DataPoint dataPoint : list) {
            com.amazon.client.metrics.thirdparty.DataPoint convertCommonToThirdParty = convertCommonToThirdParty(dataPoint);
            if (convertCommonToThirdParty != null) {
                arrayList.add(convertCommonToThirdParty);
            }
        }
        return arrayList;
    }
}
