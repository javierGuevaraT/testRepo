package com.unosquare.config;

public final class Configuration {

	private String mainUrl;
	private String browser;
	private String driversLocation;
	private String driverName;
	
	public String getMainUrl() {
		return this.mainUrl;
	}
	
	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}
	
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	
	public Browsers getBrowser() {
		switch(this.browser) {
		    case "chrome":
		    case "CHROME": return Browsers.CHROME;
		    
		    case "firefox":
		    case "FIREFOX": return Browsers.FIREFOX;
		    
		    default: return null;
		}
	}
	
	public void setDriversLocation(String driversLocation) {
		this.driversLocation = driversLocation;
	}
	
	public String getDriversLocation() {
		return this.driversLocation;
	}
	
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	
	public String getDriverName() {
		return this.driverName;
	}
}
