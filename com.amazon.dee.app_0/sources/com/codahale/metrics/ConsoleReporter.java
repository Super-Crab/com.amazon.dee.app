package com.codahale.metrics;

import java.io.PrintStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes9.dex */
public class ConsoleReporter extends ScheduledReporter {
    private static final int CONSOLE_WIDTH = 80;
    private final Clock clock;
    private final DateFormat dateFormat;
    private final Locale locale;
    private final PrintStream output;

    /* loaded from: classes9.dex */
    public static class Builder {
        private Clock clock;
        private TimeUnit durationUnit;
        private MetricFilter filter;
        private Locale locale;
        private PrintStream output;
        private TimeUnit rateUnit;
        private final MetricRegistry registry;
        private TimeZone timeZone;

        private Builder(MetricRegistry metricRegistry) {
            this.registry = metricRegistry;
            this.output = System.out;
            this.locale = Locale.getDefault();
            this.clock = Clock.defaultClock();
            this.timeZone = TimeZone.getDefault();
            this.rateUnit = TimeUnit.SECONDS;
            this.durationUnit = TimeUnit.MILLISECONDS;
            this.filter = MetricFilter.ALL;
        }

        public ConsoleReporter build() {
            return new ConsoleReporter(this.registry, this.output, this.locale, this.clock, this.timeZone, this.rateUnit, this.durationUnit, this.filter);
        }

        public Builder convertDurationsTo(TimeUnit timeUnit) {
            this.durationUnit = timeUnit;
            return this;
        }

        public Builder convertRatesTo(TimeUnit timeUnit) {
            this.rateUnit = timeUnit;
            return this;
        }

        public Builder filter(MetricFilter metricFilter) {
            this.filter = metricFilter;
            return this;
        }

        public Builder formattedFor(Locale locale) {
            this.locale = locale;
            return this;
        }

        public Builder formattedFor(TimeZone timeZone) {
            this.timeZone = timeZone;
            return this;
        }

        public Builder outputTo(PrintStream printStream) {
            this.output = printStream;
            return this;
        }

        public Builder withClock(Clock clock) {
            this.clock = clock;
            return this;
        }
    }

    private ConsoleReporter(MetricRegistry metricRegistry, PrintStream printStream, Locale locale, Clock clock, TimeZone timeZone, TimeUnit timeUnit, TimeUnit timeUnit2, MetricFilter metricFilter) {
        super(metricRegistry, "console-reporter", metricFilter, timeUnit, timeUnit2);
        this.output = printStream;
        this.locale = locale;
        this.clock = clock;
        this.dateFormat = DateFormat.getDateTimeInstance(3, 2, locale);
        this.dateFormat.setTimeZone(timeZone);
    }

    public static Builder forRegistry(MetricRegistry metricRegistry) {
        return new Builder(metricRegistry);
    }

    private void printCounter(Map.Entry<String, Counter> entry) {
        this.output.printf(this.locale, "             count = %d%n", Long.valueOf(entry.getValue().getCount()));
    }

    private void printGauge(Map.Entry<String, Gauge> entry) {
        this.output.printf(this.locale, "             value = %s%n", entry.getValue().mo6796getValue());
    }

    private void printHistogram(Histogram histogram) {
        this.output.printf(this.locale, "             count = %d%n", Long.valueOf(histogram.getCount()));
        Snapshot snapshot = histogram.getSnapshot();
        this.output.printf(this.locale, "               min = %d%n", Long.valueOf(snapshot.getMin()));
        this.output.printf(this.locale, "               max = %d%n", Long.valueOf(snapshot.getMax()));
        this.output.printf(this.locale, "              mean = %2.2f%n", Double.valueOf(snapshot.getMean()));
        this.output.printf(this.locale, "            stddev = %2.2f%n", Double.valueOf(snapshot.getStdDev()));
        this.output.printf(this.locale, "            median = %2.2f%n", Double.valueOf(snapshot.getMedian()));
        this.output.printf(this.locale, "              75%% <= %2.2f%n", Double.valueOf(snapshot.get75thPercentile()));
        this.output.printf(this.locale, "              95%% <= %2.2f%n", Double.valueOf(snapshot.get95thPercentile()));
        this.output.printf(this.locale, "              98%% <= %2.2f%n", Double.valueOf(snapshot.get98thPercentile()));
        this.output.printf(this.locale, "              99%% <= %2.2f%n", Double.valueOf(snapshot.get99thPercentile()));
        this.output.printf(this.locale, "            99.9%% <= %2.2f%n", Double.valueOf(snapshot.get999thPercentile()));
    }

    private void printMeter(Meter meter) {
        this.output.printf(this.locale, "             count = %d%n", Long.valueOf(meter.getCount()));
        this.output.printf(this.locale, "         mean rate = %2.2f events/%s%n", Double.valueOf(convertRate(meter.getMeanRate())), getRateUnit());
        this.output.printf(this.locale, "     1-minute rate = %2.2f events/%s%n", Double.valueOf(convertRate(meter.getOneMinuteRate())), getRateUnit());
        this.output.printf(this.locale, "     5-minute rate = %2.2f events/%s%n", Double.valueOf(convertRate(meter.getFiveMinuteRate())), getRateUnit());
        this.output.printf(this.locale, "    15-minute rate = %2.2f events/%s%n", Double.valueOf(convertRate(meter.getFifteenMinuteRate())), getRateUnit());
    }

    private void printTimer(Timer timer) {
        Snapshot snapshot = timer.getSnapshot();
        this.output.printf(this.locale, "             count = %d%n", Long.valueOf(timer.getCount()));
        this.output.printf(this.locale, "         mean rate = %2.2f calls/%s%n", Double.valueOf(convertRate(timer.getMeanRate())), getRateUnit());
        this.output.printf(this.locale, "     1-minute rate = %2.2f calls/%s%n", Double.valueOf(convertRate(timer.getOneMinuteRate())), getRateUnit());
        this.output.printf(this.locale, "     5-minute rate = %2.2f calls/%s%n", Double.valueOf(convertRate(timer.getFiveMinuteRate())), getRateUnit());
        this.output.printf(this.locale, "    15-minute rate = %2.2f calls/%s%n", Double.valueOf(convertRate(timer.getFifteenMinuteRate())), getRateUnit());
        this.output.printf(this.locale, "               min = %2.2f %s%n", Double.valueOf(convertDuration(snapshot.getMin())), getDurationUnit());
        this.output.printf(this.locale, "               max = %2.2f %s%n", Double.valueOf(convertDuration(snapshot.getMax())), getDurationUnit());
        this.output.printf(this.locale, "              mean = %2.2f %s%n", Double.valueOf(convertDuration(snapshot.getMean())), getDurationUnit());
        this.output.printf(this.locale, "            stddev = %2.2f %s%n", Double.valueOf(convertDuration(snapshot.getStdDev())), getDurationUnit());
        this.output.printf(this.locale, "            median = %2.2f %s%n", Double.valueOf(convertDuration(snapshot.getMedian())), getDurationUnit());
        this.output.printf(this.locale, "              75%% <= %2.2f %s%n", Double.valueOf(convertDuration(snapshot.get75thPercentile())), getDurationUnit());
        this.output.printf(this.locale, "              95%% <= %2.2f %s%n", Double.valueOf(convertDuration(snapshot.get95thPercentile())), getDurationUnit());
        this.output.printf(this.locale, "              98%% <= %2.2f %s%n", Double.valueOf(convertDuration(snapshot.get98thPercentile())), getDurationUnit());
        this.output.printf(this.locale, "              99%% <= %2.2f %s%n", Double.valueOf(convertDuration(snapshot.get99thPercentile())), getDurationUnit());
        this.output.printf(this.locale, "            99.9%% <= %2.2f %s%n", Double.valueOf(convertDuration(snapshot.get999thPercentile())), getDurationUnit());
    }

    private void printWithBanner(String str, char c) {
        this.output.print(str);
        this.output.print(Chars.SPACE);
        for (int i = 0; i < (80 - str.length()) - 1; i++) {
            this.output.print(c);
        }
        this.output.println();
    }

    @Override // com.codahale.metrics.ScheduledReporter
    public void report(SortedMap<String, Gauge> sortedMap, SortedMap<String, Counter> sortedMap2, SortedMap<String, Histogram> sortedMap3, SortedMap<String, Meter> sortedMap4, SortedMap<String, Timer> sortedMap5) {
        printWithBanner(this.dateFormat.format(new Date(this.clock.getTime())), Chars.EQ);
        this.output.println();
        if (!sortedMap.isEmpty()) {
            printWithBanner("-- Gauges", '-');
            for (Map.Entry<String, Gauge> entry : sortedMap.entrySet()) {
                this.output.println(entry.getKey());
                printGauge(entry);
            }
            this.output.println();
        }
        if (!sortedMap2.isEmpty()) {
            printWithBanner("-- Counters", '-');
            for (Map.Entry<String, Counter> entry2 : sortedMap2.entrySet()) {
                this.output.println(entry2.getKey());
                printCounter(entry2);
            }
            this.output.println();
        }
        if (!sortedMap3.isEmpty()) {
            printWithBanner("-- Histograms", '-');
            for (Map.Entry<String, Histogram> entry3 : sortedMap3.entrySet()) {
                this.output.println(entry3.getKey());
                printHistogram(entry3.getValue());
            }
            this.output.println();
        }
        if (!sortedMap4.isEmpty()) {
            printWithBanner("-- Meters", '-');
            for (Map.Entry<String, Meter> entry4 : sortedMap4.entrySet()) {
                this.output.println(entry4.getKey());
                printMeter(entry4.getValue());
            }
            this.output.println();
        }
        if (!sortedMap5.isEmpty()) {
            printWithBanner("-- Timers", '-');
            for (Map.Entry<String, Timer> entry5 : sortedMap5.entrySet()) {
                this.output.println(entry5.getKey());
                printTimer(entry5.getValue());
            }
            this.output.println();
        }
        this.output.println();
        this.output.flush();
    }
}
