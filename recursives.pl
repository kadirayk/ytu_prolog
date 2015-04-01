yazdir(M):- not(M=0),write(M),write(' '),K is M-1,yazdir(K).

topla(A,B,C):- C is A + B.

guc(X,1,X).
guc(X,Y,Z):-
    Y>1,
    G is Y-1,
    guc(X,G,T),
    Z is X*T.

fakt(0,1).
fakt(X,Y):- X>0, G is X-1, fakt(G,T), Y is X * T.

tumtopla(1,1).
tumtopla(X,Y):- X > 1, G is X-1, tumtopla(G, T), Y is X + T.
