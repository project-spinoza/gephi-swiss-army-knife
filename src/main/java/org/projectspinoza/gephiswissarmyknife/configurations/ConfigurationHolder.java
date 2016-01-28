package org.projectspinoza.gephiswissarmyknife.configurations;

import com.google.inject.Singleton;

@Singleton
public class ConfigurationHolder {
	
	private String host = "0.0.0.0";
	private int port = 9090;
	private String webroot = "/public";
	
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getWebroot() {
		return webroot;
	}
	public void setWebroot(String webroot) {
		this.webroot = webroot;
	}
}
