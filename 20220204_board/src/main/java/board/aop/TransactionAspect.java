package board.aop;

import java.util.Collections;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;


@Configuration
public class TransactionAspect {

	private static final String AOP_TRANSACTION_NAME = "*";
	private static final String AOP_TRANSACTION_EXPRESSION = "execution(* board..service.*Impl.*(..))";
	
	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Bean
	public TransactionInterceptor transactionAdvice() {
		MatchAlwaysTransactionAttributeSource source =
				new MatchAlwaysTransactionAttributeSource();
		
		RuleBasedTransactionAttribute transactionAttribute =
				new RuleBasedTransactionAttribute();
		
		transactionAttribute.setName(AOP_TRANSACTION_NAME);

		transactionAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class))); //Exception클래스를 상속받은 모든 예외 동작시 롤백이 발생.
		

		source.setTransactionAttribute(transactionAttribute);
		
	
		
		return new TransactionInterceptor(transactionManager, source);
	}
	
	// 실제 동작 
	@Bean
	public Advisor transactionAdviceAdvisor() {
		AspectJExpressionPointcut pointcut =
				new AspectJExpressionPointcut();
		pointcut.setExpression(AOP_TRANSACTION_EXPRESSION); //비즈니스 로직이 수행되는 serviceImpl클래스의 모든 메서드  
		
		return new DefaultPointcutAdvisor(pointcut, transactionAdvice());
	}
	
}
