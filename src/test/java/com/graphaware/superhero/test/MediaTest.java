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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.graphaware.superhero.domain.Binding;
import com.graphaware.superhero.domain.Character;
import com.graphaware.superhero.domain.Comic;
import com.graphaware.superhero.domain.Game;
import com.graphaware.superhero.domain.Movie;
import com.graphaware.superhero.domain.Platform;
import com.graphaware.superhero.domain.Rating;
import com.graphaware.superhero.service.ComicService;
import com.graphaware.superhero.service.GameService;
import com.graphaware.superhero.service.MovieService;
import com.graphaware.superhero.test.context.TestContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
public class MediaTest {

	@Autowired  Session session;
	@Autowired MovieService movieService;
	@Autowired ComicService comicService;
	@Autowired GameService gameService;

	@Before
	public void setUp() {
		session.query(TestUtils.readCQLFile("graphdata.cyp").toString(), Utils.map());
		session.clear();
	}

	@After
	public void tearDown() {
		session.purgeDatabase();
	}

	@Test
	public void shouldLoadMovieCorrectly() {
		Movie movie = movieService.getById(41l);
		assertNotNull(movie);
		assertEquals("Man of Steel", movie.getTitle());
		assertEquals(2013, movie.getYear());
		assertEquals(Rating.PG_13, movie.getRating());
		assertEquals(1, movie.getStars().size());
		assertEquals("Superman", movie.getStars().iterator().next().getCharacter().getName());
		assertEquals("Henry Cavill", movie.getStars().iterator().next().getActor());
	}

	@Test
	public void shouldLoadComicCorrectly() {
		Comic comic = comicService.getById(31l);
		assertNotNull(comic);
		assertEquals("Teen Titans #20", comic.getTitle());
		assertEquals("Ian Churchill", comic.getArtist());
		assertEquals("Tom Bedard", comic.getAuthor());
		assertEquals(Binding.SOFT, comic.getBinding());
		assertFalse(comic.isAvailable());
		assertEquals(1464114600000l, comic.getOnSaleDate().getTime());
		assertEquals(2, comic.getCharacters().size());
		List<String> names = new ArrayList<>();
		for (Character character : comic.getCharacters()) {
			names.add(character.getName());
		}
		assertTrue(names.contains("Raven"));
		assertTrue(names.contains("Starfire"));
	}

	@Test
	public void shouldLoadGameCorrectly() {
		Game game = gameService.getById(35l);
		assertNotNull(game);
		assertEquals("Young Justice: Legacy", game.getTitle());
		assertEquals("Little Orbit", game.getPublisher());
		assertEquals(Rating.T, game.getRating());
		assertTrue(game.getPlatforms().contains(Platform.PLAYSTATION_3));
		assertTrue(game.getPlatforms().contains(Platform.XBOX_360));
		assertEquals(8, game.getCharacters().size());
	}


}
