package com.interbanking.interbanking.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

public class SiteLocale {
	
	private static final Map<String, ZoneId> countryTimeZones = new HashMap<>();
	private static final Map<String, Locale> countryLocales = new HashMap<>();
	private static final Map<String, String> countrySites = new HashMap<>();


	private SiteLocale(){
       throw  new IllegalStateException("Utility class");
	}

	static {
		countryTimeZones.put("MX", ZoneId.of("America/Mexico_City"));
		countryTimeZones.put("VA", ZoneId.of("America/Mexico_City"));
		countryTimeZones.put("VE", ZoneId.of("America/Caracas"));
		countryTimeZones.put("PA", ZoneId.of("America/Panama"));
		countryTimeZones.put("PE", ZoneId.of("America/Lima"));
		countryTimeZones.put("UB", ZoneId.of("America/Lima"));
		countryTimeZones.put("BR", ZoneId.of("America/Sao_Paulo"));
		countryTimeZones.put("CM", ZoneId.of("America/Sao_Paulo"));
		countryTimeZones.put("CL", ZoneId.of("America/Santiago"));
		countryTimeZones.put("UY", ZoneId.of("America/Montevideo"));
		countryTimeZones.put("EC", ZoneId.of("America/Guayaquil"));
		countryTimeZones.put("AR", ZoneId.of("America/Argentina/Buenos_Aires"));
		
		countryLocales.put("MX", new Locale("es", "MX"));
		countryLocales.put("VA", new Locale("es", "VA"));
		countryLocales.put("VE", new Locale("es", "VE"));
		countryLocales.put("PA", new Locale("es", "PA"));
		countryLocales.put("PE", new Locale("es", "PE"));
		countryLocales.put("UB", new Locale("es", "PE"));
		countryLocales.put("BR", new Locale("pt"));
		countryLocales.put("CM", new Locale("pt"));
		countryLocales.put("CL", new Locale("es", "CL"));
		countryLocales.put("UY", new Locale("es", "UY"));
		countryLocales.put("EC", new Locale("es", "EC"));
		countryLocales.put("AR", new Locale("es", "AR"));
	}
	
	public static LocalDate now(String site) {
		return LocalDate.now(getZone(site));
	}

	public static LocalDate yesterday(String site) {
		return now(site).minusDays(1);
	}

	public static LocalDateTime nowDateTime(String site) {
		return LocalDateTime.now(getZone(site));
	}
	
	public static LocalDateTime atEndOfDay(String site, LocalDate localDate) {
		return LocalDateTime.of(localDate, LocalTime.MAX).atZone(getZone(site)).toLocalDateTime();
	}
	
	public static Locale locale(String site) {
		return countryLocales.get(StringUtils.substring(site, 2));
	}
	
	public static LocalDateTime of(String site, Date date) {
		return date.toInstant().atZone(getZone(site)).toLocalDateTime();
	}

	public static Date toDate(LocalDateTime dateTime, String site) {
		return Date.from(dateTime.atZone(getZone(site)).toInstant());
	}

	public static LocalDate asLocalDate(Date date, String site) {
		return date.toInstant().atZone(getZone(site)).toLocalDate();
	}

	public static LocalDateTime asLocalDateTime(Date date, String site) {
		return date.toInstant().atZone(getZone(site)).toLocalDateTime();
	}

	private static ZoneId getZone(String site) {
		return countryTimeZones.get(site);
	}
	
	public static LocalDate toLocalDateOrYesterday(Date from, String site) {
		if (from != null) {
			return asLocalDate(from, site);
		} else {
			return yesterday(site);
		}
	}
	public static String getSiteForCountry(String country) {
		return countrySites.get(country);
	}

	public static Calendar getLastMonth(Integer month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MONTH, -month);

		return cal;
	}
	
}
