package Modelo;

import java.util.LinkedList;
import java.util.List;

public class ModelContigua {

    public static void setLongitudMemoria(int longitudMemoria) {
        LONGITUD_MEMORIA = longitudMemoria;
    }

    public static void setTam_SO(int tam_SO) {
        Tam_SO = tam_SO;
    }

    public static int Tam_SO;
    public static int LONGITUD_MEMORIA;
    public static List<int[]> listaControl;
    public static int[] memoria;
    public static int ultimoPid;
    static boolean memoria_inicializada = false;
    static boolean encolarProcesos = false; // Variable para determinar si se encolan los procesos en espera
    static List<ProcesoEnEspera> colaProcesosEnEspera = new LinkedList<>();

    public static void inicializaMemoria() {

        if (!memoria_inicializada) {
            listaControl = new LinkedList<int[]>();
            memoria = new int[LONGITUD_MEMORIA];
            ultimoPid = 0;
            int[] huecoInicial = {1, 0, Tam_SO, -1}; // Espacio para Tam_SO
            int[] hueco = {0, Tam_SO, LONGITUD_MEMORIA - Tam_SO, 0}; // Espacio restante
            listaControl.add(huecoInicial);
            listaControl.add(hueco);
            // Marcar memoria correspondiente al SO como ocupada en el mapa de bits
            for (int i = 0; i < Tam_SO; i++) {
                memoria[i] = 1;
            }

            memoria_inicializada = true;
        }
    }

    public static void escribeMemoria(int direccion, int tamanyo, int dato) {
        for (int i = 0; i < tamanyo; i++) {
            memoria[direccion + i] = dato;
        }
    }

    public static boolean creaProceso(int pid, int tamanyo, int opcion) {
        int hueco;
        switch (opcion) {
            case 1:
                hueco = Ajustes.primerAjuste(listaControl, tamanyo);
                break;
            case 2:
                hueco = Ajustes.siguienteAjuste(listaControl, tamanyo);
                break;
            case 3:
                hueco = Ajustes.mejorAjuste(listaControl, tamanyo);
                break;
            case 4:
                hueco = Ajustes.peorAjuste(listaControl, tamanyo);
                break;
            default:
                System.out.println("Opción no válida.");
                return false;
        }

        boolean res = (hueco != -1);
        if (res) {
            int direcc = listaControl.get(hueco)[1];
            int[] proceso = {1, direcc, tamanyo, pid};
            int espacioRestante = listaControl.get(hueco)[2] - tamanyo;
            listaControl.set(hueco, proceso);
            if (espacioRestante > 0) {
                int[] bloqRestante = {0, direcc + tamanyo, espacioRestante, 0};
                listaControl.add(hueco + 1, bloqRestante);
            }
            escribeMemoria(direcc, tamanyo, 1);
        } else {
            agregarProcesoEnEspera(pid, tamanyo, opcion);
        }
        return res;
    }

    public static boolean destruyeProceso(int pid) {
        int indice = 0;
        for (int[] bloque : listaControl) {
            indice++;
            if (bloque[3] == pid) {
                break;
            }
        }
        indice--;
        boolean encontrado = (indice != -1);
        if (encontrado) {
            int[] bloqueABorrar = listaControl.get(indice);
            listaControl.get(indice)[0] = 0;
            listaControl.get(indice)[3] = 0;
            escribeMemoria(bloqueABorrar[1], bloqueABorrar[2], 0);
            fusiona(indice);
            fusiona(indice - 1);
        }
        return encontrado;
    }

    private static boolean fusiona(int indice) {
        boolean fusionable = false;
        if (indice >= 0 && (indice + 1) < listaControl.size()) {
            fusionable = listaControl.get(indice)[0] == 0 && listaControl.get(indice + 1)[0] == 0;
        }
        if (fusionable) {
            int tamanyo = listaControl.remove(indice + 1)[2];
            listaControl.get(indice)[2] += tamanyo;
        }
        return fusionable;
    }

    // Método para activar o desactivar la opción de encolar procesos en espera
    public static boolean setEncolarProcesos(boolean encolar) {
        boolean encolarProcesos = encolar;
        return encolarProcesos;
    }

    public static void agregarProcesoEnEspera(int pid, int tamanyo, int opcion) {

        if (setEncolarProcesos(true)) {

            colaProcesosEnEspera.add(new ProcesoEnEspera(pid, tamanyo, opcion));

        }


    }

    // Clase interna para representar un proceso en espera
    static class ProcesoEnEspera {
        int pid;
        int tamanyo;
        int opcion;

        public ProcesoEnEspera(int pid, int tamanyo, int opcion) {
            this.pid = pid;
            this.tamanyo = tamanyo;
            this.opcion = opcion;
        }
    }

    // Método para verificar si hay espacio libre en la memoria y notificar a los procesos en espera
    public static void verificarEspacioLibre() {
        // Calcular el espacio libre total en la memoria
        int espacioLibreTotal = calcularEspacioLibre();

        // Iterar sobre los procesos en espera y ver si alguno puede ser asignado a la memoria
        List<ProcesoEnEspera> procesosAAsignar = new LinkedList<>();
        for (ProcesoEnEspera proceso : colaProcesosEnEspera) {
            if (proceso.tamanyo <= espacioLibreTotal) {
                // Si el tamaño del proceso en espera es menor o igual al espacio libre total, agregarlo a la lista de procesos a asignar
                procesosAAsignar.add(proceso);
            }
        }

        // Intentar asignar los procesos en espera a la memoria
        for (ProcesoEnEspera proceso : procesosAAsignar) {
            boolean asignado = creaProceso(proceso.pid, proceso.tamanyo, proceso.opcion);
            if (asignado) {
                // Si el proceso en espera se asignó correctamente, quitarlo de la cola de procesos en espera
                colaProcesosEnEspera.remove(proceso);
                // Actualizar el espacio libre total restando el tamaño del proceso asignado
                espacioLibreTotal -= proceso.tamanyo;
            }
        }
    }

    // Método para calcular el espacio libre total en la memoria
    public static int calcularEspacioLibre() {
        int espacioLibreTotal = 0;
        for (int[] bloque : listaControl) {
            if (bloque[0] == 0) {
                espacioLibreTotal += bloque[2];
            }
        }
        return espacioLibreTotal;
    }


    public static int calcularFragmentacionExterna() {
        int fragmentacionExterna = 0;
        boolean bloqueLibre = false;
        for (int[] bloque : listaControl) {
            if (bloque[0] == 0) {
                if (!bloqueLibre) {
                    // Si encontramos un bloque libre y no hemos comenzado a contar la fragmentación externa,
                    // establecemos bloqueLibre a true para empezar a contar.
                    bloqueLibre = true;
                }
                // Si ya hemos empezado a contar la fragmentación externa, agregamos el tamaño del bloque libre a la fragmentación total.
                fragmentacionExterna += bloque[2];
            } else {
                // Si encontramos un bloque asignado, restablecemos bloqueLibre a false para indicar el final de la fragmentación externa.
                bloqueLibre = false;
            }
        }
        return fragmentacionExterna;
    }

    public static String imprimeMemoria() {
        String s = "Lista de control: {[EST-DIR-TAM-PROC]:";
        for (int[] bloque : listaControl) {
            s += "[" + (bloque[0] == 0 ? "H" : "P" + bloque[3]) + " " + bloque[1] + " " + bloque[2] + "]";
        }
        s += "}\nMemoria: \n";

        // Imprimir vector de bits en filas de 32 caracteres
        int contador = 0;
        for (int i : memoria) {
            s += i;
            contador++;
            if (contador % 32 == 0) {
                s += "\n";
            }
        }

        // Imprimir vector base 10 en filas de 4 caracteres
        s += "\ndecimal: \n";
        contador = 0;
        for (int i = 0; i < memoria.length; i += 8) {
            int decimal = 0;
            for (int j = 0; j < 8; j++) {
                if (i + j < memoria.length) {
                    decimal += memoria[i + j] * Math.pow(2, 7 - j);
                }
            }
            s += decimal + " ";
            contador++;
            if (contador % 4 == 0) {
                s += "\n";
            }
        }

        // Imprimir información de procesos y huecos
        s += "\nLista ligada:\n";

        boolean primerElemento = true;
        int index = 0;
        for (int[] bloque : listaControl) {

            if (primerElemento && bloque[3] == -1) {
                s += "|SO: " + bloque[1] + " " + bloque[2] + "|";
            } else {
                s += (bloque[0] == 0 ? "|H" : "|P:" + bloque[3]) + " " + bloque[1] + " " + bloque[2] + "|";
            }

            // Verifica si hay un siguiente elemento en la lista
            if (index < listaControl.size() - 1) {
                s += " --> "; // Agrega una flecha
            } else {
                s += ""; // Si es el último elemento, indica que no hay más elementos
            }
            index++;
        }

        s += "\nProcesos en cola:\n";
        for (ProcesoEnEspera proceso : colaProcesosEnEspera) {
            s += "PID:" + proceso.pid + "\n";
        }

        s += "\nFragmentacion externa:\n";
        s += calcularFragmentacionExterna();

        return s+"\n";
    }
}
