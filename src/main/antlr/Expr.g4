grammar Expr;

expr:   expr PLUS term
    |   expr MINUS term
    |   term
    ;

term:   term MUL fact
    |   term DIV fact
    |   fact
    ;

fact:   INT
    |   LBRACKET expr RBRACKET
    ;

INT      :   [0-9]+;
LBRACKET :   '(';
RBRACKET :   ')';
PLUS     :   '+';
MINUS    :   '-';
MUL      :   '*';
DIV      :   '/';
