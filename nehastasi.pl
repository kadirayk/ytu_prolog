tansiyon(ahmet,12).
tansiyon(mehmet, 15).
tansiyon(hilmi, 16).
tansiyon(mahzar, 18).
ates(ahmet, 34).
ates(mehmet, 35).
ates(hilmi, 39).
ates(mahzar, 40).
mide_bulantisi(ahmet).
mide_bulantisi(mehmet).
mide_bulantisi(mahzar).
bas_donmesi(hilmi).
bas_donmesi(mahzar).


yuksekTansiyon(X):- tansiyon(X,K), K>13.
yuksekAtes(X):- ates(X,K), K>37.
x_hastaligi(X):- yuksekAtes(X), mide_bulantisi(X).
y_hastaligi(X):- yuksekTansiyon(X), x_hastaligi(X).
z_hastaligi(X):- bas_donmesi(X), y_hastaligi(X).
