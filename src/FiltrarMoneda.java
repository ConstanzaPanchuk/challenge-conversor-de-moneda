import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FiltrarMoneda {

    public void filtrarMoneda(String monedaBase) {
        URI direccion = URI.create(
                "https://v6.exchangerate-api.com/v6/86a83205f8687efed7846183/latest/" + monedaBase);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        {
            try {
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                JsonObject json = JsonParser
                        .parseString(response.body())
                        .getAsJsonObject();
                JsonObject conversionRates = json.getAsJsonObject("conversion_rates");

                String[] monedasFiltradas = {
                        "ARS", "BOB", "BRL", "CLP", "COP", "USD"
                };

                for (String codigo : monedasFiltradas) {
                    double valor = conversionRates.get(codigo).getAsDouble();
                    System.out.println(codigo + " : " + valor);
                }

            } catch (Exception e) {
                throw new RuntimeException("Error al filtrar monedas");
            }
        }
    }
}
