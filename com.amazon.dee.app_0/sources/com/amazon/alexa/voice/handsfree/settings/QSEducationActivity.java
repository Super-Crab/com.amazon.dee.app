package com.amazon.alexa.voice.handsfree.settings;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;
import com.amazon.alexa.handsfree.notification.views.quicksettings.QuickSettingsNotificationPresenter;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.alexa.handsfree.ui.utils.FontAdapter;
import com.amazon.alexa.voice.handsfree.R;
/* loaded from: classes11.dex */
public class QSEducationActivity extends AppCompatActivity {
    private Initializer mInitializer;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.mInitializer = InitializerProvider.getInitializer();
        this.mInitializer.initialize(this);
        setContentView(R.layout.qs_education_layout);
        setRequestedOrientation(1);
        QSEducationActivityPresenter qSEducationActivityPresenter = new QSEducationActivityPresenter(this);
        View findViewById = findViewById(R.id.qs_education_done_button);
        setupDoneButtonOnClickListener(findViewById, qSEducationActivityPresenter);
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmber_Bold, findViewById);
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmberDisplay_Bold, findViewById(R.id.qs_education_title_textView));
        FontAdapter.setFont(FontAdapter.FontName.AmazonEmber_Regular, findViewById(R.id.qs_education_instructions));
        LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.qs_education_animation);
        TextView textView = (TextView) findViewById(R.id.qs_education_title_textView);
        ViewStub viewStub = (ViewStub) findViewById(R.id.qs_education_instructions);
        boolean z = getIntent().getExtras().getBoolean(QuickSettingsNotificationPresenter.EXTRA_IS_QS_TILE_IN_MAIN_MENU);
        if (z) {
            lottieAnimationView.setAnimation("qs_main_menu_animation.json");
            textView.setText(R.string.qs_education_main_menu_title);
            viewStub.setLayoutResource(R.layout.qs_education_main_menu_instructions);
        } else {
            lottieAnimationView.setAnimation("qs_edit_menu_animation.json");
            textView.setText(R.string.qs_education_edit_menu_title);
            viewStub.setLayoutResource(R.layout.qs_education_edit_menu_instructions);
        }
        viewStub.inflate();
        lottieAnimationView.playAnimation();
        qSEducationActivityPresenter.onNotificationClick(getIntent());
        qSEducationActivityPresenter.onPageView(z);
    }

    @VisibleForTesting
    void setupDoneButtonOnClickListener(@NonNull View view, @NonNull final QSEducationActivityPresenter qSEducationActivityPresenter) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.handsfree.settings.QSEducationActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(@NonNull View view2) {
                qSEducationActivityPresenter.onDoneButtonClick();
                QSEducationActivity.this.finish();
            }
        });
    }
}
