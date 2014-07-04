package ws.abhis.utils.CurrencyInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class AllCurrencyData implements Serializable {
	public List<CurrencyType> allData = new ArrayList<CurrencyType>();
	
	public void setCurrency(CurrencyType c) {
		allData.add(c);
	}
}
