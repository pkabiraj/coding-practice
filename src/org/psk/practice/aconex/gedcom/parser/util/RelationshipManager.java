package org.psk.practice.aconex.gedcom.parser.util;

import java.util.Iterator;

import org.psk.practice.aconex.gedcom.parser.model.Node;
import org.psk.practice.aconex.gedcom.parser.model.ParentNode;
import org.psk.practice.aconex.gedcom.parser.model.ParentNodeAdapter;

/**
 * The Class RelationshipManager is an utility class to establish different
 * types of relationships between nodes.
 * 
 * @author Pinaki S Kabiraj
 */
public final class RelationshipManager {

	/**
	 * Prevent instantiation.
	 */
	private RelationshipManager() {
		// Utility class
	}

	/**
	 * Establishes relationship between old member in the node tree with the new
	 * member.
	 * 
	 * @param oldMember the old member
	 * @param newMember the new member
	 */
	public static void establishRelation(Node oldMember, Node newMember) {
		if (newMember.getLevel() == oldMember.getLevel()) {
			// If current node and previous nodes are at same level
			RelationshipManager.establishSiblingRelation(oldMember, newMember);
		} else if (newMember.getLevel() > oldMember.getLevel()) {
			// If current node is a child of previous node
			RelationshipManager.establishChildRelation(oldMember, newMember);
		} else {
			// If current should be somewhere up in the hierarchy
			RelationshipManager.establishFamilyRelation(oldMember, newMember);
		}
	}

	/**
	 * Establish sibling relation between siblings.
	 * 
	 * @param sibling1 the sibling1
	 * @param sibling2 the sibling2
	 */
	public static void establishSiblingRelation(Node sibling1, Node sibling2) {
		sibling2.setParent(sibling1.getParent());
		((ParentNode) sibling1.getParent()).addChild(sibling2);
	}

	/**
	 * Establish parent child relation.
	 * 
	 * @param parent the parent
	 * @param child the child
	 */
	public static void establishChildRelation(Node parent, Node child) {
		ParentNode parentNode = null;
		if (parent.getLevel() > 0) {
			// Convert Leaf node to parent node because it has children now
			parentNode = new ParentNodeAdapter(parent, GedcomConstants.ATTR_VALUE);

			// Replace parent with parentNode to reflect that it is now a parent
			// node
			ParentNode node = (ParentNode) parent.getParent();
			Iterator<Node> iterator = node.getChildren().iterator();
			while (iterator.hasNext()) {
				Node childNode = iterator.next();
				if (childNode.equals(parent)) {
					iterator.remove();
					break;
				}
			}
			node.addChild(parentNode);
		} else {
			parentNode = (ParentNode) parent;
		}

		child.setParent(parentNode);
		parentNode.addChild(child);
	}

	/**
	 * Establish family member relation as somewhere in top of the family tree.
	 * 
	 * @param familyMember the family member
	 * @param newMember the new member
	 */
	public static void establishFamilyRelation(Node familyMember, Node newMember) {
		Node temp = familyMember;
		while (temp.getLevel() != newMember.getLevel()) {
			temp = temp.getParent();
		}
		temp = temp.getParent();

		((ParentNode) temp).addChild(newMember);
		newMember.setParent(temp);
	}
}
