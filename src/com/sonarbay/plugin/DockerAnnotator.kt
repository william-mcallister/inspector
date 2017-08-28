package com.sonarbay.plugin

import com.intellij.lang.annotation.Annotation
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLiteralExpression
import com.sonarbay.plugin.psi.DockerfileProperty

/**
 * Created by sh4d0wz on 8/24/17.
 */
class DockerAnnotator : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {

        if (element.text.equals("FROM")) {
            val imageName: String = if (element.nextSibling.nextSibling.text is String) element.nextSibling.nextSibling.text as String else null as String
            val properties: List<DockerfileProperty> = DockerUtil.findProperties(element.project, element.text) as List<DockerfileProperty>


            // TODO: need to handle error cases (no value etc...)
            if (properties.size == 1) {
                val range: TextRange = TextRange(element.textRange.startOffset + 4, element.textRange.startOffset + 4)

                // start out with a warning until we get back some scan results.
                var annotation: Annotation = holder.createWarningAnnotation(range, "Pending security scan for $imageName container")
                annotation.textAttributes = DefaultLanguageHighlighterColors.LINE_COMMENT
            } else {
                val range: TextRange = TextRange(element.nextSibling.nextSibling.textRange.startOffset, element.nextSibling.nextSibling.textRange.startOffset)
                holder.createErrorAnnotation(range, "Unable to perform scan for $imageName")
            }
        }
    }
}