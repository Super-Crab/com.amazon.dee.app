package com.amazon.alexa.redesign.repository;

import android.content.Context;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.redesign.R;
import com.amazon.alexa.redesign.actions.Action;
import com.amazon.alexa.redesign.actions.ActionFactory;
import com.amazon.alexa.redesign.entity.templates.VoxIngressTemplateModel;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class VoxIngressRepository {
    private final ActionFactory actionFactory;
    private final Context context;
    private final LocaleRepository localeRepository;
    private final VoiceService voiceService;

    public VoxIngressRepository(ActionFactory actionFactory, VoiceService voiceService, LocaleRepository localeRepository, Context context) {
        this.actionFactory = actionFactory;
        this.voiceService = voiceService;
        this.localeRepository = localeRepository;
        this.context = context;
    }

    public VoxIngressTemplateModel getModel() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(MessagingControllerConstant.MESSAGING_CONTROLLER_PAYLOAD_TYPE, "NavigateAction");
            jSONObject.put("route", "voice");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new VoxIngressTemplateModel(this.actionFactory.getAction(jSONObject), getVoxHintStringId(this.voiceService));
    }

    public Action getTWAAction() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(MessagingControllerConstant.MESSAGING_CONTROLLER_PAYLOAD_TYPE, "NavigateAction");
            jSONObject.put("route", "type-to-alexa");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this.actionFactory.getAction(jSONObject);
    }

    public int getVoxHintStringId(VoiceService voiceService) {
        boolean isAMPDDevice = AMPDInformationProvider.getInstance(this.context).isAMPDDevice();
        if (!isWakeWordAvailable(voiceService) && !isAMPDDevice) {
            if (this.localeRepository.isUsLocale()) {
                return R.string.amahc_alexa_hint_no_wakeword_t2;
            }
            return R.string.amahc_alexa_hint_no_wakeword;
        }
        return R.string.amahc_alexa_hint;
    }

    public boolean isWakeWordAvailable(VoiceService voiceService) {
        return voiceService.isHandsfreeEnabled() && voiceService.isHandsfreeSupported() && voiceService.isVoiceAllowed();
    }
}
