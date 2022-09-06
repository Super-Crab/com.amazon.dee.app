package com.amazon.client.metrics.common.clickstream.internal;

import com.amazon.client.metrics.common.clickstream.ClickStreamInfo;
import com.amazon.client.metrics.common.clickstream.ImpressionTrackingData;
import java.util.List;
import java.util.Map;
@Deprecated
/* loaded from: classes11.dex */
public interface IImpressionTrackingData extends ClickStreamInfo {
    @Deprecated
    List<String> getImpresionData();

    @Deprecated
    Map<String, String> getImpressionMetadata();

    @Deprecated
    String getImpressionProgramGroup();

    @Deprecated
    String getImpressionType();

    @Deprecated
    ImpressionTrackingData setImpresionData(List<String> list);

    @Deprecated
    ImpressionTrackingData setImpressionMetadata(Map<String, String> map);

    @Deprecated
    ImpressionTrackingData setImpressionProgramGroup(String str);

    @Deprecated
    ImpressionTrackingData setImpressionType(String str);
}
