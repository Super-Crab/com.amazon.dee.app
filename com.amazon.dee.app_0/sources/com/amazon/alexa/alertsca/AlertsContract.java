package com.amazon.alexa.alertsca;

import android.provider.BaseColumns;
/* loaded from: classes6.dex */
public class AlertsContract {

    /* loaded from: classes6.dex */
    public static class AlertAssetInfoEntry implements BaseColumns {
        public static final String COLUMN_NAME_BACKGROUND_ALERT_ASSET = "backgroundAlertAsset";
        public static final String COLUMN_NAME_FOREGROUND_ALERT_ASSET = "foregroundAlertAsset";
        public static final String COLUMN_NAME_LABEL = "label";
        public static final String COLUMN_NAME_LOOP_COUNT = "loopCount";
        public static final String COLUMN_NAME_LOOP_PAUSE = "loopPauseInMilliseconds";
        public static final String COLUMN_NAME_TOKEN = "token";
        public static final String TABLE_NAME = "assetInfoTable";
    }

    /* loaded from: classes6.dex */
    public static class AlertRecordEntry implements BaseColumns {
        public static final String COLUMN_NAME_ASSET_INFO_EXISTS = "assetInfoExists";
        public static final String COLUMN_NAME_SCHEDULED_TIME = "scheduledTime";
        public static final String COLUMN_NAME_TOKEN = "token";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String TABLE_NAME = "alertsTable";
    }

    private AlertsContract() {
    }
}
