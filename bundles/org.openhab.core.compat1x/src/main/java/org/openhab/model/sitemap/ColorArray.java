/**
 * Copyright (c) 2015-2015 Kai Kreuzer and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/**
 */
package org.openhab.model.sitemap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Color Array</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.openhab.model.sitemap.ColorArray#getItem <em>Item</em>}</li>
 *   <li>{@link org.openhab.model.sitemap.ColorArray#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.openhab.model.sitemap.ColorArray#getSign <em>Sign</em>}</li>
 *   <li>{@link org.openhab.model.sitemap.ColorArray#getState <em>State</em>}</li>
 *   <li>{@link org.openhab.model.sitemap.ColorArray#getArg <em>Arg</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.openhab.model.sitemap.SitemapPackage#getColorArray()
 * @model
 * @generated
 */
public interface ColorArray extends EObject
{
  /**
   * Returns the value of the '<em><b>Item</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Item</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Item</em>' attribute.
   * @see #setItem(String)
   * @see org.openhab.model.sitemap.SitemapPackage#getColorArray_Item()
   * @model
   * @generated
   */
  String getItem();

  /**
   * Sets the value of the '{@link org.openhab.model.sitemap.ColorArray#getItem <em>Item</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Item</em>' attribute.
   * @see #getItem()
   * @generated
   */
  void setItem(String value);

  /**
   * Returns the value of the '<em><b>Condition</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Condition</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Condition</em>' attribute.
   * @see #setCondition(String)
   * @see org.openhab.model.sitemap.SitemapPackage#getColorArray_Condition()
   * @model
   * @generated
   */
  String getCondition();

  /**
   * Sets the value of the '{@link org.openhab.model.sitemap.ColorArray#getCondition <em>Condition</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Condition</em>' attribute.
   * @see #getCondition()
   * @generated
   */
  void setCondition(String value);

  /**
   * Returns the value of the '<em><b>Sign</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sign</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sign</em>' attribute.
   * @see #setSign(String)
   * @see org.openhab.model.sitemap.SitemapPackage#getColorArray_Sign()
   * @model
   * @generated
   */
  String getSign();

  /**
   * Sets the value of the '{@link org.openhab.model.sitemap.ColorArray#getSign <em>Sign</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sign</em>' attribute.
   * @see #getSign()
   * @generated
   */
  void setSign(String value);

  /**
   * Returns the value of the '<em><b>State</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>State</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>State</em>' attribute.
   * @see #setState(String)
   * @see org.openhab.model.sitemap.SitemapPackage#getColorArray_State()
   * @model
   * @generated
   */
  String getState();

  /**
   * Sets the value of the '{@link org.openhab.model.sitemap.ColorArray#getState <em>State</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>State</em>' attribute.
   * @see #getState()
   * @generated
   */
  void setState(String value);

  /**
   * Returns the value of the '<em><b>Arg</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Arg</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Arg</em>' attribute.
   * @see #setArg(String)
   * @see org.openhab.model.sitemap.SitemapPackage#getColorArray_Arg()
   * @model
   * @generated
   */
  String getArg();

  /**
   * Sets the value of the '{@link org.openhab.model.sitemap.ColorArray#getArg <em>Arg</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Arg</em>' attribute.
   * @see #getArg()
   * @generated
   */
  void setArg(String value);

} // ColorArray
