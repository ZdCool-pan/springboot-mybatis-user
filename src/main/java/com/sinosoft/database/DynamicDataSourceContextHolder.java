package com.sinosoft.database;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断指定DataSrouce当前是否存在
 */
public class DynamicDataSourceContextHolder {
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	public static List<String> dataSourceIds = new ArrayList<>();

	public static void setDataSourceType(String dataSourceType) {
		contextHolder.set(dataSourceType);
	}

	public static String getDataSourceType() {
		return contextHolder.get();
	}

	public static void clearDataSourceType() {
		contextHolder.remove();
	}
	
	public static boolean containsDataSource(String dataSourceId){
		return dataSourceIds.contains(dataSourceId);
	}
}
