package com.sonarbay.plugin;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.sonarbay.plugin.psi.DockerTypes;
import com.intellij.psi.TokenType;
%%

%class DockerLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{
    return;
%eof}

CRLF=\R
WHITE_SPACE=[\ \n\t\f]
FIRST_VALUE_CHARACTER=[^n\f\\] | "\\"{CRLF} | "\\"
VALUE_CHARACTER=[^\n\f\\] | "\\"{CRLF} | "\\"
SEPARATOR=[" "]
KEY_CHARACTER=[^\ \n\t\f\\] | "\\ "

%state WAITING_VALUE

%%

<YYINITIAL> {KEY_CHARACTER}+                                { yybegin(YYINITIAL); return DockerTypes.KEY; }
<YYINITIAL> {SEPARATOR}                                     { yybegin(WAITING_VALUE); return DockerTypes.SEPARATOR; }
<WAITING_VALUE> {CRLF}({CRLF}|{WHITE_SPACE})+               { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
<WAITING_VALUE> {WHITE_SPACE}+                              { yybegin(WAITING_VALUE); return TokenType.WHITE_SPACE; }
<WAITING_VALUE> {FIRST_VALUE_CHARACTER}{VALUE_CHARACTER}*   { yybegin(YYINITIAL); return DockerTypes.VALUE; }
({CRLF}|{WHITE_SPACE})+                                     { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

.                                                           { return TokenType.BAD_CHARACTER; }