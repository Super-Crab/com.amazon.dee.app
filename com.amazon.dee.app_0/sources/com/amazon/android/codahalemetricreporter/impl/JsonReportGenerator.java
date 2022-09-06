package com.amazon.android.codahalemetricreporter.impl;

import android.util.JsonWriter;
import com.amazon.android.codahalemetricreporter.JsonReportFormat;
import com.codahale.metrics.Counter;
import com.codahale.metrics.Counting;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.Meter;
import com.codahale.metrics.Metered;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Sampling;
import com.codahale.metrics.Snapshot;
import com.codahale.metrics.Timer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.SortedMap;
/* loaded from: classes11.dex */
public class JsonReportGenerator implements ReportGenerator {
    private static final Charset CHARSET = Charset.forName("UTF-8");

    private static void writeCounters(JsonWriter jsonWriter, SortedMap<String, Counter> sortedMap) throws IOException {
        if (sortedMap.isEmpty()) {
            return;
        }
        jsonWriter.name(JsonReportFormat.COUNTERS);
        jsonWriter.beginObject();
        for (Map.Entry<String, Counter> entry : sortedMap.entrySet()) {
            jsonWriter.name(entry.getKey()).value(entry.getValue().getCount());
        }
        jsonWriter.endObject();
    }

    private static void writeCounting(JsonWriter jsonWriter, Counting counting) throws IOException {
        jsonWriter.name(JsonReportFormat.COUNT).value(counting.getCount());
    }

    private static void writeGauges(JsonWriter jsonWriter, SortedMap<String, Gauge> sortedMap) throws IOException {
        if (sortedMap.isEmpty()) {
            return;
        }
        jsonWriter.name(JsonReportFormat.GAUGES);
        jsonWriter.beginObject();
        for (Map.Entry<String, Gauge> entry : sortedMap.entrySet()) {
            jsonWriter.name(entry.getKey()).value(String.valueOf(entry.getValue().mo6796getValue()));
        }
        jsonWriter.endObject();
    }

    private static void writeHistograms(JsonWriter jsonWriter, SortedMap<String, Histogram> sortedMap) throws IOException {
        if (sortedMap.isEmpty()) {
            return;
        }
        jsonWriter.name(JsonReportFormat.HISTOGRAMS);
        jsonWriter.beginObject();
        for (Map.Entry<String, Histogram> entry : sortedMap.entrySet()) {
            jsonWriter.name(entry.getKey());
            Histogram value = entry.getValue();
            jsonWriter.beginObject();
            writeCounting(jsonWriter, value);
            writeSampling(jsonWriter, value);
            jsonWriter.endObject();
        }
        jsonWriter.endObject();
    }

    private static void writeMetered(JsonWriter jsonWriter, Metered metered) throws IOException {
        jsonWriter.name(JsonReportFormat.RATE_AVG).value(metered.getMeanRate());
        jsonWriter.name(JsonReportFormat.RATE_1M).value(metered.getOneMinuteRate());
        jsonWriter.name(JsonReportFormat.RATE_5M).value(metered.getFiveMinuteRate());
        jsonWriter.name(JsonReportFormat.RATE_15M).value(metered.getFifteenMinuteRate());
    }

    private static void writeMeters(JsonWriter jsonWriter, SortedMap<String, Meter> sortedMap) throws IOException {
        if (sortedMap.isEmpty()) {
            return;
        }
        jsonWriter.name(JsonReportFormat.METERS);
        jsonWriter.beginObject();
        for (Map.Entry<String, Meter> entry : sortedMap.entrySet()) {
            jsonWriter.name(entry.getKey());
            Meter value = entry.getValue();
            jsonWriter.beginObject();
            writeCounting(jsonWriter, value);
            writeMetered(jsonWriter, value);
            jsonWriter.endObject();
        }
        jsonWriter.endObject();
    }

    private static void writeRegistry(MetricRegistry metricRegistry, MetricFilter metricFilter, Writer writer) throws IOException {
        JsonWriter jsonWriter = new JsonWriter(writer);
        jsonWriter.setIndent("  ");
        jsonWriter.beginObject();
        writeGauges(jsonWriter, metricRegistry.getGauges(metricFilter));
        writeCounters(jsonWriter, metricRegistry.getCounters(metricFilter));
        writeHistograms(jsonWriter, metricRegistry.getHistograms(metricFilter));
        writeMeters(jsonWriter, metricRegistry.getMeters(metricFilter));
        writeTimers(jsonWriter, metricRegistry.getTimers(metricFilter));
        jsonWriter.endObject();
        jsonWriter.flush();
    }

    private static void writeSampling(JsonWriter jsonWriter, Sampling sampling) throws IOException {
        Snapshot snapshot = sampling.getSnapshot();
        jsonWriter.name(JsonReportFormat.SAMPLING_MIN).value(snapshot.getMin());
        jsonWriter.name(JsonReportFormat.SAMPLING_MAX).value(snapshot.getMax());
        jsonWriter.name(JsonReportFormat.SAMPLING_AVG).value(snapshot.getMean());
        jsonWriter.name(JsonReportFormat.SAMPLING_MEDIAN).value(snapshot.getMedian());
        jsonWriter.name(JsonReportFormat.SAMPLING_STDDEV).value(snapshot.getStdDev());
        jsonWriter.name(JsonReportFormat.SAMPLING_75P).value(snapshot.get75thPercentile());
        jsonWriter.name(JsonReportFormat.SAMPLING_95P).value(snapshot.get95thPercentile());
        jsonWriter.name(JsonReportFormat.SAMPLING_99P).value(snapshot.get99thPercentile());
    }

    private static void writeTimers(JsonWriter jsonWriter, SortedMap<String, Timer> sortedMap) throws IOException {
        if (sortedMap.isEmpty()) {
            return;
        }
        jsonWriter.name("timers");
        jsonWriter.beginObject();
        for (Map.Entry<String, Timer> entry : sortedMap.entrySet()) {
            jsonWriter.name(entry.getKey());
            Timer value = entry.getValue();
            jsonWriter.beginObject();
            writeCounting(jsonWriter, value);
            writeMetered(jsonWriter, value);
            writeSampling(jsonWriter, value);
            jsonWriter.endObject();
        }
        jsonWriter.endObject();
    }

    @Override // com.amazon.android.codahalemetricreporter.impl.ReportGenerator
    public void generate(MetricRegistry metricRegistry, MetricFilter metricFilter, OutputStream outputStream) throws IOException {
        writeRegistry(metricRegistry, metricFilter, new OutputStreamWriter(outputStream, CHARSET));
    }
}
