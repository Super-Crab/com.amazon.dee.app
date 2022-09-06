package com.amazon.deecomms.calling.ui;

import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.widget.PopupMenu;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.calling.enums.AudioRoutes;
import com.amazon.deecomms.calling.telecom.audiomanagement.TelecomCallAudioManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.Utils;
import com.android.tools.r8.GeneratedOutlineSupport;
import java.util.List;
/* loaded from: classes12.dex */
public class AudioPickerPopUp implements PopupMenu.OnMenuItemClickListener {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, AudioPickerPopUp.class);
    private final PopupMenu popupMenu;
    private final TelecomCallAudioManager telecomCallAudioManager;

    public AudioPickerPopUp(@NonNull TelecomCallAudioManager telecomCallAudioManager, @NonNull PopupMenu popupMenu) {
        this.telecomCallAudioManager = telecomCallAudioManager;
        this.popupMenu = popupMenu;
    }

    private void reflectCurrentAudioState() {
        String activeRoute = this.telecomCallAudioManager.getActiveRoute();
        CommsLogger commsLogger = LOG;
        commsLogger.i("Current audio state is :" + activeRoute);
        if (activeRoute.equals(AudioRoutes.WIRED_HEADSET)) {
            activeRoute = AudioRoutes.WIREDHEADSET.toString();
        }
        int size = this.popupMenu.getMenu().size();
        for (int i = 0; i < size; i++) {
            MenuItem item = this.popupMenu.getMenu().getItem(i);
            String charSequence = item.getTitle().toString();
            if (charSequence.equalsIgnoreCase(activeRoute)) {
                item.setChecked(true);
                return;
            } else if (Utils.isPieAndAbove() && activeRoute.equalsIgnoreCase(AudioRoutes.BLUETOOTH.toString()) && charSequence.equalsIgnoreCase(this.telecomCallAudioManager.getActiveBluetoothDevice())) {
                CommsLogger commsLogger2 = LOG;
                commsLogger2.i("Setting " + charSequence + " as the current route");
                item.setChecked(true);
                return;
            }
        }
    }

    @Override // androidx.appcompat.widget.PopupMenu.OnMenuItemClickListener
    public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
        String charSequence = menuItem.getTitle().toString();
        CommsLogger commsLogger = LOG;
        commsLogger.i("Item selected is : " + charSequence);
        if (AudioRoutes.isValidEnum(charSequence)) {
            this.telecomCallAudioManager.setAudioRoute(charSequence);
            return true;
        }
        LOG.i("Setting active bluetooth device");
        this.telecomCallAudioManager.setActiveBluetoothDevice(charSequence);
        return true;
    }

    @VisibleForTesting
    public void populatePicker() {
        LOG.i("Initializing audio picker");
        List<String> supportedAudioRoutes = this.telecomCallAudioManager.getSupportedAudioRoutes();
        this.popupMenu.getMenu().clear();
        for (String str : supportedAudioRoutes) {
            GeneratedOutlineSupport.outline4("Adding entry:", str, LOG);
            this.popupMenu.getMenu().add(1, 0, 0, str);
        }
        this.popupMenu.getMenu().setGroupCheckable(1, true, true);
        this.popupMenu.setOnMenuItemClickListener(this);
    }

    public void showPicker() {
        LOG.i("Showing the picker");
        populatePicker();
        reflectCurrentAudioState();
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.AUDIO_PICKER_SHOWN);
        this.popupMenu.show();
    }
}
