package Producent;

import Data.DataK;

import java.util.Date;
import java.util.Scanner;

public class ProducentFront {
	public ProducentFront(){
		System.out.println("Podaj identyfikator producenta");
		Scanner scan =new Scanner(System.in);
		int id=0;
		id=scan.nextInt();
		ProducentBack nowy=new ProducentBack(Integer.toString(id));
		System.out.println("***   Interfejs Producenta"+id+"   ***");
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
		while (true){
			System.out.println("Co chcesz zrobić?");
			scan.nextLine();
			temp=scan.nextLine();
			if (temp.equals("odpowiedz")){
				boolean status;
				int nr=0;
				String odpowiedz="";
				System.out.println("Na którą reklamację?");
				nr=scan.nextInt();
				scan.nextLine();
				System.out.println("Czy akceptacja?");
				status=scan.nextBoolean();
				scan.nextLine();
				System.out.println("Odpowiedź:");
				odpowiedz= scan.nextLine();
				System.out.println("Podaj rok odbioru");
				Data.DataK data=new DataK();
				data.rok=scan.nextInt();
				System.out.println("Podaj miesiąc odbioru");
				while (true){
					try {
						data.setMiesiac(scan.nextInt());
						break;
					}catch (Exception e){
						System.out.println(e + ", ponów próbę wejścia");
					}
				}
				System.out.println("Podaj dzień odbioru");
				while (true){
					try {
						data.setDzien(scan.nextInt());
						break;
					}catch (Exception e){
						System.out.println(e + ", ponów próbę wejścia");
					}
				}
				nowy.odpowiedz(status,nr ,odpowiedz, data );
			}
		}
	}
}
