package Klient;

import java.util.Scanner;

public class KlientFront {

	public KlientFront(){
		String temp, produkt="", opis="";
		int cel, numerReklamacji=1;
		System.out.println("Podaj swój numer klienta (jeśli jesteś nowy wpisz -1)");
		Scanner scan =new Scanner(System.in);
		KlientBack nowy = new KlientBack(scan.nextInt());
		System.out.println("***   Interfejs Klienta   ***");
		while (true)
		{
			System.out.println("Co chcesz zrobić?");
			scan.nextLine();
			temp=scan.nextLine();
			if(temp.equals("reklamacja")){
				System.out.println("Reklamacja nr: "+numerReklamacji);
				System.out.println("Nazwa Produktu:");
				produkt=scan.nextLine();
				System.out.println("Opis:");
				opis=scan.nextLine();
				System.out.println("0 - naprawa; 1 - zwrot pieniędzy:");
				cel=scan.nextInt();
				nowy.reklamuj(produkt, opis, cel);
				numerReklamacji++;
			}else if(temp.equals("koniec")){
				break;
			}else if (temp.equals("status")){
				System.out.println("Podaj numer porządkowy reklamacji");
				int p=scan.nextInt();
				if (nowy.status(p)){
					System.out.println(nowy.odpowiedz);
				}else {
					System.out.println("Brak odpowiedzi");
				}
			}

		}


	}
}
