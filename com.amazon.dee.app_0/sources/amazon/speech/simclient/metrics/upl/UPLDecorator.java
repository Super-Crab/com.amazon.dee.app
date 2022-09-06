package amazon.speech.simclient.metrics.upl;

import amazon.speech.simclient.metrics.upl.UPLConstants;
import amazon.speech.simclient.metrics.upl.data.DirectiveData;
import amazon.speech.simclient.metrics.upl.data.InteractionRequestData;
import amazon.speech.simclient.metrics.upl.data.SpeechRequestData;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class UPLDecorator {
    private static final String OFFLINE_PROGRAM_NAME_TAG = "_offline";
    private static final boolean DEBUG = Build.TYPE.contains("debug");
    private static final String TAG = UPLDecorator.class.getSimpleName();

    static void decorateDirectiveUplData(DirectiveData directiveData, Intent intent) {
        long firstByteTimestamp = directiveData.getFirstByteTimestamp();
        long parseCompleteTimestamp = directiveData.getParseCompleteTimestamp();
        long dispatchTimestamp = directiveData.getDispatchTimestamp();
        if (firstByteTimestamp > 0) {
            intent.putExtra("directive_first_byte_time", firstByteTimestamp);
            if (DEBUG) {
                GeneratedOutlineSupport1.outline153("decorating with first byte time: ", firstByteTimestamp);
            }
        }
        if (parseCompleteTimestamp > 0) {
            intent.putExtra("directive_parse_complete_time", parseCompleteTimestamp);
            if (DEBUG) {
                GeneratedOutlineSupport1.outline153("decorating with parse complete time: ", parseCompleteTimestamp);
            }
        }
        if (dispatchTimestamp > 0) {
            intent.putExtra("directive_dispatch_time", dispatchTimestamp);
            if (DEBUG) {
                GeneratedOutlineSupport1.outline153("decorating with dispatch time: ", dispatchTimestamp);
            }
        }
        if (!TextUtils.isEmpty(directiveData.getName())) {
            intent.putExtra("name", directiveData.getName());
            if (DEBUG) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("decorating with directive name:");
                outline107.append(directiveData.getName());
                outline107.toString();
            }
        }
        if (!TextUtils.isEmpty(directiveData.getNamespace())) {
            intent.putExtra("namespace", directiveData.getNamespace());
            if (!DEBUG) {
                return;
            }
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("decorating with directive ns:");
            outline1072.append(directiveData.getNamespace());
            outline1072.toString();
        }
    }

    public static void decorateInteractionRequestUplToIntent(String str, InteractionRequestData interactionRequestData, String str2, String str3, Intent intent) {
        DirectiveData directiveData = new DirectiveData();
        directiveData.setNamespace(str2);
        directiveData.setName(str3);
        decorateUplToIntent(str, interactionRequestData, directiveData, intent);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0093, code lost:
        if (r3 > r5) goto L32;
     */
    /* JADX WARN: Removed duplicated region for block: B:30:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void decorateRequestUplData(java.lang.String r8, amazon.speech.simclient.metrics.upl.data.RequestData r9, amazon.speech.simclient.metrics.upl.data.DirectiveData r10, android.content.Intent r11) {
        /*
            amazon.speech.simclient.metrics.upl.data.RequestData$Type r0 = r9.getType()
            int r0 = r0.ordinal()
            java.lang.String r1 = r9.getEventId()
            java.lang.String r2 = r9.getAlexaIntentName()
            long r3 = r9.getLastAudioDirectiveTimestamp()
            long r5 = r9.getLastVisualDirectiveTimestamp()
            java.lang.String r7 = "event_type"
            r11.putExtra(r7, r0)
            java.lang.String r0 = "event_id"
            r11.putExtra(r0, r1)
            java.lang.String r0 = "intent_name"
            r11.putExtra(r0, r2)
            boolean r9 = r9.isOfflineInteraction()
            java.lang.String r0 = "metrics_program_name"
            if (r9 == 0) goto L44
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r8)
            java.lang.String r8 = "_offline"
            r9.append(r8)
            java.lang.String r8 = r9.toString()
            r11.putExtra(r0, r8)
            goto L47
        L44:
            r11.putExtra(r0, r8)
        L47:
            r8 = 0
            int r0 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1))
            if (r0 <= 0) goto L5b
            java.lang.String r0 = "last_audio_directive_time"
            r11.putExtra(r0, r3)
            boolean r0 = amazon.speech.simclient.metrics.upl.UPLDecorator.DEBUG
            if (r0 == 0) goto L5b
            java.lang.String r0 = "decorating with previous audio completion time: "
            com.android.tools.r8.GeneratedOutlineSupport1.outline153(r0, r3)
        L5b:
            int r0 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r0 <= 0) goto L6d
            java.lang.String r0 = "last_visual_directive_time"
            r11.putExtra(r0, r5)
            boolean r0 = amazon.speech.simclient.metrics.upl.UPLDecorator.DEBUG
            if (r0 == 0) goto L6d
            java.lang.String r0 = "decorating with previous visual completion time: "
            com.android.tools.r8.GeneratedOutlineSupport1.outline153(r0, r5)
        L6d:
            decorateDirectiveUplData(r10, r11)
            java.lang.String r10 = r10.getChannel()
            r0 = -1
            java.lang.String r2 = "audio"
            boolean r2 = r2.equals(r10)
            if (r2 == 0) goto L7f
            goto L95
        L7f:
            java.lang.String r2 = "visual"
            boolean r2 = r2.equals(r10)
            if (r2 == 0) goto L89
            goto L97
        L89:
            java.lang.String r2 = "both"
            boolean r10 = r2.equals(r10)
            if (r10 == 0) goto L98
            int r10 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r10 <= 0) goto L97
        L95:
            r0 = r3
            goto L98
        L97:
            r0 = r5
        L98:
            int r8 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r8 <= 0) goto Lab
            java.lang.String r8 = "previous_directive_complete_time"
            r11.putExtra(r8, r0)
            boolean r8 = amazon.speech.simclient.metrics.upl.UPLDecorator.DEBUG
            if (r8 == 0) goto Lab
            java.lang.String r8 = "decorating with previous directive completion time: "
            com.android.tools.r8.GeneratedOutlineSupport1.outline153(r8, r0)
        Lab:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: amazon.speech.simclient.metrics.upl.UPLDecorator.decorateRequestUplData(java.lang.String, amazon.speech.simclient.metrics.upl.data.RequestData, amazon.speech.simclient.metrics.upl.data.DirectiveData, android.content.Intent):void");
    }

    public static void decorateUplToIntent(String str, SpeechRequestData speechRequestData, DirectiveData directiveData, Intent intent) {
        decorateRequestUplData(str, speechRequestData, directiveData, intent);
        long wakewordTimestamp = speechRequestData.getWakewordTimestamp();
        long endOfSpeechTimestamp = speechRequestData.getEndOfSpeechTimestamp();
        long endPointTimestamp = speechRequestData.getEndPointTimestamp();
        if (wakewordTimestamp > 0) {
            intent.putExtra("wake_word_detection_time", wakewordTimestamp);
            if (DEBUG) {
                GeneratedOutlineSupport1.outline153("decorating with wake word time: ", wakewordTimestamp);
            }
        }
        if (endOfSpeechTimestamp > 0) {
            intent.putExtra("end_of_speech_time", endOfSpeechTimestamp);
            if (DEBUG) {
                GeneratedOutlineSupport1.outline153("decorating with end of speech time: ", endOfSpeechTimestamp);
            }
        }
        if (endPointTimestamp > 0) {
            intent.putExtra("stop_capture_time", endPointTimestamp);
            if (!DEBUG) {
                return;
            }
            GeneratedOutlineSupport1.outline153("decorating with stop capture time: ", endPointTimestamp);
        }
    }

    public static void decorateUplToIntent(String str, InteractionRequestData interactionRequestData, DirectiveData directiveData, Intent intent) {
        decorateRequestUplData(str, interactionRequestData, directiveData, intent);
        long initiationTimestamp = interactionRequestData.getInitiationTimestamp();
        if (initiationTimestamp > 0) {
            intent.putExtra(UPLConstants.IntentKey.INTENT_KEY_INTERACTION_START_TIME, initiationTimestamp);
            if (DEBUG) {
                GeneratedOutlineSupport1.outline153("decorating with initiation time: ", initiationTimestamp);
            }
        }
        long eventRequestTimestamp = interactionRequestData.getEventRequestTimestamp();
        if (eventRequestTimestamp > 0) {
            intent.putExtra(UPLConstants.IntentKey.INTENT_KEY_DEVICE_REQUEST_TIME, eventRequestTimestamp);
            if (DEBUG) {
                GeneratedOutlineSupport1.outline153("decorating with event request time: ", eventRequestTimestamp);
            }
        }
        String interactionLabel = interactionRequestData.getInteractionLabel();
        if (!TextUtils.isEmpty(interactionLabel)) {
            intent.putExtra(UPLConstants.IntentKey.INTENT_KEY_INTERACTION_LABEL, interactionLabel);
            if (!DEBUG) {
                return;
            }
            GeneratedOutlineSupport1.outline158("decorating with interaction label: ", interactionLabel);
        }
    }
}
