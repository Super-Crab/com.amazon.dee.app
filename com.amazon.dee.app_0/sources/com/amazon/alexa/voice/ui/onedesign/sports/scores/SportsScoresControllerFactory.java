package com.amazon.alexa.voice.ui.onedesign.sports.scores;

import android.text.TextUtils;
import com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCard;
import com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresCardModel;
import com.amazon.alexa.voice.ui.onedesign.util.JSONUtils;
import com.amazon.alexa.voice.ui.superbowl.ControllerFactory;
import com.sun.mail.imap.IMAPStore;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public final class SportsScoresControllerFactory implements ControllerFactory<SportsScoresController> {
    private List<CharSequence> extractInnerFieldList(JSONArray jSONArray, String str) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getJSONObject(i).getString(str));
        }
        return arrayList;
    }

    private SportsScoresCardModel.NextGameModel makeNextGame(JSONObject jSONObject) throws JSONException {
        boolean z = jSONObject.getBoolean("nextFixtureAtHome");
        String string = jSONObject.getString("nextDay");
        String string2 = jSONObject.getString("nextTime");
        String string3 = jSONObject.getString("nextTeam");
        if (TextUtils.isEmpty(string3) || TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
            return null;
        }
        return new SportsScoresCard.NextGame.Builder().title("Next Game:").date(string).time(string2).hostedByHomeTeam(z).opponent(string3).build();
    }

    private SportsScoresCardModel.ScoreComponentsModel makeScoreComponents(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONArray("divisionScores");
        return new SportsScoresCard.ScoreComponents.Builder().homeTeamShortName(jSONObject.getJSONObject("homeTeam").getString("abbreviatedName")).awayTeamShortName(jSONObject.getJSONObject("awayTeam").getString("abbreviatedName")).homeTeamScoreComponents(extractInnerFieldList(jSONArray, "homeValue")).awayTeamScoreComponents(extractInnerFieldList(jSONArray, "awayValue")).scoreComponentsHeadings(extractInnerFieldList(jSONArray, "title")).build();
    }

    private CharSequence makeSubTitle(JSONObject jSONObject) throws JSONException {
        return TextUtils.concat(jSONObject.getString("league"), " | ", jSONObject.getString("location"));
    }

    private SportsScoresCardModel.TeamStandingModel makeTeamStanding(JSONObject jSONObject, boolean z) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject(z ? "homeTeam" : "awayTeam");
        boolean z2 = false;
        String string = jSONObject.getJSONArray("totalScores").getJSONObject(0).getString("homeValue");
        String string2 = jSONObject.getJSONArray("totalScores").getJSONObject(0).getString("awayValue");
        String str = z ? string : string2;
        int parseInt = Integer.parseInt(string.toString());
        int parseInt2 = Integer.parseInt(string2.toString());
        if ((z && parseInt > parseInt2) || (!z && parseInt2 > parseInt)) {
            z2 = true;
        }
        return new SportsScoresCard.TeamStanding.Builder().imageUrl(jSONObject2.getJSONObject("imageReference").getString("url")).teamName(jSONObject2.getString("nickname")).isAhead(z2).points(str).build();
    }

    private CharSequence makeTitle(JSONObject jSONObject) throws JSONException {
        return TextUtils.concat(jSONObject.getString("gameState"), " | ", jSONObject.getString(IMAPStore.ID_DATE));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.voice.ui.superbowl.ControllerFactory
    /* renamed from: create */
    public SportsScoresController mo2780create(JSONObject jSONObject) throws JSONException {
        return SportsScoresController.create(new SportsScoresCard.Builder().title(makeTitle(jSONObject)).subTitle(makeSubTitle(jSONObject)).sportName(JSONUtils.optionalString(jSONObject, "sport")).homeTeamStanding(makeTeamStanding(jSONObject, true)).awayTeamStanding(makeTeamStanding(jSONObject, false)).scores(makeScoreComponents(jSONObject)).nextGame(makeNextGame(jSONObject)).build());
    }
}
