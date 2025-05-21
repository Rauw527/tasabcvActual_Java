
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class disable_SSL {
	 static public Connection getConnection(String url){
	        return Jsoup.connect(url).sslSocketFactory(disable_SSL.socketFactory());
	    }

	    static private SSLSocketFactory socketFactory() {
	        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
	            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
	                return null;
	            }


				@Override
				public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
						throws CertificateException {
					// TODO Auto-generated method stub
					
				}
	        }};

	        try {
	            SSLContext sslContext = SSLContext.getInstance("SSL");
	            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
	            SSLSocketFactory result = sslContext.getSocketFactory();

	            return result;
	        } catch (NoSuchAlgorithmException | KeyManagementException e) {
	            throw new RuntimeException("Error al crear un sockets SSL", e);
	        }
	    }
	}