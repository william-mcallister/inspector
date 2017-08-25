package com.sonarbay.plugin

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.indexing.FileBasedIndex
import com.sonarbay.plugin.psi.Dockerfile
import com.sonarbay.plugin.psi.DockerfileProperty
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by sh4d0wz on 8/24/17.
 */
object DockerUtil {

    fun findProperties(project: Project, key: String): List<DockerfileProperty> {
        var result: MutableList<DockerfileProperty> = ArrayList<DockerfileProperty> ()
        val virtualFiles: Collection<VirtualFile> = FileBasedIndex.getInstance()
                .getContainingFiles(FileTypeIndex.NAME, DockerFiletype.INSTANCE, GlobalSearchScope.allScope(project))

        for (vf: VirtualFile in virtualFiles) {
            val dockerfile: Dockerfile = PsiManager.getInstance(project).findFile(vf) as Dockerfile

            if (dockerfile != null) {
                val properties: Array<DockerfileProperty> = PsiTreeUtil.getChildrenOfType(dockerfile, DockerfileProperty::class.java) as Array<DockerfileProperty>
                if (properties != null) {
                    for (property: DockerfileProperty in properties) {
                        if (key.equals(property.key)) {
                            if (result == null)
                                result = ArrayList<DockerfileProperty>()

                            result.add (property)
                        }
                    }
                }
            }
        }

        return if (result != null) result else Collections.emptyList()
    }

    fun findProperties (project: Project) : List<DockerfileProperty> {
        var result : MutableList<DockerfileProperty> = ArrayList<DockerfileProperty> ()
        val virtualFiles : Collection<VirtualFile> = FileBasedIndex.getInstance()
                .getContainingFiles(FileTypeIndex.NAME, DockerFiletype.INSTANCE, GlobalSearchScope.allScope(project))

        for (vf: VirtualFile in virtualFiles) {
            val dockerfile: Dockerfile = PsiManager.getInstance(project).findFile(vf) as Dockerfile

            if (dockerfile != null) {
                val properties: Array<DockerfileProperty> = PsiTreeUtil.getChildrenOfType(dockerfile, DockerfileProperty::class.java) as Array<DockerfileProperty>

                if (properties != null)
                    result.addAll(properties)
            }
        }

        return result
    }
}