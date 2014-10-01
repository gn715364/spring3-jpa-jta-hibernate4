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
  <li>Hibernate 4.3.6 Final </li>
  <li>MYSQL 5.x </li>
</ul>

==========================
<h3>Gradle 配置：</h3>
<pre>
import org.gradle.plugins.ide.eclipse.model.Facet
apply plugin: 'java'
apply plugin: 'war'
apply plugin: 'eclipse-wtp'
compileJava {
    sourceCompatibility=1.8
    targetCompatibility=1.8
    options.encoding='UTF-8'
}
repositories {  
	mavenCentral()
}
dependencies {
	providedCompile 'javax.servlet:javax.servlet-api:3.0.1'
	providedCompile 'org.apache.tomcat:tomcat-dbcp:7.0.42'
    providedCompile 'javax.servlet:javax.servlet-api:3.0.1'
    compile group: 'org.springframework', name: 'spring-core', version: '3.2.10.RELEASE'
    compile group: 'org.springframework', name: 'spring-context', version: '3.2.10.RELEASE'
    compile group: 'org.springframework', name: 'spring-tx', version: '3.2.10.RELEASE'
    compile group: 'org.springframework', name: 'spring-orm', version: '3.2.10.RELEASE'
    compile group: 'org.springframework', name: 'spring-web', version: '3.2.10.RELEASE'
    compile group: 'org.springframework', name: 'spring-webmvc', version: '3.2.10.RELEASE'
    compile (group: 'org.hibernate', name: 'hibernate-entitymanager', version: '4.3.6.Final') {
    	exclude group: 'cglib', module: 'cglib'
    	exclude group: 'dom4j', module: 'dom4j'
    }
    compile group: 'com.atomikos', name: 'transactions-jta', version: '3.9.3'
    compile group: 'com.atomikos', name: 'transactions-jdbc', version: '3.9.3'
    compile group: 'com.atomikos', name: 'transactions-hibernate3', version: '3.9.3'
    compile group: 'mysql', name: 'mysql-connector-java', version: '5.1.30'
    compile group: 'log4j', name: 'log4j', version: '1.2.16'
    compile group: 'dom4j', name: 'dom4j', version: '1.6.1'
    
}
eclipse {
    wtp {
        facet {
            facet name: 'jst.web', type: Facet.FacetType.fixed
            facet name: 'wst.jsdt.web', type: Facet.FacetType.fixed
            facet name: 'jst.java', type: Facet.FacetType.fixed
            facet name: 'jst.web', version: '3.0'
            facet name: 'jst.java', version: '1.8'
            facet name: 'wst.jsdt.web', version: '1.0'
        }
    }
}
</pre>
