package main;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import service.impl.AdService;
import service.impl.AdgroupService;
import service.impl.CampaignService;
import utils.HibernateUtils;

public class Main {
	private static final Logger logger = Logger.getLogger(Main.class);
	
	public static void main(String[] args) throws IOException{
		if (args.length == 2) {
			importData(args[0], Long.parseLong(args[1]));
		} else {
			logger.error("It must have 2 input accessToken and advertiserId");
		}
	}

	public static void importData(String accessToken, Long advertiserId) throws IOException{
		Session session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();

		CampaignService campainService = new CampaignService();
		campainService.importData(accessToken, advertiserId, session);

		AdgroupService adgroupService = new AdgroupService();
		adgroupService.importData(accessToken, advertiserId, session);

		AdService adService = new AdService();
		adService.importData(accessToken, advertiserId, session);

		session.getTransaction().commit();
		HibernateUtils.shutdown();
	}
}
