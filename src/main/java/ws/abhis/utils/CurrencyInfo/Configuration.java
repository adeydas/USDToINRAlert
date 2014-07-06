package ws.abhis.utils.CurrencyInfo;

public class Configuration {
	protected String twilioAccountSid;
	protected String twilioAuthToken;
	protected String toNumber;
	protected String fromNumber;
	protected String htmlPath;
	protected String webBasePath;
	protected String currencyDataFilename;
	public String getTwilioAccountSid() {
		return twilioAccountSid;
	}
	public void setTwilioAccountSid(String twilioAccountSid) {
		this.twilioAccountSid = twilioAccountSid;
	}
	public String getTwilioAuthToken() {
		return twilioAuthToken;
	}
	public void setTwilioAuthToken(String twilioAuthToken) {
		this.twilioAuthToken = twilioAuthToken;
	}
	public String getToNumber() {
		return toNumber;
	}
	public void setToNumber(String toNumber) {
		this.toNumber = toNumber;
	}
	public String getFromNumber() {
		return fromNumber;
	}
	public void setFromNumber(String fromNumber) {
		this.fromNumber = fromNumber;
	}
	public String getHtmlPath() {
		return htmlPath;
	}
	public void setHtmlPath(String htmlPath) {
		this.htmlPath = htmlPath;
	}
	public String getWebBasePath() {
		return webBasePath;
	}
	public void setWebBasePath(String webBasePath) {
		this.webBasePath = webBasePath;
	}
	public String getCurrencyDataFilename() {
		return currencyDataFilename;
	}
	public void setCurrencyDataFilename(String currencyDataFilename) {
		this.currencyDataFilename = currencyDataFilename;
	}
	
	
	
}
