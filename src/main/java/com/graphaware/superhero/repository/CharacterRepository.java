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

package com.graphaware.superhero.repository;

import java.util.List;

import com.graphaware.superhero.domain.Character;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Luanne Misquitta
 */
public interface CharacterRepository<T extends Character> extends GraphRepository<T> {

	T findById(Long id);

	List<T> findByNameLike(String keyword);

	@Query(" MATCH (c:Character {id: {characterId}}) " +
			"OPTIONAL MATCH (c)-[:ALLY_OF|ENEMY_OF]-(other) " +
			"WITH c, collect(other) as others " +
			"OPTIONAL MATCH (c)-[:MEMBER_OF|FEATURED_IN]->()<-[:MEMBER_OF|FEATURED_IN]-(teamMember) " +
			"WITH c, others + collect(teamMember) as othersWithTeam " +
			"OPTIONAL MATCH (c)<-[:STARS]-()-[:STARS]->(actors) " +
			"WITH othersWithTeam + collect(actors) as allOthers " +
			"UNWIND allOthers as related " +
			"WITH count(*) as count, related " +
			"RETURN related ORDER BY count DESC")
	List<Character> findRelatedCharacters(@Param("characterId") Long id);

}
