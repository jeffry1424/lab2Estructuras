/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.lab2.frame;

import static datos.lab2.frame.archivo.aeropuertos;


/**
 *
 * @author MIGUEL MARQUEZ
 */
public class Dijstra2F3 {

    private double[][] Pesos; // Matriz de costos
    private int[] ultimo;  // Array para almacenar los vértices previos en el camino mínimo
    private double[] D;       // Array para almacenar las distancias mínimas
    private boolean[] F;   // Array para marcar los vértices visitados
    private int s, n;      // Vértice origen y número de vértices
    public int[] may = new int[10];
    public static String hola = "";
    public Dijstra2F3(double[][] matrizCostos, int origen) {
        n = matrizCostos.length; // Obtener el número de vértices
        Pesos = matrizCostos;
        ultimo = new int[n];
        D = new double[n];
        F = new boolean[n];
        s = origen;
    }

    public void caminoMinimos() {
        // Inicialización de valores
        for (int i = 0; i < n; i++) {
            F[i] = false;
            D[i] = Pesos[s][i];

            ultimo[i] = s;
//            System.out.println("f=" + F[i] + " " + i);
//            System.out.println("d=" + D[i]);
//            System.out.println("p=" + Pesos[s][i]);
//            System.out.println("ul=" + ultimo[i]);
        }
        F[s] = true;
        D[s] = 0;

        // Pasos para marcar los n-1 vértices
        for (int i = 1; i < n; i++) {
            int v = minimo(); // Selecciona vértice no marcado de menor distancia
            F[v] = true;

            // Actualiza distancia de vértices no marcados
            for (int w = 0; w < n; w++) {
                if (!F[w] && (D[v] + Pesos[v][w] < D[w])) {
                    D[w] = D[v] + Pesos[v][w];
                    ultimo[w] = v;
                }
            }
        }
    }

    private int minimo() {
        double mx = Integer.MAX_VALUE;
        int v = -1;
//        System.out.println("mx" + mx);
        for (int j = 0; j < n; j++) {
//            System.out.println("jf" + j);
//            System.out.println("f" + F[j]);
            if (!F[j] && D[j] <= mx) {
//                System.out.println("mx" + mx);
//                System.out.println("d" + D[j]);
//                System.out.println("j" + j);
                mx = D[j];
                v = j;
            }
        }
//        System.out.println("v" + v);
        return v;
    }

    public void si(int origen, aeropuerto[] v, double[][] m) {
        mayores(D);
        for (int i = 0; i < 10; i++) {
            hola+=("Distancia mínima desde " + v[origen].nombre + " a " + v[may[i]].nombre + ": " + D[may[i]]+"\n");
//            System.out.println("Camino mínimo: " + caminoMinimo(origen, i, v));
            hola+=(v[may[i]].mostrar_info() + "\n\n");
        }
//        for (int i : may) {
//            System.out.println("Camino mínimo: " + caminoMinimo(origen, i, v));
//        }

//        for (int i = 0; i < D.length; i++) {
//            if (D[i] != 9999.0) {
//                System.out.println("Distancia mínima desde " + v[origen].codigo + " a " + v[i].codigo + ": " + D[i]);
//                System.out.println("Camino mínimo: " + caminoMinimo(origen, i, v));
//                System.out.println();
//            }
//        }
    }

    public String indices_code(String code1, String code2) {
        System.out.println("Entro indicecode");
        int c1 = 0, c2 = 0, cont = 0;
        for (aeropuerto i : aeropuertos) {
            if (i.codigo.equals(code1)) {
                c1 = cont;
            }
            if (i.codigo.equals(code2)) {
                c2 = cont;
            }
            cont++;
        }
        if (c1 < aeropuertos.size() || c2 < aeropuertos.size()) {
            aeropuerto[] aeropuertosV = aeropuertos.toArray(new aeropuerto[aeropuertos.size()]);
            return caminoMinimo(c1, c2, aeropuertosV);
        } else {
            return "Uno de los dos aeropuertos no existen";
        }
    }

    public String caminoMinimo(int origen, int destino, aeropuerto[] v) {
        System.out.println("Entre caminominimo");
        boolean bb = false;
        String path = "";
        String a ="";
        if (D[destino] != 999999.0) {
            path = v[destino].codigo + "";
            a+=("\n" + v[destino].mostrar_info()+ "\n");
            while (ultimo[destino] != origen) {
                destino = ultimo[destino];
                path = v[destino].codigo + " -> " + path;
                a+=("\n" + v[destino].mostrar_info()+"\n");
            }
            a+=("\n" + v[origen].mostrar_info()+"\n");
            path = v[origen].codigo + " -> " + path;
            return path +"\n\n"+a;
        } else {
            System.out.println("No hay manera de llegar hasta alla en ese punto");
            return "No hay manera de llegar hasta alla en ese punto";
        }
    }

    public void mayores(double[] d) {
        int[] indices = new int[10];
        for (int i = 0; i < d.length; i++) {
            for (int j = 0; j < indices.length; j++) {
                if (d[i] > d[indices[j]] && (d[i] != 999999.0)) {
                    for (int k = indices.length - 1; k > j; k--) {
                        indices[k] = indices[k - 1];
                    }
                    indices[j] = i;
                    break;
                }
            }
        }
        may = indices;
        System.out.println("Los índices de los 10 mayores valores son:");
        for (int i = 0; i < 10; i++) {
            System.out.println("Índice: " + indices[i]);
        }
    }
}
