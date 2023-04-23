package taskcreator.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class ConfigurationMongoDB extends AbstractMongoClientConfiguration {

        @Value("${spring.data.mongodb.database}")
        private String taskCreator;

        @Override
        public MongoClient mongoClient() {
            return MongoClients.create();
        }

        @Override
        protected String getDatabaseName() {
            return taskCreator;
        }

}
