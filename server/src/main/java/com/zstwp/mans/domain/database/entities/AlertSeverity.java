package com.zstwp.mans.domain.database.entities;

import java.util.HashMap;
import java.util.Map;

public enum AlertSeverity {
    EMERGENCY("emerg", 0),
    ALERT("alert", 1),
    CRITICAL("crit", 2),
    ERROR("err", 3),
    WARNING("warning", 4),
    NOTICE("notice", 5),
    INFORMATIONAL("info", 6),
    DEBUG("debug", 7);

    private static final Map<String, AlertSeverity> BY_LABEL = new HashMap<>();
    private static final Map<Integer, AlertSeverity> BY_ATOMIC_NUMBER = new HashMap<>();

    static {
        for(AlertSeverity s : values()) {
            BY_LABEL.put(s.label, s);
            BY_ATOMIC_NUMBER.put(s.atomicNumber, s);
        }
    }

    private final String label;
    private final int atomicNumber;

    private AlertSeverity(String label, int atomicNumber) {
        this.label = label;
        this.atomicNumber = atomicNumber;
    }

    public static AlertSeverity valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

    public static AlertSeverity valueOfAtomicNumber(int number) {
        return BY_ATOMIC_NUMBER.get(number);
    }
}
