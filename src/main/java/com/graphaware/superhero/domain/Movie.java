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

import java.net.URL;
import java.util.Set;

import com.graphaware.superhero.domain.converters.UrlConverter;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.Convert;

/**
 * @author Luanne Misquitta
 */
@NodeEntity(label = "Movie")
public class Movie {

	@GraphId private Long graphId;
	private Long id;
	private String title;
	private int year;
	private Rating rating;
	@Convert(UrlConverter.class)
	private URL imdbUrl;

	@Relationship(type = "STARS")
	private Set<Role> stars;

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

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public URL getImdbUrl() {
		return imdbUrl;
	}

	public void setImdbUrl(URL imdbUrl) {
		this.imdbUrl = imdbUrl;
	}

	@Relationship(type = "STARS")
	public Set<Role> getStars() {
		return stars;
	}

	@Relationship(type = "STARS")
	public void setStars(Set<Role> stars) {
		this.stars = stars;
	}
}
