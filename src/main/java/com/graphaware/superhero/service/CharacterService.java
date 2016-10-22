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

package com.graphaware.superhero.service;

import java.util.ArrayList;
import java.util.List;

import com.graphaware.superhero.domain.Character;
import com.graphaware.superhero.domain.CharacterSummary;
import com.graphaware.superhero.repository.CharacterRepository;
import com.graphaware.superhero.repository.HeroRepository;
import com.graphaware.superhero.repository.VillainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Luanne Misquitta
 */
@Service
public class CharacterService {

	@Autowired CharacterRepository<Character> characterRepository;
	@Autowired HeroRepository heroRepository;
	@Autowired VillainRepository villainRepository;

	public List<CharacterSummary> searchByKeyword(String keyword) {
		List<Character> characters = characterRepository.findByNameLike(getKeywordParam(keyword));
		return summarizeCharacter(characters);
	}

	public List<CharacterSummary> searchHeroesByKeyword(String keyword) {
		return summarizeCharacter(heroRepository.findByNameLike(getKeywordParam(keyword)));
	}

	public List<CharacterSummary> searchVillainsByKeyword(String keyword) {
		return summarizeCharacter(villainRepository.findByNameLike(getKeywordParam(keyword)));
	}
	public Character getById(Long id) {
		return characterRepository.findById(id);
	}

	public List<Character> findRelatedCharacters(Long id) {
		return characterRepository.findRelatedCharacters(id);
	}

	private List<CharacterSummary> summarizeCharacter(List characters) {
		List<CharacterSummary> summaries = new ArrayList<>(characters.size());
		for (Object o : characters) {
			Character character = (Character) o;
			CharacterSummary summary = new CharacterSummary();
			summary.setId(character.getId());
			summary.setName(character.getName());
			summary.setAlias(character.getAlias());
			summary.setImg(character.getImg());
			summary.setComicCount(character.getComicsFeaturedIn().size());
			summary.setGameCount(character.getGamesFeaturedIn().size());
			summary.setMovieCount(character.getRoles().size());
			summaries.add(summary);
		}
		return  summaries;
	}

	private String getKeywordParam(String keyword) {
		return "*" + keyword + "*";
	}
}
