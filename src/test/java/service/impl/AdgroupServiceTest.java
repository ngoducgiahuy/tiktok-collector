package service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import dto.AdgroupDto;
import dto.CampaignDto;
import model.Adgroup;
import model.Campaign;

@ExtendWith(MockitoExtension.class)
public class AdgroupServiceTest {
	String token = "8fc7854be9412a39df88a761e22461ab875696fe";
	Long advertiserId = 6791983391823626245L;
	AdgroupService adgroupService = new AdgroupService();
	
	String jsonString = "{"
			+ "\"status\": \"ADGROUP_STATUS_CREATE\","
			+ "\"optimize_goal\": \"INSTALL\","
			+ "\"create_time\": \"2019-12-30 12:01:24\","
			+ "\"campaign_name\": \"xxx\","
			+ "\"adgroup_id\": 111,"
			+ "\"adgroup_name\": \"xxx\","
			+ "\"languages\": [\"kr\",\"en\"],"
			+ "\"location\": ["
			+ "    1861060"
			+ "],"
			+ "\"campaign_id\": 111,"
            + "\"opt_status\": \"ENABLE\","
			+ "\"advertiser_id\": 111,"
			+ "\"schedule_start_time\": \"2019-12-31 10:32:47\","
			+ "\"schedule_end_time\": \"2020-12-30 10:32:47\","
			+ "\"gender\": null,"
			+ "\"age\": [\"AGE_18\",\"AGE_20\"],"
			+ "\"modify_time\": \"2020-02-05 06:53:40\""
			+ "}";
	JSONObject objectTest = new JSONObject(jsonString);
	String[] arrAge = {"AGE_18", "AGE_20"};
	String[] arrLocation = {"1861060"};
	String[] arrLanguage = {"kr", "en"};
	AdgroupDto adgroupDtoTest = new AdgroupDto(111L, 111L, 111L, "xxx", "xxx", "ADGROUP_STATUS_CREATE", "ENABLE", null, null,
			null, null, arrAge, null, arrLanguage, arrLocation);
	Adgroup adgroupTest = new Adgroup(111L, 111L, 111L, "xxx", "xxx", "ADGROUP_STATUS_CREATE", "ENABLE", null, null,
			null, null, "AGE_18,AGE_20", null, "kr,en", "1861060");
	
	@Test
	public void convertToDto_Success() throws JsonMappingException, JsonProcessingException {
		AdgroupDto adgroupDto = adgroupService.convertToDto(objectTest);
		assertEquals(adgroupDto.getAdgroupId(), adgroupDtoTest.getAdgroupId());
	}
	
	@Test
	public void convertToEntity_Success() {
		Adgroup adgroup = adgroupService.convertToEntity(adgroupDtoTest);
		assertEquals(adgroup, adgroupTest);
	}
}
