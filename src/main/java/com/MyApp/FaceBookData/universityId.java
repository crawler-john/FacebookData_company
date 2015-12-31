package com.MyApp.FaceBookData;

import java.util.HashMap;
import java.util.List;

import com.MyApp.FaceBookCralwer.CrawlBase;
import com.MyApp.FaceBookUtil.jsonUtil;
import com.db.manager.FaceBookDB;
import com.fasterxml.jackson.databind.JsonNode;

public class universityId  extends CrawlBase {
	// 设置头信息头信息

		private static HashMap<String, String> params;
		static {
			params = new HashMap<String, String>();
			params.put("Host", "graph.facebook.com");
			params.put("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; WOW64; rv:42.0) Gecko/20100101 Firefox/42.0");
		}
		public universityId(String url) {
			
		readJsonByGet(url, params, "utf-8");
		}
		

	
		public static String accessToken = "CAATvgqd5bWoBAPF91aJVlVbTtSw0nz6rUci1fBjRWcjtBrSGsWkkGAqeXMYDq08s7f1FMt0wymFYNE8N1PbMRMxZAajOql5fumgELCZAviBvARNBvCIKmsdtT6CILReVXYAaThTIZBNSJHht08maq5lYDn8K1EZAVyclhL7IttemyDZCN52TUO4mH8Xj3mYcZD";
		
		public String getSchoolName(){
			JsonNode root = jsonUtil.jsonToObject(this.getJsonSourceCode());
			JsonNode value = root.findValue("data");
			
			return (null == value.get(0).findValue("name"))?null:value.get(0).get("name").asText();
		}
		public String getSchoolID(){
			JsonNode root = jsonUtil.jsonToObject(this.getJsonSourceCode());
			JsonNode value = root.findValue("data");
			
			return (null == value.get(0).findValue("id"))?null:value.get(0).get("id").asText();
		}
		
		
	
		
		
		public static void main(String[] args) {
			String id =args[0];
			FaceBookDB db = new FaceBookDB();
			List<String> emptySchoolName = db.getEmptySchoolNameList(id);
			System.out.println(111);
			int i = 0;
			for(String s:emptySchoolName){ 
				i++;
				try {
					String schoolName =s.replaceAll(" ", "%20");
					
					System.out.println(s);
					
					universityId universityInfo = new universityId("https://graph.facebook.com/v2.5/search?q="+schoolName+"&type=page&access_token="+accessToken);
					System.out.println(universityInfo.getSchoolID()+"  "+universityInfo.getSchoolName());
					if(universityInfo.getSchoolName().trim().equals(s))
					db.updateSchoolId(s, universityInfo.getSchoolName(), universityInfo.getSchoolID());
					if(i%800==0){
						Thread.sleep(1000*60*5);
					}

				} catch (Exception e) {
					// TODO: handle exception
					continue;
				}
				
			}
//
			
		
		}

}
