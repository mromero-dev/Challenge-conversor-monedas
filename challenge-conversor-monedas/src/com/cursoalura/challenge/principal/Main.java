package com.cursoalura.challenge.principal;

import com.cursoalura.challenge.clases.ConsultaConversion;
import com.cursoalura.challenge.clases.SelectorMoneda;
import com.cursoalura.challenge.records.StandardResponse;
import com.cursoalura.challenge.records.SupportedCodes;

import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        ConsultaConversion consultaConversion = new ConsultaConversion();
        SelectorMoneda selectorMoneda = new SelectorMoneda();

        SupportedCodes supportedCodes = consultaConversion.supportedCodes();
        selectorMoneda.listSupportedCodes(supportedCodes);

        String origen = selectorMoneda.seleccionarMoneda("ORIGEN");
        if (origen == null) return;

        StandardResponse standardResponse = consultaConversion.standardResponse(origen);
        selectorMoneda.listStandarResponse(standardResponse);
    }
}
