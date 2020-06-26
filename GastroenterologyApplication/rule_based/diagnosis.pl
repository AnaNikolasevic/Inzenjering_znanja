% DIAGNOSIS
%-----------

diagnosis(X,  gallstone):- (cbc(X, high_wbc), bilirubin(X, high), alp(X, high), pancreatic_enzymes(X, high)); 
			  	   (mri(X, gallstone); ct(X, gallstone); ercp(X, gallstone); ultrasound(X, gallstone)).

diagnosis(X, hiatal_hernia):- barium_swallow(X, hiatal_hernia); x_ray(X, hiatal_hernia); 
				      endoscopy(X, hiatal_hernia); ct(X, hiatal_hernia).

diagnosis(X, gerb):-  x_ray(X, gerb); endoscopy(X, gerb); esophageal_manometry(X, gerb); ambulatory_pH_probe(X, gerb); 
			esophageal_impedance_pH(X, gerb); bravo_wireless(X, gerb).  


diagnosis(X, esophageal_cancer):- (barium_swallow(X, esophageal_cancer); x_ray(X, esophageal_cancer); mri(X, esophageal_cancer ); 
				ultrasound(X, esophageal_cancer); endoscopy(X, esophageal_cancer); ct(X, esophageal_cancer); 
				biopsy(X, esophageal_cancer)), (cbc(X, anemia); her2(X, high)).

diagnosis(X, stomach_cancer):- (barium_swallow(X, stomach_cancerr); mri(X, stomach_cancer ); 
				ultrasound(X, stomach_cancer); endoscopy(X, stomach_cancer); ct(X, stomach_cancer); 
				biopsy(X, stomach_cancer)), (cbc(X, anemia); her2(X, high)).
 


diagnosis(X, gastritis):- (cbc(X, anemia),(urinalysis(X, 8-ohdg); endoscopy(X, inflamation)));
                         (c13(X, h_pylori); c14(X, h_pylori); blood_test(X, h_pylori); stool_test(X, h_pylori)).

diagnosis(X, pancreatic_cancer):- (c19_9(X, high); cea(X, high)),
                                  x_ray(X, pancreatic_cancer);
                                  mri(X, pancreatitic_cancer); ct(X, pancreatitic_cancer).

diagnosis(X, ulcerative_colitis):-blood_test(X, pANCA), (cbc(X, anemia); cbc(X, infection)). 


diagnosis(X, hepatitis_A) :- anti_hepatitis_A(X, positive).
diagnosis(X, hepatitis_B) :- anti_hepatitis_B(X, positive).
diagnosis(X, hepatitis_C) :- anti_hepatitis_C(X, positive).
diagnosis(X, drug_induced_hepatitis) :- alt(X, high), ast(X, high), alp(X, normal), bilirubin(X, normal), albumin(X, normal);
                                        eaodt(X, toxic).
diagnosis(X, hemochromatosis) :- iron_tests(X, hemochromatosis).
diagnosis(X, alpha_1_antitrypsin_deficiency) :- alpha_1_antirypsin(X, low).
diagnosis(X, wilson_disease ) :- ceruloplasmin(X, positive), copper_tests(X, positive).
diagnosis(X, autoimmune_hepatitis_type1) :- ana(X, positive);
                                            asma(X, positive).
diagnosis(X, autoimmune_hepatitis_type2) :- anti_lkm1(X, positive).

diagnosis(X, cirrhosis) :- (alt(X, high), ast(X, high), (alp(X, normal); alp(X, high)), bilirubin(X, high), (albumin(X, low); albumin(X, normal)), cbc(X, platelets_low));
                           liver_biopsy(X, cirrhosis).
diagnosis(X, crohn_disease) :- (blood_test(X, asca); cBir1(X, positive)),
							 (cbc(X, anemia); cbc(X, infection)).

