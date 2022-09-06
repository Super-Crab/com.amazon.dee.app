package com.amazon.alexa.smarthomecameras.ptz.directives;

import android.util.Log;
import com.amazon.alexa.smarthomecameras.CameraViewContract;
import com.amazon.alexa.smarthomecameras.directives.AlexaDirective;
import com.amazon.alexa.smarthomecameras.directives.DirectiveSender;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;
/* loaded from: classes10.dex */
public class DataChannelDirectiveSender implements DirectiveSender {
    private static final String TAG = "DataChannelDirectiveSender";
    private final Gson gson;
    private final CameraViewContract.Presenter presenter;

    public DataChannelDirectiveSender(Gson gson, CameraViewContract.Presenter presenter) {
        Preconditions.checkNotNull(gson, "Gson cannot be null");
        Preconditions.checkNotNull(presenter, "Presenter cannot be null");
        this.gson = gson;
        this.presenter = presenter;
    }

    @Override // com.amazon.alexa.smarthomecameras.directives.DirectiveSender
    public void sendDirective(AlexaDirective alexaDirective) {
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Sending ");
        outline107.append(alexaDirective.getName());
        outline107.append(" directive");
        Log.i(str, outline107.toString());
        this.presenter.sendData(this.gson.toJson(PtzDirectiveContainer.create(alexaDirective)));
    }
}
