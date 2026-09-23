## Análisis de Precedencia y Asociatividad

En este taller creamos 4 gramaticas para una calculadora aritmética básica que opera con suma (`+`), resta (`-`), multiplicación (`*`) y división (`/`).

El objetivo principal es diseñar, probar y validar cómo se controla el comportamiento del computador al evaluar expresiones matemáticas, manipulando deliberadamente:
1. **Asociatividad por la izquierda** (comportamiento estándar: de izquierda a derecha).
2. **Asociatividad por la derecha** (evaluación de derecha a izquierda).
3. **Precedencia mayor para la multiplicación y división** (jerarquía matemática estándar).
4. **Precedencia mayor para la suma y la resta** (jerarquía invertida).

A partir de estas condiciones, se construyen y prueban los **4 casos posibles** tanto a nivel formal de gramática como en su implementación práctica con **ANTLR4** y **Java**.

---

## Los 4 Casos de Estudio

### 1. Caso 1: Multiplicación y División Alta | Asociatividad Izquierda *(Estándar)*
* **Lógica:** La suma/resta van en el nivel superior `E` (menor prioridad, se evalúan al final). La multiplicación/división van en `T` (mayor prioridad, se evalúan primero). Ambos niveles se llaman a sí mismos por el lado izquierdo.
* **Gramática:**
  ```antlr
  E → E + T | E - T | T
  T → T * F | T / F | F
  F → num
  ```
* **Prueba** 

---

### 2. Caso 2: Suma y Resta Alta | Asociatividad Izquierda *(Prioridad Invertida)*
* **Lógica:** Como la suma y la resta ahora tienen más importancia, se colocan en el nivel inferior `T` para que se evalúen antes. La multiplicación y división pasan a `E`.
* **Gramática:**
  ```antlr
  E → E * T | E / T | T
  T → T + F | T - F | F
  F → num
  ```
* **Prueba** 
---

### 3. Caso 3: Multiplicación y División Alta | Asociatividad Derecha
* **Lógica:** Mantiene la jerarquía clásica (multiplicación antes que suma), pero la recursión se llama al **lado derecho** del operador (`T + E` y `F * T`), forzando a resolver de derecha a izquierda.
* **Gramática:**
  ```antlr
  E → T + E | T - E | T
  T → F * T | F / T | F
  F → num
  ```
* **Prueba** 

---

### 4. Caso 4: Suma y Resta Alta | Asociatividad Derecha
* **Lógica:** Combina la prioridad más alta para suma y resta (`T`) con la recursión por la derecha en todos los operadores.
* **Gramática:**
  ```antlr
  E → T * E | T / E | T
  T → F + T | F - T | F
  F → num
  ```
* **Prueba** 

---

---

## 🛠️ Requisitos Previos

Para compilar y ejecutar las pruebas de este proyecto en tu máquina necesitas:

1. **Java JDK 11 o superior** (probado con OpenJDK / Java 17).
2. **ANTLR4** (versión `4.13.x`).
3. Configurar en tu `~/.bashrc` (o terminal) las variables de entorno de ANTLR:
   ```bash
   export ANTLR_HOME=$HOME/antlr
   export CLASSPATH=".:$ANTLR_HOME/antlr-4.13.2-complete.jar:$CLASSPATH"
   alias antlr4='java -jar $ANTLR_HOME/antlr-4.13.2-complete.jar'
   ```

---

## ¿Cómo compilar y ejecutar el proyecto?

Abre una terminal en la carpeta del proyecto donde se encuentran la gramática `.g4`, los archivos Java y el archivo de prueba `t.expr`:

```bash
cd calculadora
```

### 1. Generar los analizadores sintácticos y léxicos con ANTLR4
Ejecuta el comando para generar el Lexer, Parser y el Visitor a partir de la gramática:
```bash
antlr4 -visitor LabeledExpr.g4
```

### 2. Compilar el código Java
Compila todas las clases generadas y los archivos del evaluador:
```bash
javac *.java
```

### 3. Ejecutar las pruebas con archivo de expresiones
Para evaluar las operaciones escritas en el archivo de texto `t.expr`:
```bash
java Calc t.expr
```


##  Estructura del Repositorio

```text
├── README.md                                         # Presentación y guía de ejecución
├── Tarea Análisis de Precedencia y asociatividad.pdf # Documento formal con desarrollo teórico
└── calculadora/
    ├── LabeledExpr.g4                                # Gramática definida en ANTLR4
    ├── Calc.java                                     # Clase principal (Main) que carga el parser
    ├── EvalVisitor.java                              # Visitor que recorre y evalúa el árbol
    └── t.expr                                        # Archivo de entrada con expresiones de prueba
```

El Documento Teórico y Explicación Paso a Paso se encuentra documentado en el PDF
