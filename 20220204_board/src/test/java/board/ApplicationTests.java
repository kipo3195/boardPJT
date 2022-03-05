package board;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private SqlSessionTemplate sqlSession;

	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testSqlSession() throws Exception{
		System.out.println(sqlSession.toString());
	}

}
