package com.MyApp.FaceBookData;

import com.db.manager.FaceBookDB;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		FaceBookDB db = new FaceBookDB();
		//String accessToken = "CAACEdEose0cBAGCAuWE20jZA12D3wqpMXEGDjZAkT4YsZBITO17i3v9HGsouUIuuq1JOCJttkNg1ViEHcYibiGkCO5EqfkV2Xmeg2DjI5TxVZBDKdU9hSpwS8DVcQcyZBJwBhkZAEANwEBHvkwnRgy0RHveSlXt7hmxRzy00YQPPdupAX4B7Wm5OiaOkPSD0Cnqm2Gzo4gLGRwgdHEIRwt";
		String accessToken = "CAATvgqd5bWoBAAaZC1SBopDBg1YZB88DNO89GXv1bAguGGVA6aZCAZCi6ZAi2VZBE7UpBIqu4wJWZAMu9dhiznHKT1MMkE0U9ob4veZCrq45gjD6hFSk8G7Wxu139tkpanZCHcOgc6Wt2VBESdUunyXFUNDhrPLLQRXoZASHzwrs5Fq5kZCgbUojbCEElCToaEnBZCwZD";
		String str;
		while(null !=( str= db.randomGetSchoolID())){
			try {
				System.out.println(str);
				//通过url读取JSON数据
				universityInfo universityInfo = new universityInfo(
						"https://graph.facebook.com/v2.5/"
								+ db.randomGetSchoolID()
								+ "?fields=id,name,about,can_post,category_list,cover,description,emails,founded,general_info,global_brand_page_name,is_always_open,is_community_page,is_published,is_verified,link,location,phone,talking_about_count,username,website,were_here_count,albums,checkins,feed,likes,milestones,photos,videos&access_token="
								+ accessToken);
				//解析并且存储数据
				db.updateUniversityData(universityInfo.analyzer());
			} catch (Exception e) {
				continue;
		
			}
			
		}
		
    }
}
