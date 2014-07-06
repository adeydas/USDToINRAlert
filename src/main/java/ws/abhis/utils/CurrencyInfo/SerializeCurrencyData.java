package ws.abhis.utils.CurrencyInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SerializeCurrencyData {

	
	private static final Logger logger = LogManager
			.getLogger(SerializeCurrencyData.class);
	private Configuration config;
	
	public SerializeCurrencyData(Configuration config) {
		this.config = config;
	}

	public void saveCurrencyData(CurrencyType ct) {

		AllCurrencyData dataTillNow = retrieveCurrencyData();

		if (dataTillNow == null) {
			dataTillNow = new AllCurrencyData();
			dataTillNow.allData.add(ct);
		} else {
			List<CurrencyType> lct = dataTillNow.allData;
			lct.add(ct);
		}

		// save the object to file
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream(config.getCurrencyDataFilename());
			out = new ObjectOutputStream(fos);
			out.writeObject(dataTillNow);

			out.close();
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

	public AllCurrencyData retrieveCurrencyData() {

		File f = new File(config.getCurrencyDataFilename());
		if (!f.exists()) {
			return null;
		}

		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(config.getCurrencyDataFilename());
			in = new ObjectInputStream(fis);
			AllCurrencyData p = (AllCurrencyData) in.readObject();
			in.close();
			return p;
		} catch (Exception ex) {
			logger.error(ex);
			return null;
		}
	}
}
