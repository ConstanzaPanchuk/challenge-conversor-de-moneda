import com.google.gson.JsonObject;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        FiltrarMoneda filtro = new FiltrarMoneda();
        JsonObject rates = filtro.filtrarMoneda("USD");
        System.out.println(rates);


        double tasaBRL = rates.get("USD").getAsDouble();
        double tasaARS = rates.get("ARS").getAsDouble();

        ConvertirMoneda conversor = new ConvertirMoneda();

        double resultado = conversor.conversion(
                1,
                tasaBRL,
                tasaARS
        );

        System.out.println("100 BRL equivalen a " + resultado + " ARS");

    }
}


