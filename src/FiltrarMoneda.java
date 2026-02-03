import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FiltrarMoneda {

    public JsonObject filtrarMoneda(String monedaBase) {
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

                String[] monedasPrincipales = {
                        "ARS", "BOB", "BRL", "CLP", "COP", "USD"
                };
                JsonObject monedasFiltradas = new JsonObject();

                for (String codigo : monedasPrincipales) {
                    monedasFiltradas.addProperty(codigo,
                            conversionRates.get(codigo).getAsDouble());
                }

                return monedasFiltradas;

            } catch (Exception e) {
                throw new RuntimeException("Error al filtrar monedas");
            }
        }
    }
}
