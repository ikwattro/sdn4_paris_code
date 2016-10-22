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

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

/**
 * @author Luanne Misquitta
 */
@NodeEntity(label = "Comic")
public class Comic {

	@GraphId private Long graphId;
	private Long id;
	private String title;
	private String author;
	private String artist;
	private boolean available;
	private Binding binding;


	@DateLong
	private Date onSaleDate;

	@Relationship(type = "FEATURED_IN", direction = Relationship.INCOMING)
	private Set<Character> characters = new HashSet<>();

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Binding getBinding() {
		return binding;
	}

	public void setBinding(Binding binding) {
		this.binding = binding;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Date getOnSaleDate() {
		return onSaleDate;
	}

	public void setOnSaleDate(Date onSaleDate) {
		this.onSaleDate = onSaleDate;
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
