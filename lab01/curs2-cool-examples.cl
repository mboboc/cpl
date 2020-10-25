-- class List inherits IO {
-- 	-- listele au cel putin un element
-- 	elem : Int;
-- 	next : List;

-- 	init(e: Int, n : List) : List {
-- 		{
-- 			elem <- e;
-- 			next <- n;
-- 			self;
-- 		}
-- 	};

-- 	print() : Object {
-- 		{
-- 			out_int(elem);
-- 			out_string("\n");
-- 			if not (isvoid next) then next.print() else out_string("\n") fi; 
-- 		}
-- 	};
-- };

class List inherits IO {

	isEmpty() : Bool {
		true
	};

	hd() : Int {
		{
			abort();
			0; -- nu ajungem aici niciodata, dar ca sa satisfacem returnul functiei
		}
	};

	tl() : List {
		{
			abort();
			self;
		}
	 };

	-- cream o noua lista hd = e, tail = self
	cons(e : Int) : List {
		new Cons.init(e, self)
	};

	print() : IO {
		out_string("\n")
	};
};

class Cons inherits List {
	hd : Int;
	tl : List;

	init(h : Int, t : List) : List {
		{
			hd <- h;
			tl <- t;
			self;
		}
	};


	isEmpty() : Bool {
		true
	};

	hd() : Int { hd };

	tl() : List { tl };

	print() : IO {
		{
			out_int(hd);
			tl.print();
		}
	};

};

class Main inherits IO {
	--io : IO <- new IO; --void

	main() : Object {{

		
		-- let io : IO <- new Main
		-- in {
		-- 	--io.out_string("Hello, CPL!\n");
		-- 	self.out_string(io.type_name()); -- afiseaza tipul dinamic
		-- 	io.out_string("\n");
		-- };

		-- case : doar ca sa verifici tipul dinamic
		-- daca am un obiect pe care l-am declarat static Object si dinamic String
		-- va trebuie sa facem un proxy (un obiect de e de tip String caruia ii atribuim var de tip Object)
		-- mai particular, ca sa ne lase verificatorul de tipuri la comilare sa chemam metode de clase String)
		-- daca nu matchuieste nimic da eroare la runtime
		-- fiecare ramura are domeniu diferit de vizibilitate
		-- daca avem 2 ramuri la fel --> eroare la compilare
		-- daca avem o singura expresie --> fara ";""
		-- daca avem mai multe --> neaparat ";"
		-- let x : Object <- "abc"
		-- in case x of
		-- 	s : String => {
		-- 		out_string(s);
		-- 		out_string("\n");
		-- 	};
		-- 	n : Int => out_int(n);
		-- 	o : Object => 0; -- nu conteaza ordinea, face match pe cel mai particular, cel mai apropiat de cel mai particular
		-- esac;

		-- let empty : List,
		-- 	list : List <- new List.init(1, new List.init(2, new List.init(3, empty)))
		-- in
		-- 	list.print();

		let list : List <- new List.cons(1).cons(2).cons(3),
			temp : List <- list
		in {
			list.print();
			while not temp.isEmpty() loop
				{
					out_int(temp.hd());
					temp <- temp.tl();
				} pool;
		};

		let m : Main <- new Main
		in m.out_string("abc").fact_rec(5); -- daca tipul static Main, tipul intors de functie e tipul static
	}};

	fact_rec(n : Int) : Int {
		-- nu poti sa lasi functia goala
		-- if ... then ... else --> neaparat, nu poti avea if fara else
		if n = 0 then 1 else n * fact_rec(n - 1) fi
	};

	fact_iter(n : Int) : Int {
		-- let p : Int <- new Int 	# se face un obiect int
		-- in p <- 1				# se uita ref la primul si se face alt obiect  
		let p : Int <- 1
		in {
			while (0 < n) loop {
				p <- p * n; 
				n <- n - 1;
			} pool;
			p;
		}
	};
};

(*
	About COOL Language:
		* expression language
		* Object, String, Int, Bool, IO
		* class atributes are private
		* methods are public
		* you cannot change acces
		* simple inheritance
		* atribuire este "->"
		* comparare este "="
		* argumentele sunt trimise in functii prin referinta
		* int-urile sunt immutable, daca facem n + 1, se va face o copie pentru n + 1
		* while-ul intoarce void
		* se initializeaza obiecte
			* Int, Bool, String = 0, False, sirul vid '\0'
			* restul, care nu sunt astea --> se initializeaza cu VOID


	let ... in {...} --> {instructions separeted by ;}
	
	daca vrei mai multe let-uri --> faci wrap intr-un {}

	Object:
		abort() 	--> cand vrei sa inchizi programul
		type_name() --> tipul dinamic
		copy() 		--> copie la obiect

	IO: mosteneste Object
		out_string
		in_string
		...

*)