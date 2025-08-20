package com.cursoalura.challenge.records;

public record PairConversion(String base_code,
                             String target_code,
                             double conversion_rate,
                             double  conversion_result) {

    public  String prettyPrint(double amount){
        return String.format("%.2f %s = %.2f %s",
                amount, base_code, conversion_result, target_code);
    }
}
