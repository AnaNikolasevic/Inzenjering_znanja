% DIAGNOSIS
%-----------

diagnosis(X,  gallstone):- (cbc(X, high_wbc); liver_panel(X, high_bilirubin); liver_panel(X, high_ALP); lipase(X, high_pancreatic_enzymes)), 
			  	   (mri(X, gallstone); ct(X, gallstone); ercp(X, gallstone); ultrasound(X, gallstone)).

diagnosis(X, hiatal_hernia):- barium_swallow(X, hiatal_hernia); x_ray(X, hiatal_hernia); 
				      endoscopy(X, hiatal_hernia); ct(X, hiatal_hernia).

diagnosis(X, gastritis):- cbc(X, anemia),
			        (urinalysis(X, 8-ohdg); endoscopy(X, inflamation));
                          h_pylori_test(X, positive).

diagnosis(X, pancreatic_cancer):- (c19_9(X, high_level); cea(X, high_level)),
                                  x_ray(X, pancreatic_cancer);
                                  (mr_cholangiopancreatography(X, pancreatitic_cancer); mri(X, pancreatitic_cancer); ct(X, pancreatitic_cancer)).

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
                  
