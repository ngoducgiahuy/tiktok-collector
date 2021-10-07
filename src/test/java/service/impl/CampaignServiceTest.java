package service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dto.CampaignDto;
import model.Campaign;

@ExtendWith(MockitoExtension.class)
public class CampaignServiceTest {

	String token = "8fc7854be9412a39df88a761e22461ab875696fe";
	Long advertiserId = 6791983391823626245L;
	CampaignService campaignService = new CampaignService();
	String jsonString = "{"
			+ "\"budget_mode\": \"BUDGET_MODE_TOTAL\","
			+ "\"status\": \"CAMPAIGN_STATUS_DISABLE\","
			+ "\"split_test_variable\": null,"
			+ "\"is_new_structure\": true,"
			+ "\"objective_type\": \"REACH\","
			+ "\"budget\": 50.0,"
			+ "\"campaign_type\": \"REGULAR_CAMPAIGN\","
			+ "\"advertiser_id\": 6791983391823626245,"
			+ "\"create_time\": \"2021-09-16 21:30:33\","
			+ "\"modify_time\": \"2021-10-07 07:13:34\","
			+ "\"objective\": \"LANDING_PAGE\","
			+ "\"campaign_name\": \"[OPTIMIZER REGRESSION] Impressions 2\","
			+ "\"opt_status\": \"DISABLE\","
			+ "\"campaign_id\": 1711095423090689"
			+ "}";
	JSONObject objectTest = new JSONObject(jsonString);
	CampaignDto campaignDtoTest = new CampaignDto(1711095423090689L, 6791983391823626245L,
			"[OPTIMIZER REGRESSION] Impressions 2", 50.0, "BUDGET_MODE_TOTAL", "CAMPAIGN_STATUS_DISABLE", "DISABLE",
			null, null, null, null, null, null, null);
	Campaign campaignTest = new Campaign(1711095423090689L, 6791983391823626245L,
			"[OPTIMIZER REGRESSION] Impressions 2", 50.0, "BUDGET_MODE_TOTAL", "CAMPAIGN_STATUS_DISABLE", "DISABLE",
			null, null, null, null, null, null, null);

	@Test
	public void convertToDto_Success() throws JsonMappingException, JsonProcessingException {
		CampaignDto campaignDto = campaignService.convertToDto(objectTest);
		assertEquals(campaignDto.getAdvertiserId(), campaignDtoTest.getAdvertiserId());
	}

	@Test
	public void convertToEntity_Success() {
		Campaign campaign = campaignService.convertToEntity(campaignDtoTest);
		assertEquals(campaign, campaignTest);
	}
}
