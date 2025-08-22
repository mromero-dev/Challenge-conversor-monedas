package com.cursoalura.challenge.clases;

import com.cursoalura.challenge.records.StandardResponse;
import com.cursoalura.challenge.records.SupportedCodes;

import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class SelectorMoneda {

    private Map<String, String> monedaCiudad;
    private String[] listado;

    public void listSupportedCodes(SupportedCodes supportedCodes) {

        this.monedaCiudad = supportedCodes.supported_codes()
                .stream()
                .collect(Collectors.toMap(
                        item -> item.get(0),
                        item -> item.get(1),
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));

        this.listado = monedaCiudad.entrySet()
                .stream()
                .map(e -> e.getKey() + " - " + e.getValue())
                .toArray(String[]::new);
    }

    public String seleccionarMoneda(String tituloSeleccion) {

        if (listado == null || listado.length == 0) {
            JOptionPane.showMessageDialog(null, "No hay monedas disponibles",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        String seleccion = (String) JOptionPane.showInputDialog(
                null,
                "Selecciona la moneda de: " + tituloSeleccion + ":",
                "Conversor de monedas",
                JOptionPane.QUESTION_MESSAGE,
                null,
                this.listado,
                this.listado[0]
        );

        if (seleccion == null) {
            return null;
        }
        return seleccion.split(" - ")[0];
    }

    public String[] seleccionarParDeMonedas() {
        String origen = seleccionarMoneda("ORIGEN");
        if (origen == null) return null;

        String destino = seleccionarMoneda("DESTINO");
        if (destino == null) return null;

        return new String[]{origen, destino};
    }

    public void listStandarResponse(StandardResponse standardResponse) {
        if (standardResponse == null) {
            JOptionPane.showMessageDialog(null, "No se pudo obtener información.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("Tasas de conversión para: " + standardResponse.base_code() + "\n\n");

        standardResponse.conversion_rates().forEach((moneda, valor) -> {
            sb.append(moneda).append(": ").append(valor).append("\n");
        });

        JTextArea textArea = new JTextArea(sb.toString(), 20, 20);
        textArea.setEditable(false);

        JScrollPane jScrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, jScrollPane, "Conversiones",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void listarPairConversion(StandardResponse standardResponse, String origen, String destino, double monto) {
        if (standardResponse == null) {
            JOptionPane.showMessageDialog(null, "No se pudo obtener información.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Double tasaDestino = standardResponse.conversion_rates().get(destino);

        if (tasaDestino == null) {
            JOptionPane.showMessageDialog(null, "No se encontró la moneda destino.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double resultado = monto * tasaDestino;

        String mensajeSalida = String.format(
                "%.2f %s = %.2f %s",
                monto, origen, resultado, destino
        );

        JOptionPane.showMessageDialog(null, mensajeSalida,
                "Resultado de Conversión",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
