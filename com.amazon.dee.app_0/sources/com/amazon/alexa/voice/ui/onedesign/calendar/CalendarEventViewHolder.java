package com.amazon.alexa.voice.ui.onedesign.calendar;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.cards.util.StringUtils;
import com.amazon.alexa.voice.ui.onedesign.calendar.CalendarAdapter;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.amazon.alexa.voice.ui.onedesign.widget.BindableViewHolder;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public final class CalendarEventViewHolder extends BindableViewHolder<CalendarEventModel> {
    private static final String ALEXA_PROVIDER = "Alexa";
    private static final String MOMENTO_PROVIDER = "Momento";
    private final TextView mCalendarSourceView;
    private final TextView mDayOfMonthView;
    private final TextView mDayOfWeekView;
    private final TextView mLocationView;
    private CalendarEventModel mModel;
    private final TextView mMonthView;
    private final TextView mNameView;
    private final Resources mResources;
    private final TextView mTimesView;

    public CalendarEventViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, final CalendarAdapter.OnEventClickListener onEventClickListener, Resources resources) {
        super(layoutInflater.inflate(R.layout.voice_ui_od_calendar_event, viewGroup, false));
        this.mResources = resources;
        this.mMonthView = (TextView) this.itemView.findViewById(R.id.month);
        this.mDayOfWeekView = (TextView) this.itemView.findViewById(R.id.dayOfWeek);
        this.mDayOfMonthView = (TextView) this.itemView.findViewById(R.id.dayOfMonth);
        this.mNameView = (TextView) this.itemView.findViewById(R.id.name);
        this.mLocationView = (TextView) this.itemView.findViewById(R.id.location);
        this.mTimesView = (TextView) this.itemView.findViewById(R.id.times);
        this.mCalendarSourceView = (TextView) this.itemView.findViewById(R.id.calendarSource);
        FontUtils.apply(Font.AMAZON_EMBER_BOLD, this.mMonthView, this.mDayOfWeekView, this.mNameView);
        FontUtils.apply(Font.AMAZON_EMBER_DISPLAY_BOLD, this.mDayOfMonthView);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.mTimesView, this.mLocationView, this.mCalendarSourceView);
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.calendar.-$$Lambda$CalendarEventViewHolder$4-G2OdXVSWzltYCYkLcncni58ds
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CalendarEventViewHolder.this.lambda$new$0$CalendarEventViewHolder(onEventClickListener, view);
            }
        });
    }

    private String getCalendarSource(CalendarEventModel calendarEventModel) {
        boolean isBlank = StringUtils.isBlank(calendarEventModel.getId());
        if (!StringUtils.isBlank(calendarEventModel.getProvider())) {
            if (isBlank) {
                String charSequence = calendarEventModel.getProvider().toString();
                if (MOMENTO_PROVIDER.equalsIgnoreCase(charSequence) || "Alexa".equalsIgnoreCase(charSequence)) {
                    return this.mResources.getString(R.string.voice_ui_od_alexa_calendar);
                }
            } else {
                return this.mResources.getString(R.string.voice_ui_od_calendar_provider, String.format("%s's %s", calendarEventModel.getId(), calendarEventModel.getProvider()));
            }
        }
        return this.mResources.getString(R.string.voice_ui_od_no_provider_calendar);
    }

    private static void setTextAndVisibility(TextView textView, CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(8);
            return;
        }
        textView.setVisibility(0);
        textView.setText(charSequence);
    }

    public /* synthetic */ void lambda$new$0$CalendarEventViewHolder(CalendarAdapter.OnEventClickListener onEventClickListener, View view) {
        onEventClickListener.onEventClicked(this.mModel);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.BindableViewHolder
    public void bind(CalendarEventModel calendarEventModel) {
        this.mModel = calendarEventModel;
        setTextAndVisibility(this.mMonthView, calendarEventModel.getMonth());
        setTextAndVisibility(this.mDayOfWeekView, calendarEventModel.getDayOfWeek());
        setTextAndVisibility(this.mDayOfMonthView, calendarEventModel.getDayOfMonth());
        this.mNameView.setText(calendarEventModel.getName());
        setTextAndVisibility(this.mLocationView, calendarEventModel.getLocation());
        setTextAndVisibility(this.mTimesView, calendarEventModel.getTimes());
        setTextAndVisibility(this.mCalendarSourceView, getCalendarSource(calendarEventModel));
    }
}
