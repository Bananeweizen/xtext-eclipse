/*
 * generated by Xtext
 */
package org.eclipse.xtext.example.css.scoping;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.access.IJvmTypeProvider;
import org.eclipse.xtext.common.types.access.TypeNotFoundException;
import org.eclipse.xtext.common.types.xtext.AbstractTypeScopeProvider;
import org.eclipse.xtext.example.css.xcss.Filter;
import org.eclipse.xtext.example.css.xcss.Selector;
import org.eclipse.xtext.example.css.xcss.StyleRule;
import org.eclipse.xtext.example.css.xcss.XcssPackage;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.xbase.scoping.XbaseScopeProvider;
import org.eclipse.xtext.xbase.scoping.newapi.INewScope;
import org.eclipse.xtext.xbase.scoping.newapi.SingletonScope;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.inject.Inject;

/**
 * This class contains custom scoping description.
 * 
 * see : http://www.eclipse.org/Xtext/documentation/latest/xtext.html#scoping
 * on how and when to use it 
 *
 */
public class XcssScopeProvider extends XbaseScopeProvider {

	public static final QualifiedName ASSIGN_COLON = QualifiedName.create(":");
	
	@Inject
	private AbstractTypeScopeProvider typeScopeProvider;
	
	@Override
	public IScope getScope(EObject context, EReference reference) {
		if (reference == XcssPackage.Literals.COLOR_CONSTANT__CONSTANT) {
			IJvmTypeProvider typeProvider = typeScopeProvider.getTypeProvider(context.eResource().getResourceSet());
			try {
				JvmType type = typeProvider.findTypeByName("org.eclipse.swt.SWT");
				return typeScopeProvider.createMemberScope(type, new Predicate<JvmMember>() {
					
					public boolean apply(JvmMember input) {
						if (!(input instanceof JvmField))
							return false;
						JvmField field = (JvmField) input;
						if (!field.isStatic())
							return false;
						return field.getSimpleName().startsWith("COLOR_");
					}
				}, new Function<JvmMember, QualifiedName>() {
					public QualifiedName apply(JvmMember from) {
						return QualifiedName.create(from.getSimpleName().substring(6).toLowerCase());
					}
				}, IScope.NULLSCOPE);
			} catch(TypeNotFoundException tnfe) {
				return IScope.NULLSCOPE;
			}
		}
		return super.getScope(context, reference);
	}
	
	@Override
	protected INewScope createLocalVarScope(EObject context,
			EReference reference, INewScope parentScope) {
		if (context instanceof Filter) {
			Selector selector = EcoreUtil2.getContainerOfType(context, Selector.class);
			return new SingletonScope(EObjectDescription.create(THIS, selector), parentScope);
		}
		if (context instanceof StyleRule) {
			List<Selector> selectors = ((StyleRule) context).getSelectors();
			if (!selectors.isEmpty()) {
				Selector selector = selectors.get(0);
				return new SingletonScope(EObjectDescription.create(THIS, selector), parentScope);
			}
			return INewScope.NULL_SCOPE;
		}
		return super.createLocalVarScope(context, reference, parentScope);
	}
	
	@Override
	protected QualifiedName getAssignmentOperator() {
		return ASSIGN_COLON;
	}
	
}
