% ANAMNESIS

% Non-steroidal anti-inflammatory drug (nsaid_abuse)
% Presence of non–type O blood antigens (nt0ba)
% Body_mass_index (BMI)

%--------------

anamnesis(hepatitis_A, [drug_abuse, unprotected_sex, abusing_alchohol]).
anamnesis(hepatitis_B, [drug_abuse, unprotected_sex, abusing_alchohol, hiv_positive]).
anamnesis(hepatitis_C, [drug_abuse, abusing_alchohol, hiv_positive, hepatitis_C_risk_group]).
anamnesis(drug_induced_hepatitis, [abusing_alchohol, aspirin_abuse, steroids_abuse, antibiotics_abuse, herbal_supplements_abuse, vitamin_supplements_abuse]). 
anamnesis(inherited_hepatitis, [family_history_of_liver_disease]). 
anamnesis(cirrhosis, [autoimmune_hepatitis, hepatitis_A, hepatitis_B, hepatitis_C, abusing_alchohol, inherited_hepatitis, drug_abuse]).
anamnesis(gastritis, [genetic_predisposition_gastritis, active_smoking, nsaid_abuse, stress, abusing_alchohol]).
anamnesis(pancreatic_cancer, [active_smoking, abusing_alchohol,nt0ba, BMI_over_35, family_history_of_pancreatic_cancer]).

%-------------------------------------------------------------------------------------------------------------------------------------------
% PERSONAL ANAMNESIS
%---------------------

personal_anamnesis(anaa, [hiv_positive]).
personal_anamnesis(sara, [abusing_alchohol, nt0ba]).
personal_anamnesis(peca, [abusing_alchohol, sterss]).
personal_anamnesis(isi, [hepatitis_A]).

%-------------------------------------------------------------------------------------------------------------------------------------------
% PERSONAL INFORMATIONS
%-----------------------

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

%baby(X):- age(X,Y), Y<=2.
%child(X):- age(X,Y), Y>2, Y<=12.
%adolescent(X):- age(X,Y), Y>=13, Y<=19.
%younger_adult(X):- age(X,Y), Y>=20, Y<40.
%older_adult(X):- age(X,Y), Y>=40, Y<60.
%old(X):- age(X,Y), Y>=60.

