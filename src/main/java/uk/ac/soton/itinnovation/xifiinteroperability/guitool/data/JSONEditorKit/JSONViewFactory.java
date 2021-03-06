/////////////////////////////////////////////////////////////////////////
//
// © University of Southampton IT Innovation Centre, 2017
//
// Copyright in this library belongs to the University of Southampton
// University Road, Highfield, Southampton, UK, SO17 1BJ
//
// This software may not be used, sold, licensed, transferred, copied
// or reproduced in whole or in part in any manner or form or in or
// on any media by any person other than in accordance with the terms
// of the Licence Agreement supplied with the software, or otherwise
// without the prior written consent of the copyright owners.
//
// This software is distributed WITHOUT ANY WARRANTY, without even the
// implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
// PURPOSE, except where stated in the Licence Agreement supplied with
// the software.
//
// Created By : Nikolay Stanchev - ns17@it-innovation.soton.ac.uk
//
/////////////////////////////////////////////////////////////////////////
//
//  License : GNU Lesser General Public License, version 3
//
/////////////////////////////////////////////////////////////////////////

package uk.ac.soton.itinnovation.xifiinteroperability.guitool.data.JSONEditorKit;

import javax.swing.text.AbstractDocument;
import javax.swing.text.BoxView;
import javax.swing.text.ComponentView;
import javax.swing.text.Element;
import javax.swing.text.LabelView;
import javax.swing.text.StyleConstants;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

/**
 * a View factory, which creates the appropriate views based on the name of the element
 *
 * Project acknowledgements - developed in FIESTA (http://www.fiesta-iot.eu)
 *
 * @author Nikolay Stanchev
 */
public class JSONViewFactory implements ViewFactory {

    @Override
    public View create(Element elem) {
        String type = elem.getName();

        if (type != null){
            switch (type) {
                case JSONDocument.ROOT_ELEMENT:
                    return new IndentView(elem, false);
                case JSONDocument.INDENT_ELEMENT:
                    return new IndentView(elem, true);
                case JSONDocument.KEY_ELEMENT:
                    return new IndentView(elem, false);
                case JSONDocument.ROW_START_ELEMENT:
                case JSONDocument.ROW_END_ELEMENT:
                    return new BoxView(elem, View.X_AXIS) {
                        @Override
                        public float getAlignment(int axis) {
                            return 0;
                        }
                        @Override
                        public float getMaximumSpan(int axis) {
                            return getPreferredSpan(axis);
                        }
                    };
                case JSONDocument.JSON_KEY_NAME:
                    return new JSONLabelView(elem);
                case JSONDocument.JSON_VALUE_NAME:
                    return new JSONLabelView(elem);
                case AbstractDocument.ContentElementName:
                    return new LabelView(elem);
                case AbstractDocument.SectionElementName:
                    return new BoxView(elem, View.Y_AXIS);
                case StyleConstants.ComponentElementName:
                    return new ComponentView(elem);
                case StyleConstants.IconElementName:
                default:
                    break;
            }
        }

        return new LabelView(elem);
    }

}
