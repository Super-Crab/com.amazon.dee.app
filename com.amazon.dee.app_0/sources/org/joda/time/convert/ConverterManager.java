package org.joda.time.convert;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.joda.time.JodaTimePermission;
/* loaded from: classes5.dex */
public final class ConverterManager {
    private static ConverterManager INSTANCE;
    private ConverterSet iInstantConverters = new ConverterSet(new Converter[]{ReadableInstantConverter.INSTANCE, StringConverter.INSTANCE, CalendarConverter.INSTANCE, DateConverter.INSTANCE, LongConverter.INSTANCE, NullConverter.INSTANCE});
    private ConverterSet iPartialConverters = new ConverterSet(new Converter[]{ReadablePartialConverter.INSTANCE, ReadableInstantConverter.INSTANCE, StringConverter.INSTANCE, CalendarConverter.INSTANCE, DateConverter.INSTANCE, LongConverter.INSTANCE, NullConverter.INSTANCE});
    private ConverterSet iDurationConverters = new ConverterSet(new Converter[]{ReadableDurationConverter.INSTANCE, ReadableIntervalConverter.INSTANCE, StringConverter.INSTANCE, LongConverter.INSTANCE, NullConverter.INSTANCE});
    private ConverterSet iPeriodConverters = new ConverterSet(new Converter[]{ReadableDurationConverter.INSTANCE, ReadablePeriodConverter.INSTANCE, ReadableIntervalConverter.INSTANCE, StringConverter.INSTANCE, NullConverter.INSTANCE});
    private ConverterSet iIntervalConverters = new ConverterSet(new Converter[]{ReadableIntervalConverter.INSTANCE, StringConverter.INSTANCE, NullConverter.INSTANCE});

    protected ConverterManager() {
    }

    private void checkAlterDurationConverters() throws SecurityException {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new JodaTimePermission("ConverterManager.alterDurationConverters"));
        }
    }

    private void checkAlterInstantConverters() throws SecurityException {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new JodaTimePermission("ConverterManager.alterInstantConverters"));
        }
    }

    private void checkAlterIntervalConverters() throws SecurityException {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new JodaTimePermission("ConverterManager.alterIntervalConverters"));
        }
    }

    private void checkAlterPartialConverters() throws SecurityException {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new JodaTimePermission("ConverterManager.alterPartialConverters"));
        }
    }

    private void checkAlterPeriodConverters() throws SecurityException {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            securityManager.checkPermission(new JodaTimePermission("ConverterManager.alterPeriodConverters"));
        }
    }

    public static ConverterManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConverterManager();
        }
        return INSTANCE;
    }

    public DurationConverter addDurationConverter(DurationConverter durationConverter) throws SecurityException {
        checkAlterDurationConverters();
        if (durationConverter == null) {
            return null;
        }
        DurationConverter[] durationConverterArr = new DurationConverter[1];
        this.iDurationConverters = this.iDurationConverters.add(durationConverter, durationConverterArr);
        return durationConverterArr[0];
    }

    public InstantConverter addInstantConverter(InstantConverter instantConverter) throws SecurityException {
        checkAlterInstantConverters();
        if (instantConverter == null) {
            return null;
        }
        InstantConverter[] instantConverterArr = new InstantConverter[1];
        this.iInstantConverters = this.iInstantConverters.add(instantConverter, instantConverterArr);
        return instantConverterArr[0];
    }

    public IntervalConverter addIntervalConverter(IntervalConverter intervalConverter) throws SecurityException {
        checkAlterIntervalConverters();
        if (intervalConverter == null) {
            return null;
        }
        IntervalConverter[] intervalConverterArr = new IntervalConverter[1];
        this.iIntervalConverters = this.iIntervalConverters.add(intervalConverter, intervalConverterArr);
        return intervalConverterArr[0];
    }

    public PartialConverter addPartialConverter(PartialConverter partialConverter) throws SecurityException {
        checkAlterPartialConverters();
        if (partialConverter == null) {
            return null;
        }
        PartialConverter[] partialConverterArr = new PartialConverter[1];
        this.iPartialConverters = this.iPartialConverters.add(partialConverter, partialConverterArr);
        return partialConverterArr[0];
    }

    public PeriodConverter addPeriodConverter(PeriodConverter periodConverter) throws SecurityException {
        checkAlterPeriodConverters();
        if (periodConverter == null) {
            return null;
        }
        PeriodConverter[] periodConverterArr = new PeriodConverter[1];
        this.iPeriodConverters = this.iPeriodConverters.add(periodConverter, periodConverterArr);
        return periodConverterArr[0];
    }

    public DurationConverter getDurationConverter(Object obj) {
        DurationConverter durationConverter = (DurationConverter) this.iDurationConverters.select(obj == null ? null : obj.getClass());
        if (durationConverter != null) {
            return durationConverter;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No duration converter found for type: ");
        outline107.append(obj == null ? "null" : obj.getClass().getName());
        throw new IllegalArgumentException(outline107.toString());
    }

    public DurationConverter[] getDurationConverters() {
        ConverterSet converterSet = this.iDurationConverters;
        DurationConverter[] durationConverterArr = new DurationConverter[converterSet.size()];
        converterSet.copyInto(durationConverterArr);
        return durationConverterArr;
    }

    public InstantConverter getInstantConverter(Object obj) {
        InstantConverter instantConverter = (InstantConverter) this.iInstantConverters.select(obj == null ? null : obj.getClass());
        if (instantConverter != null) {
            return instantConverter;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No instant converter found for type: ");
        outline107.append(obj == null ? "null" : obj.getClass().getName());
        throw new IllegalArgumentException(outline107.toString());
    }

    public InstantConverter[] getInstantConverters() {
        ConverterSet converterSet = this.iInstantConverters;
        InstantConverter[] instantConverterArr = new InstantConverter[converterSet.size()];
        converterSet.copyInto(instantConverterArr);
        return instantConverterArr;
    }

    public IntervalConverter getIntervalConverter(Object obj) {
        IntervalConverter intervalConverter = (IntervalConverter) this.iIntervalConverters.select(obj == null ? null : obj.getClass());
        if (intervalConverter != null) {
            return intervalConverter;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No interval converter found for type: ");
        outline107.append(obj == null ? "null" : obj.getClass().getName());
        throw new IllegalArgumentException(outline107.toString());
    }

    public IntervalConverter[] getIntervalConverters() {
        ConverterSet converterSet = this.iIntervalConverters;
        IntervalConverter[] intervalConverterArr = new IntervalConverter[converterSet.size()];
        converterSet.copyInto(intervalConverterArr);
        return intervalConverterArr;
    }

    public PartialConverter getPartialConverter(Object obj) {
        PartialConverter partialConverter = (PartialConverter) this.iPartialConverters.select(obj == null ? null : obj.getClass());
        if (partialConverter != null) {
            return partialConverter;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No partial converter found for type: ");
        outline107.append(obj == null ? "null" : obj.getClass().getName());
        throw new IllegalArgumentException(outline107.toString());
    }

    public PartialConverter[] getPartialConverters() {
        ConverterSet converterSet = this.iPartialConverters;
        PartialConverter[] partialConverterArr = new PartialConverter[converterSet.size()];
        converterSet.copyInto(partialConverterArr);
        return partialConverterArr;
    }

    public PeriodConverter getPeriodConverter(Object obj) {
        PeriodConverter periodConverter = (PeriodConverter) this.iPeriodConverters.select(obj == null ? null : obj.getClass());
        if (periodConverter != null) {
            return periodConverter;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("No period converter found for type: ");
        outline107.append(obj == null ? "null" : obj.getClass().getName());
        throw new IllegalArgumentException(outline107.toString());
    }

    public PeriodConverter[] getPeriodConverters() {
        ConverterSet converterSet = this.iPeriodConverters;
        PeriodConverter[] periodConverterArr = new PeriodConverter[converterSet.size()];
        converterSet.copyInto(periodConverterArr);
        return periodConverterArr;
    }

    public DurationConverter removeDurationConverter(DurationConverter durationConverter) throws SecurityException {
        checkAlterDurationConverters();
        if (durationConverter == null) {
            return null;
        }
        DurationConverter[] durationConverterArr = new DurationConverter[1];
        this.iDurationConverters = this.iDurationConverters.remove(durationConverter, durationConverterArr);
        return durationConverterArr[0];
    }

    public InstantConverter removeInstantConverter(InstantConverter instantConverter) throws SecurityException {
        checkAlterInstantConverters();
        if (instantConverter == null) {
            return null;
        }
        InstantConverter[] instantConverterArr = new InstantConverter[1];
        this.iInstantConverters = this.iInstantConverters.remove(instantConverter, instantConverterArr);
        return instantConverterArr[0];
    }

    public IntervalConverter removeIntervalConverter(IntervalConverter intervalConverter) throws SecurityException {
        checkAlterIntervalConverters();
        if (intervalConverter == null) {
            return null;
        }
        IntervalConverter[] intervalConverterArr = new IntervalConverter[1];
        this.iIntervalConverters = this.iIntervalConverters.remove(intervalConverter, intervalConverterArr);
        return intervalConverterArr[0];
    }

    public PartialConverter removePartialConverter(PartialConverter partialConverter) throws SecurityException {
        checkAlterPartialConverters();
        if (partialConverter == null) {
            return null;
        }
        PartialConverter[] partialConverterArr = new PartialConverter[1];
        this.iPartialConverters = this.iPartialConverters.remove(partialConverter, partialConverterArr);
        return partialConverterArr[0];
    }

    public PeriodConverter removePeriodConverter(PeriodConverter periodConverter) throws SecurityException {
        checkAlterPeriodConverters();
        if (periodConverter == null) {
            return null;
        }
        PeriodConverter[] periodConverterArr = new PeriodConverter[1];
        this.iPeriodConverters = this.iPeriodConverters.remove(periodConverter, periodConverterArr);
        return periodConverterArr[0];
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ConverterManager[");
        outline107.append(this.iInstantConverters.size());
        outline107.append(" instant,");
        outline107.append(this.iPartialConverters.size());
        outline107.append(" partial,");
        outline107.append(this.iDurationConverters.size());
        outline107.append(" duration,");
        outline107.append(this.iPeriodConverters.size());
        outline107.append(" period,");
        return GeneratedOutlineSupport1.outline86(outline107, this.iIntervalConverters.size(), " interval]");
    }
}
