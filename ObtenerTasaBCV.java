//nota Agreagar la ruta donde se ejecuta la clase.

import java.io.IOException;


import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;






public class test {
	private final static Logger log = Logger.getLogger(ObtenerTasaBCV.class);
	private static final String URLHtmlTASABCV = "https://www.bcv.org.ve/"; //url de sitio a consulta tasa bcv
	private static final String IDCONSULTAHTML = "dolar"; //id del div segun moneda (dolar=USD, euro = EUR, yuan=CNY, lira=TRY, rublo=RUB).


public static void main(String[] args) throws Exception {
	leerHtml(); 

	} 
	
	private static void leerHtml() throws Exception {
		
		
		Element  selectTasaBcv; // variable para obtener valor de tasa bcv
		Element monedaCambio; // variable para obtener tipo de moneda
		Double valorTasaBcv;
		Document doc;
		try {
			
			doc = disable_SSL.getConnection(URLHtmlTASABCV).get(); // permite colocar en disable el SSL
			selectTasaBcv = doc.select("div#"+IDCONSULTAHTML+" > div > div >div >strong").first();// Trae el valor de la moneda. 
			monedaCambio = doc.select("div#"+IDCONSULTAHTML+" > div > div >div> span").first(); // traer la moneda ej; USD, EUR, etc
			String extraerTasaBcv= selectTasaBcv.text().replace(",","."); // Cambia de coma a punto
			String valormonedaCambio= monedaCambio.text();
			valorTasaBcv = formatearDecimales(Double.valueOf(extraerTasaBcv),2); // para obtener dos decimales 
		
			if(valorTasaBcv!=0 && valorTasaBcv !=null) {
				log.info(valormonedaCambio+" "+extraerTasaBcv);
				log.info(valorTasaBcv +" "+ ((Object)valorTasaBcv).getClass().getSimpleName());
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			doc = null;
		}
		
		
		
		
	}
	public static Double formatearDecimales(Double numero, Integer numeroDecimales) {
		return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
		}
	}
