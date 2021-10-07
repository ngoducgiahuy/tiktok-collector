package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import service.impl.CampaignService;

@ExtendWith(MockitoExtension.class)
public class AbstractServiceTest {
	String token = "8fc7854be9412a39df88a761e22461ab875696fe";
	Long advertiserId = 6791983391823626245L;
	String PATH = "/open_api/v1.2/campaign/get/";

	CampaignService campaignService = new CampaignService();

	@Test
	void getDataSuccess() {
		String response = "{"
				+ "  \"message\": \"OK\","
				+ "  \"code\": 0,"
				+ "  \"data\": {"
				+ "    \"page_info\": {"
				+ "      \"total_number\": 40,"
				+ "      \"page\": 1,"
				+ "      \"page_size\": 2,"
				+ "      \"total_page\": 3"
				+ "    },"
				+ "    \"list\": ["
				+ "      {"
				+ "        \"budget_mode\": \"BUDGET_MODE_INFINITE\","
				+ "        \"objective_type\": \"CONVERSIONS\","
				+ "        \"budget\": 100.0,"
				+ "        \"campaign_id\": 1658793910149174,"
				+ "        \"advertiser_id\": 6797669162035445766,"
				+ "        \"create_time\": \"2020-03-05 13:52:31\","
				+ "        \"modify_time\": \"2020-03-05 13:52:31\","
				+ "        \"campaign_name\": \"test1\""
				+ "      }"
				+ "    ]"
				+ "  },"
				+ "  \"request_id\": \"20200310084902010189072215505ECDB2\""
				+ "}";
		JSONObject objectResponse = new JSONObject(response);
		campaignService = spy(campaignService);
		List<String> fieldList = new ArrayList<String>();
		fieldList.add("advertiser_id");
		fieldList.add("campaign_id");
		fieldList.add("campaign_name");
		String fields = fieldList.stream().map(s -> String.format("\"%s\"", s)).collect(Collectors.joining(", "));

		when(campaignService.getData(PATH, token, advertiserId, 1, 20, fields)).thenReturn(objectResponse);
		when(campaignService.getData(PATH, token, advertiserId, 2, 20, fields)).thenReturn(objectResponse);

		JSONArray arrResult = campaignService.getListWithAllData(PATH, token, advertiserId, fieldList);
		assertEquals(arrResult.length(), 2);
	}
}
