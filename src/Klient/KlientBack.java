package Klient;

import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class KlientBack {
	int licznikReklamacji=0;
	int idKlienta;
	Date dataOdbioru=null;
	String odpowiedz="";
	KlientBack(int id){
		this.idKlienta=id;
	}
	/*Date getZegar(){

	}*/
	void reklamuj(String towar, String opis, int cel){

		int idPliku=0;
		File info = new File("src/Pliki/Klient/info.txt");
		Scanner infoScanner=null;
		PrintWriter infoWriter=null;
		try{
			infoScanner=new Scanner(info);
			if (info.isFile()){
				idPliku = infoScanner.nextInt()+1;
			}
			infoScanner.close();
			try {
				infoWriter=new PrintWriter(info);
				if (info.isFile()){
					infoWriter.write("");
					infoWriter.print(idPliku);
					infoWriter.close();
				}
			}catch (FileNotFoundException e){
				e.printStackTrace();
			}
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}
		File reklamacja = new File("src/Pliki/Klient/reklamacja_"+idPliku+".txt");
		PrintWriter reklamacjaWriter =null;
		try {
			reklamacjaWriter=new PrintWriter(reklamacja);
			if (info.isFile()){
				//reklamacjaWriter.println("Hello World");
				reklamacjaWriter.println(idKlienta);
				reklamacjaWriter.println(licznikReklamacji);
				//reklamacjaWriter.println(getZegar());
				reklamacjaWriter.println(towar);
				reklamacjaWriter.println(opis);
				reklamacjaWriter.println(cel);

				reklamacjaWriter.close();
			}

		}catch (FileNotFoundException e){
			e.printStackTrace();
		}

		licznikReklamacji++;
	}
	boolean status(int numerReklamacji){
		File stat = new File("src/Pliki/Klient/odpowiedz_"+idKlienta+"_"+numerReklamacji+".txt");
		BufferedReader statusReader = null;
		int status=0;
		try{
			statusReader=new BufferedReader(new FileReader(stat));
			status=Integer.parseInt(statusReader.readLine());
			if (status==1){
				odpowiedz=statusReader.readLine();
				//data odbioru
			}

		}catch (Exception e){
			System.out.println("Reklamacja jeszcze nie została przetworzona!");
			status=0;
		}

		if (status==1)return true;
		return false;
	}
	void odbior(int idReklamacji){

	}
}
