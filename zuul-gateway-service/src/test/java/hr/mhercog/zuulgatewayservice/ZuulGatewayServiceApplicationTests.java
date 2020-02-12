package hr.mhercog.zuulgatewayservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest({ "spring.redis.host=localhost", "embedded.redis.port=6379", "embedded.redis.password=strongpass123" })
public class ZuulGatewayServiceApplicationTests {

	@Test
	public void contextLoads() {
	}
}
