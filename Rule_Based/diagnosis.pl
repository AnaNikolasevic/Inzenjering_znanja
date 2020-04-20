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
diagnosis(X, cirrhosis) :- alt(X, high), ast(X, high), alp(X, normal), unconjugated_bilirubin(X, high), albumin(X, low).
