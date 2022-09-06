package com.amazon.alexa.handsfree.protocols.voiceappreporter;

import android.net.Uri;
import android.provider.BaseColumns;
/* loaded from: classes8.dex */
public final class VoiceAppEventReporterContract {
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://com.amazon.alexa.handsfree.voiceappreporter.VoiceAppEventContentProvider");
    public static final String CONTENT_AUTHORITY = "com.amazon.alexa.handsfree.voiceappreporter.VoiceAppEventContentProvider";

    /* loaded from: classes8.dex */
    public static final class VoiceAppEventsTable implements BaseColumns {
        public static final String COLUMN_EVENT_DATE = "EVENT_DATE";
        public static final String COLUMN_EVENT_TYPE = "EVENT_TYPE";
        public static final String COLUMN_STACK_TRACE = "STACK_TRACE";
        public static final String COLUMN_THREAD_DUMP = "THREAD_DUMP";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.dir/vnd.com.amazon.alexa.handsfree.voiceappreporter.VoiceAppEventContentProvider.voice_app_events";
        public static final String DATABASE_NAME = "voice_app_events.db";
        public static final String EVENT_TYPE_APP_CRASH = "VOICE_APK_CRASH";
        public static final String EVENT_TYPE_APP_START = "VOICE_APK_START";
        public static final String TABLE_NAME = "eventReportTable";
        public static final String VOICE_APP_EVENT_DIRECTORY = "voice_app_events";
        public static final Uri CONTENT_URI = VoiceAppEventReporterContract.BASE_CONTENT_URI.buildUpon().appendPath(VOICE_APP_EVENT_DIRECTORY).build();
    }

    private VoiceAppEventReporterContract() {
    }
}
