/**
 *
 * Copyright (c) 2006-2015, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.codegen.lang.models;

import com.speedment.codegen.lang.interfaces.Copyable;
import com.speedment.codegen.lang.interfaces.Typeable;
import com.speedment.codegen.lang.models.modifiers.DependencyModifier;
import com.speedment.codegen.lang.models.modifiers.Modifier;
import com.speedment.util.Copier;
import java.util.EnumSet;
import java.util.Set;

/**
 *
 * @author Emil Forslund
 */
public class Import implements 
		Copyable<Import>,
		Typeable<Import>,
		DependencyModifier<Import> {
	
	private Type type;
	private final Set<Modifier> modifiers;

	public Import(Type type) {
		this.type = type;
		this.modifiers = EnumSet.noneOf(Modifier.class);
	}
	
	private Import(Import prototype) {
		type		= type.copy();
		modifiers	= Copier.copy(prototype.modifiers, c -> c.copy(), EnumSet.noneOf(Modifier.class));
	}

	@Override
	public Import setType(Type type) {
		this.type = type;
		return this;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public Set<Modifier> getModifiers() {
		return this.modifiers;
	}

	@Override
	public Import copy() {
		return new Import(this);
	}
}