package com.amazon.client.metrics.common.clickstream.internal;

import com.amazon.client.metrics.common.DataPoint;
import java.util.List;
/* loaded from: classes11.dex */
public interface IASINData {
    List<DataPoint> getDatapoints();

    String getGroupingASIN();

    String getProductGLID();
}
