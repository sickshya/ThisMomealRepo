package co.doeat.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@Configuration
@EnableEncryptableProperties
public class JasyptConfig {
//	@Value("${jasypt.encryptor.password}")
//	// 복호화 키값(jasypt.encryptor.password)는
//	// Application 실행 시 외부 Environment 를 통해 주입받는다.
//	private String encryptKey;
//
//	@Bean(name = "jasyptStringEncryptor")
//	public StringEncryptor stringEncryptor() {
//		// java -jar -Djasypt.encryptor.password=my_jasypt_key app.jar
//		// String key = "my_jasypt_key";
//		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
//		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
//		config.setPassword(encryptKey); // 암호화할 때 사용하는 키 config.setAlgorithm("PBEWithMD5AndDES"); // 암호화 알고리즘
//										// config.setKeyObtentionIterations("1000"); // 반복할 해싱 회수
//		config.setPoolSize("1"); // 인스턴스 pool
//		config.setProviderName("SunJCE");
//		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator"); // salt 생성 클래스 
//		config.setStringOutputType("base64"); //인코딩 방식
//		encryptor.setConfig(config);
//		return encryptor;
//	}
}
