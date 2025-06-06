# Simulador de Gestión de Memoria:

Este codigo implementa un simulador completo de gestión de memoria desarrollado en Java que implementa algoritmos de asignación de memoria contigua y virtual.

## 🚀 Características

- **Memoria Contigua**: Best Fit, Worst Fit, First Fit, Next Fit
- **Memoria Virtual**: FIFO y LRU con análisis de rendimiento
- **Interfaz gráfica** intuitiva
- **Visualización** de estructuras de memoria (mapas de bits, listas ligadas)
- **Análisis de fragmentación** y estadísticas de rendimiento

## 🧪 Ejemplo de Configuración

**Memoria Contigua:**
```
Tamaño de memoria: 1000 bytes
Sistema operativo: 100 bytes
Algoritmo: Best Fit
Procesos: P1(200), P2(150), P3(300)
```

**Memoria Virtual:**
```
Cadena de referencia: 1 2 3 4 1 2 5 1 2 3 4 5
Algoritmo: LRU
Marcos: 3
```

## 📋 Requisitos

- Java JDK 8+
- IDE compatible con Java (NetBeans, Eclipse, IntelliJ)

## ⚡ Instalación y Ejecución

**Clonar el repositorio:**
```bash
git clone https://github.com/Sahinos-JSDV/SimuladorMemoria.git
cd simulador-gestion-memoria
```

**Compilar:**
```bash
javac -d bin -cp src src/Vista/Detect.java src/Controlador/*.java src/Modelo/*.java src/Vista/*.java
```

**Ejecutar:**
```bash
java -cp bin Vista.Detect
```

> **Recomendación:** Abrir el proyecto en NetBeans para mejor experiencia de desarrollo

## 📁 Estructura del Proyecto

```
src/
├── Controlador/
│   ├── ControlContigua.java
│   ├── ControlGeneral.java
│   └── ControlPaginada.java
├── Modelo/
│   ├── Ajustes.java
│   ├── FIFO.java
│   ├── LRU.java
│   └── ModelContigua.java
└── Vista/
    ├── Detect.java
    ├── MemContigua.form
    ├── MemContigua.java
    ├── MemPaginada.form
    ├── MemPaginada.java
    ├── dibujoMemoria.form
    └── dibujoMemoria.java
```

**Arquitectura MVC:**
- **Controlador:** Lógica de control y coordinación
- **Modelo:** Algoritmos de memoria y lógica de negocio  
- **Vista:** Interfaces gráficas y formularios

## 🎯 Uso Rápido

**Memoria Contigua:**
1. Selecciona el algoritmo de asignación
2. Configura tamaño de memoria y SO
3. Agrega procesos con PID y tamaño
4. Visualiza la asignación y fragmentación

**Memoria Virtual:**
1. Ingresa cadena de referencias (ejemplo: `1 2 3 4 1 2 5`)
2. Selecciona algoritmo (FIFO/LRU)
3. Define número de marcos
4. Ejecuta y compara rendimiento

## 📖 Documentación

- **[Manual de Usuario](docs/Manual_Usuario_Sim1.pdf)**: Guía completa con ejemplos




## 👥 Desarrolladores:

- Jesús David Sahinos Gaona
- Juan Pablo Cielo Coyotl  




## 📄 Licencia

Este proyecto tiene fines académicos y educativos.

---

⭐ Si este proyecto te fue útil no olvides darle una estrella.
