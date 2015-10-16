package com.jisheng.update;

import java.util.HashMap;
import java.util.Map;

public class UpdateService {
	public Map<String,String> queryData(String order){
		Map<String,String> map = new HashMap<String,String>();
		String qry_jct = "select po_no,mobile from primary_order where po_no='"+order+"'";
		map = DataUtil.qry(qry_jct, DataUtil.CONN_URL_JCT);
		if(map.size()==0){
			return null;
		}
		String qry_crm = "select mobile,business_name,balance from crm_customer c where c.mobile = '"+map.get("mobile")+"'";
		map = DataUtil.qry(qry_crm, DataUtil.CONN_URL_CRM);
		if(map.size()==0){
			return null;
		}
		map.put("order", order);
		return map;
	}
	
	public boolean updateDate(String mobile,String balance){
		String update_crm = "update crm_customer c set c.balance = " + balance + " where c.mobile='" + mobile +"'";
		boolean result = false;
		result = DataUtil.exec(update_crm, DataUtil.CONN_URL_CRM);
		return result;
	}
}
