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
anamnesis(gastritis, [genetic_predisposition_gastritis, active_smoking, nsaid_abuse, stress, abusing_alchohol]).
anamnesis(pancreatic_cancer, [active_smoking, abusing_alchohol, nt0ba, bmi_over_35, family_history_of_pancreatic_cancer]).
anamnesis(ulceraive_colitis, [active_smoking, nsaid_abuse]).
anamnesis(crohn_disease, [active_smoking, nsaid_abuse, family_history_of_crohn_disease]).

anamnesis(esophageal_cancer, [abusing_alchohol, gerb, barretts_esophagus, overweight, active_smoking]).
anamnesis(hiatal_hernia, [overweight, pregnancy, active_smoking, heavy_lifting, physical_strain,  persistent_coughing, persistent_vomiting,  born_with_larger_hiatus, straining_during_bowel_movements]).
anamnesis(gallstone, [overweight, high_blood_cholesterol, family_history_of_gallstones, pregnancy, diabetes,  crohns_disease, ibs, rapid_weight_loss]).
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



patient(X):- male(X).
patient(X):- female(X).



%baby(X):- age(X,Y), Y<=2.
%child(X):- age(X,Y), Y>2, Y<=12.
%adolescent(X):- age(X,Y), Y>=13, Y<=19.
%younger_adult(X):- age(X,Y), Y>=20, Y<40.
%older_adult(X):- age(X,Y), Y>=40, Y<60.
%old(X):- age(X,Y), Y>=60.


personal_anamnesis(masa_matovic,[active_smoking, overweight]).
age(masa_matovic,50).
female(masa_matovic).
personal_anamnesis(zoran_nikolasevic,[family_history_of_liver_disease]).
age(zoran_nikolasevic,54).
male(zoran_nikolasevic).
personal_anamnesis(sara_fojkar,[active_smoking, stress]).
age(sara_fojkar,23).
female(sara_fojkar).

personal_anamnesis(milana_galin,[family_history_of_liver_disease]).
age(milana_galin,23).
female(milana_galin).


