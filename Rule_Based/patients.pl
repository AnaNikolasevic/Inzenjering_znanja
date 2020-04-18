female(ana).
female(sara).
female(masa).
male(aca).
male(peca).
male(neca).

patient(X):- male(X).
patient(X):- female(X).

age(ana, 33).
age(aca,46).
age(sara,22).
age(peca,65).
age(masa,50).
age(neca,70).

baby(X):-age(X,Y), Y<=2.
child(X):- age(X,Y), Y>2, Y<=12.
adolescent(X):- age(X,Y), Y>=13, Y<=19.
younger_adult(X):- age(X,Y), Y>=20, Y<40.
older_adult(X):- age(X,Y), Y>=40, Y<60.
old(X):- age(X,Y), Y>=60.

alcoholic(sara).
alcoholic(peca).