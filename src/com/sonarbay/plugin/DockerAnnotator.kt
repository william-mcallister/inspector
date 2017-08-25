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
        if (element is PsiLiteralExpression) {
            val literalExpression: PsiLiteralExpression = element as PsiLiteralExpression
            var value: String = if (literalExpression.value is String) literalExpression.value as String else null as String

            if (value != null && value.startsWith("FROM")) {
                val project: Project = element.project
                val key: String = value.substring(4);
                val properties: List<DockerfileProperty> = DockerUtil.findProperties(project, key) as List<DockerfileProperty>

                if (properties.size == 1) {
                    val range: TextRange = TextRange(element.textRange.startOffset + 4, element.textRange.startOffset + 4)
                    var annotation: Annotation = holder.createInfoAnnotation(range, null)
                    annotation.textAttributes = DefaultLanguageHighlighterColors.LINE_COMMENT
                } else if (properties.size == 0) {
                    val range: TextRange = TextRange(element.textRange.startOffset + 4, element.textRange.startOffset + 5)

                    holder.createErrorAnnotation(range, "Unresolved Property")//.registerFix(CreatePropertyQuickFix(key))
                }
            }

        }
    }
}