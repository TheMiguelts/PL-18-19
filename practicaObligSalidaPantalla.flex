
import java_cup.runtime.*;
import java.io.*;


%%
%unicode
%line
%class AnalizadorLexico
%cup
%column

/* PALABRAS RESERVADAS */
entero = "INTEGER"
real = "REAL"
funcion="FUNCTION"
procedimiento="PROCEDURE"
begin="BEGIN"
end="END"
vacio="VOID"
estIf = "if"
estThen = "then"
estElse = "else"
estFor = "for"
estWhile = "while"
estDo = "do"
estUntil = "until"
estRepetir="repeat"
punto = "."
to="to"
downto="downto"
programa="program"
variables="var"
unit = "UNIT"

/* OPERADORES */
opMas = "+"
opMenos = "-"
opMultiplicacion = "*"
opDivision = "div"
opMayor = ">"
opMenor = "<"
opMayorIgual = ">="
opMenorIgual = "<="
opModulo = "mod"
opAsignacion = ":="
opIgual = "="
opNot = "not"
opAnd = "and"
opOr= "or"


/* SIMBOLOS RESERVADOS */
abrirP = "("
cerrarP = ")"
abrirLl = "{"
cerrarLl = "}"
puntoComa = ";"
coma = ","
dosPuntos=":"
abrirC = "["
cerrarC = "]"
constante = "const"

/* IDENTIFICADORES */
identificadores = ([:letter:])([:letter:]|[0-9]|"_")*

/* NUMEROS ENTEROS */
cero = "0"
enteroDecimal = ("+"|"-")?[1-9][0-9]*

/* NUMEROS REALES */
realDecimal = ("+"|"-")?([1-9][0-9]*+"."|"0.")[0-9]+
realExponencial = ("+"|"-")?([1-9][0-9]*+"."|"0.")("e"|"E")("+"|"-")?[0-9]*
realMixto = [realDecimal]("e"|"E")("+"|"-")?[0-9]*

/* COMENTARIOS */
comentarioLinea = "{"[^\n]"}"
comentarioParrafo = "(*"(.|"\r\n")*"*)"

/* CONSTANTES LITERALES */
constanteLiteral = "'"([^"'"]|"\\'")+"'"

/* CARACTERES ESPECIALES */
espacio = " "
saltos = [\r\n]
tabuladores = [\t]
%%
{vacio}			{System.out.println("TOKEN VACIO: "+yytext());}
{entero}		{System.out.println("TOKEN ENTERO: "+yytext());}
{real}			{System.out.println("TOKEN REAL: "+yytext());}

{estIf}			{System.out.println("TOKEN PR IF: "+yytext());}
{estThen}		{System.out.println("TOKEN PR THEN: "+yytext());}
{estElse}		{System.out.println("TOKEN PR ELSE: "+yytext());}
{estFor}		{System.out.println("TOKEN PR FOR: "+yytext());}
{estWhile}		{System.out.println("TOKEN PR WHILE: "+yytext());}
{estDo}			{System.out.println("TOKEN PR DO: "+yytext());}
{estUntil}		{System.out.println("TOKEN PR UNTIL: "+yytext());}
{estRepetir} {System.out.println("TOKEN PR UNTIL: "+yytext());}

{to}        {System.out.println("TOKEN PR TO: "+yytext());}
{downto}    {System.out.println("TOKEN PR DOWNTO: "+yytext());}
{programa}  {System.out.println("TOKEN PR PROGRAM: "+yytext());}
{variables} {System.out.println("TOKEN PR VAR: "+yytext());}
{unit}		{System.out.println("TOKEN PR UNIT: "+yytext());}
{constante} {System.out.println("TOKEN PR CONST: "+yytext());}



{opMas}			{System.out.println("TOKEN OP MAS: "+yytext());}
{opMenos}		{System.out.println("TOKEN OP MENOS: "+yytext());}
{opMultiplicacion}	{System.out.println("TOKEN OP MULT: "+yytext());}
{opDivision}		{System.out.println("TOKEN OP DIV: "+yytext());}
{opMayor}		{System.out.println("TOKEN OP MAYOR: "+yytext());}
{opMenor}		{System.out.println("TOKEN OP MENOR: "+yytext());}
{opMayorIgual}		{System.out.println("TOKEN OP MAYOR IGUAL: "+yytext());}
{opMenorIgual}		{System.out.println("TOKEN OP MENOR IGUAL: "+yytext());}
{opModulo}		{System.out.println("TOKEN OP MODULO: "+yytext());}
{opAsignacion}		{System.out.println("TOKEN OP ASIGNACION: "+yytext());}
{opIgual}		{System.out.println("TOKEN OP IGUAL: "+yytext());}
{opNot}			{System.out.println("TOKEN OP NOT: "+yytext());}
{opAnd}			{System.out.println("TOKEN OP AND: "+yytext());}
{opOr}			{System.out.println("TOKEN OP OR: "+yytext());}

{abrirP}		{System.out.println("TOKEN ABRIR PARENTESIS: "+yytext());}
{cerrarP}		{System.out.println("TOKEN CERRAR PARENTESIS: "+yytext());}
{abrirLl}		{System.out.println("TOKEN ABRIR LLAVE: "+yytext());}
{cerrarLl}		{System.out.println("TOKEN CERRAR LLAVE: "+yytext());}
{punto}		{System.out.println("TOKEN PUNTO: "+yytext());}
{puntoComa}		{System.out.println("TOKEN PUNTO COMA: "+yytext());}
{coma}			{System.out.println("TOKEN COMA: "+yytext());}
{dosPuntos}  {System.out.println("TOKEN DOS PUNTOS: "+yytext());}
{abrirC}		{System.out.println("TOKEN ABRIR CORCHETES: "+yytext());}
{cerrarC}		{System.out.println("TOKEN CERRAR CORCHETES: "+yytext());}


{identificadores}		{System.out.println("TOKEN IDENTIFICADOR: "+yytext());}

{cero}			{System.out.println("TOKEN CERO: "+yytext());}
{enteroDecimal}		{System.out.println("TOKEN DECIMAL: "+yytext());}


{realDecimal}		{System.out.println("TOKEN REAL DECIMAL: "+yytext());}
{realExponencial} {System.out.println("TOKEN REAL EXPONENCIAL: "+yytext());}
{realMixto}  {System.out.println("TOKEN REAL MIXTO: "+yytext());}

{comentarioLinea}	{System.out.println("TOKEN COMENTARIO LINEA: "+yytext());}
{comentarioParrafo}	{System.out.println("TOKEN COMENTARIO PARRAFO: "+yytext());}

{constanteLiteral}	{System.out.println("TOKEN CONSTANTE LITERAL: "+yytext());}

{espacio}		{System.out.println("TOKEN ESPACIO/SALTO/TABULADOR");}
{saltos}		{System.out.println("TOKEN ESPACIO/SALTO/TABULADOR");}
{tabuladores}		{System.out.println("TOKEN ESPACIO/SALTO/TABULADOR");}
[^]   {System.out.println("TOKEN NO RECONOCIDO: "+yytext()+"["+yyline+","+yycolumn+"]");}
