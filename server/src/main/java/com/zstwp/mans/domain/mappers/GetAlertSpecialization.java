package com.zstwp.mans.domain.mappers;


import com.zstwp.mans.domain.database.entities.UserSpecialization;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class GetAlertSpecialization {

//    we need some rule to map alert to a certain specialization
    private static final Random PRNG = new Random();

    public static UserSpecialization getRandomSpecialization() {
        List<UserSpecialization> specializations = Arrays.asList(UserSpecialization.values());

        return specializations.get(PRNG.nextInt(specializations.size()));
    }
}
