package com.amazon.alexa.voice.ui.cards;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.ui.onedesign.calendar.CalendarControllerFactory;
import com.amazon.alexa.voice.ui.onedesign.list.ListControllerFactory;
import com.amazon.alexa.voice.ui.onedesign.local.LocalControllerFactory;
import com.amazon.alexa.voice.ui.onedesign.movies.MoviesControllerFactory;
import com.amazon.alexa.voice.ui.onedesign.shopping.ShoppingControllerFactory;
import com.amazon.alexa.voice.ui.onedesign.sports.scores.SportsScoresControllerFactory;
import com.amazon.alexa.voice.ui.onedesign.standard.StandardControllerFactory;
import com.amazon.alexa.voice.ui.onedesign.traffic.TrafficControllerFactory;
import com.amazon.alexa.voice.ui.onedesign.travel.TravelTimeDistanceControllerFactory;
import com.amazon.alexa.voice.ui.onedesign.weather.WeatherControllerFactory;
import com.amazon.alexa.voice.ui.superbowl.ControllerFactory;
import com.amazon.regulator.ViewController;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes11.dex */
public final class StandardModeCardRendererControllerFactory extends CardRendererControllerFactory {
    public StandardModeCardRendererControllerFactory(CardCreationEventListener cardCreationEventListener) {
        super(cardCreationEventListener, getControllerFactories());
    }

    @VisibleForTesting
    static Map<String, ControllerFactory<? extends ViewController>> getControllerFactories() {
        HashMap hashMap = new HashMap();
        CardRendererControllerFactory.register(hashMap, new StandardControllerFactory(), "StandardCard", "KnowledgeCard", "TextCard", "KnowledgeTextCard");
        CardRendererControllerFactory.register(hashMap, new WeatherControllerFactory(), "WeatherCard");
        CardRendererControllerFactory.register(hashMap, new CalendarControllerFactory(), "EonCard");
        CardRendererControllerFactory.register(hashMap, new ShoppingControllerFactory(), "ProductInfoCard", "MultiProductInfoCard");
        CardRendererControllerFactory.register(hashMap, new LocalControllerFactory(), "PuffinCard");
        CardRendererControllerFactory.register(hashMap, new MoviesControllerFactory(), "MoviesMobileCard");
        CardRendererControllerFactory.register(hashMap, new TrafficControllerFactory(), "TrafficCard");
        CardRendererControllerFactory.register(hashMap, new ListControllerFactory(), "ListCard");
        CardRendererControllerFactory.register(hashMap, new SportsScoresControllerFactory(), "SportsCard");
        CardRendererControllerFactory.register(hashMap, new TravelTimeDistanceControllerFactory(), "TravelTimeDistanceCard");
        return hashMap;
    }
}
