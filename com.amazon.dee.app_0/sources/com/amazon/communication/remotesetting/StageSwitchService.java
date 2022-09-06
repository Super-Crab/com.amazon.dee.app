package com.amazon.communication.remotesetting;

import android.app.IntentService;
import android.content.Intent;
import com.amazon.communication.ir.RemoteSettingIdentityResolver;
import com.amazon.communication.utils.StringUtils;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class StageSwitchService extends IntentService {
    public static final String CONFIG_HOSTNAME_KEY = "hostname";
    public static final String CONFIG_PORT_KEY = "secure-port";
    public static final String DEFAULT_CONFIG_FILE = "defaultSetting.json";
    public static final String EXTRA_DESTINATION = "com.amazon.device.environment.extra.DESTINATION";
    public static final String EXTRA_STAGE = "com.amazon.device.environment.extra.STAGE";
    public static final String IR_AIV_KEY_PREFIX = "IdentityResolver.AIVRelayService";
    public static final String IR_BOB_KEY_PREFIX = "IdentityResolver.BobDispatchService";
    public static final String IR_GW_D2D_KEY_PREFIX = "IdentityResolver.Gateway";
    public static final String IR_GW_KEY_PREFIX = "IdentityResolver.DPGatewayService";
    public static final String IR_GW_PROB_KEY_PREFIX = "IdentityResolver.DPGatewayProbingService";
    private String destination;
    private String hostname;
    private String stage;
    private static final String TAG = "TComm.StageSwitchService";
    private static final DPLogger log = new DPLogger(TAG);
    public static final HashMap<String, String> STAGES = new HashMap<>();

    /* loaded from: classes12.dex */
    public enum DESTINATIONS {
        GW,
        BOB
    }

    static {
        STAGES.put("DESKTOP", "switchBetaConfig.json");
        STAGES.put("ALPHA", "switchAlphaConfig.json");
        STAGES.put("BETA", "switchBetaConfig.json");
        STAGES.put("GAMMA", "switchGammaConfig.json");
        STAGES.put("PROD", "switchProdConfig.json");
    }

    public StageSwitchService() {
        super(TAG);
    }

    private void handleStageSwitch() {
        try {
            if (isValidStage(this.stage)) {
                updateSettingsForStage();
                log.info("handleStageSwitch", "Switching stage to", "stage", this.stage);
                return;
            }
            throw new IllegalArgumentException("Invalid stage: " + this.stage);
        } catch (IOException | IllegalArgumentException | JSONException e) {
            log.warn("handleStageSwitch", "Stage switch failed, aborting.", "stage", this.stage, "destination", this.destination, "failure", e);
        }
    }

    public static boolean isValidStage(String str) {
        return STAGES.containsKey(str);
    }

    protected JSONObject getStageConfigurationValues() throws IllegalArgumentException, IOException, JSONException {
        if (isValidStage(this.stage)) {
            String str = STAGES.get(this.stage);
            log.debug("getStageConfigurationValues", "Configuration file is", "file", str);
            JSONObject readJsonFile = FileUtils.readJsonFile(getAssets(), str);
            String string = readJsonFile.getString(RemoteSettingIdentityResolver.DOMAIN_KEY);
            String string2 = readJsonFile.getString(RemoteSettingIdentityResolver.REALM_KEY);
            if ("DESKTOP".equals(this.stage)) {
                if (!StringUtils.isNullOrEmpty(this.destination)) {
                    try {
                        this.destination = this.destination.startsWith("wss://") ? this.destination : "wss://".concat(this.destination);
                        URI uri = new URI(this.destination);
                        String format = String.format("%s.%s.%s", IR_GW_KEY_PREFIX, string, string2);
                        String format2 = String.format("%s.%s.%s", IR_GW_D2D_KEY_PREFIX, string, string2);
                        String format3 = String.format("%s.%s.%s", IR_GW_PROB_KEY_PREFIX, string, string2);
                        String format4 = String.format("%s.%s.%s", IR_AIV_KEY_PREFIX, string, string2);
                        JSONObject jSONObject = FileUtils.readJsonFile(getAssets(), "defaultSetting.json").getJSONObject(format);
                        jSONObject.put(CONFIG_HOSTNAME_KEY, uri.getHost());
                        jSONObject.put(CONFIG_PORT_KEY, uri.getPort());
                        readJsonFile.put(format, jSONObject);
                        readJsonFile.put(format2, jSONObject);
                        readJsonFile.put(format3, jSONObject);
                        readJsonFile.put(format4, jSONObject);
                        log.debug("getStageConfigurationValues", "Configured host", "uri", uri);
                    } catch (URISyntaxException unused) {
                        throw new IllegalArgumentException("Unparsable endpoint, it must follow  URI hostname:port syntax.");
                    } catch (JSONException e) {
                        throw new IllegalArgumentException("Error while reading configuration for desktop", e);
                    }
                } else {
                    throw new IllegalArgumentException("DESKTOP stage must be followed by a DESTINATION parameter username.aka.corp.amazon.com");
                }
            } else if (DESTINATIONS.BOB.name().equals(this.destination)) {
                try {
                    JSONObject jSONObject2 = FileUtils.readJsonFile(getAssets(), "defaultSetting.json").getJSONObject(String.format("%s.%s.%s", IR_BOB_KEY_PREFIX, string, string2));
                    String format5 = String.format("%s.%s.%s", IR_GW_KEY_PREFIX, string, string2);
                    String format6 = String.format("%s.%s.%s", IR_GW_D2D_KEY_PREFIX, string, string2);
                    String format7 = String.format("%s.%s.%s", IR_GW_PROB_KEY_PREFIX, string, string2);
                    String format8 = String.format("%s.%s.%s", IR_AIV_KEY_PREFIX, string, string2);
                    readJsonFile.put(format5, jSONObject2);
                    readJsonFile.put(format6, jSONObject2);
                    readJsonFile.put(format7, jSONObject2);
                    readJsonFile.put(format8, jSONObject2);
                } catch (JSONException e2) {
                    throw new IllegalArgumentException("Error while reading configuration for Bob endpoints", e2);
                }
            }
            log.info("getStageConfigurationValues", "Setting configuration values", "configs", readJsonFile);
            return readJsonFile;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid stage: ");
        outline107.append(this.stage);
        throw new IllegalArgumentException(outline107.toString());
    }

    protected boolean isSyncEnabledForStage(String str) {
        return isValidStage(str) && str.equals("PROD");
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        this.stage = intent.getStringExtra("com.amazon.device.environment.extra.STAGE");
        this.destination = intent.getStringExtra("com.amazon.device.environment.extra.DESTINATION");
        log.debug("onHandleIntent", "Service started", "stage", this.stage, "destination", this.destination, "extras", intent.getExtras());
        handleStageSwitch();
    }

    protected void updateSettingsForStage() throws JSONException, IOException, IllegalArgumentException {
        RemoteSettingManager.updateSettings(this, getStageConfigurationValues());
        ConfigurationSyncService.setSyncEnabled(this, isSyncEnabledForStage(this.stage));
    }
}
