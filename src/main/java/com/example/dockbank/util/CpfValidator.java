package com.example.dockbank.util;

public class CpfValidator {
    public static boolean isValid(String cpf) {
        if (cpf == null) return false;
        String cleaned = cpf.replaceAll("\\D", "");
        if (cleaned.length() != 11) return false;
        // checar repetidos
        if (cleaned.chars().distinct().count() == 1) return false;

        try {
            int[] nums = cleaned.chars().map(c -> c - '0').toArray();
            // calculo do primeiro digito
            int sum = 0;
            for (int i = 0; i < 9; i++) sum += nums[i] * (10 - i);
            int dv1 = sum % 11 < 2 ? 0 : 11 - (sum % 11);
            if (dv1 != nums[9]) return false;

            sum = 0;
            for (int i = 0; i < 10; i++) sum += nums[i] * (11 - i);
            int dv2 = sum % 11 < 2 ? 0 : 11 - (sum % 11);
            return dv2 == nums[10];
        } catch (Exception e) {
            return false;
        }
    }
}

