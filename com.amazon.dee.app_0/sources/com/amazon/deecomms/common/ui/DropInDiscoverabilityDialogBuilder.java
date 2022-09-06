package com.amazon.deecomms.common.ui;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;
import com.amazon.deecomms.R;
/* loaded from: classes12.dex */
public class DropInDiscoverabilityDialogBuilder extends CustomDialogBuilder {
    public DropInDiscoverabilityDialogBuilder(@NonNull Activity activity) {
        super(activity);
        this.layoutResId = Integer.valueOf(R.layout.drop_in_discoverability_dialog);
    }

    @Override // com.amazon.deecomms.common.ui.CustomDialogBuilder
    public AppCompatDialog build() {
        disableTitleBar(true);
        withTitle(R.string.drop_in_discover_title);
        withBody(R.string.drop_in_discover_body);
        AppCompatDialog build = super.build();
        ((CommsTextView) build.findViewById(R.id.point_one)).setText(R.string.drop_in_discover_point_one);
        ((CommsTextView) build.findViewById(R.id.point_two)).setText(R.string.drop_in_discover_point_two);
        return build;
    }
}
