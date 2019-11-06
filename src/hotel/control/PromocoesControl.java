package hotel.control;

import hotel.entidades.Promocoes;

import java.util.Base64;
import java.util.Random;

public class PromocoesControl {
	private String cupom;
	
	public String generateToken(int valor){
		Promocoes p = new Promocoes();
		Random random = new Random();
		cupom = Integer.toString(random.nextInt(valor));
		cupom = Base64.getEncoder().encodeToString(cupom.getBytes());
		return cupom;	
	}
	
	
}
