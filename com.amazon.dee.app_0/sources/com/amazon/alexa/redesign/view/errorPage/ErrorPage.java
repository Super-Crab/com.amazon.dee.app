package com.amazon.alexa.redesign.view.errorPage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.redesign.HomeContract;
import com.amazon.alexa.redesign.R;
/* loaded from: classes10.dex */
public class ErrorPage extends LinearLayout implements ErrorPageApi {
    TextView retry;
    View.OnClickListener retryClicked;
    @VisibleForTesting
    View rootView;
    HomeContract.View view;

    public ErrorPage(Context context) {
        super(context);
        this.retryClicked = new View.OnClickListener() { // from class: com.amazon.alexa.redesign.view.errorPage.ErrorPage.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HomeContract.View view2 = ErrorPage.this.view;
                if (view2 != null) {
                    view2.onErrorPageRetryClicked();
                }
            }
        };
        init(context);
    }

    private void init(Context context) {
        this.rootView = LinearLayout.inflate(context, R.layout.amahc_error_page, this);
        this.rootView.setOnClickListener(null);
        this.retry = (TextView) this.rootView.findViewById(R.id.error_page_subtitle);
        this.retry.setOnClickListener(this.retryClicked);
    }

    @Override // com.amazon.alexa.redesign.view.errorPage.ErrorPageApi
    public void setHomeView(HomeContract.View view) {
        this.view = view;
    }

    @Override // com.amazon.alexa.redesign.view.errorPage.ErrorPageApi
    public void setVisibility(boolean z) {
        this.rootView.setVisibility(z ? 0 : 8);
    }

    public ErrorPage(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.retryClicked = new View.OnClickListener() { // from class: com.amazon.alexa.redesign.view.errorPage.ErrorPage.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HomeContract.View view2 = ErrorPage.this.view;
                if (view2 != null) {
                    view2.onErrorPageRetryClicked();
                }
            }
        };
        init(context);
    }
}
