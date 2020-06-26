% ANAMNESIS

% Non-steroidal anti-inflammatory drug (nsaid_abuse)
% Presence of non–type O blood antigens (nt0ba)

%--------------

anamnesis(hepatitis_A, [drug_abuse, unprotected_sex, abusing_alchohol]).
anamnesis(hepatitis_B, [drug_abuse, unprotected_sex, abusing_alchohol, hiv_positive]).
anamnesis(hepatitis_C, [drug_abuse, abusing_alchohol, hiv_positive, hepatitis_C_risk_group]).
anamnesis(drug_induced_hepatitis, [abusing_alchohol, aspirin_abuse, steroids_abuse, antibiotics_abuse, herbal_supplements_abuse, vitamin_supplements_abuse]). 
anamnesis(inherited_hepatitis, [family_history_of_liver_disease]). 
anamnesis(cirrhosis, [autoimmune_hepatitis, hepatitis_A, hepatitis_B, hepatitis_C, abusing_alchohol, inherited_hepatitis, drug_abuse]).
anamnesis(pancreatic_cancer, [active_smoking, abusing_alchohol, nt0ba, bmi_over_35, family_history_of_pancreatic_cancer]).
anamnesis(ulceraive_colitis, [active_smoking, nsaid_abuse]).
anamnesis(crohn_disease, [active_smoking, nsaid_abuse, family_history_of_crohn_disease]).

anamnesis(esophageal_cancer, [abusing_alchohol, gerb, barretts_esophagus, overweight, active_smoking]).
anamnesis(hiatal_hernia, [overweight, pregnancy, active_smoking, heavy_lifting, physical_strain,  persistent_coughing, persistent_vomiting,  born_with_larger_hiatus, straining_during_bowel_movements]).
anamnesis(gerb, [overweight, pregnancy, hiatal_hernia, delayed_stomach_emptying, active_smoking, abusing_alchohol]).
anamnesis(stomach_cancer, [pernicious_anaemia, family_history_of_stomach_cancer, previous_stomach_surgery, h_pylori_infection,
			 overweight, active_smoking, abusing_alchohol, long_term_stomach_inflammation, stomach_polyps, chronic_gastritis,
			diet_high_in_salty_foods, diet_high_in_smoked_foods, diet_low_in_fruits, diet_low_in_vegetables ]).



%-------------------------------------------------------------------------------------------------------------------------------------------
% PERSONAL ANAMNESIS
%---------------------

personal_anamnesis(isi, [hepatitis_A]).
personal_anamnesis(una, active_smoking).

personal_anamnesis(marti, [overweight, active_smoking, persistent_coughing]).
personal_anamnesis(ljilja, [family_history_of_stomach_cancer, chronic_gastritis, diet_high_in_salty_foods]).






%-------------------------------------------------------------------------------------------------------------------------------------------
% PERSONAL INFORMATIONS
%-----------------------

female(ana).
female(una).

patient(X):- male(X).
patient(X):- female(X).

age(ana, 33).
age(una, 25).

%baby(X):- age(X,Y), Y<=2.
%child(X):- age(X,Y), Y>2, Y<=12.
%adolescent(X):- age(X,Y), Y>=13, Y<=19.
%younger_adult(X):- age(X,Y), Y>=20, Y<40.
%older_adult(X):- age(X,Y), Y>=40, Y<60.
%old(X):- age(X,Y), Y>=60.


personal_anamnesis(neca,[family_history_of_gallstones, high_blood_cholesterol, overweight]).
age(neca,70).
male(neca).
personal_anamnesis(masa,[delayed_stomach_emptying, hiatal_hernia, pregnancy]).
age(masa,50).
female(masa).
personal_anamnesis(peca,[abusing_alchohol]).
age(peca,65).
male(peca).
personal_anamnesis(zivka,[abusing_alchohol]).
age(zivka,53).
female(zivka).
personal_anamnesis(zorica,[abusing_alchohol]).
age(zorica,54).
female(zorica).
personal_anamnesis(tijana,[abusing_alchohol]).
age(tijana,60).
female(tijana).
personal_anamnesis(sara,[abusing_alchohol]).
age(sara,30).
female(sara).
personal_anamnesis(stevan_matovic,[gerb]).
age(stevan_matovic,25).
male(stevan_matovic).
personal_anamnesis(biljana_matovic,[gerb]).
age(biljana_matovic,45).
female(biljana_matovic).
personal_anamnesis(mirko_mirkovic,[gerb]).
age(mirko_mirkovic,45).
male(mirko_mirkovic).
personal_anamnesis(mima,[abusing_alchohol]).
age(mima,53).
female(mima).
personal_anamnesis(sara_vukoje,[gerb]).
age(sara_vukoje,30).
female(sara_vukoje).
personal_anamnesis(aca,[aspirin_abuse]).
age(aca,46).
female(aca).
personal_anamnesis(aa,[gerb]).
age(aa,12).
female(aa).
personal_anamnesis(sada,[gerb]).
age(sada,30).
female(sada).
personal_anamnesis(ss,[abusing_alchohol]).
age(ss,20).
female(ss).

