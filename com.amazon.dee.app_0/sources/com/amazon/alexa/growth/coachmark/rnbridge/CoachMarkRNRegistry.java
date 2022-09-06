package com.amazon.alexa.growth.coachmark.rnbridge;

import androidx.annotation.NonNull;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes8.dex */
public final class CoachMarkRNRegistry {

    /* loaded from: classes8.dex */
    enum CoachMarkRNID {
        COACH_MARK_CONTACT_BUTTON("coach_mark_contact_button"),
        COACH_MARK_DEVICE_SETTINGS_BUTTON("coach_mark_device_settings_button"),
        COACH_MARK_ADD_DEVICE_BUTTON("coach_mark_add_device_button"),
        COACH_MARK_FOLLOW_UP_REMINDERS_INFO("coach_mark_follow_up_reminders_info"),
        COACH_MARK_FOLLOW_UP_REMINDERS_INFO_GLOBAL_SETTING("coach_mark_follow_up_reminders_info_global_setting"),
        COACH_MARK_COMMS_HISTORY_VIEW_BUTTON("coach_mark_comms_history_open_button"),
        COACH_MARK_DEVICES_SETTING_SYNC("coach_mark_devices_setting_sync");
        
        private static final Set<String> coachMarkIdsInString = new HashSet();
        private final String toString;

        static {
            for (CoachMarkRNID coachMarkRNID : values()) {
                coachMarkIdsInString.add(coachMarkRNID.toString);
            }
        }

        CoachMarkRNID(String str) {
            this.toString = str;
        }

        @Override // java.lang.Enum
        @NonNull
        public String toString() {
            return this.toString;
        }
    }

    private CoachMarkRNRegistry() {
    }

    public static boolean contains(String str) {
        return CoachMarkRNID.coachMarkIdsInString.contains(str);
    }
}
