package com.cursoalura.challenge.principal;

import com.cursoalura.challenge.clases.ConsultaConversion;
import com.cursoalura.challenge.clases.SelectorMoneda;
import com.cursoalura.challenge.records.StandardResponse;
import com.cursoalura.challenge.records.SupportedCodes;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ConsultaConversion consultaConversion = new ConsultaConversion();
        SelectorMoneda selectorMoneda = new SelectorMoneda();
        SupportedCodes supportedCodes;
        StandardResponse standardResponse;
        int opcion;
        String origen, destino;

        String tipoConversion = """
                Ingresa el número de la opción que deseas ver:
                
                1.- Listar tipo de cambio de una moneda.
                2.- Convertir de una moneda a otra.
                3.- Salir
                """;

        while (true) {

            String entrada = JOptionPane.showInputDialog(null, tipoConversion, "Conversor de Moneda", JOptionPane.INFORMATION_MESSAGE);
            if (entrada == null || entrada.equals("")) return;
            opcion = Integer.parseInt(entrada);

            switch (opcion) {
                case 1:
                    supportedCodes = consultaConversion.supportedCodes();
                    selectorMoneda.listSupportedCodes(supportedCodes);

                    origen = selectorMoneda.seleccionarMoneda("ORIGEN");
                    if (origen == null) return;

                    standardResponse = consultaConversion.standardResponse(origen);
                    selectorMoneda.listStandarResponse(standardResponse);
                    break;

                case 2:
                    supportedCodes = consultaConversion.supportedCodes();
                    selectorMoneda.listSupportedCodes(supportedCodes);

                    String[] parMonedas = selectorMoneda.seleccionarParDeMonedas();
                    if (parMonedas == null) return;

                    origen = parMonedas[0];
                    destino = parMonedas[1];

                    String pedirMonto = JOptionPane.showInputDialog("Ingresa monto en " + origen + ": ");
                    if (pedirMonto == null) return;
                    double monto = Double.parseDouble(pedirMonto);

                    standardResponse = consultaConversion.standardResponse(origen);
                    selectorMoneda.listarPairConversion(standardResponse, origen, destino, monto);

                    break;

                case 3:
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    System.exit(0);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Selecciono una opción incorrecta!");
                    break;
            }
        }
    }
}
