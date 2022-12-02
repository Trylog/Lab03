package Producent;

import Data.DataK;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ProducentBack {

	public class Dane{
		public int idReklamacj=0, cel=0;
		boolean akceptacja=false;
		public String nazwa="", opis="", odpowiedz="";
		public DataK dataWyslaniaPrzezSklep =null, dataZwrotuPrzewidywana=null, dataZwrotuFaktyczna=null;
		Dane(int idReklamacji, String nazwa, String opis, int cel, DataK dataWyslaniaPrzezSklep) {
			this.idReklamacj = idReklamacji;
			this.nazwa = nazwa;
			this.opis = opis;
			this.cel = cel;
			this.dataWyslaniaPrzezSklep = dataWyslaniaPrzezSklep;
		}
	}

	String nazwaProducenta = "";
	public ArrayList<Dane> dane;
	ProducentBack(String producent){

		 dane=new ArrayList<>();

		this.nazwaProducenta=producent;
		File info = new File("src/Pliki/Producenci/"+nazwaProducenta+"/info.txt");
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
		for (int i=1;i<liczbaReklamacji+1;i++){

			File reklamacja = new File("src/Pliki/Producenci/"+nazwaProducenta+"/reklamacja_"+i+".txt");
			BufferedReader reklamacjaReader =null;
			String towar="", opis="";
			int cel=0, idReklamacji=0;
			DataK dataWyslaniaPrzezSklep=null;
			try {
				reklamacjaReader = new BufferedReader(new FileReader(reklamacja));
				if (info.isFile()) {
					idReklamacji = Integer.valueOf(reklamacjaReader.readLine());
					towar = reklamacjaReader.readLine().strip();
					opis = reklamacjaReader.readLine().strip();
					cel = Integer.valueOf(reklamacjaReader.readLine());
					//zegar
					reklamacjaReader.close();
				}
			}catch (Exception e){
			e.printStackTrace();
			}
			dane.add(new Dane(idReklamacji, towar, opis, cel, dataWyslaniaPrzezSklep));
			//System.out.println(towar);
		}

	}

	public void odswiez(){

	}
	public void odpowiedz(boolean akceptacja, int miejsceNaLiscie, String odpowiedz, DataK dataZwrotuPrzewidywana){
		dane.get(miejsceNaLiscie-1).dataZwrotuPrzewidywana=dataZwrotuPrzewidywana;
		dane.get(miejsceNaLiscie-1).odpowiedz=odpowiedz;
		dane.get(miejsceNaLiscie-1).akceptacja=akceptacja;

		int idPlikuP=0;
		File infoP = new File("src/Pliki/Producenci/"+nazwaProducenta+"/infoP.txt");
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
		File reklamacjaP = new File("src/Pliki/Producenci/"+nazwaProducenta+"/odpowiedz_"+idPlikuP+".txt");
		PrintWriter reklamacjaWriter =null;
		try {
			reklamacjaWriter=new PrintWriter(reklamacjaP);
			if (reklamacjaP.isFile()){
				//reklamacjaWriter.println("Hello World");
				reklamacjaWriter.println(dane.get(miejsceNaLiscie-1).idReklamacj);//id reklamacji
				reklamacjaWriter.println(dane.get(miejsceNaLiscie-1).akceptacja);
				reklamacjaWriter.println(dane.get(miejsceNaLiscie-1).odpowiedz);
				reklamacjaWriter.println(dane.get(miejsceNaLiscie-1).dataZwrotuPrzewidywana.drukuj());
				//reklamacjaWriter.println(getZegar());
				reklamacjaWriter.close();
			}

		}catch (FileNotFoundException e){
			e.printStackTrace();
		}

	}
	public void zwrotTowaru(Date dataZwrotuFaktyczna){

	}

}
