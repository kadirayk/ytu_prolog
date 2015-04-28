:- [fiiller, isimler].

vardir(Ozne, Nesne, Mekan, File):- 
	iliski(Nesne, 'nerede bulunur? ', Mekan),
	write(Ozne), write(' '), write(Nesne), write(' ('), write(Mekan), write('da '), write(Nesne), write(' bulunur) '), nl,
	write(File, Ozne), write(File, ' '), write(File, Nesne), write(File, ' ('), write(File, Mekan), write(File, 'da '), write(File, Nesne), write(File, ' bulunur) \n').
		
kapsar(Mekan1, Mekan3, File):-
	iliski(Mekan1, 'nerede bulunur? ', Mekan2),
	iliski(Mekan2, 'nerede bulunur? ', Mekan3),
	write(Mekan1), write(' bulunur -> '), write(Mekan3), nl,
	write(File, Mekan1), write(File, ' bulunur -> '), write(File, Mekan3), nl, 
	vardir2(Mekan1, Mekan2, File).


yapar(Ozne, Nesne, Eylem, File):-
	iliski(Eylem, 'kim/ne yapar?', Nesne),
	write(Ozne), write(' '), write(Eylem), write(' ( '), write(Ozne), write(' '), write(Nesne), write(' ise, '),
	write(Nesne), write(' '), write(Eylem), write(' yaparsa '), write(Ozne), write(' '), write(Eylem), write(' yapar )'), nl,
	write(File, Ozne), write(File, ' '), write(File, Eylem), write(File, ' ( '), write(File, Ozne), write(File, ' '), write(File, Nesne), write(File, ' ise, '),
	write(File, Nesne), write(File, ' '), write(File, Eylem), write(File, ' yaparsa '), write(File, Ozne), write(File, ' '), write(File, Eylem), write(File, ' yapar ) \n').

sebepolmak(Ozne, Nesne, Eylem1, File):-
	iliski(Eylem1, 'yapınca ne olur?', Eylem),
	write(Ozne), write(' '), write(Eylem),
	write(' ( '), write(Ozne), write(' '),  write(Nesne), write(' ise, '), write(Nesne), write(' '), write(Eylem1), write(' yaparsa, '),
	write(Eylem1), write(' yapmak '), write(Eylem), write(' yapmaya sebep oluyorsa '), write(Ozne), write(' '), write(Eylem), write(' yapar )'), nl,
	write(File, Ozne), write(File, ' '), write(File, Eylem),
	write(File, ' ( '), write(File, Ozne), write(File, ' '),  write(File, Nesne), write(File, ' ise, '), write(File, Nesne), write(File, ' '), write(File, Eylem1), write(File, ' yaparsa, '),
	write(File, Eylem1), write(File, ' yapmak '), write(File, Eylem), write(File, ' yapmaya sebep oluyorsa '), write(File, Ozne), write(File, ' '), write(File, Eylem), write(File, ' yapar ) \n').

	
cumle(Ozne, Nesne, Zaman, Mekan, Eylem, File):- vardir(Ozne, Nesne, Mekan, File), yapar(Ozne, Nesne, Eylem, File), sebepolmak(Ozne, Nesne, Eylem, File), fail.
