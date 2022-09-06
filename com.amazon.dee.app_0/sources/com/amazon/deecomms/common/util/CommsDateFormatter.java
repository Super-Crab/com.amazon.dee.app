package com.amazon.deecomms.common.util;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.deecomms.R;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import org.joda.time.DateTime;
import org.joda.time.Duration;
/* loaded from: classes12.dex */
public class CommsDateFormatter extends JodaTimeInitializer {
    private static final String YEAR_PATTERN = "[^\\w ]*[Yy][^\\w ]*";
    private static final String YEAR_REPLACEMENT = "";
    private final Context context;
    private DateFormat dayformatter;
    private SimpleDateFormat fullDateFormatter;
    private SimpleDateFormat fullDateTimeFormatter;
    private final Locale locale;
    private DateFormat timeFormatter;
    private DateFormat yearlessDateFormatter;
    private DateFormat yearlessDateTimeFormatter;

    /* loaded from: classes12.dex */
    public static final class Response {
        private String accessible;
        private String humanReadable;

        private Response() {
        }

        @Nullable
        public String getAccessible() {
            String str = this.accessible;
            return str == null ? this.humanReadable : str;
        }

        @Nullable
        public String getHumanReadable() {
            return this.humanReadable;
        }

        /* synthetic */ Response(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommsDateFormatter(Context context) {
        super(context);
        Locale locale = Locale.getDefault();
        this.context = context;
        this.locale = locale;
    }

    private DateFormat getDayFormatter() {
        if (this.dayformatter == null) {
            this.dayformatter = new SimpleDateFormat("EEEE", this.locale);
        }
        return this.dayformatter;
    }

    private SimpleDateFormat getFullDateFormatter() {
        if (this.fullDateFormatter == null) {
            this.fullDateFormatter = (SimpleDateFormat) SimpleDateFormat.getDateInstance(3, this.locale);
        }
        return this.fullDateFormatter;
    }

    private SimpleDateFormat getFullDateTimeFormatter() {
        if (this.fullDateTimeFormatter == null) {
            this.fullDateTimeFormatter = (SimpleDateFormat) SimpleDateFormat.getDateTimeInstance(3, 3, this.locale);
        }
        return this.fullDateTimeFormatter;
    }

    private DateFormat getTimeFormatter() {
        if (this.timeFormatter == null) {
            this.timeFormatter = SimpleDateFormat.getTimeInstance(3, this.locale);
        }
        return this.timeFormatter;
    }

    private DateFormat getYearlessDateFormatter() {
        if (this.yearlessDateFormatter == null) {
            this.yearlessDateFormatter = new SimpleDateFormat(getFullDateFormatter().toPattern().replaceAll(YEAR_PATTERN, ""), this.locale);
        }
        return this.yearlessDateFormatter;
    }

    private DateFormat getYearlessDateTimeFormatter() {
        if (this.yearlessDateTimeFormatter == null) {
            this.yearlessDateTimeFormatter = new SimpleDateFormat(getFullDateTimeFormatter().toPattern().replaceAll(YEAR_PATTERN, ""), this.locale);
        }
        return this.yearlessDateTimeFormatter;
    }

    @NonNull
    public Response toString(long j, boolean z) {
        Response response = new Response(null);
        DateTime withTimeAtStartOfDay = new DateTime().withTimeAtStartOfDay();
        DateTime dateTime = new DateTime(j);
        if (dateTime.isAfter(withTimeAtStartOfDay)) {
            response.humanReadable = getTimeFormatter().format(Long.valueOf(j));
            return response;
        }
        long standardHours = new Duration(j, withTimeAtStartOfDay.getMillis()).getStandardHours();
        if (standardHours <= 24) {
            StringBuilder sb = new StringBuilder();
            if (z) {
                sb.append(this.context.getString(R.string.yesterday_at_text, getTimeFormatter().format(Long.valueOf(j))));
            } else {
                sb.append(this.context.getString(R.string.yesterday_text));
            }
            response.humanReadable = sb.toString();
            return response;
        } else if (standardHours <= 144) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(getDayFormatter().format(Long.valueOf(j)));
            if (z) {
                sb2.append(", ");
                sb2.append(getTimeFormatter().format(Long.valueOf(j)));
            }
            response.humanReadable = sb2.toString();
            return response;
        } else if (dateTime.getYear() != withTimeAtStartOfDay.getYear()) {
            if (z) {
                response.humanReadable = getFullDateTimeFormatter().format(Long.valueOf(j));
            } else {
                response.humanReadable = getFullDateFormatter().format(Long.valueOf(j));
            }
            return response;
        } else {
            if (z) {
                response.humanReadable = getYearlessDateTimeFormatter().format(Long.valueOf(j));
                response.accessible = getFullDateTimeFormatter().format(Long.valueOf(j));
            } else {
                response.humanReadable = getYearlessDateFormatter().format(Long.valueOf(j));
                response.accessible = getFullDateFormatter().format(Long.valueOf(j));
            }
            return response;
        }
    }

    public CommsDateFormatter(Context context, Locale locale) {
        super(context);
        this.context = context;
        this.locale = locale;
    }
}
