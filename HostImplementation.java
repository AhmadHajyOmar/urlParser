package uri.implementation;

import uri.Host;

public class HostImplementation implements Host {
	String host;
	public HostImplementation(String host) {
		this.host = host;
	}

	@Override
	public String toString() {
		return host;
	}

}
