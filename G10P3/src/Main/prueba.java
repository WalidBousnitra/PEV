package Main;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class prueba {

	public static void main(String[] args) {
		
		DecimalFormat f = new DecimalFormat("0.0000");
		f.setRoundingMode(RoundingMode.DOWN);
		String sol = "{";
		double x = -1.00;
		
		for(int i =0;i<=100;i++) {
			double dato =Math.pow(x,4) + Math.pow(x,3) + Math.pow(x,2) + x + 1;
			sol+= String.valueOf(f.format(dato))+"# ";
			if((i+1)%10==0) {
				sol+="\n";
			}
			x+=0.02;
		}
		sol+="};";
		
		if( sol.indexOf(",") != -1 )
		     sol = sol.replace(',','.');
		
		if( sol.indexOf("#") != -1 )
		     sol = sol.replace('#',',');
		
		System.out.print(sol);
	}

}
