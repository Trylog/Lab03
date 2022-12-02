package Sklep;

import Data.DataK;

import java.util.Date;
import java.util.Scanner;

public class SklepFront {
	public SklepFront(){
		SklepBack nowy=new SklepBack();
		System.out.println("***   Interfejs Sklepu   ***");
		for (int i =0;i<nowy.dane.size();i++){
			System.out.println("");
			System.out.println("Id Reklamacji:");
			System.out.println(nowy.dane.get(i).idReklamacj);
			System.out.println("Nazwa Produktu:");
			System.out.println(nowy.dane.get(i).nazwa);
			System.out.println("Cel reklamacji:");
			System.out.println(nowy.dane.get(i).cel);
			System.out.println("Opis reklamacji:");
			System.out.println(nowy.dane.get(i).opis);
		}
		String temp;
		Scanner scan =new Scanner(System.in);
		while (true) {
			System.out.println("Co chcesz zrobić?");
			//scan.nextLine();
			temp = scan.nextLine();
			if (temp.equals("refresh")) {
				nowy.odswiezOdpowiedziProdu();
				for (int i = 0; i < nowy.dane.size(); i++) {
					System.out.println("");
					System.out.println("Id Reklamacji:");
					System.out.println(nowy.dane.get(i).idReklamacj);
					System.out.println("Nazwa Produktu:");
					System.out.println(nowy.dane.get(i).nazwa);
					System.out.println("Cel reklamacji:");
					System.out.println(nowy.dane.get(i).cel);
					System.out.println("Opis reklamacj:");
					System.out.println(nowy.dane.get(i).opis);
					System.out.println("CZY ZAAKCEPTOWANE:");
					System.out.println(nowy.dane.get(i).akceptacja);
					System.out.println("ODPOWIEDZ PRODUCENTA:");
					System.out.println(nowy.dane.get(i).odpowiedz);
					System.out.println("Przewidywana data wysyłki producenta;");
					System.out.println(nowy.dane.get(i).dataZwrotuPrzewidywana.drukuj());

					System.out.println("");
					System.out.println("Odpowiedź do klienta:");

					temp = scan.nextLine();
					System.out.println("Podaj rok odbioru");
					DataK data = new DataK();
					data.rok = scan.nextInt();
					System.out.println("Podaj miesiąc odbioru");
					data.miesiac = scan.nextInt();
					System.out.println("Podaj dzień odbioru");
					data.dzien = scan.nextInt();
					nowy.odpowiedz(i, temp, data);
				}
			}
		}
	}


}
