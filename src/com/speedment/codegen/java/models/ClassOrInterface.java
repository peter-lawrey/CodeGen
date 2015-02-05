/*
 * Copyright 2015 Duncan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.speedment.codegen.java.models;

import com.speedment.codegen.base.CodeModel;
import com.speedment.codegen.java.interfaces.Annotable;
import com.speedment.codegen.java.interfaces.Dependable;
import com.speedment.codegen.java.interfaces.Documentable;
import com.speedment.codegen.java.interfaces.Fieldable;
import com.speedment.codegen.java.interfaces.Generable;
import com.speedment.codegen.java.interfaces.Interfaceable;
import com.speedment.codegen.java.interfaces.Methodable;
import com.speedment.codegen.java.interfaces.Modifiable;
import com.speedment.codegen.java.interfaces.Nameable;
import com.speedment.codegen.java.models.modifiers.Modifier;
import com.speedment.util.Copier;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Duncan
 * @param <T>
 */
public abstract class ClassOrInterface<T extends ClassOrInterface<T>> implements 
		CodeModel<T>,
		Nameable<T>, 
		Documentable<T>, 
		Dependable<T>,
		Generable<T>,
		Interfaceable<T>,
		Methodable<T>,
		Fieldable<T>,
		Annotable<T>,
		Modifiable<T> {
	
	private CharSequence name;
	private Optional<Javadoc> javadoc;
	private final List<AnnotationUsage> annotations;
	private final List<Import> dependencies;
	private final List<Generic> generics;
	private final List<Type> interfaces;
	private final List<Field> fields;
	private final List<Method> methods;
	private final EnumSet<Modifier> modifiers;

	public ClassOrInterface(CharSequence name) {
		this.name			= name;
		this.javadoc		= Optional.empty();
		this.annotations	= new ArrayList<>();
		this.dependencies	= new ArrayList<>();
		this.generics		= new ArrayList<>();
		this.interfaces		= new ArrayList<>();
		this.fields			= new ArrayList<>();
		this.methods		= new ArrayList<>();
		this.modifiers		= EnumSet.noneOf(Modifier.class);
	}
	
	public ClassOrInterface(ClassOrInterface<T> prototype) {
		name			= prototype.name.toString();
		javadoc			= Copier.copy(prototype.javadoc);
		annotations		= Copier.copy(prototype.annotations);
		dependencies	= Copier.copy(prototype.dependencies);
		generics		= Copier.copy(prototype.generics);
		interfaces		= Copier.copy(prototype.interfaces);
		fields			= Copier.copy(prototype.fields);
		methods			= Copier.copy(prototype.methods);
		modifiers		= Copier.copy(prototype.modifiers);
	}

	@Override
	public T setName(CharSequence name) {
		this.name = name;
		return (T) this;
	}

	@Override
	public CharSequence getName() {
		return name;
	}

	@Override
	public T setJavadoc(Javadoc doc) {
		javadoc = Optional.of(doc);
		return (T) this;
	}

	@Override
	public Optional<Javadoc> getJavadoc() {
		return javadoc;
	}

	@Override
	public T add(Import dep) {
		dependencies.add(dep);
		return (T) this;
	}

	@Override
	public List<Import> getDependencies() {
		return dependencies;
	}

	@Override
	public T add(Method method) {
		methods.add(method);
		return (T) this;
	}

	@Override
	public List<Method> getMethods() {
		return methods;
	}

	@Override
	public T add(Field field) {
		fields.add(field);
		return (T) this;
	}

	@Override
	public List<Field> getFields() {
		return fields;
	}

	@Override
	public T add(Type interf) {
		interfaces.add(interf);
		return (T) this;
	}

	@Override
	public List<Type> getInterfaces() {
		return interfaces;
	}
	
	public Type asType() {
		return new Type(name);
	}
	
	@Override
	public EnumSet<Modifier> getModifiers() {
		return modifiers;
	}

	@Override
	public T add(Generic generic) {
		generics.add(generic);
		return (T) this;
	}

	@Override
	public List<Generic> getGenerics() {
		return generics;
	}

	@Override
	public T add(AnnotationUsage annotation) {
		annotations.add(annotation);
		return (T) this;
	}

	@Override
	public List<AnnotationUsage> getAnnotations() {
		return annotations;
	}
}