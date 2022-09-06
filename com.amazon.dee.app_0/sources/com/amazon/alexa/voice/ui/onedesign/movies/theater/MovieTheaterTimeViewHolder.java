package com.amazon.alexa.voice.ui.onedesign.movies.theater;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.onedesign.util.DateUtils;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.vox.ui.onedesign.R;
import java.text.ParseException;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class MovieTheaterTimeViewHolder extends MovieTheaterViewHolder<MovieTheaterTimeModel> {
    private final boolean is24HrFormat;
    private final Locale locale;
    private final TextView titleView;

    public MovieTheaterTimeViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, Locale locale, boolean z) {
        super(layoutInflater.inflate(R.layout.voice_ui_od_movie_theater_time, viewGroup, false));
        this.locale = locale;
        this.is24HrFormat = z;
        this.titleView = (TextView) this.itemView.findViewById(R.id.theaterTitle);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.titleView);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterViewHolder
    public void bind(MovieTheaterTimeModel movieTheaterTimeModel) {
        String charSequence = movieTheaterTimeModel.getTitle().toString();
        try {
            charSequence = DateUtils.convert24HrToTargetFormat(charSequence, this.locale, this.is24HrFormat);
        } catch (ParseException unused) {
        }
        this.titleView.setText(charSequence);
    }
}
