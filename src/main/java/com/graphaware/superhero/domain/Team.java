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

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author Luanne Misquitta
 */
@NodeEntity(label = "Team")
public class Team {

	private Long id;
	private String name;
	private String operationsBase;

	@Relationship(type = "MEMBER_OF", direction = "INCOMING")
	private Set<Character> members = new HashSet<>();
	public Team() {
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOperationsBase() {
		return operationsBase;
	}

	public void setOperationsBase(String operationsBase) {
		this.operationsBase = operationsBase;
	}

	@Relationship(type = "MEMBER_OF", direction = "INCOMING")
	public Set<Character> getMembers() {
		return members;
	}

	@Relationship(type = "MEMBER_OF", direction = "INCOMING")
	public void setMembers(Set<Character> members) {
		this.members = members;
	}
}

