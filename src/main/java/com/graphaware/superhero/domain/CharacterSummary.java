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

import org.springframework.data.neo4j.annotation.QueryResult;

/**
 * @author Luanne Misquitta
 */
@QueryResult
public class CharacterSummary {

	private Long id;
	private String name;
	private String alias;
	private String img;
	private int movieCount;
	private int gameCount;
	private int comicCount;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMovieCount() {
		return movieCount;
	}

	public int getGameCount() {
		return gameCount;
	}

	public int getComicCount() {
		return comicCount;
	}

	public void setMovieCount(int movieCount) {
		this.movieCount = movieCount;
	}

	public void setGameCount(int gameCount) {
		this.gameCount = gameCount;
	}

	public void setComicCount(int comicCount) {
		this.comicCount = comicCount;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
}
