package com.amazon.alexa.redesign.actions;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.protocols.usermessage.UserMessageService;
import com.amazon.alexa.redesign.R;
import java.util.Map;
/* loaded from: classes10.dex */
public class DeepLinkAction extends Action {
    private final Context context;
    private final String playStoreName;
    private final String playStoreUrl;
    private final String url;
    private final UserMessageService userMessageService;

    public DeepLinkAction(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5, @NonNull UserMessageService userMessageService, @NonNull Context context) {
        super(ActionFactory.DEEPLINK_ACTION, str, str2);
        if (str != null) {
            this.url = str3;
            this.playStoreName = str5;
            this.playStoreUrl = str4;
            this.userMessageService = userMessageService;
            this.context = context;
            return;
        }
        throw new IllegalArgumentException("DeepLinkAction: metaActionType must not be null");
    }

    static /* synthetic */ void lambda$alertUserOfBadLink$1() {
    }

    @VisibleForTesting
    void alertUserOfBadLink() {
        this.userMessageService.message(String.format(this.context.getResources().getString(R.string.amahc_deep_link_app_not_installed), this.playStoreName)).withAction(R.string.amahc_yes, new Runnable() { // from class: com.amazon.alexa.redesign.actions.-$$Lambda$DeepLinkAction$-TBhhVbtErwpqsU7oyELZE7EfGQ
            @Override // java.lang.Runnable
            public final void run() {
                DeepLinkAction.this.lambda$alertUserOfBadLink$0$DeepLinkAction();
            }
        }).withCancel(R.string.amahc_no, $$Lambda$DeepLinkAction$OVKREbI1ak25QSJux4bgFCY31Y.INSTANCE).show();
    }

    @Override // com.amazon.alexa.redesign.actions.Action
    public void execute() {
        startDeepLink();
    }

    @Override // com.amazon.alexa.redesign.actions.Action
    public boolean getOnClickUserLeavesHome() {
        return true;
    }

    @VisibleForTesting
    Intent makeIntent(String str) {
        return new Intent("android.intent.action.VIEW", Uri.parse(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* renamed from: redirectUserToPlayStore */
    public void lambda$alertUserOfBadLink$0$DeepLinkAction() {
        Intent makeIntent = makeIntent(this.playStoreUrl);
        if (ActionUtils.isIntentSafe(makeIntent, this.context)) {
            makeIntent.setFlags(268435456);
            this.context.startActivity(makeIntent);
        }
    }

    @VisibleForTesting
    void startDeepLink() {
        Intent makeIntent = makeIntent(this.url);
        if (ActionUtils.isIntentSafe(makeIntent, this.context)) {
            makeIntent.setFlags(268435456);
            this.context.startActivity(makeIntent);
            return;
        }
        alertUserOfBadLink();
    }

    @Override // com.amazon.alexa.redesign.actions.Action
    public void execute(Map<String, Object> map) {
        startDeepLink();
    }
}
