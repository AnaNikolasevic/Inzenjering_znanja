% ABBREVIATIONS FOR TESTS
%-------------------------


% Emergency and overdose drug testing (eaodt)
% Antinuclear antibodies (ana)
% Anti-smooth muscle antibodies (asma) and anti-actin antibodies 
% Antibodies to liver and kidney microsomes (anti_lkm1)
% Complete blood count (cbc)
% Liver biopsy
% Computed tomography (ct) 
% Endoscopic retrograde cholangiopancreatography (ercp)
% Magnetic resonance imaging (mri)
% Upper Endoscopy (edg)
% Perinuclear anti-neutrophil antibodies (pANCA) 
% Anti-Saccharomyces Cerevisiae antibody (ASCA)
% Anti-flagellin antibody (cBir1) 


%-------------------------------------------------------------------------------------------------------------------------------------------
% LIST OF TESTS
%--------------

bravo_wireless(_, n/a).
ambulatory_pH_probe(_, n/a).
esophageal_impedance_pH(_, n/a).
esophageal_manometry(_, n/a).
her2(_, n/a).
biopsy(_, n/a).
cbc(_, n/a).
eaodt(_, n/a).
pancreatic_enzymes(_, n/a).
ct(_, n/a). 
ercp(_, n/a). 
ultrasound(_, n/a).
mri(_,n/a).
barium_swallow(_, n/a).
x_ray(_, n/a).
endoscopy(_, n/a).
urinalysis(_, n/a).
c13(_,n/a).
c14(_,n/a).
stool_test(_,n/a).
blood_test(_,n/a).
h_pylori_test(_,n/a).
c19_9(_, n/a).
cea(_,n/a).
mr_cholangiopancreatography(_,n/a).
anti_hepatitis_A(_, n/a).
anti_hepatitis_B(_, n/a).
anti_hepatitis_C(_, n/a).
alt(_, n/a).
ast(_, n/a).
alp(_, n/a).
bilirubin(_, n/a).
albumin(_, n/a).
cBir1(_,n/a).



%--------------------------------------------------------------------------------------------------------------------------------------
% TESTS FOR A PARTICULAR DISEASE
%--------------------------------

test( hiatal_hernia, [barium_swallow, x_ray, endoscopy, ct]).
test( gallstone, [cbc, bilirubin, alp, lipase, pancreatic_enzymes, mri, ct, ercp]).
test( gerb, [endoscopy, x_ray, esophageal_manometry, ambulatory_pH_probe, esophageal_impedance_pH, bravo_wireless]).
test( esophageal_cancer, [barium_swallow, x_ray,  endoscopy, biopsy, ct, mri, ultrasound, cbc, her2]).
test( stomach_cancer, [barium_swallow,  endoscopy, biopsy, ct, mri, ultrasound, cbc, her2]).

test(gastritis, [cbc, urinalysis, endoscopy, c13, c14, stool_test, blood_test]).
test(pancreatic_cancer,[x_ray, c19_9, cea, mri, ct, mr_cholangiopancreatography]).
test(ulcerative_colitis, [blood_test, cbc]).
test(crohn_disease, [blood_test, cBir1, cbc]).

test(hepatitis_A, [anti_hepatitis_A]).
test(hepatitis_B, [anti_hepatitis_B]).
test(hepatitis_C, [anti_hepatitis_C]).
test(cirrhosis, [alt, ast, alp, bilirubin, albumin, cbc, liver_biopsy]).
test(drug_induced_hepatitis, [alt, ast, alp, bilirubin, eaodt]).
test(inherited_hepatitis, [iron_tests, alpha_1_antirypsin, ceruloplasmin, copper_tests]).
test(autoimmune_hepatitis, [ana, asma, anti_lkm1]).

%-----------------------------------------------------------------------------------------------------------------------------------------------
% RESULTS OF TESTS
%-----------------

cbc(ana,high_wbc).
ct(ana, hiatal_hernia).

anti_hepatitis_A(anaa, negative).
anti_hepatitis_B(anaa, negative).
anti_hepatitis_C(anaa, positive).
alt(aca, high).
ast(aca, high).
alp(aca, normal).
unconjugated_bilirubin(aca, high).
albumin(aca, low).
liver_biopsy(isi, cirrhosis).

c19_9(sara, high_level).
x_ray(sara, pancreatic_cancer).
c13(peca, h_pylori).
blood_test(una, pANCA).
cbc(una, anemia).
cBir1(tijana, positive).
cbc(tijana, infection).




cbc(mina, anemia).
biopsy(mima, esophageal_cancer ).
esophageal_manometry(masa, gerb).
bilirubin(neca, high).
alp(neca, high).
barium_swallow(marti, hiatal_hernia).
endoscopy(ljilja, stomach_cancer).
cbc(ljilja, anemia).




%--------------------------------------------------------------------------------------------
% RULES 
%-------

contains(S,[]).
contains(S,[H|T]) :- member(H,S), contains(S,T).

% OLD
possible_diseases(Name,D) :- personal_symptoms(Name, L),  symptoms(D,L1), contains(L1,L),
                             personal_anamnesis(Name, A),  anamnesis(D,A1), contains(A1,A). 

% NEW
% possible_diseases(Name, listOfSymptoms, anamnesis, answer)

%possible_diseases(Name, L, A, D) :- symptoms(D,L1), contains(L1,L),
%									anamnesis(D,A1), contains(A1,A). 
%old
additional_tests(Name,T) :-  possible_diseases(Name,D), test(D,T).

%new
%additional_tests(Name, L, A, T) :-  possible_diseases(Name,L,A,D), test(D,T).

medications(Name,M) :-  diagnosis(Name,D), medication(D,M).

anti_hepatitis_C(dsa,null).
anti_hepatitis_C(das,positive).
anti_hepatitis_C(ww,positive).
anti_hepatitis_C(qqq,null).
anti_hepatitis_C(rrr,positive).
anti_hepatitis_C(asdsada,positive).
 anti_hepatitis_A(qqq,positive).
 anti_hepatitis_B(qqq,positive).
