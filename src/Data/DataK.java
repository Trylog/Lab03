package Data;

public class DataK {
	public int rok;
	public int miesiac;
	public int dzien;

	public void setMiesiac(int miesiac)throws DataException {
		if(miesiac<1||miesiac>12){
			throw new DataException("Nie ma takiego miesiąca");
		}else{
			this.miesiac = miesiac;
		}
	}
	public void setDzien(int dzien)throws DataException {
		if(dzien<1||dzien>31){
			throw new DataException("Nie ma takiego dnia");
		}else{
			this.dzien = dzien;
		}
	}

	public DataK(){
		rok=-1;
		miesiac=-1;
		dzien=-1;
	}
	public String drukuj(){

		String m, d;
		if(miesiac<10){
			m="0"+miesiac;
		}else m=Integer.toString(miesiac);
		if(dzien<10){
			d="0"+dzien;
		}else d=Integer.toString(dzien);
		return rok+"-"+m+"-"+d;
	}
	public DataK wczytaj(String wejscie){
		System.out.println(wejscie);
		DataK wyjscie =new DataK();
		if (wejscie.length()==10){
			wyjscie.rok=Integer.parseInt(String.valueOf(wejscie.charAt(0)))*1000 + Integer.parseInt(String.valueOf(wejscie.charAt(1)))*100 + Integer.parseInt(String.valueOf(wejscie.charAt(2)))*10 + Integer.parseInt(String.valueOf(wejscie.charAt(3)));
			wyjscie.miesiac=Integer.parseInt(String.valueOf(wejscie.charAt(5)))*10 + Integer.parseInt(String.valueOf(wejscie.charAt(6)));
			wyjscie.dzien=Integer.parseInt(String.valueOf(wejscie.charAt(8)))*10 + Integer.parseInt(String.valueOf(wejscie.charAt(9)));
		}

		return wyjscie;
	}
}
