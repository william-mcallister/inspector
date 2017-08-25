package com.sonarbay.plugin.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFileFactory
import com.sonarbay.plugin.DockerFiletype

/**
 * Created by sh4d0wz on 8/24/17.
 */
class DockerElementFactory {
    companion object {
        fun createProperty(project: Project, name: String, value: String): DockerfileProperty {
            val file: Dockerfile = createFile(project, name + " = " + value)

            return file.firstChild as DockerfileProperty
        }

        fun createProperty(project: Project, name: String): DockerfileProperty {
            val file: Dockerfile = createFile(project, name)

            return file.firstChild as DockerfileProperty
        }

        fun createCRLF(project: Project): PsiElement {
            val file: Dockerfile = createFile(project, "\n")

            return file.firstChild
        }

        fun createFile(project: Project, text: String): Dockerfile {
            val name: String = "dummy.simple"

            return PsiFileFactory.getInstance(project).createFileFromText(name, DockerFiletype.INSTANCE, text) as Dockerfile
        }
    }
}