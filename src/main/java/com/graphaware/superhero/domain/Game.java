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

package com.graphaware.superhero.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author Luanne Misquitta
 */
@NodeEntity(label = "Game")
public class Game {

	@GraphId private Long graphId;
	private Long id;
	private String title;
	private int year;
	private String publisher;
	private Rating rating;
	private Set<Platform> platforms;

	@Relationship(type = "FEATURED_IN", direction = Relationship.INCOMING)
	private Set<Character> characters = new HashSet<>();

	public Game() {
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Set<Platform> getPlatforms() {
		return platforms;
	}

	public void setPlatforms(Set<Platform> platforms) {
		this.platforms = platforms;
	}

	@Relationship(type = "FEATURED_IN", direction = Relationship.INCOMING)
	public Set<Character> getCharacters() {
		return characters;
	}

	@Relationship(type = "FEATURED_IN", direction = Relationship.INCOMING)
	public void setCharacters(Set<Character> characters) {
		this.characters = characters;
	}
}
