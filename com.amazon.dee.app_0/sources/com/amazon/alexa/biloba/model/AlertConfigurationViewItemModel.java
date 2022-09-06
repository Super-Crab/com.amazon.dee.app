package com.amazon.alexa.biloba.model;

import androidx.annotation.NonNull;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Objects;
/* loaded from: classes6.dex */
public class AlertConfigurationViewItemModel {
    private static final String DISABLED_STRING = "disabled";
    private static final String ENABLED_STRING = "enabled";
    private static final int NOT_SET = -1;
    private static final String TAG = "AlertConfigurationViewItemModel";
    private AlertCondition condition;
    private String description;
    private DeviceInfo device;
    private int endTimeHours;
    private int endTimeMinutes;
    private String id;
    boolean isEnabled;
    private int startTimeHours;
    private int startTimeMinutes;
    private String title;

    public AlertConfigurationViewItemModel(String str) {
        this.id = str;
        this.condition = null;
        this.device = null;
        this.isEnabled = false;
        this.startTimeHours = -1;
        this.startTimeMinutes = -1;
        this.endTimeHours = -1;
        this.endTimeMinutes = -1;
    }

    private static String getDisplayTime(int i, int i2, @NonNull DateFormat dateFormat, String str, String str2) {
        String format;
        if (i == -1 || i2 == -1) {
            return null;
        }
        Calendar gregorianCalendar = GregorianCalendar.getInstance();
        gregorianCalendar.set(11, i);
        gregorianCalendar.set(12, i2);
        if (i == 0 && i2 == 0 && str2 != null) {
            format = String.format(str2, dateFormat.format(gregorianCalendar.getTime()));
        } else {
            format = (i == 12 && i2 == 0 && str != null) ? String.format(str, dateFormat.format(gregorianCalendar.getTime())) : dateFormat.format(gregorianCalendar.getTime());
        }
        String str3 = TAG;
        StringBuilder outline110 = GeneratedOutlineSupport1.outline110("display time for ", i, " hours and ", i2, " minutes --> ");
        outline110.append(format);
        LogUtils.v(str3, outline110.toString());
        return format;
    }

    private static String hoursAndMinutesToStorageFormat(int i, int i2) {
        if (i == -1 || i2 == -1) {
            return null;
        }
        return String.format("%02d%02d", Integer.valueOf(i), Integer.valueOf(i2));
    }

    private static Calendar parseStartTimeFromStorageFormat(String str) {
        if (str == null) {
            return null;
        }
        try {
            Date parse = new SimpleDateFormat("HHmm", Locale.US).parse(str);
            if (parse == null) {
                LogUtils.e(TAG, "Could not parse time from null date");
                return null;
            }
            Calendar gregorianCalendar = GregorianCalendar.getInstance();
            gregorianCalendar.setTime(parse);
            return gregorianCalendar;
        } catch (ParseException e) {
            LogUtils.e(TAG, e.getLocalizedMessage());
            return null;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AlertConfigurationViewItemModel.class != obj.getClass()) {
            return false;
        }
        AlertConfigurationViewItemModel alertConfigurationViewItemModel = (AlertConfigurationViewItemModel) obj;
        return Objects.equals(this.id, alertConfigurationViewItemModel.id) && Objects.equals(Integer.valueOf(this.startTimeHours), Integer.valueOf(alertConfigurationViewItemModel.startTimeHours)) && Objects.equals(Integer.valueOf(this.startTimeMinutes), Integer.valueOf(alertConfigurationViewItemModel.startTimeMinutes)) && Objects.equals(Integer.valueOf(this.endTimeHours), Integer.valueOf(alertConfigurationViewItemModel.endTimeHours)) && Objects.equals(Integer.valueOf(this.endTimeMinutes), Integer.valueOf(alertConfigurationViewItemModel.endTimeMinutes)) && Objects.equals(Boolean.valueOf(this.isEnabled), Boolean.valueOf(alertConfigurationViewItemModel.isEnabled)) && Objects.equals(this.device, alertConfigurationViewItemModel.device) && Objects.equals(this.condition, alertConfigurationViewItemModel.condition);
    }

    public AlertCondition getAlertCondition() {
        return this.condition;
    }

    public String getAlertConfigurationEnabledInString() {
        return this.isEnabled ? "enabled" : "disabled";
    }

    public int getAlertConfigurationType() {
        return this.condition.getConfigurationType();
    }

    public String getAlertConfigurationTypeInString() {
        return getCondition();
    }

    public String getCondition() {
        AlertCondition alertCondition = this.condition;
        if (alertCondition == null) {
            return null;
        }
        return alertCondition.getCondition();
    }

    public String getDescription() {
        return this.description;
    }

    public DeviceInfo getDevice() {
        return this.device;
    }

    public String getDeviceName() {
        DeviceInfo deviceInfo = this.device;
        if (deviceInfo == null) {
            return null;
        }
        return deviceInfo.getDeviceName();
    }

    public String getDisplayEndTime(@NonNull DateFormat dateFormat, String str, String str2) {
        return getDisplayTime(this.endTimeHours, this.endTimeMinutes, dateFormat, str, str2);
    }

    public String getDisplayStartTime(@NonNull DateFormat dateFormat, String str, String str2) {
        return getDisplayTime(this.startTimeHours, this.startTimeMinutes, dateFormat, str, str2);
    }

    public String getEndTime() {
        return hoursAndMinutesToStorageFormat(this.endTimeHours, this.endTimeMinutes);
    }

    public int getEndTimeHours() {
        return this.endTimeHours;
    }

    public int getEndTimeMinutes() {
        return this.endTimeMinutes;
    }

    public String getId() {
        return this.id;
    }

    public String getStartTime() {
        return hoursAndMinutesToStorageFormat(this.startTimeHours, this.startTimeMinutes);
    }

    public int getStartTimeHours() {
        return this.startTimeHours;
    }

    public int getStartTimeMinutes() {
        return this.startTimeMinutes;
    }

    public String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return Objects.hash(this.id, Integer.valueOf(this.startTimeHours), Integer.valueOf(this.startTimeMinutes), Integer.valueOf(this.endTimeHours), Integer.valueOf(this.endTimeMinutes), this.id, this.device, this.condition);
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setCondition(AlertCondition alertCondition) {
        this.condition = alertCondition;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDevice(DeviceInfo deviceInfo) {
        this.device = deviceInfo;
    }

    public void setEnabled(boolean z) {
        this.isEnabled = z;
    }

    public void setEndTime(int i, int i2) {
        this.endTimeHours = i;
        this.endTimeMinutes = i2;
    }

    public void setStartTime(int i, int i2) {
        this.startTimeHours = i;
        this.startTimeMinutes = i2;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String toString() {
        return String.format("ID: %s, device: %s, isEnabled: %s, type: %s", getId(), getDeviceName(), Boolean.valueOf(this.isEnabled), getAlertConfigurationTypeInString());
    }

    private void setEndTime(Calendar calendar) {
        if (calendar == null) {
            this.endTimeHours = -1;
            this.endTimeMinutes = -1;
            return;
        }
        this.endTimeHours = calendar.get(11);
        this.endTimeMinutes = calendar.get(12);
    }

    private void setStartTime(Calendar calendar) {
        if (calendar == null) {
            this.startTimeHours = -1;
            this.startTimeMinutes = -1;
            return;
        }
        this.startTimeHours = calendar.get(11);
        this.startTimeMinutes = calendar.get(12);
    }

    public AlertConfigurationViewItemModel(String str, AlertCondition alertCondition, DeviceInfo deviceInfo, String str2, String str3, boolean z) {
        this.id = str;
        this.title = this.title;
        this.description = this.description;
        this.condition = alertCondition;
        this.device = deviceInfo;
        this.isEnabled = z;
        setStartTime(parseStartTimeFromStorageFormat(str2));
        setEndTime(parseStartTimeFromStorageFormat(str3));
    }
}
