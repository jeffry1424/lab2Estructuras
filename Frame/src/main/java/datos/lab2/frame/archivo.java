package datos.lab2.frame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class archivo {

    BufferedReader lector;
    String linea, partes[];
    public static ArrayList<aeropuerto> aeropuertos = new ArrayList<>();
    public ArrayList<vuelo> vuelos = new ArrayList<>();
    public static double[][] matriz_final;
    public static ArrayList<String> codigos = new ArrayList<>();

    public void leercsv(String name) {
        try {
            lector = new BufferedReader(new FileReader(name));
            int cont = 0;
            while ((linea = lector.readLine()) != null) {
                if (cont != 0) {
                    String aux1 = "", aux2 = "";
                    aux1 = linea.substring(0, 1);
                    aux2 = linea.substring(linea.length() - 1);
                    linea = linea.substring(1, linea.length() - 1);
                    linea = linea.replaceAll("\"\"", "\"");
                    partes = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    ArrayList<String> partesFiltradas = new ArrayList<>();
                    for (String parte : partes) {
                        if (!parte.equals("\"")) {
                            partesFiltradas.add(parte);
                        }
                    }
                    String[] partesFiltradasArray = partesFiltradas.toArray(new String[0]);
                    partes = partesFiltradasArray;
                    for (int i = 0; i < partesFiltradasArray.length; i++) {
                        partesFiltradasArray[i] = partesFiltradasArray[i].replace("\"", "");
                    }
                    if (!aux1.equals("\"") || !aux2.equals("\"")) {
                        partes[0] = aux1 + partes[0];
                        partes[11] = partes[11] + aux2;
                    }
                    if (aero_repetido(partes[1])/*new aeropuerto(partes[0], partes[1], partes[2], partes[3], Double.parseDouble(partes[4]), Double.parseDouble(partes[5])))*/ == false) {
                        aeropuertos.add(new aeropuerto(partes[0], partes[1], partes[2], partes[3], Double.parseDouble(partes[4]), Double.parseDouble(partes[5])));
                    } else {
//                        System.out.println("\nEl aeropuerto " + partes[0] + " ya existe\n");
                    }
                    if (aero_repetido(partes[7]/*new aeropuerto(partes[6], partes[7], partes[8], partes[9], Double.parseDouble(partes[10]), Double.parseDouble(partes[11])))*/) == false) {
                        aeropuertos.add(new aeropuerto(partes[6], partes[7], partes[8], partes[9], Double.parseDouble(partes[10]), Double.parseDouble(partes[11])));
                    } else {
//                        System.out.println("\nEl aeropuerto " + partes[6] + " ya existe\n");
                    }
                    vuelos.add(new vuelo(new aeropuerto(partes[0], partes[1], partes[2], partes[3], Double.parseDouble(partes[4]), Double.parseDouble(partes[5])), new aeropuerto(partes[6], partes[7], partes[8], partes[9], Double.parseDouble(partes[10]), Double.parseDouble(partes[11]))));
                    vuelo ultimoElemento = vuelos.get(vuelos.size() - 1);
                    ultimoElemento.aero1.añadirVuelo(ultimoElemento);
                    ultimoElemento.aero2.añadirVuelo(ultimoElemento);
//                    mostrarlinea2();
                } else {
                    cont++;
                }
            }
            lector.close();
            linea = null;
            partes = null;
            cont = 0;
//            mostrar_aeros();
            creacion_matriz();
            imprimirMatriz(matriz_final);
        } catch (Exception e) {
            System.out.println("\nError");
            System.out.println(e.getMessage());
            System.out.println(e.getClass().getName());
        }
    }

    public boolean aero_repetido(String name) {
        for (aeropuerto i : aeropuertos) {
            if (i.nombre.equalsIgnoreCase(name)) {
                return true;
            }
        }
//        if (aeropuertos.contains(aero) == true) {
//            return true;
//        }
        return false;
    }

    public void creacion_matriz() {
//        System.out.println("Adentro de creacion de matriz");
        double[][] matriz = new double[aeropuertos.size()][aeropuertos.size()];
        aeropuerto[] aeropuertosV = aeropuertos.toArray(new aeropuerto[aeropuertos.size()]);
        for (int i = 0; i < aeropuertosV.length - 1; i++) {
            aeropuerto aero = aeropuertosV[i];
            for (vuelo vuelito : vuelos) {
                if (aero.nombre.equals(vuelito.aero1.nombre)) {
                    matriz[i][pos_aero(vuelito.aero2.nombre)] = vuelito.peso;
//                    System.out.println("\nEl peso actualizado\n");
                }
            }
//            System.out.println("\nFila del aeropuerto " + aero.nombre + " terminada\n");
        }
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == 0.0) {
                    matriz[i][j] = 999999.0;
                }
                if (i == j) {
                    matriz[i][j] = 0.0;
                }
            }
        }
        for (aeropuerto i : aeropuertos) {
            codigos.add(i.codigo);
        }
        matriz_final = matriz;
    }

    public int pos_aero(String name) {
        int cont = 0;
        for (aeropuerto i : aeropuertos) {
            if (name.equals(i.nombre)) {
                return cont;
            }
            cont++;
        }
        return cont;
    }

    public void imprimirMatriz(double[][] matriz) {
        System.out.println("--------Matriz--------");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    public void mostrar_aeros() {
        System.out.println("\n--------Aeropuertos--------");
        for (aeropuerto i : aeropuertos) {
            System.out.println(i.nombre);
        }
        System.out.println("\n");
    }

    public void mostrar_vuelos() {
        System.out.println("\n--------Vuelos--------");
        for (vuelo i : vuelos) {
            System.out.println(i.aero1.nombre + " ---> " + i.aero2.nombre + " |-> Distancia: " + i.peso);
        }
        System.out.println("\n");
    }

    public void mostrarlinea2() {
        for (String i : partes) {
            System.out.print(i + " | ");
        }
        System.out.println(" ");
    }

}
