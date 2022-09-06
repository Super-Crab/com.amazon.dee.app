package com.amazon.deecomms.contacts.util;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.ContactUtils;
import com.amazon.deecomms.contacts.model.Contact;
import com.amazon.deecomms.contacts.model.FullContactName;
import com.amazon.deecomms.contacts.util.BlockContactHelper;
/* loaded from: classes12.dex */
public class BlockContactHelper {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, BlockContactHelper.class);
    private final String mCommsId;
    private final Contact mContact;
    private Dialog mCurrentDialog;
    private final Fragment mFragment;
    private final String mPageSource;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.deecomms.contacts.util.BlockContactHelper$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public class AnonymousClass1 extends AsyncTask<Void, Void, Boolean> {
        private BlockContactListener blockContactListener;
        final /* synthetic */ boolean val$block;
        final /* synthetic */ String val$contactName;

        AnonymousClass1(boolean z, String str) {
            this.val$block = z;
            this.val$contactName = str;
            this.blockContactListener = (BlockContactListener) BlockContactHelper.this.mFragment;
        }

        public /* synthetic */ void lambda$onPostExecute$0$BlockContactHelper$1(DialogInterface dialogInterface, int i) {
            BlockContactHelper.this.dismissCurrentDialog();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Void... voidArr) {
            return Boolean.valueOf(new ContactStatusManager().setBlockStatus(BlockContactHelper.this.mCommsId, this.val$block));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            int i;
            String string;
            CounterMetric generateOperational;
            super.onPostExecute((AnonymousClass1) bool);
            if (bool.booleanValue()) {
                i = this.val$block ? R.string.contact_blocking_feedback_title_blocked : R.string.contact_blocking_feedback_title_unblocked;
                string = String.format(BlockContactHelper.this.mFragment.getString(this.val$block ? R.string.contact_blocking_feedback_message_blocked : R.string.contact_blocking_feedback_message_unblocked), this.val$contactName);
                generateOperational = CounterMetric.generateOperational(MetricKeys.CONTACTS_BLOCK_SUCCESS);
                BlockContactHelper.this.mContact.setBlocked(this.val$block);
                this.blockContactListener.onBlockContactTaskCompleted(bool.booleanValue());
            } else {
                MetricsHelper.recordAlertDialogMetric(MetricKeys.ALERT_ERROR, BlockContactHelper.this.mPageSource, AlertSource.newClassSource(BlockContactHelper.this.mFragment.getClass()));
                i = R.string.contact_blocking_failure_title;
                string = BlockContactHelper.this.mFragment.getString(R.string.contact_blocking_failure_message);
                generateOperational = CounterMetric.generateOperational(MetricKeys.CONTACTS_BLOCK_FAILURE);
            }
            generateOperational.getMetadata().put("source", BlockContactHelper.this.mPageSource);
            MetricsHelper.recordSingleOccurrence(generateOperational);
            BlockContactHelper.access$400(BlockContactHelper.this, new AlertDialog.Builder(BlockContactHelper.this.mFragment.getContext()).setTitle(i).setMessage(string).setPositiveButton(R.string.dialog_ok_button, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.contacts.util.-$$Lambda$BlockContactHelper$1$5fRk8ObdmjTzIkOUlHjG6NiqzYY
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    BlockContactHelper.AnonymousClass1.this.lambda$onPostExecute$0$BlockContactHelper$1(dialogInterface, i2);
                }
            }).create());
        }
    }

    public BlockContactHelper(Fragment fragment, Contact contact, String str, String str2) {
        this.mFragment = fragment;
        this.mContact = contact;
        this.mCommsId = str;
        this.mPageSource = str2;
    }

    static /* synthetic */ void access$400(BlockContactHelper blockContactHelper, Dialog dialog) {
        blockContactHelper.dismissCurrentDialog();
        dialog.show();
        blockContactHelper.mCurrentDialog = dialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissCurrentDialog() {
        Dialog dialog = this.mCurrentDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
        this.mCurrentDialog = null;
    }

    private void setContactBlockStatus(@NonNull String str, boolean z) {
        new AnonymousClass1(z, str).execute(new Void[0]);
    }

    private void setupBlockingAlertButton(Button button) {
        if (button == null) {
            return;
        }
        button.setTextColor(this.mFragment.getResources().getColor(R.color.alexaBlue));
    }

    private void showDialog(Dialog dialog) {
        dismissCurrentDialog();
        dialog.show();
        this.mCurrentDialog = dialog;
    }

    public void blockContact(boolean z) {
        String format;
        int i;
        DialogInterface.OnClickListener onClickListener;
        final String fullName = ContactUtils.getFullName(new FullContactName(this.mContact.getContactName(), this.mContact.getCompany()));
        if (z) {
            format = String.format(this.mFragment.getString(R.string.contact_blocking_message_block), fullName);
            i = R.string.contact_blocking_action_block;
            onClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.contacts.util.-$$Lambda$BlockContactHelper$RIIxnXSl5eRBEeb2J_hB6BxQ7qQ
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    BlockContactHelper.this.lambda$blockContact$0$BlockContactHelper(fullName, dialogInterface, i2);
                }
            };
        } else {
            format = String.format(this.mFragment.getString(R.string.contact_blocking_message_unblock), fullName);
            i = R.string.contact_blocking_action_unblock;
            onClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.contacts.util.-$$Lambda$BlockContactHelper$xys75JPVW77we69pFK15_Qi0Bp4
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    BlockContactHelper.this.lambda$blockContact$1$BlockContactHelper(fullName, dialogInterface, i2);
                }
            };
        }
        AlertDialog create = new AlertDialog.Builder(this.mFragment.getActivity(), R.style.AlexaContactBlockingDialogStyle).setCancelable(false).setMessage(format).setNegativeButton(R.string.cancel, $$Lambda$BlockContactHelper$T_2tn_NeZU682pm0JVs4gpwxcmE.INSTANCE).setPositiveButton(i, onClickListener).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.amazon.deecomms.contacts.util.-$$Lambda$BlockContactHelper$la_8P6NWim69QtbqXNno5bI_ILc
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                BlockContactHelper.this.lambda$blockContact$3$BlockContactHelper(dialogInterface);
            }
        }).create();
        showDialog(create);
        setupBlockingAlertButton(create.getButton(-1));
        setupBlockingAlertButton(create.getButton(-2));
    }

    public void hideBlockContactDialog() {
        Dialog dialog = this.mCurrentDialog;
        if (dialog != null) {
            dialog.hide();
        }
    }

    public /* synthetic */ void lambda$blockContact$0$BlockContactHelper(String str, DialogInterface dialogInterface, int i) {
        LOG.i("User clicked block");
        setContactBlockStatus(str, true);
    }

    public /* synthetic */ void lambda$blockContact$1$BlockContactHelper(String str, DialogInterface dialogInterface, int i) {
        LOG.i("User clicked unblock");
        setContactBlockStatus(str, false);
    }

    public /* synthetic */ void lambda$blockContact$3$BlockContactHelper(DialogInterface dialogInterface) {
        dismissCurrentDialog();
    }

    public void showBlockContactDialog() {
        Dialog dialog = this.mCurrentDialog;
        if (dialog != null) {
            dialog.show();
        }
    }
}
