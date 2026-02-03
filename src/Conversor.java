import com.google.gson.JsonObject;

import java.util.Scanner;

public class Conversor {
    public static void exibirMenu(){

        Scanner lectora = new Scanner(System.in);
        FiltrarMoneda filtro = new FiltrarMoneda();
        ConvertirMoneda conversor = new ConvertirMoneda();

        JsonObject rates = filtro.filtrarMoneda("USD");

        int opcion = 0;

        while (opcion != 7) {
            System.out.println("""
                *************************************************************
                Bienvendio/a al Conversor de Monedas =]
                
                1) Dólar =>> Peso argentino
                2) Peso argentino =>> Dólar
                3) Dólar =>> Real brasileño
                4) Real brasileño =>> Dólar
                5) Dólar =>> Peso colombiano
                6) Peso colombiano =>> Dólar
                7) Salir
                Elija una opción válida:
                *************************************************************
                """);

            opcion = lectora.nextInt();

            if (opcion >= 1 && opcion <= 6) {
                System.out.println("Ingrese el monto que desea convertir: ");
                double monto = lectora.nextDouble();

                double resultado = 0;

                String monedaOrigen = "";
                String monedaDestino = "";
                switch (opcion) {
                    case 1 -> {
                        monedaOrigen = "USD";
                        monedaDestino = "ARS";
                        resultado = conversor.conversion(
                            monto,
                            rates.get("USD").getAsDouble(),
                            rates.get("ARS").getAsDouble()
                    );
                    }
                    case 2 -> {
                        monedaOrigen = "ARS";
                        monedaDestino = "USD";
                        resultado = conversor.conversion(
                            monto,
                            rates.get("ARS").getAsDouble(),
                            rates.get("USD").getAsDouble()
                    );
                    }
                    case 3 -> {
                        monedaOrigen = "USD";
                        monedaDestino = "BRL";
                        resultado = conversor.conversion(
                            monto,
                            rates.get("USD").getAsDouble(),
                            rates.get("BRL").getAsDouble()
                    );
                    }
                    case 4 -> {
                        monedaOrigen = "BRL";
                        monedaDestino = "USD";
                        resultado = conversor.conversion(
                            monto,
                            rates.get("BRL").getAsDouble(),
                            rates.get("USD").getAsDouble()
                    );
                    }
                    case 5 -> {
                        monedaOrigen = "USD";
                        monedaDestino = "COP";
                        resultado = conversor.conversion(
                            monto,
                            rates.get("USD").getAsDouble(),
                            rates.get("COP").getAsDouble()
                    );
                    }
                    case 6 -> {
                        monedaOrigen = "COP";
                        monedaDestino = "USD";
                        resultado = conversor.conversion(
                            monto,
                            rates.get("COP").getAsDouble(),
                            rates.get("USD").getAsDouble()
                    );
                    }
                }
                System.out.printf(
                        "El valor %.2f [%s] corresponde al valor final de =>>> %.2f [%s]%n",
                        monto, monedaOrigen, resultado, monedaDestino
                );
            }
        }

        System.out.println("Gracias por usar el conversor");
        lectora.close();
    }
}
