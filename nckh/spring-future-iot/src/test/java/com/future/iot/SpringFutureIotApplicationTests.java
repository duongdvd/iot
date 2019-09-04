package com.future.iot;

import com.future.iot.model.SensorTemperature;
import com.future.iot.repo.SensorTemperatureRepository;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
public class SpringFutureIotApplicationTests {

	private RestTemplate restTemplate;
	@Autowired
	private SensorTemperatureRepository tempRepo;

	@Before
	public void setUp() {
		HttpClientBuilder builder = HttpClientBuilder.create();
		CloseableHttpClient httpClient = builder.build();
		restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));

	}

	@Test
	public void contextLoads() {
//		String address = "http://localhost:8080/api/cbnd/save";
//
////
//	SensorTemperature sensor = new SensorTemperature();
//		sensor.setMacAddress("6A:45:EW:23:111");
//		sensor.setTemperatureValue(20f);
//		sensor.setHumidityValue(67f);
//		//String sensor = "253349E8EA88CD19D3E0806A24D6F762+CGsUpZrKX/E68U26UEr/A==";
//		//System.out.println(sensor);
//		RestTemplate restTemplate = new RestTemplate();
//
//		restTemplate.postForEntity(address, sensor, String.class);

//		RestTemplate restTemplate = new RestTemplate();
//
//
//		//String requestJson = "{\"queriedQuestion\":\"Is there pain in your hand?\"}";
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//
//		HttpEntity<String> entity = new HttpEntity<String>(sensor,headers);
//		restTemplate.postForObject(address, entity, String.class);
		SensorTemperature temperature = new SensorTemperature();
		temperature.setUpdateTime(new Date());
		//temperature.setMacAddress("6A:45:EW:23:111");
		tempRepo.save(temperature);
		//System.out.println(tempRepo.resultTimeLable("6A:45:EW:23:111", 1));



	}

}
