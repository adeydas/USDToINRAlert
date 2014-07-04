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

	private static final String currencyDataFilename = "savedData.dat";
	private static final Logger logger = LogManager
			.getLogger(SerializeCurrencyData.class);

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
			fos = new FileOutputStream(currencyDataFilename);
			out = new ObjectOutputStream(fos);
			out.writeObject(dataTillNow);

			out.close();
		} catch (Exception ex) {
			logger.error(ex);
		}
	}

	public AllCurrencyData retrieveCurrencyData() {

		File f = new File(currencyDataFilename);
		if (!f.exists()) {
			return null;
		}

		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(currencyDataFilename);
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
