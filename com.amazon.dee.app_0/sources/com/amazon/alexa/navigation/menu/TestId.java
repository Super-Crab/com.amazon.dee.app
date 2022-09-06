package com.amazon.alexa.navigation.menu;
/* loaded from: classes9.dex */
enum TestId {
    ADD_DEVICE(R.id.more_add_device_icon, R.id.more_add_device_text, R.id.more_add_device),
    LISTS_AND_NOTES(R.id.more_lists_and_notes_icon, R.id.more_lists_and_notes_text, R.id.more_lists_and_notes),
    REMINDERS_AND_ALARMS(R.id.more_reminders_and_alarms_icon, R.id.more_reminders_and_alarms_text, R.id.more_reminders_and_alarms),
    ROUTINES(R.id.more_routines_icon, R.id.more_routines_text, R.id.more_routines),
    THINGS_TO_TRY(R.id.more_things_to_try_icon, R.id.more_things_to_try_text, R.id.more_things_to_try),
    SKILLS_AND_GAMES(R.id.more_skills_and_games_icon, R.id.more_skills_and_games_text, R.id.more_skills_and_games),
    BLUEPRINTS(R.id.more_blueprints_icon, R.id.more_blueprints_text, R.id.more_blueprints),
    SETTINGS(R.id.more_settings_icon, R.id.more_settings_text, R.id.more_settings),
    ACTIVITY(R.id.more_activity_icon, R.id.more_activity_text, R.id.more_activity),
    HELP_AND_FEEDBACK(R.id.more_help_and_feedback_icon, R.id.more_help_and_feedback_text, R.id.more_help_and_feedback),
    ALARMS_TIMERS(R.id.more_alarms_timers_icon, R.id.more_alarms_timers_text, R.id.more_alarms_timers),
    ALEXA_PRIVACY(R.id.more_alexa_privacy_icon, R.id.more_alexa_privacy_text, R.id.more_alexa_privacy),
    AUTOMOTIVE(R.id.more_automotive_icon, R.id.more_automotive_text, R.id.more_automotive),
    REMINDERS(R.id.more_reminders_icon, R.id.more_reminders_text, R.id.more_reminders),
    CARE_HUB(R.id.more_care_hub_icon, R.id.more_care_hub_text, R.id.more_care_hub),
    FIND_MY(R.id.more_find_my_icon, R.id.more_find_my_text, R.id.more_find_my),
    SEE_MORE(R.id.more_see_more_icon, R.id.more_see_more_text, R.id.more_see_more),
    WORKOUT(R.id.more_workout_icon, R.id.more_workout_text, R.id.more_workout),
    FSV2_SAMPLE_TEST(R.id.more_help_and_feedback_icon, R.id.more_fsv2_sample_test_text, R.id.more_fsv2_sample_test);
    
    final int iconTestId;
    final int itemTestId;
    final int textTestId;

    TestId(int i, int i2, int i3) {
        this.iconTestId = i;
        this.textTestId = i2;
        this.itemTestId = i3;
    }
}
