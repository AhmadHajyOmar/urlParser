package uri.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uri.Host;
import uri.IPv4Address;
import uri.Uri;
import uri.UriParserFactory;


/**
 * This class provides a very simple example of how to write tests for this project.
 * You can implement your own tests within this class or any other class within this package.
 * Tests in other packages will not be run and considered for completion of the project.
 */
public class SimpleTests {

	/**
	 * Helper function to determine if the given host is an instance of {@link IPv4Address}.
	 *
	 * @param host the host
	 * @return {@code true} if the host is an instance of {@link IPv4Address}
	 */
	public boolean isIPv4Address(Host host) {
		return host instanceof IPv4Address;
	}

	/**
	 * Helper function to retrieve the byte array representation of a given host which must be an instance of {@link IPv4Address}.
	 *
	 * @param host the host
	 * @return the byte array representation of the IPv4 address
	 */
	public byte[] getIPv4Octets(Host host) {
		if (!isIPv4Address(host))
			throw new IllegalArgumentException("host must be an IPv4 address");
		return ((IPv4Address) host).getOctets();
	}

	/*
	@Test
	public void octa_1() {
		byte[] IPv4_octats = getIPv4Octets(UriParserFactory.create("http:///username:password@1.2.3.4/path/data?key=value").parse().getHost());
		byte[] expectd_Octats = {55,2,3,4};
		assertEquals("fsd", expectd_Octats, IPv4_octats);

	}
	 */

	@Test
	public void testNonNull() {
		assertNotNull(UriParserFactory.create("scheme://").parse());
	}

	@Test
	public void testNegativeSimple() {
		assertNull(UriParserFactory.create("").parse());
	}

	@Test
	public void testIPv4AddressSimple() {
		Host host = UriParserFactory.create("scheme://1.2.3.4").parse().getHost();
		assertTrue("host must be an IPv4 address", isIPv4Address(host));
	}
	@Test(expected = NullPointerException.class)
	public void scheme_test() {
		String uri_scheme = UriParserFactory.create("9http://").parse().getScheme();
		//String expected_scheme = "9http";
		//assertEquals("the scheme is not correct", expected_scheme, uri_scheme);
	}
	@Test
	public void testNegativeSimple_2() {
		Uri uri = UriParserFactory.create(null).parse();
		assertNull("uri is null", uri);
	}
	@Test
	public void scheme_test_2() {
		String uri_scheme = UriParserFactory.create("http://example.org/test/test1?search=test-question#part2").parse().getScheme();
		String expected_scheme = "http";
		assertTrue("the scheme is coorect", expected_scheme.compareTo(uri_scheme) == 0);
	}
	@Test
	public void scheme_test_3() {
		String uri_scheme = UriParserFactory.create("h2ttp://example.org/test/test1?search=test-question#part2").parse().getScheme();
		String expected_scheme = "h2ttp";
		assertEquals("the scheme is correct",  expected_scheme, uri_scheme);
	}

	@Test
	public void scheme_test_4() {
		String uri_scheme = UriParserFactory.create("http4://example.org/test/test1?search=test-question#part2").parse().getScheme();
		String expected_scheme = "http4";
		assertTrue("the scheme is coorect", expected_scheme.compareTo(uri_scheme) == 0);
	}

	@Test
	public void scheme_test_5() {
		String uri_scheme = UriParserFactory.create("ht4tp4://example.org/test/test1?search=test-question#part2").parse().getScheme();
		String expected_scheme = "ht4tp4";
		assertTrue("the scheme is coorect", expected_scheme.compareTo(uri_scheme) == 0);
	}

	@Test
	public void scheme_test_6() {
		String uri_scheme = UriParserFactory.create("h2t4tp4://example.org/test/test1?search=test-question#part2").parse().getScheme();
		String expected_scheme = "h2t4tp4";
		assertTrue("the scheme is coorect", expected_scheme.compareTo(uri_scheme) == 0);
	}

	@Test
	public void scheme_test_7() {
		String uri_scheme = UriParserFactory.create("h2t4tp://example.org/test/test1?search=test-question#part2").parse().getScheme();
		String expected_scheme = "h2t4tp";
		assertTrue("the scheme is coorect", expected_scheme.compareTo(uri_scheme) == 0);
	}

	@Test
	public void scheme_test_simple() {
		String uri_scheme = UriParserFactory.create("https://de.wikipedia.org/wiki/Uniform_Resource_Identifier").parse().getScheme();
		String expected_scheme = "https";
		assertTrue("the scheme is coorect", expected_scheme.compareTo(uri_scheme) == 0);
	}

	@Test
	public void hierarchical_part_1() {
		Uri uri = UriParserFactory.create("http:/example.org/test/test1?search=test-question#part2").parse();
		assertNull("the beginn of every hierarchical part is //", uri);
	}
	@Test
	public void hierarchical_part_2() {
		Uri uri = UriParserFactory.create("http:example.org/test/test1?search=test-question#part2").parse();
		assertNull("the beginn of every hierarchical part is //", uri);
	}
	@Test
	public void hierarchical_part_3() {
		Uri uri = UriParserFactory.create("example.org/test/test1?search=test-question#part2").parse();
		assertNull("no scheme", uri);
	}
	@Test
	public void hierarchical_part_4() {
		Uri uri = UriParserFactory.create("http//example.org/test/test1?search=test-question#part2").parse();
		assertNull("the beginn of every hierarchical part is //", uri);
	}

	@Test
	public void uri_double_points() {
		Uri uri = UriParserFactory.create("http//example.org/test/test1?search=test-question#part2").parse();
		assertNull("the double points is missing", uri);
	}

	@Test
	public void user_information_1() {
		String user_info = UriParserFactory.create("http://username:password@example.com/path/data?key=value").parse().getUserInfo();
		String expected_user_info = "username:password";
		assertTrue("the username is correct", user_info.compareTo(expected_user_info) == 0);
	}

	@Test
	public void user_information_2() {
		String user_info = UriParserFactory.create("http://3username:password@example.com/path/data?key=value").parse().getUserInfo();
		String expected_user_info = "3username:password";
		assertTrue("the username is correct", user_info.compareTo(expected_user_info) == 0);
	}

	@Test
	public void user_information_3() {
		String user_info = UriParserFactory.create("http://.3username:password@example.com/path/data?key=value").parse().getUserInfo();
		String expected_user_info = ".3username:password";
		assertTrue("the username is correct", user_info.compareTo(expected_user_info) == 0);
	}
	@Test
	public void user_information_OnlyAlpha() {
		String user_info = UriParserFactory.create("http://usernamepassword@example.com/path/data?key=value").parse().getUserInfo();
		String expected_user_info = "usernamepassword";
		assertTrue("the username is correct", user_info.compareTo(expected_user_info) == 0);
	}

	@Test
	public void user_information_OnlyDigit() {
		String user_info = UriParserFactory.create("http://123@example.com/path/data?key=value").parse().getUserInfo();
		String expected_user_info = "123";
		assertTrue("the username is correct", user_info.compareTo(expected_user_info) == 0);
	}
	@Test
	public void user_information_OnlyDot() {
		String user_info = UriParserFactory.create("http://.@example.com/path/data?key=value").parse().getUserInfo();
		String expected_user_info = ".";
		assertTrue("the username is correct", user_info.compareTo(expected_user_info) == 0);
	}

	@Test
	public void user_information_All_Pchar_Options() {
		String user_info = UriParserFactory.create("http://.3username56password.@example.com/path/data?key=value").parse().getUserInfo();
		String expected_user_info = ".3username56password.";
		assertTrue("the username is correct", user_info.compareTo(expected_user_info) == 0);
	}

	@Test
	public void user_information_Only_Double_Points() {
		String user_info = UriParserFactory.create("http://:@example.com/path/data?key=value").parse().getUserInfo();
		String expected_user_info = ":";
		assertTrue("the username is correct", user_info.compareTo(expected_user_info) == 0);
	}

	@Test
	public void user_information_Only_Pchar_Pct_Encoded_1() {
		String user_info = UriParserFactory.create("http://%00@example.com/path/data?key=value").parse().getUserInfo();
		String expected_user_info = "%00";
		assertTrue("the username is correct", user_info.compareTo(expected_user_info) == 0);
	}

	@Test
	public void user_information_Only_Pchar_Pct_Encoded_2() {
		String user_info = UriParserFactory.create("http://%aa@example.com/path/data?key=value").parse().getUserInfo();
		String expected_user_info = "%aa";
		assertTrue("the username is correct", user_info.compareTo(expected_user_info) == 0);
	}

	@Test
	public void user_information_Only_Pchar_Pct_Encoded_3() {
		String user_info = UriParserFactory.create("http://%AA@example.com/path/data?key=value").parse().getUserInfo();
		String expected_user_info = "%AA";
		assertTrue("the username is correct", user_info.compareTo(expected_user_info) == 0);
	}

	@Test
	public void user_information_Only_Pchar_Pct_Encoded_4() {
		String user_info = UriParserFactory.create("http://%9a@example.com/path/data?key=value").parse().getUserInfo();
		String expected_user_info = "%9a";
		assertTrue("the username is correct", user_info.compareTo(expected_user_info) == 0);
	}

	@Test
	public void user_information_Only_Pchar_Pct_Encoded_5() {
		String user_info = UriParserFactory.create("http://%9A@example.com/path/data?key=value").parse().getUserInfo();
		String expected_user_info = "%9A";
		assertTrue("the username is correct", user_info.compareTo(expected_user_info) == 0);
	}

	@Test
	public void user_information_Only_Pchar_Pct_Encoded_6() {
		String user_info = UriParserFactory.create("http://%a2@example.com/path/data?key=value").parse().getUserInfo();
		String expected_user_info = "%a2";
		assertTrue("the username is correct", user_info.compareTo(expected_user_info) == 0);
	}

	@Test
	public void user_information_Only_Pchar_Pct_Encoded_8() {
		String user_info = UriParserFactory.create("http://%A9@example.com/path/data?key=value").parse().getUserInfo();
		String expected_user_info = "%A9";
		assertTrue("the username is correct", user_info.compareTo(expected_user_info) == 0);
	}

	@Test
	public void user_information_Only_Pchar_Pct_Encoded_9() {
		String user_info = UriParserFactory.create("http://%aA@example.com/path/data?key=value").parse().getUserInfo();
		String expected_user_info = "%aA";
		assertTrue("the username is correct", user_info.compareTo(expected_user_info) == 0);
	}

	@Test
	public void user_information_Only_Pchar_Pct_Encoded_10() {
		String user_info = UriParserFactory.create("http://%Aa@example.com/path/data?key=value").parse().getUserInfo();
		String expected_user_info = "%Aa";
		assertTrue("the username is correct", user_info.compareTo(expected_user_info) == 0);
	}

	/*
	@Test
	public void user_information_Only_Pchar_Pct_Encoded_11() {
		Uri user_info = UriParserFactory.create("http://%A@example.com/path/data?key=value").parse();
		assertNull("uri is not valid", user_info);
	}
	 */


	@Test
	public void user_information_Not_There() {
		String user_info = UriParserFactory.create("http://example.com/path/data?key=value").parse().getUserInfo();
		//assertTrue("The uri dose not contain a user-info", user_info.compareTo("")==0);
		assertNull("user_info not found", user_info);
	}

	@Test
	public void user_information_Empty() {
		String user_info = UriParserFactory.create("http://@example.com/path/data?key=value").parse().getUserInfo();
		String expected_User_Info = "";
		assertEquals("the user indo could be empty", expected_User_Info, user_info);
	}


	@Test
	public void host_1_Reg_Name_1() {
		String host = UriParserFactory.create("http://username:password@example.com/path/data?key=value").parse().getHost().toString();
		String expected_Host = "example.com";
		assertTrue("the host is correct", host.compareTo(expected_Host) == 0);
	}

	@Test
	public void host_1_Reg_Name_3() {
		String host = UriParserFactory.create("http://username:password@example23.com2/path/data?key=value").parse().getHost().toString();
		String expected_Host = "example23.com2";
		assertTrue("the host is correct", host.compareTo(expected_Host) == 0);
	}

	@Test
	public void host_1_Reg_Name_4() {
		String host = UriParserFactory.create("http://username:password@%2a/path/data?key=value").parse().getHost().toString();
		String expected_Host = "%2a";
		assertTrue("the host is correct", host.compareTo(expected_Host) == 0);
	}

	@Test
	public void host_1_Reg_Name_5() {
		String host = UriParserFactory.create("http://username:password@%2A/path/data?key=value").parse().getHost().toString();
		String expected_Host = "%2A";
		assertTrue("the host is correct", host.compareTo(expected_Host) == 0);
	}

	@Test
	public void host_1_Reg_Name_6() {
		String host = UriParserFactory.create("http://username:password@%aA/path/data?key=value").parse().getHost().toString();
		String expected_Host = "%aA";
		assertTrue("the host is correct", host.compareTo(expected_Host) == 0);
	}

	@Test
	public void host_1_Reg_Name_7() {
		String host = UriParserFactory.create("http://username:password@%24/path/data?key=value").parse().getHost().toString();
		String expected_Host = "%24";
		assertTrue("the host is correct", host.compareTo(expected_Host) == 0);
	}

	@Test
	public void host_1_Reg_Name_8() {
		String host = UriParserFactory.create("http://username:password@%aa/path/data?key=value").parse().getHost().toString();
		String expected_Host = "%aa";
		assertTrue("the host is correct", host.compareTo(expected_Host) == 0);
	}

	@Test
	public void host_1_Reg_Name_9() {
		String host = UriParserFactory.create("http://username:password@%AA/path/data?key=value").parse().getHost().toString();
		String expected_Host = "%AA";
		assertTrue("the host is correct", host.compareTo(expected_Host) == 0);
	}

	@Test(expected = NullPointerException.class)
	public void host_1_Reg_Name_10() {
		Host uri_Host = UriParserFactory.create("http://username:password@%A/path/data?key=value").parse().getHost();
	}

	@Test
	public void host_2() {
		Host host = UriParserFactory.create("http://username:password@1.2.3/path/data?key=value").parse().getHost();
		assertFalse("host must be an IPv4 address", isIPv4Address(host));
	}

	/*
	@Test(expected = NullPointerException.class)
	public void host_21_invalid() {
		Host host = UriParserFactory.create("http://username:password@1.2.3/path/data?key=value").parse().getHost();
	}
	 */

	@Test(expected = NullPointerException.class)
	public void host_22_invalid() {
		Host host = UriParserFactory.create("http://username:password@%M/path/data?key=value").parse().getHost();
	}

	@Test
	public void host_3() {
		Host host = UriParserFactory.create("http://username:password@1.2.3.4.5/path/data?key=value").parse().getHost();
		assertFalse("host must be an IPv4 address", isIPv4Address(host));
	}

	@Test
	public void host_4() {
		Host uri_host = UriParserFactory.create("http://username:password@/path/data?key=value").parse().getHost();
		assertTrue("there is no host", uri_host.toString().compareTo("") == 0);
	}

	@Test
	public void host_5() {
		Host host = UriParserFactory.create("http://username:password@001.012.133.224/path/data?key=value").parse().getHost();
		assertTrue("host must be an IPv4 address", isIPv4Address(host));
	}

	@Test
	public void path_1() {
		String path = UriParserFactory.create("http://username:password@example.com/path/data?key=value").parse().getPath();
		String expected_path = "/path/data";
		assertTrue("The path should start with a /", path.compareTo(expected_path) == 0);
	}

	@Test(expected = NullPointerException.class)
	public void path_2() {
		String path = UriParserFactory.create("http://username:password@example.com/%PM?key=value").parse().getPath();
	}

	@Test
	public void path_3() {
		String path = UriParserFactory.create("http://username:password@example.com/?key=value").parse().getPath();
		String expected_path = "/";
		assertTrue("correct path", path.compareTo(expected_path) == 0);
	}

	@Test
	public void path_empty() {
		String path = UriParserFactory.create("http://username:password@example.com?key=value").parse().getPath();
		String expected_path = "";
		assertTrue("correct path", path.compareTo(expected_path) == 0);
	}
	@Test
	public void path_Only_Dash() {
		String path = UriParserFactory.create("http://username:password@example.com/?key=value").parse().getPath();
		String expected_path = "/";
		assertTrue("correct path", path.compareTo(expected_path) == 0);
	}



	@Test(expected = NullPointerException.class)
	public void path_invalid() {
		String path = UriParserFactory.create("http://username:password@example.com/$ $?key=value").parse().getPath();
	}

	@Test
	public void hierarchical_without_authority_path() {
		Uri uri = UriParserFactory.create("http://username:password@example.com?key=value").parse();
		assertNotNull("The uri can be valid without having a path", uri);
	}

	@Test
	public void query_1() {
		String query = UriParserFactory.create("http://username:password@example.com/path/data?key=value").parse().getQuery();
		String expected_Query = "key=value";
		assertTrue("The query is correct", query.compareTo(expected_Query) == 0);
	}

	@Test
	public void query_2() {
		String query = UriParserFactory.create("http://username:password@example.com/path/data?").parse().getQuery();
		String expected_Query = "";
		assertEquals("the query could be empty", query, expected_Query);
	}

	@Test
	public void query_3_All_Pchar_Options() {
		String query = UriParserFactory.create("http://username:password@example.com/path/data?&=a4.A%a9").parse().getQuery();
		String expected_Query = "&=a4.A%a9";
		assertTrue("The query is coorect", query.compareTo(expected_Query) == 0);
	}

	@Test(expected = NullPointerException.class)
	public void query_4() {
		String uri_query = UriParserFactory.create("http://username:password@example.com/path/data?&=a4.A%a9$").parse().getQuery();
		assertNull("The query is not coorect, undefined symbol", uri_query);
	}

	@Test(expected = NullPointerException.class)
	public void query_4_invalid() {
		String uri_query = UriParserFactory.create("http://username:password@example.com/path/data?$&=a4.A%a9$").parse().getQuery();
	}

	@Test(expected = NullPointerException.class)
	public void query_5() {
		String uri_query = UriParserFactory.create("http://username:password@example.com/path/data?& =a4.A%a9$").parse().getQuery();
		//assertNull("The query is not coorect, undefined symbol", uri_query);
	}

	@Test
	public void query_6() {
		String uri_query = UriParserFactory.create("http://username:password@example.com/path/data§& =a4.A%a9$").parse().getQuery();
		assertNull("The query is not coorect, undefined symbol", uri_query);
	}

	@Test
	public void query_7() {
		String uri_query = UriParserFactory.create("http://username:password@example.com/path/data &=a4.A%a9$").parse().getQuery();
		assertNull("The query is not coorect, undefined symbol", uri_query);
	}

	@Test
	public void query_8() {
		String query = UriParserFactory.create("http://username:password@example.com/path/data?%aA").parse().getQuery();
		String expected_Query = "aA";
		assertTrue("The query is correct", query.compareTo(expected_Query) == 0);
	}

	@Test
	public void query_9() {
		String query = UriParserFactory.create("http://username:password@example.com/path/data?=").parse().getQuery();
		String expected_Query = "=";
		assertTrue("The query is correct", query.compareTo(expected_Query) == 0);
	}


	@Test
	public void query_10() {
		String query = UriParserFactory.create("http://username:password@example.com/path/data?&").parse().getQuery();
		String expected_Query = "&";
		assertTrue("The query is correct", query.compareTo(expected_Query) == 0);
	}

	@Test
	public void query_11() {
		String query = UriParserFactory.create("http://username:password@example.com/path/data").parse().getQuery();
		assertNull("Uri dose not contain a query", query);
	}

	@Test
	public void valid_Uri_1() {
		Uri uri = UriParserFactory.create("http://username:password").parse();
		assertNotNull("Uri dose not contain a query", uri);
	}

	@Test
	public void valid_Uri_2() {
		Uri uri = UriParserFactory.create("http://username:password@example.com").parse();
		assertNotNull("Uri dose not contain a query", uri);
	}


	@Test
	public void valid_Uri_3() {
		Uri uri = UriParserFactory.create("http://username:password@example.com/path/data").parse();
		assertNotNull("Uri dose not contain a query", uri);
	}

	@Test
	public void valid_Uri_4() {
		Uri uri = UriParserFactory.create("http://example.com/path/data").parse();
		assertNotNull("Uri dose not contain a query", uri);
	}

	@Test
	public void valid_Uri_5() {
		Uri uri = UriParserFactory.create("http://example.com/path/data?key=value").parse();
		assertNotNull("Uri dose not contain a query", uri);
	}


	@Test
	public void valid_Uri_6() {
		Uri uri = UriParserFactory.create("http:///path/data").parse();
		assertNotNull("Uri dose not contain a query", uri);
	}


	@Test
	public void valid_Uri_7() {
		Uri uri = UriParserFactory.create("http://username:password@?key=value").parse();
		assertNotNull("Uri dose not contain a query", uri);
	}

	@Test
	public void valid_Uri_8() {
		Uri uri = UriParserFactory.create("http://?key=value").parse();
		assertNotNull("Uri dose not contain a query", uri);
	}

	@Test
	public void valid_Uri_9() {
		Uri uri = UriParserFactory.create("http://username:password@/path/data").parse();
		assertNotNull("Uri dose not contain a query", uri);
	}

	@Test
	public void valid_Uri_10() {
		Uri uri = UriParserFactory.create("http://username:password@/").parse();
		assertNotNull("Uri dose not contain a query", uri);
	}

	@Test
	public void valid_Uri_11() {
		Uri uri = UriParserFactory.create("http://?").parse();
		assertNotNull("Uri dose not contain a query", uri);
	}

	@Test
	public void valid_Uri_12() {
		Uri uri = UriParserFactory.create("http://@").parse();
		assertNotNull("Uri dose not contain a query", uri);
	}

}