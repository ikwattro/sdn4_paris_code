/*
 * Copyright [yyyy] [name of copyright owner]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.graphaware.superhero.test.context;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Luanne Misquitta
 */
@Configuration
@ComponentScan({"com.graphaware.superhero"})
@EnableNeo4jRepositories("com.graphaware.superhero.repository")
@EnableTransactionManagement
public class TestContext extends Neo4jConfiguration {

    @Override
    @Bean
    public SessionFactory getSessionFactory() {
        return new SessionFactory("com.graphaware.superhero.domain");
    }

    @Override
    @Bean
    public Session getSession() throws Exception {
        return super.getSession();
    }
}
