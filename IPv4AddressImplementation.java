package uri.implementation;

import uri.IPv4Address;

import java.util.Arrays;

public class IPv4AddressImplementation extends HostImplementation implements IPv4Address {
	private String host_IP;
	private String[] host_arr;
	private String host_without_leading_zeros;
	private boolean leading_zeros;
	public IPv4AddressImplementation(String host) {
		super(host);
		host_arr = super.host.split("\\.");
		host_without_leading_zeros = new String();
		leading_zeros = false;
		String host_without_leading_zeros = new String();
		int index = 0;
		for(int j = 0; j < host_arr.length; j++) {
			String octa = host_arr[j];
			for(int n = 0; n < octa.length(); n++) {

				if (leading_zeros == false) {
					if(octa.charAt(n) != '0') {
						leading_zeros = true;
					}
				}

				if(leading_zeros) {
					host_without_leading_zeros += octa.charAt(n);
				}

				if (n == octa.length() -1 ) {
					if (host_without_leading_zeros.isEmpty()) {
						host_arr[index] = "0";
					}else {
						host_arr[index] = host_without_leading_zeros;
					}
					index++;
					host_without_leading_zeros = new String();
					leading_zeros = false;
				}
			}
		}

		for (int i = 0; i < host_arr.length; i++) {
			if (i == 3) {
				host_without_leading_zeros += host_arr[i];
			} else {
				host_without_leading_zeros += host_arr[i]+".";
			}
		}

		this.host_IP = host_without_leading_zeros;
	}

	@Override
	public byte[] getOctets() {
		byte[] octets = new byte[4];
		for (int i = 0; i < host_arr.length; i++) {
			octets[i] = (byte) Integer.parseInt(host_arr[i]);
		}
		return octets;
	}

	@Override
	public String toString() {
		return host_IP;
	}

}
