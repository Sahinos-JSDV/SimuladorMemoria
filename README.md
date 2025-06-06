Simulador de Gestión de Memoria
Este codigo implmenta un simulador completo de gestión de memoria desarrollado en Java que implementa algoritmos de asignación de memoria contigua y virtual.

🚀 Características

Memoria Contigua: Best Fit, Worst Fit, First Fit, Next Fit
Memoria Virtual: FIFO y LRU con análisis de rendimiento
Interfaz gráfica intuitiva
Visualización de estructuras de memoria (mapas de bits, listas ligadas)
Análisis de fragmentación y estadísticas de rendimiento

🏗️ Arquitectura
El proyecto sigue el patrón MVC (Modelo-Vista-Controlador):

Modelo: Lógica de algoritmos de memoria (FIFO, LRU, algoritmos de ajuste)
Vista: Interfaces gráficas (.form) para memoria contigua y paginada
Controlador: Coordinación entre modelo y vista para cada módulo

📋 Requisitos

Java JDK 8+
IDE compatible con Java (NetBeans, Eclipse, IntelliJ)

⚡ Instalación y Ejecución
bash# Clonar el repositorio
git clone https://github.com/Sahinos-JSDV/SimuladorMemoria.git
cd simulador-gestion-memoria

# Compilar (desde la raíz del proyecto)
javac -d bin -cp src src/Vista/Detect.java src/Controlador/*.java src/Modelo/*.java src/Vista/*.java

# Ejecutar
java -cp bin Vista.Detect

Nota: Se recomienda abrir el proyecto en NetBeans o tu IDE favorito para una mejor experiencia de desarrollo.

📁 Estructura del Proyecto
src/
├── Controlador/              # Lógica de control (MVC)
│   ├── ControlContigua.java
│   ├── ControlGeneral.java
│   └── ControlPaginada.java
├── Modelo/                   # Lógica de negocio
│   ├── Ajustes.java
│   ├── FIFO.java
│   ├── LRU.java
│   └── ModelContigua.java
└── Vista/                    # Interfaz de usuario
    ├── Detect.java
    ├── MemContigua.form/.java
    ├── MemPaginada.form/.java
    └── dibujoMemoria.form/.java

🎯 Uso Rápido
Memoria Contigua

Selecciona el algoritmo de asignación
Configura tamaño de memoria y SO
Agrega procesos con PID y tamaño
Visualiza la asignación y fragmentación

Memoria Virtual

Ingresa cadena de referencias (ej: 1 2 3 4 1 2 5)
Selecciona algoritmo (FIFO/LRU)
Define número de marcos
Ejecuta y compara rendimiento

📖 Documentación:
Manual de Usuario: Guía completa con ejemplos

🧪 Ejemplos
java// Ejemplo de configuración
Memoria: 1000 bytes
SO: 100 bytes
Procesos: P1(200), P2(150), P3(300)
Algoritmo: Best Fit

👥 Desarrolladores:
Jesús David Sahinos Gaona
Juan Pablo Cielo Coyotl



📄 Licencia
Este proyecto tiene fines académicos y educativos.

⭐ Si este proyecto te fue útil no olvides darle una estrella
