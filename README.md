# Analisis de Precedencia y Asociatividad

### Grupo 1: Ángel Arcos, Yeimy Beltrán, Nicolas Gutiérrez y Samuel Lagos Prado
 
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
 
Se ejecuto `java Calc t.expr` con 10 pruebas: 2 expresiones evaluadas en los 4 casos y 2 expresiones con parentesis.
 
```text
c1: 10 - 4 - 2
c2: 10 - 4 - 2
c3: 10 - 4 - 2
c4: 10 - 4 - 2
 
c1: 2 * 3 + 4
c2: 2 * 3 + 4
c3: 2 * 3 + 4
c4: 2 * 3 + 4
 
c1: 10 - (4 - 2)
c1: 2 * (3 + 4)
```
 
Salida obtenida:
 
```text
[Caso 1 | Mult Alta, Izquierda] 10-4-2 = 4
[Caso 2 | Suma Alta, Izquierda] 10-4-2 = 4
[Caso 3 | Mult Alta, Derecha]   10-4-2 = 8
[Caso 4 | Suma Alta, Derecha]   10-4-2 = 8
[Caso 1 | Mult Alta, Izquierda] 2*3+4 = 10
[Caso 2 | Suma Alta, Izquierda] 2*3+4 = 14
[Caso 3 | Mult Alta, Derecha]   2*3+4 = 10
[Caso 4 | Suma Alta, Derecha]   2*3+4 = 14
[Caso 1 | Mult Alta, Izquierda] 10-(4-2) = 8
[Caso 1 | Mult Alta, Izquierda] 2*(3+4) = 14
```
 
Resumen:
 
| Expresion    | c1 | c2 | c3 | c4 |
|--------------|---:|---:|---:|---:|
| `10 - 4 - 2` | 4  | 4  | 8  | 8  |
| `2 * 3 + 4`  | 10 | 14 | 10 | 14 |
 
Con parentesis (ejecutadas en c1, que sin parentesis daba 4 y 10 para estas mismas expresiones):
 
| Expresion (c1)    | Sin parentesis | Con parentesis |
|-------------------|---------------:|---------------:|
| `10 - (4 - 2)`    | 4              | 8              |
| `2 * (3 + 4)`     | 10             | 14             |
 
Agrupacion real que genera ANTLR4 (obtenida con `grun Calculadora prog -tree`):
 
| Expresion    | Caso | Agrupacion       | Valor |
|--------------|------|------------------|------:|
| `10 - 4 - 2` | c1   | `(10 - 4) - 2`   | 4     |
| `10 - 4 - 2` | c3   | `10 - (4 - 2)`   | 8     |
| `2 * 3 + 4`  | c1   | `(2 * 3) + 4`    | 10    |
| `2 * 3 + 4`  | c2   | `2 * (3 + 4)`    | 14    |
| `10 - (4 - 2)` | c1 | `10 - (4 - 2)`   | 8     |
| `2 * (3 + 4)`  | c1 | `2 * (3 + 4)`    | 14    |
 
### Analisis de Resultados:
 
**1. `10 - 4 - 2`: aqui solo importa la asociatividad.**
Los dos operadores son `-`, del mismo nivel, asi que la precedencia no decide nada; solo importa hacia que lado se agrupa. Con asociatividad izquierda (c1 y c2) se agrupa como `(10 - 4) - 2 = 4`. Con asociatividad derecha (c3 y c4) se agrupa como `10 - (4 - 2) = 8`. Por eso c1 = c2 y c3 = c4: el orden de precedencia entre los dos niveles no afecta cuando solo hay un nivel de operadores.
 
**2. `2 * 3 + 4`: aqui solo importa la precedencia.**
Hay un `*` y un `+`, cada uno una sola vez, asi que no hay dos operadores iguales seguidos y la asociatividad no interviene. Cuando `*` y `/` tienen mayor precedencia (c1 y c3) se obtiene `(2 * 3) + 4 = 10`. Cuando la tienen `+` y `-` (c2 y c4) se obtiene `2 * (3 + 4) = 14`. Por eso aqui c1 = c3 y c2 = c4.
 
**3. Con parentesis se anulan la precedencia y la asociatividad.**
En c1, `10 - 4 - 2` daba 4 y `2 * 3 + 4` daba 10. Al agregar parentesis, `10 - (4 - 2)` da 8 (el mismo valor que la asociatividad derecha de c3 daba sin parentesis) y `2 * (3 + 4)` da 14 (el mismo valor que la precedencia de la suma de c2 daba sin parentesis). Es decir, con parentesis se puede forzar a mano en c1 lo que otros casos hacen por defecto. Esto ocurre porque la alternativa `'(' expr ')'` forma un subarbol que se evalua completo antes de combinarlo con el resto, sin importar como este configurada la regla. Esa alternativa (`Parens1` a `Parens4`) es identica en las cuatro reglas, por lo que el efecto es el mismo en c2, c3 y c4.
 
**4. Como lo resuelve ANTLR4.**
- La precedencia la define el **orden de las alternativas** en la regla: la que aparece primero tiene mayor precedencia. Por eso `expr1` (multiplicacion primero) y `expr2` (suma primero) dan resultados distintos en `2 * 3 + 4`.
- La asociatividad por defecto es izquierda. Con `<assoc=right>` (casos 3 y 4) el arbol se forma hacia la derecha, como se ve en `10 - (4 - 2)`.
- `*` y `/` estan en una sola alternativa (`op=('*'|'/')`), igual que `+` y `-`, por lo que comparten el mismo nivel de precedencia.
**Conclusion.**
La precedencia y la asociatividad son configuraciones independientes: la primera decide entre operadores *distintos* y la segunda entre operadores *iguales* (del mismo nivel). Los parentesis, por su parte, anulan ambas configuraciones para la parte que encierran. Las 10 pruebas confirman que las 4 variantes de la gramatica se comportan como se espera.
 
## 6. Estructura de Archivos
 
- `Calculadora.g4`: Gramatica unica con los 4 esquemas de precedencia y asociatividad.
- `Calc.java`: Clase principal de entrada.
- `EvalVisitor.java`: Recorrido del arbol y evaluacion aritmetica.
- `t.expr`: Archivo con expresiones de prueba.
- `README.md`: Documentacion tecnica de ejecucion.
- `../Tarea Análisis de Precedencia y asociatividad.pdf`: Documento formal teorico.
- `README.md`: Documentacion tecnica de ejecucion.
- `../Tarea Análisis de Precedencia y asociatividad.pdf`: Documento formal teorico.
