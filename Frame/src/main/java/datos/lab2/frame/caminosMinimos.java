/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos.lab2.frame;

/**
 *
 * @author MIGUEL MARQUEZ
 */
public class caminosMinimos {

    public void tramz(String[][] m ,String[][] m2) {
        System.out.println("entre");
        double epa, n = 0;
//        String[][] m = {
//            {null, "A", "B", "C", "D", "E"},
//            {"A", "0", "4", "8", null, null},
//            {"B", "4", "0", "1", "2", null},
//            {"C", "8", null, "0", "4", "2"},
//            {"D", null, "2", "4", "0", "7"},
//            {"E", null, null, "2", "7", "0"},};
//        String[][] m2 = {
//            {" ", "A", "B", "C", "D", "E"},
//            {"A", " ", "B", "C", "D", "E"},
//            {"B", "A", " ", "C", "D", "E"},
//            {"C", "A", "B", " ", "D", "E"},
//            {"D", "A", "B", "C", " ", "E"},
//            {"E", "A", "B", "C", "D", " "},};
        for (int i = 1; i < m.length; i++) {
            for (int j = 1; j < m[i].length; j++) {
                if (!"0".equals(m[i][j])) {
                    for (int k = 1; k < m.length; k++) {
                        if ((!"0".equals(m[k][i]) && m[k][i] != null) && (!"0".equals(m[i][j]) && m[i][j] != null)) {
                            epa = Double.parseDouble(m[i][j]) + Double.parseDouble(m[k][i]);

                            if (!"0".equals(m[k][j])) {
                                if (m[k][j] == null) {
                                    m[k][j] = Double.toString(epa);
                                    m2[k][j] = m2[i][0];
                                } else {
                                    if (epa < Double.parseDouble(m[k][j])) {
                                        m[k][j] = Double.toString(epa);
                                        m2[k][j] = m2[i][0];
                                    }
                                }

                            } else {
                                if (!"0".equals(m[i][i])) {
                                    if (m[i][i] == null) {
                                        m[i][i] = Double.toString(epa);
                                        m2[i][i] = m2[i][0];
                                    } else {
                                        if (epa < Double.parseDouble(m[i][i])) {
                                            m[i][i] = Double.toString(epa);
                                            m2[i][i] = m2[i][0];
                                        }
                                    }
                                }
                            }
                        }

                    }

                }
            }
            System.out.println("Estoy chambeando");
        }
        System.out.println("sali");
    }

    public static void encontrarCamino(String[][] matrizPesos, String[][] matrizNodos, String origen, String destino) {
        // Encontrar los índices de origen y destino
        int indiceOrigen = -1, indiceDestino = -1;
        for (int i = 1; i < matrizNodos.length; i++) {
            if (matrizNodos[i][0].equals(origen)) {
                indiceOrigen = i;
            }
            if (matrizNodos[i][0].equals(destino)) {
                indiceDestino = i;
            }
        }

        // Verificar si se encontraron los nodos
        if (indiceOrigen == -1 || indiceDestino == -1 ||matrizPesos[indiceOrigen][indiceDestino]==null) {
            System.out.println("Nodo de origen o destino no encontrado.");
            return;
        }

        // Reconstruir el camino
        String camino = origen;
        double pesoCamino = Double.parseDouble(matrizPesos[indiceOrigen][indiceDestino]);
        while (!matrizNodos[indiceOrigen][indiceDestino].equals(destino)) {
            int indiceIntermedio = -1;
            for (int i = 1; i < matrizNodos.length; i++) {
                if (matrizNodos[indiceOrigen][i].equals(matrizNodos[indiceOrigen][indiceDestino])) {
                    indiceIntermedio = i;
                    break;
                }
            }
            camino += " -> " + matrizNodos[indiceOrigen][indiceIntermedio];
            indiceOrigen = indiceIntermedio;
        }
        camino += " -> " + destino;

        // Imprimir el resultado
        System.out.println("El camino más corto de " + origen + " a " + destino + " es:");
        System.out.println(camino);
        System.out.println("El peso del camino es: " + pesoCamino);
    }
}
