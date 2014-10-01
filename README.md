spring3-jpa-jta-hibernate4
==========================
實作多條datasource連線

參考環境：
<ul>
  <li>Tomcat 7.x </li>
  <li>JDK 1.8 </li>
  <li>Eclipse(compiler 1.8) </li>
  <li>Gradle </li>
  <li>Spring 3.2.10 </li>
  <li>Atomikos 3.9.3 </li>
  <li>Hibernate <strike>4.3.6 Final</strike> 4.2.15.Final</li>
  <li>MYSQL 5.x </li>
</ul>

註：使用Hibernate 4.3.x Final目前會遇到 以下的exception(日後有解會改)
<pre>
java.lang.NullPointerException
	at org.hibernate.engine.transaction.internal.jta.JtaStatusHelper.getStatus(JtaStatusHelper.java:76)
	at org.hibernate.engine.transaction.internal.jta.JtaStatusHelper.isActive(JtaStatusHelper.java:118)
	at org.hibernate.engine.transaction.internal.jta.CMTTransaction.join(CMTTransaction.java:149)
	at org.hibernate.jpa.spi.AbstractEntityManagerImpl.joinTransaction(AbstractEntityManagerImpl.java:1602)
	at org.hibernate.jpa.spi.AbstractEntityManagerImpl.postInit(AbstractEntityManagerImpl.java:210)
	at org.hibernate.jpa.internal.EntityManagerImpl.<init>(EntityManagerImpl.java:91)
	at org.hibernate.jpa.internal.EntityManagerFactoryImpl.internalCreateEntityManager(EntityManagerFactoryImpl.java:345)
	at org.hibernate.jpa.internal.EntityManagerFactoryImpl.createEntityManager(EntityManagerFactoryImpl.java:313)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:491)
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean.invokeProxyMethod(AbstractEntityManagerFactoryBean.java:376)
	at org.springframework.orm.jpa.AbstractEntityManagerFactoryBean$ManagedEntityManagerFactoryInvocationHandler.invoke(AbstractEntityManagerFactoryBean.java:519)
	at com.sun.proxy.$Proxy16.createEntityManager(Unknown Source)
	at org.springframework.orm.jpa.EntityManagerFactoryUtils.doGetTransactionalEntityManager(EntityManagerFactoryUtils.java:202)
	at org.springframework.orm.jpa.SharedEntityManagerCreator$SharedEntityManagerInvocationHandler.invoke(SharedEntityManagerCreator.java:211)
	at com.sun.proxy.$Proxy20.persist(Unknown Source)
	at com.byteslounge.spring.tx.dao.impl.TableOneDaoImpl.save(TableOneDaoImpl.java:23)
	at com.byteslounge.spring.tx.service.impl.TransactionalServiceImpl.persist(TransactionalServiceImpl.java:25)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:491)
	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:317)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:183)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:150)
	at org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:96)
	at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:260)
	at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:94)
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:172)
	at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:204)
	at com.sun.proxy.$Proxy24.persist(Unknown Source)
	at com.byteslounge.spring.tx.servlet.TestServlet.handleRequest(TestServlet.java:34)
	at org.springframework.web.context.support.HttpRequestHandlerServlet.service(HttpRequestHandlerServlet.java:68)
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:728)
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:305)
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:210)
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:222)
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:123)
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:502)
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:171)
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:99)
	at org.apache.catalina.valves.AccessLogValve.invoke(AccessLogValve.java:953)
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:118)
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:408)
	at org.apache.coyote.http11.AbstractHttp11Processor.process(AbstractHttp11Processor.java:1023)
	at org.apache.coyote.AbstractProtocol$AbstractConnectionHandler.process(AbstractProtocol.java:589)
	at org.apache.tomcat.util.net.JIoEndpoint$SocketProcessor.run(JIoEndpoint.java:310)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:724)
</pre>
==========================
<h3>使用方式：</h3>
<pre>
<bean id="dataSource1" class="com.atomikos.jdbc.AtomikosDataSourceBean"
	destroy-method="close">
	<property name="uniqueResourceName" value="DataSource1" />
	<property name="xaDataSource" ref="dataBase1" />
	<property name="poolSize" value="3" />
</bean>
<b>
<bean id="dataBase1" class="com.mysql.jdbc.jdbc2.optional.MysqlXADataSource"
	lazy-init="true">
	<property name="pinGlobalTxToPhysicalConnection" value="true" />
	<property name="user" value="root" />
	<property name="password" value="1234" />
	<property name="url" value="jdbc:mysql://localhost:3306/mysql" />
</bean>
</b>
<bean id="dataSource2" class="com.atomikos.jdbc.AtomikosDataSourceBean"
    destroy-method="close">
	<property name="uniqueResourceName" value="DataSource2" />
	<property name="xaDataSource" ref="dataBase2" />
	<property name="poolSize" value="3" />
</bean>
<b>
<bean id="dataBase2" class="com.mysql.jdbc.jdbc2.optional.MysqlXADataSource"
	lazy-init="true">
	<property name="pinGlobalTxToPhysicalConnection" value="true" />
	<property name="user" value="root" />
	<property name="password" value="1234" />
	<property name="url" value="jdbc:mysql://localhost:3306/test" />
</bean>
</b>
</pre>

