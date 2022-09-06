package com.amazon.client.metrics.common.internal;

import com.amazon.client.metrics.common.DataPointType;
/* loaded from: classes11.dex */
public interface IDataPoint {
    String getName();

    int getSamples();

    DataPointType getType();

    String getValue();
}
