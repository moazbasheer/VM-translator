@17
D=A
@SP
A=M
M=D
@SP
M=M+1
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@EQ0
D;JEQ
@SP
A=M
M=0
@SP
M=M+1
@ENDEQ0
0;JMP
(EQ0)
@SP
A=M
M=-1
@SP
M=M+1
(ENDEQ0)
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
@16
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@EQ1
D;JEQ
@SP
A=M
M=0
@SP
M=M+1
@ENDEQ1
0;JMP
(EQ1)
@SP
A=M
M=-1
@SP
M=M+1
(ENDEQ1)
@16
D=A
@SP
A=M
M=D
@SP
M=M+1
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@EQ2
D;JEQ
@SP
A=M
M=0
@SP
M=M+1
@ENDEQ2
0;JMP
(EQ2)
@SP
A=M
M=-1
@SP
M=M+1
(ENDEQ2)
@892
D=A
@SP
A=M
M=D
@SP
M=M+1
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@LT0
D;JLT
@SP
A=M
M=0
@SP
M=M+1
@ENDLT0
0;JMP
(LT0)
@SP
A=M
M=-1
@SP
M=M+1
(ENDLT0)
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
@892
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@LT1
D;JLT
@SP
A=M
M=0
@SP
M=M+1
@ENDLT1
0;JMP
(LT1)
@SP
A=M
M=-1
@SP
M=M+1
(ENDLT1)
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@LT2
D;JLT
@SP
A=M
M=0
@SP
M=M+1
@ENDLT2
0;JMP
(LT2)
@SP
A=M
M=-1
@SP
M=M+1
(ENDLT2)
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@GT0
D;JGT
@SP
A=M
M=0
@SP
M=M+1
@ENDGT0
0;JMP
(GT0)
@SP
A=M
M=-1
@SP
M=M+1
(ENDGT0)
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@GT1
D;JGT
@SP
A=M
M=0
@SP
M=M+1
@ENDGT1
0;JMP
(GT1)
@SP
A=M
M=-1
@SP
M=M+1
(ENDGT1)
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@GT2
D;JGT
@SP
A=M
M=0
@SP
M=M+1
@ENDGT2
0;JMP
(GT2)
@SP
A=M
M=-1
@SP
M=M+1
(ENDGT2)
@57
D=A
@SP
A=M
M=D
@SP
M=M+1
@31
D=A
@SP
A=M
M=D
@SP
M=M+1
@53
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
M=M+D
@SP
M=M+1
@112
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
M=M-D
@SP
M=M+1
@SP
M=M-1
A=M
M=-M
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
M=M&D
@SP
M=M+1
@82
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
M=M|D
@SP
M=M+1
@SP
M=M-1
A=M
M=!M
@SP
M=M+1
