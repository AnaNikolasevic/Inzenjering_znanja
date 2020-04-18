
%Complete blood count (cbc)
cbc(_, negative).

liver_panel(_, negative).

lipase(_, negative).

%Computed tomography (ct) 
ct(_, negative). 

%Endoscopic retrograde cholangiopancreatography (ercp)
ercp(_, negative). 

ultrasound(_, negative).

%Magnetic resonance imaging (mri)
mri(_,negative).

barium_swallow(_, negative).

x_ray(_, negative).

%Upper Endoscopy (edg)
%endoscopy(_, negative).

urinalysis(_, negative).
endoscopy(_,negative).
h_pylori_test(_,negative).
c19_9(_, negative).
cea(_,negative).
mr_cholangiopancreatography(_,negative).

anti_hepatitis_A(_, negative).
anti_hepatitis_B(_, negative).
anti_hepatitis_C(_, negative).
alt(_, negative).
ast(_, negative).
alp(_, negative).
unconjugated_bilirubin(_, negative).
albumin(_, negative).


test( hiatal_hernia, [barium_swallow, x_ray, endoscopy, ct]).
test( gallstone, [cbc, liver_panel, lipase, ultrasound, mri, ct, ercp]).

test(gastritis, [cbc, urinalysis, endoscopy,h_pylori_test]). 
test(pancreatic_cancer,[x_ray, c19_9, cea, mri, ct, mr_cholangiopancreatography]).

test(hepatitis_A, [anti_hepatitis_A]).
test(hepatitis_B, [anti_hepatitis_B]).
test(hepatitis_C, [anti_hepatitis_C]).
test(cirrhosis, [alt, ast, alp, unconjugated_bilirubin, albumin]).

cbc(ana,high_wbc).
ct(ana, hiatal_hernia).

anti_hepatitis_A(anaa, positive).
anti_hepatitis_B(anaa, negative).
anti_hepatitis_C(anaa, negative).
alt(aca, high).
ast(aca, high).
alp(aca, normal).
unconjugated_bilirubin(aca, high).
albumin(aca, low).

cbc(sara,anemia).
urinalysis(sara, 8-ohdg).
h_pylori_test(peca, positive).


contains(S,[]).
contains(S,[H|T]) :- member(H,S), contains(S,T).

possible_diseases(Name,D) :- personal_symptoms(Name, L),  symptoms(D,L1), contains(L1,L). 

additional_tests(Name,T) :-  possible_diseases(Name,D), test(D,T).