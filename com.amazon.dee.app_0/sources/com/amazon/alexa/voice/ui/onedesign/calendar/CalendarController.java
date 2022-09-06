package com.amazon.alexa.voice.ui.onedesign.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.calendar.CalendarAdapter;
import com.amazon.alexa.voice.ui.onedesign.calendar.CalendarContract;
import com.amazon.alexa.voice.ui.onedesign.speech.SpeechControlView;
import com.amazon.alexa.voice.ui.onedesign.util.AndroidResources;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.util.HeaderUtils;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.Component;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public final class CalendarController extends ViewController implements CalendarContract.View {
    private static final String EXTRA_CARD = "card";
    private CalendarAdapter contentAdapter;
    private CalendarContract.Presenter presenter;
    private TextView titleView;

    public static CalendarController create(CalendarCard calendarCard) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("card", calendarCard);
        CalendarController calendarController = new CalendarController();
        calendarController.setArguments(bundle);
        return calendarController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarContract.View
    public void add(Object obj) {
        this.contentAdapter.add(obj);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarContract.View
    public void floodBackgroundToStatusBar() {
    }

    public /* synthetic */ void lambda$onCreateView$0$CalendarController(View view) {
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
        overrideTheme(R.style.Theme_Alexa_Voice_OneDesign_Content_Dark);
        CalendarCard calendarCard = (CalendarCard) getArguments().getParcelable("card");
        if (calendarCard != null) {
            CalendarInteractor calendarInteractor = new CalendarInteractor(calendarCard, new CalendarMediator(this));
            Component component = getComponent();
            this.presenter = new CalendarPresenter(this, calendarInteractor, (CardMetricsInteractor) component.get(CardMetricsInteractor.class), ComponentUtils.getLocale(component));
            return;
        }
        throw new IllegalStateException("Use CalendarController.create(CalendarCard) to create a controller");
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.voice_ui_od_calendar, viewGroup, false);
        View findViewById = inflate.findViewById(R.id.cardLayout);
        SpeechControlView speechControlView = (SpeechControlView) inflate.findViewById(R.id.control);
        ViewUtils.addStatusBarHeightAsTopPadding(findViewById, speechControlView);
        ((ContentLayout) inflate.findViewById(R.id.layout)).setOnContentListener(new ContentLayout.SimpleOnContentListener() { // from class: com.amazon.alexa.voice.ui.onedesign.calendar.CalendarController.1
            @Override // com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.SimpleOnContentListener, com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.OnContentListener
            public void onDismiss() {
                CalendarController.this.presenter.dismiss();
            }
        });
        Component component = getComponent();
        speechControlView.setComponent(component);
        AndroidResources androidResources = new AndroidResources(getContext(), ComponentUtils.getLocale(component));
        final CalendarContract.Presenter presenter = this.presenter;
        presenter.getClass();
        this.contentAdapter = new CalendarAdapter(androidResources, new CalendarAdapter.OnEventClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.calendar.-$$Lambda$a6NfNX8G-7EFx7Na36QYO9aHRNc
            @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarAdapter.OnEventClickListener
            public final void onEventClicked(CalendarEventModel calendarEventModel) {
                CalendarContract.Presenter.this.eventClicked(calendarEventModel);
            }
        });
        this.titleView = (TextView) inflate.findViewById(R.id.title);
        HeaderUtils.applyFontStyles(this.titleView);
        ((ImageView) inflate.findViewById(R.id.close)).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.calendar.-$$Lambda$CalendarController$K_8-jkKTSk2AtxomJVbRjKwSYvM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CalendarController.this.lambda$onCreateView$0$CalendarController(view);
            }
        });
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.content);
        recyclerView.setAdapter(this.contentAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.alexa.voice.ui.onedesign.calendar.CalendarController.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int i) {
                CalendarController.this.presenter.interacted();
            }
        });
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroyView(@NonNull View view) {
        this.presenter.viewDestroyed();
        this.contentAdapter = null;
        this.titleView = null;
    }

    @Override // com.amazon.regulator.ViewController
    public void onDetach(@NonNull View view) {
        this.presenter.end();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.calendar.CalendarContract.View
    public void setTitle(@Nullable CharSequence charSequence) {
        ViewUtils.setTextOrRemove(this.titleView, charSequence);
    }
}
