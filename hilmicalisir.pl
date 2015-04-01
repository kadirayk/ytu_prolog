icindedir(berlin, almanya).
icindedir(miami, amerika).
icindedir(venedik, italya).

eylem(gondolabin, hilmi, venedik).

eylem2(F,K,Y):- eylem(_,K,Y).
eylem2(F,K,Y1):- icindedir(Y2,Y1), eylem2(F,K,Y2).

eylem2(calisir, hilmi, mercedes):- eylem2(_,hilmi,almanya).
eylem2(calisir, hilmi, ferrari):- eylem2(_,hilmi, italya).
eylem2(calisir, hilmi, google):- eylem2(_,hilmi, amerika).
