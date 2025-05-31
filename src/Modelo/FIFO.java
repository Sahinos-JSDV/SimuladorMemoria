/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Sahin
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FIFO{

    private int cantidadPaginas;
    private int cantidadFrames;
    private int[] paginas;
    private int[][] matriz;
    private int[] fallos;
    private ArrayList<Queue<Integer>> estadosCola; // ArrayList para almacenar los estados de la cola en cada iteración
    private int auxiliar = 0; // variable auxiliar para la política FIFO

    public FIFO(){

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

    public void siguiente() {
        auxiliar++;
        if (auxiliar == cantidadFrames) { // si llega al final de los frames regresa al primer frame
            auxiliar = 0;
        }
    }

    public boolean buscar(int paginaActual) {
        boolean bandera = false;
        for (int j = 0; j < cantidadFrames; j++) {
            // recorre todos los frames de una pagina determinada
            if (paginas[paginaActual] == matriz[j][paginaActual]) {
                // si el valor de la pagina actual existe en algun frame la bandera se le asigna verdadero
                bandera = true;
            }
        }
        return bandera;
    }

    public void modificar(boolean encontrado, int paginaActual) {
        if (!encontrado) {
            for (int aux = paginaActual; aux < cantidadPaginas; aux++) {
                matriz[auxiliar][aux] = paginas[paginaActual];
                fallos[paginaActual] = 1;
            }
            siguiente();
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

    public void fifo() {
        matriz = new int[cantidadFrames][cantidadPaginas];
        fallos = new int[cantidadPaginas];
        estadosCola = new ArrayList<>(); // Inicializar el ArrayList para almacenar los estados de la cola
        iniciarxfallos();
        iniciarMatriz();
        Queue<Integer> cola = new LinkedList<>(); // Crear una cola para mantener el orden de los procesos
        for (int j = 0; j < cantidadPaginas; j++) {
            
            modificar(buscar(j), j);
            
            cola.add(paginas[j]); // Agregar el proceso a la cola
			if (cola.size() > cantidadFrames) {
                cola.poll(); // Si la cola excede el tamaño máximo, eliminar el proceso más antiguo
            }
            estadosCola.add(new LinkedList<>(cola)); // Guardar una copia del estado actual de la cola en el ArrayList
            
        }
        mostrarMatriz();
    }

    public void mostrarMatriz(){
		int cantidadFallos=0;
		for(int i=0;i<cantidadFrames;i++){
			for(int j=0;j<cantidadPaginas;j++){
				System.out.print(""+matriz[i][j]);
			}
			System.out.println();
		}
		
		for(int i=0;i<cantidadPaginas;i++){
			if(fallos[i]==1){
				cantidadFallos++;
			}
			System.out.print(""+fallos[i]);
		}
		System.out.println("\n\nFallos encontrados: "+cantidadFallos);
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

    public int getCantidadPaginas() {
        return cantidadPaginas;
    }

    public int getCantidadFrames() {
        return cantidadFrames;
    }

    public int[] getPaginas() {
        return paginas;
    }

    public ArrayList<Queue<Integer>> getEstadosCola() {
        return estadosCola;
    }
    
}
