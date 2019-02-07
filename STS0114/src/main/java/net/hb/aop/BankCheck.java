package net.hb.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class BankCheck {
	
	@Before("execution(public * net.hb.order.GuestDAO.dbDetail(..))")
	public void open() {
		System.out.println("open�Լ� �������");
		System.out.println("ActiveX��ġ\n");
	}//end
	
	@After("execution(public * net.hb.order.GuestDAO.dbDetail(..))")
	public void close() {
		System.out.println("close�Լ� �˸��޼���");
		System.out.println("�ŷ������� ����\n");
	}//end
	
}//class END
