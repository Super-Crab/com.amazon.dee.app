package com.amazon.alexa.voice.ui.onedesign.calendar;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.voice.ui.cards.util.StringUtils;
import com.amazon.alexa.voice.ui.onedesign.calendar.CalendarCard;
import com.amazon.alexa.voice.ui.onedesign.calendar.CalendarCardModel;
import com.amazon.alexa.voice.ui.onedesign.empty.EmptiableCardType;
import com.amazon.alexa.voice.ui.onedesign.empty.EmptyCard;
import com.amazon.alexa.voice.ui.onedesign.empty.EmptyController;
import com.amazon.alexa.voice.ui.onedesign.util.DateUtils;
import com.amazon.alexa.voice.ui.onedesign.util.JSONUtils;
import com.amazon.alexa.voice.ui.superbowl.ControllerFactory;
import com.amazon.regulator.ViewController;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public final class CalendarControllerFactory implements ControllerFactory<ViewController> {
    private boolean isCalendarLinked(JSONObject jSONObject) {
        return !jSONObject.optBoolean("isNoLinkedCalendarCard", false);
    }

    private List<CalendarCardModel.EventModel> makeEventList(JSONObject jSONObject) throws JSONException {
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray = jSONObject.optJSONArray("eonEventList");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                String string = jSONObject2.getJSONObject("eonMetadata").getString("calendarId");
                if (!StringUtils.isValidEmail(string)) {
                    string = jSONObject2.getJSONObject("eonMetadata").getString("name");
                    if (!StringUtils.isValidEmail(string)) {
                        string = null;
                    }
                }
                arrayList.add(new CalendarCard.Event.Builder().name(jSONObject2.getString("title")).startTime(makeTimeFromDateString(jSONObject2.getString("startTime"))).endTime(makeTimeFromDateString(jSONObject2.getString("endTime"))).location(JSONUtils.optionalString(jSONObject2, "location")).allDay(jSONObject2.getBoolean("allDay")).multiDay(jSONObject2.getBoolean("multiDay")).id(string).provider(jSONObject2.getJSONObject("eonMetadata").getString(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PROVIDER)).build());
            }
        }
        return arrayList;
    }

    private long makeTimeFromDateString(String str) throws JSONException {
        try {
            return DateUtils.extractTimeFromDateString(Arrays.asList("yyyy-MM-dd'T'HH:mm:ss.SSS", "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"), str);
        } catch (ParseException e) {
            throw new JSONException(e.getMessage());
        }
    }

    @Override // com.amazon.alexa.voice.ui.superbowl.ControllerFactory
    /* renamed from: create */
    public ViewController mo2780create(JSONObject jSONObject) throws JSONException {
        if (!isCalendarLinked(jSONObject)) {
            return EmptyController.create(new EmptyCard(EmptiableCardType.CALENDAR_NOT_LINKED.toString(), JSONUtils.optionalString(jSONObject, "title", (String) null), null));
        }
        List<CalendarCardModel.EventModel> makeEventList = makeEventList(jSONObject);
        if (makeEventList.isEmpty()) {
            return EmptyController.create(new EmptyCard(EmptiableCardType.NO_CALENDAR_EVENT.toString(), null, null));
        }
        return CalendarController.create(new CalendarCard.Builder().eventList(makeEventList).build());
    }
}
