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

package com.graphaware.superhero.controller;

import com.graphaware.superhero.domain.Comic;
import com.graphaware.superhero.domain.Game;
import com.graphaware.superhero.domain.Movie;
import com.graphaware.superhero.service.ComicService;
import com.graphaware.superhero.service.GameService;
import com.graphaware.superhero.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Luanne Misquitta
 */
@RestController
public class MediaController {

	@Autowired MovieService movieService;
	@Autowired GameService gameService;
	@Autowired ComicService comicService;

	@RequestMapping(value = "/movies/{id}", method = RequestMethod.GET)
	public Movie getMovieById(@PathVariable("id") Long id) {
		return movieService.getById(id);
	}

	@RequestMapping(value = "/comics/{id}", method = RequestMethod.GET)
	public Comic getComicById(@PathVariable("id") Long id) {
		return comicService.getById(id);
	}

	@RequestMapping(value = "/games/{id}", method = RequestMethod.GET)
	public Game getGameById(@PathVariable("id") Long id) {
		return gameService.getById(id);
	}
}
