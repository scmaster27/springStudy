package com.example.springStudy.controller.geoip;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.City;
import com.maxmind.geoip2.record.Country;
import com.maxmind.geoip2.record.Location;
import com.maxmind.geoip2.record.Postal;
import com.maxmind.geoip2.record.Subdivision;

@Controller
@RequestMapping("/geoip/*")
public class GeoipController {
	@RequestMapping(value = "/CurrentGeoInfo")
	@ResponseBody
	public Map<String, Object> CurrentGeoInfo() {
		String address = getPublicIPAddr();
		Map<String, Object> map = getGeoInfo(address);
		
		return map;
	}
	
	private String getPublicIPAddr() {
		StringBuilder ipAddress = new StringBuilder();
		
		try {
			URL url;
			url = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			ipAddress.append(in.readLine());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ipAddress.toString();
	}
	
	private Map<String, Object> getGeoInfo(String address) {
		ClassPathResource resource = new ClassPathResource("GeoLite2-City.mmdb");

		DatabaseReader reader;

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		try {
			reader = new DatabaseReader.Builder(resource.getFile()).build();

			InetAddress ipAddress = InetAddress.getByName(address);

			map.put("IP", ipAddress.getHostAddress());
			map.put("Hostname", ipAddress.getHostName());

			CityResponse response = reader.city(ipAddress);
			Country country = response.getCountry();

			map.put("Country", country.getName());
			map.put("Continent Code", response.getContinent().getCode());
			map.put("Continent Name", response.getContinent().getName());

			City city = response.getCity();
			map.put("City", city.getName());
			
			Location location = response.getLocation();
			map.put("Latitude", location.getLatitude());
			map.put("Longitude", location.getLongitude());
			map.put("TimeZone", location.getTimeZone());
			
			Postal postal = response.getPostal();
			map.put("Postal Code", postal.getCode());
			
			Subdivision subdivision = response.getMostSpecificSubdivision();
			map.put("Region", subdivision.getName() + "(" + subdivision.getIsoCode() + ")");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
}
