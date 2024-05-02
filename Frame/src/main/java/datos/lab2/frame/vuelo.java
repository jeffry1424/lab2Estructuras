package datos.lab2.frame;

public class vuelo {

    aeropuerto aero1, aero2;
    double peso;

    public vuelo(aeropuerto aero1, aeropuerto aero2) {
        this.aero1 = aero1;
        this.aero2 = aero2;
        this.peso = this.haversine(aero1.latitud, aero1.longitud, aero2.latitud, aero2.longitud);
    }

    public final double R = 6371.0; // Radio de la Tierra en kilómetros

    public double haversine(double lat1, double lon1, double lat2, double lon2) {
        // Convertir grados a radianes
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Fórmula del haversine
        double a = Math.pow(Math.sin(dLat / 2), 2)
                + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distancia = R * c;

        return distancia;
    }
}
