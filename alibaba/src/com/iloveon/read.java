package com.iloveon;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document; 
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class read {

	public String getHtml(String username) throws Exception
	{
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String aliurl = ".cn.alibaba.com/page/contactinfo.htm";
		
		URL url = new URL("http://" + username + aliurl);
		//System.out.println(url);
		conn = (HttpURLConnection)url.openConnection();
		String cookie = "cna=ScmYBWPzDkECAcPA8XLK/66h; ali_apache_id=114.243.156.43.17991657150916.7; ali_beacon_id=113.45.123.160.1355140642804.2; JSESSIONID=7Z966F71-7EEBG7HNCF97M6Z4FC9P1-MVVOE0BH-J2102; _tmp_hny_0=\"kFRcK6prBN8WZQg0TgFr4%2BeiaUBXaG7oWdcxMpqLwb8nkpa6I%2BZDHPzXcIO9eRy8K2Xu93hJyJHwVWfReHcDGKuTywtOc8rTWHJIWktFgJ7onVeEaURNbsbOqsh5TOMhc%2BASQm26R9s9thus4WI5DUugnXATSQrH11klwSBovVzNrbNlKC1oyZf4DyWgFEqQmhv6attdQT8EQqY019OgydN6AbKlq%2BN9GL%2BVI%2Bp10z4JqB0pI9mdVaVT4MwkVzwpKFm8W9KP6PTqtpATU237ouxybyVV%2B4qE7gVM5hPq4Kx0xZG9md57vYHvpQJKXSNR19FVa%2BQirCv1oq3EDruHpA%3D%3D\"; ali_apache_sid=113.45.174.4.83892160075807.6|1356161875; lzpg_2706934_uv=20248489281608618338; _ITBU_IS_FIRST_VISITED_=n; ali_apache_track=\"c_ms=1|c_mt=3|c_mid=enhesn|c_lid=enhesn\"; ali_apache_tracktmp=\"c_w_signed=Y\"; _t_ck_0=\"AhAPwLGenDsU2obBvfytDuYzutyC9CTAp5Hmhx2OJP7MTMF4pJr%2BQGvY2Sj5VEZdJhk41y7heY%2BNCwzvtXGyyDcIG0io%2B7MCIthZxYm%2F9wTorK8Fcb2cGXpnIk5ckWwLu1aQjdtIMd0hczNbKG32curEBYgJ3FhwCbPn8GNUamnoUVFI9D84alIMuGDzFPemxjitKytCkdwcaoHLxvZzN6DV81oHmuVPPJCGE%2BeuknfEOKwTrkO3ln18883LB7CGqlD0YPHm4XtKyIa2bN18QFqt5gOTFPhXzrvmfgkN%2FhL36bN8FFmnfx%2BOudAu%2BJz9YqsWsoV32IIboA9l4aTG%2Fw%3D%3D\"; _t_ck_sum=AhAPwLGenDsU2ob; track_cookie=n&null; ali_ab=113.45.123.160.1355140643566.8; alicnweb=lastlogonid%3Denhesn%7Ctouch_tb_at%3D1356171580275; __cn_logon__=true; __cn_logon_id__=enhesn; last_mid=enhesn; __last_loginid__=enhesn; _cn_slid_=NJ224G8Gw%2F; lzpg_2706934_ss=1159806472_2_1356172792; _tmp_ck_0=nkKUi3QG63WorHLjlqh3R4rXSsjyjKYP71Hevgfga9RjMsNYJZmYGRUTqGRSWLIOjUwOPmiMCMMMAixr78Wjx0ErPloBYGR2%2FxMxqoTmElhEHloNNXY7mFRV92YqF4UeIX0OLC6eiasiCOL7ErqTgRMmi3frIm%2F4gfgyZU852GRVH11jXBxtJf78GxqUhYj5zFD9yp7X8CsXoj52IU57XuB64cOzZUPOtbbAKfXtmT6iLlA2bEEOId%2FNc%2BcVFpp%2By0iqcBVJCdsqumKSpQGpbp4MgM71%2F7uT%2BO3zmqDVajq7rLYcAHmStIpsL1Qwa4Kq2YnDVT1Q%2BQE1icCz51ALHI5tQhBaLextXhyLDL3MQKXuZSF6b3KObbzZVJKmI%2FtSgH9PttQQv7jzI%2FJjJZUzN7jal6rLU1OjgOyML83ItwPN%2Bp%2FQtzXO%2BBrLwA1J9kEU5yJ90Al%2FDXCnrjbUkN6xbO6E%2BWgJ6S4rbqrf2si2VuiRYtsrT0q%2BQgsgvSGPlHbwcZ%2F%2B21a4l99T2Az7doPYdEeb3xRYz0GakP0OyOoWB8Ydo%2Fscc8XfmPQx8aTV8EwSgh9apI6nC1zajwIdp1t7%2BA%3D%3D; cn_tmp=\"Z28mC+GqtZ2ilGw+fki6SN8ZPP8OqVxe+zjQkoLxkMJ4DdY9dhc03/c+VgLBDZz1QHY/e+psE/13weFt1ZMaIl3gbrYMHb+IvrbM30PLLQd3DAhErim1sAjEcD2kZjLuPc0E2j9Rm+UKcGloNpzZ5KZnx4P6WinIK3wP7Bqvim6KUukQC9XlcKW8LiX+9dhvPtUYmnlJuej1RWLAFn6onjQAtyDjvOO0\"; _csrf_token=1356166680679";
		conn.setRequestProperty("Cookie",cookie);   
		conn.connect();
		StringBuffer sb = new StringBuffer();
		reader = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}

		return sb.toString();
	}
	
	public String getPhone(String html)
	{
		/*
		String logo = "<dt>移动电话：</dt>";
		int p = html.indexOf(logo);
		String phone = html.substring(p + 35, p + 99);
		phone = phone.trim();
		*/
		Document doc = Jsoup.parse(html);
		
		Elements eles = doc.select("dl.m-mobilephone");
	
		if(eles != null && eles.size() > 0){
			//System.out.println(eles.text());
			String phone = eles.select("dd").get(0).text().trim();
			//System.out.println(phone);
			if(phone != null && phone.length() > 10){
				if(phone.charAt(0) == '0')
				{
					phone = phone.substring(1,phone.length());
				}
				if(phone.charAt(0) == '1')
				{
					return phone.trim();
				}
			}
		}
		return "";
	}
	
	public String getHtml1(String username) throws Exception
	{
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String aliurl = "http://china.alibaba.com/company/detail/contact/";
		
		URL url = new URL(aliurl+ username + ".html");
		//System.out.println(url);
		conn = (HttpURLConnection)url.openConnection();
		String cookie = "cna=ScmYBWPzDkECAcPA8XLK/66h; ali_apache_id=114.243.156.43.17991657150916.7; ali_beacon_id=113.45.123.160.1355140642804.2; JSESSIONID=7Z966F71-7EEBG7HNCF97M6Z4FC9P1-MVVOE0BH-J2102; _tmp_hny_0=\"kFRcK6prBN8WZQg0TgFr4%2BeiaUBXaG7oWdcxMpqLwb8nkpa6I%2BZDHPzXcIO9eRy8K2Xu93hJyJHwVWfReHcDGKuTywtOc8rTWHJIWktFgJ7onVeEaURNbsbOqsh5TOMhc%2BASQm26R9s9thus4WI5DUugnXATSQrH11klwSBovVzNrbNlKC1oyZf4DyWgFEqQmhv6attdQT8EQqY019OgydN6AbKlq%2BN9GL%2BVI%2Bp10z4JqB0pI9mdVaVT4MwkVzwpKFm8W9KP6PTqtpATU237ouxybyVV%2B4qE7gVM5hPq4Kx0xZG9md57vYHvpQJKXSNR19FVa%2BQirCv1oq3EDruHpA%3D%3D\"; ali_apache_sid=113.45.174.4.83892160075807.6|1356161875; lzpg_2706934_uv=20248489281608618338; _ITBU_IS_FIRST_VISITED_=n; ali_apache_track=\"c_ms=1|c_mt=3|c_mid=enhesn|c_lid=enhesn\"; ali_apache_tracktmp=\"c_w_signed=Y\"; _t_ck_0=\"AhAPwLGenDsU2obBvfytDuYzutyC9CTAp5Hmhx2OJP7MTMF4pJr%2BQGvY2Sj5VEZdJhk41y7heY%2BNCwzvtXGyyDcIG0io%2B7MCIthZxYm%2F9wTorK8Fcb2cGXpnIk5ckWwLu1aQjdtIMd0hczNbKG32curEBYgJ3FhwCbPn8GNUamnoUVFI9D84alIMuGDzFPemxjitKytCkdwcaoHLxvZzN6DV81oHmuVPPJCGE%2BeuknfEOKwTrkO3ln18883LB7CGqlD0YPHm4XtKyIa2bN18QFqt5gOTFPhXzrvmfgkN%2FhL36bN8FFmnfx%2BOudAu%2BJz9YqsWsoV32IIboA9l4aTG%2Fw%3D%3D\"; _t_ck_sum=AhAPwLGenDsU2ob; track_cookie=n&null; ali_ab=113.45.123.160.1355140643566.8; alicnweb=lastlogonid%3Denhesn%7Ctouch_tb_at%3D1356171580275; __cn_logon__=true; __cn_logon_id__=enhesn; last_mid=enhesn; __last_loginid__=enhesn; _cn_slid_=NJ224G8Gw%2F; lzpg_2706934_ss=1159806472_2_1356172792; _tmp_ck_0=nkKUi3QG63WorHLjlqh3R4rXSsjyjKYP71Hevgfga9RjMsNYJZmYGRUTqGRSWLIOjUwOPmiMCMMMAixr78Wjx0ErPloBYGR2%2FxMxqoTmElhEHloNNXY7mFRV92YqF4UeIX0OLC6eiasiCOL7ErqTgRMmi3frIm%2F4gfgyZU852GRVH11jXBxtJf78GxqUhYj5zFD9yp7X8CsXoj52IU57XuB64cOzZUPOtbbAKfXtmT6iLlA2bEEOId%2FNc%2BcVFpp%2By0iqcBVJCdsqumKSpQGpbp4MgM71%2F7uT%2BO3zmqDVajq7rLYcAHmStIpsL1Qwa4Kq2YnDVT1Q%2BQE1icCz51ALHI5tQhBaLextXhyLDL3MQKXuZSF6b3KObbzZVJKmI%2FtSgH9PttQQv7jzI%2FJjJZUzN7jal6rLU1OjgOyML83ItwPN%2Bp%2FQtzXO%2BBrLwA1J9kEU5yJ90Al%2FDXCnrjbUkN6xbO6E%2BWgJ6S4rbqrf2si2VuiRYtsrT0q%2BQgsgvSGPlHbwcZ%2F%2B21a4l99T2Az7doPYdEeb3xRYz0GakP0OyOoWB8Ydo%2Fscc8XfmPQx8aTV8EwSgh9apI6nC1zajwIdp1t7%2BA%3D%3D; cn_tmp=\"Z28mC+GqtZ2ilGw+fki6SN8ZPP8OqVxe+zjQkoLxkMJ4DdY9dhc03/c+VgLBDZz1QHY/e+psE/13weFt1ZMaIl3gbrYMHb+IvrbM30PLLQd3DAhErim1sAjEcD2kZjLuPc0E2j9Rm+UKcGloNpzZ5KZnx4P6WinIK3wP7Bqvim6KUukQC9XlcKW8LiX+9dhvPtUYmnlJuej1RWLAFn6onjQAtyDjvOO0\"; _csrf_token=1356166680679";
		conn.setRequestProperty("Cookie",cookie);   
		conn.connect();
		StringBuffer sb = new StringBuffer();
		reader = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}

		return sb.toString();
	}
	
	public String getPhone1(String html)
	{
		Document doc = Jsoup.parse(html);
		
		Elements eles = doc.select("span#cellphone-number");
		if(eles != null && eles.size() > 1 )
		{
			Element ele = eles.get(1);
			//String ele = eles.attr("companyId");
			
			String phone = ele.text();
			
			//System.out.println(phone);
			phone = phone.trim();
			if(phone.length() > 10)
			{
				//System.out.println("phone");
				if(phone.charAt(0) == '0')
				{
					phone = phone.substring(1,phone.length());
				}
				if(phone.charAt(0) == '1')
				{
					return phone.trim();
				}
			}
		}
		return "";
	}
	
	public String getCompanyByForm(int index) throws Exception
	{
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		
		String wz = "http://search.china.alibaba.com/company/company_search.htm?showStyle=noimg&amp;keywords=%CE%C2%D6%DD&amp;pageSize=30&amp;n=y";
		URL url = new URL(wz);
		//System.out.println("url = " + url);
		conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);// 打开写入属性  
		//conn.setDoInput(true);// 打开读取属性 
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_0) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.97 Safari/537.11");
		conn.setRequestProperty("Host", "search.china.alibaba.com");
		conn.setRequestProperty("Origin", "http://search.china.alibaba.com");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
		conn.setRequestProperty("Cache-Control", "max-age=0");
		conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		conn.setRequestProperty("Connection", "keep-alive");
		
		String coo = "ali_beacon_id=220.113.8.242.1356053894562.5; JSESSIONID=7Z966G81-47HB6ZU6H3UXR478NRHU1-6J6HNYAH-KRB4; cna=doXLB7ayuiMCAYIi/3dIb7rq; ali_apache_id=220.113.8.242.26400753908851.5; ali_apache_sid=220.113.8.242.26400753908851.5|1356055708; __last_loginid__=%E8%91%AC%E7%88%B1love88; _tmp_hny_0=\"AhAPwLGenDsU2obBvfytDhX2vdqOc6ETwZde8jyqyAuJ90Zxhbzf6bvs8WZsWhvX7CZvUxR8HGJJFl6T9GipaRqwAOHjArgl323vfO6jMvI3rR2FluhZEf4B9cbae4bk873BcuqUPY7x6zh%2BbMAflWFOwExVVj0cdcPj%2BsR0%2FHAMbjVSAU3IbhD1VyrqxMnYZsthPUGAEtRkrvkvB8amdj3IONt%2BlRm96tweBVe%2B4FHkv5ZJS2nW8iuzY%2B7OhhE%2FhLoh4fDa%2FXUz9AwK2xlRp6%2B5CrGkTg2nr3f1J664V8jdDicBqGH0%2Fn9nReWonXLrY1Feje5qH7BrngNsxBRDrg%3D%3D\"; __cn_logon__=false; track_cookie=n&null; _csrf_token=1356316482849; _tmp_ck_0=ZriQB1UOr1AW%2FJNpn4AxCNniPkxQ64Siwb%2BT2tRZjiiEXDV5yQyauo2PlI7UxljUpxHBM2abj6VHCzYpw8hiW1d4caqU8mUn3c7uJelIxfttYFWMWn3y81QYULRq%2F3IAmy50gYaKpiIZdoRfqZjxSt4JR1dZqBHSTKbs57XwmfzMAI8kYgVviRfaohpdsgZewKDkRAQu%2FFgCrs8x%2B6gqirN336r9VCNxr28HER9npnypwzMcBJEhrZFfbVqH3%2BXZ8bJisfFeeY4sg5cOO8hqAwQlwIL2Kwix1OsqsH9BcJ8Vo1FF6chkQoWefhEJcfUXHHiOiEwwfJZ9xl6jGZD31E4EwNMHHDG6l5tzKKBF%2FuTDqjZByNmHXMNlj48g7PBHW7vSSdXoIw%2FoCtNyVyIApoOyegqtwa%2FpikNKUP5dU7Zwa7B%2FnPjTOC3RFlXMyhXU9ZRG9S8o%2BJE%3D; h_keys=\"%u6e29%u5dde#PP#%u516c%u53f8\"; alicnweb=lastlogonid%3D%25E8%2591%25AC%25E7%2588%25B1love88%7Ctouch_tb_at%3D1356327772137; alisw=swIs1200%3D1%7C; _t_ck_0=\"GDnm0JftrAAlFLwK1AauVsDbIzrv6jnF6llvr22fUK3UXvtLNmT3RbfcJqbJIFal7sIy0cD2O%2BXfCkWhRvZBC036kRGhTbC6K87BkPMipbLyqYV9tMSIKTVYapUloASZ%2F7Oj893eE2DaMuvSmxMG4s9YBOBIYns0kmZVg3g33aquIHOWi99ABXs7phnMcqkcxFPJfOOXybsz%2FzKTCxnnfZYQtiBsifZ6GTZz3ln618paKB13OgQ8eDCVzpDxVozBsh6vJwlu%2FeoMkGosyXpJTxzxDxgb18%2BW79%2F%2BKt224QglpisIr4gl0PXNANEbt4D5mjZsp6DWAGvHO75tT1MsDA%3D%3D\"; _t_ck_sum=GDnm0JftrAAlFLw; aliBeacon_bcookie=; ad_prefer=\"2012/12/24 15:11:22\"; ali_ab=220.113.8.242.1356314736989.5";
		conn.setRequestProperty("Cookie", coo);
		conn.setRequestProperty("Referer", "http://search.china.alibaba.com/company/company_search.htm?showStyle=noimg&keywords=%CE%C2%D6%DD&pageSize=30&offset=3&n=y&beginPage"+(index-1));
		//conn.setRequestProperty("Referer", "http://www.china.alibaba.com");
		//conn.connect();
		
		StringBuffer psb = new StringBuffer();
		//psb.append("UA_InputId=");
		psb.append("ua=");
		String inputhidden = "089BqJHKoxwGrxGKIt2E7JCLuY=|BaJHQPd9Sa4IOckiUvUPf8hMcYdsbaU=|BKJHQOccbMksQeYZdM0xVfcIeNwnQ+YDZ8I7S+8Uf9w5UvABAMg=|A6dcLJtjDa5RNJNtVfAONMVpUfFcZsI5UqYJMcY9UadZNZZpV/MJYMM5VvIKZcI6VPAbGg==|AqZWJugRYcM/T+4LdY4hB8kiI+s=|AaVcLJsJZsU1Uf0YGK9XOJhnC6tRNJRtBKNdfdg/U/MKb8I5VPILbsk0WvwMZ8c7O4xpBMwnJg==|AKReLuALGu5EOOJ4BKBZK4ZhDbpfIflxbtsjTvcLe90lVfUeH9c=|D65LTPtdZ5Q4FrBJN45yH7NWP55gCbBIIYV9F7pBQIg=|Dq5LTPsQYK5WOJpiErJMJIMXZ8InWe4LZsY+VvoAZa05|DaxJTvkSYsY/UfQRfNkgUPQIZsE8U/VhYA==|DK1IT/hcYZ06RP0Facs2RucYdNI3WvoAas00WpIG|C6pPSP8UZMA5V/sed9QvS/IKY8I5U/8HBs4=|CqtOSf4VZcM7UegUfdwtXfkGbs0xVfVhYA==|CalMS/wXZ6lRP5xiErJILYAUZMEkWu0IZcY6UPALbqYy|CKlMS/xGbacDMMFXYpkkHfpWeZhzA6deO5ZzGrZKI5piCKhWPZtqa6M=|F7ZTVOMIeNsmQvsHbsk5Se0SeNwsROVxcA==|FrZTVOMIeLZKJoVgCalSOoN+Gr9YMYhwFbJXOJh9ErcjU/YTbZArEuEVIcB9UeEbBrZNV+ccBuBMcZY8CLMZLdF2S/BYaJQ/AvVdL9l/Tv4FH+lPfpszAe0Bb7xYR5cdc6JdRZRzR6YCMoJ4YoQlFvRuRqoPNoZ8ZJ85DPRaI4R7RbUbLtVMcYMvVvB9FLdcLIh3HbpHIoQQEQ==|FbFUO4J8DKhVOIF7ErBVMJB1GLtBMZ14FbdSOYB/D69KIptjDbRPP5t+G9M=";
		psb.append(inputhidden);
		psb.append("&");
		//psb.append("company_pagenav_token=");
		psb.append("token=");
		String comtoken = "1266927d07fded5df127afdb20c7066b1356331132121";
		psb.append(comtoken);
		psb.append("&");
		//psb.append("jumpto="+index);
		psb.append("beginPage="+index);
		//System.out.println(psb.toString());
		
		OutputStream os = conn.getOutputStream();       
		os.write(psb.toString().getBytes("utf-8"));       
		os.close();  

		StringBuffer sb = new StringBuffer();
		reader = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}

		return sb.toString();
	}
	
	public String getComp1(int index)throws Exception
	{
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String coo = "cna=ScmYBWPzDkECAcPA8XLK/66h; ali_apache_id=114.243.156.43.17991657150916.7; ali_beacon_id=113.45.123.160.1355140642804.2; ali_apache_track=\"c_ms=1|c_mt=3|c_mid=enhesn|c_lid=enhesn\"; alisw=swIs1200%3D1%7C; _cn_slid_=NJ224G8Gw%2F; h_keys=PP; alicnweb=lastlogonid%3Denhesn%7Ctouch_tb_at%3D1356182380794; track_cookie=n&null; JSESSIONID=WZ966391-WBHBYTEDKFONB0VVRQ182-SLIZV1BH-VWUA; _tmp_ck_0=fnMwTlXNzAfcTzB3pcukP6UmNivHiqUeaR0%2B7etAZM%2BDCRVkor3JJQJDrGrWx0cLMgWt%2FOZ2NB2efpQGlOMMUE2KnfPYNIk%2FuYHk82XoZOzCIjK0D4qqKTUAd9XXW7XXJDdchNalH8KUZIoZ4Yc4H2tFX0N93XzDIh45ODiO61I5UkBhhOlljm%2BjlbpkNkvmTUTNYWd0dfHahk5bMbjkH3i3u04uPgoIDh6b1ge1%2BsPQlwGwoKydUAZx2803PHvDzsBdp%2BrpJT7NGtW%2FOne6GRDNJ0eRH1opzVCdeRv%2FcqtcHAYnVYAdtJ7qDyik1XSEwjSlhsiojPkonAHnUCP%2Bw6caBUZzHYgyTJPDm4QSu4XgKjtXX2g6e0ItYbLuzQduK%2Fs5rmJuODpZsFXe9p6anQ%3D%3D; _tmp_hny_0=\"kFRcK6prBN8Qi7eFzZgJGMY84HFyUalqItI7mPgtQghG2KFK3dK5%2FjJh%2BP2Z72RxDAuePPVT%2F3sk5W4xx4TX0%2BwppGrzCTLx2Ljb9s5Lqt0qto4WC0MGUMxa29KIfn7QTE1E%2F2OBD4pJfwyzSMMJTs5hh9ZSmh8oshCdLJxk%2FzLzhn2NI6Ifh4HxBpoAcd7LMN3VhKG6tcJZyCAZ3eoZYWwhQ36p8guKHs4CRvh0b16RW2P599FJF1nEmAO2PUPbs82zL9KtjysR9l5SqjIh6vXXNtgTwEi9DUaE5WLH6cCn2VaYBCZHQeeguYSoyyijKDZFi6HNQ4ObmCgi295Cgw%3D%3D\"; ali_apache_sid=113.45.174.4.47362949627978.4|1356251427; aliBeacon_bcookie=; ad_prefer=\"2012/12/23 16:04:24\"; ali_ab=113.45.123.160.1355140643566.8";
		//String wz = "http://search.china.alibaba.com/company/company_search.htm?showStyle=noimg&keywords=%CE%C2%D6%DD&pageSize=30&n=y&beginPage=";
		//String wz = "http://search.china.alibaba.com/company/-CEC2D6DD.html?showStyle=noimg&amp;offset=3&amp;beginPage="+index;
		//URL url = new URL("http://search.china.alibaba.com/company/company_search.htm?showStyle=noimg&keywords=PP&pageSize=300&n=y&beginPage=" + index + "&event=222504565");
		URL url = new URL("http://search.china.alibaba.com/company/company_search.htm?showStyle=noimg&keywords=%CE%C2%D6%DD&pageSize=300&n=y&beginPage=" + index + "&event=222504565");
		//URL url = new URL(wz + index);
		//System.out.println("url = " + url);
		conn = (HttpURLConnection)url.openConnection();
		conn.setDoOutput(true);// 打开写入属性  
		//String cookie = "cna=ScmYBWPzDkECAcPA8XLK/66h; ali_apache_id=114.243.156.43.17991657150916.7; ali_beacon_id=113.45.123.160.1355140642804.2; JSESSIONID=7Z966F71-7EEBG7HNCF97M6Z4FC9P1-MVVOE0BH-J2102; _tmp_hny_0=\"kFRcK6prBN8WZQg0TgFr4%2BeiaUBXaG7oWdcxMpqLwb8nkpa6I%2BZDHPzXcIO9eRy8K2Xu93hJyJHwVWfReHcDGKuTywtOc8rTWHJIWktFgJ7onVeEaURNbsbOqsh5TOMhc%2BASQm26R9s9thus4WI5DUugnXATSQrH11klwSBovVzNrbNlKC1oyZf4DyWgFEqQmhv6attdQT8EQqY019OgydN6AbKlq%2BN9GL%2BVI%2Bp10z4JqB0pI9mdVaVT4MwkVzwpKFm8W9KP6PTqtpATU237ouxybyVV%2B4qE7gVM5hPq4Kx0xZG9md57vYHvpQJKXSNR19FVa%2BQirCv1oq3EDruHpA%3D%3D\"; ali_apache_sid=113.45.174.4.83892160075807.6|1356161875; ali_apache_track=\"c_ms=1|c_mt=3|c_mid=enhesn|c_lid=enhesn\"; ali_apache_tracktmp=\"c_w_signed=Y\"; _t_ck_0=\"AhAPwLGenDsU2obBvfytDuYzutyC9CTAp5Hmhx2OJP7MTMF4pJr%2BQGvY2Sj5VEZdJhk41y7heY%2BNCwzvtXGyyDcIG0io%2B7MCIthZxYm%2F9wTorK8Fcb2cGXpnIk5ckWwLu1aQjdtIMd0hczNbKG32curEBYgJ3FhwCbPn8GNUamnoUVFI9D84alIMuGDzFPemxjitKytCkdwcaoHLxvZzN6DV81oHmuVPPJCGE%2BeuknfEOKwTrkO3ln18883LB7CGqlD0YPHm4XtKyIa2bN18QFqt5gOTFPhXzrvmfgkN%2FhL36bN8FFmnfx%2BOudAu%2BJz9YqsWsoV32IIboA9l4aTG%2Fw%3D%3D\"; _t_ck_sum=AhAPwLGenDsU2ob; track_cookie=n&null; ali_ab=113.45.123.160.1355140643566.8; alisw=swIs1200%3D1%7C; _ITBU_IS_FIRST_VISITED_=ruixuansj999%3Apm04ojccpn; __cn_logon__=true; __cn_logon_id__=enhesn; last_mid=enhesn; __last_loginid__=enhesn; _cn_slid_=NJ224G8Gw%2F; cn_tmp=\"Z28mC+GqtZ2ilGw+fki6SN8ZPP8OqVxe+zjQkoLxkMJ6NPfGC1tj6rqaKZv5dLDSAJkBDQGCve+tW3NzEFXaOPndTuAcRIYisoYvDFLpti6TRXsajDjuCiDms9tkF0Iyg5SPZhqTONNPU3AvO4Ew2bmfd7jpwg4p3hWqFuxE80EJxbPYCEVKRHLq7nhda4VK33L3uBQCt6x/1vT6+zPEg3+RnotO12HO\"; _csrf_token=1356166680679; _tmp_ck_0=\"nkKUi3QG63XD%2FeiGUfS%2Ffd35QHd4DzqYMugELn8vJKMMJ5quDEYhMJB0XEZrh6yxPUHagGCnJFlz8APc97tGybeuRk22ICO5UMLVUly1l%2FfEAgEAfvOz50%2Fz3DqvfBtUHwPdqM9JIOqo0abix9H99A3NxkRQ2lWD8krhOCAmCJ6C5TjrZqZVJYmFLpFHLWvItuQzIGGIw5fKRiO6T9PDiBD8q%2Bjs8KXw4aCQd9Q5R2E3KRyqkbvjg7DSXcR4kG%2FZwOam%2Bev%2F1ev6LASckjLgAJ1WONVHEblCNIHfv7uQ%2FaStqqHDGUfWYXLwJDncUu6uTuRqZq%2BdbNRmN2N%2FxkFVnC8177uB4V527BFRZrWFec6rfJdIOUmN29GXLHB2FRHA1GzIPmrPYr9J7YYncSGrhzaFklSL21fSDWyUJmAcFpVqVR6DwFEv6mKlhwg4vEjxC8Gr4Sxt6G4cTxTZOsBacQ3rqR0MbSzcc04eiegkTY1qQjO7YnAgUxBnLANmIcfmulo4B%2BeytHwK8ATDXauJTg743n8myWKTH%2FGQrOt0mAfhJfpwJcG0OOgNcWrwyRJ%2FT2eNp%2F9tzhk%3D\"; alicnweb=lastlogonid%3Denhesn%7Ctouch_tb_at%3D1356174280350";
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_0) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.97 Safari/537.11");
		conn.setRequestProperty("Host", "search.china.alibaba.com");
		conn.setRequestProperty("Origin", "http://search.china.alibaba.com");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("Cache-Control", "max-age=0");
		conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		conn.setRequestProperty("Connection", "keep-alive");
		
		//String coo = "ali_beacon_id=220.113.8.242.1356053894562.5; JSESSIONID=7Z966G81-47HB6ZU6H3UXR478NRHU1-6J6HNYAH-KRB4; cna=doXLB7ayuiMCAYIi/3dIb7rq; ali_apache_id=220.113.8.242.26400753908851.5; ali_apache_sid=220.113.8.242.26400753908851.5|1356055708; __last_loginid__=%E8%91%AC%E7%88%B1love88; _tmp_hny_0=\"AhAPwLGenDsU2obBvfytDhX2vdqOc6ETwZde8jyqyAuJ90Zxhbzf6bvs8WZsWhvX7CZvUxR8HGJJFl6T9GipaRqwAOHjArgl323vfO6jMvI3rR2FluhZEf4B9cbae4bk873BcuqUPY7x6zh%2BbMAflWFOwExVVj0cdcPj%2BsR0%2FHAMbjVSAU3IbhD1VyrqxMnYZsthPUGAEtRkrvkvB8amdj3IONt%2BlRm96tweBVe%2B4FHkv5ZJS2nW8iuzY%2B7OhhE%2FhLoh4fDa%2FXUz9AwK2xlRp6%2B5CrGkTg2nr3f1J664V8jdDicBqGH0%2Fn9nReWonXLrY1Feje5qH7BrngNsxBRDrg%3D%3D\"; __cn_logon__=false; track_cookie=n&null; _csrf_token=1356316482849; _tmp_ck_0=ZriQB1UOr1AW%2FJNpn4AxCNniPkxQ64Siwb%2BT2tRZjiiEXDV5yQyauo2PlI7UxljUpxHBM2abj6VHCzYpw8hiW1d4caqU8mUn3c7uJelIxfttYFWMWn3y81QYULRq%2F3IAmy50gYaKpiIZdoRfqZjxSt4JR1dZqBHSTKbs57XwmfzMAI8kYgVviRfaohpdsgZewKDkRAQu%2FFgCrs8x%2B6gqirN336r9VCNxr28HER9npnypwzMcBJEhrZFfbVqH3%2BXZ8bJisfFeeY4sg5cOO8hqAwQlwIL2Kwix1OsqsH9BcJ8Vo1FF6chkQoWefhEJcfUXHHiOiEwwfJZ9xl6jGZD31E4EwNMHHDG6l5tzKKBF%2FuTDqjZByNmHXMNlj48g7PBHW7vSSdXoIw%2FoCtNyVyIApoOyegqtwa%2FpikNKUP5dU7Zwa7B%2FnPjTOC3RFlXMyhXU9ZRG9S8o%2BJE%3D; h_keys=\"%u6e29%u5dde#PP#%u516c%u53f8\"; alicnweb=lastlogonid%3D%25E8%2591%25AC%25E7%2588%25B1love88%7Ctouch_tb_at%3D1356327772137; alisw=swIs1200%3D1%7C; _t_ck_0=\"GDnm0JftrAAlFLwK1AauVsDbIzrv6jnF6llvr22fUK3UXvtLNmT3RbfcJqbJIFal7sIy0cD2O%2BXfCkWhRvZBC036kRGhTbC6K87BkPMipbLyqYV9tMSIKTVYapUloASZ%2F7Oj893eE2DaMuvSmxMG4s9YBOBIYns0kmZVg3g33aquIHOWi99ABXs7phnMcqkcxFPJfOOXybsz%2FzKTCxnnfZYQtiBsifZ6GTZz3ln618paKB13OgQ8eDCVzpDxVozBsh6vJwlu%2FeoMkGosyXpJTxzxDxgb18%2BW79%2F%2BKt224QglpisIr4gl0PXNANEbt4D5mjZsp6DWAGvHO75tT1MsDA%3D%3D\"; _t_ck_sum=GDnm0JftrAAlFLw; aliBeacon_bcookie=; ad_prefer=\"2012/12/24 15:11:22\"; ali_ab=220.113.8.242.1356314736989.5";
		conn.setRequestProperty("Cookie", coo);
		conn.setRequestProperty("Referer", "http://search.china.alibaba.com/company/company_search.htm?showStyle=noimg&keywords=%CE%C2%D6%DD&pageSize=30&n=y");
		//String fd = "http://search.china.alibaba.com/company/company_search.htm?showStyle=noimg&keywords=PP&pageSize=30&n=y&beginPage=375";
		String fd = "http://search.china.alibaba.com/company/company_search.htm?keywords=%BA%BC%D6%DD&pageSize=30&n=y&showStyle=noimg&beginPage=376&event=226348323";
		conn.setRequestProperty("Referer",fd);
		//conn.connect();
		
		
		StringBuffer psb = new StringBuffer();
		//psb.append("UA_InputId=");
		psb.append("ua=");
		//String inputhidden = "083n%2BuE8Ib%2Bh%2FCD%2B436g%2FqJ%2F18%3D%7CnOmGGH37UOVX508qRTBGKUzceN67G7s%3D%7CneuEGm8adQVxHmofZhJ9BHIKch1pHGcUewBzAG8bbhRtAngCedl5%7Cmu6b9JHpTuie6006TjlK7ZUzSOxIMZjkQuWc4JXjn%2BdDNk81TjpMNE06STFHMEc8Rz1Y%2BA%3D%3D%7Cm%2B%2BT%2FGIXeAx6DWIXeB2oAGXFZQ%3D%3D%7CmOya9WsOnjqc%2F237mOyf7pXkl%2FKd%2BGjsagl9CWYecQdzHGQBoQE%3D%7Cme2e8ZQKfAV5An4Rjuuf6ZHom%2BOV6ZHimu%2BVKlkoWyNQLFciViBTL1otWC5SKFAot9K9yWkMrA%3D%3D%7CluGOEHUQfwd7CGcebRtiDXkKcAR93X0%3D%7Cl%2B%2BAHnvHf9N7wnXRad5swGcXZAFu8IT3jOOa6Z3hQS5bNFE0Wy9XK1gkhCQ%3D%7ClOOMEncSfQt9CmUcbxVmCX0GcQZ11XU%3D%7CleKNE3YTfAdyC2QdaRpgD3oOewN21nY%3D%7CkuWKFHEUewF7B2gRYhRoB3ELcw901HQ%3D%7Ck%2BeI%2FpHrhPCH85zqkuiH84r9ku6Z9orlkeuE%2FpHoh%2F9f";
		String inputhidden = "124fCF5ZCRyNmsiZXEkYnYtbh8%3D%7CfyF5Dl9%2FZX5heDt%2BJjJtNTsFBxZdcFk%3D%7CfiF5Dk8eQBlFABRFAApSEQtNAkEaUhYcQwcRURQKThtFG1wQa1E%3D%7CeSRiYjNjJHg%2FLz49L205emMhIDdvKGV1d2AnKGR9Ojx7K2woPmgvPGYmM3MiYTx%2BOSdiSw%3D%3D%7CeCVoaEARTxdfGhBAGBxNXBNbWlpWBC1W%7CeyZiYjMLShVcEA9EbGg1cmsufTpgKWh%2BLGp7ay8tbjh6KmIkM2cjPGIiPHwmYz96UlYdWCNtRA%3D%3D%7CeidgYEgJNj4teRAkcGc7YXUoeytuPAViRhIFWAAZRhZVEFUDeEI%3D%7CdS11AlMSTBRVFgBLCxtbGgJAGl5qRw%3D%3D%7CdC11AlMSTH4%2FeWk3b3ggCQlKBVUeQgMQRwAWJw4%3D%7Cdy93AFEQThBYGBJBAxZOFg9KHl0MIQg%3D%7Cdi93AFFYX1tbWxJXD3ImZnkmcTdvLQQOWQEFSxMKQRJQCSQN%7CcSpyBVRdWl5eXhdSCh1dHAhLGV5qRw%3D%3D%7CcCpyBVRdWl5eXhdSChlEHABfCUMSVRVuVA%3D%3D%7CcylxBldeWV1dXRRRCRpDGwdYDkUdVBliWA%3D%3D%7CcihwB1ZfWFxcXBVQCBtDGwdYDkUaXhtgWg%3D%3D%7CbTdvGElAR0NDQwpPFwBfBxtEEVMORgR%2FRQ%3D%3D%7CbDFpdil9I3s%2FenAkZXQ0eWA%2FbSlzL2JoPn13LHRpL2AhcCxqeDN1YCBmeCd2NQE%3D";
		psb.append(inputhidden);
		psb.append("&");
		//psb.append("company_pagenav_token=");
		psb.append("token=");
		//String comtoken = "5dc32c4140d5c8aa6f9cd692395a26781356405344887";
		String comtoken = "3557d7dfd8751be1796ecac854e597001356493214238";
		psb.append(comtoken);
		psb.append("&");
		//psb.append("jumpto="+index);
		psb.append("beginPage="+index);
		//System.out.println(psb.toString());
		
		OutputStream os = conn.getOutputStream();       
		os.write(psb.toString().getBytes("utf-8"));       
		os.close();  
		
		
		
		StringBuffer sb = new StringBuffer();
		reader = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}

		return sb.toString();
	}
	
	public String getComp(int index)throws Exception
	{
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		//String coo = "cna=ScmYBWPzDkECAcPA8XLK/66h; ali_apache_id=114.243.156.43.17991657150916.7; ali_beacon_id=113.45.123.160.1355140642804.2; JSESSIONID=7Z966F71-7EEBG7HNCF97M6Z4FC9P1-MVVOE0BH-J2102; _tmp_hny_0=\"kFRcK6prBN8WZQg0TgFr4%2BeiaUBXaG7oWdcxMpqLwb8nkpa6I%2BZDHPzXcIO9eRy8K2Xu93hJyJHwVWfReHcDGKuTywtOc8rTWHJIWktFgJ7onVeEaURNbsbOqsh5TOMhc%2BASQm26R9s9thus4WI5DUugnXATSQrH11klwSBovVzNrbNlKC1oyZf4DyWgFEqQmhv6attdQT8EQqY019OgydN6AbKlq%2BN9GL%2BVI%2Bp10z4JqB0pI9mdVaVT4MwkVzwpKFm8W9KP6PTqtpATU237ouxybyVV%2B4qE7gVM5hPq4Kx0xZG9md57vYHvpQJKXSNR19FVa%2BQirCv1oq3EDruHpA%3D%3D\"; ali_apache_sid=113.45.174.4.83892160075807.6|1356161875; ali_apache_track=\"c_ms=1|c_mt=3|c_mid=enhesn|c_lid=enhesn\"; ali_apache_tracktmp=\"c_w_signed=Y\"; _t_ck_0=\"AhAPwLGenDsU2obBvfytDuYzutyC9CTAp5Hmhx2OJP7MTMF4pJr%2BQGvY2Sj5VEZdJhk41y7heY%2BNCwzvtXGyyDcIG0io%2B7MCIthZxYm%2F9wTorK8Fcb2cGXpnIk5ckWwLu1aQjdtIMd0hczNbKG32curEBYgJ3FhwCbPn8GNUamnoUVFI9D84alIMuGDzFPemxjitKytCkdwcaoHLxvZzN6DV81oHmuVPPJCGE%2BeuknfEOKwTrkO3ln18883LB7CGqlD0YPHm4XtKyIa2bN18QFqt5gOTFPhXzrvmfgkN%2FhL36bN8FFmnfx%2BOudAu%2BJz9YqsWsoV32IIboA9l4aTG%2Fw%3D%3D\"; _t_ck_sum=AhAPwLGenDsU2ob; track_cookie=n&null; alisw=swIs1200%3D1%7C; _ITBU_IS_FIRST_VISITED_=ruixuansj999%3Apm04ojccpn; last_mid=enhesn; _cn_slid_=NJ224G8Gw%2F; h_keys=PP; _csrf_token=1356166680679; _tmp_ck_0=nkKUi3QG63XD%2FeiGUfS%2Ffd35QHd4DzqYFQJ9smkUEXv%2BxpiBqKhyCVj7Ig6tZpVNZXFMEk407ZSCfOOL3xB%2Fuu%2F4iv96rGRjVrIMt0alwY3Nt%2B0EuauIPyBgDF3o1gw%2Fc7dl43KVODHu1ZDIQ%2FDBuAzvMWsdU0lp5zoTloLKYCfE8i5L0YVY2QCmf7c2soHQm8wECcE8N7W1vG8N3%2BChbL9WW%2F35iu%2BO04%2BEVSPiR518n2RAXu649OwWBbjfAv13it4WiqGsY%2B%2Bz6qRwuMsmsea1YTTNrGOoXPvGkKCHNnj%2B3Y1JJptijRQquVfla%2F1Q11N5gpwgqAzXiTYC%2FmOv%2FzG9oZynomRtlbAN%2FEZ1IZKJS4vMil7iNBlISRaKnji%2BEUPgFytM5WQbvhCHFLdWDSokxr7vONw6%2B1Xf847IIu7TfMfmFL8hNhrasVxEakjqszBsC5dDpxv9zQm7Z4Gadr8iVn9SDWAi6ewGCEEIU9gNp5Z6dcmiEZPQXAHFqEaDnf6ZHLqS3%2FEQv09gGERia4lChB1cd6RikNSCHa%2BjM1qmfycAc64v6sspm3P14DWLRhUSfzXO9dY%3D; __cn_logon_id__=enhesn; __last_loginid__=enhesn; __cn_logon__=true; alicnweb=lastlogonid%3Denhesn%7Ctouch_tb_at%3D1356182380794; aliBeacon_bcookie=; ad_prefer=\"2012/12/22 21:28:30\"; ali_ab=113.45.123.160.1355140643566.8";
		String coo = "cna=ScmYBWPzDkECAcPA8XLK/66h; ali_apache_id=114.243.156.43.17991657150916.7; ali_beacon_id=113.45.123.160.1355140642804.2; ali_apache_track=\"c_ms=1|c_mt=3|c_mid=enhesn|c_lid=enhesn\"; alisw=swIs1200%3D1%7C; _cn_slid_=NJ224G8Gw%2F; h_keys=PP; alicnweb=lastlogonid%3Denhesn%7Ctouch_tb_at%3D1356182380794; track_cookie=n&null; JSESSIONID=WZ966391-WBHBYTEDKFONB0VVRQ182-SLIZV1BH-VWUA; _tmp_ck_0=fnMwTlXNzAfcTzB3pcukP6UmNivHiqUeaR0%2B7etAZM%2BDCRVkor3JJQJDrGrWx0cLMgWt%2FOZ2NB2efpQGlOMMUE2KnfPYNIk%2FuYHk82XoZOzCIjK0D4qqKTUAd9XXW7XXJDdchNalH8KUZIoZ4Yc4H2tFX0N93XzDIh45ODiO61I5UkBhhOlljm%2BjlbpkNkvmTUTNYWd0dfHahk5bMbjkH3i3u04uPgoIDh6b1ge1%2BsPQlwGwoKydUAZx2803PHvDzsBdp%2BrpJT7NGtW%2FOne6GRDNJ0eRH1opzVCdeRv%2FcqtcHAYnVYAdtJ7qDyik1XSEwjSlhsiojPkonAHnUCP%2Bw6caBUZzHYgyTJPDm4QSu4XgKjtXX2g6e0ItYbLuzQduK%2Fs5rmJuODpZsFXe9p6anQ%3D%3D; _tmp_hny_0=\"kFRcK6prBN8Qi7eFzZgJGMY84HFyUalqItI7mPgtQghG2KFK3dK5%2FjJh%2BP2Z72RxDAuePPVT%2F3sk5W4xx4TX0%2BwppGrzCTLx2Ljb9s5Lqt0qto4WC0MGUMxa29KIfn7QTE1E%2F2OBD4pJfwyzSMMJTs5hh9ZSmh8oshCdLJxk%2FzLzhn2NI6Ifh4HxBpoAcd7LMN3VhKG6tcJZyCAZ3eoZYWwhQ36p8guKHs4CRvh0b16RW2P599FJF1nEmAO2PUPbs82zL9KtjysR9l5SqjIh6vXXNtgTwEi9DUaE5WLH6cCn2VaYBCZHQeeguYSoyyijKDZFi6HNQ4ObmCgi295Cgw%3D%3D\"; ali_apache_sid=113.45.174.4.47362949627978.4|1356251427; aliBeacon_bcookie=; ad_prefer=\"2012/12/23 16:04:24\"; ali_ab=113.45.123.160.1355140643566.8";
		//String wz = "http://search.china.alibaba.com/company/company_search.htm?showStyle=noimg&keywords=%CE%C2%D6%DD&pageSize=30&n=y&beginPage=";
		//String wz = "http://search.china.alibaba.com/company/-CEC2D6DD.html?showStyle=noimg&amp;offset=3&amp;beginPage="+index;
		URL url = new URL("http://search.china.alibaba.com/company/company_search.htm?showStyle=noimg&keywords=PP&pageSize=300&n=y&beginPage=" + index + "&event=222504565");
		//URL url = new URL(wz + index);
		//System.out.println("url = " + url);
		conn = (HttpURLConnection)url.openConnection();
		//String cookie = "cna=ScmYBWPzDkECAcPA8XLK/66h; ali_apache_id=114.243.156.43.17991657150916.7; ali_beacon_id=113.45.123.160.1355140642804.2; JSESSIONID=7Z966F71-7EEBG7HNCF97M6Z4FC9P1-MVVOE0BH-J2102; _tmp_hny_0=\"kFRcK6prBN8WZQg0TgFr4%2BeiaUBXaG7oWdcxMpqLwb8nkpa6I%2BZDHPzXcIO9eRy8K2Xu93hJyJHwVWfReHcDGKuTywtOc8rTWHJIWktFgJ7onVeEaURNbsbOqsh5TOMhc%2BASQm26R9s9thus4WI5DUugnXATSQrH11klwSBovVzNrbNlKC1oyZf4DyWgFEqQmhv6attdQT8EQqY019OgydN6AbKlq%2BN9GL%2BVI%2Bp10z4JqB0pI9mdVaVT4MwkVzwpKFm8W9KP6PTqtpATU237ouxybyVV%2B4qE7gVM5hPq4Kx0xZG9md57vYHvpQJKXSNR19FVa%2BQirCv1oq3EDruHpA%3D%3D\"; ali_apache_sid=113.45.174.4.83892160075807.6|1356161875; ali_apache_track=\"c_ms=1|c_mt=3|c_mid=enhesn|c_lid=enhesn\"; ali_apache_tracktmp=\"c_w_signed=Y\"; _t_ck_0=\"AhAPwLGenDsU2obBvfytDuYzutyC9CTAp5Hmhx2OJP7MTMF4pJr%2BQGvY2Sj5VEZdJhk41y7heY%2BNCwzvtXGyyDcIG0io%2B7MCIthZxYm%2F9wTorK8Fcb2cGXpnIk5ckWwLu1aQjdtIMd0hczNbKG32curEBYgJ3FhwCbPn8GNUamnoUVFI9D84alIMuGDzFPemxjitKytCkdwcaoHLxvZzN6DV81oHmuVPPJCGE%2BeuknfEOKwTrkO3ln18883LB7CGqlD0YPHm4XtKyIa2bN18QFqt5gOTFPhXzrvmfgkN%2FhL36bN8FFmnfx%2BOudAu%2BJz9YqsWsoV32IIboA9l4aTG%2Fw%3D%3D\"; _t_ck_sum=AhAPwLGenDsU2ob; track_cookie=n&null; ali_ab=113.45.123.160.1355140643566.8; alisw=swIs1200%3D1%7C; _ITBU_IS_FIRST_VISITED_=ruixuansj999%3Apm04ojccpn; __cn_logon__=true; __cn_logon_id__=enhesn; last_mid=enhesn; __last_loginid__=enhesn; _cn_slid_=NJ224G8Gw%2F; cn_tmp=\"Z28mC+GqtZ2ilGw+fki6SN8ZPP8OqVxe+zjQkoLxkMJ6NPfGC1tj6rqaKZv5dLDSAJkBDQGCve+tW3NzEFXaOPndTuAcRIYisoYvDFLpti6TRXsajDjuCiDms9tkF0Iyg5SPZhqTONNPU3AvO4Ew2bmfd7jpwg4p3hWqFuxE80EJxbPYCEVKRHLq7nhda4VK33L3uBQCt6x/1vT6+zPEg3+RnotO12HO\"; _csrf_token=1356166680679; _tmp_ck_0=\"nkKUi3QG63XD%2FeiGUfS%2Ffd35QHd4DzqYMugELn8vJKMMJ5quDEYhMJB0XEZrh6yxPUHagGCnJFlz8APc97tGybeuRk22ICO5UMLVUly1l%2FfEAgEAfvOz50%2Fz3DqvfBtUHwPdqM9JIOqo0abix9H99A3NxkRQ2lWD8krhOCAmCJ6C5TjrZqZVJYmFLpFHLWvItuQzIGGIw5fKRiO6T9PDiBD8q%2Bjs8KXw4aCQd9Q5R2E3KRyqkbvjg7DSXcR4kG%2FZwOam%2Bev%2F1ev6LASckjLgAJ1WONVHEblCNIHfv7uQ%2FaStqqHDGUfWYXLwJDncUu6uTuRqZq%2BdbNRmN2N%2FxkFVnC8177uB4V527BFRZrWFec6rfJdIOUmN29GXLHB2FRHA1GzIPmrPYr9J7YYncSGrhzaFklSL21fSDWyUJmAcFpVqVR6DwFEv6mKlhwg4vEjxC8Gr4Sxt6G4cTxTZOsBacQ3rqR0MbSzcc04eiegkTY1qQjO7YnAgUxBnLANmIcfmulo4B%2BeytHwK8ATDXauJTg743n8myWKTH%2FGQrOt0mAfhJfpwJcG0OOgNcWrwyRJ%2FT2eNp%2F9tzhk%3D\"; alicnweb=lastlogonid%3Denhesn%7Ctouch_tb_at%3D1356174280350";
		conn.setRequestProperty("Cookie",coo);
		//String fd = "http://search.china.alibaba.com/company/company_search.htm?showStyle=noimg&keywords=PP&pageSize=30&n=y&beginPage=1";
		//conn.setRequestProperty("Referer",fd);
		conn.connect();
		StringBuffer sb = new StringBuffer();
		reader = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}

		return sb.toString();
	}
	
	public List getComList(String html)throws Exception
	{
		ArrayList clist = new ArrayList();
		Document doc = Jsoup.parse(html);
		for(int loop = 0;loop < 34;loop++)
		{
			//li#offer
			Elements eles = doc.select("li#offer" + loop);
			String ele = eles.attr("companyId");
			//System.out.println(ele);
			if(!ele.trim().equals("") && ele.trim() != "")
			{
				clist.add(ele);
			}
		}
		return clist;
	}
	
	
	public void writephone(int index,List phone) 
	{
		String s = new String(); 
		String s1 = new String(); 
		try { 
			File f = new File("/Users/lumin/Documents/alibaba/data/hz_phone"+"_"+index+"_"+phone.size()+".txt"); 
			if (f.exists()) 
			{ 
				System.out.println("文件存在"); 
			} 
			else 
			{ 
				System.out.println("文件不存在，正在创建..."); 
				if (f.createNewFile()) 
				{ 
					System.out.println("创建成功！"); 
				} else 
				{ 
					System.out.println("创建失败！"); 
				} 
			} 
			BufferedReader input = new BufferedReader(new FileReader(f)); 
			while ((s = input.readLine()) != null) 
			{ 
				s1 += s + "\n"; 
			} 
			//System.out.println("文件内容：" + s1); 
			input.close(); 
			for(int loop = 0;loop<phone.size();loop++)
			{
				String p = phone.get(loop).toString();
				s1 = s1 + p + "\n"; 
			}
			BufferedWriter utput = new BufferedWriter(new FileWriter(f)); 
			utput.write(s1); 
			utput.close(); 
		} catch (Exception e) 
		{ 
			e.printStackTrace(); 
		}
	}
	
	public static void main(String args[])throws Exception
	{

		read r = new read();

		/*获取一个公司的联系方式
		String cdetail = r.getHtml("wutiesheng86");
		System.out.println(cdetail);
		获取一个公司的联系方式结束*/
		
		
		/* 获取一页数据
		//获取公司搜索结果页
		String html = r.getComp1(234);
		System.out.println(html);
		//根据HTML解析公司列表ID
		List clist = r.getComList(html);
		for(int loop = 0;loop < clist.size();loop++)
		{
			String c = clist.get(loop).toString();
			//获取联系方式页面
			String cdetail = r.getHtml(c);
			//System.out.println(cdetail);
			String phone = r.getPhone(cdetail);
			if(phone !=null && phone.length() > 0)
			{
				System.out.println("开始进行第" + loop + "个公司电话搜索，结果：" + phone);
			}
			//System.out.println(phone);
		}
		//获取一页数据结束*/
		
		/*获取老系统数据
		String html = r.getHtml1("runchengsm");
		r.getPhone1(html);
		*/
		
		/*获取新系统数据
		String html = r.getHtml("renhesujiao");
		r.getPhone(html);
		//*/
		
		
		//获取整站搜索数据
		int m = 0;

		for(int j = 11;j<20;j++){
			List phonelist = new ArrayList();
			for(int i=1;i<26;i++)
			{
				//System.out.println(j*50+i);
				int pagecount = j*25+i;
				List clist = null;
				String html = r.getComp1(pagecount);
				clist = r.getComList(html);
				System.out.println("开始第"+pagecount+"页公司信息搜索");
				for(int loop = 0;loop < clist.size();loop++)
				{
					String c = clist.get(loop).toString();
					String cdetail = r.getHtml1(c);
					String phone = r.getPhone1(cdetail);
					//String cdetail = r.getHtml(c);
					//String phone = r.getPhone(cdetail);
					if(phone !=null && phone.length() > 0)
					{
						m++;
						System.out.println("开始进行第" + m + "个公司("+c+")电话搜索，结果：" + phone);
						phonelist.add(phone);
					}
					//System.out.println(phone);
				}
				
			}
			r.writephone(j+1,phonelist);
			System.out.println("-----------完成第" + (j+1) + "页公司电话记录-----------");	
		}
		//*/
		
		/*
		for(int j = 0; j < 20; j++)
		{
			for(int i=1;i<26;i++)
			{
				//System.out.println(j*50+i);
				int pagecount = j*25 + i;
				System.out.println("开始第"+pagecount+"页公司信息搜索");
				
				}

			System.out.println("完成第" + (j+1) + "页公司电话记录");		
		}
		*/
	}
}
