package uri.implementation;

import uri.Uri;
import uri.UriParser;

public class UriParserImplementation implements UriParser {

	private String uri;

	public UriParserImplementation(String uri) {
		this.uri = uri;
	}

	@Override
	public Uri parse() {
		boolean ui_Uri = false;
		boolean host_Uri = false;
		boolean path_Uri = false;
		boolean query = false;
		boolean uri_Is_Valid = true;
		int pct_Encode = 0;

		Uri uri_parsed = null;
		if (uri == null) {
			return null;
		}
		if (!this.uri.isEmpty()) {
			if (uri.contains("://")) {
				if (uri.length() > 3) {
					String first_charracter = String.valueOf(uri.split("://")[0].charAt(0));
					if (first_charracter.matches("([a-zA-Z]*)")) {
						String check = uri.split("://")[0];
						if (!(check.isEmpty()) && !(check == null)) {
							if (check.matches("([a-zA-Z\\d])*")) {
								if (uri.split("://").length > 1) {
									String check_2 = uri.split("://")[1];
									char[] all_Optional_Characters = check_2.toCharArray();
									int number_Of_Valid_Separation_Marks = 0;

									for (int i = 0; i < all_Optional_Characters.length; i++) {
										if(all_Optional_Characters[i] == ' '){
											return null;
										}
										if (number_Of_Valid_Separation_Marks > 2) {
											return null;
										}
										if (all_Optional_Characters[i] == '@' || all_Optional_Characters[i] == '?') {
											number_Of_Valid_Separation_Marks++;
										}
									}

									if (uri.split("://")[1].charAt(0) != '@') {
										if(uri.split("://")[1].contains("@")) {
											ui_Uri = true;
										}
									}

									for (int i = 0; i < all_Optional_Characters.length; i++) {
										if(!ui_Uri && !host_Uri && !path_Uri && !query) {
											if(uri.split("://")[1].charAt(0) != '/' || uri.split("://")[1].charAt(0) != '?') {
												host_Uri = true;
												path_Uri = false;
												host_Uri = false;
												ui_Uri = false;
											}
										}

										if(all_Optional_Characters[i] == '/' && !path_Uri && !ui_Uri && !query) {
											path_Uri = true;
											host_Uri = false;
											ui_Uri = false;
											query = false;
										}

										if (all_Optional_Characters[i] == '@' && i != all_Optional_Characters.length - 1) {
											if(all_Optional_Characters[i+1] != '/' || all_Optional_Characters[i+1] != '?') {
												host_Uri = true;
												ui_Uri = false;
											}
										}

										if (all_Optional_Characters[i] == '?') {
											query = true;
											path_Uri =false;
											host_Uri =false;
											ui_Uri = false;
										}

										boolean term = !Character.toString(all_Optional_Characters[i]).matches("([a-fA-F\\d])*");
										if (ui_Uri) {
											if(pct_Encode > 0) {
												pct_Encode++;
												if (term) {
													uri_Is_Valid =false;
												}
												if (pct_Encode == 3) {
													pct_Encode = 0;
												}
											}else {
												if(all_Optional_Characters[i] == '%') {
													pct_Encode = 1;
												} else {
													if (!Character.toString(all_Optional_Characters[i]).matches("(([a-zA-Z\\d.])|:)*") && all_Optional_Characters[i] != '@') {
														uri_Is_Valid =false;
													}
												}
											}
										}

										if (host_Uri && all_Optional_Characters[i] != '@') {
											if(pct_Encode > 0) {
												pct_Encode++;
												if (term) {
													uri_Is_Valid =false;
												}
												if (pct_Encode == 3) {
													pct_Encode = 0;
												}
											}else {
												if(all_Optional_Characters[i] == '%') {
													pct_Encode = 1;
												} else {
													if (!Character.toString(all_Optional_Characters[i]).matches("(\\d((\\d)?\\d)?\\.\\d((\\d)?\\d)?\\.\\d((\\d)?\\d)?\\.\\d((\\d)?\\d)?)") &&
															!Character.toString(all_Optional_Characters[i]).matches("([a-zA-Z\\d.])*")) {
														uri_Is_Valid =false;
													}
												}
											}
										}

										if(path_Uri && all_Optional_Characters[i] != '/') {
											if(pct_Encode > 0) {
												pct_Encode++;
												if (term) {
													uri_Is_Valid =false;
												}
												if (pct_Encode == 3) {
													pct_Encode = 0;
												}
											}else {
												if(all_Optional_Characters[i] == '%') {
													pct_Encode = 1;
												} else {
													if (!Character.toString(all_Optional_Characters[i]).matches("([a-zA-Z\\d.])*")) {
														uri_Is_Valid =false;
													}
												}
											}
										}

										if(query && all_Optional_Characters[i] != '?') {
											if(pct_Encode > 0) {
												pct_Encode++;
												if (term) {
													uri_Is_Valid =false;
												}
												if (pct_Encode == 3) {
													pct_Encode = 0;
												}
											}else {
												if(all_Optional_Characters[i] == '%') {
													pct_Encode = 1;
												} else {
													if (!Character.toString(all_Optional_Characters[i]).matches("(([a-zA-Z\\d.])|&|=)*")) {
														uri_Is_Valid =false;
													}
												}
											}
										}
									}

									if (uri_Is_Valid && pct_Encode == 0) {
										uri_parsed = new UriImplementation(uri);
									}
								} else {
									uri_parsed = new UriImplementation(uri);
								}
							}
						}
					}
				}
			}
		}
		return uri_parsed;
	}

}
