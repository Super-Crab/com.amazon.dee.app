package com.amazon.dee.app.ui.view;

import android.app.ProgressDialog;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import com.amazon.dee.app.R;
import com.amazon.dee.app.databinding.LoadingProgressDialogBinding;
/* loaded from: classes12.dex */
public class LoadingProgressDialog extends ProgressDialog {
    private LoadingProgressDialogBinding binding;
    private LoadingProgressViewModel viewModel;

    public LoadingProgressDialog(Context context) {
        super(context);
        this.viewModel = new LoadingProgressViewModel();
        this.binding = (LoadingProgressDialogBinding) DataBindingUtil.inflate(getLayoutInflater(), R.layout.loading_progress_dialog, null, false);
        this.binding.setViewModel(this.viewModel);
        setIndeterminate(true);
        setCancelable(false);
    }

    @Override // android.app.ProgressDialog, android.app.AlertDialog
    public void setMessage(CharSequence charSequence) {
        this.viewModel.setMessage(charSequence);
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        setContentView(this.binding.getRoot());
        this.binding.message.setText(this.viewModel.getMessage());
    }
}
