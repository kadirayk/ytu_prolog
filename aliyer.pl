kapsar(oda,ev).
kapsar(sinif,okul).

vardir(oku,ali,kitap,sinif).
vardir2(git,M,_,Yer):-vardir(_,M,_,Yer). 

vardir2(F,M,B,Yer1):-kapsar(Yer2,Yer1),vardir2(F,M,B,Yer2). 

vardir2(yer,ali,oglenyemegi,mutfak):-vardir2(git,ali,_,ev). 

vardir2(yer,ali,oglenyemegi,kantin):-vardir2(git,ali,_,okul).
