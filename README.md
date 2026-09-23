# Analisis de Precedencia y Asociatividad


Este taller creamos una calculadora aritmetica en ANTLR4 y Java para las operaciones basicas (+, -, *, /). El objetivo es comprobar como la configuracion de las reglas gramaticales define la precedencia de operadores y la asociatividad (izquierda o derecha).

Para evitar recompilar y modificar la gramatica repetidamente, se desarrollo una solucion unificada en un unico archivo (Calculadora.g4) que contiene las 4 variantes solicitadas en el taller.



## 2. Los 4 Casos Implementados

En la gramatica, cada caso se define mediante una regla especifica:

### Caso 1 (c1): Multiplicacion y Division Mayor Precedencia | Asociatividad Izquierda
- Regla: `expr1`
- Comportamiento: La regla de multiplicacion y division esta arriba (mayor precedencia). No usa modificador de asociatividad, por lo que ANTLR4 asocia a la izquierda por defecto.

### Caso 2 (c2): Suma y Resta Mayor Precedencia | Asociatividad Izquierda
- Regla: `expr2`
- Comportamiento: La regla de suma y resta esta arriba (mayor precedencia que multiplicacion y division). Asocia a la izquierda por defecto.

### Caso 3 (c3): Multiplicacion y Division Mayor Precedencia | Asociatividad Derecha
- Regla: `expr3`
- Comportamiento: Multiplicacion y division estan arriba. Ambas reglas incluyen el modificador `<assoc=right>`, obligando a resolver de derecha a izquierda.

### Caso 4 (c4): Suma y Resta Mayor Precedencia | Asociatividad Derecha
- Regla: `expr4`
- Comportamiento: Suma y resta estan arriba. Ambas reglas incluyen `<assoc=right>`, resolviendo de derecha a izquierda con prioridad en la suma y resta.


## 3. Formato del Archivo de Entrada (t.expr)

El archivo de pruebas permite indicar el prefijo del caso a evaluar:

```text
c1: 10 - 4 - 2
c2: 10 - 4 - 2
c3: 10 - 4 - 2
c4: 10 - 4 - 2

c1: 2 * 3 + 4
c2: 2 * 3 + 4
c3: 2 * 3 + 4
c4: 2 * 3 + 4
```



## 4. Instrucciones de Compilacion y Ejecucion

Desde la terminal, situarse en el directorio del proyecto:

```bash
cd /home/yeimy-beltran/Documentos/calculadora
```

### Paso 1: Generar el analizador con ANTLR4
```bash
antlr4 -visitor Calculadora.g4
```

### Paso 2: Compilar los archivos Java
```bash
javac *.java
```

### Paso 3: Ejecutar el evaluador
```bash
java Calc t.expr
```



## 5. Resultados de las Pruebas



### Analisis de Resultados:



## 6. Estructura de Archivos

- `Calculadora.g4`: Gramatica unica con los 4 esquemas de precedencia y asociatividad.
- `Calc.java`: Clase principal de entrada.
- `EvalVisitor.java`: Recorrido del arbol y evaluacion aritmetica.
- `t.expr`: Archivo con expresiones de prueba.
- `README.md`: Documentacion tecnica de ejecucion.
- `../Tarea Análisis de Precedencia y asociatividad.pdf`: Documento formal teorico.
