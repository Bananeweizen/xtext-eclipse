/*******************************************************************************
 * Copyright (c) 2008 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package org.eclipse.xtext.ui.editor.model;

import org.apache.log4j.Logger;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.reconciler.DirtyRegion;
import org.eclipse.jface.text.reconciler.IReconcilingStrategy;

/**
 * @author Peter Friese - Initial contribution and API
 * 
 */
public class XtextEditorModelReconcileStrategy implements IReconcilingStrategy {
	
	private static final Logger log = Logger.getLogger(XtextEditorModelReconcileStrategy.class);

	private final IEditorModelProvider editorModelProvider;

	public XtextEditorModelReconcileStrategy(IEditorModelProvider editorModelProvider) {
		this.editorModelProvider = editorModelProvider;
	}

	public void reconcile(IRegion partition) {
		try {
			IEditorModel model = editorModelProvider.getModel();
			if (model != null) {
				model.reconcile(partition);
			}
		}
		catch (Throwable t) {
			log.error("Reconciling failed. " + t);
		}
	}

	public void reconcile(DirtyRegion dirtyRegion, IRegion subRegion) {
		reconcile(subRegion);
	}

	public void setDocument(IDocument document) {
	}

}
