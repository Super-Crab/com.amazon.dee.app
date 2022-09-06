package com.amazon.alexa.drive.landing;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.mode.util.Fonts;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes7.dex */
public class LandingPageVoiceHintHelper {
    private static final String TAG = "LandingPageVoiceHintHelper";
    private int voiceHintIndex;
    private final List<String> voiceHintList;

    public LandingPageVoiceHintHelper(Context context) {
        this.voiceHintList = Arrays.asList(context.getString(R.string.dm_vui_hint_find_gas_station), context.getString(R.string.dm_vui_hint_play_flash_briefing), context.getString(R.string.dm_vui_hint_whats_news), context.getString(R.string.dm_vui_hint_play_games), context.getString(R.string.dm_vui_hint_add_milk_to_shopping_list), context.getString(R.string.dm_vui_hint_play_road_trip_music));
    }

    public void displayVoiceHint(View view) {
        Log.i(TAG, "Display voice hint on landing page.");
        TextView textView = (TextView) view.findViewById(R.id.dm_vui_landing_page_hint);
        textView.setText(this.voiceHintList.get(this.voiceHintIndex));
        textView.setContentDescription(view.getContext().getResources().getString(R.string.entertainment_page_default_title) + this.voiceHintList.get(this.voiceHintIndex));
        Fonts.EMBER_BOLD_ITALIC.apply(textView);
    }

    public void displayVoiceHintV2(View view) {
        Log.i(TAG, "Display voice hint on landing page.");
        ((TextView) view.findViewById(R.id.auto_mode_hint)).setText(this.voiceHintList.get(this.voiceHintIndex));
    }

    @VisibleForTesting
    List<String> getVoiceHintList() {
        return this.voiceHintList;
    }

    public void onVoiceHintSwitch() {
        this.voiceHintIndex = (this.voiceHintIndex + 1) % this.voiceHintList.size();
    }
}
