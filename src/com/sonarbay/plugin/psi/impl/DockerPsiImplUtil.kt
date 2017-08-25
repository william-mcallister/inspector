package com.sonarbay.plugin.psi.impl

import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.sonarbay.plugin.DockerIcon
import com.sonarbay.plugin.psi.DockerElementFactory
import com.sonarbay.plugin.psi.DockerTypes
import com.sonarbay.plugin.psi.DockerfileProperty
import javax.swing.Icon

/**
 * Created by sh4d0wz on 8/24/17.
 */

// TODO: need to figure out why this does not work with the parser, for now we have a java version...

object DockerPsiImplUtil {
    fun getKey(element: DockerfileProperty) : String? {
        val keyNode: ASTNode? = element.getNode().findChildByType(DockerTypes.KEY)

        if (keyNode != null) {
            return keyNode.text.replace("\\\\ ", " ")
        } else {
            return null
        }
    }

    fun getValue(element: DockerfileProperty) : String? {
        val valueNode: ASTNode? = element.node.findChildByType(DockerTypes.VALUE)

        if (valueNode != null)
            return valueNode.text
        else
            return null
    }

    fun getName (element: DockerfileProperty) : String? {
        return getKey(element)
    }

    fun setName (element: DockerfileProperty, newName: String) : PsiElement {
        val keyNode: ASTNode? = element.node.findChildByType(DockerTypes.KEY)
        if (keyNode != null) {
            val property: DockerfileProperty = DockerElementFactory.createProperty(element.project, newName)
            val newKeyNode: ASTNode = property.firstChild.node
            element.node.replaceChild(keyNode, newKeyNode)
        }

        return element
    }

    fun getNameIdentifier (element: DockerfileProperty) : PsiElement? {
        val keyNode: ASTNode? = element.node.findChildByType(DockerTypes.KEY)

        if (keyNode != null)
            return keyNode.getPsi()
        else
            return null
    }

    fun getPresentation (element: DockerfileProperty) : ItemPresentation {
        return object: ItemPresentation  {
            override fun getLocationString(): String? {
                val containingFile: PsiFile = element.containingFile
                return containingFile.name
            }

            override fun getPresentableText(): String? {
                return element.key
            }

            override fun getIcon(p0: Boolean): Icon? {
                return DockerIcon.Icon
            }
        }
    }
}