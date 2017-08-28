package com.sonarbay.plugin

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder
import com.intellij.psi.PsiElement
import com.sonarbay.plugin.DockerIcon
import com.sonarbay.plugin.DockerUtil
import com.sonarbay.plugin.psi.DockerfileProperty

/**
 * Created by sh4d0wz on 8/28/17.
 */
class DockerLineMarker : RelatedItemLineMarkerProvider() {

    override fun collectNavigationMarkers(element: PsiElement, result: MutableCollection<in RelatedItemLineMarkerInfo<PsiElement>>?) {
        if (element.text.equals("FROM")) {
            val imageName: String = if (element.nextSibling.nextSibling.text is String) element.nextSibling.nextSibling.text as String else null as String
            val properties: List<DockerfileProperty> = DockerUtil.findProperties(element.project, element.text) as List<DockerfileProperty>

           if (properties.size > 0) {
                val builder: NavigationGutterIconBuilder<PsiElement> = NavigationGutterIconBuilder.create(DockerIcon.Icon).setTargets(properties)
                builder.setTooltipText("Uses container $imageName")
               result?.add(builder.createLineMarkerInfo(element))
            }
        }
    }
}