package com.amazon.alexa.voice.ui.onedesign.weather;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.speech.SpeechControlView;
import com.amazon.alexa.voice.ui.onedesign.util.AndroidResources;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.onedesign.util.HeaderUtils;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout;
import com.amazon.alexa.voice.ui.onedesign.widget.background.CardSolidColorLayout;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public final class WeatherController extends ViewController implements WeatherViewContract {
    private static final String ALWAYS_SHOW_FULLSCREEN = "alwaysShowFullScreen";
    private static final String EXTRA_CARD = "card";
    private static final String SHOW_VOICE_INGRESS = "showVoiceIngress";
    private static final int TRANSPARENCY = 25;
    private TextView alertDescriptionView;
    private ImageView alertImageView;
    private CardSolidColorLayout cardLayout;
    private TextView currentView;
    private LinearLayout dailyForecastContainer;
    private TextView degreeSignView;
    private ViewGroup highAndLowContainer;
    private TextView highView;
    private ViewGroup hourForecastContainer;
    private ImageView imageView;
    private TextView lowView;
    private WeatherPresenter presenter;
    private Resources resources;
    private SpeechControlView speechControlView;
    private TextView subTitleView;
    private TextView titleView;
    private View voiceIngressPadding;
    private TextView weatherDataProvider;
    private TextView weatherDescriptionView;

    public static WeatherController create(WeatherCard weatherCard) {
        return create(weatherCard, true, false);
    }

    private void setRootViewBackground(@ColorRes int i) {
        this.cardLayout.setBackgroundColor(ContextCompat.getColor(getContext(), i));
    }

    private boolean shouldHideHighAndLowContainer(WeatherTodayModel weatherTodayModel) {
        return TextUtils.isEmpty(weatherTodayModel.getHighTemperature()) || TextUtils.isEmpty(weatherTodayModel.getLowTemperature());
    }

    private void updateAlertView(WeatherTodayModel weatherTodayModel) {
        int i;
        if (!TextUtils.isEmpty(weatherTodayModel.getCurrentWeatherAlert())) {
            this.weatherDescriptionView.setVisibility(8);
            this.alertImageView.setVisibility(0);
            this.alertDescriptionView.setVisibility(0);
            this.alertDescriptionView.setText(weatherTodayModel.getCurrentWeatherAlert());
            if (WeatherIcon.isIconForNight(WeatherIcon.of(weatherTodayModel.getIconId()))) {
                i = R.color.voice_ui_od_alert_dark_background;
            } else {
                i = R.color.voice_ui_od_alert_light_background;
            }
            this.alertImageView.setColorFilter(ContextCompat.getColor(getContext(), i));
            return;
        }
        this.weatherDescriptionView.setText(weatherTodayModel.getCurrentWeatherDescription());
    }

    public /* synthetic */ void lambda$onCreateView$0$WeatherController(View view) {
        this.presenter.closeClicked();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onAttach(@NonNull View view) {
        this.presenter.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onCreate() {
        super.onCreate();
        Bundle arguments = getArguments();
        WeatherCard weatherCard = arguments != null ? (WeatherCard) arguments.getParcelable("card") : null;
        if (weatherCard != null) {
            boolean z = true;
            if (arguments != null) {
                z = arguments.getBoolean(SHOW_VOICE_INGRESS, true);
            }
            boolean z2 = arguments != null ? arguments.getBoolean(ALWAYS_SHOW_FULLSCREEN) : false;
            this.resources = new AndroidResources(getContext(), ComponentUtils.getLocale(getComponent()));
            this.presenter = new WeatherPresenter(this, new WeatherInteractor(weatherCard, new WeatherMediator(this), z, z2), (CardMetricsInteractor) getComponent().get(CardMetricsInteractor.class));
            if (WeatherIcon.isIconForNight(WeatherIcon.of(weatherCard.getIcon()))) {
                overrideTheme(R.style.Theme_Alexa_Voice_OneDesign_Content_Dark);
                return;
            } else {
                overrideTheme(R.style.Theme_Alexa_Voice_OneDesign_Content_Light);
                return;
            }
        }
        throw new IllegalStateException("Use WeatherController.create(WeatherCard) to create a controller");
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.voice_ui_od_weather_card, viewGroup, false);
        this.speechControlView = (SpeechControlView) inflate.findViewById(R.id.control);
        this.cardLayout = (CardSolidColorLayout) inflate.findViewById(R.id.cardLayout);
        ViewUtils.addStatusBarHeightAsTopPadding(this.cardLayout, this.speechControlView);
        this.titleView = (TextView) inflate.findViewById(R.id.title);
        this.subTitleView = (TextView) inflate.findViewById(R.id.subTitle);
        HeaderUtils.applyFontStyles(this.titleView, this.subTitleView);
        inflate.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.weather.-$$Lambda$WeatherController$nyVbwZgHqz4WPV9lBXU51kvVZ1w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeatherController.this.lambda$onCreateView$0$WeatherController(view);
            }
        });
        this.imageView = (ImageView) inflate.findViewById(R.id.image);
        this.currentView = (TextView) inflate.findViewById(R.id.currentTemperatureNumber);
        this.degreeSignView = (TextView) inflate.findViewById(R.id.degreeSign);
        this.lowView = (TextView) inflate.findViewById(R.id.low);
        this.highView = (TextView) inflate.findViewById(R.id.high);
        this.weatherDescriptionView = (TextView) inflate.findViewById(R.id.weatherDescription);
        this.weatherDataProvider = (TextView) inflate.findViewById(R.id.weatherDataProvider);
        this.alertImageView = (ImageView) inflate.findViewById(R.id.alertImage);
        this.alertDescriptionView = (TextView) inflate.findViewById(R.id.alertDescription);
        this.highAndLowContainer = (ViewGroup) inflate.findViewById(R.id.highAndLowContainer);
        this.hourForecastContainer = (ViewGroup) inflate.findViewById(R.id.hourWeatherContainer);
        this.dailyForecastContainer = (LinearLayout) inflate.findViewById(R.id.forecastContent);
        FontUtils.apply(Font.AMAZON_EMBER_BOLD, this.currentView, this.degreeSignView, this.highView);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.alertDescriptionView, this.weatherDescriptionView, this.weatherDataProvider);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.alertDescriptionView, this.weatherDescriptionView, this.weatherDataProvider);
        FontUtils.apply(Font.AMAZON_EMBER_LIGHT, this.lowView);
        this.speechControlView.setComponent(getComponent());
        this.voiceIngressPadding = inflate.findViewById(R.id.vi_padding);
        ((ContentLayout) inflate.findViewById(R.id.layout)).setOnContentListener(new ContentLayout.SimpleOnContentListener() { // from class: com.amazon.alexa.voice.ui.onedesign.weather.WeatherController.1
            @Override // com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.SimpleOnContentListener, com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.OnContentListener
            public void onDismiss() {
                WeatherController.this.presenter.dismiss();
            }
        });
        ((NestedScrollView) inflate.findViewById(R.id.scrollView)).setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() { // from class: com.amazon.alexa.voice.ui.onedesign.weather.WeatherController.2
            @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                WeatherController.this.presenter.interacted();
            }
        });
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroyView(@NonNull View view) {
        this.presenter.viewDestroyed();
        this.titleView = null;
        this.subTitleView = null;
        this.imageView = null;
        this.currentView = null;
        this.degreeSignView = null;
        this.lowView = null;
        this.highView = null;
        this.weatherDescriptionView = null;
        this.weatherDataProvider = null;
        this.alertImageView = null;
        this.alertDescriptionView = null;
        this.cardLayout = null;
        this.highAndLowContainer = null;
        this.hourForecastContainer = null;
        this.dailyForecastContainer = null;
    }

    @Override // com.amazon.regulator.ViewController
    public void onDetach(@NonNull View view) {
        this.presenter.end();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherViewContract
    public void setBackground(int i) {
        int i2;
        int i3;
        WeatherIcon of = WeatherIcon.of(i);
        if (WeatherIcon.isIconForNight(of)) {
            i2 = R.drawable.ic_voice_ui_od_dark_divider;
        } else {
            i2 = R.drawable.ic_voice_ui_od_light_divider;
        }
        Drawable drawable = ContextCompat.getDrawable(getContext(), i2);
        drawable.setAlpha(25);
        this.dailyForecastContainer.setDividerDrawable(drawable);
        this.dailyForecastContainer.setShowDividers(2);
        if (WeatherIcon.isIconForSunny(of)) {
            i3 = R.color.voice_ui_od_sunny_background;
        } else if (WeatherIcon.isIconForNight(of)) {
            i3 = R.color.voice_ui_od_night_background;
        } else {
            i3 = R.color.voice_ui_od_other_background;
        }
        setRootViewBackground(i3);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherViewContract
    public void setTitleAndSubTitle(CharSequence charSequence, CharSequence charSequence2) {
        this.titleView.setText(charSequence);
        ViewUtils.setTextOrRemove(this.subTitleView, charSequence2);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherViewContract
    public void setVoiceIngressVisibility(int i) {
        this.speechControlView.setVisibility(i);
        this.voiceIngressPadding.setVisibility(i);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherViewContract
    public void setWeatherTodayModel(WeatherTodayModel weatherTodayModel) {
        this.imageView.setImageResource(WeatherIcon.of(weatherTodayModel.getIconId()).getIconLargeId());
        this.imageView.setContentDescription(weatherTodayModel.getCurrentWeatherDescription());
        CharSequence currentTemperature = weatherTodayModel.getCurrentTemperature();
        CharSequence subSequence = weatherTodayModel.getCurrentTemperature().subSequence(0, currentTemperature.length() - 1);
        CharSequence subSequence2 = currentTemperature.subSequence(currentTemperature.length() - 1, currentTemperature.length());
        this.currentView.setText(subSequence);
        this.currentView.setContentDescription(this.resources.getString(R.string.voice_ui_od_weather_current_temperature, subSequence));
        this.degreeSignView.setText(subSequence2);
        updateAlertView(weatherTodayModel);
        ViewUtils.setTextOrRemove(this.weatherDataProvider, weatherTodayModel.getWeatherDataProvider());
        if (shouldHideHighAndLowContainer(weatherTodayModel)) {
            this.highAndLowContainer.setVisibility(8);
            return;
        }
        this.lowView.setText(weatherTodayModel.getLowTemperature());
        this.lowView.setContentDescription(this.resources.getString(R.string.voice_ui_od_weather_low_temperature, weatherTodayModel.getLowTemperature()));
        this.highView.setText(weatherTodayModel.getHighTemperature());
        this.highView.setContentDescription(this.resources.getString(R.string.voice_ui_od_weather_high_temperature, weatherTodayModel.getHighTemperature()));
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherViewContract
    public void showFullScreen() {
        this.cardLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.cardLayout.setRoundedCornerRadius(0);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherViewContract
    public void updateDailyForecast(WeatherForecastModel weatherForecastModel) {
        View inflate = LayoutInflater.from(getView().getContext()).inflate(R.layout.voice_ui_od_weather_daily_forecast_item, (ViewGroup) this.dailyForecastContainer, false);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.image);
        TextView textView = (TextView) inflate.findViewById(R.id.low);
        TextView textView2 = (TextView) inflate.findViewById(R.id.high);
        TextView textView3 = (TextView) inflate.findViewById(R.id.date);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, textView3);
        FontUtils.apply(Font.AMAZON_EMBER_BOLD, textView2);
        FontUtils.apply(Font.AMAZON_EMBER_LIGHT, textView);
        WeatherIcon of = WeatherIcon.of(weatherForecastModel.getIconId());
        if (of == WeatherIcon.SUNNY) {
            imageView.setColorFilter(ContextCompat.getColor(getContext(), R.color.voice_ui_od_sun_fill_color));
        }
        imageView.setImageResource(of.getIconSmallId());
        textView.setText(weatherForecastModel.getLowTemperature());
        textView.setContentDescription(this.resources.getString(R.string.voice_ui_od_weather_low_temperature, weatherForecastModel.getLowTemperature()));
        textView2.setText(weatherForecastModel.getHighTemperature());
        textView2.setContentDescription(this.resources.getString(R.string.voice_ui_od_weather_high_temperature, weatherForecastModel.getHighTemperature()));
        textView3.setText(weatherForecastModel.getDate());
        this.dailyForecastContainer.addView(inflate);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.weather.WeatherViewContract
    public void updateHourlyForecast(HourlyForecastModel hourlyForecastModel) {
        View inflate = LayoutInflater.from(getView().getContext()).inflate(R.layout.voice_ui_od_weather_hourly_forecast_item, this.hourForecastContainer, false);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.hourlyForecastImage);
        TextView textView = (TextView) inflate.findViewById(R.id.hourlyTempreture);
        TextView textView2 = (TextView) inflate.findViewById(R.id.hourlyDescription);
        FontUtils.apply(Font.AMAZON_EMBER_BOLD, textView);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, textView2);
        WeatherIcon of = WeatherIcon.of(hourlyForecastModel.getIconId());
        if (of == WeatherIcon.SUNNY) {
            imageView.setColorFilter(ContextCompat.getColor(getContext(), R.color.voice_ui_od_sun_fill_color));
        }
        imageView.setImageResource(of.getIconSmallId());
        textView.setText(hourlyForecastModel.getHourlyTemperature());
        textView2.setText(hourlyForecastModel.getHourlyDescriptiveText());
        this.hourForecastContainer.addView(inflate);
    }

    public static WeatherController create(WeatherCard weatherCard, boolean z, boolean z2) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("card", weatherCard);
        bundle.putBoolean(SHOW_VOICE_INGRESS, z);
        bundle.putBoolean(ALWAYS_SHOW_FULLSCREEN, z2);
        WeatherController weatherController = new WeatherController();
        weatherController.setArguments(bundle);
        return weatherController;
    }
}
