
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
escribir = "writeln"

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
{vacio}			{System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.vacio,yyline,yycolumn);}
{entero}		{System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.entero,yyline,yycolumn);}
{real}			{System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.real,yyline,yycolumn);}

{estIf}			{System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.estIf,yyline,yycolumn);}
{estThen}		{System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.estThen,yyline,yycolumn);}
{estElse}		{System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.estElse,yyline,yycolumn);}
{estFor}		{System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.estFor,yyline,yycolumn);}
{estWhile}		{System.out.print(yytext());
              return new java_cup.runtime.Symbol(sym.estWhile,yyline,yycolumn);}
{estDo}			{System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.estDo,yyline,yycolumn);}
{estUntil}		{System.out.print(yytext());
              return new java_cup.runtime.Symbol(sym.estUntil,yyline,yycolumn);}
{estRepetir} {System.out.print(yytext());
              return new java_cup.runtime.Symbol(sym.estRepetir,yyline,yycolumn);}


{to}        {System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.to,yyline,yycolumn);}
{downto}    {System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.downto,yyline,yycolumn);}
{programa}  {System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.programa,yyline,yycolumn);}
{variables} {System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.variables,yyline,yycolumn);}
{unit}		{System.out.print(yytext());
          return new java_cup.runtime.Symbol(sym.unit,yyline,yycolumn);}
{constante} {System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.constante,yyline,yycolumn);}
{begin} {System.out.print(yytext());
        return new java_cup.runtime.Symbol(sym.begin,yyline,yycolumn);}
{end} {System.out.print(yytext());
      return new java_cup.runtime.Symbol(sym.end,yyline,yycolumn);}
{procedimiento} {System.out.print(yytext());
                return new java_cup.runtime.Symbol(sym.procedimiento,yyline,yycolumn);}
{funcion} {System.out.print(yytext());
          return new java_cup.runtime.Symbol(sym.funcion,yyline,yycolumn);}
{escribir} {System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.escribir,yyline,yycolumn);}


{opMas}			{System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.opMas,yyline,yycolumn);}
{opMenos}		{System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.opMenos,yyline,yycolumn);}
{opMultiplicacion}	{System.out.print(yytext());
                    return new java_cup.runtime.Symbol(sym.opMultiplicacion,yyline,yycolumn);}
{opDivision}		{System.out.print(yytext());
                return new java_cup.runtime.Symbol(sym.opDivision,yyline,yycolumn);}
{opMayor}		{System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.opMayor,yyline,yycolumn);}
{opMenor}		{System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.opMenor,yyline,yycolumn);}
{opMayorIgual}		{System.out.print(yytext());
                  return new java_cup.runtime.Symbol(sym.opMayorIgual,yyline,yycolumn);}
{opMenorIgual}		{System.out.print(yytext());
                  return new java_cup.runtime.Symbol(sym.opMenorIgual,yyline,yycolumn);}
{opModulo}		{System.out.print(yytext());
              return new java_cup.runtime.Symbol(sym.opModulo,yyline,yycolumn);}
{opAsignacion}		{System.out.print(yytext());
                  return new java_cup.runtime.Symbol(sym.opAsignacion,yyline,yycolumn);}
{opIgual}		{System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.opIgual,yyline,yycolumn);}
{opNot}			{System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.opNot,yyline,yycolumn);}
{opAnd}			{System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.opAnd,yyline,yycolumn);}
{opOr}			{System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.opOr,yyline,yycolumn);}

{abrirP}		{System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.abrirP,yyline,yycolumn);}
{cerrarP}		{System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.cerrarP,yyline,yycolumn);}
{abrirLl}		{System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.abrirLl,yyline,yycolumn);}
{cerrarLl}		{System.out.print(yytext());
              return new java_cup.runtime.Symbol(sym.cerrarLl,yyline,yycolumn);}
{punto}		{System.out.print(yytext());
          return new java_cup.runtime.Symbol(sym.punto,yyline,yycolumn);}
{puntoComa}		{System.out.print(yytext());
              return new java_cup.runtime.Symbol(sym.puntoComa,yyline,yycolumn);}
{coma}			{System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.coma,yyline,yycolumn);}
{dosPuntos}  {System.out.print(yytext());
              return new java_cup.runtime.Symbol(sym.dosPuntos,yyline,yycolumn);}
{abrirC}		{System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.abrirC,yyline,yycolumn);}
{cerrarC}		{System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.cerrarC,yyline,yycolumn);}


{identificadores}		{System.out.print(yytext());
                    return new java_cup.runtime.Symbol(sym.identificador,yyline,yycolumn);}

{cero}			{System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.cero,yyline,yycolumn);}
{enteroDecimal}		{System.out.print(yytext());
                  return new java_cup.runtime.Symbol(sym.enteroDecimal,yyline,yycolumn);}


{realDecimal}		{System.out.print(yytext());
                return new java_cup.runtime.Symbol(sym.realDecimal,yyline,yycolumn);}
{realExponencial} {System.out.print(yytext());
                  return new java_cup.runtime.Symbol(sym.realExponencial,yyline,yycolumn);}
{realMixto}  {System.out.print(yytext());
            return new java_cup.runtime.Symbol(sym.realMixto,yyline,yycolumn);}

{comentarioLinea}	{System.out.print(yytext());}
{comentarioParrafo}	{System.out.print(yytext());}

{constanteLiteral}	{System.out.print(yytext());
                    return new java_cup.runtime.Symbol(sym.constanteLiteral,yyline,yycolumn);}

{espacio}		{System.out.print(yytext());}
{saltos}		{System.out.print(yytext());}
{tabuladores}		{System.out.print(yytext());}

[^]   {;}
