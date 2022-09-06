package com.amazon.client.metrics.thirdparty.internal;

import com.amazon.client.metrics.thirdparty.DataPoint;
import com.amazon.client.metrics.thirdparty.DataPointType;
import com.amazon.client.metrics.thirdparty.MetricEvent;
import com.amazon.client.metrics.thirdparty.MetricEventType;
import com.amazon.client.metrics.thirdparty.MetricsException;
import com.amazon.client.metrics.thirdparty.clickstream.internal.ClickStreamData;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class BasicMetricEvent implements MetricEvent {
    private static final String ALLOW_RUNNING_TIMERS_KEY = "AllowRunning";
    public static final String ATTRIBUTE_DELIMITER = " ; ";
    private static final String DATAPOINTS_MAP_KEY = "Datapoints";
    private static final String DATAPOINT_DELIMITER = "\t";
    public static final String LIST_DELIMITER = ",";
    private static final String METRIC_EVENT_TYPE_KEY = "Type";
    private static final String PROGRAM_MAP_KEY = "Program";
    private static final String SOURCE_MAP_KEY = "Source";
    private boolean mAllowRunningTimers;
    private boolean mAnonymous;
    protected Map<String, Double> mCounters;
    private MetricEventType mMetricEventType;
    private String mNonAnonymousCustomerId;
    private String mNonAnonymousSessionId;
    private String mProgram;
    private String mSource;
    protected Map<String, String> mStringClickstreamValues;
    protected Map<String, List<String>> mStringDiscreteValues;
    protected Map<String, AggregatingTimer> mTimers;

    /* renamed from: com.amazon.client.metrics.thirdparty.internal.BasicMetricEvent$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$client$metrics$thirdparty$DataPointType = new int[DataPointType.values().length];

        static {
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$DataPointType[DataPointType.CT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$DataPointType[DataPointType.TI.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$DataPointType[DataPointType.DV.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$client$metrics$thirdparty$DataPointType[DataPointType.CK.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public BasicMetricEvent(String str, String str2) {
        this(str, str2, MetricEventType.getDefault());
    }

    private void addClickStreamData(String str, String str2) {
        validateString(str);
        if (validateStringValue(str2)) {
            this.mStringClickstreamValues.put(str, str2);
        }
    }

    private AggregatingTimer getOrCreateTimer(String str) {
        AggregatingTimer aggregatingTimer = this.mTimers.get(str);
        return aggregatingTimer == null ? new AggregatingTimer(this.mAllowRunningTimers) : aggregatingTimer;
    }

    private String listToString(List<?> list) {
        if (list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0).toString());
        for (int i = 1; i < list.size(); i++) {
            sb.append(",");
            sb.append(list.get(i).toString());
        }
        return sb.toString();
    }

    private void validateString(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Invalid string. Cannot be null or empty");
        }
    }

    private boolean validateStringValue(String str) {
        return str != null && !str.contains(ATTRIBUTE_DELIMITER);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addCounter(String str, double d) {
        incrementCounter(str, d);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addDataPoint(DataPoint dataPoint) throws MetricsException {
        DataPointType type = dataPoint.getType();
        try {
            int ordinal = type.ordinal();
            if (ordinal == 0) {
                addCounter(dataPoint.getName(), Double.parseDouble(dataPoint.getValue()));
            } else if (ordinal == 1) {
                addTimer(dataPoint.getName(), Double.parseDouble(dataPoint.getValue()), dataPoint.getSamples());
            } else if (ordinal == 2) {
                addString(dataPoint.getName(), dataPoint.getValue());
            } else if (ordinal == 3) {
                addClickStreamData(dataPoint.getName(), dataPoint.getValue());
            } else {
                throw new IllegalArgumentException("Invalid DataPointType");
            }
        } catch (IllegalArgumentException e) {
            throw new MetricsException("Invalid DataPoint. DataPointType: " + type + ", DataPoint value: " + dataPoint.getValue(), e);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addDataPoints(List<DataPoint> list) throws MetricsException {
        int i = 0;
        MetricsException e = null;
        for (DataPoint dataPoint : list) {
            try {
                addDataPoint(dataPoint);
            } catch (MetricsException e2) {
                e = e2;
                i++;
            }
        }
        if (i == 0) {
            return;
        }
        throw new MetricsException(i + " MetricsExceptions were thrown while adding datapoints", e);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addString(String str, String str2) {
        appendString(str, str2);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addTimer(String str, double d) {
        addTimer(str, d, 1);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void appendString(String str, String str2) {
        validateString(str);
        if (validateStringValue(str2)) {
            List<String> list = this.mStringDiscreteValues.get(str);
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(str2);
            this.mStringDiscreteValues.put(str, list);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void clear() {
        this.mCounters.clear();
        this.mTimers.clear();
        this.mStringDiscreteValues.clear();
    }

    protected String convertDataPointToString(DataPoint dataPoint) {
        return dataPoint.getName() + ATTRIBUTE_DELIMITER + dataPoint.getType().toString() + ATTRIBUTE_DELIMITER + dataPoint.getValue() + ATTRIBUTE_DELIMITER + dataPoint.getSamples();
    }

    protected DataPoint convertStringToDataPoint(String str) throws IllegalArgumentException {
        String[] split = str.split(ATTRIBUTE_DELIMITER);
        if (split.length >= 4) {
            return new DataPoint(split[0], split[2], Integer.valueOf(split[3]).intValue(), DataPointType.valueOf(split[1]));
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Not enough parts to the datapoint: ", str));
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public boolean getAnonymous() {
        return this.mAnonymous;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public List<DataPoint> getAsDataPoints() {
        ClickStreamData[] values;
        DataPoint dataPoint;
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, Double> entry : this.mCounters.entrySet()) {
            arrayList.add(new DataPoint(entry.getKey(), Double.toString(entry.getValue().doubleValue()), 1, DataPointType.CT));
        }
        for (Map.Entry<String, AggregatingTimer> entry2 : this.mTimers.entrySet()) {
            try {
                if (entry2.getValue().getSamples() == 0) {
                    if (!this.mAllowRunningTimers || entry2.getValue().numRunningTimers() <= 0) {
                        throw new IllegalStateException("Discarding timer as sample count is 0. Timer name: " + entry2.getKey());
                    }
                } else {
                    if (this.mMetricEventType.equals(MetricEventType.AGGREGATING)) {
                        dataPoint = new DataPoint(entry2.getKey(), Double.toString(entry2.getValue().getElapsedTime()), entry2.getValue().getSamples(), DataPointType.TI);
                    } else if (this.mMetricEventType.equals(MetricEventType.AVERAGING)) {
                        dataPoint = new DataPoint(entry2.getKey(), Double.toString(entry2.getValue().getElapsedTime() / entry2.getValue().getSamples()), 1, DataPointType.TI);
                    } else {
                        throw new IllegalArgumentException("Unknown Metric event type. Metric event type: " + this.mMetricEventType);
                    }
                    arrayList.add(dataPoint);
                }
            } catch (IllegalArgumentException | IllegalStateException unused) {
            }
        }
        for (Map.Entry<String, List<String>> entry3 : this.mStringDiscreteValues.entrySet()) {
            boolean z = false;
            for (ClickStreamData clickStreamData : ClickStreamData.values()) {
                if (clickStreamData.getName().equals(entry3.getKey()) && !ClickStreamData.isOverrideAllowed(clickStreamData)) {
                    z = true;
                }
            }
            if (!z) {
                arrayList.add(new DataPoint(entry3.getKey(), listToString(entry3.getValue()), 1, DataPointType.DV));
            }
        }
        for (Map.Entry<String, String> entry4 : this.mStringClickstreamValues.entrySet()) {
            arrayList.add(new DataPoint(entry4.getKey(), entry4.getValue(), 1, DataPointType.CK));
        }
        return arrayList;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public MetricEventType getMetricEventType() {
        return this.mMetricEventType;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public String getNonAnonymousCustomerId() {
        return this.mNonAnonymousCustomerId;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public String getNonAnonymousSessionId() {
        return this.mNonAnonymousSessionId;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public String getProgram() {
        return this.mProgram;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public String getSource() {
        return this.mSource;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public boolean hasDataPoints() {
        return getAsDataPoints().size() > 0;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void incrementCounter(String str, double d) {
        DoubleValidator.validateDouble(d);
        validateString(str);
        this.mCounters.put(str, Double.valueOf((this.mCounters.containsKey(str) ? this.mCounters.get(str).doubleValue() : FrostVideoEffectController.VIDEO_STRENGTH_CLEAR) + d));
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void removeCounter(String str) {
        this.mCounters.remove(str);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void removeString(String str) {
        this.mStringDiscreteValues.remove(str);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void removeTimer(String str) {
        this.mTimers.remove(str);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void restoreFromMap(Map<String, String> map) throws IllegalArgumentException {
        if (map.containsKey(PROGRAM_MAP_KEY) && map.containsKey("Source") && map.containsKey(METRIC_EVENT_TYPE_KEY) && map.containsKey(ALLOW_RUNNING_TIMERS_KEY) && map.containsKey(DATAPOINTS_MAP_KEY)) {
            this.mProgram = map.get(PROGRAM_MAP_KEY);
            this.mSource = map.get("Source");
            this.mMetricEventType = MetricEventType.valueOf(map.get(METRIC_EVENT_TYPE_KEY));
            if ("1".equals(map.get(ALLOW_RUNNING_TIMERS_KEY))) {
                this.mAllowRunningTimers = true;
            } else {
                this.mAllowRunningTimers = false;
            }
            String str = map.get(DATAPOINTS_MAP_KEY);
            if ("".equals(str)) {
                return;
            }
            String[] split = str.split("\t");
            for (int i = 0; i < split.length; i++) {
                try {
                    addDataPoint(convertStringToDataPoint(split[i]));
                } catch (MetricsException e) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to restore, invalid datapoint string: ");
                    outline107.append(split[i]);
                    throw new IllegalArgumentException(outline107.toString(), e);
                }
            }
            return;
        }
        throw new IllegalArgumentException("Map doesn't capture a MetricEvent");
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void saveToMap(Map<String, String> map) {
        map.put(PROGRAM_MAP_KEY, this.mProgram);
        map.put("Source", this.mSource);
        map.put(METRIC_EVENT_TYPE_KEY, this.mMetricEventType.toString());
        map.put(ALLOW_RUNNING_TIMERS_KEY, this.mAllowRunningTimers ? "1" : "0");
        StringBuilder sb = new StringBuilder();
        List<DataPoint> asDataPoints = getAsDataPoints();
        for (int i = 0; i < asDataPoints.size(); i++) {
            sb.append(convertDataPointToString(asDataPoints.get(i)));
            sb.append("\t");
        }
        map.put(DATAPOINTS_MAP_KEY, sb.toString());
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void setAnonymous(boolean z) {
        this.mAnonymous = z;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void setClickstreamUserAgent(String str) {
        addString(ClickStreamData.USER_AGENT.getName(), str);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void setNonAnonymousCustomerId(String str) {
        this.mNonAnonymousCustomerId = str;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void setNonAnonymousSessionId(String str) {
        this.mNonAnonymousSessionId = str;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void startTimer(String str) {
        validateString(str);
        AggregatingTimer orCreateTimer = getOrCreateTimer(str);
        orCreateTimer.startTimer();
        this.mTimers.put(str, orCreateTimer);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void stopTimer(String str) {
        validateString(str);
        AggregatingTimer aggregatingTimer = this.mTimers.get(str);
        if (aggregatingTimer == null) {
            return;
        }
        aggregatingTimer.stopTimer();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mProgram);
        sb.append(" ");
        sb.append(this.mSource);
        sb.append(" ");
        List<DataPoint> asDataPoints = getAsDataPoints();
        for (int i = 0; i < asDataPoints.size(); i++) {
            sb.append(asDataPoints.get(i).toString());
        }
        sb.append("\n");
        return sb.toString();
    }

    public BasicMetricEvent(String str, String str2, MetricEventType metricEventType) {
        this(str, str2, metricEventType, false);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addTimer(String str, double d, int i) {
        validateString(str);
        AggregatingTimer orCreateTimer = getOrCreateTimer(str);
        orCreateTimer.addTime(d, i);
        this.mTimers.put(str, orCreateTimer);
    }

    public BasicMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z) {
        this.mCounters = new HashMap();
        this.mTimers = new HashMap();
        this.mStringDiscreteValues = new HashMap();
        this.mStringClickstreamValues = new HashMap();
        validateString(str);
        validateString(str2);
        this.mProgram = str;
        this.mSource = str2;
        this.mMetricEventType = metricEventType;
        this.mAllowRunningTimers = z;
    }
}
