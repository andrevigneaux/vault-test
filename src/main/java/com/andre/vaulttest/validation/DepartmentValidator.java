package com.andre.vaulttest.validation;

import com.andre.vaulttest.util.DateUtil;
import com.andre.vaulttest.validation.generic.GenericValidation;
import com.andre.vaulttest.validation.generic.Validation;

import java.util.Date;

public class DepartmentValidator {
    private static Double firstFortnightAvgSalaryValidation = 1000D;
    private static Double secondFortnightAvgSalaryValidation = 1500D;

    public static final Validation<Date> firstFortnightDate =
            GenericValidation.from(d -> DateUtil.isDateTheFirstFortnight(d));

    public static final Validation<Double> firstFortnightSalary =
            GenericValidation.from(s -> s < firstFortnightAvgSalaryValidation);
    public static final Validation<Double> secondFortnightSalary =
            GenericValidation.from(s -> s < secondFortnightAvgSalaryValidation);
}
