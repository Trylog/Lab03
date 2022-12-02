package Sklep;
import Data.DataK;
import Producent.ProducentBack;

import java.io.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class SklepBack {
	private final int liczbaProducentow=3;
	int ostatniaWczytanaReklamacja=0;
	int[] ostatniaWczytanaReklamacjaProducenta;

	public class Dane{
		public int idReklamacj=0, cel=0, idKlienta=0, licznikReklamacji=0;
		boolean akceptacja=false;
		public String nazwa="", opis="", odpowiedz="";
		public DataK dataWyslaniaPrzezSklep, dataZwrotuPrzewidywana, dataZwrotuFaktyczna;
		Dane(int idReklamacji, String nazwa, String opis, int cel, DataK dataWyslaniaPrzezSklep, int idKlienta, int licznikReklamacji) {
			this.idReklamacj = idReklamacji;
			dataZwrotuPrzewidywana =new DataK();
			this.nazwa = nazwa;
			this.opis = opis;
			this.cel = cel;
			this.dataWyslaniaPrzezSklep = dataWyslaniaPrzezSklep;
			this.idKlienta=idKlienta;
			this.licznikReklamacji=licznikReklamacji;
		}
	}
	public ArrayList<SklepBack.Dane> dane;

	SklepBack() {

		ostatniaWczytanaReklamacjaProducenta=new int[liczbaProducentow];
		for (int i =0; i<liczbaProducentow;i++){
			ostatniaWczytanaReklamacjaProducenta[i]=0;
		}
		dane=new ArrayList<>();
		File info = new File("src/Pliki/Klient/info.txt");
		Scanner infoScanner = null;
		int liczbaReklamacji = 0;
		try {
			infoScanner = new Scanner(info);
			if (info.isFile()) {
				liczbaReklamacji = infoScanner.nextInt();
				infoScanner.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 1; i < (liczbaReklamacji + 1); i++) {
			File reklamacja = new File("src/Pliki/Klient/reklamacja_" + i + ".txt");
			BufferedReader reklamacjaReader = null;
			String towar = "", opis = "", producent = "";
			int cel = 0, idKlienta=0, licznikReklamacji=0;
			try {
				reklamacjaReader = new BufferedReader(new FileReader(reklamacja));
				if (info.isFile()) {
					String temp = "";

					idKlienta = Integer.parseInt(reklamacjaReader.readLine());
					//reklamacjaReader.println(idKlienta);
					licznikReklamacji = Integer.parseInt(reklamacjaReader.readLine());
					//reklamacjaReader.println(licznikReklamacji);
					//temp = reklamacjaReader.readLine();
					//reklamacjaReader.println(getZexgar());
					towar = reklamacjaReader.readLine();
					towar = towar.strip();
					//reklamacjaReader.println(towar);
					opis = reklamacjaReader.readLine();
					opis = opis.strip();
					//reklamacjaReader.println(opis);
					cel = Integer.parseInt(reklamacjaReader.readLine());
					//reklamacjaReader.println(cel);
					reklamacjaReader.close();
					dane.add(new Dane(i,towar, opis, cel, null,idKlienta, licznikReklamacji));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			//tworzymy plik statusu
			File odpowiedzKa = new File("src/Pliki/Klient/odpowiedz_"+dane.get(i-1).idKlienta+"_"+dane.get(i-1).licznikReklamacji+".txt");
			PrintWriter statusWriter =null;
			try {
				statusWriter = new PrintWriter(odpowiedzKa);
				if (odpowiedzKa.isFile()) {
					//reklamacjaWriter.println("Hello World");
					statusWriter.println(i);//id reklamacji
					statusWriter.println("0");
				}
			}catch (FileNotFoundException e){
				e.printStackTrace();
			}
			try {
				BufferedReader producenci = new BufferedReader(new FileReader("src/Pliki/Sklep/producenci.txt"));
				String temp;
				while ((temp = producenci.readLine().strip()) != null && !temp.equals(towar)) ;
				//System.out.println(towar);
				//System.out.println(temp);
				producent = producenci.readLine();
				producenci.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int idPlikuP = 0;
			File infoP = new File("src/Pliki/Producenci/" + producent + "/info.txt");
			Scanner infoPScanner = null;
			PrintWriter infoPWriter = null;
			try {
				infoPScanner = new Scanner(infoP);
				if (info.isFile()) {
					idPlikuP = infoPScanner.nextInt() + 1;
				}
				infoPScanner.close();
				try {
					infoPWriter = new PrintWriter(infoP);
					if (info.isFile()) {
						infoPWriter.write("");
						infoPWriter.print(idPlikuP);
						infoPWriter.close();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			File reklamacjaP = new File("src/Pliki/Producenci/" + producent + "/reklamacja_" + idPlikuP + ".txt");
			PrintWriter reklamacjaWriter = null;
			try {
				reklamacjaWriter = new PrintWriter(reklamacjaP);
				if (info.isFile()) {
					//reklamacjaWriter.println("Hello World");
					reklamacjaWriter.println(i);//id reklamacji
					reklamacjaWriter.println(towar);
					reklamacjaWriter.println(opis);
					reklamacjaWriter.println(cel);
					//reklamacjaWriter.println(getZegar());
					reklamacjaWriter.close();
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		ostatniaWczytanaReklamacja = liczbaReklamacji;
		//System.out.println(dane);
	}
	void odswiez(){
		File info = new File("src/Pliki/Klient/info.txt");
		Scanner infoScanner=null;
		int liczbaReklamacji=0;
		try{
			infoScanner=new Scanner(info);
			if (info.isFile()){
				liczbaReklamacji = infoScanner.nextInt();
				infoScanner.close();
			}

		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
		for (int i=1+ostatniaWczytanaReklamacja; i<liczbaReklamacji+1;i++){
			File reklamacja = new File("src/Pliki/Klient/reklamacja_"+i+".txt");
			BufferedReader reklamacjaReader =null;
			String towar="", opis="", producent="";
			int cel=0;
			try {
				reklamacjaReader= new BufferedReader(new FileReader(reklamacja));
				if (info.isFile()){
					String temp="";
					temp = reklamacjaReader.readLine();
					//reklamacjaReader.println(idKlienta);
					temp = reklamacjaReader.readLine();
					//reklamacjaReader.println(licznikReklamacji);
					temp = reklamacjaReader.readLine();
					//reklamacjaReader.println(getZexgar());
					towar=reklamacjaReader.readLine();
					towar=towar.strip();
					//reklamacjaReader.println(towar);
					opis=reklamacjaReader.readLine();
					opis=opis.strip();
					//reklamacjaReader.println(opis);
					cel=Integer.parseInt(reklamacjaReader.readLine());
					//reklamacjaReader.println(cel);
					reklamacjaReader.close();
				}

			}catch (Exception e){
				e.printStackTrace();
			}
			try {
				BufferedReader producenci = new BufferedReader(new FileReader("src/Pliki/Sklep/producenci.txt"));
				String temp;
				while ((temp=producenci.readLine().strip())!=null&&temp.equals(towar));
				producent=temp;
				producenci.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			int idPlikuP=0;
			File infoP = new File("src/Pliki/Producenci/"+producent+"/info.txt");
			Scanner infoPScanner=null;
			PrintWriter infoPWriter=null;
			try{
				infoPScanner=new Scanner(infoP);
				if (infoP.isFile()){
					idPlikuP = infoPScanner.nextInt()+1;
				}
				infoPScanner.close();
				try {
					infoPWriter=new PrintWriter(infoP);
					if (infoP.isFile()){
						infoPWriter.write("");
						infoPWriter.print(idPlikuP);
						infoPWriter.close();
					}
				}catch (FileNotFoundException e){
					e.printStackTrace();
				}
			}catch (FileNotFoundException e){
				e.printStackTrace();
			}
			File reklamacjaP = new File("src/Pliki/Producenci/"+producent+"/reklamacja_"+idPlikuP+".txt");
			PrintWriter reklamacjaWriter =null;
			try {
				reklamacjaWriter=new PrintWriter(reklamacjaP);
				if (reklamacjaP.isFile()){
					//reklamacjaWriter.println("Hello World");
					reklamacjaWriter.println(i);//id reklamacji
					reklamacjaWriter.println(towar);
					reklamacjaWriter.println(opis);
					reklamacjaWriter.println(cel);
					//reklamacjaWriter.println(getZegar());
					reklamacjaWriter.close();
				}

			}catch (FileNotFoundException e){
				e.printStackTrace();
			}
		}
		ostatniaWczytanaReklamacja =liczbaReklamacji;
	}
	void odswiezOdpowiedziProdu(){
		for (int j =0;j<liczbaProducentow;j++){

			File info = new File("src/Pliki/Producenci/"+j+"/infoP.txt");
			Scanner infoScanner=null;
			int liczbaReklamacji=0;
			try{
				infoScanner=new Scanner(info);
				if (info.isFile()){
					liczbaReklamacji = infoScanner.nextInt();
					infoScanner.close();
				}

			}catch (FileNotFoundException e){
				e.printStackTrace();
			}
			for (int i=1+ostatniaWczytanaReklamacjaProducenta[j]; i<liczbaReklamacji+1;i++) {
				File reklamacja = new File("src/Pliki/Producenci/" + j + "/odpowiedz_" + i + ".txt");
				BufferedReader reklamacjaReader = null;
				int cel = 0;
				try {
					reklamacjaReader = new BufferedReader(new FileReader(reklamacja));
					if (info.isFile()) {
						int id = 0;
						id = Integer.parseInt(reklamacjaReader.readLine())-1;
						dane.get(id).akceptacja = Boolean.parseBoolean(reklamacjaReader.readLine());
						dane.get(id).odpowiedz = reklamacjaReader.readLine();
						dane.get(id).dataZwrotuPrzewidywana.wczytaj(reklamacjaReader.readLine());

						reklamacjaReader.close();
						ostatniaWczytanaReklamacjaProducenta[j]=i;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	void odpowiedz(int idReklamacji, String odpowiedz, DataK przewidywanaDataOdbioru){

		File odpowiedzK = new File("src/Pliki/Klient/odpowiedz_"+dane.get(idReklamacji).idKlienta+"_"+dane.get(idReklamacji).licznikReklamacji+".txt");
		PrintWriter reklamacjaWriter =null;
		try {
			reklamacjaWriter=new PrintWriter(odpowiedzK);
			if (odpowiedzK.isFile()){

				//reklamacjaWriter.println("Hello World");
				//reklamacjaWriter.println(idReklamacji);//id reklamacji
				reklamacjaWriter.println("1");
				reklamacjaWriter.println(odpowiedz);
				reklamacjaWriter.println(przewidywanaDataOdbioru.drukuj());
				//reklamacjaWriter.println(getZegar());
				reklamacjaWriter.close();
			}

		}catch (FileNotFoundException e){
			e.printStackTrace();
		}

	}
}
