grammar Calculadora;

prog : stat+ ;

stat : ('c1' | 'caso1') ':'? expr1 NEWLINE   # printCaso1
     | ('c2' | 'caso2') ':'? expr2 NEWLINE   # printCaso2
     | ('c3' | 'caso3') ':'? expr3 NEWLINE   # printCaso3
     | ('c4' | 'caso4') ':'? expr4 NEWLINE   # printCaso4
     | NEWLINE                               # blank
     ;

/* Caso 1: Multiplicacion y Division Alta | Asociatividad Izquierda */
expr1 : expr1 op=('*'|'/') expr1   # MulDiv1
      | expr1 op=('+'|'-') expr1   # AddSub1
      | INT                        # Int1
      | '(' expr1 ')'              # Parens1
      ;

/* Caso 2: Suma y Resta Alta | Asociatividad Izquierda */
expr2 : expr2 op=('+'|'-') expr2   # AddSub2
      | expr2 op=('*'|'/') expr2   # MulDiv2
      | INT                        # Int2
      | '(' expr2 ')'              # Parens2
      ;

/* Caso 3: Multiplicacion y Division Alta | Asociatividad Derecha */
expr3 : <assoc=right> expr3 op=('*'|'/') expr3   # MulDiv3
      | <assoc=right> expr3 op=('+'|'-') expr3   # AddSub3
      | INT                                      # Int3
      | '(' expr3 ')'                            # Parens3
      ;

/* Caso 4: Suma y Resta Alta | Asociatividad Derecha */
expr4 : <assoc=right> expr4 op=('+'|'-') expr4   # AddSub4
      | <assoc=right> expr4 op=('*'|'/') expr4   # MulDiv4
      | INT                                      # Int4
      | '(' expr4 ')'                            # Parens4
      ;

INT : [0-9]+ ;
NEWLINE : '\r'? '\n' ;
WS : [ \t]+ -> skip ;
