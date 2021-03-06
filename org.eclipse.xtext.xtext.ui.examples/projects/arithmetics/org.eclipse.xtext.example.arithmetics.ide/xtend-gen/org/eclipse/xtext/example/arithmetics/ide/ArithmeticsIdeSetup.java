/**
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.xtext.example.arithmetics.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.xtext.example.arithmetics.ArithmeticsRuntimeModule;
import org.eclipse.xtext.example.arithmetics.ArithmeticsStandaloneSetup;
import org.eclipse.xtext.example.arithmetics.ide.ArithmeticsIdeModule;

/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
@SuppressWarnings("all")
public class ArithmeticsIdeSetup extends ArithmeticsStandaloneSetup {
  @Override
  public Injector createInjector() {
    ArithmeticsRuntimeModule _arithmeticsRuntimeModule = new ArithmeticsRuntimeModule();
    ArithmeticsIdeModule _arithmeticsIdeModule = new ArithmeticsIdeModule();
    return Guice.createInjector(_arithmeticsRuntimeModule, _arithmeticsIdeModule);
  }
}
