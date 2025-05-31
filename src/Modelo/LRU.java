/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Sahin
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
public class LRU{
	
    private int cantidadPaginas;
    private int cantidadFrames;
    private int[] paginas;
    private int[][] matriz;
    private int[] fallos;
    private int[] distancia;
    private ArrayList<Queue<Integer>> estadosCola; // ArrayList para almacenar los estados de la cola en cada iteración

    public LRU() {
    }

    public void setPaginas(int[] paginas) {
        this.paginas = paginas;
    }

    public void setCantidadPaginas(int cantidadPaginas) {
        this.cantidadPaginas = cantidadPaginas;
    }

    public void setCantidadFrames(int cantidadFrames) {
        this.cantidadFrames = cantidadFrames;
    }

    public void iniciarxfallos() {
        fallos = new int[cantidadPaginas]; // Inicializar el arreglo de fallos
        for (int i = 0; i < cantidadPaginas; i++) {
            fallos[i] = 0;
        }
    }

    private void iniciarMatriz() {
        matriz = new int[cantidadFrames][cantidadPaginas]; // Inicializar la matriz
        for (int i = 0; i < cantidadFrames; i++) {
            for (int j = 0; j < cantidadPaginas; j++) {
                matriz[i][j] = -1;
            }
        }
    }

    private boolean buscar(int paginaActual) {
        boolean encontrado = false;
        for (int i = 0; i < cantidadFrames; i++) {
            if (paginas[paginaActual] == matriz[i][paginaActual]) {
                encontrado = true;
            }
        }
        return encontrado;
    }

    private void llenarFila(int paginaActual, int frame) {
        for (int j = paginaActual; j < cantidadPaginas; j++) {
            matriz[frame][j] = paginas[paginaActual];
        }
    }

    private int MayorDistancia(int paginaActual) {
        int mayorDist = 0;
        for (int i = 0; i < cantidadFrames; i++) {
            for (int j = paginaActual; j >= 0; j--) {
                if (matriz[i][paginaActual] == paginas[j]) {
                    distancia[i] = paginaActual - j;
                    break;
                }
            }
        }

        for (int i = 0; i < cantidadFrames; i++) {
            if (distancia[i] > distancia[mayorDist]) {
                mayorDist = i;
            }
        }
        return mayorDist;
    }

    private void modificar(int paginaActual) {
        boolean encontradoFrameLibre = false;
        int i;
        for (i = 0; i < cantidadFrames; i++) {
            if (matriz[i][paginaActual] == -1) {
                encontradoFrameLibre = true;
                break;
            }
        }

        if (!encontradoFrameLibre) {
            llenarFila(paginaActual, MayorDistancia(paginaActual));
        } else {
            llenarFila(paginaActual, (i));
        }

        fallos[paginaActual] = 1;

    }

    public void lru() {
		matriz = new int[cantidadFrames][cantidadPaginas];
		fallos = new int[cantidadPaginas];
		distancia = new int[cantidadFrames];
		estadosCola = new ArrayList<>(); // Inicializar el ArrayList para almacenar los estados de la cola
		iniciarxfallos();
		iniciarMatriz();
		Queue<Integer> cola = new LinkedList<>(); // Crear una cola para mantener el orden de los procesos
		for (int j = 0; j < cantidadPaginas; j++) {
			if (!buscar(j)) {
				modificar(j);
			}
			if (cola.contains(paginas[j])) {
				cola.remove(paginas[j]); // Eliminar cualquier instancia previa del proceso en la cola
			}
			cola.add(paginas[j]); // Agregar el proceso a la cola
			
			if (cola.size() > cantidadFrames) {
				cola.poll(); // Si la cola excede el tamaño máximo, eliminar el proceso más antiguo
			}
			estadosCola.add(new LinkedList<>(cola)); // Guardar una copia del estado actual de la cola en el ArrayList
			
		}
	}

    public void mostrarMatriz() {
        int cantidadFallos = 0;
        for (int i = 0; i < cantidadFrames; i++) {
            for (int j = 0; j < cantidadPaginas; j++) {
                if (matriz[i][j] == -1) {
                    System.out.print(" "); // Para que no se muestre el -1 en la matriz
                } else
                    System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i < cantidadPaginas; i++) {
            if (fallos[i] == 1) {
                cantidadFallos++;
            }
            System.out.print("" + fallos[i]);
        }
        System.out.println("\n\nFallos encontrados: " + cantidadFallos);
    }

    public void mostrarEstadosCola() {
        System.out.println("\nEstados de la cola en cada iteración:");
        for (int i = 0; i < estadosCola.size(); i++) {
            System.out.println("Iteración " + (i + 1) + ": " + estadosCola.get(i));
        }
    }
    //Getters para el acceso a los datos del algoritmo
    public int[][] getMatriz(){
        return matriz;
    }
    
    public int[] getFallos(){
        return fallos;
    }

    public ArrayList<Queue<Integer>> getEstadosCola() {
        return estadosCola;
    }

    public int getCantidadPaginas() {
        return cantidadPaginas;
    }

    public int getCantidadFrames() {
        return cantidadFrames;
    }

    public int[] getPaginas() {
        return paginas;
    }
    
    
}
