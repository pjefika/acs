package util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {

	public static void addInfoMessage(String msg) {		

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", msg);

		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	public static void addErrorMessage(String msg) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", msg);

		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	public static void addWarnMessage(String msg) {

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Informa��o", msg);

		FacesContext.getCurrentInstance().addMessage(null, message);

	}

	public static String gerarStringAleatoria(Integer nrStrings){


		java.util.Random r = new java.util.Random();

		char[] goodChar = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
				'h','i', 'j', 'k','l', 'm', 'n','o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'x','w',
				'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I','J', 'K','L',
				'M', 'N','O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z','1',
				'2', '3', '4', '5', '6', '7', '8', '9'};
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < nrStrings; i++) {
			sb.append(goodChar[r.nextInt(goodChar.length)]);
		}

		return sb.toString(); 
	}

	public static String formatarData(Date date) {

		String dataFormatada = null;

		if (date != null) {

			SimpleDateFormat formmater = new SimpleDateFormat("dd/MM/yyyy");

			dataFormatada = formmater.format(date);

		}	

		return dataFormatada;

	}

	public static String formatarDataHra(Date date) {

		String dataFormatada = null;

		if (date != null) {

			SimpleDateFormat formmater = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

			dataFormatada = formmater.format(date);

		}	

		return dataFormatada;


	}
	
	public static String formatarTimeStampHra(Long date) {
		
		Date dateStamp = new Date(date);
		
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		
		String dataFormatada = formatter.format(dateStamp);
		
		return dataFormatada;
		
	}
	
	public static String autenticacao() {
		
		/*String username = "efika_system";
		String password = "Efika@v1v0Gvt";*/
		
		String username = "co_g0047217";
		String password = "chk1234";
		
		String userPassword = username + ":" + password;
		String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());
		String concat = "Basic " + encoding;
		
		return concat;	
		
	}
	
	public static String acs() {
		
		//Produ��o
		//String link = "http://10.200.6.150/nbbs/api/";
		
		//Teste
		String link = "http://10.200.38.235/nbbs/api/";		
		
		return link;
		
	}
	
}