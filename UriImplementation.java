package uri.implementation;

import uri.Host;
import uri.Uri;

// TODO implement this class or another implementation of Uri
public class UriImplementation implements Uri {
	private String uri;

	public UriImplementation(String uri) {
		this.uri = uri;
	}
	@Override
	public String getScheme() {
		// TODO implement this
		String scheme_output = new String();
			String scheme = uri.split("://")[0];
				char first_character = scheme.charAt(0);
				boolean scheme_Is_Valid = (first_character >= 'A' && first_character <= 'Z') || (first_character >= 'a' && first_character <= 'z');
				if (scheme_Is_Valid) {
					if (scheme.length() > 1) {
						if (scheme.matches("^[a-zA-Z\\d]*$")) {
							scheme_output = scheme;
						} else {
							throw new NullPointerException("the scheme must beginn with an alphbet a-z A-Z");
						}
					} else {
						scheme_output = scheme;
					}
				} else {
					throw new NullPointerException("the scheme must beginn with an alphbet a-z A-Z");
				}

		return scheme_output;
	}

	@Override
	public String getQuery() {
		// TODO implement this
		String query = null;
		if (uri.contains("?")) {
				String[] split = uri.split("\\?");
				if (split.length == 2) {
					String query_To_Check = split[1];
					if (!(query_To_Check.isEmpty()) && !(query_To_Check == null)) {
						if (query_To_Check.matches("((([a-zA-Z\\d.])|(\\%[\\dA-Fa-f][\\dA-Fa-f]))|&|=)*")) {
							query = query_To_Check;
						} else {
							throw new NullPointerException("Query is invalid");
						}
					}
				} else {
					query = new String();
				}
			}

		return query;
	}

	@Override
	public String getUserInfo() {
		// TODO implement this
		String user_info = null;
		String uri_splited = uri.split("://")[1];
		if (uri_splited.contains("@")) {
			String user_To_Check = uri_splited.split("@")[0];
			if (!(user_To_Check.isEmpty()) && !(user_To_Check == null)) {
				if (user_To_Check.matches("((([a-zA-Z\\d.])|(\\%[\\dA-Fa-f][\\dA-Fa-f]))|:)*")) {
					//^[0-9A-Fa-f]+$
					user_info = user_To_Check;
				} else {
					throw new NullPointerException("the user info is not valid");
				}

			} else {
				user_info = new String();
			}

		}

		return user_info;
	}

	@Override
	public Host getHost() {
		// TODO implement this
		Host host = new HostImplementation("");
		String host_str = new String();
		String host_To_Check = new String();
		String first_split = new String();
		if (uri.contains("@")) {
			first_split = uri.split("@")[1];
		} else {
			first_split = uri.split("://")[1];
		}
			if (first_split.contains("/")){
				host_To_Check = first_split.split("/")[0];

			} else {
				if (first_split.contains("?")) {
					host_To_Check = first_split.split("\\?")[0];
				} else {
					host_To_Check = first_split;
				}
			}
			if (!(host_To_Check.isEmpty()) && !(host_To_Check == null)) {
				if (host_To_Check.matches("(\\d(\\d\\d)?\\.\\d(\\d\\d)?\\.\\d(\\d\\d)?\\.\\d(\\d\\d)?)")) {
					String[] host_IPv4 = host_To_Check.split("(\\.)");
					boolean IPv4_Is_Valid = true;
					for(int i = 0; i < host_IPv4.length; i++) {
						if(Integer.parseInt(host_IPv4[i]) > 255 || Integer.parseInt(host_IPv4[i]) < 0) {
							IPv4_Is_Valid = false;
						}
					}
					if(IPv4_Is_Valid) {
						host = new IPv4AddressImplementation(host_To_Check);
					}
					//"((00[\\d])|(0[1-9]\\d)|(1\\d\\d)|(2[0-4]\\d)|(25[0-5]))\\.((00[\\d])|(0[1-9]\\d)|(1\\d\\d)|(2[0-4]\\d)|(25[0-5]))\\.((00[\\d])|(0[1-9]\\d)|(1\\d\\d)|(2[0-4]\\d)|(25[0-5]))\\.((00[\\d])|(0[1-9]\\d)|(1\\d\\d)|(2[0-4]\\d)|(25[0-5]))"
					//((00[\d])|(0[1-9]\d)|(1\d\d)|(2[0-4]\d)|(25[0-5]))
				} else {
					if (host_To_Check.matches("(([a-zA-Z\\d.])|(\\%[\\dA-Fa-f][\\dA-Fa-f]))*")) {
						host = new HostImplementation(host_To_Check);
					} else {
						throw new NullPointerException("the host is not valid");
					}
				}
			}

		return host;
	}

	@Override
	public String getPath() {
		// TODO implement this
		String path = new String();
		String path_To_Check = new String();
		String first_split = uri.split("://")[1];
		first_split = first_split.substring(1);
		if (first_split.contains("/")) {
			String second_split = first_split.substring(first_split.indexOf("/", 1));
			if (second_split.contains("?")) {
				path_To_Check = second_split.split("\\?")[0];
				//path_To_Check = path_To_Check.substring(0, path_To_Check.length()-1);
			} else {
				path_To_Check = second_split;
			}

			if (!(path_To_Check.isEmpty()) && !(path_To_Check == null)) {
				if (path_To_Check.matches("(/(([a-zA-Z\\d.])|(\\%[\\dA-Fa-f][\\dA-Fa-f]))*)*") ){
					path = path_To_Check;
				} else {
					throw new NullPointerException("the path is not valid");
				}
			}


       // / could be a path
		}

		return path;
	}

}
