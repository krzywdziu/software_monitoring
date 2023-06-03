package com.zstwp.mans.database.entities;

import java.util.Arrays;

public enum AlertStatus {
    UNASSIGNED("unassigned"),
    IN_PROGRESS("in_progress"),
    RESOLVED("resolved"),
    WONT_FIX("wont_fix");

    private String value;

    private AlertStatus(String value) {
        this.value = value;
    }

    public static AlertStatus fromValue(String value) {
        for (AlertStatus category : values()) {
            if (category.value.equalsIgnoreCase(value)) {
                return category;
            }
        }
        throw new IllegalArgumentException(
                "Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
    }
}
