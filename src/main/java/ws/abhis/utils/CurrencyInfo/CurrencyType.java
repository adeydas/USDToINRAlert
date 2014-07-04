package ws.abhis.utils.CurrencyInfo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CurrencyType implements Serializable {
	private String to;
	private double rate;
	private String from;
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
}
