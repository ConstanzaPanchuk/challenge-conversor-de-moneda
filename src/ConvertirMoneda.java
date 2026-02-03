public class ConvertirMoneda {
    public double conversion(double monto, double monedaOrigen, double monedaDestino){
        return monto * (monedaDestino / monedaOrigen);
    }
}
