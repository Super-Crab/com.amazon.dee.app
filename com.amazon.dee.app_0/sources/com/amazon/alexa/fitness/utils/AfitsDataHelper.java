package com.amazon.alexa.fitness.utils;

import com.amazon.alexa.fitness.api.afits.AfitsClient;
import com.amazon.alexa.fitness.api.afits.FitnessSession;
import com.amazon.alexa.fitness.api.afits.GetAllFitnessSessionResponse;
import com.amazon.alexa.fitness.api.util.DateTime;
import com.amazon.alexa.fitness.view.workoutTab.FitnessListType;
import com.amazon.alexa.fitness.view.workoutTab.HeaderItem;
import com.amazon.alexa.fitness.view.workoutTab.ListItem;
import com.amazon.alexa.fitness.view.workoutTab.WeeklySummary;
import com.amazon.alexa.fitness.view.workoutTab.WorkoutItem;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.sun.mail.imap.IMAPStore;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AfitsDataHelper.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0017\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u0017J\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020\"J\u000e\u0010#\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J \u0010$\u001a\f\u0012\u0004\u0012\u00020\u00100\u000fj\u0002`\u00112\f\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&H\u0016R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR$\u0010\u000e\u001a\f\u0012\u0004\u0012\u00020\u00100\u000fj\u0002`\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006("}, d2 = {"Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;", "", "()V", "afitsClient", "Lcom/amazon/alexa/fitness/api/afits/AfitsClient;", "kotlin.jvm.PlatformType", "getAfitsClient", "()Lcom/amazon/alexa/fitness/api/afits/AfitsClient;", "nextToken", "", "getNextToken", "()Ljava/lang/String;", "setNextToken", "(Ljava/lang/String;)V", "workoutHistoryList", "", "Lcom/amazon/alexa/fitness/view/workoutTab/WorkoutItem;", "Lcom/amazon/alexa/fitness/utils/MutableWorkoutHistoryList;", "getWorkoutHistoryList", "()Ljava/util/List;", "setWorkoutHistoryList", "(Ljava/util/List;)V", "addAllWorkoutSessions", "", "response", "Lcom/amazon/alexa/fitness/api/afits/GetAllFitnessSessionResponse;", "addWeeklySummaryItem", "calculateWeeklySummary", "Lcom/amazon/alexa/fitness/view/workoutTab/WeeklySummary;", IMAPStore.ID_DATE, "Ljava/util/Date;", "getEndOfList", "", "hasLocalWorkoutSessions", "", "initializeWorkoutSessions", "transformAFITSData", "fitnessHistory", "", "Lcom/amazon/alexa/fitness/api/afits/FitnessSession;", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public class AfitsDataHelper {
    @Nullable
    private String nextToken;
    private final AfitsClient afitsClient = (AfitsClient) GeneratedOutlineSupport1.outline20(AfitsClient.class);
    @NotNull
    private List<WorkoutItem> workoutHistoryList = new ArrayList();

    public final void addAllWorkoutSessions(@NotNull GetAllFitnessSessionResponse response) {
        List<FitnessSession> list;
        Intrinsics.checkParameterIsNotNull(response, "response");
        List<WorkoutItem> list2 = this.workoutHistoryList;
        list = ArraysKt___ArraysKt.toList(response.getFitnessSessions());
        list2.addAll(transformAFITSData(list));
        this.nextToken = response.getNextToken();
    }

    public final void addWeeklySummaryItem() {
        WorkoutItem workoutItem = (WorkoutItem) CollectionsKt.firstOrNull((List<? extends Object>) this.workoutHistoryList);
        if (workoutItem == null || workoutItem.getItemType() == FitnessListType.WEEKLY_VIEW) {
            return;
        }
        this.workoutHistoryList.add(0, calculateWeeklySummary(new Date(DateTime.Companion.now().getEpochMilli())));
    }

    @NotNull
    public final WeeklySummary calculateWeeklySummary(@NotNull Date date) {
        long j;
        Intrinsics.checkParameterIsNotNull(date, "date");
        WeeklySummary weeklySummary = new WeeklySummary(null, 0, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, null, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, 127, null);
        Calendar calendar = Calendar.getInstance();
        Long startOfWeekDateInMillis = DayOfWeekPosition.Companion.getStartOfWeekDateInMillis(date);
        if (startOfWeekDateInMillis != null) {
            long longValue = startOfWeekDateInMillis.longValue();
            List<WorkoutItem> list = this.workoutHistoryList;
            ArrayList<WorkoutItem> arrayList = new ArrayList();
            Iterator<T> it2 = list.iterator();
            while (true) {
                boolean z = true;
                if (!it2.hasNext()) {
                    break;
                }
                Object next = it2.next();
                if (((WorkoutItem) next).getItemType() != FitnessListType.LIST_ITEM) {
                    z = false;
                }
                if (z) {
                    arrayList.add(next);
                }
            }
            double d = 0.0d;
            int i = 0;
            for (WorkoutItem workoutItem : arrayList) {
                if (workoutItem != null) {
                    FitnessSession fitnessSession = ((ListItem) workoutItem).getFitnessSession();
                    if (FormatUtilKt.getFormattedDateInMs(FormatUtilKt.convertUTCToLocalTimeZone(fitnessSession.getStartTime())) >= longValue) {
                        weeklySummary.setNumOfWorkouts(weeklySummary.getNumOfWorkouts() + 1);
                        weeklySummary.setTotalActiveWorkoutTimeInSeconds(weeklySummary.getTotalActiveWorkoutTimeInSeconds() + (fitnessSession.getActivitySummary().getDurationInMs() / 1000));
                        Double caloriesInKcal = fitnessSession.getActivitySummary().getCaloriesInKcal();
                        if (caloriesInKcal != null) {
                            j = longValue;
                            if (caloriesInKcal.doubleValue() > 0) {
                                d += caloriesInKcal.doubleValue();
                                weeklySummary.setTotalCalories(Double.valueOf(d));
                            }
                        } else {
                            j = longValue;
                        }
                        weeklySummary.setTotalDistanceInMeters(fitnessSession.getActivitySummary().getDistanceInMeters() + weeklySummary.getTotalDistanceInMeters());
                        weeklySummary.setTotalSteps(weeklySummary.getTotalSteps() + fitnessSession.getActivitySummary().getStepCount());
                        weeklySummary.setAveragePace(fitnessSession.getActivitySummary().getPaceInMinutesPerKm() + weeklySummary.getAveragePace());
                        if (fitnessSession.getActivitySummary().getPaceInMinutesPerKm() > 0) {
                            i++;
                        }
                        Intrinsics.checkExpressionValueIsNotNull(calendar, "calendar");
                        calendar.setTime(new Date(FormatUtilKt.convertUTCToLocalTimeZoneDateTime(fitnessSession.getStartTime()).getEpochMilli()));
                        weeklySummary.getActiveDays().add(Integer.valueOf(calendar.get(7)));
                    } else {
                        j = longValue;
                    }
                    longValue = j;
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.view.workoutTab.ListItem");
                }
            }
            if (i != 0) {
                weeklySummary.setAveragePace(weeklySummary.getAveragePace() / i);
            }
        }
        return weeklySummary;
    }

    public final AfitsClient getAfitsClient() {
        return this.afitsClient;
    }

    public final int getEndOfList() {
        return this.workoutHistoryList.size() - 1;
    }

    @Nullable
    public final String getNextToken() {
        return this.nextToken;
    }

    @NotNull
    public final List<WorkoutItem> getWorkoutHistoryList() {
        return this.workoutHistoryList;
    }

    public final boolean hasLocalWorkoutSessions() {
        return !this.workoutHistoryList.isEmpty();
    }

    public final void initializeWorkoutSessions(@NotNull GetAllFitnessSessionResponse response) {
        List<FitnessSession> list;
        Intrinsics.checkParameterIsNotNull(response, "response");
        this.workoutHistoryList = new ArrayList();
        List<WorkoutItem> list2 = this.workoutHistoryList;
        list = ArraysKt___ArraysKt.toList(response.getFitnessSessions());
        list2.addAll(transformAFITSData(list));
        String nextToken = response.getNextToken();
        if (nextToken != null) {
            this.nextToken = nextToken;
        }
    }

    public final void setNextToken(@Nullable String str) {
        this.nextToken = str;
    }

    public final void setWorkoutHistoryList(@NotNull List<WorkoutItem> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.workoutHistoryList = list;
    }

    @NotNull
    public List<WorkoutItem> transformAFITSData(@NotNull List<FitnessSession> fitnessHistory) {
        Intrinsics.checkParameterIsNotNull(fitnessHistory, "fitnessHistory");
        ArrayList arrayList = new ArrayList();
        String str = "";
        for (FitnessSession fitnessSession : fitnessHistory) {
            if (arrayList.isEmpty()) {
                str = FormatUtilKt.convertUTCToLocalTimeZone(fitnessSession.getStartTime());
                arrayList.add(new HeaderItem(str));
            } else {
                String convertUTCToLocalTimeZone = FormatUtilKt.convertUTCToLocalTimeZone(fitnessSession.getStartTime());
                if (!FormatUtilKt.getFormattedDate(str).equals(FormatUtilKt.getFormattedDate(convertUTCToLocalTimeZone))) {
                    arrayList.add(new HeaderItem(convertUTCToLocalTimeZone));
                    str = convertUTCToLocalTimeZone;
                }
            }
            arrayList.add(new ListItem(fitnessSession));
        }
        return arrayList;
    }
}
