package com.amazon.deecomms.calling.controller;

import android.os.AsyncTask;
import androidx.annotation.NonNull;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.model.BeginCallPayload;
import com.google.common.base.Optional;
/* loaded from: classes12.dex */
public abstract class InitiateCallRequest extends AsyncTask<Void, Void, Optional<BeginCallPayload>> {
    private ValidBeginCallPayloadHandler validBeginCallPayloadHandler;

    public InitiateCallRequest(@NonNull ValidBeginCallPayloadHandler validBeginCallPayloadHandler) {
        this.validBeginCallPayloadHandler = validBeginCallPayloadHandler;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showGenericErrorMessage() {
        this.validBeginCallPayloadHandler.showErrorScreen(R.string.generic_error_title, R.string.generic_error_msg);
    }
}
