package com.amazon.alexa.biloba.model;

import android.content.res.Resources;
import com.amazon.alexa.biloba.R;
import java.util.Objects;
/* loaded from: classes6.dex */
public class AlertCondition {
    private final String condition;
    private int configurationType;
    private final String id;
    private boolean selected;

    public AlertCondition(String str, String str2, boolean z) {
        this.id = str;
        this.condition = str2;
        this.configurationType = -1;
        this.selected = z;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static int getAlertConfigurationTypeFromString(String str) {
        boolean z;
        if (str == null) {
            return -1;
        }
        switch (str.hashCode()) {
            case -1850772531:
                if (str.equals("NO_ACTIVITY")) {
                    z = false;
                    break;
                }
                z = true;
                break;
            case -1109522855:
                if (str.equals("SMART_HOME_ACTIVITY")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 21649588:
                if (str.equals("PAUSE_NOTIFICATION")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 198088487:
                if (str.equals("FIRST_ACTIVITY_OF_THE_DAY")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 279501858:
                if (str.equals("ANY_ACTIVITY")) {
                    z = true;
                    break;
                }
                z = true;
                break;
            default:
                z = true;
                break;
        }
        if (!z) {
            return 0;
        }
        if (z) {
            return 1;
        }
        if (z) {
            return 2;
        }
        if (z) {
            return 3;
        }
        return !z ? -1 : 4;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AlertCondition.class != obj.getClass()) {
            return false;
        }
        AlertCondition alertCondition = (AlertCondition) obj;
        return Objects.equals(this.id, alertCondition.id) && Objects.equals(this.condition, alertCondition.condition) && Objects.equals(Integer.valueOf(this.configurationType), Integer.valueOf(alertCondition.configurationType)) && Objects.equals(Boolean.valueOf(this.selected), Boolean.valueOf(alertCondition.selected));
    }

    public String getAlertConfigurationTypeAsStringResource(Resources resources) {
        int i = this.configurationType;
        if (i != 0) {
            if (i == 1) {
                return resources.getString(R.string.alert_first_activity_of_the_day);
            }
            if (i == 2) {
                return resources.getString(R.string.alert_any_activity_detected);
            }
            if (i == 3) {
                return resources.getString(R.string.alert_smart_home_activity_detected);
            }
            if (i != 4) {
                return resources.getString(R.string.alert_unknown);
            }
            return resources.getString(R.string.alert_pause_notification);
        }
        return resources.getString(R.string.alert_no_activity_detected);
    }

    public String getAlertConfigurationTypeInString() {
        int i = this.configurationType;
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNKNOWN" : "PAUSE_NOTIFICATION" : "SMART_HOME_ACTIVITY" : "ANY_ACTIVITY" : "FIRST_ACTIVITY_OF_THE_DAY" : "NO_ACTIVITY";
    }

    public String getCondition() {
        String str = this.condition;
        return str == null ? getAlertConfigurationTypeInString() : str;
    }

    public int getConfigurationType() {
        return this.configurationType;
    }

    public String getId() {
        return this.id;
    }

    public int hashCode() {
        return Objects.hash(this.id, this.condition, Integer.valueOf(this.configurationType), Boolean.valueOf(this.selected));
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public AlertCondition(String str, int i, boolean z) {
        this.id = str;
        this.condition = null;
        this.configurationType = i;
        this.selected = z;
    }
}
