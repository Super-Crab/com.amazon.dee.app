package com.amazon.client.metrics.thirdparty.clickstream;

import com.amazon.client.metrics.thirdparty.DataPoint;
import com.amazon.client.metrics.thirdparty.clickstream.internal.ClickStreamData;
import com.amazon.client.metrics.thirdparty.clickstream.internal.ClickStreamHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class ImpressionTrackingData implements ClickStreamInfo {
    List<String> mImpressionData;
    Map<String, String> mImpressionMetadata;
    String mImpressionType;
    String mProgramGroup;

    @Override // com.amazon.client.metrics.thirdparty.clickstream.ClickStreamInfo
    public List<DataPoint> getDataPoints() {
        ArrayList arrayList = new ArrayList();
        ClickStreamHelper.addDatapoint(arrayList, ClickStreamData.IMPRESSION_PROGRAM_GROUP.getName(), this.mProgramGroup);
        ClickStreamHelper.addDatapoint(arrayList, ClickStreamData.IMPRESSION_TYPE.getName(), this.mImpressionType);
        ClickStreamHelper.addDatapoint(arrayList, ClickStreamData.IMPRESSION_DATA.getName(), ClickStreamHelper.listToString(this.mImpressionData));
        ClickStreamHelper.addDatapoint(arrayList, ClickStreamData.IMPRESSION_METADATA.getName(), ClickStreamHelper.mapToString(this.mImpressionMetadata));
        return arrayList;
    }

    public List<String> getImpresionData() {
        return this.mImpressionData;
    }

    public Map<String, String> getImpressionMetadata() {
        return this.mImpressionMetadata;
    }

    public String getImpressionProgramGroup() {
        return this.mProgramGroup;
    }

    public String getImpressionType() {
        return this.mImpressionType;
    }

    public ImpressionTrackingData setImpresionData(List<String> list) {
        this.mImpressionData = list;
        return this;
    }

    public ImpressionTrackingData setImpressionMetadata(Map<String, String> map) {
        this.mImpressionMetadata = map;
        return this;
    }

    public ImpressionTrackingData setImpressionProgramGroup(String str) {
        this.mProgramGroup = str;
        return this;
    }

    public ImpressionTrackingData setImpressionType(String str) {
        this.mImpressionType = str;
        return this;
    }
}
