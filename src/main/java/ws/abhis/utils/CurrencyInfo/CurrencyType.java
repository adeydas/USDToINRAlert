package ws.abhis.utils.CurrencyInfo;

import java.io.Serializable;
import java.util.Map;

@SuppressWarnings("serial")
public class CurrencyType implements Serializable {
	private String disclaimer;
	private long timestamp;
	private String license;
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	private String base;
	private Map<String, Double> rates;
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public Map<String, Double> getRates() {
		return rates;
	}
	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}
}
