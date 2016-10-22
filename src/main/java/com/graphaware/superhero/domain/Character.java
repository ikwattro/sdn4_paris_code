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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author Luanne Misquitta
 */
@JsonIdentityInfo(generator = JSOGGenerator.class)
@NodeEntity(label = "Character")
public class Character {

	@JsonProperty("id")
	@GraphId Long graphId;

	private Long id;
	private String name;
	private String alias;
	private String realName;
	private String img;

	@Relationship(type = "ALLY_OF", direction = Relationship.UNDIRECTED)
	Set<Character> allies = new HashSet<>();

	@Relationship(type = "ENEMY_OF", direction = Relationship.UNDIRECTED)
	Set<Character> enemies = new HashSet<>();

	@Relationship(type = "FEATURED_IN")
	Set<Game> gamesFeaturedIn = new HashSet<>();

	@Relationship(type = "FEATURED_IN")
	Set<Comic> comicsFeaturedIn = new HashSet<>();

	@Relationship(type = "STARS", direction = Relationship.INCOMING)
	Set<Role> roles = new HashSet<>();

	@Relationship(type = "MEMBER_OF")
	Set<Team> teams = new HashSet<>();

	public Character() {
	}

	Character(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getAlias() {
		return alias;
	}

	public String getRealName() {
		return realName;
	}

	public Long getId() {
		return id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Relationship(type = "ALLY_OF", direction = Relationship.UNDIRECTED)
	public Set<Character> getAllies() {
		return allies;
	}

	@Relationship(type = "ENEMY_OF", direction = Relationship.UNDIRECTED)
	public Set<Character> getEnemies() {
		return enemies;
	}

	@Relationship(type = "FEATURED_IN")
	public Set<Game> getGamesFeaturedIn() {
		return gamesFeaturedIn;
	}

	@Relationship(type = "FEATURED_IN")
	public Set<Comic> getComicsFeaturedIn() {
		return comicsFeaturedIn;
	}

	@Relationship(type = "STARS", direction = Relationship.INCOMING)
	public Set<Role> getRoles() {
		return roles;
	}

	@Relationship(type = "MEMBER_OF")
	public Set<Team> getTeams() {
		return teams;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Relationship(type = "ALLY_OF", direction = Relationship.UNDIRECTED)
	public void setAllies(Set<Character> allies) {
		this.allies = allies;
	}

	@Relationship(type = "ENEMY_OF", direction = Relationship.UNDIRECTED)
	public void setEnemies(Set<Character> enemies) {
		this.enemies = enemies;
	}

	@Relationship(type = "FEATURED_IN")
	public void setGamesFeaturedIn(Set<Game> gamesFeaturedIn) {
		this.gamesFeaturedIn = gamesFeaturedIn;
	}

	@Relationship(type = "FEATURED_IN")
	public void setComicsFeaturedIn(Set<Comic> comicsFeaturedIn) {
		this.comicsFeaturedIn = comicsFeaturedIn;
	}

	@Relationship(type = "STARS", direction = Relationship.INCOMING)
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Relationship(type = "MEMBER_OF")
	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}
}
