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

package com.graphaware.superhero.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.graphaware.superhero.domain.Character;
import com.graphaware.superhero.domain.CharacterSummary;
import com.graphaware.superhero.domain.Hero;
import com.graphaware.superhero.domain.Villain;
import com.graphaware.superhero.repository.CharacterRepository;
import com.graphaware.superhero.repository.HeroRepository;
import com.graphaware.superhero.repository.VillainRepository;
import com.graphaware.superhero.service.CharacterService;
import com.graphaware.superhero.test.context.TestContext;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.Utils;
import org.neo4j.ogm.testutil.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Luanne Misquitta
 */
@ContextConfiguration(classes = {TestContext.class})
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class CharacterIntegrationTest {

	@Autowired CharacterRepository characterRepository;
	@Autowired VillainRepository villainRepository;
	@Autowired HeroRepository heroRepository;
	@Autowired CharacterService characterService;
	@Autowired Session session;

	@After
	public void tearDown() {
		session.purgeDatabase();
	}

	@Test
	public void shouldBeAbleToSearchForCharacters() {
		Hero superman = new Hero("Superman");
		heroRepository.save(superman);

		Hero batman = new Hero("Batman");
		heroRepository.save(batman);

		Villain joker = new Villain("Joker");
		villainRepository.save(joker);

		Villain blackManta = new Villain("Black Manta");
		villainRepository.save(blackManta);

		session.clear();

		List<CharacterSummary> results = characterService.searchByKeyword("bat");
		assertNotNull(results);
		assertEquals(1, results.size());
		assertEquals(batman.getName(), results.get(0).getName());

		results = characterService.searchByKeyword("man");
		assertNotNull(results);
		assertEquals(3, results.size());
		Set<String> resultNames = new HashSet<>();
		for (CharacterSummary character : results) {
			resultNames.add(character.getName());
		}
		assertTrue(resultNames.contains(superman.getName()));
		assertTrue(resultNames.contains(batman.getName()));
		assertTrue(resultNames.contains(blackManta.getName()));

		results = characterService.searchByKeyword("cat");
		assertNotNull(results);
		assertEquals(0, results.size());

		//Search for superheroes
		List<CharacterSummary> heroes = characterService.searchHeroesByKeyword("man");
		assertNotNull(heroes);
		assertEquals(2, heroes.size());
		resultNames.clear();
		for (CharacterSummary character : heroes) {
			resultNames.add(character.getName());
		}
		assertTrue(resultNames.contains(superman.getName()));
		assertTrue(resultNames.contains(batman.getName()));

		//Search for villains
		List<CharacterSummary> villains = characterService.searchVillainsByKeyword("man");
		assertNotNull(results);
		assertEquals(1, villains.size());
		assertEquals(blackManta.getName(),villains.get(0).getName());
	}

	@Test
	public void shouldLoadCharacterRelationships() {
		session.query(TestUtils.readCQLFile("graphdata.cyp").toString(), Utils.map());
		session.clear();

		Character character = characterService.getById(7l);
		assertNotNull(character);
		assertEquals("Black Manta", character.getName());
		assertEquals(1, character.getEnemies().size());
		assertEquals("Aquaman", character.getEnemies().iterator().next().getName());
		assertEquals(0, character.getAllies().size());
		assertEquals(2, character.getComicsFeaturedIn().size());
		assertEquals(1, character.getGamesFeaturedIn().size());
		assertEquals(0, character.getRoles().size());
	}

	@Test
	public void searchShouldReturnCharacterSummary() {
		session.query(TestUtils.readCQLFile("graphdata.cyp").toString(), Utils.map());
		session.clear();

		List<CharacterSummary> results = characterService.searchByKeyword("batman");
		assertEquals(1, results.size());
		CharacterSummary batman = results.get(0);
		assertEquals("Batman", batman.getName());
		assertEquals(9, batman.getComicCount());
		assertEquals(5, batman.getGameCount());
		assertEquals(6, batman.getMovieCount());
	}




}
