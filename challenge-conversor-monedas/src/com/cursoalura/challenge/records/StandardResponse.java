package com.cursoalura.challenge.records;

import java.util.Map;

public record StandardResponse(String base_code,
                               Map<String, Double> conversion_rates) {
}
