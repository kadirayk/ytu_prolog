:- [cikarim].

basla :-
    open('X.txt',write, File),
	write('bos birakmak icin buyuk bir Harf girin'),nl,
    write('Ozne girin'),nl,
    read(Ozne),
	write('Nesne girin'),nl,
    read(Nesne),
	write('Zaman girin'),nl,
    read(Zaman),
	write('Mekan girin'),nl,
    read(Mekan),
    write('Eylem girin'),nl,
    read(Eylem),
    cumle(Ozne, Nesne, Zaman, Mekan, Eylem, File),
    close(File).
